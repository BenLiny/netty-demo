package com.lyh.netty.handler.common;

import com.lyh.netty.handler.MessageService;
import com.lyh.netty.message.AbstractDataPackage;
import com.lyh.netty.message.Cpid;
import com.lyh.netty.message.MSG_TYPE;
import com.lyh.netty.message.MsgTypeHandler;
import com.lyh.netty.message.common.response.InWhitelistResTemp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.netty.channel.ChannelHandlerContext;

/**
 * ClassName: InWhitelistReqTempHandler <br/>
 * Function:  <br/>
 * Reason:  <br/>
 * date: 2019/12/23 <br/>
 *
 * @author lyh
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
@MsgTypeHandler(value = MSG_TYPE.IN_WHITELIST_REQ_TEMP)
public class InWhitelistReqTempHandler implements MessageService {

  private static final Logger logger = LoggerFactory.getLogger(InWhitelistReqTempHandler.class);

  @Override
  public void handleMessage(ChannelHandlerContext ctx, AbstractDataPackage msg) {
    logger.info("received a package: " + msg);

    Cpid tmp = msg.getHost();
    msg.setHost(msg.getSender());
    msg.setSender(tmp);

    InWhitelistResTemp res = new InWhitelistResTemp();
    res.setHost(msg.getHost());
    res.setSender(msg.getSender());
    res.setAreaId(msg.getAreaId());
    res.setType((msg).getType());
    res.setVersion((msg).getVersion());
    res.setReservedBytes((msg).getReservedBytes());
    res.setCode(1315);
    res.setBodyLength(128);

    res.setNumber("111");
    res.setResultCode("abcdef");
    res.setReason("abcs");
    ctx.writeAndFlush(res);

    logger.info("write back a package: " + res);
  }
}
