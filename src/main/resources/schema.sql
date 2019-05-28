--Comic_data
drop table if exists comic_data;
create table comic_data(
    id integer not null auto_increment,
    title varchar(255) not null,
    path varchar(255) not null,
    primary key (id)
);

--Authors
drop table if exists authors;
create table authors(
    id integer not null auto_increment,
    first_name varchar(255) not null,
    middle_name varchar(255) default '',
    last_name varchar(255) default '',
    primary key (id)
);

--Comic_data_Author
drop table if exists comic_data_author;
create table comic_data_author(
    comic_data_id int not null,
    author_id int not null,
    foreign key (comic_data_id) references comic_data(id),
    foreign key (author_id) references authors(id)
);
