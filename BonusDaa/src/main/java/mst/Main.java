package mst;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a sample graph
        Graph graph = createSampleGraph();

        // Build and display original
        MSTReplacer replacer = new MSTReplacer(graph);
        List<Edge> originalMST = replacer.getOriginalMST();

        Graph.displayEdges(originalMST, "\nORIGINAL MINIMUM SPANNING TREE:");

        if (!originalMST.isEmpty()) {
            Edge edgeToRemove = originalMST.get(2); // Remove third edge for interesting results

            MSTReplacer.ReplacementResult result = replacer.replaceEdge(edgeToRemove);

            result.display();

            verifyResult(originalMST, result);
        } else {
            System.out.println("\nError: Could not build MST!");
        }

        System.out.println("Program completed successfully!");
    }

    private static Graph createSampleGraph() {
        Graph graph = new Graph(6); // 6 vertices (0-5)

        // Add edges with weights
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 3, 8);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 3, 11);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 4, 2);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 14);
        graph.addEdge(4, 5, 10);

        System.out.println("GRAPH STRUCTURE:");
        System.out.println("Vertices: 6 (labeled 0-5)");
        System.out.println("Edges: 9");
        System.out.println("\nEdge List:");
        for (Edge edge : graph.getEdges()) {
            System.out.println("  " + edge);
        }

        return graph;
    }

    private static void verifyResult(List<Edge> originalMST,
                                     MSTReplacer.ReplacementResult result) {
        System.out.println("\nVERIFICATION:");

        int originalWeight = Graph.calculateTotalWeight(originalMST);
        int newWeight = Graph.calculateTotalWeight(result.getNewMST());

        System.out.println("Original MST weight: " + originalWeight);
        System.out.println("New MST weight: " + newWeight);
        System.out.println("Weight difference: " + (newWeight - originalWeight));

        if (result.getNewMST().size() == originalMST.size()) {
            System.out.println("✓ Tree structure maintained (correct number of edges)");
        } else {
            System.out.println("✗ Tree structure broken!");
        }

        if (result.getReplacementEdge() != null) {
            System.out.println("✓ Replacement edge found successfully");
        } else {
            System.out.println("✗ No replacement edge available");
        }
    }
}