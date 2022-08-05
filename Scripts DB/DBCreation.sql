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
  `HoraInicio` timestamp NOT NULL,
  `FechaFin` date NOT NULL,
  `HoraFin` timestamp NOT NULL,
  `Pagado` tinyint NOT NULL,
  `Precio` decimal(10,0) NOT NULL,
  `ID_FormaPago` int NOT NULL,
  `ID_Empleado` int NOT NULL,
  `ID_TipoAlquiler` int NOT NULL,
  `ID_Vehiculo` int NOT NULL,
  `ID_Cochera` int NOT NULL,
  PRIMARY KEY (`ID_Alquiler`),
  KEY `t_alquiler_t_formapago_idx` (`ID_FormaPago`),
  KEY `t_alquiler_t_empleado_idx` (`ID_Empleado`),
  KEY `t_alquiler_t_tipoalquiler_idx` (`ID_TipoAlquiler`),
  KEY `t_alquiler_t_vehiculo_idx` (`ID_Vehiculo`),
  KEY `t_alquiler_t_cochera_idx` (`ID_Cochera`),
  CONSTRAINT `t_alquiler_t_cochera` FOREIGN KEY (`ID_Cochera`) REFERENCES `t_cochera` (`ID_Cochera`),
  CONSTRAINT `t_alquiler_t_empleado` FOREIGN KEY (`ID_Empleado`) REFERENCES `t_empleado` (`ID_Persona`),
  CONSTRAINT `t_alquiler_t_formapago` FOREIGN KEY (`ID_FormaPago`) REFERENCES `t_formapago` (`ID_FormaPago`),
  CONSTRAINT `t_alquiler_t_tipoalquiler` FOREIGN KEY (`ID_TipoAlquiler`) REFERENCES `t_tipoalquiler` (`ID_TipoAlquiler`),
  CONSTRAINT `t_alquiler_t_vehiculo` FOREIGN KEY (`ID_Vehiculo`) REFERENCES `t_vehiculo` (`ID_Vehiculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cliente`
--

DROP TABLE IF EXISTS `t_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_cliente` (
  `ID_Persona` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID_Persona`),
  CONSTRAINT `t_cliente_t_persona` FOREIGN KEY (`ID_Persona`) REFERENCES `t_persona` (`ID_Persona`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_empleado`
--

DROP TABLE IF EXISTS `t_empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_empleado` (
  `ID_Persona` int NOT NULL AUTO_INCREMENT,
  `FechaNacimiento` date NOT NULL,
  `Cuit` varchar(12) NOT NULL,
  PRIMARY KEY (`ID_Persona`),
  CONSTRAINT `t_empleado_t_persona` FOREIGN KEY (`ID_Persona`) REFERENCES `t_persona` (`ID_Persona`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_listaespera`
--

DROP TABLE IF EXISTS `t_listaespera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_listaespera` (
  `ID_ListaEspera` int NOT NULL AUTO_INCREMENT,
  `FechaIngreso` datetime NOT NULL,
  `HoraIngreso` time NOT NULL,
  `ID_TipoCochera` int NOT NULL,
  `ID_Cliente` int NOT NULL,
  PRIMARY KEY (`ID_ListaEspera`),
  KEY `t_listaespera_t_tipocochera_idx` (`ID_TipoCochera`),
  KEY `t_listaespera_t_cliente_idx` (`ID_Cliente`),
  CONSTRAINT `t_listaespera_t_cliente` FOREIGN KEY (`ID_Cliente`) REFERENCES `t_cliente` (`ID_Persona`),
  CONSTRAINT `t_listaespera_t_tipocochera` FOREIGN KEY (`ID_TipoCochera`) REFERENCES `t_tipocochera` (`ID_TipoCochera`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_persona`
--

DROP TABLE IF EXISTS `t_persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_persona` (
  `ID_Persona` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Apellido` varchar(45) NOT NULL,
  `DNI` varchar(9) NOT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Telefono` varchar(15) DEFAULT NULL,
  `Direccion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID_Persona`)
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
  `FechaVigencia` datetime NOT NULL,
  `ID_TipoCochera` int NOT NULL,
  `ID_TipoAlquiler` int NOT NULL,
  PRIMARY KEY (`ID_PrecioAlquiler`),
  KEY `t_precioalquiler_t_tipocochera_idx` (`ID_TipoCochera`),
  KEY `t_precioalquiler_t_tipoalquiler_idx` (`ID_TipoAlquiler`),
  CONSTRAINT `t_precioalquiler_t_tipoalquiler` FOREIGN KEY (`ID_TipoAlquiler`) REFERENCES `t_tipoalquiler` (`ID_TipoAlquiler`),
  CONSTRAINT `t_precioalquiler_t_tipocochera` FOREIGN KEY (`ID_TipoCochera`) REFERENCES `t_tipocochera` (`ID_TipoCochera`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  PRIMARY KEY (`ID_ServicioVehiculo`),
  KEY `t_serviciovehiculo_t_servicio_idx` (`ID_Servicio`),
  KEY `t_serviciovehiculo_t_empleado_idx` (`ID_Empleado`),
  KEY `t_serviciovehiculo_t_vehiculo_idx` (`ID_Vehiculo`),
  CONSTRAINT `t_serviciovehiculo_t_empleado` FOREIGN KEY (`ID_Empleado`) REFERENCES `t_empleado` (`ID_Persona`),
  CONSTRAINT `t_serviciovehiculo_t_servicio` FOREIGN KEY (`ID_Servicio`) REFERENCES `t_servicio` (`ID_Servicio`),
  CONSTRAINT `t_serviciovehiculo_t_vehiculo` FOREIGN KEY (`ID_Vehiculo`) REFERENCES `t_vehiculo` (`ID_Vehiculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `ID_Persona` int NOT NULL,
  PRIMARY KEY (`ID_Usuario`),
  KEY `t_usuario_t_persona_idx` (`ID_Persona`),
  CONSTRAINT `t_usuario_t_persona` FOREIGN KEY (`ID_Persona`) REFERENCES `t_persona` (`ID_Persona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_usuariorol`
--

DROP TABLE IF EXISTS `t_usuariorol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_usuariorol` (
  `ID_UsuarioRol` int NOT NULL AUTO_INCREMENT,
  `ID_Usuario` int NOT NULL,
  `ID_Rol` int NOT NULL,
  PRIMARY KEY (`ID_UsuarioRol`),
  KEY `t_usuariorol_t_usuario_idx` (`ID_Usuario`),
  KEY `t_usuariorol_t_rol_idx` (`ID_Rol`),
  CONSTRAINT `t_usuariorol_t_rol` FOREIGN KEY (`ID_Rol`) REFERENCES `t_rol` (`ID_Rol`),
  CONSTRAINT `t_usuariorol_t_usuario` FOREIGN KEY (`ID_Usuario`) REFERENCES `t_usuario` (`ID_Usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  CONSTRAINT `t_vehiculo_t_cliente` FOREIGN KEY (`ID_Cliente`) REFERENCES `t_cliente` (`ID_Persona`),
  CONSTRAINT `t_vehiculo_t_tipovehiculo` FOREIGN KEY (`ID_TipoVehiculo`) REFERENCES `t_tipovehiculo` (`ID_TipoVehiculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-05 19:49:36
