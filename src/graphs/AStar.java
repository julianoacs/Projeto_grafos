package graphs;

import java.util.*;

public class AStar implements SearchAlgorithm {
    private Graph graph;
    private Heuristic heuristic;

    public AStar(Graph graph, Heuristic heuristic) {
        this.graph = graph;
        this.heuristic = heuristic;
    }

    @Override
    public SearchResult search(int startId, int endId) {
        for (Node n : graph.getAllNodes()) n.reset();

        Node start = graph.getNode(startId);
        Node end = graph.getNode(endId);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(n -> n.f));

        start.g = 0;
        start.h = heuristic.calculate(start, end);
        start.f = start.g + start.h;
        pq.add(start);

        int expanded = 0;
        Set<Integer> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.id == endId) {
                return reconstructPath(current, expanded);
            }

            visited.add(current.id);
            expanded++;

            for (Edge edge : current.neighbors) {
                Node neighbor = edge.target;
                if (visited.contains(neighbor.id)) continue;

                double tentativeG = current.g + edge.weight;

                if (tentativeG < neighbor.g) {
                    neighbor.parent = current;
                    neighbor.g = tentativeG;
                    neighbor.h = heuristic.calculate(neighbor, end);
                    neighbor.f = neighbor.g + neighbor.h;
                    
                    pq.remove(neighbor);
                    pq.add(neighbor);
                }
            }
        }
        return new SearchResult(false, "", 0, expanded);
    }
    
    private SearchResult reconstructPath(Node target, int expanded) {
        List<String> path = new ArrayList<>();
        Node curr = target;
        double cost = curr.g;
        while (curr != null) {
            path.add(String.valueOf(curr.id));
            curr = curr.parent;
        }
        Collections.reverse(path);
        return new SearchResult(true, String.join(" -> ", path), cost, expanded);
    }
}