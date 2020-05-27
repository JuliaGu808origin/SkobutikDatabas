/*
 * Java
 */
package upp2FX;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Julia
 */
public class BeställFX extends Application {
    private static String name;
    private static int id;
    private int demoId=0, beställId=0, colorId=0, sizeId=0, ortId=0;
    private List<ColorF> colors = new ArrayList<>();
    private List<SizeF> sizes = new ArrayList<>();
    private List<OrtF> orter = new ArrayList<>();
    private List<BeställningF> beställningar = new ArrayList<>();
    private List<BeställningF> validBeställ = new ArrayList<>();
    VBox beställerBox = new VBox();
    VBox produkterBox = new VBox();
    HBox colorBox = new HBox();
    HBox sizeBox = new HBox();
    HBox ortBox = new HBox();
    Text produktInfo = new Text();
    Text extraInfo = new Text();
    Button btn = new Button("Add to Cart");
    GridPane grid = new GridPane();
    BorderPane border = new BorderPane();
    AddSizeF as = new AddSizeF();
    AddColorF ac = new AddColorF();
    @Override
    public void start(Stage primaryStage) {
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(0,10,0,10));
        RepositoryF r = new RepositoryF();
        colors = r.showAllColor();
        sizes = r.showAllSize();
        orter = r.showAllOrt();
        ProduktRepo pr = new ProduktRepo();
        List<ProduktF> produkter = pr.showAllProdukt();
        produkterBox = addVBoxP(produkter, "choose one produkt");
        Text titC = new Text("choose color");
        titC.setFont(Font.font("Aria", FontWeight.BOLD, 14));
        colorBox.getChildren().add(titC);
        Text titS = new Text("choose size");
        titS.setFont(Font.font("Aria", FontWeight.BOLD, 14));
        sizeBox.getChildren().add(titS);
        BeställRepo br = new BeställRepo();
        beställningar = br.showAllBeställ();
        BeställControlF bc = new BeställControlF();
        validBeställ = bc.showValidBeställ(beställningar,id); // visa false
        beställerBox = addVBoxB(validBeställ, "choose one beställning");
        ortBox = addBoxO("choose one place");
        ortBox.setVisible(false);
        grid.add(produkterBox, 0, 0);
        grid.add(beställerBox, 3, 0);
        grid.add(ortBox, 0, 1, 2, 1);
        grid.add(colorBox, 0, 2, 2, 1);
        grid.add(sizeBox, 0, 3, 2, 1);
        grid.add(produktInfo, 3, 1, 1, 2);
        grid.add(extraInfo, 3, 3, 1,1);
        grid.add(btn, 1, 4, 2, 1);
        StackPane root = new StackPane();
        border.setCenter(grid);
        root.getChildren().add(border);
        Scene scene = new Scene(root, 1000, 500);
        primaryStage.setTitle(name + " beställningar!");
        primaryStage.setScene(scene);
        primaryStage.show();
        btn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {   
                extraInfo.setVisible(true);
                sizeId = as.getSizeId();
                colorId = ac.getColorId();
                if(demoId==0 && beställId==0) 
                    extraInfo.setText("choose one produkt och one beställning");
                if(demoId==0 && beställId != 0)
                    extraInfo.setText("choose one produkt");
                if(demoId != 0 && beställId==0)
                    extraInfo.setText("choose one beställning");
                if(demoId != 0 && beställId != 0){
                    if(ortId==0 && colorId==0 && sizeId==0){
                        r.callAddToCart(id, beställId, demoId);
                        try {
                                Thread.sleep(2000);
                                System.exit(0);
                        } catch (InterruptedException ex) {
                                Logger.getLogger(AddProduktF.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else{
                        if(ortId==0) ortId=2;
                        if(colorId==0) colorId=1;
                        if(sizeId==0) sizeId=5;
                        r.callAddToCart2(id, beställId, demoId, ortId, colorId, sizeId);
                        try {
                                Thread.sleep(2000);
                                System.exit(0);
                        } catch (InterruptedException ex) {
                                Logger.getLogger(AddProduktF.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                }
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        name = args[0];
        id = Integer.parseInt(args[1].trim());
        launch();
    }
    public VBox addVBoxP(List<ProduktF> produkt, String str){
        AddMap ap = new AddMap();
        Map mp = ap.changeMapP(produkt);
        VBox box = new VBox();
        box.setPadding(new Insets(10));
        box.setSpacing(8);
        Text title = new Text(str);
        title.setFont(Font.font("Aria", FontWeight.BOLD, 14));
        box.getChildren().add(title);
        List<RadioButton> rbtn = new ArrayList<>();
        ToggleGroup group = new ToggleGroup();
        for(int i = 0; i<produkt.size(); i++){
            if(produkt.get(i).getTotal() > 0){  
                RadioButton b = new RadioButton(mp.get(produkt.get(i).getDemoId()).toString());
                b.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        extraInfo.setVisible(false);
                        RadioButton rb = (RadioButton) event.getSource(); 
                        String s = rb.getText(); 
                        int num = ap.getId(mp, s);
                        demoId = num;
                        //同一demo不同色号
                        List<ColorF> färg = ac.addCol(colors, num, "choose one color");
                        grid.getChildren().remove(colorBox);    //annars ska 重叠
                        colorBox = ac.addBox(); // färg inne
                        grid.add(colorBox, 0, 2, 2, 1);
                        //同一demo不同尺寸
                        List<SizeF> storlek = as.addSize(sizes, num, "choose one size");
                        grid.getChildren().remove(sizeBox);
                        sizeBox = as.addBox();
                        grid.add(sizeBox, 0, 3, 2, 1);
                        title.setText(s);
                    }
                });
                b.setToggleGroup(group);
                rbtn.add(b);
            }
        }
        box.getChildren().addAll(rbtn);
        return box;
    } 
    public HBox addBoxO(String title){
        AddMap ap = new AddMap();
        Map mp = ap.changeMapO(orter);
        HBox box = new HBox();
        box.setPadding(new Insets(10));
        box.setSpacing(8);
        Text tit = new Text(title);
        tit.setFont(Font.font("Aria", FontWeight.BOLD, 14));
        box.getChildren().add(tit);
        List<RadioButton> rbtn = new ArrayList<>();
        ToggleGroup group = new ToggleGroup();
        for(int i = 0; i<orter.size(); i++){
            RadioButton b = new RadioButton(mp.get(orter.get(i).getId()).toString());
            b.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                RadioButton rb = (RadioButton) event.getSource();
                String s = rb.getText().trim();   
                tit.setText(s);
                ortId = ap.getId(mp, s);
                }
            });
            b.setToggleGroup(group);
            rbtn.add(b);
        }
        box.getChildren().addAll(rbtn);
        return box;
    }
    public VBox addVBoxB(List<BeställningF> beställ, String str){
        AddMap ap = new AddMap();
        Map mp = ap.changeMapB(beställ);
        VBox box = new VBox();
        box.setPadding(new Insets(10));
        box.setSpacing(8);
        Text title = new Text(str);
        title.setFont(Font.font("Aria", FontWeight.BOLD, 14));
        box.getChildren().add(title);
        List<RadioButton> rbtn = new ArrayList<>();
        ToggleGroup group = new ToggleGroup();
        for(int i = 0; i<beställ.size(); i++){
            if(beställ.get(i).isSkickad()==false){
                RadioButton b = new RadioButton(mp.get(beställ.get(i).getId()).toString());
                b.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    extraInfo.setVisible(false);
                    RadioButton rb = (RadioButton) event.getSource();
                    String s = rb.getText();  
                    int num = ap.getId(mp, s);
                    AddProduktF af = new AddProduktF(beställningar, num);
                    List<BeställningF> produkts = af.addPro();
                    String strPro = "";
                    for(BeställningF b: produkts){
                        strPro = strPro + b.getInfo();
                    }
                    produktInfo.setText(strPro);
                    produktInfo.setVisible(true);
                    title.setText(str);
                    beställId = num;
                    ortBox.setVisible(false);
                    }
                });
                b.setToggleGroup(group);
                rbtn.add(b);
            }
        }
            RadioButton testB = new RadioButton("new beställning");
            testB.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                extraInfo.setVisible(false);
                RadioButton rbt = (RadioButton) event.getSource();
                String st = rbt.getText().trim();    
                title.setText(st);
                ortBox.setVisible(true);
                produktInfo.setVisible(false);
                beställId = -1;
            }
        });
            testB.setToggleGroup(group);
            rbtn.add(testB);
        box.getChildren().addAll(rbtn);
        return box;        
    }
}
