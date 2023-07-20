/*
MySQL Backup
Database: test
Backup Time: 2023-07-20 16:24:24
*/

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `test`.`attendance`;
DROP TABLE IF EXISTS `test`.`departments`;
DROP TABLE IF EXISTS `test`.`employees`;
DROP TABLE IF EXISTS `test`.`salaries`;
DROP TABLE IF EXISTS `test`.`text`;
DROP TABLE IF EXISTS `test`.`user`;
CREATE TABLE `attendance` (
  `EmployeeID` int NOT NULL,
  `Date` date DEFAULT NULL,
  `StartTime` time DEFAULT NULL,
  `EndTime` time DEFAULT NULL,
  KEY `attendance_ibfk_1` (`EmployeeID`),
  CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `employees` (`EmployeeID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `departments` (
  `DepartmentID` int NOT NULL AUTO_INCREMENT,
  `DepartmentName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`DepartmentID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `employees` (
  `EmployeeID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  `DepartmentID` int DEFAULT NULL,
  `Position` varchar(50) DEFAULT NULL,
  `HireDate` date DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`),
  KEY `DepartmentID` (`DepartmentID`),
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`DepartmentID`) REFERENCES `departments` (`DepartmentID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `salaries` (
  `EmployeeID` int NOT NULL,
  `Salary` decimal(10,2) DEFAULT NULL,
  `EffectiveDate` date DEFAULT NULL,
  KEY `salaries_ibfk_1` (`EmployeeID`),
  CONSTRAINT `salaries_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `employees` (`EmployeeID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `text` (
  `account` varchar(255) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `user` (
  `account` varchar(255) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
BEGIN;
LOCK TABLES `test`.`attendance` WRITE;
DELETE FROM `test`.`attendance`;
INSERT INTO `test`.`attendance` (`EmployeeID`,`Date`,`StartTime`,`EndTime`) VALUES (12, '2017-06-27', '14:25:25', '09:30:29'),(20, '2017-10-02', '13:38:48', '17:13:26'),(6, '2004-12-09', '15:46:37', '12:05:53'),(6, '2002-03-27', '14:07:45', '13:37:17'),(4, '2005-09-22', '09:23:35', '12:42:02'),(15, '2019-12-05', '14:27:45', '15:13:33'),(12, '2018-03-23', '12:37:40', '10:31:23'),(1, '2010-02-09', '16:09:21', '13:09:29'),(1, '2018-11-16', '14:32:09', '10:34:11'),(12, '2012-06-08', '14:24:06', '15:44:32');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `test`.`departments` WRITE;
DELETE FROM `test`.`departments`;
INSERT INTO `test`.`departments` (`DepartmentID`,`DepartmentName`) VALUES (1, '人力资源部'),(2, '采购部'),(3, '工程部'),(4, '生产部'),(5, '人力资源部');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `test`.`employees` WRITE;
DELETE FROM `test`.`employees`;
INSERT INTO `test`.`employees` (`EmployeeID`,`Name`,`DepartmentID`,`Position`,`HireDate`) VALUES (1, 'AA', 3, '2222', '2023-07-13'),(2, 'AAw', 1, '软件开发员', '2006-09-06'),(3, 'wAA', 1, '制片人', '2014-09-21'),(4, '测试用', 5, '医生', '2020-10-15'),(5, '改名', 1, '作家', '2002-12-20'),(6, '邹杰宏', 4, '运营经理', '2004-09-25'),(7, 'Shawn Nichols', 1, '信息安全分析师', '2007-04-29'),(8, '张秀英', 5, '医生', '2012-03-11'),(9, 'Cynthia Bennett', 1, '审计师', '2001-04-27'),(10, 'Stephanie Gray', 4, '客戶協調員', '2019-03-26'),(11, '武杰宏', 1, '园艺家', '2019-01-10'),(12, 'Karen Cook', 2, '人力资源经理', '2004-08-15'),(13, '于晓明', 3, '饲养员', '2011-12-18'),(14, '戴詩涵', 2, '化妆师', '2007-08-19'),(15, '汤璐', 1, '首席运营官', '2002-03-15'),(16, 'Florence Wood', 1, '理发师', '2001-08-23'),(17, 'Glenn Meyer', 5, '市场总监', '2022-07-11'),(18, 'Christine Herrera', 4, '演员', '2005-03-19'),(19, '苏宇宁', 5, '网页开发人员', '2015-06-18'),(20, 'Tina Vargas', 1, '生物化学家', '2019-10-13'),(23, '测试用', 5, '医生', '2020-10-15'),(24, 'John Doe', 1, 'Manager', '2023-07-20');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `test`.`salaries` WRITE;
DELETE FROM `test`.`salaries`;
INSERT INTO `test`.`salaries` (`EmployeeID`,`Salary`,`EffectiveDate`) VALUES (14, 11554.71, '2013-10-14'),(6, 11789.71, '2013-09-03'),(4, 19872.40, '2007-12-26'),(16, 13823.46, '2011-03-24'),(13, 11812.77, '2020-03-20'),(17, 10532.02, '2000-03-12'),(7, 12055.17, '2021-05-14'),(3, 10636.81, '2015-03-23'),(15, 17989.72, '2004-11-29'),(12, 17159.23, '2010-06-06'),(20, 19148.76, '2017-03-15'),(18, 14776.67, '2003-10-14'),(11, 11004.91, '2002-05-23'),(1, 15111.18, '2010-02-14'),(9, 11306.29, '2000-06-14'),(8, 13827.53, '2021-08-12'),(19, 14340.09, '2008-06-24'),(2, 10065.80, '2016-05-25'),(10, 19839.40, '2017-02-02');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `test`.`text` WRITE;
DELETE FROM `test`.`text`;
INSERT INTO `test`.`text` (`account`,`id`,`text`) VALUES ('123456', 1, '你好'),(NULL, 2, NULL);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `test`.`user` WRITE;
DELETE FROM `test`.`user`;
INSERT INTO `test`.`user` (`account`,`password`,`id`) VALUES ('123456', '123456', 1),('aaaa', '123456', 3),('aabbb', NULL, 4),('78910', '123456', 5);
UNLOCK TABLES;
COMMIT;
