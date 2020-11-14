package TSP;

import TrioPuntos.Cloud;
import TrioPuntos.Algoritmos;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class maintsp {
    public static void main(String[] args) throws IOException {
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
        //tspcloud.showCloud();
        TrioPuntos.Algoritmos alg = new TrioPuntos.Algoritmos();
        //System.out.println("Perimetro min Exhaustivo:" +alg.exhaustivo(tspcloud.cloud));
        System.out.println("############### MAIN TSP ###################");
        System.out.println("Perimetro min DyV:" +alg.divideVenceras(tspcloud.cloud));
    }
}
