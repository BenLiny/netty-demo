package com.lyh.netty.message;

import lombok.Data;
import lombok.ToString;

/**
 * ClassName: Cpid <br/>
 * Function:  消息接收和发送者标识<br/>
 * Reason:  <br/>
 * date: 2019/12/10 <br/>
 *
 * @author lyh
 * @version 1.0.0
 * @since JDK 1.8
 */
@Data
@ToString(callSuper = true)
public class Cpid {

  /**
   * 进程号
   */
  private Short pid;

  /**
   * 功能号
   */
  private byte fid;

  /**
   * 模块号
   */
  private byte mid;
}
