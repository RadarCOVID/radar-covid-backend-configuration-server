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

import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.gob.radarcovid.common.annotation.Loggable;
import es.gob.radarcovid.configuration.api.ErrorDto;
import es.gob.radarcovid.configuration.api.UuidDto;
import es.gob.radarcovid.configuration.business.ApplicationTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class ApplicationTokenController {

    private final ApplicationTokenService service;

    @Loggable
    @GetMapping(path = {"/uuid"})
    @Operation(summary = "Get application UUID token", description = "Get application UUID token", tags = { "token" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = UuidDto.class))),
        @ApiResponse(responseCode = "500", description = "error message", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })	
    public ResponseEntity<UuidDto> getUuid() {
    	
        return ResponseEntity
        		.status(HttpStatus.OK)
        		.cacheControl(CacheControl.noStore())
        		.body(service.getUuid());
    }
    
}
