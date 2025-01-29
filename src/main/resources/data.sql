CREATE TABLE IF NOT EXISTS Realisateur(id INT primary key auto_increment, nom VARCHAR(100), prenom VARCHAR(100), date_naissance TIMESTAMP, celebre BOOLEAN);
INSERT INTO Realisateur(nom, prenom, date_naissance, celebre) VALUES('Cameron', 'James', '1954-08-16', false);
INSERT INTO Realisateur(nom, prenom, date_naissance, celebre) VALUES('Jackson', 'Peter', '1961-10-31', true);

CREATE TABLE IF NOT EXISTS Film(id INT primary key auto_increment, titre VARCHAR(100), duree INT, realisateur_id INT, genre_id INT, note_moyenne DOUBLE);
INSERT INTO Film(titre, duree, realisateur_id, genre_id, note_moyenne) VALUES('avatar', 162, 1, 1, 4);
INSERT INTO Film(titre, duree, realisateur_id, genre_id, note_moyenne) VALUES('La communauté de l''anneau', 178, 2, 9, 0);
INSERT INTO Film(titre, duree, realisateur_id, genre_id, note_moyenne) VALUES('Les deux tours', 179, 2, 9, 0);
INSERT INTO Film(titre, duree, realisateur_id, genre_id, note_moyenne) VALUES('Le retour du roi', 201, 2, 9, 0);

CREATE TABLE IF NOT EXISTS Role(id INT primary key auto_increment, nom VARCHAR(100));
INSERT INTO Role(nom) VALUES('USER');
INSERT INTO Role(nom) VALUES('ADMIN');

CREATE TABLE IF NOT EXISTS Utilisateur(id INT primary key auto_increment, role_id INT, username VARCHAR(100), md5hex VARCHAR(100));
INSERT INTO Utilisateur(username, md5hex, role_id) VALUES('user', 'ee11cbb19052e40b07aac0ca060c23ee', 1);
INSERT INTO Utilisateur(username, md5hex, role_id) VALUES('admin', '21232f297a57a5a743894a0e4a801fc3', 2);

CREATE TABLE IF NOT EXISTS Genre(id INT primary key auto_increment, nom VARCHAR(100));
INSERT INTO Genre(nom) VALUES('Science-fiction');
INSERT INTO Genre(nom) VALUES('Action');
INSERT INTO Genre(nom) VALUES('Thriller');
INSERT INTO Genre(nom) VALUES('Policier');
INSERT INTO Genre(nom) VALUES('Horreur');
INSERT INTO Genre(nom) VALUES('Comédie');
INSERT INTO Genre(nom) VALUES('Drame');
INSERT INTO Genre(nom) VALUES('Fantastique');
INSERT INTO Genre(nom) VALUES('Aventure');
INSERT INTO Genre(nom) VALUES('Biopic');
INSERT INTO Genre(nom) VALUES('Animation');
INSERT INTO Genre(nom) VALUES('Documentaire');

CREATE TABLE IF NOT EXISTS Commentaire(id INT primary key auto_increment, film_id INT, utilisateur_id INT, texte TEXT);
INSERT INTO Commentaire(film_id, utilisateur_id, texte) VALUES(1, 1, 'Helloworld');
INSERT INTO Commentaire(film_id, utilisateur_id, texte) VALUES(1, 2, 'Jerome il pue');

CREATE TABLE IF NOT EXISTS Note(id INT primary key auto_increment, film_id INT, utilisateur_id INT, note INT);
INSERT INTO Note(film_id, utilisateur_id, note) VALUES(1, 1, 5);
INSERT INTO Note(film_id, utilisateur_id, note) VALUES(1, 2, 3);