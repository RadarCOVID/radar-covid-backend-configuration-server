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

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import es.gob.radarcovid.configuration.api.ApplicationVersionDto;
import es.gob.radarcovid.configuration.api.VersionDto;
import es.gob.radarcovid.configuration.persistence.entity.GeneralConfigurationEntity;

@Mapper(componentModel = "spring")
public interface ApplicationVersionMapper {

	ApplicationVersionMapper INSTANCE = Mappers.getMapper(ApplicationVersionMapper.class);

	public static final String KEY_ANDROID_VERSION = "AndroidVersion";
	public static final String KEY_ANDROID_COMPILATION = "AndroidCompilation";
	public static final String KEY_ANDROID_URL = "AndroidUrl";
	public static final String KEY_IOS_VERSION = "iOSVersion";
	public static final String KEY_IOS_COMPILATION = "iOSCompilation";
	public static final String KEY_IOS_URL = "iOSUrl";

	default ApplicationVersionDto entityToDto(List<GeneralConfigurationEntity> source) {
		ApplicationVersionDto target = null;
		if (!CollectionUtils.isEmpty(source)) {
			target = new ApplicationVersionDto();
			target.setAndroid(new VersionDto(source.stream().filter(t -> t.getLabel().equals(KEY_ANDROID_VERSION))
					.map(t -> t.getValue()).findFirst().orElse(null),
					source.stream().filter(t -> t.getLabel().equals(KEY_ANDROID_COMPILATION))
					.map(t -> stringToInteger(t.getValue())).findFirst().orElse(null),
					source.stream().filter(t -> t.getLabel().equals(KEY_ANDROID_URL))
					.map(t -> t.getValue()).findFirst().orElse(null)
					));
			target.setIos(new VersionDto(source.stream().filter(t -> t.getLabel().equals(KEY_IOS_VERSION))
					.map(t -> t.getValue()).findFirst().orElse(null),
					source.stream().filter(t -> t.getLabel().equals(KEY_IOS_COMPILATION))
					.map(t -> stringToInteger(t.getValue())).findFirst().orElse(null),
					source.stream().filter(t -> t.getLabel().equals(KEY_IOS_URL))
					.map(t -> t.getValue()).findFirst().orElse(null)));
		}
		return target;
	}

    default Integer stringToInteger(String string) {
        return (StringUtils.isNumeric(string)) ? Integer.parseInt(string) : null;
    }
    
}
