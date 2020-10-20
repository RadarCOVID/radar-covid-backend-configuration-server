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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.CDAHttpException;

import es.gob.radarcovid.common.exception.ConfigurationServerException;
import es.gob.radarcovid.configuration.contentful.client.ContentfulClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@RequiredArgsConstructor
public class ContentfulClientImpl implements ContentfulClient {

	private static final String TEXT_CONTENT_TYPE = "textType";
	private static final String MASTER_DATA_CONTENT_TYPE = "masterDataType";

	private static final String FIELDS = "fields";
	private static final String CCAA_FIELD = "fields.ccaa";
	private static final String ID_FIELD = "fields.id";
	private static final String DESCRIPTION_FIELD = "fields.description";
	private static final String ORDER_FIELD = "fields.order";
	private static final String KEY_FIELD = "fields.key";
	private static final String VALUE_FIELD = "fields.value";

	private static final String SEARCH_CATEGORY_IN_FIELD = "fields.category[in]";
	private static final String SEARCH_KEY_IN_FIELD = "fields.key[in]";
	private static final String CATEGORY_LOCALE = "locale";
	private static final String CATEGORY_CCAA = "ccaa";
	private static final String CATEGORY_COUNTRY = "country";
	private static final String KEY_IN_ADDITIONAL_INFO = "CONTACT_PHONE,CONTACT_EMAIL,CONTACT_WEB,CONTACT_WEB_NAME,CONTACT_ADDITIONAL_INFO";
	
    private static final String LOCALE = "locale";
    private static final int LIMIT = 1000;
	
    @Value("${contentful.url}")
    private String contentfulUrl;
    @Value("${contentful.space}")
    private String contentfulSpace;
    @Value("${contentful.token}")
    private String contentfulToken;
    @Value("${contentful.environment}")
    private String contentfulEnvironment;
    @Value("${contentful.alias.enabled}")
    private boolean isAliasEnabled; 
    
	private final CDAClient client;

    @Override
	public Collection<CDAEntry> get(String ccaa, String locale, String platformAlias) {
    	Collection<CDAEntry> result = null;
    	log.debug("Get texts for environment \"{}\"", platformAlias);
    	try {
        	result = this.getClient(platformAlias)
		                .fetch(CDAEntry.class)
		                .withContentType(TEXT_CONTENT_TYPE)
		                .where(CCAA_FIELD, ccaa)
		                .where(LOCALE, locale)
		                .select(FIELDS)
		                .limit(LIMIT)
		                .all()
		                .entries()
		                .values();
    	} catch (CDAHttpException e) {
			log.warn("Environment \"{}\" does not exist or is not enabled in Contentful. Get texts for environment \"{}\"",
					platformAlias, contentfulEnvironment);
			try {
	        	result = client
		                .fetch(CDAEntry.class)
		                .withContentType(TEXT_CONTENT_TYPE)
		                .where(CCAA_FIELD, ccaa)
		                .where(LOCALE, locale)
		                .select(FIELDS)
		                .limit(LIMIT)
		                .all()
		                .entries()
		                .values();
			} catch (CDAHttpException cdae) {
				log.error("Contentful error for \"{}\" alias, \"{}\" ccaa and \"{}\" locale", platformAlias, ccaa, locale);
				throw new ConfigurationServerException(HttpStatus.BAD_REQUEST, "Error in Contentful request");
			}
    	}
    	return result;
    }
 
    @Override
	public Collection<CDAEntry> getLocales(String locale, String platformAlias) {
    	Collection<CDAEntry> result = null;
    	log.debug("Get locales for environment \"{}\"", platformAlias);
    	try {
    		result = this.getClient(platformAlias)
	                    .fetch(CDAEntry.class)
	                    .withContentType(MASTER_DATA_CONTENT_TYPE)
	                    .where(SEARCH_CATEGORY_IN_FIELD, CATEGORY_LOCALE)
	                    .where(LOCALE, locale)
	                    .select(ID_FIELD, DESCRIPTION_FIELD)
	                    .orderBy(ORDER_FIELD)
	                    .limit(LIMIT)
	                    .all()
	                    .entries()
	                    .values();
    	} catch (CDAHttpException e) {
			log.warn("Environment \"{}\" does not exist or is not enabled in Contentful. Get locales for environment \"{}\"",
					platformAlias, contentfulEnvironment);
			try {
	    		result = client
	                    .fetch(CDAEntry.class)
	                    .withContentType(MASTER_DATA_CONTENT_TYPE)
	                    .where(SEARCH_CATEGORY_IN_FIELD, CATEGORY_LOCALE)
	                    .where(LOCALE, locale)
	                    .select(ID_FIELD, DESCRIPTION_FIELD)
	                    .orderBy(ORDER_FIELD)
	                    .limit(LIMIT)
	                    .all()
	                    .entries()
	                    .values();
			} catch (CDAHttpException cdae) {
				log.error("Contentful error for \"{}\" alias and \"{}\" locale", platformAlias, locale);
				throw new ConfigurationServerException(HttpStatus.BAD_REQUEST, "Error in Contentful request");
			}
    	}
    	return result;
    }
    
    @Override
	public Collection<CDAEntry> getAutonomousCommunities(String locale, String platformAlias) {
    	Collection<CDAEntry> result = null;
    	log.debug("Get Autonomous Communities for environment \"{}\"", platformAlias);
    	try {
	        result = this.getClient(platformAlias)
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
    	} catch (CDAHttpException e) {
			log.warn("Environment \"{}\" does not exist or is not enabled in Contentful. Get Autonomous Communities for environment \"{}\"",
					platformAlias, contentfulEnvironment);
			try {
		        result = client
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
			} catch (CDAHttpException cdae) {
				log.error("Contentful error for \"{}\" alias and \"{}\" locale", platformAlias, locale);
				throw new ConfigurationServerException(HttpStatus.BAD_REQUEST, "Error in Contentful request");
			}
    	}
    	return result;
    }
    
    @Override
	public Collection<CDAEntry> getAutonomousCommunitiesAdditionalInfo(String locale, String platformAlias) {
    	Collection<CDAEntry> result = null;
    	log.debug("Get Autonomous Communities Additional Info for environment \"{}\"", platformAlias);
    	try {
	        result = this.getClient(platformAlias)
		                .fetch(CDAEntry.class)
		                .withContentType(TEXT_CONTENT_TYPE)
		                .where(SEARCH_KEY_IN_FIELD, KEY_IN_ADDITIONAL_INFO)
		                .where(LOCALE, locale)
		                .select(KEY_FIELD, VALUE_FIELD, CCAA_FIELD)
		                .limit(LIMIT)
		                .all()
		                .entries()
		                .values();
    	} catch (CDAHttpException e) {
			log.warn("Environment \"{}\" does not exist or is not enabled in Contentful. Get Autonomous Communities Additional Info for environment \"{}\"",
					platformAlias, contentfulEnvironment);
			try {
		        result = client
		                .fetch(CDAEntry.class)
		                .withContentType(TEXT_CONTENT_TYPE)
		                .where(SEARCH_KEY_IN_FIELD, KEY_IN_ADDITIONAL_INFO)
		                .where(LOCALE, locale)
		                .select(KEY_FIELD, VALUE_FIELD, CCAA_FIELD)
		                .limit(LIMIT)
		                .all()
		                .entries()
		                .values();
			} catch (CDAHttpException cdae) {
				log.error("Contentful error for \"{}\" alias and \"{}\" locale", platformAlias, locale);
				throw new ConfigurationServerException(HttpStatus.BAD_REQUEST, "Error in Contentful request");
			}
    	}
    	return result;
    }

	@Override
	public Collection<CDAEntry> getCountries(String locale, String platformAlias) {
    	Collection<CDAEntry> result = null;
    	log.debug("Get Countries for environment \"{}\"", platformAlias);
    	try {
	        result = this.getClient(platformAlias)
						.fetch(CDAEntry.class)
						.withContentType(MASTER_DATA_CONTENT_TYPE)
						.where(SEARCH_CATEGORY_IN_FIELD, CATEGORY_COUNTRY)
						.where(LOCALE, locale)
						.select(ID_FIELD, DESCRIPTION_FIELD)
						.orderBy(DESCRIPTION_FIELD)
						.limit(LIMIT)
						.all()
						.entries()
						.values();
    	} catch (CDAHttpException e) {
			log.warn("Environment \"{}\" does not exist or is not enabled in Contentful. Get Countries for environment \"{}\"",
					platformAlias, contentfulEnvironment);
			try {
		        result = client
						.fetch(CDAEntry.class)
						.withContentType(MASTER_DATA_CONTENT_TYPE)
						.where(SEARCH_CATEGORY_IN_FIELD, CATEGORY_COUNTRY)
						.where(LOCALE, locale)
						.select(ID_FIELD, DESCRIPTION_FIELD)
						.orderBy(DESCRIPTION_FIELD)
						.limit(LIMIT)
						.all()
						.entries()
						.values();
			} catch (CDAHttpException cdae) {
				log.error("Contentful error for \"{}\" alias and \"{}\" locale", platformAlias, locale);
				throw new ConfigurationServerException(HttpStatus.BAD_REQUEST, "Error in Contentful request");
			}
    	}
    	return result;
	}

    private CDAClient getClient(String platformAlias) {
    	CDAClient result = this.client;
    	if (isAliasEnabled && StringUtils.isNotBlank(platformAlias) && !platformAlias.equals(contentfulEnvironment)) {
    		result = CDAClient.builder()
                    .setEndpoint(contentfulUrl)
                    .setSpace(contentfulSpace)
                    .setToken(contentfulToken)
                    .setEnvironment(platformAlias)
                    .build();
    	}
    	return result;
    }

}
