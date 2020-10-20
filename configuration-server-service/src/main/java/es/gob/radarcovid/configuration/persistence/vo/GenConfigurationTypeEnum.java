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

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public enum GenConfigurationTypeEnum {

	MIN_RISK_SCORE(1),
	RISK_SCORE_CLASSIFICATION(2),
	ATTENUATION_DURATION_THRESHOLDS(3),
	APPLICATION_VERSION(4),
	MIN_DURATION_FOR_EXPOSURE(5),
	ATTENUATION_FACTOR(6),
	TIME_BETWEEN_STATES(7),
    CONTENTFUL_ALIAS(8),
    LEGAL_TERMS_VERSION(9)
	;

    private static final Map<Integer, GenConfigurationTypeEnum> genConfigurationTypeEnumByName;

    static {
    	genConfigurationTypeEnumByName = new HashMap<>();
        for (GenConfigurationTypeEnum genConfigurationTypeEnum : GenConfigurationTypeEnum.values()) {
        	genConfigurationTypeEnumByName.put(genConfigurationTypeEnum.getId(), genConfigurationTypeEnum);
        }
    }

    @Getter
    private final Integer id;

    GenConfigurationTypeEnum(Integer id) {
        this.id = id;
    }

    public static GenConfigurationTypeEnum valueFromId(Integer id) {
        GenConfigurationTypeEnum result = genConfigurationTypeEnumByName.get(id);
        if (result == null)
            throw new IllegalArgumentException("No GenConfigurationTypeEnum with value " + id);
        return result;
    }
}
