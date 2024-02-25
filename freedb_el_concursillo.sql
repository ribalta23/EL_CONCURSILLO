-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: sql.freedb.tech
-- Tiempo de generación: 09-02-2024 a las 14:36:23
-- Versión del servidor: 8.0.36-0ubuntu0.22.04.1
-- Versión de PHP: 8.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `freedb_el_concursillo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categories`
--

CREATE TABLE `categories` (
  `ID_categoria` int NOT NULL,
  `categoria` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categories`
--

INSERT INTO `categories` (`ID_categoria`, `categoria`) VALUES
(1, 'Ciència'),
(2, 'Geografia'),
(3, 'Història'),
(4, 'Arts'),
(5, 'Esport'),
(6, 'Literatura'),
(7, 'Música'),
(8, 'Tecnologia'),
(9, 'Videojocs');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadistiques`
--

CREATE TABLE `estadistiques` (
  `ID_usuari` int NOT NULL,
  `victories` int DEFAULT NULL,
  `partides_jugades` int DEFAULT NULL,
  `preguntes_correctes` int DEFAULT NULL,
  `punts_competitius` int DEFAULT NULL,
  `punts_normals` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estadistiques`
--

INSERT INTO `estadistiques` (`ID_usuari`, `victories`, `partides_jugades`, `preguntes_correctes`, `punts_competitius`, `punts_normals`) VALUES
(1, 8, 30, 55, 15, 81),
(99, NULL, NULL, NULL, NULL, NULL),
(100, 0, 1, 2, 0, 1),
(101, 0, 2, 4, 0, 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partides`
--

CREATE TABLE `partides` (
  `ID_partida` int NOT NULL,
  `ID_usuari` int DEFAULT NULL,
  `mode_joc` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `respostes_correctes` int DEFAULT NULL,
  `respostes_incorrectes` int DEFAULT NULL,
  `puntuacio` int DEFAULT NULL,
  `data_hora_partida` datetime DEFAULT NULL,
  `num_preguntes` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `partides`
--

INSERT INTO `partides` (`ID_partida`, `ID_usuari`, `mode_joc`, `respostes_correctes`, `respostes_incorrectes`, `puntuacio`, `data_hora_partida`, `num_preguntes`) VALUES
(1, 100, 'normal', 2, 1, 7, '2024-02-05 21:05:01', 3),
(2, 1, 'normal', 2, 3, 11, '2024-02-09 12:59:56', 5),
(3, 1, 'competitivo', 14, 16, 14, '2024-02-09 13:01:06', 30),
(4, 1, 'competitivo', 9, 20, -50, '2024-02-09 13:22:09', 30),
(5, 1, 'competitivo', 6, 20, -62, '2024-02-09 13:26:35', 30),
(6, 1, 'competitivo', 0, 0, NULL, '2024-02-09 13:35:32', 30),
(7, 1, 'competitivo', 6, 24, -74, '2024-02-09 14:04:46', 30),
(8, 1, 'normal', 4, 1, 18, '2024-02-09 14:24:51', 0),
(9, 101, 'normal', 2, 1, 9, '2024-02-09 15:24:48', 3),
(10, 101, 'normal', 2, 8, 9, '2024-02-09 15:26:58', 10),
(11, 99, 'competitivo', 1, 2, 6, '2024-02-09 15:30:06', 30);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preguntes`
--

CREATE TABLE `preguntes` (
  `ID_pregunta` int NOT NULL,
  `pregunta` text COLLATE utf8mb4_general_ci NOT NULL,
  `ID_categoria` int DEFAULT NULL,
  `puntuacio` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `preguntes`
--

INSERT INTO `preguntes` (`ID_pregunta`, `pregunta`, `ID_categoria`, `puntuacio`) VALUES
(1, ' Quin es el jugador de futbol amb mes pilotes d\'or?', 1, 5),
(2, 'Quin riu és el més llarg del món?', 2, 4),
(3, 'En quin any va començar la Segona Guerra Mundial?', 3, 6),
(4, 'Qui va pintar la Mona Lisa?', 4, 5),
(5, 'Quin esport utilitza una pista ovalada?', 5, 3),
(6, 'Qui va escriure \"Cien años de soledad\"?', 6, 4),
(7, 'Quin instrument toca Yo-Yo Ma?', 7, 3),
(8, 'Quin any es va llançar el primer iPhone?', 8, 6),
(9, 'Quina és la capital de Japó?', 2, 2),
(10, 'Quina part del cos controla la respiració?', 1, 4),
(11, 'Quin planeta és conegut com el \"Planeta Vermell\"?', 1, 5),
(12, 'En quin continent es troba l\'Amazònia?', 2, 4),
(13, 'Qui va ser el primer president dels Estats Units?', 3, 6),
(14, 'Quin artista va crear la pinta \"La nit estrellada\"?', 4, 5),
(15, 'Quin esport es practica amb una pala i una pilota?', 5, 3),
(16, 'Quin escriptor va escriure \"1984\"?', 6, 4),
(17, 'Quin instrument utilitza una embocadura?', 7, 3),
(18, 'En quin any va ser llançat el primer ordinador personal?', 8, 6),
(19, 'Quina és la població més gran del món?', 2, 2),
(20, 'Quin òrgan produeix la insulina?', 1, 4),
(21, 'Quin metall és conegut com \"l\'or vermell\"?', 1, 5),
(22, 'Quin país té la forma d\'una bota a la península ibèrica?', 2, 4),
(23, 'En quin any va ser signada la Declaració d\'independència dels EUA?', 3, 6),
(24, 'Qui va pintar \"Les Menines\"?', 4, 5),
(25, 'Quin esport es coneix com \"l\'esport rei\"?', 5, 3),
(26, 'Qui va escriure \"Romeu i Julieta\"?', 6, 4),
(27, 'Quin instrument és conegut com \"el rei dels instruments\"?', 7, 3),
(28, 'En quin any es va realitzar el primer vol amb èxit dels germans Wright?', 8, 6),
(29, 'Quina és la capital d\'Espanya?', 2, 2),
(30, 'Quina part del cervell controla les emocions?', 1, 4),
(31, 'Quin gas és més abundant a l\'atmosfera de la Terra?', 1, 5),
(32, 'En quin continent es troba el desert del Kalahari?', 2, 4),
(33, 'Qui va ser el primer emperador de la Xina unificada?', 3, 6),
(34, 'Quina és la capital de França?', 4, 5),
(35, 'Quin esport utilitza un trampolí?', 5, 3),
(36, 'Qui va escriure \"Don Quijote de la Mancha\"?', 6, 4),
(37, 'Quin instrument té telescopis i es fa servir per observar els astres?', 7, 3),
(38, 'Quin any es va llançar el primer ordinador personal Macintosh?', 8, 6),
(39, 'Quina és la capital de França?', 2, 2),
(40, 'Quin tipus de teixit connecta els músculs amb els ossos?', 1, 4),
(41, 'Quin planeta és conegut com \"el planeta blau\"?', 1, 5),
(42, 'En quin país es troba el mont Everest?', 2, 4),
(43, 'En quin any es va signar el Tractat de Versalles que va posar fi a la Primera Guerra Mundial?', 3, 6),
(44, 'Qui va pintar \"El gernika\"?', 4, 5),
(45, 'Quin esport es coneix com \"l\'esport dels reis\"?', 5, 3),
(46, 'Qui va escriure \"Orgull i prejudici\"?', 6, 4),
(47, 'Quin instrument de vent es toca amb una boqueta?', 7, 3),
(48, 'En quin any es va llançar el primer telèfon intel·ligent Android?', 8, 6),
(49, 'Quina és la capital de Portugal?', 2, 2),
(50, 'Quina part del cos produeix la insulina?', 1, 4),
(51, 'Quin metall és conegut com \"l\'or blanc\"?', 1, 5),
(52, 'En quin país es va celebrar la primera Copa del Món de futbol?', 2, 4),
(53, 'En quin any va començar la Guerra de Vietnam?', 3, 6),
(54, 'Qui va ser el pintor del quadre \"La persistència de la memòria\"?', 4, 5),
(55, 'Quin esport es practica amb una raqueta i una pilota?', 5, 3),
(56, 'Qui va escriure \"Crònica de una mort anunciada\"?', 6, 4),
(57, 'Quants costats té un triangle?', 7, 3),
(58, 'En quin any es va llançar el primer ordinador personal IBM PC?', 8, 6),
(59, 'Quina és la capital de Suècia?', 2, 2),
(60, 'Quin animal té el cervell més gran en relació amb el seu cos?', 1, 4),
(61, 'Quin element químic té el símbol Hg?', 1, 5),
(62, 'Quin tipus de molècula emmagatzema l’energia en les cèl·lules?', 1, 3),
(63, 'Quina és la ciutat més poblada del món?', 2, 2),
(64, 'Quin país té més illes al món?', 2, 4),
(65, 'Quin riu és conegut com el \"ríu de la vida\"?', 2, 5),
(66, 'Qui va ser el líder de la Revolució Russa de 1917?', 3, 6),
(67, 'Quin esdeveniment històric va desencadenar la Revolució Francesa?', 3, 5),
(68, 'Quina dinastia va governar l`Imperi Romà durant el seu període més àlgid?', 3, 4),
(69, 'Qui va ser l’arquitecte de la Sagrada Família a Barcelona?', 4, 5),
(70, 'Quin moviment artístic va inspirar l’obra \"Guernica\" de Picasso?', 4, 6),
(71, 'Quin artista va pintar el sostre de la Capella Sixtina?', 4, 4),
(72, 'Quin país va guanyar la Copa del Món de futbol de 2018?', 5, 3),
(73, 'Quin esport es practica al llarg d’una corda suspenduda a una certa altura?', 5, 4),
(74, 'Qui va guanyar la medalla d’or en bàsquet als Jocs Olímpics de 1992?', 5, 5),
(75, 'Quin poeta va escriure \"Les flors del mal\"?', 6, 4),
(76, 'Qui és l’autor de \"Moby Dick\"?', 6, 5),
(77, 'Quin personatge de Shakespeare diu la frase \"Ser o no ser, aquesta és la qüestió\"?', 6, 3),
(78, 'Qui va ser el compositor de \"La Traviata\"?', 7, 4),
(79, 'Quin estil de música va popularitzar Elvis Presley?', 7, 5),
(80, 'Quin instrument és el protagonista a la peça \"El carnaval dels animals\" de Saint-Saëns?', 7, 3),
(81, 'Quin és el nom del llenguatge de programació més utilitzat en el desenvolupament web?', 8, 6),
(82, 'Qui va ser el cofundador d`Apple juntament amb Steve Jobs?', 8, 5),
(83, 'Quin és l’element principal de les pantalles tàctils?', 8, 4),
(84, 'Quin videojoc va popularitzar els personatges Mario i Luigi?', 9, 3),
(85, 'Quina és la saga de videojocs que té com a protagonista a Geralt de Rivia?', 9, 4),
(86, 'Qui és el creador del videojoc \"Minecraft\"?', 9, 5),
(93, 'Quin personatge és conegut com el mascle oficial de Nintendo?', 9, 5),
(94, 'Quin videojoc és conegut per introduir el concepte de \"moba\" (arena de batalla multijugador en línia)?', 9, 5),
(95, 'Quin videojoc popular està ambientat en un món de blocs virtuals?', 9, 5),
(96, 'Qui és el personatge principal de la saga de videojocs \"The Witcher\"?', 9, 5),
(97, 'Quin videojoc és conegut per les seves lluites de plataformes i la captura de criatures?', 9, 5),
(98, 'Quin videojoc de terror ha introduït el concepte de \"susto asimètric\"?', 9, 5),
(99, 'Quin és el nom de la franquícia de videojocs que inclou títols com \"Red\", \"Blue\" i \"Yellow\"?', 9, 5),
(100, 'Quin videojoc és conegut pel seu mode de supervivència en un món post-apocalíptic construït per blocs?', 9, 5),
(101, 'Quin videojoc és conegut per les seves batalles èpiques entre equips de herois?', 9, 5),
(102, 'Quin videojoc ha revolucionat els esports electrònics amb la seva jugabilitat tàctica?', 9, 5),
(103, 'Quin videojoc és conegut per la seva immersiva experiència d`exploració espacial i generació procedural?', 9, 5),
(104, 'Quin videojoc és conegut per la seva intriga i misteris ambientats en un món de fantasia medieval?', 9, 5),
(105, 'Quin videojoc és conegut per les seves mecàniques de construcció i supervivència en un món pixelat?', 9, 5),
(106, 'Quin videojoc és considerat un dels primers de l`era moderna i ha inspirat nombrosos títols de plataformes?', 9, 5),
(107, 'Quin personatge de videojoc és conegut per ser un fontaner saltarí?', 9, 5),
(108, 'Quina empresa és coneguda per produir els processadors \"Ryzen\" i \"Threadripper\"?', 8, 6),
(109, 'Quin és el nom del sistema operatiu mòbil desenvolupat per Google?', 8, 6),
(110, 'Quina empresa va desenvolupar el primer microprocessador comercial?', 8, 6),
(111, 'Quin dispositiu electrònic va ser introduït al mercat per Apple el 2007?', 8, 6),
(112, 'Quin tipus de connexió d`àudio és comúment utilitzat per connectar els auriculars a un telèfon mòbil sense un connector de 3,5 mm?', 8, 6),
(113, 'Quin és el nom del vehicle autònom desenvolupat per Tesla?', 8, 6),
(114, 'Quin és el nom del projecte de realitat virtual desenvolupat per Valve?', 8, 6),
(115, 'Quina empresa va desenvolupar el primer microprocessador comercial?', 8, 6),
(116, 'Quin és el nom del projecte de realitat virtual desenvolupat per Facebook?', 8, 6),
(117, 'Quin és el nom de l`altaveu intel·ligent desenvolupat per Amazon?', 8, 6),
(118, 'Quina empresa va llançar el primer ordinador personal comercialment exitós?', 8, 6),
(119, 'Quin és el nom del llenguatge de programació que es considera el \"pare\" d`Internet?', 8, 6),
(120, 'Quin és el nom del vehicle autònom desenvolupat per Google?', 8, 6),
(121, 'Quin és el nom del sistema d`xploitació desenvolupat per Apple per a la seva gamma de telèfons mòbils?', 8, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `respostes`
--

CREATE TABLE `respostes` (
  `ID_resposta` int NOT NULL,
  `ID_pregunta` int DEFAULT NULL,
  `resposta` text COLLATE utf8mb4_general_ci NOT NULL,
  `correcta` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `respostes`
--

INSERT INTO `respostes` (`ID_resposta`, `ID_pregunta`, `resposta`, `correcta`) VALUES
(1, 1, 'Cristiano Ronaldo', 0),
(2, 1, 'Pele', 0),
(3, 1, 'Leo Messi', 1),
(4, 1, 'Maradona', 0),
(5, 2, 'Níl', 1),
(6, 2, 'Amazones', 0),
(7, 2, 'Mississippi', 0),
(8, 2, 'Danubi', 0),
(9, 3, '1939', 1),
(10, 3, '1945', 0),
(11, 3, '1914', 0),
(12, 3, '1955', 0),
(13, 4, 'Pablo Picasso', 0),
(14, 4, 'Vincent van Gogh', 0),
(15, 4, 'Leonardo da Vinci', 0),
(16, 4, 'Leonardo da Vinci', 1),
(17, 5, 'Bàsquetbol', 0),
(18, 5, 'Ciclisme', 0),
(19, 5, 'Patinatge artístic', 1),
(20, 5, 'Esgrima', 0),
(21, 6, 'Gabriel García Márquez', 1),
(22, 6, 'Mario Vargas Llosa', 0),
(23, 6, 'Isabel Allende', 0),
(24, 6, 'Jorge Luis Borges', 0),
(25, 7, 'Violí', 0),
(26, 7, 'Piano', 0),
(27, 7, 'Violoncel', 1),
(28, 7, 'Flauta', 0),
(29, 8, '2007', 1),
(30, 8, '2010', 0),
(31, 8, '2005', 0),
(32, 8, '2012', 0),
(33, 9, 'Tokio', 1),
(34, 9, 'Pequín', 0),
(35, 9, 'Seül', 0),
(36, 9, 'Bangkok', 0),
(37, 10, 'Cor', 0),
(38, 10, 'Pulmons', 1),
(39, 10, 'Estómac', 0),
(40, 10, 'Ronyons', 0),
(41, 11, 'Marte', 1),
(42, 11, 'Venus', 0),
(43, 11, 'Júpiter', 0),
(44, 11, 'Saturn', 0),
(45, 12, 'Àfrica', 0),
(46, 12, 'Amèrica del Sud', 0),
(47, 12, 'Àsia', 1),
(48, 12, 'Europa', 0),
(49, 13, '1776', 1),
(50, 13, '1789', 0),
(51, 13, '1812', 0),
(52, 13, '1901', 0),
(53, 14, 'Pablo Picasso', 0),
(54, 14, 'Vincent van Gogh', 0),
(55, 14, 'Diego Velázquez', 1),
(56, 14, 'Rembrandt', 0),
(57, 15, 'Golf', 0),
(58, 15, 'Hockey sobre gel', 0),
(59, 15, 'Pàdel', 0),
(60, 15, 'Tennis', 1),
(61, 16, 'George Orwell', 1),
(62, 16, 'Aldous Huxley', 0),
(63, 16, 'Ray Bradbury', 0),
(64, 16, 'Philip K. Dick', 0),
(65, 17, 'Saxòfon', 0),
(66, 17, 'Trompeta', 0),
(67, 17, 'Piano', 0),
(68, 17, 'Orgue', 1),
(69, 18, '1975', 0),
(70, 18, '1980', 0),
(71, 18, '1985', 0),
(72, 18, '1970', 1),
(73, 19, 'Pequín', 0),
(74, 19, 'Nova Delhi', 0),
(75, 19, 'Ciutat de Mèxic', 0),
(76, 19, 'Tòquio', 1),
(77, 20, 'Pancrees', 0),
(78, 20, 'Fetge', 0),
(79, 20, 'Pulmons', 0),
(80, 20, 'Ronyons', 1),
(81, 21, 'Coure', 0),
(82, 21, 'Ferro', 0),
(83, 21, 'Alumini', 0),
(84, 21, 'Or', 1),
(85, 22, 'Estats Units', 0),
(86, 22, 'Rússia', 0),
(87, 22, 'Canadà', 1),
(88, 22, 'Xina', 0),
(89, 23, '1940', 0),
(90, 23, '1938', 0),
(91, 23, '1939', 1),
(92, 23, '1941', 0),
(93, 24, 'Édouard Manet', 0),
(94, 24, 'Diego Velázquez', 0),
(95, 24, 'Pablo Picasso', 0),
(96, 24, 'Leonardo da Vinci', 1),
(97, 25, 'Rugby', 0),
(98, 25, 'Críquet', 0),
(99, 25, 'Hoquei sobre gel', 0),
(100, 25, 'Patinatge artístic', 1),
(101, 26, 'Gabriel García Márquez', 1),
(102, 26, 'Julio Cortázar', 0),
(103, 26, 'Mario Vargas Llosa', 0),
(104, 26, 'Isabel Allende', 0),
(105, 27, 'Clarinet', 0),
(106, 27, 'Harp', 0),
(107, 27, 'Orgue', 1),
(108, 27, 'Xilòfon', 0),
(109, 28, '2008', 0),
(110, 28, '2006', 0),
(111, 28, '2007', 1),
(112, 28, '2009', 0),
(113, 29, 'Seül', 0),
(114, 29, 'Bangkok', 0),
(115, 29, 'Hanoi', 0),
(116, 29, 'Tòquio', 1),
(117, 30, 'Cervell mitjà', 0),
(118, 30, 'Tàlem', 0),
(119, 30, 'Hipotàlem', 1),
(120, 30, 'Cervell posterior', 0),
(121, 31, 'Oxigen', 1),
(122, 31, 'Diòxid de carboni', 0),
(123, 31, 'Hidrogen', 0),
(124, 31, 'Nitrògen', 0),
(125, 32, 'Àfrica', 1),
(126, 32, 'Àsia', 0),
(127, 32, 'Amèrica del Sud', 0),
(128, 32, 'Europa', 0),
(129, 33, 'Qin Shi Huang', 1),
(130, 33, 'Sun Tzu', 0),
(131, 33, 'Genghis Khan', 0),
(132, 33, 'Mao Zedong', 0),
(133, 34, 'Berlín', 0),
(134, 34, 'Madrid', 0),
(135, 34, 'París', 1),
(136, 34, 'Roma', 0),
(137, 35, 'Esquí', 0),
(138, 35, 'Salts ornamentals', 0),
(139, 35, 'Natació', 0),
(140, 35, 'Trampolí', 1),
(141, 36, 'Miguel de Cervantes', 1),
(142, 36, 'Lope de Vega', 0),
(143, 36, 'Garcilaso de la Vega', 0),
(144, 36, 'Federico García Lorca', 0),
(145, 37, 'Telescopi', 0),
(146, 37, 'Microscopi', 0),
(147, 37, 'Binocles', 0),
(148, 37, 'Telescopi', 1),
(149, 38, '1984', 0),
(150, 38, '1982', 0),
(151, 38, '1986', 0),
(152, 38, '1984', 1),
(153, 39, 'Madrid', 0),
(154, 39, 'París', 1),
(155, 39, 'Berlín', 0),
(156, 39, 'Roma', 0),
(157, 40, 'Tendó', 0),
(158, 40, 'Lligament', 1),
(159, 40, 'Múscul', 0),
(160, 40, 'Os', 0),
(161, 41, 'Terra', 0),
(162, 41, 'Júpiter', 0),
(163, 41, 'Neptú', 0),
(164, 41, 'Urà', 1),
(165, 42, 'Xina', 0),
(166, 42, 'Nepal', 0),
(167, 42, 'Índia', 1),
(168, 42, 'Pakistan', 0),
(169, 43, '1919', 1),
(170, 43, '1925', 0),
(171, 43, '1914', 0),
(172, 43, '1933', 0),
(173, 44, 'Pablo Picasso', 0),
(174, 44, 'Salvador Dalí', 1),
(175, 44, 'Vincent van Gogh', 0),
(176, 44, 'Claude Monet', 0),
(177, 45, 'Golf', 0),
(178, 45, 'Patinatge artístic', 0),
(179, 45, 'Hípica', 1),
(180, 45, 'Esgrima', 0),
(181, 46, 'Jane Austen', 1),
(182, 46, 'Emily Brontë', 0),
(183, 46, 'Charles Dickens', 0),
(184, 46, 'Fyodor Dostoevsky', 0),
(185, 47, 'Flauta', 0),
(186, 47, 'Oboè', 0),
(187, 47, 'Clarinet', 0),
(188, 47, 'Fagot', 1),
(189, 48, '2004', 0),
(190, 48, '2005', 0),
(191, 48, '2006', 0),
(192, 48, '2003', 1),
(193, 49, 'Madrid', 0),
(194, 49, 'Lisboa', 1),
(195, 49, 'Barcelona', 0),
(196, 49, 'Àmsterdam', 0),
(197, 50, 'Pàncrees', 1),
(198, 50, 'Ronyons', 0),
(199, 50, 'Cor', 0),
(200, 50, 'Estómac', 0),
(201, 51, 'Or blanc', 1),
(202, 51, 'Platí', 0),
(203, 51, 'Platí', 0),
(204, 51, 'Platí', 0),
(205, 52, 'Brasil', 0),
(206, 52, 'Argentina', 0),
(207, 52, 'Uruguai', 0),
(208, 52, 'Uruguai', 1),
(209, 53, '1960', 0),
(210, 53, '1965', 0),
(211, 53, '1970', 1),
(212, 53, '1975', 0),
(213, 54, 'Salvador Dalí', 0),
(214, 54, 'Vincent van Gogh', 0),
(215, 54, 'Salvador Dalí', 0),
(216, 54, 'Salvador Dalí', 1),
(217, 55, 'Tennis', 0),
(218, 55, 'Bàdminton', 0),
(219, 55, 'Squash', 0),
(220, 55, 'Tennis de taula', 1),
(221, 56, 'Gabriel García Márquez', 1),
(222, 56, 'Julio Cortázar', 0),
(223, 56, 'Mario Vargas Llosa', 0),
(224, 56, 'Isabel Allende', 0),
(225, 57, '5', 0),
(226, 57, '3', 1),
(227, 57, '6', 0),
(228, 57, '2', 0),
(229, 58, '1970', 1),
(230, 58, '1975', 0),
(231, 58, '1980', 0),
(232, 58, '1985', 0),
(233, 59, 'Copenhaguen', 0),
(234, 59, 'Estocolm', 1),
(235, 59, 'Helsinki', 0),
(236, 59, 'Oslo', 0),
(237, 60, 'Cetaci', 1),
(238, 60, 'Elefant', 0),
(239, 60, 'Seri', 0),
(240, 60, 'Humà', 0),
(241, 61, 'Hg', 1),
(242, 61, 'Fe', 0),
(243, 61, 'Au', 0),
(244, 61, 'Ag', 0),
(245, 62, 'ATP', 1),
(246, 62, 'ADN', 0),
(247, 62, 'ARN', 0),
(248, 62, 'AMP', 0),
(249, 63, 'Xangai', 0),
(250, 63, 'Tòquio', 1),
(251, 63, 'Nova Delhi', 0),
(252, 63, 'Ciutat de Mèxic', 0),
(253, 64, 'Indonèsia', 0),
(254, 64, 'Suècia', 0),
(255, 64, 'Japó', 1),
(256, 64, 'Filipines', 0),
(257, 65, 'Níl', 1),
(258, 65, 'Ganges', 0),
(259, 65, 'Mississippi', 0),
(260, 65, 'Mekong', 0),
(261, 66, 'Vladímir Lenin', 1),
(262, 66, 'Josef Stalin', 0),
(263, 66, 'León Trotski', 0),
(264, 66, 'Nicolás II', 0),
(265, 67, 'La presa de la Bastilla', 1),
(266, 67, 'L`Independència dels EUA', 0),
(267, 67, 'L`assassinat d`Arquiduc Francesc Ferran', 0),
(268, 67, 'El Tractat de Versalles', 0),
(269, 68, 'Dinastia Julio-Claudia', 0),
(270, 68, 'Dinastia Flavia', 1),
(271, 68, 'Dinastia Antonina', 0),
(272, 68, 'Dinastia Severa', 0),
(273, 69, 'Antoni Gaudí', 1),
(274, 69, 'Le Corbusier', 0),
(275, 69, 'Frank Lloyd Wright', 0),
(276, 69, 'Mies van der Rohe', 0),
(277, 70, 'Cubisme', 1),
(278, 70, 'Rococó', 0),
(279, 70, 'Surrealisme', 0),
(280, 70, 'Impressionisme', 0),
(281, 71, 'Miquel Àngel', 0),
(282, 71, 'Rafael', 0),
(283, 71, 'Leonardo da Vinci', 0),
(284, 71, 'Pietro Perugino', 1),
(285, 72, 'França', 0),
(286, 72, 'Brasil', 1),
(287, 72, 'Alemanya', 0),
(288, 72, 'Espanya', 0),
(289, 73, 'Gimnàstica', 1),
(290, 73, 'Patins sobre gel', 0),
(291, 73, 'Bàsquet', 0),
(292, 73, 'Ciclisme', 0),
(293, 74, 'Estats Units', 1),
(294, 74, 'Rússia', 0),
(295, 74, 'Espanya', 0),
(296, 74, 'Argentina', 0),
(297, 75, 'Charles Baudelaire', 1),
(298, 75, 'Arthur Rimbaud', 0),
(299, 75, 'Paul Verlaine', 0),
(300, 75, 'Stéphane Mallarmé', 0),
(301, 76, 'Herman Melville', 1),
(302, 76, 'Nathaniel Hawthorne', 0),
(303, 76, 'Mark Twain', 0),
(304, 76, 'Edgar Allan Poe', 0),
(305, 77, 'Piano', 0),
(306, 77, 'Violí', 0),
(307, 77, 'Flauta', 1),
(308, 77, 'Trompeta', 0),
(309, 78, 'Verdi', 0),
(310, 78, 'Puccini', 1),
(311, 78, 'Rossini', 0),
(312, 78, 'Mozart', 0),
(313, 79, 'Rock and Roll', 1),
(314, 79, 'Country', 0),
(315, 79, 'Blues', 0),
(316, 79, 'Jazz', 0),
(317, 80, 'Piano', 1),
(318, 80, 'Violoncel', 0),
(319, 80, 'Xilòfon', 0),
(320, 80, 'Trompeta', 0),
(321, 81, 'HTML', 0),
(322, 81, 'JavaScript', 0),
(323, 81, 'Python', 0),
(324, 81, 'CSS', 1),
(325, 82, '1896', 0),
(326, 82, '1901', 0),
(327, 82, '1998', 0),
(328, 82, '2008', 1),
(329, 83, 'Olor', 0),
(330, 83, 'Gust', 0),
(331, 83, 'Vista', 1),
(332, 83, 'Tacte', 0),
(333, 84, 'Super Mario Bros', 0),
(334, 84, 'The Legend of Zelda', 0),
(335, 84, 'Pokémon', 1),
(336, 84, 'Metroid', 0),
(337, 85, 'Fortnite', 0),
(338, 85, 'League of Legends', 1),
(339, 85, 'Valorant', 0),
(340, 85, 'Apex Legends', 0),
(341, 86, 'PlayStation', 0),
(342, 86, 'Xbox', 0),
(343, 86, 'Nintendo', 1),
(344, 86, 'PC', 0),
(369, 93, 'Mario', 1),
(370, 93, 'Sonic', 0),
(371, 93, 'Donkey Kong', 0),
(372, 93, 'Pac-Man', 0),
(373, 94, 'League of Legends', 1),
(374, 94, 'Fortnite', 0),
(375, 94, 'Minecraft', 0),
(376, 94, 'Overwatch', 0),
(377, 95, 'Minecraft', 1),
(378, 95, 'Terraria', 0),
(379, 95, 'Roblox', 0),
(380, 95, 'Fortnite', 0),
(381, 96, 'Geralt de Rivia', 1),
(382, 96, 'Ezio Auditore', 0),
(383, 96, 'Nathan Drake', 0),
(384, 96, 'Kratos', 0),
(385, 97, 'Pokémon', 1),
(386, 97, 'Digimon', 0),
(387, 97, 'Yu-Gi-Oh!', 0),
(388, 97, 'Beyblade', 0),
(389, 98, 'Five Nights at Freddy\'s', 1),
(390, 98, 'Outlast', 0),
(391, 98, 'Amnesia: The Dark Descent', 0),
(392, 98, 'Resident Evil', 0),
(393, 99, 'Pokémon', 1),
(394, 99, 'Final Fantasy', 0),
(395, 99, 'The Legend of Zelda', 0),
(396, 99, 'Dragon Quest', 0),
(397, 100, 'Minecraft', 1),
(398, 100, 'Terraria', 0),
(399, 100, 'Fortnite', 0),
(400, 100, 'Roblox', 0),
(401, 101, 'League of Legends', 1),
(402, 101, 'Dota 2', 0),
(403, 101, 'Heroes of the Storm', 0),
(404, 101, 'Smite', 0),
(405, 102, 'Counter-Strike: Global Offensive', 1),
(406, 102, 'Rainbow Six Siege', 0),
(407, 102, 'Valorant', 0),
(408, 102, 'Overwatch', 0),
(409, 103, 'No Man\'s Sky', 1),
(410, 103, 'Elite Dangerous', 0),
(411, 103, 'Star Citizen', 0),
(412, 103, 'Eve Online', 0),
(413, 104, 'Dark Souls', 1),
(414, 104, 'The Elder Scrolls V: Skyrim', 0),
(415, 104, 'The Legend of Zelda: Breath of the Wild', 0),
(416, 104, 'Dragon Age: Inquisition', 0),
(417, 105, 'Minecraft', 1),
(418, 105, 'Terraria', 0),
(419, 105, 'Fortnite', 0),
(420, 105, 'Roblox', 0),
(421, 106, 'Super Mario Bros', 1),
(422, 106, 'Pac-Man', 0),
(423, 106, 'Space Invaders', 0),
(424, 106, 'Tetris', 0),
(425, 107, 'Mario', 1),
(426, 107, 'Sonic', 0),
(427, 107, 'Link', 0),
(428, 107, 'Mega Man', 0),
(429, 108, 'AMD', 1),
(430, 108, 'Intel', 0),
(431, 108, 'NVIDIA', 0),
(432, 108, 'Qualcomm', 0),
(433, 109, 'Android', 1),
(434, 109, 'iOS', 0),
(435, 109, 'Windows Phone', 0),
(436, 109, 'BlackBerry OS', 0),
(437, 110, 'Intel', 1),
(438, 110, 'AMD', 0),
(439, 110, 'IBM', 0),
(440, 110, 'Motorola', 0),
(441, 111, 'iPhone', 1),
(442, 111, 'iPod', 0),
(443, 111, 'iPad', 0),
(444, 111, 'iMac', 0),
(445, 112, 'Bluetooth', 1),
(446, 112, 'USB-C', 0),
(447, 112, 'Lightning', 0),
(448, 112, 'NFC', 0),
(449, 113, 'Tesla Autopilot', 1),
(450, 113, 'Tesla Drive', 0),
(451, 113, 'Tesla Navigate', 0),
(452, 113, 'Tesla Connect', 0),
(453, 114, 'HTC Vive', 1),
(454, 114, 'Oculus Rift', 0),
(455, 114, 'PlayStation VR', 0),
(456, 114, 'Samsung Gear VR', 0),
(457, 115, 'Intel', 1),
(458, 115, 'AMD', 0),
(459, 115, 'IBM', 0),
(460, 115, 'Apple', 0),
(461, 116, 'Oculus Rift', 1),
(462, 116, 'Oculus Quest', 0),
(463, 116, 'Oculus Go', 0),
(464, 116, 'Oculus Connect', 0),
(465, 117, 'Amazon Echo', 1),
(466, 117, 'Google Home', 0),
(467, 117, 'Apple HomePod', 0),
(468, 117, 'Samsung Galaxy Home', 0),
(469, 118, 'IBM', 1),
(470, 118, 'Apple', 0),
(471, 118, 'Microsoft', 0),
(472, 118, 'Commodore', 0),
(473, 119, 'C', 1),
(474, 119, 'Python', 0),
(475, 119, 'JavaScript', 0),
(476, 119, 'Java', 0),
(477, 120, 'Waymo', 1),
(478, 120, 'Tesla Autopilot', 0),
(479, 120, 'Apple CarPlay', 0),
(480, 120, 'Uber', 0),
(481, 121, 'iOS', 1),
(482, 121, 'macOS', 0),
(483, 121, 'watchOS', 0),
(484, 121, 'tvOS', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuaris`
--

CREATE TABLE `usuaris` (
  `ID_usuari` int NOT NULL,
  `nickname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `cognom` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `contrasenya` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `pais` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `edat` int DEFAULT NULL,
  `data_registre` date DEFAULT NULL,
  `correu_electronic` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `Status` tinyint(1) NOT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuaris`
--

INSERT INTO `usuaris` (`ID_usuari`, `nickname`, `nom`, `cognom`, `contrasenya`, `pais`, `edat`, `data_registre`, `correu_electronic`, `Status`, `admin`) VALUES
(1, 'biel', 'biel', 'biel', 'biel', 'biel', 19, '2024-01-22', 'biel@biel.biel', 1, 1),
(99, 'admin', 'admin', 'admin', 'admin', 'admin', 99, '2024-01-29', 'admin', 0, 1),
(100, 'Oriol', 'Oriol', 'Torra', 'oriol', 'catalunya', 18, '2024-02-05', 'oriol', 0, 0),
(101, 'dam', 'dam', 'dam', '', 'dam', 19, '2024-02-09', 'dam', 0, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`ID_categoria`);

--
-- Indices de la tabla `estadistiques`
--
ALTER TABLE `estadistiques`
  ADD PRIMARY KEY (`ID_usuari`);

--
-- Indices de la tabla `partides`
--
ALTER TABLE `partides`
  ADD PRIMARY KEY (`ID_partida`),
  ADD KEY `ID_usuari` (`ID_usuari`);

--
-- Indices de la tabla `preguntes`
--
ALTER TABLE `preguntes`
  ADD PRIMARY KEY (`ID_pregunta`),
  ADD KEY `ID_categoria` (`ID_categoria`);

--
-- Indices de la tabla `respostes`
--
ALTER TABLE `respostes`
  ADD PRIMARY KEY (`ID_resposta`),
  ADD KEY `ID_pregunta` (`ID_pregunta`);

--
-- Indices de la tabla `usuaris`
--
ALTER TABLE `usuaris`
  ADD PRIMARY KEY (`ID_usuari`),
  ADD UNIQUE KEY `correu_electronic` (`correu_electronic`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `preguntes`
--
ALTER TABLE `preguntes`
  MODIFY `ID_pregunta` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=123;

--
-- AUTO_INCREMENT de la tabla `respostes`
--
ALTER TABLE `respostes`
  MODIFY `ID_resposta` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=489;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `estadistiques`
--
ALTER TABLE `estadistiques`
  ADD CONSTRAINT `estadistiques_ibfk_1` FOREIGN KEY (`ID_usuari`) REFERENCES `usuaris` (`ID_usuari`);

--
-- Filtros para la tabla `partides`
--
ALTER TABLE `partides`
  ADD CONSTRAINT `partides_ibfk_1` FOREIGN KEY (`ID_usuari`) REFERENCES `usuaris` (`ID_usuari`);

--
-- Filtros para la tabla `preguntes`
--
ALTER TABLE `preguntes`
  ADD CONSTRAINT `preguntes_ibfk_1` FOREIGN KEY (`ID_categoria`) REFERENCES `categories` (`ID_categoria`);

--
-- Filtros para la tabla `respostes`
--
ALTER TABLE `respostes`
  ADD CONSTRAINT `respostes_ibfk_1` FOREIGN KEY (`ID_pregunta`) REFERENCES `preguntes` (`ID_pregunta`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
