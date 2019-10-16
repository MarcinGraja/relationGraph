package Graph;

public class NodeFile implements Printable {
    private String name; // Name of the file combined with it's type ex. "class File"

    public NodeFile(String name) {
        this.name = name;
    }

    @Override
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
