-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: shopcart
-- ------------------------------------------------------
-- Server version	5.7.39-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bill_order`
--

DROP TABLE IF EXISTS `bill_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `total` bigint(20) NOT NULL,
  `create_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bill_order_ibfk_1` (`user_id`),
  CONSTRAINT `bill_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_order`
--

LOCK TABLES `bill_order` WRITE;
/*!40000 ALTER TABLE `bill_order` DISABLE KEYS */;
INSERT INTO `bill_order` VALUES (2,8,90000000,'2022-08-10 13:58:49');
/*!40000 ALTER TABLE `bill_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total` bigint(20) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (102,0,3),(103,0,5),(104,0,6),(105,0,7),(106,0,8);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_line`
--

DROP TABLE IF EXISTS `cart_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_line` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cart_id` int(11) DEFAULT NULL,
  `count` int(11) NOT NULL,
  `bill_order_id` int(11) DEFAULT NULL,
  `status` enum('WAIT_FOR_PAY','PAYING_SUCCESSFUL') DEFAULT NULL,
  `create_at` datetime NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cart_line_ibfk_1` (`cart_id`),
  KEY `cart_line_ibfk_2` (`product_id`),
  KEY `cart_line_ibfk_3` (`bill_order_id`),
  CONSTRAINT `cart_line_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  CONSTRAINT `cart_line_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `cart_line_ibfk_3` FOREIGN KEY (`bill_order_id`) REFERENCES `bill_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_line`
--

LOCK TABLES `cart_line` WRITE;
/*!40000 ALTER TABLE `cart_line` DISABLE KEYS */;
INSERT INTO `cart_line` VALUES (4,106,2,2,'PAYING_SUCCESSFUL','2022-08-10 13:58:30','2022-08-10 13:58:49',2);
/*!40000 ALTER TABLE `cart_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `price` bigint(20) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `category` enum('MOBILE','TABLET','LAPTOP') DEFAULT NULL,
  `create_at` datetime NOT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Iphone 13 128GB',19500000,10,'MOBILE','2022-08-10 13:11:15',NULL),(2,'Macbook Pro M2 newseak',45000000,10,'LAPTOP','2022-08-10 13:12:09',NULL),(3,'Ipad Pro 12.9inc 99%',12000000,10,'TABLET','2022-08-10 13:12:45',NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` text NOT NULL,
  `password` text NOT NULL,
  `full_name` text NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `email` text NOT NULL,
  `address` text NOT NULL,
  `role` enum('ADMIN','MEMBER') DEFAULT NULL,
  `status` enum('ACTIVE','UNACTIVE') DEFAULT NULL,
  `create_at` datetime NOT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'admin','admin','Tran Binh Chien','0356562991','chient0122gmail.com','Nam Dinh','ADMIN','ACTIVE','2022-08-10 12:47:59',NULL),(4,'admin1','admin','Tran Binh Chien','0356562991','chient0122gmail.com','Nam Dinh','ADMIN','ACTIVE','2022-08-10 12:48:56',NULL),(5,'admin31','admin','Tran Binh Chien','0356562991','chient0122gmail.com','Nam Dinh','MEMBER','ACTIVE','2022-08-10 12:50:17',NULL),(6,'6','admin','Tran Binh Chien','0356562991','chient0122gmail.com','Nam Dinh','MEMBER','UNACTIVE','2022-08-10 12:50:52',NULL),(7,'member','member','Tran Binh Chien','0356562991','chient0122gmail.com','Nam Dinh','MEMBER','ACTIVE','2022-08-10 13:21:07',NULL),(8,'cuong','cuong','Tran Binh Cuong','0356562991','chient0122gmail.com','Nam Dinh','MEMBER','ACTIVE','2022-08-10 13:58:20',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-10 14:09:46
