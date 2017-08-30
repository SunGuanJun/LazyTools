package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by hzsunguanjun on 2017/5/23.
 */
public class Client {
    public static void main(String[] args){
        Subject subject = new RealSubject();

        InvocationHandler handler = new DynamicProxy(subject);

        Subject subject1 = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(), subject.getClass().getInterfaces(),
                handler);

        System.out.println(subject1.getClass().getName());
        subject1.echo1();
        subject1.echo2("java");
    }
}
