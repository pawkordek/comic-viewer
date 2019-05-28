--Comic_data
insert into comic_data
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

--Comics
insert into comic_data_author
(comic_data_id,author_id)
values
(select id from comic_data where title = 'Kajko i Kokosz',select id from authors where first_name = 'Janusz' and last_name = 'Christa'),
(select id from comic_data where title = 'Tytus, Romek i Atomek', select id from authors where first_name = 'Henryk' and middle_name = 'Jerzy' and last_name = 'Chmielewski'),
(select id from comic_data where title = 'Asterix', select id from authors where first_name = 'René' and last_name = 'Goscinny'),
(select id from comic_data where title = 'Asterix', select id from authors where first_name = 'Albert' and last_name = 'Uderzo');
