package net.zamasoft.reader.book;

import java.util.Properties;
import net.zamasoft.reader.util.g;

public class p {
  public static final int a = 0;
  
  public static final int b = 1;
  
  public static final int c = 2;
  
  public static final int d = 3;
  
  public static final int e = 0;
  
  public static final int f = 1;
  
  public static final int g = 2;
  
  public static final int h = 3;
  
  public static final int i = 0;
  
  public static final int j = 1;
  
  public static final int k = 0;
  
  public static final int l = 1;
  
  public static final int m = 2;
  
  public int n = 0;
  
  public double o = 1.0D;
  
  public int p = 0;
  
  public boolean q = true;
  
  public String r = null;
  
  public double s = 0.0D;
  
  public double t = 0.0D;
  
  public int u = 0;
  
  public int v = 0;
  
  public boolean w = false;
  
  public static final int x = 0;
  
  public static final int y = 1;
  
  public int z = 0;
  
  public void a(Properties paramProperties) {
    paramProperties.setProperty("spread", String.valueOf(this.n));
    paramProperties.setProperty("textSize", String.valueOf(this.o));
    paramProperties.setProperty("writingMode", String.valueOf(this.p));
    paramProperties.setProperty("ruby", String.valueOf(this.q));
    if (this.r != null)
      paramProperties.setProperty("fontFamily", String.valueOf(this.r)); 
    paramProperties.setProperty("lineHeight", String.valueOf(this.s));
    paramProperties.setProperty("letterSpacing", String.valueOf(this.t));
    paramProperties.setProperty("backgroundColor", String.valueOf(this.v));
    paramProperties.setProperty("invert", String.valueOf(this.w));
    paramProperties.setProperty("flip", String.valueOf(this.u));
    paramProperties.setProperty("searchEngine", String.valueOf(this.z));
    paramProperties.setProperty("speechRate", String.valueOf(g.a().b()));
  }
  
  public void b(Properties paramProperties) {
    try {
      this.n = Integer.parseInt(paramProperties.getProperty("spread"));
    } catch (Exception exception) {
      this.n = Boolean.parseBoolean(paramProperties.getProperty("spread")) ? 0 : 2;
    } 
    this.o = Double.parseDouble(paramProperties.getProperty("textSize"));
    this.p = Integer.parseInt(paramProperties.getProperty("writingMode"));
    this.q = Boolean.parseBoolean(paramProperties.getProperty("ruby"));
    this.r = paramProperties.getProperty("fontFamily");
    this.s = Double.parseDouble(paramProperties.getProperty("lineHeight"));
    this.t = Double.parseDouble(paramProperties.getProperty("letterSpacing"));
    this.v = Integer.parseInt(paramProperties.getProperty("backgroundColor"));
    this.w = Boolean.parseBoolean(paramProperties.getProperty("invert"));
    this.u = Integer.parseInt(paramProperties.getProperty("flip"));
    this.z = Integer.parseInt(paramProperties.getProperty("searchEngine"));
    g.a().a(Double.parseDouble(paramProperties.getProperty("speechRate", "1")));
  }
  
  public boolean equals(Object paramObject) {
    if (!(paramObject instanceof p))
      return false; 
    p p1 = (p)paramObject;
    return (p1.n == this.n && p1.o == this.o && p1.p == this.p && p1.q == this.q && p1.r == this.r && p1.s == this.s && p1.t == this.t && p1.v == this.v && p1.w == this.w && p1.u == this.u && p1.z == this.z);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/p.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */