package com.b.a;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class g {
  private static final byte[] a = new byte[] { 
      51, -106, -114, -6, 36, -87, -79, 106, 81, 3, 
      39, -59, 82, -110, -29, -8, 25, 7, 58, -110, 
      104 };
  
  public static String a(String paramString) {
    String str = null;
    if (paramString != null && paramString.length() != 0)
      try {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(paramString.getBytes());
        byte[] arrayOfByte = messageDigest.digest();
        StringBuffer stringBuffer = new StringBuffer("");
        for (byte b = 0; b < arrayOfByte.length; b++) {
          byte b1 = arrayOfByte[b];
          if (b1 < 0)
            b1 += 256; 
          if (b1 < 16)
            stringBuffer.append("0"); 
          stringBuffer.append(Integer.toHexString(b1));
        } 
        str = stringBuffer.toString();
      } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
        noSuchAlgorithmException.printStackTrace();
      }  
    return str;
  }
  
  public static String a(String paramString, int paramInt) {
    if (paramInt == 0)
      paramInt = paramString.length() * 4; 
    String str1 = "";
    String str2 = a(paramString);
    int i = str2.length();
    while (i < paramInt) {
      str1 = str1.concat(str2);
      i = str1.length();
      str2 = a(str1);
    } 
    return str1;
  }
  
  public static void a(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) {
    if (paramArrayOfbyte1.length <= 0)
      return; 
    int i = a.length;
    for (byte b = 0; b < paramArrayOfbyte1.length; b++) {
      byte b1 = (byte)(b % i);
      paramArrayOfbyte2[b] = (byte)(paramArrayOfbyte1[b] ^ a[b1]);
      paramArrayOfbyte2[b] = (byte)(paramArrayOfbyte2[b] ^ b);
    } 
  }
  
  public static String b(String paramString) {
    return (paramString == null) ? null : paramString.intern();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/b/a/g.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */