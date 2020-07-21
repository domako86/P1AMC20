package TSP;

import TrioPuntos.Cloud;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class maintsp {
    public static void main(String[] args) {
        Cloud tspcloud = new Cloud();
        Parse p = new Parse();
        Scanner sc = new Scanner(System.in);
        System.out.println("Ruta fichero TSP: ");
        //String fichero = sc.nextLine();
        String fichero = "burma14.tsp";

        try {
            tspcloud.cloud = p.leer(fichero);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Cloud TSP");
        tspcloud.showCloud();

    }
}
