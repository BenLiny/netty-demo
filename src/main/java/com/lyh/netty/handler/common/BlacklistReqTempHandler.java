package com.lyh.netty.handler.common;

import com.lyh.netty.handler.MessageService;
import com.lyh.netty.message.AbstractDataPackage;
import com.lyh.netty.message.Cpid;
import com.lyh.netty.message.MSG_TYPE;
import com.lyh.netty.message.MsgTypeHandler;
import com.lyh.netty.message.common.response.BlacklistResTemp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.netty.channel.ChannelHandlerContext;

/**
 * ClassName: BlacklistReqTempHandler <br/>
 * Function:  <br/>
 * Reason:  <br/>
 * date: 2019/12/17 <br/>
 *
 * @author lyh
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
@MsgTypeHandler(value = MSG_TYPE.BLACKLIST_REQ_TEMP)
public class BlacklistReqTempHandler implements MessageService {

  private static final Logger logger = LoggerFactory.getLogger(BlacklistReqTempHandler.class);

  @Override
  public void handleMessage(ChannelHandlerContext ctx ,AbstractDataPackage msg) {
    logger.info("received a package: " + msg);

    Cpid tmp = msg.getHost();
    msg.setHost(msg.getSender());
    msg.setSender(tmp);

    BlacklistResTemp res = new BlacklistResTemp();
    res.setHost(msg.getHost());
    res.setSender(msg.getSender());
    res.setAreaId(msg.getAreaId());
    res.setType((msg).getType());
    res.setVersion((msg).getVersion());
    res.setReservedBytes((msg).getReservedBytes());
    res.setCode(1289);
    res.setBodyLength(128);

    res.setNumber("1111");
    res.setResultCode("abcdefg");
    res.setReason("abcdefgsas");
    ctx.writeAndFlush(res);

    //测试服务端主动发起消息能不能用
    //new Thread(new SendTaskTemp(res,ctx.channel().id().asLongText())).start();
    logger.info("write back a package: " + res);
  }

}
