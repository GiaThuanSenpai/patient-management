-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: patient_service_db
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `id` binary(16) NOT NULL,
  `address` varchar(255) NOT NULL,
  `date_of_birth` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `registered_date` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKa370hmxgv0l5c9panryr1ji7d` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (_binary '>Eg\è›Ó¤VBf@\0','123 Main St, Springfield','1985-06-15','john.doe@example.com','John Doe','2024-01-10'),(_binary '>Eg\è›Ó¤VBf@','456 Elm St, Shelbyville','1990-09-23','jane.smith@example.com','Jane Smith','2023-12-01'),(_binary '>Eg\è›Ó¤VBf@','789 Oak St, Capital City','1978-03-12','alice.johnson@example.com','Alice Johnson','2022-06-20'),(_binary '>Eg\è›Ó¤VBf@','321 Pine St, Springfield','1982-11-30','bob.brown@example.com','Bob Brown','2023-05-14'),(_binary '>Eg\è›Ó¤VBf@','654 Maple St, Shelbyville','1995-02-05','emily.davis@example.com','Emily Davis','2024-03-01'),(_binary '\">Eg\è›Ó¤VBf@','987 Cedar St, Springfield','1988-07-25','michael.green@example.com','Michael Green','2024-02-15'),(_binary '\">Eg\è›Ó¤VBf@','123 Birch St, Shelbyville','1992-04-18','sarah.taylor@example.com','Sarah Taylor','2023-08-25'),(_binary '\">Eg\è›Ó¤VBf@','456 Ash St, Capital City','1975-01-11','david.wilson@example.com','David Wilson','2022-10-10');
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-24 14:34:40
