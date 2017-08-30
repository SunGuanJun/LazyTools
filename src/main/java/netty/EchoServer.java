package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by hzsunguanjun on 2017/5/16.
 */
public class EchoServer {
    private int port;

    public EchoServer(int port){
        this.port = port;
    }

    public static void main(String[] args) throws Exception{
        if (args.length != 1){
            System.err.println("Usage: " + EchoServer.class.getSimpleName() + "<port>");
        }
        int port = Integer.valueOf(args[0]);
        new EchoServer(port).start();
    }

    public void start() throws Exception{
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup(); // 1
        try{
            ServerBootstrap b = new ServerBootstrap(); //2
            b.group(group)
                    .channel(NioServerSocketChannel.class)  //3
                    .localAddress(new InetSocketAddress(port))  //4
                    .childHandler(new ChannelInitializer<SocketChannel>() { //5
                        public void initChannel (SocketChannel channel) throws Exception{
                            channel.pipeline().addLast(serverHandler);
                        }
                    });
            ChannelFuture f = b.bind().sync();  //6
            f.channel().closeFuture().sync();   //7
        } finally {
            group.shutdownGracefully().sync();  //8
        }
    }
}
