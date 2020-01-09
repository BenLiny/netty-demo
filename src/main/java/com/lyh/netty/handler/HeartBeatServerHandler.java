package com.lyh.netty.handler;

import com.lyh.netty.message.HandleMessage;
import com.lyh.netty.context.MessageHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * ClassName: HeartBeatServerHandler <br/>
 * Function:  接收消息的处理类<br/>
 * Reason:  心跳包处理<br/>
 * date: 2019/12/11 <br/>
 *
 * @author lyh
 * @version 1.0.0
 * @since JDK 1.8
 */
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {

  /**
   * Logger:日志记录器.
   *
   * @since JDK 1.7
   */
  private static final Logger logger = LoggerFactory.getLogger(HeartBeatServerHandler.class);

  private int heartBeatRequestLostCount = 0;

  private static final int HEART_BEAT_REQUEST_TRY_TIMES = 3;

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {

    //SocketChannel 客户端连接对象存入缓存
    String uuid = ctx.channel().id().asLongText();
    MessageHandlerContext.addChannel(uuid,(SocketChannel) ctx.channel());

    super.channelActive(ctx);
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) {

    ChannelFuture close = ctx.channel().close();
    while (true) {
      if (close.isSuccess()) break;

    }
    logger.info("channel is closed");
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    if (msg instanceof HandleMessage) {
      logger.info("server has received  heartbeat request "+ msg +" from client "+ctx.channel().id());
      ((HandleMessage) msg).setType((byte) 1);
      ctx.channel().writeAndFlush(msg);
    }
    ctx.fireChannelRead(msg);
  }

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
    if (evt instanceof IdleStateEvent) {
      IdleStateEvent event = (IdleStateEvent) evt;
      if (event.state() == IdleState.READER_IDLE) {
        if (heartBeatRequestLostCount >= HEART_BEAT_REQUEST_TRY_TIMES) {
          ctx.channel().close();
          logger.info("断开连接：" + ctx.channel().remoteAddress());
        } else {
          heartBeatRequestLostCount++;
          logger.info("lost heartbeat packet");
        }
      }
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    super.exceptionCaught(ctx, cause);
  }
}
