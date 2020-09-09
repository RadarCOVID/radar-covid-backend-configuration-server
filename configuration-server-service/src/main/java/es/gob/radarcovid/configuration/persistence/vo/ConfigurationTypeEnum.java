/*
 * Copyright (c) 2020 Gobierno de España
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package es.gob.radarcovid.configuration.persistence.vo;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public enum ConfigurationTypeEnum {

	TRANSMISSION_RISK(1),
	DURATION_RISK(2), 
	DAYS_RISK(3), 
	ATTENUATION_RISK(4);

    private static final Map<Integer, ConfigurationTypeEnum> configurationTypeEnumByName;

    static {
    	configurationTypeEnumByName = new HashMap<>();
        for (ConfigurationTypeEnum configurationTypeEnum : ConfigurationTypeEnum.values()) {
        	configurationTypeEnumByName.put(configurationTypeEnum.getId(), configurationTypeEnum);
        }
    }

    @Getter
    private final Integer id;

    ConfigurationTypeEnum(Integer id) {
        this.id = id;
    }

    public static ConfigurationTypeEnum valueFromId(Integer id) {
        ConfigurationTypeEnum result = configurationTypeEnumByName.get(id);
        if (result == null)
            throw new IllegalArgumentException("No ConfigurationTypeEnum with value " + id);
        return result;
    }
}
