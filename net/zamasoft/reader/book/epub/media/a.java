package net.zamasoft.reader.book.epub.media;

import java.io.IOException;
import java.net.URI;
import jp.cssj.homare.b.a.a.l;
import jp.cssj.homare.b.a.f;
import jp.cssj.sakae.c.b;
import jp.cssj.sakae.c.b.b;

public class a implements l, b {
  public final URI a;
  
  private final double d;
  
  private final double e;
  
  public final boolean b;
  
  public final b c;
  
  public a(URI paramURI, double paramDouble1, double paramDouble2, boolean paramBoolean, b paramb) throws IOException {
    this.a = paramURI;
    this.b = paramBoolean;
    this.c = paramb;
    if (this.c != null) {
      this.d = this.c.a();
      this.e = this.c.b();
    } else {
      this.d = paramDouble1;
      this.e = paramDouble2;
    } 
  }
  
  public void a(f paramf, double paramDouble1, double paramDouble2) {}
  
  public double a() {
    return this.d;
  }
  
  public double b() {
    return this.e;
  }
  
  public String c() {
    return "";
  }
  
  public void a(b paramb) {}
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/epub/media/a.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */