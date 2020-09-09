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

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class SediaExceptionHandler {

    /**
     * This method handles unknown Exceptions and Server Errors.
     *
     * @param ex the thrown exception
     * @param wr the WebRequest
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void unknownException(Exception ex, WebRequest wr) {
        log.error("Unable to handle {}", wr.getDescription(false), ex);
    }

    /**
     * This method handles Bad Requests.
     *
     * @param ex the thrown exception
     * @param wr the WebRequest
     */
    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            ServletRequestBindingException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void bindingExceptions(Exception ex, WebRequest wr) {
        log.error("Binding failed {}", wr.getDescription(false), ex);
    }

    /**
     * This method handles Validation Exceptions.
     *
     * @return ResponseEntity<?> returns Bad Request
     */
    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class
    })
    public ResponseEntity<?> handleValidationExceptions() {
        return ResponseEntity.badRequest().build();
    }

}
