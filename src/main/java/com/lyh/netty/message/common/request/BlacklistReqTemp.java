package com.lyh.netty.message.common.request;

import com.lyh.netty.message.AbstractDataPackage;
import com.lyh.netty.util.StringToBytesUtil;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * ClassName: BlacklistReqTemp <br/>
 * Function:  消息实体类<br/>
 * Reason:  body部分<br/>
 * date: 2019/12/10 <br/>
 *
 * @author lyh
 * @version 1.0.0
 * @since JDK 1.8
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class BlacklistReqTemp extends AbstractDataPackage {

  private Integer operation;

  private String number;

  @Override
  public BlacklistReqTemp decode(ByteBuf byteBuf) {
    this.setOperation(byteBuf.readInt());

    byte[] number = new byte[32];
    byteBuf.readBytes(number);
    this.setNumber(new String(number));

    return this;

  }

  @Override
  protected void encodeBody(ByteBuf byteBuf) {

    byteBuf.writeIntLE(this.operation);
    byteBuf.writeBytes(StringToBytesUtil.stringToByte(this.number,32));

  }
}
