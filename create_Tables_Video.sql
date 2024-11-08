-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `LeslyJacobVideodb` DEFAULT CHARACTER SET utf8 ;
USE `LeslyJacobVideodb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Utilisateur`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LeslyJacobVideodb`.`Utilisateur` (
  `idUtilisateur` INT NOT NULL,
  `nom` VARCHAR(45) NOT NULL,
  `courriel` VARCHAR(45) NOT NULL,
  `coordonnées` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUtilisateur`),
  UNIQUE INDEX `nom_UNIQUE` (`nom` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Video`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LeslyJacobVideodb`.`Video` (
  `idVideo` INT NOT NULL AUTO_INCREMENT,
  `titre` VARCHAR(60) NOT NULL,
  `description` VARCHAR(500),
  `miniature` VARCHAR(255),
  `fichier_video` VARCHAR(255) NOT NULL,
  `date_publication` DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` SET('public', 'privé') NOT NULL DEFAULT 'public',
  `auteur` INT NOT NULL,
  PRIMARY KEY (`idVideo`),
  UNIQUE INDEX `titre_UNIQUE` (`titre` ASC),
  INDEX `fk_Video_Utilisateur_idx` (`auteur` ASC),
  CONSTRAINT `fk_Video_Utilisateur`
    FOREIGN KEY (`auteur`)
    REFERENCES `LeslyJacobVideodb`.`Utilisateur` (`idUtilisateur`)
    ON DELETE NO ACTION
    ON UPDATE  CASCADE)
ENGINE = InnoDB;




