import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;

public class Algoritmos {
    //private casos caso;
    private Point.Double[] dista1;
    private Point.Double[] dista2;
    private Point.Double[] dista3;
    private double distaaux;
    private Point.Double[] franjaIzq;
    private Point.Double[] franjaDcha;

    public Algoritmos() {
        //this.caso = c;
        //distaaux = new Point.Double[2];
        dista1 = new Point.Double[2];
        dista2 = new Point.Double[2];
        dista3 = new Point.Double[2];
    }

    public double exhaustivo(ArrayList<Point.Double> nube){
        Instant start = Instant.now();
        Cloud auxCloud = new Cloud();
        Point.Double pi = new Point();
        Point.Double pj = new Point();
        Point.Double pk = new Point();
        double pmin = 999;
        for(int i = 0; i < nube.size(); i++){
            for(int j = i + 1; j < nube.size(); j++){
                for(int k = j + 1; k < nube.size(); k++){
                    System.out.println("--------------------");
                    System.out.println("Pmin Calculado:"+auxCloud.perimetroMin(nube.get(i),nube.get(j), nube.get(k)));
                    System.out.println("Pminimo: "+pmin);
                    if(auxCloud.perimetroMin(nube.get(i),nube.get(j), nube.get(k)) < pmin ){
                        pmin = auxCloud.perimetroMin(nube.get(i),nube.get(j), nube.get(k));
                        pi = nube.get(i);
                        pj = nube.get(j);
                        pk = nube.get(k);
                    }
                }
            }
        }
        Instant finish = Instant.now();
        Point fr = new Point();
        distaaux = fr.distanciaEuclidea(pi,pj);
        System.out.println("Perimetro minimo: "+pmin);
        System.out.println(pi);
        System.out.println(pj);
        System.out.println(pk);
        long timeElapsed = Duration.between(start,finish).toMillis();
        System.out.println("Time Elapsed: " +timeElapsed+ "ms");
        return pmin;
    }

/*
    public double divideVenceras(ArrayList<Point.Double> nube){
        HeapSort heap = new HeapSort();
        ArrayList<Point.Double> sorted = new ArrayList<>();
        sorted = heap.heapSort(nube);
        for (int i = 0; i < sorted.size(); i++)
            System.out.println(sorted.get(i));

        return -1;
    }
*/

    public double divideVenceras(ArrayList<Point.Double> nube){
        Instant startHeap = Instant.now();
        HeapSort heap = new HeapSort();
        ArrayList<Point.Double> sorted = new ArrayList<>();
        sorted = heap.heapSort(nube);
        Instant finishHeap = Instant.now();
        long timeElapsedHeap = Duration.between(startHeap,finishHeap).toMillis();

        for (int i = 0; i < sorted.size(); i++)
            System.out.println(sorted.get(i));

        System.out.println("Time Elapsed Heapsort: " +timeElapsedHeap+ "ms");
        System.out.println("------------------------------------");

        Instant startDyV = Instant.now();
        int fin = nube.size()-1;
        int centro = (nube.size()) / 2;
        Cloud auxCloud = new Cloud();

        ArrayList<Point.Double> nubeIzq = new ArrayList<>(sorted.subList(0,centro));
        ArrayList<Point.Double> nubeDcha = new ArrayList<>(sorted.subList(centro, sorted.size()));

        if(nubeIzq.size() == 3){
            Instant finishDyV = Instant.now();
            long timeElapsedDyV = Duration.between(startDyV,finishDyV).toMillis();
            System.out.println("Time Elapsed DyV Izq: " +timeElapsedDyV+ "ms");
            return auxCloud.perimetroMin(nubeIzq.get(0), nubeIzq.get(1), nubeIzq.get(2));
        }
        else {
            if (nubeDcha.size() == 3) {
                Instant finishDyV = Instant.now();
                long timeElapsedDyV = Duration.between(startDyV,finishDyV).toMillis();
                System.out.println("Time Elapsed DyV Dcha: " +timeElapsedDyV+ "ms");
                return auxCloud.perimetroMin(nubeDcha.get(0), nubeDcha.get(1), nubeDcha.get(2));
            } else {
                System.out.println("---NubeIzq---");
                for (int i = 0; i < nubeIzq.size(); i++)
                    System.out.println(nubeIzq.get(i));
                System.out.println("-------------");

                System.out.println("---NubeDcha---");
                for (int i = 0; i < nubeDcha.size(); i++)
                    System.out.println(nubeDcha.get(i));
                System.out.println("-------------");
                //double dist1,dist2,dist3;
                //dist1 = divideVenceras(nubeIzq);
                //dist2 = divideVenceras(nubeDcha);
                double minIzq = exhaustivo(nubeIzq);
                double distIzq = distaaux;
                double minDch = exhaustivo(nubeDcha);
                double distDcha = distaaux;

                System.out.println("Dist Izq: "+distIzq);
                System.out.println("Dist Dcha: "+distDcha);
                ArrayList<Point.Double> franjaAux = franja(nube,distIzq,distDcha);
                double minFranja = exhaustivo(franjaAux);

                Instant finishDyV = Instant.now();
                long timeElapsedDyV = Duration.between(startDyV,finishDyV).toMillis();
                System.out.println("Time Elapsed DyV: " +timeElapsedDyV+ "ms");

                if((minIzq < minDch) && (minIzq < minFranja)){
                    return minIzq;
                }
                else{
                    if(minFranja < minDch){
                        return minFranja;
                    }
                    else{
                        return minDch;
                    }
                }

            }
        }
        //Instant finishDyV = Instant.now();
        //long timeElapsedDyV = Duration.between(startDyV,finishDyV).toMillis();
        //System.out.println("Time Elapsed DyV: " +timeElapsedDyV+ "ms");
        //return -1;
    }

    public ArrayList<Point.Double> franja (ArrayList<Point.Double> nube, double distIzq, double distDcha){
        int centro = (nube.size())/ 2;
        System.out.println("Centro = " +centro);
        System.out.println("Math.abs(centro - (int)distIzq) = " +Math.abs(centro - (int)distIzq));
        System.out.println("Math.abs(centro + (int)distIzq) = " +Math.abs(centro + (int)distIzq));
        ArrayList<Point.Double> franja = new ArrayList<>(nube.subList(Math.abs(centro - (int)distIzq), Math.abs(centro + (int)distIzq)));
        System.out.println("---FRANJA---");
        for (int i = 0; i < franja.size(); i++)
            System.out.println(franja.get(i));
        System.out.println("-------------");

        return franja;
    }

/*
public double DyV(ArrayList<Point.Double> nube, int inicio, int fin) {
        int centro = (inicio + fin)/2; // recoge el centro para dividir
        Point aux = new Point();
        //si quedan 3 puntos, calculamos el minimo de la distancia entre los 3 puntos
        if ((fin - inicio)+1 == 3) {

            double dis1, dis2, dis3;
            dis1 = aux.distanciaEuclidea(nube.get(inicio), nube.get(inicio + 1));
            dis2 = aux.distanciaEuclidea(nube.get(inicio + 1), nube.get(fin));
            dis3 = aux.distanciaEuclidea(nube.get(inicio), nube.get(fin));

            if (dis1 < dis2) {// cmp dis 1 y 2
                setPunt(nube.get(inicio), nube.get(inicio + 1));
                return dis1;
            } else if (dis2 < dis3) {// cmp dis 2 y 3
                setPunt(nube.get(inicio + 1), nube.get(fin));
                return dis2;
            } else if (dis1 < dis3) {// cmp dis 1y 3
                setPunt(nube.get(inicio), nube.get(inicio + 1));
                return dis1;
            } else {// si no dist3 mayor
                setPunt(nube.get(inicio), nube.get(fin));
                return dis3;
            }

        //si solo quedan 2, devuelve la distancia entre ellos
        } else if ((fin - inicio)+1 == 2) {
            setPunt(nube.get(inicio), nube.get(fin));
            return aux.distanciaEuclidea(nube.get(inicio), nube.get(fin));
        //si no es ningun caso base, llamamos de nuevo a divide y venceras
        } else {
            double dist1, dist2, dist3;
            dist1 = DyV(nube, inicio, centro);
            //coge puntos y loguarda en dista1
            dista1 = getpoint();
            dist2 = DyV(nube, centro + 1, fin);
            //coge puntos y loguarda en dista2
            dista2 = getpoint();
            dist3 = divide(nube, Math.min(dist1, dist2), inicio, fin);
            //coge puntos y loguarda en dista3
            dista3 = getpoint();
            //compara cuan el la menor distancia y escribe las corrdenadas de este
            if (dist1 < dist2) {// cmp dis 1 y 2
                setPunt(dista1[0], dista1[1]);
                return dist1;
            } else if (dist2 < dist3) {// cmp dis 2 y 3
                setPunt(dista2[0], dista2[1]);
                return dist2;
            } else if (dist1 < dist3) {// cmp dis 1y 3
                setPunt(dista1[0], dista1[1]);
                return dist1;
            } else {// si no dist3 mayor
                setPunt(dista3[0], dista3[1]);
                return dist3;
            }
        }
    }

    public void setPunt(Point.Double a, Point.Double b) {
        distaaux[0] = a;
        distaaux[1] = b;
    }

    public Point.Double[] getpoint() {
        return distaaux;
    }

    public double divide (ArrayList<Point.Double> nube, double disminima, int ini, int fin){
        int centro = (ini + fin) / 2;
        int centroEnd = ((ini + fin) / 2) + 1;
        Point aux = new Point();
        while ((centro >= ini) && Math.abs(nube.get(centro).x - nube.get(centroEnd).x) < disminima) { //centro izq
            while ((centroEnd <= fin) && Math.abs(nube.get(centro).x - nube.get(centroEnd).x) < disminima) {//centro der
                // si la distancia entre los dospuntos es menor a la minima
                if ((aux.distanciaEuclidea(nube.get(centro), nube.get(centroEnd)) <
                        disminima)&& (!(nube.get(centro).equals(nube.get(centroEnd)))) && Math.abs(nube.get(centro).y - nube.get(centroEnd).y) < disminima ) {
                    //si los 2 puntos son iguales, no coje que es la distancia minima
                    if ((!(nube.get(centro).equals(nube.get(centroEnd)))) && Math.abs(nube.get(centro).y - nube.get(centroEnd).y) < disminima) {
                        disminima = aux.distanciaEuclidea(nube.get(centro), nube.get(centroEnd));
                    }
                }
                centroEnd++;
            }
            centro--;
            centroEnd = ((ini + fin) / 2) + 1;
        }
        return disminima;
    }

*/
}
