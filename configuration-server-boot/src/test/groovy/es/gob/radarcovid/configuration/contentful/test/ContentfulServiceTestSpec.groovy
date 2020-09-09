/**
 * Copyright (c) 2020 Gobierno de España
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package es.gob.radarcovid.configuration.contentful.test

import es.gob.radarcovid.configuration.api.KeyValueDto
import es.gob.radarcovid.configuration.contentful.ContentfulService
import org.spockframework.spring.SpringBean
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
@ActiveProfiles('test')
@Testcontainers
class ContentfulServiceTestSpec extends Specification {

    @SpringBean
    ContentfulService contentfulService = Stub() {
        getLocales('es-ES') >> [new KeyValueDto('es-ES', 'Castellano')]
    }

    @Unroll
    def 'get locales [#locale] with id [#id] and description [#description]'(String locale, String id, String description) {
        when:
        List<KeyValueDto> list = contentfulService.getLocales(locale)

        then:
        list.first().id == id
        list.first().description == description

        where:
        locale  | id      | description
        'es-ES' | 'es-ES' | 'Castellano'
    }
}
