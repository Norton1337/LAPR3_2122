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
INSERT INTO container VALUES('87',9500,500,1345,'45G1',8,'Refrigerated');
INSERT INTO local VALUES('74','MATOSINHOS','EUROPA','PORTUGAL',41.183,-8.67977);
INSERT INTO local VALUES('75','PORTO','EUROPA','PORTUGAL',41.15,-8.61024);
INSERT INTO vehicle VALUES('12','Ship');
INSERT INTO ship VALUES('12','barco', '235762000','8814275','50',120,'ZCEF2',70,150,35,100000,18);
INSERT INTO position_data VALUES('1015','12',(timestamp '2021-11-30 21:22:23'),40.6412,-8.65362,35,140,200,50,'Class A');
INSERT INTO cargo_manifest VALUES('2023','12','74','75',(timestamp '2021-11-30 22:22:23'),'Load');
INSERT INTO operation VALUES('115','87','2023',1,0,0);

INSERT INTO container VALUES('27',9500,500,1345,'12C1',8,'Not Refrigerated');
INSERT INTO local VALUES('14','BRAGA','EUROPA','PORTUGAL',61.183,-0.67977);
INSERT INTO local VALUES('15','LISBOA','EUROPA','PORTUGAL',43.15,-7.61024);
INSERT INTO vehicle VALUES('99','Truck');
INSERT INTO truck VALUES('99','99-BC-47');
INSERT INTO cargo_manifest VALUES('1087','99','14','15',(timestamp '2021-11-30 21:22:23'),'Unload');
INSERT INTO operation VALUES('1017','27','1087',NULL,NULL,NULL);


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

    SELECT operation.container_id,container.container_type,container.container_payload,COORDINATE_X,COORDINATE_Y,COORDINATE_Z
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

    SELECT operation.container_id,container.container_type,container.container_payload,COORDINATE_X,COORDINATE_Y,COORDINATE_Z 
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