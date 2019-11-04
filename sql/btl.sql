-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 11, 2017 at 04:59 PM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `btl`
--

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `username` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `username`, `password`) VALUES
(1, 'admin', 'a'),
(2, 'sone', 'a'),
(3, 'hamchoiqh', 'a'),
(4, 'minhduc', 'a'),
(5, 'quangduc', 'a'),
(6, 'huydao', 'a'),
(7, 'ductinh', 'a');

-- --------------------------------------------------------

--
-- Table structure for table `lschat`
--

CREATE TABLE `lschat` (
  `stt` int(11) NOT NULL,
  `id1` int(11) NOT NULL,
  `id2` int(11) NOT NULL,
  `content` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `idsender` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `lschat`
--

INSERT INTO `lschat` (`stt`, `id1`, `id2`, `content`, `idsender`) VALUES
(1, 1, 2, 'Hello', 1),
(2, 1, 2, 'Xin Chào', 2),
(3, 1, 3, 'Hi!', 1),
(4, 1, 3, 'Hi Hi!', 3),
(5, 1, 3, 'Ahihi 1', 1),
(6, 1, 3, 'Ahihi 3', 3),
(7, 1, 2, 'Ahihi', 1),
(8, 1, 2, 'Okie hihi', 2),
(9, 2, 3, 'Okie em yêu', 3),
(10, 2, 3, 'Haha Hay', 2),
(11, 1, 2, 'Okie hihi', 2),
(12, 1, 2, 'Ahihi3', 1),
(13, 1, 2, 'Okie hihi3', 2),
(14, 1, 2, '<3', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `lschat`
--
ALTER TABLE `lschat`
  ADD PRIMARY KEY (`stt`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `lschat`
--
ALTER TABLE `lschat`
  MODIFY `stt` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
