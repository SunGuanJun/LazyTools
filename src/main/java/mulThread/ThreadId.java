package mulThread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hzsunguanjun on 2017/7/27.
 */
public class ThreadId {
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId =
         new ThreadLocal<Integer>() {
             @Override
             protected Integer initialValue() {
                 return nextId.getAndIncrement();
             }
         };

    // Returns the current thread's unique ID, assigning it if necessary
     public static int get() {
         return threadId.get();
     }

     public static void main(String[] args){
         Thread thread = new Thread(new Runnable() {
             @Override
             public void run() {
                 for (;;){
                     try {
                         Thread.sleep(5000);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     System.out.println("1:" + ThreadId.get());
                 }
             }
         });
         Thread thread1 = new Thread(new Runnable() {
             @Override
             public void run() {
                 for (;;){
                     try {
                         Thread.sleep(5000);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     System.out.println("2:" + ThreadId.get());
                 }
             }
         });

         thread.start();
         thread1.start();
     }
}