package jvicedo.mongo;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean stillin=true;
        while(stillin!=false)
        {
            int menu=0;
            System.out.println("MENU\n\t1.Introducir una nueva obra de arte o varias a la vez \n\t2.Insertar datos de prueba(Insertar varios)\n\t3.Selecciona con un filtro\n\t4.Actualiza con un filtro\n\t5.Elimina con un filtro\n\t0.Salir");
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
                    Insertar.insertaEjemplos();
                    break;

                default:
                    System.out.println("Introduzca otro numero que si contenga una funcionalidad\n");
                    break;
            }
        }

        System.exit(0);
    }
}
