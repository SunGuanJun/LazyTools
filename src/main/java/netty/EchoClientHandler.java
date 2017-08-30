package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Created by hzsunguanjun on 2017/5/16.
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    public void channelActive(ChannelHandlerContext ctx){
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!!", CharsetUtil.UTF_8));
    }

    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in){
        System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));
    }

    public void exceptionCaugth(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
