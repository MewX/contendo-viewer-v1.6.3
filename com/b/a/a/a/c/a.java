package com.b.a.a.a.c;

import com.b.a.a.a;
import com.b.a.a.a.b.b;
import java.util.WeakHashMap;

public class a implements a {
  private static WeakHashMap<Object, byte[]> b = (WeakHashMap)new WeakHashMap<>();
  
  public static Object a(byte[] paramArrayOfbyte) {
    Object object = new Object();
    b.put(object, paramArrayOfbyte);
    return object;
  }
  
  public static byte[] a(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) {
    byte[] arrayOfByte1 = new byte[paramArrayOfbyte1.length];
    byte[] arrayOfByte2 = new byte[paramArrayOfbyte2.length];
    System.arraycopy(paramArrayOfbyte2, 0, arrayOfByte2, 0, paramArrayOfbyte2.length);
    b b = new b(paramArrayOfbyte1.length, 2, paramArrayOfbyte2.length, 4);
    byte[] arrayOfByte3 = new byte[Math.min(paramArrayOfbyte2.length, 64)];
    b.nextBytes(arrayOfByte3);
    byte b1 = 0;
    int i = arrayOfByte3.length;
    while (b1 < i) {
      arrayOfByte2[b1] = (byte)((arrayOfByte2[b1] ^ arrayOfByte3[b1]) & 0xFF);
      b1++;
    } 
    a(arrayOfByte1, 0, paramArrayOfbyte1, arrayOfByte2, 0, 0, paramArrayOfbyte1.length);
    return arrayOfByte1;
  }
  
  public static void a(byte[] paramArrayOfbyte1, int paramInt1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, int paramInt2, int paramInt3, int paramInt4) {
    int i = paramArrayOfbyte3.length;
    int j = paramInt2 % i;
    int k = paramInt2;
    for (byte b = 0; b < paramInt4; b++) {
      paramArrayOfbyte1[paramInt1++] = (byte)(paramArrayOfbyte2[paramInt3++] ^ paramArrayOfbyte3[j] ^ (k ^ k >> 8 ^ k >> 16 ^ k >> 24) & 0xFF);
      if (++j >= i)
        j = 0; 
      k++;
    } 
  }
  
  public static void a(byte[] paramArrayOfbyte1, int paramInt1, byte[] paramArrayOfbyte2, Object paramObject, int paramInt2, int paramInt3, int paramInt4) {
    byte[] arrayOfByte = b.get(paramObject);
    a(paramArrayOfbyte1, paramInt1, paramArrayOfbyte2, arrayOfByte, paramInt2, paramInt3, paramInt4);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/b/a/a/a/c/a.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */