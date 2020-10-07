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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.contentful.java.cda.CDAEntry;

import es.gob.radarcovid.common.annotation.Loggable;
import es.gob.radarcovid.common.exception.ConfigurationServerException;
import es.gob.radarcovid.configuration.api.CcaaKeyValueDto;
import es.gob.radarcovid.configuration.api.KeyValueDto;
import es.gob.radarcovid.configuration.api.TextCustomMap;
import es.gob.radarcovid.configuration.contentful.ContentfulService;
import es.gob.radarcovid.configuration.contentful.client.ContentfulClient;
import es.gob.radarcovid.configuration.contentful.mapper.CcaaKeyValueMapper;
import es.gob.radarcovid.configuration.contentful.mapper.KeyValueMapper;
import es.gob.radarcovid.configuration.contentful.mapper.TextCustomMapper;
import es.gob.radarcovid.configuration.persistence.GeneralConfigurationDao;
import es.gob.radarcovid.configuration.persistence.vo.CDAEntryKeyTypeEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContentfulServiceImpl implements ContentfulService {

	private final ContentfulClient contentful;
	private final KeyValueMapper mapper;
	private final CcaaKeyValueMapper ccaaMapper;
	private final TextCustomMapper textCustomMapper;
	private final GeneralConfigurationDao generalConfigurationDao;

    @Value("${contentful.alias.enabled}")
    private boolean isAliasEnabled;
    @Value("${contentful.environment}")
    private String contentfulEnvironment;
    @Value("#{'${contentful.locales}'.split(',')}")
    private List<String> contentfulLocales;
    @Value("#{'${contentful.ccaa}'.split(',')}")
    private List<String> contentfulCCAA;
    

	@Loggable
	@Override
	public TextCustomMap get(String ccaa, String locale, String platform, String version) {
		this.checkCCAA(ccaa);
		this.checkLocale(locale);
		return textCustomMapper.entityToDto(contentful.get(ccaa, locale, this.getPlatformAlias(platform, version)));
	}

	@Loggable
	@Override
	public List<KeyValueDto> getLocales(String locale, String platform, String version) {
		this.checkLocale(locale);
		return mapper.entityToDto(contentful.getLocales(locale, this.getPlatformAlias(platform, version)));
	}

	@Loggable
	@Override
	public List<CcaaKeyValueDto> getAutonomousCommunities(String locale, String platform, String version, boolean additionalInfo) {
		this.checkLocale(locale);
		List<CcaaKeyValueDto> result = ccaaMapper
				.entityToDto(contentful.getAutonomousCommunities(locale, this.getPlatformAlias(platform, version)));
		
		if (additionalInfo) {
			Collection<CDAEntry> e = contentful.getAutonomousCommunitiesAdditionalInfo(locale, this.getPlatformAlias(platform, version));
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

	@Loggable
	@Override
	public List<KeyValueDto> getCountries(String locale, String platform, String version) {
		this.checkLocale(locale);
		return mapper.entityToDto(contentful.getCountries(locale, this.getPlatformAlias(platform, version)));
	}

	
	private String getPlatformAlias(String platform, String version) {
		String platformAlias = contentfulEnvironment;
		if (isAliasEnabled
			&& StringUtils.isNotBlank(platform)
			&& StringUtils.isNotBlank(version)) {
			platformAlias = generalConfigurationDao.getValueBylabel(
					new StringBuilder().append(platform).append("_").append(version).toString(), contentfulEnvironment);
		}
		return platformAlias;
	}

	private void checkCCAA(String ccaa) {
		if (!contentfulCCAA.contains(ccaa)) {
			throw new ConfigurationServerException(HttpStatus.BAD_REQUEST,
					new StringBuffer("CCAA ").append(ccaa).append(" is not valid").toString());
		}
	}

	private void checkLocale(String locale) {
		if (!contentfulLocales.contains(locale)) {
			throw new ConfigurationServerException(HttpStatus.BAD_REQUEST,
					new StringBuffer("Locale ").append(locale).append(" is not valid").toString());
		}
	}
}
