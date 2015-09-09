-- Copyright (c), Jaocopo Greenslade
-- -----------------------------------------------------
-- Schema contact_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `contact_db` ;

-- -----------------------------------------------------
-- Schema contact_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `contact_db` DEFAULT CHARACTER SET utf8 ;
USE `contact_db` ;

-- -----------------------------------------------------
-- Table `contact_db`.`contacts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `contact_db`.`contacts` ;

CREATE TABLE IF NOT EXISTS `contact_db`.`contacts` (
  `index` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(15) NOT NULL ,
  `middle_name` VARCHAR(15) NULL DEFAULT NULL ,
  `last_name` VARCHAR(15) NULL DEFAULT NULL,
  `email_address` VARCHAR(45) NULL DEFAULT NULL ,
  `major` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`index`)  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


INSERT INTO contact_db.contacts (`first_name`, `middle_name`, `last_name`, `email_address`, `major`)
VALUES("Jacopo", "Rush", "Greenslade", "jgreenslade@elon.edu", "Computer Science") ,
("Colby", "Cheese", "Chatterton", "cchatterton@elon.edu", "Computer Science") ,
("Mr.", "Potato", "Head", "mhead@elon.edu", "Agricultural Studies") ,
("Anakin", "D.V.", "Skywalker", "askywalker.edu", "Astronomy") ,
("Richard", "P.", "Riddick", "rRiddick@elon.edu", "Kill Theory");
