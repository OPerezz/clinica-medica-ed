DROP DATABASE clinica;
CREATE DATABASE IF NOT EXISTS clinica;

USE clinica;

CREATE TABLE pacientes ( 
    id BIGINT AUTO_INCREMENT,
    nome VARCHAR(100),
    email VARCHAR(100),
    cpf BIGINT(11),
    idade INTEGER(2),
    endereco VARCHAR(30),
    problema VARCHAR(20),
    PRIMARY KEY( id )
);

CREATE TABLE medicos (
    id BIGINT AUTO_INCREMENT,
    nome VARCHAR(100),
    email VARCHAR(100),
    cpf BIGINT(11),
    idade INT(2),
    crm VARCHAR(20),
    telefone VARCHAR(15),

    PRIMARY KEY (id)
);

CREATE TABLE atendimentos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    medico_id BIGINT NOT NULL,
    paciente_id BIGINT NOT NULL,
    data DATETIME NOT NULL,

    FOREIGN KEY (medico_id) REFERENCES medicos(id),
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);

INSERT INTO pacientes (nome, email, cpf, idade, endereco, problema) VALUES ('Joao Silva', 'joao@teste.com', 1111111111, 23, 'Rua: XXX , SP', 'Asma');

INSERT INTO medicos (nome, email, cpf, idade, crm)
VALUES ('Carlos Silva', 'carlos@example.com', 12345678901, 35, 'CRM12345');

INSERT INTO atendimentos (medico_id, paciente_id, data)
VALUES (LAST_INSERT_ID(), 1, '2026-11-26 14:30:00');

SELECT * from pacientes;
SELECT * from medicos;
SELECT * from atendimentos;
