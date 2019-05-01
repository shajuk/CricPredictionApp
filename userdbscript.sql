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