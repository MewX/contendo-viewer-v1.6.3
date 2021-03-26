package net.zamasoft.reader.book;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jp.cssj.c.b;
import jp.cssj.e.b;
import jp.cssj.homare.ua.ImageLoader;
import jp.cssj.homare.ua.m;
import jp.cssj.sakae.c.b;
import jp.cssj.sakae.c.b.b;
import jp.cssj.sakae.c.c;
import net.zamasoft.reader.book.epub.n;
import net.zamasoft.reader.util.d;

public class o implements b {
  private static Logger b = Logger.getLogger(b.class.getName());
  
  private final n c;
  
  private final b d;
  
  static final Map<String, b> a = Collections.synchronizedMap((Map<String, b>)new d(0.25D));
  
  public o(n paramn, b paramb) {
    this.c = paramn;
    this.d = paramb;
  }
  
  private b a(b paramb) throws IOException {
    ImageLoader imageLoader = (ImageLoader)b.a().a(ImageLoader.class, paramb);
    return imageLoader.loadImage((m)this.c, paramb);
  }
  
  public b d() {
    try {
      String str = "" + this.c.w().c() + "_" + this.c.w().c();
      synchronized (a) {
        b b1 = a.get(str);
        if (b1 == null)
          a.put(str, b1 = a(this.d)); 
        return b1;
      } 
    } catch (IOException iOException) {
      b.log(Level.FINE, "画像が読み込めませんでした", iOException);
      return null;
    } 
  }
  
  public double a() {
    b b1 = d();
    return (b1 == null) ? 1.0D : b1.a();
  }
  
  public double b() {
    b b1 = d();
    return (b1 == null) ? 1.0D : b1.b();
  }
  
  public void a(b paramb) throws c {
    b b1 = d();
    if (b1 == null)
      return; 
    b1.a(paramb);
  }
  
  public String c() {
    b b1 = d();
    return (b1 == null) ? "" : b1.c();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/o.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */