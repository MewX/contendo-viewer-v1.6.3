package net.zamasoft.reader.book.epub;

import java.awt.geom.Area;
import jp.cssj.sakae.c.a.f;
import jp.cssj.sakae.c.d.h;
import net.zamasoft.reader.book.l;

public class l extends l {
  private final h i;
  
  private double[] j;
  
  public l(byte paramByte, Area paramArea, h paramh) {
    super(paramByte, paramArea);
    this.i = paramh;
  }
  
  public byte a() {
    return this.i.b().a();
  }
  
  public double b() {
    return (this.i.f() + this.i.g()) * 96.0D / 72.0D;
  }
  
  public double[] c() {
    if (this.j != null)
      return this.j; 
    f f = this.i.d();
    int[] arrayOfInt = this.i.j();
    this.j = new double[this.i.l()];
    double[] arrayOfDouble = this.i.a(false);
    for (byte b = 0; b < this.j.length; b++) {
      this.j[b] = f.a(arrayOfInt[b]) + this.i.m();
      if (arrayOfDouble != null && b < arrayOfDouble.length)
        this.j[b] = this.j[b] + arrayOfDouble[b]; 
      if (b > 0)
        this.j[b] = this.j[b] - f.a(arrayOfInt[b - 1], arrayOfInt[b]); 
      this.j[b] = this.j[b] * 96.0D / 72.0D;
    } 
    return this.j;
  }
  
  public char[] d() {
    return this.i.h();
  }
  
  public int e() {
    return this.i.e();
  }
  
  public int f() {
    return this.i.i();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/epub/l.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */