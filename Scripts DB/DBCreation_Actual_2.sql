CREATE DATABASE  IF NOT EXISTS `parkitdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `parkitdb`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: parkitdb
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `t_alquiler`
--

DROP TABLE IF EXISTS `t_alquiler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_alquiler` (
  `ID_Alquiler` int NOT NULL AUTO_INCREMENT,
  `FechaInicio` date NOT NULL,
  `HoraInicio` time NOT NULL,
  `FechaFin` date NOT NULL,
  `HoraFin` time NOT NULL,
  `Pagado` tinyint NOT NULL,
  `Precio` decimal(10,0) NOT NULL,
  `ID_FormaPago` int NOT NULL,
  `ID_Empleado` int NOT NULL,
  `ID_TipoAlquiler` int NOT NULL,
  `ID_Vehiculo` int NOT NULL,
  `ID_Cochera` int NOT NULL,
  `TiempoEstadia` int NOT NULL,
  PRIMARY KEY (`ID_Alquiler`),
  KEY `t_alquiler_t_formapago_idx` (`ID_FormaPago`),
  KEY `t_alquiler_t_empleado_idx` (`ID_Empleado`),
  KEY `t_alquiler_t_tipoalquiler_idx` (`ID_TipoAlquiler`),
  KEY `t_alquiler_t_vehiculo_idx` (`ID_Vehiculo`),
  KEY `t_alquiler_t_cochera_idx` (`ID_Cochera`),
  CONSTRAINT `t_alquiler_t_cochera` FOREIGN KEY (`ID_Cochera`) REFERENCES `t_cochera` (`ID_Cochera`),
  CONSTRAINT `t_alquiler_t_empleado` FOREIGN KEY (`ID_Empleado`) REFERENCES `t_empleado` (`ID_Empleado`),
  CONSTRAINT `t_alquiler_t_formapago` FOREIGN KEY (`ID_FormaPago`) REFERENCES `t_formapago` (`ID_FormaPago`),
  CONSTRAINT `t_alquiler_t_tipoalquiler` FOREIGN KEY (`ID_TipoAlquiler`) REFERENCES `t_tipoalquiler` (`ID_TipoAlquiler`),
  CONSTRAINT `t_alquiler_t_vehiculo` FOREIGN KEY (`ID_Vehiculo`) REFERENCES `t_vehiculo` (`ID_Vehiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cliente`
--

DROP TABLE IF EXISTS `t_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_cliente` (
  `ID_Cliente` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Apellido` varchar(45) NOT NULL,
  `DNI` varchar(9) NOT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Telefono` varchar(15) DEFAULT NULL,
  `Direccion` varchar(100) DEFAULT NULL,
  `ID_Usuario` int DEFAULT NULL,
  PRIMARY KEY (`ID_Cliente`),
  KEY `fk_Cliente_Usuario_idx` (`ID_Usuario`),
  CONSTRAINT `fk_Cliente_Usuario` FOREIGN KEY (`ID_Usuario`) REFERENCES `t_usuario` (`ID_Usuario`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cochera`
--

DROP TABLE IF EXISTS `t_cochera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_cochera` (
  `ID_Cochera` int NOT NULL AUTO_INCREMENT,
  `NroCochera` int NOT NULL,
  `Disponible` tinyint NOT NULL DEFAULT '1',
  `Ubicacion` varchar(100) DEFAULT NULL,
  `ID_TipoCochera` int NOT NULL,
  PRIMARY KEY (`ID_Cochera`),
  KEY `t_cochera_t_tipocochera_idx` (`ID_TipoCochera`),
  CONSTRAINT `t_cochera_t_tipocochera` FOREIGN KEY (`ID_TipoCochera`) REFERENCES `t_tipocochera` (`ID_TipoCochera`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_empleado`
--

DROP TABLE IF EXISTS `t_empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_empleado` (
  `ID_Empleado` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Apellido` varchar(45) NOT NULL,
  `DNI` varchar(9) NOT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Telefono` varchar(15) DEFAULT NULL,
  `Direccion` varchar(100) DEFAULT NULL,
  `FechaNacimiento` date NOT NULL,
  `Cuit` varchar(12) NOT NULL,
  `ID_Usuario` int DEFAULT NULL,
  PRIMARY KEY (`ID_Empleado`),
  KEY `fk_Empleado_Usuario_idx` (`ID_Usuario`),
  CONSTRAINT `fk_Empleado_Usuario` FOREIGN KEY (`ID_Usuario`) REFERENCES `t_usuario` (`ID_Usuario`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_formapago`
--

DROP TABLE IF EXISTS `t_formapago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_formapago` (
  `ID_FormaPago` int NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(50) NOT NULL,
  `Descuento` double DEFAULT NULL,
  `Incremento` double DEFAULT NULL,
  PRIMARY KEY (`ID_FormaPago`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_listaespera`
--

DROP TABLE IF EXISTS `t_listaespera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_listaespera` (
  `ID_ListaEspera` int NOT NULL AUTO_INCREMENT,
  `FechaIngreso` date NOT NULL,
  `HoraIngreso` time NOT NULL,
  `ID_TipoCochera` int NOT NULL,
  `ID_Cliente` int NOT NULL,
  PRIMARY KEY (`ID_ListaEspera`),
  KEY `t_listaespera_t_tipocochera_idx` (`ID_TipoCochera`),
  KEY `t_listaespera_t_cliente_idx` (`ID_Cliente`),
  CONSTRAINT `t_listaespera_t_cliente` FOREIGN KEY (`ID_Cliente`) REFERENCES `t_cliente` (`ID_Cliente`),
  CONSTRAINT `t_listaespera_t_tipocochera` FOREIGN KEY (`ID_TipoCochera`) REFERENCES `t_tipocochera` (`ID_TipoCochera`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_precioalquiler`
--

DROP TABLE IF EXISTS `t_precioalquiler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_precioalquiler` (
  `ID_PrecioAlquiler` int NOT NULL AUTO_INCREMENT,
  `Precio` decimal(10,0) NOT NULL,
  `FechaVigencia` date NOT NULL,
  `ID_TipoCochera` int NOT NULL,
  `ID_TipoAlquiler` int NOT NULL,
  PRIMARY KEY (`ID_PrecioAlquiler`),
  KEY `t_precioalquiler_t_tipocochera_idx` (`ID_TipoCochera`),
  KEY `t_precioalquiler_t_tipoalquiler_idx` (`ID_TipoAlquiler`),
  CONSTRAINT `t_precioalquiler_t_tipoalquiler` FOREIGN KEY (`ID_TipoAlquiler`) REFERENCES `t_tipoalquiler` (`ID_TipoAlquiler`),
  CONSTRAINT `t_precioalquiler_t_tipocochera` FOREIGN KEY (`ID_TipoCochera`) REFERENCES `t_tipocochera` (`ID_TipoCochera`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_rol`
--

DROP TABLE IF EXISTS `t_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_rol` (
  `ID_Rol` int NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_Rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_servicio`
--

DROP TABLE IF EXISTS `t_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_servicio` (
  `ID_Servicio` int NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(100) NOT NULL,
  `Precio` decimal(10,0) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID_Servicio`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_serviciovehiculo`
--

DROP TABLE IF EXISTS `t_serviciovehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_serviciovehiculo` (
  `ID_ServicioVehiculo` int NOT NULL AUTO_INCREMENT,
  `FechaRealizacion` date NOT NULL,
  `ID_Servicio` int NOT NULL,
  `ID_Empleado` int NOT NULL,
  `ID_Vehiculo` int NOT NULL,
  `Pagado` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID_ServicioVehiculo`),
  KEY `t_serviciovehiculo_t_servicio_idx` (`ID_Servicio`),
  KEY `t_serviciovehiculo_t_empleado_idx` (`ID_Empleado`),
  KEY `t_serviciovehiculo_t_vehiculo_idx` (`ID_Vehiculo`),
  CONSTRAINT `t_serviciovehiculo_t_empleado` FOREIGN KEY (`ID_Empleado`) REFERENCES `t_empleado` (`ID_Empleado`),
  CONSTRAINT `t_serviciovehiculo_t_servicio` FOREIGN KEY (`ID_Servicio`) REFERENCES `t_servicio` (`ID_Servicio`),
  CONSTRAINT `t_serviciovehiculo_t_vehiculo` FOREIGN KEY (`ID_Vehiculo`) REFERENCES `t_vehiculo` (`ID_Vehiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_tipoalquiler`
--

DROP TABLE IF EXISTS `t_tipoalquiler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_tipoalquiler` (
  `ID_TipoAlquiler` int NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_TipoAlquiler`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_tipocochera`
--

DROP TABLE IF EXISTS `t_tipocochera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_tipocochera` (
  `ID_TipoCochera` int NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_TipoCochera`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_tipovehiculo`
--

DROP TABLE IF EXISTS `t_tipovehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_tipovehiculo` (
  `ID_TipoVehiculo` int NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_TipoVehiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_usuario`
--

DROP TABLE IF EXISTS `t_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_usuario` (
  `ID_Usuario` int NOT NULL AUTO_INCREMENT,
  `NombreUsuario` varchar(45) NOT NULL,
  `Password` varchar(15) NOT NULL,
  `Activo` tinyint NOT NULL DEFAULT '1',
  `TipoUsuario` int NOT NULL,
  PRIMARY KEY (`ID_Usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vehiculo`
--

DROP TABLE IF EXISTS `t_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_vehiculo` (
  `ID_Vehiculo` int NOT NULL AUTO_INCREMENT,
  `Patente` varchar(7) NOT NULL,
  `Modelo` varchar(100) DEFAULT NULL,
  `Marca` varchar(50) DEFAULT NULL,
  `ID_Cliente` int NOT NULL,
  `ID_TipoVehiculo` int NOT NULL,
  PRIMARY KEY (`ID_Vehiculo`),
  KEY `t_vehiculo_t_cliente_idx` (`ID_Cliente`),
  KEY `t_vehiculo_t_tipovehiculo_idx` (`ID_TipoVehiculo`),
  CONSTRAINT `t_vehiculo_t_cliente` FOREIGN KEY (`ID_Cliente`) REFERENCES `t_cliente` (`ID_Cliente`),
  CONSTRAINT `t_vehiculo_t_tipovehiculo` FOREIGN KEY (`ID_TipoVehiculo`) REFERENCES `t_tipovehiculo` (`ID_TipoVehiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'parkitdb'
--
/*!50003 DROP PROCEDURE IF EXISTS `sp_getAlquileresByFilter` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`parkitAdmin`@`%` PROCEDURE `sp_getAlquileresByFilter`(IN pFechaInicio DATE, IN pFechaFin DATE, IN pEstado INT, IN pFormaPagoID INT, IN pTipoAlquiler INT)
BEGIN
	SET @vEstadoTodos = 0, 
		@vEstadoPagado = 1, 
        @vEstadoSinPagar = 2, 
        @vFormaPagoTodas = 0,
        @vTipoAlquilerTodos = 0;
    
	SELECT *
    FROM t_Alquiler a
    INNER JOIN t_FormaPago fp ON fp.ID_FormaPago = a.ID_FormaPago
    INNER JOIN t_Empleado e ON e.ID_Empleado = a.ID_Empleado
    INNER JOIN t_TipoAlquiler t ON t.ID_TipoAlquiler = a.ID_TipoAlquiler
    INNER JOIN t_Vehiculo v ON v.ID_Vehiculo = a.ID_Vehiculo
    INNER JOIN t_Cochera c ON c.ID_Cochera = a.ID_Cochera
    WHERE 	(a.FechaInicio >= pFechaInicio)
    AND		(a.FechaFin <= pFechaFin)
    AND		(pEstado = @vEstadoTodos OR (pEstado = @vEstadoPagado AND a.Pagado = TRUE) OR (pEstado = @vEstadoSinPagar AND a.Pagado = FALSE))
    AND		(pFormaPagoID = @vFormaPagoTodas OR (a.ID_FormaPago = pFormaPagoID))
    AND		(pTipoAlquiler = @vTipoAlquilerTodos OR (pTipoAlquiler = a.ID_TipoAlquiler));
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_getCocherasLibres` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_getCocherasLibres`()
BEGIN
    SELECT c.*
    FROM t_Cochera c
	WHERE c.ID_Cochera NOT IN 
    (
		SELECT ID_Cochera
        FROM t_Alquiler
        WHERE Pagado = 0
    )
    AND c.Disponible = TRUE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_getCurrentPrice` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_getCurrentPrice`(IN pID_TipoAlquiler INT, IN pID_TipoCochera INT, IN pFechaVigencia DATE)
BEGIN
	SELECT p.*
	FROM t_precioalquiler p
	INNER JOIN (
		SELECT MAX(FechaVigencia) FechaVigencia
		FROM t_PrecioAlquiler
		WHERE 	(FechaVigencia <= pFechaVigencia)
	) historico ON p.FechaVigencia = historico.FechaVigencia
	WHERE 	pID_TipoAlquiler = ID_TipoAlquiler
    AND 	pID_TipoCochera = ID_TipoCochera;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_getVehiculosLibres` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_getVehiculosLibres`()
BEGIN
	SELECT v.*
    FROM t_Vehiculo v
	WHERE v.ID_Vehiculo NOT IN 
    (
		SELECT ID_Vehiculo
        FROM t_Alquiler
        WHERE Pagado = 0
    );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-10 18:41:59
