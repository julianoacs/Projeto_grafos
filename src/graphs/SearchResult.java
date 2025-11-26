package graphs;

public class SearchResult {
    public boolean found;
    public String pathStr;
    public double totalCost;
    public int expandedNodes;

    public SearchResult(boolean found, String pathStr, double totalCost, int expandedNodes) {
        this.found = found;
        this.pathStr = pathStr;
        this.totalCost = totalCost;
        this.expandedNodes = expandedNodes;
    }
}