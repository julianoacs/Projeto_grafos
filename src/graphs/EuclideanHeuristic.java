package graphs;

public class EuclideanHeuristic implements Heuristic {
    @Override
    public double calculate(Node a, Node b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
}