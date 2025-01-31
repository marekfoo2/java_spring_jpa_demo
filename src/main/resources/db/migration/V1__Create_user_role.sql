--Tables definitions

-- USER: ManyToMany ROLE, OneToMany ADDRESS, OneToOne ?
CREATE TABLE "USER"
  (
     ID          int NOT NULL AUTO_INCREMENT PRIMARY KEY,
     LOGIN       varchar(30) NOT NULL UNIQUE,
     FIRST_NAME  varchar(50),
     LAST_NAME   varchar(70),
     PASSWORD    varchar(50),
     EMAIL       varchar(60)
  );

-- ADDRESS: ManyToOne USER.
CREATE TABLE ADDRESS
  (
     ID          int NOT NULL AUTO_INCREMENT PRIMARY KEY,
     STREET      varchar(70) NOT NULL,
     ZIP_CODE    varchar(10),
     CITY        varchar(70),
     ADDRESS_TYPE varchar(20) NOT NULL,
     USER_ID     int NOT NULL
  );

--ManyToMany User-Role
CREATE TABLE ROLE
  (
     ID          int NOT NULL AUTO_INCREMENT PRIMARY KEY,
     NAME        varchar(30) NOT NULL UNIQUE
  );

CREATE TABLE ROLE_USER
  (
     ROLE_ID      int         NOT NULL,
     USER_ID      int         NOT NULL,
     PRIMARY KEY (ROLE_ID, USER_ID)
  );

-- LOGIN_HISTORY: ManyToOne USER
CREATE TABLE "LOGIN_HISTORY"
  (
     ID          int NOT NULL AUTO_INCREMENT PRIMARY KEY,
     LOGIN       varchar(30) NOT NULL,
     LAST_LOGIN  timestamp    NULL NULL,
     IP_ADDRESS  varchar(50) NULL,
     IS_SUCCESS  varchar(1) NOT NULL
  );

--Sequences
CREATE SEQUENCE USER_ID_SEQ INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 100 CACHE 1;

CREATE SEQUENCE ADDRESS_ID_SEQ INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 100 CACHE 1;

CREATE SEQUENCE ROLE_ID_SEQ INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 100 CACHE 1;

CREATE SEQUENCE LOGIN_HISTORY_ID_SEQ INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 100 CACHE 1;

-- FK constraints
ALTER TABLE ROLE_USER
  ADD CONSTRAINT ROLE_ROLE_USER_FK FOREIGN KEY (ROLE_ID) REFERENCES ROLE(ID);

ALTER TABLE ROLE_USER
  ADD CONSTRAINT USER_ROLE_USER_FK FOREIGN KEY (USER_ID) REFERENCES "USER"(ID);

ALTER TABLE ADDRESS
  ADD CONSTRAINT ADDRESS_FK FOREIGN KEY (USER_ID) REFERENCES "USER"(ID);

ALTER TABLE LOGIN_HISTORY
  ADD CONSTRAINT LOGIN_HISTORY_FK FOREIGN KEY (LOGIN) REFERENCES "USER"(LOGIN);

-- Unique constraints
ALTER TABLE ADDRESS
  ADD CONSTRAINT ADDRESS_TYPE_USER_UQ UNIQUE (USER_ID, ADDRESS_TYPE);