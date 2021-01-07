package collections.copyonwrite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

//CopyOnWriteArrayList可以在迭代过程中修改数组内容,但ArrayList不行
public class CopyOnWriteArrayListDemo1 {
    public static void main(String[] args){
        CopyOnWriteArrayList<String> list=new CopyOnWriteArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        Iterator<String> iterable = list.iterator();

        while(iterable.hasNext()){
            System.out.println("list is"+list);
            String next=iterable.next();
            System.out.println(next);

            if(next.equals("2")){
                list.remove("5");
            }
        }
    }
}
