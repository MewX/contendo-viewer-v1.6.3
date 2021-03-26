package net.zamasoft.reader.book.epub;

import a.a.a.a;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import jp.cssj.d.a.a;
import net.zamasoft.reader.book.b;

public class o extends a {
  private final b d;
  
  private final Map<String, URI> e = new HashMap<>();
  
  public o(b paramb) {
    this.d = paramb;
  }
  
  public void a(URI paramURI) {
    this.e.put(paramURI.toString(), paramURI);
  }
  
  public a.k a(a.i parami) {
    a.k k;
    String str = parami.e().substring(1);
    URI uRI = this.e.get(str);
    Map map = parami.d();
    try {
      String str1 = Integer.toHexString(str.hashCode());
      long l1 = 0L;
      long l2 = -1L;
      String str2 = (String)map.get("range");
      if (str2 != null && str2.startsWith("bytes=")) {
        str2 = str2.substring("bytes=".length());
        int j = str2.indexOf('-');
        try {
          if (j > 0) {
            l1 = Long.parseLong(str2.substring(0, j));
            l2 = Long.parseLong(str2.substring(j + 1));
          } 
        } catch (NumberFormatException numberFormatException) {}
      } 
      b b1 = (b)this.d;
      String str3 = uRI.getPath().substring(1);
      String str4 = (b1.a(str3)).b;
      a a1 = b1.y();
      FilterInputStream filterInputStream = new FilterInputStream(this, a1.b(str3), a1) {
          public void close() throws IOException {
            super.close();
            this.a.a();
          }
        };
      long l3 = filterInputStream.available();
      if (str2 != null && l1 >= 0L) {
        if (l1 >= l3) {
          k = a(a.k.a.n, "text/plain", "");
          k.a("Content-Range", "bytes 0-0/" + l3);
          k.a("ETag", str1);
        } else {
          if (l2 < 0L)
            l2 = l3 - 1L; 
          long l4 = l2 - l1 + 1L;
          if (l4 < 0L)
            l4 = 0L; 
          long l5 = l4;
          long l6;
          for (l6 = l1; l6 > 0L; l6 -= l) {
            long l = filterInputStream.skip(l6);
            if (l <= 0L)
              break; 
          } 
          k = a(a.k.a.f, str4, filterInputStream);
          k.a("Content-Length", "" + l5);
          k.a("Content-Range", "bytes " + l1 + "-" + l2 + "/" + l3);
          k.a("ETag", str1);
        } 
      } else if (str1.equals(map.get("if-none-match"))) {
        k = a(a.k.a.h, str4, "");
      } else {
        k = a(a.k.a.b, str4, filterInputStream);
        k.a("Content-Length", "" + l3);
        k.a("ETag", str1);
      } 
    } catch (IOException iOException) {
      k = a(a.k.a.k, "text/plain", "FORBIDDEN: Reading file failed.");
    } 
    System.out.println(str + "/" + str);
    return k;
  }
  
  private a.k a(a.k.a parama, String paramString, InputStream paramInputStream) {
    a.k k = new a.k(parama, paramString, paramInputStream);
    k.a("Accept-Ranges", "bytes");
    return k;
  }
  
  private a.k a(a.k.a parama, String paramString1, String paramString2) {
    a.k k = new a.k(parama, paramString1, paramString2);
    k.a("Accept-Ranges", "bytes");
    return k;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/epub/o.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */