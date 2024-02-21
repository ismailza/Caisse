-- DATABASE : caisse
-- USERNAME : caisse
-- PASSWORD : 1234

-- CREATE TABLES

DROP TABLE articles;
CREATE TABLE articles(
    reference VARCHAR(12) PRIMARY KEY,
    designation VARCHAR(20) NOT NULL,
    prix FLOAT NOT NULL,
    stock INT NOT NULL
);

DROP TABLE ventes;
CREATE TABLE ventes(
    id_vente INT PRIMARY KEY,
    datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE lignes_vente;
CREATE TABLE lignes_vente(
    id_ligne_vente INT PRIMARY KEY,
    id_vente INT NOT NULL,
    reference VARCHAR(12) NOT NULL,
    prix_unitaire FLOAT NOT NULL,
    quantite INT NOT NULL
);

ALTER TABLE lignes_vente ADD CONSTRAINT fk_vente FOREIGN KEY (id_vente) REFERENCES ventes (id_vente);
ALTER TABLE lignes_vente ADD CONSTRAINT fk_article FOREIGN KEY (reference) REFERENCES articles (reference);

DROP SEQUENCE vente_seq;
CREATE SEQUENCE vente_seq START WITH 1 INCREMENT BY 1;

DROP SEQUENCE ligne_vente_seq;
CREATE SEQUENCE ligne_vente_seq START WITH 1 INCREMENT BY 1;

DROP TRIGGER create_vente;
CREATE OR REPLACE TRIGGER create_vente
    BEFORE INSERT ON ventes
    FOR EACH ROW
    BEGIN
       :NEW.id_vente := vente_seq.nextval;
    END;

CREATE OR REPLACE TRIGGER create_ligne_vente
    BEFORE INSERT ON lignes_vente
    FOR EACH ROW
    BEGIN
        :NEW.id_ligne_vente := ligne_vente_seq.nextval;
        UPDATE articles SET stock = stock - :NEW.quantite WHERE reference = :NEW.reference;
    END;

INSERT INTO articles (reference, designation, prix, stock) VALUES ('10001', 'Article 1', 10.00, 100);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10002', 'Article 2', 10.50, 95);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10003', 'Article 3', 11.00, 90);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10004', 'Article 4', 11.50, 85);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10005', 'Article 5', 12.00, 80);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10006', 'Article 6', 12.50, 75);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10007', 'Article 7', 13.00, 70);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10008', 'Article 8', 13.50, 65);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10009', 'Article 9', 14.00, 60);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10010', 'Article 10', 14.50, 55);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10011', 'Article 11', 15.00, 50);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10012', 'Article 12', 15.50, 45);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10013', 'Article 13', 16.00, 40);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10014', 'Article 14', 16.50, 35);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10015', 'Article 15', 17.00, 30);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10016', 'Article 16', 17.50, 25);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10017', 'Article 17', 18.00, 20);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10018', 'Article 18', 18.50, 15);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10019', 'Article 19', 19.00, 10);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10020', 'Article 20', 19.50, 5);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10021', 'Article 21', 20.00, 100);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10022', 'Article 22', 20.50, 95);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10023', 'Article 23', 21.00, 90);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10024', 'Article 24', 21.50, 85);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10025', 'Article 25', 22.00, 80);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10026', 'Article 26', 22.50, 75);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10027', 'Article 27', 23.00, 70);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10028', 'Article 28', 23.50, 65);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10029', 'Article 29', 24.00, 60);
INSERT INTO articles (reference, designation, prix, stock) VALUES ('10030', 'Article 30', 24.50, 55);


SELECT * FROM ventes NATURAL JOIN lignes_vente;