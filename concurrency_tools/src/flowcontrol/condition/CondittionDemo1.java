package flowcontrol.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CondittionDemo1 {
    private ReentrantLock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    void method1() throws InterruptedException {
        lock.lock();
        try{
            System.out.println("条件不满足，开始await");
            condition.await();
            System.out.println("条件满足了，开始执行后续的任务");
        } finally {
            lock.unlock();
        }
    }

    void method2(){
        lock.lock();
        try{
            System.out.println("准备工作完成,唤醒其他的线程");
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CondittionDemo1 condittionDemo1=new CondittionDemo1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    try {
                        Thread.sleep(1000);
                        condittionDemo1.method2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        condittionDemo1.method1();
    }
}
