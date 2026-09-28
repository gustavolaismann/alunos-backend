package com.unipar.alunosbackend.controller;


import com.unipar.alunosbackend.model.Aluno;
import com.unipar.alunosbackend.model.Curso;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    // Simulação Banco de dados
    private List<Aluno> bancoDados = new ArrayList<>();

    // Para concatenar o ID
    private Long proximoId = 1L;

    // Simulação do banco de cursos
    private List<Curso> bancoCursos = List.of(
            new Curso(1L, "Desenvolvimento Backend", "Java, Springboot, Hibernate"),
            new Curso(2L, "Desenvolvimento Frontend", "Angular, Typescript, SPA"),
            new Curso(3L, "Banco de Dados", "Modelagem e consultas SQL")
    );


    // GET para listar todos os registros
    @GetMapping("/listar")
    public ResponseEntity<List<Aluno>> listarAlunos(){
        return ResponseEntity.ok(bancoDados);
    }

    // GET para buscar pelo id
    @GetMapping("/listar/{index}")
    public ResponseEntity<Aluno> listarAluno(@PathVariable int index){
        if (index <= -1){
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(bancoDados.get(index));
        }
    }

    // POST para cadastrar
    @PostMapping("/gravar")
    public ResponseEntity<String> gravarAluno(@RequestBody(required = true) Aluno aluno){

        List<Curso> cursos = new ArrayList<>();

        for (Curso cursoJson: aluno.getCursos()){
            for(Curso cursoCadastrado : bancoCursos){
                if (cursoCadastrado.getId().equals(cursoJson.getId())){
                    cursos.add(cursoCadastrado);
                    break;
                }
            }
        }

        aluno.setId(proximoId++);
        aluno.setCursos(cursos);
        bancoDados.add(aluno);
        return  ResponseEntity.ok("Adicionado " + aluno.getNome());
    }

    // PUT para editar
    @PutMapping("/editar/{index}")
    public ResponseEntity<Aluno> editarAluno(@PathVariable int index,
                                             @RequestBody(required = false) Aluno aluno){
        if (aluno == null){
            return ResponseEntity.noContent().build();
        } else {

            List<Curso> cursos = new ArrayList<>();

            for (Curso cursoJson: aluno.getCursos()){
                for(Curso cursoCadastrado : bancoCursos){
                    if (cursoCadastrado.getId().equals(cursoJson.getId())){
                        cursos.add(cursoCadastrado);
                        break;
                    }
                }
            }
            Aluno alunoAntigo = bancoDados.get(index);
            Aluno a = new Aluno();
            a.setNome(aluno.getNome());
            a.setId(alunoAntigo.getId());
            a.setCursos(cursos);
            a.setEmail(aluno.getEmail());


            bancoDados.set(index, a);

            return ResponseEntity.ok(a);
        }
    }


    // Deletar
    @DeleteMapping("/deletar/{index}")
    public ResponseEntity deletarAluno(@PathVariable int index){
        bancoDados.remove(index);
        return ResponseEntity.ok("Aluno " + index + " deletado" );

    }

}


