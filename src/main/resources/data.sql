--Comics
insert into comics
(title, path)
values
('Kajko i Kokosz', 'kajkoko_kokosz'),
('Tytus, Romek i Atomek', 'tytus_romek_atomek');

--Authors
insert into authors
(first_name, middle_name, last_name, comic_id)
values
('Janusz', '', 'Christa', (select id from comics where title = 'Kajko i kokosz')),
('Henryk', 'Jerzy', 'Chmielewski', (select id from comics where title = 'Tytus, Romek i Atomek'));
