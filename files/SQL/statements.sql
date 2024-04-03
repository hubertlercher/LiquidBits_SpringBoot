USE liquidbits;

INSERT INTO DRINKTYPE(NAME, ALCVALUE, INTENSITY)
VALUES ('Bier', 0, 0);

INSERT INTO DRINKTYPE(NAME, ALCVALUE, INTENSITY)
VALUES ('Wein', 0, 0);

INSERT INTO DRINKTYPE(NAME, ALCVALUE, INTENSITY)
VALUES ('Juice', 0, 0);

INSERT INTO DEVICE(LOCATION, MANUFACTURER, MODELL)
VALUES ('HTL Mössingerstrasse', 'liquidBits', 'iSchank');

INSERT INTO USER(SURNAME, mail)
VALUES ('Hubi', 'hubert.lercher@edu.htl-klu.at');

INSERT INTO USER(SURNAME, mail)
VALUES ('Dopler', 'stephan.dopler@edu.htl-klu.at');

INSERT INTO USER(SURNAME, mail)
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



INSERT INTO MAINTENANCE_LOG(description, drinktype_id, device_id, timestamp)
VALUES ('Intensiv-Reinigung', 1, 1, NOW());

INSERT INTO MAINTENANCE_LOG(description, drinktype_id, device_id, timestamp)
VALUES ('Intensiv-Reinigung', 2, 1, NOW());

INSERT INTO MAINTENANCE_LOG(description, drinktype_id, device_id, timestamp)
VALUES ('Desinfektion', 3, 1, NOW());



-- für Falge
/*
create table CONTACT
(
    CONTACT_ID int auto_increment,
    NAME       VARCHAR(45) not null,
    MAIL       varchar(70) not null,
    TELEPHONE  varchar(20) null,
    constraint CONTACT_pk
        primary key (CONTACT_ID)
);

alter table MAINTENANCE_LOG
    add CONTACT_ID int null;


alter table MAINTENANCE_LOG
    add constraint MAINTENANCE_LOG_CONTACT_CONTACT_ID_fk
        foreign key (CONTACT_ID) references CONTACT (CONTACT_ID);


INSERT INTO CONTACT(NAME, MAIL, TELEPHONE)
VALUES ('Unteregger', 'stephan.dopler@edu.htl-klu.at', '06761234567');

INSERT INTO CONTACT(NAME, MAIL, TELEPHONE)
VALUES ('Brau-Union', 'hubert.lercher@edu.htl-klu.at', '06761234567');

INSERT INTO CONTACT(NAME, MAIL, TELEPHONE)
VALUES ('Zapfdoktor', 'daniel.falgenhauer@edu.htl-klu.at', '06761234567');

INSERT INTO USER(SURNAME, FIRSTNAME, MAIL)
VALUES ('Mustermann', 'Maximilian', 'maximilian.mustermann@edu.htl-klu.at');

INSERT INTO USER(SURNAME, FIRSTNAME, mail)
VALUES ('Musterfrau', 'Tanja', 'tanja.musterfrau@edu.htl-klu.at');

INSERT INTO USER(SURNAME, FIRSTNAME, mail)
VALUES ('Müller', 'Thomas', 'thomas.mueller@edu.htl-klu.at');

INSERT INTO USER(SURNAME, FIRSTNAME, mail)
VALUES ('Kargl', 'Henry', 'henry.kargl@edu.htl-klu.at');




NEUE LOGS

INSERT INTO MAINTENANCE_LOG(description, drinktype_id, device_id, timestamp)
VALUES ('Intensiv-Reinigung', 1, 1, NOW());

INSERT INTO MAINTENANCE_LOG(description, drinktype_id, device_id, timestamp)
VALUES ('Intensiv-Reinigung', 2, 1, NOW());

INSERT INTO MAINTENANCE_LOG(description, drinktype_id, device_id, timestamp)
VALUES ('Intensiv-Reinigung', 3, 1, NOW());

INSERT INTO MAINTENANCE_LOG(description, drinktype_id, device_id, timestamp)
VALUES ('Desinfektion', 1, 1, NOW());

INSERT INTO MAINTENANCE_LOG(description, drinktype_id, device_id, timestamp)
VALUES ('Desinfektion', 2, 1, NOW());

INSERT INTO MAINTENANCE_LOG(description, drinktype_id, device_id, timestamp)
VALUES ('Desinfektion', 3, 1, NOW());

INSERT INTO MAINTENANCE_LOG(description, drinktype_id, device_id, timestamp)
VALUES ('Allgemeine Service-Wartung', 1, 1, NOW());

INSERT INTO MAINTENANCE_LOG(description, drinktype_id, device_id, timestamp)
VALUES ('Allgemeine Service-Wartung', 2, 1, NOW());

INSERT INTO MAINTENANCE_LOG(description, drinktype_id, device_id, timestamp)
VALUES ('Allgemeine Service-Wartung', 3, 1, NOW());


NEUE LIMO INSERTS

INSERT INTO DRINK(amount, drinktype_id, container_id, device_id, user_id, TIMESTAMP)
VALUES (125, 3,3,1,2, '2024-02-01 18:15:00');

INSERT INTO DRINK(amount, drinktype_id, container_id, device_id, user_id, TIMESTAMP)
VALUES (250, 3,3,1,3, '2024-02-02 18:15:00');

INSERT INTO DRINK(amount, drinktype_id, container_id, device_id, user_id, TIMESTAMP)
VALUES(300,3,3,1,3, '2024-01-19 18:15:00');

INSERT INTO DRINK(amount, drinktype_id, container_id, device_id, user_id, TIMESTAMP)
VALUES(300,3,3,1,5, '2024-01-14 18:15:00');

INSERT INTO DRINK(amount, drinktype_id, container_id, device_id, user_id, TIMESTAMP)
VALUES(500,3,3,1,5, '2024-01-16 18:15:00');

INSERT INTO DRINK(amount, drinktype_id, container_id, device_id, user_id, TIMESTAMP)
VALUES (125, 3,3,1,6, '2024-01-30 19:15:00');

INSERT INTO DRINK(amount, drinktype_id, container_id, device_id, user_id, TIMESTAMP)
VALUES (250, 3,3,1,7, '2024-01-28 19:15:00');

INSERT INTO DRINK(amount, drinktype_id, container_id, device_id, user_id, TIMESTAMP)
VALUES(300,3,3,1,9, '2023-01-27 19:15:00');

INSERT INTO DRINK(amount, drinktype_id, container_id, device_id, user_id, TIMESTAMP)
VALUES(300,3,3,1,8, '2024-01-11 19:15:00');

INSERT INTO DRINK(amount, drinktype_id, container_id, device_id, user_id, TIMESTAMP)
VALUES(500,3,3,1,8, '2024-02-19 19:15:00');


*/

SELECT @@global.time_zone;

SHOW GRANTS FOR 'liquidbits'@'%';

GRANT SUPER ON *.* TO 'liquidbits'@'192.168.51.70';

GRANT SUPER ON *.* TO 'liquidbits'@'192.168.51.70' IDENTIFIED BY 'liquidbits';



GRANT ALL PRIVILEGES ON *.* TO 'liquidbits'@'192.168.51.70' WITH GRANT OPTION;

SET GLOBAL time_zone = 'Europe/Berlin';





