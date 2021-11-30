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
                          container_payload float(50) CONSTRAINT nn_container_payload NOT NULL,
                          container_tare float(50) CONSTRAINT nn_container_tare NOT NULL,
                          container_gross float(50) CONSTRAINT nn_container_gross NOT NULL,
                          iso_code varchar(5) CONSTRAINT nn_iso_code NOT NULL,
                          temperature float(10),
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
INSERT INTO container VALUES('1',9500,500,25000,'20G0',20,'Not Refrigerated','Brinquedos');
INSERT INTO container VALUES('2',9500,500,25000,'20G0',20,'Not Refrigerated','Brinquedos');
INSERT INTO container VALUES('3',9500,500,25000,'20G0',20,'Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('4',9500,500,25000,'20G0',20,'Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('5',9500,500,25000,'20H0',8,'Refrigerated','Comida');
INSERT INTO container VALUES('6',9500,500,25000,'20H0',8,'Refrigerated','Comida');
INSERT INTO container VALUES('7',9500,500,25000,'20G0',20,'Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('8',9500,500,25000,'20H0',8,'Refrigerated','Comida');
INSERT INTO container VALUES('9',9500,500,25000,'20G0',20,'Not Refrigerated','Tecnologia');
INSERT INTO container VALUES('10',9500,500,25000,'20H0',8,'Refrigerated','Tecnologia');

INSERT INTO local VALUES('30','BRAGA','EUROPA','PORTUGAL',61.183,-0.67977);
INSERT INTO local VALUES('31','MATOSINHOS','EUROPA','PORTUGAL',41.183,-8.67977);
INSERT INTO local VALUES('32','PORTO','EUROPA','PORTUGAL',41.15,-8.61024);
INSERT INTO local VALUES('33','AVEIRO','EUROPA','PORTUGAL',40.64, -8.65362);
INSERT INTO local VALUES('34','FIGUEIRA DA FOZ','EUROPA','PORTUGAL',40.15,-8.85121);
INSERT INTO local VALUES('35','LISBOA','EUROPA','PORTUGAL',39.783,-9.13547);
INSERT INTO local VALUES('36','ALGARVE','EUROPA','PORTUGAL',37.089, -8.25228);
INSERT INTO local VALUES('37','BARCELONA','EUROPA','ESPANHA',41.3879,2.16992);
INSERT INTO local VALUES('38','MARSELHA','EUROPA','FRANÇA',43.2698, 5.39585);
INSERT INTO local VALUES('39','CAGLIARI','EUROPA','ITÁLIA',39.227779,9.111111);

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

INSERT INTO cargo_manifest VALUES('2000','41','30','31',(timestamp '2021-09-15 21:12:23'),'Load');
INSERT INTO cargo_manifest VALUES('3000','42','31','32',(timestamp '2021-10-20 22:02:23'),'Load');
INSERT INTO cargo_manifest VALUES('4000','43','32','33',(timestamp '2021-11-14 21:42:23'),'Load');
INSERT INTO cargo_manifest VALUES('5000','44','33','34',(timestamp '2021-11-12 22:20:23'),'Load');
INSERT INTO cargo_manifest VALUES('2001','41','31',NULL,(timestamp '2021-11-30 21:12:23'),'Unload');
INSERT INTO cargo_manifest VALUES('3001','42','32','33',(timestamp '2021-12-1 22:02:23'),'Unload');
INSERT INTO cargo_manifest VALUES('4001','43','32','33',(timestamp '2021-12-1 21:42:23'),'Load');
INSERT INTO cargo_manifest VALUES('5001','44','33','34',(timestamp '2021-12-1 22:20:23'),'Load');
INSERT INTO cargo_manifest VALUES('6000','45','34','35',(timestamp '2021-11-17 12:22:23'),'Load');
INSERT INTO cargo_manifest VALUES('7000','46','35','37',(timestamp '2021-11-30 05:22:23'),'Load');
INSERT INTO cargo_manifest VALUES('8000','47','36','38',(timestamp '2021-12-27 07:22:23'),'Load');
INSERT INTO cargo_manifest VALUES('9000','48','37','39',(timestamp '2021-12-14 23:22:23'),'Load');
INSERT INTO cargo_manifest VALUES('10000','46','35',NULL,(timestamp '2022-11-24 21:42:23'),'Unload');
INSERT INTO cargo_manifest VALUES('11000','47','36',NULL,(timestamp '2022-01-05 21:21:23'),'Unload');
INSERT INTO cargo_manifest VALUES('12000','48','37',NULL,(timestamp '2022-02-08 20:24:23'),'Unload');

INSERT INTO operation VALUES('100','1','2000',0,0,0);
INSERT INTO operation VALUES('450','10','2000',1,0,6);
INSERT INTO operation VALUES('150','2','3000',0,0,0);
INSERT INTO operation VALUES('200','3','3000',1,0,6);
INSERT INTO operation VALUES('250','4','4000',0,0,0);
INSERT INTO operation VALUES('300','5','5000',0,0,0);
INSERT INTO operation VALUES('350','6','6000',0,0,0);
INSERT INTO operation VALUES('400','7','7000',0,0,0);
INSERT INTO operation VALUES('451','8','8000',0,0,0);
INSERT INTO operation VALUES('500','9','9000',0,0,0);
INSERT INTO operation VALUES('550','7','10000',0,0,0);
INSERT INTO operation VALUES('600','8','11000',0,0,0);
INSERT INTO operation VALUES('650','9','12000',0,0,0);
INSERT INTO operation VALUES('700','3','2001',0,0,0);
INSERT INTO operation VALUES('750','9','3001',0,0,0);
INSERT INTO operation VALUES('850','4','3001',0,0,0);
INSERT INTO operation VALUES('900','5','4001',2,5,3);
INSERT INTO operation VALUES('950','7','5001',1,5,0);


CREATE OR REPLACE function func_freeships return sys_refcursor
IS
    ships_available sys_refcursor
BEGIN
    open ships_available for
        SELECT NEXT_DAY(CURRENT_TIMESTAMP,'MONDAY') AS NEXT_MONDAY, vehicle_ship_id as AVAILABLE_SHIP
        FROM ship WHERE vehicle_ship_id NOT IN (SELECT vehicle_id FROM cargo_manifest WHERE cm_date <= NEXT_DAY(CURRENT_TIMESTAMP,'MONDAY') GROUP BY vehicle_id) 
        OR vehicle_ship_id IN (SELECT vehicle_ship_id FROM ship where 
        vehicle_ship_id in (SELECT cargo_manifest.vehicle_id FROM cargo_manifest
        INNER JOIN (SELECT vehicle_id, MAX(CM_DATE) AS maxdate FROM cargo_manifest WHERE cm_date <= NEXT_DAY(CURRENT_TIMESTAMP,'MONDAY')
        GROUP BY vehicle_id) md ON cargo_manifest.vehicle_id = md.vehicle_id AND CM_DATE = maxdate
        WHERE next_local_id is null))
return ships_available
END
/

CREATE OR REPLACE function containers_to_load(p_ship_id ship.vehicle_ship_id%type) return sys_refcursor
IS
    load_containers sys_refcursor
BEGIN
    OPEN load_containers FOR

    SELECT operation.container_id,container.container_type,container.container_payload,COORDINATE_X,COORDINATE_Y,COORDINATE_Z,container.container_load
    FROM operation INNER JOIN container ON operation.container_id = container.container_id
    WHERE cargo_manifest_id = (SELECT cargo_manifest_id FROM cargo_manifest WHERE UPPER(operation_type) = 'LOAD' AND cm_date > (SELECT cm_date FROM cargo_manifest 
    WHERE cm_date <= CURRENT_TIMESTAMP AND vehicle_id = p_ship_id ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY) 
    AND local_id = (SELECT next_local_id FROM cargo_manifest WHERE cm_date <= CURRENT_TIMESTAMP AND vehicle_id = p_ship_id ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY)
    AND vehicle_id = p_ship_id ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY)
    return load_containers
END
/

CREATE OR REPLACE function containers_to_offload(p_ship_id ship.vehicle_ship_id%type) return sys_refcursor
IS
    offload_containers sys_refcursor
BEGIN
    OPEN offload_containers FOR

    SELECT operation.container_id,container.container_type,container.container_payload,COORDINATE_X,COORDINATE_Y,COORDINATE_Z,container.container_load
    FROM operation INNER JOIN container ON operation.container_id = container.container_id
    WHERE cargo_manifest_id = (SELECT cargo_manifest_id FROM cargo_manifest WHERE UPPER(operation_type) = 'UNLOAD' AND cm_date > (SELECT cm_date FROM cargo_manifest 
    WHERE cm_date <= CURRENT_TIMESTAMP AND vehicle_id = p_ship_id ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY) 
    AND local_id = (SELECT next_local_id FROM cargo_manifest WHERE cm_date <= CURRENT_TIMESTAMP AND vehicle_id = p_ship_id ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY)
    AND vehicle_id = p_ship_id ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY)
    return offload_containers
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