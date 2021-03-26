package net.zamasoft.reader.book;

import java.io.Serializable;
import java.net.URI;

public abstract class g implements Serializable {
  private static final long d = 1L;
  
  public static final byte a = 1;
  
  public static final byte b = 2;
  
  public static final byte c = 3;
  
  public abstract byte a();
  
  public static class b extends g {
    private static final long d = 1L;
    
    private final URI e;
    
    public b(URI param1URI) {
      this.e = param1URI;
    }
    
    public byte a() {
      return 3;
    }
    
    public URI b() {
      return this.e;
    }
    
    public String toString() {
      return this.e.toString();
    }
  }
  
  public static class a extends g {
    private static final long d = 1L;
    
    private final h e;
    
    public a(h param1h) {
      this.e = param1h;
    }
    
    public byte a() {
      return 2;
    }
    
    public h b() {
      return this.e;
    }
    
    public String toString() {
      return this.e.toString();
    }
  }
  
  public static class c extends g {
    private static final long d = 1L;
    
    private final URI e;
    
    public c(URI param1URI) {
      this.e = param1URI;
    }
    
    public byte a() {
      return 1;
    }
    
    public URI b() {
      return this.e;
    }
    
    public String toString() {
      return this.e.toString();
    }
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/g.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */