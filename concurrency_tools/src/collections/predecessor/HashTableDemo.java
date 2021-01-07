package collections.predecessor;

import java.util.Hashtable;

public class HashTableDemo {
    public static void main(String[] args){
        Hashtable<String,String> hashtable=new Hashtable<>();
        hashtable.put("name","wys");
        System.out.println(hashtable.get("name"));
    }
}
