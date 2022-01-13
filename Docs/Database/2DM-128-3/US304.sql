CREATE OR REPLACE TRIGGER audit_logs
AFTER INSERT OR DELETE OR UPDATE ON operation
FOR EACH ROW
DECLARE 
username_actual user_users.username%type;
cdate TIMESTAMP;
BEGIN
 SELECT user_users.username into username_actual FROM user_users;
 SELECT SYSTIMESTAMP into cdate FROM DUAL;
IF DELETING THEN
    INSERT INTO audit_trail
    VALUES (DBMS_RANDOM.string('p',20),username_actual, 'DELETE', :OLD.container_id,:OLD.cargo_manifest_id,cdate); 
END IF;
IF INSERTING THEN
    INSERT INTO audit_trail
    VALUES (DBMS_RANDOM.string('p',20),username_actual, 'INSERT', :NEW.container_id,:NEW.cargo_manifest_id,cdate); 
END IF;
IF UPDATING THEN
    INSERT INTO audit_trail
    VALUES (DBMS_RANDOM.string('p',20),username_actual, 'UPDATE', :NEW.container_id,:NEW.cargo_manifest_id,cdate); 
END IF;
END;