drop table comics if exists;
create table comics(
    id integer not null auto_increment,
    title varchar(255) not null,
    path varchar(255) not null,
    primary key (id)
);