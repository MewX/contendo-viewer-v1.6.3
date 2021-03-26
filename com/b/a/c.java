package com.b.a;

import com.a.a.b.a.b;
import java.io.IOException;

public class c implements b {
  private final a a;
  
  private Object b = null;
  
  public c(a parama) {
    this.a = parama;
  }
  
  public int a() {
    return 1024;
  }
  
  public int a(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    if (paramInt2 < 1024)
      return paramInt2 - 1024; 
    if (paramInt1 > 0) {
      byte[] arrayOfByte = new byte[1024];
      System.arraycopy(paramArrayOfbyte, paramInt1, arrayOfByte, 0, 1024);
      paramArrayOfbyte = arrayOfByte;
    } 
    try {
      this.b = this.a.a(paramArrayOfbyte);
    } catch (SecurityException securityException) {
      throw securityException;
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    } 
    return (this.b == null) ? 0 : 1024;
  }
  
  public void a(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3) throws IOException {
    this.a.a(paramArrayOfbyte, this.b, paramInt1, paramInt2, paramInt3);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/b/a/c.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */