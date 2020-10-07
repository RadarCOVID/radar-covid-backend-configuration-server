/*
 * Copyright (c) 2020 Gobierno de España
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package es.gob.radarcovid.configuration.persistence.impl;

import org.springframework.stereotype.Service;

import es.gob.radarcovid.configuration.api.SettingsDto;
import es.gob.radarcovid.configuration.persistence.GeneralConfigurationDao;
import es.gob.radarcovid.configuration.persistence.mapper.GeneralConfigurationMapper;
import es.gob.radarcovid.configuration.persistence.repository.GeneralConfigurationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class GeneralConfigurationDaoImpl implements GeneralConfigurationDao {

	private final GeneralConfigurationMapper generalConfigurationMapper;
	private final GeneralConfigurationRepository generalConfigurationRepository;

	@Override
	public SettingsDto getGeneralConfiguration() {
		return generalConfigurationMapper.entityToDto(generalConfigurationRepository.findAll());
	}
	
	@Override
	public String getValueBylabel(String label, String defaultValue) {
		return generalConfigurationRepository.findByLabel(label).map(t -> t.getValue()).orElse(defaultValue);
	}
}
