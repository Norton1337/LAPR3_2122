create or replace function cont_where(client VARCHAR,cont varchar)RETURN VARCHAR
IS 
tipo varchar(100);
ids varchar(100);
num varchar(100);
nume NUMBER;
conf NUMBER;
BEGIN
SELECT leasing.container_id into num
    from leasing 
    where leasing.client_id=client and leasing.container_id=cont and leasing.leasing_start_date <= current_timestamp and leasing.leasing_end_date >= current_timestamp;

select cargo_manifest.operation_type into tipo from operation inner join cargo_manifest on operation.cargo_manifest_id=cargo_manifest.cargo_manifest_id where container_id=cont and cargo_manifest.cm_date < CURRENT_TIMESTAMP order by cargo_manifest.cm_date DESC FETCH FIRST ROW ONLY;
if num=NULL THEN
    dbms_output.put_line('NO DATA');
end if;
IF (UPPER(tipo) = 'LOAD') THEN
    select cargo_manifest.vehicle_id into ids from operation inner join cargo_manifest on operation.cargo_manifest_id=cargo_manifest.cargo_manifest_id where operation.container_id=cont and cargo_manifest.cm_date < CURRENT_TIMESTAMP order by cargo_manifest.cm_date DESC FETCH FIRST ROW ONLY;

ELSE
    select cargo_manifest.local_id into ids from operation inner join cargo_manifest on operation.cargo_manifest_id=cargo_manifest.cargo_manifest_id where operation.container_id=cont and cargo_manifest.cm_date < CURRENT_TIMESTAMP order by cargo_manifest.cm_date DESC FETCH FIRST ROW ONLY;
END IF;
return ids;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    select count(container.container_id) into nume
    from container
    where container.container_id=cont;
    
if nume=1 then
    return 'container is not leased by the client / container is not in use yet';
else
    return 'container id invalid';
end if;
        
END;















--------

SET SERVEROUTPUT ON
declare
ids VARCHAR(100);
BEGIN
    ids := cont_where('4nTLI','1WSt0');
IF ids='container is not leased by the client' or ids='container id invalid' THEN
    dbms_output.put_line(ids);

ELSE
    dbms_output.put_line('ESTÃ? NO VEICULO/LOCAL COM O ID-'||ids);
END IF;
END;

