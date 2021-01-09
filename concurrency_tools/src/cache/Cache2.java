package cache;

import cache.computable.Computable;
import cache.computable.ExpensiveFunction;

import java.util.HashMap;
import java.util.Map;

public class Cache2<A,V> implements Computable<A,V> {

    private final Map<A,V> cache=new HashMap<>();

    private final Computable<A,V> c;

    public Cache2(Computable<A,V> c){
        this.c=c;
    }

    @Override
    public V compute(A arg) throws Exception{
        System.out.println("进入缓存机制");
        V result=cache.get(arg);
        if(result==null){
            result=c.compute(arg);
            cache.put(arg,result);

        }
        return result;
    }

    public static void main(String[] args)throws Exception{
        Cache2<String,Integer> expensiveComputer=new Cache2<>(new ExpensiveFunction());
        Integer result=expensiveComputer.compute("25");
        System.out.println("第一次计算结果"+result);
        result=expensiveComputer.compute("25");
        System.out.println("第二次计算结果"+result);
    }



}
