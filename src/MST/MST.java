package MST;

import java.util.*;
import java.lang.*;
import java.io.*;

public class MST {
    File archivo;
    FileReader fr;
    static BufferedReader br;

// Pair class with implemented comparable
static class Pair<U extends Comparable<U>, V extends Comparable<V>> implements Comparable<Pair<U, V>> {
    public final U a;
    public final V b;

    private Pair(U a, V b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;
        if (!a.equals(pair.a))
            return false;
        return b.equals(pair.b);
    }

    // Overriding so that objects in map
    // could find the object key
    @Override
    public int hashCode() {
        return 31 * a.hashCode() + b.hashCode();
    }

    @Override
    public String toString() {
        return "(" + a + ", " + b + ")";
    }

    @Override
    public int compareTo(Pair<U, V> o) {
        return getV().compareTo(o.getV());
    }

    private U getU() {
        return a;
    }

    private V getV() {
        return b;
    }
}

static class Graph {

    int vertices;
    ArrayList[] edges;

    // This variable keeps the least cost edge
    static Pair<Pair<Integer, Integer>,
            Integer>
            minCostEdge;

    Graph(int vertices) {
        minCostEdge = new Pair<>(new Pair<>(1, 1),
                Integer.MAX_VALUE);
        this.vertices = vertices;
        edges = new ArrayList[vertices + 1];
        for (int i = 0; i <= vertices; i++) {
            edges[i]
                    = new ArrayList<Pair<Integer, Integer>>();
        }
    }

    void addEdge(int a, int b, int weight) {
        edges[a].add(new Pair<>(b, weight));

        // Since its undirected, adding the
        // edges to both the vertices
        edges[b].add(new Pair<>(a, weight));
        if (weight < minCostEdge.b) {
            minCostEdge
                    = new Pair<>(new Pair<>(a, b), weight);
        }
    }

    void MST() {

        // Priority queue for applying heap
        PriorityQueue<Pair<Pair<Integer, Integer>,
                Integer>>
                priorityQueue
                = new PriorityQueue<>();

        // Adding all the connected vertices
        // of MinCostEdge vertex A to PQ
        Iterator<Pair<Integer, Integer>> iterator
                = edges[minCostEdge.a.a].listIterator();
        while (iterator.hasNext()) {
            Pair<Integer, Integer> pair
                    = iterator.next();
            priorityQueue.add(
                    new Pair<>(
                            new Pair<>(minCostEdge.a.a, pair.a),
                            pair.b));
        }

        // Adding all the connected vertices
        // of MinCostEdge vertex B to PQ
        iterator = edges[minCostEdge.a.b].listIterator();
        while (iterator.hasNext()) {
            Pair<Integer, Integer> pair = iterator.next();
            priorityQueue.add(
                    new Pair<>(
                            new Pair<>(minCostEdge.a.b, pair.a),
                            pair.b));
        }

        // Set to check vertex is added or not
        Set<Integer> addedVertices = new HashSet<>();

        // Set contains all the added edges and cost from source
        Set<Pair<Pair<Integer, Integer>, Integer>> addedEdges
                = new HashSet<>();

        // Using the greedy approach to find
        // the least costing edge to the GRAPH
        while (addedEdges.size() < vertices - 1) {

            // Polling from priority queue
            Pair<Pair<Integer, Integer>, Integer> pair
                    = priorityQueue.poll();

            // Checking wether the vertex A is added or not
            if (!addedVertices.contains(pair.a.a)) {
                addedVertices.add(pair.a.a);
                addedEdges.add(pair);

                // Adding all the connected vertices with vertex A
                iterator = edges[pair.a.a].listIterator();
                while (iterator.hasNext()) {
                    Pair<Integer, Integer> pair1
                            = iterator.next();
                    priorityQueue.add(
                            new Pair<>(
                                    new Pair<>(pair.a.a, pair1.a),
                                    pair1.b));
                }
            }

            // Checking wether the vertex B is added or not
            if (!addedVertices.contains(pair.a.b)) {
                addedVertices.add(pair.a.b);
                addedEdges.add(pair);

                // Adding all the connected vertices with vertex B
                iterator = edges[pair.a.b].listIterator();
                while (iterator.hasNext()) {
                    Pair<Integer, Integer> pair1
                            = iterator.next();
                    priorityQueue
                            .add(
                                    new Pair<>(
                                            new Pair<>(pair.a.b, pair1.a),
                                            pair1.b));
                }
            }
        }

        // Printing the MST
        Iterator<Pair<Pair<Integer, Integer>, Integer>> iterator1
                = addedEdges.iterator();
        System.out.println("((A"
                + ", "
                + "B)"
                + ", "
                + "Cost)");
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }
    }
}
    public static void main(String[] args) throws IOException {
        ParseMST p = new ParseMST();
        Scanner sc = new Scanner(System.in);
        System.out.println("Ruta fichero TSP: ");
        //String fichero = sc.nextLine();

        /* DESCOMENTAR SEGÚN FICHERO A UTILIZAR */

        //String fichero = "burma14.tsp";
        String fichero = "berlin52.tsp";
        //String fichero = "ch130.tsp";
        //String fichero = "ch150.tsp";

        System.out.println("Dimension fichero: "+p.getDimension(fichero));
        p.generarDistancias(fichero);

        String ciudades_file = "ciudades.dat";
        br = new BufferedReader(new FileReader(ciudades_file));
        String linea;
        //while ((linea = br.readLine()) != null) {
        Graph g = new Graph(p.getDimension(ciudades_file));
        while (!(linea = br.readLine()).matches("EOF")) {
            StringTokenizer token = new StringTokenizer(linea, " ");
            //System.out.println("----");
            int i = 0;
            Double[] ciudad = new Double[3];
            //Integer[] aux = new Integer[3];
            while (token.hasMoreTokens()) {
                ciudad[i] = Double.parseDouble(token.nextToken());
                i++;
            }
            g.addEdge(ciudad[0].intValue(), ciudad[1].intValue(), ciudad[2].intValue());
            //[0],ciudad[1],ciudad[2]);
        }
        g.MST();
    }
}
