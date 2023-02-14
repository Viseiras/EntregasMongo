package jvicedo.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.w3c.dom.html.HTMLFieldSetElement;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Indexes.descending;
import static com.mongodb.client.model.Projections.*;

public class Galeria {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean stillin=true;
        while(stillin!=false)
        {
            int menu=0;
            System.out.println("\nMENU\n\t1.Introducir una nueva obra de arte o varias a la vez \n\t2.Insertar datos de prueba(Insertar varios)\n\t3.Selecciona con un filtro\n\t4.Selección Compleja\n\t5.Actualiza\n\t6.Elimina\n\t0.Salir");
            menu=sc.nextInt();

            switch (menu)
            {
                case 0:
                    stillin=false;
                    break;

                case 1:
                    System.out.println("Cuantas obras de arte quieres insertar?[1]");
                    int inserciones = sc.nextInt();

                    if(inserciones==0)
                    {
                        System.out.println("No se introducirán datos...");
                    }
                    else if(inserciones==1)
                    {
                        System.out.println("Se introducirá una obra:");
                        Insertar.insertaUno();
                    }
                    else if(inserciones>1)
                    {
                        System.out.println("Se introducirán "+inserciones);
                        Insertar.InsertaMuchos(inserciones);
                    }
                    else
                    {
                        System.out.println("Se introducirá una obra:");
                        Insertar.insertaUno();
                    }
                    break;

                case 2:
                    MongoClient mc = new MongoClient();
                    MongoDatabase mdb = mc.getDatabase("Galería");
                    MongoCollection mco = mdb.getCollection("Obra de Arte");
                    List<Obra> obras = new ArrayList<>();
                    Document doc= new Document();
                    FindIterable fit = mco.find(doc);
                    MongoCursor mcursor= fit.iterator();
                    if(mcursor.hasNext()==false)
                        Insertar.insertaEjemplos();
                    else
                        System.out.println("Ya hay datos insertados en la base de datos");
                    break;

                case 3:
                    System.out.println("Introduce por cual campo quieres filtrar [por defecto ID]\n\t1.ID\n\t2.Nombre\n\t3.Autor\n\t4.Año de Creación\n\t5.Estilo\n\t6.Precio de venta\n\tOtro.Mostrar todos");
                    String filtro="", filtroValor="";
                    int campo = sc.nextInt();
                    if(campo==0)
                        campo=1;
                    sc.nextLine();
                    switch (campo)
                    {
                        case 1:
                            filtro="_id";
                            System.out.println("Introduce el ID por el que quieras seleccionar:");
                            filtroValor=sc.nextLine();
                            break;
                        case 2:
                            filtro="nombre";
                            System.out.println("Introduce el nombre por el que que quieres seleccionar:");
                            filtroValor=sc.nextLine();
                            break;
                        case 3:
                            filtro="autor";
                            System.out.println("Introduce el autor por el que quieres filtrar:");
                            filtroValor=sc.nextLine();
                            break;
                        case 4:
                            filtro="año de creación";
                            System.out.println("Introduce el año por el que quieres filtrar:");
                            filtroValor=sc.nextLine();
                            break;
                        case 5:
                            filtro="estilo";
                            System.out.println("Introduce el estilo por el que quieres filtrar:");
                            filtroValor=sc.nextLine();
                            break;
                        case 6:
                            filtro="precio de venta";
                            System.out.println("Introduce por el precio que quieres filtrar:");
                            filtroValor=sc.nextLine();
                            break;
                        default:
                            break;
                    }
                    Selecciona.Muestra(filtro,filtroValor);
                    break;

                case 4:
                    mc = new MongoClient();
                    mdb = mc.getDatabase("Galería");
                    mco = mdb.getCollection("Obra de Arte");
                    obras = new ArrayList<>();
                    doc= new Document();
                    fit = mco.find(doc);
                    mcursor = fit.iterator();
                    System.out.println("SELECCIONES PREHECHAS:\n\t1.Obras antes del 1600 (siglo de oro de la pintura española)\n\t2.Obras Posteriores o del año 1500\n\t3.Ordenado por precio");
                    int menusel=sc.nextInt();
                    switch (menusel)
                    {
                        case 1:
                            Selecciona.MuestraCompleja("año de creación","$lt","1600");
                            break;
                        case 2:
                            Selecciona.MuestraCompleja("año de creación","$gte","1500");
                            break;
                        case 3:
                            MongoCollection<Document> collection = mco;
                            List<Document> sortedDocuments = collection.find().sort(Sorts.descending("precio de venta")).into(new ArrayList<>());
                            for (Document o : sortedDocuments)
                            {
                                System.out.println(o);
                            }
                            break;
                    }
                    break;
                case 5:
                    Actualiza.actualizar();
                    break;
                case 6:
                    Borra.borrar();
                    break;
                default:
                    System.out.println("Introduzca otro numero que si contenga una funcionalidad\n");
                    break;
            }
        }

        System.exit(0);
    }
}
