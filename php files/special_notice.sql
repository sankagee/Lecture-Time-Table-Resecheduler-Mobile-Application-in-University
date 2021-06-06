-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 29, 2021 at 08:29 PM
-- Server version: 5.7.23
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lecture_scheduling`
--

-- --------------------------------------------------------

--
-- Table structure for table `special_notice`
--

DROP TABLE IF EXISTS `special_notice`;
CREATE TABLE IF NOT EXISTS `special_notice` (
  `message_no` int(1) NOT NULL AUTO_INCREMENT,
  `message` text NOT NULL,
  `message_date` datetime NOT NULL,
  `dep_batch` varchar(10) NOT NULL,
  `lecturer` varchar(20) NOT NULL,
  PRIMARY KEY (`message_no`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `special_notice`
--

INSERT INTO `special_notice` (`message_no`, `message`, `message_date`, `dep_batch`, `lecturer`) VALUES
(1, 'adsjadskjcbadjccjn', '2020-01-28 17:10:20', 'MTT1617', 'em010'),
(2, 'jdbasd sadhas jasbd as', '2020-01-28 17:10:20', 'ICT1617', 'em01'),
(3, 'today\'s lecture postpond', '2020-01-28 17:10:20', 'das', 'asd');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
