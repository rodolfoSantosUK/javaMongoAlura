package br.com.mongo.escola.repository;

import br.com.mongo.escola.codec.AlunoCodec;
import br.com.mongo.escola.model.Aluno;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;
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
        if (aluno.getId() == null) {
            alunos.insertOne(aluno);
        } else {
            alunos.updateOne(Filters.eq("nome", aluno.getNome()), new Document("$set", aluno));

        }
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

    public Aluno obterAlunoPor(String nome) {

        Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
        AlunoCodec alunoCodec = new AlunoCodec(codec);

        CodecRegistry registro = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromCodecs(alunoCodec));


        MongoClientOptions opcoes = MongoClientOptions.builder().codecRegistry(registro).build();

        MongoClient cliente = new MongoClient("localhost:27017", opcoes);
        MongoDatabase bancoDeDados = cliente.getDatabase("test");
        MongoCollection<Aluno> alunos = bancoDeDados.getCollection("alunos", Aluno.class);

        Aluno aluno = alunos.find(Filters.eq("nome", nome)).first();
        return aluno;
    }


    public List<Aluno> obterListaAlunoPor(String nome) {

        Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
        AlunoCodec alunoCodec = new AlunoCodec(codec);

        CodecRegistry registro = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromCodecs(alunoCodec));

        MongoClientOptions opcoes = MongoClientOptions.builder().codecRegistry(registro).build();

        MongoClient cliente = new MongoClient("localhost:27017", opcoes);
        MongoDatabase bancoDeDados = cliente.getDatabase("test");
        MongoCollection<Aluno> alunosCollection = bancoDeDados.getCollection("alunos", Aluno.class);

        MongoCursor<Aluno> resultados = alunosCollection.
                find(Filters.eq("nome", nome), Aluno.class).
                iterator();

        List<Aluno> alunos = new ArrayList<Aluno>();
        while (resultados.hasNext()) {
            alunos.add(resultados.next());
        }
        return alunos;
    }


}
