/*
 * Java
 */
package Upp2Swing;

/**
 *
 * @author Julia
 */
public class KundS {
    private int id;
    private String förnamn;
    private String efternamn;
    private String lösenord;
    
    public KundS(int id, String förnamn, String efternamn, String lösenord){
        this.id=id;
        this.förnamn=förnamn;
        this.efternamn=efternamn;
        this.lösenord=lösenord;
    }
    public KundS(){}

    public String getFörnamn() {
        return förnamn;
    }

    public void setFörnamn(String förnamn) {
        this.förnamn = förnamn;
    }

    public String getEfternamn() {
        return efternamn;
    }

    public void setEfternamn(String efternamn) {
        this.efternamn = efternamn;
    }

    public String getLösenord() {
        return lösenord;
    }

    public void setLösenord(String lösenord) {
        this.lösenord = lösenord;
    }
    
    public void print(){
        System.out.println("namn: " + förnamn + " " + efternamn + " \t" + "Lösenord: " + lösenord);
    }
    
}
