CREATE DATABASE  IF NOT EXISTS `member` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `member`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: sosohappy.co.kr    Database: member
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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_id` tinyint NOT NULL AUTO_INCREMENT,
  `member_id` bigint NOT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `member_id_UNIQUE` (`member_id`),
  CONSTRAINT `admin_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,26);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banned_member`
--

DROP TABLE IF EXISTS `banned_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banned_member` (
  `member_id` bigint NOT NULL,
  `end_timestamp` timestamp NOT NULL,
  PRIMARY KEY (`member_id`),
  CONSTRAINT `banned_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banned_member`
--

LOCK TABLES `banned_member` WRITE;
/*!40000 ALTER TABLE `banned_member` DISABLE KEYS */;
INSERT INTO `banned_member` VALUES (27,'2023-11-21 00:00:00');
/*!40000 ALTER TABLE `banned_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` bigint NOT NULL AUTO_INCREMENT,
  `member_sign_id` varchar(20) NOT NULL,
  `member_sign_password` varchar(64) NOT NULL,
  `nickname` varchar(15) NOT NULL,
  `name` varchar(5) NOT NULL,
  `profile_monster_id` int NOT NULL DEFAULT '1',
  `profile_background_id` int NOT NULL DEFAULT '1',
  `disabled` tinyint NOT NULL,
  `gender` tinyint NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `member_sign_id_UNIQUE` (`member_sign_id`),
  UNIQUE KEY `nickname_UNIQUE` (`nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'ssafy','9646bec96a923a5c1165f2e8b3b49b414ca8509ced0736b9d7c9f3c8f7376baf','쌉벌레츄츄몬김석주','김석주',15,1,1,0,'2023-10-26 04:23:09'),(2,'sssafy','2e0a7e34bb07ec0ae60aa3a6405ead57014ea5ac90dcf102d9b623b349b4038d','워그래이 찬영몬','최찬영',1,2,0,0,'2023-10-27 05:08:47'),(23,'geniuschoi123','aa263feb3474359ad7aa9487726969fb2c7b9f589b03bf3933a44509e93baa90','응애민희곤듀','정민희',1,1,0,0,'2023-11-01 00:16:05'),(25,'ssafy2','22324b34dcd9d0ad8dd44daa717fe6cf0b7ac9f5b012560b4ffdc2778a88c0ba','결백한윤드래곤','윤태영',1,1,1,1,'2023-11-01 00:36:27'),(26,'wang','69650b82f5c960347212b7c616845342a45a5027e3150571a7b1b6167dc26a8e','츄츄몬킬러','왕준영',1,1,0,1,'2023-11-10 00:52:50'),(27,'seok','e8f8b90088be5729724a7da2bf59308491ea17ca9c62ad9c304190f62239736f','쌉벌레츄츄몬부케','김석주',1,1,1,1,'2023-11-10 02:00:20'),(28,'yty','8a2b23a1f2a8fe74a2e7fc5d4fbb0fd9feeb3d421dbcf47c463d1c916bbebbf2','석주같은태영','윤태영',1,1,0,1,'2023-11-10 05:37:41'),(32,'asd','8631965779a50ac20b36a6fd2a1686f78a9987d2753dc6ac95f27a044c0a1c18','asd','asd',1,1,0,1,'2023-11-15 05:37:48'),(33,'ssafy3','0426ab2b37dbbf799e3a3352303d5b9d94da1c7d0352a643336e0f6f67a02d5c','소소행','소소행',1,1,0,1,'2023-11-15 06:04:43'),(34,'ssafy4','7a258b80a795709c98e64eda8d31cb59ad08dadd5d1ab3b490f05b7945758259','페이커','하창무',1,1,1,1,'2023-11-15 06:05:22');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_device`
--

DROP TABLE IF EXISTS `member_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_device` (
  `member_id` bigint NOT NULL,
  `device_token` varchar(255) NOT NULL,
  PRIMARY KEY (`member_id`),
  CONSTRAINT `member_device_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_device`
--

LOCK TABLES `member_device` WRITE;
/*!40000 ALTER TABLE `member_device` DISABLE KEYS */;
INSERT INTO `member_device` VALUES (1,'e_lTRe1hR3C6C6mpjeN980:APA91bFT3xyudniyhPL5NzYkokWwMrqZoYbvRzL1oMHf6noJb_YnNK7vFb8K4fn8PEKUyHx-hlh6FOqmvEfc6V9eReRBHGxHJJuPhH7J_0MF50oTj0_0X20ds7JciGLSKn-FxXKcNjba'),(2,'eX-kJFemSqGTF6VABKq98N:APA91bGklO5ScJvmJ0tFxNK2UePLqCC56FiX2jrNVMsySCWx1RxKQBgz6xyFlO4D9dKwDY-veof0V-sRC8Al3sll9xobTsaJGHZ-tpBAVRttt5wa3wAllzxrCagEaENR5DSaDxLluHMw'),(26,'e_lTRe1hR3C6C6mpjeN980:APA91bFT3xyudniyhPL5NzYkokWwMrqZoYbvRzL1oMHf6noJb_YnNK7vFb8K4fn8PEKUyHx-hlh6FOqmvEfc6V9eReRBHGxHJJuPhH7J_0MF50oTj0_0X20ds7JciGLSKn-FxXKcNjba'),(28,'e_lTRe1hR3C6C6mpjeN980:APA91bFT3xyudniyhPL5NzYkokWwMrqZoYbvRzL1oMHf6noJb_YnNK7vFb8K4fn8PEKUyHx-hlh6FOqmvEfc6V9eReRBHGxHJJuPhH7J_0MF50oTj0_0X20ds7JciGLSKn-FxXKcNjba'),(32,'cvlFIGdCSrGtiMU5UMNZDv:APA91bFNwUtyyWttQ_NvdEd5OGJB2pYSzNWVfwfr75aEragwEy20q9RbdXAN9G70McAgKZgIRQ507duT74lbnMj1O-DsmgJvdtAgEIkjtma0MqqODrbieH-Fw6srATCPg_lO_H7saBys'),(33,'ccsbyKHxQQmHp_GhkO31lm:APA91bELnQYFvZoE_M3buWDB80i4Z3M7E6xjvAyyicT_fksHn3jQOoSct3UBQmqw7vmA2WgfHLeWkWyqmD9YHJfs7rKZKhiMeVSCaP2CxLzX7gsjThzUZ5mLeNzEpgpwCtFcGbba1yUf'),(34,'e_lTRe1hR3C6C6mpjeN980:APA91bFT3xyudniyhPL5NzYkokWwMrqZoYbvRzL1oMHf6noJb_YnNK7vFb8K4fn8PEKUyHx-hlh6FOqmvEfc6V9eReRBHGxHJJuPhH7J_0MF50oTj0_0X20ds7JciGLSKn-FxXKcNjba');
/*!40000 ALTER TABLE `member_device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_report`
--

DROP TABLE IF EXISTS `member_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_report` (
  `report_id` bigint NOT NULL AUTO_INCREMENT,
  `reporting_member_id` bigint NOT NULL,
  `reported_member_id` bigint NOT NULL,
  PRIMARY KEY (`report_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_report`
--

LOCK TABLES `member_report` WRITE;
/*!40000 ALTER TABLE `member_report` DISABLE KEYS */;
INSERT INTO `member_report` VALUES (1,2,1),(2,1,3),(3,26,28),(4,26,28),(5,26,28),(6,26,28),(7,26,28);
/*!40000 ALTER TABLE `member_report` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-15 16:54:40
