drop database if exists optigrader;
create database optigrader;
use optigrader;


CREATE TABLE users
 (
 nid VARCHAR(8) NOT NULL,		# NID, must be unique (PK)
 user_mode BIT(1) NOT NULL,		# 0=student, 1=teacher
 current_test VARCHAR(50) NOT NULL, 	# active test name
 returned_score TINYINT unsigned NULL,	# score to show the student, [0-255]
 returned_answers VARCHAR(500) NULL,	# answers to show the student once window closes
 dateCreated DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, # the rest is account info
 firstName VARCHAR(50) NULL, 		# leaving all these optional for now
 lastName VARCHAR(50) NULL,
 login VARCHAR(254) NULL,
 password CHAR(64) NULL,
 PRIMARY KEY (nid)
 );

CREATE TABLE sessions
 (
 nid VARCHAR(8) NOT NULL,	# FK, traces back to user's NID
 ip VARCHAR(45) NOT NULL,	# PK since this must be unique, supports IPv6 max len
 token CHAR(64) NOT NULL,	# sessionID
 PRIMARY KEY (ip),
 FOREIGN KEY (nid) REFERENCES users(nid)
 );

CREATE TABLE tests
 (
 testName VARCHAR(50) NOT NULL,		# must be unique (PK)
 code CHAR(4) NOT NULL,			# code that will sync the student with the test
 solutions VARCHAR(500) NOT NULL,	# string representing solutions to this test
 testOwner VARCHAR(8) NOT NULL,		# nid of teacher
 expiration DATETIME NOT NULL,		# datetime that test closes
 PRIMARY KEY (testName),
 FOREIGN KEY (testOwner) REFERENCES users(nid) # testOwner is user's NID
 );
