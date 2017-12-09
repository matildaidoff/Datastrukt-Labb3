import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Graph {

    private HashMap<String, LinkedList<Pair<String, Integer>>> nodes; //nod etiketter och vikter

    public Graph() {
        nodes = new HashMap<>();
    }

    public void addVertice(String label) { ... }

    public void addEdge(String node1, String node2, int dist) { ... }

    public static class Path {
        public int totalDist;
        public List<String> vertices;
        public Path(int totalDist, List<String> vertices) {
            this.totalDist = totalDist;
            this.vertices = vertices;
        }

        @Override
        public String toString() {
            return "totalDist: " + totalDist + ", vertices: " + vertices.toString();
        }
    }

    public Path shortestPath(String start, String dest) {
        HashMap<String, Integer> d = new HashMap<>();
        HashMap<String, Integer> p = new HashMap<>();
        HashSet<Pair<String, Integer>> k = new HashSet();
        PrioMap<String, Integer> q = new APrioMap<>();

    }
}