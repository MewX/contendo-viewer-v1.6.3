package net.zamasoft.reader.book.epub;

import java.awt.geom.Area;
import net.zamasoft.reader.book.l;

public class k extends l {
  private static final char[] i = new char[] { ' ' };
  
  private final int j;
  
  private final byte k;
  
  private final double l;
  
  private final double[] m;
  
  public k(byte paramByte1, Area paramArea, int paramInt, byte paramByte2, double paramDouble1, double paramDouble2) {
    super(paramByte1, paramArea);
    this.j = paramInt;
    this.k = paramByte2;
    this.m = new double[] { paramDouble1 };
    this.l = paramDouble2;
  }
  
  public byte a() {
    return this.k;
  }
  
  public double b() {
    return this.l;
  }
  
  public double[] c() {
    return this.m;
  }
  
  public char[] d() {
    return i;
  }
  
  public int e() {
    return this.j;
  }
  
  public int f() {
    return 1;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/epub/k.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */