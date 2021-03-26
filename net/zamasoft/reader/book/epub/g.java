package net.zamasoft.reader.book.epub;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import jp.cssj.sakae.c.a.e;
import jp.cssj.sakae.c.e;
import net.zamasoft.reader.book.c;
import net.zamasoft.reader.book.e;
import net.zamasoft.reader.book.h;
import net.zamasoft.reader.book.i;
import net.zamasoft.reader.book.j;
import net.zamasoft.reader.book.l;

public class g extends e.a implements j {
  private final int c;
  
  private final e d;
  
  private final double e;
  
  private final double f;
  
  private final h g;
  
  private final c[] h;
  
  private final c.b[] i;
  
  private final d[] j;
  
  private final l[] k;
  
  private final l[] l;
  
  private final i[] m;
  
  private final c.a[] n;
  
  private String o = null;
  
  private static final Comparator<l> p;
  
  protected g(int paramInt, e parame, double paramDouble1, double paramDouble2, URI paramURI, Object[] paramArrayOfObject, c[] paramArrayOfc, c.b[] paramArrayOfb, l[] paramArrayOfl, d[] paramArrayOfd, i[] paramArrayOfi, c.a[] paramArrayOfa) {
    super(paramArrayOfObject);
    if (!b && paramInt == 0)
      throw new AssertionError(); 
    this.c = paramInt;
    this.d = parame;
    this.e = paramDouble1;
    this.f = paramDouble2;
    this.h = paramArrayOfc;
    this.i = paramArrayOfb;
    String str = paramURI.getPath().substring(1);
    this.j = paramArrayOfd;
    this.m = paramArrayOfi;
    this.n = paramArrayOfa;
    ArrayList<l> arrayList = new ArrayList();
    for (l l1 : paramArrayOfl) {
      if (l1.b == 1 || l1.b == 2)
        arrayList.add(l1); 
    } 
    this.k = arrayList.<l>toArray(new l[arrayList.size()]);
    if (this.k.length >= 1) {
      this.g = new e(str, paramArrayOfl[0].e());
      Arrays.sort(this.k, p);
    } else {
      this.g = new e(str, 0);
    } 
    arrayList = new ArrayList<>();
    for (l l1 : paramArrayOfl) {
      if (l1.b == 1 || l1.b == 3 || l1.b == 4)
        arrayList.add(l1); 
    } 
    this.l = arrayList.<l>toArray(new l[arrayList.size()]);
    Arrays.sort(this.l, p);
  }
  
  public e a() {
    return this.d;
  }
  
  public h e() {
    return this.g;
  }
  
  public c b(double paramDouble1, double paramDouble2) {
    for (c c1 : this.h) {
      if (c1.a().contains(paramDouble1, paramDouble2))
        return c1; 
    } 
    return null;
  }
  
  public c.b a(int paramInt) {
    return this.i[paramInt];
  }
  
  public int k() {
    return this.i.length;
  }
  
  public e b(int paramInt) {
    return this.j[paramInt];
  }
  
  public int f() {
    return this.j.length;
  }
  
  public int b() {
    return this.c;
  }
  
  public double c() {
    return this.e;
  }
  
  public double d() {
    return this.f;
  }
  
  public int a(double paramDouble1, double paramDouble2) {
    for (byte b1 = 0; b1 < this.j.length; b1++) {
      d d1 = this.j[b1];
      if (d1.a().contains(paramDouble1, paramDouble2))
        return b1; 
    } 
    return -1;
  }
  
  public int g() {
    return this.k.length;
  }
  
  public int d(double paramDouble1, double paramDouble2) {
    for (byte b1 = 0; b1 < this.k.length; b1++) {
      l l1 = this.k[b1];
      if (l1.a.contains(paramDouble1, paramDouble2))
        return b1; 
    } 
    return -1;
  }
  
  public l c(int paramInt) {
    return this.k[paramInt];
  }
  
  public l c(double paramDouble1, double paramDouble2) {
    int k = d(paramDouble1, paramDouble2);
    return (k == -1) ? null : this.k[k];
  }
  
  public String h() {
    if (this.o != null)
      return this.o; 
    StringBuffer stringBuffer = new StringBuffer();
    for (byte b1 = 0; b1 < g(); b1++) {
      l l1 = c(b1);
      stringBuffer.append(l1.d(), 0, l1.f());
    } 
    return this.o = stringBuffer.toString();
  }
  
  public l d(int paramInt) {
    return this.l[paramInt];
  }
  
  public int i() {
    return this.l.length;
  }
  
  public int e(double paramDouble1, double paramDouble2) {
    for (byte b1 = 0; b1 < this.l.length; b1++) {
      l l1 = this.l[b1];
      if (l1.a.contains(paramDouble1, paramDouble2))
        return b1; 
    } 
    return -1;
  }
  
  public i e(int paramInt) {
    return this.m[paramInt];
  }
  
  public int j() {
    return this.m.length;
  }
  
  public c.a f(int paramInt) {
    return this.n[paramInt];
  }
  
  public int l() {
    return this.n.length;
  }
  
  public String toString() {
    return h();
  }
  
  static {
    p = new Comparator<l>() {
        public int a(l param1l1, l param1l2) {
          return (param1l1.e() < param1l2.e()) ? -1 : ((param1l1.e() == param1l2.e()) ? 0 : 1);
        }
      };
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/epub/g.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */