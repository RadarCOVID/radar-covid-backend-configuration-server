/*
 * Copyright (c) 2020 Gobierno de España
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package es.gob.radarcovid.configuration.business.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.gob.radarcovid.common.annotation.Loggable;
import es.gob.radarcovid.configuration.api.CcaaKeyValueDto;
import es.gob.radarcovid.configuration.api.KeyValueDto;
import es.gob.radarcovid.configuration.business.MasterDataService;
import es.gob.radarcovid.configuration.contentful.ContentfulService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MasterDataServiceImpl implements MasterDataService {

	private final ContentfulService contentfulService;

	@Loggable
	@Override
	public List<KeyValueDto> getLocales(String locale, String platform, String version) {
		return contentfulService.getLocales(locale, platform, version);
	}
	
	@Loggable
	@Override
	public List<CcaaKeyValueDto> getAutonomousCommunities(String locale, String platform, String version, boolean additionalInfo) {
		return contentfulService.getAutonomousCommunities(locale, platform, version, additionalInfo);
	}

	@Loggable
	@Override
	public List<KeyValueDto> getCountries(String locale, String platform, String version) {
		return contentfulService.getCountries(locale, platform, version);
	}
}
