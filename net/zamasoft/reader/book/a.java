package net.zamasoft.reader.book;

import java.awt.geom.Area;

public class a extends l {
  private final String i;
  
  private final int j;
  
  public a(Area paramArea, String paramString, int paramInt) {
    super((byte)4, paramArea);
    this.i = paramString;
    this.j = paramInt;
  }
  
  public byte a() {
    return 1;
  }
  
  public double b() {
    return this.a.getBounds2D().getHeight();
  }
  
  public double[] c() {
    double[] arrayOfDouble = new double[f()];
    double d = this.a.getBounds().getWidth();
    for (byte b = 0; b < arrayOfDouble.length; b++)
      arrayOfDouble[b] = d / arrayOfDouble.length; 
    return arrayOfDouble;
  }
  
  public char[] d() {
    return this.i.toCharArray();
  }
  
  public int e() {
    return this.j;
  }
  
  public int f() {
    return this.i.length();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/a.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */