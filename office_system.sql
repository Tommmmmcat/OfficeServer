-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- 主机： 127.0.0.1
-- 生成日期： 2020-03-05 12:26:47
-- 服务器版本： 10.4.8-MariaDB
-- PHP 版本： 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `office_system`
--

-- --------------------------------------------------------

--
-- 表的结构 `attendance`
--

CREATE TABLE `attendance` (
  `User_Id` int(10) NOT NULL,
  `Date` date NOT NULL,
  `Start` date NOT NULL,
  `End` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `department`
--

CREATE TABLE `department` (
  `department_id` int(10) NOT NULL,
  `department_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `department`
--

INSERT INTO `department` (`department_id`, `department_name`) VALUES
(1, 'Marketing'),
(2, 'Software Development'),
(3, 'Management');

-- --------------------------------------------------------

--
-- 表的结构 `employee`
--

CREATE TABLE `employee` (
  `employee_id` int(10) NOT NULL,
  `employee_account` varchar(100) NOT NULL,
  `employee_name` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `position_id` int(10) DEFAULT NULL,
  `department_id` int(10) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `date` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `employee`
--

INSERT INTO `employee` (`employee_id`, `employee_account`, `employee_name`, `password`, `position_id`, `department_id`, `status`, `date`) VALUES
(65, 'D00198309', 'BO', '$2a$10$sSfa9GvbSxXuWDznResHh.dzVUp5g2sVzGWBE.aW6U8pfwGQ9PuWG', 1, 3, 2, ''),
(66, 'D00198320', 'JI', '$2a$10$OfrS9Hsx3dsJ9ijpwPhrbu1L6uoVBkML63/.OSRwMd.ce/Fh8SVeq', 1, 3, 2, '');

-- --------------------------------------------------------

--
-- 表的结构 `employee_details`
--

CREATE TABLE `employee_details` (
  `id` int(11) NOT NULL,
  `employee_id` int(200) NOT NULL,
  `genre` int(1) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `employee_details`
--

INSERT INTO `employee_details` (`id`, `employee_id`, `genre`, `birthday`, `email`, `address`) VALUES
(2, 65, 1, '1997-02-14', 'D00198309@student.dkit.ie', '224 Glenwood'),
(3, 66, 1, '1990-02-01', 'D00198320@student.dkit.ie', '224 Glenwood');

-- --------------------------------------------------------

--
-- 表的结构 `message`
--

CREATE TABLE `message` (
  `Message_Id` int(10) NOT NULL,
  `Sender` varchar(50) NOT NULL,
  `Receiver` int(10) NOT NULL,
  `Text` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `position`
--

CREATE TABLE `position` (
  `position_id` int(10) NOT NULL,
  `department_id` int(10) NOT NULL,
  `position_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `position`
--

INSERT INTO `position` (`position_id`, `department_id`, `position_name`) VALUES
(1, 3, 'HR'),
(2, 3, 'Accountant');

-- --------------------------------------------------------

--
-- 表的结构 `seeker`
--

CREATE TABLE `seeker` (
  `Seeker_Id` int(10) NOT NULL,
  `Seeker_Name` varchar(50) NOT NULL,
  `Seeker_Address` varchar(500) NOT NULL,
  `Phone_Number` varchar(100) NOT NULL,
  `DOB` date NOT NULL,
  `Education` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `vacation`
--

CREATE TABLE `vacation` (
  `User_Id` int(10) NOT NULL,
  `Date` date NOT NULL,
  `Type` varchar(50) NOT NULL,
  `Des` varchar(500) NOT NULL,
  `IsSalay` tinyint(1) NOT NULL,
  `Status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转储表的索引
--

--
-- 表的索引 `attendance`
--
ALTER TABLE `attendance`
  ADD KEY `User_Id` (`User_Id`);

--
-- 表的索引 `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`department_id`);

--
-- 表的索引 `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- 表的索引 `employee_details`
--
ALTER TABLE `employee_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- 表的索引 `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`Message_Id`),
  ADD KEY `Sender` (`Sender`),
  ADD KEY `Receiver` (`Receiver`);

--
-- 表的索引 `position`
--
ALTER TABLE `position`
  ADD PRIMARY KEY (`position_id`);

--
-- 表的索引 `seeker`
--
ALTER TABLE `seeker`
  ADD PRIMARY KEY (`Seeker_Id`);

--
-- 表的索引 `vacation`
--
ALTER TABLE `vacation`
  ADD KEY `User_Id` (`User_Id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `department`
--
ALTER TABLE `department`
  MODIFY `department_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- 使用表AUTO_INCREMENT `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- 使用表AUTO_INCREMENT `employee_details`
--
ALTER TABLE `employee_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- 使用表AUTO_INCREMENT `message`
--
ALTER TABLE `message`
  MODIFY `Message_Id` int(10) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `position`
--
ALTER TABLE `position`
  MODIFY `position_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用表AUTO_INCREMENT `seeker`
--
ALTER TABLE `seeker`
  MODIFY `Seeker_Id` int(10) NOT NULL AUTO_INCREMENT;

--
-- 限制导出的表
--

--
-- 限制表 `employee_details`
--
ALTER TABLE `employee_details`
  ADD CONSTRAINT `fk_employee_details` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
