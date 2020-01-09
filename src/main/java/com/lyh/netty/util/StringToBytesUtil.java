package com.lyh.netty.util;

import io.netty.util.CharsetUtil;

/**
 * ClassName: StringToBytesUtil <br/>
 * Function:  字符串转byte数组<br/>
 * Reason:  <br/>
 * date: 2019/12/13 <br/>
 *
 * @author lyh
 * @version 1.0.0
 * @since JDK 1.8
 */
public class StringToBytesUtil {

  public static byte[] stringToByte(String data,int length){

    byte[] bytes;
    bytes = data.getBytes(CharsetUtil.UTF_8);
    if (bytes.length == length) {
      return bytes;
    } else if (bytes.length < length) {
      return addAll(bytes, new byte[length - bytes.length]);
    } else {
      throw new RuntimeException();
    }
  }

  private static byte[] addAll(byte[] array1, byte[] array2) {
    if (array1 == null) {
      return clone(array2);
    } else if (array2 == null) {
      return clone(array1);
    } else {
      byte[] joinedArray = new byte[array1.length + array2.length];
      System.arraycopy(array1, 0, joinedArray, 0, array1.length);
      System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
      return joinedArray;
    }
  }

  private static byte[] clone(byte[] array) {
    return array == null ? null : (array.clone());
  }

}
