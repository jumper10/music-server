
CREATE DATABASE IF NOT EXISTS musics DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS Smoke (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    message VARCHAR(100) NOT NULL,
    createDateTime datetime default  CURRENT_TIMESTAMP,
    lastUpdateTime datetime default  CURRENT_TIMESTAMP
);

insert into Smoke(message) values("hello smoke!");

CREATE TABLE IF NOT EXISTS Users (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userName VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    enabled TINYINT NOT NULL DEFAULT 1,
    creatorId BIGINT,
    createDateTime datetime default  CURRENT_TIMESTAMP,
    lastUpdateTime datetime default  CURRENT_TIMESTAMP
);
insert into Users(userName,password) values("admin","123456");
insert into Users(userName,password) values("jumper","123456");

CREATE TABLE IF NOT EXISTS Roles (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    roleName VARCHAR(20) NOT NULL,
    displayName VARCHAR(20) NOT NULL,
    enabled TINYINT NOT NULL DEFAULT 1,
    creatorId BIGINT,
    createDateTime datetime default  CURRENT_TIMESTAMP,
    lastUpdateTime datetime default  CURRENT_TIMESTAMP
);
insert into Roles(roleName,displayName) values("ROLE_ADMIN","管理员");
insert into Roles(roleName,displayName) values("ROLE_USER","用户");

CREATE TABLE IF NOT EXISTS User_Roles (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId BIGINT NOT NULL,
    roleId BIGINT NOT NULL,
    createDateTime datetime default  CURRENT_TIMESTAMP,
    lastUpdateTime datetime default  CURRENT_TIMESTAMP
);

insert into User_Roles(userId,roleId) select u.id,r.id from Users u,Roles r where u.userName='admin' and r.roleName='ROLE_ADMIN';
insert into User_Roles(userId,roleId) select u.id,r.id from Users u,Roles r where u.userName='jumper' and r.roleName='ROLE_USER';


