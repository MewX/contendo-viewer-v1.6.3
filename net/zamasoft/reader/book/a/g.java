package net.zamasoft.reader.book.a;

import net.zamasoft.reader.book.D;
import net.zamasoft.reader.book.h;
import net.zamasoft.reader.book.k;
import net.zamasoft.reader.book.s;

public class g extends s {
  public final int e;
  
  public g(int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2) {
    super(paramInt2, paramInt3, paramString1, paramString2);
    this.e = paramInt1;
  }
  
  public boolean a() {
    return false;
  }
  
  public h b() {
    return new f(this.e);
  }
  
  public D b(k paramk, int paramInt) {
    return (paramInt != this.e) ? null : super.b(paramk, paramInt);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/a/g.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */