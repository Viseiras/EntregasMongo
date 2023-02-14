package jvicedo.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Scanner;

public class Borra {
    public static void borrar()
    {
        Scanner sc = new Scanner(System.in);
        MongoClient mc = new MongoClient();
        MongoDatabase mdb = mc.getDatabase("Galería");
        MongoCollection collection = mdb.getCollection("Obra de Arte");
        System.out.print("\nID de la obra de arte a eliminar: ");

        int deleteID = sc.nextInt();

        // Si el número de eliminados es 0, se imprime el primer String, si no, se imprime el segundo
        System.out.println(
                collection.deleteOne(new Document("_id", deleteID)).getDeletedCount() == 0 ?
                        "No se ha encontrado el Doujinshi con ID " + deleteID : "Eliminado la obra "+deleteID+ "con éxito"
        );
    }
}
