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

import java.util.concurrent.TimeUnit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.gob.radarcovid.common.annotation.Loggable;
import es.gob.radarcovid.configuration.api.TextCustomMap;
import es.gob.radarcovid.configuration.business.TextsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/texts")
@RequiredArgsConstructor
@Slf4j
public class TextsController {

    private final TextsService service;
    private static final String DEFAULT_CCAA = "ES";
	private static final String DEFAULT_LOCALE = "es-ES";

    @Loggable
    @GetMapping(produces = "application/json")
    @Operation(summary = "Get texts by locale and CCAA", description = "Get texts by locale and CCAA", tags = { "texts" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = TextCustomMap.class))),
        @ApiResponse(responseCode = "400", description = "error", content = @Content(schema = @Schema(implementation = Void.class))),
        @ApiResponse(responseCode = "500", description = "error", content = @Content(schema = @Schema(implementation = Void.class)))
    })	
	public ResponseEntity<TextCustomMap> getTexts(
			@RequestParam(value = "ccaa", required = false, defaultValue = DEFAULT_CCAA) final String ccaa,
			@RequestParam(value = "locale", required = false, defaultValue = DEFAULT_LOCALE) final String locale) {

		HttpStatus httpStatus;
		TextCustomMap text = null;
		ResponseEntity<TextCustomMap> response;
		try {
			text = service.getTexts(ccaa, locale);
			httpStatus = HttpStatus.OK;
			response = ResponseEntity.status(httpStatus).cacheControl(CacheControl.maxAge(1L, TimeUnit.HOURS)).body(text);
		} catch (Exception e) {
			log.error("Exception: {}", e.getMessage(), e);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response = ResponseEntity.status(httpStatus).body(text);
		}
		return response;
	}
    
}
