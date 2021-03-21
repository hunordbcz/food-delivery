-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Mar 21, 2021 at 08:42 PM
-- Server version: 8.0.23-0ubuntu0.20.04.1
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `food_delivery`
--
DROP DATABASE IF EXISTS `food_delivery`;
CREATE DATABASE IF NOT EXISTS `food_delivery` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `food_delivery`;

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `id` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id`, `name`, `price`, `is_deleted`) VALUES
(20, 'Crunchy Box', 31, 0),
(21, 'Meniu Cheesy Bacon Fries Burrito', 28, 0),
(22, 'Meniu 2 Tacos Supreme', 24, 0),
(23, 'Mix box', 31, 0),
(24, 'Meniu 2x Chalupa', 28, 0),
(25, 'Meniu Crunchwrap Supreme', 27, 0),
(26, 'Pizza Margherita', 29, 0),
(27, 'Pizza Cotto e Funghi', 31, 0),
(28, 'Pizza Quattro Stagioni', 32, 0),
(29, 'Pizza Prosciutto Cotto', 31, 0),
(30, 'Pizza Capricciosa', 32, 0),
(31, 'Pizza al Salame Piccante', 32, 0),
(32, 'Apa plata Dorna - 500ml', 6, 0),
(33, 'Coca-Cola - 250ml', 8, 0),
(34, 'Sprite - 250ml', 8, 0),
(35, 'Paulaner - 500ml', 15, 0),
(36, 'Peroni - 330ml', 10, 0),
(37, 'Apa minerala Dorna - 500ml', 6, 0),
(38, 'Fanta - 250ml', 8, 0),
(39, 'Schweppes - 250ml', 8, 0),
(40, 'Paulaner fara alcool - 500ml', 15, 0),
(41, 'RED BULL', 12, 0),
(42, 'Sos de rosii', 5, 0),
(43, 'Sos de usturoi', 5, 0),
(44, 'Sos Calabrese', 5, 0),
(45, 'Ulei picant', 5, 0),
(46, 'Tagliatelle al ragu', 24.75, 0),
(47, 'Penne Quattro Formaggi', 27, 0),
(48, 'Spaghetti Carbonara', 24, 0),
(49, 'Meniu classic burger', 36, 0),
(50, 'Shaorma la farfurie', 24, 0),
(51, 'Shaorma la lipie mare', 22, 0),
(52, 'Shaorma la lipie mica', 20, 0);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int NOT NULL,
  `items_list` varchar(100) DEFAULT NULL,
  `user_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `delivery_address` varchar(100) DEFAULT NULL,
  `is_processed` tinyint(1) DEFAULT '0',
  `payment_type` int DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  `discount` double DEFAULT '0',
  `total` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `items_list`, `user_id`, `created_at`, `delivery_address`, `is_processed`, `payment_type`, `is_deleted`, `discount`, `total`) VALUES
(9, '26,27,28,29,33,33', 5, '2021-03-21 18:16:55', 'Capitala 21', 1, 0, 0, 6.95, 132.05),
(10, '22,20', 5, '2021-03-21 18:19:02', 'Capitala 21', 0, 1, 0, 2.75, 52.25);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `nr_identity` varchar(100) DEFAULT NULL,
  `cnp` int DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `is_admin` tinyint(1) DEFAULT '0',
  `is_loyal` tinyint(1) DEFAULT '0',
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `is_deleted` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `nr_identity`, `cnp`, `address`, `is_admin`, `is_loyal`, `username`, `password`, `is_deleted`) VALUES
(1, 'Administrator', NULL, NULL, NULL, 1, NULL, 'admin', 'secret', 0),
(5, 'Hunor', '222222', 1990729, 'Capitala 21', 0, 1, 'user', '123', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
