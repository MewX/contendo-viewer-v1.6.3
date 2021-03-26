package com.b.a.a.a.b;

import com.b.a.a.a.a.a;
import com.b.a.a.a.a.b;
import java.nio.ByteBuffer;
import java.util.Random;

public class b extends Random implements b {
  private static final long e = -2904811717002732791L;
  
  int a;
  
  int b;
  
  int c;
  
  int d;
  
  public b() {
    this(System.currentTimeMillis());
  }
  
  public b(long paramLong) {
    super(paramLong);
  }
  
  public b(int[] paramArrayOfint) {
    if (paramArrayOfint != null) {
      if (paramArrayOfint.length > 0)
        this.a = paramArrayOfint[0]; 
      if (paramArrayOfint.length > 1)
        this.b = paramArrayOfint[1]; 
      if (paramArrayOfint.length > 2)
        this.c = paramArrayOfint[2]; 
      if (paramArrayOfint.length > 3)
        this.d = paramArrayOfint[3]; 
    } 
  }
  
  public b(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this();
    this.a = paramInt1;
    this.b = paramInt2;
    this.c = paramInt3;
    this.d = paramInt4;
  }
  
  public int[] b() {
    return new int[] { this.a, this.b, this.c, this.d };
  }
  
  public synchronized void setSeed(long paramLong) {
    super.setSeed(paramLong);
    this.a = nextInt() ^ (int)(paramLong >>> 16L ^ 0x4DF72EDFL);
    this.b = nextInt() ^ (int)(paramLong >>> 34L ^ 0x3CE21AEBL);
    this.c = nextInt() ^ (int)(paramLong >>> 27L ^ 0x53124574L);
    this.d = nextInt() ^ (int)(paramLong ^ 0xFFFFFFFFE2C3D4B2L);
  }
  
  protected int next(int paramInt) {
    int i = this.a ^ this.a << 11;
    this.a = this.b;
    this.b = this.c;
    this.c = this.d;
    this.d = this.d ^ this.d >>> 19 ^ i ^ i >>> 8;
    return this.d >>> 32 - paramInt;
  }
  
  public byte[] a() {
    byte[] arrayOfByte = new byte[16];
    int i = 0;
    i = a.a(arrayOfByte, this.a, i);
    i = a.a(arrayOfByte, this.b, i);
    i = a.a(arrayOfByte, this.c, i);
    i = a.a(arrayOfByte, this.d, i);
    return arrayOfByte;
  }
  
  public void a(byte[] paramArrayOfbyte) {
    ByteBuffer byteBuffer = ByteBuffer.wrap(paramArrayOfbyte);
    this.a = byteBuffer.getInt();
    this.b = byteBuffer.getInt();
    this.c = byteBuffer.getInt();
    this.d = byteBuffer.getInt();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/b/a/a/a/b/b.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */