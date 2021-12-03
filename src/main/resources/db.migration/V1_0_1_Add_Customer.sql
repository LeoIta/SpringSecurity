use bank;

CREATE TABLE `customer` (
`id` INT NOT NULL AUTO_INCREMENT,
`email` VARCHAR(45) NOT NULL,
`pwd` VARCHAR(45) NOT NULL,
`role` VARCHAR(45) NOT NULL,
PRIMARY KEY (`id`)
);

INSERT ignore INTO `customer` values (NULL, 'user@example.com', '54321','admin');
