CREATE DATABASE `food_delivery` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `items` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `name` varchar(100) DEFAULT NULL,
                         `price` int DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `shopping_cart` (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `items_list` varchar(100) DEFAULT NULL,
                                 `user_id` int NOT NULL,
                                 `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                 `delivery_address` varchar(100) DEFAULT NULL,
                                 `is_processed` bit(1) DEFAULT b'0',
                                 `payment_type` int DEFAULT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `name` varchar(100) DEFAULT NULL,
                         `nr_identity` varchar(100) DEFAULT NULL,
                         `cnp` int DEFAULT NULL,
                         `address` varchar(100) DEFAULT NULL,
                         `is_admin` bit(1) DEFAULT b'0',
                         `is_loyal` bit(1) DEFAULT b'0',
                         `username` varchar(100) NOT NULL,
                         `password` varchar(100) NOT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
