
create or replace function warehouse_cont(port varchar,mes date)return SYS_REFCURSOR
IS
cur SYS_REFCURSOR;
entr date:= TRUNC(mes, 'MM');
fim DATE :=LAST_DAY(mes);
BEGIN

    OPEN cur for
    
        select t1.local_id,t1.operation_warehouse_id,t1.container_id,t1.cm_date,MIN(t2.cm_date) as saida
        from (select local_id,operation_warehouse_id,container_id,cargo_manifest.cm_date,operation_type 
            from operation inner join cargo_manifest on operation.cargo_manifest_id = cargo_manifest.cargo_manifest_id 
            where local_id=port and operation_type='Unload') T1
        left join 
            (select local_id,operation_warehouse_id,container_id,cargo_manifest.cm_date,operation_type 
            from operation 
            inner join cargo_manifest on operation.cargo_manifest_id = cargo_manifest.cargo_manifest_id 
            where local_id=port and operation_type='Load') T2
        on T1.container_id=T2.container_id 
        where (T1.cm_date<T2.CM_DATE and t1.operation_warehouse_id=t2.operation_warehouse_id 
        and extract(month from t1.cm_date)=extract(month from mes) 
        and extract(year from t1.cm_date)=extract(year from mes)) 
        or (t2.cm_date is null and t1.cm_date<fim) 
        or (T1.cm_date<T2.CM_DATE 
        and t1.operation_warehouse_id=t2.operation_warehouse_id and extract(month from t2.cm_date)=extract(month from mes) 
        and extract(year from t2.cm_date)=extract(year from mes)) 
        or (T1.cm_date<T2.CM_DATE 
        and t1.operation_warehouse_id=t2.operation_warehouse_id and t1.cm_date<entr and t2.cm_date>fim)
        group by t1.local_id,t1.container_id,t1.cm_date,t1.operation_warehouse_id;
        RETURN CUR;

END;

SET SERVEROUTPUT ON
DECLARE
    curs SYS_REFCURSOR;
    p_local varchar(100);
    p_operation varchar(100);
    p_cont varchar(100);
    p_entr DATE;
    p_said DATE;

BEGIN
    curs:=warehouse_cont('YBglm','2022-01-15');
    LOOP
        FETCH curs into p_local,p_operation,p_cont,p_entr,p_said;
        EXIT WHEN curs%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('local:' || p_local);
        DBMS_OUTPUT.PUT_LINE('operation:' || p_operation);
        DBMS_OUTPUT.PUT_LINE('container:' || p_cont);
        DBMS_OUTPUT.PUT_LINE('entrada:' || p_entr); 
        DBMS_OUTPUT.PUT_LINE('saida:'||p_said);
        DBMS_OUTPUT.PUT_LINE('---------------------------------------------');
    END LOOP;
END;