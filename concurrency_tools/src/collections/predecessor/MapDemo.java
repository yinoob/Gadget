package collections.predecessor;

import java.util.HashMap;
import java.util.Map;

//Map的基本用法
public class MapDemo {
    public static void main(String[] args){
        Map<String,Integer> map=new HashMap<>();
        System.out.println(map.isEmpty());
        map.put("age",25);
        map.put("height",170);
        System.out.println(map.keySet());
        System.out.println(map.size());
        System.out.println(map.containsKey("age"));

    }
}
