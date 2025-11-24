-- MySQL dump 10.13  Distrib 8.0.36, for macos14 (arm64)
--
-- Host: 127.0.0.1    Database: concesionario_db
-- ------------------------------------------------------
-- Server version	9.4.0

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `id_tipo_identificacion` int NOT NULL,
  `identificacion` varchar(50) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `edad` int NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `fecha_registro` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `identificacion` (`identificacion`),
  KEY `id_tipo_identificacion` (`id_tipo_identificacion`),
  CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`id_tipo_identificacion`) REFERENCES `tipos_identificacion` (`id_tipo_identificacion`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,1,'9000001','Shakira Mebarak','1977-02-02',47,'shakira@music.com','2025-11-24 20:19:57'),(2,1,'9000002','Bad Bunny','1994-03-10',30,'benito@pr.com','2025-11-24 20:19:57'),(3,1,'9000003','Karol G','1991-02-14',33,'karol@g.com','2025-11-24 20:19:57'),(4,1,'9000004','J Balvin','1985-05-07',39,'jose@colores.com','2025-11-24 20:19:57'),(5,1,'9000005','Maluma','1994-01-28',30,'juan@hawaii.com','2025-11-24 20:19:57'),(6,4,'8000010','Lionel Messi','1987-06-24',37,'leomessi@goat.com','2025-11-24 20:19:57'),(7,4,'8000007','Cristiano Ronaldo','1985-02-05',39,'cr7@siuuu.com','2025-11-24 20:19:57'),(8,4,'5000001','Elon Musk','1971-06-28',53,'elon@tesla.com','2025-11-24 20:19:57'),(9,4,'5000002','Kim Kardashian','1980-10-21',43,'kim@skims.com','2025-11-24 20:19:57'),(10,4,'5000003','Bill Gates','1955-10-28',68,'bill@msft.com','2025-11-24 20:19:57'),(11,4,'5000004','Keanu Reeves','1964-09-02',59,'neo@matrix.com','2025-11-24 20:19:57'),(12,4,'5000005','Robert Downey Jr.','1965-04-04',59,'tony@stark.com','2025-11-24 20:19:57');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-24 15:51:01
