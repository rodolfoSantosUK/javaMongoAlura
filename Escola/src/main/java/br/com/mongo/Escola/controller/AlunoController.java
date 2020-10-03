package br.com.mongo.Escola.controller;

import br.com.mongo.Escola.br.com.mongo.Escola.repository.AlunoRepository;
import br.com.mongo.Escola.model.Aluno;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class AlunoController {

    private AlunoRepository alunoRepository;

    @GetMapping("/aluno/cadastrar")
    public String cadastrar(Model model) {

        return "cadastro de alunos  b";
    }

    @PostMapping("/aluno/salvar")
    public String salvar() {

        Aluno aluno = new Aluno();
        alunoRepository.salvar(aluno);

        return "salvar";
    }


}
