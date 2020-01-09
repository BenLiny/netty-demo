package com.lyh.netty.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.lyh.netty.handler.MessageService;


import org.springframework.stereotype.Component;

import io.netty.channel.socket.SocketChannel;

/**
 * ClassName: MessageContext <br/>
 * Function:  静态map存放SocketChannel<br/>
 * Reason:  <br/>
 * date: 2019/12/16 <br/>
 *
 * @author lyh
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component
public class MessageHandlerContext {

  private static Map<String, SocketChannel> map = new ConcurrentHashMap<>();

  public static void addChannel(String id, SocketChannel gateway_channel){
    map.put(id, gateway_channel);
  }

  public static Map<String, SocketChannel> getChannels(){
    return map;
  }

  public static SocketChannel getChannel(String id){
    return map.get(id);
  }

  public static void removeChannel(String id){
    map.remove(id);
  }


  private static final Map<Integer, MessageService> handlerMap = new ConcurrentHashMap<>();

  public MessageService getMessageService(Integer type) {
    return handlerMap.get(type);
  }

  public void putMessageService(Integer code, MessageService messageService) {
    handlerMap.put(code, messageService);
  }
}
