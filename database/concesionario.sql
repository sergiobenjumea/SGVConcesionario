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
-- Table structure for table `automoviles`
--

DROP TABLE IF EXISTS `automoviles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `automoviles` (
  `id_auto` int NOT NULL AUTO_INCREMENT,
  `id_linea` int NOT NULL,
  `modelo_anio` int NOT NULL,
  `color` varchar(50) NOT NULL,
  `id_tipo_motor` int NOT NULL,
  `precio_base` decimal(12,2) NOT NULL,
  `impuesto_venta` decimal(12,2) NOT NULL DEFAULT '0.00',
  `iva` decimal(12,2) NOT NULL DEFAULT '0.00',
  `precio_total` decimal(12,2) NOT NULL DEFAULT '0.00',
  `estado` enum('Disponible','Vendido','Reservado') DEFAULT 'Disponible',
  `fecha_registro` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_auto`),
  KEY `id_linea` (`id_linea`),
  KEY `id_tipo_motor` (`id_tipo_motor`),
  CONSTRAINT `automoviles_ibfk_1` FOREIGN KEY (`id_linea`) REFERENCES `lineas` (`id_linea`),
  CONSTRAINT `automoviles_ibfk_2` FOREIGN KEY (`id_tipo_motor`) REFERENCES `tipos_motor` (`id_tipo_motor`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `automoviles`
--

LOCK TABLES `automoviles` WRITE;
/*!40000 ALTER TABLE `automoviles` DISABLE KEYS */;
INSERT INTO `automoviles` VALUES (1,1,2025,'Acero',2,450000000.00,67500000.00,85500000.00,603000000.00,'Vendido','2025-11-24 19:44:49'),(2,7,2024,'Negro/Azul',1,900000000.00,135000000.00,171000000.00,1206000000.00,'Vendido','2025-11-24 19:44:49'),(3,3,2023,'Rojo Corsa',3,850000000.00,127500000.00,161500000.00,1139000000.00,'Vendido','2025-11-24 19:44:49'),(4,9,2024,'Blanco Mate',1,750000000.00,112500000.00,142500000.00,1005000000.00,'Vendido','2025-11-24 19:44:49'),(5,6,2024,'Amarillo',1,800000000.00,120000000.00,152000000.00,1072000000.00,'Vendido','2025-11-24 19:44:49'),(6,8,2024,'Gris GT',1,650000000.00,97500000.00,123500000.00,871000000.00,'Vendido','2025-11-24 19:44:49'),(7,2,2024,'Negro Mate',2,500000000.00,75000000.00,95000000.00,670000000.00,'Vendido','2025-11-24 19:44:49'),(8,10,2024,'Plata',1,600000000.00,90000000.00,114000000.00,804000000.00,'Vendido','2025-11-24 19:44:49'),(9,11,2023,'Azul',3,700000000.00,105000000.00,133000000.00,938000000.00,'Vendido','2025-11-24 19:44:49'),(10,5,2023,'Arcoiris',1,880000000.00,132000000.00,167200000.00,1179200000.00,'Vendido','2025-11-24 19:44:49'),(11,1,2025,'Negro',2,460000000.00,69000000.00,87400000.00,616400000.00,'Disponible','2025-11-24 19:44:49'),(12,10,2024,'Negro',1,760000000.00,114000000.00,144400000.00,1018400000.00,'Disponible','2025-11-24 19:44:49');
/*!40000 ALTER TABLE `automoviles` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `clientes` VALUES (1,1,'SHAKIRA','Shakira Mebarak','1977-02-02',47,'shakira@music.com','2025-11-24 19:44:49'),(2,1,'BADBUNNY','Bad Bunny','1994-03-10',30,'benito@pr.com','2025-11-24 19:44:49'),(3,1,'KAROLG','Karol G','1991-02-14',33,'bichota@medallo.com','2025-11-24 19:44:49'),(4,1,'JBALVIN','J Balvin','1985-05-07',39,'jose@colores.com','2025-11-24 19:44:49'),(5,1,'MALUMA','Maluma','1994-01-28',30,'juan.luis@hawaii.com','2025-11-24 19:44:49'),(6,4,'MESSI10','Lionel Messi','1987-06-24',37,'leomessi@goat.com','2025-11-24 19:44:49'),(7,4,'CR7','Cristiano Ronaldo','1985-02-05',39,'cr7@siuuu.com','2025-11-24 19:44:49'),(8,4,'ELON','Elon Musk','1971-06-28',53,'elon@tesla.com','2025-11-24 19:44:49'),(9,4,'KIMK','Kim Kardashian','1980-10-21',43,'kim@skims.com','2025-11-24 19:44:49'),(10,4,'BILLG','Bill Gates','1955-10-28',68,'bill@microsoft.com','2025-11-24 19:44:49'),(11,4,'KEANU','Keanu Reeves','1964-09-02',59,'neo@matrix.com','2025-11-24 19:44:49'),(12,4,'RDJ','Robert Downey Jr.','1965-04-04',59,'ironman@avengers.com','2025-11-24 19:44:49');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formas_pago`
--

DROP TABLE IF EXISTS `formas_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `formas_pago` (
  `id_forma_pago` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id_forma_pago`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formas_pago`
--

LOCK TABLES `formas_pago` WRITE;
/*!40000 ALTER TABLE `formas_pago` DISABLE KEYS */;
INSERT INTO `formas_pago` VALUES (1,'Contado'),(2,'Crédito Directo'),(3,'Crédito Bancario');
/*!40000 ALTER TABLE `formas_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lineas`
--

DROP TABLE IF EXISTS `lineas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lineas` (
  `id_linea` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `id_marca` int NOT NULL,
  PRIMARY KEY (`id_linea`),
  KEY `id_marca` (`id_marca`),
  CONSTRAINT `lineas_ibfk_1` FOREIGN KEY (`id_marca`) REFERENCES `marcas` (`id_marca`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lineas`
--

LOCK TABLES `lineas` WRITE;
/*!40000 ALTER TABLE `lineas` DISABLE KEYS */;
INSERT INTO `lineas` VALUES (1,'Cybertruck',1),(2,'Model S Plaid',1),(3,'LaFerrari',2),(4,'SF90 Stradale',2),(5,'Aventador',3),(6,'Urus',3),(7,'Chiron',4),(8,'911 GT3',5),(9,'G-Wagon',6),(10,'Land Cruiser 300',7),(11,'i8',8);
/*!40000 ALTER TABLE `lineas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marcas`
--

DROP TABLE IF EXISTS `marcas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marcas` (
  `id_marca` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id_marca`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marcas`
--

LOCK TABLES `marcas` WRITE;
/*!40000 ALTER TABLE `marcas` DISABLE KEYS */;
INSERT INTO `marcas` VALUES (1,'Tesla'),(2,'Ferrari'),(3,'Lamborghini'),(4,'Bugatti'),(5,'Porsche'),(6,'Mercedes-Benz'),(7,'Toyota'),(8,'BMW');
/*!40000 ALTER TABLE `marcas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_identificacion`
--

DROP TABLE IF EXISTS `tipos_identificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos_identificacion` (
  `id_tipo_identificacion` int NOT NULL AUTO_INCREMENT,
  `codigo` varchar(10) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id_tipo_identificacion`),
  UNIQUE KEY `codigo` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_identificacion`
--

LOCK TABLES `tipos_identificacion` WRITE;
/*!40000 ALTER TABLE `tipos_identificacion` DISABLE KEYS */;
INSERT INTO `tipos_identificacion` VALUES (1,'CC','Cédula',NULL,1),(2,'TI','Tarjeta Identidad',NULL,1),(3,'CE','Extranjería',NULL,1),(4,'PAS','Pasaporte',NULL,1);
/*!40000 ALTER TABLE `tipos_identificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_motor`
--

DROP TABLE IF EXISTS `tipos_motor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos_motor` (
  `id_tipo_motor` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id_tipo_motor`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_motor`
--

LOCK TABLES `tipos_motor` WRITE;
/*!40000 ALTER TABLE `tipos_motor` DISABLE KEYS */;
INSERT INTO `tipos_motor` VALUES (1,'Gasolina'),(2,'Electrico'),(3,'Hibrido');
/*!40000 ALTER TABLE `tipos_motor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendedores`
--

DROP TABLE IF EXISTS `vendedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendedores` (
  `id_vendedor` int NOT NULL AUTO_INCREMENT,
  `identificacion` varchar(50) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `profesion` varchar(100) DEFAULT NULL,
  `fecha_contratacion` date NOT NULL,
  `fecha_registro` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_vendedor`),
  UNIQUE KEY `identificacion` (`identificacion`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedores`
--

LOCK TABLES `vendedores` WRITE;
/*!40000 ALTER TABLE `vendedores` DISABLE KEYS */;
INSERT INTO `vendedores` VALUES (1,'YT-001','MrBeast (Jimmy)','Filántropo y Vendedor','2020-01-01','2025-11-24 19:44:49'),(2,'YT-002','Ibai Llanos','Streamer y Negociador','2021-05-15','2025-11-24 19:44:49'),(3,'YT-003','Luisito Comunica','Viajero y Vendedor','2019-08-20','2025-11-24 19:44:49'),(4,'YT-004','Marques Brownlee (MKBHD)','Experto Tech','2022-03-10','2025-11-24 19:44:49'),(5,'YT-005','AuronPlay','Atención al Cliente','2023-11-01','2025-11-24 19:44:49');
/*!40000 ALTER TABLE `vendedores` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES (1,'FAC-001','2024-01-10',8,1,1,1,450000000.00,67500000.00,85500000.00,603000000.00,'Activa',NULL,'2025-11-24 19:44:49'),(2,'FAC-002','2024-01-15',7,2,2,3,900000000.00,135000000.00,171000000.00,1206000000.00,'Activa',NULL,'2025-11-24 19:44:49'),(3,'FAC-003','2024-02-01',6,3,3,3,850000000.00,127500000.00,161500000.00,1139000000.00,'Activa',NULL,'2025-11-24 19:44:49'),(4,'FAC-004','2024-02-10',9,1,4,2,750000000.00,112500000.00,142500000.00,1005000000.00,'Activa',NULL,'2025-11-24 19:44:49'),(5,'FAC-005','2024-02-14',2,5,5,1,800000000.00,120000000.00,152000000.00,1072000000.00,'Activa',NULL,'2025-11-24 19:44:49'),(6,'FAC-006','2024-03-01',11,4,6,1,650000000.00,97500000.00,123500000.00,871000000.00,'Activa',NULL,'2025-11-24 19:44:49'),(7,'FAC-007','2024-03-15',10,4,7,1,500000000.00,75000000.00,95000000.00,670000000.00,'Activa',NULL,'2025-11-24 19:44:49'),(8,'FAC-008','2024-03-20',1,3,8,2,600000000.00,90000000.00,114000000.00,804000000.00,'Activa',NULL,'2025-11-24 19:44:49'),(9,'FAC-009','2024-04-01',12,2,9,1,700000000.00,105000000.00,133000000.00,938000000.00,'Activa',NULL,'2025-11-24 19:44:49'),(10,'FAC-010','2024-04-05',4,5,10,3,880000000.00,132000000.00,167200000.00,1179200000.00,'Activa',NULL,'2025-11-24 19:44:49');
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

-- Dump completed on 2025-11-24 14:49:26
