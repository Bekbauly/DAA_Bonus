package mst;

import java.util.*;

public class MSTReplacer {
    private Graph graph;
    private List<Edge> originalMST;

    public MSTReplacer(Graph graph) {
        this.graph = graph;
        this.originalMST = graph.buildMST();
    }
    public List<Edge> getOriginalMST() {
        return originalMST;
    }
    public ReplacementResult replaceEdge(Edge edgeToRemove) {
        // Create MST without the removed edge
        List<Edge> mstWithoutEdge = new ArrayList<>();
        for (Edge edge : originalMST) {
            if (!edge.equals(edgeToRemove)) {
                mstWithoutEdge.add(edge);
            }
        }
        // Find the two components after edge removal
        Set<Integer> component1 = graph.findComponent(edgeToRemove.getSource(), mstWithoutEdge);
        Set<Integer> component2 = new HashSet<>();

        for (int i = 0; i < graph.getVertices(); i++) {
            if (!component1.contains(i)) {
                component2.add(i);
            }
        }
        // Find replacement edge - the minimum weight edge connecting the two components
        Edge replacementEdge = findReplacementEdge(component1, component2);

        // Build new MST
        List<Edge> newMST = new ArrayList<>(mstWithoutEdge);
        if (replacementEdge != null) {
            newMST.add(replacementEdge);
        }
        return new ReplacementResult(edgeToRemove, mstWithoutEdge, component1, component2,
                replacementEdge, newMST);
    }
    private Edge findReplacementEdge(Set<Integer> component1, Set<Integer> component2) {
        Edge minEdge = null;
        int minWeight = Integer.MAX_VALUE;

        // Check all edges in the original graph
        for (Edge edge : graph.getEdges()) {
            int source = edge.getSource();
            int dest = edge.getDestination();

            // Check if edge connects the two components
            boolean connectsComponents =
                    (component1.contains(source) && component2.contains(dest)) ||
                            (component2.contains(source) && component1.contains(dest));
            if (connectsComponents && edge.getWeight() < minWeight) {
                minWeight = edge.getWeight();
                minEdge = edge;
            }
        }

        return minEdge;
    }
    public static class ReplacementResult {
        private Edge removedEdge;
        private List<Edge> mstAfterRemoval;
        private Set<Integer> component1;
        private Set<Integer> component2;
        private Edge replacementEdge;
        private List<Edge> newMST;

        public ReplacementResult(Edge removedEdge, List<Edge> mstAfterRemoval,
                                 Set<Integer> component1, Set<Integer> component2,
                                 Edge replacementEdge, List<Edge> newMST) {
            this.removedEdge = removedEdge;
            this.mstAfterRemoval = mstAfterRemoval;
            this.component1 = component1;
            this.component2 = component2;
            this.replacementEdge = replacementEdge;
            this.newMST = newMST;
        }
        public void display() {
            System.out.println("EDGE REMOVAL AND REPLACEMENT");

            System.out.println("\nRemoved Edge: " + removedEdge);

            System.out.println("Components after removal:");
            System.out.println("Component 1: " + formatComponent(component1));
            System.out.println("Component 2: " + formatComponent(component2));

            if (replacementEdge != null) {
                System.out.println("Replacement Edge Found: " + replacementEdge);
            } else {
                System.out.println("No replacement edge available!");
            }

            Graph.displayEdges(newMST, "\nNEW MINIMUM SPANNING TREE:");
        }

        private String formatComponent(Set<Integer> component) {
            List<Integer> sorted = new ArrayList<>(component);
            Collections.sort(sorted);
            return sorted.toString();
        }
        public Edge getReplacementEdge() {
            return replacementEdge;
        }

        public List<Edge> getNewMST() {
            return newMST;
        }
    }
}