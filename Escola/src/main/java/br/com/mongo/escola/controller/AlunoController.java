package br.com.mongo.escola.controller;

import br.com.mongo.escola.model.Curso;
import br.com.mongo.escola.repository.AlunoRepository;
import br.com.mongo.escola.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("/aluno/cadastrar")
    public String cadastrar(Model model) {

        return "cadastro de alunos  b";
    }

    //O correto é post
    @GetMapping("/aluno/salvar")
    public String salvar() {

        Aluno aluno = new Aluno();
        aluno.setNome("Julia");
        aluno.setDataNascimento(new Date());
        aluno.setCurso(new Curso("Sistemas da Informação"));

        alunoRepository.salvar(aluno);

        return "salvar";
    }

    @GetMapping("/aluno/listar")
    public String listar() {

        List<Aluno> alunos = alunoRepository.obterTodosAlunos();
        alunos.forEach(System.out::println);
        return "listar" ;
    }



}
