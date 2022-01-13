--testing ship capacity
INSERT INTO operation VALUES ('gv8_K','I_htQ','NtrYuSUEHwqZ2wd9Wl-g','Yf9F8',2,1,0);
INSERT INTO operation VALUES ('JnuZ_','JJtSV','NtrYuSUEHwqZ2wd9Wl-g','Yf9F8',1,1,0);
INSERT INTO operation VALUES ('tACYY','eHt0V','NtrYuSUEHwqZ2wd9Wl-g','Yf9F8',4,1,0);
INSERT INTO operation VALUES ('80Ynn','4JAVW','NtrYuSUEHwqZ2wd9Wl-g','Yf9F8',5,1,0);
INSERT INTO operation VALUES ('6CfBI','04D9w','NtrYuSUEHwqZ2wd9Wl-g','Yf9F8',2,2,0);
INSERT INTO operation VALUES ('wLaUt','zYc1D','NtrYuSUEHwqZ2wd9Wl-g','Yf9F8',2,3,0);
INSERT INTO operation VALUES ('3NN16','qUpuM','NtrYuSUEHwqZ2wd9Wl-g','Yf9F8',2,2,1);
INSERT INTO operation VALUES ('Q9_Br','PWQdy','NtrYuSUEHwqZ2wd9Wl-g','Yf9F8',2,2,2);
INSERT INTO operation VALUES ('t0LdY','Qg3Uk','NtrYuSUEHwqZ2wd9Wl-g','Yf9F8',2,2,3);
INSERT INTO operation VALUES ('Wd_HU','Hj5F2','NtrYuSUEHwqZ2wd9Wl-g','Yf9F8',2,3,1);
INSERT INTO operation VALUES ('YbZOy','JOalw','NtrYuSUEHwqZ2wd9Wl-g','Yf9F8',2,3,1);

--testing warehouse capacityu
INSERT INTO operation VALUES ('Fa-0N','I_htQ','BaXnse5UhhzjjQhF6aT2','qWYhr',0,0,0);
INSERT INTO operation VALUES ('clwTs','JJtSV','BaXnse5UhhzjjQhF6aT2','qWYhr',0,0,0);
INSERT INTO operation VALUES ('rXtwe','eHt0V','BaXnse5UhhzjjQhF6aT2','qWYhr',0,0,0);
INSERT INTO operation VALUES ('1htof','4JAVW','BaXnse5UhhzjjQhF6aT2','qWYhr',0,0,0);
INSERT INTO operation VALUES ('BWHGd','04D9w','BaXnse5UhhzjjQhF6aT2','qWYhr',0,0,0);
INSERT INTO operation VALUES ('cgjvy','zYc1D','BaXnse5UhhzjjQhF6aT2','qWYhr',0,0,0);
INSERT INTO operation VALUES ('bexOc','qUpuM','BaXnse5UhhzjjQhF6aT2','qWYhr',0,0,0);
INSERT INTO operation VALUES ('C0Gpr','PWQdy','BaXnse5UhhzjjQhF6aT2','qWYhr',0,0,0);
INSERT INTO operation VALUES ('hVcGs','Qg3Uk','BaXnse5UhhzjjQhF6aT2','qWYhr',0,0,0);
INSERT INTO operation VALUES ('-0P1O','Hj5F2','BaXnse5UhhzjjQhF6aT2','qWYhr',0,0,0);
INSERT INTO operation VALUES ('hMYH1','JOalw','BaXnse5UhhzjjQhF6aT2','qWYhr',0,0,0);

--testing freeShips
INSERT INTO cargo_manifest VALUES('voy7Zhvze_E9MabkULKB','0B0-X_rZctTr1-LtowOZ','JrTxe','itU77',(timestamp '2022-01-8 22:12:43'),'Load');

--testing audit trails
CREATE OR REPLACE FUNCTION testing_audit_trail(
                    p_container_id container.container_id%type, 
                    p_cargo_manifest_id cargo_manifest.cargo_manifest_id%type)
                    RETURN SYS_REFCURSOR
IS
cur SYS_REFCURSOR;
BEGIN
    OPEN cur FOR
    SELECT *  FROM audit_trail 
    WHERE p_container_id =  audit_container_id
    AND  p_cargo_manifest_id = audit_cargo_manifest_id;
    return cur;
    
END;

SET SERVEROUTPUT ON SIZE 1000000
DECLARE
  l_cursor   SYS_REFCURSOR;
BEGIN
  l_cursor := testing_audit_trail('1LFRX', 'xqLF9p8hGekONv8iJWV_');
  DBMS_SQL.return_result(l_cursor);
END;
