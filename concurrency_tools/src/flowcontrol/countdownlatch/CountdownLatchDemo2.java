package flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//模拟100米跑步的场景,5名运动员等待裁判的发令枪
public class CountdownLatchDemo2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch begin=new CountDownLatch(1);
        ExecutorService service= Executors.newFixedThreadPool(5);
        for(int i=0;i<5;i++){
            final int no=i+1;
            Runnable runnable=new Runnable(){
                @Override
                public void run(){
                    System.out.println("No"+no+"准备完毕，等待发令枪");
                    try {
                        begin.await();
                        System.out.println("No"+no+"开始跑步了");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            service.submit(runnable);
        }
        Thread.sleep(5000);
        System.out.println("发令枪响,比赛开始！");
        begin.countDown();
    }
}
