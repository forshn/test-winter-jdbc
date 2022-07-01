SET FOREIGN_KEY_CHECKS = 0;

drop table if exists movie;
drop table if exists actor;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE movie
(
    id           INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(30) NOT NULL unique,
    release_date DATE        NOT NULL,
    description  text,
    geo_type     text
);

create table actor
(
    id    INT          NOT NULL primary key auto_increment,
    name  varchar(100) not null unique ,
    movie int,
    foreign key (movie) references movie(id) on delete cascade
);


/*create table movie_actor
(
    actor int not null unique,
    movie int not null unique,
    name  varchar(100),
    primary key (actor, movie)
);

create table rental
(
    id       int not null primary key AUTO_INCREMENT,
    movie    int references movie (id),
    duration int,
    price    int
);
*/