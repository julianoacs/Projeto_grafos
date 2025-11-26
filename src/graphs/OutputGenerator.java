package graphs;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Locale;

public class OutputGenerator {

    public static void saveFile(Path inputPath, String extension, SearchResult result, 
                                String algoName, String heuristicName, int start, int end, double timeMs) {
        
        String outputFolderName = "resultado dos testes";
        File outputDir = new File(outputFolderName);
        if (!outputDir.exists()) outputDir.mkdirs();

        // Nome do arquivo: "teste_3x3.txt.a.manhattan" (preserva extensão original + nova extensão) [cite: 49]
        String fileName = inputPath.getFileName().toString();
        String fullPath = outputDir.getAbsolutePath() + File.separator + fileName + extension;

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(fullPath), StandardCharsets.UTF_8))) {
            
            // Formatação RIGOROSA conforme PDF (Página 3)
            writer.write("ALGORITIMO: " + algoName + "\n");
            writer.write("HEURISTICA: " + heuristicName + "\n"); // Deixa em branco se vazio
            writer.write("ORIGEM: " + start + "\n");
            writer.write("DESTINO: " + end + "\n");
            
            if (result.found) {
                writer.write("CAMINHO: " + result.pathStr + "\n");
                // Custo formatado como inteiro se for inteiro, ou decimal se precisar
                if (result.totalCost % 1 == 0) {
                    writer.write("CUSTO: " + (int)result.totalCost + "\n");
                } else {
                    writer.write(String.format(Locale.US, "CUSTO: %.1f%n", result.totalCost));
                }
            } else {
                // PDF: "Caso um caminho válido não tenha sido encontrado, deixar em branco" [cite: 66]
                writer.write("CAMINHO: \n");
                writer.write("CUSTO: \n");
            }

            writer.write("NOS EXPANDIDOS: " + result.expandedNodes + "\n");
            writer.write(String.format(Locale.US, "TEMPO (ms): %.2f%n", timeMs));
            
        } catch (IOException e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
        }
    }
}