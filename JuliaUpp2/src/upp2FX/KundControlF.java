/*
 * Java
 */
package upp2FX;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Julia
 */
public class KundControlF {
    List<KundF> kunder = new ArrayList<>();
    private int id;
    private String name;
    public KundControlF(List<KundF> kunder){
        this.kunder=kunder;
    }
    public boolean checkFörNamn(String förnamn){
        return kunder.stream().anyMatch(c -> c.getFörnamn().equalsIgnoreCase(förnamn.trim()));
    }
    public int getKundId(String förnamn){
        for(KundF k: kunder){
            if(k.getFörnamn().equalsIgnoreCase(förnamn.trim())){
                id=k.getId();
            }
        }
        return id;
    }
    public String getKundName(String förnamn){
        for(KundF k: kunder){
            if(k.getFörnamn().equalsIgnoreCase(förnamn.trim())){
                name=k.getFörnamn();
            }
        }
        return name;
    }    
}
