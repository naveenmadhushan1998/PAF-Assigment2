-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2020 at 07:00 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `healthcaremanagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE IF NOT EXISTS `payment` (
`payment_id` int(10) NOT NULL,
  `cardType` varchar(30) NOT NULL,
  `cardNo` varchar(16) NOT NULL,
  `cardHolderName` varchar(30) NOT NULL,
  `expiryDate` date NOT NULL,
  `time` varchar(10) NOT NULL,
  `date` date NOT NULL,
  `totalAmount` decimal(10,2) NOT NULL,
  `patient_id` int(10) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=602 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`payment_id`, `cardType`, `cardNo`, `cardHolderName`, `expiryDate`, `time`, `date`, `totalAmount`, `patient_id`) VALUES
(300, 'credit', '1234567891234567', 'Waruna', '2022-07-14', '4.30a.m.', '2020-04-15', '3500.00', 301),
(310, 'Visa', '4234.0', 'kk', '2022-07-14', '10.30a.m.', '2020-04-15', '2132.00', 301),
(321, 'Naveen', '2424535253', 'Spiderman', '2022-07-14', '10.30a.m.', '2020-04-15', '5000.00', 301),
(324, 'Master', '543543453', 'Naveen', '2022-07-14', '10.30a.m.', '2020-04-15', '2520.00', 301),
(505, 'credit', '1234567891234567', 'KK', '2022-07-14', '10.30a.m.', '2020-04-15', '3500.00', 301),
(543, 'Visa', '47572424142', 'Dayal', '2022-07-14', '10.30a.m.', '2020-04-15', '3500.00', 301),
(601, 'Debit', '4234.0', 'nimal', '2022-07-14', '10.30a.m.', '2020-04-15', '2132.00', 301);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
 ADD PRIMARY KEY (`payment_id`), ADD KEY `fk_payment_patient` (`patient_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
MODIFY `payment_id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=602;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
ADD CONSTRAINT `fk_payment_patient` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
