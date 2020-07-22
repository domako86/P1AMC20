package TrioPuntos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static void main (String[] args) throws IOException {
        Cloud nube = new Cloud();
        /*
        TrioPuntos.Cloud sizes to test:
        - 200
        - 500
        - 1500
        - 5000


         */

        nube.fillCloud(100);
        nube.showCloud();
        System.out.println("Size:" +nube.cloud.size());
        TrioPuntos.Algoritmos alg = new TrioPuntos.Algoritmos();
        System.out.println("Perimetro min Exhaustivo:" +alg.exhaustivo(nube.cloud));
        System.out.println("##################################");
        System.out.println("Perimetro min DyV:" +alg.divideVenceras(nube.cloud));






/*
        TrioPuntos.HeapSort heap = new TrioPuntos.HeapSort();
        ArrayList<TrioPuntos.Point.Double> sorted = new ArrayList<>();
        sorted = heap.heapSort(nube.cloud);
        for (int i = 0; i < sorted.size(); i++)
            System.out.println(sorted.get(i));

*/
/*
        TrioPuntos.Point p1 = new TrioPuntos.Point(0,0);
        TrioPuntos.Point p2 = new TrioPuntos.Point(1,3);
        TrioPuntos.Point p3 = new TrioPuntos.Point(1,5);

        TrioPuntos.Point q1 = new TrioPuntos.Point(0,0);
        TrioPuntos.Point q2 = new TrioPuntos.Point(0,2);
        TrioPuntos.Point q3 = new TrioPuntos.Point(2,1);

        TrioPuntos.Cloud aux = new TrioPuntos.Cloud();
        System.out.println("Perimetro min P: "+aux.perimetroMin(p1,p2,p3));
        System.out.println("Perimetro min Q: "+aux.perimetroMin(q1,q2,q3));
*/
    }
}
