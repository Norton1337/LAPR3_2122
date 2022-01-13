CREATE OR REPLACE TRIGGER ship_capacity
    BEFORE INSERT ON operation
    FOR EACH ROW 
    DECLARE
    p_ship_id ship.vehicle_ship_id%type;
    current_capacity NUMBER;
    maximum_capacity ship.capacity%type;
    BEGIN 

    if(:new.cargo_manifest_id is null) then
        RAISE_APPLICATION_ERROR(-20000, 'Can´t be null');
    end if;

    SELECT vehicle_id INTO p_ship_id FROM cargo_manifest
    WHERE cargo_manifest_id = :NEW.cargo_manifest_id;

    SELECT capacity INTO maximum_capacity FROM ship WHERE vehicle_ship_id = p_ship_id;
    
    
    current_capacity := func_occupancy_rate(p_ship_id, :NEW.cargo_manifest_id)/100 * maximum_capacity;
    
    
    IF (current_capacity + 1) > maximum_capacity THEN
    DELETE FROM operation WHERE cargo_manifest_id = :NEW.cargo_manifest_id;
    DELETE FROM cargo_manifest WHERE cargo_manifest_id = :NEW.cargo_manifest_id;
    RAISE_APPLICATION_ERROR(-20010, 'Ship capacity exceeded || Cargo Manifest deleted Eu sou erro');
    END IF;
    END;
    
    