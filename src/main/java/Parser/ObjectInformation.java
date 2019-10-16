package Parser;

public class ObjectInformation {
    String className;
    String objectName;
    int uses = 0;

    public ObjectInformation(String className, String objectName) {
        this.className = className;
        this.objectName = objectName;
    }

    public String getClassName() {
        return className;
    }

    public String getObjectName() {
        return objectName;
    }

    public int getUses() {
        return uses;
    }
    public String toString(){
        return "class: " + getClassName() + " object: " + getObjectName() + " uses: " + getUses();
    }
    public void incrementUses() {
        this.uses++;
    }
}
