package net.zamasoft.reader.book.epub;

import java.util.ArrayList;
import java.util.Map;
import jp.cssj.d.a.i;
import net.zamasoft.reader.book.g;
import net.zamasoft.reader.book.m;

public class m implements m.a {
  private static final long a = 1L;
  
  private final i b;
  
  private final Map<i, m.a> c;
  
  private m.a[] d = null;
  
  protected m(i parami, Map<i, m.a> paramMap) {
    this.b = parami;
    this.c = paramMap;
  }
  
  public String a() {
    return this.b.a;
  }
  
  public g b() {
    return (g)new g.c(this.b.b);
  }
  
  public m.a[] c() {
    if (this.d == null) {
      ArrayList<m.a> arrayList = new ArrayList();
      for (i i1 : this.b.f)
        arrayList.add(this.c.get(i1)); 
      this.d = arrayList.<m.a>toArray(new m.a[arrayList.size()]);
    } 
    return this.d;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/epub/m.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */