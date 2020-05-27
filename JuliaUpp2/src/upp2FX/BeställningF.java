/*
 * Java
 */
package upp2FX;

import java.sql.Date;

/**
 *
 * @author Julia
 */
class BeställningF {
    private int id;
    private int kundId;
    private int produktId;
    private String name;
    private int ortId;
    private Date datum;
    private boolean skickad;
    public BeställningF(int id, int kundId, int produktId, String name, int ordId, Date datum, boolean skickad){
        this.id=id;
        this.kundId=kundId;
        this.produktId=produktId;
        this.name=name;
        this.ortId=ortId;
        this.datum=datum;
        this.skickad=skickad;
    }
    public int getKundId() {
        return kundId;
    }
    public void setKundId(int kundId) {
        this.kundId = kundId;
    }
    public int getOrtId() {
        return ortId;
    }
    public void setOrtId(int ortId) {
        this.ortId = ortId;
    }
    public boolean isSkickad() {
        return skickad;
    }
    public void setSkickad(boolean skickad) {
        this.skickad = skickad;
    }   
    public void print(){
        System.out.println("beställningId: "+getId()+ "\tkundId: " + kundId + 
                " \tproduktId: " + produktId + "\tortId: " + ortId + 
                " \tdatum: "+datum+" \tskickad: "+skickad + " \n");
    }
    public String getInfo(){
        return name + " \tdatum: "+datum+" \tskickad: "+skickad + " \n";
    }
    @Override
    public String toString(){
        return "datum: " + datum + " \t en beställning " + " \tskickad: "+skickad + " \n";
    }
    public int getId() {
        return id;
    }   
}
