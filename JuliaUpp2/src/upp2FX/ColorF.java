/*
 * Java
 */
package upp2FX;

/**
 *
 * @author Julia
 */
public class ColorF {
    private int id;
    private int demoId;
    private int färgId;  
    private String name;
    public ColorF(int id, int demoId, int färgId, String name){
        this.id=id;
        this.demoId=demoId;
        this.färgId=färgId;
        this.name=name;
    }
    public int getId() {
        return id;
    }
    public int getDemoId() {
        return demoId;
    }
    public int getFärgId() {
        return färgId;
    }
    public String getName() {
        return färgId + "." + name;
    }
    @Override
    public String toString(){
        return name;
    }
    public void print(){
        System.out.println("id: " + id + "\tdemoId: " + demoId + "\tcolor: " + name);
    }
}
