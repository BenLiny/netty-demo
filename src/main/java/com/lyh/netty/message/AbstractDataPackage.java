package com.lyh.netty.message;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * ClassName: AbstractDataPackage <br/>
 * Function:  消息抽象类<br/>
 * Reason:  帧体类<br/>
 * date: 2019/12/10 <br/>
 *
 * @author lyh
 * @version 1.0.0
 * @since JDK 1.8
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public abstract class AbstractDataPackage extends AbstractCommonPackage {

  /**
   * 数据包头格式：
   *
   *  消息类别码areaId(4) | 消息接收者标识host(4) | 消息发送者标识sender(4) | 消息种别码code(4) | 消息体长度bodylength(4) | 消息体body(n)
   *
   */
  private Integer areaId;

  private Cpid host;

  private Cpid sender;

  private Integer code;

  private Integer bodyLength;

  public void encode(ByteBuf byteBuf){
    byteBuf.writeByte(this.version);
    byteBuf.writeByte(this.type);
    byteBuf.writeBytes(this.reservedBytes);
    byteBuf.writeIntLE(this.areaId);
    byteBuf.writeShortLE(this.host.getPid());
    byteBuf.writeByte(this.host.getFid());
    byteBuf.writeByte(this.host.getMid());
    byteBuf.writeShortLE(this.sender.getPid());
    byteBuf.writeByte(this.sender.getFid());
    byteBuf.writeByte(this.sender.getMid());
    byteBuf.writeIntLE(this.code);
    byteBuf.writeIntLE(this.bodyLength);
    encodeBody(byteBuf);
  }

  public AbstractDataPackage decode(ByteBuf byteBuf){
    throw new UnsupportedOperationException();
  }

  protected abstract void encodeBody(ByteBuf byteBuf);
}


