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
 constraint user_pk primary key(id),
 constraint unique_username UNIQUE(username)
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

insert into role value(1, 'USER');

INSERT INTO `user` (`id`, `firstname`, `lastname`, `location`, `username`, `password`, `active`) VALUES (1,'Shaju','Kuppelan','Kochi','373962','$2a$10$pOLc5e/JU3iZ1ktOkomn7.Le5N5Mxay7CBZpfLI1Zox0FFEQr09ti',1);
INSERT INTO `user` (`id`, `firstname`, `lastname`, `location`, `username`, `password`, `active`) VALUES (2,'Shibu','Kuppelan','Kochi','373963','$2a$10$FRZHdlSqPmIVlMdDtvvkmetxvedmU2gHUO8rI2ultH8euIflD.KY.',1);
INSERT INTO `user` (`id`, `firstname`, `lastname`, `location`, `username`, `password`, `active`) VALUES (3,'Sandhya','KU','Chennai','373964','$2a$10$PJyrHC6GIJ6ykEyuck/NtO9Q8PyG94xqupujHuUP.2AExDNhz//P2',1);
INSERT INTO `user` (`id`, `firstname`, `lastname`, `location`, `username`, `password`, `active`) VALUES (4,'Selvin','Rich','Chennai','373965','$2a$10$pOLc5e/JU3iZ1ktOkomn7.Le5N5Mxay7CBZpfLI1Zox0FFEQr09ti',1);
INSERT INTO `user` (`id`, `firstname`, `lastname`, `location`, `username`, `password`, `active`) VALUES (5,'Karthik','Perumal','Coimbatore','373966','$2a$10$FRZHdlSqPmIVlMdDtvvkmetxvedmU2gHUO8rI2ultH8euIflD.KY.',1);
INSERT INTO `user` (`id`, `firstname`, `lastname`, `location`, `username`, `password`, `active`) VALUES (6,'Nandhini','D','Coimbatore','373967','$2a$10$PJyrHC6GIJ6ykEyuck/NtO9Q8PyG94xqupujHuUP.2AExDNhz//P2',1);

insert into user_role values(1,1);
insert into user_role values(2,1);
insert into user_role values(3,1);
insert into user_role values(4,1);
insert into user_role values(5,1);
insert into user_role values(6,1);


drop table if exists `persistent_logins`;
create table `persistent_logins`(
username varchar(255) not null,
series varchar(255) not null,
token varchar(255) not null,
last_used timestamp not null default current_timestamp on update current_timestamp,
primary key(series)
);

---@@@@@@@@@@@@@@@@@@@
drop table if exists `scoretable`;
drop table if exists `championprediction`;
drop table if exists `semifinalprediction`;
drop table if exists `finalprediction`;
drop table if exists `dailyprediction`;
drop table if exists `game`;


CREATE TABLE scoretable(
userid int(11) PRIMARY KEY,
total_score INTEGER  DEFAULT '0' NOT NULL,
history_score INTEGER DEFAULT '0' NOT NULL,
constraint userid_scoretable foreign key (userid) references user(id)
);



CREATE TABLE championprediction(
userid int(11) PRIMARY KEY,
prediction VARCHAR(40) NOT NULL,
points int(11) DEFAULT '0' NOT NULL,
constraint userid_championprediction foreign key (userid) references user(id)
);


CREATE TABLE semifinalprediction(
userid int(11) PRIMARY KEY,
team1 VARCHAR(40) NOT NULL,
team2 VARCHAR(40) NOT NULL,
team3 VARCHAR(40) NOT NULL,
team4 VARCHAR(40) NOT NULL,
points int(11) DEFAULT '0' NOT NULL,
constraint userid_semifinalprediction foreign key (userid) references user(id)
);



CREATE TABLE finalprediction(
userid int(11) PRIMARY KEY,
team1 VARCHAR(40) NOT NULL,
team2 VARCHAR(40) NOT NULL,
points int(11) DEFAULT '0' NOT NULL,
constraint userid_finalprediction foreign key (userid) references user(id)
);

CREATE TABLE game (
match_no INTEGER PRIMARY KEY,
team1  VARCHAR(40) NOT NULL,
team2 VARCHAR(40) NOT NULL,
match_date datetime,
venue varchar(255) not null,
match_result VARCHAR(40) NOT NULL
);


CREATE TABLE dailyprediction(
userid int(11),
match_no INTEGER, 
prediction VARCHAR(40) NOT NULL,
points int(11) DEFAULT '0' NOT NULL,
PRIMARY KEY (userid , match_no),
constraint userid_dailyprediction foreign key (userid) references user(id),
constraint match_no_dailyprediction foreign key (match_no) references game(match_no)
);


INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (44,'SRI LANKA','INDIA','2019-05-09 15:00:00','Headingley, Leeds','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (45,'AUSTRALIA','SOUTH AFRICA','2019-05-09 18:00:00','Old Trafford, Manchester','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (46,'1ST','4TH','2019-05-09 15:00:00','Old Trafford, Manchester','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (47,'2ND','3RD','2019-05-09 15:00:00','Edgbaston, Birmingham','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (48,'TBC','TBC','2019-05-09 15:00:00','Lord\'s Cricket Ground, London','');


INSERT INTO `dailyprediction` (`userid`, `match_no`, `prediction`) VALUES (1,44,'INDIA');
INSERT INTO `dailyprediction` (`userid`, `match_no`, `prediction`) VALUES (1,45,'SOUTH AFRICA');
INSERT INTO `dailyprediction` (`userid`, `match_no`, `prediction`) VALUES (1,46,'INDIA');
INSERT INTO `dailyprediction` (`userid`, `match_no`, `prediction`) VALUES (1,47,'SOUTH AFRICA');
INSERT INTO `dailyprediction` (`userid`, `match_no`, `prediction`) VALUES (1,48,'INDIA');

INSERT INTO `dailyprediction` (`userid`, `match_no`, `prediction`) VALUES (2,44,'SRI LANKA');
INSERT INTO `dailyprediction` (`userid`, `match_no`, `prediction`) VALUES (2,45,'SOUTH AFRICA');
INSERT INTO `dailyprediction` (`userid`, `match_no`, `prediction`) VALUES (2,46,'PAKISTAN');
INSERT INTO `dailyprediction` (`userid`, `match_no`, `prediction`) VALUES (2,47,'SOUTH AFRICA');
INSERT INTO `dailyprediction` (`userid`, `match_no`, `prediction`) VALUES (2,48,'SOUTH AFRICA');

INSERT INTO `dailyprediction` (`userid`, `match_no`, `prediction`) VALUES (3,44,'INDIA');
INSERT INTO `dailyprediction` (`userid`, `match_no`, `prediction`) VALUES (3,45,'AUSTRALIA');
INSERT INTO `dailyprediction` (`userid`, `match_no`, `prediction`) VALUES (3,46,'INDIA');
INSERT INTO `dailyprediction` (`userid`, `match_no`, `prediction`) VALUES (3,47,'ENGLAND');
INSERT INTO `dailyprediction` (`userid`, `match_no`, `prediction`) VALUES (3,48,'SOUTH AFRICA');



INSERT INTO `semifinalprediction` (`userid`, `team1`, `team2`, `team3`, `team4`) VALUES (1,'INDIA','SOUTH AFRICA','PAKISTAN','ENGLAND');
INSERT INTO `semifinalprediction` (`userid`, `team1`, `team2`, `team3`, `team4`) VALUES (2,'INDIA','SOUTH AFRICA','ENGLAND','AUSTRALIA');
INSERT INTO `semifinalprediction` (`userid`, `team1`, `team2`, `team3`, `team4`) VALUES (3,'INDIA','SOUTH AFRICA','SRI LANKA','BANGLADESH');
INSERT INTO `semifinalprediction` (`userid`, `team1`, `team2`, `team3`, `team4`) VALUES (4,'INDIA','SRI LANKA','BANGLADESH','AUSTRALIA');
INSERT INTO `semifinalprediction` (`userid`, `team1`, `team2`, `team3`, `team4`) VALUES (5,'SRI LANKA','BANGLADESH','AUSTRALIA','NEW ZEALAND');
INSERT INTO `semifinalprediction` (`userid`, `team1`, `team2`, `team3`, `team4`) VALUES (6,'AUSTRALIA','AFGHANISTAN','SRI LANKA','BANGLADESH');

INSERT INTO `finalprediction` (`userid`, `team1`, `team2`) VALUES (1,'INDIA','PAKISTAN');
INSERT INTO `finalprediction` (`userid`, `team1`, `team2`) VALUES (2,'INDIA','SOUTH AFRICA');
INSERT INTO `finalprediction` (`userid`, `team1`, `team2`) VALUES (3,'BANGLADESH','AUSTRALIA');

INSERT INTO `championprediction` (`userid`, `prediction`) VALUES (1,'INDIA');
INSERT INTO `championprediction` (`userid`, `prediction`) VALUES (2,'INDIA');
INSERT INTO `championprediction` (`userid`, `prediction`) VALUES (3,'PAKISTAN');
INSERT INTO `championprediction` (`userid`, `prediction`) VALUES (4,'SOUTH AFRICA');




-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@ STORED PROCEDURE  starts

DROP PROCEDURE IF EXISTS cricapp.getMatchByVenue;
DELIMITER //
CREATE PROCEDURE cricapp.`getMatchByVenue`(IN venueIn varchar(255), IN teamNo2 varchar(255))
BEGIN
  select * FROM game where venue=venueIn and team2=teamNo2;
END;



-- CALL getMatchByVenue('Headingley, Leeds','INDIA');

DROP PROCEDURE IF EXISTS cricapp.updateDailyPredictionByMatch;
DELIMITER //
CREATE PROCEDURE cricapp.`updateDailyPredictionByMatch`(IN matchNoIn INTEGER, IN successPointsIn int(11), 
														IN failurePointsIn int(11), matchResultIn varchar(25))
BEGIN
  
UPDATE `dailyprediction` SET `points` = CASE
    WHEN prediction = matchResultIn THEN points + successPointsIn
    ELSE points + failurePointsIn
    END
WHERE match_no  = matchNoIn;

END;

-- CALL updateDailyPredictionByMatch(1, 100, -30, 'SOUTH AFRICA');


DROP PROCEDURE IF EXISTS cricapp.updateSemiFinalPrediction;
DELIMITER //
CREATE PROCEDURE cricapp.`updateSemiFinalPrediction`(IN team1In varchar(25), 
													 IN team2In varchar(25), 
													 IN team3In varchar(25),
													 IN team4In varchar(25),
													 IN successPointsIn int(11),
													 IN failurePointsIn int(11))
BEGIN
  
UPDATE `semifinalprediction` SET `points` =points + successPointsIn WHERE team1 in (team1In,team2In,team3In,team4In);
  
UPDATE `semifinalprediction` SET `points` =points + successPointsIn WHERE team2 in (team1In,team2In,team3In,team4In);
  
UPDATE `semifinalprediction` SET `points` =points + successPointsIn WHERE team3 in (team1In,team2In,team3In,team4In);
  
UPDATE `semifinalprediction` SET `points` =points + successPointsIn WHERE team4 in (team1In,team2In,team3In,team4In);


UPDATE `semifinalprediction` SET `points` = CASE
	WHEN  points = (successPointsIn * 4)
		THEN (successPointsIn * 4) 
    WHEN  points =  (successPointsIn * 3)
		THEN (successPointsIn * 3) + failurePointsIn
    WHEN  points = (successPointsIn * 2)
		THEN (successPointsIn * 2) + failurePointsIn
    WHEN  points = (successPointsIn * 1)
		THEN (successPointsIn * 1) + failurePointsIn
    WHEN  points = 0
		THEN (failurePointsIn * 4)
    ELSE points = 0
    END
WHERE userid IS NOT NULL ;

END;


-- call updateSemiFinalPrediction('INDIA','SOUTH AFRICA','PAKISTAN','ENGLAND',25,-5);

DROP PROCEDURE IF EXISTS cricapp.updateAllFinalPredictions;
DELIMITER //
CREATE PROCEDURE cricapp.`updateAllFinalPredictions`(IN team1In varchar(25), 
													 IN team2In varchar(25), 
													 IN successPointsIn int(11),
													 IN failurePointsIn int(11))
BEGIN
  
UPDATE `finalprediction` SET `points` =points + successPointsIn WHERE team1 in (team1In,team2In);
  
UPDATE `finalprediction` SET `points` =points + successPointsIn WHERE team2 in (team1In,team2In);
 
UPDATE `finalprediction` SET `points` = CASE
	WHEN  points = (successPointsIn * 2)
		THEN (successPointsIn * 2) 
    WHEN  points = (successPointsIn * 1)
		THEN (successPointsIn * 1) + failurePointsIn
    WHEN  points = 0
		THEN (failurePointsIn * 2)
    ELSE points = 0
    END
WHERE userid IS NOT NULL;

END;


-- call updateAllFinalPredictions('INDIA','PAKISTAN',25,-5);


DROP PROCEDURE IF EXISTS cricapp.updateAllChampionPredictions;
DELIMITER //
CREATE PROCEDURE cricapp.`updateAllChampionPredictions`(IN championIn varchar(25), 
													    IN successPointsIn int(11),
													    IN failurePointsIn int(11))
BEGIN
  
UPDATE `championprediction` SET `points` = CASE
    WHEN prediction = championIn THEN points + successPointsIn
		ELSE points + failurePointsIn
    END
WHERE userid IS NOT NULL;

END;

-- call updateAllChampionPredictions('INDIA',25,-5);

DROP PROCEDURE IF EXISTS cricapp.updateAllScores;
DELIMITER //
CREATE PROCEDURE cricapp.`updateAllScores`()
BEGIN
  
update scoretable set history_score=total_score;

insert into scoretable (select userid, sum(points) as points,0 from dailyprediction group by userid)  ON DUPLICATE KEY UPDATE 
total_score=VALUES(total_score)+total_score;

insert into scoretable (select userid, sum(points) as points,0 from semifinalprediction group by userid)  ON DUPLICATE KEY UPDATE 
total_score=VALUES(total_score)+total_score;

insert into scoretable (select userid, sum(points) as points,0 from finalprediction group by userid)  ON DUPLICATE KEY UPDATE 
total_score=VALUES(total_score)+total_score;

insert into scoretable (select userid, sum(points) as points,0 from championprediction group by userid)  ON DUPLICATE KEY UPDATE 
total_score=VALUES(total_score)+total_score;

END;

--call updateAllScores();
--@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
