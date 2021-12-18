CREATE SCHEMA bank;

use bank;
CREATE TABLE `users` (
`id` INT NOT NULL AUTO_INCREMENT,
`username` VARCHAR(45) NOT NULL,
`password` VARCHAR(45) NOT NULL,
`enabled` INT NOT NULL,
PRIMARY KEY (`id`)
);
CREATE TABLE `authorities` (
`id` INT NOT NULL AUTO_INCREMENT,
`username` VARCHAR(45) NOT NULL,
`authority` VARCHAR(45) NOT NULL,
PRIMARY KEY (`id`)
);

INSERT ignore INTO `users` values (NULL, 'happy', '12345','1');
INSERT ignore INTO `authorities` values (NULL, 'happy', 'write');