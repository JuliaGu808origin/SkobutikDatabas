/*
 * Java
 */
package upp2FX;

import java.util.List;

/**
 *
 * @author Julia
 */
public class ProduktF {
    private int id;
    private int demoId;
    private String namn;
    private double pris;
    private int total;
    public ProduktF(int id, int demoId, String namn, double pris, int total){
        this.id=id;
        this.demoId=demoId;
        this.namn=namn;
        this.pris=pris;
        this.total=total;
    }
    public int getDemoId() {
        return demoId;
    }
    public void setDemoId(int demoId) {
        this.demoId = demoId;
    }
    public double getPris() {
        return pris;
    }
    public void setPris(double pris) {
        this.pris = pris;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }  
    public void print(){
        System.out.println("demoId: "+demoId+"\tnamn: "+getNamn()+" \tpris: "+pris+"\ttotal: "+total);
    }
    @Override
    public String toString(){
        return namn;
    }
    public String getNamn() {
        return namn;
    }
}
