package net.zamasoft.reader.book.epub;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import jp.cssj.d.a.f;
import jp.cssj.d.a.g;
import jp.cssj.sakae.b.a.b;
import jp.cssj.sakae.c.b;
import net.zamasoft.reader.ReaderApplication;
import net.zamasoft.reader.book.h;
import net.zamasoft.reader.book.j;
import net.zamasoft.reader.book.s;
import net.zamasoft.reader.util.c;
import net.zamasoft.reader.util.d;
import net.zamasoft.reader.util.f;

public class i implements h {
  public final int a;
  
  public final String b;
  
  private final g e;
  
  private final f f;
  
  private final int g;
  
  private final int h;
  
  private final int i;
  
  private final b j;
  
  static final Map<String, List<g>> c = Collections.synchronizedMap((Map<String, List<g>>)new d(0.2D));
  
  static final Map<String, Image> d = Collections.synchronizedMap((Map<String, Image>)new d(0.2D));
  
  private final Map<String, Integer> k = new HashMap<>();
  
  private boolean l = false;
  
  protected i(int paramInt1, g paramg, f paramf, int paramInt2, String paramString, b paramb) throws Exception {
    this.a = paramInt1;
    this.e = paramg;
    this.f = paramf;
    this.g = paramInt2;
    this.b = paramString;
    this.j = paramb;
    ArrayList<g> arrayList = new ArrayList();
    this.j.a(this.e, this.f, arrayList, this.g);
    this.i = arrayList.size();
    this.h = ((g)arrayList.get(this.i - 1)).b();
    byte b1 = 0;
    for (g g1 : arrayList) {
      for (byte b2 = 0; b2 < g1.k(); b2++) {
        c.b b3 = g1.a(b2);
        this.k.put(b3.a, Integer.valueOf(b1));
      } 
      b1++;
    } 
    c.put(this.b, arrayList);
  }
  
  public f a() {
    return this.f;
  }
  
  public int r_() {
    return this.a;
  }
  
  public String b() {
    return this.f.d;
  }
  
  public synchronized void e() {
    this.l = true;
  }
  
  public synchronized j e(int paramInt) {
    List<g> list = c.get(this.b);
    if (list == null) {
      list = new ArrayList();
      try {
        this.j.a(this.e, this.f, list, this.g);
      } catch (Exception exception) {
        throw new RuntimeException(exception);
      } 
      c.put(this.b, list);
    } 
    return list.get(paramInt);
  }
  
  public Image f(int paramInt) {
    b b1;
    String str = this.b + "_" + this.b;
    synchronized (d) {
      Image image = d.get(str);
      if (image != null)
        return image; 
    } 
    Thread.yield();
    g g1 = (g)e(paramInt);
    double d = ReaderApplication.getRenderScale();
    int j = (int)Math.ceil(g1.c() * d);
    int k = (int)Math.ceil(g1.d() * d);
    BufferedImage bufferedImage = new BufferedImage(j, k, 2);
    Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
    RenderingHints renderingHints = graphics2D.getRenderingHints();
    c.a(renderingHints);
    graphics2D.setRenderingHints(renderingHints);
    if ("application/xhtml+xml".equals(this.f.b)) {
      f f1 = new f(graphics2D, g1.a());
    } else {
      b1 = new b(graphics2D, g1.a());
    } 
    b1.a(AffineTransform.getScaleInstance(d, d));
    g1.a((b)b1);
    WritableImage writableImage = SwingFXUtils.toFXImage(bufferedImage, null);
    synchronized (d) {
      if (!this.l)
        d.put(str, writableImage); 
      return (Image)writableImage;
    } 
  }
  
  public synchronized int s_() {
    return this.i;
  }
  
  public synchronized int a(String paramString) {
    return this.k.containsKey(paramString) ? ((Integer)this.k.get(paramString)).intValue() : 0;
  }
  
  public synchronized int b(h paramh) {
    e e = (e)paramh;
    return a(e.b);
  }
  
  public synchronized int a(int paramInt) {
    int j = 0;
    for (j = 0; j < s_(); j++) {
      g g1 = (g)e(j);
      e e = (e)g1.e();
      if (e.b > paramInt) {
        j--;
        break;
      } 
    } 
    if (j == -1) {
      j = 0;
    } else if (j == s_()) {
      j = s_() - 1;
    } 
    return j;
  }
  
  public s a(int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2, boolean paramBoolean) {
    return new f(b(), paramInt2, paramInt3, paramString1, paramString2, paramBoolean);
  }
  
  public boolean u_() {
    return (this.j.a(this.e) == 2);
  }
  
  public boolean t_() {
    return (this.j.b(this.e) == 1);
  }
  
  public int c() {
    return this.h;
  }
  
  public void v_() {}
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/epub/i.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */