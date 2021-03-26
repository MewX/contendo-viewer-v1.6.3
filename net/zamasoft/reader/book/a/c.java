package net.zamasoft.reader.book.a;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.AttributedString;
import java.util.List;
import java.util.Properties;
import javafx.collections.ObservableList;
import javax.imageio.ImageIO;
import net.zamasoft.reader.ReaderApplication;
import net.zamasoft.reader.book.b;
import net.zamasoft.reader.book.f;
import net.zamasoft.reader.book.h;
import net.zamasoft.reader.book.k;
import net.zamasoft.reader.book.m;
import net.zamasoft.reader.book.s;
import net.zamasoft.reader.book.y;
import net.zamasoft.reader.shelf.b;

public class c extends b implements f {
  public c(File paramFile) {
    super(paramFile);
  }
  
  public boolean z() {
    return false;
  }
  
  public b a(boolean paramBoolean) throws Exception {
    return (b)new a(c());
  }
  
  public f B() {
    return this;
  }
  
  public String i_() {
    return c().getName();
  }
  
  public String j_() {
    return null;
  }
  
  public String[] f() {
    return null;
  }
  
  public String[] g() {
    return null;
  }
  
  public String[] h() {
    return null;
  }
  
  public String[] k_() {
    return null;
  }
  
  public String[] j() {
    return null;
  }
  
  public String[] m() {
    return null;
  }
  
  public long n() {
    return 0L;
  }
  
  public m C() {
    return null;
  }
  
  public boolean v() {
    return true;
  }
  
  public BufferedImage d(int paramInt1, int paramInt2) {
    try {
      BufferedImage bufferedImage1;
      BufferedImage bufferedImage2 = ImageIO.read(ReaderApplication.class.getResource("icons/locked.png"));
      if (bufferedImage2.getWidth() == paramInt1 && bufferedImage2.getHeight() == paramInt2) {
        bufferedImage1 = bufferedImage2;
      } else {
        bufferedImage1 = new BufferedImage(paramInt1, paramInt2, 2);
        Graphics2D graphics2D1 = (Graphics2D)bufferedImage1.getGraphics();
        graphics2D1.drawImage(bufferedImage2.getScaledInstance(paramInt1, paramInt2, 4), 0, 0, (ImageObserver)null);
        bufferedImage2.flush();
      } 
      Graphics2D graphics2D = (Graphics2D)bufferedImage1.getGraphics();
      String str = c().getName();
      graphics2D.setColor(Color.WHITE);
      AttributedString attributedString = new AttributedString(str);
      double d = (paramInt1 / 12);
      attributedString.addAttribute(TextAttribute.SIZE, Double.valueOf(d));
      LineBreakMeasurer lineBreakMeasurer = new LineBreakMeasurer(attributedString.getIterator(), graphics2D.getFontRenderContext());
      float f1 = (float)(paramInt1 * 0.06D);
      float f2 = paramInt1 - f1 * 2.0F;
      float f3 = (float)(paramInt2 * 0.55D);
      byte b1 = 0;
      while (lineBreakMeasurer.getPosition() < str.length()) {
        if (++b1 == 5)
          f2 = (float)(f2 - d); 
        TextLayout textLayout = lineBreakMeasurer.nextLayout(f2);
        f3 += textLayout.getAscent();
        float f4 = textLayout.isLeftToRight() ? 0.0F : (f2 - textLayout.getAdvance());
        textLayout.draw(graphics2D, f1 + f4, f3);
        if (b1 == 5) {
          AttributedString attributedString1 = new AttributedString("…");
          attributedString1.addAttribute(TextAttribute.SIZE, Double.valueOf(d));
          graphics2D.drawString(attributedString1.getIterator(), f1 + textLayout.getAdvance(), f3);
          break;
        } 
        f3 += textLayout.getDescent() + textLayout.getLeading();
      } 
      return bufferedImage1;
    } catch (IOException iOException) {
      throw new RuntimeException(iOException);
    } 
  }
  
  protected void a(Properties paramProperties) throws Exception {
    throw new UnsupportedOperationException();
  }
  
  protected void b(Properties paramProperties) {
    throw new UnsupportedOperationException();
  }
  
  public int k() {
    throw new UnsupportedOperationException();
  }
  
  public k a(int paramInt) throws Exception {
    throw new UnsupportedOperationException();
  }
  
  public k a(h paramh) throws Exception {
    throw new UnsupportedOperationException();
  }
  
  public k a(URI paramURI) throws Exception {
    throw new UnsupportedOperationException();
  }
  
  public String b(int paramInt) throws Exception {
    throw new UnsupportedOperationException();
  }
  
  public String c(int paramInt1, int paramInt2) {
    throw new UnsupportedOperationException();
  }
  
  public int l() {
    throw new UnsupportedOperationException();
  }
  
  public int a(k paramk, int paramInt) {
    throw new UnsupportedOperationException();
  }
  
  public int c(int paramInt) throws Exception {
    throw new UnsupportedOperationException();
  }
  
  public y d(int paramInt) throws Exception {
    throw new UnsupportedOperationException();
  }
  
  public ObservableList<s> o() {
    throw new UnsupportedOperationException();
  }
  
  public List<s> a(k paramk) {
    throw new UnsupportedOperationException();
  }
  
  public void r() {}
  
  public boolean s() {
    return false;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/a/c.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */