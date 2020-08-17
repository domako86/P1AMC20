package TSP;

import TrioPuntos.Cloud;
import TrioPuntos.Point;

import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parse {
    File archivo;
    FileReader fr;
    BufferedReader br;

    public Parse() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
    }

    public ArrayList<Point2D.Double> leer (String fichero) throws FileNotFoundException {
        try {
            br = new BufferedReader(new FileReader(fichero));
            Pattern pat = Pattern.compile("NODE_COORD_SECTION");
            Matcher mat = pat.matcher("NODE_COORD_SECTION");
            String linea;
            Cloud auxCloud = new Cloud();
            //Point.Double p = new Point.Double();
            while ((linea = br.readLine()) != null) {
                //System.out.println(mat.matches());
                if (linea.matches("NODE_COORD_SECTION")) {
                    while (!(linea = br.readLine()).matches("EOF")) {
                         StringTokenizer token = new StringTokenizer(linea, " ");
                         System.out.println("----");
                         int i = 0;
                         Double[] ciudad = new Double[3];
                         while (token.hasMoreTokens()) {
                             ciudad[i] = Double.parseDouble(token.nextToken());
                             System.out.println("Ciudad: " +ciudad[i]);
                             i++;
                         }
                         Point p = new Point();
                         p.setX(ciudad[1]);
                         p.setY(ciudad[2]);
                         auxCloud.cloud.add(p);
                    }
                }
            }
            return auxCloud.cloud;

        }catch (IOException ex){
            ex.printStackTrace();
        }

        return null;
    }
}
