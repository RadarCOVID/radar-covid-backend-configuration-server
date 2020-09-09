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

public enum GenValueTypeEnum {

	SIMPLE_VALUE(1),
	RANGE_VALUE(2);

    private static final Map<Integer, GenValueTypeEnum> genValueTypeEnumByName;

    static {
    	genValueTypeEnumByName = new HashMap<>();
        for (GenValueTypeEnum genValueTypeEnum : GenValueTypeEnum.values()) {
        	genValueTypeEnumByName.put(genValueTypeEnum.getId(), genValueTypeEnum);
        }
    }

    @Getter
    private final Integer id;

    GenValueTypeEnum(Integer id) {
        this.id = id;
    }

    public static GenValueTypeEnum valueFromId(Integer id) {
        GenValueTypeEnum result = genValueTypeEnumByName.get(id);
        if (result == null)
            throw new IllegalArgumentException("No GenValueTypeEnum with value " + id);
        return result;
    }
}
