package TSP;

import TrioPuntos.Point;

import java.io.*;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parse {
    File archivo;
    FileReader fr;
    BufferedReader br;

    Parse() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
    }

    void leer (String fichero) throws FileNotFoundException {
        try {
            br = new BufferedReader(new FileReader(fichero));
            Pattern pat = Pattern.compile("NODE_COORD_SECTION");
            Matcher mat = pat.matcher("NODE_COORD_SECTION");
            String linea;
            while ((linea = br.readLine()) != null) {
                //System.out.println(mat.matches());
                if (linea.matches("NODE_COORD_SECTION")) {
                        while (!(linea = br.readLine()).matches("EOF")) {
                            StringTokenizer token = new StringTokenizer(linea, " ");
                            System.out.println("----");
                            while (token.hasMoreTokens()) {
                                System.out.println("Token0: "+token.nextToken());
                                System.out.println("Token1: "+token.nextToken());
                                System.out.println("Token2: "+token.nextToken());

                                //System.out.println("------");
                            }
                        }
                    }
                }

            //if (mat.matches()) {



        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
