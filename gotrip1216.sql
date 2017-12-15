-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: gotrip
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authority` varchar(15) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `users_id_idx` (`username`),
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (1,'ROLE_ADMIN','ericgj9484'),(6,'ROLE_USER','fuck');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hot`
--

DROP TABLE IF EXISTS `hot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hot` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `address` varchar(30) NOT NULL,
  `photo` varchar(15) NOT NULL,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hot`
--

LOCK TABLES `hot` WRITE;
/*!40000 ALTER TABLE `hot` DISABLE KEYS */;
INSERT INTO `hot` VALUES (1,'蘭嶼','台東縣','蘭嶼.jpg','Good1-2'),(2,'正興咖啡館','台南市','正興咖啡館.jpg','Good2'),(3,'伯朗大道','台東縣','伯朗大道.jpg','Good3'),(4,'奇美博物館','台南市','奇美博物館.jpg','Good4'),(5,'西子灣','高雄市','西子灣.jpg','Good5'),(6,'清水斷崖','花蓮縣','花蓮遊.jpg','Good6');
/*!40000 ALTER TABLE `hot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spot`
--

DROP TABLE IF EXISTS `spot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `day` int(11) DEFAULT NULL,
  `duration` varchar(15) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  `tour_id` int(11) DEFAULT NULL,
  `place_id` varchar(100) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tour_id_idx` (`tour_id`),
  CONSTRAINT `tour_id` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spot`
--

LOCK TABLES `spot` WRITE;
/*!40000 ALTER TABLE `spot` DISABLE KEYS */;
INSERT INTO `spot` VALUES (14,'台灣大學',4,'02:00','nothing',1,'ChIJqS4y_ompQjQRZn8d7gQEdSE',2),(15,'輔仁大學',4,'00:20','nothing',1,'ChIJDSNgn8KnQjQRPcD80cC8sZw',1),(16,'迪化街',5,'00:50','nothing',1,'ChIJKZRnrjmpQjQRNON-qkJW7Lo',1),(17,'八里',5,'00:50','nothing',1,'ChIJW8H_X7ilQjQRCbe4IB3750M',2),(18,'深坑老街',6,'01:20','nothing',1,'ChIJX571WcSqQjQR_b3GNcTpwqs',3),(24,'陶花園',7,'08:70','nothing',1,'ChIJW_GTjeinQjQRlDJT3cYbfJo',1),(25,'輔大醫院',7,'00:01','nothing',1,'ChIJf7teBcOnQjQR8BeYpp3w9yU',2),(26,'新莊costco',8,'00:20','nothing',1,'ChIJTQe4e-WnQjQROY83kiPDVO4',2),(27,'大安公園',8,'00:20','nothing',1,'ChIJnzZlOoCpQjQRH-WG9egh-2E',1),(31,'心園巧瑋鬆餅屋',3,NULL,NULL,1,'ChIJAQAAPOinQjQRNgBnybmQWKk',1),(32,'輔大小夜市',3,NULL,NULL,1,'ChIJidHgFMKnQjQRILnsqzu9CK0',3),(33,'新莊文昌祠',6,NULL,NULL,1,'ChIJjcilT3KoQjQR0w-W0FqovOU',1),(34,'林本源園邸',6,NULL,NULL,1,'ChIJ-bWxkQKoQjQRclXL_gr5kbg',2),(35,'靴子義大利餐館 輔大店',3,NULL,NULL,1,'ChIJT9gnvuinQjQRQEYgbE--X5c',2),(39,'台鐵台北車站',2,NULL,NULL,1,'ChIJCZEzfnKpQjQRy75KOs4xSsM',3),(40,'二二八和平公園',2,NULL,NULL,1,'ChIJKX1kH3WpQjQRcyy76pvtt-s',2),(42,'淡水紅毛城',2,NULL,NULL,1,'ChIJqZ4mwE-lQjQR3d6CJEWh2O8',1),(45,'淡水老街',1,NULL,NULL,1,'ChIJOboJaVqlQjQRxwuIT4kvkKQ',3),(46,'西門町',1,NULL,NULL,1,'ChIJKyaoGAmpQjQR99RS3zrx9Ms',1),(47,'寧夏夜市',1,NULL,NULL,1,'ChIJxUXPVmupQjQRtBB6oj-S5qI',2);
/*!40000 ALTER TABLE `spot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session`
--

DROP TABLE IF EXISTS `spring_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spring_session` (
  `SESSION_ID` char(36) NOT NULL DEFAULT '',
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`SESSION_ID`),
  KEY `SPRING_SESSION_IX1` (`LAST_ACCESS_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session`
--

LOCK TABLES `spring_session` WRITE;
/*!40000 ALTER TABLE `spring_session` DISABLE KEYS */;
INSERT INTO `spring_session` VALUES ('0e91c488-9eb8-4992-83b9-d00bc037f825',1513360755951,1513360937329,1800,NULL);
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session_attributes`
--

DROP TABLE IF EXISTS `spring_session_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spring_session_attributes` (
  `SESSION_ID` char(36) NOT NULL DEFAULT '',
  `ATTRIBUTE_NAME` varchar(200) NOT NULL DEFAULT '',
  `ATTRIBUTE_BYTES` blob,
  PRIMARY KEY (`SESSION_ID`,`ATTRIBUTE_NAME`),
  KEY `SPRING_SESSION_ATTRIBUTES_IX1` (`SESSION_ID`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_ID`) REFERENCES `spring_session` (`SESSION_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session_attributes`
--

LOCK TABLES `spring_session_attributes` WRITE;
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
INSERT INTO `spring_session_attributes` VALUES ('0e91c488-9eb8-4992-83b9-d00bc037f825','org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN','�\�\0sr\06org.springframework.security.web.csrf.DefaultCsrfTokenZ\�\�/��\�\0L\0\nheaderNamet\0Ljava/lang/String;L\0\rparameterNameq\0~\0L\0tokenq\0~\0xpt\0X-CSRF-TOKENt\0_csrft\0$1a0165d3-2d0b-4f53-b229-f0e65dea8888'),('0e91c488-9eb8-4992-83b9-d00bc037f825','SPRING_SECURITY_SAVED_REQUEST','�\�\0sr\0Aorg.springframework.security.web.savedrequest.DefaultSavedRequestX��)&u�n\0I\0\nserverPortL\0contextPatht\0Ljava/lang/String;L\0cookiest\0Ljava/util/ArrayList;L\0headerst\0Ljava/util/Map;L\0localesq\0~\0L\0methodq\0~\0L\0\nparametersq\0~\0L\0pathInfoq\0~\0L\0queryStringq\0~\0L\0\nrequestURIq\0~\0L\0\nrequestURLq\0~\0L\0schemeq\0~\0L\0\nserverNameq\0~\0L\0servletPathq\0~\0xp\0\0�t\0\0sr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\09org.springframework.security.web.savedrequest.SavedCookie@+����f\0I\0maxAgeZ\0secureI\0versionL\0commentq\0~\0L\0domainq\0~\0L\0nameq\0~\0L\0pathq\0~\0L\0valueq\0~\0xp����\0\0\0\0\0ppt\0SESSIONpt\0$0e91c488-9eb8-4992-83b9-d00bc037f825xsr\0java.util.TreeMap��>-%j\�\0L\0\ncomparatort\0Ljava/util/Comparator;xpsr\0*java.lang.String$CaseInsensitiveComparatorw\\}\\P\�\�\0\0xpw\0\0\0	t\0acceptsq\0~\0\0\0\0w\0\0\0t\0Utext/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8xt\0accept-encodingsq\0~\0\0\0\0w\0\0\0t\0gzip, deflate, brxt\0accept-languagesq\0~\0\0\0\0w\0\0\0t\0#zh-TW,zh;q=0.9,en-US;q=0.8,en;q=0.7xt\0\nconnectionsq\0~\0\0\0\0w\0\0\0t\0\nkeep-alivext\0cookiesq\0~\0\0\0\0w\0\0\0t\0,SESSION=0e91c488-9eb8-4992-83b9-d00bc037f825xt\0hostsq\0~\0\0\0\0w\0\0\0t\0localhost:8080xt\0referersq\0~\0\0\0\0w\0\0\0t\0http://localhost:8080/loginxt\0upgrade-insecure-requestssq\0~\0\0\0\0w\0\0\0t\01xt\0\nuser-agentsq\0~\0\0\0\0w\0\0\0t\0rMozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36xxsq\0~\0\0\0\0w\0\0\0sr\0java.util.Locale~�`�0�\�\0I\0hashcodeL\0countryq\0~\0L\0\nextensionsq\0~\0L\0languageq\0~\0L\0scriptq\0~\0L\0variantq\0~\0xp����t\0TWq\0~\0t\0zhq\0~\0q\0~\0xsq\0~\0-����q\0~\0q\0~\0q\0~\00q\0~\0q\0~\0xsq\0~\0-����t\0USq\0~\0t\0enq\0~\0q\0~\0xsq\0~\0-����q\0~\0q\0~\0q\0~\04q\0~\0q\0~\0xxt\0GETsq\0~\0pw\0\0\0\0xppt\0/user/spotSearcht\0%http://localhost:8080/user/spotSearcht\0httpt\0	localhostt\0/user/spotSearch');
/*!40000 ALTER TABLE `spring_session_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour`
--

DROP TABLE IF EXISTS `tour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `begin_date` date NOT NULL,
  `days` int(2) NOT NULL,
  `note` varchar(100) DEFAULT NULL,
  `usernametour` varchar(20) DEFAULT NULL,
  `photo` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `username_idx` (`usernametour`),
  CONSTRAINT `usernameTour` FOREIGN KEY (`usernametour`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour`
--

LOCK TABLES `tour` WRITE;
/*!40000 ALTER TABLE `tour` DISABLE KEYS */;
INSERT INTO `tour` VALUES (1,'花蓮之旅','2017-12-22',8,'Good',NULL,'花蓮遊.jpg'),(3,'苗栗之旅','2017-12-13',2,'123',NULL,'苗栗遊.jpg');
/*!40000 ALTER TABLE `tour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `name` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phone` varchar(15) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('ericgj9484','1234',1,'張宇辰','ericgj9484@gmail.com','0905263673'),('fuck','9487',1,'某某某','aaa@gmail.com','0910123456');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-16  2:06:38
