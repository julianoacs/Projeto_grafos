package graphs;

import java.util.*;

public class Dijkstra implements SearchAlgorithm {
    private Graph graph;

    public Dijkstra(Graph graph) {
        this.graph = graph;
    }

    @Override
    public SearchResult search(int startId, int endId) {
        for (Node n : graph.getAllNodes()) n.reset();

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(n -> n.g));
        
        Node start = graph.getNode(startId);
        start.g = 0;
        pq.add(start);
        
        int expanded = 0;
        Set<Integer> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            
            if (visited.contains(current.id)) continue;
            visited.add(current.id);
            expanded++;

            if (current.id == endId) {
                return reconstructPath(current, expanded);
            }

            for (Edge edge : current.neighbors) {
                Node neighbor = edge.target;
                double newDist = current.g + edge.weight;
                
                if (newDist < neighbor.g) {
                    neighbor.g = newDist;
                    neighbor.parent = current;
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