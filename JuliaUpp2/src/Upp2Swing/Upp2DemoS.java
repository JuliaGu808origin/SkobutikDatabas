/*
 * Java
 */

package Upp2Swing;

import java.util.List;
import javax.swing.*;


public class Upp2DemoS {

    
    public static void main(String[] args) {
       RepositoryS r = new RepositoryS();
       List<KundS> kunder = r.showAllKund();
       String s = JOptionPane.showInputDialog("Your förnamn ?");
       if(kunder != null){
           String name="";
//           String validBeställ="";
           for(KundS k : kunder){
               if(k.getFörnamn().equalsIgnoreCase(s)){
                   name=k.getFörnamn();
                   r.showAllProdukt(name).forEach((c -> c.print()));
               }
           }

           AddProduktS ap = new AddProduktS(name);
           
       }

    }

}
