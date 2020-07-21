package TrioPuntos;

import java.util.Random;

import static java.lang.Math.sqrt;

public class Point extends java.awt.Point.Double  {
    //private double x;
    //private double y;

    public double distanciaEuclidea (Point.Double p, Point.Double q){
        return sqrt(Math.pow(p.x - q.x,2) + Math.pow(q.y - q.y,2));
    }

    public Point (double x1, double y1){
        this.x = x1;
        this.y = y1;
    }
    public Point(){}

    public Point.Double rndCoordenada(int rango){
        Point.Double aux = new Point.Double();
        aux.x = new Random().nextInt(rango);
        aux.y = new Random().nextInt(rango);
        //System.out.println(aux.x+","+aux.y);
        return aux;
    }

    public void setX (double x1){    x = x1;   }


    public void setY(double y1){  y = y1;    }


    /*
    public void paint(Graphics g){
        g.setColor(Color.green);
        g.fillOval(x-1, y-1, 3, 3);
        g.setColor(Color.black);
        g.drawOval(x-1,y-1, 3,3);
    }

 */
}
