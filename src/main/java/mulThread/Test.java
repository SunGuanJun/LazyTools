package mulThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by hzsunguanjun on 2017/4/18.
 */
public class Test {
    int i;
    int j;
    Long start;

    public static void main(String[] args){
        Test test = new Test();

        test.testExchanger();

    }

    public void testFuture(){
    }
    public void testExchanger(){
        final Exchanger<List<Integer>> exchanger = new Exchanger<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Integer> list = new ArrayList<>(2);
                list.add(1);
                list.add(2);

                try {
                    list = exchanger.exchange(list);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread1:" + list);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Integer> list2 = new ArrayList<>(2);
                list2.add(4);
                list2.add(5);

                try {
                    list2 = exchanger.exchange(list2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread2:" + list2);
            }
        }).start();

    }


    public void singleThreadPrint(){
        while (true){
            if (i > 1000000000){
                System.out.println(System.currentTimeMillis() - start);
                break;
            }
            i++;
        }
        while (true){
            if (j > 1000000000){
                System.out.println(System.currentTimeMillis() - start);
                break;
            }
            j++;
        }
    }

    public void mulThreadPrint(){

        new Thread(new Runnable() {
            public void run() {
                while (true){
                    if (i > 1000000000){
                        System.out.println(System.currentTimeMillis() - start);
                        break;
                    }
                    i++;
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                while (true){
                    if (j > 1000000000){
                        System.out.println(System.currentTimeMillis() - start);
                        break;
                    }
                    j++;
                }
            }
        }).start();


        System.out.println("hh");
    }
}
