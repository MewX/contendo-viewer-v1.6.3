package net.zamasoft.reader.book.a;

import net.zamasoft.reader.book.g;
import net.zamasoft.reader.book.m;

public class j implements Comparable<j>, m.a {
  private static final long b = 1L;
  
  private final String c;
  
  private final int d;
  
  m.a[] a;
  
  public j(String paramString, int paramInt, m.a[] paramArrayOfa) {
    this.c = paramString;
    this.d = paramInt;
    this.a = paramArrayOfa;
  }
  
  public String a() {
    return this.c;
  }
  
  public g b() {
    return (g)new g.a(new f(this.d));
  }
  
  public m.a[] c() {
    return this.a;
  }
  
  public int a(j paramj) {
    g.a a1 = (g.a)b();
    f f1 = (f)a1.b();
    g.a a2 = (g.a)paramj.b();
    f f2 = (f)a2.b();
    return (f1.a < f2.a) ? -1 : ((f1.a > f2.a) ? 1 : 0);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/a/j.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */