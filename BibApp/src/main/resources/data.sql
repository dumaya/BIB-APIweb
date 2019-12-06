
insert into reference (id, titre, auteur) values (1, 'Memoires', 'Hugues POL');
insert into Ouvrage (id, id_reference, emplacement) values (1, 1, 'Etagere du bas');
insert into pret (id, id_ouvrage, id_usager,date_fin, top_prolongation) values (1,1,1,'	2020-11-27 00:00:00' ,false);
insert into pret (id, id_ouvrage, id_usager,date_fin, top_prolongation) values (2,1,3,'	2018-11-27 00:00:00' ,false);
insert into pret (id, id_ouvrage, id_usager,date_fin, top_prolongation) values (3,1,4,'	2020-12-27 00:00:00' ,false);

insert into reference (id, titre, auteur) values (2, 'Les tracteurs', 'Sesmat');
insert into Ouvrage (id, id_reference, emplacement) values (2, 2, '5eme couloir, 5eme ligne');

insert into Ouvrage (id, id_reference, emplacement) values (3, 3, 'OUPS Oublié');