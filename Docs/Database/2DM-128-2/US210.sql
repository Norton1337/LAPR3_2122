create or replace function func_freeships return sys_refcursor
is
    ships_available sys_refcursor;
begin
    open ships_available for
        SELECT NEXT_DAY(CURRENT_TIMESTAMP,'MONDAY' ) AS NEXT_MONDAY, vehicle_ship_id as AVAILABLE_SHIP
        FROM ship WHERE vehicle_ship_id NOT IN (SELECT vehicle_id FROM cargo_manifest WHERE cm_date <= NEXT_DAY(CURRENT_TIMESTAMP,1) GROUP BY vehicle_id) 
        OR vehicle_ship_id IN (SELECT vehicle_ship_id FROM ship where 
        vehicle_ship_id in (SELECT cargo_manifest.vehicle_id FROM cargo_manifest INNER JOIN (SELECT vehicle_id, MAX(CM_DATE) AS maxdate FROM cargo_manifest WHERE cm_date <= NEXT_DAY(CURRENT_TIMESTAMP,1) GROUP BY vehicle_id) md ON cargo_manifest.vehicle_id = md.vehicle_id AND CM_DATE = maxdate
        WHERE next_local_id is null));
return ships_available;
 EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RAISE_APPLICATION_ERROR(-20003, 'No Free Ships');
end;

--Teste
SET SERVEROUTPUT ON SIZE 1000000
DECLARE
  l_cursor   SYS_REFCURSOR;
BEGIN
  l_cursor := func_freeships();
  DBMS_SQL.return_result(l_cursor);
END;