CREATE OR REPLACE FUNCTION container_route(l_id leasing.leasing_id %type,c_id container.container_id%type,cl_id client.client_id%type) RETURN sys_refcursor 
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

END;
/

SET SERVEROUTPUT ON SIZE 1000000
DECLARE
  l_cursor   SYS_REFCURSOR;
BEGIN
  l_cursor := container_route('LKQU0pMAVuUkORHDSHO_','dz-eA','4nTLI');
  DBMS_SQL.return_result(l_cursor);
END;