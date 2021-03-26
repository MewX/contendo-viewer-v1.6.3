package net.zamasoft.reader.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import jp.cssj.sakae.b.a.b;
import jp.cssj.sakae.c.a.e;
import jp.cssj.sakae.c.b;
import jp.cssj.sakae.c.b.b;
import jp.cssj.sakae.c.b.c;
import jp.cssj.sakae.c.c;
import jp.cssj.sakae.d.g;

public class f extends b {
  public f(Graphics2D paramGraphics2D, e parame) {
    super(paramGraphics2D, parame);
  }
  
  public void a(b paramb) throws c {
    AffineTransform affineTransform = o();
    if (affineTransform.getShearX() == 0.0D && affineTransform.getShearY() == 0.0D) {
      double d = 800.0D;
      if (paramb instanceof net.zamasoft.reader.book.o) {
        d = paramb.a();
      } else if (paramb instanceof c) {
        c c = (c)paramb;
        b b2 = c.d();
        if (b2 instanceof g) {
          g g = (g)b2;
          d = g.d().getBounds().getWidth();
        } 
      } 
      int i = (int)Math.ceil(paramb.a() * affineTransform.getScaleX());
      int j = (int)Math.ceil(paramb.b() * affineTransform.getScaleY());
      BufferedImage bufferedImage = new BufferedImage((int)Math.ceil(d), (int)Math.ceil(d * paramb.b() / paramb.a()), 2);
      b b1 = new b((Graphics2D)bufferedImage.getGraphics(), this.k);
      b1.a(AffineTransform.getScaleInstance(d / paramb.a(), d / paramb.a()));
      paramb.a((b)b1);
      Image image = bufferedImage.getScaledInstance(i, j, 4);
      bufferedImage.flush();
      Graphics2D graphics2D = b();
      graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
      graphics2D.drawImage(image, 0, 0, (int)paramb.a(), (int)paramb.b(), null);
      graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
      image.flush();
      return;
    } 
    super.a(paramb);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/util/f.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */