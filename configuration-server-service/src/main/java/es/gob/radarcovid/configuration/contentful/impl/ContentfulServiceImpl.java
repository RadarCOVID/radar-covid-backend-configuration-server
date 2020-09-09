/*
 * Copyright (c) 2020 Gobierno de España
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package es.gob.radarcovid.configuration.contentful.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.contentful.java.cda.CDAEntry;

import es.gob.radarcovid.common.annotation.Loggable;
import es.gob.radarcovid.configuration.api.CcaaKeyValueDto;
import es.gob.radarcovid.configuration.api.KeyValueDto;
import es.gob.radarcovid.configuration.api.TextCustomMap;
import es.gob.radarcovid.configuration.contentful.ContentfulService;
import es.gob.radarcovid.configuration.contentful.client.ContentfulClient;
import es.gob.radarcovid.configuration.contentful.mapper.CcaaKeyValueMapper;
import es.gob.radarcovid.configuration.contentful.mapper.KeyValueMapper;
import es.gob.radarcovid.configuration.contentful.mapper.TextCustomMapper;
import es.gob.radarcovid.configuration.persistence.vo.CDAEntryKeyTypeEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContentfulServiceImpl implements ContentfulService {

	private final ContentfulClient contentful;
	private final KeyValueMapper mapper;
	private final CcaaKeyValueMapper ccaaMapper;
	private final TextCustomMapper textCustomMapper;

	@Loggable
	@Override
	public TextCustomMap get(String ccaa, String locale) {
		return textCustomMapper.entityToDto(contentful.get(ccaa, locale));
	}

	@Loggable
	@Override
	public List<KeyValueDto> getLocales(String locale) {
		return mapper.entityToDto(contentful.getLocales(locale));
	}

	@Loggable
	@Override
	public List<CcaaKeyValueDto> getAutonomousCommunities(String locale, boolean additionalInfo) {

		List<CcaaKeyValueDto> result = ccaaMapper.entityToDto(contentful.getAutonomousCommunities(locale));
		
		if (additionalInfo) {
			Collection<CDAEntry> e = contentful.getAutonomousCommunitiesAdditionalInfo(locale);
			for (CcaaKeyValueDto ccaa : result) {
				for (CDAEntry cdaEntry : e) {
					if (cdaEntry.getField("ccaa").toString().contains(ccaa.getId())) {
// Idea from https://medium.com/@roanmonteiro/clean-code-with-java-replace-the-logical-condition-using-enum-if-else-statements-898bd6a85327
						CDAEntryKeyTypeEnum cdaEntryType = CDAEntryKeyTypeEnum.valueOf(cdaEntry.getField("key"));
						cdaEntryType.setAdditionalInfo(ccaa, cdaEntry.getField("value"));
					}
				}
			}
		}
		return result;
	}

}
