/*
 * Java
 */

package upp2FX;

import java.util.List;
import javax.swing.*;


public class Upp2DemoF {
    public Upp2DemoF(){
       KundRepo r = new KundRepo();
       KundControlF kf = new KundControlF(r.showAllKund());
       String s = JOptionPane.showInputDialog("Your förnamn ?");
       if(s == null || s.trim().equals("")){
           System.exit(0);
       }
       else if(kf.checkFörNamn(s)){
           String[] args = new String[2];
           args[0] = kf.getKundName(s);
           args[1] = kf.getKundId(s)+"";
           BeställFX.main(args);
       }
       else JOptionPane.showMessageDialog(null, "No this person !");
    }  
    public static void main(String[] args) {
        Upp2DemoF körVi = new Upp2DemoF();
    }   
}
