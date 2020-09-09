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

import es.gob.radarcovid.configuration.persistence.vo.GenConfigurationTypeEnum;
import es.gob.radarcovid.configuration.persistence.vo.GenValueTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "GENERAL_CONFIGURATION")
public class GeneralConfigurationEntity implements Serializable {

    private static final String SEQUENCE_NAME = "SQ_NM_ID_EXPOSURE_CONFIGURATION";

    @Id
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @Column(name = "NM_ID_GEN_CONFIGURATION")
    private Long id;
    
    @Column(name = "NM_ID_GEN_CONFIGURATION_TYPE")
    private Integer genConfigurationType;
    
    @Column(name = "NM_ID_GEN_VALUE_TYPE")
    private Integer genValueType;
    
    @Column(name = "DE_LABEL")
    private String label;

    @Column(name = "DE_VALUE")
    private String value;

    @Column(name = "DE_MIN_VALUE")
    private String minValue;

    @Column(name = "DE_MAX_VALUE")
    private String maxValue;
    
    public GenConfigurationTypeEnum getGenConfigurationType() {
        return GenConfigurationTypeEnum.valueFromId(this.genConfigurationType);
    }

    public void setGenConfigurationType(GenConfigurationTypeEnum genConfigurationTypeEnum) {
        this.genConfigurationType = genConfigurationTypeEnum.getId();
    }    

    public GenValueTypeEnum getGenValueType() {
        return GenValueTypeEnum.valueFromId(this.genValueType);
    }

    public void setGenValueType(GenValueTypeEnum genValueTypeEnum) {
        this.genValueType = genValueTypeEnum.getId();
    }    
}
