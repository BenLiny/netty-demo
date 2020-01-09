package com.lyh.netty.codec;

import java.nio.ByteOrder;

import com.lyh.netty.message.factory.MessageFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * ClassName: ICPDecoder <br/>
 * Function:  icp解码器<br/>
 * Reason:  icp解码器<br/>
 * date: 2019/12/10 <br/>
 *
 * @author lyh
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ICPDecoder extends LengthFieldBasedFrameDecoder {

  private static final ByteOrder BYTE_ORDER = ByteOrder.LITTLE_ENDIAN;

  private static final int MAX_FRAME_LENGTH = 1024;

  private static final int LENGTH_FIELD_OFFSET = 0;

  private static final int LENGTH_FIELD_LENGTH = 2;

  private static final int LENGTH_ADJUSTMENT = 0;

  private static final int INITIAL_BYTES_TO_STRIP =2;

  public ICPDecoder() {
    super(BYTE_ORDER,MAX_FRAME_LENGTH, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH,LENGTH_ADJUSTMENT,INITIAL_BYTES_TO_STRIP,true);
  }

  @Override
  protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
    in = (ByteBuf) super.decode(ctx, in);

    if(in == null){
      return null;
    }

    return MessageFactory.getMessage(in);
  }
}
