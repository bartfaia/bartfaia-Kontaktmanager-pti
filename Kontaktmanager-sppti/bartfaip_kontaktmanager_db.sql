-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Gép: localhost:3306
-- Létrehozás ideje: 2024. Nov 20. 12:41
-- Kiszolgáló verziója: 10.6.19-MariaDB-cll-lve
-- PHP verzió: 8.3.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `bartfaip_kontaktmanager_db`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `nevjegyek`
--

CREATE TABLE `nevjegyek` (
  `azonosito` int(11) NOT NULL,
  `telefonszam` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `megszolitas` varchar(20) DEFAULT NULL,
  `titulus` varchar(20) DEFAULT NULL,
  `vezeteknev` varchar(50) DEFAULT NULL,
  `keresztnev` varchar(50) DEFAULT NULL,
  `utonev` varchar(50) DEFAULT NULL,
  `iranyitoszam` varchar(10) DEFAULT NULL,
  `telepules` varchar(50) DEFAULT NULL,
  `utca` varchar(100) DEFAULT NULL,
  `hazszam` varchar(10) DEFAULT NULL,
  `emelet_ajto` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `nevjegyek`
--

INSERT INTO `nevjegyek` (`azonosito`, `telefonszam`, `email`, `megszolitas`, `titulus`, `vezeteknev`, `keresztnev`, `utonev`, `iranyitoszam`, `telepules`, `utca`, `hazszam`, `emelet_ajto`) VALUES
(4, '+36123456789', 'peter.kiss@example.com', 'Mr.', 'Dr.', 'Kiss', 'Péter', 'András', '1111', 'Budapest', 'Kossuth Lajos utca', '2A', '2/3'),
(5, '+36301239876', 'eva.toth@example.com', 'Mrs.', NULL, 'Tóth', 'Éva', 'Mária', '9023', 'Győr', 'Baross Gábor utca', '12', NULL),
(6, '+36703455678', 'janos.veres@example.com', NULL, NULL, 'Veres', 'János', NULL, '7624', 'Pécs', 'Árpád utca', '6', '3/5'),
(7, '+36904561234', 'klara.nagy@example.com', 'Ms.', 'Prof.', 'Nagy', 'Klára', NULL, '6000', 'Kecskemét', 'Petőfi Sándor utca', '10', NULL),
(8, '+36875643210', 'david.horvath@example.com', NULL, NULL, 'Horváth', 'Dávid', 'Pál', '4032', 'Debrecen', 'Nagyerdő utca', '15B', '1/1');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `nevjegyek`
--
ALTER TABLE `nevjegyek`
  ADD PRIMARY KEY (`azonosito`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `nevjegyek`
--
ALTER TABLE `nevjegyek`
  MODIFY `azonosito` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
