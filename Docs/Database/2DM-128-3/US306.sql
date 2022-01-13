CREATE OR REPLACE FUNCTION occupancy_rate_and_containers_leaving(port_id local.local_id%type) return sys_refcursor
IS
    occupancy_rate sys_refcursor;
    latest_cargo_id cargo_manifest.cargo_manifest_id%type;
    latest_operation_warehouse_id operation.operation_warehouse_id%type;
    date_o cargo_manifest.cm_date%type;
    cdate cargo_manifest.cm_date%type;
BEGIN
    
    SELECT SYSTIMESTAMP into cdate FROM DUAL;
    date_o := ADD_MONTHS(cdate,1);
    

    OPEN occupancy_rate FOR
        
        SELECT (SELECT count(operation.container_id) as "Capacity" FROM operation 
        INNER JOIN (SELECT t1.operation_id, t1.local_id,t1.operation_warehouse_id,t1.container_id,t1.cm_date,MIN(t2.cm_date) as saida
        FROM (SELECT local_id,operation_id,operation_warehouse_id,container_id,cargo_manifest.cm_date,operation_type 
        FROM operation INNER JOIN cargo_manifest ON operation.cargo_manifest_id = cargo_manifest.cargo_manifest_id 
        WHERE operation_warehouse_id=o1. operation_warehouse_id AND operation_type='Unload' AND cm_date <= s1.cm_date) T1
        LEFT JOIN 
        (SELECT local_id,operation_warehouse_id,container_id,cargo_manifest.cm_date,operation_type
        FROM operation 
        INNER JOIN cargo_manifest ON operation.cargo_manifest_id = cargo_manifest.cargo_manifest_id 
        WHERE operation_warehouse_id=o1. operation_warehouse_id AND operation_type='Load' AND cm_date <= s1.cm_date) T2
        ON T1.container_id=T2.container_id AND T1.cm_date<T2.CM_DATE AND t1.operation_warehouse_id=t2.operation_warehouse_id
        WHERE t2.cm_date IS NULL
        GROUP BY t1.operation_id, t1.local_id,t1.container_id,t1.cm_date,t1.operation_warehouse_id) T3 
        ON operation.operation_id = t3.operation_id) c_total, operation_warehouse_id as "Warehouse", container_id, s1.cm_date as "Data_Saida" FROM operation o1
        INNER JOIN (SELECT cargo_manifest_id, cm_date FROM cargo_manifest
        WHERE operation_type = 'Load' AND cm_date >= cdate 
        AND cm_date <= date_o AND local_id = port_id) s1
        ON o1.cargo_manifest_id = s1.cargo_manifest_id;
        RETURN occupancy_rate;
END;
/

SET SERVEROUTPUT ON SIZE 1000000
DECLARE
  l_cursor   SYS_REFCURSOR;
BEGIN
  l_cursor := occupancy_rate_and_containers_leaving('itU77');
  DBMS_SQL.return_result(l_cursor);
END;