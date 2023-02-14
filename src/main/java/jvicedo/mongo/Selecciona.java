package jvicedo.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Selecciona {
    public static void Muestra(String filtro, String filtroValor)
    {
        MongoClient mc = new MongoClient();
        MongoDatabase mdb = mc.getDatabase("Galería");
        MongoCollection mco = mdb.getCollection("Obra de Arte");
        List<Obra> obras = new ArrayList<>();
        Document doc;
        if(filtro=="")
        {
            doc= new Document();
        }
        else if(filtro=="_id")
        {
            doc= new Document(filtro,Long.parseLong(filtroValor));
        }
        else if(filtro=="año de creación")
        {
            doc= new Document(filtro,Integer.parseInt(filtroValor));
        }
        else if(filtro=="precio de venta")
        {
            doc= new Document(filtro,Double.parseDouble(filtroValor));
        }
        else
        {
            //Aquí quitamos todas las mayusculas que se introduzcan y si hay espacios delante o detrás de la string
            filtroValor= filtroValor.toLowerCase().trim();
            doc = new Document(filtro, filtroValor);
        }
        FindIterable fit = mco.find(doc);

        MongoCursor mcursor = fit.iterator();
        while(mcursor.hasNext()){
            doc = (Document) mcursor.next();
            Obra obra = new Obra();
            obra.setId(doc.getLong("_id"));
            obra.setNombre(doc.getString("nombre"));
            obra.setAutor(doc.getString("autor"));
            obra.setEstilo(doc.getString("estilo"));
            obra.setAnyoCreacion(doc.getInteger("año de creación"));
            obra.setPrecioVenta(doc.getDouble("precio de venta"));
            obras.add(obra);
        }
        for(Obra obra : obras)
        {
            System.out.println(obra);
        }
    }
    public static void MuestraCompleja(String filtro,String modificador ,String filtroValor)
    {
        MongoClient mc = new MongoClient();
        MongoDatabase mdb = mc.getDatabase("Galería");
        MongoCollection mco = mdb.getCollection("Obra de Arte");
        List<Obra> obras = new ArrayList<>();
        Document doc;
        if(filtro=="")
        {
            doc= new Document();
        }
        else if(filtro=="_id")
        {
            doc= new Document(filtro,new Document(modificador,Long.parseLong(filtroValor)));
        }
        else if(filtro=="año de creación")
        {
            doc= new Document(filtro,new Document(modificador,Integer.parseInt(filtroValor)));
        }
        else if(filtro=="precio de venta")
        {
            doc= new Document(filtro,new Document(modificador,Double.parseDouble(filtroValor)));
        }
        else
        {
            //Aquí quitamos todas las mayusculas que se introduzcan y si hay espacios delante o detrás de la string
            filtroValor= filtroValor.toLowerCase().trim();
            doc = new Document(filtro, new Document(modificador,filtroValor));
        }
        FindIterable fit = mco.find(doc);

        MongoCursor mcursor = fit.iterator();
        while(mcursor.hasNext()){
            doc = (Document) mcursor.next();
            Obra obra = new Obra();
            obra.setId(doc.getLong("_id"));
            obra.setNombre(doc.getString("nombre"));
            obra.setAutor(doc.getString("autor"));
            obra.setEstilo(doc.getString("estilo"));
            obra.setAnyoCreacion(doc.getInteger("año de creación"));
            obra.setPrecioVenta(doc.getDouble("precio de venta"));
            obras.add(obra);
        }
        for(Obra obra : obras)
        {
            System.out.println(obra);
        }
    }
}
