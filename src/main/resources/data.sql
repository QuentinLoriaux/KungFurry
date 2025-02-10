CREATE TABLE IF NOT EXISTS Realisateur(
    id INT primary key auto_increment,
    nom VARCHAR(100), prenom VARCHAR(100),
    date_naissance TIMESTAMP,
    celebre BOOLEAN);

INSERT INTO Realisateur(nom, prenom, date_naissance, celebre)
VALUES
    ('Cameron', 'James', '1954-08-16', false),
    ('Jackson', 'Peter', '1961-10-31', true);

CREATE TABLE IF NOT EXISTS Film(
    id INT primary key auto_increment,
    titre VARCHAR(100),
    duree INT,
    realisateur_id INT,
    genre_id INT,
    note_moyenne DOUBLE,
    nb_vues INT,
    date_sortie DATE,
    affiche BLOB,
    synopsis TEXT);

INSERT INTO Film(titre, duree, realisateur_id, genre_id, note_moyenne, nb_vues, date_sortie, affiche, synopsis)
VALUES
    ('Avatar', 162, 1, 1, 4, 1, '2009-12-18', null, 'Avatar est un film de science-fiction américain réalisé par James Cameron, sorti en 2009.'),
    ('La communauté de l''anneau', 178, 2, 9, 0, 2, '2001-12-19', null, 'La Communauté de l''anneau est un film américano-néo-zélandais réalisé par Peter Jackson, sorti en 2001.'),
    ('Les deux tours', 179, 2, 9, 0, 1, '2002-12-18', null, 'Les Deux Tours est un film américano-néo-zélandais réalisé par Peter Jackson, sorti en 2002.'),
    ('Le retour du roi', 201, 2, 9, 0, 0, '2003-12-17', null, 'Le Retour du roi est un film américano-néo-zélandais réalisé par Peter Jackson, sorti en 2003.');


CREATE TABLE IF NOT EXISTS Role(
    id INT primary key auto_increment,
    nom VARCHAR(100));

INSERT INTO Role(nom)
VALUES
    ('USER'),
    ('ADMIN');


CREATE TABLE IF NOT EXISTS Utilisateur(
    role_id INT,
    username VARCHAR(100) primary key,
    md5hex VARCHAR(100));

INSERT INTO Utilisateur(username, md5hex, role_id)
VALUES
    ('user', 'ee11cbb19052e40b07aac0ca060c23ee', 1),
    ('admin', '21232f297a57a5a743894a0e4a801fc3', 2);

CREATE TABLE IF NOT EXISTS Genre(
    id INT primary key auto_increment,
    nom VARCHAR(100));

INSERT INTO Genre(nom)
VALUES
    ('Science-fiction'),
    ('Action'),
    ('Thriller'),
    ('Policier'),
    ('Horreur'),
    ('Comédie'),
    ('Drame'),
    ('Fantastique'),
    ('Aventure'),
    ('Biopic'),
    ('Animation'),
    ('Documentaire');

CREATE TABLE IF NOT EXISTS Commentaire(id INT primary key auto_increment, film_id INT, username VARCHAR(100), texte TEXT, date TIMESTAMP);
INSERT INTO Commentaire(film_id, username, texte, date) VALUES(1, 'user', 'Helloworld', '2021-01-01 00:00:00');
INSERT INTO Commentaire(film_id, username, texte, date) VALUES(1, 'admin', 'Jerome il pue', '2021-01-01 00:00:00');

CREATE TABLE IF NOT EXISTS Note(id INT primary key auto_increment, film_id INT, username VARCHAR(100), note INT);
INSERT INTO Note(film_id, username, note) VALUES(1, 'user', 5);
INSERT INTO Note(film_id, username, note) VALUES(1, 'admin', 3);

CREATE TABLE IF NOT EXISTS Vue(id INT primary key auto_increment, film_id INT, username VARCHAR(100));
INSERT INTO Vue(film_id, username) VALUES(1, 'user');
INSERT INTO Vue(film_id, username) VALUES(2, 'admin');
INSERT INTO Vue(film_id, username) VALUES(3, 'user');
INSERT INTO Vue(film_id, username) VALUES(2, 'admin');