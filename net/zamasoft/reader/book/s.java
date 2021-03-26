package net.zamasoft.reader.book;

import net.zamasoft.reader.book.epub.d;
import net.zamasoft.reader.book.epub.g;

public abstract class s {
  public final int a;
  
  public final int b;
  
  public final String c;
  
  public String d;
  
  public s(int paramInt1, int paramInt2, String paramString1, String paramString2) {
    this.a = paramInt1;
    this.b = paramInt2;
    this.c = paramString1;
    this.d = paramString2;
  }
  
  public abstract boolean a();
  
  public abstract h b();
  
  public e a(k paramk, int paramInt) {
    g g = (g)paramk.e(paramInt);
    for (byte b = 0; b < g.f(); b++) {
      e e = g.b(b);
      if (((d)e).c == this.a)
        return e; 
    } 
    return null;
  }
  
  public D b(k paramk, int paramInt) {
    byte b = -1;
    int i = 0;
    int j = 0;
    int m = 0;
    j j1 = paramk.e(paramInt);
    for (byte b1 = 0; b1 < j1.g(); b1++) {
      l l = j1.c(b1);
      int n = l.e();
      int i1 = l.f();
      if (this.a < n + i1) {
        if (this.b < n)
          break; 
        if (b == -1) {
          b = b1;
          if (this.a >= n && this.a < n + i1) {
            j = this.a - l.e();
          } else {
            j = 0;
          } 
        } 
        if (this.b <= n + i1 && this.b >= n) {
          i = b1 + 1;
          m = this.b - l.e();
        } else if (b1 == j1.g() - 1) {
          i = b1 + 1;
          m = i1;
        } 
      } 
    } 
    if (b == -1)
      return null; 
    D d = new D(paramk, paramInt);
    d.c = b;
    d.d = j;
    d.e = i;
    d.f = m;
    return d;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/s.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */