package lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Mustlock {
    private static Lock lock=new ReentrantLock();

    public static void main(String[] args){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+
                    "开始执行任务");
        }finally {
            lock.unlock();
        }

    }
}
