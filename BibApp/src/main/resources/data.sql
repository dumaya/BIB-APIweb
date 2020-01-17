
insert into reference (id, titre, auteur) values (100, 'Memoires', 'Hugues POL');
insert into Ouvrage (id, id_reference, emplacement) values (100, 100, 'Etagere du bas');

insert into reference (id, titre, auteur) values (200, 'Les tracteurs', 'Sesmat');
insert into Ouvrage (id, id_reference, emplacement) values (200, 200, '5eme couloir, 5eme ligne');

insert into Ouvrage (id, id_reference, emplacement) values (300, 300, 'OUPS Oublié');

insert into pret (id, id_ouvrage, id_usager,date_fin, top_prolongation) values (100,100,100,'	2020-11-27 00:00:00' ,false);

insert into pret (id, id_ouvrage, id_usager,date_fin, top_prolongation) values (200,200,300,'	2018-11-27 00:00:00' ,false);
insert into pret (id, id_ouvrage, id_usager,date_fin, top_prolongation) values (300,300,300,'	2020-12-27 00:00:00' ,true );
insert into pret (id, id_ouvrage, id_usager,date_fin, date_retour, top_prolongation) values (400,300,300,'	2020-01-30 00:00:00' ,'	2020-01-10 00:00:00',false);
insert into pret (id, id_ouvrage, id_usager,date_fin, top_prolongation) values (500,100,200,'	2018-11-27 00:00:00' ,false);
insert into pret (id, id_ouvrage, id_usager,date_fin, top_prolongation) values (600,100,100,'	2018-11-27 00:00:00' ,false);

INSERT INTO usager (usager_id, nom, prenom, email, password, active) VALUES (100, 'Alexis', 'Dumay', 'alexis.dumay@axa.fr', '$2a$10$RyY4bXtV3LKkDCutlUTYDOKd2AiJYZGp4Y7MPVdLzWzT1RX.JRZyG', true);

INSERT INTO role (role_id, role) VALUES (10, 'ROLE_ADMIN');
INSERT INTO role (role_id, role) VALUES (20, 'ROLE_USAGER');
INSERT INTO role (role_id, role) VALUES (30, 'ROLE_BIBLIOTHECAIRE');

INSERT INTO usager_role (usager_id, role_id) VALUES (100, 10);
INSERT INTO usager_role (usager_id, role_id) VALUES (100, 20);
INSERT INTO usager_role (usager_id, role_id) VALUES (100, 30);

INSERT INTO usager (usager_id, nom, prenom, email, password,active) VALUES (200, 'Tom', 'Dumoulin', 'alexlanoisette@gmail.fr', '$2a$10$RyY4bXtV3LKkDCutlUTYDOKd2AiJYZGp4Y7MPVdLzWzT1RX.JRZyG',true );
INSERT INTO usager_role (usager_id, role_id) VALUES (200, 10);
INSERT INTO usager_role (usager_id, role_id) VALUES (200, 20);