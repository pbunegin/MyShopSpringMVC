CREATE DATABASE  IF NOT EXISTS `my_shop_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `my_shop_db`;
-- MySQL dump 10.13  Distrib 8.0.14, for Win64 (x86_64)
--
-- Host: localhost    Database: my_shop_db
-- ------------------------------------------------------
-- Server version	8.0.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `basket`
--

DROP TABLE IF EXISTS `basket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `basket` (
  `order_number` int(11) NOT NULL,
  `product_code` int(11) NOT NULL,
  `quantity` int(11) DEFAULT '1',
  PRIMARY KEY (`order_number`,`product_code`),
  KEY `Basket_fk1` (`product_code`),
  CONSTRAINT `Basket_fk0` FOREIGN KEY (`order_number`) REFERENCES `orders` (`order_number`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Basket_fk1` FOREIGN KEY (`product_code`) REFERENCES `products` (`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basket`
--

LOCK TABLES `basket` WRITE;
/*!40000 ALTER TABLE `basket` DISABLE KEYS */;
INSERT INTO `basket` VALUES (26,44,3),(26,56,6),(26,62,1),(27,44,5),(27,51,3),(27,58,1),(28,18,5),(30,44,3),(30,51,3),(30,58,3);
/*!40000 ALTER TABLE `basket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categories` (
  `category_code` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) NOT NULL,
  PRIMARY KEY (`category_code`),
  UNIQUE KEY `category_name_UNIQUE` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (2,'Видеокарты'),(1,'Процессоры');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feadback`
--

DROP TABLE IF EXISTS `feadback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `feadback` (
  `user_code` int(11) NOT NULL,
  `product_code` int(11) NOT NULL,
  `score` int(11) NOT NULL DEFAULT '5',
  `text` text NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_code`,`product_code`),
  KEY `feadback_fk1` (`product_code`),
  CONSTRAINT `feadback_fk0` FOREIGN KEY (`user_code`) REFERENCES `users` (`user_code`),
  CONSTRAINT `feadback_fk1` FOREIGN KEY (`product_code`) REFERENCES `products` (`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feadback`
--

LOCK TABLES `feadback` WRITE;
/*!40000 ALTER TABLE `feadback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feadback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufacturers`
--

DROP TABLE IF EXISTS `manufacturers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `manufacturers` (
  `maker_code` int(11) NOT NULL AUTO_INCREMENT,
  `maker` varchar(20) NOT NULL,
  `country` varchar(20) NOT NULL,
  PRIMARY KEY (`maker_code`),
  UNIQUE KEY `maker_UNIQUE` (`maker`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturers`
--

LOCK TABLES `manufacturers` WRITE;
/*!40000 ALTER TABLE `manufacturers` DISABLE KEYS */;
INSERT INTO `manufacturers` VALUES (1,'ASUS','China');
/*!40000 ALTER TABLE `manufacturers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders` (
  `order_number` int(11) NOT NULL AUTO_INCREMENT,
  `point_code` int(11) NOT NULL DEFAULT '1',
  `user_code` int(11) NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'created',
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_number`),
  KEY `Order_fk0` (`point_code`),
  KEY `Order_fk1` (`user_code`),
  CONSTRAINT `Order_fk0` FOREIGN KEY (`point_code`) REFERENCES `shop` (`point_code`),
  CONSTRAINT `Order_fk1` FOREIGN KEY (`user_code`) REFERENCES `users` (`user_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,1,'created','2019-02-05 10:51:53'),(2,1,1,'created','2019-02-05 12:56:33'),(13,1,1,'OK','2019-02-05 19:03:49'),(26,1,1,'OK','2019-02-06 11:49:07'),(27,1,1,'OK','2019-02-06 12:32:34'),(28,1,1,'OK','2019-02-06 12:44:23'),(30,1,1,'OK','2019-02-07 06:49:28');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `products` (
  `product_code` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) NOT NULL,
  `price` float NOT NULL,
  `characteristic` varchar(100) NOT NULL,
  `img_url` varchar(50) DEFAULT NULL,
  `category_code` int(11) NOT NULL,
  `maker_code` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`product_code`),
  KEY `Products_fk0` (`category_code`),
  KEY `Products_fk1` (`maker_code`),
  CONSTRAINT `Products_fk0` FOREIGN KEY (`category_code`) REFERENCES `categories` (`category_code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Products_fk1` FOREIGN KEY (`maker_code`) REFERENCES `manufacturers` (`maker_code`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (17,'Intel Celexfbndgron G4920 OEM',9344400,'cash: L3 - 2 МБ;frequency: 2x3200 МГц;','/prodImg/17.jpg?0.556530726287973',1,1),(18,'Intel Celeron G4900T OEM',8900,'cash: L3 - 2 МБ;frequency: 2x2900 МГц;','/prodImg/18.jpg?0.2823946967591472',1,1),(19,'AMD A8-9600 OEM',6800,'cash: L2 - 2 МБ;frequency: 4x3100 МГц;','/prodImg/19.jpg?0.5635847193304097',1,1),(20,'AMD FX-8320E OEM',11000,'cash: L2 - 8 МБ;frequency: 8x3200 МГц;','/prodImg/20.jpg?0.6108416574063595',1,1),(21,'Intel Celeron G1840 OEM',15000,'cash: L3 - 2 МБ;frequency: 2x2800 МГц;','/prodImg/21.jpg?0.34458954096948036',1,1),(22,'AMD FX-4350 OEM',13000,'cash: L2 - 4 МБ;frequency: 4x4200 МГц;','/prodImg/22.jpg?0.722196129372498',1,1),(23,'AMD A8-7680 OEM',15000,'cash: L2 - 2 МБ;frequency: 2x3500 МГц;','/prodImg/23.jpg?0.9158952153119388',1,1),(24,'AMD FX-8300 OEM',11000,'cash: L2 - 8 МБ;frequency: 8x3300 МГц;','/prodImg/24.jpg?0.8777886305304397',1,1),(25,'AMD FX-8320E BOX',14500,'cash: L2 - 8 МБ;frequency: 8x3200 МГц;','/prodImg/25.jpg?0.851942287820104',1,1),(26,'Intel Celeron G4920 BOX',6543,'cash: L3 - 2 МБ;frequency: 2x3200 МГц;','/prodImg/26.jpg?0.5819134175226949',1,1),(27,'Intel Pentium G4400 OEM',11300,'cash: L3 - 3 МБ;frequency: 2x3300 МГц;','/prodImg/27.jpg?0.9328731402986503',1,1),(28,'Intel Pentium Gold G5400 OEM',12400,'cash: L3 - 4 МБ;frequency: 2x3700 МГц;','/prodImg/28.jpg?0.7483669385294035',1,1),(29,'AMD Ryzen 3 1200 OEM',16500,'cash: L2 - 2 МБ;frequency: 4x3100 МГц;','/prodImg/29.jpg?0.1941450603037358',1,1),(30,'AMD Ryzen 3 1200 BOX',14000,'cash: L2 - 2 МБ;frequency: 4x3100 МГц;','/prodImg/30.jpg?0.5036129964804118',1,1),(31,'Intel Pentium G4560 OEM',15500,'cash: L3 - 3 МБ;frequency: 2x3500 МГц;','/prodImg/31.jpg?0.6801432703752704',1,1),(32,'Intel Pentium Gold G5400 BOX',18000,'cash: L3 - 4 МБ;frequency: 2x3700 МГц;','/prodImg/32.jpg?0.9927744505535554',1,1),(33,'Intel Pentium G4400 BOX',19000,'cash: L3 - 3 МБ;frequency: 2x3300 МГц;','/prodImg/33.jpg?0.32407863012435656',1,1),(34,'AMD A10-9700 BOX',16900,'cash: L2 - 2 МБ;frequency: 4x3500 МГц;','/prodImg/34.jpg?0.0788247742741095',1,1),(35,'Intel Pentium Gold G5500 OEM',16800,'cash: L3 - 4 МБ;frequency: 2x3800 МГц;','/prodImg/35.jpg?0.29946370729625515',1,1),(36,'AMD Ryzen 3 2200G OEM',16800,'cash: L2 - 2 МБ;frequency: 4x3500 МГц;','/prodImg/36.jpg?0.6415800966263278',1,1),(37,'Intel Pentium G4560 BOX',17000,'cash: L3 - 3 МБ;frequency: 2x3500 МГц;','/prodImg/37.jpg?0.43330688898531244',1,1),(38,'AMD A12-9800 OEM',15700,'cash: L2 - 2 МБ;frequency: 4x3800 МГц;','/prodImg/38.jpg?0.6985986086221336',1,1),(39,'AMD Ryzen 3 2200G BOX',15000,'cash: L2 - 2 МБ;frequency: 4x3500 МГц;','/prodImg/39.jpg?0.0966374363451521',1,1),(40,'Intel Pentium Gold G5600 BOX',16200,'cash: L3 - 4 МБ;frequency: 2x3900 МГц;','/prodImg/40.jpg?0.4577329637268337',1,1),(41,'Intel Pentium Gold G5500 BOX',16000,'cash: L3 - 4 МБ;frequency: 2x3800 МГц;','/prodImg/41.jpg?0.543490901681508',1,1),(42,'AMD Ryzen 3 1300X OEM',14000,'cash: L2 - 2 МБ;frequency: 4x3500 МГц;','/prodImg/42.jpg?0.8593265216711293',1,1),(44,'MSI GeForce GT 710 1GD3H LP',2550,'memory: 1 ГБ DDR3;frequency: 954 МГц;','/prodImg/44.jpg?0.10468672108994104',2,1),(45,'ASUS GeForce GT710-SL-1GD5',2899,'memory: 2 ГБ DDR3;frequency: 954 МГц;','/prodImg/45.jpg?0.04220840001871051',2,1),(46,'ASUS GeForce GT710-SL-1GD5-BRK',2999,'memory: 1 ГБ DDR3;frequency: 954 МГц;','/prodImg/46.jpg?0.30937074413382903',2,1),(47,'GIGABYTE GeForce N710SL-1GL',3150,'memory: 1 ГБ DDR3;frequency: 954 МГц;','/prodImg/47.jpg?0.23739021531254378',2,1),(48,'INNO3D GeForce N710-1SDV-E3BX',3299,'memory: 2 ГБ DDR3;frequency: 954 МГц;','/prodImg/48.jpg?0.9062092418822545',2,1),(49,'MSI GeForce GT 710 2GD3H LP',3350,'memory: 2 ГБ DDR3;frequency: 954 МГц;','/prodImg/49.jpg?0.2803969374010713',2,1),(50,'Palit GeForce NEAT7100HD46-2080F',3499,'memory: 2 ГБ DDR3;frequency: 954 МГц;','/prodImg/50.jpg?0.4225149076070972',2,1),(51,'ASUS GeForce 710-2-SL',3699,'memory: 2 ГБ DDR3;frequency: 954 МГц;','/prodImg/51.jpg?0.8198411303449362',2,1),(52,'GIGABYTE GeForce GV-N710D5SL-2GL',3999,'memory: 2 ГБ DDR3;frequency: 730 МГц;','/prodImg/52.jpg?0.17925942999294076',2,1),(53,'ASUS GeForce GT710-SL-2GD5-BRK',4250,'memory: 2 ГБ DDR3;frequency: 700 МГц;','/prodImg/53.jpg?0.34180269965337995',2,1),(54,'ASUS GeForce GT710-SL-2GD5',4399,'memory: 1 ГБ DDR3;frequency: 902 МГц;','/prodImg/54.jpg?0.9664812197337235',2,1),(55,'ASUS GeForce GT730-SL-2GD3-BRK',4550,'memory: 2 ГБ DDR3;frequency: 902 МГц;','/prodImg/55.jpg?0.7784348282713621',2,1),(56,'Palit GeForce NEAT7300HD46-2080H',4699,'memory: 2 ГБ DDR3;frequency: 902 МГц;','/prodImg/56.jpg?0.5307429025987521',2,1),(57,'MSI GeForce GT 710 1GD3H LP',2750,'memory: 1 ГБ DDR3;frequency: 954 МГц;','/prodImg/57.jpg?0.6192310399962069',2,1),(58,'ASUS GeForce GT710-SL-1GD5',2899,'memory: 2 ГБ DDR3;frequency: 954 МГц;','/prodImg/58.jpg?0.9982339718379565',2,1),(59,'ASUS GeForce GT710-SL-1GD5-BRK',2999,'memory: 1 ГБ DDR3;frequency: 954 МГц;','/prodImg/59.jpg?0.6102825158061558',2,1),(60,'GIGABYTE GeForce N710SL-1GL',3150,'memory: 1 ГБ DDR3;frequency: 954 МГц;','/prodImg/60.jpg?0.1360874369360271',2,1),(61,'INNO3D GeForce N710-1SDV-E3BX',3299,'memory: 2 ГБ DDR3;frequency: 954 МГц;','/prodImg/61.jpg?0.8880591303665011',2,1),(62,'MSI GeForce GT 710 2GD3H LP',3350,'memory: 2 ГБ DDR3;frequency: 954 МГц;','/prodImg/62.jpg?0.9625159197562428',2,1),(63,'Palit GeForce NEAT7100HD46-2080F',3499,'memory: 2 ГБ DDR3;frequency: 954 МГц;','/prodImg/63.jpg?0.8102968043166239',2,1),(64,'ASUS GeForce 710-2-SL',3699,'memory: 2 ГБ DDR3;frequency: 954 МГц;','/prodImg/64.jpg?0.9895082147488447',2,1),(87,'IND GeForce N710-1SDV-D3BX',16000,'memory: 1000 ГБ DDR3;frequency: 954 МГц;','/prodImg/87.jpg?0.9151757298423534',2,1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_role` varchar(20) NOT NULL,
  `description` text,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `user_role` (`user_role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'admin',NULL),(2,'user',NULL);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `shop` (
  `point_code` int(11) NOT NULL AUTO_INCREMENT,
  `shop_name` varchar(20) NOT NULL,
  `address` varchar(40) NOT NULL,
  PRIMARY KEY (`point_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (1,'Mirazh','Vyazov, 13');
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storage`
--

DROP TABLE IF EXISTS `storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `storage` (
  `code_point` int(11) NOT NULL,
  `product_code` int(11) NOT NULL,
  `quantity` int(11) DEFAULT '100',
  PRIMARY KEY (`code_point`,`product_code`),
  KEY `Storage_fk1` (`product_code`),
  CONSTRAINT `Storage_fk0` FOREIGN KEY (`code_point`) REFERENCES `shop` (`point_code`),
  CONSTRAINT `Storage_fk1` FOREIGN KEY (`product_code`) REFERENCES `products` (`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storage`
--

LOCK TABLES `storage` WRITE;
/*!40000 ALTER TABLE `storage` DISABLE KEYS */;
/*!40000 ALTER TABLE `storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `user_code` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `login` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(20) NOT NULL,
  `role_id` int(11) NOT NULL DEFAULT '2',
  PRIMARY KEY (`user_code`),
  UNIQUE KEY `login` (`login`),
  KEY `Users_fk0` (`role_id`),
  CONSTRAINT `Users_fk0` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Иванов','Иван','ivan','111',1),(2,'Петров','Петр','petr','111',2),(7,'Бунегин','Петр','mac','111',1);
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

-- Dump completed on 2019-03-12 11:29:50
