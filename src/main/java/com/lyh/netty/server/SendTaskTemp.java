package com.lyh.netty.server;

import io.netty.channel.socket.SocketChannel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

import com.lyh.netty.context.MessageHandlerContext;
import com.lyh.netty.message.AbstractCommonPackage;
import com.lyh.netty.message.HandleMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName: SendTaskTemp <br/>
 * Function:  向客户端主动发消息<br/>
 * Reason:  测试<br/>
 * date: 2019/12/16 <br/>
 *
 * @author lyh
 * @version 1.0.0
 * @since JDK 1.8
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SendTaskTemp implements Runnable {

  private static final Logger logger = LoggerFactory.getLogger(SendTaskTemp.class);

  private AbstractCommonPackage msg;

  private String id;

  @Override
  public void run() {
    try {

      if (id == null && msg == null) {
        Map<String, SocketChannel> map = MessageHandlerContext.getChannels();
        for (Map.Entry<String, SocketChannel> entry : map.entrySet()) {
          msg = new HandleMessage();
          msg.setVersion((byte) 0);
          msg.setType((byte) 0);
          byte[] res = {18, 20, 0, 0, 0, 0};
          msg.setReservedBytes(res);
          entry.getValue().writeAndFlush(new HandleMessage());
          logger.info("广播所有客户端: " + msg);
        }
      } else if (msg != null && id != null) {
        SocketChannel socketChannel = MessageHandlerContext.getChannel(id);
        socketChannel.writeAndFlush(msg);

        logger.info("向客户端： " + id + " 发送： " + msg);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
