package mulThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by hzsunguanjun on 2017/5/15.
 */
public class TestCallable {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> futureList = new ArrayList<>();

        for (int i=0; i<10; i++){
            futureList.add(executorService.submit(new Task(i)));
        }
        executorService.shutdown();

        for (Future<String> future : futureList){
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

class Task implements Callable<String>{
    private int id;

    public Task(int id){
        this.id = id;
    }

    public String call() throws Exception{
        System.out.println("call方法被自动调用" + Thread.currentThread().getName());

        return "call方法被自动调用,任务的执行结果是" + id + "," + Thread.currentThread().getName();
    }
}