package net.zamasoft.reader.book.epub;

import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import jp.cssj.homare.b.a.j;
import jp.cssj.sakae.c.a.e;
import jp.cssj.sakae.c.d.h;
import jp.cssj.sakae.c.e;
import net.zamasoft.reader.book.e;
import net.zamasoft.reader.book.g;
import net.zamasoft.reader.book.i;
import net.zamasoft.reader.book.l;

public class c extends e {
  private final double x;
  
  private final double y;
  
  private final URI z;
  
  private final int A;
  
  private final List<e> B = new ArrayList<>();
  
  private final List<l> C = new ArrayList<>();
  
  private final List<net.zamasoft.reader.book.c> D = new ArrayList<>();
  
  private final List<i> E = new ArrayList<>();
  
  private final List<b> F = new ArrayList<>();
  
  private final List<a> G = new ArrayList<>();
  
  protected c(e parame, double paramDouble1, double paramDouble2, URI paramURI, int paramInt) {
    super(parame);
    this.x = paramDouble1;
    this.y = paramDouble2;
    this.z = paramURI;
    this.A = paramInt;
  }
  
  public void a(Area paramArea, jp.cssj.sakae.c.b.b paramb, int paramInt) throws jp.cssj.sakae.c.c {
    paramArea = paramArea.createTransformedArea(o());
    this.B.add(new d(paramArea, paramb, paramInt));
  }
  
  public void a(byte paramByte, Area paramArea, h paramh) {
    paramArea = paramArea.createTransformedArea(o());
    a(new l(paramByte, paramArea, paramh));
  }
  
  public void a(l paraml) {
    this.C.add(paraml);
  }
  
  public void a(byte paramByte1, Area paramArea, int paramInt, byte paramByte2, double paramDouble1, double paramDouble2) {
    paramArea = paramArea.createTransformedArea(o());
    this.C.add(new k(paramByte1, paramArea, paramInt, paramByte2, paramDouble1, paramDouble2));
  }
  
  public void a(Area paramArea, URI paramURI, byte paramByte) {
    paramArea = paramArea.createTransformedArea(o());
    URI uRI = this.z.resolve(paramURI);
    g g = (g)((paramByte == 1) ? new g.c(uRI) : new g.b(uRI));
    this.D.add(new net.zamasoft.reader.book.c(paramArea, g));
  }
  
  public void a(Area paramArea, URI paramURI, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, jp.cssj.sakae.c.b.b paramb, int paramInt) {
    paramArea = paramArea.createTransformedArea(o());
    String str = "" + paramInt + "/" + paramInt;
    i i = i.a(paramArea, paramURI, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramb, str);
    this.E.add(i);
  }
  
  public void a(String paramString, Point2D paramPoint2D) {
    paramPoint2D = o().transform(paramPoint2D, paramPoint2D);
    this.F.add(new b(paramString, paramPoint2D));
  }
  
  public void a(String paramString, j paramj) {
    this.G.add(new a(paramString, paramj));
  }
  
  public e.a b() {
    Object[] arrayOfObject = this.m.toArray(new Object[this.m.size()]);
    net.zamasoft.reader.book.c[] arrayOfC = this.D.<net.zamasoft.reader.book.c>toArray(new net.zamasoft.reader.book.c[this.D.size()]);
    l[] arrayOfL = this.C.<l>toArray(new l[this.C.size()]);
    d[] arrayOfD = this.B.<d>toArray(new d[this.B.size()]);
    b[] arrayOfB = this.F.<b>toArray(new b[this.F.size()]);
    i[] arrayOfI = this.E.<i>toArray(new i[this.E.size()]);
    a[] arrayOfA = this.G.<a>toArray(new a[this.G.size()]);
    return new g(this.A, this.l, this.x, this.y, this.z, arrayOfObject, arrayOfC, arrayOfB, arrayOfL, arrayOfD, arrayOfI, arrayOfA);
  }
  
  public static class a {
    public final String a;
    
    public final j b;
    
    a(String param1String, j param1j) {
      if (param1String == null)
        throw new NullPointerException(); 
      this.a = param1String;
      this.b = param1j;
    }
  }
  
  public static class b {
    public final String a;
    
    public final Point2D b;
    
    b(String param1String, Point2D param1Point2D) {
      this.a = param1String;
      this.b = param1Point2D;
    }
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/epub/c.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */