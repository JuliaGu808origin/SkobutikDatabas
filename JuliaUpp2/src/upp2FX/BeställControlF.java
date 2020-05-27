/*
 * Java
 */
package upp2FX;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Julia
 */
public class BeställControlF {
    public List<BeställningF> showValidBeställ(List<BeställningF> beställningar, int id){
        List<BeställningF> temp = new ArrayList<>();
        temp = beställningar.stream().
                filter(c -> c.isSkickad()==false && c.getKundId()==id).collect(Collectors.toList());
        for(int j = 0; j<temp.size()-1; j++){   //去重复
            if(temp.get(j).getId()==temp.get(j+1).getId()){
                temp.remove(j+1);
                j--;
            }
        }
        return temp;
    }
}
