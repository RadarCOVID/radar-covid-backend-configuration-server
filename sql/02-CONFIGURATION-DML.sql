INSERT INTO CONFIGURATION.EXPOSURE_CONFIGURATION (NM_ID_CONFIGURATION_TYPE, NM_LEVEL_VALUE_1, NM_LEVEL_VALUE_2, 
			NM_LEVEL_VALUE_3, NM_LEVEL_VALUE_4, NM_LEVEL_VALUE_5, NM_LEVEL_VALUE_6,NM_LEVEL_VALUE_7,NM_LEVEL_VALUE_8, NM_RISK_WEIGHT) VALUES
(1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(2, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(3, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(4, 1, 1, 1, 1, 1, 1, 1, 1, 100.0)
;

INSERT INTO CONFIGURATION.GENERAL_CONFIGURATION (NM_ID_GEN_CONFIGURATION_TYPE, NM_ID_GEN_VALUE_TYPE, DE_LABEL, DE_VALUE, DE_MIN_VALUE, DE_MAX_VALUE) VALUES
(1, 1, 'Min Score Risk', '1', null, null),
(2, 2, 'LOW', null, '0', '4095'),
(2, 2, 'HIGH', null, '4096', '4096'),
(3, 2, 'Attenuation Duration Thresholds', null, '55', '74'),
(4, 1, 'AndroidVersion', '1.0.0', null, null),
(4, 1, 'AndroidCompilation', '1', null, null),
(4, 1, 'AndroidUrl', 'https://play.google.com/store/apps/details?id=es.gob.radarcovid', null, null),
(4, 1, 'iOSVersion', '1.0.0', null, null),
(4, 1, 'iOSCompilation', '1', null, null),
(4, 1, 'iOSUrl', 'itms-apps://itunes.apple.com/app/id1520443509', null, null),
(5, 1, 'Min Duration For Exposure', '15', null, null),
(6, 2, 'Attenuation Factor', null, '1.0', '0.5'),
(7, 1, 'HighRiskToLowRisk', '20160', null, null),
(7, 1, 'InfectedToHealthy', '43200', null, null),
(8, 1, 'iOS_1.0.8', 'master', null, null),
(8, 1, 'Android_1.0.7', 'master', null, null),
(9, 1, 'legalTermsVersion', '1.0.0', null, null),
(10, 1, 'radarCovidDownloadUrl', 'https://radarcovid.gob.es/home', null, null),
(11, 1, 'notificationReminder', '1440', null, null),
(12, 1, 'timeBetweenKpi', '1440', null, null)
;