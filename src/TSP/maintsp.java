package TSP;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class maintsp {
    public static void main(String[] args) {
        Parse p = new Parse();
        Scanner sc = new Scanner(System.in);
        System.out.println("Ruta fichero TSP: ");
        //String fichero = sc.nextLine();
        String fichero = "burma14.tsp";
        try {
            p.leer(fichero);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
