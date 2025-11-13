package mst;

import java.util.*;

public class Graph {
    private int vertices;
    private List<Edge> edges;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
    }
    public void addEdge(int source, int destination, int weight) {
        edges.add(new Edge(source, destination, weight));
    }
    public int getVertices() {
        return vertices;
    }
    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }
    public List<Edge> buildMST() {
        List<Edge> mst = new ArrayList<>();
        Collections.sort(edges);

        UnionFind uf = new UnionFind(vertices);

        for (Edge edge : edges) {
            int source = edge.getSource();
            int dest = edge.getDestination();

            // If including this edge doesn't form a cycle
            if (!uf.connected(source, dest)) {
                mst.add(edge);
                uf.union(source, dest);

                if (mst.size() == vertices - 1) {
                    break;
                }
            }
        }

        return mst;
    }
    public Set<Integer> findComponent(int start, List<Edge> edgeList) {
        Set<Integer> component = new HashSet<>();
        Set<Integer> visited = new HashSet<>();

        dfs(start, edgeList, visited, component);

        return component;
    }
    private void dfs(int vertex, List<Edge> edgeList, Set<Integer> visited, Set<Integer> component) {
        visited.add(vertex);
        component.add(vertex);
        for (Edge edge : edgeList) {
            if (edge.connectsVertex(vertex)) {
                int other = edge.getOtherVertex(vertex);
                if (!visited.contains(other)) {
                    dfs(other, edgeList, visited, component);
                }
            }
        }
    }
    public static int calculateTotalWeight(List<Edge> edges) {
        return edges.stream().mapToInt(Edge::getWeight).sum();
    }
    public static void displayEdges(List<Edge> edges, String title) {
        System.out.println(title);

        if (edges.isEmpty()) {
            System.out.println("No edges to display.");
            return;
        }
        for (Edge edge : edges) {
            System.out.println(edge);
        }
        int totalWeight = calculateTotalWeight(edges);
        System.out.println("Total Weight: " + totalWeight);
        System.out.println("Number of Edges: " + edges.size());
    }
}