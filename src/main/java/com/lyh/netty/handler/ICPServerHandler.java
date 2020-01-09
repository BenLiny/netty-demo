package com.lyh.netty.handler;

import com.lyh.netty.message.AbstractDataPackage;
import com.lyh.netty.context.MessageHandlerContext;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * ClassName: ICPServerHandler <br/>
 * Function:  普通包处理<br/>
 * Reason:  <br/>
 * date: 2019/12/11 <br/>
 *
 * @author lyh
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ICPServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    /*if (msg instanceof AbstractDataPackage) {
      System.out.println("received a package:" + msg);
      Cpid tmp = ((AbstractDataPackage) msg).getHost();
      ((AbstractDataPackage) msg).setHost(((AbstractDataPackage) msg).getSender());
      ((AbstractDataPackage) msg).setSender(tmp);
      ctx.writeAndFlush(msg);
      System.out.println("write back a package:" + msg);
    }*/

    /*
     * 测试
     * 通过code获得对应的service实现方法,对消息进行处理
     * 策略模式
     */
    if (msg instanceof AbstractDataPackage){
      MessageHandlerContext messageContext = new MessageHandlerContext();
      MessageService messageService = messageContext.getMessageService(((AbstractDataPackage) msg).getCode());
      messageService.handleMessage(ctx,(AbstractDataPackage)msg);
    }
  }

}
