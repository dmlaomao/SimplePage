create table blog
(
 id int unsigned not null auto_increment primary key,
 title varchar(100),
 time datetime,
 userID varchar(20),
 content varchar(20000)
);

