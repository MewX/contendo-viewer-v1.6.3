package net.zamasoft.reader.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SystemUtils;

public abstract class g {
  private static final g c;
  
  private Process d;
  
  protected double a = 1.0D;
  
  protected int b = 100;
  
  private Thread e = new Thread(this) {
      public void run() {
        this.a.g();
      }
    };
  
  private int f;
  
  private ByteArrayOutputStream g;
  
  private a h = null;
  
  public static g a() {
    return c;
  }
  
  protected g() {
    this.e.start();
  }
  
  public double b() {
    return this.a;
  }
  
  public void a(double paramDouble) {
    this.a = paramDouble;
  }
  
  public int c() {
    return this.b;
  }
  
  public void a(int paramInt) {
    this.b = paramInt;
  }
  
  private void f() {
    if (this.d != null)
      try {
        this.d.waitFor();
        this.f = (this.d == null) ? 0 : this.d.exitValue();
      } catch (InterruptedException interruptedException) {
      
      } finally {
        this.d = null;
      }  
  }
  
  private void g() {
    while (true) {
      f();
      synchronized (this) {
        if (this.h != null) {
          a a1 = this.h;
          this.h = null;
          a1.task();
        } 
        if (this.d == null)
          try {
            wait();
          } catch (InterruptedException interruptedException) {} 
      } 
    } 
  }
  
  public synchronized void a(String paramString, a parama) throws IOException {
    f();
    if (this.f != 0) {
      this.f = 0;
      throw new IOException(new String(this.g.toByteArray()));
    } 
    this.h = parama;
    this.d = a(paramString);
    InputStream inputStream = this.d.getErrorStream();
    this.g = new ByteArrayOutputStream();
    Thread thread = new Thread(() -> {
          try {
            IOUtils.copy(paramInputStream, this.g);
          } catch (IOException iOException) {}
        });
    thread.start();
    notify();
  }
  
  public abstract Process a(String paramString) throws IOException;
  
  public synchronized boolean d() {
    return (this.d != null);
  }
  
  public synchronized void e() {
    if (this.d != null) {
      this.h = null;
      Process process = this.d;
      this.d = null;
      process.destroy();
      try {
        process.waitFor();
      } catch (InterruptedException interruptedException) {}
    } 
  }
  
  static {
    if (SystemUtils.IS_OS_MAC) {
      c = new e();
    } else {
      c = new h();
    } 
  }
  
  public static interface a {
    void task();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/util/g.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */