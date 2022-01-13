create or replace FUNCTION a_cm (ano NUMBER) RETURN SYS_REFCURSOR 
IS
    cur sys_refcursor;
    num NUMBER;
BEGIN

    select count(DISTINCT cargo_manifest.cargo_manifest_id) INTO NUM FROM cargo_manifest INNER JOIN operation ON OPERATION.CARGO_MANIFEST_ID=CARGO_MANIFEST.CARGO_MANIFEST_ID where EXTRACT (YEAR FROM cm_date)= ano group by EXTRACT(YEAR FROM cargo_manifest.cm_date);

    OPEN cur for
        select count(DISTINCT cargo_manifest.cargo_manifest_id)as cargo_manifests,(COUNT(operation.CONTAINER_ID)/count(distinct CARGO_MANIFEST.cargo_manifest_id)) AS MEDIA_ano FROM cargo_manifest INNER JOIN operation ON OPERATION.CARGO_MANIFEST_ID=CARGO_MANIFEST.CARGO_MANIFEST_ID where EXTRACT (YEAR FROM cm_date)= ano group by EXTRACT(YEAR FROM cargo_manifest.cm_date);
        return cur;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
      DBMS_OUTPUT.PUT_LINE('NO DATA FOUND');
    OPEN cur for
        select count(DISTINCT cargo_manifest.cargo_manifest_id)as cargo_manifests,(COUNT(operation.CONTAINER_ID)/count(distinct CARGO_MANIFEST.cargo_manifest_id)) AS MEDIA_ano FROM cargo_manifest INNER JOIN operation ON OPERATION.CARGO_MANIFEST_ID=CARGO_MANIFEST.CARGO_MANIFEST_ID where EXTRACT (YEAR FROM cm_date)= ano group by EXTRACT(YEAR FROM cargo_manifest.cm_date);
        return cur;
END;  

--Teste
SET SERVEROUTPUT ON
DECLARE
    curs SYS_REFCURSOR;
    anos NUMBER;
    media FLOAT;

BEGIN
    curs:=a_cm(2022);
    LOOP
        FETCH curs into anos,media;
        EXIT WHEN curs%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('cargo manifests:'||anos);
        DBMS_OUTPUT.PUT_LINE('media por ano:'||media);
    END LOOP;
END;

