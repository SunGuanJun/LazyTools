package rpc;

/**
 * Created by sun on 2017/5/21.
 */
public class HelloServiceImpl implements HelloService {
    public String sayHi(String name) {

        return "Hi, " + name;
    }
}
