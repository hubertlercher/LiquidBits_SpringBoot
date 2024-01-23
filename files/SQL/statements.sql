USE liquidbits;

INSERT INTO DRINKTYPE(NAME, ALCVALUE, INTENSITY)
VALUES ('Bier', 0, 0);

INSERT INTO DRINKTYPE(NAME, ALCVALUE, INTENSITY)
VALUES ('Wein', 0, 0);

INSERT INTO DRINKTYPE(NAME, ALCVALUE, INTENSITY)
VALUES ('Juice', 0, 0);

INSERT INTO DEVICE(LOCATION, MANUFACTURER, MODELL)
VALUES ('HTL Mössingerstrasse', 'liquidBits', 'iSchank');

INSERT INTO USER(NAME, mail)
VALUES ('Hubi', 'hubert.lercher@edu.htl-klu.at');

INSERT INTO USER(NAME, mail)
VALUES ('Dopler', 'stephan.dopler@edu.htl-klu.at');

INSERT INTO USER(NAME, mail)
VALUES ('Falge', 'daniel.falgenhauer@edu.htl-klu.at');

INSERT INTO CONTAINER(tapped, size_ml, drinktype_id)
VALUES('2023-11-29', 10000, 1);

INSERT INTO CONTAINER(tapped, size_ml, drinktype_id)
VALUES('2023-11-29', 5000, 2);

INSERT INTO CONTAINER(tapped, size_ml, drinktype_id)
VALUES('2023-11-29', 10000, 3);

INSERT INTO DRINK(amount, drinktype_id, container_id, device_id, user_id)
VALUES(300,1,1,1,1);

INSERT INTO DRINK(amount, drinktype_id, container_id, device_id, user_id)
VALUES (250, 2,2,1,2);

INSERT INTO DRINK(amount, drinktype_id, container_id, device_id, user_id)
VALUES (250, 2,2,1,2);


UPDATE USER
SET IMAGE = '/resources/images/Dope.jpeg'
WHERE USER_ID = 1;

UPDATE CONTAINER
SET UNTAPPED = NOW()
WHERE CONTAINER_ID = 1;

UPDATE CONTAINER
SET UNTAPPED = NOW()
WHERE CONTAINER_ID = 2;


INSERT INTO CONTAINER(TAPPED, SIZE_ML, DRINKTYPE_ID, STATUS)
VALUES(NOW(), 50000, 1, 'OK');

INSERT INTO CONTAINER(TAPPED, SIZE_ML, DRINKTYPE_ID, STATUS)
VALUES(NOW(), 25000, 2, 'OK');

INSERT INTO DRINK(amount, drinktype_id, container_id, device_id, user_id, TIMESTAMP)
VALUES (125, 2,4,1,2, NOW());

INSERT INTO DRINK(amount, drinktype_id, container_id, device_id, user_id, TIMESTAMP)
VALUES (125, 2,4,1,2, NOW());

INSERT INTO DRINK(amount, drinktype_id, container_id, device_id, user_id, TIMESTAMP)
VALUES (500, 1,5,1,2, NOW());

INSERT INTO DRINK(amount, drinktype_id, container_id, device_id, user_id, TIMESTAMP)
VALUES (500, 1,5,1,2, NOW());


alter table DRINKTYPE
    add LAST_CLEANING TIMESTAMP null;

INSERT INTO DRINK(amount, drinktype_id, container_id, device_id, user_id, TIMESTAMP)
VALUES (45000, 1,5,1,2, NOW());



INSERT INTO DRINK(amount, drinktype_id, container_id, device_id, user_id, TIMESTAMP)
VALUES (40000, 2,4,1,2, NOW());

INSERT INTO DRINK(amount, drinktype_id, container_id, device_id, user_id, TIMESTAMP)
VALUES (20000, 2,4,1,2, NOW());


-- Wartungsbuch SQL-Statements

/* create table MAINTENANCE_LOG;
 alter table MAINTENANCE_LOG
   add LOG_ID int;

 alter table MAINTENANCE_LOG
     add constraint MAINTENANCE_LOG_pk
         primary key (LOG_ID);

 alter table MAINTENANCE_LOG
     modify LOG_ID int auto_increment;

 alter table MAINTENANCE_LOG
 add DESCRIPTION VARCHAR(200) not null;

 alter table MAINTENANCE_LOG
 add DRINKTYPE_ID int not null;

 alter table MAINTENANCE_LOG
    add constraint MAINTENANCE_LOG__DRINKTYPE_ID_fk
        foreign key (DRINKTYPE_ID) references DRINKTYPE(DRINKTYPE_ID);

    alter table MAINTENANCE_LOG
    add DEVICE_ID int not null;


 alter table MAINTENANCE_LOG
    add constraint MAINTENANCE_LOG_DEVICE_DEVICE_ID_fk
        foreign key (DEVICE_ID) references DEVICE (DEVICE_ID);

 alter table MAINTENANCE_LOG
    add TIMESTAMP TIMESTAMP default NOW() not null;
 */









