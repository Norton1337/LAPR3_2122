--Domain
    --Fail becasuse the database system assure the integrity of data types
        INSERT INTO container VALUES(2,'s','a','b',2,'s');
        INSERT INTO ship VALUES (1.22,1.22,'1ss23','dasd','sffww','dsww',123,'ads2','2ff2','2sd2','222d','djfj');
        INSERT INTO position_data VALUES (1.22,1.22,2038,'aff','aff','ff2','dd','aff0','aff',22);
        INSERT INTO port_and_warehouse VALUES (1.22,1.22,1.22,1.22,'aff','aff');
        INSERT INTO operation VALUES (12.2,1.22,1.34,12.3,'dd','ff','fff');
    --Sucess
        INSERT INTO container VALUES ('2sa1',3.0,3.0,3.0,'sad',1.2);
        INSERT INTO vechile VALUES ('aa1','Truck');
        INSERT INTO truck VALUES ('aa1','aSSSa2');
        INSERT INTO ship VALUES ('aa1','444556789','444556789','3133322',1312,31323,'wdw2',1234,1234,4144,412,13323);
        INSERT INTO position_data VALUES ('22AD','aa1','2038-01-19 03:14:07',22,33,42,,44,55,14,'fasf');
        INSERT INTO port_and_warehouse VALUES ('add','addd','a22','addd',123,213);
        INSERT INTO cargo_manifest VALUES ('Add','add','aa1');
        INSERT INTO operation VALUES ('dd','2sa1','Add','fff',12,23,14);
        
--Identity
    -- Fail because the database system assure the integrety of identity
        INSERT INTO container VALUES ('2sa1',3.2,3.0,3.0,'sad',1.2);
        INSERT INTO container VALUES ('2sa1',3.22,3.02,3.02,'sad2',1.22);
        INSERT INTO vechile VALUES ('aa1','Truck');
        INSERT INTO vechile VALUES ('aa1','Truck');
        INSERT INTO truck VALUES ('aa1','aSSSa2');
        INSERT INTO truck VALUES ('aa1','aSSSa22');
        INSERT INTO ship VALUES ('aa1','444556789','444556789','3133322',1312,31323,'wdw2',1234,1234,4144,412,13323);
        INSERT INTO ship VALUES ('aa1','444556389','444556789','3133322',13122,313232,'wdw22',12342,12342,41442,4122,133232);
        INSERT INTO position_data VALUES ('22AD','aa1','2038-01-19 03:14:07',22,33,44,42,55,14,'fasf');
        INSERT INTO position_data VALUES ('22AD','aa1','2037-01-19 03:14:07',2,32,42,42,52,42,'fasf2');
        INSERT INTO port_and_warehouse VALUES ('add','addd','a22','addd',123,213);
        INSERT INTO port_and_warehouse VALUES ('add','addd2','a222','addd2',1232,2132);
        INSERT INTO cargo_manifest VALUES ('Add','add','aa1');
        INSERT INTO cargo_manifest VALUES ('Add','add2','aa1');
        INSERT INTO operation VALUES ('dd','2sa1','Add','fff',12,23,14);
        INSERT INTO operation VALUES ('dd','2sa1','Add','fff3',124,234,145);
    --Sucess
        INSERT INTO container VALUES ('2sa1',3.0,3.0,3.0,'sad',1.2);
        INSERT INTO container VALUES ('2sa12',3.0,3.02,3.02,'sad2',1.22);
        INSERT INTO vechile VALUES ('aa1','Truck');
        INSERT INTO vechile VALUES ('aa12','Truck');
        INSERT INTO truck VALUES ('aa1','aSSSa2');
        INSERT INTO truck VALUES ('aa12','aSSSa22');
        INSERT INTO ship VALUES ('aa1','444556789','444556789','3133322',1312,31323,'wdw2',1234,1234,4144,412,13323);
        INSERT INTO ship VALUES ('aa12','444556784','444556789','3133322',13122,313232,'wdw22',12342,12342,41442,4122,133232);
        INSERT INTO position_data VALUES ('22AD','aa1','2038-01-19 03:14:07',22,33,44,42,55,14,'fasf');
        INSERT INTO position_data VALUES ('22AD2','aa12','2037-01-19 03:14:07',22,32,42,42,52,12,'fasf2');
        INSERT INTO port_and_warehouse VALUES ('add','addd','a22','addd',123,213);
        INSERT INTO port_and_warehouse VALUES ('add2','addd2','a222','addd2',1232,2132);
        INSERT INTO cargo_manifest VALUES ('Add','add','aa1');
        INSERT INTO cargo_manifest VALUES ('Add2','add2','aa12');
        INSERT INTO operation VALUES ('dd','2sa1','Add','fff',12,23,14);
        INSERT INTO operation VALUES ('dd2','2sa12','Add2','fff3',124,234,145);
--Referential
    --Fail because the database system assure the referencial integrity
        INSERT INTO truck VALUES ('aa12','aSSSa2');
        INSERT INTO ship VALUES ('aa31','444556789','444556789','3133322',1312,31323,'wdw2',1234,1234,4144,412,13323);
        INSERT INTO position_data VALUES ('22AD3','aa42','2037-01-19 03:14:07',222,332,442,552,42,142,'fasf2');
        INSERT INTO cargo_manifest VALUES ('Ad3d','ad3d','a2dsd');
        INSERT INTO operation VALUES ('dd','ff3','fff3','fff',12,23,14);

    --Sucess
        INSERT INTO vechile VALUES ('aa3','Truck');
        INSERT INTO truck VALUES ('aa3','aSSSa2');
        INSERT INTO vechile VALUES ('aa4','Ship');
        INSERT INTO ship VALUES ('aa4','444556789','444556789','3133322',1312,31323,'wdw2',1234,1234,4144,412,13323);
        INSERT INTO position_data VALUES ('22AD','aa4','2038-01-19 03:14:07',22,33,44,55,42,14,'fasf');
        INSERT INTO port_and_warehouse VALUES ('add333','addd','a22','addd',123,213);
        INSERT INTO cargo_manifest VALUES ('Add1','aa4','add333');
        INSERT INTO container VALUES ('2sa1',3.0,3.0,3.0,'sad',1.2);
        INSERT INTO operation VALUES ('dd2','2sa1','Add1','fff3',124,234,145);
        
--Application
    -- Container Coordinates bigger than or equal to 0
        --Fail
            INSERT INTO vechile VALUES ('aa4','Ship');
            INSERT INTO container VALUES ('2sa1',33,3.0,3.0,'sad',1.2);
            INSERT INTO cargo_manifest VALUES ('Add1','aa4','add333');
            INSERT INTO operation VALUES ('dd2','2sa1','Add1','fff3',-124,-234,-145);

            
        --Sucess
            INSERT INTO vechile VALUES ('aa4','Ship');
            INSERT INTO container VALUES ('2sa1',33,3.0,3.0,'sad',1.2);
            INSERT INTO cargo_manifest VALUES ('Add1','aa4','add333');
            INSERT INTO operation VALUES ('dd2','2sa1','Add1','fff3',124,234,145);
            
    -- Container tare bigger than or equal to 0
        --Fail 
            INSERT INTO container VALUES ('2sa1',33,-3.0,3.0,'sad',1.2);
            
        --Sucess
            INSERT INTO container VALUES ('2sa1',33,3.0,3.0,'sad',1.2);
            
    -- Container gross bigger than or equal to 0
        --Fail 
            INSERT INTO container VALUES ('2sa1',33,3.0,-3.0,'sad',1.2);
            
        --Sucess
            INSERT INTO container VALUES ('2sa1',33,3.0,3.0,'sad',1.2);
            
    -- Container payload bigger than or equal to 0
        --Fail 
            INSERT INTO container VALUES ('2sa1',-33,3.0,3.0,'sad',1.2);
            
        --Sucess
            INSERT INTO container VALUES ('2sa1',33,3.0,3.0,'sad',1.2);
            
    -- positon_data latitude between -90 and 91
        --Fail
            INSERT INTO ship VALUES ('aa4','444556789','444556789','3133322',1312,31323,'wdw2',1234,1234,4144,412,13323);
            INSERT INTO position_data VALUES ('22AD','aa4','2038-01-19 03:14:07',222,33,33,44,55,14,'fasf');
            
        --Sucess
            INSERT INTO ship VALUES ('aa4','444556789','444556789','3133322',1312,31323,'wdw2',1234,1234,4144,412,13323);
            INSERT INTO position_data VALUES ('22AD','aa4','2038-01-19 03:14:07',22,22,33,44,55,14,'fasf');
            
    -- positon_data longitude between -180 and 181
        --Fail
            INSERT INTO ship VALUES ('aa4','444556789','444556789','3133322',1312,31323,'wdw2',1234,1234,4144,412,13323);
            INSERT INTO position_data VALUES ('22AD','aa4','2038-01-19 03:14:07',22,333,22,44,55,14,'fasf');
            
        --Sucess
            INSERT INTO ship VALUES ('aa4','444556789','444556789','3133322',1312,31323,'wdw2',1234,1234,4144,412,13323);
            INSERT INTO position_data VALUES ('22AD','aa4','2038-01-19 03:14:07',22,22,33,44,55,14,'fasf');
            
    -- positon_data cog between 0 and 359
        --Fail
            INSERT INTO ship VALUES ('aa4','444556789','444556789','3133322',1312,31323,'wdw2',1234,1234,4144,412,13323);
            INSERT INTO position_data VALUES ('22AD','aa4','2038-01-19 03:14:07',22,333,22,444,55,14,'fasf');
            
        --Sucess
            INSERT INTO ship VALUES ('aa4','444556789','444556789','3133322',1312,31323,'wdw2',1234,1234,4144,412,13323);
            INSERT INTO position_data VALUES ('22AD','aa4','2038-01-19 03:14:07',22,22,33,44,55,14,'fasf');
            
    -- positon_data heading between 0 and 359 or 511
        --Fail
            INSERT INTO ship VALUES ('aa4','444556789','444556789','3133322',1312,31323,'wdw2',1234,1234,4144,412,13323);
            INSERT INTO position_data VALUES ('22AD','aa4','2038-01-19 03:14:07',22,333,22,44,455,14,'fasf');
            
        --Sucess
            INSERT INTO ship VALUES ('aa4','444556789','444556789','3133322',1312,31323,'wdw2',1234,1234,4144,412,13323);
            INSERT INTO position_data VALUES ('22AD','aa4','2038-01-19 03:14:07',22,22,33,44,55,14,'fasf');
            
    -- Ship MMSI 9 Digits
        --Fail
            INSERT INTO ship VALUES ('aa4','444556789','44456789','3133322',1312,31323,'wdw2',1234,1234,4144,412,13323);
            
        --Sucess
            INSERT INTO ship VALUES ('aa4','444556789','444556789','3133322',1312,31323,'wdw2',1234,1234,4144,412,13323);
            
    -- Ship IMO 7 Digits
        --Fail
            INSERT INTO ship VALUES ('aa4','444556789','444567898','333322',1312,31323,'wdw2',1234,1234,4144,412,13323);
            
        --Sucess
            INSERT INTO ship VALUES ('aa4','444556789','444556789','3133322',1312,31323,'wdw2',1234,1234,4144,412,13323);
            
    -- Ship number of generators bigger than or equal to 0
        --Fail
            INSERT INTO ship VALUES ('aa4','444556789','444567898','3333222',-1312,31323,'wdw2',1234,1234,4144,412,13323);
            
        --Sucess
            INSERT INTO ship VALUES ('aa4','444556789','444556789','3133322',1312,31323,'wdw2',1234,1234,4144,412,13323);
            
    -- Ship power output bigger than or equal to 0
        --Fail
            INSERT INTO ship VALUES ('aa4','444556789','444567898','3333222',1312,-31323,'wdw2',1234,1234,4144,412,13323);
            
        --Sucess
            INSERT INTO ship VALUES ('aa4','444556789','444556789','3133322',1312,31323,'wdw2',1234,1234,4144,412,13323);
            
    -- Ship length bigger than or equal to 0
        --Fail
            INSERT INTO ship VALUES ('aa4','444556789','444567898','3333222',1312,31323,'wdw2',1234,-1234,4144,412,13323);
            
        --Sucess
            INSERT INTO ship VALUES ('aa4','444556789','444556789','3133322',1312,31323,'wdw2',1234,1234,4144,412,13323);
            
    -- Ship width bigger than or equal to 0
        --Fail
            INSERT INTO ship VALUES ('aa4','444556789','444567898','3333222',1312,31323,'wdw2',1234,1234,-4144,412,13323);
            
        --Sucess
            INSERT INTO ship VALUES ('aa4','444556789','444556789','3133322',1312,31323,'wdw2',1234,1234,4144,412,13323);

    -- Ship capacity bigger than or equal to 0
        --Fail
            INSERT INTO ship VALUES ('aa4','444556789','444567898','3333222',1312,31323,'wdw2',1234,1234,4144,-412,13323);
            
        --Sucess
            INSERT INTO ship VALUES ('aa4','444556789','444556789','3133322',1312,31323,'wdw2',1234,1234,4144,412,13323);
    