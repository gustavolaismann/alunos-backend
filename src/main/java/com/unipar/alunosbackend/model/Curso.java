package com.unipar.alunosbackend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Curso {

    private Long id;
    private String materia;
    private String descricao;

    public Curso(Long id, String materia, String descricao ){
        this.id = id;
        this.materia = materia;
        this.descricao = descricao;
    }
}
