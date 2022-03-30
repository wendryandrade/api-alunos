package com.rodarte.nogueira.challenge.service;

import com.rodarte.nogueira.challenge.model.AlunoModel;
import com.rodarte.nogueira.challenge.model.dto.AlunoDTO;
import com.rodarte.nogueira.challenge.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private GetFileFromResourcesService getFileFromResourcesService;

    public void salvaDadosPlanilhaNaBase() throws Exception {
        var listAlunoDTO = getFileFromResourcesService.recuperarListaAlunos();

        listAlunoDTO.stream()
                .forEach(alunoDTO -> {
                    var alunoModel = new AlunoModel();
                    alunoModel.setIdentificacao(alunoDTO.getIdentificacao());
                    alunoModel.setNome(alunoDTO.getNome());
                    alunoModel.setSexo(alunoDTO.getSexo());
                    java.sql.Date sqlDate = new java.sql.Date(alunoDTO.getDataNascimento().getTime());
                    alunoModel.setDataNascimento(sqlDate);
                    alunoModel.setNota1(alunoDTO.getNota1());
                    alunoModel.setNota2(alunoDTO.getNota2());
                    alunoModel.setNota3(alunoDTO.getNota3());
                    alunoRepository.save(alunoModel);
                });
    }

    public List<AlunoDTO> recuperaAlunos() {
        var listAlunoDTO = new ArrayList<AlunoDTO>();

        alunoRepository.getAllAlunos().forEach(alunoModel -> {
            var alunoDTO = new AlunoDTO();
            alunoDTO.setIdentificacao(alunoModel.getIdentificacao());
            alunoDTO.setNome(alunoModel.getNome());
            alunoDTO.setSexo(alunoModel.getSexo());
            java.util.Date sqlUtil = new java.util.Date(alunoModel.getDataNascimento().getTime());
            alunoDTO.setDataNascimento(sqlUtil);
            alunoDTO.setMedia(alunoModel.getNota1()+alunoDTO.getNota2()+alunoModel.getNota3()/3);
            listAlunoDTO.add(alunoDTO);
        });

        return listAlunoDTO;
    }
}
