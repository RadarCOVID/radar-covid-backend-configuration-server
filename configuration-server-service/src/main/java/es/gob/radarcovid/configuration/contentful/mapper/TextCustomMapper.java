/*
 * Copyright (c) 2020 Gobierno de España
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package es.gob.radarcovid.configuration.contentful.mapper;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.apache.commons.lang3.StringUtils;

import com.contentful.java.cda.CDAEntry;

import es.gob.radarcovid.configuration.api.TextCustomMap;

@Mapper(componentModel = "spring")
@Slf4j
public abstract class TextCustomMapper {

	private static final String REGEX1 = "(?<!\\\\)__([^\"]+)(?<!\\\\)__";
	private static final String REGEX2 = "(?<!\\\\)\\*([^\"]+)(?<!\\\\)\\*";
	private static final String REGEX3 = "\\\\([_\\*])";
	
	private static final Pattern PATTERN1 = Pattern.compile(REGEX1);
    private static final Pattern PATTERN2 = Pattern.compile(REGEX2);

    public TextCustomMap entityToDto(Collection<CDAEntry> source) {
        TextCustomMap result = new TextCustomMap();
        source.forEach(component -> {
            String key = component.getField("key");
            String value = component.getField("value");
            if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
                result.put(key, textFormatter(value));
            } else {
                log.warn("[KEY]:{}|[VALUE]:{}", key, value);
                result.put(key, key);
            }
        });
        return result;
    }

    private String textFormatter(String text) {
        String result = text;
        if (!StringUtils.isEmpty(text)) {
            Matcher m = PATTERN1.matcher(text);
            while (m.find()) {
                text = text.replaceFirst(REGEX1, "<b>$1</b>");
                m = PATTERN1.matcher(text);
            }
            m = PATTERN2.matcher(text);
            while (m.find()) {
                text = text.replaceFirst(REGEX2, "<i>$1</i>");
                m = PATTERN2.matcher(text);
            }
            result = text.replaceAll(REGEX3, "$1");
        }
        return result;
    }

}
