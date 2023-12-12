USE liquidbits;

INSERT INTO DRINKTYPE(NAME, ALCVALUE, INTENSITY)
VALUES ('Bier', 0, 0);

INSERT INTO DRINKTYPE(NAME, ALCVALUE, INTENSITY)
VALUES ('Wein', 0, 0);

INSERT INTO DRINKTYPE(NAME, ALCVALUE, INTENSITY)
VALUES ('Juice', 0, 0);

INSERT INTO DEVICE(LOCATION, MANUFACTURER, MODELL)
VALUES ('HTL Mössingerstrasse', 'liquidBits', 'iSchank');

INSERT INTO USER(username, mail)
VALUES ('Hubi', 'hubert.lercher@edu.htl-klu.at');

INSERT INTO USER(username, mail)
VALUES ('Dopler', 'stephan.dopler@edu.htl-klu.at');

INSERT INTO USER(username, mail)
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


