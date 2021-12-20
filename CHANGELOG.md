# Changelog

All notable changes to this project will be documented in this file. 

## [Unreleased]

## [1.3.2.RELEASE] - 2021-12-20

### Changed

- Updated log4j2 version to 2.17.0

## [1.3.1.RELEASE] - 2021-12-17

### Changed

- Updated log4j2 version to 2.15.0
- Updated testcontainers version to 1.16.2

## [1.3.0.RELEASE] - 2021-02-17

### Added

- New configuration general parameter: "timeBetweenKpi", used to control the delivery time between kpi metrics.

### Changed

- Modified general configuration mapper to allow negative values.

## [1.2.0.RELEASE] - 2020-12-17

### Added

- Added [efficient Docker images with Spring Boot 2.3](https://spring.io/blog/2020/08/14/creating-efficient-docker-images-with-spring-boot-2-3).
- New configuration general parameter: "radarCovidDownloadUrl", used to provide the application download URL.
- New configuration general parameter: "notificationReminder", used to provide notification reminder interval in minutes.

## [1.1.0.RELEASE] - 2020-10-28

### Added

- New configuration general parameter: "legalTermsVersion", used to control the current version number of legal terms.
- Set order in locale list (new order field).

### Fixed

- Fixed messages in aspects.

## [1.0.1.RELEASE] - 2020-10-09

### Added

- Country endpoint, which returns a list of countries of interest.
- Configured AWS Parameters Store for service property management.
- Management of aliases for access to different environments in Contentful.
- Cache time properties in masters data and texts endpoints.
- Added new records in GENERAL_CONFIGURATION table to store the Contentful aliases.
- Added CODE_OF_CONDUCT.md file.
- Added CHANGELOG.md file.

### Changed

- Modified exception handling. Error messages has been included.

### Deleted

- The properties files for Preproduction and Production environments have been removed, because these properties are stored encrypted in the infrastructure (AWS parameter stores).
- Deleted Headers started with "x-forwarded", in server logs.
- Deleted api-docs.yaml file. Yaml available through swagger endpoint.

### Fixed

- Fixed contact email in THIRD-PARTY-NOTICES file

## [1.0.0.RELEASE] - 2020-09-15

* Configuration Service. Initial version.

### Added

- Token UUID service, through which a unique identifier token is obtained.
- Settings service, through which the exposure configuration and other adjustment parameters are obtained.
- Locales service, which returns the list of available locales.
- Autonomous Community service, which returns the list of available autonomous communities.
- Texts service, through which internationalized texts are obtained.

[Unreleased]: https://github.com/RadarCOVID/radar-covid-backend-configuration-server/compare/1.3.2.RELEASE...develop
[1.3.2.RELEASE]: https://github.com/RadarCOVID/radar-covid-backend-configuration-server/compare/1.3.1.RELEASE...1.3.2.RELEASE
[1.3.1.RELEASE]: https://github.com/RadarCOVID/radar-covid-backend-configuration-server/compare/1.3.0.RELEASE...1.3.1.RELEASE
[1.3.0.RELEASE]: https://github.com/RadarCOVID/radar-covid-backend-configuration-server/compare/1.2.0.RELEASE...1.3.0.RELEASE
[1.2.0.RELEASE]: https://github.com/RadarCOVID/radar-covid-backend-configuration-server/compare/1.1.0.RELEASE...1.2.0.RELEASE
[1.1.0.RELEASE]: https://github.com/RadarCOVID/radar-covid-backend-configuration-server/compare/1.0.1.RELEASE...1.1.0.RELEASE
[1.0.1.RELEASE]: https://github.com/RadarCOVID/radar-covid-backend-configuration-server/compare/1.0.0.RELEASE...1.0.1.RELEASE
[1.0.0.RELEASE]: https://github.com/RadarCOVID/radar-covid-backend-configuration-server/releases/tag/1.0.0.RELEASE
