create or replace function cont_where(cont varchar)RETURN VARCHAR
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

END;

--Teste
SET SERVEROUTPUT ON;
BEGIN
dbms_output.put_line('ESTÁ EM ID:'||cont_where(5));
END;


