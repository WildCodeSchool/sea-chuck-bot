# SET UTF-8
SET names 'utf8';

# CREATE TABLE

CREATE TABLE joke (
    id int NOT NULL AUTO_INCREMENT,
    joke_text VARCHAR(255) NOT NULL,
    creation_date date,
    PRIMARY KEY (ID)
);

# INSERT TEST DATA

INSERT INTO joke (joke_text, creation_date) VALUES ('Chuck Norris kann Feuer mit einer Lupe machen – nachts!', NOW());
INSERT INTO joke (joke_text, creation_date) VALUES ('Chuck Norris läuft 100 Meter in einer Sekunde. Er kennt immer eine Abkürzung.', NOW());
INSERT INTO joke (joke_text, creation_date) VALUES ('Chuck Norris kann ein Feuer entfachen, indem er zwei Eiswürfel aneinander reibt.', NOW());
INSERT INTO joke (joke_text, creation_date) VALUES ('Chuck Norris wurde gestern geblitzt – beim Einparken.', NOW());
INSERT INTO joke (joke_text, creation_date) VALUES ('Wenn man Chuck Norris fragt, wie viele Liegestütze er schafft, antwortet er: "Alle."', NOW());
INSERT INTO joke (joke_text, creation_date) VALUES ('Chuck Norris kann ein Fünfeck zeichnen – mit vier Strichen.', NOW());
INSERT INTO joke (joke_text, creation_date) VALUES ('Chuck Norris entführt Aliens.', NOW());
INSERT INTO joke (joke_text, creation_date) VALUES ('Chuck Norris kann Drehtüren zuknallen.', NOW());
INSERT INTO joke (joke_text, creation_date) VALUES ('Chuck Norris kann Atom- von Ökostrom unterscheiden – und zwar am Geschmack.', NOW());
INSERT INTO joke (joke_text, creation_date) VALUES ('Chuck Norris hat bis Unendlich gezählt. Zweimal.', NOW());