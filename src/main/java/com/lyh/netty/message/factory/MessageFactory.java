package com.lyh.netty.message.factory;

import com.lyh.netty.message.AbstractCommonPackage;
import com.lyh.netty.message.AbstractDataPackage;
import com.lyh.netty.message.Cpid;
import com.lyh.netty.message.HandleMessage;
import com.lyh.netty.message.MSG_TYPE;

import io.netty.buffer.ByteBuf;

/**
 * ClassName: MessageFactory <br/>
 * Function:  消息解码工厂<br/>
 * Reason:  <br/>
 * date: 2019/12/10 <br/>
 *
 * @author lyh
 * @version 1.0.0
 * @since JDK 1.8
 */
public class MessageFactory {

  private static final String PACKAGE_PATH = ".common.request.";

  public static Object getMessage(ByteBuf in) throws Exception {

    byte version = in.readByte();
    byte type = in.readByte();
    byte[] reservedBytes = new byte[6];
    in.readBytes(reservedBytes);
    AbstractCommonPackage message;

    switch (type){
      case AbstractCommonPackage.HEARTBEAT_REQ:
        //心跳包
        message = getHeartBeat(version,type,reservedBytes);
        break;
      case AbstractCommonPackage.HEARTBEAT_RES:
        //心跳响应
        message = getHeartBeat(version,type,reservedBytes);
        break;
      case AbstractCommonPackage.DATA_REQ_OR_RES:{
        //普通数据包,读帧头
        int areaId = in.readIntLE();
        short hostPid = in.readShortLE();
        byte hostFid = in.readByte();
        byte hostMid = in.readByte();
        short senderPid = in.readShortLE();
        byte senderFid = in.readByte();
        byte senderMid = in.readByte();
        int code = in.readIntLE();
        int bodyLength = in.readIntLE();

        message = ((AbstractDataPackage) Class.forName(AbstractDataPackage.class.getPackage().getName() + PACKAGE_PATH +
                  MSG_TYPE.getValue(code)).newInstance()).decode(in);

        message.setVersion(version);
        message.setType(type);
        message.setReservedBytes(reservedBytes);
        ((AbstractDataPackage)message).setAreaId(areaId);
        Cpid host = new Cpid();
        host.setPid(hostPid);
        host.setFid(hostFid);
        host.setMid(hostMid);
        ((AbstractDataPackage)message).setHost(host);
        Cpid sender = new Cpid();
        sender.setPid(senderPid);
        sender.setFid(senderFid);
        sender.setMid(senderMid);
        ((AbstractDataPackage)message).setSender(sender);
        ((AbstractDataPackage)message).setCode(code);
        ((AbstractDataPackage)message).setBodyLength(bodyLength);
        break;
      }
      default:
        throw new Exception("UNKNOWN MESSAGE");
    }
    return message;
  }

  private static HandleMessage getHeartBeat(byte version, byte type, byte[] reservedBytes) {
    HandleMessage heartBeatPackage = new HandleMessage();
    heartBeatPackage.setType(type);
    heartBeatPackage.setVersion(version);
    heartBeatPackage.setReservedBytes(reservedBytes);
    return heartBeatPackage;
  }
}
