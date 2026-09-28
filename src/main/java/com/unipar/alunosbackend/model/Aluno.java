package com.unipar.alunosbackend.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Aluno {
    private Long id;
    private String nome;
    private String email;

    private List<Curso> cursos = new ArrayList<Curso>();
}
