use s09p12c210;

-- drop tables

DROP TABLE IF EXISTS `evaluation`;
DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `scrap`;
DROP TABLE IF EXISTS `reaction`;
DROP TABLE IF EXISTS `post_image`;
DROP TABLE IF EXISTS `post`;
DROP TABLE IF EXISTS `collection`;
DROP TABLE IF EXISTS `recipe`;
DROP TABLE IF EXISTS `preference`;
DROP TABLE IF EXISTS `history`;
DROP TABLE IF EXISTS `follow`;
DROP TABLE IF EXISTS `member`;
DROP TABLE IF EXISTS `hibernate_sequence`;
DROP TABLE IF EXISTS `category`;

-- s09p12c210.category definition

CREATE TABLE `category` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `image` varchar(255) DEFAULT NULL,
                            `name` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- s09p12c210.hibernate_sequence definition

CREATE TABLE `hibernate_sequence` (
    `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- s09p12c210.`member` definition

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


-- s09p12c210.follow definition

CREATE TABLE `follow` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `create_date_time` datetime(6) DEFAULT NULL,
                          `me_id` bigint(20) DEFAULT NULL,
                          `target_id` bigint(20) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `FKd44awit5xp9iv3nbmolc1u8ls` (`me_id`),
                          KEY `FKi4vw44ytov6dhpyir57thbdbh` (`target_id`),
                          CONSTRAINT `FKd44awit5xp9iv3nbmolc1u8ls` FOREIGN KEY (`me_id`) REFERENCES `member` (`id`) ON DELETE CASCADE,
                          CONSTRAINT `FKi4vw44ytov6dhpyir57thbdbh` FOREIGN KEY (`target_id`) REFERENCES `member` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- s09p12c210.history definition

CREATE TABLE `history` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `create_date_time` datetime(6) DEFAULT NULL,
                           `word` varchar(255) DEFAULT NULL,
                           `member_id` bigint(20) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FKbnwj6i7md9xd8vr1pfqqbf1q5` (`member_id`),
                           CONSTRAINT `FKbnwj6i7md9xd8vr1pfqqbf1q5` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- s09p12c210.preference definition

CREATE TABLE `preference` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `weight` int(11) NOT NULL,
                              `category_id` bigint(20) DEFAULT NULL,
                              `member_id` bigint(20) DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              KEY `FKrbs9ljrkj517ayouph71vol6n` (`category_id`),
                              KEY `FKg1k85wg4bngr1y82ahkbyyj2y` (`member_id`),
                              CONSTRAINT `FKg1k85wg4bngr1y82ahkbyyj2y` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE CASCADE,
                              CONSTRAINT `FKrbs9ljrkj517ayouph71vol6n` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- s09p12c210.recipe definition

CREATE TABLE `recipe` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `create_date_time` datetime(6) DEFAULT NULL,
                          `modify_date_time` datetime(6) DEFAULT NULL,
                          `deleted` bit(1) DEFAULT NULL,
                          `difficulty` int(11) NOT NULL,
                          `image` varchar(255) DEFAULT NULL,
                          `ingredient` varchar(255) DEFAULT NULL,
                          `name` varchar(255) DEFAULT NULL,
                          `steps` varchar(3000) DEFAULT NULL,
                          `time` int(11) NOT NULL,
                          `category_id` bigint(20) DEFAULT NULL,
                          `member_id` bigint(20) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `FKrufhnv33hpfxstx9x108553kj` (`category_id`),
                          KEY `FKsoo4pxu79yt7nethdmbiks3pe` (`member_id`),
                          CONSTRAINT `FKrufhnv33hpfxstx9x108553kj` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE,
                          CONSTRAINT `FKsoo4pxu79yt7nethdmbiks3pe` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- s09p12c210.collection definition

CREATE TABLE `collection` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `create_date_time` datetime(6) DEFAULT NULL,
                              `member_id` bigint(20) DEFAULT NULL,
                              `recipe_id` bigint(20) DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              KEY `FK7bm2ngjfpn2yrlhf90safc33l` (`member_id`),
                              KEY `FKj9v3rjhqhx0amf9fsj9eqaowh` (`recipe_id`),
                              CONSTRAINT `FK7bm2ngjfpn2yrlhf90safc33l` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE CASCADE,
                              CONSTRAINT `FKj9v3rjhqhx0amf9fsj9eqaowh` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- s09p12c210.post definition

CREATE TABLE `post` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `create_date_time` datetime(6) DEFAULT NULL,
                        `modify_date_time` datetime(6) DEFAULT NULL,
                        `avg_price` int(11) NOT NULL,
                        `avg_price_cnt` int(11) NOT NULL,
                        `contents` varchar(3000) DEFAULT NULL,
                        `dis_like_cnt` int(11) NOT NULL,
                        `like_cnt` int(11) NOT NULL,
                        `member_id` bigint(20) DEFAULT NULL,
                        `recipe_id` bigint(20) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        KEY `FK83s99f4kx8oiqm3ro0sasmpww` (`member_id`),
                        KEY `FKmngn1r3090p14pnhhae23c8jr` (`recipe_id`),
                        CONSTRAINT `FK83s99f4kx8oiqm3ro0sasmpww` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE CASCADE,
                        CONSTRAINT `FKmngn1r3090p14pnhhae23c8jr` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- s09p12c210.post_image definition

CREATE TABLE `post_image` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `image` varchar(255) DEFAULT NULL,
                              `post_id` bigint(20) DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              KEY `FKsip7qv57jw2fw50g97t16nrjr` (`post_id`),
                              CONSTRAINT `FKsip7qv57jw2fw50g97t16nrjr` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- s09p12c210.reaction definition

CREATE TABLE `reaction` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `state` bit(1) DEFAULT NULL,
                            `member_id` bigint(20) DEFAULT NULL,
                            `post_id` bigint(20) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `FKf0kgc48u5e6wakvieex07kk5w` (`member_id`),
                            KEY `FKathfhl7fif9f9mggdjhg7ktdt` (`post_id`),
                            CONSTRAINT `FKathfhl7fif9f9mggdjhg7ktdt` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE,
                            CONSTRAINT `FKf0kgc48u5e6wakvieex07kk5w` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- s09p12c210.scrap definition

CREATE TABLE `scrap` (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `create_date_time` datetime(6) DEFAULT NULL,
                         `member_id` bigint(20) DEFAULT NULL,
                         `post_id` bigint(20) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `FKq0ff1jblgu8vrsh90u826qell` (`member_id`),
                         KEY `FK6cjkagewjywuylil3gw7di68q` (`post_id`),
                         CONSTRAINT `FK6cjkagewjywuylil3gw7di68q` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE,
                         CONSTRAINT `FKq0ff1jblgu8vrsh90u826qell` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- s09p12c210.comment definition

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
                           CONSTRAINT `FKbpa02r5cfhqseda598fc5aqa5` FOREIGN KEY (`parents_id`) REFERENCES `comment` (`id`) ON DELETE CASCADE,
                           CONSTRAINT `FKmrrrpi513ssu63i2783jyiv9m` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE CASCADE,
                           CONSTRAINT `FKs1slvnkuemjsq2kj4h3vhx7i1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- s09p12c210.evaluation definition

CREATE TABLE `evaluation` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `price` int(11) NOT NULL,
                              `member_id` bigint(20) DEFAULT NULL,
                              `post_id` bigint(20) DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              KEY `FKb9ixvns6joh5jp42jfe9999nj` (`member_id`),
                              KEY `FK4nhl13aw1kvxdm2w4sb2nvefc` (`post_id`),
                              CONSTRAINT `FK4nhl13aw1kvxdm2w4sb2nvefc` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE,
                              CONSTRAINT `FKb9ixvns6joh5jp42jfe9999nj` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;