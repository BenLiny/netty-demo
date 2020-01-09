package com.lyh.netty.listener;

import java.util.Map;

import com.lyh.netty.handler.MessageService;
import com.lyh.netty.message.MsgTypeHandler;
import com.lyh.netty.context.MessageHandlerContext;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * ClassName: MessageServiceListener <br/>
 * Function:  在spring的启动过程中，通过解析注解，将消息类型->消息处理对象的映射关系保存到MessageServiceContext对象中<br/>
 * Reason:  <br/>
 * date: 2019/12/17 <br/>
 *
 * @author lyh
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component
public class MessageServiceListener implements ApplicationListener<ContextRefreshedEvent> {

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {

    Map<String,Object> beans = event.getApplicationContext().getBeansWithAnnotation(MsgTypeHandler.class);
    MessageHandlerContext messageContext = event.getApplicationContext().getBean(MessageHandlerContext.class);

    beans.forEach((name,bean) ->{
      MsgTypeHandler typeHandler = bean.getClass().getAnnotation(MsgTypeHandler.class);
      messageContext.putMessageService(typeHandler.value().code,(MessageService)bean);
    });

  }
}
