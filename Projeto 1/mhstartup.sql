DROP DATABASE IF EXISTS projetoummegadados;
CREATE DATABASE projetoummegadados;
USE projetoummegadados;

CREATE TABLE Admin(
	id_Admin INT NOT NULL AUTO_INCREMENT,
    login_name  VARCHAR(32)  NOT NULL,
    pass_hash CHAR(64),
	PRIMARY KEY (id_Admin));

CREATE TABLE ElementalVulnerability(
  id_Monster INT NOT NULL AUTO_INCREMENT,
  fireVulnerability INT NOT NULL,
  waterVulnerability INT NOT NULL,
  thunderVulnerability INT NOT NULL,
  iceVulnerability INT NOT NULL,
  dragonVulnerability INT NOT NULL,
  PRIMARY KEY (id_Monster)
  /*
  INDEX fk_ElementalVulnerability_Monster1_idx (id_Monster ASC),
  CONSTRAINT fk_ElementalVulnerability_Monster1
    FOREIGN KEY (id_Monster)
    REFERENCES Monster (id_Monster)
 --   ON DELETE NO ACTION
 --   ON UPDATE NO ACTION)
   */
	);


CREATE TABLE AilmentVulnerability (
  id_Monster INT NOT NULL AUTO_INCREMENT,
  poisonVulnerability INT NOT NULL,
  sleepVulnerability INT NOT NULL,
  paralysisVulnerability INT NOT NULL,
  blastVulnerability INT NOT NULL,
  stunVulnerability INT NOT NULL,
  PRIMARY KEY (id_Monster)
  /*
  INDEX fk_AilmentVulnerability_Monster1_idx (id_Monster ASC),
    CONSTRAINT fk_AilmentVulnerability_Monster1
    FOREIGN KEY (id_Monster)
    REFERENCES Monster (id_Monster)
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION)
  */
	);


CREATE TABLE Monster (
  id_Monster INT NOT NULL AUTO_INCREMENT,
  label VARCHAR(45) NOT NULL,
  idSubspeciesOf INT NULL,
  PRIMARY KEY (id_Monster),
  INDEX fk_Monster_Monster1_idx (idSubspeciesOf ASC),
  CONSTRAINT fk_Monster_Monster1
    FOREIGN KEY (idSubspeciesOf)
    REFERENCES Monster (id_Monster)
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION
	);

-- CREATE TABLE SharpnessDictionary (
--  id_Sharpness INT NOT NULL AUTO_INCREMENT,
--  ColorName VARCHAR(45) NOT NULL,
--  SharpnessGrade INT UNSIGNED NOT NULL,
--  PRIMARY KEY (id_Sharpness));


CREATE TABLE PhysicalVulnerability (
  id_BodyPart INT NOT NULL AUTO_INCREMENT,
  bluntVulnerability INT NOT NULL,
  severingVulnerability INT NOT NULL,
  shotVulnerability INT NOT NULL,
  PRIMARY KEY (id_BodyPart));

CREATE TABLE IF NOT EXISTS BodyPart (
  id_BodyPart INT NOT NULL AUTO_INCREMENT,
  label VARCHAR(45) NOT NULL,
  idMonster INT NOT NULL,
  -- idHardness INT NOT NULL,
  isBreakable TINYINT NOT NULL,
  isHidden TINYINT NOT NULL,
  idPair INT NULL,
  isSeverable TINYINT NOT NULL,
  PRIMARY KEY (id_BodyPart),
  INDEX fk_BodyPart_Monster_idx (idMonster ASC),
--  INDEX fk_BodyPart_SharpnessDictionary1_idx (idHardness ASC),
  INDEX fk_BodyPart_BodyPart1_idx (idPair ASC),
  CONSTRAINT fk_BodyPart_Monster
    FOREIGN KEY (idMonster)
    REFERENCES Monster (id_Monster)
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION
	,
--  CONSTRAINT fk_BodyPart_SharpnessDictionary1
--    FOREIGN KEY (idHardness)
--    REFERENCES SharpnessDictionary (id_Sharpness)
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION,
  CONSTRAINT fk_BodyPart_BodyPart1
    FOREIGN KEY (idPair)
    REFERENCES BodyPart (id_BodyPart)
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION
	);