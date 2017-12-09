import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Graph {

    private HashMap<String, LinkedList<Pair<String, Integer>>> nodes; //nod etiketter och vikter

    public Graph() {
        nodes = new HashMap<>();
    }

    public void addVertice(String label) {
        nodes.put(label, new LinkedList<>());
    }

    public void addEdge(String node1, String node2, int dist) {
        nodes.get(node1).add(new Pair<>(node2, dist));
        nodes.get(node2).add(new Pair<>(node1, dist));
    }

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

        for (String s : nodes.keySet())
            d.put(s, Integer.MAX_VALUE);

        d.put(start, 0);
        p.put(start, 0);
        while (q.peek() != null) {
            Pair<String, Integer> v = q.poll();
            if (!k.contains(v)) {
                k.add(v);
                for (Pair<String, Integer> successor : nodes.get(v.a)) {
                    int oldPathLenght = d.get(successor.a);
                    int newPathLenght = d.get(v.a) + successor.b;
                    if (!k.contains(v) && oldPathLenght > newPathLenght) {
                        d.replace(successor.a, newPathLenght);
                        p.put(successor.a, v.b);
                        q.put(successor.a, d.get(successor.a));
                    }
                }
            }

        }
        if (d.get(dest) == Integer.MAX_VALUE)
            return null;
        LinkedList<String> pathList = new LinkedList<>();
        pathList.add(dest);
        while (!pathList.get(0).equals(start)){
            pathList.addFirst(p.get(pathList.getFirst()));
        }
        return new Path(d.get(dest), pathList);
    }
}

