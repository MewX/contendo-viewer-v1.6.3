package com.b.a.a.a.a;

public class a {
  static final int a = 2;
  
  static final int b = 2;
  
  static final int c = 4;
  
  static final int d = 8;
  
  static final int e = 4;
  
  static final int f = 8;
  
  public static char a(byte[] paramArrayOfbyte, int paramInt) {
    int i = paramArrayOfbyte[paramInt] & 0xFF;
    i = i << 8 | paramArrayOfbyte[paramInt + 1] & 0xFF;
    return (char)i;
  }
  
  public static short b(byte[] paramArrayOfbyte, int paramInt) {
    int i = paramArrayOfbyte[paramInt] & 0xFF;
    i = i << 8 | paramArrayOfbyte[paramInt + 1] & 0xFF;
    return (short)i;
  }
  
  public static int c(byte[] paramArrayOfbyte, int paramInt) {
    null = paramArrayOfbyte[paramInt] & 0xFF;
    null = null << 8 | paramArrayOfbyte[paramInt + 1] & 0xFF;
    null = null << 8 | paramArrayOfbyte[paramInt + 2] & 0xFF;
    return null << 8 | paramArrayOfbyte[paramInt + 3] & 0xFF;
  }
  
  public static long d(byte[] paramArrayOfbyte, int paramInt) {
    null = paramArrayOfbyte[paramInt] & 0xFFL;
    null = null << 8L | paramArrayOfbyte[paramInt + 1] & 0xFFL;
    null = null << 8L | paramArrayOfbyte[paramInt + 2] & 0xFFL;
    null = null << 8L | paramArrayOfbyte[paramInt + 3] & 0xFFL;
    null = null << 8L | paramArrayOfbyte[paramInt + 4] & 0xFFL;
    null = null << 8L | paramArrayOfbyte[paramInt + 5] & 0xFFL;
    null = null << 8L | paramArrayOfbyte[paramInt + 6] & 0xFFL;
    return null << 8L | paramArrayOfbyte[paramInt + 7] & 0xFFL;
  }
  
  public static int a(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    if (paramArrayOfbyte != null) {
      paramArrayOfbyte[paramInt2 + 3] = (byte)(paramInt1 & 0xFF);
      paramInt1 >>>= 8;
      paramArrayOfbyte[paramInt2 + 2] = (byte)(paramInt1 & 0xFF);
      paramInt1 >>>= 8;
      paramArrayOfbyte[paramInt2 + 1] = (byte)(paramInt1 & 0xFF);
      paramInt1 >>>= 8;
      paramArrayOfbyte[paramInt2] = (byte)(paramInt1 & 0xFF);
    } 
    return paramInt2 + 4;
  }
  
  public static int a(byte[] paramArrayOfbyte, short paramShort, int paramInt) {
    if (paramArrayOfbyte != null) {
      int i = paramShort;
      paramArrayOfbyte[paramInt + 1] = (byte)(i & 0xFF);
      i >>>= 8;
      paramArrayOfbyte[paramInt] = (byte)(i & 0xFF);
    } 
    return paramInt + 2;
  }
  
  public static int a(byte[] paramArrayOfbyte, char paramChar, int paramInt) {
    if (paramArrayOfbyte != null) {
      int i = paramChar;
      paramArrayOfbyte[paramInt + 1] = (byte)(i & 0xFF);
      i >>>= 8;
      paramArrayOfbyte[paramInt] = (byte)(i & 0xFF);
    } 
    return paramInt + 2;
  }
  
  public static int a(byte[] paramArrayOfbyte, long paramLong, int paramInt) {
    if (paramArrayOfbyte != null) {
      paramArrayOfbyte[paramInt + 7] = (byte)(int)(paramLong & 0xFFL);
      paramLong >>>= 8L;
      paramArrayOfbyte[paramInt + 6] = (byte)(int)(paramLong & 0xFFL);
      paramLong >>>= 8L;
      paramArrayOfbyte[paramInt + 5] = (byte)(int)(paramLong & 0xFFL);
      paramLong >>>= 8L;
      paramArrayOfbyte[paramInt + 4] = (byte)(int)(paramLong & 0xFFL);
      paramLong >>>= 8L;
      paramArrayOfbyte[paramInt + 3] = (byte)(int)(paramLong & 0xFFL);
      paramLong >>>= 8L;
      paramArrayOfbyte[paramInt + 2] = (byte)(int)(paramLong & 0xFFL);
      paramLong >>>= 8L;
      paramArrayOfbyte[paramInt + 1] = (byte)(int)(paramLong & 0xFFL);
      paramLong >>>= 8L;
      paramArrayOfbyte[paramInt] = (byte)(int)(paramLong & 0xFFL);
    } 
    return paramInt + 8;
  }
  
  public static int a(byte[] paramArrayOfbyte, byte paramByte, int paramInt) {
    if (paramArrayOfbyte != null)
      paramArrayOfbyte[paramInt] = paramByte; 
    return paramInt + 1;
  }
  
  public static int a(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, int paramInt) {
    if (paramArrayOfbyte1 != null)
      System.arraycopy(paramArrayOfbyte2, 0, paramArrayOfbyte1, paramInt, paramArrayOfbyte2.length); 
    return paramInt + paramArrayOfbyte2.length;
  }
  
  public static char a(char paramChar) {
    return (char)((paramChar << 8 | paramChar >>> 8) & 0xFFFF);
  }
  
  public static short a(short paramShort) {
    return (short)((paramShort << 8 | paramShort >>> 8) & 0xFFFF);
  }
  
  public static int a(int paramInt) {
    int i = paramInt << 8 & 0xFF00FF00;
    int j = paramInt >>> 8 & 0xFF00FF;
    paramInt = i | j;
    i = paramInt << 16;
    j = paramInt >>> 16;
    return i | j;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/b/a/a/a/a/a.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */