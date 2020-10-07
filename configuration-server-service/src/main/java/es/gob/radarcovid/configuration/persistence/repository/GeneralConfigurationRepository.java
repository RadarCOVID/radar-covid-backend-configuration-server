/*
 * Copyright (c) 2020 Gobierno de España
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package es.gob.radarcovid.configuration.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.gob.radarcovid.configuration.persistence.entity.GeneralConfigurationEntity;

@Repository
public interface GeneralConfigurationRepository extends JpaRepository<GeneralConfigurationEntity, Long> {

    /**
     * This method looks in the Database for an if a GeneralConfigurationEntity exists for the label.
     *
     * @param label value to search for
     * @return Optional GeneralConfigurationEntity
     */
    Optional<GeneralConfigurationEntity> findByLabel(String label);

}
