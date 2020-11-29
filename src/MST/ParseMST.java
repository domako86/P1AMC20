package MST;

import TSP.Parse;
import TrioPuntos.Cloud;
import TrioPuntos.Point;

import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseMST {
    File archivo;
    FileReader fr;
    BufferedReader br;

    ParseMST() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
    }

    public int getDimension (String fichero) throws FileNotFoundException{
        //String regexp = "[DIMENSION:] \\d+";
        String linea;
        String[] attr = null;
        int cont = 1;
        try {
            br = new BufferedReader(new FileReader(fichero));
            while ((linea = br.readLine()) != null && cont != 4) {
                cont++;
            }
            String pattern = "[DIMENSION]:";
            linea = linea.replaceAll(pattern,"");
            attr = linea.split(" ");
            br.close();
            return Integer.parseInt(attr[1]);

        }catch (IOException ex){
            ex.printStackTrace();
        }
        return Integer.parseInt(attr[1]);
    }

    public void generarDistancias(String fichero) throws IOException {
        Cloud mstcloud = new Cloud();
        Parse p = new Parse();
        mstcloud.cloud = p.leer(fichero);
        System.out.println("---Nube MST---");
        for (int i = 0; i < mstcloud.cloud.size(); i++)
            System.out.println(mstcloud.cloud.get(i));

        //coger de 2 en 2 calcular distancia euclidea y guadar en nube auxiliar, repetir el proceso agregando a la nube sin borrar los nuevos calculos y usarlo para el algoritmo de prim
        System.out.println("-------------");

        File ciudades = new File("ciudades.dat");
        BufferedWriter bw_ciudades = null;
        bw_ciudades = new BufferedWriter(new FileWriter(ciudades));
        int totalDistancia = 0;
        for (int i = 0; i < mstcloud.cloud.size(); i+=2){
            //double perimetro = 0;
            Point aux = new Point();
            bw_ciudades.write(i+"\t"+(i+1)+"\t"+ aux.distanciaEuclidea(mstcloud.cloud.get(i),mstcloud.cloud.get(i+1))+"\n");
            totalDistancia += aux.distanciaEuclidea(mstcloud.cloud.get(i),mstcloud.cloud.get(i+1));
        }
        /*
        for (int j = 1; j <= mstcloud.cloud.size(); j+=2){
            //double perimetro = 0;
            Point aux = new Point();
            bw_ciudades.write(j+" "+(j+1)+" "+ aux.distanciaEuclidea(mstcloud.cloud.get(j),mstcloud.cloud.get(j+1))+"\n");
        }
        */
        bw_ciudades.write("Total: "+totalDistancia+"\n");
        bw_ciudades.write("EOF");
        bw_ciudades.close();
    }



}
