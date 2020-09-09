/*
 * Copyright (c) 2020 Gobierno de España
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package es.gob.radarcovid.configuration.contentful.client.impl;

import java.util.Collection;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;

import es.gob.radarcovid.configuration.contentful.client.ContentfulClient;

@Component
@RequiredArgsConstructor
public class ContentfulClientImpl implements ContentfulClient {

	private static final String TEXT_CONTENT_TYPE = "textType";
	private static final String MASTER_DATA_CONTENT_TYPE = "masterDataType";
	
	private static final String FIELDS = "fields";
	private static final String CCAA_FIELD = "fields.ccaa";
	private static final String ID_FIELD = "fields.id";
	private static final String DESCRIPTION_FIELD = "fields.description";
	private static final String KEY_FIELD = "fields.key";
	private static final String VALUE_FIELD = "fields.value";
	
	
	private static final String SEARCH_CATEGORY_IN_FIELD = "fields.category[in]";
	private static final String SEARCH_KEY_IN_FIELD = "fields.key[in]";
	private static final String CATEGORY_LOCALE = "locale";
	private static final String CATEGORY_CCAA = "ccaa";
	private static final String KEY_IN_ADDITIONAL_INFO = "CONTACT_PHONE,CONTACT_EMAIL,CONTACT_WEB,CONTACT_WEB_NAME,CONTACT_ADDITIONAL_INFO";
	
    private static final String LOCALE = "locale";
    private static final int LIMIT = 1000;
	
	private final CDAClient client;

    @Override
	public Collection<CDAEntry> get(String ccaa, String locale) {
        return client
                .fetch(CDAEntry.class)
                .withContentType(TEXT_CONTENT_TYPE)
                .where(CCAA_FIELD, ccaa)
                .where(LOCALE, locale)
                .select(FIELDS)
                .limit(LIMIT)
                .all()
                .entries()
                .values();
    }
 
    @Override
	public Collection<CDAEntry> getLocales(String locale) {
        return client
                .fetch(CDAEntry.class)
                .withContentType(MASTER_DATA_CONTENT_TYPE)
                .where(SEARCH_CATEGORY_IN_FIELD, CATEGORY_LOCALE)
                .where(LOCALE, locale)
                .select(ID_FIELD, DESCRIPTION_FIELD)
                .orderBy(DESCRIPTION_FIELD)
                .limit(LIMIT)
                .all()
                .entries()
                .values();
    }
    
    @Override
	public Collection<CDAEntry> getAutonomousCommunities(String locale) {
        return client
                .fetch(CDAEntry.class)
                .withContentType(MASTER_DATA_CONTENT_TYPE)
                .where(SEARCH_CATEGORY_IN_FIELD, CATEGORY_CCAA)
                .where(LOCALE, locale)
                .select(ID_FIELD, DESCRIPTION_FIELD)
                .orderBy(DESCRIPTION_FIELD)
                .limit(LIMIT)
                .all()
                .entries()
                .values();
    }
    
    @Override
	public Collection<CDAEntry> getAutonomousCommunitiesAdditionalInfo(String locale) {
        return client
                .fetch(CDAEntry.class)
                .withContentType(TEXT_CONTENT_TYPE)
                .where(SEARCH_KEY_IN_FIELD, KEY_IN_ADDITIONAL_INFO)
                .where(LOCALE, locale)
                .select(KEY_FIELD, VALUE_FIELD, CCAA_FIELD)
                .limit(LIMIT)
                .all()
                .entries()
                .values();
    }
}
