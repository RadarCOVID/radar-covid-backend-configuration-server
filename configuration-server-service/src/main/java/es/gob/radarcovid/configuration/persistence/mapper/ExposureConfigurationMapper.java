/*
 * Copyright (c) 2020 Gobierno de España
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package es.gob.radarcovid.configuration.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import es.gob.radarcovid.configuration.api.ExposureConfigurationDto;
import es.gob.radarcovid.configuration.persistence.entity.ExposureConfigurationEntity;

@Mapper(componentModel = "spring")
public interface ExposureConfigurationMapper {

	ExposureConfigurationMapper INSTANCE = Mappers.getMapper(ExposureConfigurationMapper.class);
	
	default ExposureConfigurationDto entityToDto(List<ExposureConfigurationEntity> source) {
		ExposureConfigurationDto exposureConfigurationDto = null;
		if ((source != null) && (!source.isEmpty())) {
			source.sort((i1,i2) -> i1.getConfigurationType().getId().compareTo(i2.getConfigurationType().getId()));
			exposureConfigurationDto = new ExposureConfigurationDto();
			exposureConfigurationDto.setTransmission(RiskLevelDataMapper.INSTANCE.entityToDto(source.get(0)));
			exposureConfigurationDto.setDuration(RiskLevelDataMapper.INSTANCE.entityToDto(source.get(1)));
			exposureConfigurationDto.setDays(RiskLevelDataMapper.INSTANCE.entityToDto(source.get(2)));
			exposureConfigurationDto.setAttenuation(RiskLevelDataMapper.INSTANCE.entityToDto(source.get(3)));
		}
		return exposureConfigurationDto;
	}
	
}
