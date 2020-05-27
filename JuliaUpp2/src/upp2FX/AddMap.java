/*
 * Java
 */
package upp2FX;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Julia
 */
public class AddMap {
    Map<Integer, String> map = new HashMap<>();
    public Map<Integer, String> changeMapP(List<ProduktF> list){
        for(int i=0; i<list.size(); i++){
            map.put(list.get(i).getDemoId(), list.get(i).toString());
        }
        return map;
    }
    public Map<Integer, String> changeMapC(List<ColorF> list){
        for(int i=0; i<list.size(); i++){
            map.put(list.get(i).getFärgId(), list.get(i).toString());
        }
        return map;
    }
    public Map<Integer, String> changeMapS(List<SizeF> list){
        for(int i=0; i<list.size(); i++){
            map.put(list.get(i).getStorlekId(), list.get(i).toString());
        }
        return map;
    }
    public Map<Integer, String> changeMapO(List<OrtF> list){
        for(int i=0; i<list.size(); i++){
            map.put(list.get(i).getId(), list.get(i).toString());
        }
        return map;
    }
    public Map<Integer, String> changeMapB(List<BeställningF> list){
        for(int i=0; i<list.size(); i++){
            map.put(list.get(i).getId(), (i+1)+". "+list.get(i).toString());
        }
        return map;
    }
    public int getId(Map<Integer, String> m, String name){
        int id=0;
        Set<Map.Entry<Integer, String>> set = m.entrySet();
        Iterator<Map.Entry<Integer, String>> iter = set.iterator();
        while(iter.hasNext()){
            Map.Entry<Integer, String> me = iter.next();
            if(me.getValue().equals(name)){
                id = me.getKey();
            }
        }
        return id;
    }
}
