/*
 * Copyright (c) 2020 Gobierno de España
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package es.gob.radarcovid.configuration.persistence.mapper;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import es.gob.radarcovid.configuration.api.SettingsDto;
import es.gob.radarcovid.configuration.persistence.entity.GeneralConfigurationEntity;
import es.gob.radarcovid.configuration.persistence.vo.GenConfigurationTypeEnum;

@Mapper(componentModel = "spring", uses = { RiskScoreClassificationMapper.class })
public interface GeneralConfigurationMapper {

	GeneralConfigurationMapper INSTANCE = Mappers.getMapper(GeneralConfigurationMapper.class);

	default SettingsDto entityToDto(List<GeneralConfigurationEntity> source) {
		SettingsDto target = null;
		if (!CollectionUtils.isEmpty(source)) {
			target = new SettingsDto();
			target.setResponseDate(new GregorianCalendar().getTimeInMillis());
			target.setMinRiskScore(
					source.stream().filter(t -> t.getGenConfigurationType() == GenConfigurationTypeEnum.MIN_RISK_SCORE)
							.map(t -> stringToLong(t.getValue())).findFirst().orElse(null));
			target.setMinDurationForExposure(
					source.stream().filter(t -> t.getGenConfigurationType() == GenConfigurationTypeEnum.MIN_DURATION_FOR_EXPOSURE)
							.map(t -> stringToLong(t.getValue())).findFirst().orElse(null));
			target.setRiskScoreClassification(RiskScoreClassificationMapper.INSTANCE.entityToDto(source.stream()
					.filter(t -> t.getGenConfigurationType() == GenConfigurationTypeEnum.RISK_SCORE_CLASSIFICATION)
					.collect(Collectors.toList())));
			target.setAttenuationDurationThresholds(AttenuationDurationThresholdsMapper.INSTANCE.entityToDto(source
					.stream()
					.filter(t -> t.getGenConfigurationType() == GenConfigurationTypeEnum.ATTENUATION_DURATION_THRESHOLDS)
					.findFirst().orElse(null)));
			target.setApplicationVersion(ApplicationVersionMapper.INSTANCE.entityToDto(source.stream()
					.filter(t -> t.getGenConfigurationType() == GenConfigurationTypeEnum.APPLICATION_VERSION)
					.collect(Collectors.toList())));
			target.setAttenuationFactor(AttenuationFactorMapper.INSTANCE.entityToDto(source
					.stream()
					.filter(t -> t.getGenConfigurationType() == GenConfigurationTypeEnum.ATTENUATION_FACTOR)
					.findFirst().orElse(null)));
			target.setTimeBetweenStates(TimeBetweenStatesMapper.INSTANCE.entityToDto(source.stream()
					.filter(t -> t.getGenConfigurationType() == GenConfigurationTypeEnum.TIME_BETWEEN_STATES)
					.collect(Collectors.toList())));
			target.setLegalTermsVersion(
					source.stream().filter(t -> t.getGenConfigurationType() == GenConfigurationTypeEnum.LEGAL_TERMS_VERSION)
							.map(t -> t.getValue()).findFirst().orElse(null));
			target.setRadarCovidDownloadUrl(
					source.stream().filter(t -> t.getGenConfigurationType() == GenConfigurationTypeEnum.RADAR_COVID_DOWNLOAD_URL)
							.map(t -> t.getValue()).findFirst().orElse(null));
			target.setNotificationReminder(
					source.stream().filter(t -> t.getGenConfigurationType() == GenConfigurationTypeEnum.NOTIFICATION_REMINDER)
							.map(t -> stringToLong(t.getValue())).findFirst().orElse(null));
			target.setTimeBetweenKpi(
					source.stream().filter(t -> t.getGenConfigurationType() == GenConfigurationTypeEnum.TIME_BETWEEN_KPI)
							.map(t -> stringToLong(t.getValue())).findFirst().orElse(null));
			target.setVenueConfiguration(VenueConfigurationMapper.INSTANCE.entityToDto(source
					.stream()
					.filter(t -> t.getGenConfigurationType() == GenConfigurationTypeEnum.VENUE_CONFIGURATION)
					.collect(Collectors.toList())));
		}
		return target;
	}

	private Long stringToLong(String string) {
		return (NumberUtils.isCreatable(string)) ? Long.parseLong(string) : null;
	}

}
