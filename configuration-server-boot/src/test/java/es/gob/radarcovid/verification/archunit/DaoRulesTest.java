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

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noMethods;

import java.sql.SQLException;

import javax.persistence.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "es.gob.radarcovid")
public class DaoRulesTest {

    @ArchTest
    static final ArchRule DAOs_must_reside_in_persistence_package =
            classes()
                    .that().haveNameMatching(".*Dao")
                    .should().resideInAnyPackage("..persistence")
                    .as("DAOs should reside in a package '..persistence'");

    @ArchTest
    static final ArchRule DAOs_Impl_must_reside_in_persistence_impl_package =
            classes()
                    .that().haveNameMatching(".*DaoImpl")
                    .should().resideInAnyPackage("..persistence.impl")
                    .as("DAOs should reside in a package '..persistence.impl'");

    @ArchTest
    static final ArchRule Entities_must_reside_in_persistence_entity_package_suffix =
            classes()
                    .that().haveNameMatching(".*Entity")
                    .should().resideInAnyPackage("..persistence.entity")
                    .as("Entities should reside in a package '..persistence.entity'");

    @ArchTest
    static final ArchRule Entities_must_reside_in_persistence_entity_package_annotation =
            classes()
                    .that().areAnnotatedWith(Entity.class)
                    .should().resideInAnyPackage("..persistence.entity")
                    .as("Entities should reside in a package '..persistence.entity'");

    @ArchTest
    static final ArchRule Repositories_must_reside_in_persistence_repository_package =
            classes()
                    .that().haveNameMatching(".*Repository")
                    .should().resideInAnyPackage("..persistence.repository")
                    .as("Repositories should reside in a package '..persistence.repository'");

    @ArchTest
    static final ArchRule only_DAOs_may_use_the_JpaRepository =
            noClasses()
                    .that().resideOutsideOfPackage("..persistence.impl")
                    .should().accessClassesThat().areAssignableTo(JpaRepository.class)
                    .as("Only DAOs may use the " + JpaRepository.class.getSimpleName());

    @ArchTest
    static final ArchRule DAOs_must_not_throw_SQLException =
            noMethods()
                    .that().areDeclaredInClassesThat().haveNameMatching(".*DaoImpl")
                    .should().declareThrowableOfType(SQLException.class);

    @ArchTest
    static final ArchRule only_repositories_should_have_Repository_annotation =
            noClasses()
                    .that().areAnnotatedWith(Repository.class)
                    .should().resideOutsideOfPackage("..repository")
                    .as("Only Repositories in '..repository' package should have Repository annotation");

    @ArchTest
    static final ArchRule only_DAOs_should_have_Transactional_annotation =
            noMethods()
                    .that().areAnnotatedWith(Transactional.class)
                    .should().beDeclaredInClassesThat().resideOutsideOfPackage("..persistence")
                    .as("Only DAOs in '..persistence' package should have Transactional annotation");

}
