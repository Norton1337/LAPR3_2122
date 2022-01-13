create or replace function containers_to_offload(p_ship_id ship.vehicle_ship_id%type) return sys_refcursor
is
    offload_containers sys_refcursor;
begin
    OPEN offload_containers FOR

    SELECT operation.container_id,container.container_type, COORDINATE_X,COORDINATE_Y,COORDINATE_Z,container.container_load 
    FROM operation INNER JOIN container ON operation.container_id = container.container_id
    WHERE cargo_manifest_id = (SELECT cargo_manifest_id FROM cargo_manifest WHERE UPPER(operation_type) = 'UNLOAD' AND cm_date > (SELECT cm_date FROM cargo_manifest 
    WHERE cm_date <= CURRENT_TIMESTAMP AND vehicle_id = p_ship_id ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY) 
    AND local_id = (SELECT next_local_id FROM cargo_manifest WHERE cm_date <= CURRENT_TIMESTAMP AND vehicle_id = p_ship_id ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY)
    AND vehicle_id = p_ship_id ORDER BY cm_date DESC FETCH FIRST 1 ROWS ONLY);
    return (offload_containers);
    EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RAISE_APPLICATION_ERROR(-20003, 'NO CARGO MANIFESTS AVAILABLE FOR THIS DATE');
end;

--Teste
--Se veiculo 41 não funcionar, experimentar até ao 44.
SET SERVEROUTPUT ON SIZE 1000000
DECLARE
  l_cursor   SYS_REFCURSOR;
BEGIN
  l_cursor := containers_to_offload(41);
  DBMS_SQL.return_result(l_cursor);
END;