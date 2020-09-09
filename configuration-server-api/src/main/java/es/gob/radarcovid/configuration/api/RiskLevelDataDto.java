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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RiskLevelDataDto implements Serializable {

    private Integer riskLevelValue1;
    
    private Integer riskLevelValue2;
    
    private Integer riskLevelValue3;
    
    private Integer riskLevelValue4;
    
    private Integer riskLevelValue5;
    
    private Integer riskLevelValue6;

    private Integer riskLevelValue7;
    
    private Integer riskLevelValue8;
    
    private Double riskLevelWeight;
}
