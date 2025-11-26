package graphs;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int id;
    public double x;
    public double y;
    public List<Edge> neighbors;

    public double g = 0;
    public double h = 0;
    public double f = 0;
    public Node parent = null;

    public Node(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.neighbors = new ArrayList<>();
    }

    public void addEdge(Edge edge) {
        this.neighbors.add(edge);
    }
    
    public void reset() {
        this.g = Double.MAX_VALUE;
        this.h = 0;
        this.f = Double.MAX_VALUE;
        this.parent = null;
    }
}