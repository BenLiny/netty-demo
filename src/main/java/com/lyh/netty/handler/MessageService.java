package com.lyh.netty.handler;

import com.lyh.netty.message.AbstractDataPackage;

import io.netty.channel.ChannelHandlerContext;

/**
 * 策略设计模式
 * 消息处理总接口
 */
public interface MessageService {

  /**
   * 消息处理接口
   */
  void handleMessage(ChannelHandlerContext ctx , AbstractDataPackage messageInfo);
}
