/*
 * Copyright (c) 2020 Gobierno de España
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */
package es.gob.radarcovid.verification.archunit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import org.mapstruct.Mapper;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "es.gob.radarcovid")
public class MapperRulesTest {

    @ArchTest
    static final ArchRule only_mappers_should_have_Mapper_annotation =
            noClasses()
                    .that().areAnnotatedWith(Mapper.class)
                    .should().resideOutsideOfPackage("..mapper")
                    .as("Only Mappers in '..mapper' package should have Mapper annotation");

    @ArchTest
    static final ArchRule only_mappers_should_be_in_mapper_package =
            noClasses()
                    .that().haveNameMatching(".*Mapper(Impl)?")
                    .should().resideOutsideOfPackage("..mapper")
                    .as("Only Mappers in '..mapper' package should be in Mapper package");

}
