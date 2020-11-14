package TrioPuntos;

import java.util.ArrayList;

import static java.lang.Math.sqrt;

public class Cloud {

    public ArrayList<Point.Double> cloud = new ArrayList<>();


    public void fillCloud (int size){
        for (int i = 0; i < size; i++){
            Point p = new Point();
            cloud.add(p.rndCoordenada(100)); //Random coord to set range 1-100
        }
    }

    public void showCloud(){
        for (int i = 0; i < cloud.size(); i++){
            System.out.println("Punto["+i+"] =("+cloud.get(i).getX()+","+cloud.get(i).getY()+")");
        }
    }

    public double perimetroMin(Point.Double p1, Point.Double p2, Point.Double p3){
        double perimetro = 0;
        Point aux = new Point();
        perimetro += aux.distanciaEuclidea(p1,p2);
        perimetro += aux.distanciaEuclidea(p1,p3);
        perimetro += aux.distanciaEuclidea(p2,p3);
        //System.out.println("Test PerimetroMin: "+perimetro);
        return perimetro;
    }

    public double areaHeron (Point.Double p1, Point.Double p2, Point.Double p3){
        double perimetro = perimetroMin(p1, p2, p3);
        //System.out.println("Semiperimetro: "+semiPerimetro);
        Point auxHeron = new Point();
        double area = 0;
        double lado1 = auxHeron.distanciaEuclidea(p1, p2);
        double lado2 = auxHeron.distanciaEuclidea(p1, p3);
        double lado3 = auxHeron.distanciaEuclidea(p2, p3);
        //System.out.println("Lado1: "+lado1);
        //System.out.println("Lado2: "+lado2);
        //System.out.println("Lado3: "+lado3);
        area = sqrt(perimetro * (perimetro - lado1) * (perimetro - lado2) * (perimetro - lado3)) / 2;
        //System.out.println("AreaHeron: "+area);
        return area;
    }

    public void agregar (Point p){
        cloud.add(p);
    }
}
