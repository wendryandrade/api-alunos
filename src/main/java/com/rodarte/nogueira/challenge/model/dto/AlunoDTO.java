package com.rodarte.nogueira.challenge.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;


@Data
public class AlunoDTO {

    private int identificacao;

    private String nome;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataNascimento;

    private String sexo;

    @JsonIgnore
    private int nota1;

    @JsonIgnore
    private int nota2;

    @JsonIgnore
    private int nota3;

    private double media;

}
