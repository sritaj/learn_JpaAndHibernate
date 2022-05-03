DROP DATABASE IF EXISTS jpa_exercise_db;
DROP USER IF EXISTS `admin`@`%`;
DROP USER IF EXISTS `user`@`%`;

CREATE DATABASE IF NOT EXISTS jpa_exercise_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `admin`@`%`IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON`jpa_exercise_db`.* TO `admin`@`%`;

CREATE USER IF NOT EXISTS `user`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON`jpa_exercise_db`.* TO `user`@`%`;
FLUSH PRIVILEGES;