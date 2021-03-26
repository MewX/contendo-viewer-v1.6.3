/*     */ package jp.cssj.homare.driver;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PipedInputStream;
/*     */ import java.io.PipedOutputStream;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerConfigurationException;
/*     */ import javax.xml.transform.sax.SAXTransformerFactory;
/*     */ import javax.xml.transform.sax.TransformerHandler;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import jp.cssj.b.a.a;
/*     */ import jp.cssj.b.a.d;
/*     */ import jp.cssj.b.b.a;
/*     */ import jp.cssj.b.c;
/*     */ import jp.cssj.b.c.b;
/*     */ import jp.cssj.b.d;
/*     */ import jp.cssj.b.d.c;
/*     */ import jp.cssj.c.b;
/*     */ import jp.cssj.e.b;
/*     */ import jp.cssj.e.c;
/*     */ import jp.cssj.e.e.d;
/*     */ import jp.cssj.homare.a.c;
/*     */ import jp.cssj.homare.formatter.Formatter;
/*     */ import jp.cssj.homare.ua.UserAgentFactory;
/*     */ import jp.cssj.homare.ua.a.B;
/*     */ import jp.cssj.homare.ua.b;
/*     */ import jp.cssj.homare.ua.j;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.a.g;
/*     */ import jp.cssj.sakae.a.h;
/*     */ import jp.cssj.sakae.pdf.c.f;
/*     */ import org.apache.commons.collections.map.LRUMap;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.commons.io.input.TeeInputStream;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.AttributesImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   extends a
/*     */   implements a, c, c
/*     */ {
/*     */   static {
/*  81 */     d = Logger.getLogger(a.class.getName());
/*     */ 
/*     */     
/*  84 */     e = new HashSet<>(Arrays.asList(new String[] { "input.include", "input.exclude" }));
/*     */ 
/*     */     
/*  87 */     f = URI.create("http://www.cssj.jp/ns/ctip/version");
/*     */ 
/*     */     
/*  90 */     g = URI.create("http://www.cssj.jp/ns/ctip/output-types");
/*     */ 
/*     */     
/*  93 */     h = URI.create("http://www.cssj.jp/ns/ctip/fonts");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 119 */     p = Collections.synchronizedMap((Map<File, h>)new LRUMap());
/*     */   }
/*     */   private c i = null; private b j = null; private a k = d.c; private Map<String, String> l = new HashMap<>();
/*     */   private final e m = new e();
/*     */   
/*     */   private class a extends Thread {
/*     */     IOException e;
/*     */     final PipedOutputStream f;
/*     */     
/*     */     a(a this$0, PipedOutputStream out) {
/*     */       this.f = out;
/*     */     } }
/*     */   private a n = null;
/*     */   private boolean r = false;
/*     */   private boolean s = false;
/*     */   
/*     */   public InputStream a(URI uri) throws IOException {
/* 136 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/*     */     try {
/* 138 */       TransformerHandler handler = (TransformerHandler)SAXTransformerFactory.newInstance().newTransformer();
/* 139 */       handler.setResult(new StreamResult(out));
/* 140 */       Transformer tr = handler.getTransformer();
/* 141 */       tr.setOutputProperty("method", "xml");
/* 142 */       tr.setOutputProperty("encoding", "UTF-8");
/* 143 */       tr.setOutputProperty("indent", "yes");
/* 144 */       AttributesImpl atts = new AttributesImpl();
/* 145 */       if (uri.equals(f)) {
/*     */         
/* 147 */         handler.startDocument();
/* 148 */         handler.startElement("", "version", "version", atts);
/*     */         
/* 150 */         handler.startElement("", "long-version", "long-version", atts);
/* 151 */         String data = jp.cssj.homare.a.a.g;
/* 152 */         handler.characters(data.toCharArray(), 0, data.length());
/* 153 */         handler.endElement("", "long-version", "long-version");
/*     */ 
/*     */         
/* 156 */         handler.startElement("", "name", "name", atts);
/* 157 */         data = jp.cssj.homare.a.a.b;
/* 158 */         handler.characters(data.toCharArray(), 0, data.length());
/* 159 */         handler.endElement("", "name", "name");
/*     */ 
/*     */         
/* 162 */         handler.startElement("", "number", "number", atts);
/* 163 */         data = jp.cssj.homare.a.a.c;
/* 164 */         handler.characters(data.toCharArray(), 0, data.length());
/* 165 */         handler.endElement("", "number", "number");
/*     */ 
/*     */         
/* 168 */         handler.startElement("", "build", "build", atts);
/* 169 */         data = jp.cssj.homare.a.a.d;
/* 170 */         handler.characters(data.toCharArray(), 0, data.length());
/* 171 */         handler.endElement("", "build", "build");
/*     */ 
/*     */         
/* 174 */         handler.startElement("", "copyrights", "copyrights", atts);
/* 175 */         data = jp.cssj.homare.a.a.e;
/* 176 */         handler.characters(data.toCharArray(), 0, data.length());
/* 177 */         handler.endElement("", "copyrights", "copyrights");
/*     */ 
/*     */         
/* 180 */         handler.startElement("", "credits", "credits", atts);
/* 181 */         data = jp.cssj.homare.a.a.f;
/* 182 */         handler.characters(data.toCharArray(), 0, data.length());
/* 183 */         handler.endElement("", "credits", "credits");
/*     */         
/* 185 */         handler.endElement("", "version", "version");
/* 186 */         handler.endDocument();
/* 187 */       } else if (uri.equals(g)) {
/*     */         
/* 189 */         handler.startDocument();
/* 190 */         handler.startElement("", "output-types", "output-types", atts);
/* 191 */         for (Iterator<?> i = b.a().a(UserAgentFactory.class); i.hasNext(); ) {
/* 192 */           UserAgentFactory uaf = (UserAgentFactory)i.next();
/* 193 */           for (Iterator<?> j = uaf.types(); j.hasNext(); ) {
/* 194 */             UserAgentFactory.a type = (UserAgentFactory.a)j.next();
/* 195 */             atts.addAttribute("", "name", "name", "CDATA", type.a);
/* 196 */             atts.addAttribute("", "mimeType", "mimeType", "CDATA", type.b);
/* 197 */             atts.addAttribute("", "suffix", "suffix", "CDATA", type.c);
/* 198 */             handler.startElement("", "type", "type", atts);
/* 199 */             atts.clear();
/* 200 */             handler.endElement("", "type", "type");
/*     */           } 
/*     */         } 
/* 203 */         handler.endElement("", "output-types", "output-types");
/* 204 */         handler.endDocument();
/* 205 */       } else if (uri.equals(h)) {
/*     */         
/* 207 */         h fsm = e();
/* 208 */         g[] fonts = fsm.a(null);
/*     */         
/* 210 */         handler.startDocument();
/* 211 */         handler.startElement("", "fonts", "fonts", atts);
/* 212 */         for (int i = 0; i < fonts.length; i++) {
/* 213 */           String directionStr; g font = fonts[i];
/* 214 */           atts.addAttribute("", "name", "name", "CDATA", font.d());
/* 215 */           if (font.b()) {
/* 216 */             atts.addAttribute("", "italic", "italic", "CDATA", "true");
/*     */           }
/* 218 */           atts.addAttribute("", "weight", "weight", "CDATA", String.valueOf(font.c()));
/* 219 */           if (font instanceof f) {
/*     */             String typeStr;
/* 221 */             byte type = ((f)font).h_();
/* 222 */             switch (type) {
/*     */               case 0:
/* 224 */                 typeStr = "missing";
/*     */                 break;
/*     */               case 2:
/* 227 */                 typeStr = "embedded";
/*     */                 break;
/*     */               case 1:
/* 230 */                 typeStr = "core";
/*     */                 break;
/*     */               case 4:
/* 233 */                 typeStr = "cid-keyed";
/*     */                 break;
/*     */               case 3:
/* 236 */                 typeStr = "cid-identity";
/*     */                 break;
/*     */               default:
/* 239 */                 throw new IllegalStateException();
/*     */             } 
/* 241 */             atts.addAttribute("", "type", "type", "CDATA", String.valueOf(typeStr));
/*     */           } 
/*     */ 
/*     */           
/* 245 */           byte direction = font.e();
/* 246 */           switch (direction) {
/*     */             case 1:
/* 248 */               directionStr = "ltr";
/*     */               break;
/*     */             case 2:
/* 251 */               directionStr = "rtl";
/*     */               break;
/*     */             case 3:
/* 254 */               directionStr = "tb";
/*     */               break;
/*     */             default:
/* 257 */               throw new IllegalStateException();
/*     */           } 
/* 259 */           atts.addAttribute("", "direction", "direction", "CDATA", String.valueOf(directionStr));
/*     */           
/* 261 */           handler.startElement("", "font", "font", atts);
/* 262 */           atts.clear();
/*     */           
/* 264 */           String[] aliases = font.a();
/* 265 */           for (int j = 0; j < aliases.length; j++) {
/* 266 */             atts.addAttribute("", "name", "name", "CDATA", aliases[j]);
/* 267 */             handler.startElement("", "alias", "alias", atts);
/* 268 */             atts.clear();
/* 269 */             handler.endElement("", "alias", "alias");
/*     */           } 
/*     */           
/* 272 */           handler.endElement("", "font", "font");
/*     */         } 
/* 274 */         handler.endElement("", "fonts", "fonts");
/* 275 */         handler.endDocument();
/*     */       } 
/* 277 */     } catch (TransformerConfigurationException transformerConfigurationException) {
/* 278 */       throw new RuntimeException(transformerConfigurationException);
/* 279 */     } catch (SAXException sAXException) {
/* 280 */       throw new RuntimeException(sAXException);
/*     */     } 
/* 282 */     return new ByteArrayInputStream(out.toByteArray());
/*     */   }
/*     */   private boolean t = true; private boolean u = false; private static final Logger d; private static final Set<String> e; private static final URI f; private static final URI g; private static final URI h; private File o; private static final Map<File, h> p; private m q;
/*     */   public File c() {
/* 286 */     if (this.o == null) {
/* 287 */       this.o = DirectDriver.getProfileFile(null);
/*     */     }
/* 289 */     return this.o;
/*     */   }
/*     */   
/*     */   public void a(File profileFile) {
/* 293 */     this.o = profileFile;
/*     */   }
/*     */   
/*     */   public void a(short code, String[] args) {
/* 297 */     a(code, args, null);
/*     */   }
/*     */   
/*     */   public void a(short code, String[] args, String mes) {
/* 301 */     if (this.k != null) {
/* 302 */       if (this.t && mes == null) {
/* 303 */         mes = jp.cssj.homare.a.a.b(code, args);
/*     */       }
/* 305 */       this.k.a(code, args, mes);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean d() {
/* 310 */     return this.t;
/*     */   }
/*     */   
/*     */   public void b(boolean decodeMessage) {
/* 314 */     this.t = decodeMessage;
/*     */   }
/*     */   
/*     */   public void a(c results) {
/* 318 */     if (!c && results == null) throw new AssertionError(); 
/* 319 */     this.i = results;
/*     */   }
/*     */   
/*     */   public void a(m ua) {
/* 323 */     if (!c && ua == null) throw new AssertionError(); 
/* 324 */     this.q = ua;
/*     */   }
/*     */   
/*     */   public void a(b progressListener) {
/* 328 */     this.j = progressListener;
/*     */   }
/*     */   
/*     */   public void a(a messageHandler) {
/* 332 */     this.k = messageHandler;
/*     */   }
/*     */   
/*     */   public void a(String name, String value) throws IOException {
/* 336 */     if (e.contains(name)) {
/* 337 */       b(name, value);
/*     */     }
/* 339 */     else if (value == null || value.length() == 0) {
/* 340 */       this.l.remove(name);
/*     */     } else {
/* 342 */       this.l.put(name, value);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(boolean continuous) {
/* 348 */     this.r = continuous;
/*     */   }
/*     */   
/*     */   private void b(String name, String value) {
/* 352 */     if (name.equals("input.include")) {
/*     */       
/*     */       try {
/* 355 */         URI uri = d.a("UTF-8", value);
/* 356 */         this.m.a(uri);
/* 357 */       } catch (URISyntaxException uRISyntaxException) {
/* 358 */         a((short)10251, new String[] { value });
/*     */       } 
/* 360 */     } else if (name.equals("input.exclude")) {
/*     */       try {
/* 362 */         URI uri = d.a("UTF-8", value);
/* 363 */         this.m.c(uri);
/* 364 */       } catch (URISyntaxException uRISyntaxException) {
/* 365 */         a((short)10251, new String[] { value });
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public OutputStream a(jp.cssj.e.a metaSource) throws IOException {
/* 371 */     File file = this.m.a(metaSource);
/* 372 */     OutputStream out = new FileOutputStream(file);
/* 373 */     return out;
/*     */   }
/*     */   
/*     */   public void a(b source) throws IOException {
/* 377 */     try(OutputStream out = a((jp.cssj.e.a)source); InputStream in = source.h()) {
/* 378 */       IOUtils.copy(in, out);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(c resolver) {
/* 383 */     this.m.a(resolver);
/*     */   }
/*     */   
/*     */   public void b(URI uri) throws IOException, d {
/* 387 */     c(uri);
/* 388 */     b source = this.m.a(uri, true); try {
/*     */       jp.cssj.e.h.a a1;
/* 390 */       b xsource = source;
/* 391 */       if (this.j != null) {
/*     */         try {
/* 393 */           long srcLength = source.b();
/* 394 */           if (srcLength != -1L) {
/* 395 */             this.j.a(srcLength);
/*     */           }
/* 397 */           InputStream in = source.h();
/* 398 */           in = new BufferedInputStream((InputStream)new f(in, this.j));
/* 399 */           a1 = new jp.cssj.e.h.a(uri, in, source.c(), source.a());
/* 400 */         } catch (IOException iOException) {
/* 401 */           throw new FileNotFoundException();
/*     */         } 
/*     */       }
/* 404 */       b((b)a1);
/* 405 */     } catch (FileNotFoundException fileNotFoundException) {
/* 406 */       short code = 14342;
/* 407 */       String[] args = { uri.toString() };
/* 408 */       a((short)14342, args);
/* 409 */       throw new d((byte)2, (short)14342, args, 
/* 410 */           jp.cssj.homare.a.a.b((short)14342, args));
/*     */     } finally {
/* 412 */       this.m.a(source);
/*     */     } 
/*     */   }
/*     */   
/*     */   public OutputStream b(jp.cssj.e.a metaSource) throws IOException {
/* 417 */     c(metaSource.d());
/* 418 */     PipedOutputStream out = new PipedOutputStream(this) {
/*     */         public void close() throws IOException {
/* 420 */           super.close();
/* 421 */           this.a.f();
/*     */         }
/*     */       };
/* 424 */     String outputType = B.J.a(this.l);
/* 425 */     PipedInputStream in = new PipedInputStream(out);
/* 426 */     this.n = new a(this, out, in, metaSource, outputType) {
/*     */         public void run() {
/*     */           try {
/* 429 */             InputStream xin = this.a;
/* 430 */             if (a.a(this.d) != null) {
/* 431 */               if (this.b.b() != -1L) {
/* 432 */                 a.a(this.d).a(this.b.b());
/*     */               }
/* 434 */               xin = new BufferedInputStream((InputStream)new f(this.a, a.a(this.d)));
/*     */             } 
/* 436 */             jp.cssj.e.h.a a1 = new jp.cssj.e.h.a(this.b.d(), xin, this.c, this.b.a());
/* 437 */             this.d.b((b)a1);
/* 438 */           } catch (IOException e) {
/* 439 */             this.e = e;
/*     */           } 
/*     */         }
/*     */       };
/* 443 */     this.n.start();
/* 444 */     return out;
/*     */   }
/*     */   protected h e() throws IOException {
/*     */     jp.cssj.sakae.pdf.c.a a1;
/* 448 */     File dir = c().getParentFile();
/* 449 */     String systemFonts = this.l.get("system.fonts");
/* 450 */     if (systemFonts == null) {
/* 451 */       systemFonts = "fonts/fonts.xml";
/*     */     }
/* 453 */     File fontSource = (new File(dir, systemFonts)).getCanonicalFile();
/*     */     
/* 455 */     synchronized (p) {
/* 456 */       h fsm = p.get(fontSource);
/* 457 */       if (fsm == null) {
/* 458 */         File fontDb = new File(dir, systemFonts + ".db");
/* 459 */         a1 = new jp.cssj.sakae.pdf.c.a((b)new jp.cssj.e.d.a(fontSource), fontDb);
/* 460 */         p.put(fontSource, a1);
/*     */       } 
/*     */     } 
/* 463 */     return (h)a1;
/*     */   }
/*     */   
/*     */   public void b(b source) throws IOException, d {
/* 467 */     URI uri = source.d();
/* 468 */     c(uri);
/*     */ 
/*     */     
/* 471 */     this.q.a(this.m);
/* 472 */     this.q.a(this);
/* 473 */     this.q.a(this.l);
/*     */     
/* 475 */     h fsm = e();
/* 476 */     this.q.a().a(fsm);
/*     */ 
/*     */     
/*     */     try {
/* 480 */       c(source);
/* 481 */       if (!this.r) {
/* 482 */         this.q.t();
/*     */       }
/* 484 */     } catch (jp.cssj.homare.ua.a a1) {
/*     */       
/* 486 */       this.r = false;
/* 487 */       short code = 4097;
/* 488 */       String mes = jp.cssj.homare.a.a.b(code, null);
/* 489 */       if (a1.a() == 1) {
/*     */         try {
/* 491 */           this.q.t();
/* 492 */         } catch (b e1) {
/* 493 */           throw new d((byte)2, code, null, mes);
/*     */         } 
/*     */       } else {
/* 496 */         throw new d((byte)2, code, null, mes);
/*     */       } 
/* 498 */     } catch (d d) {
/* 499 */       this.r = false;
/*     */       
/* 501 */       if (d.c() == 1) {
/*     */         try {
/* 503 */           this.q.t();
/* 504 */         } catch (b e1) {
/* 505 */           throw new d((byte)2, d.a(), d.b(), d
/* 506 */               .getMessage());
/*     */         } 
/*     */         return;
/*     */       } 
/* 510 */       throw d;
/* 511 */     } catch (FileNotFoundException fileNotFoundException) {
/* 512 */       this.r = false;
/* 513 */       short code = 14342;
/* 514 */       String[] args = { uri.toString() };
/* 515 */       a(code, args);
/* 516 */       throw new d((byte)2, code, args, 
/* 517 */           jp.cssj.homare.a.a.b(code, args));
/* 518 */     } catch (Throwable t) {
/* 519 */       this.r = false;
/* 520 */       this.q.a((short)16385, t.getMessage());
/* 521 */       d.log(Level.SEVERE, "予期しないエラー", t);
/* 522 */       short code = 16385;
/* 523 */       String mes = jp.cssj.homare.a.a.b(code, new String[] { t.getMessage() });
/* 524 */       if (!B.aB.a(this.q)) {
/*     */         try {
/* 526 */           this.q.t();
/* 527 */         } catch (b e1) {
/* 528 */           throw new d((byte)2, code, null, mes);
/*     */         } 
/*     */         return;
/*     */       } 
/* 532 */       throw new d((byte)2, code, null, mes);
/*     */     } finally {
/* 534 */       if (!this.r) {
/* 535 */         this.q.v();
/* 536 */         this.q = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void h() throws IOException {
/* 542 */     File profileFile = c();
/* 543 */     Properties defaultProperties = new Properties();
/* 544 */     try (InputStream in = new FileInputStream(profileFile)) {
/* 545 */       defaultProperties.load(in);
/* 546 */     } catch (IOException iOException) {
/* 547 */       a((short)10268, new String[] { String.valueOf(profileFile) });
/*     */     } 
/* 549 */     for (Iterator<?> i = defaultProperties.entrySet().iterator(); i.hasNext(); ) {
/* 550 */       Map.Entry<?, ?> entry = (Map.Entry<?, ?>)i.next();
/* 551 */       String name = (String)entry.getKey();
/* 552 */       String value = (String)entry.getValue();
/* 553 */       a(name, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void c(URI uri) throws IOException, d {
/* 558 */     if (this.q != null) {
/*     */       return;
/*     */     }
/* 561 */     if (this.i == null) {
/* 562 */       throw new IllegalStateException("Resultsが設定されていません。");
/*     */     }
/* 564 */     h();
/*     */ 
/*     */     
/* 567 */     for (int i = 0;; i++) {
/* 568 */       String exName = "input.exclude." + i;
/* 569 */       String inName = "input.include." + i;
/* 570 */       String exValue = this.l.get(exName);
/* 571 */       String inValue = this.l.get(inName);
/* 572 */       if (exValue == null && inValue == null) {
/*     */         break;
/*     */       }
/* 575 */       if (exValue != null) {
/* 576 */         b("input.exclude", exValue);
/*     */       }
/* 578 */       if (inValue != null) {
/* 579 */         b("input.include", inValue);
/*     */       }
/*     */     } 
/*     */     
/* 583 */     this.m.a(uri, this.l, this);
/* 584 */     this.s = false;
/*     */     
/* 586 */     String outputType = B.J.a(this.l);
/* 587 */     UserAgentFactory factory = (UserAgentFactory)b.a().a(UserAgentFactory.class, outputType);
/*     */     
/* 589 */     if (factory != null) {
/* 590 */       this.q = factory.createUserAgent();
/*     */     } else {
/* 592 */       throw new IllegalStateException("UnsupportedType: " + outputType);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void f() throws IOException, d {
/* 597 */     if (this.n != null) {
/*     */       try {
/* 599 */         this.n.join();
/* 600 */       } catch (InterruptedException interruptedException) {}
/*     */ 
/*     */       
/*     */       try {
/* 604 */         if (this.n.e != null) {
/* 605 */           throw this.n.e;
/*     */         }
/*     */       } finally {
/* 608 */         this.n = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a() throws IOException {
/* 614 */     this.u = false;
/* 615 */     if (this.q == null) {
/*     */       return;
/*     */     }
/*     */     try {
/* 619 */       this.q.t();
/* 620 */     } catch (b b1) {
/* 621 */       short code = 16385;
/* 622 */       String mes = jp.cssj.homare.a.a.b(code, new String[] { b1.getMessage() });
/* 623 */       throw new d((byte)2, code, null, mes);
/*     */     } finally {
/* 625 */       this.q.v();
/* 626 */       this.q = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(byte mode) throws IOException {
/* 631 */     if (!this.s && this.q != null) {
/* 632 */       this.q.a(mode);
/* 633 */       this.s = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void g() throws IOException {
/* 638 */     h();
/*     */   }
/*     */   
/*     */   public void b() throws IOException {
/*     */     try {
/* 643 */       if (this.q != null) {
/* 644 */         a((byte)2);
/* 645 */         if (this.n != null) {
/* 646 */           this.n.f.close();
/*     */         }
/*     */       } 
/*     */     } finally {
/* 650 */       this.q = null;
/* 651 */       this.u = false;
/* 652 */       this.l.clear();
/* 653 */       h();
/* 654 */       this.m.a();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 659 */     b();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void c(b source) throws jp.cssj.homare.ua.a, d {
/* 671 */     Formatter formatter = (Formatter)b.a().a(Formatter.class, source);
/* 672 */     c results = this.i;
/* 673 */     long limit = B.M.a(this.q);
/* 674 */     if (limit != -1L) {
/* 675 */       results = new b(results, limit, this.q);
/*     */     }
/* 677 */     int passCount = B.ay.a(this.q);
/*     */     try {
/* 679 */       if (passCount == 1) {
/*     */         
/* 681 */         byte mode = 0;
/* 682 */         boolean middlePath = B.az.a(this.q);
/* 683 */         if (this.u != middlePath) {
/* 684 */           mode = middlePath ? 1 : 2;
/* 685 */           if (middlePath) {
/* 686 */             ((j)this.q).a(results);
/*     */           }
/*     */         } 
/* 689 */         if (!middlePath) {
/* 690 */           ((j)this.q).a(results);
/*     */         }
/* 692 */         this.u = middlePath;
/* 693 */         this.q.f(mode);
/* 694 */         this.q.c().a(source.d());
/* 695 */         this.q.a().a(passCount);
/* 696 */         this.q.a((short)6147, String.valueOf(passCount));
/* 697 */         formatter.format(source, this.q);
/*     */       } else {
/*     */         
/* 700 */         ((j)this.q).a(results);
/* 701 */         File tmpFile = null;
/*     */         try {
/* 703 */           tmpFile = File.createTempFile("copper", ".tmp");
/*     */           
/* 705 */           this.q.f((byte)1);
/* 706 */           this.q.c().a(source.d());
/* 707 */           this.q.a().a(passCount);
/* 708 */           this.q.a((short)6147, String.valueOf(passCount));
/* 709 */           try(FileOutputStream out = new FileOutputStream(tmpFile); 
/* 710 */               TeeInputStream in = new TeeInputStream(source.h(), out)) {
/*     */             
/* 712 */             jp.cssj.e.h.a a1 = new jp.cssj.e.h.a(source.d(), (InputStream)teeInputStream, source.c(), source.a());
/* 713 */             formatter.format((b)a1, this.q);
/*     */           } 
/*     */           
/* 716 */           for (; --passCount > 1; passCount--) {
/* 717 */             this.q.f((byte)1);
/* 718 */             this.q.c().a(source.d());
/* 719 */             this.q.a().a(passCount);
/* 720 */             this.q.a((short)6147, String.valueOf(passCount));
/* 721 */             try (InputStream in = new FileInputStream(tmpFile)) {
/*     */               
/* 723 */               jp.cssj.e.h.a a1 = new jp.cssj.e.h.a(source.d(), inputStream, source.c(), source.a());
/* 724 */               formatter.format((b)a1, this.q);
/*     */             } 
/*     */           } 
/*     */           
/* 728 */           this.q.f((byte)2);
/* 729 */           this.q.c().a(source.d());
/* 730 */           try (InputStream in = new FileInputStream(tmpFile)) {
/*     */             
/* 732 */             jp.cssj.e.h.a a1 = new jp.cssj.e.h.a(source.d(), in, source.c(), source.a());
/* 733 */             this.q.a().a(passCount);
/* 734 */             this.q.a((short)6147, String.valueOf(passCount));
/* 735 */             formatter.format((b)a1, this.q);
/*     */           } 
/*     */         } finally {
/* 738 */           if (tmpFile != null) {
/* 739 */             tmpFile.delete();
/*     */           }
/*     */         } 
/*     */       } 
/* 743 */     } catch (IOException iOException) {
/* 744 */       short code = 12290;
/* 745 */       String[] args = { iOException.getMessage() };
/* 746 */       String mes = jp.cssj.homare.a.a.b(code, args);
/* 747 */       this.q.a(code, args);
/* 748 */       d.log(Level.WARNING, mes, iOException);
/* 749 */       throw new d(code, args, mes);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/driver/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */