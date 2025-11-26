package graphs;

import java.util.*;

public class BFS implements SearchAlgorithm {
    private Graph graph;

    public BFS(Graph graph) {
        this.graph = graph;
    }

    @Override
    public SearchResult search(int startId, int endId) {
        for (Node n : graph.getAllNodes()) n.reset();

        Queue<Node> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        
        Node start = graph.getNode(startId);
        Node end = graph.getNode(endId);
        
        queue.add(start);
        visited.add(start.id);
        start.g = 0;
        
        int expanded = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            expanded++;

            if (current.id == end.id) {
                return reconstructPath(current, expanded);
            }

            for (Edge edge : current.neighbors) {
                Node neighbor = edge.target;
                if (!visited.contains(neighbor.id)) {
                    visited.add(neighbor.id);
                    neighbor.parent = current;
                    neighbor.g = current.g + edge.weight;
                    queue.add(neighbor);
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