/*
 * Java
 */
package upp2FX;

/**
 *
 * @author Julia
 */
public class KundF {
    private int id;
    private String förnamn;
    private String efternamn;
    private String lösenord;
    public KundF(int id, String förnamn, String efternamn, String lösenord){
        this.id=id;
        this.förnamn=förnamn;
        this.efternamn=efternamn;
        this.lösenord=lösenord;
    }
    public int getId() {
        return id;
    }
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
