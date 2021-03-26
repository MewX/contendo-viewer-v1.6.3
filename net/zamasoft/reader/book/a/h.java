package net.zamasoft.reader.book.a;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import jp.cssj.homare.b.f.c;
import net.zamasoft.reader.ReaderApplication;
import net.zamasoft.reader.book.c;
import net.zamasoft.reader.book.e;
import net.zamasoft.reader.book.g;
import net.zamasoft.reader.book.i;
import net.zamasoft.reader.book.j;
import net.zamasoft.reader.book.l;
import org.apache.commons.io.output.NullWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.interactive.action.PDAction;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionGoTo;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionURI;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDNamedDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class h extends f implements e, j {
  private static Logger e = Logger.getLogger(h.class.getName());
  
  private double f;
  
  private double g;
  
  protected final int b;
  
  protected final d c;
  
  private c[] h = null;
  
  private l[] i = null;
  
  protected String d = null;
  
  protected h(d paramd, int paramInt) {
    super(paramInt);
    this.c = paramd;
    switch ((this.c.l_()).n) {
      case 0:
        if (paramInt == 0) {
          this.b = 0;
        } else if (this.c.l() == 1) {
          this.b = (paramInt == 0) ? 1 : ((paramInt % 2 == 1) ? 1 : 2);
        } else {
          this.b = (paramInt == 0) ? 2 : ((paramInt % 2 == 1) ? 2 : 1);
        } 
        return;
      case 1:
        if (this.c.l() == 1) {
          this.b = (paramInt % 2 == 1) ? 2 : 1;
        } else {
          this.b = (paramInt % 2 == 1) ? 1 : 2;
        } 
        return;
      case 2:
        this.b = 0;
        return;
    } 
    throw new IllegalStateException();
  }
  
  protected Image k() {
    WritableImage writableImage;
    String str = "" + this.c.c() + "_" + this.c.c();
    Image image = d.q.get(str);
    if (image != null) {
      double d1 = ReaderApplication.getRenderScale();
      this.f = image.getWidth();
      this.g = image.getHeight();
      this.f = image.getWidth() / d1;
      this.g = image.getHeight() / d1;
      return image;
    } 
    image = null;
    try {
      BufferedImage bufferedImage;
      synchronized (this.c) {
        float f1 = (this.b == 0) ? this.c.n_() : this.c.m_();
        float f2 = this.c.i();
        PDRectangle pDRectangle = d.a(this.c.A().getPage(this.a));
        float f3 = f1 / pDRectangle.getWidth();
        f3 = Math.min(f3, f2 / pDRectangle.getHeight());
        double d1 = ReaderApplication.getRenderScale();
        f3 = (float)(f3 * d1);
        bufferedImage = this.c.a(this.a, f3);
        this.f = bufferedImage.getWidth();
        this.g = bufferedImage.getHeight();
        this.f = bufferedImage.getWidth() / d1;
        this.g = bufferedImage.getHeight() / d1;
      } 
      if ((this.c.l_()).w) {
        WritableRaster writableRaster = bufferedImage.getRaster();
        ColorModel colorModel = bufferedImage.getColorModel();
        for (byte b = 0; b < bufferedImage.getHeight(); b++) {
          for (byte b1 = 0; b1 < bufferedImage.getWidth(); b1++) {
            int i = colorModel.getRGB(writableRaster.getDataElements(b1, b, null));
            int k = i >>> 24;
            int m = i >> 16 & 0xFF;
            int n = i >> 8 & 0xFF;
            int i1 = i & 0xFF;
            m = 255 - m;
            n = 255 - n;
            i1 = 255 - i1;
            i = k << 24 | m << 16 | n << 8 | i1;
            Object object = colorModel.getDataElements(i, null);
            writableRaster.setDataElements(b1, b, object);
          } 
        } 
      } 
      writableImage = SwingFXUtils.toFXImage(bufferedImage, null);
      d.q.put(str, writableImage);
    } catch (IOException iOException) {
      e.log(Level.WARNING, "PDFページの生成に失敗しました。", iOException);
    } 
    return (Image)writableImage;
  }
  
  public int b() {
    return this.b;
  }
  
  public double c() {
    k();
    return this.f;
  }
  
  public double d() {
    k();
    return this.g;
  }
  
  public net.zamasoft.reader.book.h e() {
    return this;
  }
  
  public int a(double paramDouble1, double paramDouble2) {
    return (paramDouble1 < 0.0D || paramDouble2 < 0.0D || paramDouble1 >= c() || paramDouble2 >= d()) ? -1 : 0;
  }
  
  public e b(int paramInt) {
    return this;
  }
  
  public int f() {
    return 1;
  }
  
  public c b(double paramDouble1, double paramDouble2) {
    if (this.h == null) {
      Image image = k();
      ArrayList<c> arrayList = new ArrayList();
      try {
        double d1 = ReaderApplication.getRenderScale();
        synchronized (this.c) {
          PDPage pDPage = this.c.A().getPage(this.a);
          for (PDAnnotation pDAnnotation : pDPage.getAnnotations()) {
            if (!(pDAnnotation instanceof PDAnnotationLink))
              continue; 
            PDAnnotationLink pDAnnotationLink = (PDAnnotationLink)pDAnnotation;
            PDRectangle pDRectangle1 = pDAnnotationLink.getRectangle();
            PDRectangle pDRectangle2 = d.a(pDPage);
            double d2 = pDRectangle1.getWidth() * image.getWidth() / pDRectangle2.getWidth();
            double d3 = pDRectangle1.getHeight() * image.getHeight() / pDRectangle2.getHeight();
            double d4 = (pDRectangle1.getLowerLeftX() - pDRectangle2.getLowerLeftX()) * image.getWidth() / pDRectangle2.getWidth();
            double d5 = (pDRectangle2.getHeight() - pDRectangle1.getUpperRightY() + pDRectangle2.getLowerLeftY()) * image.getHeight() / pDRectangle2.getHeight();
            d2 /= d1;
            d3 /= d1;
            d4 /= d1;
            d5 /= d1;
            PDAction pDAction = pDAnnotationLink.getAction();
            PDDestination pDDestination = null;
            if (pDAction != null) {
              if (pDAction instanceof PDActionURI) {
                try {
                  URI uRI = URI.create(((PDActionURI)pDAction).getURI());
                  arrayList.add(new c(new Area(new Rectangle2D.Double(d4, d5, d2, d3)), (g)new g.c(uRI)));
                } catch (Exception exception) {
                  e.log(Level.WARNING, "不正なURI。", exception);
                } 
                continue;
              } 
              if (pDAction instanceof PDActionGoTo)
                pDDestination = ((PDActionGoTo)pDAction).getDestination(); 
            } 
            if (pDDestination == null)
              pDDestination = pDAnnotationLink.getDestination(); 
            PDPageDestination pDPageDestination = null;
            if (pDDestination instanceof PDPageDestination) {
              pDPageDestination = (PDPageDestination)pDDestination;
            } else if (pDDestination instanceof PDNamedDestination) {
              pDPageDestination = this.c.A().getDocumentCatalog().findNamedDestinationPage((PDNamedDestination)pDDestination);
            } 
            if (pDPageDestination != null)
              arrayList.add(new c(new Area(new Rectangle2D.Double(d4, d5, d2, d3)), (g)new g.a(new f(pDPageDestination.retrievePageNumber())))); 
          } 
        } 
      } catch (IOException iOException) {
        e.log(Level.WARNING, "PDFリンクの抽出に失敗しました。", iOException);
      } 
      this.h = arrayList.<c>toArray(new c[arrayList.size()]);
    } 
    for (c c1 : this.h) {
      if (c1.a().contains(paramDouble1, paramDouble2))
        return c1; 
    } 
    return null;
  }
  
  public Area a() {
    return new Area(new Rectangle2D.Double(0.0D, 0.0D, c(), d()));
  }
  
  public BufferedImage a(int paramInt) {
    try {
      return this.c.a(this.a, paramInt / 72.0F);
    } catch (IOException iOException) {
      e.log(Level.WARNING, "PDF画像の生成に失敗しました。", iOException);
      return null;
    } 
  }
  
  private void l() {
    if (this.i != null)
      return; 
    String str = String.valueOf(this.a + 1);
    try {
      ArrayList arrayList = new ArrayList();
      PDFTextStripper pDFTextStripper = new PDFTextStripper(this, arrayList) {
          double a;
          
          double b;
          
          double c;
          
          double d;
          
          double e;
          
          double f;
          
          double g;
          
          double h;
          
          StringBuffer i = null;
          
          c j = null;
          
          int k = 0;
          
          byte l;
          
          byte m;
          
          protected void writeString(String param1String, List<TextPosition> param1List) throws IOException {
            for (TextPosition textPosition : param1List) {
              double d3;
              double d4;
              double d5;
              double d6;
              String str = textPosition.getUnicode();
              double d1 = textPosition.getPageWidth();
              double d2 = textPosition.getPageHeight();
              this.l = textPosition.getFont().isVertical() ? 3 : 2;
              if (this.l == 2) {
                d3 = textPosition.getX() * this.o.c() / d1;
                d4 = (textPosition.getY() - textPosition.getFontSizeInPt()) * this.o.d() / d2;
                d5 = textPosition.getEndX() * this.o.c() / d1;
                d6 = (textPosition.getY() + textPosition.getHeight()) * this.o.d() / d2;
              } else {
                d3 = textPosition.getX() * this.o.c() / d1;
                d4 = (textPosition.getY() - textPosition.getFontSizeInPt()) * this.o.d() / d2;
                d5 = (textPosition.getX() + textPosition.getFontSizeInPt()) * this.o.c() / d1;
                d6 = textPosition.getY() * this.o.d() / d2;
              } 
              if (this.i != null)
                if (this.m == 2) {
                  if (this.l == this.m && d4 == this.e && d3 >= this.d) {
                    this.i.append(str);
                    this.j.a(d3 - this.d);
                  } else {
                    a();
                  } 
                } else if (this.l == this.m && d3 == this.d && d4 >= this.e) {
                  this.i.append(str);
                  this.j.a(d6 - this.f);
                } else {
                  a();
                }  
              if (this.i == null) {
                this.i = new StringBuffer();
                this.i.append(str);
                this.j = new c();
                this.a = d3;
                this.b = d4;
                if (this.l == 2) {
                  this.c = d6 - d4;
                } else {
                  this.c = d5 - d3;
                  this.j.a(d6 - d4);
                } 
                if (this.c == 0.0D)
                  this.c = this.h; 
              } 
              if (this.l == 2) {
                this.g = d5 - d3;
              } else {
                this.g = d6 - d4;
              } 
              if (this.g == 0.0D)
                this.g = this.h; 
              this.d = d3;
              this.e = d4;
              this.f = d6;
              this.m = this.l;
            } 
          }
          
          private void a() {
            Area area;
            if (this.m == 2) {
              this.j.a(this.g);
              area = new Area(new Rectangle2D.Double(this.a, this.b, this.d + this.g - this.a, this.c));
            } else {
              area = new Area(new Rectangle2D.Double(this.a, this.b, this.c, this.f - this.b));
            } 
            char[] arrayOfChar = this.i.toString().toCharArray();
            this.n.add(new i(area, arrayOfChar, this.j.a(), this.k, this.m));
            this.k += arrayOfChar.length;
            this.i = null;
            this.j = null;
          }
          
          public void writeText(PDDocument param1PDDocument, Writer param1Writer) throws IOException {
            super.writeText(param1PDDocument, param1Writer);
            if (this.i != null)
              a(); 
          }
        };
      pDFTextStripper.setStartPage(this.a + 1);
      pDFTextStripper.setEndPage(this.a + 1);
      synchronized (this.c) {
        pDFTextStripper.writeText(this.c.A(), (Writer)NullWriter.NULL_WRITER);
      } 
      this.i = (l[])arrayList.toArray((Object[])new l[arrayList.size()]);
    } catch (Exception exception) {
      e.log(Level.WARNING, "PDFからテキストを抽出できませんでした:" + str, exception);
    } 
  }
  
  public int d(double paramDouble1, double paramDouble2) {
    l();
    for (byte b = 0; b < this.i.length; b++) {
      l l1 = this.i[b];
      if (l1.a.contains(paramDouble1, paramDouble2))
        return b; 
    } 
    return -1;
  }
  
  public int g() {
    l();
    return this.i.length;
  }
  
  public l c(int paramInt) {
    l();
    return this.i[paramInt];
  }
  
  public l c(double paramDouble1, double paramDouble2) {
    int i = d(paramDouble1, paramDouble2);
    return (i == -1) ? null : this.i[i];
  }
  
  public String h() {
    if (this.d != null)
      return this.d; 
    StringBuffer stringBuffer = new StringBuffer();
    for (byte b = 0; b < g(); b++) {
      l l1 = c(b);
      stringBuffer.append(l1.d(), 0, l1.f());
    } 
    return this.d = stringBuffer.toString();
  }
  
  public int i() {
    return g();
  }
  
  public l d(int paramInt) {
    return c(paramInt);
  }
  
  public int e(double paramDouble1, double paramDouble2) {
    return d(paramDouble1, paramDouble2);
  }
  
  public int j() {
    return 0;
  }
  
  public i e(int paramInt) {
    throw new UnsupportedOperationException();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/a/h.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */