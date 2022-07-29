package com.ymchen.incubatordemo.examples.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class Client {
    public static void main(String[] args) throws InterruptedException {

        new Bootstrap().group(new NioEventLoopGroup()).channel(NioSocketChannel.class).handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel sc) {
                sc.pipeline().addLast(new StringEncoder());
            }
        }).connect(new InetSocketAddress(8080)).sync().channel().writeAndFlush("hello");

    }
}
