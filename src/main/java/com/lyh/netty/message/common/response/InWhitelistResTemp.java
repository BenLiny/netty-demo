package com.lyh.netty.message.common.response;

import com.lyh.netty.message.AbstractDataPackage;
import com.lyh.netty.util.StringToBytesUtil;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: InWhitelistResTemp <br/>
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
public class InWhitelistResTemp extends AbstractDataPackage {

  private String number;

  private String resultCode;

  private String reason;

  @Override
  protected void encodeBody(ByteBuf byteBuf) {
    byteBuf.writeBytes(StringToBytesUtil.stringToByte(this.number,32));
    byteBuf.writeBytes(StringToBytesUtil.stringToByte(this.resultCode,32));
    byteBuf.writeBytes(StringToBytesUtil.stringToByte(this.reason,64));
  }
}
