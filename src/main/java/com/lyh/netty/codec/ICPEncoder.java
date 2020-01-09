package com.lyh.netty.codec;

import com.lyh.netty.message.AbstractDataPackage;
import com.lyh.netty.message.HandleMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * ClassName: ICPEncoder <br/>
 * Function:  icp编码器<br/>
 * Reason:  icp编码器<br/>
 * date: 2019/12/11 <br/>
 *
 * @author lyh
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ICPEncoder extends MessageToByteEncoder {

  @Override
  protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf byteBuf) {
    if (msg instanceof HandleMessage) {
      byteBuf.writeByte(((HandleMessage) msg).getVersion());
      byteBuf.writeByte(((HandleMessage) msg).getType());
      byteBuf.writeBytes(((HandleMessage) msg).getReservedBytes());
    }else if (msg instanceof AbstractDataPackage) {
      ((AbstractDataPackage)msg).encode(byteBuf);
    }
  }

}
