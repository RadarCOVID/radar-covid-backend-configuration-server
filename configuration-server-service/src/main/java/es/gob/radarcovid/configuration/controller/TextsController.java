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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.gob.radarcovid.common.annotation.Loggable;
import es.gob.radarcovid.configuration.api.ErrorDto;
import es.gob.radarcovid.configuration.api.TextCustomMap;
import es.gob.radarcovid.configuration.business.TextsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/texts")
@CrossOrigin(origins = { "https://test-radar.covid19.gob.es", "https://radarcovid.gob.es" })
@RequiredArgsConstructor
@Slf4j
public class TextsController {

    private final TextsService service;
    private static final String DEFAULT_CCAA = "ES";
	private static final String DEFAULT_LOCALE = "es-ES";
	private static final String DEFAULT_APPLICATION = "ALL";

	@Value("${application.cache.texts:1}")
	private long cacheTexts;
	
    @Loggable
    @GetMapping(produces = "application/json")
    @Operation(summary = "Get texts by locale and CCAA", description = "Get texts by locale and CCAA", tags = { "texts" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = TextCustomMap.class))),
        @ApiResponse(responseCode = "400", description = "error", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
        @ApiResponse(responseCode = "404", description = "error", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
        @ApiResponse(responseCode = "500", description = "error", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })	
	public ResponseEntity<TextCustomMap> getTexts(
			@RequestParam(value = "ccaa", required = false, defaultValue = DEFAULT_CCAA) final String ccaa,
			@RequestParam(value = "locale", required = false, defaultValue = DEFAULT_LOCALE) final String locale,
			@RequestParam(value = "application", required = false, defaultValue = DEFAULT_APPLICATION) final String application,
			@RequestParam(value = "platform", required = false) final String platform,
			@RequestParam(value = "version", required = false) final String version) {

		return ResponseEntity
				.status(HttpStatus.OK)
				.cacheControl(CacheControl.maxAge(cacheTexts, TimeUnit.HOURS))
				.body(this.service.getTexts(ccaa, locale, application, platform, version));
	}
    
}
