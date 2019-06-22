
use studentska_zadruga;
delimiter $$
create trigger clan_posao_insert_trigger after insert
on clan_posao
for each row
BEGIN
 update posao set BrojPotrebnihRadnika=BrojPotrebnihRadnika-1 where PosaoId=new.PosaoId;
END$$
delimiter ; 

delimiter $$
create procedure update_posao(in cijenaPoSatu DECIMAL(10, 2), in brojPotrebnihRadnika integer,in posaoId integer)
begin
	update posao set CijenaPoSatu=cijenaPoSatu, BrojPotrebnihRadnika=brojPotrebnihRadnika where PosaoId=posaoId;
end$$
delimiter;

delimiter $$
create procedure update_clan_posao(in cJmb varchar(13), in posaoId integer, in brojRadnihSati float, in ukupnoZaradjeno float)
begin
	update clan_posao set BrojRadnihSati=BrojRadnihSati+brojRadnihSati, Zaradjeno=Zaradjeno+ukupnoZaradjeno where C_JMB=cJmb and PosaoId=posaoId;
end$$
delimiter;
delimiter $$
create procedure insert_into_clan_posao(in cJmb varchar(13), in posaoId integer, in brojRadnihSati float, in ukupnoZaradjeno float, in dat date)
begin
	insert into clan_posao(BrojRadnihSati, Zaradjeno, C_JMB,PosaoId, Datum) values (brojRadnihSati, ukupnoZaradjeno, cJmb, posaoId, dat);
	update posao set BrojPotrebnihRadnika=BrojPotrebnihRadnika-1 where PosaoId=posaoId;
end$$
delimiter;

delimiter $$
create procedure insert_into_radnik(in aJMB char(13), 
													in ime varchar(50), 
													in prezime varchar(50), 
													in korisnickoIme varchar(20), 
													in lozinka varchar(50))
begin
		insert into zaposleni(A_JMB, Ime, Prezime, KorisnickoIme, Lozinka) values (aJMB, ime, prezime, korisnickoIme, lozinka);
		insert into radnik(A_JMB) values (aJMB);
end$$
delimiter;

delimiter $$
create procedure insert_into_clan(in cJMB char(13), in ime varchar(50), in prezime varchar(50), 
								  in adresa varchar(50), in zipKod INTEGER,
                                  in brojClanskeKarte integer, in brojLK varchar(50),
                                  in datumIstekaClanskeKarte date, in aJMB char(13))
begin
		insert into clan(C_JMB, Ime, Prezime, Adresa, ZIPkod, BrojClanskeKarte, BrojLK, 
						 DatumIstekaClanskeKarte, A_JMB) values (cJMB, ime, prezime, 
								   adresa, zipKod,
                                   brojClanskeKarte, brojLK,
                                   datumIstekaClanskeKarte, aJMB);
end$$
delimiter;



delimiter $$
create procedure insert_into_posao(in cijenaPoSatu decimal(10, 2), in opisPosla varchar(100), in brojPotrebnihRadnika int,
								   in aJmb char(13), in poslodavacId int)
begin
	insert into posao(CijenaPoSatu, OpisPosla, BrojPotrebnihRadnika, A_JMB, PoslodavacId) values (cijenaPoSatu, opisPosla,
					brojPotrebnihRadnika, aJmb, poslodavacId);
end $$; 

delimiter $$
create procedure insert_into_fizicko_lice(in ZipKod integer, in ime varchar(50), in prezime varchar(50), in brojLK varchar(20))
begin
	insert into poslodavac(PoslodavacId, ZIPkod) values (null, ZipKod);
    insert into fizicko_lice(PoslodavacId, Ime, Prezime, BrojLK) values (LAST_INSERT_ID(), ime, prezime, brojLK);
end$$
delimiter;

delimiter $$
create procedure insert_into_kompanija(in ZipKod integer, in naziv varchar(50), in adresa varchar(50), in jib char(13))
begin
	insert into poslodavac(PoslodavacId, ZIPkod) values (null, ZipKod);
    insert into kompanija(PoslodavacId, Naziv, Adresa, JIB) values(LAST_INSERT_ID(), naziv, adresa, jib);
end$$
delimiter;


delimiter $$


create view pregled_clan_posao  as
select Ime, Prezime, BrojRadnihSati, Zaradjeno, OpisPosla, Datum from  clan_posao
natural join clan
natural join posao;

create view pregled_uradjenih_poslova as
select * from clan_posao 
natural join posao
natural join clan
where clan_posao.C_JMB=clan.C_JMB and clan_posao.PosaoId=posao.PosaoId;

create view prikaz_poslova as
select * from posao
natural join poslodavac;

create view prikaz_clanova as
select * from clan
where Aktivan is true;

create view prikaz_fizickih_lica as
select * from fizicko_lice
natural join poslodavac;

create view prikaz_kompanija as
select * from kompanija
natural join poslodavac;

