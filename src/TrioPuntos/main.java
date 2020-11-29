package TrioPuntos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static void main (String[] args) throws IOException {
        Cloud nube = new Cloud();
        /* Tamaños para probar los algoritmos:
        * - 10
        * - 200 = 3min
        * - 450 = 3min solo DyV */
        nube.fillCloud(10);
        //nube.fillCloud(200);
        //nube.fillCloud(450);
        nube.showCloud();
        System.out.println("Size:" +nube.cloud.size());
        TrioPuntos.Algoritmos alg = new TrioPuntos.Algoritmos();

        /*DESCOMENTAR SEGÚN EL ALGORITMO A PROBAR*/
        System.out.println("Exhaustivo: \n" +alg.exhaustivo(nube.cloud));
        //System.out.println("Perimetro minimo DyV: " +alg.divideVenceras(nube.cloud));







    }
}
