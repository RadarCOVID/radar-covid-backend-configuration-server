/*
 * Copyright (c) 2020 Gobierno de España
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package es.gob.radarcovid.common.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import es.gob.radarcovid.common.exception.ConfigurationServerException;
import es.gob.radarcovid.configuration.api.ErrorDto;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class SediaExceptionHandler extends ResponseEntityExceptionHandler {

	/**
     * This method handles Configuration Exceptions.
     *
     * @param exception the thrown exception
     * @param request the WebRequest
     * @return returns a response with error message
     */
    @ExceptionHandler(ConfigurationServerException.class)
    public ResponseEntity<?> handleConfigurationServerExceptions(ConfigurationServerException exception, WebRequest request) {
        log.error("Configuration server error: {}", exception.getMessage());
        return handleError(exception.getMessage(), exception, request, HttpStatus.BAD_REQUEST);
    }
	
    /**
     * This method handles unknown Exceptions and Server Errors.
     *
     * @param exception the thrown exception
     * @param request the WebRequest
	 * @return returns a response with error message
	 */
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<?> handleInternal(Exception exception, WebRequest request) {
		return handleError("Unable to handle " + request.getDescription(false), exception, request, HttpStatus.INTERNAL_SERVER_ERROR);
	}

    /**
     * This method create an ErrorDto object
     * @param errorCode code of error
     * @param errorDescription error description
     * @param request origin request
     * @param status status code
     * @return returns a ErrorDto object 
     */
	private ErrorDto createError(String errorDescription, WebRequest request, HttpStatus status) {
		return ErrorDto.builder().message(errorDescription)
				.path(((ServletWebRequest) request).getRequest().getRequestURI())
				.timestamp(Long.valueOf(System.currentTimeMillis())).status(status.value()).build();
	}
	
	/**
	 * This method handle error
	 * @param errorDescription description of error
	 * @param exception the thrown exception
	 * @param request the WebRequest
	 * @param status status code
	 * @return returns a response with error message
	 */
	private ResponseEntity<Object> handleError(String errorDescription, Exception exception, WebRequest request, HttpStatus status) {
		return handleExceptionInternal(exception, createError(errorDescription, request, status), new HttpHeaders(), status, request);
	}
}
