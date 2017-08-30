package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by hzsunguanjun on 2017/5/23.
 */
public class DynamicProxy implements InvocationHandler {
    private Object subject;

    public DynamicProxy(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("proxy start");
        method.invoke(subject, args);
        System.out.println("proxy end");
        return null;
    }
}
