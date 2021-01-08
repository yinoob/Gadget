package flowcontrol.condition;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//使用Condition实现生产者消费者模式
public class ConditionDemo2 {
    private int queueSize=10;
    private PriorityQueue<Integer> queue =new PriorityQueue<>(queueSize);
    private Lock lock=new ReentrantLock();
    private Condition notFull=lock.newCondition();
    private Condition notEmpty=lock.newCondition();

    public static void main(String[] args){
        ConditionDemo2 conditionDemo2=new ConditionDemo2();
        Produce produce=conditionDemo2.new Produce();
        Consumer consumer=conditionDemo2.new Consumer();
        produce.start();
        consumer.start();
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == 0) {
                        System.out.println("队列空,等待数据");
                        try {
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    queue.poll();
                    notFull.signal();
                    System.out.println("从队列取走了一个数据,队列剩余"+queue.size()+"个元素");
                }finally {
                    lock.unlock();
                }
            }
        }
    }

    class Produce extends Thread {
        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == queueSize) {
                        System.out.println("队列满,等待有空余");
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    queue.offer(1);
                    notEmpty.signal();
                    System.out.println("从队列插入了一个数据,队列还有"+(queueSize-queue.size())+"个剩余空间");
                }finally {
                    lock.unlock();
                }
            }
        }
    }
}

