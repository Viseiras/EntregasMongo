package jvicedo.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Iterator;
import java.util.Scanner;

public class Actualiza{
    public static void actualizar() {
        Scanner sc = new Scanner(System.in);
        MongoClient mc = new MongoClient();
        MongoDatabase mdb = mc.getDatabase("Galería");
        MongoCollection collection = mdb.getCollection("Obra de Arte");
        System.out.print("\nID de la obra de arte a actualizar: ");
        int updateID = sc.nextInt();
        sc.nextLine();
        FindIterable<Document> iterable = collection.find(new Document("_id", updateID));
        Iterator<Document> iterator = iterable.iterator();

        if (!iterator.hasNext())
            System.out.println("No se ha encontrado la Obra de Arte con ID " + updateID);

        else {
            Document updated;
            Document base = iterator.next();
            Obra obra = new Obra(
                    base.getLong("_id"),
                    base.getString("nombre"),
                    base.getString("autor"),
                    base.getInteger("año de creación"),
                    base.getString("estilo"),
                    base.getDouble("precio de venta")
            );

            System.out.println("\n" + obra + "\n\nElige el campo a actualizar: nombre, autor, año de creación, estilo ,precio de venta (debe escribirse exactamente así).");

            switch (sc.nextLine()) {
                case "nombre" -> {
                    String newName;
                    do {
                        System.out.print("Inserta un nuevo nombre: ");
                        newName = sc.nextLine();
                    } while(newName.isEmpty() || newName.isBlank());

                    updated = new Document("$set", new Document("nombre", newName));
                }
                case "autor" -> {
                    String newAutor;
                    do {
                        System.out.print("Inserta un nuevo autor: ");
                        newAutor = sc.nextLine();
                    } while(newAutor.isEmpty() || newAutor.isBlank());
                    updated = new Document("$set", new Document("autor", newAutor));
                }
                case "año de creación" -> {
                    int newYear;
                    do {
                        System.out.print("Inserta un nuevo año de creación: ");
                        newYear = sc.nextInt();
                    } while(newYear <= 0);
                    updated = new Document("$set", new Document("año de creación", newYear));
                }
                case "estilo" -> {
                    String newStyle;
                    do {
                        System.out.print("Inserta un nuevo estilo: ");
                        newStyle = sc.nextLine();
                    } while(newStyle.isEmpty() || newStyle.isBlank());

                    updated = new Document("$set", new Document("estilo", newStyle));
                }
                case "precio de venta" -> {
                    double newPrize;
                    do {
                        System.out.print("Inserta un nuevo precio de venta: ");
                        newPrize = sc.nextDouble();
                    } while(newPrize <= 0);
                    updated = new Document("$set", new Document("precio de venta", newPrize));
                }
                default -> {
                    System.out.println("Saliendo.");
                    return;
                }
            }
            collection.updateOne(base, updated);
        }
    }
}