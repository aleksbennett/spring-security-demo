DROP DATABASE IF EXISTS usertest;
DROP USER IF EXISTS `user_test`@`%`;
CREATE DATABASE IF NOT EXISTS usertest CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `user_test`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `usertest`.* TO `usertest`@`%`;
FLUSH PRIVILEGES;