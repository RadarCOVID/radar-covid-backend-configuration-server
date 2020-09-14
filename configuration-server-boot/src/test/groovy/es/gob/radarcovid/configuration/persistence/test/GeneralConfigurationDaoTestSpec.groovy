/**
 * Copyright (c) 2020 Gobierno de España
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package es.gob.radarcovid.configuration.persistence.test

import es.gob.radarcovid.configuration.persistence.GeneralConfigurationDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.PostgreSQLContainer
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles('test')
class GeneralConfigurationDaoTestSpec extends Specification {

    @Shared
    PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer('postgres:12-alpine')
            .withDatabaseName('RADARCOVID')
            .withUsername('radarcovid')
            .withPassword('radarcovid')

    @Autowired
    GeneralConfigurationDao generalConfigurationDao

    def 'get general configuration'() {

        when:
        def settingsDto = generalConfigurationDao.getGeneralConfiguration()

        then:
        1 == settingsDto.minRiskScore
        15 == settingsDto.minDurationForExposure
        55 == settingsDto.attenuationDurationThresholds.low
        74 == settingsDto.attenuationDurationThresholds.medium
        1.0 == settingsDto.attenuationFactor.low
        0.5 == settingsDto.attenuationFactor.medium

    }

}
