package net.zamasoft.reader.book.a;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import net.zamasoft.reader.book.b;
import net.zamasoft.reader.book.f;
import net.zamasoft.reader.book.g;
import net.zamasoft.reader.book.h;
import net.zamasoft.reader.book.j;
import net.zamasoft.reader.book.k;
import net.zamasoft.reader.book.m;
import net.zamasoft.reader.book.n;
import net.zamasoft.reader.book.s;
import net.zamasoft.reader.book.y;
import net.zamasoft.reader.shelf.b;
import net.zamasoft.reader.util.c;
import org.apache.commons.io.output.NullWriter;
import org.apache.fontbox.FontBoxFont;
import org.apache.fontbox.ttf.TTFParser;
import org.apache.fontbox.ttf.TrueTypeFont;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.CIDFontMapping;
import org.apache.pdfbox.pdmodel.font.FontMapper;
import org.apache.pdfbox.pdmodel.font.FontMappers;
import org.apache.pdfbox.pdmodel.font.FontMapping;
import org.apache.pdfbox.pdmodel.font.PDCIDSystemInfo;
import org.apache.pdfbox.pdmodel.font.PDFontDescriptor;
import org.apache.pdfbox.pdmodel.interactive.action.PDAction;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionGoTo;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.pdmodel.interactive.viewerpreferences.PDViewerPreferences;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class d extends b implements f, k, m {
  private static Logger s = Logger.getLogger(d.class.getName());
  
  private e t = new e();
  
  protected ObservableList<s> o = FXCollections.observableArrayList();
  
  protected PDDocument p;
  
  private PDFRenderer u;
  
  private j[] v;
  
  static final Map<String, Image> q = Collections.synchronizedMap((Map<String, Image>)new net.zamasoft.reader.util.d(0.2D));
  
  final File r = new File(d(), "pdf_info");
  
  private int w = 0;
  
  public d(File paramFile) throws IOException, Exception {
    super(paramFile);
    E();
  }
  
  protected boolean y() throws IOException {
    File file = new File(d(), "pdf_info");
    if (file.exists() && file.lastModified() == c().lastModified() && this.t.a(file))
      this.m.set(this.t.h); 
    return false;
  }
  
  private void E() throws IOException {
    if (y())
      return; 
    try {
      PDDocument pDDocument = A();
      this.t.h = pDDocument.getNumberOfPages();
      PDDocumentInformation pDDocumentInformation = pDDocument.getDocumentInformation();
      this.t.b = pDDocumentInformation.getTitle();
      if (this.t.b == null)
        this.t.b = ""; 
      this.t.c = pDDocumentInformation.getAuthor();
      if (this.t.c == null)
        this.t.c = ""; 
      Calendar calendar = pDDocumentInformation.getModificationDate();
      if (calendar == null)
        pDDocumentInformation.getCreationDate(); 
      if (calendar != null) {
        this.t.d = calendar.getTimeInMillis();
      } else {
        this.t.d = c().lastModified();
      } 
      this.m.set(this.t.h);
      PDViewerPreferences pDViewerPreferences = this.p.getDocumentCatalog().getViewerPreferences();
      if (pDViewerPreferences != null && PDViewerPreferences.READING_DIRECTION.R2L.name().equals(pDViewerPreferences.getReadingDirection()))
        this.t.e = 2; 
      ArrayList<j> arrayList = new ArrayList();
      ArrayList<PDOutlineItem> arrayList1 = new ArrayList();
      arrayList1.add(new ArrayList());
      try {
        PDDocumentOutline pDDocumentOutline = pDDocument.getDocumentCatalog().getDocumentOutline();
        if (pDDocumentOutline != null)
          for (PDOutlineItem pDOutlineItem = pDDocumentOutline.getFirstChild();; pDOutlineItem = pDOutlineItem.getNextSibling()) {
            if (pDOutlineItem != null) {
              arrayList1.add(pDOutlineItem);
              arrayList1.add((PDOutlineItem)new ArrayList());
              pDOutlineItem = pDOutlineItem.getFirstChild();
              if (pDOutlineItem != null)
                continue; 
            } else {
              if (arrayList1.size() == 1)
                break; 
              pDOutlineItem = arrayList1.get(arrayList1.size() - 2);
            } 
            List list1 = (List)arrayList1.remove(arrayList1.size() - 1);
            pDOutlineItem = arrayList1.remove(arrayList1.size() - 1);
            List<j> list2 = (List)arrayList1.get(arrayList1.size() - 1);
            PDDestination pDDestination = pDOutlineItem.getDestination();
            if (!(pDDestination instanceof PDPageDestination)) {
              PDAction pDAction = pDOutlineItem.getAction();
              if (pDAction instanceof PDActionGoTo)
                pDDestination = ((PDActionGoTo)pDAction).getDestination(); 
            } 
            if (pDDestination instanceof PDPageDestination) {
              PDPageDestination pDPageDestination = (PDPageDestination)pDDestination;
              j j1 = new j(pDOutlineItem.getTitle().replaceAll("[\n\r]", ""), pDPageDestination.retrievePageNumber(), (m.a[])list1.toArray((Object[])new m.a[list1.size()]));
              list2.add(j1);
              arrayList.add(j1);
            } 
          }  
      } catch (IOException iOException) {
        s.log(Level.WARNING, "PDFブックマークの取得に失敗しました。", iOException);
      } 
      this.t.f = arrayList.<m.a>toArray(new m.a[arrayList.size()]);
      Arrays.sort((Object[])this.t.f);
      List list = (List)arrayList1.get(0);
      this.t.g = (m.a[])list.toArray((Object[])new m.a[list.size()]);
      this.t.b(this.r);
      this.r.setLastModified(c().lastModified());
    } finally {
      D();
    } 
    this.v = new j[s_()];
  }
  
  public void a(int paramInt1, int paramInt2) throws Exception {
    E();
    super.a(paramInt1, paramInt2);
  }
  
  public synchronized PDDocument A() {
    if (this.p == null)
      try {
        this.p = PDDocument.load(c(), MemoryUsageSetting.setupTempFileOnly());
      } catch (IOException iOException) {
        throw new RuntimeException(iOException);
      }  
    return this.p;
  }
  
  public synchronized void D() throws IOException {
    if (this.p == null)
      return; 
    this.p.close();
    this.p = null;
    this.u = null;
  }
  
  private synchronized PDFRenderer F() {
    if (this.u == null)
      this.u = new PDFRenderer(A()); 
    return this.u;
  }
  
  public boolean z() {
    return true;
  }
  
  public b a(boolean paramBoolean) throws Exception {
    return (b)this;
  }
  
  public f B() {
    return this;
  }
  
  public String i_() {
    return (this.t.b == null) ? "" : this.t.b;
  }
  
  public String j_() {
    return "";
  }
  
  public String[] f() {
    return (this.t.c == null) ? new String[0] : new String[] { this.t.c };
  }
  
  public String[] g() {
    return (this.t.c == null) ? new String[0] : new String[] { null };
  }
  
  public String[] h() {
    return new String[0];
  }
  
  public String[] k_() {
    return new String[0];
  }
  
  public String[] j() {
    return new String[0];
  }
  
  public String[] m() {
    return new String[0];
  }
  
  public long n() {
    return this.t.d;
  }
  
  public m C() {
    return this;
  }
  
  public String v() {
    return this.t.b;
  }
  
  public m.a[] w() {
    return this.t.f;
  }
  
  public m.a[] x() {
    return this.t.g;
  }
  
  protected void a(Properties paramProperties) throws Exception {
    // Byte code:
    //   0: aload_0
    //   1: getfield n : Ljavafx/collections/ObservableList;
    //   4: invokeinterface clear : ()V
    //   9: iconst_0
    //   10: istore_2
    //   11: aload_1
    //   12: iload_2
    //   13: <illegal opcode> makeConcatWithConstants : (I)Ljava/lang/String;
    //   18: invokevirtual getProperty : (Ljava/lang/String;)Ljava/lang/String;
    //   21: astore_3
    //   22: aload_3
    //   23: ifnonnull -> 29
    //   26: goto -> 81
    //   29: aload_1
    //   30: iload_2
    //   31: <illegal opcode> makeConcatWithConstants : (I)Ljava/lang/String;
    //   36: invokevirtual getProperty : (Ljava/lang/String;)Ljava/lang/String;
    //   39: invokestatic parseInt : (Ljava/lang/String;)I
    //   42: istore #4
    //   44: new net/zamasoft/reader/book/n
    //   47: dup
    //   48: aload_3
    //   49: new net/zamasoft/reader/book/a/f
    //   52: dup
    //   53: iload #4
    //   55: invokespecial <init> : (I)V
    //   58: invokespecial <init> : (Ljava/lang/String;Lnet/zamasoft/reader/book/h;)V
    //   61: astore #5
    //   63: aload_0
    //   64: getfield n : Ljavafx/collections/ObservableList;
    //   67: aload #5
    //   69: invokeinterface add : (Ljava/lang/Object;)Z
    //   74: pop
    //   75: iinc #2, 1
    //   78: goto -> 11
    //   81: aload_0
    //   82: getfield o : Ljavafx/collections/ObservableList;
    //   85: invokeinterface clear : ()V
    //   90: iconst_0
    //   91: istore_2
    //   92: aload_1
    //   93: iload_2
    //   94: <illegal opcode> makeConcatWithConstants : (I)Ljava/lang/String;
    //   99: invokevirtual getProperty : (Ljava/lang/String;)Ljava/lang/String;
    //   102: astore_3
    //   103: aload_3
    //   104: ifnonnull -> 110
    //   107: goto -> 203
    //   110: aload_1
    //   111: iload_2
    //   112: <illegal opcode> makeConcatWithConstants : (I)Ljava/lang/String;
    //   117: invokevirtual getProperty : (Ljava/lang/String;)Ljava/lang/String;
    //   120: invokestatic parseInt : (Ljava/lang/String;)I
    //   123: istore #4
    //   125: aload_1
    //   126: iload_2
    //   127: <illegal opcode> makeConcatWithConstants : (I)Ljava/lang/String;
    //   132: invokevirtual getProperty : (Ljava/lang/String;)Ljava/lang/String;
    //   135: invokestatic parseInt : (Ljava/lang/String;)I
    //   138: istore #5
    //   140: aload_1
    //   141: iload_2
    //   142: <illegal opcode> makeConcatWithConstants : (I)Ljava/lang/String;
    //   147: invokevirtual getProperty : (Ljava/lang/String;)Ljava/lang/String;
    //   150: invokestatic parseInt : (Ljava/lang/String;)I
    //   153: istore #6
    //   155: aload_1
    //   156: iload_2
    //   157: <illegal opcode> makeConcatWithConstants : (I)Ljava/lang/String;
    //   162: invokevirtual getProperty : (Ljava/lang/String;)Ljava/lang/String;
    //   165: astore #7
    //   167: new net/zamasoft/reader/book/a/g
    //   170: dup
    //   171: iload #4
    //   173: iload #5
    //   175: iload #6
    //   177: aload_3
    //   178: aload #7
    //   180: invokespecial <init> : (IIILjava/lang/String;Ljava/lang/String;)V
    //   183: astore #8
    //   185: aload_0
    //   186: getfield o : Ljavafx/collections/ObservableList;
    //   189: aload #8
    //   191: invokeinterface add : (Ljava/lang/Object;)Z
    //   196: pop
    //   197: iinc #2, 1
    //   200: goto -> 92
    //   203: aload_0
    //   204: aload_1
    //   205: ldc 'location.pageNumber'
    //   207: invokevirtual getProperty : (Ljava/lang/String;)Ljava/lang/String;
    //   210: invokestatic parseInt : (Ljava/lang/String;)I
    //   213: putfield l : I
    //   216: aload_0
    //   217: aload_0
    //   218: putfield k : Lnet/zamasoft/reader/book/k;
    //   221: goto -> 225
    //   224: astore_2
    //   225: return
    // Exception table:
    //   from	to	target	type
    //   203	221	224	java/lang/Exception
  }
  
  protected void b(Properties paramProperties) {
    paramProperties.setProperty("location.pageNumber", String.valueOf(this.l));
    byte b1 = 0;
    for (s s : this.o) {
      g g = (g)s;
      paramProperties.setProperty("marker." + b1 + ".page", String.valueOf(g.e));
      paramProperties.setProperty("marker." + b1 + ".startCharOffset", String.valueOf(g.a));
      paramProperties.setProperty("marker." + b1 + ".endCharOffset", String.valueOf(g.b));
      paramProperties.setProperty("marker." + b1 + ".text", g.c);
      paramProperties.setProperty("marker." + b1 + ".note", g.d);
      b1++;
    } 
    b1 = 0;
    for (n n : this.n) {
      paramProperties.setProperty("bookmark." + b1 + ".title", n.b);
      f f1 = (f)n.a;
      paramProperties.setProperty("bookmark." + b1 + ".pageNumber", String.valueOf(f1.a));
      b1++;
    } 
  }
  
  public static PDRectangle a(PDPage paramPDPage) {
    PDRectangle pDRectangle = paramPDPage.getCropBox();
    int i = paramPDPage.getRotation();
    if (i < 0) {
      i += 360;
    } else if (i >= 360) {
      i -= 360;
    } 
    if (i == 90 || i == 270) {
      float f1 = pDRectangle.getWidth();
      float f2 = pDRectangle.getHeight();
      float f3 = f2;
      f2 = f1;
      f1 = f3;
      return new PDRectangle(f1, f2);
    } 
    return pDRectangle;
  }
  
  public synchronized BufferedImage d(int paramInt1, int paramInt2) {
    try {
      BufferedImage bufferedImage1 = new BufferedImage(paramInt1, paramInt2, 2);
      PDRectangle pDRectangle = a(A().getPage(0));
      float f1 = pDRectangle.getWidth();
      float f2 = pDRectangle.getHeight();
      float f3 = (paramInt1 * 4) / f1;
      f3 = Math.min(f3, (paramInt2 * 4) / f2);
      f1 = f1 * f3 / 4.0F;
      f2 = f2 * f3 / 4.0F;
      BufferedImage bufferedImage2 = a(0, f3);
      Graphics2D graphics2D = (Graphics2D)bufferedImage1.getGraphics();
      RenderingHints renderingHints = graphics2D.getRenderingHints();
      c.a(renderingHints);
      graphics2D.setRenderingHints(renderingHints);
      graphics2D.drawImage(bufferedImage2.getScaledInstance((int)f1, (int)f2, 4), (int)((paramInt1 - f1) / 2.0F), (int)((paramInt2 - f2) / 2.0F), (ImageObserver)null);
      bufferedImage2.flush();
      return bufferedImage1;
    } catch (IOException iOException) {
      s.log(Level.WARNING, "PDFサムネイルの生成に失敗しました。", iOException);
      return null;
    } finally {
      try {
        D();
      } catch (IOException iOException) {}
    } 
  }
  
  public synchronized BufferedImage a(int paramInt, float paramFloat) throws IOException {
    PDPage pDPage = A().getPage(paramInt);
    PDRectangle pDRectangle = a(pDPage);
    float f1 = pDRectangle.getWidth();
    float f2 = pDRectangle.getHeight();
    float f3 = paramFloat;
    if (paramFloat < 2.7777777F)
      paramFloat = 2.7777777F; 
    int i = Math.round(f1 * paramFloat);
    int n = Math.round(f2 * paramFloat);
    BufferedImage bufferedImage = new BufferedImage(i, n, 5);
    Graphics2D graphics2D = bufferedImage.createGraphics();
    RenderingHints renderingHints = graphics2D.getRenderingHints();
    c.a(renderingHints);
    graphics2D.setRenderingHints(renderingHints);
    graphics2D.setBackground(Color.WHITE);
    graphics2D.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
    F().renderPageToGraphics(paramInt, graphics2D, paramFloat);
    graphics2D.dispose();
    if (f3 != paramFloat) {
      BufferedImage bufferedImage1 = bufferedImage;
      bufferedImage = new BufferedImage(Math.round(f1 * f3), Math.round(f2 * f3), 1);
      bufferedImage.getGraphics().drawImage(bufferedImage1.getScaledInstance(Math.round(f1 * f3), Math.round(f2 * f3), 4), 0, 0, null);
      bufferedImage1.flush();
    } 
    if (++this.w % 5 == 0) {
      D();
      System.gc();
    } 
    return bufferedImage;
  }
  
  public int k() {
    return 1;
  }
  
  public k a(int paramInt) throws Exception {
    return this;
  }
  
  public k a(h paramh) throws Exception {
    return this;
  }
  
  public k a(URI paramURI) throws Exception {
    return null;
  }
  
  public String b(int paramInt) throws Exception {
    int i = c(paramInt);
    int n = (d(paramInt)).b;
    return c(i, n);
  }
  
  public String c(int paramInt1, int paramInt2) {
    String str = i_();
    for (int i = this.t.f.length - 1; i >= 0; i--) {
      g.a a = (g.a)this.t.f[i].b();
      f f1 = (f)a.b();
      if (f1.a <= paramInt2) {
        str = this.t.f[i].a();
        break;
      } 
    } 
    if (str.length() == 0) {
      str = str + "P" + str;
    } else {
      str = str + " / P" + str;
    } 
    return str;
  }
  
  public int l() {
    switch (this.h.p) {
      case 1:
        return 1;
      case 2:
      case 3:
        return 2;
    } 
    return this.t.e;
  }
  
  public int a(k paramk, int paramInt) {
    return paramInt;
  }
  
  public int c(int paramInt) throws Exception {
    return 0;
  }
  
  public y d(int paramInt) throws Exception {
    return new y(this, paramInt);
  }
  
  public ObservableList<s> o() {
    return this.o;
  }
  
  public List<s> b(String paramString) {
    return Collections.unmodifiableList((List<? extends s>)this.o);
  }
  
  public List<s> a(k paramk) {
    return Collections.unmodifiableList((List<? extends s>)this.o);
  }
  
  public void r() {
    String str = "" + c() + "_";
    ArrayList<String> arrayList = new ArrayList();
    for (String str1 : q.keySet()) {
      if (str1.startsWith(str))
        arrayList.add(str1); 
    } 
    for (String str1 : arrayList)
      q.remove(str1); 
    for (byte b1 = 0; b1 < this.v.length; b1++)
      this.v[b1] = null; 
  }
  
  public boolean s() {
    return false;
  }
  
  public int r_() {
    return 0;
  }
  
  public j e(int paramInt) {
    j j1 = this.v[paramInt];
    if (j1 != null)
      return j1; 
    j1 = new h(this, paramInt);
    this.v[paramInt] = j1;
    return j1;
  }
  
  public Image f(int paramInt) {
    return ((h)e(paramInt)).k();
  }
  
  public int s_() {
    return this.t.h;
  }
  
  public int a(String paramString) {
    return 0;
  }
  
  public int b(h paramh) {
    int i = ((f)paramh).a;
    if (i < 0) {
      i = 0;
    } else if (i >= this.t.h) {
      i = this.t.h - 1;
    } 
    return i;
  }
  
  public s a(int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2, boolean paramBoolean) {
    return new g(paramInt1, paramInt2, paramInt3, paramString1, paramString2);
  }
  
  public boolean t_() {
    return false;
  }
  
  public boolean u_() {
    return false;
  }
  
  public synchronized void v_() {
    boolean bool = false;
    for (byte b1 = 0; b1 < this.t.h; b1++) {
      h h = (h)e(b1);
      if (h.d == null) {
        bool = true;
        break;
      } 
    } 
    if (!bool)
      return; 
    try {
      StringBuffer stringBuffer = new StringBuffer();
      PDFTextStripper pDFTextStripper = new PDFTextStripper(this, stringBuffer) {
          protected void writeString(String param1String, List<TextPosition> param1List) throws IOException {
            this.a.append(param1String);
          }
        };
      for (byte b2 = 0; b2 < this.t.h; b2++) {
        if (b2 % 10 == 9)
          pDFTextStripper = new PDFTextStripper(this, stringBuffer) {
              protected void writeString(String param1String, List<TextPosition> param1List) throws IOException {
                this.a.append(param1String);
              }
            }; 
        pDFTextStripper.setStartPage(b2 + 1);
        pDFTextStripper.setEndPage(b2 + 1);
        h h = (h)e(b2);
        pDFTextStripper.writeText(A(), (Writer)NullWriter.NULL_WRITER);
        h.d = stringBuffer.toString();
        stringBuffer.delete(0, stringBuffer.length());
      } 
    } catch (IOException iOException) {
      s.log(Level.WARNING, "PDFからテキストを抽出できませんでした。", iOException);
    } 
  }
  
  public void b() throws IOException {
    if (this.p != null)
      D(); 
    super.b();
  }
  
  public synchronized void a() {
    super.a();
    try {
      if (this.p != null)
        D(); 
    } catch (IOException iOException) {
      throw new RuntimeException(iOException);
    } 
  }
  
  static {
    TTFParser tTFParser = new TTFParser();
    try {
      TrueTypeFont trueTypeFont1 = tTFParser.parse(c.c);
      CIDFontMapping cIDFontMapping1 = new CIDFontMapping(null, (FontBoxFont)trueTypeFont1, true);
      FontMapping fontMapping1 = new FontMapping((FontBoxFont)trueTypeFont1, true);
      FontMapping fontMapping2 = new FontMapping((FontBoxFont)trueTypeFont1, true);
      TrueTypeFont trueTypeFont2 = tTFParser.parse(c.b);
      CIDFontMapping cIDFontMapping2 = new CIDFontMapping(null, (FontBoxFont)trueTypeFont2, true);
      FontMapping fontMapping3 = new FontMapping((FontBoxFont)trueTypeFont2, true);
      FontMapping fontMapping4 = new FontMapping((FontBoxFont)trueTypeFont2, true);
      FontMappers.set(new FontMapper(cIDFontMapping1, cIDFontMapping2, fontMapping1, fontMapping3, fontMapping2, fontMapping4) {
            public CIDFontMapping getCIDFont(String param1String, PDFontDescriptor param1PDFontDescriptor, PDCIDSystemInfo param1PDCIDSystemInfo) {
              return (param1PDFontDescriptor.isSerif() || param1PDFontDescriptor.getPanose() == null) ? this.a : this.b;
            }
            
            public FontMapping<FontBoxFont> getFontBoxFont(String param1String, PDFontDescriptor param1PDFontDescriptor) {
              return (param1PDFontDescriptor.isSerif() || param1PDFontDescriptor.getPanose() == null) ? this.c : this.d;
            }
            
            public FontMapping<TrueTypeFont> getTrueTypeFont(String param1String, PDFontDescriptor param1PDFontDescriptor) {
              return (param1PDFontDescriptor.isSerif() || param1PDFontDescriptor.getPanose() == null) ? this.e : this.f;
            }
          });
    } catch (IOException iOException) {}
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/a/d.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */