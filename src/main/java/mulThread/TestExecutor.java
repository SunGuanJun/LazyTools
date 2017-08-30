package mulThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hzsunguanjun on 2017/5/15.
 */
public class TestExecutor {
    public static void main(String[] args){
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService = Executors.newFixedThreadPool(6);
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i=0; i<5; i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "被调用了");
                }
            });
            System.out.println("==========" + i + "===============");
        }

        executorService.shutdown();
    }
}


