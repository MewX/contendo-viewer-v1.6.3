package net.zamasoft.reader.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import jp.cssj.e.d;
import jp.cssj.e.e.a;
import jp.cssj.e.e.e;

public class a extends a {
  private static final Logger a = Logger.getLogger(a.class.getName());
  
  private final jp.cssj.d.a.a c;
  
  private final String d;
  
  private final String e;
  
  private String f = null;
  
  public a(jp.cssj.d.a.a parama, String paramString1, URI paramURI, String paramString2, String paramString3) {
    super(paramURI);
    if (parama == null)
      throw new NullPointerException(); 
    if (paramString1 == null) {
      paramString1 = paramURI.getPath().substring(1);
      try {
        paramString1 = URLDecoder.decode(paramString1, "UTF-8");
      } catch (Exception exception) {
        a.log(Level.WARNING, "URIをデコードできません。", exception);
      } 
    } 
    this.c = parama;
    this.d = paramString1;
    this.f = paramString2;
    this.e = paramString3;
  }
  
  public a(jp.cssj.d.a.a parama, URI paramURI, String paramString1, String paramString2) {
    this(parama, null, paramURI, paramString1, paramString2);
  }
  
  public a(jp.cssj.d.a.a parama, URI paramURI, String paramString) {
    this(parama, paramURI, paramString, (String)null);
  }
  
  public a(jp.cssj.d.a.a parama, URI paramURI) throws IOException {
    this(parama, paramURI, (String)null);
  }
  
  public a(jp.cssj.d.a.a parama, String paramString1, URI paramURI, String paramString2) {
    this(parama, paramString1, paramURI, paramString2, null);
  }
  
  public a(jp.cssj.d.a.a parama, String paramString, URI paramURI) {
    this(parama, paramString, paramURI, null, null);
  }
  
  public String c() throws IOException {
    if (this.f == null) {
      int i = this.d.indexOf('.');
      if (i != -1) {
        String str = this.d.substring(i, this.d.length());
        if (str.equalsIgnoreCase(".html") || str.equalsIgnoreCase(".htm")) {
          this.f = "text/html";
        } else if (str.equalsIgnoreCase(".xml") || str.equalsIgnoreCase(".xhtml") || str.equalsIgnoreCase(".xht")) {
          this.f = "text/xml";
        } 
      } 
    } 
    return this.f;
  }
  
  public String a() {
    return this.e;
  }
  
  public boolean f() throws IOException {
    return this.c.a(this.d);
  }
  
  public boolean k() throws IOException {
    return false;
  }
  
  public boolean g() throws IOException {
    return true;
  }
  
  public boolean i() throws IOException {
    return (this.e != null);
  }
  
  public InputStream h() throws IOException {
    return this.c.b(this.d);
  }
  
  public Reader j() throws IOException {
    if (!i())
      throw new UnsupportedOperationException(); 
    return new InputStreamReader(h(), this.e);
  }
  
  public File l() {
    throw new UnsupportedOperationException();
  }
  
  public long b() throws IOException {
    return f() ? -1L : 0L;
  }
  
  public d m() throws IOException {
    return (d)e.d;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/util/a.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */