package mulThread;

/**
 * Created by hzsunguanjun on 2017/7/17.
 */
public class Reload extends Thread{

    public static void main(String[] args){
        Reload app = new Reload();
        app.start();

        while (true){
            try {
                System.out.println("xxx");
                Thread.sleep(50000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkAndConfigure() {
        System.out.println("如果改变，则重新加载");
    }

    public void run() {
        while (!this.interrupted()) {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkAndConfigure();
        }

    }
}



