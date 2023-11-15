CREATE DATABASE  IF NOT EXISTS `help_history` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `help_history`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: sosohappy.co.kr    Database: help_history
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `certificate`
--

DROP TABLE IF EXISTS `certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `certificate` (
  `certificate_id` bigint NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `category_name` varchar(255) NOT NULL,
  `member_id` bigint NOT NULL,
  PRIMARY KEY (`certificate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificate`
--

LOCK TABLES `certificate` WRITE;
/*!40000 ALTER TABLE `certificate` DISABLE KEYS */;
INSERT INTO `certificate` VALUES (1,'쌉벌레츄츄몬김석주','2023-11-15 00:15:48','계단 이용',0),(2,'쌉벌레츄츄몬김석주','2023-11-15 00:16:39','계단 이용',0),(3,'쌉벌레츄츄몬김석주','2023-11-15 01:11:19','계단 이용',28),(4,'페이커','2023-11-15 06:07:19','보행 도우미',33),(5,'페이커','2023-11-15 06:15:11','휠체어 이동',33),(6,'페이커','2023-11-15 06:24:11','휠체어 이동',33),(7,'페이커','2023-11-15 06:58:58','휠체어 이동',33);
/*!40000 ALTER TABLE `certificate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fortune_card`
--

DROP TABLE IF EXISTS `fortune_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fortune_card` (
  `fortune_card_id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  PRIMARY KEY (`fortune_card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fortune_card`
--

LOCK TABLES `fortune_card` WRITE;
/*!40000 ALTER TABLE `fortune_card` DISABLE KEYS */;
/*!40000 ALTER TABLE `fortune_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `help_history`
--

DROP TABLE IF EXISTS `help_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `help_history` (
  `history_id` bigint NOT NULL AUTO_INCREMENT,
  `category_id` bigint NOT NULL,
  `content` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `from_member_id` bigint NOT NULL,
  `to_member_id` bigint NOT NULL,
  `x` double NOT NULL,
  `y` double NOT NULL,
  PRIMARY KEY (`history_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `help_history`
--

LOCK TABLES `help_history` WRITE;
/*!40000 ALTER TABLE `help_history` DISABLE KEYS */;
INSERT INTO `help_history` VALUES (73,1,'Fd','2023-11-15 00:15:48',28,1,37.5013923,127.039686),(74,1,'Fd','2023-11-15 00:16:39',28,1,37.5013923,127.039686),(75,1,'Fd','2023-11-15 01:11:19',28,1,37.5013923,127.039686),(76,2,'ㅁㄹ','2023-11-15 06:07:19',33,34,37.5013952,127.03958),(77,4,'오르막길 도와주세요','2023-11-15 06:15:11',33,34,37.5014002,127.0395813),(78,4,'오르막길 도와주세요','2023-11-15 06:24:11',33,34,37.5013992,127.0395815),(79,4,'오르막길 도와주세요','2023-11-15 06:58:58',33,34,37.5014119,127.0395818);
/*!40000 ALTER TABLE `help_history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-15 16:54:02
