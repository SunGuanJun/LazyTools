package rpc;

import java.io.IOException;

/**
 * Created by sun on 2017/5/21.
 */
public interface Server {
    public void stop();

    public void start() throws IOException;

    public void register(Class serviceInteface, Class impl);

    public boolean isRunning();

    public int getPort();
}
