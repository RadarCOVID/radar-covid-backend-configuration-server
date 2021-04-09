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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.gob.radarcovid.common.annotation.Loggable;
import es.gob.radarcovid.configuration.api.CcaaKeyValueDto;
import es.gob.radarcovid.configuration.api.ErrorDto;
import es.gob.radarcovid.configuration.api.KeyValueDto;
import es.gob.radarcovid.configuration.business.MasterDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/masterData")
@CrossOrigin(origins = { "https://test-radar.covid19.gob.es", "https://radarcovid.gob.es" })
@RequiredArgsConstructor
@Slf4j
public class MasterDataController {

    private final MasterDataService service;
    private static final String DEFAULT_LOCALE = "es-ES";

    @Value("${application.cache.master-data.locales:1}")
	private long cacheLocales;

	@Value("${application.cache.master-data.ccaa:1}")
	private long cacheCCAA;

	@Value("${application.cache.master-data.countries:1}")
	private long cacheCountries;

	@Loggable
    @GetMapping(path = {"/locales"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get availables locales", description = "Get availables locales", tags = { "masterData" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = KeyValueDto.class)))),
        @ApiResponse(responseCode = "400", description = "error", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
        @ApiResponse(responseCode = "404", description = "error", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
        @ApiResponse(responseCode = "500", description = "error", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })	
	public ResponseEntity<List<KeyValueDto>> getLocales(
			@RequestParam(value = "locale", required = false, defaultValue = DEFAULT_LOCALE) final String locale,
			@RequestParam(value = "platform", required = false) final String platform,
			@RequestParam(value = "version", required = false) final String version) {

		return ResponseEntity
				.status(HttpStatus.OK)
				.cacheControl(CacheControl.maxAge(cacheLocales, TimeUnit.HOURS))
				.body(this.service.getLocales(locale, platform, version));
	}
  
    @Loggable
    @GetMapping(path = {"/ccaa"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get availables autonomous communities", description = "Get availables autonomous communities", tags = { "masterData" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CcaaKeyValueDto.class)))),
        @ApiResponse(responseCode = "400", description = "error", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
        @ApiResponse(responseCode = "404", description = "error", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
        @ApiResponse(responseCode = "500", description = "error", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })	
	public ResponseEntity<List<CcaaKeyValueDto>> getCcaa(
			@RequestParam(value = "locale", required = false, defaultValue = DEFAULT_LOCALE) final String locale,
			@RequestParam(value = "platform", required = false) final String platform,
			@RequestParam(value = "version", required = false) final String version,
			@RequestParam(value = "additionalInfo", required = false, defaultValue = "false") final boolean additionalInfo) {

		return ResponseEntity
				.status(HttpStatus.OK)
				.cacheControl(CacheControl.maxAge(cacheCCAA, TimeUnit.HOURS))
				.body(this.service.getAutonomousCommunities(locale, platform, version, additionalInfo));
	}

	@Loggable
	@GetMapping(path = {"/countries"}, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get countries", description = "Get country list available", tags = { "masterData"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = KeyValueDto.class)))),
	        @ApiResponse(responseCode = "400", description = "error", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
	        @ApiResponse(responseCode = "404", description = "error", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
			@ApiResponse(responseCode = "500", description = "error", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
	})
	public ResponseEntity<List<KeyValueDto>> getCountries(
			@RequestParam(value = "locale", required = false, defaultValue = DEFAULT_LOCALE) final String locale,
			@RequestParam(value = "platform", required = false) final String platform,
			@RequestParam(value = "version", required = false) final String version) {

		return ResponseEntity
				.status(HttpStatus.OK)
				.cacheControl(CacheControl.maxAge(cacheCountries, TimeUnit.HOURS))
				.body(this.service.getCountries(locale, platform, version));
	}
	
}
