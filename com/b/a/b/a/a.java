package com.b.a.b.a;

import com.b.a.a.a;
import com.b.a.a.a.b.b;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;
import java.util.zip.CRC32;

public class a implements a {
  private static final int b = 1;
  
  private LinkedList<byte[]> c = null;
  
  private Object[] d = null;
  
  private b e = new b();
  
  private WeakReference<b> f = null;
  
  private byte[] g = null;
  
  private CRC32 h = new CRC32();
  
  public a(b paramb) {
    this.f = new WeakReference<>(paramb);
  }
  
  a() {}
  
  Random a() {
    return (Random)this.e;
  }
  
  public synchronized int a(byte[] paramArrayOfbyte) {
    if (this.c == null)
      this.c = (LinkedList)new LinkedList<>(); 
    this.c.add(paramArrayOfbyte);
    return this.c.size();
  }
  
  public synchronized Object a(int paramInt) {
    Object object = null;
    if (this.d != null && --paramInt >= 0 && paramInt < this.d.length) {
      object = this.d[paramInt];
      this.d[paramInt] = null;
    } 
    return object;
  }
  
  public synchronized void b(byte[] paramArrayOfbyte) {
    try {
      if (paramArrayOfbyte == null)
        return; 
      int i = paramArrayOfbyte.length - 4;
      if (i < 0)
        return; 
      this.h.reset();
      this.h.update(paramArrayOfbyte, 0, i);
      long l = com.b.a.a.a.a.a.c(paramArrayOfbyte, i) & 0xFFFFFFFFL;
      if (this.h.getValue() != l)
        return; 
      ByteBuffer byteBuffer = ByteBuffer.wrap(paramArrayOfbyte);
      int j = byteBuffer.getInt();
      byte[] arrayOfByte1 = a(byteBuffer);
      this.e.a(arrayOfByte1);
      for (int k = byteBuffer.position(); k < i; k++)
        paramArrayOfbyte[k] = (byte)((paramArrayOfbyte[k] ^ this.e.nextInt(256)) & 0xFF); 
      byte[] arrayOfByte2 = a(byteBuffer);
      if (this.f == null)
        this.g = arrayOfByte2; 
      int m = byteBuffer.getInt();
      if (m > 0) {
        if (this.d == null || this.d.length > m)
          this.d = new Object[Math.max(m, 10)]; 
        for (byte b1 = 0; b1 < m; b1++) {
          byte[] arrayOfByte = a(byteBuffer);
          Object object = null;
          if (arrayOfByte != null)
            object = com.b.a.a.a.c.a.a(arrayOfByte); 
          this.d[b1] = object;
        } 
      } 
    } catch (Throwable throwable) {
    
    } finally {}
  }
  
  byte[] a(ByteBuffer paramByteBuffer) {
    int i = paramByteBuffer.getInt();
    byte[] arrayOfByte = new byte[i];
    paramByteBuffer.get(arrayOfByte);
    return arrayOfByte;
  }
  
  public synchronized byte[] b() {
    byte[] arrayOfByte = null;
    try {
      byte[] arrayOfByte1 = this.e.a();
      this.g = null;
      byte[] arrayOfByte2 = c();
      int i = 0;
      int j = 0;
      while (arrayOfByte == null) {
        if (i)
          arrayOfByte = new byte[i + 4]; 
        int m = 0;
        m = com.b.a.a.a.a.a.a(arrayOfByte, 1, m);
        m = a(arrayOfByte, arrayOfByte1, m);
        j = m;
        m = a(arrayOfByte, arrayOfByte2, m);
        m = a(arrayOfByte, (Collection<byte[]>)this.c, m);
        i = m;
      } 
      int k;
      for (k = j; k < i; k++)
        arrayOfByte[k] = (byte)((arrayOfByte[k] ^ this.e.nextInt(256)) & 0xFF); 
      this.h.reset();
      this.h.update(arrayOfByte, 0, i);
      com.b.a.a.a.a.a.a(arrayOfByte, (int)(this.h.getValue() & 0xFFFFFFFFFFFFFFFFL), i);
      if (this.d != null)
        for (k = 0; k < this.d.length; k++)
          this.d[k] = null;  
      if (this.c != null)
        this.c.clear(); 
    } catch (Throwable throwable) {}
    return arrayOfByte;
  }
  
  byte[] c() {
    if (this.f != null) {
      b b1 = this.f.get();
      if (b1 != null)
        return b1.a(); 
    } 
    return this.g;
  }
  
  private int a(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, int paramInt) {
    boolean bool = (paramArrayOfbyte2 == null) ? false : paramArrayOfbyte2.length;
    paramInt = com.b.a.a.a.a.a.a(paramArrayOfbyte1, bool, paramInt);
    if (bool)
      paramInt = com.b.a.a.a.a.a.a(paramArrayOfbyte1, paramArrayOfbyte2, paramInt); 
    return paramInt;
  }
  
  private int a(byte[] paramArrayOfbyte, Collection<byte[]> paramCollection, int paramInt) {
    boolean bool = (paramCollection == null) ? false : paramCollection.size();
    paramInt = com.b.a.a.a.a.a.a(paramArrayOfbyte, bool, paramInt);
    if (bool)
      for (byte[] arrayOfByte : paramCollection)
        paramInt = a(paramArrayOfbyte, arrayOfByte, paramInt);  
    return paramInt;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/b/a/b/a/a.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */