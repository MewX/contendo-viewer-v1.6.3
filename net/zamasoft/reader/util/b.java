package net.zamasoft.reader.util;

import java.io.IOException;
import java.net.URI;
import jp.cssj.d.a.a;
import jp.cssj.e.c;

public class b implements c {
  protected final a a;
  
  public b(a parama) {
    this.a = parama;
  }
  
  public jp.cssj.e.b b(URI paramURI) throws IOException {
    return (jp.cssj.e.b)new a(this.a, paramURI);
  }
  
  public void a(jp.cssj.e.b paramb) {
    ((a)paramb).close();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/util/b.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */