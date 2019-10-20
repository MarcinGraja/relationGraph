package Graph;

public class Dependency {
    private NodeFile source;    // File from which dependency was found
    private NodeFile target;    // File to which dependency refers
    private int amount;         // Number of dependencies found

    //Constructor
    public Dependency(NodeFile source, NodeFile target, int amount) {
        this.source = source;
        this.target = target;
        this.amount = amount;
    }

    public void print() {
        System.out.println(source.getName() + " --" + amount + "-> " + target.getName());
    }

    public NodeFile getSource() {
        return source;
    }

    public void setSource(NodeFile source) {
        this.source = source;
    }

    public NodeFile getTarget() {
        return target;
    }

    public void setTarget(NodeFile target) {
        this.target = target;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
