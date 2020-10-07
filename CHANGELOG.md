# Changelog

All notable changes to this project will be documented in this file. 

## [Unreleased]

### Added

- Country endpoint, which returns a list of countries of interest.
- Configured AWS Parameters Store for service property management.
- Management of aliases for access to different environments in Contentful.
- Cache time properties in masters data and texts endpoints.
- Added new records in GENERAL_CONFIGURATION table to store the Contentful aliases.

### Changed

- Modified exception handling. Error messages has been included.

### Deleted

- The properties files for Preproduction and Production environments have been removed, because these properties are stored encrypted in the infrastructure (AWS parameter stores).
- Deleted Headers started with "x-forwarded", in server logs.

## [1.0.0] - 2020-09-15

* Configuration Service. Initial version.

### Added

- Token UUID service, through which a unique identifier token is obtained.
- Settings service, through which the exposure configuration and other adjustment parameters are obtained.
- Locales service, which returns the list of available locales.
- Autonomous Community service, which returns the list of available autonomous communities.
- Texts service, through which internationalized texts are obtained.

[1.0.0]: https://github.com/RadarCOVID/radar-covid-backend-configuration-server/releases/tag/1.0.0.RELEASE