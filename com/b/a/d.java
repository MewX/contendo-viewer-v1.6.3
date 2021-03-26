package com.b.a;

import com.a.a.b.a.b;
import com.a.a.b.c;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import jp.cssj.d.a.a;

public class d implements a {
  private final a a;
  
  private final a b;
  
  public d(a parama, a parama1) {
    this.a = parama;
    this.b = parama1;
  }
  
  public boolean a(String paramString) {
    return this.a.a(paramString);
  }
  
  public InputStream b(String paramString) throws IOException {
    BufferedInputStream bufferedInputStream = new BufferedInputStream(this.a.b(paramString), 1050);
    byte[] arrayOfByte = new byte[1024];
    bufferedInputStream.mark(arrayOfByte.length);
    int i = bufferedInputStream.read(arrayOfByte);
    try {
      bufferedInputStream.reset();
      if (i < arrayOfByte.length || this.b.a(arrayOfByte) == null)
        return bufferedInputStream; 
    } catch (SecurityException securityException) {
      throw securityException;
    } catch (Exception exception) {
      throw new IOException(exception);
    } 
    return (InputStream)new c(this, bufferedInputStream, new c(this.b)) {
        byte[] a = new byte[8192];
        
        int b = super.available() - 1024;
        
        public long skip(long param1Long) throws IOException {
          long l = read(this.a, 0, (int)Math.min(this.a.length, param1Long));
          return (l < 0L) ? 0L : l;
        }
        
        public int available() throws IOException {
          return this.b;
        }
        
        public int read() throws IOException {
          int i = super.read();
          if (i == -1)
            return i; 
          this.b--;
          return i;
        }
        
        public int read(byte[] param1ArrayOfbyte) throws IOException {
          int i = super.read(param1ArrayOfbyte);
          this.b -= i;
          return i;
        }
        
        public int read(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
          int i = super.read(param1ArrayOfbyte, param1Int1, param1Int2);
          this.b -= i;
          return i;
        }
      };
  }
  
  public void a() throws IOException {
    this.a.a();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/b/a/d.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */