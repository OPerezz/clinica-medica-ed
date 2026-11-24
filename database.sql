CREATE DATABASE IF NOT EXISTS clinica;

USE clinica;

CREATE TABLE pacientes ( 
    id int AUTO_INCREMENT,
    nome varchar(100),
    email varchar(100),
    cpf float(30),
    idade integer(2),
    endereco varchar(30),
    problema varchar(20),
    PRIMARY KEY( id )
);

INSERT INTO pacientes (nome, email, cpf, idade, endereco, problema) VALUES ('Joao Silva', 'joao@teste.com', 1111111111, 23, 'Rua: XXX , SP', 'Asma');

SELECT * FROM pacientes;
