package net.zamasoft.reader.book.a;

import java.awt.geom.Area;
import net.zamasoft.reader.book.l;

public class i extends l {
  private final char[] i;
  
  private final double[] j;
  
  private final int k;
  
  private final byte l;
  
  public i(Area paramArea, char[] paramArrayOfchar, double[] paramArrayOfdouble, int paramInt, byte paramByte) {
    super((byte)1, paramArea);
    this.i = paramArrayOfchar;
    this.j = paramArrayOfdouble;
    this.k = paramInt;
    this.l = paramByte;
  }
  
  public byte a() {
    return this.l;
  }
  
  public double b() {
    return (this.l == 2) ? this.a.getBounds2D().getHeight() : this.a.getBounds2D().getWidth();
  }
  
  public double[] c() {
    return this.j;
  }
  
  public char[] d() {
    return this.i;
  }
  
  public int e() {
    return this.k;
  }
  
  public int f() {
    return this.i.length;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/a/i.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */