/*
 * Copyright (c) 2020 Gobierno de España
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package es.gob.radarcovid.configuration.persistence.vo;

import es.gob.radarcovid.configuration.api.CcaaKeyValueDto;

/*
 * Idea from https://medium.com/@roanmonteiro/clean-code-with-java-replace-the-logical-condition-using-enum-if-else-statements-898bd6a85327
 */
public enum CDAEntryKeyTypeEnum {

    CONTACT_PHONE {
        @Override
        public void setAdditionalInfo(CcaaKeyValueDto ccaa, String value) {
            ccaa.setPhone(value);
        }
    },
    CONTACT_EMAIL {
        @Override
        public void setAdditionalInfo(CcaaKeyValueDto ccaa, String value) {
            ccaa.setEmail(value);
        }
    },
    CONTACT_WEB {
        @Override
        public void setAdditionalInfo(CcaaKeyValueDto ccaa, String value) {
            ccaa.setWeb(value);
        }
    },
    CONTACT_WEB_NAME {
        @Override
        public void setAdditionalInfo(CcaaKeyValueDto ccaa, String value) {
            ccaa.setWebName(value);
        }
    },
    CONTACT_ADDITIONAL_INFO {
        @Override
        public void setAdditionalInfo(CcaaKeyValueDto ccaa, String value) {
            ccaa.setAdditionalInfo(value);
        }
    };

    public abstract void setAdditionalInfo(CcaaKeyValueDto ccaa, String value);

}