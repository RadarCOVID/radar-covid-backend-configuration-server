/*
 * Copyright (c) 2020 Gobierno de España
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package es.gob.radarcovid.configuration.business.impl;

import org.springframework.stereotype.Service;

import es.gob.radarcovid.common.annotation.Loggable;
import es.gob.radarcovid.configuration.api.SettingsDto;
import es.gob.radarcovid.configuration.business.SettingsService;
import es.gob.radarcovid.configuration.persistence.ExposureConfigurationDao;
import es.gob.radarcovid.configuration.persistence.GeneralConfigurationDao;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SettingsServiceImpl implements SettingsService {

	private final ExposureConfigurationDao exposureConfigurationDao;
	private final GeneralConfigurationDao generalConfigurationDao;

	@Loggable
	@Override
	public SettingsDto getSettings() {
		SettingsDto settingsDto = null;
		settingsDto = generalConfigurationDao.getGeneralConfiguration();
		settingsDto.setExposureConfiguration(exposureConfigurationDao.getExposureConfiguration());
		return settingsDto;
	}

}
