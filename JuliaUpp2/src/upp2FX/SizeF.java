/*
 * Java
 */
package upp2FX;

/**
 *
 * @author Julia
 */
public class SizeF {
    private int id;
    private int demoId;
    private int storlekId;  
    private double size;
    public SizeF(int id, int demoId, int storlekId, double size){
        this.id=id;
        this.demoId=demoId;
        this.storlekId=storlekId;
        this.size=size;
    }    
    public int getId() {
        return id;
    }
    public int getDemoId() {
        return demoId;
    }
    public int getStorlekId() {
        return storlekId;
    }
    public double getSize() {
        return size;
    }
    @Override
    public String toString(){
        return size+"";
    }
    public void print(){
        System.out.println("id: " + id + "\tdemoId: " + demoId + "\tsize: " + size);
    }
}
