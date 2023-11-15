CREATE DATABASE  IF NOT EXISTS `monster` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `monster`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: sosohappy.co.kr    Database: monster
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
-- Table structure for table `member_monster_growth`
--

DROP TABLE IF EXISTS `member_monster_growth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_monster_growth` (
  `member_monster_id` bigint NOT NULL AUTO_INCREMENT,
  `monster_clover` int NOT NULL DEFAULT '0',
  `member_id` bigint DEFAULT NULL,
  `type_id` tinyint DEFAULT NULL,
  PRIMARY KEY (`member_monster_id`),
  KEY `FKg1uyjybbvxb14hxfp168nxh8k` (`member_id`),
  KEY `FKavtins7rlw49dq5vymhfinlqb` (`type_id`),
  CONSTRAINT `FKavtins7rlw49dq5vymhfinlqb` FOREIGN KEY (`type_id`) REFERENCES `monster_type` (`type_id`),
  CONSTRAINT `FKg1uyjybbvxb14hxfp168nxh8k` FOREIGN KEY (`member_id`) REFERENCES `member_monster_profile` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=496 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_monster_growth`
--

LOCK TABLES `member_monster_growth` WRITE;
/*!40000 ALTER TABLE `member_monster_growth` DISABLE KEYS */;
INSERT INTO `member_monster_growth` VALUES (1,40,1,1),(2,17,1,2),(3,35,1,3),(348,0,2,1),(349,0,2,2),(350,0,2,3),(432,0,2,1),(433,0,2,2),(434,0,2,3),(447,78,10,1),(448,78,10,2),(449,78,10,3),(450,15,23,1),(451,32,23,2),(452,50,23,3),(454,0,2,4),(458,0,23,4),(459,0,10,4),(464,0,26,1),(465,0,26,2),(466,0,26,3),(467,0,26,4),(468,0,27,1),(469,0,27,2),(470,0,27,3),(471,0,27,4),(472,0,28,1),(473,0,28,2),(474,0,28,3),(475,0,28,4),(484,0,32,1),(485,0,32,2),(486,0,32,3),(487,0,32,4),(488,0,33,1),(489,0,33,2),(490,0,33,3),(491,0,33,4),(492,0,34,1),(493,0,34,2),(494,0,34,3),(495,0,34,4);
/*!40000 ALTER TABLE `member_monster_growth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_monster_profile`
--

DROP TABLE IF EXISTS `member_monster_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_monster_profile` (
  `member_id` bigint NOT NULL,
  `member_accrued_clover` bigint DEFAULT NULL,
  `member_clover` int NOT NULL,
  `monster_id` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`member_id`),
  KEY `FK872romk2t0q09ywima2laojpu` (`monster_id`),
  CONSTRAINT `FK872romk2t0q09ywima2laojpu` FOREIGN KEY (`monster_id`) REFERENCES `monster_info` (`monster_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_monster_profile`
--

LOCK TABLES `member_monster_profile` WRITE;
/*!40000 ALTER TABLE `member_monster_profile` DISABLE KEYS */;
INSERT INTO `member_monster_profile` VALUES (1,122,30,13),(2,0,0,1),(10,100,24,1),(23,100,30,1),(26,0,0,1),(27,0,0,1),(28,2,2,1),(32,0,0,1),(33,0,0,1),(34,0,0,1);
/*!40000 ALTER TABLE `member_monster_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monster_info`
--

DROP TABLE IF EXISTS `monster_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `monster_info` (
  `monster_id` int NOT NULL AUTO_INCREMENT,
  `monster_level` int NOT NULL,
  `required_clover` int NOT NULL,
  `type_id` tinyint DEFAULT NULL,
  PRIMARY KEY (`monster_id`),
  KEY `FKqibxny4ng6h2wb19c8x7mp5sb` (`type_id`),
  CONSTRAINT `FKqibxny4ng6h2wb19c8x7mp5sb` FOREIGN KEY (`type_id`) REFERENCES `monster_type` (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monster_info`
--

LOCK TABLES `monster_info` WRITE;
/*!40000 ALTER TABLE `monster_info` DISABLE KEYS */;
INSERT INTO `monster_info` VALUES (1,1,0,1),(2,2,3,1),(3,3,5,1),(4,4,10,1),(5,5,10,1),(6,6,10,1),(7,7,10,1),(8,8,10,1),(9,9,10,1),(10,10,10,1),(11,1,0,2),(12,2,3,2),(13,3,5,2),(14,4,10,2),(15,5,10,2),(16,6,10,2),(17,7,10,2),(18,8,10,2),(19,9,10,2),(20,10,10,2),(21,1,0,3),(22,2,3,3),(23,3,5,3),(24,4,10,3),(25,5,10,3),(26,6,10,3),(27,7,10,3),(28,8,10,3),(29,9,10,3),(30,10,10,3),(31,1,0,4);
/*!40000 ALTER TABLE `monster_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monster_type`
--

DROP TABLE IF EXISTS `monster_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `monster_type` (
  `type_id` tinyint NOT NULL,
  `type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monster_type`
--

LOCK TABLES `monster_type` WRITE;
/*!40000 ALTER TABLE `monster_type` DISABLE KEYS */;
INSERT INTO `monster_type` VALUES (1,'육지'),(2,'해양'),(3,'비행'),(4,'고래');
/*!40000 ALTER TABLE `monster_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-15 16:55:22
