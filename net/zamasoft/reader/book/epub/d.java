package net.zamasoft.reader.book.epub;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import jp.cssj.sakae.a.h;
import jp.cssj.sakae.b.a.b;
import jp.cssj.sakae.b.b.a;
import jp.cssj.sakae.c.a.e;
import jp.cssj.sakae.c.b;
import jp.cssj.sakae.c.b.b;
import jp.cssj.sakae.c.b.c;
import jp.cssj.sakae.d.g;
import jp.cssj.sakae.pdf.c.c;
import net.zamasoft.reader.book.e;
import net.zamasoft.reader.util.c;

public class d implements e {
  public final Area a;
  
  public final b b;
  
  public final int c;
  
  d(Area paramArea, b paramb, int paramInt) {
    this.a = paramArea;
    this.b = paramb;
    this.c = paramInt;
  }
  
  public Area a() {
    return this.a;
  }
  
  public BufferedImage a(int paramInt) {
    if (this.b instanceof a)
      return ((a)this.b).d(); 
    double d1 = 800.0D;
    if (this.b instanceof net.zamasoft.reader.book.o) {
      d1 = this.b.a();
    } else if (this.b instanceof c) {
      c c = (c)this.b;
      b b2 = c.d();
      if (b2 instanceof g) {
        g g = (g)b2;
        d1 = g.d().getBounds().getWidth();
      } 
    } 
    BufferedImage bufferedImage = new BufferedImage((int)d1, (int)(d1 * this.b.b() / this.b.a()), 2);
    Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
    RenderingHints renderingHints = graphics2D.getRenderingHints();
    c.a(renderingHints);
    graphics2D.setRenderingHints(renderingHints);
    b b1 = new b(graphics2D, (e)new c((h)c.a()));
    b1.a(AffineTransform.getScaleInstance(d1 / this.b.a(), d1 / this.b.a()));
    this.b.a((b)b1);
    return bufferedImage;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/epub/d.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */