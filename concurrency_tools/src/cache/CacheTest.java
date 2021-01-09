package cache;

import cache.computable.ExpensiveFunction;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheTest {
    static Cache4<String,Integer> expensiveComputer=new Cache4<>(new ExpensiveFunction());

    public static CountDownLatch countDownLatch=new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service= Executors.newFixedThreadPool(100);
        long start=System.currentTimeMillis();
        for(int i=0;i<100;i++){
            service.submit(()->{
                Integer result=null;
                try {
                    System.out.println(Thread.currentThread().getName()+"开始等待");
                    countDownLatch.await();
                    System.out.println(Thread.currentThread().getName()+"被放行");
                    result=expensiveComputer.compute("25");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(result);
            });
        }
        Thread.sleep(5000);
        countDownLatch.countDown();
        service.shutdown();
     // while (!service.isTerminated()){

     // }
     // System.out.println("总耗时:"+(System.currentTimeMillis()-start));
    }
}
