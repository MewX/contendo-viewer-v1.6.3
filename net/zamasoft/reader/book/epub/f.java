package net.zamasoft.reader.book.epub;

import net.zamasoft.reader.book.h;
import net.zamasoft.reader.book.s;

public class f extends s {
  public final String e;
  
  public final boolean f;
  
  public f(String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3, boolean paramBoolean) {
    super(paramInt1, paramInt2, paramString2, paramString3);
    this.e = paramString1;
    this.f = paramBoolean;
  }
  
  public boolean a() {
    return this.f;
  }
  
  public h b() {
    return new e(this.e, this.a);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/epub/f.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */