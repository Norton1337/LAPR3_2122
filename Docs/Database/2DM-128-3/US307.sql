CREATE OR REPLACE TRIGGER warehouse_capacity
    BEFORE INSERT ON operation
    FOR EACH ROW
    DECLARE
    
    current_capacity NUMBER;
    maximum_capacity NUMBER;
    date_o cargo_manifest.cm_date%type;
    
    BEGIN
    
    SELECT CM_DATE into date_o FROM CARGO_MANIFEST WHERE CARGO_MANIFEST_ID = :new.cargo_manifest_id;
    
    SELECT count(operation.container_id) INTO current_capacity FROM operation 
    INNER JOIN (SELECT t1.operation_id, t1.local_id,t1.operation_warehouse_id,t1.container_id,t1.cm_date,MIN(t2.cm_date) as saida
    FROM (SELECT local_id,operation_id,operation_warehouse_id,container_id,cargo_manifest.cm_date,operation_type 
    FROM operation INNER JOIN cargo_manifest ON operation.cargo_manifest_id = cargo_manifest.cargo_manifest_id 
    WHERE operation_warehouse_id=:new.operation_warehouse_id AND operation_type='Unload' AND cm_date <= date_o) T1
    LEFT JOIN 
    (SELECT local_id,operation_warehouse_id,container_id,cargo_manifest.cm_date,operation_type
    FROM operation 
    INNER JOIN cargo_manifest ON operation.cargo_manifest_id = cargo_manifest.cargo_manifest_id 
    WHERE operation_warehouse_id=:new.operation_warehouse_id AND operation_type='Load' AND cm_date <= date_o) T2
    ON T1.container_id=T2.container_id AND T1.cm_date<T2.CM_DATE AND t1.operation_warehouse_id=t2.operation_warehouse_id
    WHERE t2.cm_date IS NULL
    GROUP BY t1.operation_id, t1.local_id,t1.container_id,t1.cm_date,t1.operation_warehouse_id) T3 
    ON operation.operation_id = t3.operation_id;
    
    SELECT local_capacity INTO maximum_capacity FROM local WHERE local_id = :NEW.operation_warehouse_id;
    
    DBMS_OUTPUT.PUT_LINE('A ocupaçao é:' || current_capacity);
    
    IF (current_capacity + 1) > maximum_capacity THEN 
    DELETE FROM operation WHERE cargo_manifest_id = :NEW.cargo_manifest_id;
    DELETE FROM cargo_manifest WHERE cargo_manifest_id = :NEW.cargo_manifest_id;
    RAISE_APPLICATION_ERROR(-20010, 'Warehouse capacity exceeded.');
    END IF;
    END;