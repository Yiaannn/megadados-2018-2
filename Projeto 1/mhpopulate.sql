USE projetoummegadados;

-- Admin

INSERT INTO Admin (login_name, pass_hash) VALUES(
	'admin',
    '8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918');


--
-- Great Jagras
--


INSERT INTO AilmentVulnerability (poisonVulnerability, sleepVulnerability, paralysisVulnerability, blastVulnerability, stunVulnerability) VALUES(
	3,
    3,
    3,
    3,
    3);
    
INSERT INTO ElementalVulnerability (fireVulnerability, waterVulnerability, thunderVulnerability, iceVulnerability, dragonVulnerability) VALUES(
	3,
    0,
    2,
    2,
    1);
    
INSERT INTO Monster (label) VALUES(
	'Great Jagras');
    
--

INSERT INTO PhysicalVulnerability (bluntVulnerability, severingVulnerability, shotVulnerability) VALUES(
	3,
    3,
    3);
    
INSERT INTO BodyPart (label, idMonster, isBreakable, isHidden, isSeverable) VALUES(
	"Cabeça",
    (SELECT id_Monster FROM Monster WHERE label = 'Great Jagras'),
    true,
    false,
    false);
    
--

INSERT INTO PhysicalVulnerability (bluntVulnerability, severingVulnerability, shotVulnerability) VALUES(
	3,
    3,
    3);
    
INSERT INTO BodyPart (label, idMonster, isBreakable, isHidden, isSeverable) VALUES(
	"Abdomen",
    (SELECT id_Monster FROM Monster WHERE label = 'Great Jagras'),
    true,
    false,
    false);
    
--

INSERT INTO PhysicalVulnerability (bluntVulnerability, severingVulnerability, shotVulnerability) VALUES(
	2,
    2,
    2);
    
INSERT INTO BodyPart (label, idMonster, isBreakable, isHidden, isSeverable) VALUES(
	"Garras Dianteiras",
    (SELECT id_Monster FROM Monster WHERE label = 'Great Jagras'),
    true,
    false,
    false);


--
-- Pukei-Pukei
--


INSERT INTO AilmentVulnerability (poisonVulnerability, sleepVulnerability, paralysisVulnerability, blastVulnerability, stunVulnerability) VALUES(
	1,
    3,
    3,
    2,
    2);
    
INSERT INTO ElementalVulnerability (fireVulnerability, waterVulnerability, thunderVulnerability, iceVulnerability, dragonVulnerability) VALUES(
	2,
    0,
    3,
    2,
    1);
    
INSERT INTO Monster (label) VALUES(
	'Pukei-Pukei');
    
--

INSERT INTO PhysicalVulnerability (bluntVulnerability, severingVulnerability, shotVulnerability) VALUES(
	3,
    3,
    3);
    
INSERT INTO BodyPart (label, idMonster, isBreakable, isHidden, isSeverable) VALUES(
	"Cabeça",
    (SELECT id_Monster FROM Monster WHERE label = 'Pukei-Pukei'),
    true,
    false,
    false);
    
--

INSERT INTO PhysicalVulnerability (bluntVulnerability, severingVulnerability, shotVulnerability) VALUES(
	2,
    2,
    2);
    
INSERT INTO BodyPart (label, idMonster, isBreakable, isHidden, isSeverable) VALUES(
	"Asas",
    (SELECT id_Monster FROM Monster WHERE label = 'Pukei-Pukei'),
    true,
    false,
    false);
    
--

INSERT INTO PhysicalVulnerability (bluntVulnerability, severingVulnerability, shotVulnerability) VALUES(
	2,
    2,
    2);
    
INSERT INTO BodyPart (label, idMonster, isBreakable, isHidden, isSeverable) VALUES(
	"Rabo",
    (SELECT id_Monster FROM Monster WHERE label = 'Pukei-Pukei'),
    false,
    false,
    true);



--
-- Tobi-Kadachi
--


INSERT INTO AilmentVulnerability (poisonVulnerability, sleepVulnerability, paralysisVulnerability, blastVulnerability, stunVulnerability) VALUES(
	3,
    2,
    2,
    2,
    2);
    
INSERT INTO ElementalVulnerability (fireVulnerability, waterVulnerability, thunderVulnerability, iceVulnerability, dragonVulnerability) VALUES(
	2,
    3,
    0,
    2,
    1);
    
INSERT INTO Monster (label) VALUES(
	'Tobi-Kadachi');
    
--

INSERT INTO PhysicalVulnerability (bluntVulnerability, severingVulnerability, shotVulnerability) VALUES(
	2,
    2,
    2);
    
INSERT INTO BodyPart (label, idMonster, isBreakable, isHidden, isSeverable) VALUES(
	"Cabeça",
    (SELECT id_Monster FROM Monster WHERE label = 'Tobi-Kadachi'),
    true,
    false,
    false);
    
--

INSERT INTO PhysicalVulnerability (bluntVulnerability, severingVulnerability, shotVulnerability) VALUES(
	3,
    3,
    3);
    
INSERT INTO BodyPart (label, idMonster, isBreakable, isHidden, isSeverable) VALUES(
	"Rabo",
    (SELECT id_Monster FROM Monster WHERE label = 'Tobi-Kadachi'),
    true,
    false,
    false);


--
-- Anjanath
--


INSERT INTO AilmentVulnerability (poisonVulnerability, sleepVulnerability, paralysisVulnerability, blastVulnerability, stunVulnerability) VALUES(
	2,
    2,
    2,
    1,
    2);
    
INSERT INTO ElementalVulnerability (fireVulnerability, waterVulnerability, thunderVulnerability, iceVulnerability, dragonVulnerability) VALUES(
	0,
    3,
    2,
    2,
    1);
    
INSERT INTO Monster (label) VALUES(
	'Anjanath');
    
--

INSERT INTO PhysicalVulnerability (bluntVulnerability, severingVulnerability, shotVulnerability) VALUES(
	3,
    3,
    3);
    
INSERT INTO BodyPart (label, idMonster, isBreakable, isHidden, isSeverable) VALUES(
	"Cabeça",
    (SELECT id_Monster FROM Monster WHERE label = 'Anjanath'),
    true,
    false,
    false);
    
--

INSERT INTO PhysicalVulnerability (bluntVulnerability, severingVulnerability, shotVulnerability) VALUES(
	2,
    2,
    2);
    
INSERT INTO BodyPart (label, idMonster, isBreakable, isHidden, isSeverable) VALUES(
	"Rabo",
    (SELECT id_Monster FROM Monster WHERE label = 'Anjanath'),
    false,
    false,
    true);


--
-- Rathalos
--


INSERT INTO AilmentVulnerability (poisonVulnerability, sleepVulnerability, paralysisVulnerability, blastVulnerability, stunVulnerability) VALUES(
	1,
    2,
    2,
    1,
    2);

INSERT INTO ElementalVulnerability (fireVulnerability, waterVulnerability, thunderVulnerability, iceVulnerability, dragonVulnerability) VALUES(
	0,
    1,
    2,
    1,
    3);
    
INSERT INTO Monster (label) VALUES(
	'Rathalos');
    
--

INSERT INTO PhysicalVulnerability (bluntVulnerability, severingVulnerability, shotVulnerability) VALUES(
	3,
    3,
    3);
    
INSERT INTO BodyPart (label, idMonster, isBreakable, isHidden, isSeverable) VALUES(
	"Cabeça",
    (SELECT id_Monster FROM Monster WHERE label = 'Rathalos'),
    true,
    false,
    false);
    
--

INSERT INTO PhysicalVulnerability (bluntVulnerability, severingVulnerability, shotVulnerability) VALUES(
	2,
    2,
    2);
    
INSERT INTO BodyPart (label, idMonster, isBreakable, isHidden, isSeverable) VALUES(
	"Asas",
    (SELECT id_Monster FROM Monster WHERE label = 'Rathalos'),
    true,
    false,
    false);
    
--

INSERT INTO PhysicalVulnerability (bluntVulnerability, severingVulnerability, shotVulnerability) VALUES(
	2,
    2,
    2);
    
INSERT INTO BodyPart (label, idMonster, isBreakable, isHidden, isSeverable) VALUES(
	"Rabo",
    (SELECT id_Monster FROM Monster WHERE label = 'Rathalos'),
    false,
    false,
    true);
    
--
-- Kulu-Ya-Ku
--


INSERT INTO AilmentVulnerability (poisonVulnerability, sleepVulnerability, paralysisVulnerability, blastVulnerability, stunVulnerability) VALUES(
	2,
    2,
    2,
    2,
    2);

INSERT INTO ElementalVulnerability (fireVulnerability, waterVulnerability, thunderVulnerability, iceVulnerability, dragonVulnerability) VALUES(
	2,
    3,
    2,
    2,
    2);
    
INSERT INTO Monster (label) VALUES(
	'Kulu-Ya-Ku');
    
--

INSERT INTO PhysicalVulnerability (bluntVulnerability, severingVulnerability, shotVulnerability) VALUES(
	3,
    3,
    3);
    
INSERT INTO BodyPart (label, idMonster, isBreakable, isHidden, isSeverable) VALUES(
	"Cabeça",
    (SELECT id_Monster FROM Monster WHERE label = 'Kulu-Ya-Ku'),
    true,
    false,
    false);

    
--
-- Azure Rathalos
--


INSERT INTO AilmentVulnerability (poisonVulnerability, sleepVulnerability, paralysisVulnerability, blastVulnerability, stunVulnerability) VALUES(
	1,
    2,
    2,
    1,
    2);

INSERT INTO ElementalVulnerability (fireVulnerability, waterVulnerability, thunderVulnerability, iceVulnerability, dragonVulnerability) VALUES(
	0,
    1,
    1,
    2,
    3);
    
    
SELECT id_Monster INTO @subspecies FROM Monster WHERE label='Rathalos';

INSERT INTO Monster (idSubspeciesOf, label) VALUES(
	@subspecies,
	'Azure Rathalos');
    
--

INSERT INTO PhysicalVulnerability (bluntVulnerability, severingVulnerability, shotVulnerability) VALUES(
	3,
    3,
    3);
    
INSERT INTO BodyPart (label, idMonster, isBreakable, isHidden, isSeverable) VALUES(
	"Cabeça",
    (SELECT id_Monster FROM Monster WHERE label = 'Azure Rathalos'),
    true,
    false,
    false);
    
--

INSERT INTO PhysicalVulnerability (bluntVulnerability, severingVulnerability, shotVulnerability) VALUES(
	2,
    2,
    2);
    
INSERT INTO BodyPart (label, idMonster, isBreakable, isHidden, isSeverable) VALUES(
	"Asas",
    (SELECT id_Monster FROM Monster WHERE label = 'Azure Rathalos'),
    true,
    false,
    false);
    
--

INSERT INTO PhysicalVulnerability (bluntVulnerability, severingVulnerability, shotVulnerability) VALUES(
	2,
    2,
    2);
    
INSERT INTO BodyPart (label, idMonster, isBreakable, isHidden, isSeverable) VALUES(
	"Rabo",
    (SELECT id_Monster FROM Monster WHERE label = 'Azure Rathalos'),
    false,
    false,
    true);
