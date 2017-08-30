package proxy;

/**
 * Created by hzsunguanjun on 2017/5/23.
 */
public class RealSubject implements Subject {
    @Override
    public void echo1() {
        System.out.println("hello world");
    }

    @Override
    public void echo2(String name) {
        System.out.println("hello" + name);
    }
}
