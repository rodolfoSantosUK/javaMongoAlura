package br.com.mongo.escola.repository;

import br.com.mongo.escola.codec.AlunoCodec;
import br.com.mongo.escola.model.Aluno;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class AlunoRepository {

    public void salvar(Aluno aluno) {

        Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
        AlunoCodec alunoCodec = new AlunoCodec(codec);

        CodecRegistry registro = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromCodecs(alunoCodec));

        MongoClientOptions opcoes = MongoClientOptions.builder().codecRegistry(registro).build();

        MongoClient cliente = new MongoClient("localhost:27017", opcoes);
        MongoDatabase bancoDeDados = cliente.getDatabase("test");
        MongoCollection<Aluno> alunos = bancoDeDados.getCollection("alunos", Aluno.class);
        alunos.insertOne(aluno);
        cliente.close();
    }


    public List<Aluno> obterTodosAlunos() {
        Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
        AlunoCodec alunoCodec = new AlunoCodec(codec);

        CodecRegistry registro = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromCodecs(alunoCodec));

        MongoClientOptions opcoes = MongoClientOptions.builder().codecRegistry(registro).build();

        MongoClient cliente = new MongoClient("localhost:27017", opcoes);
        MongoDatabase bancoDeDados = cliente.getDatabase("test");
        MongoCollection<Aluno> alunos = bancoDeDados.getCollection("alunos", Aluno.class);

        MongoCursor<Aluno> resultado = alunos.find().iterator();

        List<Aluno> alunosEncontrados = new ArrayList<Aluno>();

        while (resultado.hasNext()) {
            Aluno aluno = resultado.next();
            alunosEncontrados.add(aluno);
        }
        cliente.close();
        return alunosEncontrados;
    }

}
