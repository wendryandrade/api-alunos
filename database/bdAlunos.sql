CREATE DATABASE testDB;

USE testDB;

CREATE TABLE ALUNO (
    identificacao int,
    nome varchar(255),
    sexo varchar(255),
    data_nascimento DATE,
    nota1 int,
    nota2 int,
    nota3 int,
    PRIMARY KEY (identificacao)
);