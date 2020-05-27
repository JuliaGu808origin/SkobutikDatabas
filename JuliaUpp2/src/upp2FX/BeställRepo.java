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
public class BeställRepo {
    private Properties p = new Properties();
    private List<BeställningF> beställningar = new ArrayList<>();
    public BeställRepo(){
        try{
            p.load(new FileInputStream("src/upp2fx/setting.properties"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }  
    public List<BeställningF> showAllBeställ(){
        String query = "select beställning.ID, beställning.kundID, "
                + "vara.produktID, produktDemo.produktName, beställning.ortID, beställning.datum, "
                + "beställning.skickad from beställning inner join vara "
                + "on beställning.ID=vara.beställningID inner join produktDemo "
                + "on produktDemo.ID=vara.produktID "
                + "order by beställning.ID";  //方便后续的去重复
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
                                                  p.getProperty("name"), 
                                                  p.getProperty("password"));
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
                ){
            while(rs.next()){
                beställningar.add(new BeställningF(rs.getInt("ID"), 
                            rs.getInt("kundID"), rs.getInt("produktID"), rs.getString("produktName"), 
                            rs.getInt("ortID"), rs.getDate("datum"), 
                            rs.getBoolean("skickad")));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return beställningar;
    }
}
