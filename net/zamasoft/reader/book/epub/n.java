package net.zamasoft.reader.book.epub;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jp.cssj.e.b;
import jp.cssj.homare.b.a.c.C;
import jp.cssj.homare.b.a.f;
import jp.cssj.homare.b.a.h;
import jp.cssj.homare.b.a.j;
import jp.cssj.homare.b.e.b;
import jp.cssj.homare.b.f.e;
import jp.cssj.homare.b.g.a;
import jp.cssj.homare.css.a;
import jp.cssj.homare.css.f.b.d;
import jp.cssj.homare.impl.ua.a.a;
import jp.cssj.homare.impl.ua.b;
import jp.cssj.homare.ua.a.B;
import jp.cssj.homare.ua.m;
import jp.cssj.sakae.b.a.b;
import jp.cssj.sakae.b.b.b;
import jp.cssj.sakae.c.b;
import jp.cssj.sakae.c.b.b;
import jp.cssj.sakae.c.d.b.a.a;
import jp.cssj.sakae.c.d.h;
import net.zamasoft.reader.book.a;
import net.zamasoft.reader.book.epub.media.a;
import net.zamasoft.reader.book.l;
import net.zamasoft.reader.book.o;

public class n extends a {
  private final b c;
  
  private final List<g> t;
  
  private final boolean u;
  
  private final boolean v;
  
  private int w;
  
  private final Set<String> x = new HashSet<>();
  
  public n(b paramb, List<g> paramList, int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
    this.c = paramb;
    this.t = paramList;
    this.w = paramInt;
    this.u = paramBoolean1;
    this.v = paramBoolean2;
  }
  
  public b w() {
    return this.c;
  }
  
  public b c(b paramb) throws IOException {
    return (b)new o(this, paramb);
  }
  
  public b s() {
    Point2D.Double double_ = new Point2D.Double(this.a, this.b);
    double d1 = B.U.a((m)this);
    double d2 = d1 / 72.0D;
    AffineTransform affineTransform = AffineTransform.getScaleInstance(d2, d2);
    affineTransform.transform(double_, double_);
    int i = (int)double_.getX();
    int j = (int)double_.getY();
    c c = new c(q(), i, j, c().a(), this.w);
    c.a(affineTransform);
    if (this.u)
      switch (this.w) {
        case 1:
          this.w = 2;
          break;
        case 2:
          this.w = 1;
          break;
      }  
    return (b)c;
  }
  
  public void a(b paramb) throws IOException {
    c c = (c)paramb;
    g g = (g)c.b();
    this.t.add(g);
  }
  
  public d j() {
    return d.c;
  }
  
  public a b(b paramb) {
    c c = (c)paramb;
    b b1 = new b(this, (m)this, c) {
        public void a(AffineTransform param1AffineTransform, j param1j, double param1Double1, double param1Double2) {
          super.a(param1AffineTransform, param1j, param1Double1, param1Double2);
          byte b1 = param1j.a();
          if (b1 == 6)
            a(param1AffineTransform, (f)param1j, param1Double1, param1Double2); 
          if (b1 == 3 || b1 == 4) {
            byte b2 = 1;
            a a = (param1j.b()).al;
            if (a != null)
              if ("rb".equalsIgnoreCase(a.C)) {
                b2 = 2;
              } else if ("rt".equalsIgnoreCase(a.C)) {
                b2 = 3;
              }  
            h h = (h)param1j;
            a(b2, param1AffineTransform, h, param1Double1, param1Double2);
          } 
          if (b1 == 5) {
            a a = (param1j.b()).al;
            if (a != null && a.H != null && "footnote".equalsIgnoreCase(a.H.getValue("http://www.idpf.org/2007/ops", "type"))) {
              String str = (param1j.b()).al.H.getValue("id");
              if (str != null)
                this.b.a(str, param1j); 
            } 
          } 
        }
        
        private void a(AffineTransform param1AffineTransform, f param1f, double param1Double1, double param1Double2) {
          b b1 = param1f.m();
          param1Double1 += b1.b();
          param1Double2 += b1.a();
          Area area = new Area(new Rectangle2D.Double(param1Double1, param1Double2, param1f.s(), param1f.t()));
          area = area.createTransformedArea(param1AffineTransform);
          C c1 = param1f.d_();
          if (c1.a instanceof a) {
            a a = (a)c1.a;
            if (a.a != null) {
              a a1 = c1.al;
              String str1 = a1.H.getValue("autoplay");
              String str2 = a1.H.getValue("loop");
              String str3 = a1.H.getValue("controls");
              this.b.a(area, a.a, (str1 != null), (str2 != null), (str3 != null), a.b, a.c, (param1f.b()).al.J);
            } 
            return;
          } 
          if ((param1f.b()).al.a("gaiji")) {
            int i = (int)(c1.M.d() * 255.0F);
            int j = (int)(c1.M.e() * 255.0F);
            int k = (int)(c1.M.f() * 255.0F);
            b b2 = c1.a;
            int m = (int)b2.a();
            int i1 = (int)b2.b();
            BufferedImage bufferedImage = new BufferedImage(m, i1, 2);
            b b3 = new b((Graphics2D)bufferedImage.getGraphics(), null);
            b2.a((b)b3);
            WritableRaster writableRaster = bufferedImage.getRaster();
            ColorModel colorModel = bufferedImage.getColorModel();
            for (byte b4 = 0; b4 < i1; b4++) {
              for (byte b5 = 0; b5 < m; b5++) {
                int i2 = colorModel.getRGB(writableRaster.getDataElements(b5, b4, null));
                int i3 = i2 >>> 24;
                int i4 = i2 >> 16 & 0xFF;
                int i5 = i2 >> 8 & 0xFF;
                int i6 = i2 & 0xFF;
                if (this.c.v) {
                  i4 = i - i * i6 / 255;
                  i5 = j - j * i6 / 255;
                  i6 = k - k * i6 / 255;
                } else {
                  i4 = i + (255 - i) * i6 / 255;
                  i5 = j + (255 - j) * i6 / 255;
                  i6 = k + (255 - k) * i6 / 255;
                } 
                i2 = i3 << 24 | i4 << 16 | i5 << 8 | i6;
                Object object = colorModel.getDataElements(i2, null);
                writableRaster.setDataElements(b5, b4, object);
              } 
            } 
            c1.a = (b)new b(bufferedImage, b2.c());
          } 
          String str = c1.al.H.getValue("alt");
          if (str == null)
            str = c1.al.H.getValue("title"); 
          this.b.a(area, c1.a, c1.al.J);
          if (str != null && str.length() > 0) {
            area = area.createTransformedArea(this.b.o());
            this.b.a((l)new a(area, str, c1.al.J));
          } 
        }
        
        private void a(byte param1Byte, AffineTransform param1AffineTransform, h param1h, double param1Double1, double param1Double2) {
          boolean bool = e.a((param1h.g()).D);
          for (byte b1 = 0; b1 < param1h.n(); b1++) {
            h h1;
            h.a a1;
            a a;
            Area area;
            char c1;
            switch (param1h.b(b1)) {
              case 1:
                h1 = (h)param1h.a(b1);
                if (h1.e() == -1)
                  break; 
                if (h1.b().a() == 3) {
                  area = new Area(new Rectangle2D.Double(param1Double1, param1Double2, param1h.p(), h1.c()));
                } else {
                  area = new Area(new Rectangle2D.Double(param1Double1, param1Double2, h1.c(), param1h.q()));
                } 
                area = area.createTransformedArea(param1AffineTransform);
                this.b.a(param1Byte, area, h1);
                if (bool) {
                  param1Double2 += h1.c();
                  break;
                } 
                param1Double1 += h1.c();
                break;
              case 3:
                a1 = (h.a)param1h.a(b1);
                if (bool) {
                  param1Double2 += a1.a.q();
                  break;
                } 
                param1Double1 += a1.a.p();
                break;
              case 4:
                break;
              case 2:
                a = (a)param1h.a(b1);
                c1 = a.e();
                if (c1 == ' ' || c1 == '\n' || c1 == '\t') {
                  double d2 = a.c();
                  double d1;
                  Area area1 = new Area(new Rectangle2D.Double(param1Double1, param1Double2, d1 = param1h.p(), d2));
                  area1 = new Area(new Rectangle2D.Double(param1Double1, param1Double2, d2, d1 = param1h.q()));
                  area1 = area1.createTransformedArea(param1AffineTransform);
                  this.b.a(param1Byte, area1, a.b(), bool ? 3 : 2, d2, d1);
                } 
                if (bool) {
                  param1Double2 += a.c();
                  break;
                } 
                param1Double1 += a.c();
                break;
              default:
                throw new IllegalStateException();
            } 
          } 
        }
        
        protected void a(Shape param1Shape, URI param1URI, a param1a) {
          this.b.a(new Area(param1Shape), param1URI, (param1a != null && "noteref".equalsIgnoreCase(param1a.H.getValue("http://www.idpf.org/2007/ops", "type"))) ? 3 : 1);
        }
        
        protected void a(String param1String, Point2D param1Point2D) {
          if (this.c.x.contains(param1String))
            return; 
          this.c.x.add(param1String);
          this.b.a(param1String, param1Point2D);
        }
        
        protected void b(String param1String, Point2D param1Point2D) {}
        
        protected void a() {}
      };
    b1.c(true);
    b1.b(true);
    return (a)b1;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/epub/n.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */