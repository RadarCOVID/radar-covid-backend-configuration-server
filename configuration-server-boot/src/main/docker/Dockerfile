#
# Copyright (c) 2020 Gobierno de España
#
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at http://mozilla.org/MPL/2.0/.
#
# SPDX-License-Identifier: MPL-2.0
#

FROM adoptopenjdk:11-jre-hotspot

# Metadata
LABEL module.vendor="Radar-COVID"         \
      module.maintainer="SEDIAgob"        \
      module.name="${project.artifactId}" \
      module.version="${project.version}"

COPY  [ "${project.artifactId}-${project.version}-exec.jar", "/app.jar" ]

RUN sh -c 'touch /app.jar'

VOLUME [ "/tmp" ]

ARG AWS_ACCESS_KEY
ARG AWS_SECRET_KEY
ARG AWS_PARAMSTORE_ENABLED

ENV AWS_ACCESS_KEY_ID ${AWS_ACCESS_KEY}
ENV AWS_SECRET_KEY ${AWS_SECRET_KEY}
ENV AWS_PARAMSTORE_ENABLED ${AWS_PARAMSTORE_ENABLED}

ENV JAVA_OPTS $JAVA_OPTS -Xms256M -Xmx1G
ENV SERVER_PORT 8080

EXPOSE ${SERVER_PORT}

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=${build.profile.id} -jar /app.jar" ]
