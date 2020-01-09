package com.lyh.netty.message;

public enum MSG_TYPE {

  BLACKLIST_REQ_TEMP(0x508, "BlacklistReqTemp"),
  IN_WHITELIST_REQ_TEMP(0x522,"InWhitelistReqTemp");

  public final int code;

  public final String name;

  MSG_TYPE(int code, String name) {
    this.code = code;
    this.name = name;
  }

  public static String getValue(int code){
    for (MSG_TYPE msgType : values()){

      if (msgType.code == code){
        return msgType.name;
      }

    }

    return null;
  }
}
