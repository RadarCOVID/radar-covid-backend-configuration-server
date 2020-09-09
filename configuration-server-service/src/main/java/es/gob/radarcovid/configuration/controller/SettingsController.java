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

import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.gob.radarcovid.common.annotation.Loggable;
import es.gob.radarcovid.configuration.api.SettingsDto;
import es.gob.radarcovid.configuration.business.SettingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/settings")
@RequiredArgsConstructor
public class SettingsController {

    private final SettingsService service;

    @Loggable
    @GetMapping()
    @Operation(summary = "Get application settings", description = "Get application settings", tags = { "settings" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = SettingsDto.class))),
        @ApiResponse(responseCode = "400", description = "error", content = @Content(schema = @Schema(implementation = Void.class))),
        @ApiResponse(responseCode = "500", description = "error", content = @Content(schema = @Schema(implementation = Void.class)))
    })	
    public ResponseEntity<SettingsDto> getSettings() {
        
    	HttpStatus httpStatus;
    	SettingsDto settingsDto = null;
        try {
        	settingsDto = service.getSettings();
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).cacheControl(CacheControl.noStore()).body(settingsDto);
    }
}
