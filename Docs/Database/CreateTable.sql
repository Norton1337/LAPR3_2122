DROP TABLE container		    CASCADE CONSTRAINTS PURGE;
DROP TABLE vehicle	            CASCADE CONSTRAINTS PURGE;
DROP TABLE truck		    CASCADE CONSTRAINTS PURGE;
DROP TABLE ship	                    CASCADE CONSTRAINTS PURGE;
DROP TABLE position_data            CASCADE CONSTRAINTS PURGE;
DROP TABLE local	            CASCADE CONSTRAINTS PURGE;
DROP TABLE cargo_manifest 	    CASCADE CONSTRAINTS PURGE;
DROP TABLE operation	 	    CASCADE CONSTRAINTS PURGE;

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