package com.rodarte.nogueira.challenge.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "ALUNO")
@Data
public class AlunoModel {
    @Id
    @Column(name = "identificacao", nullable = false)
    private int identificacao;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;

    @Column(name = "sexo", nullable = false)
    private String sexo;

    @Column(name = "nota1", nullable = false)
    private int nota1;

    @Column(name = "nota2", nullable = false)
    private int nota2;

    @Column(name = "nota3", nullable = false)
    private int nota3;
}


