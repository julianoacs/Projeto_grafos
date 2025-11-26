package graphs;

public class ManhattanHeuristic implements Heuristic {
    @Override
    public double calculate(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}