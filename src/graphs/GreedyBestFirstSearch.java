package graphs;

import java.util.*;

public class GreedyBestFirstSearch implements SearchAlgorithm {
    private Graph graph;
    private Heuristic heuristic;

    public GreedyBestFirstSearch(Graph graph, Heuristic heuristic) {
        this.graph = graph;
        this.heuristic = heuristic;
    }

    @Override
    public SearchResult search(int startId, int endId) {
        for (Node n : graph.getAllNodes()) n.reset();

        Node start = graph.getNode(startId);
        Node end = graph.getNode(endId);

        start.g = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(n -> n.h));

        start.h = heuristic.calculate(start, end);
        pq.add(start);

        int expanded = 0;
        Set<Integer> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.id == endId) {
                return reconstructPath(current, expanded);
            }

            if (visited.contains(current.id)) continue;
            visited.add(current.id);
            expanded++;

            for (Edge edge : current.neighbors) {
                Node neighbor = edge.target;
                if (!visited.contains(neighbor.id)) {
                    neighbor.parent = current;
                    neighbor.g = current.g + edge.weight; 
                    neighbor.h = heuristic.calculate(neighbor, end);
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