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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (8,'ROLE_ADMIN','admin1234'),(9,'ROLE_USER','user1234'),(21,'ROLE_USER','fjuschool'),(22,'ROLE_USER','gotrip'),(23,'ROLE_ADMIN','admin4567'),(24,'ROLE_ADMIN','admin6789'),(25,'ROLE_USER','user4567'),(26,'ROLE_USER','user7891');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collection`
--

DROP TABLE IF EXISTS `collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  `place_id` varchar(100) DEFAULT NULL,
  `usernamecol` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usernameCol_idx` (`usernamecol`),
  CONSTRAINT `usernameCol` FOREIGN KEY (`usernamecol`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collection`
--

LOCK TABLES `collection` WRITE;
/*!40000 ALTER TABLE `collection` DISABLE KEYS */;
INSERT INTO `collection` VALUES (19,'輔大文園餐廳','242台灣新北市新莊區中正路510號','ChIJyYd6ZOinQjQRduCCCGmCHs4','user1234'),(23,'我家牛排泰山店','243台灣新北市泰山區台麗街69號','ChIJSwVuIcCnQjQREkCcb8FE5Q4','user1234'),(30,'李石樵故居懋德居','243台灣新北市泰山區民生路166號','ChIJP2I7m9ynQjQRxDdweCxol7o','user1234'),(31,'米里 Pasta','242台灣新北市新莊區民安路16號','ChIJ31G3Uu2nQjQRVDLoICg4qDU','user1234');
/*!40000 ALTER TABLE `collection` ENABLE KEYS */;
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
  `description` varchar(400) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hot`
--

LOCK TABLES `hot` WRITE;
/*!40000 ALTER TABLE `hot` DISABLE KEYS */;
INSERT INTO `hot` VALUES (1,'澎湖','澎湖縣','澎湖.jpg','澎湖群島位於中國大陸與台灣島之間的臺灣海峽上，由90座大小不等之島嶼組成。近年，因澎湖群島特殊的玄武岩地質景觀，國際地質專家建議爭取列為世界遺產或設置為國家地質公園，引發國內地質學者及文史工作者對澎湖群島64座島嶼、總面積126.864平方公里資料的正確性提出質疑。上述資料係日治時期由日本政府於大正5年（1916年）所測得。官方文獻紀錄及資料之引述，長久以來均以此為依據。澎湖群島地勢為起伏平緩、山勢低矮的丘陵地，澎湖本島山勢最高的拱北山也只有海拔52公尺高，澎湖外島最高的望安鄉大貓嶼則有海拔79公尺。著名的有湖西鄉的拱北山、奎壁山以及西嶼鄉的牛心山。'),(2,'正興咖啡館','台南市','正興咖啡館.jpg','台南正興街，是現在遊客來台南不能錯過的一條街，也是台南老屋欣力盎盎拔茁的一條街。從始祖佳佳西市場旅店開始，往前走，一路會經過市定古蹟西市場、布萊恩紅茶、謝宅作品 IORI Tea House、巷子裡的小滿食堂、假日人滿為患的蜷尾家散步甜食、開了八十年的泰成水果店，再往前一點還會看到落腳在台南的彩虹來了實體店鋪。 而就在布萊恩紅茶斜後方、IORI Tea House旁邊，一棟屋齡超過60年的老宅換上藍色的新衣在陽光下昂然而立，這裡，就是正興咖啡館，正興街最閃耀的一顆藍色寶石──歡迎妳來這裡一品咖啡與人文的老式浪漫。'),(3,'草悟道','台中市','勤美.jpg','台中是一個充滿活力的城市，每次來都會遇到許多不同的驚喜，特別是勤美誠品綠園道一帶，每到周末假日，總會有許多不同的市集，素人老闆們認真展示著自己的作品，讓人有尋寶的樂趣。\r\n\r\n離開綠光計劃，沿著草悟道漫步前行，來到最近的打卡熱點「綠圈圈藝術季」，雖然有些作品已經撤走了，不過在勤美術館的草地上，還留有部分像是巨大的高爾夫球、酷炫保齡球道、可愛臘腸狗單輪車以及五彩繽紛的籃球場、游泳池等，雖然天空中飄著小雨，不過大家還是玩得很開心呢!!\r\n\r\n市民大道週邊的小巷弄裡，有不少餐廳、咖啡館、冰淇淋店、日式雜貨小舖等，擁有整片綠色大草皮的市民廣場，成了附近的居民和遊客的遊樂場，也有一些攤販在賣東西，整個商圈熱鬧又充滿悠閒的氣息。'),(4,'日月潭','南投縣','日月潭.jpg','日月潭位於南投縣魚池鄉，是台灣最大的淡水湖泊，也是最美麗的高山湖泊，海拔高度為748公尺。日月潭湖泊東側水域形狀如日輪，西側水域形狀細長如月鉤，因而得名。日月潭風景的美，從黎明到黃昏，從春夏到秋冬，無論風和日麗或是煙雨迷濛，都盪漾著綺麗的風光，有令人百看不厭的美景。而日月潭有幾個景點大受歡迎，如；潭中浮島、文武廟、孔雀園、水挖仔步道、伊達邵、玄奘寺、慈恩塔、玄光寺等都是美不盛收的最佳景點。\r\n日月潭舊稱水沙連，全潭以拉魯島（原名光華島）為界，南形如月弧，北形如日輪，為台灣的八景之一'),(5,'西子灣','高雄市','西子灣.jpg','西子灣位於高雄市西側，壽山西南端山麓下，北瀕萬壽山，南臨旗津半島，為一黃澄碧藍的海水浴場，是一處以夕陽美景及天然礁石聞名的灣澳。距市中心車程約20分鐘，依山臨海，風景宜人，每當夜幕低垂，將大海點綴的瑰麗可人，漁船燈火閃爍其間；西子灣的夕陽是高雄八景之一，海天一色的美景，美不勝收，黃昏時分，常可見一對對情侶在此互道情愫，更有詩情畫意的情境。\r\n\r\n中山大學就座落於西子灣風景區內，倚著壽山，傍西子灣，校園內草木蓊鬱、花團錦簇，校舍美輪美奐，是一座美麗的大學。由中山大學左方的旅客服務中心即可進入西子灣海水浴場，夏日總是有成群結隊的遊客到此戲潮、游泳，熱鬧非凡。'),(6,'清水斷崖','花蓮縣','清水斷崖.jpg','清水斷崖位於台灣花蓮縣秀林鄉的海岸斷崖，是蘇花公路和仁至崇德路段著名的旅遊景點。台灣戰後，台灣省政府將其列為台灣八景之一。 約九百萬年前歐亞板塊與菲律賓板塊發生碰撞，而且不斷隆起，加上豐沛的雨水，上覆的岩層受風化侵蝕作用剝失，深處的大理岩和片麻岩於是逐漸抬升露出地表。這些岩石的岩性均是緻密、堅硬而不易崩落，故能維持陡峭壁立的山壁。加上台灣東部的地殼隆升快速，造成在短距離內即急速拔升的情形。再受到強烈的海蝕作用，坡度極陡，幾近垂直。這便是台灣八景之一的清水斷崖形成的原因。');
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
) ENGINE=InnoDB AUTO_INCREMENT=287 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spot`
--

LOCK TABLES `spot` WRITE;
/*!40000 ALTER TABLE `spot` DISABLE KEYS */;
INSERT INTO `spot` VALUES (176,'七星潭',1,NULL,NULL,13,'ChIJ3aMda6ogZjQRa9Ib1W1eDpc',4),(177,'溪畔公園',1,NULL,NULL,13,'ChIJWxvcpWqeaDQRVDBPQn5dLJ4',5),(178,'瑞穗牧場',1,NULL,NULL,13,'ChIJR32Q_HJGbzQRRL_0TxjNtQA',2),(179,'鯉魚潭',1,NULL,NULL,13,'ChIJ3eVHa7SjaDQR1K-MKuWZ5S4',3),(180,'遠雄海洋公園',1,NULL,NULL,13,'ChIJ-Qdkbe2gaDQRCtLcEau0bi8',1),(197,'鯨魚洞',1,NULL,NULL,15,'ChIJdTIsxhtcbDQRRRzUOAC7O0A',1),(198,'中屯風車',1,NULL,NULL,15,'ChIJWUiERLBcbDQROWlRIouW4YA',2),(199,'七美雙心石滬',1,NULL,NULL,15,'ChIJVVVVVemnbTQRCLB89S5YC-A',3),(200,'內垵遊憩區',1,NULL,NULL,15,'ChIJ6-XKT-9ZbDQRU_fceX0sjEg',4),(201,'西嶼西臺',1,NULL,NULL,15,'ChIJucgezuBZbDQRK89JeSpSCv8',5),(205,'三仙塔',3,NULL,NULL,15,'ChIJK1D4DNhZbDQRdtcQhEcN4nc',1),(206,'山水吼洞',3,NULL,NULL,15,'ChIJpcVe3jtQbDQRyHBYn9Ukmkc',2),(226,'西門町',1,NULL,NULL,14,'ChIJKyaoGAmpQjQR99RS3zrx9Ms',1),(227,'淡水老街',1,NULL,NULL,14,'ChIJOboJaVqlQjQRxwuIT4kvkKQ',3),(228,'公館夜市',1,NULL,NULL,14,'ChIJVVVVVRWsQjQRwQH6WTfTfPA',4),(229,'以諾美食館',1,NULL,NULL,14,'ChIJdfmnVOinQjQR4X5MOZVlfss',5),(244,'輔大文園餐廳',1,NULL,NULL,14,'ChIJyYd6ZOinQjQRduCCCGmCHs4',2),(255,'國立故宮博物院',2,NULL,NULL,14,'ChIJfUpAzTqsQjQRwQl6ORhwbV0',1),(256,'九份',2,NULL,NULL,14,'ChIJeWdTBRhFXTQRhDuVjIM6uCk',2),(257,'黃金瀑布',2,NULL,NULL,14,'ChIJV6Smh_xEXTQR8GdS0X6lqds',3),(258,'月鯉灣浮潛',2,NULL,NULL,15,'ChIJs1hD1pGnbTQRpXQuVc1fOIo',1),(259,'西嶼東臺',2,NULL,NULL,15,'ChIJGe4D2hxabDQR02YZ2xzW_Io',2),(260,'池西柱狀玄武岩',2,NULL,NULL,15,'ChIJAQAAAMRabDQR7BA6FdCFuMs',3),(261,'後山星月',2,NULL,NULL,13,'ChIJIUY_B7amaDQRuWe2Nc8zxOA',1),(262,'綠野鄉渡假村',2,NULL,NULL,13,'ChIJbRzlEr2jaDQRRFLkqY1e3O4',2),(263,'吉野神社遺址',3,NULL,NULL,13,'ChIJXe3PQHefaDQReW886BImBiA',1),(264,'楓林步道',3,NULL,NULL,13,'ChIJAQAAAFefaDQRarDrbHq_ATY',2);
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
INSERT INTO `spring_session` VALUES ('0a0540fc-3a8f-4fe8-b482-fb3e6da03617',1515255368317,1515255379617,1800,NULL);
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
INSERT INTO `spring_session_attributes` VALUES ('0a0540fc-3a8f-4fe8-b482-fb3e6da03617','SPRING_SECURITY_SAVED_REQUEST','�\�\0sr\0Aorg.springframework.security.web.savedrequest.DefaultSavedRequestX��)&u�n\0I\0\nserverPortL\0contextPatht\0Ljava/lang/String;L\0cookiest\0Ljava/util/ArrayList;L\0headerst\0Ljava/util/Map;L\0localesq\0~\0L\0methodq\0~\0L\0\nparametersq\0~\0L\0pathInfoq\0~\0L\0queryStringq\0~\0L\0\nrequestURIq\0~\0L\0\nrequestURLq\0~\0L\0schemeq\0~\0L\0\nserverNameq\0~\0L\0servletPathq\0~\0xp\0\0�t\0\0sr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\09org.springframework.security.web.savedrequest.SavedCookie@+����f\0I\0maxAgeZ\0secureI\0versionL\0commentq\0~\0L\0domainq\0~\0L\0nameq\0~\0L\0pathq\0~\0L\0valueq\0~\0xp����\0\0\0\0\0ppt\0SESSIONpt\0$0a0540fc-3a8f-4fe8-b482-fb3e6da03617xsr\0java.util.TreeMap��>-%j\�\0L\0\ncomparatort\0Ljava/util/Comparator;xpsr\0*java.lang.String$CaseInsensitiveComparatorw\\}\\P\�\�\0\0xpw\0\0\0	t\0acceptsq\0~\0\0\0\0w\0\0\0t\0Utext/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8xt\0accept-encodingsq\0~\0\0\0\0w\0\0\0t\0gzip, deflate, brxt\0accept-languagesq\0~\0\0\0\0w\0\0\0t\0#zh-TW,zh;q=0.9,en-US;q=0.8,en;q=0.7xt\0\nconnectionsq\0~\0\0\0\0w\0\0\0t\0\nkeep-alivext\0cookiesq\0~\0\0\0\0w\0\0\0t\0,SESSION=0a0540fc-3a8f-4fe8-b482-fb3e6da03617xt\0hostsq\0~\0\0\0\0w\0\0\0t\0localhost:8080xt\0referersq\0~\0\0\0\0w\0\0\0t\0http://localhost:8080/loginxt\0upgrade-insecure-requestssq\0~\0\0\0\0w\0\0\0t\01xt\0\nuser-agentsq\0~\0\0\0\0w\0\0\0t\0sMozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36xxsq\0~\0\0\0\0w\0\0\0sr\0java.util.Locale~�`�0�\�\0I\0hashcodeL\0countryq\0~\0L\0\nextensionsq\0~\0L\0languageq\0~\0L\0scriptq\0~\0L\0variantq\0~\0xp����t\0TWq\0~\0t\0zhq\0~\0q\0~\0xsq\0~\0-����q\0~\0q\0~\0q\0~\00q\0~\0q\0~\0xsq\0~\0-����t\0USq\0~\0t\0enq\0~\0q\0~\0xsq\0~\0-����q\0~\0q\0~\0q\0~\04q\0~\0q\0~\0xxt\0GETsq\0~\0pw\0\0\0\0xppt\0/user/tourRetrieveAllt\0*http://localhost:8080/user/tourRetrieveAllt\0httpt\0	localhostt\0/user/tourRetrieveAll');
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour`
--

LOCK TABLES `tour` WRITE;
/*!40000 ALTER TABLE `tour` DISABLE KEYS */;
INSERT INTO `tour` VALUES (13,'花蓮自由行','2017-12-25',3,'水、手機','user1234','花蓮遊.jpg'),(14,'台北趴趴走','2017-12-18',2,'車票、衣物','user1234','台北101.jpg'),(15,'澎湖之旅','2018-01-01',3,'船票、錢包','user1234','澎湖行.jpg');
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
INSERT INTO `users` VALUES ('admin1234','1234',1,'張宇辰','ericgj1027@gmail.com','0910456789'),('admin4567','4567',1,'admin2','jasdfk@gmail.com','0912345677'),('admin6789','6789',1,'admin3','kjlwer@yahoo.com.tw','0912353974'),('fjuschool','abc123',1,'王曉明','school@gmail.com','0912345678'),('gotrip','asd123',1,'小華','qwer@yahoo.com.tw','0945369245'),('user1234','1234',1,'cow','ericgj1258@gmail.com','0910234564'),('user4567','4657',1,'user2','asdfdg@gmail.com','0945612356'),('user7891','7981',1,'user3','ljwler@gmail.com','0945671236');
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

-- Dump completed on 2018-01-07  0:19:55
