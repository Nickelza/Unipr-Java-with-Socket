-- phpMyAdmin SQL Dump
-- version 4.6.6deb5ubuntu0.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 26, 2020 at 09:13 PM
-- Server version: 5.7.32-0ubuntu0.18.04.1
-- PHP Version: 7.2.34-8+ubuntu18.04.1+deb.sury.org+1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Winery`
--
CREATE DATABASE IF NOT EXISTS `Winery` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `Winery`;

-- --------------------------------------------------------

--
-- Table structure for table `Order`
--

CREATE TABLE IF NOT EXISTS `Order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `delivered` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `OrderUser`
--

CREATE TABLE IF NOT EXISTS `OrderUser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `OrderUser_fk0` (`user_id`),
  KEY `OrderUser_fk1` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `OrderWine`
--

CREATE TABLE IF NOT EXISTS `OrderWine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wine_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `OrderWine_fk0` (`wine_id`),
  KEY `OrderWine_fk1` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Vinyard`
--

CREATE TABLE IF NOT EXISTS `Vinyard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Warehouse`
--

CREATE TABLE IF NOT EXISTS `Warehouse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `provisioning_manager_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Warehouse_fk0` (`provisioning_manager_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `WarehouseWines`
--

CREATE TABLE IF NOT EXISTS `WarehouseWines` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `warehouse_id` int(11) NOT NULL,
  `wine_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `WarehouseWines_fk0` (`warehouse_id`),
  KEY `WarehouseWines_fk1` (`wine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `WarehouseWineShops`
--

CREATE TABLE IF NOT EXISTS `WarehouseWineShops` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `warehouse_id` int(11) NOT NULL,
  `wineshop_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `WarehouseWineShops_fk0` (`warehouse_id`),
  KEY `WarehouseWineShops_fk1` (`wineshop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Wine`
--

CREATE TABLE IF NOT EXISTS `Wine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `year` year(4) NOT NULL,
  `producer` varchar(255) NOT NULL,
  `tech_note` varchar(1023) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `WineShop`
--

CREATE TABLE IF NOT EXISTS `WineShop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `provisioning_manager` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `WineShop_fk0` (`provisioning_manager`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `WineShopUsersList`
--

CREATE TABLE IF NOT EXISTS `WineShopUsersList` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wineshop_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `WineShopUsersList_fk0` (`wineshop_id`),
  KEY `WineShopUsersList_fk1` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `WineVineyards`
--

CREATE TABLE IF NOT EXISTS `WineVineyards` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wine_id` int(11) NOT NULL,
  `vineyard_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `WineVineyards_fk0` (`wine_id`),
  KEY `WineVineyards_fk1` (`vineyard_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `OrderUser`
--
ALTER TABLE `OrderUser`
  ADD CONSTRAINT `OrderUser_fk0` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  ADD CONSTRAINT `OrderUser_fk1` FOREIGN KEY (`order_id`) REFERENCES `Order` (`id`);

--
-- Constraints for table `OrderWine`
--
ALTER TABLE `OrderWine`
  ADD CONSTRAINT `OrderWine_fk0` FOREIGN KEY (`wine_id`) REFERENCES `Wine` (`id`),
  ADD CONSTRAINT `OrderWine_fk1` FOREIGN KEY (`order_id`) REFERENCES `Order` (`id`);

--
-- Constraints for table `Warehouse`
--
ALTER TABLE `Warehouse`
  ADD CONSTRAINT `Warehouse_fk0` FOREIGN KEY (`provisioning_manager_id`) REFERENCES `User` (`id`);

--
-- Constraints for table `WarehouseWines`
--
ALTER TABLE `WarehouseWines`
  ADD CONSTRAINT `WarehouseWines_fk0` FOREIGN KEY (`warehouse_id`) REFERENCES `Warehouse` (`id`),
  ADD CONSTRAINT `WarehouseWines_fk1` FOREIGN KEY (`wine_id`) REFERENCES `Wine` (`id`);

--
-- Constraints for table `WarehouseWineShops`
--
ALTER TABLE `WarehouseWineShops`
  ADD CONSTRAINT `WarehouseWineShops_fk0` FOREIGN KEY (`warehouse_id`) REFERENCES `Warehouse` (`id`),
  ADD CONSTRAINT `WarehouseWineShops_fk1` FOREIGN KEY (`wineshop_id`) REFERENCES `WineShop` (`id`);

--
-- Constraints for table `WineShop`
--
ALTER TABLE `WineShop`
  ADD CONSTRAINT `WineShop_fk0` FOREIGN KEY (`provisioning_manager`) REFERENCES `User` (`id`);

--
-- Constraints for table `WineShopUsersList`
--
ALTER TABLE `WineShopUsersList`
  ADD CONSTRAINT `WineShopUsersList_fk0` FOREIGN KEY (`wineshop_id`) REFERENCES `WineShop` (`id`),
  ADD CONSTRAINT `WineShopUsersList_fk1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`);

--
-- Constraints for table `WineVineyards`
--
ALTER TABLE `WineVineyards`
  ADD CONSTRAINT `WineVineyards_fk0` FOREIGN KEY (`wine_id`) REFERENCES `Wine` (`id`),
  ADD CONSTRAINT `WineVineyards_fk1` FOREIGN KEY (`vineyard_id`) REFERENCES `Vinyard` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
