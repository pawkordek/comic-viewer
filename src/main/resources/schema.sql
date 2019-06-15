--Comic
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
    last_name varchar(255) default '',
    primary key (id)
);

--Comic_Author
drop table if exists comic_author;
create table comic_author(
    comic_id int not null,
    author_id int not null,
    foreign key (comic_id) references comics(id),
    foreign key (author_id) references authors(id)
);

--Author roles
drop table if exists author_roles;
create table author_roles(
    id integer not null auto_increment,
    name varchar(255) not null,
    primary key (id)
);

--Author_Author_role
drop table if exists author_author_role;
create table author_author_role(
    author_id int not null,
    author_role_id int not null,
    foreign key (author_id) references authors(id),
    foreign key (author_role_id) references author_roles(id)
);

--Tags
drop table if exists tags;
create table tags(
    id int not null auto_increment,
    name varchar(255) not null,
    primary key (id)
);

--Comic_tag
drop table if exists comic_tag;
create table comic_tag(
    comic_id int not null,
    tag_id int not null,
    foreign key (comic_id) references comics(id),
    foreign key (tag_id) references tags(id)
);

--Chapter
drop table if exists chapters;
create table chapters(
    id int not null auto_increment,
    title varchar(255) not null,
    amount_of_pages int not null,
    path varchar(255) not null,
    comic_id int not null,
    foreign key (comic_id) references comics(id)
)

