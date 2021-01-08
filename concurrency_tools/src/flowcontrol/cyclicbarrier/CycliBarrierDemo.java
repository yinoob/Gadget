package flowcontrol.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//演示CyclicBarrier
public class CycliBarrierDemo {
    public static void main(String[] args){
        CyclicBarrier cyclicBarrier=new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有人到场,大家统一出发");
            }
        });
        for(int i=0;i<5;i++){
            new Thread(new Task(i,cyclicBarrier)).start();
        }
    }

    static class Task implements Runnable{
        private int id;
        private CyclicBarrier cyclicBarrier;

        public Task(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run(){
            System.out.println(id+"现在前往集合地点");
            try{
                Thread.sleep((long)(Math.random()*2000));
                System.out.println(id+"到了集合地点，开始等待其他人到达");
                cyclicBarrier.await();
            }catch (InterruptedException e){
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
