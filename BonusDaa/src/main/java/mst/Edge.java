package mst;

public class Edge implements Comparable<Edge> {
    private int source;
    private int destination;
    private int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    public int getSource() {
        return source;
    }
    public int getDestination() {
        return destination;
    }
    public int getWeight() {
        return weight;
    }
    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge edge = (Edge) obj;
        return (source == edge.source && destination == edge.destination) ||
                (source == edge.destination && destination == edge.source);
    }
    @Override
    public int hashCode() {
        return source + destination + weight;
    }
    @Override
    public String toString() {
        return source + " -- " + destination + " (weight: " + weight + ")";
    }
    public boolean connectsVertex(int vertex) {
        return source == vertex || destination == vertex;
    }
    public int getOtherVertex(int vertex) {
        if (source == vertex) return destination;
        if (destination == vertex) return source;
        throw new IllegalArgumentException("Vertex " + vertex + " is not part of this edge");
    }
}