# CREATE USER AND DATABASE

CREATE USER 'chuck'@'localhost' IDENTIFIED BY 'norris';
CREATE DATABASE chuckbot;
GRANT ALL PRIVILEGES ON chuckbot.* TO 'chuck'@'localhost';
FLUSH PRIVILEGES;

# CREATE TABLE

CREATE TABLE JOKES (
    ID int NOT NULL AUTO_INCREMENT,
    JOKETEXT text NOT NULL,
    ADDED date,
    PRIMARY KEY (ID)
);

# INSERT TEST DATA

INSERT INTO JOKES (JOKETEXT, ADDED) VALUES ('Chuck Norris kann Feuer mit einer Lupe machen – nachts!', NOW());
INSERT INTO JOKES (JOKETEXT, ADDED) VALUES ('Chuck Norris läuft 100 Meter in einer Sekunde. Er kennt immer eine Abkürzung.', NOW());
INSERT INTO JOKES (JOKETEXT, ADDED) VALUES ('Chuck Norris kann ein Feuer entfachen, indem er zwei Eiswürfel aneinander reibt.', NOW());
INSERT INTO JOKES (JOKETEXT, ADDED) VALUES ('Chuck Norris wurde gestern geblitzt – beim Einparken.', NOW());
INSERT INTO JOKES (JOKETEXT, ADDED) VALUES ('Wenn man Chuck Norris fragt, wie viele Liegestütze er schafft, antwortet er: "Alle."', NOW());
INSERT INTO JOKES (JOKETEXT, ADDED) VALUES ('Chuck Norris kann ein Fünfeck zeichnen – mit vier Strichen.', NOW());
INSERT INTO JOKES (JOKETEXT, ADDED) VALUES ('Chuck Norris entführt Aliens.', NOW());
INSERT INTO JOKES (JOKETEXT, ADDED) VALUES ('Chuck Norris kann Drehtüren zuknallen.', NOW());
INSERT INTO JOKES (JOKETEXT, ADDED) VALUES ('Chuck Norris kann Atom- von Ökostrom unterscheiden – und zwar am Geschmack.', NOW());
INSERT INTO JOKES (JOKETEXT, ADDED) VALUES ('Chuck Norris hat bis Unendlich gezählt. Zweimal.', NOW());
