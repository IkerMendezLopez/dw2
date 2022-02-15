-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 22-11-2017 a las 21:04:54
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `bdbus`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `buses`
--

CREATE TABLE IF NOT EXISTS `buses` (
  `Id_Placa` char(10) NOT NULL,
  `Capacidad` int(10) unsigned NOT NULL,
  `imagen` varchar(200) NOT NULL,
  PRIMARY KEY (`Id_Placa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `buses`
--

INSERT INTO `buses` (`Id_Placa`, `Capacidad`, `imagen`) VALUES
('WMX-0001', 44, 'mercedes 1721.jpg'),
('WMX-0002', 40, 'otokar 2130.jpg'),
('WMX-0003', 35, 'tata 8000.jpg'),
('WMX-0004', 41, 'volvo b12.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE IF NOT EXISTS `clientes` (
  `Id_DNI` char(10) NOT NULL,
  `Nombre` char(60) NOT NULL,
  `Direccion` char(60) NOT NULL,
  `Email` text NOT NULL,
  PRIMARY KEY (`Id_DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`Id_DNI`, `Nombre`, `Direccion`, `Email`) VALUES
('10845812', 'Jesus Maita', 'Av. Los portales 754', 'jmaita@gmail.com'),
('10858871', 'Nicolas Sanchez', 'Av. Ica 578', 'utnico@gmail.com'),
('30190900', 'Pedro Orbea', 'Paseo Acacias 309 - Lima', 'porbea@correo.pe'),
('41352696', 'Carlos Morales', 'Jr.Aguarico 875', 'cmorales@gmail.com'),
('41522188', 'Lesly Briceno', 'Calle Larco 111', 'lbricen.b@gmail.com'),
('42539687', 'Martin Ramirez', 'Jr.Aguarico 555', 'mramirez@gmail.com'),
('42558685', 'Juan Carlos Damian', 'Jr. Iquitos 235', 'jdamian@gmail.com'),
('44531258', 'Yessel Briceno', 'Jr.Aguarico 649', 'ybricen.b@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE IF NOT EXISTS `reservas` (
  `Id_Ticket` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Pagado` tinyint(1) NOT NULL,
  `NumAsiento` int(10) unsigned NOT NULL,
  `Id_DNI` char(10) NOT NULL,
  `Id_Ruta` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id_Ticket`),
  KEY `FK_reservas_1` (`Id_DNI`),
  KEY `FK_reservas_2` (`Id_Ruta`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=31 ;

--
-- Volcado de datos para la tabla `reservas`
--

INSERT INTO `reservas` (`Id_Ticket`, `Pagado`, `NumAsiento`, `Id_DNI`, `Id_Ruta`) VALUES
(1, 0, 1, '44531258', 1),
(2, 0, 1, '41522188', 2),
(3, 0, 1, '10845812', 3),
(4, 0, 1, '41352696', 4),
(5, 0, 2, '10845812', 1),
(6, 1, 2, '30190900', 2),
(7, 1, 3, '30190900', 2),
(8, 1, 4, '30190900', 2),
(9, 1, 5, '30190900', 2),
(10, 1, 6, '30190900', 2),
(11, 1, 39, '44531258', 6),
(12, 1, 36, '44531258', 6),
(13, 1, 31, '44531258', 6),
(14, 1, 18, '30190900', 1),
(15, 1, 22, '30190900', 1),
(16, 1, 43, '30190900', 5),
(17, 1, 15, '30190900', 5),
(18, 1, 12, '30190900', 5),
(19, 1, 15, '10845812', 7),
(20, 1, 23, '10845812', 7),
(21, 1, 31, '10845812', 7),
(22, 1, 27, '10845812', 7),
(23, 1, 35, '10845812', 7),
(24, 1, 1, '10845812', 9),
(25, 1, 2, '10845812', 9),
(26, 1, 3, '10845812', 9),
(27, 1, 19, '10845812', 9),
(28, 1, 20, '10845812', 9),
(29, 1, 43, '10845812', 7),
(30, 1, 44, '10845812', 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rutas`
--

CREATE TABLE IF NOT EXISTS `rutas` (
  `Id_Ruta` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CiudadOrigen` char(60) NOT NULL,
  `CiudadDestino` char(60) NOT NULL,
  `HoraSalida` datetime NOT NULL,
  `HoraLlegada` datetime NOT NULL,
  `Tarifa` double NOT NULL,
  `Id_Placa` char(10) NOT NULL,
  PRIMARY KEY (`Id_Ruta`),
  KEY `FK_rutas_1` (`Id_Placa`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Volcado de datos para la tabla `rutas`
--

INSERT INTO `rutas` (`Id_Ruta`, `CiudadOrigen`, `CiudadDestino`, `HoraSalida`, `HoraLlegada`, `Tarifa`, `Id_Placa`) VALUES
(1, 'Tumbes', 'Barranca', '2018-05-11 11:40:00', '2018-05-25 00:00:00', 74, 'WMX-0002'),
(2, 'Lima', 'Ica', '2018-05-11 09:36:00', '2018-05-25 14:00:00', 88, 'WMX-0004'),
(3, 'Chiclayo', 'Arequipa', '2018-05-26 09:00:00', '2018-05-26 00:00:00', 45, 'WMX-0003'),
(4, 'Lima', 'Barranca', '2017-10-09 18:00:00', '2017-10-09 21:40:00', 35, 'WMX-0002'),
(5, 'Tumbes', 'Arequipa', '2018-05-28 20:15:00', '2018-05-28 00:00:00', 86, 'WMX-0001'),
(6, 'Tumbes', 'Barranca', '2018-01-05 13:10:00', '2018-01-06 14:21:00', 22, 'WMX-0003'),
(7, 'Chiclayo', 'Barranca', '2018-01-22 08:30:00', '2018-01-22 10:00:00', 14, 'WMX-0001'),
(8, 'Tumbes', 'Barranca', '2018-01-16 18:45:00', '2018-01-16 20:30:00', 25, 'WMX-0003'),
(9, 'Chiclayo', 'Ica', '2018-01-22 06:30:00', '2018-01-23 00:00:00', 56, 'WMX-0004');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `FK_reservas_1` FOREIGN KEY (`Id_DNI`) REFERENCES `clientes` (`Id_DNI`),
  ADD CONSTRAINT `FK_reservas_2` FOREIGN KEY (`Id_Ruta`) REFERENCES `rutas` (`Id_Ruta`);

--
-- Filtros para la tabla `rutas`
--
ALTER TABLE `rutas`
  ADD CONSTRAINT `FK_rutas_1` FOREIGN KEY (`Id_Placa`) REFERENCES `buses` (`Id_Placa`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
