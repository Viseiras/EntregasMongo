package jvicedo.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class InsertaEjemplos {
    public static void insertar()
    {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("Galería");
        MongoCollection mco = database.getCollection("Obra de Arte");
        Obra obra;
        List<Obra> obras = new ArrayList<Obra>();
        obra = new Obra(Long.parseLong("1"),"La Capilla Sixtina","Miguel Ángel",1512,"Alto Renacimiento",0);
        obras.add(obra);
        obra = new Obra(Long.parseLong("2"),"La Gioconda","Leonardo Da Vinci",1519,"Alto Renacimiento",300000000);
        obras.add(obra);
        obra = new Obra(Long.parseLong("3"),"El Grito","Edvard Munch",1893,"Expresionismo",120000000);
        obras.add(obra);
        obra = new Obra(Long.parseLong("4"),"La persistencia de la memoria","Salvador Dalí",1931,"Surrealismo",400);
        obras.add(obra);
        obra = new Obra(Long.parseLong("5"),"El 3 de Mayo en Madrid","Francisco de Goya",1814,"Pintura Historica",0);
        obras.add(obra);
        List<Document> docs = new ArrayList<Document>();
        boolean stillcounting = true;
        for(int i=0;i<obras.size();i++)
        {
            obra = obras.get(i);
            Document doc = new org.bson.Document("_id", obra.getId())
                    .append("nombre",obra.getNombre())
                    .append("autor",obra.getAutor())
                    .append("año de creación",obra.getAnyoCreacion())
                    .append("estilo",obra.getEstilo())
                    .append("precio de venta", obra.getPrecioVenta());
            docs.add(doc);
        }
        mco.insertMany(docs);
        mongoClient.close();
    }
}
