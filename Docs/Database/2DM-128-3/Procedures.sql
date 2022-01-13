--criar procedures
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
END;
/
CREATE OR REPLACE PROCEDURE insertVehicle(
            p_vehicle_id IN vehicle.vehicle_id%type,
            p_vehicle_type IN vehicle.type%type)
IS
BEGIN
    
    INSERT INTO vehicle 
    VALUES(p_vehicle_id,p_vehicle_type);
    
    COMMIT;
END;
/

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
END;
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
    
    
    INSERT INTO vehicle 
    VALUES(p_ship_id,'Ship');
    INSERT INTO ship
    VALUES(p_ship_id,p_shipname,p_mmsi,p_imo,p_generators_number,p_generators_power,p_call_sign,p_vesel_type,p_length,p_width,p_capacity,p_draft);
    
    COMMIT;
END;
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
    VALUES(p_position_data_id,p_vehicle_ship_id,p_bdt,p_latitude,p_longitude,p_sog,p_cog,p_heading,p_position,p_transceiver_class);
    
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insertLocal(
            p_local_id IN local.local_id%type,
            p_local_port_id IN local.local_port_id%type,
            p_local_code IN local.local_code%type,
            p_local_capacity IN local.local_capacity%type,
            p_local_name IN local.local_name%type,
            p_local_type IN local.local_type%type,
            p_local_country_id IN local.local_country_id%type,
            p_local_latitude IN local.local_lalitude%type,
            p_local_longitude IN local.local_longitude%type)
IS
BEGIN
    
    INSERT INTO local
    VALUES(p_local_id,p_local_port_id,p_local_code,p_local_capacity,p_local_name,p_local_type,p_local_country_id,p_local_latitude,p_local_longitude);
    
    COMMIT;
END;
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
    VALUES(p_cargo_manifest_id,p_vehicle_id,p_local_id,p_next_local_id,p_cm_date,p_operation_type);
    
    COMMIT;
END;
/

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
END;
/
CREATE OR REPLACE PROCEDURE insertBorders(
            p_border_id IN borders.border_id%type,
            p_country_id IN borders.country_id%type,
            p_country_id2 IN borders.country_id2%type)
IS
BEGIN
    
    INSERT INTO borders
    VALUES(p_border_id,p_country_id,p_country_id2);
    
    COMMIT;
END;
/
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
END;
/
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
END;
/