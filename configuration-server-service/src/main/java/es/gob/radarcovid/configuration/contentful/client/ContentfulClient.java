/*
 * Copyright (c) 2020 Gobierno de España
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package es.gob.radarcovid.configuration.contentful.client;

import com.contentful.java.cda.CDAEntry;

import java.util.Collection;

public interface ContentfulClient {
	
	Collection<CDAEntry> get(String ccaa, String locale, String platformAlias);
	
	Collection<CDAEntry> getWeb(String locale, String application, String platformAlias);

	Collection<CDAEntry> getLocales(String locale, String platformAlias);

	Collection<CDAEntry> getAutonomousCommunities(String locale, String platformAlias);

	Collection<CDAEntry> getAutonomousCommunitiesAdditionalInfo(String locale, String platformAlias);

	Collection<CDAEntry> getCountries(String locale, String platformAlias);

}