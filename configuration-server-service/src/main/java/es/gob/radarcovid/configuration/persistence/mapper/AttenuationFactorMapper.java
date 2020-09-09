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

import org.apache.commons.lang3.math.NumberUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import es.gob.radarcovid.configuration.api.AttenuationFactorDto;
import es.gob.radarcovid.configuration.persistence.entity.GeneralConfigurationEntity;

@Mapper(componentModel = "spring")
public interface AttenuationFactorMapper {

	AttenuationFactorMapper INSTANCE = Mappers.getMapper(AttenuationFactorMapper.class);
	
	@Mappings(value = {
			@Mapping(target="low", source="minValue", qualifiedByName = "stringToDouble"),
			@Mapping(target="medium", source="maxValue", qualifiedByName = "stringToDouble")
	})
	AttenuationFactorDto entityToDto (GeneralConfigurationEntity source);
		
    default Double stringToDouble(String string) {
        return (NumberUtils.isCreatable(string)) ? Double.parseDouble(string) : null;
    }
    
}
