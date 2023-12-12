-- MySQL Script generated by MySQL Workbench
-- Tue Nov 14 19:30:39 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema liquidbits
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema liquidbits
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `liquidbits` DEFAULT CHARACTER SET utf8 ;
USE `liquidbits` ;

-- -----------------------------------------------------
-- Table `liquidbits`.`DEVICE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `liquidbits`.`DEVICE` (
  `DEVICE_ID` INT NOT NULL AUTO_INCREMENT,
  `LOCATION` VARCHAR(45) NOT NULL,
  `MANUFACTURER` VARCHAR(45) NOT NULL,
  `MODELL` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`DEVICE_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liquidbits`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `liquidbits`.`USER` (
  `USER_ID` INT NOT NULL AUTO_INCREMENT,
  `USERNAME` VARCHAR(45) NOT NULL,
  `MAIL` VARCHAR(45) NULL,
  PRIMARY KEY (`USER_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liquidbits`.`DRINKTYPE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `liquidbits`.`DRINKTYPE` (
  `DRINKTYPE_ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `ALCVALUE` INT NULL,
  PRIMARY KEY (`DRINKTYPE_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liquidbits`.`CONTAINER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `liquidbits`.`CONTAINER` (
  `CONTAINER_ID` INT NOT NULL AUTO_INCREMENT,
  `TAPPED` DATE NOT NULL,
  `SIZE_ML` INT NOT NULL,
  `DRINKTYPE_ID` INT NOT NULL,
  PRIMARY KEY (`CONTAINER_ID`),
  INDEX `fk_CONTAINER_DRINKTYPE1_idx` (`DRINKTYPE_ID` ASC) VISIBLE,
  CONSTRAINT `fk_CONTAINER_DRINKTYPE1`
    FOREIGN KEY (`DRINKTYPE_ID`)
    REFERENCES `liquidbits`.DRINKTYPE (DRINKTYPE_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `liquidbits`.`DRINK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `liquidbits`.`DRINK` (
  `DRINK_ID` INT NOT NULL AUTO_INCREMENT,
  `AMOUNT` INT NULL,
  `DRINKTYPE_ID` INT NOT NULL,
  `CONTAINER_ID` INT NOT NULL,
  `DEVICE_ID` INT NOT NULL,
  `USER_ID` INT NOT NULL,
  PRIMARY KEY (`DRINK_ID`),
  INDEX `fk_DRINK_DRINKTYPE_idx` (`DRINKTYPE_ID` ASC) VISIBLE,
  INDEX `fk_DRINK_CONTAINER1_idx` (`CONTAINER_ID` ASC) VISIBLE,
  INDEX `fk_DRINK_DEVICE1_idx` (`DEVICE_ID` ASC) VISIBLE,
  INDEX `fk_DRINK_USER1_idx` (`USER_ID` ASC) VISIBLE,
  CONSTRAINT `fk_DRINK_DRINKTYPE`
    FOREIGN KEY (`DRINKTYPE_ID`)
    REFERENCES `liquidbits`.DRINKTYPE (DRINKTYPE_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DRINK_CONTAINER1`
    FOREIGN KEY (`CONTAINER_ID`)
    REFERENCES `liquidbits`.`CONTAINER` (`CONTAINER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DRINK_DEVICE1`
    FOREIGN KEY (`DEVICE_ID`)
    REFERENCES `liquidbits`.`DEVICE` (`DEVICE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DRINK_USER1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `liquidbits`.`USER` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;