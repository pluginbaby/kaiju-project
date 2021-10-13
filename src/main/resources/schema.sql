CREATE TABLE dna_type
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    type  varchar NOT NULL,
    max   INT     NOT NULL,
    min   INT     NOT NULL,
    total INT     NOT NULL

);


CREATE TABLE dna
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    sample      varchar NOT NULL,
    dna_type_id INT     NOT NULL,
    CONSTRAINT `fk_dna_type_id` FOREIGN KEY (`dna_type_id`) REFERENCES `dna_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
