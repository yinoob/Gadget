package atomic;

import java.util.concurrent.atomic.AtomicInteger;


//AtomicInteger的基本用法
public class AtomicIntegerDemo1 implements Runnable{
    private static final AtomicInteger atomicInteger=new AtomicInteger();

    public void incrementAtomic(){
        atomicInteger.getAndIncrement();
    }

    private static volatile  int basicCount=0;

    public synchronized void incrementBasic(){
        basicCount++;
    }



    public static void main(String[] args) throws  InterruptedException{
        AtomicIntegerDemo1 atomicIntegerDemo1 = new AtomicIntegerDemo1();
        Thread t1 = new Thread(atomicIntegerDemo1);
        Thread t2 = new Thread(atomicIntegerDemo1);
        t1.start();
        t2.start();
        t1.join();
        t1.join();
        System.out.println("原子类的结果"+atomicInteger.get());
        System.out.println("普通变量的结果"+basicCount);
    }

    @Override
    public void run(){
        for(int i=0;i<10000;i++){
            incrementAtomic();
            incrementBasic();
        }
    }
}
