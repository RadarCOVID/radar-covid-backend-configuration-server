# RadarCOVID Configuration Service

<p align="center">
    <a href="https://github.com/RadarCOVID/radar-covid-backend-configuration-server/commits/" title="Last Commit"><img src="https://img.shields.io/github/last-commit/RadarCOVID/radar-covid-backend-configuration-server?style=flat"></a>
    <a href="https://github.com/RadarCOVID/radar-covid-backend-configuration-server/issues" title="Open Issues"><img src="https://img.shields.io/github/issues/RadarCOVID/radar-covid-backend-configuration-server?style=flat"></a>
    <a href="https://github.com/RadarCOVID/radar-covid-backend-configuration-server/blob/master/LICENSE" title="License"><img src="https://img.shields.io/badge/License-MPL%202.0-brightgreen.svg?style=flat"></a>
</p>

## Introduction

Configuration Service in terms of the Radar COVID project enables:

- Getting exposition settings to be used by apps.
- Getting internationalized texts.
- Getting list of Autonomous Communities and their information.
- Getting languages available: es-ES, ca-ES, en-US,...
- Getting countries of interest available: DE, AT, DK, IT,...

## Prerequisites

These are the frameworks and tools used to develop the solution:

- [Java 11](https://openjdk.java.net/).
- [Maven](https://maven.apache.org/).
- [Spring Boot](https://spring.io/projects/spring-boot) version 2.3.
- [Lombok](https://projectlombok.org/), to help programmer. Developers have to include the IDE plugin to support Lombok features (ie, for Eclipse based IDE, go [here](https://projectlombok.org/setup/eclipse)).
- [ArchUnit](https://www.archunit.org/) is used to check Java architecture.
- [PostgreSQL](https://www.postgresql.org/).
- Testing:
    - [Spock Framework](http://spockframework.org/).
    - [Docker](https://www.docker.com/), because of using Testcontainers.
    - [Testcontainers](https://www.testcontainers.org/).

[Contentful](https://www.contentful.com/) is also used as content platform to manage literals for the different languages, etc.

## Installation and Getting Started

### Building from Source

To build the project, you need to run this command:

```shell
mvn clean package -P <environment>
```

Where `<environment>` has these possible values:

- `local-env`. To run the application from local (eg, from IDE o from Maven using `mvn spring-boot:run`). It is the default profile, using [`application-local.yml`](./configuration-server-boot/src/main/resources/application-local.yml) configuration file.
- `docker-env`. To run the application in a Docker container with `docker-compose`, using [`application-docker.yml`](./configuration-server-boot/src/main/resources/application-docker.yml) configuration file.
- `pre-env`. To run the application in the Preproduction environment. Preproduction environment properties are configured in the infrastructure.
- `pro-env`. To run the application in the Production environment. Production environment properties are configured in the infrastructure

All profiles will load the default [configuration](./configuration-server-boot/src/main/resources/application.yml).

### Running the Project

Depends on the environment you selected when you built the project, you can run the project:

- From the IDE, if you selected `local-env` environment (or you didn't select any Maven profile).
- From Docker. Once you build the project, you will have in `configuration-server-boot/target/docker` the files you would need to run the application from a container (`Dockerfile` and the Spring Boot fat-jar).

If you want to run the application inside a Docker container in local, once you built it, you should run:

```shell
docker-compose up -d postgres
docker-compose up -d backend
```

#### Database

This project doesn't use either [Liquibase](https://www.liquibase.org/) or [Flyway](https://flywaydb.org/) because:

1. DB-Admins should only have database privileges to maintain the database model ([DDL](https://en.wikipedia.org/wiki/Data_definition_language)).
2. Applications should only have privileges to maintain the data ([DML](https://en.wikipedia.org/wiki/Data_manipulation_language)).

Because of this, there are two scripts:

- [`01-CONFIGURATION-DDL.sql`](./sql/01-CONFIGURATION-DDL.sql). Script to create the model.
- [`02-CONFIGURATION-DML.sql`](./sql/02-CONFIGURATION-DML.sql). Script with inserts.

### API Documentation

Along with the application there comes with [OpenAPI Specification](https://www.openapis.org/), which you can access in your web browser when the Verification Service is running (unless in Production environment, where it is inactive by default):

```shell
<base-url>/openapi/api-docs
```
You can download the YAML version in `/openapi/api-docs.yaml`

If running in local, you can get:
- OpenAPI: http://localhost:8080/openapi/api-docs
- Swagger UI: http://localhost:8080/openapi/ui 

#### Endpoints

| Endpoint | Description | Default values | Sample response |
| -------- | ----------- | -------------- | --------------- |
| `/masterData/ccaa?locale=<locale>[&additionalInfo=<additionalInfo>&platform=<platform>&version=<platformVersion>]` | Get Autonomous Communities available | `locale=es-ES`<br>`additionalInfo=false` | Response with `additionalInfo=true`:<br>[`response-masterData-ccaa-additionalInfo.json`](./responses/response-masterData-ccaa-additionalInfo.json) |
| `/masterData/locales?locale=<locale>[&platform=<platform>&version=<platformVersion>]` | Get locales available | `locale=es-ES` | [`response-masterData-locales.json`](./responses/response-masterData-locales.json) |
| `/masterData/countries?locale=<locale>[&platform=<platform>&version=<platformVersion>]` | Get countries available | `locale=es-ES` | [`response-masterData-countries.json`](./responses/response-masterData-countries.json) |
| `/settings` | Get application settings | | [`response-settings.json`](./responses/response-settings.json) |
| `/texts?ccaa=<ccaa>[&locale=<locale>&platform=<platform>&version=<platformVersion>]` | Get texts by locale and Autonomous Community | `ccaa=ES`<br>`locale=es-ES` | Response with default parameters.<br>[`response-texts.json`](./responses/response-texts.json)

NOTE: The sample responses are using default parameters so returns are in _castellano_. The real information is not in a JSON file; as we said before, we use Contentful to maintain the languages, texts, etc.

### Modules

Configuration Service has four modules:

- `configuration-server-parent`. Parent Maven project to define dependencies and plugins.
- `configuration-server-api`. [DTOs](https://en.wikipedia.org/wiki/Data_transfer_object) exposed.
- `configuration-server-boot`. Main application, global configurations and properties. This module also has integration tests and Java architecture tests with ArchUnit.
- `configuration-server-service`. Business and data layers.

## Support and Feedback

The following channels are available for discussions, feedback, and support requests:

| Type       | Channel                                                |
| ---------- | ------------------------------------------------------ |
| **Issues** | <a href="https://github.com/RadarCOVID/radar-covid-backend-configuration-server/issues" title="Open Issues"><img src="https://img.shields.io/github/issues/RadarCOVID/radar-covid-backend-configuration-server?style=flat"></a> |

## Contribute

If you want to contribute with this exciting project follow the steps in [How to create a Pull Request in GitHub](https://opensource.com/article/19/7/create-pull-request-github).

More details in [CONTRIBUTING.md](./CONTRIBUTING.md).

## License

This Source Code Form is subject to the terms of the [Mozilla Public License, v. 2.0](https://www.mozilla.org/en-US/MPL/2.0/).
