use bank;

ALTER TABLE customer MODIFY COLUMN pwd VARCHAR(200);

INSERT ignore INTO `customer` values (NULL, 'user@BCrypt.com', '$2y$10$zcW2O6CzodLMI4m74d.mcu7/RQqSPbCaLvi.3mZrE7lmEK6Y2LDc2','admin');