CREATE SCHEMA `test` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `internetshop`.`items` (
  `item_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `price` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`item_id`));

  INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('Lenovo', '500');
INSERT INTO `internetshop`.`items` (`name`, `price`) VALUES ('HP', '700');

CREATE TABLE `internetshop`.`buckets` (
  `idBuckets` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `items` VARCHAR(200) NULL,
  PRIMARY KEY (`idBuckets`));

  CREATE TABLE `internetshop`.`buckets` (
  `idBuckets` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `items` VARCHAR(200) NULL,
  PRIMARY KEY (`idBuckets`));

  CREATE TABLE `internetshop`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `bucket_id` INT NULL,
  PRIMARY KEY (`user_id`));

  CREATE TABLE `internetshop`.`roles` (
  `roles_id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NULL,
  PRIMARY KEY (`roles_id`));

  CREATE TABLE `internetshop`.`user_roles` (
  `user_roles_id` INT NOT NULL AUTO_INCREMENT,
  `uder_id` INT NOT NULL,
  `role_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_roles_id`));

