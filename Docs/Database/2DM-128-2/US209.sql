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
end;


--Teste
SET SERVEROUTPUT ON;
begin
DBMS_OUTPUT.PUT_LINE('A percentagem é:' || func_occupancy_rate_now(41));
END;