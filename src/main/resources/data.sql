--comic
insert into comics
(id, title, path)
values
(1, 'Kajko i Kokosz', 'kajko_kokosz'),
(2, 'Tytus, Romek i Atomek', 'tytus_romek_atomek'),
(3, 'Asterix', 'asterix');

--Authors
insert into authors
(id, first_name, middle_name, last_name)
values
(1, 'Janusz', '', 'Christa'),
(2, 'Henryk', 'Jerzy', 'Chmielewski'),
(3, 'René', '', 'Goscinny'),
(4, 'Albert','','Uderzo');

--Comics' authors
insert into comic_author
(comic_id,author_id)
values
(select id from comics where title = 'Kajko i Kokosz',select id from authors where first_name = 'Janusz' and last_name = 'Christa'),
(select id from comics where title = 'Tytus, Romek i Atomek', select id from authors where first_name = 'Henryk' and middle_name = 'Jerzy' and last_name = 'Chmielewski'),
(select id from comics where title = 'Asterix', select id from authors where first_name = 'René' and last_name = 'Goscinny'),
(select id from comics where title = 'Asterix', select id from authors where first_name = 'Albert' and last_name = 'Uderzo');

--Author roles
insert into author_roles
(id, name)
values
(1, 'Writer'),
(2, 'Artist');

--Authors' roles
insert into author_author_role
(author_id, author_role_id)
values
(select id from authors where first_name = 'Janusz' and last_name = 'Christa', select id from author_roles where name = 'Writer'),
(select id from authors where first_name = 'Janusz' and last_name = 'Christa', select id from author_roles where name = 'Artist'),
(select id from authors where first_name = 'Henryk' and middle_name = 'Jerzy' and last_name = 'Chmielewski', select id from author_roles where name = 'Writer'),
(select id from authors where first_name = 'Henryk' and middle_name = 'Jerzy' and last_name = 'Chmielewski', select id from author_roles where name = 'Artist'),
(select id from authors where first_name = 'René' and last_name = 'Goscinny', select id from author_roles where name = 'Writer'),
(select id from authors where first_name = 'Albert' and last_name = 'Uderzo', select id from author_roles where name = 'Artist');

--Tags
insert into tags
(id, name)
values
(1, 'Polish'),
(2, 'French');

--Comics' tags
insert into comic_tag
(comic_id, tag_id)
values
(select id from comics where title = 'Kajko i Kokosz',select id from tags where name = 'Polish'),
(select id from comics where title = 'Tytus, Romek i Atomek', select id from tags where name = 'Polish'),
(select id from comics where title = 'Asterix', select id from tags where name = 'French');
