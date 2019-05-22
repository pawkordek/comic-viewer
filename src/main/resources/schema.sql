--Comics
drop table if exists comics;
create table comics(
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
    last_name varchar(255) not null,
    comic_id integer,
    foreign key (comic_id) references comics(id),
    primary key (id)
);
