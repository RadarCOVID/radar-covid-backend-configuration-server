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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.contentful.java.cda.CDAClient;

@Configuration
public class ContentfulConfiguration {

    @Value("${contentful.url}")
    private String contentfulUrl;
    @Value("${contentful.space}")
    private String contentfulSpace;
    @Value("${contentful.token}")
    private String contentfulToken;
    @Value("${contentful.environment}")
    private String contentfulEnvironment;
    
    @Bean
    public CDAClient contentfulClientConfigurer() {
        return CDAClient.builder()
                .setEndpoint(contentfulUrl)
                .setSpace(contentfulSpace)
                .setToken(contentfulToken)
                .setEnvironment(contentfulEnvironment)
                .build();
    }
}
