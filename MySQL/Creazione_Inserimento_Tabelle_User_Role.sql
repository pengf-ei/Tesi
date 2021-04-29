create database if not exists questionario;
use questionario;

drop table if exists user;

create table user (
	id int NOT NULL auto_increment,
	username varchar(50) unique not null,
	nome varchar(50) not null,
	cognome varchar(50) not null,
	email varchar(80) unique,
	password varchar(80) not null,
	eta int,
	genere char(1),
	primary key(id)
) auto_increment=1;

insert into user (username, nome, cognome, email, password, eta, genere)
values ('user_mario', 'Mario', 'Rossi', 'mario@questionario.com', 'test123', null, null),
('user_luigi', 'Luigi', 'Rossi', 'luigi@questionario.com', 'test123', null, 'M'),
('user_daisy', 'Daisy', 'Principessa', 'daisy@questionario.com', 'test123', null, 'F');



create table role (
	id int NOT NULL AUTO_INCREMENT,
	name varchar(50) DEFAULT NULL,
	PRIMARY KEY (id)
) AUTO_INCREMENT=1;

insert into role (name) values ('ROLE_UTENTE'),('ROLE_ADMIN'),('ROLE_GUEST');

create table users_roles (
	user_id int not null,
    role_id int not null,
    
    primary key (user_id, role_id),
    
    constraint FK_USER foreign key (user_id)
    references user(id)
    on update no action on delete no action,
    
    constraint FK_ROLE foreign key (role_id)
    references role(id)
    on update no action on delete no action
);

insert into users_roles (user_id, role_id)
values
(1,1),
(1,2),
(2,1),
(3,1);

