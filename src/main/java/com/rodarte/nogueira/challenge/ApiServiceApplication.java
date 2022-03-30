package com.rodarte.nogueira.challenge;

import com.rodarte.nogueira.challenge.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiServiceApplication {

    @Autowired
    private final AlunoService alunoService;

    public ApiServiceApplication(AlunoService alunoService) throws Exception {
        this.alunoService = alunoService;

        alunoService.salvaDadosPlanilhaNaBase();
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiServiceApplication.class, args);
    }

}
