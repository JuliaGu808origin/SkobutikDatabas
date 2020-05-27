/*
 * Java
 */
package upp2FX;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Julia
 */
public class ProduktRepo {
    private Properties p = new Properties();
    private List<ProduktF> produkter = new ArrayList<>();
    public ProduktRepo(){
        try{
            p.load(new FileInputStream("src/upp2fx/setting.properties"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }  
    public List<ProduktF> showAllProdukt(){
        String query = "select produkt.id, produkt.demoID, produktDemo.produktName, "
                + "produkt.pris,  produkt.total from produkt "
                + "inner join produktDemo on produkt.demoID=produktDemo.ID";
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
                                                  p.getProperty("name"), 
                                                  p.getProperty("password"));
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
                ){
            while(rs.next()){
                produkter.add(new ProduktF(rs.getInt("id"), rs.getInt("demoID"), 
                    rs.getString("produktName"), rs.getDouble("pris"), rs.getInt("total")));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return produkter;
    }
}
