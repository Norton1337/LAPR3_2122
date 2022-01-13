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
end;

--Teste
SET SERVEROUTPUT ON;
begin
DBMS_OUTPUT.PUT_LINE('A percentagem é:' || func_occupancy_rate(41,1002));
END;