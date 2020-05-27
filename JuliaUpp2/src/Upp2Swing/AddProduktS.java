/*
 * Java
 */
package Upp2Swing;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Julia
 */
public class AddProduktS extends JFrame{
    JFrame f = new JFrame();
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    RepositoryS r = new RepositoryS();
    public AddProduktS(String name){
        String validBeställ="";
        List<BeställningS> beställer = r.showValidBeställ(name);
           for(BeställningS b: beställer){
               validBeställ = validBeställ + b.print();
           }
        JLabel demo = new JLabel("produktdemoId: ");
        JButton add = new JButton("add produkt");
        JLabel info = new JLabel(validBeställ);
        JTextField demoWrite = new JTextField();
        JLabel beställ = new JLabel("beställningId: ");
        JTextField beställWrite = new JTextField();
        p1.setLayout(new GridLayout(2,2));
        p2.setLayout(new BorderLayout());
        p2.add("North", info);
        p2.add("Center", p1);
        p2.add("South", add);
        p1.add(demo);
        p1.add(demoWrite);
        p1.add(beställ);
        p1.add(beställWrite);
        javax.swing.Timer tim = new javax.swing.Timer(3000, add.getAction());//每隔1秒的动作
        add.addActionListener(addP ->{
            int kundId = r.kundId(name);
            int beställId = Integer.parseInt(beställWrite.getText());            
            int demoId = Integer.parseInt(demoWrite.getText());
            r.callAddToCart(kundId, beställId, demoId);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AddProduktS.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
        });
        f.add(p2);
        f.setTitle("Personlig Page");
        f.setSize(1000, 500);
        f.setLocation(800, 500);
        
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
