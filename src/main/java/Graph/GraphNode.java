package Graph;

import java.util.Objects;

public class GraphNode implements Printable {
    private String name; // Name of the node

    //Constructor
    public GraphNode(String name) {
        this.name = name;
    }

    @Override
    public void print() {
        System.out.println(name);
    }

    @Override
    public String toString(){
       return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphNode graphNode = (GraphNode) o;
        return getName().equals(graphNode.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
