/*
 * Java
 */
package upp2FX;

/**
 *
 * @author Julia
 */
public class OrtF {
    private int id;
    private String name;
    public OrtF(int id, String name){
        this.id=id;
        this.name=name;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void print(){
        System.out.println("id: " + id + "\tort: " + name);
    }
    @Override
    public String toString(){
        return name;
    }
}
