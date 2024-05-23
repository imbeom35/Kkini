-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: s09p12c210
-- ------------------------------------------------------
-- Server version	11.0.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `collection`
--

DROP TABLE IF EXISTS `collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collection` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date_time` datetime(6) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `recipe_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7bm2ngjfpn2yrlhf90safc33l` (`member_id`),
  KEY `FKj9v3rjhqhx0amf9fsj9eqaowh` (`recipe_id`),
  CONSTRAINT `FK7bm2ngjfpn2yrlhf90safc33l` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FKj9v3rjhqhx0amf9fsj9eqaowh` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collection`
--

LOCK TABLES `collection` WRITE;
/*!40000 ALTER TABLE `collection` DISABLE KEYS */;
/*!40000 ALTER TABLE `collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preference`
--

DROP TABLE IF EXISTS `preference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preference` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `weight` int(11) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrbs9ljrkj517ayouph71vol6n` (`category_id`),
  KEY `FKg1k85wg4bngr1y82ahkbyyj2y` (`member_id`),
  CONSTRAINT `FKg1k85wg4bngr1y82ahkbyyj2y` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FKrbs9ljrkj517ayouph71vol6n` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preference`
--

LOCK TABLES `preference` WRITE;
/*!40000 ALTER TABLE `preference` DISABLE KEYS */;
INSERT INTO `preference` VALUES (1,1,1,1),(2,1,2,1),(3,1,3,1),(4,1,4,1),(5,1,5,1),(6,1,1,2),(7,1,2,2),(8,1,3,2),(9,1,4,2),(10,1,5,2),(11,1,1,3),(12,1,2,3),(13,1,3,3),(14,1,4,3),(15,1,5,3),(16,1,1,4),(17,1,2,4),(18,1,3,4),(19,1,4,4),(20,1,5,4),(21,1,1,5),(22,1,2,5),(23,1,3,5),(24,1,4,5),(25,1,5,5);
/*!40000 ALTER TABLE `preference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'https://upload.wikimedia.org/wikipedia/commons/b/b2/KOCIS_Korean_meal_table_%284553953910%29.jpg?20140320225932','한식'),(2,'https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Beijing_DUck_sliced.jpeg/1200px-Beijing_DUck_sliced.jpeg?20170110051654','중식'),(3,'https://upload.wikimedia.org/wikipedia/commons/thumb/1/18/Osechi_001.jpg/1200px-Osechi_001.jpg?20131231080221','일식'),(4,'https://upload.wikimedia.org/wikipedia/commons/thumb/1/18/Osechi_001.jpg/1200px-Osechi_001.jpg?20131231080221','양식'),(5,'https://cdn.pixabay.com/photo/2017/02/01/00/26/cranium-2028555_1280.png','기타');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date_time` datetime(6) DEFAULT NULL,
  `me_id` bigint(20) DEFAULT NULL,
  `target_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd44awit5xp9iv3nbmolc1u8ls` (`me_id`),
  KEY `FKi4vw44ytov6dhpyir57thbdbh` (`target_id`),
  CONSTRAINT `FKd44awit5xp9iv3nbmolc1u8ls` FOREIGN KEY (`me_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FKi4vw44ytov6dhpyir57thbdbh` FOREIGN KEY (`target_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (1,NULL,1,2),(2,NULL,1,3),(3,NULL,1,4),(4,NULL,1,5),(5,NULL,2,1),(6,NULL,2,3),(7,NULL,2,5),(8,NULL,3,1),(9,NULL,3,4),(10,NULL,4,2),(11,NULL,4,5),(12,NULL,5,1),(13,NULL,5,3);
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date_time` datetime(6) DEFAULT NULL,
  `contents` varchar(255) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `parents_id` bigint(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmrrrpi513ssu63i2783jyiv9m` (`member_id`),
  KEY `FKbpa02r5cfhqseda598fc5aqa5` (`parents_id`),
  KEY `FKs1slvnkuemjsq2kj4h3vhx7i1` (`post_id`),
  CONSTRAINT `FKbpa02r5cfhqseda598fc5aqa5` FOREIGN KEY (`parents_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `FKmrrrpi513ssu63i2783jyiv9m` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FKs1slvnkuemjsq2kj4h3vhx7i1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,NULL,'맛있어 보이네요!',1,NULL,1),(2,NULL,'치즈가 녹는 모습이 멋지네요.',2,NULL,2),(3,NULL,'타코 색다른 아이디어네요!',3,NULL,3),(4,NULL,'가든 샐러드 신선하게 보이네요.',1,NULL,4),(5,NULL,'피자 완성도 높아보여요.',2,NULL,5);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date_time` datetime(6) DEFAULT NULL,
  `modify_date_time` datetime(6) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `difficulty` int(11) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `ingredient` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `steps` varchar(255) DEFAULT NULL,
  `time` int(11) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrufhnv33hpfxstx9x108553kj` (`category_id`),
  KEY `FKsoo4pxu79yt7nethdmbiks3pe` (`member_id`),
  CONSTRAINT `FKrufhnv33hpfxstx9x108553kj` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKsoo4pxu79yt7nethdmbiks3pe` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (1,NULL,NULL,_binary '\0',3,'https://cookiebebe.com/shopimages/cookiebebe/002003000013.jpg?1622104450','밀가루 2컵, 설탕 2컵, 코코아 파우더 3/4컵, ...','맛있는 초콜릿 케이크','1. 오븐 예열...\n2. 건조 재료 섞기...\n3. 습기 있는 재료 추가하기...',45,1,1),(2,NULL,NULL,_binary '\0',2,'https://i.ytimg.com/vi/C_V6H9DxeUk/maxresdefault.jpg','뼈 없는 닭가슴살 2조각, 생크림 1컵, 파마산 치즈 1/2컵, ...','크리미한 치킨 알프레도','1. 닭살 조리...\n2. 파스타 준비...\n3. 알프레도 소스 만들기...',30,2,2),(3,NULL,NULL,_binary '\0',2,'https://example.com/beef-tacos.jpg','1파운드 갈은 소고기, 타코 시즈닝 1팩, 샐러드용 양상추 1컵, ...','매콤한 소고기 타코','1. 소고기 볶기...\n2. 시즈닝 추가...\n3. 타코 조립하기...',25,1,3),(4,NULL,NULL,_binary '\0',1,'https://example.com/garden-salad.jpg','잡채 채소 2컵, 체리 토마토 1/2컵, 얇게 썬 오이 1/4컵, ...','신선한 가든 샐러드','1. 채소 씻고 다듬기...\n2. 보울에 재료 넣기...\n3. 드레싱 뿌리기...',15,3,1),(5,NULL,NULL,_binary '\0',2,'https://example.com/margherita-pizza.jpg','피자 반죽 1개, 토마토 소스 1/2컵, 모짜렐라 치즈 1 1/2컵, ...','클래식 마르게리따 피자','1. 반죽 펴기...\n2. 소스 바르기...\n3. 치즈와 토핑 추가하기...\n4. 예열한 오븐에서 굽기...',20,2,2);
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date_time` datetime(6) DEFAULT NULL,
  `word` varchar(255) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbnwj6i7md9xd8vr1pfqqbf1q5` (`member_id`),
  CONSTRAINT `FKbnwj6i7md9xd8vr1pfqqbf1q5` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scrap`
--

DROP TABLE IF EXISTS `scrap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scrap` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date_time` datetime(6) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq0ff1jblgu8vrsh90u826qell` (`member_id`),
  KEY `FK6cjkagewjywuylil3gw7di68q` (`post_id`),
  CONSTRAINT `FK6cjkagewjywuylil3gw7di68q` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKq0ff1jblgu8vrsh90u826qell` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scrap`
--

LOCK TABLES `scrap` WRITE;
/*!40000 ALTER TABLE `scrap` DISABLE KEYS */;
INSERT INTO `scrap` VALUES (1,NULL,1,1),(2,NULL,2,2),(3,NULL,3,3),(4,NULL,1,4),(5,NULL,2,5);
/*!40000 ALTER TABLE `scrap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date_time` datetime(6) DEFAULT NULL,
  `modify_date_time` datetime(6) DEFAULT NULL,
  `avg_price` int(11) NOT NULL,
  `avg_price_cnt` int(11) NOT NULL,
  `contents` varchar(255) DEFAULT NULL,
  `dis_like_cnt` int(11) NOT NULL,
  `like_cnt` int(11) NOT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `recipe_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK83s99f4kx8oiqm3ro0sasmpww` (`member_id`),
  KEY `FKmngn1r3090p14pnhhae23c8jr` (`recipe_id`),
  CONSTRAINT `FK83s99f4kx8oiqm3ro0sasmpww` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FKmngn1r3090p14pnhhae23c8jr` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,NULL,NULL,10000,1,'맛있는 초콜릿 케이크를 만들었어요!',2,15,1,1),(2,NULL,NULL,15000,2,'오늘 치킨 알프레도를 시도해보았는데 정말 맛있었어요.',1,23,2,2),(3,NULL,NULL,8000,1,'소고기 타코 완성! 매콤하고 고소해요~',3,10,3,3),(4,NULL,NULL,5000,1,'가든 샐러드를 간단하게 만들었어요.',0,8,1,4),(5,NULL,NULL,12000,1,'클래식 마르게리따 피자 퍼펙트!',2,18,2,5);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date_time` datetime(6) DEFAULT NULL,
  `modify_date_time` datetime(6) DEFAULT NULL,
  `auth_provider` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `level` int(11) NOT NULL DEFAULT 1,
  `name` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `oauth2id` varchar(255) DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `stars` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_email` (`email`),
  KEY `idx_nickname` (`nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'2023-08-14 21:33:03.000000','2023-08-14 21:33:03.000000','NAVER','byh9811@naver.com','imagename123',1,'배용현','너시련나길여','oatuh2id1','token12345','ROLE_USER',1),(2,'2023-08-14 21:33:03.000000','2023-08-14 21:33:03.000000','NAVER','byh9811@google.com','imagename456',3,'김범창','sillyeog','oauth2id2','token67890','ROLE_USER',2),(3,'2023-08-14 21:33:03.000000','2023-08-14 21:33:03.000000','NAVER','byh9811@google.com','imagename789',3,'진병욱','IUFAN','oauth2id2','token67890','ROLE_USER',2),(4,'2023-08-14 21:33:03.000000','2023-08-14 21:33:03.000000','NAVER','byh9811@google.com','imagename789',3,'박태규','IUFAN','oauth2id2','token67890','ROLE_USER',2),(5,'2023-08-14 21:33:03.000000','2023-08-14 21:33:03.000000','NAVER','byh9811@google.com','imagename789',3,'김승영','IUFAN','oauth2id2','token67890','ROLE_USER',2),(6,'2023-08-14 21:33:03.000000','2023-08-14 21:33:03.000000','NAVER','byh9811@google.com','imagename789',3,'이승태','IUFAN','oauth2id2','token67890','ROLE_USER',2);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_image`
--

DROP TABLE IF EXISTS `post_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsip7qv57jw2fw50g97t16nrjr` (`post_id`),
  CONSTRAINT `FKsip7qv57jw2fw50g97t16nrjr` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_image`
--

LOCK TABLES `post_image` WRITE;
/*!40000 ALTER TABLE `post_image` DISABLE KEYS */;
INSERT INTO `post_image` VALUES (1,'https://example.com/image1.jpg',1),(2,'https://example.com/image2.jpg',2),(3,'https://example.com/image3.jpg',3),(4,'https://example.com/image4.jpg',4),(5,'https://example.com/image5.jpg',5),(6,'https://bakeitwithlove.com/wp-content/uploads/2021/04/Grilled-Chicken-Fettucine-Alfredo-lg-500x500.jpg',2),(7,'https://bakeitwithlove.com/wp-content/uploads/2021/04/Grilled-Chicken-Fettuccine-Alfredo-h.jpg',2),(8,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_eMXBi5GQn-wgHuErh-r5flo-u8jYJPPjrg&usqp=CAU',5);
/*!40000 ALTER TABLE `post_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reaction`
--

DROP TABLE IF EXISTS `reaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reaction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `state` bit(1) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf0kgc48u5e6wakvieex07kk5w` (`member_id`),
  KEY `FKathfhl7fif9f9mggdjhg7ktdt` (`post_id`),
  CONSTRAINT `FKathfhl7fif9f9mggdjhg7ktdt` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKf0kgc48u5e6wakvieex07kk5w` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reaction`
--

LOCK TABLES `reaction` WRITE;
/*!40000 ALTER TABLE `reaction` DISABLE KEYS */;
INSERT INTO `reaction` VALUES (1,_binary '',1,1),(2,_binary '\0',2,2),(3,_binary '',3,3),(4,_binary '\0',1,4),(5,_binary '',2,5);
/*!40000 ALTER TABLE `reaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` int(11) NOT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb9ixvns6joh5jp42jfe9999nj` (`member_id`),
  KEY `FK4nhl13aw1kvxdm2w4sb2nvefc` (`post_id`),
  CONSTRAINT `FK4nhl13aw1kvxdm2w4sb2nvefc` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKb9ixvns6joh5jp42jfe9999nj` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluation`
--

LOCK TABLES `evaluation` WRITE;
/*!40000 ALTER TABLE `evaluation` DISABLE KEYS */;
INSERT INTO `evaluation` VALUES (1,10000,1,1),(2,20000,1,2),(3,10000,2,2),(4,8000,3,3),(5,5000,1,4),(6,12000,1,5);
/*!40000 ALTER TABLE `evaluation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-14 22:05:43
