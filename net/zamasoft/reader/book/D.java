package net.zamasoft.reader.book;

public class D {
  public final k a;
  
  public final int b;
  
  public int c;
  
  public int d;
  
  public int e;
  
  public int f;
  
  public D(k paramk, int paramInt) {
    this.a = paramk;
    this.b = paramInt;
  }
  
  public boolean a() {
    return (this.c == this.e - 1 && this.d == this.f);
  }
  
  public D b() {
    if (this.c >= this.e || (this.c == this.e - 1 && this.d > this.f)) {
      D d = new D(this.a, this.b);
      d.e = this.c + 1;
      d.f = this.d;
      d.c = this.e - 1;
      d.d = this.f;
      return d;
    } 
    return this;
  }
  
  public String c() {
    D d = b();
    StringBuffer stringBuffer = new StringBuffer();
    j j = d.a.e(d.b);
    for (int i = d.c; i < d.e; i++) {
      l l = j.c(i);
      int m = 0;
      int n = l.f();
      if (i == d.c) {
        m += d.d;
        n -= d.d;
      } 
      if (i == d.e - 1)
        n -= m + n - d.f; 
      stringBuffer.append(l.d(), m, n);
    } 
    return stringBuffer.toString();
  }
  
  public String toString() {
    return "startTextIndex=" + this.c + " startTextPos=" + this.d + " endTextIndex=" + this.e + " endTextPos=" + this.f;
  }
  
  public static D a(k paramk, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
    j j = paramk.e(paramInt1);
    D d = new D(paramk, paramInt1);
    int i = 0;
    d.c = -1;
    for (byte b = 0; b < (paramBoolean ? j.i() : j.g()); b++) {
      l l = paramBoolean ? j.d(b) : j.c(b);
      int m = l.f();
      i += m;
      if (d.c == -1 && i > paramInt2) {
        d.c = b;
        d.d = m - i - paramInt2;
      } 
      if (i > paramInt3) {
        d.e = b + 1;
        d.f = m - i - paramInt3 + 1;
        break;
      } 
    } 
    return d;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/D.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */