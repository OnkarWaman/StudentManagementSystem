-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 13, 2023 at 03:06 PM
-- Server version: 8.0.33
-- PHP Version: 7.4.3-4ubuntu2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `onkarwaman`
--

-- --------------------------------------------------------

--
-- Table structure for table `sms_admins`
--

CREATE TABLE `sms_admins` (
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `sms_admins`
--

INSERT INTO `sms_admins` (`username`, `password`) VALUES
('onkarwaman', 'onkarwaman');

-- --------------------------------------------------------

--
-- Table structure for table `sms_students`
--

CREATE TABLE `sms_students` (
  `ugid` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `branch` varchar(20) NOT NULL,
  `dob` date NOT NULL,
  `mobileno` varchar(10) NOT NULL,
  `email` varchar(30) NOT NULL,
  `address` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `sms_students`
--

INSERT INTO `sms_students` (`ugid`, `name`, `branch`, `dob`, `mobileno`, `email`, `address`) VALUES
(1, 'abc', 'Comp', '2002-01-01', '1234567890', 'abc@gmail.com', 'Pune'),
(2, 'def', 'IT', '2002-02-03', '2345678901', 'def@yahoo.com', 'Nashik'),
(3, 'xyz', 'E&TC', '2002-03-12', '3456789012', 'xyz@outlook.com', 'Nagar');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `sms_students`
--
ALTER TABLE `sms_students`
  ADD PRIMARY KEY (`ugid`),
  ADD UNIQUE KEY `ugid_UNIQUE` (`ugid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `sms_students`
--
ALTER TABLE `sms_students`
  MODIFY `ugid` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
