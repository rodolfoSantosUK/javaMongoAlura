import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Arrays;
import java.util.Date;

public class Principal {



    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = mongoDatabase.getCollection("alunos");
        Document aluno =  collection.find().first();
        System.out.println(aluno);

        Document novoAluno =  new Document("nome", "Joao")
                .append("data_nascimento", new Date(2003,10,10))
                .append("curso",new Document("nome", "Historia"))
                .append("notas", Arrays.asList(10,9,8))
                .append("habilidades", Arrays.asList(new Document()
                                                     .append("nome","Ingles")
                                                     .append("nível","Básico")
                                                     ,
                                                     new Document()
                                                     .append("nome","Espanhol")
                                                     .append("nível","Básico")));

        collection.insertOne(novoAluno);

        collection.updateOne( Filters.eq( "nome", "Joao") ,
                new Document("$set", new Document("nome","Joao Silva")));

        collection.deleteOne(Filters.eq("nome", "Joao"));

        mongoClient.close();
    }


}
