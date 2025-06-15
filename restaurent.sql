-- phpMyAdmin SQL Dump
-- version 5.2.1deb3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 15, 2025 at 09:44 AM
-- Server version: 10.11.13-MariaDB-0ubuntu0.24.04.1
-- PHP Version: 8.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `restaurent`
--

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `category` varchar(100) NOT NULL,
  `size` varchar(50) NOT NULL,
  `price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`id`, `name`, `category`, `size`, `price`) VALUES
(1, 'Zinger 2', 'Burger', 'Medium', 150.00),
(2, 'Chicken', 'Biryani', 'Large', 200.00),
(7, 'French', 'Fries', 'Medium', 120.00);

--
-- Triggers `menu`
--
DELIMITER $$
CREATE TRIGGER `before_delete_menu` BEFORE DELETE ON `menu` FOR EACH ROW BEGIN
    INSERT INTO menu_log (id, name, price, category, size)
    VALUES (OLD.id, OLD.name, OLD.price, OLD.category, OLD.size)$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `special_instructions` text NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `table_id` int(11) NOT NULL,
  `date` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `name`, `email`, `special_instructions`, `amount`, `table_id`, `date`) VALUES
(1, 'Mudasser Khan', 'kmudasser44@gmail.com', '', 0.00, 5, '2025-06-14'),
(2, 'Mudasser Khan', 'kmudasser44@gmail.com', '', 760.00, 1, '2025-06-14'),
(3, 'Mudasser Khan', 'kmudasser44@gmail.com', '', 200.00, 1, '2025-06-14'),
(4, 'Zainab', 'zaini@gmail.com', '', 960.00, 2, '2025-06-14'),
(5, 'Zainab', 'zaini@gmail.com', '', 960.00, 2, '2025-06-14'),
(6, 'third', 'thir@jsd.com', '', 200.00, 1, '2025-06-14'),
(7, 'kj', 'hgd@sdkjjg.com', '', 200.00, 2, '2025-06-14'),
(8, 'Mudasser Khan', 'kmudasser44@gmail.com', '', 760.00, 1, '2025-06-14'),
(9, 'hi', 'hi@tma.com', '', 200.00, 1, '2025-06-14'),
(10, 'jk', 'kjgh@kjhf.com', '', 760.00, 1, '2025-06-14'),
(11, 'Mudasser Khan', 'kmudasser44@gmail.com', '', 200.00, 0, '2025-06-14'),
(12, 'asdj', 'dsjkg@fdjsa.com', '', 200.00, 0, '2025-06-14'),
(13, 'Mudasser Khan', 'ajsdhk@lkafe.com', '', 200.00, 0, '2025-06-14'),
(14, 'Mudasser Khan', 'ikhksfdaj@s.com', '', 200.00, 1, '2025-06-14'),
(15, 'Ammara Aslam', 'ammara@gmail.com', 'hi', 860.00, 0, '2025-06-15'),
(16, 'Mudasser Khan', 'kmudasser44@gmail.com', 'hellow', 470.00, 1, '2025-06-15'),
(17, 'Zainab', 'zaini@gmail.com', 'hellow', 620.00, 1, '2025-06-15');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
