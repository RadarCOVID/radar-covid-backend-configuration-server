/*
 * Copyright (c) 2020 Gobierno de España
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package es.gob.radarcovid.configuration.contentful;

import java.util.List;

import es.gob.radarcovid.configuration.api.CcaaKeyValueDto;
import es.gob.radarcovid.configuration.api.KeyValueDto;
import es.gob.radarcovid.configuration.api.TextCustomMap;

public interface ContentfulService {

	TextCustomMap get(String ccaa, String locale, String platform, String version);

	List<KeyValueDto> getLocales(String locale, String platform, String version);

	List<CcaaKeyValueDto> getAutonomousCommunities(String locale, String platform, String version, boolean additionalInfo);

	List<KeyValueDto> getCountries(String locale, String platform, String version);

}