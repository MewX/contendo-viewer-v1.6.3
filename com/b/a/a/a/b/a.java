package com.b.a.a.a.b;

import java.util.Random;

public class a extends Random {
  private static final long j = 1L;
  
  static final int a = 624;
  
  static final int b = 397;
  
  static final int c = -2147483648;
  
  static final int d = 2147483647;
  
  static final int e = -1727483681;
  
  int[] f = new int[624];
  
  int g;
  
  int h;
  
  int i;
  
  public a() {
    setSeed(System.currentTimeMillis());
  }
  
  public a(long paramLong) {
    setSeed(paramLong);
  }
  
  public a(int[] paramArrayOfint) {
    a(paramArrayOfint);
  }
  
  public synchronized void setSeed(long paramLong) {
    if (this.f == null)
      return; 
    this.f[0] = (int)paramLong;
    for (byte b = 1; b < 'ɰ'; b++)
      this.f[b] = 1812433253 * (this.f[b - 1] ^ this.f[b - 1] >>> 30) + b; 
    this.g = 0;
    this.h = 1;
    this.i = 397;
  }
  
  public synchronized void a(int[] paramArrayOfint) {
    setSeed(19650218L);
    byte b1 = 1;
    byte b2 = 0;
    byte b3;
    for (b3 = 0; b3 < Math.max(624, paramArrayOfint.length); b3++) {
      this.f[b1] = this.f[b1] ^ (this.f[b1 - 1] ^ this.f[b1 - 1] >>> 30) * 1664525;
      this.f[b1] = this.f[b1] + paramArrayOfint[b2] + b2;
      if (++b1 >= 'ɰ') {
        this.f[0] = this.f[623];
        b1 = 1;
      } 
      if (++b2 >= paramArrayOfint.length)
        b2 = 0; 
    } 
    for (b3 = 0; b3 < 'ɯ'; b3++) {
      this.f[b1] = this.f[b1] ^ (this.f[b1 - 1] ^ this.f[b1 - 1] >>> 30) * 1566083941;
      this.f[b1] = this.f[b1] - b1;
      if (++b1 >= 'ɰ') {
        this.f[0] = this.f[623];
        b1 = 1;
      } 
    } 
    this.f[0] = Integer.MIN_VALUE;
  }
  
  protected synchronized int next(int paramInt) {
    int i = this.f[this.g] & Integer.MIN_VALUE | this.f[this.h] & Integer.MAX_VALUE;
    i = this.f[this.g] = this.f[this.i] ^ i >>> 1 ^ (i & 0x1) * -1727483681;
    if (++this.g == 624)
      this.g = 0; 
    if (++this.h == 624)
      this.h = 0; 
    if (++this.i == 624)
      this.i = 0; 
    i ^= i >>> 11;
    i ^= i << 7 & 0x9D2C5680;
    i ^= i << 15 & 0xEFC60000;
    i ^= i >>> 18;
    return i >>> 32 - paramInt;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/b/a/a/a/b/a.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */