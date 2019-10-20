package Graph;

public class NodeFile  {
    private String name; // Name of the file combined with it's type ex. "class File"

    //Constructor
    public NodeFile(String name) {
        this.name = name;
    }

    public void print() {
        System.out.println(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
