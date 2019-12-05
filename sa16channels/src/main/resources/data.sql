insert into AUTHOR (id, `name`, `nationality`) values (1, 'W.Shakespeare', 'English');
insert into AUTHOR (id, `name`, `nationality`) values (2, 'V.Hugo', 'French');
insert into AUTHOR (id, `name`, `nationality`) values (3, 'I.Goethe', 'Germany');

insert into GENRE (id, `name`) values (1, 'tragedy');
insert into GENRE (id, `name`) values (2, 'comedy');
insert into GENRE (id, `name`) values (3, 'prose');
insert into GENRE (id, `name`) values (4, 'science fiction');


insert into BOOK (id, `name`, authorid, genreid) values (1, 'Faust', 3,  1);
insert into BOOK (id, `name`, authorid, genreid) values (2, 'Midsummer nights dream', 1,  2);
insert into BOOK (id, `name`, authorid, genreid) values (3, 'Otello', 1,  1);
insert into BOOK (id, `name`, authorid, genreid) values (4, 'Notre Dame', 2,  3);


insert into BRAND (id, `name`, `country`) values (1, 'KUOMA', 'Finland');
insert into BRAND (id, `name`, `country`) values (2, 'Nike', 'USA');
insert into BRAND (id, `name`, `country`) values (3, 'Columbia', 'Jermany');

insert into STORE (id, `code`, `address`) values (1, 'M234', 'Moscow, Gorodeckaya 1');

insert into PROVIDER (id, `name`, `inn`,`address`, flagblacklist) values (1, 'Поставщик 1', '2234555', 'Moscow, Gorodeckaya 3', 0);
insert into PROVIDER (id, `name`, `inn`,`address`, flagblacklist) values (2, 'Поставщик 2', '2233344', 'Moscow, Gorodeckaya 5', 0);
insert into PROVIDER (id, `name`, `inn`,`address`, flagblacklist) values (3, 'Поставщик 3', '5566344', 'Moscow, Gorodeckaya 8', 1);

insert into CUSTOMER (id, `login`,`name`, `phone`) values (1, 'ivanov', 'Иванов И.И.', '+79162345456');

insert into GOODSTYPE (id, `code`,`name`) values (1, 'SH345', 'Обувь');
insert into GOODSTYPE (id, `code`,`name`) values (2, 'CL355', 'Одежда');
insert into GOODSTYPE (id, `code`,`name`) values (3, 'HH433', 'Товары для дома');

insert into GOODS (id, `code`,`name`, `colour`, `size`, goodstypeid, brandid, providerid, storeid) values (1, 'R456', 'Сапоги Kuoma', 'blue', '38', 1, 1, 1, 1);
insert into GOODS (id, `code`,`name`, `colour`, `size`, goodstypeid, brandid, providerid, storeid) values (2, 'R454', 'Сапоги Kuoma', 'red', '36', 1, 1, 1, 1);
insert into GOODS (id, `code`,`name`, `colour`, `size`, goodstypeid, brandid, providerid, storeid) values (3, 'R354', 'Куртка Colambia', 'red', '44', 2, 3, 2, 1);

/*
CREATE TABLE GOODS(ID NUMERIC(18) PRIMARY KEY AUTO_INCREMENT, CODE VARCHAR(100) NOT NULL, NAME VARCHAR(255) NOT NULL, COLOUR VARCHAR(100), SIZE VARCHAR(20), GOODSTYPEID NUMERIC(18), BRANDID NUMERIC(18), PROVIDERID NUMERIC(18), STOREID NUMERIC(18));

CREATE TABLE GOODSTYPE(ID NUMERIC(18) PRIMARY KEY AUTO_INCREMENT, CODE VARCHAR(100) NOT NULL, NAME VARCHAR(255));


*/