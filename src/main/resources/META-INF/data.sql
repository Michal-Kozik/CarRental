INSERT INTO "USER" (ID, LOGIN, PASSWORD, EMAIL, FIRSTNAME, LASTNAME) VALUES (9999, 'admin', 'admin', 'abc@gmail.com', 'myName', 'MySurname')
INSERT INTO USERGROUP (ID, "NAME", USER_ID) VALUES (9998, 'ROLE_CLIENT', 9999)
INSERT INTO USERGROUP (ID, "NAME", USER_ID) VALUES (9999, 'ROLE_ADMIN', 9999)