/*     */ package jp.cssj.sakae.pdf.c;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.TreeSet;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import jp.cssj.e.b;
/*     */ import jp.cssj.e.c;
/*     */ import jp.cssj.e.e.d;
/*     */ import jp.cssj.sakae.a.g;
/*     */ import jp.cssj.sakae.a.i;
/*     */ import jp.cssj.sakae.c.a.b;
/*     */ import jp.cssj.sakae.c.a.c;
/*     */ import jp.cssj.sakae.c.a.k;
/*     */ import jp.cssj.sakae.pdf.b;
/*     */ import jp.cssj.sakae.pdf.c.a.f;
/*     */ import jp.cssj.sakae.pdf.c.b.d;
/*     */ import jp.cssj.sakae.pdf.c.b.f;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ 
/*     */ class h
/*     */   extends DefaultHandler
/*     */ {
/*  42 */   private static final Logger d = Logger.getLogger(h.class.getName());
/*     */   
/*     */   private final URI e;
/*     */   
/*     */   private final c f;
/*     */   
/*     */   private static final byte g = 0;
/*     */   
/*     */   private static final byte h = 1;
/*     */   
/*     */   private static final byte i = 2;
/*     */   
/*     */   private static final byte j = 3;
/*     */   
/*     */   private static final byte k = 5;
/*     */   
/*     */   private static final byte l = 6;
/*     */   
/*  60 */   private byte m = 0;
/*     */   
/*     */   private a[] n;
/*     */   
/*     */   private f o;
/*     */   
/*  66 */   private final Map<String, d> p = new HashMap<>();
/*     */   
/*     */   private d q;
/*     */   
/*  70 */   private final Map<String, f> r = new HashMap<>();
/*     */   
/*  72 */   final Map<String, Object> a = new HashMap<>();
/*     */   
/*  74 */   final Map<String, c> b = new HashMap<>();
/*     */   
/*  76 */   final Collection<g> c = new ArrayList<>();
/*     */   
/*     */   h(URI base) throws IOException {
/*  79 */     this.e = base;
/*  80 */     this.f = (c)jp.cssj.e.b.a.a();
/*     */   }
/*     */   
/*     */   private File a(String src) throws SAXException {
/*     */     try {
/*  85 */       b source = this.f.b(d.a("UTF-8", this.e, src));
/*     */       try {
/*  87 */         return source.l();
/*     */       } finally {
/*  89 */         this.f.a(source);
/*     */       } 
/*  91 */     } catch (IOException e) {
/*  92 */       throw new SAXException(e);
/*  93 */     } catch (URISyntaxException e) {
/*  94 */       throw new SAXException(e);
/*     */     }  } public void startElement(String uri, String lName, String qName, Attributes atts) throws SAXException { String genericFamily; String fontFamily;
/*     */     List<b> entries;
/*     */     StringTokenizer i;
/*     */     c family;
/*  99 */     switch (this.m) {
/*     */       case 0:
/* 101 */         if (qName.equals("encodings")) {
/*     */ 
/*     */           
/* 104 */           this.m = 1; break;
/* 105 */         }  if (qName.equals("core-fonts")) {
/*     */           
/* 107 */           String encoding = atts.getValue("encoding");
/* 108 */           String unicodeSrc = atts.getValue("unicode-src");
/*     */           
/* 110 */           this.q = this.p.get(encoding);
/*     */           
/*     */           try {
/* 113 */             b source = this.f.b(d.a("UTF-8", this.e, unicodeSrc));
/*     */             try {
/* 115 */               this.o = f.a(source.h());
/*     */             } finally {
/* 117 */               this.f.a(source);
/*     */             } 
/* 119 */           } catch (IOException e) {
/* 120 */             throw new SAXException(e);
/* 121 */           } catch (URISyntaxException e) {
/* 122 */             throw new SAXException(e);
/*     */           } 
/* 124 */           this.m = 2; break;
/* 125 */         }  if (qName.equals("cmaps")) {
/*     */ 
/*     */           
/* 128 */           this.m = 3; break;
/* 129 */         }  if (qName.equals("cid-fonts")) {
/*     */           
/* 131 */           this.m = 5; break;
/* 132 */         }  if (qName.equals("generic-fonts"))
/*     */         {
/* 134 */           this.m = 6;
/*     */         }
/*     */         break;
/*     */       case 1:
/* 138 */         if (qName.equals("encoding")) {
/*     */           
/* 140 */           String src = atts.getValue("src");
/* 141 */           b source = null;
/*     */           try {
/* 143 */             source = this.f.b(d.a("UTF-8", this.e, src));
/* 144 */             d encoding = d.a(source.h());
/* 145 */             this.p.put(encoding.a, encoding);
/* 146 */           } catch (IOException e) {
/* 147 */             throw new SAXException(e);
/* 148 */           } catch (URISyntaxException e) {
/* 149 */             throw new SAXException(e);
/*     */           } finally {
/* 151 */             if (source != null) {
/* 152 */               this.f.a(source);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 2:
/* 159 */         if (qName.equals("letter-font")) {
/*     */           
/* 161 */           String src = atts.getValue("src");
/* 162 */           b source = null; try {
/*     */             d pdfEncoding;
/* 164 */             source = this.f.b(d.a("UTF-8", this.e, src));
/*     */ 
/*     */             
/* 167 */             String encoding = atts.getValue("encoding");
/* 168 */             if (encoding != null) {
/* 169 */               pdfEncoding = this.p.get(encoding);
/*     */             } else {
/* 171 */               pdfEncoding = this.q;
/*     */             } 
/*     */             
/* 174 */             this
/* 175 */               .n = new a[] { new a(b.a(this.o, pdfEncoding, source.h())) };
/* 176 */           } catch (Exception e) {
/* 177 */             d.log(Level.SEVERE, "AFMファイル'" + src + "'を読み込めません。", e);
/* 178 */             throw new SAXException(e);
/*     */           } finally {
/* 180 */             if (source != null)
/* 181 */               this.f.a(source); 
/*     */           } 
/*     */           break;
/*     */         } 
/* 185 */         if (qName.equals("symbol-font")) {
/*     */           
/* 187 */           String src = atts.getValue("src");
/* 188 */           b source = null, encodingSource = null;
/*     */           try {
/* 190 */             source = this.f.b(d.a("UTF-8", this.e, src));
/*     */             
/* 192 */             String encodingSrc = atts.getValue("encoding-src");
/* 193 */             encodingSource = this.f.b(d.a("UTF-8", this.e, encodingSrc));
/*     */             
/* 195 */             this
/* 196 */               .n = new a[] { new a(b.a(source.h(), encodingSource)) };
/* 197 */           } catch (Exception e) {
/* 198 */             d.log(Level.SEVERE, "AFMファイル'" + src + "'を読み込めません。", e);
/* 199 */             throw new SAXException(e);
/*     */           } finally {
/* 201 */             if (source != null) {
/* 202 */               this.f.a(source);
/*     */             }
/* 204 */             if (encodingSource != null) {
/* 205 */               this.f.a(encodingSource);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 3:
/* 212 */         if (qName.equals("cmap")) {
/*     */           
/* 214 */           String src = atts.getValue("src");
/* 215 */           String javaEncoding = atts.getValue("java-encoding");
/*     */           try {
/* 217 */             b source = this.f.b(d.a("UTF-8", this.e, src));
/* 218 */             f cmap = new f(source, javaEncoding);
/* 219 */             this.r.put(cmap.b(), cmap);
/* 220 */           } catch (Exception e) {
/* 221 */             d.log(Level.SEVERE, "CMapファイル'" + src + "'を読み込めません", e);
/* 222 */             throw new SAXException(e);
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 5:
/* 228 */         if (qName.equals("cid-keyed-font")) {
/* 229 */           String warraySrc = atts.getValue("warray");
/* 230 */           b source = null;
/*     */           try {
/* 232 */             source = this.f.b(d.a("UTF-8", this.e, warraySrc));
/*     */             
/* 234 */             jp.cssj.sakae.c.a.a face = b.a(atts);
/* 235 */             f[] sources = b.a(source, face, this.r);
/* 236 */             this.n = new a[sources.length];
/* 237 */             for (int j = 0; j < sources.length; j++) {
/* 238 */               this.n[j] = new a(sources[j]);
/*     */             }
/* 240 */           } catch (Exception e) {
/* 241 */             d.log(Level.SEVERE, "'" + source.d() + "'の読み込みに失敗しました。", e);
/* 242 */             throw new SAXException(e);
/*     */           } finally {
/* 244 */             if (source != null)
/* 245 */               this.f.a(source); 
/*     */           }  break;
/*     */         } 
/* 248 */         if (qName.equals("font-file")) {
/* 249 */           String src = atts.getValue("src");
/* 250 */           String types = atts.getValue("types");
/*     */           try {
/*     */             int index;
/* 253 */             File ttfFile = a(src);
/*     */             
/*     */             try {
/* 256 */               index = Integer.parseInt(atts.getValue("index"));
/* 257 */             } catch (Exception e) {
/* 258 */               index = 0;
/*     */             } 
/*     */             
/* 261 */             List<g> list = new ArrayList<>();
/* 262 */             jp.cssj.sakae.c.a.a face = b.a(atts);
/*     */             
/* 264 */             if (types.indexOf("cid-keyed") != -1) {
/* 265 */               b.a(list, face, (byte)3, ttfFile, index, this.r);
/*     */             }
/* 267 */             if (types.indexOf("cid-identity") != -1) {
/* 268 */               b.a(list, face, (byte)2, ttfFile, index, this.r);
/*     */             }
/* 270 */             if (types.indexOf("embedded") != -1) {
/* 271 */               b.a(list, face, (byte)1, ttfFile, index, this.r);
/*     */             }
/* 273 */             this.n = new a[list.size()];
/* 274 */             for (int j = 0; j < list.size(); j++) {
/* 275 */               this.n[j] = new a((f)list.get(j));
/*     */             }
/*     */           }
/* 278 */           catch (Exception e) {
/* 279 */             d.log(Level.WARNING, "'" + src + "'のフォント情報の取得に失敗しました。", e);
/* 280 */             this.n = null;
/*     */           }  break;
/* 282 */         }  if (qName.equals("font-dir")) {
/* 283 */           String dir = atts.getValue("dir");
/* 284 */           String types = atts.getValue("types");
/*     */           
/* 286 */           File dirFile = a(dir);
/* 287 */           if (d.isLoggable(Level.FINE)) {
/* 288 */             d.fine("scan: " + dirFile);
/*     */           }
/* 290 */           File[] files = dirFile.listFiles();
/* 291 */           if (files != null) {
/* 292 */             jp.cssj.sakae.c.a.a face = b.a(atts);
/* 293 */             for (int j = 0; j < files.length; j++) {
/* 294 */               File ttfFile = files[j];
/* 295 */               if (!ttfFile.isDirectory()) {
/*     */ 
/*     */                 
/* 298 */                 String name = ttfFile.getName().toLowerCase();
/* 299 */                 if (name.endsWith(".ttf") || name.endsWith(".ttc") || name.endsWith(".otf") || name
/* 300 */                   .endsWith(".woff"))
/*     */ 
/*     */                   
/*     */                   try { 
/* 304 */                     List<g> list = new ArrayList<>();
/*     */                     
/* 306 */                     int numFonts = 1;
/* 307 */                     try (RandomAccessFile raf = new RandomAccessFile(ttfFile, "r")) {
/* 308 */                       byte[] tagBytes = new byte[4];
/* 309 */                       raf.readFully(tagBytes);
/* 310 */                       String tag = new String(tagBytes, "ISO-8859-1");
/* 311 */                       if ("ttcf".equals(tag)) {
/*     */                         
/* 313 */                         raf.skipBytes(4);
/* 314 */                         numFonts = raf.readInt();
/*     */                       } 
/*     */                     } 
/*     */                     int k;
/* 318 */                     for (k = 0; k < numFonts; k++) {
/* 319 */                       if (types.indexOf("cid-identity") != -1) {
/* 320 */                         b.a(list, face, (byte)2, ttfFile, k, this.r);
/*     */                       }
/*     */                       
/* 323 */                       if (types.indexOf("embedded") != -1) {
/* 324 */                         b.a(list, face, (byte)1, ttfFile, k, this.r);
/*     */                       }
/*     */                     } 
/*     */                     
/* 328 */                     this.c.addAll(list);
/* 329 */                     for (k = 0; k < list.size(); k++) {
/* 330 */                       b.a(new a((f)list.get(k)), this.a);
/*     */                     } }
/* 332 */                   catch (Exception e)
/* 333 */                   { d.log(Level.WARNING, "'" + ttfFile + "'のフォント情報の取得に失敗しました。", e); }  
/*     */               } 
/*     */             } 
/*     */           }  break;
/* 337 */         }  if (qName.equals("system-font")) {
/* 338 */           String src = atts.getValue("src");
/* 339 */           String file = atts.getValue("file");
/* 340 */           String dir = atts.getValue("dir");
/* 341 */           String types = atts.getValue("types");
/*     */           
/* 343 */           List<g> list = new ArrayList<>();
/* 344 */           jp.cssj.sakae.c.a.a face = b.a(atts);
/*     */           
/*     */           try {
/* 347 */             if (file != null) {
/* 348 */               File theFile = a(file);
/* 349 */               try (InputStream in = new FileInputStream(theFile)) {
/* 350 */                 Font font = Font.createFont(0, in);
/* 351 */                 b.a(face, list, types, font, this.r);
/*     */               } 
/* 353 */             } else if (dir != null) {
/* 354 */               File theDir = a(dir);
/* 355 */               File[] files = theDir.listFiles();
/* 356 */               for (int j = 0; j < files.length; j++) {
/* 357 */                 File theFile = files[j];
/* 358 */                 String name = theFile.getName().toLowerCase();
/* 359 */                 if (name.endsWith(".ttf") || name.endsWith(".ttc") || name.endsWith(".otf") || name
/* 360 */                   .endsWith(".woff")) {
/* 361 */                   try (InputStream in = new FileInputStream(theFile)) {
/* 362 */                     Font font = Font.createFont(0, in);
/* 363 */                     b.a(face, list, types, font, this.r);
/*     */                   } 
/*     */                 }
/*     */               } 
/*     */             } else {
/* 368 */               Font font = Font.decode(src);
/* 369 */               b.a(face, list, types, font, this.r);
/*     */             } 
/* 371 */             this.n = list.<a>toArray(new a[list.size()]);
/* 372 */           } catch (Exception e) {
/* 373 */             d.log(Level.WARNING, "'" + src + "'のフォント情報の取得に失敗しました。", e);
/* 374 */             this.n = null;
/*     */           }  break;
/* 376 */         }  if (qName.equals("all-system-fonts")) {
/* 377 */           Font[] fonts; String types = atts.getValue("types");
/* 378 */           String dir = atts.getValue("dir");
/*     */ 
/*     */           
/* 381 */           if (dir != null) {
/* 382 */             File dirFile = a(dir);
/* 383 */             File[] files = dirFile.listFiles();
/* 384 */             List<Font> fontList = new ArrayList<>();
/* 385 */             for (int k = 0; k < files.length; k++) {
/*     */               
/* 387 */               try (InputStream in = new FileInputStream(files[k])) {
/* 388 */                 fontList.add(Font.createFont(0, in));
/*     */               }
/* 390 */               catch (Exception e) {
/* 391 */                 d.log(Level.WARNING, "フォントファイルを読み込めません", e);
/*     */               } 
/*     */             } 
/* 394 */             fonts = fontList.<Font>toArray(new Font[fontList.size()]);
/*     */           } else {
/* 396 */             fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
/*     */           } 
/* 398 */           jp.cssj.sakae.c.a.a face = b.a(atts);
/* 399 */           for (int j = 0; j < fonts.length; j++) {
/* 400 */             Font font = fonts[j];
/*     */             try {
/* 402 */               if (types.indexOf("cid-keyed") != -1) {
/*     */                 
/* 404 */                 a fontSource = new a(b.a(face, (byte)3, font, this.r));
/* 405 */                 this.c.add(fontSource);
/* 406 */                 b.a(fontSource, this.a);
/*     */               } 
/* 408 */               if (types.indexOf("cid-identity") != -1) {
/* 409 */                 a fontSource = new a(b.a(face, (byte)2, font, this.r));
/*     */                 
/* 411 */                 this.c.add(fontSource);
/* 412 */                 b.a(fontSource, this.a);
/*     */               } 
/* 414 */               if (types.indexOf("embedded") != -1) {
/*     */                 
/* 416 */                 a fontSource = new a(b.a(face, (byte)1, font, this.r));
/* 417 */                 this.c.add(fontSource);
/* 418 */                 b.a(fontSource, this.a);
/*     */               } 
/* 420 */             } catch (Exception e) {
/* 421 */               d.log(Level.WARNING, "'" + font.getFontName() + "'のフォント情報の取得に失敗しました。", e);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 6:
/* 428 */         genericFamily = lName;
/* 429 */         fontFamily = atts.getValue("font-family");
/*     */         
/* 431 */         entries = new ArrayList<>();
/* 432 */         for (i = new StringTokenizer(fontFamily, ","); i.hasMoreTokens(); ) {
/* 433 */           b entry = new b(i.nextToken());
/* 434 */           entries.add(entry);
/*     */         } 
/*     */         
/* 437 */         family = new c(entries.<b>toArray(new b[entries.size()]));
/* 438 */         this.b.put(genericFamily, family);
/*     */         break;
/*     */     } 
/* 441 */     if (this.n != null) {
/* 442 */       if (qName.equals("alias")) {
/*     */         
/* 444 */         String name = atts.getValue("name");
/* 445 */         for (int j = 0; j < this.n.length; j++) {
/* 446 */           this.n[j].a(name);
/*     */         }
/* 448 */       } else if (qName.equals("include")) {
/* 449 */         String unicodeRange = atts.getValue("unicode-range");
/* 450 */         for (StringTokenizer st = new StringTokenizer(unicodeRange, ","); st.hasMoreTokens(); ) {
/* 451 */           k range = k.a(st.nextToken());
/* 452 */           for (int j = 0; j < this.n.length; j++) {
/* 453 */             this.n[j].a(range);
/*     */           }
/*     */         } 
/* 456 */       } else if (qName.equals("exclude")) {
/* 457 */         String unicodeRange = atts.getValue("unicode-range");
/* 458 */         for (StringTokenizer st = new StringTokenizer(unicodeRange, ","); st.hasMoreTokens(); ) {
/* 459 */           k range = k.a(st.nextToken());
/* 460 */           for (int j = 0; j < this.n.length; j++) {
/* 461 */             this.n[j].b(range);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } }
/*     */ 
/*     */   
/*     */   public void endElement(String uri, String lName, String qName) throws SAXException {
/* 469 */     if (qName.equals("letter-font") || qName.equals("symbol-font") || qName.equals("cid-keyed-font") || qName
/* 470 */       .equals("font-file") || qName.equals("system-font")) {
/* 471 */       if (this.n == null) {
/* 472 */         throw new SAXException(qName);
/*     */       }
/* 474 */       for (int i = 0; i < this.n.length; i++) {
/* 475 */         this.c.add(this.n[i]);
/* 476 */         b.a(this.n[i], this.a);
/*     */       } 
/* 478 */       this.n = null;
/* 479 */     } else if (qName.equals("encodings") || qName.equals("core-fonts") || qName.equals("cmaps") || qName
/* 480 */       .equals("cid-fonts") || qName.equals("generic-fonts")) {
/* 481 */       if (this.n != null) {
/* 482 */         throw new SAXException(qName);
/*     */       }
/* 484 */       this.m = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static class a
/*     */     extends i implements f {
/*     */     private static final long j = 1L;
/* 491 */     protected final List<String> f = new ArrayList<>();
/*     */     
/* 493 */     protected final List<k> g = new ArrayList<>();
/*     */     
/* 495 */     protected final List<k> i = new ArrayList<>();
/*     */     
/* 497 */     private transient String[] k = null;
/*     */     
/*     */     public a(f source) {
/* 500 */       super(source);
/*     */     }
/*     */     
/*     */     public final synchronized void a(String aliase) {
/* 504 */       this.f.add(aliase);
/*     */     }
/*     */     
/*     */     public final synchronized void a(k range) {
/* 508 */       this.g.add(range);
/*     */     }
/*     */     
/*     */     public final synchronized void b(k range) {
/* 512 */       this.i.add(range);
/*     */     }
/*     */     
/*     */     public String[] a() {
/* 516 */       String[] aliases = this.W_.a();
/* 517 */       int count = aliases.length + this.f.size();
/* 518 */       if (this.k == null || this.k.length != count) {
/* 519 */         Set<String> result = new TreeSet<>();
/* 520 */         for (int j = 0; j < aliases.length; j++) {
/* 521 */           result.add(aliases[j]);
/*     */         }
/* 523 */         result.addAll(this.f);
/* 524 */         this.k = result.<String>toArray(new String[result.size()]);
/*     */       } 
/* 526 */       return this.k;
/*     */     }
/*     */     
/*     */     public boolean a(int c) {
/* 530 */       if (!this.i.isEmpty()) {
/* 531 */         for (int j = 0; j < this.i.size(); j++) {
/* 532 */           k range = this.i.get(j);
/* 533 */           if (range.a(c)) {
/* 534 */             return false;
/*     */           }
/*     */         } 
/*     */       }
/* 538 */       if (!this.g.isEmpty()) {
/* 539 */         for (int j = 0; j < this.g.size(); j++) {
/* 540 */           k range = this.g.get(j);
/* 541 */           if (range.a(c)) {
/* 542 */             return this.W_.a(c);
/*     */           }
/*     */         } 
/* 545 */         return false;
/*     */       } 
/* 547 */       return this.W_.a(c);
/*     */     }
/*     */     
/*     */     public e a(String name, b fontRef) {
/* 551 */       return ((f)this.W_).a(name, fontRef);
/*     */     }
/*     */     
/*     */     public byte h_() {
/* 555 */       return ((f)this.W_).h_();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */