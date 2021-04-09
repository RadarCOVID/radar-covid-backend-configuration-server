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

import es.gob.radarcovid.configuration.api.VenueConfigurationDto;
import es.gob.radarcovid.configuration.persistence.entity.GeneralConfigurationEntity;

@Mapper(componentModel = "spring")
public interface VenueConfigurationMapper {

	VenueConfigurationMapper INSTANCE = Mappers.getMapper(VenueConfigurationMapper.class);
	
	public static final String KEY_RECORD_NOTIFICATION = "recordNotification";
	public static final String KEY_AUTO_CHECKOUT = "autoCheckout";
	public static final String KEY_TROUBLED_PLACE_CHECK = "troubledPlaceCheck";
	public static final String KEY_QUARENTINE_AFTER_EXPOSED = "quarentineAfterExposed";
	
	default VenueConfigurationDto entityToDto(List<GeneralConfigurationEntity> source) {
		VenueConfigurationDto target = null;
		if (!CollectionUtils.isEmpty(source)) {
			target = new VenueConfigurationDto();
			target.setRecordNotification(source.stream().filter(t -> t.getLabel().equals(KEY_RECORD_NOTIFICATION))
					.map(t -> stringToLong(t.getValue())).findFirst().orElse(null));
			target.setAutoCheckout(source.stream().filter(t -> t.getLabel().equals(KEY_AUTO_CHECKOUT))
					.map(t -> stringToLong(t.getValue())).findFirst().orElse(null));
			target.setTroubledPlaceCheck(source.stream().filter(t -> t.getLabel().equals(KEY_TROUBLED_PLACE_CHECK))
					.map(t -> stringToLong(t.getValue())).findFirst().orElse(null));
			target.setQuarentineAfterExposed(source.stream().filter(t -> t.getLabel().equals(KEY_QUARENTINE_AFTER_EXPOSED))
					.map(t -> stringToLong(t.getValue())).findFirst().orElse(null));
		}
		return target;
	}

    default Long stringToLong(String string) {
        return (NumberUtils.isCreatable(string)) ? Long.parseLong(string) : null;
    }
    
}
