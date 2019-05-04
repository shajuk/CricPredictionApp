drop database if exists `cricapp`;
create database cricapp;
use cricapp;
drop table if exists `role`;
create table role(
 role_id int(11) not null auto_increment,
 role varchar(255) default null,
 primary key(role_id)
);

drop table if exists `user`;
create table `user`(
 id int(11) not null auto_increment,
 firstname varchar(255) not null,
 lastname varchar(255) not null,
 location varchar(255) not null,
 username varchar(255) not null,
 password varchar(255) not null,
 active int(11) default null,
  primary key(id)
);

drop table if exists `user_role`;
create table `user_role`(
 user_id int(11) not null,
 role_id int(11) not null,
 primary key (user_id, role_id),
 key user_role_key (role_id),
 constraint user_userrole foreign key (user_id) references user(id),
 constraint role_userrole foreign key (role_id) references role(role_id)
);


drop table if exists `persistent_logins`;
create table `persistent_logins`(
username varchar(255) not null,
series varchar(255) not null,
token varchar(255) not null,
last_used timestamp not null default current_timestamp on update current_timestamp,
primary key(series)
);

insert into role value(1, 'USER');


drop table if exists `matches`;
CREATE TABLE matches (
match_no INTEGER PRIMARY KEY,
team1  VARCHAR(40) NOT NULL,
team2 VARCHAR(40) NOT NULL,
date datetime,
venue varchar(255) not null,
result VARCHAR(40) NOT NULL 
);

drop table if exists `dailyprediction`;
CREATE TABLE dailyprediction(
userid int(11),
match_no INTEGER, 
prediction VARCHAR(40) NOT NULL,
PRIMARY KEY (userid , match_no),
constraint userid_dailyprediction foreign key (userid) references user(id),
constraint match_no_dailyprediction foreign key (match_no) references matches(match_no)
);

drop table if exists `semifinalprediction`;
CREATE TABLE semifinalprediction(
userid int(11) PRIMARY KEY,
team1 VARCHAR(40) NOT NULL,
team2 VARCHAR(40) NOT NULL,
team3 VARCHAR(40) NOT NULL,
team4 VARCHAR(40) NOT NULL,
constraint userid_semifinalprediction foreign key (userid) references user(id)
);

drop table if exists `finalprediction`;
CREATE TABLE finalprediction(
userid int(11) PRIMARY KEY,
team1 VARCHAR(40) NOT NULL,
team2 VARCHAR(40) NOT NULL,
constraint userid_finalprediction foreign key (userid) references user(id)
);

drop table if exists `championprediction`;
CREATE TABLE championprediction(
userid int(11) PRIMARY KEY,
prediction VARCHAR(40) NOT NULL,
constraint userid_championprediction foreign key (userid) references user(id)
);

drop table if exists `scoretable`;
CREATE TABLE scoretable(
userid int(11) PRIMARY KEY,
total_score INTEGER NOT NULL,
history_score INTEGER NOT NULL,
constraint userid_scoretable foreign key (userid) references user(id)
);


