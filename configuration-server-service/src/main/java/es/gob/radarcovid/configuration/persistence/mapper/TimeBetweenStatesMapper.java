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

import org.apache.commons.lang3.math.NumberUtils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import es.gob.radarcovid.configuration.api.TimeBetweenStatesDto;
import es.gob.radarcovid.configuration.persistence.entity.GeneralConfigurationEntity;

@Mapper(componentModel = "spring")
public interface TimeBetweenStatesMapper {

	TimeBetweenStatesMapper INSTANCE = Mappers.getMapper(TimeBetweenStatesMapper.class);

	public static final String KEY_HIGHRISK_TO_LOWRISK = "HighRiskToLowRisk";
	public static final String KEY_INFECTED_TO_HEALTHY = "InfectedToHealthy";

	default TimeBetweenStatesDto entityToDto(List<GeneralConfigurationEntity> source) {
		TimeBetweenStatesDto target = null;
		if (!CollectionUtils.isEmpty(source)) {
			target = new TimeBetweenStatesDto();
			target.setHighRiskToLowRisk(source.stream().filter(t -> t.getLabel().equals(KEY_HIGHRISK_TO_LOWRISK))
					.map(t -> stringToLong(t.getValue())).findFirst().orElse(null));
			target.setInfectedToHealthy(source.stream().filter(t -> t.getLabel().equals(KEY_INFECTED_TO_HEALTHY))
					.map(t -> stringToLong(t.getValue())).findFirst().orElse(null));;
		}
		return target;
	}

    default Long stringToLong(String string) {
        return (NumberUtils.isCreatable(string)) ? Long.parseLong(string) : null;
    }
    
}
