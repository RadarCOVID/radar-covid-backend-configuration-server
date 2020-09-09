/*
 * Copyright (c) 2020 Gobierno de España
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package es.gob.radarcovid.configuration.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.gob.radarcovid.common.annotation.Loggable;
import es.gob.radarcovid.configuration.api.CcaaKeyValueDto;
import es.gob.radarcovid.configuration.api.KeyValueDto;
import es.gob.radarcovid.configuration.business.MasterDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/masterData")
@RequiredArgsConstructor
public class MasterDataController {

    private final MasterDataService service;
    private static final String DEFAULT_LOCALE = "es-ES";
    
    @Loggable
    @GetMapping(path = {"/locales"}, produces = "application/json")
    @Operation(summary = "Get availables locales", description = "Get availables locales", tags = { "masterData" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = KeyValueDto.class)))),
        @ApiResponse(responseCode = "400", description = "error", content = @Content(schema = @Schema(implementation = Void.class))),
        @ApiResponse(responseCode = "500", description = "error", content = @Content(schema = @Schema(implementation = Void.class)))
    })	
	public ResponseEntity<List<KeyValueDto>> getLocales(
			@RequestParam(value = "locale", required = false, defaultValue = DEFAULT_LOCALE) final String locale) {

		HttpStatus httpStatus;
		List<KeyValueDto> text = null;
		try {
			text = service.getLocales(locale);
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return ResponseEntity.status(httpStatus).cacheControl(CacheControl.maxAge(1L, TimeUnit.HOURS)).body(text);
	}
  
    @Loggable
    @GetMapping(path = {"/ccaa"}, produces = "application/json")
    @Operation(summary = "Get availables autonomous communities", description = "Get availables autonomous communities", tags = { "masterData" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CcaaKeyValueDto.class)))),
        @ApiResponse(responseCode = "400", description = "error", content = @Content(schema = @Schema(implementation = Void.class))),
        @ApiResponse(responseCode = "500", description = "error", content = @Content(schema = @Schema(implementation = Void.class)))
    })	
	public ResponseEntity<List<CcaaKeyValueDto>> getCcaa(
			@RequestParam(value = "locale", required = false, defaultValue = DEFAULT_LOCALE) final String locale,
			@RequestParam(value = "additionalInfo", required = false, defaultValue = "false") final boolean additionalInfo) {

		HttpStatus httpStatus;
		List<CcaaKeyValueDto> text = null;
		try {
			text = service.getAutonomousCommunities(locale, additionalInfo);
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return ResponseEntity.status(httpStatus).cacheControl(CacheControl.maxAge(1L, TimeUnit.HOURS)).body(text);
	}
}
