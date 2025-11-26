package graphs;

import java.util.*;

public class DFS implements SearchAlgorithm {
    private Graph graph;

    public DFS(Graph graph) {
        this.graph = graph;
    }

    @Override
    public SearchResult search(int startId, int endId) {
        for (Node n : graph.getAllNodes()) n.reset();

        Stack<Node> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        
        Node start = graph.getNode(startId);
        
        stack.push(start);
        start.g = 0;
        
        int expanded = 0;

        while (!stack.isEmpty()) {
            Node current = stack.pop();

            if (!visited.contains(current.id)) {
                visited.add(current.id);
                expanded++;

                if (current.id == endId) {
                    return reconstructPath(current, expanded);
                }

                for (Edge edge : current.neighbors) {
                    Node neighbor = edge.target;
                    if (!visited.contains(neighbor.id)) {
                        neighbor.parent = current;
                        neighbor.g = current.g + edge.weight;
                        stack.push(neighbor);
                    }
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