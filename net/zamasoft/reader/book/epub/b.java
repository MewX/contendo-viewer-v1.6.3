package net.zamasoft.reader.book.epub;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Dimension2D;
import javafx.scene.media.Media;
import jp.cssj.d.a.a;
import jp.cssj.d.a.a.a;
import jp.cssj.d.a.a.c;
import jp.cssj.d.a.d;
import jp.cssj.d.a.e;
import jp.cssj.d.a.f;
import jp.cssj.d.a.g;
import jp.cssj.d.a.i;
import jp.cssj.d.a.l;
import jp.cssj.d.a.n;
import jp.cssj.d.a.o;
import jp.cssj.e.b.a;
import jp.cssj.e.c;
import jp.cssj.e.e.d;
import jp.cssj.e.h.a;
import jp.cssj.homare.formatter.Formatter;
import jp.cssj.homare.impl.ua.a;
import jp.cssj.homare.ua.m;
import jp.cssj.homare.xml.ParserFactory;
import jp.cssj.homare.xml.e;
import jp.cssj.homare.xml.g;
import jp.cssj.homare.xml.i;
import jp.cssj.sakae.a.h;
import net.zamasoft.reader.book.b;
import net.zamasoft.reader.book.f;
import net.zamasoft.reader.book.h;
import net.zamasoft.reader.book.k;
import net.zamasoft.reader.book.m;
import net.zamasoft.reader.book.n;
import net.zamasoft.reader.book.s;
import net.zamasoft.reader.book.y;
import net.zamasoft.reader.util.a;
import net.zamasoft.reader.util.c;
import org.apache.commons.io.IOUtils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class b extends b implements f, m {
  private static Logger o = Logger.getLogger(b.class.getName());
  
  protected static final ThreadPoolExecutor p = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue()) {
      public ThreadFactory getThreadFactory() {
        return new ThreadFactory(this) {
            public Thread newThread(Runnable param2Runnable) {
              Thread thread = new Thread(param2Runnable);
              thread.setPriority(1);
              thread.setDaemon(true);
              return thread;
            }
          };
      }
    };
  
  protected final d q;
  
  protected final String r;
  
  protected final m.a[] s;
  
  protected final m.a[] t;
  
  protected static final int u = 1;
  
  protected static final int v = 2;
  
  protected final int w;
  
  public static final byte x = 0;
  
  public static final byte y = 1;
  
  public static final byte z = 2;
  
  public static final byte A = 3;
  
  public static final byte B = 4;
  
  protected final int C;
  
  protected Exception D;
  
  protected static Dimension2D E = new Dimension2D(0.0D, 0.0D);
  
  protected final Map<f, Dimension2D> F = new HashMap<>();
  
  protected final Map<Integer, j> G = Collections.synchronizedMap(new HashMap<>());
  
  protected final Map<String, h> H = Collections.synchronizedMap(new HashMap<>());
  
  protected ObservableList<s> I = FXCollections.observableArrayList();
  
  protected final Map<String, List<s>> J = new LinkedHashMap<>();
  
  private boolean L = false;
  
  private int M;
  
  final DateFormat K = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");
  
  public b(File paramFile) throws IOException, Exception {
    super(paramFile);
    a a1 = y();
    try {
      e e = new e(a1);
      jp.cssj.d.a.b b1 = e.a();
      jp.cssj.d.a.b.a a2 = b1.a[0];
      this.q = e.a(a2);
      n n = e.a(this.q);
      this.r = n.a;
      ArrayList<m> arrayList = new ArrayList();
      HashMap<Object, Object> hashMap = new HashMap<>();
      for (i i : n.a()) {
        if (i.c != null && i.c.e == null)
          i.c.e = i.a; 
        m m1 = new m(i, (Map)hashMap);
        arrayList.add(m1);
        hashMap.put(i, m1);
      } 
      this.s = arrayList.<m.a>toArray(new m.a[arrayList.size()]);
      ArrayList<m.a> arrayList1 = new ArrayList();
      for (i i : n.b) {
        m.a a3 = (m.a)hashMap.get(i);
        arrayList1.add(a3);
      } 
      this.t = arrayList1.<m.a>toArray(new m.a[arrayList1.size()]);
      this.w = "pre-paginated".equals(this.q.a("rendition:layout")) ? 2 : 1;
      String str = this.q.a("rendition:spread");
      if (str != null) {
        if (str.equals("none")) {
          this.C = 1;
        } else if (str.equals("portrait")) {
          this.C = 2;
        } else if (str.equals("landscape")) {
          this.C = 3;
        } else if (str.equals("both")) {
          this.C = 4;
        } else {
          this.C = 0;
        } 
      } else {
        this.C = 0;
      } 
    } finally {
      a1.a();
    } 
    this.I.addListener(new ListChangeListener<s>(this) {
          public void onChanged(ListChangeListener.Change<? extends s> param1Change) {
            while (param1Change.next()) {
              for (s s : param1Change.getRemoved()) {
                f f = (f)s;
                List list = this.a.J.get(f.e);
                if (list != null)
                  list.remove(f); 
              } 
              for (s s : param1Change.getAddedSubList()) {
                f f = (f)s;
                List<s> list = this.a.J.get(f.e);
                if (list == null) {
                  list = new ArrayList();
                  this.a.J.put(f.e, list);
                } 
                list.add(f);
              } 
            } 
          }
        });
    this.m.set(k());
  }
  
  protected void a(Properties paramProperties) throws Exception {
    this.n.clear();
    boolean bool = false;
    while (true) {
      String str1;
      int i;
      String str2 = paramProperties.getProperty("bookmark." + bool + ".fullPath");
      if (str2 == null) {
        this.I.clear();
        bool = false;
        while (true) {
          str2 = paramProperties.getProperty("marker." + bool + ".fullPath");
          if (str2 == null) {
            str1 = paramProperties.getProperty("location.fullPath");
            i = Integer.parseInt(paramProperties.getProperty("location.charOffset"));
            this.k = b(str1);
            if (this.k != null)
              this.l = ((h)this.k).a(i); 
            return;
          } 
          int k = Integer.parseInt(paramProperties.getProperty("marker." + str1 + ".startCharOffset"));
          int i1 = Integer.parseInt(paramProperties.getProperty("marker." + str1 + ".endCharOffset"));
          String str4 = paramProperties.getProperty("marker." + str1 + ".text");
          String str5 = paramProperties.getProperty("marker." + str1 + ".note");
          String str6 = paramProperties.getProperty("marker." + str1 + ".image");
          f f1 = new f(i, k, i1, str4, str5, (str6 != null));
          this.I.add(f1);
          str1++;
        } 
        break;
      } 
      int j = Integer.parseInt(paramProperties.getProperty("bookmark." + str1 + ".charOffset"));
      String str3 = paramProperties.getProperty("bookmark." + str1 + ".title");
      n n = new n(str3, new e(i, j));
      this.n.add(n);
      str1++;
    } 
  }
  
  public boolean z() {
    try {
      a(false);
    } catch (Exception exception) {
      return false;
    } 
    return true;
  }
  
  public net.zamasoft.reader.shelf.b a(boolean paramBoolean) throws Exception {
    f f1 = (this.q.r[this.q.r.length - 1]).a;
    a a1 = y();
    try {
      URI uRI = d.a("UTF-8", "archive:///" + f1.d);
      a a2 = new a(a1, uRI, f1.b);
      a2.h().close();
    } finally {
      a1.a();
    } 
    return (net.zamasoft.reader.shelf.b)this;
  }
  
  public a y() throws IOException {
    return (a)new o(c());
  }
  
  protected void b(Properties paramProperties) {
    e e = (e)q();
    paramProperties.setProperty("location.fullPath", e.a);
    paramProperties.setProperty("location.charOffset", String.valueOf(e.b));
    byte b1 = 0;
    for (List<s> list : this.J.values()) {
      for (s s : list) {
        f f1 = (f)s;
        paramProperties.setProperty("marker." + b1 + ".fullPath", f1.e);
        paramProperties.setProperty("marker." + b1 + ".startCharOffset", String.valueOf(f1.a));
        paramProperties.setProperty("marker." + b1 + ".endCharOffset", String.valueOf(f1.b));
        paramProperties.setProperty("marker." + b1 + ".text", f1.c);
        paramProperties.setProperty("marker." + b1 + ".note", f1.d);
        if (f1.f)
          paramProperties.setProperty("marker." + b1 + ".image", "true"); 
        b1++;
      } 
    } 
    b1 = 0;
    for (n n : this.n) {
      paramProperties.setProperty("bookmark." + b1 + ".title", n.b);
      e e1 = (e)n.a;
      paramProperties.setProperty("bookmark." + b1 + ".fullPath", e1.a);
      paramProperties.setProperty("bookmark." + b1 + ".charOffset", String.valueOf(e1.b));
      b1++;
    } 
  }
  
  protected int a(g paramg) {
    int i = this.w;
    if (paramg != null)
      if (paramg.f.contains("rendition:layout-reflowable")) {
        i = 1;
      } else if (paramg.f.contains("rendition:layout-pre-paginated")) {
        i = 2;
      }  
    return i;
  }
  
  protected int b(g paramg) {
    int i = this.C;
    if (paramg != null)
      if (paramg.f.contains("rendition:spread-none")) {
        i = 1;
      } else if (paramg.f.contains("rendition:spread-portrait")) {
        i = 2;
      } else if (paramg.f.contains("rendition:spread-landscape")) {
        i = 3;
      } else if (paramg.f.contains("rendition:spread-both")) {
        i = 4;
      } else if (paramg.f.contains("rendition:spread-auto")) {
        i = 0;
      }  
    return i;
  }
  
  private int c(g paramg) {
    byte b1;
    if (paramg.f.contains("rendition:page-spread-center")) {
      b1 = 3;
    } else {
      b1 = paramg.e;
    } 
    return b1;
  }
  
  public Media b(URI paramURI) {
    String str1;
    String str2 = paramURI.getScheme();
    if ("http".equalsIgnoreCase("scheme") || "https".equalsIgnoreCase("scheme")) {
      str1 = paramURI.toString();
    } else if ("archive".equals(str2)) {
      try {
        a a1 = y();
        File file = File.createTempFile("CopperReader", ".au");
        try {
          file.deleteOnExit();
          String str = paramURI.getPath().substring(1);
          str = URLDecoder.decode(str, "UTF-8");
          InputStream inputStream = a1.b(str);
          try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
              IOUtils.copy(inputStream, fileOutputStream);
            } finally {
              fileOutputStream.close();
            } 
          } finally {
            inputStream.close();
          } 
        } finally {
          a1.a();
        } 
        str1 = file.toURI().toString();
      } catch (IOException iOException) {
        o.log(Level.WARNING, "オーディオを取得できませんでした", iOException);
        return null;
      } 
    } else {
      return null;
    } 
    return new Media(str1);
  }
  
  public synchronized BufferedImage d(int paramInt1, int paramInt2) {
    int i = paramInt1 * 2;
    int j = paramInt2 * 2;
    f f1 = this.q.o;
    if (f1 == null)
      f1 = (this.q.r[0]).a; 
    g g = null;
    ArrayList<g> arrayList = new ArrayList();
    try {
      a((g)null, f1, arrayList, i, j, (this.q.s == 2) ? 1 : 2, false, false);
      g = arrayList.get(0);
    } catch (Exception exception) {
      o.log(Level.WARNING, "ページレイアウトに失敗しました。", exception);
    } 
    BufferedImage bufferedImage = new BufferedImage(paramInt1, paramInt2, 2);
    if (g == null) {
      Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
      graphics2D.setColor(Color.GRAY);
      graphics2D.fillRect(0, 0, paramInt1, paramInt2);
    } else {
      double d1 = g.c();
      double d2 = g.d();
      double d3 = Math.min(i / d1, j / d2);
      BufferedImage bufferedImage1 = new BufferedImage(i, j, 2);
      Graphics2D graphics2D = (Graphics2D)bufferedImage1.getGraphics();
      RenderingHints renderingHints = graphics2D.getRenderingHints();
      c.a(renderingHints);
      graphics2D.setRenderingHints(renderingHints);
      jp.cssj.sakae.b.a.b b1 = new jp.cssj.sakae.b.a.b(graphics2D, g.a());
      if (j / d2 > d3)
        b1.a(AffineTransform.getTranslateInstance(0.0D, j - d2 * d3)); 
      b1.a(AffineTransform.getScaleInstance(d3, d3));
      g.a((jp.cssj.sakae.c.b)b1);
      graphics2D = (Graphics2D)bufferedImage.getGraphics();
      renderingHints = graphics2D.getRenderingHints();
      c.a(renderingHints);
      graphics2D.setRenderingHints(renderingHints);
      graphics2D.drawImage(bufferedImage1.getScaledInstance(paramInt1, paramInt2, 4), 0, 0, (ImageObserver)null);
      bufferedImage1.flush();
    } 
    return bufferedImage;
  }
  
  public void a(a parama, g paramg, f paramf, int paramInt1, int paramInt2, boolean paramBoolean) throws IOException, URISyntaxException, SAXException {
    a a1 = y();
    try {
      URI uRI = d.a("UTF-8", "archive:///" + paramf.d);
      a a2 = new a(a1, uRI, paramf.b);
      a a3 = new a();
      a3.a("archive", (c)new net.zamasoft.reader.util.b(a1));
      a3.a(parama.p());
      a3.c("archive");
      parama.a((c)a3);
      parama.c().a(uRI);
      parama.a().a((h)c.a());
      parama.a("output.media_types", "all screen paged visual bitmap static");
      parama.a("output.page-margins", "0");
      parama.a("output.pdf.hyperlinks.href", "absolute");
      int i = a(paramg);
      String str = a2.c();
      if (str.equals("application/xhtml+xml")) {
        i i1;
        boolean bool = false;
        if (paramBoolean) {
          parama.a("input.viewport", "false");
          double d1 = 300.0D;
          double d2 = 30.0D;
          if (i == 2) {
            parama.a("output.no-page-break", "true");
            parama.a("output.fit-to-paper", "preserve-aspect-ratio");
            Dimension2D dimension2D = this.F.get(paramf);
            if (dimension2D == null) {
              dimension2D = E;
              if (str.equals("application/xhtml+xml")) {
                c c = new c();
                ParserFactory parserFactory1 = (ParserFactory)jp.cssj.c.b.a().a(ParserFactory.class, str);
                e e1 = parserFactory1.createParser();
                try {
                  e1.a((m)parama, (jp.cssj.e.b)a2, (g)new i((ContentHandler)c, null));
                } catch (Exception exception) {}
                String str1 = (String)c.a().get("viewport");
                if (str1 != null) {
                  Pattern pattern = Pattern.compile("\\s*width\\s*=\\s*([0-9\\.]+),\\s*height\\s*=\\s*([0-9\\.]+)\\s*");
                  Matcher matcher = pattern.matcher(str1);
                  if (matcher.find())
                    dimension2D = new Dimension2D(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2))); 
                } 
              } 
              this.F.put(paramf, dimension2D);
            } 
            double d3 = paramInt1;
            double d4 = paramInt2;
            if (dimension2D != E) {
              double d5 = dimension2D.getWidth();
              double d6 = dimension2D.getHeight();
              if (paramInt1 / d5 < paramInt2 / d6) {
                d3 = paramInt1;
                d4 = d6 * paramInt1 / d5;
                d1 *= d5 / paramInt1;
                d2 *= d5 / paramInt1;
              } else {
                d3 = d5 * paramInt2 / d6;
                d4 = paramInt2;
                d1 *= d6 / paramInt2;
                d2 *= d6 / paramInt2;
              } 
              paramInt1 = (int)d5;
              paramInt2 = (int)d6;
            } 
            parama.a("output.paper-width", "" + d3 + "px");
            parama.a("output.paper-height", "" + d4 + "px");
          } else {
            parama.a("output.no-page-break", "false");
            parama.a("output.paper-width", null);
            parama.a("output.paper-height", null);
            parama.a("output.fit-to-paper", null);
            parama.a("output.page-margins", "48px");
          } 
          parama.a("output.page-width", "" + paramInt1 + "px");
          parama.a("output.page-height", "" + paramInt2 + "px");
          parama.a("output.text-size", String.valueOf(this.h.o));
          StringBuffer stringBuffer = new StringBuffer();
          if (!this.h.q) {
            stringBuffer.append("ruby rt { display: none; }");
            stringBuffer.append("ruby rp { display: none; }");
          } 
          stringBuffer.append("@namespace epub url(http://www.idpf.org/2007/ops);");
          if (i != 2)
            switch (this.h.p) {
              case 1:
                if ((A()).s == 2)
                  stringBuffer.append("body { -epub-writing-mode: horizontal-tb ! important; -cssj-direction-mode: vertical-rl; }"); 
                break;
              case 3:
                stringBuffer.append("body { -epub-column-count: 2 ! important; -epub-column-gap: 2.5em ! important; width: auto ! important; }");
              case 2:
                if ((A()).s != 2) {
                  stringBuffer.append("body { -epub-writing-mode: vertical-rl ! important; -cssj-direction-mode: horizontal-tb; widows: 1; orphan: 1; }");
                  bool = true;
                } 
                stringBuffer.append("span.x-epub-tcy { -epub-text-combine: horizontal; }");
                break;
            }  
          stringBuffer.append("*[epub|type=\"footnote\"] { display: block ! important; float: none ! important; position: static ! important; page-break-inside: avoid ! important; }");
          stringBuffer.append("*[epub|type=\"footnote\"] * { max-width: 260px; max-height: 260px; }");
          stringBuffer.append("img, svg, video { max-height: 100%; max-width: 100%; }");
          stringBuffer.append("ruby img { max-width: none; max-height: none; }");
          stringBuffer.append("pre { white-space: pre-wrap; }");
          stringBuffer.append("body { word-wrap: break-word; }");
          if (this.h.r != null)
            stringBuffer.append("body { font-family: ").append(this.h.r).append(" ! important; }"); 
          if (l() == 2) {
            parama.c(1.618D);
          } else {
            parama.c(1.414D);
          } 
          if (this.h.s > 0.0D)
            stringBuffer.append("body { line-height: ").append(this.h.s).append(" ! important; }"); 
          if (this.h.t > 0.0D)
            stringBuffer.append("body { letter-spacing: ").append(this.h.t - 1.0D).append("em ! important; }"); 
          if (this.h.w)
            stringBuffer.append("* { background-color: Black; color: White; }"); 
          stringBuffer.append("audio { display: inline-block; visibility: hidden; position: absolute; }");
          stringBuffer.append("audio[controls] { position: static; -cssj-direction-mode: physical; width: " + d1 + "px; height: " + d2 + "px; }");
          stringBuffer.append("audio, video { page-break-inside: avoid; }");
          parama.a("input.default-stylesheet", "copper-reader:///STYLE.CSS");
          a3.a("copper-reader", new c(this, stringBuffer) {
                public void a(jp.cssj.e.b param1b) {}
                
                public jp.cssj.e.b b(URI param1URI) throws IOException, FileNotFoundException {
                  return (jp.cssj.e.b)new a(param1URI, new StringReader(this.a.toString()));
                }
              });
        } else {
          parama.a("input.viewport", "true");
          parama.a("output.no-page-break", "true");
        } 
        ParserFactory parserFactory = (ParserFactory)jp.cssj.c.b.a().a(ParserFactory.class, str);
        e e = parserFactory.createParser();
        jp.cssj.homare.impl.formatter.document.b b1 = new jp.cssj.homare.impl.formatter.document.b((m)parama);
        if (bool) {
          a a4 = new a((ContentHandler)b1, paramf, true);
          i1 = new i((ContentHandler)a4, null);
        } 
        e.a((m)parama, (jp.cssj.e.b)a2, (g)i1);
      } else {
        parama.a("output.page-width", "" + paramInt1 + "px");
        parama.a("output.page-height", "" + paramInt2 + "px");
        parama.a("output.fit-to-paper", "preserve-aspect-ratio");
        parama.a("output.paper-width", "" + paramInt1 + "px");
        parama.a("output.paper-height", "" + paramInt2 + "px");
        if (i != 2 && paramBoolean) {
          parama.a("output.marks", "hidden");
          parama.a("output.trims", "48px");
        } 
        Formatter formatter = (Formatter)jp.cssj.c.b.a().a(Formatter.class, a2);
        formatter.format((jp.cssj.e.b)a2, (m)parama);
      } 
    } finally {
      a1.a();
    } 
  }
  
  private void a(g paramg, f paramf, List<g> paramList, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2) throws IOException, URISyntaxException, SAXException {
    n n = new n(this, paramList, paramInt3, paramBoolean2, this.h.w);
    a((a)n, paramg, paramf, paramInt1, paramInt2, paramBoolean1);
  }
  
  void a(g paramg, f paramf, List<g> paramList, int paramInt) throws Exception {
    try {
      double d1;
      double d2;
      boolean bool = (this.h.n != 2) ? true : false;
      switch (paramInt) {
        case 0:
          d1 = this.i;
          d2 = this.j;
          break;
        case 1:
        case 2:
          if (bool) {
            d1 = m_();
            d2 = i();
            break;
          } 
        case 3:
          d1 = this.i;
          d2 = this.j;
          bool = false;
          break;
        default:
          throw new IllegalStateException();
      } 
      a(paramg, paramf, paramList, (int)d1, (int)d2, paramInt, true, bool);
    } catch (Exception exception) {
      o.log(Level.WARNING, "ページレイアウトに失敗しました。", exception);
      this.D = exception;
      synchronized (this) {
        notifyAll();
      } 
      throw exception;
    } 
  }
  
  private j a(int paramInt, f paramf) throws Exception {
    if (paramInt < this.M && p.getActiveCount() > 0)
      do {
        this.L = false;
        try {
          synchronized (this) {
            wait(1000L);
          } 
        } catch (InterruptedException interruptedException) {}
        h h1;
        if ((h1 = this.H.get(paramf.d)) != null)
          return new j(h1, paramInt); 
      } while (p.getActiveCount() > 0); 
    h h;
    while ((h = this.H.get(paramf.d)) == null && this.D == null) {
      if (p.getActiveCount() <= 0) {
        this.L = true;
        this.M = k();
        p.execute(new Runnable(this, paramInt, paramf) {
              public void run() {
                this.c.b(this.a, this.b);
                Thread.yield();
              }
            });
      } 
      try {
        synchronized (this) {
          wait(1000L);
        } 
      } catch (InterruptedException interruptedException) {}
    } 
    if (this.D != null)
      throw this.D; 
    return new j(h, paramInt);
  }
  
  private void b(int paramInt, f paramf) {
    int i = paramInt;
    int j = p_();
    boolean bool = (this.h.n != 2) ? true : false;
    if (bool) {
      if (paramInt != -1) {
        boolean bool1 = true;
        int n;
        for (n = paramInt; n >= 0; n--) {
          k k1 = this.G.get(Integer.valueOf(n));
          if (k1 != null) {
            j = ((h)k1).c();
            switch (j) {
              case 1:
                j = 2;
                break;
              case 2:
                j = 1;
                break;
            } 
            i = n + 1;
            break;
          } 
          if (n == 0) {
            i = n;
            break;
          } 
          g g = this.q.r[n];
          int i1 = c(g);
          if (i1 == 3) {
            if (n != paramInt || j == p_()) {
              j = q_();
              i = n;
              break;
            } 
          } else if (i1 != 0) {
            if (n != paramInt || j == i1) {
              j = i1;
              i = n;
              break;
            } 
          } else if (n < paramInt && a(g) == 1) {
            bool1 = false;
          } 
        } 
        if (bool1) {
          for (n = i; n < paramInt; n++) {
            g g = this.q.r[n];
            int i1 = c(g);
            switch (i1) {
              case 1:
              case 2:
              case 3:
                j = i1;
                break;
            } 
            switch (j) {
              case 1:
                j = 2;
                break;
              case 2:
                j = 1;
                break;
              case 3:
                j = p_();
                break;
            } 
          } 
          i = paramInt;
        } 
      } 
    } else {
      j = 0;
      i = paramInt;
    } 
    this.M = i;
    Thread.currentThread().setPriority(10);
    for (int k = this.M; k < k() && this.L; k++) {
      g g;
      if (k != -1) {
        g = this.q.r[k];
        if (bool) {
          int n = c(g);
          if (n == 3) {
            j = p_();
          } else if (n != 0) {
            j = n;
          } 
        } 
        paramf = g.a;
      } else {
        g = null;
      } 
      h h = this.H.get(paramf.d);
      if (h != null) {
        if (k != -1 && !this.G.containsKey(Integer.valueOf(k)))
          this.G.put(Integer.valueOf(k), new j(h, k)); 
      } else {
        try {
          int n;
          if (c(g) == 3) {
            n = 3;
          } else {
            int i1;
            if (this.h.n == 1) {
              i1 = 4;
            } else {
              i1 = b(g);
            } 
            switch (i1) {
              case 0:
              case 4:
                n = j;
                break;
              case 1:
                n = 0;
                break;
              case 2:
                n = (this.j > this.i) ? j : 0;
                break;
              case 3:
                n = (this.j < this.i) ? j : 0;
                break;
              default:
                throw new IllegalStateException();
            } 
          } 
          h = new i(k, g, paramf, n, "" + c() + "_" + c(), this);
        } catch (Exception exception) {
          return;
        } 
        if (k != -1) {
          this.G.put(Integer.valueOf(k), new j(h, k));
          this.m.set(this.m.get() + h.s_() - 1);
        } 
        this.H.put(paramf.d, h);
        synchronized (this) {
          notifyAll();
        } 
      } 
      if (bool) {
        j = h.c();
        switch (j) {
          case 1:
            j = 2;
            break;
          case 2:
            j = 1;
            break;
          case 3:
            j = p_();
            break;
        } 
      } 
    } 
  }
  
  public k a(int paramInt) throws Exception {
    null = this.G.get(Integer.valueOf(paramInt));
    if (null != null)
      return null; 
    g g = this.q.r[paramInt];
    return a(paramInt, g.a);
  }
  
  public String b(int paramInt) throws Exception {
    int i = c(paramInt);
    String str = B().i_();
    if (i != -1)
      str = c(i, 0); 
    return str;
  }
  
  public String c(int paramInt1, int paramInt2) {
    String str = B().i_();
    for (int i = paramInt1; i >= 0; i--) {
      g g = this.q.r[paramInt1];
      if (g.a.e != null) {
        str = g.a.e;
        break;
      } 
    } 
    return str;
  }
  
  public f a(String paramString) {
    for (f f1 : this.q.p) {
      if (f1.d.equals(paramString))
        return f1; 
    } 
    return null;
  }
  
  public k b(String paramString) throws Exception {
    null = this.H.get(paramString);
    if (null != null)
      return null; 
    f f1 = a(paramString);
    if (f1 == null)
      return null; 
    byte b1 = -1;
    for (byte b2 = 0; b2 < this.q.r.length; b2++) {
      if ((this.q.r[b2]).a.d.equals(paramString)) {
        b1 = b2;
        break;
      } 
    } 
    return a(b1, f1);
  }
  
  public int k() {
    return this.q.r.length;
  }
  
  public int a(k paramk, int paramInt) {
    if (paramk == null)
      return 0; 
    if (paramk.r_() == -1)
      return paramInt; 
    int i = 0;
    for (byte b1 = 0; b1 < paramk.r_(); b1++) {
      k k1 = this.G.get(Integer.valueOf(b1));
      if (k1 == null) {
        i++;
      } else {
        i += k1.s_();
      } 
    } 
    return i + paramInt;
  }
  
  public int c(int paramInt) throws Exception {
    int i = 0;
    for (byte b1 = 0; b1 < k(); b1++) {
      k k = this.G.get(Integer.valueOf(b1));
      if (k == null) {
        if (paramInt == i)
          return b1; 
        i++;
      } else {
        if (paramInt >= i && paramInt < i + k.s_())
          return b1; 
        i += k.s_();
      } 
    } 
    return -1;
  }
  
  public y d(int paramInt) throws Exception {
    int i = 0;
    for (byte b1 = 0; b1 < k(); b1++) {
      k k = this.G.get(Integer.valueOf(b1));
      if (k == null) {
        if (paramInt == i)
          return new y(a(b1), 0); 
        i++;
      } else {
        if (paramInt >= i && paramInt < i + k.s_())
          return new y(k, paramInt - i); 
        i += k.s_();
      } 
    } 
    return null;
  }
  
  public d A() {
    return this.q;
  }
  
  public int l() {
    switch (this.h.p) {
      case 1:
        return 1;
      case 2:
      case 3:
        return 2;
    } 
    return ((A()).s == 2) ? 2 : 1;
  }
  
  public ObservableList<s> o() {
    return this.I;
  }
  
  public List<s> c(String paramString) {
    List<? extends s> list = this.J.get(paramString);
    return (list == null) ? Collections.emptyList() : Collections.unmodifiableList(list);
  }
  
  public synchronized void r() {
    if (p.getActiveCount() > 0)
      do {
        this.L = false;
        try {
          wait(1000L);
        } catch (InterruptedException interruptedException) {}
      } while (p.getActiveCount() > 0); 
    for (h h : this.H.values())
      h.e(); 
    this.H.clear();
    this.G.clear();
    this.D = null;
    this.m.set(k());
    String str = "" + c() + "_";
    ArrayList<String> arrayList = new ArrayList();
    synchronized (i.d) {
      for (String str1 : i.d.keySet()) {
        if (str1.startsWith(str))
          arrayList.add(str1); 
      } 
      for (String str1 : arrayList)
        i.d.remove(str1); 
    } 
  }
  
  public f B() {
    return this;
  }
  
  public m C() {
    return this;
  }
  
  private k d(String paramString) throws Exception {
    null = this.H.get(paramString);
    if (null != null)
      return null; 
    f f1 = null;
    for (f f2 : this.q.p) {
      if (f2.d.equals(paramString)) {
        f1 = f2;
        break;
      } 
    } 
    if (f1 == null)
      return null; 
    byte b1 = -1;
    for (byte b2 = 0; b2 < this.q.r.length; b2++) {
      if ((this.q.r[b2]).a.d.equals(paramString)) {
        b1 = b2;
        break;
      } 
    } 
    return a(b1, f1);
  }
  
  public k a(h paramh) throws Exception {
    e e = (e)paramh;
    return d(e.a);
  }
  
  public k a(URI paramURI) throws Exception {
    String str = paramURI.getPath();
    if (str.startsWith("/"))
      str = str.substring(1); 
    return d(str);
  }
  
  public List<s> a(k paramk) {
    h h = (h)paramk;
    List<? extends s> list = this.J.get(h.b());
    return (list == null) ? Collections.emptyList() : Collections.unmodifiableList(list);
  }
  
  public String v() {
    return this.r;
  }
  
  public m.a[] w() {
    return this.s;
  }
  
  public m.a[] x() {
    return this.t;
  }
  
  public String i_() {
    return (this.q.f == null) ? null : this.q.f.a;
  }
  
  public String j_() {
    return (this.q.f == null) ? null : this.q.f.a("file-as");
  }
  
  public String[] f() {
    String[] arrayOfString = new String[this.q.j.size()];
    byte b1 = 0;
    for (l l : this.q.j)
      arrayOfString[b1++] = l.a; 
    return arrayOfString;
  }
  
  public String[] g() {
    String[] arrayOfString = new String[this.q.j.size()];
    byte b1 = 0;
    for (l l : this.q.j)
      arrayOfString[b1++] = l.a("file-as"); 
    return arrayOfString;
  }
  
  public String[] h() {
    String[] arrayOfString = new String[this.q.k.size()];
    byte b1 = 0;
    for (l l : this.q.k)
      arrayOfString[b1++] = l.a; 
    return arrayOfString;
  }
  
  public String[] k_() {
    String[] arrayOfString = new String[this.q.k.size()];
    byte b1 = 0;
    for (l l : this.q.k)
      arrayOfString[b1++] = l.a("file-as"); 
    return arrayOfString;
  }
  
  public String[] j() {
    String[] arrayOfString = new String[this.q.l.size()];
    byte b1 = 0;
    for (l l : this.q.l)
      arrayOfString[b1++] = l.a; 
    return arrayOfString;
  }
  
  public String[] m() {
    String[] arrayOfString = new String[this.q.l.size()];
    byte b1 = 0;
    for (l l : this.q.l)
      arrayOfString[b1++] = l.a("file-as"); 
    return arrayOfString;
  }
  
  public long n() {
    String str = this.q.a("dcterms:modified");
    if (str == null)
      return 0L; 
    try {
      return this.K.parse(str).getTime();
    } catch (ParseException parseException) {
      return 0L;
    } 
  }
  
  public boolean s() {
    return true;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/epub/b.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */