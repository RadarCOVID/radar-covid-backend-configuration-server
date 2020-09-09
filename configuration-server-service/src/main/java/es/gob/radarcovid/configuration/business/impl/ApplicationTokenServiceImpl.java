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

import java.util.UUID;

import org.springframework.stereotype.Service;

import es.gob.radarcovid.common.annotation.Loggable;
import es.gob.radarcovid.configuration.api.UuidDto;
import es.gob.radarcovid.configuration.business.ApplicationTokenService;

@Service
public class ApplicationTokenServiceImpl implements ApplicationTokenService  {
	
	@Loggable
	@Override
	public UuidDto getUuid() {
		return new UuidDto(UUID.randomUUID().toString());
	}
	
}
