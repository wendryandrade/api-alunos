package com.rodarte.nogueira.challenge.repository;

import com.rodarte.nogueira.challenge.model.AlunoModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends CrudRepository<AlunoModel, String> {

    @Query(value = "SELECT * FROM ALUNO ORDER BY data_nascimento desc", nativeQuery = true)
    List<AlunoModel> getAllAlunos();

}