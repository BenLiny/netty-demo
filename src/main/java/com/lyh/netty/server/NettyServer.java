package com.lyh.netty.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.Data;

/**
 * ClassName: NettyServer <br/>
 * Function:  netty服务端<br/>
 * Reason:  netty服务端<br/>
 * date: 2019/12/10 <br/>
 *
 * @author lyh
 * @version 1.0.0
 * @since JDK 1.8
 */
@Data
public class NettyServer {

  /**
   * Logger:日志记录器.
   *
   * @since JDK 1.7
   */
  private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

  private int port;

  public static Map<String, ChannelHandlerContext> contextMap = new ConcurrentHashMap<>();

  public NettyServer(int port) {
    this.port = port;
  }

  public void run() throws InterruptedException {

    //boosGroup 用于 Accept 连接建立事件并分发请求
    EventLoopGroup bossGroup = new NioEventLoopGroup(0,new DefaultThreadFactory("bossWorker"));
    //workerGroup 用于处理 I/O 读写事件和业务逻辑
    EventLoopGroup workerGroup = new NioEventLoopGroup(0,new DefaultThreadFactory("workerPool"));

    ServerBootstrap bootstrap = new ServerBootstrap();
    bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class);
    bootstrap.childHandler(new IcpServerInitializer());
    bootstrap.option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

    try {
      logger.info("ICP Server Started");
      ChannelFuture f = bootstrap.bind(port).sync();
      f.channel().closeFuture().sync();
    } finally {
      logger.info("ICP Server Close");
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }
}
