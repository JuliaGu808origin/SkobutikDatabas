/*
 * Java
 */
package Upp2Swing;

import java.sql.Date;

/**
 *
 * @author Julia
 */
class BeställningS {
    private int id;
    private int kundId;
    private int produktId;
    private int ortId;
    private Date datum;
    private boolean skickad;
    
    public BeställningS(int id, int kundId, int produktId, int ordId, Date datum, boolean skickad){
        this.id=id;
        this.kundId=kundId;
        this.produktId=produktId;
        this.ortId=ortId;
        this.datum=datum;
        this.skickad=skickad;
    }
    public BeställningS(){}

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
    
    public String print(){
        return "beställningId: "+id+" \tdatum: "+datum+" \tskickad: "+skickad + " \n";
    }
    @Override
    public String toString(){
        return "Id: "+id+" \tdatum: "+datum+" \tskickad: "+skickad + " \n";
    }
    
}
