package com.lyh.netty.codec;

import java.nio.ByteOrder;

import io.netty.handler.codec.LengthFieldPrepender;

/**
 * ClassName: LengthFieldPrependerEncoder <br/>
 * Function:  协议消息头 长度字节<br/>
 * Reason:  <br/>
 * date: 2019/12/10 <br/>
 *
 * @author lyh
 * @version 1.0.0
 * @since JDK 1.8
 */
public class LengthFieldPrependerEncoder extends LengthFieldPrepender {

  private static final ByteOrder BYTE_ORDER = ByteOrder.LITTLE_ENDIAN;

  public LengthFieldPrependerEncoder() {
    super(BYTE_ORDER,2,0,false);
  }
}
