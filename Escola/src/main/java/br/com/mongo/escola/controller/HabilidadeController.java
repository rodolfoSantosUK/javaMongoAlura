package br.com.mongo.escola.controller;

import br.com.mongo.escola.model.Aluno;
import br.com.mongo.escola.model.Curso;
import br.com.mongo.escola.model.Habilidade;
import br.com.mongo.escola.model.Nota;
import br.com.mongo.escola.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class HabilidadeController {

    @Autowired
    private AlunoRepository alunoRepository;

    //O correto é post
    @GetMapping("/habilidade/cadastrar/{nome}")
    public String cadastrar(@PathVariable String nome) {
        Aluno aluno = alunoRepository.obterAlunoPor(nome);
        System.out.println(aluno);
        return "habilidade / cadastrar";
    }

    @GetMapping("/habilidade/salvar/{nome}")
    public String salvar(@PathVariable String nome  ) {
        Aluno aluno = alunoRepository.obterAlunoPor(nome);
        Habilidade habilidade = new Habilidade();
        habilidade.setNome("Frances");
        habilidade.setNivel("Basico");

        alunoRepository.salvar(aluno.adicionarHabilidade(aluno, habilidade));
        return "salvar habilidade";
    }

    //O correto é post
    @GetMapping("/nota/cadastrar/{nome}")
    public String cadastrarNota(@PathVariable String nome) {
        Aluno aluno = alunoRepository.obterAlunoPor(nome);
        alunoRepository.salvar(aluno.adicionarNota(aluno,new Nota(4.9)));
        return "nota / cadastrar";
    }


}
