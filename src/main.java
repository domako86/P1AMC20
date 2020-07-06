import java.util.ArrayList;

public class main {

    public static void main (String[] args){
        Cloud nube = new Cloud();
        /*
        Cloud sizes to test:
        - 200
        - 500
        - 1500
        - 5000


         */

        nube.fillCloud(5);
        nube.showCloud();
        System.out.println("Size:" +nube.cloud.size());
        Algoritmos alg = new Algoritmos();
        System.out.println("Perimetro min:" +alg.exhaustivo(nube.cloud));

        HeapSort heap = new HeapSort();
        ArrayList<Point.Double> sorted = new ArrayList<>();
        sorted = heap.heapSort(nube.cloud);
        for (int i = 0; i < sorted.size(); i++)
            System.out.println(sorted.get(i));


        Point p1 = new Point(0,0);
        Point p2 = new Point(1,3);
        Point p3 = new Point(1,5);

        Point q1 = new Point(0,0);
        Point q2 = new Point(0,2);
        Point q3 = new Point(2,1);

        Cloud aux = new Cloud();
        System.out.println("Perimetro min P: "+aux.perimetroMin(p1,p2,p3));
        System.out.println("Perimetro min Q: "+aux.perimetroMin(q1,q2,q3));

    }
}
