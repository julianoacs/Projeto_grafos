package graphs;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Graph {
    private Map<Integer, Node> nodes = new HashMap<>();
    private int totalNodes = 0;

    public Graph(String filePath) throws IOException {
        loadFromMatrixFile(filePath);
    }

    private void loadFromMatrixFile(String filePath) throws IOException {
        File file = new File(filePath);
        List<String[]> lines = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                lines.add(line.split("\\s+")); 
            }
        }


        totalNodes = lines.size(); 
        int gridWidth = (int) Math.ceil(Math.sqrt(totalNodes));

        System.out.println("--- DEBUG: Matriz de Adjacência lida com " + totalNodes + " nós (Grade " + gridWidth + "x" + gridWidth + ") ---");


        for (int i = 0; i < totalNodes; i++) {
            double x = i / gridWidth; // Linha
            double y = i % gridWidth; // Coluna
            
            nodes.put(i, new Node(i, x, y));
        }

        for (int i = 0; i < totalNodes; i++) {
            String[] weights = lines.get(i);
            
            for (int j = 0; j < weights.length; j++) {
                if (j >= totalNodes) break; 

                try {
                    double weight = Double.parseDouble(weights[j]);

                    if (weight > 0) {
                        Node source = nodes.get(i);
                        Node target = nodes.get(j);
                        
                        if (source != null && target != null) {
                            source.addEdge(new Edge(target, weight));
                        }
                    }
                } catch (NumberFormatException e) {
                }
            }
        }
        System.out.println("--- DEBUG: Grafo montado com " + totalNodes + " nós. ---");
    }

    public Node getNode(int id) {
        return nodes.get(id);
    }

    public Collection<Node> getAllNodes() {
        return nodes.values();
    }
}