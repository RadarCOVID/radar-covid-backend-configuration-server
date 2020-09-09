/*
 * Copyright (c) 2020 Gobierno de España
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package es.gob.radarcovid.configuration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.gob.radarcovid.configuration.etc.ApplicationOpenApiProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI(ApplicationOpenApiProperties properties) {
        return new OpenAPI()
                .info(new Info()
                              .title(properties.getTitle())
                              .version(properties.getVersion())
                              .description(properties.getDescription())
                              .termsOfService(properties.getTermsOfService()));
    }

}
