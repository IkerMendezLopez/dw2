-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-11-2020 a las 23:34:21
-- Versión del servidor: 5.7.14
-- Versión de PHP: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdcursos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aulas`
--

CREATE TABLE `aulas` (
  `IDEDIFICIO` char(7) NOT NULL,
  `NUMERO` int(2) NOT NULL,
  `CAPACIDAD` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `aulas`
--

INSERT INTO `aulas` (`IDEDIFICIO`, `NUMERO`, `CAPACIDAD`) VALUES
('CSUR03', 1, 50),
('CSUR03', 2, 30),
('CSUR03', 3, 100),
('MUN01', 1, 100),
('MUN01', 2, 75),
('MUN01', 3, 28),
('MUN01', 4, 15),
('PATR01', 1, 110),
('PATR01', 2, 100),
('PATR01', 3, 60),
('PATR01', 4, 40),
('TOR02', 1, 24),
('TOR02', 2, 15),
('TOR02', 3, 45),
('TOR02', 4, 70),
('TOR02', 5, 90);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursos`
--

CREATE TABLE `cursos` (
  `IDCURSO` int(11) NOT NULL,
  `IDEDIFICIO` char(7) NOT NULL,
  `NUM_AULA` int(2) NOT NULL,
  `DIA` date NOT NULL,
  `HORA` time NOT NULL,
  `IDTEMA` varchar(10) NOT NULL,
  `ASISTENTES` int(11) DEFAULT NULL,
  `PRECIO` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cursos`
--

INSERT INTO `cursos` (`IDCURSO`, `IDEDIFICIO`, `NUM_AULA`, `DIA`, `HORA`, `IDTEMA`, `ASISTENTES`, `PRECIO`) VALUES
(1, 'PATR01', 1, '2021-05-29', '17:00:00', 'T00001', 8, 40),
(2, 'PATR01', 3, '2020-12-12', '21:00:00', 'T00002', 0, 20),
(3, 'PATR01', 1, '2020-11-20', '17:00:00', 'T00001', 10, 30.5),
(4, 'PATR01', 1, '2020-11-22', '11:00:00', 'T00002', 0, 18.5),
(5, 'PATR01', 1, '2021-05-29', '15:00:00', 'T00011', 1, 140),
(7, 'PATR01', 1, '2021-05-28', '17:00:00', 'T00007', 22, 88),
(8, 'PATR01', 1, '2021-05-28', '10:00:00', 'T00009', 12, 80.5),
(9, 'PATR01', 1, '2021-05-29', '21:00:00', 'T00009', 3, 65),
(11, 'PATR01', 2, '2020-11-21', '19:00:00', 'T00001', 110, 40),
(12, 'PATR01', 2, '2021-05-29', '09:00:00', 'T00002', 15, 19),
(13, 'PATR01', 2, '2021-05-29', '19:00:00', 'T00001', 20, 60.5),
(14, 'PATR01', 2, '2021-05-29', '17:00:00', 'T00002', 10, 18),
(15, 'PATR01', 2, '2020-11-29', '15:00:00', 'T00011', 0, 99),
(16, 'PATR01', 2, '2021-05-29', '08:00:00', 'T00007', 11, 92),
(17, 'PATR01', 2, '2020-12-13', '19:00:00', 'T00009', 0, 83.5),
(18, 'PATR01', 2, '2021-05-29', '10:00:00', 'T00009', 6, 55),
(20, 'PATR01', 3, '2020-11-20', '17:00:00', 'T00008', 0, 21.5),
(21, 'PATR01', 3, '2021-05-29', '21:00:00', 'T00010', 13, 66),
(22, 'PATR01', 3, '2021-05-29', '13:00:00', 'T00010', 14, 110),
(23, 'PATR01', 3, '2021-05-29', '15:00:00', 'T00011', 3, 130),
(24, 'PATR01', 3, '2021-02-05', '17:00:00', 'T00007', 0, 74),
(25, 'PATR01', 4, '2021-05-29', '10:00:00', 'T00008', 10, 24),
(26, 'PATR01', 4, '2020-11-21', '21:00:00', 'T00010', 0, 79),
(27, 'PATR01', 4, '2020-11-22', '21:00:00', 'T00010', 0, 68.5),
(28, 'PATR01', 4, '2020-11-29', '15:00:00', 'T00011', 0, 75.5),
(29, 'PATR01', 4, '2021-05-29', '19:00:00', 'T00007', 10, 82),
(30, 'TOR02', 1, '2020-11-20', '15:00:00', 'T00010', 4, 80),
(31, 'TOR02', 1, '2021-05-29', '17:00:00', 'T00003', 12, 39.5),
(32, 'TOR02', 4, '2021-05-29', '17:00:00', 'T00003', 1, 54),
(33, 'TOR02', 1, '2021-05-29', '08:00:00', 'T00005', 11, 77.5),
(34, 'TOR02', 3, '2021-05-29', '17:00:00', 'T00005', 10, 66),
(35, 'TOR02', 1, '2020-12-12', '17:00:00', 'T00009', 0, 60),
(36, 'TOR02', 1, '2020-12-13', '17:00:00', 'T00009', 0, 77.4),
(41, 'CSUR03', 3, '2021-05-29', '17:00:00', 'T00001', 10, 50),
(42, 'CSUR03', 1, '2020-11-22', '17:00:00', 'T00001', 53, 55.5),
(43, 'CSUR03', 1, '2020-11-28', '17:00:00', 'T00003', 0, 38),
(44, 'CSUR03', 1, '2020-11-20', '17:00:00', 'T00003', 15, 45),
(45, 'CSUR03', 1, '2021-05-29', '17:00:00', 'T00006', 4, 75),
(46, 'CSUR03', 1, '2021-05-29', '18:00:00', 'T00007', 9, 84.5),
(47, 'CSUR03', 1, '2021-05-29', '21:00:00', 'T00006', 2, 69),
(48, 'CSUR03', 1, '2021-02-06', '21:00:00', 'T00007', 0, 90),
(53, 'MUN01', 2, '2021-05-29', '17:00:00', 'T00001', 5, 50),
(54, 'MUN01', 1, '2021-05-29', '17:00:00', 'T00004', 23, 122),
(55, 'MUN01', 1, '2020-11-29', '13:00:00', 'T00004', 0, 120),
(58, 'MUN01', 2, '2021-05-29', '18:00:00', 'T00001', 8, 88),
(59, 'MUN01', 3, '2020-11-20', '17:00:00', 'T00008', 18, 13),
(61, 'MUN01', 4, '2021-05-29', '17:00:00', 'T00008', 7, 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `edificios`
--

CREATE TABLE `edificios` (
  `IDEDIFICIO` char(7) NOT NULL,
  `NOMBRE` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `edificios`
--

INSERT INTO `edificios` (`IDEDIFICIO`, `NOMBRE`) VALUES
('CSUR03', 'Centro de Congresos Sur'),
('MUN01', 'Centro Municipal'),
('PATR01', 'Palacio Atrium'),
('TOR02', 'Torre Picasso');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `temas`
--

CREATE TABLE `temas` (
  `IDTEMA` varchar(10) NOT NULL,
  `TEMA` varchar(50) NOT NULL,
  `CATEGORIA` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `temas`
--

INSERT INTO `temas` (`IDTEMA`, `TEMA`, `CATEGORIA`) VALUES
('T00001', 'Seguridad En Redes IP', 'Redes'),
('T00002', 'AJAX, jQuery, HTML5 y CSS3', 'Programación'),
('T00003', 'Protocolos de enrutamiento', 'Redes'),
('T00004', 'Redes Wireless', 'Redes'),
('T00005', 'Excel 2013', 'Ofimática'),
('T00006', 'Auditoria de cuentas', 'Finanzas'),
('T00007', 'Cableado estructurado', 'Redes'),
('T00008', 'SAP R/3', 'Finanzas'),
('T00009', 'JQuery Mobile', 'Programación'),
('T00010', 'Gestion de tesoreria', 'Finanzas'),
('T00011', 'Enterprise Javabeans', 'Programación');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `aulas`
--
ALTER TABLE `aulas`
  ADD PRIMARY KEY (`IDEDIFICIO`,`NUMERO`);

--
-- Indices de la tabla `cursos`
--
ALTER TABLE `cursos`
  ADD PRIMARY KEY (`IDCURSO`),
  ADD KEY `FK_FUNCION__PELICULA` (`IDTEMA`),
  ADD KEY `FK_FUNCION__SALA` (`IDEDIFICIO`,`NUM_AULA`);

--
-- Indices de la tabla `edificios`
--
ALTER TABLE `edificios`
  ADD PRIMARY KEY (`IDEDIFICIO`),
  ADD UNIQUE KEY `AK_CINE` (`NOMBRE`);

--
-- Indices de la tabla `temas`
--
ALTER TABLE `temas`
  ADD PRIMARY KEY (`IDTEMA`),
  ADD UNIQUE KEY `AK_PELICULA` (`TEMA`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cursos`
--
ALTER TABLE `cursos`
  MODIFY `IDCURSO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `aulas`
--
ALTER TABLE `aulas`
  ADD CONSTRAINT `FK_SALA__CINE` FOREIGN KEY (`IDEDIFICIO`) REFERENCES `edificios` (`IDEDIFICIO`);

--
-- Filtros para la tabla `cursos`
--
ALTER TABLE `cursos`
  ADD CONSTRAINT `FK_FUNCION__PELICULA` FOREIGN KEY (`IDTEMA`) REFERENCES `temas` (`IDTEMA`),
  ADD CONSTRAINT `FK_FUNCION__SALA` FOREIGN KEY (`IDEDIFICIO`,`NUM_AULA`) REFERENCES `aulas` (`IDEDIFICIO`, `NUMERO`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
