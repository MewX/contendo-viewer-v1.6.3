package jp.cssj.sakae.pdf;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import jp.cssj.e.b;
import jp.cssj.sakae.c.a.e;
import jp.cssj.sakae.c.b.b;
import jp.cssj.sakae.pdf.d.b;
import jp.cssj.sakae.pdf.f.b;

public interface j {
  public static final double a = 3.0D;
  
  public static final double b = 3.0D;
  
  public static final double c = 14400.0D;
  
  public static final double d = 14400.0D;
  
  b a();
  
  e b();
  
  b a(b paramb) throws IOException;
  
  b a(BufferedImage paramBufferedImage) throws IOException;
  
  OutputStream a(String paramString, a parama) throws IOException;
  
  g c() throws IOException;
  
  b a(double paramDouble1, double paramDouble2) throws IOException;
  
  f a(double paramDouble1, double paramDouble2, double paramDouble3, AffineTransform paramAffineTransform) throws IOException;
  
  g a(double paramDouble, AffineTransform paramAffineTransform) throws IOException;
  
  i b(double paramDouble1, double paramDouble2) throws IOException;
  
  Object a(Object paramObject);
  
  void a(Object paramObject1, Object paramObject2);
  
  void d() throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/j.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */