-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 10-12-2022 a las 16:14:23
-- Versión del servidor: 5.7.36
-- Versión de PHP: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `productos`
--

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `registrar`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `registrar` (`Producto` VARCHAR(30), `Cantidad` DECIMAL(5,2), `Precio` DECIMAL(5,2), `Precio_kg` DECIMAL(5,2))  INSERT INTO productos(producto, cantidad_kilos, precio, precio_por_kg) VALUES (Producto, Cantidad, Precio, Precio_kg)$$

DROP PROCEDURE IF EXISTS `registro_emp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `registro_emp` (`id` INT(4), `nombre` VARCHAR(50), `apellido` VARCHAR(50), `dni` INT(10), `sueldo` INT(10))  INSERT INTO empleados (ID, Nombre, Apellido, DNI, Sueldo) VALUES (id, nombre, apellido, dni, sueldo)$$

DROP PROCEDURE IF EXISTS `registro_menu`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `registro_menu` (`numero` INT(2), `ham` VARCHAR(50), `precio` DECIMAL(5,2))  INSERT INTO menu(num,hamburquesas,precio) VALUES (numero, ham, precio)$$

DROP PROCEDURE IF EXISTS `resgisto_bebidas`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `resgisto_bebidas` (`numero` INT(2), `bebidas` VARCHAR(50), `precios` DECIMAL(5,2))  INSERT INTO bebidas (num, bebida, precio) VALUES (numero, bebidas, precios)$$

DROP PROCEDURE IF EXISTS `res_pedidos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `res_pedidos` (`p_pedido` VARCHAR(50), `p_mesa` INT(2), `p_fecha` DATE)  INSERT INTO pedidos (pedido, mesa, fecha) VALUES (P_pedido, p_mesa, p_fecha)$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bebidas`
--

DROP TABLE IF EXISTS `bebidas`;
CREATE TABLE IF NOT EXISTS `bebidas` (
  `num` int(2) NOT NULL,
  `bebida` varchar(50) DEFAULT NULL,
  `precio` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`num`),
  UNIQUE KEY `u_bebida` (`bebida`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `bebidas`
--

INSERT INTO `bebidas` (`num`, `bebida`, `precio`) VALUES
(1, 'Coca-Cola al tiempo', '4.60'),
(2, 'Inka-Cola al tiempo ', '4.50'),
(3, 'pepsi al tiempo ', '5.50'),
(4, 'Coca-Cola helada', '4.60'),
(5, 'Inka-Cola helada', '4.50');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

DROP TABLE IF EXISTS `empleados`;
CREATE TABLE IF NOT EXISTS `empleados` (
  `ID` int(4) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Apellido` varchar(50) NOT NULL,
  `DNI` int(10) NOT NULL,
  `Sueldo` int(10) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `DNI` (`DNI`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`ID`, `Nombre`, `Apellido`, `DNI`, `Sueldo`) VALUES
(1001, 'Jesus', 'Machaca', 77147248, 500),
(1002, 'Gonzalo', 'Lopez', 77247526, 500),
(1003, 'Dalesca', 'Quispe', 7324528, 750),
(1004, 'Lucas', 'Ponce', 78632452, 500),
(1005, 'Royer', 'Moreno', 45234768, 500),
(1006, 'Manuel', 'Manzano', 234789, 500),
(1007, 'Daniel', 'Ortis', 8678590, 400);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `menu`
--

DROP TABLE IF EXISTS `menu`;
CREATE TABLE IF NOT EXISTS `menu` (
  `num` int(2) NOT NULL,
  `hamburquesas` varchar(100) NOT NULL,
  `precio` decimal(5,2) NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `menu`
--

INSERT INTO `menu` (`num`, `hamburquesas`, `precio`) VALUES
(1, 'Hamburguesa de pollo', '3.50'),
(2, 'Hamburguesa de carne', '3.50'),
(3, 'Hamburguesa de pollo y bacon', '8.00'),
(4, 'Hamburguesa de pollo y chorizo', '8.00'),
(5, 'Salchipapa', '9.50');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
CREATE TABLE IF NOT EXISTS `pedidos` (
  `pedido` varchar(50) NOT NULL,
  `mesa` int(2) NOT NULL,
  `cantidad` int(3) NOT NULL,
  `fecha` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`pedido`, `mesa`, `cantidad`, `fecha`) VALUES
('Hamburguesa de pollo y chorizo', 1, 3, '2022-12-09 10:30:41'),
('Hamburguesa de carne', 5, 2, '2022-12-09 10:30:50'),
('pepsi al tiempo ', 1, 2, '2022-12-09 10:31:00'),
('Hamburguesa de pollo y chorizo', 1, 4, '2022-12-10 09:34:45'),
('Coca-Cola helada', 1, 4, '2022-12-10 09:34:55');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

DROP TABLE IF EXISTS `productos`;
CREATE TABLE IF NOT EXISTS `productos` (
  `producto` varchar(20) NOT NULL,
  `cantidad_kilos` decimal(7,2) DEFAULT NULL,
  `precio` decimal(5,2) DEFAULT NULL,
  `precio_por_kg` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`producto`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`producto`, `cantidad_kilos`, `precio`, `precio_por_kg`) VALUES
('Harina', '50.00', '250.00', '5.10'),
('Pan', '50.00', '50.00', '1.00'),
('Cebolla', '50.00', '100.00', '2.50'),
('Carne', '50.00', '100.00', '5.50'),
('Pollo', '50.00', '100.00', '8.90'),
('Salchicha', '60.00', '725.00', '13.50'),
('Hot-Dog ', '50.00', '106.50', '1.30'),
('Papa', '50.00', '100.00', '3.60');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
