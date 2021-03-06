package TrioPuntos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class Algoritmos {

    private Point.Double[] dista1;
    private Point.Double[] dista2;
    private Point.Double[] dista3;

    private double distaaux;
    private Point.Double[] franjaIzq;
    private Point.Double[] franjaDcha;

    public Algoritmos() {
        //this.caso = c;
        //distaaux = new TrioPuntos.Point.Double[2];
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
        double areaMax = 1;
        //FileWriter fw_exhaustivo = null;
        BufferedWriter bw_exhaustivo = null;
        int cont = 0;
        try {
            File exh = new File("exhaustivo"+cont+".dat");
            cont++;
            bw_exhaustivo = new BufferedWriter(new FileWriter(exh));
            double tiempo = 0;
            for(int i = 0; i < nube.size(); i++){
                Instant finish = Instant.now();
                long timeExh = Duration.between(start,finish).toMillis();
                //exh = i+" "+timeExh+"\n";
                bw_exhaustivo.write(i+"\t"+timeExh+"\n");
                //bw_exhaustivo.newLine();
                for(int j = i + 1; j < nube.size(); j++){
                    for(int k = j + 1; k < nube.size(); k++){
                        //tiempo = auxCloud.perimetroMin(nube.get(i),nube.get(j), nube.get(k));
                        System.out.println("--------------------");
                        System.out.println("Pmin Calculado:"+auxCloud.perimetroMin(nube.get(i),nube.get(j), nube.get(k)));
                        //System.out.println("Pminimo: "+pmin);
                        System.out.println("Area Calculada: "+auxCloud.areaHeron(nube.get(i), nube.get(j), nube.get(k)));
                        //System.out.println("AreaMax: "+areaMax);

                        if((auxCloud.perimetroMin(nube.get(i), nube.get(j), nube.get(k)) <= pmin)){
                            if((auxCloud.perimetroMin(nube.get(i), nube.get(j), nube.get(k)) == pmin) && (auxCloud.areaHeron(nube.get(i), nube.get(j), nube.get(k)) > areaMax)){
                                areaMax = auxCloud.areaHeron(nube.get(i), nube.get(j), nube.get(k));
                            }
                            else{
                                pmin = auxCloud.perimetroMin(nube.get(i),nube.get(j), nube.get(k));
                                pi = nube.get(i);
                                pj = nube.get(j);
                                pk = nube.get(k);
                                areaMax = auxCloud.areaHeron(nube.get(i), nube.get(j), nube.get(k));
                            }



                        }

                    }
                }
            }
            bw_exhaustivo.close();
            Instant finish = Instant.now();
            Point fr = new Point();
            distaaux = fr.distanciaEuclidea(pi,pj);
            System.out.println("Perimetro minimo: "+pmin);
            System.out.println("Area: "+areaMax);
            System.out.println(pi);
            System.out.println(pj);
            System.out.println(pk);
            long timeElapsed = Duration.between(start,finish).toMillis();
            System.out.println("Time Elapsed Ex: " +timeElapsed+ "ms");
            //return pmin;


        } catch (IOException e) {
            e.printStackTrace();
        }

        return pmin;

    }


    public double divideVenceras(ArrayList<Point.Double> nube) throws IOException{
        Instant total = Instant.now();

        Instant startHeap = Instant.now();
        File dyv = new File("dyv.dat");
        BufferedWriter bw_dyv = null;
        bw_dyv = new BufferedWriter(new FileWriter(dyv));
        HeapSort heap = new HeapSort();
        ArrayList<Point.Double> sorted = new ArrayList<>();
        sorted = heap.heapSort(nube);
        Instant ahora = Instant.now();
        bw_dyv.write(Duration.between(total,ahora).toMillis()+"\n");
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
        //int j = 0;
        Instant finishDivide = Instant.now();
        long timeDyV = Duration.between(startDyV,finishDivide).toMillis();
        //bw_dyv.write(j+" "+timeDyV+"\n");
        Instant ahora1 = Instant.now();
        bw_dyv.write(Duration.between(total,ahora1).toMillis()+"\n");
        if(nubeIzq.size() == 3){
            Instant finishDyV = Instant.now();
            long timeElapsedDyV = Duration.between(startDyV,finishDyV).toMillis();
            System.out.println("Time Elapsed DyV Izq: " +timeElapsedDyV+ "ms");
            Instant ahora2 = Instant.now();
            bw_dyv.write(Duration.between(total,ahora2).toMillis()+"\n");
            bw_dyv.close();
            return auxCloud.perimetroMin(nubeIzq.get(0), nubeIzq.get(1), nubeIzq.get(2));
        }
        else {
            if (nubeDcha.size() == 3) {
                Instant finishDyV = Instant.now();
                long timeElapsedDyV = Duration.between(startDyV,finishDyV).toMillis();
                System.out.println("Time Elapsed DyV Dcha: " +timeElapsedDyV+ "ms");
                Instant ahora3 = Instant.now();
                bw_dyv.write(Duration.between(total,ahora3).toMillis()+"\n");
                bw_dyv.close();
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
                double distIzq = Math.abs(distaaux-centro);
                double minDch = exhaustivo(nubeDcha);
                double distDcha = Math.abs(distaaux+centro);
                Instant ahora4 = Instant.now();
                bw_dyv.write(Duration.between(total,ahora4).toMillis()+"\n");
                Instant finishDivide2 = Instant.now();
                long timeDyV2 = Duration.between(startDyV,finishDivide2).toMillis();
                //bw_dyv.write(j+" "+timeDyV2+"\n");

                System.out.println("Dist Izq: "+distIzq);
                System.out.println("Dist Dcha: "+distDcha);
                ArrayList<Point.Double> franjaAux = franja(nube,distIzq,distDcha);
                double minFranja = exhaustivo(franjaAux);

                Instant finishDivide3 = Instant.now();
                long timeDyV3 = Duration.between(startDyV,finishDivide3).toMillis();
                //bw_dyv.write(j+" "+timeDyV3+"\n");
                Instant ahora5 = Instant.now();
                bw_dyv.write(Duration.between(total,ahora5).toMillis()+"\n");
                Instant finishDyV = Instant.now();
                long timeElapsedDyV = Duration.between(startDyV,finishDyV).toMillis();
                System.out.println("Time Elapsed DyV: " +timeElapsedDyV+ "ms");

                if((minIzq < minDch) && (minIzq < minFranja)){
                    bw_dyv.close();
                    return minIzq;
                }
                else{
                    if(minFranja < minDch){
                        bw_dyv.close();
                        return minFranja;
                    }
                    else{
                        bw_dyv.close();
                        return minDch;
                    }
                }

            }
        }

    }

    public ArrayList<Point.Double> franja (ArrayList<Point.Double> nube, double distIzq, double distDcha){
        //System.out.println("******** METODO FRANJA ***********");
        int centro = (nube.size())/ 2;
        /*System.out.println("Centro = " +centro);
        System.out.println("nube.size() = " +nube.size());
        System.out.println("distIzq = " +distIzq);
        System.out.println("distDcha = " +distDcha);
        System.out.println("Math.abs(centro - (int)distIzq) = " +Math.abs(centro - (int)distIzq));
        System.out.println("Math.abs(centro + (int)distDcha) = " +Math.abs(centro + (int)distDcha));*/

        if(distDcha >= nube.size())
            distDcha = nube.size();
        /*
        else
            distDcha = Math.abs(centro + (int)distDcha);
        */
        //System.out.println("distDcha) = " +distDcha);
        //System.out.println("distIzq) = " +distIzq);
        //ArrayList<Point.Double> franja = new ArrayList<>(nube.subList(Math.abs(centro - (int)distIzq), (int)distDcha));
        ArrayList<Point.Double> franja = new ArrayList<>(nube.subList(Math.abs((int)distIzq), (int)distDcha));
        /*System.out.println("---FRANJA---");
        for (int i = 0; i < franja.size(); i++)
            System.out.println(franja.get(i));
        System.out.println("-------------");*/

        return franja;
    }


}
