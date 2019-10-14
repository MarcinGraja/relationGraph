public class RelationGraphMain {
    public static void main(String[] args) {
        NodeFile f1 = new NodeFile("file1");
        NodeFile f2 = new NodeFile("file2");
        Dependency d1 = new Dependency(f1, f2, 5);
        d1.print();
    }
}