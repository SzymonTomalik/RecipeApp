
USE `scrumlab` ;
CREATE TABLE IF NOT EXISTS `scrumlab`.`otherInformations` (
                                                   `id` INT NOT NULL AUTO_INCREMENT COMMENT 'Klucz główny tabeli.',
                                                   `companyName` VARCHAR(245) NULL COMMENT 'Nazwa firmy',
                                                   `companyAdress` VARCHAR(245) NULL COMMENT 'AdresFirmy',
                                                   `companyEmail` VARCHAR(245) NULL COMMENT 'Adres email',
                                                   `companyPhone` VARCHAR(60) NULL COMMENT 'numer telefonu',
                                                   `authors` VARCHAR(60) NULL COMMENT 'autorzy',
                                                   PRIMARY KEY (`id`))
    ENGINE = InnoDB
    COMMENT = 'Tabela zawierająca dane użytkowników';

INSERT INTO scrumlab.otherInformations (id, companyName, companyAdress, companyEmail, companyPhone, authors) VALUES (1, 'Zaplanuj jedzonko', 'Wiśniwa 3, Wygwizdy dolne', 'zaplanuj@zaplanujedzonko.pl', '666-666-666', 'Szymon,Mateusz,Patryk');