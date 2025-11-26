package graphs;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        // Validação estrita
        if (args.length != 3) {
            System.out.println("Uso: java graphs.Main <arquivo_matriz> <origem> <destino>");
            System.exit(1);
        }

        try {
            Path inputPath = Paths.get(args[0]);
            int sourceId = Integer.parseInt(args[1]);
            int targetId = Integer.parseInt(args[2]);

            System.out.println("=== Processando: " + inputPath.getFileName() + " ===");

            Graph graph = new Graph(inputPath.toString());

            if (graph.getNode(sourceId) == null || graph.getNode(targetId) == null) {
                System.err.println("Erro: Nó de origem ou destino inválido.");
                System.exit(1);
            }

            // --- Execução dos 7 Cenários Exigidos ---

            // 1. BFS
            runAndLog(new BFS(graph), sourceId, targetId, inputPath, ".bfs", "BFS", "");

            // 2. DFS
            runAndLog(new DFS(graph), sourceId, targetId, inputPath, ".dfs", "DFS", "");

            // 3. Dijkstra
            runAndLog(new Dijkstra(graph), sourceId, targetId, inputPath, ".dijkstra", "Dijkstra", "");

            // 4. A* (Manhattan)
            runAndLog(new AStar(graph, new ManhattanHeuristic()), sourceId, targetId, inputPath, ".a.manhattan", "A*", "Manhattan");

            // 5. A* (Euclidiana)
            runAndLog(new AStar(graph, new EuclideanHeuristic()), sourceId, targetId, inputPath, ".a.euclidiana", "A*", "Euclidiana");

            // 6. Greedy (Manhattan)
            runAndLog(new GreedyBestFirstSearch(graph, new ManhattanHeuristic()), sourceId, targetId, inputPath, ".gbs.manhattan", "Greedy Best-First-Search", "Manhattan");

            // 7. Greedy (Euclidiana)
            runAndLog(new GreedyBestFirstSearch(graph, new EuclideanHeuristic()), sourceId, targetId, inputPath, ".gbs.euclidiana", "Greedy Best-First-Search", "Euclidiana");

            System.out.println("=== Concluído! Verifique a pasta 'resultado dos testes' ===");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runAndLog(SearchAlgorithm algorithm, int start, int end, Path path, String ext, String algoName, String heuristicName) {
        System.out.print("Executando " + algoName + " (" + (heuristicName.isEmpty() ? "Sem Heurística" : heuristicName) + ")... ");
        
        // Medição de Tempo (Milissegundos com precisão decimal)
        long startTime = System.nanoTime();
        SearchResult result = algorithm.search(start, end);
        long endTime = System.nanoTime();
        
        double durationMs = (endTime - startTime) / 1_000_000.0;

        // Salva no formato específico do PDF
        OutputGenerator.saveFile(path, ext, result, algoName, heuristicName, start, end, durationMs);
        
        System.out.println("OK (" + String.format("%.2f", durationMs) + "ms)");
    }
}