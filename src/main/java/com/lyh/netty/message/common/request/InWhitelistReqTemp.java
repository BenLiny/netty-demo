package com.lyh.netty.message.common.request;

import com.lyh.netty.message.AbstractDataPackage;
import com.lyh.netty.util.StringToBytesUtil;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: InWhitelistReqTemp <br/>
 * Function:  <br/>
 * Reason:  <br/>
 * date: 2019/12/23 <br/>
 *
 * @author lyh
 * @version 1.0.0
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InWhitelistReqTemp extends AbstractDataPackage {

  private Integer operation;

  private String number;

  @Override
  public InWhitelistReqTemp decode(ByteBuf byteBuf) {
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
