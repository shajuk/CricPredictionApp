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


drop table if exists `persistent_logins`;
create table `persistent_logins`(
username varchar(255) not null,
series varchar(255) not null,
token varchar(255) not null,
last_used timestamp not null default current_timestamp on update current_timestamp,
primary key(series)
);


drop table if exists `game`;
CREATE TABLE game (
match_no INTEGER PRIMARY KEY,
team1  VARCHAR(40) NOT NULL,
team2 VARCHAR(40) NOT NULL,
match_date datetime,
venue varchar(255) not null,
match_result VARCHAR(40) NOT NULL
);

drop table if exists `dailyprediction`;
CREATE TABLE dailyprediction(
userid int(11),
match_no INTEGER, 
prediction VARCHAR(40) NOT NULL,
points int(11) DEFAULT '0' NOT NULL,
PRIMARY KEY (userid , match_no),
constraint userid_dailyprediction foreign key (userid) references user(id),
constraint match_no_dailyprediction foreign key (match_no) references game(match_no)
);

drop table if exists `semifinalprediction`;
CREATE TABLE semifinalprediction(
userid int(11) PRIMARY KEY,
team1 VARCHAR(40) NOT NULL,
team2 VARCHAR(40) NOT NULL,
team3 VARCHAR(40) NOT NULL,
team4 VARCHAR(40) NOT NULL,
points int(11) DEFAULT '0' NOT NULL,
constraint userid_semifinalprediction foreign key (userid) references user(id)
);

drop table if exists `finalprediction`;
CREATE TABLE finalprediction(
userid int(11) PRIMARY KEY,
team1 VARCHAR(40) NOT NULL,
team2 VARCHAR(40) NOT NULL,
points int(11) DEFAULT '0' NOT NULL,
constraint userid_finalprediction foreign key (userid) references user(id)
);

drop table if exists `championprediction`;
CREATE TABLE championprediction(
userid int(11) PRIMARY KEY,
prediction VARCHAR(40) NOT NULL,
points int(11) DEFAULT '0' NOT NULL,
constraint userid_championprediction foreign key (userid) references user(id)
);

drop table if exists `scoretable`;
CREATE TABLE scoretable(
userid int(11) PRIMARY KEY,
total_score INTEGER  DEFAULT '0' NOT NULL,
history_score INTEGER DEFAULT '0' NOT NULL,
constraint userid_scoretable foreign key (userid) references user(id)
);



INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (1,'ENGLAND','SOUTH AFRICA','2019-05-30 15:00:00','The Oval, London','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (2,'WEST INDIES','PAKISTAN','2019-05-31 15:00:00','Trent Bridge, Nottingham','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (3,'NEW ZEALAND','SRI LANKA','2019-06-01 15:00:00','Cardiff Wales Stadium, Cardiff','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (4,'AFGHANISTAN','AUSTRALIA','2019-06-01 18:00:00','Bristol County Ground, Bristol','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (5,'SOUTH AFRICA','BANGLADESH','2019-06-02 15:00:00','The Oval, London','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (6,'ENGLAND','PAKISTAN','2019-06-03 15:00:00','Trent Bridge, Nottingham','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (7,'AFGHANISTAN','SRI LANKA','2019-06-04 15:00:00','Cardiff Wales Stadium, Cardiff','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (8,'SOUTH AFRICA','INDIA','2019-06-05 15:00:00','The Ageas Bowl, Southampton','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (9,'BANGLADESH','NEW ZEALAND','2019-06-05 18:00:00','The Oval, London','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (10,'AUSTRALIA','WEST INDIES','2019-06-06 15:00:00','Trent Bridge, Nottingham','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (11,'PAKISTAN','SRI LANKA','2019-06-07 15:00:00','Bristol County Ground, Bristol','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (12,'ENGLAND','BANGLADESH','2019-06-08 15:00:00','Cardiff Wales Stadium, Cardiff','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (13,'AFGHANISTAN','NEW ZEALAND','2019-06-08 18:00:00','The County Ground, Taunton','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (14,'INDIA','AUSTRALIA','2019-06-09 15:00:00','The Oval, London','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (15,'SOUTH AFRICA','WEST INDIES','2019-06-10 15:00:00','The Ageas Bowl, Southampton','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (16,'BANGLADESH','SRI LANKA','2019-06-11 15:00:00','Bristol County Ground, Bristol','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (17,'AUSTRALIA','PAKISTAN','2019-06-12 15:00:00','The County Ground, Taunton','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (18,'INDIA','NEW ZEALAND','2019-06-13 15:00:00','Trent Bridge, Nottingham','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (19,'ENGLAND','WEST INDIES','2019-06-14 15:00:00','The Ageas Bowl, Southampton','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (20,'SRI LANKA','AUSTRALIA','2019-06-15 15:00:00','The Oval, London','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (21,'SOUTH AFRICA','AFGHANISTAN','2019-06-15 18:00:00','Cardiff Wales Stadium, Cardiff','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (22,'INDIA','PAKISTAN','2019-06-16 15:00:00','Old Trafford, Manchester','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (23,'WEST INDIES','BANGLADESH','2019-06-17 15:00:00','The County Ground, Taunton','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (24,'ENGLAND','AFGHANISTAN','2019-06-18 15:00:00','Old Trafford, Manchester','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (25,'NEW ZEALAND','SOUTH AFRICA','2019-06-19 15:00:00','Edgbaston, Birmingham','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (26,'AUSTRALIA','BANGLADESH','2019-06-20 15:00:00','Trent Bridge, Nottingham','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (27,'ENGLAND','SRI LANKA','2019-06-21 15:00:00','Headingley, Leeds','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (28,'INDIA','AFGHANISTAN','2019-06-22 15:00:00','The Ageas Bowl, Southampton','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (29,'WEST INDIES','NEW ZEALAND','2019-06-22 18:00:00','Old Trafford, Manchester','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (30,'PAKISTAN','SOUTH AFRICA','2019-06-23 15:00:00','Lord\'s Cricket Ground, London','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (31,'BANGLADESH','AFGHANISTAN','2019-06-24 15:00:00','The Ageas Bowl, Southampton','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (32,'ENGLAND','AUSTRALIA','2019-06-25 15:00:00','Lord\'s Cricket Ground, London','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (33,'NEW ZEALAND','PAKISTAN','2019-06-26 15:00:00','Edgbaston, Birmingham','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (34,'WEST INDIES','INDIA','2019-06-27 15:00:00','Old Trafford, Manchester','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (35,'SRI LANKA','SOUTH AFRICA','2019-06-28 15:00:00','Emirates Riverside, Chester-le-Street','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (36,'PAKISTAN','AFGHANISTAN','2019-06-29 15:00:00','Headingley, Leeds','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (37,'NEW ZEALAND','AUSTRALIA','2019-06-29 18:00:00','Lord\'s Cricket Ground, London','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (38,'ENGLAND','INDIA','2019-06-30 15:00:00','Edgbaston, Birmingham','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (39,'SRI LANKA','WEST INDIES','2019-07-01 15:00:00','Emirates Riverside, Chester-le-Street','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (40,'BANGLADESH','INDIA','2019-07-02 15:00:00','Edgbaston, Birmingham','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (41,'ENGLAND','NEW ZEALAND','2019-07-03 15:00:00','Emirates Riverside, Chester-le-Street','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (42,'AFGHANISTAN','WEST INDIES','2019-07-04 15:00:00','Headingley, Leeds','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (43,'PAKISTAN','BANGLADESH','2019-07-05 15:00:00','Lord\'s Cricket Ground, London','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (44,'SRI LANKA','INDIA','2019-07-06 15:00:00','Headingley, Leeds','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (45,'AUSTRALIA','SOUTH AFRICA','2019-07-06 18:00:00','Old Trafford, Manchester','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (46,'1ST','4TH','2019-07-09 15:00:00','Old Trafford, Manchester','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (47,'2ND','3RD','2019-07-11 15:00:00','Edgbaston, Birmingham','');
INSERT INTO `game` (`match_no`, `team1`, `team2`, `match_date`, `venue`, `match_result`) VALUES (48,'TBC','TBC','2019-07-14 15:00:00','Lord\'s Cricket Ground, London','');


-- Stored PROCEDURE starts

DROP PROCEDURE IF EXISTS cricapp.getMatchByVenue;
DELIMITER //
CREATE PROCEDURE cricapp.`getMatchByVenue`(IN venueIn varchar(255), IN teamNo2 varchar(255))
BEGIN
  select * FROM game where venue=venueIn and team2=teamNo2;
END;



-- CALL getMatchByVenue('The Oval, London','AUSTRALIA');

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