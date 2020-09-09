/*
 * Copyright (c) 2020 Gobierno de España
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package es.gob.radarcovid.configuration.api;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Application settings")
public class SettingsDto implements Serializable {

	@Schema(description = "Response date", required = false)
    private Long responseDate;

	@Schema(description = "Exposure configuration", required = false)
    private ExposureConfigurationDto exposureConfiguration;
    
	@Schema(description = "Minimum risk score", required = false)
    private Long minRiskScore;
	
	@Schema(description = "Mininum duration for exposure", required = false)
	private Long minDurationForExposure;
    
	@Schema(description = "Risk score classification", required = false)
    private List<RiskScoreClassificationDto> riskScoreClassification;

	@Schema(description = "Attenuation duration thresholds", required = false)
	private AttenuationDurationThresholdsDto attenuationDurationThresholds;

	@Schema(description = "Attenuation factor", required = false)
	private AttenuationFactorDto attenuationFactor;

	@Schema(description = "Application version", required = false)
	private ApplicationVersionDto applicationVersion;
	
	@Schema(description = "Time between states", required = false)
	private TimeBetweenStatesDto timeBetweenStates;
	
}
