package cache;

import cache.computable.Computable;
import cache.computable.ExpensiveFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
//使用了ConcurrentHashMap,升级为支持并发的缓存
public class Cache3<A,V> implements Computable<A,V> {

    private final Map<A,V> cache=new ConcurrentHashMap<>();

    private final Computable<A,V> c;

    public Cache3(Computable<A,V> c){
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
        Cache3<String,Integer> expensiveComputer=new Cache3<>(new ExpensiveFunction());
        Integer result=expensiveComputer.compute("25");
        System.out.println("第一次计算结果"+result);
        result=expensiveComputer.compute("25");
        System.out.println("第二次计算结果"+result);
    }



}
