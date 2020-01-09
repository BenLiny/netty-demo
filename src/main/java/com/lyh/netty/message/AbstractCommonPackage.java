package com.lyh.netty.message;

import lombok.Data;

/**
 * ClassName: AbstractCommonPackage <br/>
 * Function:  消息包抽象父类<br/>
 * Reason:  消息头类<br/>
 * date: 2019/12/10 <br/>
 *
 * @author lyh
 * @version 1.0.0
 * @since JDK 1.8
 */
@Data
public abstract class AbstractCommonPackage {

  /**
   * 协议头格式：
   *
   * length(2) | version(1) | type(1) | reserved(6) | data(n)
   */
  public static final int HEARTBEAT_REQ = 0; //心跳请求帧

  public static final int HEARTBEAT_RES = 1; //心跳响应帧

  public static final int DATA_REQ_OR_RES = 4; //普通消息帧

  protected Byte version; //协议版本

  protected Byte type; //类别

  protected byte[] reservedBytes = new byte[6]; //保留字段
}
