package Graph;

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

}
