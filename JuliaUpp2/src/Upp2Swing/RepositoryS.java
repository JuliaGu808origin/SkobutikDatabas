/*
 * Java
 */
package Upp2Swing;

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
public class RepositoryS {
//    private Connection con;
    private Properties p = new Properties();
    public RepositoryS(){
        try{
            p.load(new FileInputStream("src/upp2swing/setting.properties"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<KundS> showAllKund(){
        List<KundS> kunds = new ArrayList<>();
//        ResultSet rs = null;
        String query = "select * from kund";
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
                                                          p.getProperty("name"), 
                                                          p.getProperty("password"));
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
                ){
            while(rs.next()){
                kunds.add(new KundS(rs.getInt("id"), rs.getString("förnamn"), 
                        rs.getString("efternamn"), rs.getString("personNummer")));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return kunds;
    }
    public int kundId(String förnamn){
        int id=0;
        String query = "select id from kund where förnamn = ?";
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
                                                          p.getProperty("name"), 
                                                          p.getProperty("password"));
            PreparedStatement stmt = con.prepareStatement(query);
                ){
            stmt.setString(1, förnamn);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return id;
    }
    public int produktId(int demoId){
        int id=0;
        String query = "select id from produkt where demoID = ?";
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
                                                          p.getProperty("name"), 
                                                          p.getProperty("password"));
            PreparedStatement stmt = con.prepareStatement(query);
                ){
            stmt.setString(1, demoId+"");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }        
        return id;
    }
    public List<ProduktS> showAllProdukt(String förnamn){
        int hasKund = kundId(förnamn);
        List<ProduktS> produkts = new ArrayList<>();
        String query = "select produkt.id, produkt.demoID, produktDemo.produktName, "
                + "produkt.pris,  produkt.total from produkt "
                + "inner join produktDemo on produkt.demoID=produktDemo.ID";
        if(hasKund != 0){
            try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
                                                          p.getProperty("name"), 
                                                          p.getProperty("password"));
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                    ){
                while(rs.next()){
                    produkts.add(new ProduktS(rs.getInt("id"), rs.getInt("demoID"), 
                            rs.getString("produktName"), rs.getDouble("pris"), rs.getInt("total")));
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            produkts.add(new ProduktS(0,0,"null",0,0));
        }
        return produkts;
    }
    
    public List<BeställningS> showValidBeställ(String förnamn){
        List<BeställningS> beställnings = new ArrayList<>();
        int kundId = kundId(förnamn);
//        int produktId = produktId(demoId);
        String query = "select beställning.ID, beställning.kundID, "
                + "vara.produktID, beställning.ortID, beställning.datum, "
                + "beställning.skickad from beställning inner join vara "
                + "on beställning.ID=vara.beställningID "
                + "where beställning.skickad=false and beställning.kundID=? "
                + "group by beställning.ID";
        try(Connection con = DriverManager.getConnection(p.getProperty("connectionString"), 
                                                          p.getProperty("name"), 
                                                          p.getProperty("password"));
            PreparedStatement stmt = con.prepareStatement(query);
                ){
            stmt.setString(1, kundId+"");
//            stmt.setString(2, produktId+"");
            ResultSet rs = stmt.executeQuery();
            if(rs==null) beställnings.add(new BeställningS(0,0,0,0,null,false));
            else{
                while(rs.next()){
                    beställnings.add(new BeställningS(rs.getInt("ID"), 
                            rs.getInt("kundID"), rs.getInt("produktID"), 
                            rs.getInt("ortID"), rs.getDate("datum"), 
                            rs.getBoolean("skickad")));
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }          
        return beställnings;
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
            System.out.println("Check your database from beställning");
        }
        catch(Exception e){
            e.printStackTrace();
        }        
    }
}
