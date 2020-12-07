CREATE DATABASE  IF NOT EXISTS `winery` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `winery`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: winery
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
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `delivered` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderuser`
--

DROP TABLE IF EXISTS `orderuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderuser` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `order_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `OrderUser_fk0` (`user_id`),
  KEY `OrderUser_fk1` (`order_id`),
  CONSTRAINT `OrderUser_fk0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `OrderUser_fk1` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderuser`
--

LOCK TABLES `orderuser` WRITE;
/*!40000 ALTER TABLE `orderuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderwine`
--

DROP TABLE IF EXISTS `orderwine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderwine` (
  `id` int NOT NULL AUTO_INCREMENT,
  `wine_id` int NOT NULL,
  `order_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `OrderWine_fk0` (`wine_id`),
  KEY `OrderWine_fk1` (`order_id`),
  CONSTRAINT `OrderWine_fk0` FOREIGN KEY (`wine_id`) REFERENCES `wine` (`id`) ON DELETE CASCADE,
  CONSTRAINT `OrderWine_fk1` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderwine`
--

LOCK TABLES `orderwine` WRITE;
/*!40000 ALTER TABLE `orderwine` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderwine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Luca','Bianchi','email','pwd','employee'),(2,'Luca','Bianchi','email','pwd','employee'),(3,'Luca','Bianchi','email','pwd','employee'),(4,'Luca','Bianchi','email','pwd','employee'),(5,'Luca','Bianchi','email','pwd','employee'),(6,'Luca','Bianchi','email','pwd','EMPLOYEE'),(7,'Lucaz','Bianchi','email','pwd','EMPLOYEE'),(8,'Lucaz','Bianchi','email','pwd','EMPLOYEE'),(9,'Lucaz','Bianchi','email','pwd','EMPLOYEE'),(10,'Lucaz','Bianchi','email','pwd','EMPLOYEE'),(11,'Lucaz','Bianchi','email','pwd','EMPLOYEE'),(12,'Lucaz','Bianchi','email','pwd','EMPLOYEE'),(13,'Lucaz','Bianchi','email','pwd','EMPLOYEE');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `vieworderuser`
--

DROP TABLE IF EXISTS `vieworderuser`;
/*!50001 DROP VIEW IF EXISTS `vieworderuser`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vieworderuser` AS SELECT 
 1 AS `user_id`,
 1 AS `order_id`,
 1 AS `date`,
 1 AS `delivered`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vieworderwine`
--

DROP TABLE IF EXISTS `vieworderwine`;
/*!50001 DROP VIEW IF EXISTS `vieworderwine`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vieworderwine` AS SELECT 
 1 AS `order_id`,
 1 AS `wine_id`,
 1 AS `name`,
 1 AS `year`,
 1 AS `producer`,
 1 AS `tech_note`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `viewwineshopusers`
--

DROP TABLE IF EXISTS `viewwineshopusers`;
/*!50001 DROP VIEW IF EXISTS `viewwineshopusers`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `viewwineshopusers` AS SELECT 
 1 AS `wineshop_id`,
 1 AS `user_id`,
 1 AS `name`,
 1 AS `surname`,
 1 AS `email`,
 1 AS `password`,
 1 AS `type`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `vinyard`
--

DROP TABLE IF EXISTS `vinyard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vinyard` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vinyard`
--

LOCK TABLES `vinyard` WRITE;
/*!40000 ALTER TABLE `vinyard` DISABLE KEYS */;
/*!40000 ALTER TABLE `vinyard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse`
--

DROP TABLE IF EXISTS `warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warehouse` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `provisioning_manager_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Warehouse_fk0` (`provisioning_manager_id`),
  CONSTRAINT `Warehouse_fk0` FOREIGN KEY (`provisioning_manager_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse`
--

LOCK TABLES `warehouse` WRITE;
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
/*!40000 ALTER TABLE `warehouse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehousewines`
--

DROP TABLE IF EXISTS `warehousewines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warehousewines` (
  `id` int NOT NULL AUTO_INCREMENT,
  `warehouse_id` int NOT NULL,
  `wine_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `WarehouseWines_fk0` (`warehouse_id`),
  KEY `WarehouseWines_fk1` (`wine_id`),
  CONSTRAINT `WarehouseWines_fk0` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`id`) ON DELETE CASCADE,
  CONSTRAINT `WarehouseWines_fk1` FOREIGN KEY (`wine_id`) REFERENCES `wine` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehousewines`
--

LOCK TABLES `warehousewines` WRITE;
/*!40000 ALTER TABLE `warehousewines` DISABLE KEYS */;
/*!40000 ALTER TABLE `warehousewines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehousewineshops`
--

DROP TABLE IF EXISTS `warehousewineshops`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warehousewineshops` (
  `id` int NOT NULL AUTO_INCREMENT,
  `warehouse_id` int NOT NULL,
  `wineshop_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `WarehouseWineShops_fk0` (`warehouse_id`),
  KEY `WarehouseWineShops_fk1` (`wineshop_id`),
  CONSTRAINT `WarehouseWineShops_fk0` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`id`) ON DELETE CASCADE,
  CONSTRAINT `WarehouseWineShops_fk1` FOREIGN KEY (`wineshop_id`) REFERENCES `wineshop` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehousewineshops`
--

LOCK TABLES `warehousewineshops` WRITE;
/*!40000 ALTER TABLE `warehousewineshops` DISABLE KEYS */;
/*!40000 ALTER TABLE `warehousewineshops` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wine`
--

DROP TABLE IF EXISTS `wine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wine` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `year` year NOT NULL,
  `producer` varchar(255) NOT NULL,
  `tech_note` varchar(1023) DEFAULT NULL,
  `quantity` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wine`
--

LOCK TABLES `wine` WRITE;
/*!40000 ALTER TABLE `wine` DISABLE KEYS */;
/*!40000 ALTER TABLE `wine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wineshop`
--

DROP TABLE IF EXISTS `wineshop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wineshop` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `provisioning_manager` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `WineShop_fk0` (`provisioning_manager`),
  CONSTRAINT `WineShop_fk0` FOREIGN KEY (`provisioning_manager`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wineshop`
--

LOCK TABLES `wineshop` WRITE;
/*!40000 ALTER TABLE `wineshop` DISABLE KEYS */;
INSERT INTO `wineshop` VALUES (1,'',NULL);
/*!40000 ALTER TABLE `wineshop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wineshopuserslist`
--

DROP TABLE IF EXISTS `wineshopuserslist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wineshopuserslist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `wineshop_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `WineShopUsersList_fk0` (`wineshop_id`),
  KEY `WineShopUsersList_fk1` (`user_id`),
  CONSTRAINT `WineShopUsersList_fk0` FOREIGN KEY (`wineshop_id`) REFERENCES `wineshop` (`id`) ON DELETE CASCADE,
  CONSTRAINT `WineShopUsersList_fk1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wineshopuserslist`
--

LOCK TABLES `wineshopuserslist` WRITE;
/*!40000 ALTER TABLE `wineshopuserslist` DISABLE KEYS */;
/*!40000 ALTER TABLE `wineshopuserslist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `winevineyards`
--

DROP TABLE IF EXISTS `winevineyards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `winevineyards` (
  `id` int NOT NULL AUTO_INCREMENT,
  `wine_id` int NOT NULL,
  `vineyard_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `WineVineyards_fk0` (`wine_id`),
  KEY `WineVineyards_fk1` (`vineyard_id`),
  CONSTRAINT `WineVineyards_fk0` FOREIGN KEY (`wine_id`) REFERENCES `wine` (`id`) ON DELETE CASCADE,
  CONSTRAINT `WineVineyards_fk1` FOREIGN KEY (`vineyard_id`) REFERENCES `vinyard` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `winevineyards`
--

LOCK TABLES `winevineyards` WRITE;
/*!40000 ALTER TABLE `winevineyards` DISABLE KEYS */;
/*!40000 ALTER TABLE `winevineyards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `vieworderuser`
--

/*!50001 DROP VIEW IF EXISTS `vieworderuser`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vieworderuser` AS select `orderuser`.`user_id` AS `user_id`,`order`.`id` AS `order_id`,`order`.`date` AS `date`,`order`.`delivered` AS `delivered` from (`orderuser` join `order` on((`orderuser`.`order_id` = `order`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vieworderwine`
--

/*!50001 DROP VIEW IF EXISTS `vieworderwine`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vieworderwine` AS select `orderwine`.`order_id` AS `order_id`,`wine`.`id` AS `wine_id`,`wine`.`name` AS `name`,`wine`.`year` AS `year`,`wine`.`producer` AS `producer`,`wine`.`tech_note` AS `tech_note` from (`orderwine` join `wine` on((`orderwine`.`wine_id` = `wine`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `viewwineshopusers`
--

/*!50001 DROP VIEW IF EXISTS `viewwineshopusers`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `viewwineshopusers` AS select `wineshopuserslist`.`id` AS `wineshop_id`,`user`.`id` AS `user_id`,`user`.`name` AS `name`,`user`.`surname` AS `surname`,`user`.`email` AS `email`,`user`.`password` AS `password`,`user`.`type` AS `type` from (`user` join `wineshopuserslist` on((`user`.`id` = `wineshopuserslist`.`user_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-07  1:19:32
