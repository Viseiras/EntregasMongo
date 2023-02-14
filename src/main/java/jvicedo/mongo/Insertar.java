package jvicedo.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Insertar {
    public static void insertaUno()
    {
        Scanner sc = new Scanner(System.in);
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("Galería");
        MongoCollection mco = database.getCollection("Obra de Arte");
        Obra obra;
        System.out.println("ID:");
        Long y=(long) sc.nextInt();
        sc.nextLine();
        System.out.println("Nombre:");
        String nombre= sc.nextLine();
        System.out.println("Autor:");
        String autor= sc.nextLine();
        System.out.println("Año de Creación:");
        int anyo= sc.nextInt();
        sc.nextLine();
        System.out.println("Estilo:");
        String estilo= sc.nextLine();
        System.out.println("Precio:");
        double precio= sc.nextDouble();
        obra = new Obra(y,nombre, autor, anyo, estilo, precio);
        Document doc = new org.bson.Document("_id", obra.getId())
                .append("nombre",obra.getNombre().toLowerCase())
                .append("autor",obra.getAutor().toLowerCase())
                .append("año de creación",obra.getAnyoCreacion())
                .append("estilo",obra.getEstilo().toLowerCase())
                .append("precio de venta", obra.getPrecioVenta());
        mco.insertOne(doc);
        mongoClient.close();
    }
    public static void InsertaMuchos(int inserciones)
    {
        Scanner sc = new Scanner(System.in);
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("Galería");
        MongoCollection mco = database.getCollection("Obra de Arte");
        Obra obra;
        List<Obra> obras = new ArrayList<Obra>();
        System.out.println("Introduce a partir de que ID quieres introducir(los demás se pondrán solos):");
        int idz= sc.nextInt();
        sc.nextLine();
        for(int i=0;i<inserciones;i++)
        {
            Long y=(long) idz+i;
            System.out.println("Obra numero["+(i+1)+"]");
            System.out.println("Nombre:");
            String nombre= sc.nextLine();
            System.out.println("Autor:");
            String autor= sc.nextLine();
            System.out.println("Año de Creación:");
            int anyo= sc.nextInt();
            sc.nextLine();
            System.out.println("Estilo:");
            String estilo= sc.nextLine();
            System.out.println("Precio:");
            double precio= sc.nextDouble();
            sc.nextLine();
            obra = new Obra(y,nombre.toLowerCase().trim(), autor.toLowerCase().trim(), anyo, estilo.toLowerCase().trim(), precio);
            obras.add(obra);
        }
        List<Document> docs = new ArrayList<Document>();
        for(int i=0;i<obras.size();i++)
        {
            obra = obras.get(i);
            Document doc = new org.bson.Document("_id", obra.getId())
                    .append("nombre",obra.getNombre().toLowerCase())
                    .append("autor",obra.getAutor().toLowerCase())
                    .append("año de creación",obra.getAnyoCreacion())
                    .append("estilo",obra.getEstilo().toLowerCase())
                    .append("precio de venta", obra.getPrecioVenta());
            docs.add(doc);
        }
        mco.insertMany(docs);
        mongoClient.close();
    }
    public static void insertaEjemplos()
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
                    .append("nombre",obra.getNombre().toLowerCase().trim())
                    .append("autor",obra.getAutor().toLowerCase().trim())
                    .append("año de creación",obra.getAnyoCreacion())
                    .append("estilo",obra.getEstilo().toLowerCase().trim())
                    .append("precio de venta", obra.getPrecioVenta());
            docs.add(doc);
        }
        mco.insertMany(docs);
        mongoClient.close();
    }
}
