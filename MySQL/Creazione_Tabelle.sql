-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema questionario
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema questionario
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `questionario` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `questionario` ;

-- -----------------------------------------------------
-- Table `questionario`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `questionario`.`users` (
  `username` VARCHAR(50) CHARACTER SET 'latin1' NOT NULL,
  `password` CHAR(68) CHARACTER SET 'latin1' NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  `nome` VARCHAR(50) CHARACTER SET 'latin1' NOT NULL,
  `cognome` VARCHAR(50) CHARACTER SET 'latin1' NOT NULL,
  `email` VARCHAR(50) CHARACTER SET 'latin1' NULL DEFAULT NULL,
  `eta` INT NULL DEFAULT NULL,
  `genere` CHAR(1) CHARACTER SET 'latin1' NULL DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `email` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `questionario`.`authorities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `questionario`.`authorities` (
  `username` VARCHAR(50) CHARACTER SET 'latin1' NOT NULL,
  `authority` VARCHAR(50) CHARACTER SET 'latin1' NOT NULL,
  UNIQUE INDEX `authorities_idx_1` (`username` ASC, `authority` ASC),
  CONSTRAINT `authorities_ibfk_1`
    FOREIGN KEY (`username`)
    REFERENCES `questionario`.`users` (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `questionario`.`sessioni`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `questionario`.`sessioni` (
  `id_sessione` INT NOT NULL AUTO_INCREMENT,
  `inizio` DATETIME NOT NULL,
  `fine` DATETIME NOT NULL,
  `nomesessione` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_sessione`))
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `questionario`.`questionari`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `questionario`.`questionari` (
  `id_questionario` INT NOT NULL AUTO_INCREMENT,
  `titolo` VARCHAR(45) NOT NULL,
  `id_sessione` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_questionario`),
  INDEX `fk_questionari_sessioni1_idx` (`id_sessione` ASC) VISIBLE,
  CONSTRAINT `fk_questionari_sessioni1`
    FOREIGN KEY (`id_sessione`)
    REFERENCES `questionario`.`sessioni` (`id_sessione`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `questionario`.`domande`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `questionario`.`domande` (
  `id_questionario` INT NOT NULL,
  `id_domanda` INT NOT NULL AUTO_INCREMENT,
  `domanda` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_domanda`),
  INDEX `fk_domande_questionari1_idx` (`id_questionario` ASC) VISIBLE,
  CONSTRAINT `fk_domande_questionari1`
    FOREIGN KEY (`id_questionario`)
    REFERENCES `questionario`.`questionari` (`id_questionario`))
ENGINE = InnoDB
AUTO_INCREMENT = 57
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `questionario`.`risposte`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `questionario`.`risposte` (
  `id_questionario` INT NOT NULL,
  `id_domanda` INT NOT NULL,
  `id_risposta` INT NOT NULL AUTO_INCREMENT,
  `desrisposta` VARCHAR(100) NULL DEFAULT NULL,
  `tipo` VARCHAR(20) NOT NULL,
  `score` INT NOT NULL,
  PRIMARY KEY (`id_risposta`),
  INDEX `fk_risposte_questionari1_idx` (`id_questionario` ASC) VISIBLE,
  INDEX `fk_risposte_domande1_idx` (`id_domanda` ASC) VISIBLE,
  CONSTRAINT `fk_risposte_domande1`
    FOREIGN KEY (`id_domanda`)
    REFERENCES `questionario`.`domande` (`id_domanda`),
  CONSTRAINT `fk_risposte_questionari1`
    FOREIGN KEY (`id_questionario`)
    REFERENCES `questionario`.`questionari` (`id_questionario`))
ENGINE = InnoDB
AUTO_INCREMENT = 133
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `questionario`.`registrorisposte`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `questionario`.`registrorisposte` (
  `id_registro` INT NOT NULL AUTO_INCREMENT,
  `id_questionario` INT NOT NULL,
  `id_domanda` INT NOT NULL,
  `id_risposta` INT NOT NULL,
  `rispaperta` VARCHAR(200) NULL DEFAULT NULL,
  `username` VARCHAR(50) CHARACTER SET 'latin1' NOT NULL,
  `datacompilazione` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id_registro`),
  INDEX `fk_registrorisposte_questionari1_idx` (`id_questionario` ASC) VISIBLE,
  INDEX `fk_registrorisposte_domande1_idx` (`id_domanda` ASC) VISIBLE,
  INDEX `fk_registrorisposte_risposte1_idx` (`id_risposta` ASC) VISIBLE,
  INDEX `fk_registrorisposte_users1_idx` (`username` ASC) VISIBLE,
  CONSTRAINT `fk_registrorisposte_domande1`
    FOREIGN KEY (`id_domanda`)
    REFERENCES `questionario`.`domande` (`id_domanda`),
  CONSTRAINT `fk_registrorisposte_questionari1`
    FOREIGN KEY (`id_questionario`)
    REFERENCES `questionario`.`questionari` (`id_questionario`),
  CONSTRAINT `fk_registrorisposte_risposte1`
    FOREIGN KEY (`id_risposta`)
    REFERENCES `questionario`.`risposte` (`id_risposta`),
  CONSTRAINT `fk_registrorisposte_users1`
    FOREIGN KEY (`username`)
    REFERENCES `questionario`.`users` (`username`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
