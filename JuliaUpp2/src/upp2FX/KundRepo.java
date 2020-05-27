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
public class KundRepo {
    private Properties p = new Properties();
    private List<KundF> kunder = new ArrayList<>();
    public KundRepo(){
        try{
            p.load(new FileInputStream("src/upp2fx/setting.properties"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<KundF> showAllKund(){
        String query = "select * from kund";
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
                                                          p.getProperty("name"), 
                                                          p.getProperty("password"));
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
                ){
            while(rs.next()){
                kunder.add(new KundF(rs.getInt("id"), rs.getString("förnamn"), 
                        rs.getString("efternamn"), rs.getString("personNummer")));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return kunder;
    }
}
