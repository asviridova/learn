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

-----------------------------------
INSERT INTO acl_sid (id, principal, sid) VALUES
(1, 1, 'admin'),
(2, 1, 'user'),
(3, 0, 'ROLE_EDITOR');


INSERT INTO acl_class (id, class) VALUES
(1, 'ru.otus.spring.domain.Book');


INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
(1, 1, 1, NULL, 3, 0),
(2, 1, 2, NULL, 3, 0),
(3, 1, 3, NULL, 3, 0),
(4, 1, 4, NULL, 3, 0);


INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES
(1, 1, 1, 1, 1, 1, 1, 1),  --book1 admin read
(2, 1, 2, 1, 2, 1, 1, 1),   --book1 admin write
(3, 2, 1, 1, 1, 1, 1, 1),  -- book2 admin read
(4, 2, 2, 1, 2, 1, 1, 1),   -- book2 admin write
(5, 3, 1, 1, 1, 1, 1, 1), --book3 admin read
(6, 3, 2, 1, 2, 1, 1, 1), --book3 admin write
(7, 4, 1, 1, 1, 1, 1, 1), --book4 admin read
(8, 4, 2, 1, 2, 1, 1, 1), --book4 admin write

(9, 1, 3, 2, 1, 1, 1, 1),  -- book1 user read
(10, 2, 3, 2, 1, 1, 1, 1),  -- book2 user read
(11, 3, 3, 2, 1, 1, 1, 1),  --book3 user read
(12, 4, 3, 2, 1, 1, 1, 1), --book4 user read

(13, 2, 4, 3, 1, 1, 1, 1),  --book2 ROLE_EDITOR read
(14, 2, 5, 3, 2, 1, 1, 1),   --book2  ROLE_EDITOR write
(15, 3, 4, 3, 1, 1, 1, 1), --book3 ROLE_EDITOR read
(16, 3, 5, 3, 2, 1, 1, 1), --book3 ROLE_EDITOR write

(17, 1, 4, 3, 1, 1, 1, 1),  -- book1 ROLE_EDITOR read
(18, 4, 4, 3, 1, 1, 1, 1); --book4 ROLE_EDITOR read



