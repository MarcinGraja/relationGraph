package Graph;

import java.io.Serializable;

public class GraphEdge implements Printable, Serializable {
    private GraphNode source;    // Node from which connection was found
    private GraphNode target;    // Node to which connection refers
    private int weight;          // Number of connections found

    //Constructor
    public GraphEdge(GraphNode source, GraphNode target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    @Override
    public void print() {
        System.out.println(source.getName() + " --" + weight + "-> " + target.getName());
    }

    @Override
    public String toString() {
        return Integer.toString(weight);
    }

    public GraphNode getSource() {
        return source;
    }

    public GraphNode getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int a){
        weight=a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphEdge graphEdge = (GraphEdge) o;
        return getWeight() == graphEdge.getWeight() &&
                getSource().equals(graphEdge.getSource()) &&
                getTarget().equals(graphEdge.getTarget());
    }
}
