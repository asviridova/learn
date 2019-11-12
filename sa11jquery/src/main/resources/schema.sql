DROP TABLE IF EXISTS AUTHOR;
CREATE TABLE AUTHOR(ID NUMERIC(18) PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255), NATIONALITY VARCHAR(255));

DROP TABLE IF EXISTS BOOK;
CREATE TABLE BOOK(ID NUMERIC(18) PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255), AUTHORID NUMERIC(18), GENREID NUMERIC(18));

DROP TABLE IF EXISTS GENRE;
CREATE TABLE GENRE(ID BIGINT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255));

DROP TABLE IF EXISTS COMMENTBOOK;
CREATE TABLE COMMENTBOOK(ID NUMERIC(18) PRIMARY KEY AUTO_INCREMENT, COMMENTTEXT VARCHAR(512), BOOKID  NUMERIC(18));


ALTER TABLE BOOK 
ADD FOREIGN KEY (AUTHORID) REFERENCES AUTHOR(ID);

ALTER TABLE BOOK 
ADD FOREIGN KEY (GENREID) REFERENCES GENRE(ID);

ALTER TABLE COMMENTBOOK 
ADD FOREIGN KEY (BOOKID) REFERENCES BOOK(ID);



