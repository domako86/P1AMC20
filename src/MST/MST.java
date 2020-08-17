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
        String fichero = "burma14.tsp";
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
                //aux = ciudad[i];



                //System.out.println("Ciudad: " +ciudad[i]);
                i++;
            }
            g.addEdge(ciudad[0].intValue(), ciudad[1].intValue(), ciudad[2].intValue());
            //[0],ciudad[1],ciudad[2]);
        }
        g.MST();
    }
}

        //Pasos a seguir:
        //- Calcular distancia euclidea puntos del fichero
        //- Crear matriz de distancias
        //- Parse matriz de distancias a grafo
        //- Profit

        //Gipsy Style coger de 2 en 2 las ciudades, generar fichero y luego generar otro con las combinaciones que falten
/*
        // Initializing the graph
        Graph g = new Graph(9);
        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 2, 8);
        g.addEdge(1, 7, 11);
        g.addEdge(2, 3, 7);
        g.addEdge(2, 8, 2);
        g.addEdge(2, 5, 4);
        g.addEdge(3, 4, 9);
        g.addEdge(3, 5, 14);
        g.addEdge(4, 5, 10);
        g.addEdge(5, 6, 2);
        g.addEdge(6, 7, 1);
        g.addEdge(6, 8, 6);
        g.addEdge(7, 8, 7);

        // Appling MST
        g.MST();
        */




/*
public class MST {
    //private static int n;
    // Number of vertices in the graph
    private static  int V = iniciarVertices();
    //private static final int V = 5;

    //Realizar parse para obtener el numero de vertices totales antes de analizar el fichero
    private static int iniciarVertices(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Num Vertices: ");
        int nvertices = sc.nextInt();
        return nvertices;

    }

    // A utility function to find the vertex with minimum key
    // value, from the set of vertices not yet included in MST
    int minKey(int key[], Boolean mstSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    // A utility function to print the constructed MST stored in
    // parent[]
    void printMST(int parent[], int graph[][])
    {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++)
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
    }

    // Function to construct and print MST for a graph represented
    // using adjacency matrix representation
    void primMST(int graph[][])
    {
        // Array to store constructed MST
        int parent[] = new int[V];

        // Key values used to pick minimum weight edge in cut
        int key[] = new int[V];

        // To represent set of vertices included in MST
        Boolean mstSet[] = new Boolean[V];

        // Initialize all keys as INFINITE
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Always include first 1st vertex in MST.
        key[0] = 0; // Make key 0 so that this vertex is
        // picked as first vertex
        parent[0] = -1; // First node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < V; v++)

                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }

        // print the constructed MST
        printMST(parent, graph);
    }

    /*public static void main(String[] args)
    {
        *//* Let us create the following graph
        2 3
        (0)--(1)--(2)
        | / \ |
        6| 8/ \5 |7
        | /     \ |
        (3)-------(4)
            9         *//*
        MST t = new MST();

        int graph[][] = new int[][] { { 0, 2, 0, 6, 0 },
                { 2, 0, 3, 8, 5 },
                { 0, 3, 0, 0, 7 },
                { 6, 8, 0, 0, 9 },
                { 0, 5, 7, 9, 0 } };

        // Print the solution
        t.primMST(graph);
    }*/
//}