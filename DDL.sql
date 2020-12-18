-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Dec 18, 2020 at 12:38 AM
-- Server version: 8.0.22
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `unipr`
--

-- --------------------------------------------------------

--
-- Table structure for table `ORDER_ITEM`
--

CREATE TABLE `ORDER_ITEM` (
  `ID` int NOT NULL,
  `DATE` text NOT NULL,
  `DELIVERED` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `REL_ORDER_USER`
--

CREATE TABLE `REL_ORDER_USER` (
  `ID` int NOT NULL,
  `USER_ID` int NOT NULL,
  `ORDER_ID` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Stand-in structure for view `REL_ORDER_USER_WINE_EXTENDED`
-- (See below for the actual view)
--
CREATE TABLE `REL_ORDER_USER_WINE_EXTENDED` (
`USER_ID` int
,`USER_NAME` varchar(255)
,`USER_SURNAME` varchar(255)
,`USER_EMAIL` varchar(255)
,`USER_PASSWORD` varchar(255)
,`USER_TYPE` varchar(255)
,`ORDER_ID` int
,`ORDER_DATE` text
,`ORDER_DELIVERED` varchar(10)
,`WINE_ID` int
,`WINE_NAME` varchar(255)
,`WINE_YEAR` int
,`WINE_PRODUCER` varchar(2056)
,`WINE_TECHNOTES` varchar(2056)
,`VINEYARD_ID` int
,`VINEYARD_NAME` varchar(512)
);

-- --------------------------------------------------------

--
-- Table structure for table `REL_ORDER_WINE`
--

CREATE TABLE `REL_ORDER_WINE` (
  `ID` int NOT NULL,
  `ORDER_ID` int NOT NULL,
  `WINE_ID` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `REL_USER_WINESHOP`
--

CREATE TABLE `REL_USER_WINESHOP` (
  `ID` int NOT NULL,
  `WINESHOP_ID` int NOT NULL,
  `USER_ID` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Stand-in structure for view `REL_USER_WINESHOP_EXTENDED`
-- (See below for the actual view)
--
CREATE TABLE `REL_USER_WINESHOP_EXTENDED` (
`USER_ID` int
,`NAME` varchar(255)
,`SURNAME` varchar(255)
,`EMAIL` varchar(255)
,`PASSWORD` varchar(255)
,`TYPE` varchar(255)
,`WINESHOP_ID` int
,`WINESHOP_NAME` varchar(255)
);

-- --------------------------------------------------------

--
-- Table structure for table `REL_WINESHOP_WAREHOUSE`
--

CREATE TABLE `REL_WINESHOP_WAREHOUSE` (
  `ID` int NOT NULL,
  `ID_WAREHOUSE` int NOT NULL,
  `ID_WINESHOP` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `REL_WINESHOP_WAREHOUSE`
--

INSERT INTO `REL_WINESHOP_WAREHOUSE` (`ID`, `ID_WAREHOUSE`, `ID_WINESHOP`) VALUES
(321, 330, 322);

-- --------------------------------------------------------

--
-- Table structure for table `REL_WINE_VINEYARD`
--

CREATE TABLE `REL_WINE_VINEYARD` (
  `ID` int NOT NULL,
  `WINE_ID` int NOT NULL,
  `VINEYARD_ID` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Stand-in structure for view `REL_WINE_VINEYARD_EXTENDED`
-- (See below for the actual view)
--
CREATE TABLE `REL_WINE_VINEYARD_EXTENDED` (
`WINE_ID` int
,`WINE_NAME` varchar(255)
,`WINE_YEAR` int
,`WINE_PRODUCER` varchar(2056)
,`WINE_TECHNOTES` varchar(2056)
,`VINEYARD_ID` int
,`VINEYARD_NAME` varchar(512)
);

-- --------------------------------------------------------

--
-- Table structure for table `REL_WINE_WAREHOUSE`
--

CREATE TABLE `REL_WINE_WAREHOUSE` (
  `ID` int NOT NULL,
  `WINE_ID` int NOT NULL,
  `WAREHOUSE_ID` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Stand-in structure for view `REL_WINE_WAREHOUSE_EXTENDED`
-- (See below for the actual view)
--
CREATE TABLE `REL_WINE_WAREHOUSE_EXTENDED` (
`WINESHOP_ID` int
,`WINESHOP_NAME` varchar(255)
,`WAREHOUSE_ID` int
,`WAREHOUSE_NAME` varchar(255)
,`WINE_ID` int
,`WINE_NAME` varchar(255)
,`WINE_YEAR` int
,`WINE_PRODUCER` varchar(2056)
,`WINE_TECHNOTES` varchar(2056)
,`VINEYARD_ID` int
,`VINEYARD_NAME` varchar(512)
);

-- --------------------------------------------------------

--
-- Table structure for table `USER`
--

CREATE TABLE `USER` (
  `ID` int NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `SURNAME` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `TYPE` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `VINEYARD`
--

CREATE TABLE `VINEYARD` (
  `ID` int NOT NULL,
  `NAME` varchar(512) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `WAREHOUSE`
--

CREATE TABLE `WAREHOUSE` (
  `ID` int NOT NULL,
  `NAME` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `WAREHOUSE`
--

INSERT INTO `WAREHOUSE` (`ID`, `NAME`) VALUES
(330, 'WAREHOUSE-0');

-- --------------------------------------------------------

--
-- Table structure for table `WINE`
--

CREATE TABLE `WINE` (
  `ID` int NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `YEAR` int NOT NULL,
  `PRODUCER` varchar(2056) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TECHNOTES` varchar(2056) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `WINESHOP`
--

CREATE TABLE `WINESHOP` (
  `ID` int NOT NULL,
  `NAME` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `WINESHOP`
--

INSERT INTO `WINESHOP` (`ID`, `NAME`) VALUES
(322, 'Enoteca Galvani');

-- --------------------------------------------------------

--
-- Structure for view `REL_ORDER_USER_WINE_EXTENDED`
--
DROP TABLE IF EXISTS `REL_ORDER_USER_WINE_EXTENDED`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `REL_ORDER_USER_WINE_EXTENDED` (`USER_ID`, `USER_NAME`, `USER_SURNAME`, `USER_EMAIL`, `USER_PASSWORD`, `USER_TYPE`, `ORDER_ID`, `ORDER_DATE`, `ORDER_DELIVERED`, `WINE_ID`, `WINE_NAME`, `WINE_YEAR`, `WINE_PRODUCER`, `WINE_TECHNOTES`, `VINEYARD_ID`, `VINEYARD_NAME`) AS   select `U`.`ID` AS `ID`,`U`.`NAME` AS `NAME`,`U`.`SURNAME` AS `SURNAME`,`U`.`EMAIL` AS `EMAIL`,`U`.`PASSWORD` AS `PASSWORD`,`U`.`TYPE` AS `TYPE`,`OI`.`ID` AS `ID`,`OI`.`DATE` AS `DATE`,`OI`.`DELIVERED` AS `DELIVERED`,`W`.`ID` AS `ID`,`W`.`NAME` AS `NAME`,`W`.`YEAR` AS `YEAR`,`W`.`PRODUCER` AS `PRODUCER`,`W`.`TECHNOTES` AS `TECHNOTES`,`VY`.`ID` AS `ID`,`VY`.`NAME` AS `NAME` from ((((((`REL_ORDER_USER` `r0` join `USER` `U` on((`r0`.`USER_ID` = `U`.`ID`))) join `ORDER_ITEM` `OI` on((`OI`.`ID` = `r0`.`ORDER_ID`))) join `REL_ORDER_WINE` `r1` on((`r1`.`ORDER_ID` = `r0`.`ORDER_ID`))) join `WINE` `W` on((`r1`.`WINE_ID` = `W`.`ID`))) left join `REL_WINE_VINEYARD` `r2` on((`r2`.`WINE_ID` = `W`.`ID`))) left join `VINEYARD` `VY` on((`VY`.`ID` = `r2`.`VINEYARD_ID`)))  ;

-- --------------------------------------------------------

--
-- Structure for view `REL_USER_WINESHOP_EXTENDED`
--
DROP TABLE IF EXISTS `REL_USER_WINESHOP_EXTENDED`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `REL_USER_WINESHOP_EXTENDED` (`USER_ID`, `NAME`, `SURNAME`, `EMAIL`, `PASSWORD`, `TYPE`, `WINESHOP_ID`, `WINESHOP_NAME`) AS   select `us`.`ID` AS `ID`,`us`.`NAME` AS `NAME`,`us`.`SURNAME` AS `SURNAME`,`us`.`EMAIL` AS `EMAIL`,`us`.`PASSWORD` AS `PASSWORD`,`us`.`TYPE` AS `TYPE`,`ws`.`ID` AS `ID`,`ws`.`NAME` AS `NAME` from ((`REL_USER_WINESHOP` join `USER` `us` on((`REL_USER_WINESHOP`.`USER_ID` = `us`.`ID`))) join `WINESHOP` `ws` on((`REL_USER_WINESHOP`.`WINESHOP_ID` = `ws`.`ID`)))  ;

-- --------------------------------------------------------

--
-- Structure for view `REL_WINE_VINEYARD_EXTENDED`
--
DROP TABLE IF EXISTS `REL_WINE_VINEYARD_EXTENDED`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `REL_WINE_VINEYARD_EXTENDED` (`WINE_ID`, `WINE_NAME`, `WINE_YEAR`, `WINE_PRODUCER`, `WINE_TECHNOTES`, `VINEYARD_ID`, `VINEYARD_NAME`) AS   select `W`.`ID` AS `ID`,`W`.`NAME` AS `NAME`,`W`.`YEAR` AS `YEAR`,`W`.`PRODUCER` AS `PRODUCER`,`W`.`TECHNOTES` AS `TECHNOTES`,`WY`.`ID` AS `ID`,`WY`.`NAME` AS `NAME` from ((`WINE` `W` left join `REL_WINE_VINEYARD` `r0` on((`r0`.`WINE_ID` = `W`.`ID`))) left join `VINEYARD` `WY` on((`r0`.`VINEYARD_ID` = `WY`.`ID`)))  ;

-- --------------------------------------------------------

--
-- Structure for view `REL_WINE_WAREHOUSE_EXTENDED`
--
DROP TABLE IF EXISTS `REL_WINE_WAREHOUSE_EXTENDED`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `REL_WINE_WAREHOUSE_EXTENDED` (`WINESHOP_ID`, `WINESHOP_NAME`, `WAREHOUSE_ID`, `WAREHOUSE_NAME`, `WINE_ID`, `WINE_NAME`, `WINE_YEAR`, `WINE_PRODUCER`, `WINE_TECHNOTES`, `VINEYARD_ID`, `VINEYARD_NAME`) AS   select `ws`.`ID` AS `ID`,`ws`.`NAME` AS `NAME`,`wh`.`ID` AS `ID`,`ws`.`NAME` AS `NAME`,`w`.`ID` AS `ID`,`w`.`NAME` AS `NAME`,`w`.`YEAR` AS `YEAR`,`w`.`PRODUCER` AS `PRODUCER`,`w`.`TECHNOTES` AS `TECHNOTES`,`VY`.`ID` AS `ID`,`VY`.`NAME` AS `NAME` from ((((((`REL_WINE_WAREHOUSE` `r0` join `REL_WINESHOP_WAREHOUSE` `r1` on((`r1`.`ID_WAREHOUSE` = `r0`.`WAREHOUSE_ID`))) join `WAREHOUSE` `wh` on((`wh`.`ID` = `r0`.`WAREHOUSE_ID`))) join `WINESHOP` `ws` on((`ws`.`ID` = `r1`.`ID_WINESHOP`))) join `WINE` `w` on(((`w`.`ID` = `r0`.`WINE_ID`) and (`r0`.`WINE_ID` = `w`.`ID`)))) left join `REL_WINE_VINEYARD` `r2` on((`r2`.`WINE_ID` = `w`.`ID`))) left join `VINEYARD` `VY` on((`VY`.`ID` = `r2`.`VINEYARD_ID`)))  ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ORDER_ITEM`
--
ALTER TABLE `ORDER_ITEM`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `REL_ORDER_USER`
--
ALTER TABLE `REL_ORDER_USER`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `REL_ORDER_WINE`
--
ALTER TABLE `REL_ORDER_WINE`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `REL_USER_WINESHOP`
--
ALTER TABLE `REL_USER_WINESHOP`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `REL_WINESHOP_WAREHOUSE`
--
ALTER TABLE `REL_WINESHOP_WAREHOUSE`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `REL_WINE_VINEYARD`
--
ALTER TABLE `REL_WINE_VINEYARD`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `REL_WINE_WAREHOUSE`
--
ALTER TABLE `REL_WINE_WAREHOUSE`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `USER`
--
ALTER TABLE `USER`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `VINEYARD`
--
ALTER TABLE `VINEYARD`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `WAREHOUSE`
--
ALTER TABLE `WAREHOUSE`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `WINE`
--
ALTER TABLE `WINE`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `WINESHOP`
--
ALTER TABLE `WINESHOP`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ORDER_ITEM`
--
ALTER TABLE `ORDER_ITEM`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=75;

--
-- AUTO_INCREMENT for table `REL_ORDER_USER`
--
ALTER TABLE `REL_ORDER_USER`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT for table `REL_ORDER_WINE`
--
ALTER TABLE `REL_ORDER_WINE`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1576;

--
-- AUTO_INCREMENT for table `REL_USER_WINESHOP`
--
ALTER TABLE `REL_USER_WINESHOP`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=388;

--
-- AUTO_INCREMENT for table `REL_WINESHOP_WAREHOUSE`
--
ALTER TABLE `REL_WINESHOP_WAREHOUSE`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=322;

--
-- AUTO_INCREMENT for table `REL_WINE_VINEYARD`
--
ALTER TABLE `REL_WINE_VINEYARD`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24386;

--
-- AUTO_INCREMENT for table `REL_WINE_WAREHOUSE`
--
ALTER TABLE `REL_WINE_WAREHOUSE`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27192;

--
-- AUTO_INCREMENT for table `USER`
--
ALTER TABLE `USER`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=481;

--
-- AUTO_INCREMENT for table `VINEYARD`
--
ALTER TABLE `VINEYARD`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=234;

--
-- AUTO_INCREMENT for table `WAREHOUSE`
--
ALTER TABLE `WAREHOUSE`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=331;

--
-- AUTO_INCREMENT for table `WINE`
--
ALTER TABLE `WINE`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27711;

--
-- AUTO_INCREMENT for table `WINESHOP`
--
ALTER TABLE `WINESHOP`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=323;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
