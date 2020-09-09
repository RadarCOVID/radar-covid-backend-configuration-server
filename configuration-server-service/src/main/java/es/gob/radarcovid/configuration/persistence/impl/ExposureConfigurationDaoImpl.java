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

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import es.gob.radarcovid.configuration.api.ExposureConfigurationDto;
import es.gob.radarcovid.configuration.persistence.ExposureConfigurationDao;
import es.gob.radarcovid.configuration.persistence.mapper.ExposureConfigurationMapper;
import es.gob.radarcovid.configuration.persistence.repository.ExposureConfigurationRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExposureConfigurationDaoImpl implements ExposureConfigurationDao {

	private final ExposureConfigurationMapper mapper;
	private final ExposureConfigurationRepository exposureConfigurationRepository;

	@Override
	public ExposureConfigurationDto getExposureConfiguration() {
		return mapper.entityToDto(exposureConfigurationRepository.findAll());
	}

}
