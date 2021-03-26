package net.zamasoft.reader.book.a;

import com.b.a.a;
import com.b.a.c;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.pdfbox.io.RandomAccessRead;

public class b implements RandomAccessRead {
  private int a = 12;
  
  private int b = 1 << this.a;
  
  private long c = -1L << this.a;
  
  private int d = 1000;
  
  private byte[] e = null;
  
  private final Map<Long, byte[]> f = (Map<Long, byte[]>)new LinkedHashMap<Long, byte[]>(this, this.d, 0.75F, true) {
      private static final long b = -6302488539257741101L;
      
      protected boolean removeEldestEntry(Map.Entry<Long, byte[]> param1Entry) {
        boolean bool = (size() > this.a.d) ? true : false;
        if (bool)
          this.a.e = param1Entry.getValue(); 
        return bool;
      }
    };
  
  private long g = -1L;
  
  private byte[] h = new byte[this.b];
  
  private int i = 0;
  
  private final RandomAccessFile j;
  
  private final long k;
  
  private long l = 0L;
  
  private boolean m;
  
  private final c n;
  
  private static final int o = 1024;
  
  public b(File paramFile, a parama) throws IOException {
    this.j = new RandomAccessFile(paramFile, "r");
    try {
      this.k = paramFile.length() - 1024L;
      byte[] arrayOfByte = new byte[1024];
      this.j.readFully(arrayOfByte);
      this.n = new c(parama);
      this.n.a(arrayOfByte, 0, arrayOfByte.length);
    } catch (IOException iOException) {
      this.j.close();
      throw iOException;
    } catch (RuntimeException runtimeException) {
      this.j.close();
      throw runtimeException;
    } 
  }
  
  public long getPosition() {
    return this.l;
  }
  
  public void seek(long paramLong) throws IOException {
    long l = paramLong & this.c;
    if (l != this.g) {
      byte[] arrayOfByte = this.f.get(Long.valueOf(l));
      if (arrayOfByte == null) {
        this.j.seek(l + 1024L);
        arrayOfByte = a();
        this.f.put(Long.valueOf(l), arrayOfByte);
      } 
      this.g = l;
      this.h = arrayOfByte;
    } 
    this.i = (int)(paramLong - this.g);
    this.l = paramLong;
  }
  
  private byte[] a() throws IOException {
    byte[] arrayOfByte;
    if (this.e != null) {
      arrayOfByte = this.e;
      this.e = null;
    } else {
      arrayOfByte = new byte[this.b];
    } 
    for (int i = 0; i < this.b; i += k) {
      int j = (int)this.j.getFilePointer() - 1024;
      int k = this.j.read(arrayOfByte, i, this.b - i);
      if (k < 0)
        break; 
      this.n.a(arrayOfByte, j, i, k);
    } 
    return arrayOfByte;
  }
  
  public int read() throws IOException {
    if (this.l >= this.k)
      return -1; 
    if (this.i == this.b)
      seek(this.l); 
    this.l++;
    return this.h[this.i++] & 0xFF;
  }
  
  public int read(byte[] paramArrayOfbyte) throws IOException {
    return read(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    if (this.l >= this.k)
      return -1; 
    if (this.i == this.b)
      seek(this.l); 
    int i = Math.min(this.b - this.i, paramInt2);
    if (this.k - this.l < this.b)
      i = Math.min(i, (int)(this.k - this.l)); 
    System.arraycopy(this.h, this.i, paramArrayOfbyte, paramInt1, i);
    this.i += i;
    this.l += i;
    return i;
  }
  
  public int available() throws IOException {
    return (int)Math.min(this.k - this.l, 2147483647L);
  }
  
  public long length() throws IOException {
    return this.k;
  }
  
  public void close() throws IOException {
    this.j.close();
    this.f.clear();
    this.m = true;
  }
  
  public boolean isClosed() {
    return this.m;
  }
  
  public int peek() throws IOException {
    int i = read();
    if (i != -1)
      rewind(1); 
    return i;
  }
  
  public void rewind(int paramInt) throws IOException {
    seek(getPosition() - paramInt);
  }
  
  public byte[] readFully(int paramInt) throws IOException {
    byte[] arrayOfByte = new byte[paramInt];
    for (int i = read(arrayOfByte); i < paramInt; i += read(arrayOfByte, i, paramInt - i));
    return arrayOfByte;
  }
  
  public boolean isEOF() throws IOException {
    int i = peek();
    return (i == -1);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/a/b.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */