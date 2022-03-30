package com.rodarte.nogueira.challenge.controller;

import com.rodarte.nogueira.challenge.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping({"/api/aluno"})
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllAlunos(HttpServletRequest request) throws Exception {
        return new ResponseEntity<>(alunoService.recuperaAlunos(), HttpStatus.OK);
    }

}
