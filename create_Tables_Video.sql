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
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Utilisateur`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Utilisateur` (
  `idUtilisateur` INT NOT NULL,
  `nom` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUtilisateur`),
  UNIQUE INDEX `nom_UNIQUE` (`nom` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Video`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Video` (
  `idVideo` INT NOT NULL AUTO_INCREMENT,
  `titre` VARCHAR(60) NOT NULL,
  `descritption` VARCHAR(500) NOT NULL,
  `miniature` VARCHAR(255) NOT NULL,
  `fichier_video` VARCHAR(255) NOT NULL,
  `date_publication` DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `statut` SET('public', 'privé') NOT NULL DEFAULT 'public',
  `idUtilisateur` INT NOT NULL,
  PRIMARY KEY (`idVideo`, `idUtilisateur`),
  UNIQUE INDEX `titre_UNIQUE` (`titre` ASC),
  INDEX `fk_Video_Utilisateur_idx` (`idUtilisateur` ASC),
  CONSTRAINT `fk_Video_Utilisateur`
    FOREIGN KEY (`idUtilisateur`)
    REFERENCES `mydb`.`Utilisateur` (`idUtilisateur`)
    ON DELETE NO ACTION
    ON UPDATE  CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `mydb`.`Utilisateur` (idUtilisateur, nom) VALUES
(1, 'Alice Dupont'),
(2, 'Bob Martin'),
(3, 'Chloé Bernard'),
(4, 'David Petit'),
(5, 'Émilie Dubois');

INSERT INTO `mydb`.`Video` (idVideo, titre, descritption, miniature, fichier_video, date_publication, statut, idUtilisateur) VALUES
(1, 'Introduction à la programmation', 'Un cours pour débutants sur la programmation.', 'miniature1.jpg', 'video1.mp4', '2024-01-15', 'public', 1),
(2, 'Avancées en IA', 'Discussion sur les dernières avancées en intelligence artificielle.', 'miniature2.jpg', 'video2.mp4', '2024-02-20', 'public', 2),
(3, 'Cuisine rapide', 'Recettes simples et rapides pour le quotidien.', 'miniature3.jpg', 'video3.mp4', '2024-03-10', 'privé', 3),
(4, 'Voyage en France', 'Un aperçu des plus belles régions de France.', 'miniature4.jpg', 'video4.mp4', '2024-04-05', 'public', 4),
(5, 'Fitness pour tous', 'Conseils et exercices pour rester en forme.', 'miniature5.jpg', 'video5.mp4', '2024-05-15', 'public', 5);

