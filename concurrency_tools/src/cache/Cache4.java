package cache;

import cache.computable.Computable;
import cache.computable.ExpensiveFunction;

import java.util.Map;
import java.util.concurrent.*;

//出于安全性考虑，缓存需要设置有效期，到期自动失效
public class Cache4<A,V> implements Computable<A,V> {

    private final Map<A, Future<V>> cache=new ConcurrentHashMap<>();

    private final Computable<A,V> c;

    public Cache4(Computable<A,V> c){
        this.c=c;
    }

    @Override
    public V compute(A arg) throws Exception{
        Future<V> f=cache.get(arg);
        if(f==null){
            Callable<V> callable=new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft=new FutureTask<>(callable);
            f=cache.putIfAbsent(arg,ft);
            if(f==null){
                f=ft;
                System.out.println("从FutureTask调用了计算函数");
                ft.run();
            }
        }
        return f.get();
    }

    public final static ScheduledExecutorService executor=
            Executors.newScheduledThreadPool(5);

    public V compute(A arg,long expire) throws Exception {
        if(expire>0){
            executor.schedule(new Runnable() {
                @Override
                public void run() {
                    expire(arg);
                }
            },expire,TimeUnit.MILLISECONDS);

        }
        return compute(arg);
    }

    public synchronized void expire(A key){
        Future<V> future=cache.get(key);
        if(future!=null){
            if(!future.isDone()){
                System.out.println("Future任务被取消");
                future.cancel(true);
            }
            System.out.println("到期时间到,缓存被清除");
            cache.remove(key);
        }
    }

    public static void main(String[] args)throws Exception{
        Cache4<String,Integer> expensiveComputer=new Cache4<>(new ExpensiveFunction());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer result=expensiveComputer.compute("25",5000L);
                    System.out.println("第一次的计算结果"+result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer result=expensiveComputer.compute("25");
                    System.out.println("第二次的计算结果"+result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer result=expensiveComputer.compute("24");
                    System.out.println("第三次的计算结果"+result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(6000L);
        Integer result=expensiveComputer.compute("25");
        System.out.println("主线程的计算结果"+result);
    }



}
