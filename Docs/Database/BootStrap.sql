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
                     imo char(7) CONSTRAINT nn_imo NOT NULL,
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
                     CONSTRAINT ck_imo CHECK (REGEXP_LIKE(imo,'[0-9]{7}')),
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
                         client_id varchar(20),
                         container_id varchar(20),
                         leasing_start_date TIMESTAMP(0) CONSTRAINT nn_leasing_start NOT NULL,
                         leasing_end_date TIMESTAMP(0) CONSTRAINT nn_leasing_end NOT NULL,
                         CONSTRAINT pk_client_id_and_container_id PRIMARY KEY (client_id,container_id),
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

-- criar procedures
DELIMITER //
CREATE OR REPLACE PROCEDURE insertContainer(
            p_container_id IN container.container_id%type,
            p_container_number IN container.container_number%type,
            p_container_checkDigit IN container.container_checkdigit%type,
            p_container_payload IN container.container_payload%type,
            p_container_tare IN container.container_tare%type,
            p_container_gross IN container.container_gross%type,
            p_container_volume IN container.container_volume%type,
            p_iso_code IN container.iso_code%type,
            p_container_certificates IN container.container_certificates%type,
            p_container_repair IN container.container_repair%type,
            p_container_type IN container.container_type%type,
            p_container_load IN container.container_load%type)
IS
BEGIN

    INSERT INTO container
    VALUES(p_container_id,p_container_number,p_container_checkDigit,
            p_container_payload,p_container_tare,p_container_gross,p_container_volume,
            p_iso_code,p_container_certificates,p_container_repair,
            p_container_type,p_container_load);

    COMMIT;
END;//
DELIMITER ;

DELIMITER //
CREATE OR REPLACE PROCEDURE insertVehicle(
            p_vehicle_id IN vehicle.vehicle_id%type,
            p_vehicle_type IN vehicle.type%type)
IS
BEGIN
    
    INSERT INTO vehicle 
    VALUES(p_vehicle_id,p_vehicle_type);
    
    COMMIT;
END;//
DELIMITER ;

DELIMITER //
CREATE OR REPLACE PROCEDURE insertTruck(
            p_truck_id IN truck.vehicle_truck_id%type,
            p_plate IN truck.plate%type)
IS
BEGIN
    
    INSERT INTO vehicle 
    VALUES(p_truck_id,'Truck');
    INSERT INTO truck 
    VALUES(p_truck_id,p_plate);
    
    COMMIT;
END;//
DELIMITER ;

DELIMITER //
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
    
    
    INSERT INTO vehicle 
    VALUES(p_ship_id,'Ship');
    INSERT INTO ship
    VALUES(p_ship_id,p_shipname,p_mmsi,p_imo,p_generators_number,p_generators_power,p_call_sign,p_vesel_type,p_length,p_width,p_capacity,p_draft);
    
    COMMIT;
END;//
DELIMITER ;

DELIMITER //
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
    VALUES(p_position_data_id,p_vehicle_ship_id,p_bdt,p_latitude,p_longitude,p_sog,p_cog,p_heading,p_position,p_transceiver_class);
    
    COMMIT;
END;//
DELIMITER ;

DELIMITER //
CREATE OR REPLACE PROCEDURE insertLocal(
            p_local_id IN local.local_id%type,
            p_local_port_id IN local.local_port_id%type,
            p_local_code IN local.local_code%type,
            p_local_capacity IN local.local_capacity%type,
            p_local_name IN local.local_name%type,
            p_local_type IN local.local_type%type,
            p_local_country_id IN local.local_country_id%type,
            p_local_latitude IN local.local_lalitude%type,
            p_local_altitude IN local.LOCAL_LONGITUDE%type)
IS
BEGIN
    
    INSERT INTO local
    VALUES(p_local_id,p_local_port_id,p_local_code,p_local_capacity,p_local_name,p_local_type,p_local_country_id,p_local_latitude,p_local_altitude);
    
    COMMIT;
END;//
DELIMITER ;

DELIMITER //
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
    VALUES(p_cargo_manifest_id,p_vehicle_id,p_local_id,p_next_local_id,p_cm_date,p_operation_type);
    
    COMMIT;
END;//
DELIMITER;

DELIMITER //
CREATE OR REPLACE PROCEDURE insertOperation(
            p_operation_id IN operation.operation_id%type,
            p_container_id IN operation.container_id%type,
            p_cargo_manifest_id IN operation.cargo_manifest_id%type,
            p_operation_warehouse_id IN operation.operation_warehouse_id%type,
            p_coordinate_x IN operation.coordinate_x%type,
            p_coordinate_y IN operation.coordinate_y%type,
            p_coordinate_z IN operation.coordinate_z%type)
IS
BEGIN
    
    INSERT INTO operation
    VALUES(p_operation_id,p_container_id,p_cargo_manifest_id,p_operation_warehouse_id,p_coordinate_x,p_coordinate_y,p_coordinate_z);
    
    COMMIT;
END;//
DELIMITER;

DELIMITER //
CREATE OR REPLACE PROCEDURE insertBorders(
            p_border_id IN borders.border_id%type,
            p_country_id IN borders.country_id%type,
            p_country_id2 IN borders.country_id2%type)
IS
BEGIN
    
    INSERT INTO borders
    VALUES(p_border_id,p_country_id,p_country_id2);
    
    COMMIT;
END;//
DELIMITER;

DELIMITER //
CREATE OR REPLACE PROCEDURE insertCountry(
            p_country_id IN country.country_id%type,
            p_continent IN country.continent%type,
            p_alpha_2_code IN country.alpha_2_code%type,
            p_alpha_3_code IN country.alpha_3_code%type,
            p_country_name IN country.country_name%type,
            p_population IN country.population%type,
            p_country_capital IN country.country_capital%type,
            p_latitude IN country.latitude%type,
            p_longitude IN country.longitude%type)
IS
BEGIN
    
    INSERT INTO country
    VALUES(p_country_id,p_continent,p_alpha_2_code,p_alpha_3_code,p_country_name,p_population,p_country_capital,p_latitude,p_longitude);
    
    COMMIT;
END;//
DELIMITER;

DELIMITER //
CREATE OR REPLACE PROCEDURE insertSeadist(
            p_seadist_id IN seadists.seadist_id%type,
            p_fromPort IN seadists.fromport%type,
            p_toPort IN seadists.toport%type,
            p_distance IN seadists.distance%type)
IS
BEGIN
    
    INSERT INTO seadists
    VALUES(p_seadist_id,p_fromPort,p_toPort,p_distance);
    
    COMMIT;
END;//
DELIMITER;

--criar funções
DELIMITER //
create or replace function func_freeships return sys_refcursor
is
    ships_available sys_refcursor;
begin
    open ships_available for
        SELECT NEXT_DAY(CURRENT_TIMESTAMP,'MONDAY') AS NEXT_MONDAY, vehicle_ship_id as AVAILABLE_SHIP
        FROM ship WHERE vehicle_ship_id NOT IN (SELECT vehicle_id FROM cargo_manifest WHERE cm_date <= NEXT_DAY(CURRENT_TIMESTAMP,'MONDAY') GROUP BY vehicle_id) 
        OR vehicle_ship_id IN (SELECT vehicle_ship_id FROM ship where 
        vehicle_ship_id in (SELECT cargo_manifest.vehicle_id FROM cargo_manifest INNER JOIN (SELECT vehicle_id, MAX(CM_DATE) AS maxdate FROM cargo_manifest WHERE cm_date <= NEXT_DAY(CURRENT_TIMESTAMP,'MONDAY') GROUP BY vehicle_id) md ON cargo_manifest.vehicle_id = md.vehicle_id AND CM_DATE = maxdate
        WHERE next_local_id is null));
return ships_available;
 EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RAISE_APPLICATION_ERROR(-20003, 'No Free Ships');
end;//
DELIMITER;

DELIMITER //
CREATE OR REPLACE function containers_to_load(p_ship_id ship.vehicle_ship_id%type) return sys_refcursor
    IS
    load_containers sys_refcursor;
BEGIN
    OPEN load_containers FOR

        SELECT operation.container_id, container.container_type,container.container_load
        FROM operation INNER JOIN container ON operation.container_id = container.container_id
        WHERE cargo_manifest_id = (SELECT cargo_manifest_id FROM cargo_manifest WHERE UPPER(operation_type) = 'LOAD' AND cm_date > (SELECT cm_date FROM cargo_manifest
                                                                                                                                    WHERE cm_date <= CURRENT_TIMESTAMP AND vehicle_id = p_ship_id ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY)
                                                                                  AND local_id = (SELECT next_local_id FROM cargo_manifest WHERE cm_date <= CURRENT_TIMESTAMP AND vehicle_id = p_ship_id ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY)
                                                                                  AND vehicle_id = p_ship_id ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY);
    return (load_containers);
END;//
DELIMITER;

DELIMITER //
CREATE OR REPLACE function containers_to_offload(p_ship_id ship.vehicle_ship_id%type) return sys_refcursor
    IS
    offload_containers sys_refcursor;
BEGIN
    OPEN offload_containers FOR

        SELECT operation.container_id,container.container_type, COORDINATE_X,COORDINATE_Y,COORDINATE_Z,container.container_load
        FROM operation INNER JOIN container ON operation.container_id = container.container_id
        WHERE cargo_manifest_id = (SELECT cargo_manifest_id FROM cargo_manifest WHERE UPPER(operation_type) = 'UNLOAD' AND cm_date > (SELECT cm_date FROM cargo_manifest
                                                                                                                                      WHERE cm_date <= CURRENT_TIMESTAMP AND vehicle_id = p_ship_id ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY)
                                                                                  AND local_id = (SELECT next_local_id FROM cargo_manifest WHERE cm_date <= CURRENT_TIMESTAMP AND vehicle_id = p_ship_id ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY)
                                                                                  AND vehicle_id = p_ship_id ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY);
    return (offload_containers);
END;//
DELIMITER;

DELIMITER //
create or replace function func_occupancy_rate_now(p_ship_id ship.vehicle_ship_id%type) return float
is
    cm cargo_manifest.cargo_manifest_id%type;
begin
    SELECT cargo_manifest_id into cm
    FROM cargo_manifest 
    WHERE cm_date <= CURRENT_TIMESTAMP AND vehicle_id = p_ship_id
    ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY;
    return func_occupancy_rate(p_ship_id,cm);
 EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RAISE_APPLICATION_ERROR(-20001, 'Ship Does not exist');
end;//
DELIMITER;

DELIMITER //
create or replace function func_occupancy_rate(p_ship_id ship.vehicle_ship_id%type,p_cargo_id cargo_manifest.cargo_manifest_id%type) return float
is
    containers_cargo int;
    ship_capacity float;
begin
    select count(container_id) into containers_cargo
        from operation
        where cargo_manifest_id = (SELECT cargo_manifest_id FROM cargo_manifest WHERE cargo_manifest_id = p_cargo_id and UPPER(operation_type) = 'LOAD');
    select capacity into ship_capacity
        from ship
        where vehicle_ship_id = p_ship_id;
    return containers_cargo / ship_capacity * 100;
 EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RAISE_APPLICATION_ERROR(-20002, 'VALID DATA INSERTED');
end;//
DELIMITER;

DELIMITER //
CREATE OR REPLACE FUNCTION container_route(c_id container.container_id%type,cl_id client.client_id%type) RETURN sys_refcursor 
IS
di leasing.leasing_start_date%type;
df leasing.leasing_end_date%type;
c_route sys_refcursor;
BEGIN

  select leasing_start_date
    into di
    from leasing
   where client_id = cl_id AND container_id = c_id;

   select leasing_end_date
     into df
     from leasing
    where client_id = cl_id AND container_id = c_id;

    
    open c_route for
      SELECT MIN(l1.LOCAL_NAME) AS "Origem",
        MIN(CARGO_MANIFEST.CM_DATE) AS "Departure Time",
        MIN(VEHICLE.TYPE) AS "Metodo De Transporte",
        MIN(l2.LOCAL_NAME) AS "Destino",MIN(s."Arrival_Time") AS "Arrival Time"
      FROM CARGO_MANIFEST
      INNER JOIN VEHICLE ON CARGO_MANIFEST.VEHICLE_ID = VEHICLE.VEHICLE_ID
      INNER JOIN LOCAL l1 ON CARGO_MANIFEST.LOCAL_ID = l1.LOCAL_ID
      INNER JOIN (SELECT local_id as "Destino",CARGO_MANIFEST.CM_DATE AS "Arrival_Time",VEHICLE_ID
      FROM CARGO_MANIFEST) s on cargo_manifest.next_local_id = s."Destino" 
      And cargo_manifest.VEHICLE_ID = s.VEHICLE_ID And cargo_manifest.cm_date < s."Arrival_Time" 
      INNER JOIN LOCAL l2 ON s."Destino" = l2.local_id
      WHERE CARGO_MANIFEST_ID IN (SELECT CARGO_MANIFEST_id FROM OPERATION WHERE CONTAINER_ID = c_id)
      AND CM_DATE >= di AND CM_DATE <= df
      GROUP BY cargo_manifest.cargo_manifest_id;


  RETURN c_route;

 EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RAISE_APPLICATION_ERROR(-20201, 'Leasing does not exist');

END;//
DELIMITER;

DELIMITER //
CREATE OR REPLACE FUNCTION a_cm (ano NUMBER) RETURN SYS_REFCURSOR
    IS
    cur sys_refcursor;
BEGIN
    OPEN cur FOR
        select count(DISTINCT cargo_manifest.cargo_manifest_id)
            as CARGO_MANIFESTS,(COUNT(operation.CONTAINER_ID)/count(distinct CARGO_MANIFEST.cargo_manifest_id))
            FROM cargo_manifest INNER JOIN operation ON OPERATION.CARGO_MANIFEST_ID=CARGO_MANIFEST.CARGO_MANIFEST_ID
            where EXTRACT (YEAR FROM cm_date)= ano group by EXTRACT(YEAR FROM cargo_manifest.cm_date);
    return cur;
end;//
DELIMITER;

DELIMITER //
create or replace function warehouse_cont(port NUMBER)return SYS_REFCURSOR
    IS
    cur SYS_REFCURSOR;
BEGIN
    OPEN cur for

        select t1.local_id,t1.operation_warehouse_id,t1.container_id,t1.cm_date,MIN(t2.cm_date) as saida
        from (select local_id,operation_warehouse_id,container_id,cargo_manifest.cm_date,operation_type
              from operation inner join cargo_manifest on operation.cargo_manifest_id = cargo_manifest.cargo_manifest_id
              where local_id=port and operation_type='Unload') T1
                 left join
             (select local_id,operation_warehouse_id,container_id,cargo_manifest.cm_date,operation_type
              from operation
                       inner join cargo_manifest on operation.cargo_manifest_id = cargo_manifest.cargo_manifest_id
              where local_id=port and operation_type='Load') T2
             on T1.container_id=T2.container_id
        where (T1.cm_date<T2.CM_DATE and t1.operation_warehouse_id=t2.operation_warehouse_id) or (t2.cm_date is null)
        group by t1.local_id,t1.container_id,t1.cm_date,t1.operation_warehouse_id;
    RETURN CUR;

END;//
DELIMITER;

DELIMITER //
create or replace function cont_where(cont NUMBER)RETURN VARCHAR
IS 
tipo varchar(100);
ids varchar(100);
BEGIN
select cargo_manifest.operation_type into tipo from operation inner join cargo_manifest on operation.cargo_manifest_id=cargo_manifest.cargo_manifest_id where container_id=cont and cargo_manifest.cm_date < CURRENT_TIMESTAMP order by cargo_manifest.cm_date DESC FETCH FIRST ROW ONLY;


IF (UPPER(tipo) = 'LOAD') THEN
    select cargo_manifest.vehicle_id into ids from operation inner join cargo_manifest on operation.cargo_manifest_id=cargo_manifest.cargo_manifest_id where operation.container_id=cont and cargo_manifest.cm_date < CURRENT_TIMESTAMP order by cargo_manifest.cm_date DESC FETCH FIRST ROW ONLY;

ELSE
    select cargo_manifest.local_id into ids from operation inner join cargo_manifest on operation.cargo_manifest_id=cargo_manifest.cargo_manifest_id where operation.container_id=cont and cargo_manifest.cm_date < CURRENT_TIMESTAMP order by cargo_manifest.cm_date DESC FETCH FIRST ROW ONLY;
END IF;
return ids;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
  return 'NO DATA';

END;//
