package net.zamasoft.reader.book.epub.media;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.geometry.Dimension2D;
import jp.cssj.e.e.d;
import jp.cssj.homare.css.h;
import jp.cssj.homare.ua.f;
import jp.cssj.homare.ua.m;
import net.zamasoft.reader.book.epub.n;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class b extends DefaultHandler implements h {
  private String a = null;
  
  private List<String> b = new ArrayList<>();
  
  private boolean c = false;
  
  private static final List<String> d = Arrays.asList(new String[] { "moov", "mdia", "trak" });
  
  public jp.cssj.sakae.c.b.b a(m paramm) throws IOException {
    n n = (n)paramm;
    f f = n.c();
    jp.cssj.sakae.c.b.b b1 = null;
    a a = null;
    if (this.c) {
      if (this.a != null)
        try {
          URI uRI = d.a(f.c(), f.a(), this.a);
          jp.cssj.e.b b2 = n.b(uRI);
          try {
            b1 = n.c(b2);
          } finally {
            n.a(b2);
          } 
        } catch (Exception exception) {} 
      for (String str : this.b) {
        try {
          double d1;
          double d2;
          URI uRI = d.a(f.c(), f.a(), str);
          jp.cssj.e.b b2 = n.b(uRI);
          try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(b2.h());
            try {
              bufferedInputStream.mark(10);
              if (bufferedInputStream.read() != 0) {
                bufferedInputStream.close();
                n.a(b2);
                continue;
              } 
              if (bufferedInputStream.read() != 0) {
                bufferedInputStream.close();
                n.a(b2);
                continue;
              } 
              if (bufferedInputStream.read() != 0) {
                bufferedInputStream.close();
                n.a(b2);
                continue;
              } 
              if (bufferedInputStream.read() == -1) {
                bufferedInputStream.close();
                n.a(b2);
                continue;
              } 
              if (bufferedInputStream.read() != 102) {
                bufferedInputStream.close();
                n.a(b2);
                continue;
              } 
              if (bufferedInputStream.read() != 116) {
                bufferedInputStream.close();
                n.a(b2);
                continue;
              } 
              if (bufferedInputStream.read() != 121) {
                bufferedInputStream.close();
                n.a(b2);
                continue;
              } 
              if (bufferedInputStream.read() != 112) {
                bufferedInputStream.close();
                n.a(b2);
                continue;
              } 
              bufferedInputStream.reset();
              Dimension2D dimension2D = a(bufferedInputStream);
              d1 = dimension2D.getWidth() * 72.0D / 96.0D;
              d2 = dimension2D.getHeight() * 72.0D / 96.0D;
            } finally {
              bufferedInputStream.close();
            } 
          } finally {
            n.a(b2);
          } 
          a = new a(uRI, d1, d2, this.c, b1);
          break;
        } catch (Exception exception) {}
      } 
    } 
    if (a == null)
      for (String str1 : this.b) {
        String str2 = str1.toLowerCase();
        if (str2.endsWith(".aif") || str2.endsWith(".aiff") || str2.endsWith(".fxm") || str2.endsWith(".flv") || str2.endsWith(".m3u8") || str2.endsWith(".mp3") || str2.endsWith(".wav"))
          try {
            URI uRI = d.a(f.c(), f.a(), str1);
            jp.cssj.e.b b2 = n.b(uRI);
            try {
              if (!b2.f()) {
                n.a(b2);
                continue;
              } 
              a = new a(uRI, 320.0D, 260.0D, this.c, b1);
            } finally {
              n.a(b2);
            } 
            break;
          } catch (URISyntaxException uRISyntaxException) {} 
      }  
    return a;
  }
  
  private Dimension2D a(InputStream paramInputStream) throws IOException {
    return a(paramInputStream, (byte[])null);
  }
  
  private Dimension2D a(InputStream paramInputStream, byte[] paramArrayOfbyte) throws IOException {
    byte[] arrayOfByte = new byte[8];
    while (paramInputStream.available() > 0) {
      b(paramInputStream, arrayOfByte);
      long l = a(arrayOfByte, 0);
      String str = new String(arrayOfByte, 4, 4, "ISO-8859-1");
      if (d.contains(str)) {
        Dimension2D dimension2D = a(paramInputStream, paramArrayOfbyte);
        if (dimension2D != null)
          return dimension2D; 
        continue;
      } 
      if (str.equals("tkhd")) {
        paramArrayOfbyte = new byte[(int)(l - 8L)];
        b(paramInputStream, paramArrayOfbyte);
        continue;
      } 
      if (str.equals("hdlr")) {
        byte[] arrayOfByte1 = new byte[(int)(l - 8L)];
        b(paramInputStream, arrayOfByte1);
        if (arrayOfByte1[8] == 118 && arrayOfByte1[9] == 105 && arrayOfByte1[10] == 100 && arrayOfByte1[11] == 101)
          return new Dimension2D(b(paramArrayOfbyte, paramArrayOfbyte.length - 8), b(paramArrayOfbyte, paramArrayOfbyte.length - 4)); 
        continue;
      } 
      for (l -= 8L; l > 0L; l -= l1) {
        long l1 = paramInputStream.skip(l);
        if (l1 <= 0L)
          throw new EOFException(); 
      } 
    } 
    return null;
  }
  
  private static void b(InputStream paramInputStream, byte[] paramArrayOfbyte) throws IOException {
    for (int i = paramArrayOfbyte.length; i > 0; i -= paramInputStream.read(paramArrayOfbyte, paramArrayOfbyte.length - i, i));
  }
  
  private static long a(byte[] paramArrayOfbyte, int paramInt) {
    long l = 0L;
    l |= (paramArrayOfbyte[paramInt + 0] << 24) & 0xFF000000L;
    l |= (paramArrayOfbyte[paramInt + 1] << 16) & 0xFF0000L;
    l |= (paramArrayOfbyte[paramInt + 2] << 8) & 0xFF00L;
    l |= paramArrayOfbyte[paramInt + 3] & 0xFFL;
    return l;
  }
  
  private static double b(byte[] paramArrayOfbyte, int paramInt) {
    return a(paramArrayOfbyte, paramInt) / 65536.0D;
  }
  
  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes) throws SAXException {
    if (MediaInlineObjectFactory.a.a(paramString1, paramString2) || MediaInlineObjectFactory.b.a(paramString1, paramString2) || MediaInlineObjectFactory.c.a(paramString1, paramString2)) {
      String str = paramAttributes.getValue("src");
      if (str != null)
        this.b.add(str); 
      if (MediaInlineObjectFactory.a.a(paramString1, paramString2)) {
        this.c = true;
        this.a = paramAttributes.getValue("poster");
      } 
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/epub/media/b.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */