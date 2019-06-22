INSERT INTO `studentska_zadruga`.`mjesto` (`ZIPkod`, `Naziv`) VALUES ('78000', 'Banjaluka');
INSERT INTO `studentska_zadruga`.`mjesto` (`ZIPkod`, `Naziv`) VALUES ('78400', 'Gradiska');
INSERT INTO `studentska_zadruga`.`mjesto` (`ZIPkod`, `Naziv`) VALUES ('89230', 'Bileca');
INSERT INTO `studentska_zadruga`.`mjesto` (`ZIPkod`, `Naziv`) VALUES ('73280', 'Cajnice');

INSERT INTO `studentska_zadruga`.`poslodavac` (`PoslodavacId`, `ZIPkod`) VALUES ('1', '78000');
INSERT INTO `studentska_zadruga`.`poslodavac` (`PoslodavacId`, `ZIPkod`) VALUES ('2', '89230');
INSERT INTO `studentska_zadruga`.`poslodavac` (`PoslodavacId`, `ZIPkod`) VALUES ('3', '78400');
INSERT INTO `studentska_zadruga`.`poslodavac` (`PoslodavacId`, `ZIPkod`) VALUES ('4', '73280');
INSERT INTO `studentska_zadruga`.`poslodavac` (`PoslodavacId`, `ZIPkod`) VALUES ('5', '78000');
INSERT INTO `studentska_zadruga`.`poslodavac` (`PoslodavacId`, `ZIPkod`) VALUES ('6', '78000');
INSERT INTO `studentska_zadruga`.`poslodavac` (`PoslodavacId`, `ZIPkod`) VALUES ('7', '89230');
INSERT INTO `studentska_zadruga`.`poslodavac` (`PoslodavacId`, `ZIPkod`) VALUES ('8', '78400');
INSERT INTO `studentska_zadruga`.`poslodavac` (`PoslodavacId`, `ZIPkod`) VALUES ('9', '78000');


INSERT INTO `studentska_zadruga`.`zaposleni` (`A_JMB`, `Ime`, `Prezime`, `KorisnickoIme`, `Lozinka`) VALUES ('1234567891234', 'Admin', 'Adminovic', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');

INSERT INTO `studentska_zadruga`.`administrativni_radnik` (`A_JMB`) VALUES ('1234567891234');

INSERT INTO `studentska_zadruga`.`posao` (`PosaoId`, `CijenaPoSatu`, `OpisPosla`, `Zavrsen`, `BrojPotrebnihRadnika`, `A_JMB`, `PoslodavacId`) VALUES ('1', '6.5', 'istovar namjestaja', '0', '5', '1234567891234', '1');
INSERT INTO `studentska_zadruga`.`posao` (`PosaoId`, `CijenaPoSatu`, `OpisPosla`, `Zavrsen`, `BrojPotrebnihRadnika`, `A_JMB`, `PoslodavacId`) VALUES ('2', '4.7', 'punjenje racuna u koverte', '0', '2', '1234567891234', '2');
INSERT INTO `studentska_zadruga`.`posao` (`PosaoId`, `CijenaPoSatu`, `OpisPosla`, `Zavrsen`, `BrojPotrebnihRadnika`, `A_JMB`, `PoslodavacId`) VALUES ('3', '5.0', 'sklapanje polica', '0', '3', '1234567891234', '3');
INSERT INTO `studentska_zadruga`.`posao` (`PosaoId`, `CijenaPoSatu`, `OpisPosla`, `Zavrsen`, `BrojPotrebnihRadnika`, `A_JMB`, `PoslodavacId`) VALUES ('4', '2.5', 'pomocni radnik - gradjevina', '0', '1', '1234567891234', '3');
INSERT INTO `studentska_zadruga`.`posao` (`PosaoId`, `CijenaPoSatu`, `OpisPosla`, `Zavrsen`, `BrojPotrebnihRadnika`, `A_JMB`, `PoslodavacId`) VALUES ('5', '3.0', 'postavljanje kablova', '0', '7', '1234567891234', '4');
INSERT INTO `studentska_zadruga`.`posao` (`PosaoId`, `CijenaPoSatu`, `OpisPosla`, `Zavrsen`, `BrojPotrebnihRadnika`, `A_JMB`, `PoslodavacId`) VALUES ('6', '4.2', 'pakovanje proizvoda', '0', '1', '1234567891234', '4');


INSERT INTO `studentska_zadruga`.`clan` (`C_JMB`, `Ime`, `Prezime`, `Adresa`, `ZIPkod`, `BrojClanskeKarte`, `BrojLK`, `DatumIstekaClanskeKarte`, `A_JMB`, `Aktivan`) VALUES ('1234567899874', 'Mile', 'Milic', 'Adresa 1 ', '78000', '12317', '74M78D', '2022-04-10', '1234567891234', '1');
INSERT INTO `studentska_zadruga`.`clan` (`C_JMB`, `Ime`, `Prezime`, `Adresa`, `ZIPkod`, `BrojClanskeKarte`, `BrojLK`, `DatumIstekaClanskeKarte`, `A_JMB`, `Aktivan`) VALUES ('1234567893692', 'Sara', 'Saric', 'Adresa 2 ', '78400', '14718', '45F784', '2023-11-15', '1234567891234', '1');
INSERT INTO `studentska_zadruga`.`clan` (`C_JMB`, `Ime`, `Prezime`, `Adresa`, `ZIPkod`, `BrojClanskeKarte`, `BrojLK`, `DatumIstekaClanskeKarte`, `A_JMB`, `Aktivan`) VALUES ('1236549879632', 'Milan', 'Milanovic', 'Adresa 3 ', '78000', '23517', '4E845G', '2020-01-10', '1234567891234', '1');
INSERT INTO `studentska_zadruga`.`clan` (`C_JMB`, `Ime`, `Prezime`, `Adresa`, `ZIPkod`, `BrojClanskeKarte`, `BrojLK`, `DatumIstekaClanskeKarte`, `A_JMB`, `Aktivan`) VALUES ('1237896541598', 'Marko', 'Markovic', 'Adresa 4 ', '78400', '45619', '888N88', '2025-03-01', '1234567891234', '1');
INSERT INTO `studentska_zadruga`.`clan` (`C_JMB`, `Ime`, `Prezime`, `Adresa`, `ZIPkod`, `BrojClanskeKarte`, `BrojLK`, `DatumIstekaClanskeKarte`, `A_JMB`, `Aktivan`) VALUES ('1478523693692', 'Simo', 'Simic', 'Adresa 6', '78000', '16914', '451TTT', '2021-10-10', '1234567891234', '1');

INSERT INTO `studentska_zadruga`.`kompanija` (`PoslodavacId`, `Naziv`, `Adresa`, `JIB`, `Aktivan`) VALUES ('1', 'D.O.O. NekoTrade', 'Adresa 14', '1234569631596', '1');
INSERT INTO `studentska_zadruga`.`kompanija` (`PoslodavacId`, `Naziv`, `Adresa`, `JIB`, `Aktivan`) VALUES ('2', 'AD Gringos', 'Adresa 12', '1478522587411', '1');
INSERT INTO `studentska_zadruga`.`kompanija` (`PoslodavacId`, `Naziv`, `Adresa`, `JIB`, `Aktivan`) VALUES ('3', 'Sunce', 'Adresa 78', '7896541239632', '1');
INSERT INTO `studentska_zadruga`.`kompanija` (`PoslodavacId`, `Naziv`, `Adresa`, `JIB`, `Aktivan`) VALUES ('4', 'Eldorado Company', 'Adresa 23', '1236541231231', '1');
INSERT INTO `studentska_zadruga`.`kompanija` (`PoslodavacId`, `Naziv`, `Adresa`, `JIB`, `Aktivan`) VALUES ('5', 'DarkShop', 'Adresa 99', '7412589632587', '1');

INSERT INTO `studentska_zadruga`.`fizicko_lice` (`PoslodavacId`, `Ime`, `Prezime`, `BrojLK`, `Aktivan`) VALUES ('6', 'Stojan', 'Stojanovic', '147S88', '1');
INSERT INTO `studentska_zadruga`.`fizicko_lice` (`PoslodavacId`, `Ime`, `Prezime`, `BrojLK`, `Aktivan`) VALUES ('7', 'Petar', 'Petrovic', '4SLKE7', '1');
INSERT INTO `studentska_zadruga`.`fizicko_lice` (`PoslodavacId`, `Ime`, `Prezime`, `BrojLK`, `Aktivan`) VALUES ('8', 'Ilija', 'Ilic', '78945', '1');
INSERT INTO `studentska_zadruga`.`fizicko_lice` (`PoslodavacId`, `Ime`, `Prezime`, `BrojLK`, `Aktivan`) VALUES ('9', 'Milana', 'Milic', 'G4F7R', '1');
