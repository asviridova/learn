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

