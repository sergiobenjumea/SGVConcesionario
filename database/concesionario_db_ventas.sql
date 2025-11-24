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
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventas` (
  `id_venta` int NOT NULL AUTO_INCREMENT,
  `numero_factura` varchar(50) NOT NULL,
  `fecha_venta` date NOT NULL,
  `id_cliente` int NOT NULL,
  `id_vendedor` int NOT NULL,
  `id_auto` int NOT NULL,
  `id_forma_pago` int NOT NULL,
  `precio_base` decimal(12,2) NOT NULL,
  `impuesto_venta` decimal(12,2) NOT NULL,
  `iva` decimal(12,2) NOT NULL,
  `total_pagar` decimal(12,2) NOT NULL,
  `estado` enum('Activa','Anulada') DEFAULT 'Activa',
  `fecha_anulacion` date DEFAULT NULL,
  `fecha_registro` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_venta`),
  UNIQUE KEY `numero_factura` (`numero_factura`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_vendedor` (`id_vendedor`),
  KEY `id_auto` (`id_auto`),
  KEY `id_forma_pago` (`id_forma_pago`),
  CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`id_vendedor`) REFERENCES `vendedores` (`id_vendedor`),
  CONSTRAINT `ventas_ibfk_3` FOREIGN KEY (`id_auto`) REFERENCES `automoviles` (`id_auto`),
  CONSTRAINT `ventas_ibfk_4` FOREIGN KEY (`id_forma_pago`) REFERENCES `formas_pago` (`id_forma_pago`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES (1,'FAC-001','2024-01-10',8,1,1,1,450000000.00,67500000.00,85500000.00,603000000.00,'Activa',NULL,'2025-11-24 20:19:57'),(2,'FAC-002','2024-01-15',7,2,2,3,900000000.00,135000000.00,171000000.00,1206000000.00,'Activa',NULL,'2025-11-24 20:19:57'),(3,'FAC-003','2024-02-01',6,3,3,3,850000000.00,127500000.00,161500000.00,1139000000.00,'Activa',NULL,'2025-11-24 20:19:57'),(4,'FAC-004','2024-02-10',9,1,4,2,750000000.00,112500000.00,142500000.00,1005000000.00,'Activa',NULL,'2025-11-24 20:19:57'),(5,'FAC-005','2024-02-14',2,5,5,1,800000000.00,120000000.00,152000000.00,1072000000.00,'Activa',NULL,'2025-11-24 20:19:57'),(6,'FAC-006','2024-03-01',11,4,6,1,650000000.00,97500000.00,123500000.00,871000000.00,'Activa',NULL,'2025-11-24 20:19:57'),(7,'FAC-007','2024-03-15',10,4,7,1,500000000.00,75000000.00,95000000.00,670000000.00,'Activa',NULL,'2025-11-24 20:19:57'),(8,'FAC-008','2024-03-20',1,3,8,2,600000000.00,90000000.00,114000000.00,804000000.00,'Activa',NULL,'2025-11-24 20:19:57'),(9,'FAC-009','2024-04-01',12,2,9,1,700000000.00,105000000.00,133000000.00,938000000.00,'Activa',NULL,'2025-11-24 20:19:57'),(10,'FAC-010','2024-04-05',4,5,10,3,880000000.00,132000000.00,167200000.00,1179200000.00,'Activa',NULL,'2025-11-24 20:19:57'),(11,'FAC-011','2025-11-24',1,1,1,1,450000000.00,67500000.00,85500000.00,603000000.00,'Activa',NULL,'2025-11-24 20:37:21');
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
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
