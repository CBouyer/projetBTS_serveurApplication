-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           8.0.30 - MySQL Community Server - GPL
-- SE du serveur:                Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Listage de la structure de la base pour application_serveurweb
DROP DATABASE IF EXISTS `application_serveurweb`;
CREATE DATABASE IF NOT EXISTS `application_serveurweb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `application_serveurweb`;

-- Listage de la structure de table application_serveurweb. admin
DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
                                       `idadmin` int NOT NULL AUTO_INCREMENT,
                                       `username` varchar(50) NOT NULL,
                                       `password` varchar(50) NOT NULL,
                                       `role` varchar(50) DEFAULT NULL,
                                       PRIMARY KEY (`idadmin`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table application_serveurweb.admin : ~0 rows (environ)
DELETE FROM `admin`;
INSERT INTO `admin` (`idadmin`, `username`, `password`, `role`) VALUES
    (1, 'admin', 'Admintest07!', 'admin');

-- Listage de la structure de table application_serveurweb. capteur
DROP TABLE IF EXISTS `capteur`;
CREATE TABLE IF NOT EXISTS `capteur` (
                                         `idcapteur` int NOT NULL AUTO_INCREMENT,
                                         `longitude` varchar(50) NOT NULL,
                                         `latitude` varchar(50) NOT NULL,
                                         `consommation` varchar(50) NOT NULL,
                                         PRIMARY KEY (`idcapteur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table application_serveurweb.capteur : ~0 rows (environ)
DELETE FROM `capteur`;

-- Listage de la structure de table application_serveurweb. info
DROP TABLE IF EXISTS `info`;
CREATE TABLE IF NOT EXISTS `info` (
                                      `id` int NOT NULL AUTO_INCREMENT,
                                      `username` varchar(50) DEFAULT NULL,
                                      KEY `Index 1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table application_serveurweb.info : ~0 rows (environ)
DELETE FROM `info`;

-- Listage de la structure de table application_serveurweb. prix
DROP TABLE IF EXISTS `prix`;
CREATE TABLE IF NOT EXISTS `prix` (
                                      `idprix` int NOT NULL AUTO_INCREMENT,
                                      `prix` varchar(50) NOT NULL,
                                      PRIMARY KEY (`idprix`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table application_serveurweb.prix : ~0 rows (environ)
DELETE FROM `prix`;

-- Listage de la structure de table application_serveurweb. user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
                                      `id_user` int NOT NULL AUTO_INCREMENT,
                                      `role` varchar(20) NOT NULL,
                                      `username` varchar(50) NOT NULL,
                                      `password` varchar(50) NOT NULL,
                                      PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table application_serveurweb.user : ~2 rows (environ)
DELETE FROM `user`;
INSERT INTO `user` (`id_user`, `username`, `password`, `role`) VALUES
    (2, 'user', 'Testuser07!', 'user');


/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
