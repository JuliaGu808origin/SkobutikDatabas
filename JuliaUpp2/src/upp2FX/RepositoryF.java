/*
 * Java
 */
package upp2FX;

import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Julia
 */
public class RepositoryF {
    private Properties p = new Properties();
    private List<KundF> kunder = new ArrayList<>();
    private List<BeställningF> beställningar = new ArrayList<>();
    private List<ProduktF> produkter = new ArrayList<>();
    private List<ColorF> colors = new ArrayList<>();
    private List<SizeF> sizes = new ArrayList<>();
    private List<OrtF> orter = new ArrayList<>();
    public RepositoryF(){
        try{
            p.load(new FileInputStream("src/upp2fx/setting.properties"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<ColorF> showAllColor(){
        String query = "select produktfärg.id, produktfärg.demoID, färg.id as färgID, färg.namn from färg "
        + "inner join produktfärg on färg.id = produktfärg.färgID order by produktfärg.id";
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
                                          p.getProperty("name"), 
                                          p.getProperty("password"));
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
                ){
            while(rs.next()){
                getColors().add(new ColorF(rs.getInt("id"), rs.getInt("demoID"), 
                        rs.getInt("färgID"), rs.getString("namn")));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return getColors();
    }
    public List<SizeF> showAllSize(){
        String query = "select produktstorlek.id, produktstorlek.demoID, produktstorlek.storlekID, "
                + "storlek.nummer from storlek inner join produktstorlek on storlek.id = produktstorlek.storlekID";
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
                                          p.getProperty("name"), 
                                          p.getProperty("password"));
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
                ){
            while(rs.next()){
                getSizes().add(new SizeF(rs.getInt("id"), rs.getInt("demoID"), 
                        rs.getInt("storlekID"), rs.getDouble("nummer")));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return getSizes();
    }
    public List<OrtF> showAllOrt(){
        String query = "select * from ort";
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
                                                          p.getProperty("name"), 
                                                          p.getProperty("password"));
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
                ){
            while(rs.next()){
                getOrter().add(new OrtF(rs.getInt("id"), rs.getString("namn")));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return getOrter();
    }
    public void callAddToCart(int kundId, int beställningId, int produktId){
        ResultSet rs = null;
        String errorMsg = "";
        try(
            Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
                                                          p.getProperty("name"), 
                                                          p.getProperty("password"));
            CallableStatement stm = con.prepareCall("call AddToCart(?,?,?)");                    
                ){
            stm.setString(1, kundId+"");
            stm.setString(2, beställningId+"");
            stm.setString(3, produktId+"");
            rs = stm.executeQuery();
            while(rs.next()){
                errorMsg=rs.getString("error");
            }
            if(!errorMsg.equals("")) System.out.println(errorMsg);
            System.out.println("1 Check your database from beställning");
        }
        catch(Exception e){
            e.printStackTrace();
        }        
    }
    public void callAddToCart2(int kundId, int beställningId, int produktId, 
            int ortId, int colorId, int sizeId){
        ResultSet rs = null;
        String errorMsg = "";
        try(
            Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
                                                          p.getProperty("name"), 
                                                          p.getProperty("password"));
            CallableStatement stm = con.prepareCall("call AddToCart2(?,?,?,?,?,?)");                    
                ){
            stm.setString(1, kundId+"");
            stm.setString(2, beställningId+"");
            stm.setString(3, produktId+"");
            stm.setString(4, ortId+"");
            stm.setString(5, colorId+"");
            stm.setString(6, sizeId+"");
            rs = stm.executeQuery();
            while(rs.next()){
                errorMsg=rs.getString("error");
            }
            if(!errorMsg.equals("")) System.out.println(errorMsg);
            System.out.println("2 Check your database from beställning");
        }
        catch(Exception e){
            e.printStackTrace();
        }        
    }
    public List<KundF> getKunder() {
        return kunder;
    }
    public List<BeställningF> getBeställningar() {
        return beställningar;
    }
    public List<ProduktF> getProdukter() {
        return produkter;
    }
    public List<ColorF> getColors() {
        return colors;
    }
    public List<SizeF> getSizes() {
        return sizes;
    }
    public List<OrtF> getOrter() {
        return orter;
    }
}
