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

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import es.gob.radarcovid.configuration.api.RiskLevelDataDto;
import es.gob.radarcovid.configuration.persistence.entity.ExposureConfigurationEntity;

@Mapper(componentModel = "spring")
public interface RiskLevelDataMapper {

	RiskLevelDataMapper INSTANCE = Mappers.getMapper(RiskLevelDataMapper.class);
	
	@Mappings(value = {
			@Mapping(target="riskLevelValue1", source="levelValue1"),
			@Mapping(target="riskLevelValue2", source="levelValue2"),
			@Mapping(target="riskLevelValue3", source="levelValue3"),
			@Mapping(target="riskLevelValue4", source="levelValue4"),
			@Mapping(target="riskLevelValue5", source="levelValue5"),
			@Mapping(target="riskLevelValue6", source="levelValue6"),
			@Mapping(target="riskLevelValue7", source="levelValue7"),
			@Mapping(target="riskLevelValue8", source="levelValue8"),
			@Mapping(target="riskLevelWeight", source="riskWeight")
	})
	RiskLevelDataDto entityToDto (ExposureConfigurationEntity source);
		
}
