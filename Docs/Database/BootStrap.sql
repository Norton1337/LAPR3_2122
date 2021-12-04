--eliminar tabelas (eventualmente) existentes
DROP TABLE container		    CASCADE CONSTRAINTS PURGE;
DROP TABLE vehicle	            CASCADE CONSTRAINTS PURGE;
DROP TABLE truck		    CASCADE CONSTRAINTS PURGE;
DROP TABLE ship	                    CASCADE CONSTRAINTS PURGE;
DROP TABLE position_data            CASCADE CONSTRAINTS PURGE;
DROP TABLE local	            CASCADE CONSTRAINTS PURGE;
DROP TABLE cargo_manifest 	    CASCADE CONSTRAINTS PURGE;
DROP TABLE operation	 	    CASCADE CONSTRAINTS PURGE;

--criar tabelas
CREATE TABLE container(
                          container_id varchar(50),
                          container_number NUMBER(10) CONSTRAINT nn_container_number NOT NULL,
                          container_checkDigit NUMBER(10) CONSTRAINT nn_container_checkDigit NOT NULL,
                          container_payload float(50) CONSTRAINT nn_container_payload NOT NULL,
                          container_tare float(50) CONSTRAINT nn_container_tare NOT NULL,
                          container_gross float(50) CONSTRAINT nn_container_gross NOT NULL,
                          container_volume float(50) CONSTRAINT nn_container_volume NOT NULL,
                          iso_code varchar(5) CONSTRAINT nn_iso_code NOT NULL,
                          container_certificates varchar(50) CONSTRAINT nn_container_certificates NOT NULL,
                          container_repair varchar(50) CONSTRAINT nn_container_repair NOT NULL,
                          container_type varchar(20) CONSTRAINT nn_container_type NOT NULL,
                          container_load varchar(30),
                          CONSTRAINT pk_container_id PRIMARY KEY (container_id),
                          CONSTRAINT ck_container_payload CHECK(container_payload>=0),
                          CONSTRAINT ck_container_tare CHECK(container_tare>=0),
                          CONSTRAINT ck_container_gross CHECK(container_gross>=0),
                          CONSTRAINT ck_container_type CHECK ( container_type = 'Refrigerated' OR  container_type = 'Not Refrigerated')
);

CREATE TABLE vehicle(
                        vehicle_id varchar(50),
                        type varchar(20) CONSTRAINT nn_type NOT NULL,
                        CONSTRAINT pk_vehicle_id PRIMARY KEY (vehicle_id),
                        CONSTRAINT ck_type CHECK (type = 'Truck' OR type = 'Ship')
);

CREATE TABLE truck(
                      vehicle_truck_id varchar(50),
                      plate varchar(255) CONSTRAINT nn_plate NOT NULL,
                      CONSTRAINT pk_vehicle_truck_id PRIMARY KEY (vehicle_truck_id),
                      CONSTRAINT fk_vehicle_truck_id FOREIGN KEY (vehicle_truck_id)REFERENCES vehicle(vehicle_id),
                      CONSTRAINT un_plate UNIQUE (plate)
);

CREATE TABLE ship(
                     vehicle_ship_id varchar(50),
                     shipname varchar(255) CONSTRAINT nn_shipname NOT NULL,
                     mmsi char(9) CONSTRAINT nn_mmsi NOT NULL,
                     imo char(7) CONSTRAINT nn_imo NOT NULL,
                     generators_number NUMBER(10) CONSTRAINT nn_generators_number NOT NULL,
                     generators_power float(10) CONSTRAINT nn_generators_power NOT NULL,
                     call_sign varchar(255) CONSTRAINT nn_call_sign NOT NULL,
                     vesel_type NUMBER(10) CONSTRAINT nn_vesel_type NOT NULL,
                     length float(10) CONSTRAINT nn_length NOT NULL,
                     width float(10) CONSTRAINT nn_width NOT NULL,
                     capacity float(10) CONSTRAINT nn_capacity NOT NULL,
                     draft float(10) CONSTRAINT nn_draft NOT NULL,
                     CONSTRAINT pk_vehicle_ship_id PRIMARY KEY (vehicle_ship_id),
                     CONSTRAINT fk_vehicle_ship_id FOREIGN KEY (vehicle_ship_id) REFERENCES vehicle(vehicle_id),
                     CONSTRAINT un_mmsi UNIQUE (mmsi),
                     CONSTRAINT un_imo UNIQUE (imo),
                     CONSTRAINT un_call_sign UNIQUE (call_sign),
                     CONSTRAINT ck_mmsi CHECK(REGEXP_LIKE(mmsi,'[0-9]{9}')),
                     CONSTRAINT ck_imo CHECK (REGEXP_LIKE(imo,'[0-9]{7}')),
                     CONSTRAINT ck_generators_number CHECK(generators_number>=0),
                     CONSTRAINT ck_generators_power CHECK(generators_power>=0),
                     CONSTRAINT ck_length CHECK(length>=0),
                     CONSTRAINT ck_width CHECK(width>=0),
                     CONSTRAINT ck_capacity CHECK(capacity>=0)
);

CREATE TABLE position_data(
                              position_data_id varchar(50),
                              vehicle_ship_id varchar(50),
                              bdt TIMESTAMP CONSTRAINT nn_bdt NOT NULL,
                              latitude float(10) CONSTRAINT nn_latitude NOT NULL,
                              longitude float(10) CONSTRAINT nn_longitude NOT NULL,
                              sog float(10) CONSTRAINT nn_sog NOT NULL,
                              cog float(10) CONSTRAINT nn_cog NOT NULL,
                              heading float(10) CONSTRAINT nn_heading NOT NULL,
                              position float(10) CONSTRAINT nn_position NOT NULL,
                              transceiver_class varchar(255) CONSTRAINT nn_transceiver_class NOT NULL,
                              CONSTRAINT pk_position_data_id PRIMARY KEY (position_data_id),
                              CONSTRAINT fk_pd_vehicle_ship_id FOREIGN KEY (vehicle_ship_id) REFERENCES ship(vehicle_ship_id),
                              CONSTRAINT ck_latitude CHECK (latitude >= -90 AND latitude <= 91),
                              CONSTRAINT ck_longitude CHECK (longitude >= -180 AND longitude <= 181),
                              CONSTRAINT ck_cog CHECK (cog >= 0 AND cog <= 359),
                              CONSTRAINT ck_heading CHECK ((heading >= 0 AND heading <= 359) OR heading = 511)
);

CREATE TABLE local(
                      local_id varchar(50),
                      local_code NUMBER(10) CONSTRAINT nn_local_code NOT NULL,
                      local_name varchar(255) CONSTRAINT nn_local_name NOT NULL,
                      local_continent varchar(255) CONSTRAINT nn_local_continent NOT NULL,
                      local_country varchar(255) CONSTRAINT nn_local_country NOT NULL,
                      local_lalitude float(10) CONSTRAINT nn_local_lalitude NOT NULL,
                      local_altitude float(10) CONSTRAINT nn_local_altitude NOT NULL,
                      CONSTRAINT pk_local_id PRIMARY KEY (local_id)
);

CREATE TABLE cargo_manifest (
                                cargo_manifest_id varchar(50),
                                vehicle_id  varchar(50),
                                local_id varchar(50),
                                next_local_id varchar(50),
                                cm_date TIMESTAMP(0) CONSTRAINT nn_date NOT NULL,
                                operation_type varchar(20) CONSTRAINT nn_operation_type NOT NULL,
                                CONSTRAINT fk_next_local_id FOREIGN KEY (next_local_id) REFERENCES local(local_id),
                                CONSTRAINT fk_local_id FOREIGN KEY (local_id) REFERENCES local(local_id),
                                CONSTRAINT fk_vehicle_id FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id),
                                CONSTRAINT pk_cargo_manifest_id PRIMARY KEY (cargo_manifest_id),
                                CONSTRAINT ck_operation_type CHECK ( operation_type = 'Load' OR operation_type = 'Unload')
);

CREATE TABLE operation (
                           operation_id varchar(50),
                           container_id varchar(50),
                           cargo_manifest_id varchar(50),
                           coordinate_x float(10),
                           coordinate_y float(10),
                           coordinate_z float(10),
                           CONSTRAINT fk_container_id FOREIGN KEY (container_id) REFERENCES container(container_id),
                           CONSTRAINT fk_cargo_manifest_id FOREIGN KEY (cargo_manifest_id) REFERENCES cargo_manifest(cargo_manifest_id),
                           CONSTRAINT pk_operation_id PRIMARY KEY (operation_id),
                           CONSTRAINT ck_coordinate_x CHECK(coordinate_x>=0),
                           CONSTRAINT ck_coordinate_y CHECK(coordinate_y>=0),
                           CONSTRAINT ck_coordinate_z CHECK(coordinate_z>=0)
);

--preencher tabelas
INSERT INTO container VALUES('1',2345,7,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Brinquedos');
INSERT INTO container VALUES('2',2235,6,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Brinquedos');
INSERT INTO container VALUES('3',2955,5,9500,500,25000,34,'20G0','Certificados','NotRepair','Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('4',2945,6,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('5',2845,7,9500,500,25000,34,'20H0','Certificados','NotRepair','Refrigerated','Comida');
INSERT INTO container VALUES('6',2245,8,9500,500,25000,34,'20H0','Certificados','Reparar','Refrigerated','Comida');
INSERT INTO container VALUES('7',2745,4,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('8',2645,2,9500,500,25000,34,'20H0','Certificados','NotRepair','Refrigerated','Comida');
INSERT INTO container VALUES('9',2445,3,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('10',2155,5,9500,500,25000,34,'20H0','Certificados','NotRepair','Refrigerated','Tecnologia');
INSERT INTO container VALUES('11',2336,7,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Brinquedos');
INSERT INTO container VALUES('12',2337,6,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Brinquedos');
INSERT INTO container VALUES('13',2338,5,9500,500,25000,34,'20G0','Certificados','NotRepair','Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('14',2339,6,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('15',2340,7,9500,500,25000,34,'20H0','Certificados','NotRepair','Refrigerated','Comida');
INSERT INTO container VALUES('16',2341,8,9500,500,25000,34,'20H0','Certificados','Reparar','Refrigerated','Comida');
INSERT INTO container VALUES('17',2342,4,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('18',2343,2,9500,500,25000,34,'20H0','Certificados','NotRepair','Refrigerated','Comida');
INSERT INTO container VALUES('19',2344,3,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('20',2345,5,9500,500,25000,34,'20H0','Certificados','NotRepair','Refrigerated','Tecnologia');
INSERT INTO container VALUES('21',2346,7,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Brinquedos');
INSERT INTO container VALUES('22',2347,6,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Brinquedos');
INSERT INTO container VALUES('23',2348,5,9500,500,25000,34,'20G0','Certificados','NotRepair','Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('24',2349,6,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('25',2350,7,9500,500,25000,34,'20H0','Certificados','NotRepair','Refrigerated','Comida');
INSERT INTO container VALUES('26',2351,8,9500,500,25000,34,'20H0','Certificados','Reparar','Refrigerated','Comida');
INSERT INTO container VALUES('27',2352,4,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('28',2353,2,9500,500,25000,34,'20H0','Certificados','NotRepair','Refrigerated','Comida');
INSERT INTO container VALUES('29',2354,3,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('30',2355,5,9500,500,25000,34,'20H0','Certificados','NotRepair','Refrigerated','Tecnologia');
INSERT INTO container VALUES('31',2356,7,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Brinquedos');
INSERT INTO container VALUES('32',2357,6,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Brinquedos');
INSERT INTO container VALUES('33',2358,5,9500,500,25000,34,'20G0','Certificados','NotRepair','Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('34',2359,6,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('35',2360,7,9500,500,25000,34,'20H0','Certificados','NotRepair','Refrigerated','Comida');
INSERT INTO container VALUES('36',2361,8,9500,500,25000,34,'20H0','Certificados','Reparar','Refrigerated','Comida');
INSERT INTO container VALUES('37',2362,4,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('38',2363,2,9500,500,25000,34,'20H0','Certificados','NotRepair','Refrigerated','Comida');
INSERT INTO container VALUES('39',2364,3,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('40',2365,5,9500,500,25000,34,'20H0','Certificados','NotRepair','Refrigerated','Tecnologia');
INSERT INTO container VALUES('41',2366,7,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Brinquedos');
INSERT INTO container VALUES('42',2367,6,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Brinquedos');
INSERT INTO container VALUES('43',2368,5,9500,500,25000,34,'20G0','Certificados','NotRepair','Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('44',2369,6,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('45',2370,7,9500,500,25000,34,'20H0','Certificados','NotRepair','Refrigerated','Comida');
INSERT INTO container VALUES('46',2371,8,9500,500,25000,34,'20H0','Certificados','Reparar','Refrigerated','Comida');
INSERT INTO container VALUES('47',2372,4,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('48',2373,2,9500,500,25000,34,'20H0','Certificados','NotRepair','Refrigerated','Comida');
INSERT INTO container VALUES('49',2374,3,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('50',2375,5,9500,500,25000,34,'20H0','Certificados','NotRepair','Refrigerated','Tecnologia');
INSERT INTO container VALUES('51',2376,7,9500,500,25000,34,'20H0','Certificados','NotRepair','Refrigerated','Comida');
INSERT INTO container VALUES('52',2377,8,9500,500,25000,34,'20H0','Certificados','Reparar','Refrigerated','Comida');
INSERT INTO container VALUES('53',2378,4,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('54',2379,2,9500,500,25000,34,'20H0','Certificados','NotRepair','Refrigerated','Comida');
INSERT INTO container VALUES('55',2370,3,9500,500,25000,34,'20G0','Certificados','Reparar','Not Refrigerated','Tecnologia');



INSERT INTO local VALUES('30',228,'BRAGA','EUROPA','PORTUGAL',61.183,-0.67977);
INSERT INTO local VALUES('31',227,'MATOSINHOS','EUROPA','PORTUGAL',41.183,-8.67977);
INSERT INTO local VALUES('32',226,'PORTO','EUROPA','PORTUGAL',41.15,-8.61024);
INSERT INTO local VALUES('33',231,'AVEIRO','EUROPA','PORTUGAL',40.64, -8.65362);
INSERT INTO local VALUES('34',232,'FIGUEIRA DA FOZ','EUROPA','PORTUGAL',40.15,-8.85121);
INSERT INTO local VALUES('35',239,'LISBOA','EUROPA','PORTUGAL',39.783,-9.13547);
INSERT INTO local VALUES('36',238,'ALGARVE','EUROPA','PORTUGAL',37.089, -8.25228);
INSERT INTO local VALUES('37',237,'BARCELONA','EUROPA','ESPANHA',41.3879,2.16992);
INSERT INTO local VALUES('38',236,'MARSELHA','EUROPA','FRANÇA',43.2698, 5.39585);
INSERT INTO local VALUES('39',235,'CAGLIARI','EUROPA','ITÁLIA',39.227779,9.111111);

INSERT INTO vehicle VALUES('41','Ship');
INSERT INTO vehicle VALUES('42','Ship');
INSERT INTO vehicle VALUES('43','Ship');
INSERT INTO vehicle VALUES('44','Ship');
INSERT INTO vehicle VALUES('45','Truck');
INSERT INTO vehicle VALUES('46','Truck');
INSERT INTO vehicle VALUES('47','Truck');
INSERT INTO vehicle VALUES('48','Truck');

INSERT INTO ship VALUES('41','barco1', '123456789','8814275',50,120,'2V7788',70,150,35,100000,18);
INSERT INTO ship VALUES('42','barco2', '245631547','1234567',60,120,'9V5388',70,150,35,100000,18);
INSERT INTO ship VALUES('43','barco3', '235762000','8814578',70,120,'9V5848',70,250,45,150000,25);
INSERT INTO ship VALUES('44','barco4', '987654321','8814741',40,120,'6V5148',70,300,60,200000,35);
INSERT INTO truck VALUES('45','99-BC-47');
INSERT INTO truck VALUES('46','40-AV-36');
INSERT INTO truck VALUES('47','99-FB-27');
INSERT INTO truck VALUES('48','59-ER-44');

INSERT INTO position_data VALUES('74','41',(timestamp '2021-11-30 21:22:23'),40.143347,-8.65362,35,140,200,50,'Class A');
INSERT INTO position_data VALUES('84','41',(timestamp '2021-12-7 22:30:30'),37.683661,-24.50362,35,300,150,50,'Class A');
INSERT INTO position_data VALUES('1000','42',(timestamp '2021-11-30 21:22:23'),40.57124,-8.65362,35,140,200,50,'Class A');
INSERT INTO position_data VALUES('1100','42',(timestamp '2022-01-12 18:40:23'),39.15478,-25.65362,45,200,90,50,'Class A');
INSERT INTO position_data VALUES('600','43',(timestamp '2021-11-30 21:22:23'),40.6412,-8.65362,35,140,300,50,'Class A');
INSERT INTO position_data VALUES('650','43',(timestamp '2021-12-30 13:22:00'),39.6412,-20.65362,35,200,80,50,'Class A');
INSERT INTO position_data VALUES('1200','44',(timestamp '2021-11-30 21:22:23'),38.6412,-8.65362,35,140,200,50,'Class A');
INSERT INTO position_data VALUES('1300','44',(timestamp '2021-12-15 02:22:23'),38.2512,-30.65362,35,140,200,50,'Class A');


INSERT INTO cargo_manifest VALUES('1001','41','30','31',(timestamp '2021-11-29 09:12:33'),'Load');
INSERT INTO cargo_manifest VALUES('1002','41','31','32',(timestamp '2021-12-3 09:10:00'),'Load');
INSERT INTO cargo_manifest VALUES('2001','42','31','32',(timestamp '2021-11-30 22:00:05'),'Load');
INSERT INTO cargo_manifest VALUES('2002','42','32','31',(timestamp '2021-12-6 21:12:20'),'Load');
INSERT INTO cargo_manifest VALUES('3001','43','31','36',(timestamp '2021-11-30 05:05:00'),'Load');
INSERT INTO cargo_manifest VALUES('3002','43','36','39',(timestamp '2021-12-2 22:02:10'),'Load');
INSERT INTO cargo_manifest VALUES('4001','44','35','37',(timestamp '2021-12-2 21:42:58'),'Load');
INSERT INTO cargo_manifest VALUES('4002','44','37','38',(timestamp '2021-12-3 22:20:28'),'Load');
INSERT INTO cargo_manifest VALUES('5001','45','31','32',(timestamp '2021-12-1 10:03:11'),'Load');
INSERT INTO cargo_manifest VALUES('6001','46','34','33',(timestamp '2021-12-3 22:02:29'),'Load');
INSERT INTO cargo_manifest VALUES('7001','47','35','31',(timestamp '2021-12-5 22:02:01'),'Load');
INSERT INTO cargo_manifest VALUES('8001','48','32','36',(timestamp '2021-12-4 22:02:47'),'Load');
INSERT INTO cargo_manifest VALUES('1101','41','31',NULL,(timestamp '2021-11-29 23:15:00'),'Unload');
INSERT INTO cargo_manifest VALUES('1102','41','32',NULL,(timestamp '2021-12-3 22:20:34'),'Unload');
INSERT INTO cargo_manifest VALUES('2201','42','32',NULL,(timestamp '2021-12-1 12:22:28'),'Unload');
INSERT INTO cargo_manifest VALUES('2202','42','31',NULL,(timestamp '2021-12-7 05:22:12'),'Unload');
INSERT INTO cargo_manifest VALUES('3301','43','36',NULL,(timestamp '2021-12-1 07:22:10'),'Unload');
INSERT INTO cargo_manifest VALUES('3302','43','39',NULL,(timestamp '2021-12-3 15:22:15'),'Unload');
INSERT INTO cargo_manifest VALUES('4401','44','37',NULL,(timestamp '2021-12-3 08:42:23'),'Unload');
INSERT INTO cargo_manifest VALUES('4402','44','38',NULL,(timestamp '2021-12-4 21:21:56'),'Unload');
INSERT INTO cargo_manifest VALUES('5501','45','32',NULL,(timestamp '2021-12-1 20:24:06'),'Unload');
INSERT INTO cargo_manifest VALUES('6601','46','33',NULL,(timestamp '2021-12-4 09:24:20'),'Unload');
INSERT INTO cargo_manifest VALUES('7701','47','31',NULL,(timestamp '2021-12-6 18:30:00'),'Unload');
INSERT INTO cargo_manifest VALUES('8801','48','36',NULL,(timestamp '2021-12-5 17:24:23'),'Unload');


INSERT INTO operation VALUES('101','1','1001',0,0,0);
INSERT INTO operation VALUES('102','2','1001',1,0,0);
INSERT INTO operation VALUES('103','3','1001',1,1,0);
INSERT INTO operation VALUES('104','4','1001',1,1,1);
INSERT INTO operation VALUES('105','5','1001',1,1,2);
INSERT INTO operation VALUES('106','1','1002',0,0,0);
INSERT INTO operation VALUES('107','2','1002',1,0,0);
INSERT INTO operation VALUES('108','3','1002',1,1,0);
INSERT INTO operation VALUES('109','4','1002',1,1,1);
INSERT INTO operation VALUES('110','5','1002',1,1,2);
INSERT INTO operation VALUES('201','6','2001',0,0,0);
INSERT INTO operation VALUES('202','7','2001',1,0,0);
INSERT INTO operation VALUES('203','8','2001',1,1,0);
INSERT INTO operation VALUES('204','9','2001',1,1,1);
INSERT INTO operation VALUES('205','10','2001',1,1,2);
INSERT INTO operation VALUES('206','11','2002',0,0,0);
INSERT INTO operation VALUES('207','12','2002',1,0,0);
INSERT INTO operation VALUES('208','13','2002',1,1,0);
INSERT INTO operation VALUES('209','14','2002',1,1,1);
INSERT INTO operation VALUES('210','15','2002',1,1,2);
INSERT INTO operation VALUES('301','16','3001',0,0,0);
INSERT INTO operation VALUES('302','17','3001',1,0,0);
INSERT INTO operation VALUES('303','18','3001',1,1,0);
INSERT INTO operation VALUES('304','19','3001',1,1,1);
INSERT INTO operation VALUES('305','20','3001',1,1,2);
INSERT INTO operation VALUES('306','21','3002',1,0,0);
INSERT INTO operation VALUES('307','22','3002',2,0,0);
INSERT INTO operation VALUES('308','23','3002',2,1,0);
INSERT INTO operation VALUES('309','24','3002',2,1,1);
INSERT INTO operation VALUES('310','25','3002',2,1,2);
INSERT INTO operation VALUES('401','26','4001',0,0,0);
INSERT INTO operation VALUES('402','27','4001',1,0,0);
INSERT INTO operation VALUES('403','28','4001',1,1,0);
INSERT INTO operation VALUES('404','29','4001',1,1,1);
INSERT INTO operation VALUES('405','30','4001',1,1,2);
INSERT INTO operation VALUES('406','31','4002',1,0,0);
INSERT INTO operation VALUES('407','32','4002',2,0,0);
INSERT INTO operation VALUES('408','33','4002',2,1,0);
INSERT INTO operation VALUES('409','34','4002',2,1,1);
INSERT INTO operation VALUES('410','35','4002',2,1,2);
INSERT INTO operation VALUES('501','36','5001',0,0,0);
INSERT INTO operation VALUES('502','37','5001',1,0,0);
INSERT INTO operation VALUES('503','38','5001',1,1,0);
INSERT INTO operation VALUES('504','39','5001',1,1,1);
INSERT INTO operation VALUES('505','40','5001',1,1,2);
INSERT INTO operation VALUES('601','41','6001',1,0,0);
INSERT INTO operation VALUES('602','42','6001',2,0,0);
INSERT INTO operation VALUES('603','43','6001',2,1,0);
INSERT INTO operation VALUES('604','44','6001',2,1,1);
INSERT INTO operation VALUES('605','45','6001',2,1,2);
INSERT INTO operation VALUES('701','46','7001',0,0,0);
INSERT INTO operation VALUES('702','47','7001',1,0,0);
INSERT INTO operation VALUES('703','48','7001',1,1,0);
INSERT INTO operation VALUES('704','49','7001',1,1,1);
INSERT INTO operation VALUES('705','50','7001',1,1,2);
INSERT INTO operation VALUES('801','51','8001',1,0,0);
INSERT INTO operation VALUES('802','52','8001',2,0,0);
INSERT INTO operation VALUES('803','53','8001',2,1,0);
INSERT INTO operation VALUES('804','54','8001',2,1,1);
INSERT INTO operation VALUES('805','55','8001',2,1,2);
INSERT INTO operation VALUES('111','1','1101',0,0,0);
INSERT INTO operation VALUES('112','2','1101',1,0,0);
INSERT INTO operation VALUES('113','3','1101',1,1,0);
INSERT INTO operation VALUES('114','4','1101',1,1,1);
INSERT INTO operation VALUES('115','5','1101',1,1,2);
INSERT INTO operation VALUES('116','1','1102',0,0,0);
INSERT INTO operation VALUES('117','2','1102',1,0,0);
INSERT INTO operation VALUES('118','3','1102',1,1,0);
INSERT INTO operation VALUES('119','4','1102',1,1,1);
INSERT INTO operation VALUES('120','5','1102',1,1,2);
INSERT INTO operation VALUES('211','6','2201',0,0,0);
INSERT INTO operation VALUES('212','7','2201',1,0,0);
INSERT INTO operation VALUES('213','8','2201',1,1,0);
INSERT INTO operation VALUES('214','9','2201',1,1,1);
INSERT INTO operation VALUES('215','10','2201',1,1,2);
INSERT INTO operation VALUES('216','11','2202',0,0,0);
INSERT INTO operation VALUES('217','12','2202',1,0,0);
INSERT INTO operation VALUES('218','13','2202',1,1,0);
INSERT INTO operation VALUES('219','14','2202',1,1,1);
INSERT INTO operation VALUES('220','15','2202',1,1,2);
INSERT INTO operation VALUES('311','16','3301',0,0,0);
INSERT INTO operation VALUES('312','17','3301',1,0,0);
INSERT INTO operation VALUES('313','18','3301',1,1,0);
INSERT INTO operation VALUES('314','19','3301',1,1,1);
INSERT INTO operation VALUES('315','20','3301',1,1,2);
INSERT INTO operation VALUES('316','21','3302',1,0,0);
INSERT INTO operation VALUES('317','22','3302',2,0,0);
INSERT INTO operation VALUES('318','23','3302',2,1,0);
INSERT INTO operation VALUES('319','24','3302',2,1,1);
INSERT INTO operation VALUES('320','25','3302',2,1,2);
INSERT INTO operation VALUES('411','26','4401',0,0,0);
INSERT INTO operation VALUES('412','27','4401',1,0,0);
INSERT INTO operation VALUES('413','28','4401',1,1,0);
INSERT INTO operation VALUES('414','29','4401',1,1,1);
INSERT INTO operation VALUES('415','30','4401',1,1,2);
INSERT INTO operation VALUES('416','31','4402',1,0,0);
INSERT INTO operation VALUES('417','32','4402',2,0,0);
INSERT INTO operation VALUES('418','33','4402',2,1,0);
INSERT INTO operation VALUES('419','34','4402',2,1,1);
INSERT INTO operation VALUES('420','35','4402',2,1,2);
INSERT INTO operation VALUES('511','36','5501',0,0,0);
INSERT INTO operation VALUES('512','37','5501',1,0,0);
INSERT INTO operation VALUES('513','38','5501',1,1,0);
INSERT INTO operation VALUES('514','39','5501',1,1,1);
INSERT INTO operation VALUES('515','40','5501',1,1,2);
INSERT INTO operation VALUES('611','41','6601',1,0,0);
INSERT INTO operation VALUES('612','42','6601',2,0,0);
INSERT INTO operation VALUES('613','43','6601',2,1,0);
INSERT INTO operation VALUES('614','44','6601',2,1,1);
INSERT INTO operation VALUES('615','45','6601',2,1,2);
INSERT INTO operation VALUES('711','46','7701',0,0,0);
INSERT INTO operation VALUES('712','47','7701',1,0,0);
INSERT INTO operation VALUES('713','48','7701',1,1,0);
INSERT INTO operation VALUES('714','49','7701',1,1,1);
INSERT INTO operation VALUES('715','50','7701',1,1,2);
INSERT INTO operation VALUES('811','51','8801',1,0,0);
INSERT INTO operation VALUES('812','52','8801',2,0,0);
INSERT INTO operation VALUES('813','53','8801',2,1,0);
INSERT INTO operation VALUES('814','54','8801',2,1,1);
INSERT INTO operation VALUES('815','55','8801',2,1,2);

--criar procedures
CREATE OR REPLACE PROCEDURE insertContainer(
    p_container_id IN container.container_id%type,
    p_container_payload IN container.container_payload%type,
    p_container_tare IN container.container_tare%type,
    p_container_gross IN container.container_gross%type,
    p_iso_code IN container.iso_code%type,
    p_temperature IN container.temperature%type,
    p_container_type IN container.container_type%type,
    p_container_load IN container.container_load%type)
    IS
BEGIN

    INSERT INTO container
    VALUES(p_container_id,p_container_payload,p_container_tare,p_container_gross,p_iso_code,p_temperature,p_container_type,p_container_load)

    COMMIT
END
/

CREATE OR REPLACE PROCEDURE insertVehicle(
    p_vehicle_id IN vehicle.vehicle_id%type,
    p_vehicle_type IN vehicle.type%type)
    IS
BEGIN

    INSERT INTO vehicle
    VALUES(p_vehicle_id,p_vehicle_type)

    COMMIT
END
/

CREATE OR REPLACE PROCEDURE insertTruck(
    p_truck_id IN truck.vehicle_truck_id%type,
    p_plate IN truck.plate%type)
    IS
BEGIN

    INSERT INTO truck
    VALUES(p_truck_id,p_plate)

    COMMIT
END
/

CREATE OR REPLACE PROCEDURE insertShip(
    p_ship_id IN ship.vehicle_ship_id%type,
    p_shipname IN ship.shipname%type,
    p_mmsi IN ship.mmsi%type,
    p_imo IN ship.imo%type,
    p_generators_number IN ship.generators_number%type,
    p_generators_power IN ship.generators_power%type,
    p_call_sign IN ship.call_sign%type,
    p_vesel_type IN ship.vesel_type%type,
    p_length IN ship.length%type,
    p_width IN ship.width%type,
    p_capacity IN ship.capacity%type,
    p_draft IN ship.draft%type)
    IS
BEGIN

    INSERT INTO ship
    VALUES(p_ship_id,p_shipname,p_mmsi,p_imo,p_generators_number,p_generators_power,p_call_sign,p_vesel_type,p_length,p_width,p_capacity,p_draft)

    COMMIT
END
/

CREATE OR REPLACE PROCEDURE insertPositionData(
    p_position_data_id IN position_data.position_data_id%type,
    p_vehicle_ship_id IN position_data.vehicle_ship_id%type,
    p_bdt IN position_data.bdt%type,
    p_latitude IN position_data.latitude%type,
    p_longitude IN position_data.longitude%type,
    p_sog IN position_data.sog%type,
    p_cog IN position_data.cog%type,
    p_heading IN position_data.heading%type,
    p_position IN position_data.position%type,
    p_transceiver_class IN position_data.transceiver_class%type)
    IS
BEGIN

    INSERT INTO position_data
    VALUES(p_position_data_id,p_vehicle_ship_id,p_bdt,p_latitude,p_longitude,p_sog,p_cog,p_heading,p_position,p_transceiver_class)

    COMMIT
END
/

CREATE OR REPLACE PROCEDURE insertLocal(
    p_local_id IN local.local_id%type,
    p_local_name IN local.local_name%type,
    p_local_continent IN local.local_continent%type,
    p_local_country IN local.local_country%type,
    p_local_latitude IN local.local_lalitude%type,
    p_local_altitude IN local.local_altitude%type)
    IS
BEGIN

    INSERT INTO local
    VALUES(p_local_id,p_local_name,p_local_continent,p_local_country,p_local_latitude,p_local_altitude)

    COMMIT
END
/

CREATE OR REPLACE PROCEDURE insertCargoManifest(
    p_cargo_manifest_id IN cargo_manifest.cargo_manifest_id%type,
    p_vehicle_id IN cargo_manifest.vehicle_id%type,
    p_local_id IN cargo_manifest.local_id%type,
    p_next_local_id IN cargo_manifest.next_local_id%type,
    p_cm_date IN cargo_manifest.cm_date%type,
    p_operation_type IN cargo_manifest.operation_type%type)
    IS
BEGIN

    INSERT INTO cargo_manifest
    VALUES(p_cargo_manifest_id,p_vehicle_id,p_local_id,p_next_local_id,p_cm_date,p_operation_type)

    COMMIT
END
/

CREATE OR REPLACE PROCEDURE insertOperation(
    p_operation_id IN operation.operation_id%type,
    p_container_id IN operation.container_id%type,
    p_cargo_manifest_id IN operation.cargo_manifest_id%type,
    p_coordinate_x IN operation.coordinate_x%type,
    p_coordinate_y IN operation.coordinate_y%type,
    p_coordinate_z IN operation.coordinate_z%type)
    IS
BEGIN

    INSERT INTO operation
    VALUES(p_operation_id,p_container_id,p_cargo_manifest_id,p_coordinate_x,p_coordinate_y,p_coordinate_z)

    COMMIT
END
/
--criar funções
CREATE OR REPLACE function func_freeships return sys_refcursor
    IS
    ships_available sys_refcursor
BEGIN
    open ships_available for
        SELECT NEXT_DAY(CURRENT_TIMESTAMP,'MONDAY') AS NEXT_MONDAY, vehicle_ship_id as AVAILABLE_SHIP
        FROM ship WHERE vehicle_ship_id NOT IN (SELECT vehicle_id FROM cargo_manifest WHERE cm_date <= NEXT_DAY(CURRENT_TIMESTAMP,'MONDAY') GROUP BY vehicle_id)
                     OR vehicle_ship_id IN (SELECT vehicle_ship_id FROM ship where
                    vehicle_ship_id in (SELECT cargo_manifest.vehicle_id FROM cargo_manifest
                                                                                  INNER JOIN (SELECT vehicle_id, MAX(CM_DATE) AS maxdate
                                                                                              FROM cargo_manifest WHERE cm_date <= NEXT_DAY(CURRENT_TIMESTAMP,'MONDAY') GROUP BY vehicle_id) md ON cargo_manifest.vehicle_id = md.vehicle_id AND CM_DATE = maxdate
                                        WHERE next_local_id is null))
    return ships_available
END
/

CREATE OR REPLACE function containers_to_load(p_ship_id ship.vehicle_ship_id%type) return sys_refcursor
    IS
    load_containers sys_refcursor
BEGIN
    OPEN load_containers FOR

        SELECT operation.container_id, container.container_type,container.container_load
        FROM operation INNER JOIN container ON operation.container_id = container.container_id
        WHERE cargo_manifest_id = (SELECT cargo_manifest_id FROM cargo_manifest WHERE UPPER(operation_type) = 'LOAD' AND cm_date > (SELECT cm_date FROM cargo_manifest
                                                                                                                                    WHERE cm_date <= CURRENT_TIMESTAMP AND vehicle_id = p_ship_id ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY)
                                                                                  AND local_id = (SELECT next_local_id FROM cargo_manifest WHERE cm_date <= CURRENT_TIMESTAMP AND vehicle_id = p_ship_id ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY)
                                                                                  AND vehicle_id = p_ship_id ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY)
    return (load_containers)
END
/

CREATE OR REPLACE function containers_to_offload(p_ship_id ship.vehicle_ship_id%type) return sys_refcursor
    IS
    offload_containers sys_refcursor
BEGIN
    OPEN offload_containers FOR

        SELECT operation.container_id,container.container_type, COORDINATE_X,COORDINATE_Y,COORDINATE_Z,container.container_load
        FROM operation INNER JOIN container ON operation.container_id = container.container_id
        WHERE cargo_manifest_id = (SELECT cargo_manifest_id FROM cargo_manifest WHERE UPPER(operation_type) = 'UNLOAD' AND cm_date > (SELECT cm_date FROM cargo_manifest
                                                                                                                                      WHERE cm_date <= CURRENT_TIMESTAMP AND vehicle_id = p_ship_id ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY)
                                                                                  AND local_id = (SELECT next_local_id FROM cargo_manifest WHERE cm_date <= CURRENT_TIMESTAMP AND vehicle_id = p_ship_id ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY)
                                                                                  AND vehicle_id = p_ship_id ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY)
    return (offload_containers)
END
/

CREATE OR REPLACE function func_occupancy_rate(p_ship_id ship.vehicle_ship_id%type,p_cargo_id cargo_manifest.cargo_manifest_id%type) return float
    IS
    containers_cargo int
    ship_capacity float
BEGIN
    select count(container_id) into containers_cargo
    from operation
    where cargo_manifest_id = (SELECT cargo_manifest_id FROM cargo_manifest WHERE cargo_manifest_id = p_cargo_id and UPPER(operation_type) = 'LOAD')
    select capacity into ship_capacity
    from ship
    where vehicle_ship_id = p_ship_id
    return containers_cargo / ship_capacity * 100
END
/

CREATE OR REPLACE function func_occupancy_rate_now(p_ship_id ship.vehicle_ship_id%type) return float
    IS
    cm cargo_manifest.cargo_manifest_id%type
BEGIN
    SELECT cargo_manifest_id into cm
    FROM cargo_manifest
    WHERE cm_date <= CURRENT_TIMESTAMP AND vehicle_id = p_ship_id
    ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY
    return func_occupancy_rate(p_ship_id,cm)
END
/

create or replace FUNCTION a_cm (ano NUMBER) RETURN SYS_REFCURSOR
    IS
    cur sys_refcursor
BEGIN
    OPEN cur for
        select count(DISTINCT cargo_manifest.cargo_manifest_id)
            as CARGO_MANIFESTS,(COUNT(operation.CONTAINER_ID)/count(distinct CARGO_MANIFEST.cargo_manifest_id))
            FROM cargo_manifest INNER JOIN operation ON OPERATION.CARGO_MANIFEST_ID=CARGO_MANIFEST.CARGO_MANIFEST_ID
            where EXTRACT (YEAR FROM cm_date)= ano group by EXTRACT(YEAR FROM cargo_manifest.cm_date)
    return cur
end


SET SERVEROUTPUT ON
DECLARE
    curs SYS_REFCURSOR
    anos NUMBER
    media FLOAT

BEGIN
    curs:=a_cm(2021)
    LOOP
        FETCH curs into anos,media
        EXIT WHEN curs%NOTFOUND
        DBMS_OUTPUT.PUT_LINE('cargo manifests:'||anos)
        DBMS_OUTPUT.PUT_LINE('media por ano:'||media)
    END LOOP
END
/