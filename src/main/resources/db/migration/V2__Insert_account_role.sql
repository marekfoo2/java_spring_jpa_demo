INSERT INTO ACCOUNT (ID, LOGIN, FIRST_NAME, LAST_NAME, PASSWORD, EMAIL) VALUES (1,'login1', 'hotname', 'spotName', 'passwordek', 'admin@smart.de');
INSERT INTO ACCOUNT (ID, LOGIN, FIRST_NAME, LAST_NAME, PASSWORD, EMAIL) VALUES (2,'login2', 'hotname2', 'spotName2', 'passwordek2', 'admin2@smart.de');
INSERT INTO ACCOUNT (ID, LOGIN, FIRST_NAME, LAST_NAME, PASSWORD, EMAIL) VALUES (3,'login3', 'hotname3', 'spotName3', 'passwordek3', 'admin3@smart.de');
--

INSERT INTO ADDRESS(ACCOUNT_ID, STREET, ZIP_CODE, CITY, ADDRESS_TYPE) VALUES (1,'street1','zipcode1','city1','home');
INSERT INTO ADDRESS(ACCOUNT_ID, STREET, ZIP_CODE, CITY, ADDRESS_TYPE) VALUES (1,'street11','zipcode11','city11','office');
INSERT INTO ADDRESS(ACCOUNT_ID, STREET, ZIP_CODE, CITY, ADDRESS_TYPE) VALUES (2,'street2','zipcode2','city2','home');
INSERT INTO ADDRESS(ACCOUNT_ID, STREET, ZIP_CODE, CITY, ADDRESS_TYPE) VALUES (3,'street3','zipcode3','city3','home3');

--INSERT INTO ROLE (NAME) VALUES ('ADMIN_ROLE');
--INSERT INTO ROLE (NAME) VALUES ('USER_ROLE');
--INSERT INTO ROLE (NAME) VALUES ('MEDI_ROLE');
--
--INSERT INTO ROLE_ACCOUNT (ROLE_ID,ACCOUNT_ID) VALUES (1,1);
--INSERT INTO ROLE_ACCOUNT (ROLE_ID,ACCOUNT_ID) VALUES (2,2);
--INSERT INTO ROLE_ACCOUNT (ROLE_ID,ACCOUNT_ID) VALUES (3,3);