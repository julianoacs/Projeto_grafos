# Projeto de Busca de Rotas em Grafos 

Sistema de análise de algoritmos de busca em grafos desenvolvido em Java, focado na comparação de desempenho entre estratégias informadas e não-informadas em grades bidimensionais.

## 📋 Descrição

Este projeto é uma aplicação de console que processa grafos representados por matrizes de adjacência. Ele constrói a estrutura do grafo em memória, calcula coordenadas automaticamente para aplicação de heurísticas e executa uma bateria de 7 testes de busca para encontrar a melhor rota entre dois pontos, gerando relatórios detalhados de métricas.

## ✨ Funcionalidades

* **📂 Leitura de Matrizes:** Carrega grafos a partir de arquivos de texto (formato Matriz de Adjacência).
* **🧩 Mapeamento Automático:** Converte índices lineares da matriz em coordenadas `(x, y)` para cálculos geométricos.
* **🚀 Execução em Lote:** Roda sequencialmente 7 variações de algoritmos para o mesmo cenário.
* **⏱️ Análise de Desempenho:** Mede tempo de execução, custo total da rota e número de nós expandidos.
* **📄 Relatórios Padronizados:** Gera arquivos de saída individuais e formatados para cada algoritmo executado.

## 🎯 Características Técnicas

### Algoritmos Implementados

* **Busca Não-Informada **
    * **BFS (Breadth-First Search):** Garante o menor caminho em arestas sem peso (por saltos).
    * **DFS (Depth-First Search):** Explora profundidade máxima; útil para varredura, mas não garante otimalidade.
    * **Dijkstra:** Algoritmo clássico para caminhos mínimos em grafos ponderados.

* **Busca Informada (Heurística)**
    * **Greedy Best-First:** Foca apenas na estimativa de proximidade do destino (rápido, mas não ótimo).
    * **A\* (A-Star):** Combina custo real (`g`) + estimativa (`h`). Garante o melhor caminho se a heurística for admissível.

### Heurísticas Matemáticas
Como o grafo representa uma grade, as distâncias são calculadas matematicamente:
* **Manhattan:** Distância em "L" (`|x1-x2| + |y1-y2|`).
* **Euclidiana:** Distância em linha reta (geométrica).

## 📁 Estrutura do Projeto

```text
Projeto Grafos/
├── src/
│   └── graphs/
│       ├── Main.java                # Orquestrador e Cronômetro
│       ├── Graph.java               # Leitor de Matriz e Factory de Nós
│       ├── SearchAlgorithm.java     # Interface comum para buscas
│       ├── OutputGenerator.java     # Gerador de relatórios (Writer)
│       ├── algorithms/              # Implementações (BFS, DFS, A*, etc.)
│       └── heuristics/              # Cálculos (Manhattan, Euclidiana)
├── bin/                             # Classes compiladas
├── matrizes/                        # Arquivos de entrada (.txt)
└── resultado dos testes/            # Arquivos de saída gerados (.bfs, .dfs...)

```
🚀 Como Executar

Opção 1: Via VS Code (Recomendado)

Acesse a aba **Run and Debug** e clique no botão **Play** ▶️

Opção 2: Manualmente (Terminal)

``` Compilar
mkdir -p bin
javac -d bin -sourcepath src src/graphs/Main.java

 Executar
java -cp bin graphs.Main matrizes\matrix_4x4.txt 0 15
```

## 🎓 Objetivo Acadêmico
Este projeto foi desenvolvido para a disciplina de Estruturas de Dados e Análise de Algoritmos, com foco em:

Manipulação de estruturas de grafos (Listas vs Matrizes).

Compreensão prática de complexidade de algoritmos.

Impacto do uso de heurísticas em problemas de busca de caminho (Pathfinding).

