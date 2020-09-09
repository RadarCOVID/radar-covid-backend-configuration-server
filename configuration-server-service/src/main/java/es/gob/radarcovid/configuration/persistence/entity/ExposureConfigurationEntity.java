/*
 * Copyright (c) 2020 Gobierno de España
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package es.gob.radarcovid.configuration.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.gob.radarcovid.configuration.persistence.vo.ConfigurationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "EXPOSURE_CONFIGURATION")
public class ExposureConfigurationEntity implements Serializable {

    private static final String SEQUENCE_NAME = "SQ_NM_ID_EXPOSURE_CONFIGURATION";

    @Id
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @Column(name = "NM_ID_CONFIGURATION")
    private Long id;
    
    @Column(name = "NM_ID_CONFIGURATION_TYPE")
    private Integer configurationType;
    
    @Column(name = "NM_LEVEL_VALUE_1")
    private Integer levelValue1;

    @Column(name = "NM_LEVEL_VALUE_2")
    private Integer levelValue2;

    @Column(name = "NM_LEVEL_VALUE_3")
    private Integer levelValue3;

    @Column(name = "NM_LEVEL_VALUE_4")
    private Integer levelValue4;

    @Column(name = "NM_LEVEL_VALUE_5")
    private Integer levelValue5;

    @Column(name = "NM_LEVEL_VALUE_6")
    private Integer levelValue6;

    @Column(name = "NM_LEVEL_VALUE_7")
    private Integer levelValue7;

    @Column(name = "NM_LEVEL_VALUE_8")
    private Integer levelValue8;
    
    @Column(name = "NM_RISK_WEIGHT")
    private Double riskWeight;

    public ConfigurationTypeEnum getConfigurationType() {
        return ConfigurationTypeEnum.valueFromId(this.configurationType);
    }

    public void setConfigurationType(ConfigurationTypeEnum configurationTypeEnum) {
        this.configurationType = configurationTypeEnum.getId();
    }
}
