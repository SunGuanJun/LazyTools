package mulThread;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by hzsunguanjun on 2017/5/4.
 */
public class ThreadPool1 {
    public static void main(String[] args){
        ThreadPool1 app = new ThreadPool1();
        app.testJoin();
    }

    public void testJoin(){
        Thread t1 = new Thread(){
            public void run(){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("111111111111");
            }
        };
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread(){
            public void run(){
                System.out.println("2222222222");
            }
        };
        t2.start();

    }

    public void testPool(){
        int count = 200000;
        long startTime = System.currentTimeMillis();
        final List<Integer> l = new LinkedList<>();
        ThreadPoolExecutor tp = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(count));
        final Random random = new Random();
        for (int i=0; i<count; i++){
            tp.execute(new Runnable() {
                @Override
                public void run() {
                    l.add(random.nextInt());
                }
            });
        }
        tp.shutdown();

        try {
            tp.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(l.size());
    }

    public void testMul(){
        int count = 200000;
        long startTime = System.currentTimeMillis();
        final List<Integer> l = new LinkedList<>();
        final Random random = new Random();
        for (int i=0; i<count; i++){
            Thread thread = new Thread(){
                public void run(){
                    l.add(random.nextInt());
                }
            };
            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(l.size());
    }
}
