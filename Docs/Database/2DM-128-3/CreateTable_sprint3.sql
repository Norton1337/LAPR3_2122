--eliminar tabelas (eventualmente) existentes
DROP TABLE container		    CASCADE CONSTRAINTS PURGE;
DROP TABLE vehicle	            CASCADE CONSTRAINTS PURGE;
DROP TABLE truck		    CASCADE CONSTRAINTS PURGE;
DROP TABLE ship	                    CASCADE CONSTRAINTS PURGE;
DROP TABLE position_data            CASCADE CONSTRAINTS PURGE;
DROP TABLE borders	 	    CASCADE CONSTRAINTS PURGE;
DROP TABLE seadists	 	    CASCADE CONSTRAINTS PURGE;
DROP TABLE country	 	    CASCADE CONSTRAINTS PURGE;
DROP TABLE local	            CASCADE CONSTRAINTS PURGE;
DROP TABLE cargo_manifest 	    CASCADE CONSTRAINTS PURGE;
DROP TABLE operation	 	    CASCADE CONSTRAINTS PURGE;
DROP TABLE users	 	    CASCADE CONSTRAINTS PURGE;
DROP TABLE client	 	    CASCADE CONSTRAINTS PURGE;
DROP TABLE leasing	 	    CASCADE CONSTRAINTS PURGE;
DROP TABLE audit_trail	 	    CASCADE CONSTRAINTS PURGE;

--criar tabelas
CREATE TABLE container(
    container_id varchar(20),
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
	vehicle_id varchar(20),
	type varchar(20) CONSTRAINT nn_type NOT NULL,
	CONSTRAINT pk_vehicle_id PRIMARY KEY (vehicle_id),
	CONSTRAINT ck_type CHECK (type = 'Truck' OR type = 'Ship')
);

CREATE TABLE truck(
	vehicle_truck_id varchar(20) ,
	plate varchar(255) CONSTRAINT nn_plate NOT NULL,
	CONSTRAINT pk_vehicle_truck_id PRIMARY KEY (vehicle_truck_id),
	CONSTRAINT fk_vehicle_truck_id FOREIGN KEY (vehicle_truck_id)REFERENCES vehicle(vehicle_id),
	CONSTRAINT un_plate UNIQUE (plate)
);

CREATE TABLE ship(
                     vehicle_ship_id varchar(20) ,
                     shipname varchar(255) CONSTRAINT nn_shipname NOT NULL,
                     mmsi char(9) CONSTRAINT nn_mmsi NOT NULL,
                     imo char(10) CONSTRAINT nn_imo NOT NULL,
                     generators_number NUMBER(10) CONSTRAINT nn_generators_number NOT NULL,
                     generators_power float(10) CONSTRAINT nn_generators_power NOT NULL,
                     call_sign varchar(255) CONSTRAINT nn_call_sign NOT NULL,
                     vesel_type NUMBER(10) CONSTRAINT nn_vesel_type NOT NULL,
                     length float(10) CONSTRAINT nn_length NOT NULL,
                     width float(10) CONSTRAINT nn_width NOT NULL,
                     capacity NUMBER(10) CONSTRAINT nn_capacity NOT NULL,
                     draft float(10) CONSTRAINT nn_draft NOT NULL,
                     CONSTRAINT pk_vehicle_ship_id PRIMARY KEY (vehicle_ship_id),
                     CONSTRAINT fk_vehicle_ship_id FOREIGN KEY (vehicle_ship_id) REFERENCES vehicle(vehicle_id),
                     CONSTRAINT un_mmsi UNIQUE (mmsi),
                     CONSTRAINT un_imo UNIQUE (imo),
                     CONSTRAINT un_call_sign UNIQUE (call_sign),
                     CONSTRAINT ck_mmsi CHECK(REGEXP_LIKE(mmsi,'[0-9]{9}')),
                     CONSTRAINT ck_imo CHECK (REGEXP_LIKE(imo,'IMO+[0-9]{7}')),
                     CONSTRAINT ck_generators_number CHECK(generators_number>=0),
                     CONSTRAINT ck_generators_power CHECK(generators_power>=0),
                     CONSTRAINT ck_length CHECK(length>=0),
                     CONSTRAINT ck_width CHECK(width>=0),
                     CONSTRAINT ck_capacity CHECK(capacity>=0)
);

CREATE TABLE position_data(
	position_data_id varchar(20) ,
	vehicle_ship_id varchar(20),
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

CREATE TABLE country (
      country_id      varchar(20) , 
      continent       varchar2(20) CONSTRAINT nn_country_continent NOT NULL,
      alpha_2_code    varchar2(2) CONSTRAINT nn_alpha2code NOT NULL,
      alpha_3_code    varchar2(3) CONSTRAINT nn_alpha3code NOT NULL,
      country_name    varchar2(30) CONSTRAINT nn_country_name NOT NULL, 
      population      float(10) CONSTRAINT nn_country_population NOT NULL, 
      country_capital varchar2(30) CONSTRAINT nn_country_capital NOT NULL, 
      latitude        float(10) CONSTRAINT nn_country_latitude NOT NULL, 
      longitude       float(10) CONSTRAINT nn_country_longitude NOT NULL, 
      CONSTRAINT pk_country_id PRIMARY KEY (country_id)
  );

CREATE TABLE local(
	local_id varchar(20) ,
    local_port_id varchar(20),
    local_code NUMBER(10) CONSTRAINT nn_local_code NOT NULL,
    local_capacity NUMBER(10),
	local_name varchar(255) CONSTRAINT nn_local_name NOT NULL,
    local_type varchar(20) CONSTRAINT nn_local_type NOT NULL,
	local_country_id varchar(20) CONSTRAINT nn_local_country NOT NULL,
	local_lalitude float(10) CONSTRAINT nn_local_lalitude NOT NULL,
	local_longitude float(10) CONSTRAINT nn_local_altitude NOT NULL,
    CONSTRAINT fk_local_country_id FOREIGN KEY (local_country_id) REFERENCES country(country_id),
	CONSTRAINT pk_local_id PRIMARY KEY (local_id),
    CONSTRAINT fk_local_port_id FOREIGN KEY (local_port_id) REFERENCES local(local_id),
    CONSTRAINT ck_local_type CHECK( local_type = 'Port' OR local_type =  'Warehouse')
);

CREATE TABLE cargo_manifest (
    cargo_manifest_id varchar(20),
    vehicle_id varchar(20),
    local_id varchar(20),
    next_local_id varchar(20),
    cm_date TIMESTAMP(0) CONSTRAINT nn_date NOT NULL,
    operation_type varchar(20) CONSTRAINT nn_operation_type NOT NULL,
    CONSTRAINT fk_next_local_id FOREIGN KEY (next_local_id) REFERENCES local(local_id),
    CONSTRAINT fk_local_id FOREIGN KEY (local_id) REFERENCES local(local_id),
    CONSTRAINT fk_vehicle_id FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id),
    CONSTRAINT pk_cargo_manifest_id PRIMARY KEY (cargo_manifest_id),
    CONSTRAINT ck_operation_type CHECK ( operation_type = 'Load' OR operation_type = 'Unload')
);

CREATE TABLE operation (
    operation_id varchar(20),
    container_id varchar(20),
    cargo_manifest_id varchar(20),
    operation_warehouse_id varchar(20),
    coordinate_x NUMBER(10),
    coordinate_y NUMBER(10),
    coordinate_z NUMBER(10),
    CONSTRAINT fk_container_id FOREIGN KEY (container_id) REFERENCES container(container_id),
    CONSTRAINT fk_cargo_manifest_id FOREIGN KEY (cargo_manifest_id) REFERENCES cargo_manifest(cargo_manifest_id),
    CONSTRAINT fk_warehouse_id FOREIGN KEY (operation_warehouse_id) REFERENCES local(local_id),
    CONSTRAINT pk_operation_id PRIMARY KEY (operation_id),
    CONSTRAINT ck_coordinate_x CHECK(coordinate_x>=0),
    CONSTRAINT ck_coordinate_y CHECK(coordinate_y>=0),
    CONSTRAINT ck_coordinate_z CHECK(coordinate_z>=0)
);

CREATE TABLE users (
    user_id varchar(20),
    username varchar(20) CONSTRAINT nn_username NOT NULL,
    user_password varchar(20) CONSTRAINT nn_password NOT NULL,
    user_role varchar(20) CONSTRAINT nn_role NOT NULL,
    CONSTRAINT pk_user_id PRIMARY KEY (user_id),
    CONSTRAINT un_username UNIQUE (username)
);

CREATE TABLE client (
    client_id varchar(20),
    user_id varchar(20),
    client_name varchar(50) CONSTRAINT nn_client_name NOT NULL,
    client_address varchar(50) CONSTRAINT nn_client_address NOT NULL,
    client_cellphone varchar(50) CONSTRAINT nn_client_cellphone NOT NULL,
    CONSTRAINT pk_client_id PRIMARY KEY (client_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE leasing (
    leasing_id varchar(20),
    client_id varchar(20), 
    container_id varchar(20),
    leasing_start_date TIMESTAMP(0) CONSTRAINT nn_leasing_start NOT NULL,
    leasing_end_date TIMESTAMP(0) CONSTRAINT nn_leasing_end NOT NULL,
    CONSTRAINT pk_leasing_id PRIMARY KEY (leasing_id),
    CONSTRAINT fk_client_id FOREIGN KEY (client_id) REFERENCES client(client_id),
    CONSTRAINT fk_leasing_container_id FOREIGN KEY (container_id) REFERENCES container(container_id)
  );
  

  
  CREATE TABLE borders (
      border_id     varchar(20) , 
      country_id    varchar(20) NOT NULL, 
      country_id2   varchar(20) NOT NULL, 
      CONSTRAINT pk_border_id PRIMARY KEY (border_id),
      CONSTRAINT fk_country_id FOREIGN KEY (country_id) REFERENCES country (country_id),
      CONSTRAINT fk_country_id2 FOREIGN KEY (country_id2) REFERENCES country (country_id)
  );
  
  CREATE TABLE seadists (
      seadist_id varchar(20) , 
      fromPort   varchar(20), 
      toPort     varchar(20), 
      distance   float(10) CONSTRAINT nn_distance NOT NULL, 
      CONSTRAINT pk_seadist_id PRIMARY KEY (seadist_id),
      CONSTRAINT fk_from_port FOREIGN KEY (fromPort) REFERENCES local(local_id),
      CONSTRAINT fk_to_port FOREIGN KEY (toPort) REFERENCES local(local_id)
  );
  
  CREATE TABLE audit_trail (
    audit_trail_id varchar(20),
    audit_trail_username varchar(20) CONSTRAINT nn_audit_trail_username NOT NULL,
    type_instruction varchar(10) CONSTRAINT nn_type_instruction NOT NULL,
    audit_trail_date TIMESTAMP(0) CONSTRAINT nn_audit_trail_date NOT NULL,
    audit_container_id varchar(20),
    audit_cargo_manifest_id varchar(20),
    CONSTRAINT pk_audit_trail_id PRIMARY KEY (audit_trail_id),
    CONSTRAINT fk_audit_container_id FOREIGN KEY (audit_container_id) REFERENCES container(container_id)
    );

--triggers on tables  
CREATE OR REPLACE TRIGGER audit_logs
AFTER INSERT OR DELETE OR UPDATE ON operation
FOR EACH ROW
DECLARE 
username_actual user_users.username%type;
cdate TIMESTAMP(0);
BEGIN
 SELECT user_users.username into username_actual FROM user_users;
 SELECT SYSTIMESTAMP into cdate FROM DUAL;
IF DELETING THEN
    INSERT INTO audit_trail
    VALUES (DBMS_RANDOM.string('p',20),username_actual, 'DELETE',cdate, :OLD.container_id,:OLD.cargo_manifest_id); 
END IF;
IF INSERTING THEN
    INSERT INTO audit_trail
    VALUES (DBMS_RANDOM.string('p',20),username_actual, 'INSERT',cdate, :NEW.container_id,:NEW.cargo_manifest_id); 
END IF;
IF UPDATING THEN
    INSERT INTO audit_trail
    VALUES (DBMS_RANDOM.string('p',20),username_actual, 'UPDATE',cdate, :NEW.container_id,:NEW.cargo_manifest_id); 
END IF;
END;
    



