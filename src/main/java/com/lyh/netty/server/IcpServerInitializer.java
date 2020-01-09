package com.lyh.netty.server;

import java.util.concurrent.TimeUnit;

import com.lyh.netty.codec.ICPDecoder;
import com.lyh.netty.codec.ICPEncoder;
import com.lyh.netty.codec.LengthFieldPrependerEncoder;
import com.lyh.netty.handler.HeartBeatServerHandler;
import com.lyh.netty.handler.ICPServerHandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * ClassName: IcpServerInitializer <br/>
 * Function:  添加编解码器和处理器<br/>
 * Reason:  <br/>
 * date: 2019/12/10 <br/>
 *
 * @author lyh
 * @version 1.0.0
 * @since JDK 1.8
 */
public class IcpServerInitializer extends ChannelInitializer {

  private final static int READER_IDLE_TIMEOUT = 100;
  private final static int WRITER_IDLE_TIMEOUT = 0;
  private final static int ALL_IDLE_TIMEOUT = 0;

  @Override
  protected void initChannel(Channel channel) {
    channel.pipeline().addLast(new IdleStateHandler(READER_IDLE_TIMEOUT, WRITER_IDLE_TIMEOUT,
            ALL_IDLE_TIMEOUT, TimeUnit.SECONDS));

    //注册各种编解码器，处理器
    channel.pipeline().addLast(new LengthFieldPrependerEncoder());
    channel.pipeline().addLast(new ICPDecoder());
    channel.pipeline().addLast(new ICPEncoder());
    channel.pipeline().addLast(new HeartBeatServerHandler());
    channel.pipeline().addLast(new ICPServerHandler());
  }
}
