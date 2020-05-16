DROP SCHEMA IF EXISTS BroDoGames;
CREATE SCHEMA BroDoGames;
USE BroDoGames;

CREATE TABLE prodotto(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
    titolo VARCHAR(50) NOT NULL,
    numVenduti INTEGER NOT NULL,
    prezzoFisico NUMERIC NOT NULL,
    prezzoDigitale NUMERIC NOT NULL,
    descrizione VARCHAR(100),
	qtaFisico INTEGER NOT NULL,
    qtaDigitale INTEGER NOT NULL,
    casaSviluppatrice VARCHAR(30) NOT NULL,
    inVendita BOOLEAN NOT NULL,
    pegi INTEGER,
    dataUscita DATE NOT NULL,
    cover MEDIUMBLOB

);

CREATE TABLE utente(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(30) UNIQUE,
    nome VARCHAR(20) NOT NULL,
    cognome VARCHAR(20) NOT NULL
);

CREATE TABLE ordine(
	idOrdine INTEGER AUTO_INCREMENT,
	idUtente INTEGER,
    idProdotto INTEGER,
	quantita INTEGER NOT NULL,
    dataOra DATE NOT NULL,
    prezzo INTEGER NOT NULL,
    consegnato BOOLEAN NOT NULL,
    iva DOUBLE NOT NULL,
    FOREIGN KEY(idUtente) REFERENCES utente(id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    FOREIGN KEY(idProdotto) REFERENCES prodotto(id) ON UPDATE NO ACTION ON DELETE NO ACTION,
	PRIMARY KEY (idOrdine, idUtente, idProdotto)
);

INSERT INTO utente (username, nome, cognome) VALUES ("user1", "Mario", "Rossi");
INSERT INTO prodotto (titolo, numVenduti, prezzoFisico, prezzoDigitale, descrizione, qtaFisico, qtaDigitale, casaSviluppatrice, inVendita, pegi, dataUscita) VALUES("Super Mario", 0, 15, 7, "Gioco molto vecchio", 10, 20, "Nintendo", true, 7, "2005-05-05");