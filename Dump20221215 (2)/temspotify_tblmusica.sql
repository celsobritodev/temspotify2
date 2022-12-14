-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: temspotify
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `tblmusica`
--

DROP TABLE IF EXISTS `tblmusica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblmusica` (
  `idMusica` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) NOT NULL,
  `artista` varchar(45) NOT NULL,
  `album` varchar(45) NOT NULL,
  `estilo` int DEFAULT NULL,
  `linkMP3` mediumtext,
  PRIMARY KEY (`idMusica`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblmusica`
--

LOCK TABLES `tblmusica` WRITE;
/*!40000 ALTER TABLE `tblmusica` DISABLE KEYS */;
INSERT INTO `tblmusica` VALUES (1,'Tema do He Man','Principe Adam','Desenhos Animados',6,'musicas/He-Man.mp3'),(2,'Beautiful','Real Life','Diversos',6,'musicas/Beautiful.mp3'),(3,'3D BIRD','QuickSilver','Desenhos animados',6,'musicas/3D bird.mp3'),(4,'Pirates','Pet Shop Boys','Album Leg',5,'musicas/Pirates.mp3'),(5,'Vampire','Vamp Loko','Vamps',1,'musicas/Vampire.mp3'),(6,'Vou te taxi','Angelica','Thuru',2,'musicas/Taxi.mp3');
/*!40000 ALTER TABLE `tblmusica` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-18 12:32:47
