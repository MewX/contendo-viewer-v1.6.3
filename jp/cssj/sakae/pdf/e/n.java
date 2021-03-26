/*      */ package jp.cssj.sakae.pdf.e;
/*      */ 
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.io.FilterOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.security.MessageDigest;
/*      */ import java.security.NoSuchAlgorithmException;
/*      */ import java.text.DateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import java.util.TimeZone;
/*      */ import javax.xml.transform.Transformer;
/*      */ import javax.xml.transform.sax.SAXTransformerFactory;
/*      */ import javax.xml.transform.sax.TransformerHandler;
/*      */ import javax.xml.transform.stream.StreamResult;
/*      */ import jp.cssj.e.b;
/*      */ import jp.cssj.f.a;
/*      */ import jp.cssj.f.a.b;
/*      */ import jp.cssj.sakae.a.e;
/*      */ import jp.cssj.sakae.a.g;
/*      */ import jp.cssj.sakae.a.j;
/*      */ import jp.cssj.sakae.c.a.e;
/*      */ import jp.cssj.sakae.c.b.b;
/*      */ import jp.cssj.sakae.e.d;
/*      */ import jp.cssj.sakae.pdf.a;
/*      */ import jp.cssj.sakae.pdf.a.a;
/*      */ import jp.cssj.sakae.pdf.b;
/*      */ import jp.cssj.sakae.pdf.c;
/*      */ import jp.cssj.sakae.pdf.c.c;
/*      */ import jp.cssj.sakae.pdf.d.b;
/*      */ import jp.cssj.sakae.pdf.e;
/*      */ import jp.cssj.sakae.pdf.f.a;
/*      */ import jp.cssj.sakae.pdf.f.b;
/*      */ import jp.cssj.sakae.pdf.f.i;
/*      */ import jp.cssj.sakae.pdf.f.j;
/*      */ import jp.cssj.sakae.pdf.g;
/*      */ import jp.cssj.sakae.pdf.g.b.d;
/*      */ import jp.cssj.sakae.pdf.h;
/*      */ import org.xml.sax.helpers.AttributesImpl;
/*      */ 
/*      */ public class n implements j, j {
/*      */   protected static final Random e;
/*      */   protected static final int f = 8192;
/*      */   private static final byte[] v;
/*      */   private static final byte[] w;
/*      */   private static final byte[] x;
/*      */   private static final byte[] y;
/*      */   private static final byte[] z;
/*      */   private static final byte[] A;
/*      */   private static final byte[] B;
/*      */   private static final byte[] C;
/*      */   final a g;
/*      */   final b h;
/*      */   private c D;
/*      */   protected final p i;
/*      */   private int E;
/*      */   d j;
/*      */   private final byte[][] F;
/*      */   
/*   67 */   static { e = new Random();
/*      */ 
/*      */ 
/*      */     
/*   71 */     v = new byte[] { 37, 80, 68, 70, 45 };
/*      */     
/*   73 */     w = new byte[] { 49, 46, 50 };
/*      */     
/*   75 */     x = new byte[] { 49, 46, 51 };
/*      */     
/*   77 */     y = new byte[] { 49, 46, 52 };
/*      */     
/*   79 */     z = new byte[] { 49, 46, 53 };
/*      */     
/*   81 */     A = new byte[] { 49, 46, 54 };
/*      */     
/*   83 */     B = new byte[] { 49, 46, 55 };
/*      */     
/*   85 */     C = new byte[80]; } final j k; final j l; final j m; final j n; final d o; final o p; final b q; final Map<String, b> r; private final Map<String, Integer> G; private final Map<Object, Object> H; private final i I; final h s; final e t; private final e J; private final c K; private final b L; private List<b> M; public n(a builder, b params) throws IOException {
/*      */     int m;
/*   87 */     for (int k = 0; k < 79; k++) {
/*   88 */       C[k] = 32;
/*      */     }
/*   90 */     C[79] = 10;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   97 */     this.D = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  103 */     this.E = 0;
/*      */ 
/*      */     
/*  106 */     this.j = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  137 */     this.r = new HashMap<>();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  142 */     this.G = new HashMap<>();
/*      */     
/*  144 */     this.H = new HashMap<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  164 */     this.M = null;
/*      */ 
/*      */     
/*  167 */     if (!u && builder == null) throw new AssertionError(); 
/*  168 */     if (builder.c()) {
/*  169 */       this.g = builder;
/*      */     } else {
/*  171 */       this.g = (a)new c(builder);
/*      */     } 
/*      */     
/*  174 */     if (params == null) {
/*  175 */       params = new b();
/*      */     }
/*  177 */     this.h = params;
/*      */     
/*  179 */     int id = e();
/*  180 */     this.g.b();
/*  181 */     b b1 = new b(this.g, id);
/*  182 */     this.k = new j((OutputStream)b1, this, id, -1, null);
/*      */ 
/*      */     
/*  185 */     int pdfVersion = this.h.h();
/*  186 */     this.k.write(v);
/*  187 */     switch (pdfVersion) {
/*      */       case 1200:
/*  189 */         this.k.write(w);
/*      */         break;
/*      */       
/*      */       case 1300:
/*  193 */         this.k.write(x);
/*      */         break;
/*      */       
/*      */       case 1400:
/*      */       case 1421:
/*  198 */         this.k.write(y);
/*      */         break;
/*      */       
/*      */       case 1412:
/*  202 */         this.k.write(y);
/*  203 */         this.k.k();
/*      */         
/*  205 */         this.k.write(37);
/*  206 */         for (m = 0; m < 4; m++) {
/*  207 */           this.k.write(e.nextInt(128) + 127);
/*      */         }
/*      */         break;
/*      */       
/*      */       case 1500:
/*  212 */         this.k.write(z);
/*      */         break;
/*      */       
/*      */       case 1600:
/*  216 */         this.k.write(A);
/*      */         break;
/*      */       
/*      */       case 1700:
/*  220 */         this.k.write(B);
/*      */         break;
/*      */       default:
/*  223 */         throw new IllegalStateException();
/*      */     } 
/*  225 */     this.k.k();
/*      */ 
/*      */     
/*  228 */     this.i = new p(this.k);
/*      */     
/*  230 */     this.k.g();
/*      */     
/*  232 */     this.k.a("Type");
/*  233 */     this.k.a("Catalog");
/*  234 */     this.k.k();
/*      */ 
/*      */     
/*  237 */     if (pdfVersion >= 1400) {
/*  238 */       this.k.a("Version");
/*  239 */       switch (pdfVersion) {
/*      */         case 1400:
/*      */         case 1412:
/*      */         case 1421:
/*  243 */           this.k.a("1.4");
/*      */           break;
/*      */         
/*      */         case 1500:
/*  247 */           this.k.a("1.5");
/*      */           break;
/*      */         
/*      */         case 1600:
/*  251 */           this.k.a("1.6");
/*      */           break;
/*      */         
/*      */         case 1700:
/*  255 */           this.k.a("1.7");
/*      */           break;
/*      */         default:
/*  258 */           throw new IllegalStateException();
/*      */       } 
/*  260 */       this.k.k();
/*      */     } 
/*      */ 
/*      */     
/*  264 */     this.k.a("Pages");
/*  265 */     b rootPageRef = this.i.a();
/*  266 */     this.k.b(rootPageRef);
/*  267 */     this.k.k();
/*      */ 
/*      */     
/*  270 */     b xmpmetaRef = null;
/*  271 */     if (params.h() >= 1400) {
/*  272 */       xmpmetaRef = this.i.a();
/*  273 */       this.k.a("Metadata");
/*  274 */       this.k.b(xmpmetaRef);
/*  275 */       this.k.k();
/*      */     } 
/*      */ 
/*      */     
/*  279 */     b outputIntentRef = null;
/*  280 */     if (params.h() >= 1400) {
/*  281 */       outputIntentRef = this.i.a();
/*  282 */       this.k.a("OutputIntents");
/*  283 */       this.k.i();
/*  284 */       this.k.b(outputIntentRef);
/*  285 */       this.k.j();
/*  286 */       this.k.k();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  302 */     this.l = this.k.c();
/*      */ 
/*      */     
/*  305 */     byte[] fileId = params.m();
/*  306 */     if (fileId == null) {
/*  307 */       fileId = new byte[16];
/*  308 */       synchronized (e) {
/*  309 */         e.nextBytes(fileId);
/*      */       } 
/*      */     } 
/*  312 */     this.F = new byte[][] { fileId, fileId };
/*      */ 
/*      */     
/*  315 */     this.k.h();
/*  316 */     this.k.a();
/*      */ 
/*      */     
/*  319 */     a encriptionParams = this.h.k();
/*  320 */     if (encriptionParams != null) {
/*  321 */       if (pdfVersion == 1412) {
/*  322 */         throw new IllegalArgumentException("PDF/A-1では暗号化は使用できません。");
/*      */       }
/*  324 */       int encType = encriptionParams.a();
/*  325 */       if (encType == 2 && pdfVersion < 1300) {
/*  326 */         throw new IllegalArgumentException("V2暗号化はPDF 1.3以降で使用できます。");
/*      */       }
/*  328 */       if (encType == 4) {
/*  329 */         if (pdfVersion < 1500) {
/*  330 */           throw new IllegalArgumentException("V4暗号化はPDF 1.5以降で使用できます。");
/*      */         }
/*  332 */         if (((i)encriptionParams).f() == 2 && 
/*  333 */           pdfVersion < 1600) {
/*  334 */           throw new IllegalArgumentException("AESV2暗号化はPDF 1.6以降で使用できます。");
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  339 */       this.j = new d(this.k, this.i, this.F, encriptionParams);
/*      */     } 
/*      */ 
/*      */     
/*  343 */     this.I = new i(this, rootPageRef);
/*      */ 
/*      */     
/*  346 */     if (xmpmetaRef != null) {
/*  347 */       this.m = this.k.c();
/*  348 */       this.m.a(xmpmetaRef);
/*      */     } else {
/*  350 */       this.m = null;
/*      */     } 
/*      */ 
/*      */     
/*  354 */     if (outputIntentRef != null) {
/*  355 */       String iccName, iccFile; int colors; this.k.a(outputIntentRef);
/*  356 */       this.k.g();
/*  357 */       this.k.a("Type");
/*  358 */       this.k.a("OutputIntent");
/*  359 */       this.k.k();
/*      */       
/*  361 */       this.k.a("S");
/*  362 */       if (params.h() == 1412) {
/*  363 */         this.k.a("GTS_PDFA1");
/*      */       } else {
/*  365 */         this.k.a("GTS_PDFX");
/*      */       } 
/*  367 */       this.k.k();
/*      */ 
/*      */ 
/*      */       
/*  371 */       if (pdfVersion == 1421) {
/*  372 */         iccName = "Probe Profile";
/*  373 */         iccFile = "Probev1_ICCv2.icc";
/*  374 */         colors = 4;
/*      */       } else {
/*  376 */         iccName = "sRGB IEC61966-2.1";
/*  377 */         iccFile = "sRGB_IEC61966-2-1_no_black_scaling.icc";
/*  378 */         colors = 3;
/*      */       } 
/*      */       
/*  381 */       this.k.a("OutputConditionIdentifier");
/*  382 */       this.k.c(iccName);
/*  383 */       this.k.k();
/*      */       
/*  385 */       b profRef = this.i.a();
/*  386 */       this.k.a("DestOutputProfile");
/*  387 */       this.k.b(profRef);
/*  388 */       this.k.k();
/*      */       
/*  390 */       this.k.h();
/*  391 */       this.k.a();
/*      */       
/*  393 */       this.k.a(profRef);
/*  394 */       this.k.g();
/*      */       
/*  396 */       this.k.a("N");
/*  397 */       this.k.a(colors);
/*  398 */       this.k.k();
/*      */       
/*  400 */       try(OutputStream pout = this.k.b((short)1); 
/*  401 */           InputStream in = n.class.getResourceAsStream(iccFile)) {
/*  402 */         byte[] buff = this.k.b(); int len;
/*  403 */         for (len = in.read(buff); len != -1; len = in.read(buff)) {
/*  404 */           pout.write(buff, 0, len);
/*      */         }
/*      */       } 
/*  407 */       this.k.a();
/*      */     } 
/*      */ 
/*      */     
/*  411 */     if (this.h.j()) {
/*  412 */       this.s = new h(this);
/*      */     } else {
/*  414 */       this.s = null;
/*      */     } 
/*      */ 
/*      */     
/*  418 */     this.o = new d(this);
/*      */ 
/*      */     
/*  421 */     this.t = new e(this, this, "Dests") {
/*      */         protected void a(Object entry) throws IOException {
/*  423 */           this.a.a((h.a)entry);
/*      */         }
/*      */       };
/*      */ 
/*      */     
/*  428 */     if (pdfVersion >= 1400 && pdfVersion != 1412) {
/*  429 */       this.J = new e(this, this, "EmbeddedFiles", pdfVersion) {
/*      */           protected void a(Object entry) throws IOException {
/*  431 */             this.a.g();
/*      */             
/*  433 */             this.a.a("Type");
/*  434 */             this.a.a("Filespec");
/*  435 */             this.a.k();
/*      */             
/*  437 */             a spec = (a)entry;
/*  438 */             a att = spec.a;
/*      */             
/*  440 */             this.a.a("F");
/*  441 */             this.a.a(new String[] { spec.b }, this.c.h.i());
/*  442 */             this.a.k();
/*      */             
/*  444 */             if (this.b >= 1700 && att.a != null) {
/*  445 */               this.a.a("UF");
/*  446 */               this.a.d(att.a);
/*  447 */               this.a.k();
/*      */             } 
/*      */             
/*  450 */             this.a.a("EF");
/*  451 */             this.a.g();
/*  452 */             this.a.a("F");
/*  453 */             this.a.b(spec.c);
/*  454 */             this.a.h();
/*      */             
/*  456 */             this.a.h();
/*      */           }
/*      */         };
/*      */     } else {
/*  460 */       this.J = null;
/*      */     } 
/*      */ 
/*      */     
/*  464 */     this.q = this.i.a();
/*  465 */     this.k.a(this.q);
/*  466 */     this.p = new o(this.k);
/*  467 */     this.k.a();
/*      */ 
/*      */     
/*  470 */     this.n = this.k.c();
/*  471 */     this.L = new b(this.r, this.n, this.i);
/*  472 */     this.K = new c(this.r, this.n, this.i, this.h);
/*      */   }
/*      */   
/*      */   public n(a builder) throws IOException {
/*  476 */     this(builder, new b());
/*      */   }
/*      */   
/*      */   public b a() {
/*  480 */     return this.h;
/*      */   }
/*      */   
/*      */   public Object a(Object key) {
/*  484 */     return this.H.get(key);
/*      */   }
/*      */   
/*      */   public void a(Object key, Object value) {
/*  488 */     this.H.put(key, value);
/*      */   }
/*      */   
/*      */   public e b() {
/*  492 */     if (this.D == null) {
/*  493 */       this.D = new c(this.h.a(), this);
/*      */     }
/*  495 */     return (e)this.D;
/*      */   }
/*      */   
/*      */   protected int e() {
/*  499 */     return this.E++;
/*      */   }
/*      */   
/*      */   protected b f() {
/*  503 */     b ocgRef = this.i.a();
/*  504 */     if (this.M == null) {
/*  505 */       this.M = new ArrayList<>();
/*      */     }
/*  507 */     this.M.add(ocgRef);
/*  508 */     return ocgRef;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public e a(g source) throws IOException {
/*  519 */     return this.L.a(source);
/*      */   }
/*      */   
/*      */   public b a(b source) throws IOException {
/*  523 */     return this.K.a(source);
/*      */   }
/*      */   
/*      */   public b a(BufferedImage image) throws IOException {
/*  527 */     return this.K.a(image);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String a(String type, String prefix, b resourceRef) throws IOException {
/*  540 */     Integer num = this.G.get(type);
/*  541 */     if (num == null) {
/*  542 */       num = d.a(0);
/*      */     } else {
/*  544 */       num = d.a(num.intValue() + 1);
/*      */     } 
/*  546 */     this.G.put(type, num);
/*  547 */     String name = prefix + num;
/*  548 */     this.r.put(name, resourceRef);
/*  549 */     return name;
/*      */   }
/*      */   
/*      */   public g c() throws IOException {
/*  553 */     b gsRef = this.i.a();
/*  554 */     String name = a("ExtGState", "G", gsRef);
/*  555 */     j gsOut = this.n;
/*  556 */     gsOut.a(gsRef);
/*  557 */     gsOut.g();
/*      */     
/*  559 */     gsOut.a("Type");
/*  560 */     gsOut.a("ExtGState");
/*  561 */     gsOut.k();
/*      */     
/*  563 */     g sgs = new g(this, (OutputStream)gsOut, this.h.i(), name, gsOut) {
/*      */         public String a() {
/*  565 */           return this.a;
/*      */         }
/*      */         
/*      */         public void close() throws IOException {
/*  569 */           flush();
/*  570 */           this.b.h();
/*  571 */           this.b.a();
/*      */         }
/*      */       };
/*  574 */     return sgs;
/*      */   }
/*      */ 
/*      */   
/*      */   public b a(double width, double height) throws IOException {
/*  579 */     if (a().h() < 1400) {
/*  580 */       throw new UnsupportedOperationException("Form Type 1 Group feature requres PDF >= 1.4.");
/*      */     }
/*  582 */     b imageRef = this.i.a();
/*  583 */     String name = a("XObject", "T", imageRef);
/*      */     
/*  585 */     j objectsFlow = this.n;
/*      */     
/*  587 */     objectsFlow.a(imageRef);
/*  588 */     objectsFlow.g();
/*      */     
/*  590 */     objectsFlow.a("Type");
/*  591 */     objectsFlow.a("XObject");
/*  592 */     objectsFlow.k();
/*  593 */     objectsFlow.a("Subtype");
/*  594 */     objectsFlow.a("Form");
/*  595 */     objectsFlow.k();
/*  596 */     objectsFlow.a("FormType");
/*  597 */     objectsFlow.a(1);
/*  598 */     objectsFlow.k();
/*      */     
/*  600 */     objectsFlow.a("Group");
/*  601 */     objectsFlow.g();
/*  602 */     objectsFlow.a("Type");
/*  603 */     objectsFlow.a("Group");
/*  604 */     objectsFlow.a("S");
/*  605 */     objectsFlow.a("Transparency");
/*  606 */     objectsFlow.h();
/*      */     
/*  608 */     objectsFlow.a("Resources");
/*  609 */     o newResourceFlow = new o(objectsFlow);
/*  610 */     objectsFlow.k();
/*      */ 
/*      */ 
/*      */     
/*  614 */     objectsFlow.a("Matrix");
/*  615 */     objectsFlow.i();
/*  616 */     objectsFlow.a(1.0D / width);
/*  617 */     objectsFlow.a(0.0D);
/*  618 */     objectsFlow.a(0.0D);
/*  619 */     objectsFlow.a(1.0D / height);
/*  620 */     objectsFlow.a(0.0D);
/*  621 */     objectsFlow.a(0.0D);
/*  622 */     objectsFlow.j();
/*  623 */     objectsFlow.k();
/*      */     
/*  625 */     objectsFlow.a("BBox");
/*  626 */     objectsFlow.i();
/*  627 */     objectsFlow.a(0);
/*  628 */     objectsFlow.a(0.0D);
/*  629 */     objectsFlow.a(width);
/*  630 */     objectsFlow.a(height);
/*  631 */     objectsFlow.j();
/*  632 */     objectsFlow.k();
/*      */     
/*  634 */     c formFlow = objectsFlow.c();
/*  635 */     c groupFlow = objectsFlow.c();
/*  636 */     OutputStream groupOut = groupFlow.b((short)2);
/*  637 */     objectsFlow.a();
/*      */     
/*  639 */     return new k(this, groupOut, groupFlow, newResourceFlow, width, height, name, imageRef, formFlow);
/*      */   }
/*      */ 
/*      */   
/*      */   public f a(double width, double height, double pageHeight, AffineTransform at) throws IOException {
/*      */     double scx, scy, shx, shy, tx, ty;
/*  645 */     if (!u && at != null && at.getScaleX() == 0.0D) throw new AssertionError(); 
/*  646 */     if (!u && at != null && at.getScaleY() == 0.0D) throw new AssertionError();
/*      */     
/*  648 */     b patternRef = this.i.a();
/*  649 */     String name = a("Pattern", "P", patternRef);
/*      */     
/*  651 */     j objectsFlow = this.n;
/*  652 */     objectsFlow.a(patternRef);
/*  653 */     objectsFlow.g();
/*      */     
/*  655 */     objectsFlow.a("Type");
/*  656 */     objectsFlow.a("Pattern");
/*  657 */     objectsFlow.k();
/*      */     
/*  659 */     objectsFlow.a("PatternType");
/*  660 */     objectsFlow.a(1);
/*  661 */     objectsFlow.k();
/*      */     
/*  663 */     objectsFlow.a("PaintType");
/*  664 */     objectsFlow.a(1);
/*  665 */     objectsFlow.k();
/*      */     
/*  667 */     objectsFlow.a("Resources");
/*  668 */     o newResourceFlow = new o(objectsFlow);
/*  669 */     objectsFlow.k();
/*      */     
/*  671 */     objectsFlow.a("TilingType");
/*  672 */     objectsFlow.a(1);
/*  673 */     objectsFlow.k();
/*      */ 
/*      */ 
/*      */     
/*  677 */     objectsFlow.a("Matrix");
/*  678 */     objectsFlow.i();
/*      */     
/*  680 */     if (at != null) {
/*  681 */       scx = at.getScaleX();
/*  682 */       scy = at.getScaleY();
/*  683 */       shx = at.getShearX();
/*  684 */       shy = at.getShearY();
/*  685 */       tx = at.getTranslateX();
/*  686 */       ty = at.getTranslateY();
/*      */     } else {
/*  688 */       scx = 1.0D;
/*  689 */       scy = 1.0D;
/*  690 */       shx = 0.0D;
/*  691 */       shy = 0.0D;
/*  692 */       tx = 0.0D;
/*  693 */       ty = 0.0D;
/*      */     } 
/*  695 */     objectsFlow.a(scx);
/*  696 */     objectsFlow.a(shy);
/*  697 */     objectsFlow.a(shx);
/*  698 */     objectsFlow.a(scy);
/*  699 */     objectsFlow.a(tx);
/*  700 */     objectsFlow.a(-ty + pageHeight % height * scy);
/*  701 */     objectsFlow.j();
/*  702 */     objectsFlow.k();
/*      */     
/*  704 */     objectsFlow.a("BBox");
/*  705 */     objectsFlow.i();
/*  706 */     objectsFlow.a(0);
/*  707 */     objectsFlow.a(0.0D);
/*  708 */     objectsFlow.a(width);
/*  709 */     objectsFlow.a(height);
/*  710 */     objectsFlow.j();
/*  711 */     objectsFlow.k();
/*      */     
/*  713 */     objectsFlow.a("XStep");
/*  714 */     objectsFlow.a(width);
/*  715 */     objectsFlow.k();
/*      */     
/*  717 */     objectsFlow.a("YStep");
/*  718 */     objectsFlow.a(height);
/*  719 */     objectsFlow.k();
/*      */     
/*  721 */     c patternFlow = objectsFlow.c();
/*  722 */     OutputStream patternOut = patternFlow.b((short)2);
/*  723 */     objectsFlow.a();
/*      */     
/*  725 */     return new l(this, patternOut, patternFlow, newResourceFlow, width, height, name);
/*      */   }
/*      */ 
/*      */   
/*      */   public g a(double pageHeight, AffineTransform at) throws IOException {
/*  730 */     b patternRef = this.i.a();
/*  731 */     String name = a("Pattern", "P", patternRef);
/*      */     
/*  733 */     j objectsFlow = this.n;
/*  734 */     objectsFlow.a(patternRef);
/*  735 */     objectsFlow.g();
/*      */     
/*  737 */     objectsFlow.a("Type");
/*  738 */     objectsFlow.a("Pattern");
/*  739 */     objectsFlow.k();
/*      */     
/*  741 */     objectsFlow.a("PatternType");
/*  742 */     objectsFlow.a(2);
/*  743 */     objectsFlow.k();
/*      */     
/*  745 */     objectsFlow.a("Matrix");
/*  746 */     objectsFlow.i();
/*  747 */     if (at != null) {
/*  748 */       at = new AffineTransform(at);
/*      */     } else {
/*  750 */       at = new AffineTransform();
/*      */     } 
/*  752 */     at.preConcatenate(new AffineTransform(1.0D, 0.0D, 0.0D, -1.0D, 0.0D, pageHeight));
/*  753 */     double scx = at.getScaleX();
/*  754 */     double scy = at.getScaleY();
/*  755 */     double shx = at.getShearX();
/*  756 */     double shy = at.getShearY();
/*  757 */     double tx = at.getTranslateX();
/*  758 */     double ty = at.getTranslateY();
/*  759 */     objectsFlow.a(scx);
/*  760 */     objectsFlow.a(shy);
/*  761 */     objectsFlow.a(shx);
/*  762 */     objectsFlow.a(scy);
/*  763 */     objectsFlow.a(tx);
/*  764 */     objectsFlow.a(ty);
/*  765 */     objectsFlow.j();
/*  766 */     objectsFlow.k();
/*      */     
/*  768 */     b shadingRef = this.i.a();
/*  769 */     objectsFlow.a("Shading");
/*  770 */     objectsFlow.b(shadingRef);
/*  771 */     objectsFlow.k();
/*      */     
/*  773 */     objectsFlow.h();
/*  774 */     objectsFlow.a();
/*      */     
/*  776 */     objectsFlow.a(shadingRef);
/*  777 */     objectsFlow.g();
/*  778 */     return new g(this, (OutputStream)objectsFlow, a().i(), name, objectsFlow) {
/*      */         public String a() {
/*  780 */           return this.a;
/*      */         }
/*      */         
/*      */         public void close() throws IOException {
/*  784 */           flush();
/*  785 */           this.b.h();
/*  786 */           this.b.a();
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   public OutputStream a(String name, a attachment) throws IOException {
/*  792 */     if (attachment.a == null && name == null) {
/*  793 */       throw new NullPointerException();
/*      */     }
/*  795 */     if (this.h.h() < 1400) {
/*  796 */       throw new UnsupportedOperationException("ファイルの添付は PDF 1.4 以降で使用できます。");
/*      */     }
/*  798 */     if (this.h.h() == 1412) {
/*  799 */       throw new UnsupportedOperationException("ファイルの添付は PDF/A では利用できません。");
/*      */     }
/*  801 */     if (this.h.h() == 1421) {
/*  802 */       throw new UnsupportedOperationException("ファイルの添付は PDF/X では利用できません。");
/*      */     }
/*      */     
/*  805 */     String desc = attachment.a;
/*  806 */     if (desc == null) {
/*  807 */       desc = name;
/*  808 */     } else if (name == null) {
/*  809 */       name = desc;
/*      */     } 
/*      */     
/*  812 */     b fileRef = this.i.a();
/*      */     
/*  814 */     j objectsFlow = this.n;
/*  815 */     objectsFlow.a(fileRef);
/*  816 */     objectsFlow.g();
/*      */     
/*  818 */     objectsFlow.a("Type");
/*  819 */     objectsFlow.a("EmbeddedFile");
/*  820 */     objectsFlow.k();
/*      */     
/*  822 */     if (attachment.b != null) {
/*  823 */       objectsFlow.a("Subtype");
/*  824 */       objectsFlow.a(attachment.b);
/*  825 */       objectsFlow.k();
/*      */     } 
/*      */     
/*  828 */     a filespac = new a(attachment, name, fileRef);
/*  829 */     this.J.a(desc, filespac);
/*      */     
/*  831 */     j paramsFlow = objectsFlow.c();
/*      */     try {
/*  833 */       MessageDigest md5 = MessageDigest.getInstance("md5");
/*      */       
/*  835 */       OutputStream out = objectsFlow.b((short)1);
/*  836 */       return new FilterOutputStream(this, out, md5, objectsFlow, paramsFlow) {
/*  837 */           private int e = 0;
/*      */           
/*      */           public void write(byte[] buff, int off, int len) throws IOException {
/*  840 */             this.out.write(buff, off, len);
/*  841 */             this.a.update(buff, off, len);
/*  842 */             this.e += len;
/*      */           }
/*      */           
/*      */           public void write(byte[] buff) throws IOException {
/*  846 */             this.out.write(buff);
/*  847 */             this.a.update(buff);
/*  848 */             this.e += buff.length;
/*      */           }
/*      */           
/*      */           public void write(int b) throws IOException {
/*  852 */             this.out.write(b);
/*  853 */             this.a.update((byte)b);
/*  854 */             this.e++;
/*      */           }
/*      */           
/*      */           public void close() throws IOException {
/*  858 */             this.out.close();
/*  859 */             this.b.a();
/*      */             
/*  861 */             this.c.a("Params");
/*  862 */             this.c.g();
/*      */             
/*  864 */             this.c.a("Size");
/*  865 */             this.c.a(this.e);
/*  866 */             this.c.k();
/*      */             
/*  868 */             this.c.a("CheckSum");
/*  869 */             byte[] hash = this.a.digest();
/*  870 */             this.c.a(hash, 0, hash.length);
/*  871 */             this.c.k();
/*      */             
/*  873 */             this.c.h();
/*  874 */             this.c.close();
/*      */           }
/*      */         };
/*  877 */     } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
/*  878 */       throw new RuntimeException(noSuchAlgorithmException);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public i b(double width, double height) throws IOException {
/*  894 */     return this.I.a(width, height);
/*      */   }
/*      */ 
/*      */   
/*      */   public void d() throws IOException {
/*  899 */     e info = this.h.n();
/*      */     
/*  901 */     String author = info.a();
/*  902 */     String creator = info.b();
/*  903 */     String producer = info.d();
/*  904 */     String title = info.f();
/*  905 */     String subject = info.e();
/*  906 */     String keywords = info.c();
/*  907 */     TimeZone zone = TimeZone.getDefault();
/*  908 */     long create = info.g();
/*  909 */     if (create == -1L) {
/*  910 */       create = System.currentTimeMillis();
/*      */     }
/*  912 */     long modify = info.h();
/*      */     
/*  914 */     b infoRef = this.i.a();
/*  915 */     this.n.a(infoRef);
/*  916 */     this.n.g();
/*      */     
/*  918 */     if (this.h.h() == 1421) {
/*  919 */       if (title == null || title.length() == 0) {
/*  920 */         title = "Untitled";
/*      */       }
/*  922 */       this.n.a("GTS_PDFXVersion");
/*  923 */       this.n.e("PDF/X-1a:2003");
/*  924 */       this.n.k();
/*      */     } 
/*      */     
/*  927 */     if (author != null) {
/*  928 */       this.n.a("Author");
/*  929 */       this.n.e(author);
/*  930 */       this.n.k();
/*      */     } 
/*      */     
/*  933 */     this.n.a("CreationDate");
/*  934 */     this.n.a(create, zone);
/*  935 */     this.n.k();
/*      */     
/*  937 */     if (modify == -1L) {
/*  938 */       modify = create;
/*      */     }
/*  940 */     this.n.a("ModDate");
/*  941 */     this.n.a(modify, zone);
/*  942 */     this.n.k();
/*      */     
/*  944 */     if (creator != null) {
/*  945 */       this.n.a("Creator");
/*  946 */       this.n.e(creator);
/*  947 */       this.n.k();
/*      */     } 
/*      */     
/*  950 */     if (producer != null) {
/*  951 */       this.n.a("Producer");
/*  952 */       this.n.e(producer);
/*  953 */       this.n.k();
/*      */     } 
/*      */     
/*  956 */     if (title != null) {
/*  957 */       this.n.a("Title");
/*  958 */       this.n.e(title);
/*  959 */       this.n.k();
/*      */     } 
/*      */     
/*  962 */     if (subject != null) {
/*  963 */       this.n.a("Subject");
/*  964 */       this.n.e(subject);
/*  965 */       this.n.k();
/*      */     } 
/*      */     
/*  968 */     if (keywords != null) {
/*  969 */       this.n.a("Keywords");
/*  970 */       this.n.e(keywords);
/*  971 */       this.n.k();
/*      */     } 
/*      */     
/*  974 */     this.n.a("Trapped");
/*  975 */     this.n.a("False");
/*  976 */     this.n.k();
/*      */     
/*  978 */     this.n.h();
/*  979 */     this.n.a();
/*      */ 
/*      */     
/*  982 */     if (this.m != null) {
/*  983 */       this.m.g();
/*      */       
/*  985 */       this.m.a("Type");
/*  986 */       this.m.a("Metadata");
/*  987 */       this.m.k();
/*      */       
/*  989 */       this.m.a("Subtype");
/*  990 */       this.m.a("XML");
/*  991 */       this.m.k();
/*      */       
/*  993 */       try (OutputStream xout = this.m.b((short)0)) {
/*  994 */         xout.write("<?xpacket begin='".getBytes("UTF-8"));
/*  995 */         xout.write("ï»¿".getBytes("ISO-8859-1"));
/*  996 */         xout.write("' id='W5M0MpCehiHzreSzNTczkc9d'?>\n".getBytes("UTF-8"));
/*      */         
/*  998 */         TransformerHandler handler = ((SAXTransformerFactory)SAXTransformerFactory.newInstance()).newTransformerHandler();
/*  999 */         handler.setResult(new StreamResult(xout));
/* 1000 */         Transformer t = handler.getTransformer();
/* 1001 */         t.setOutputProperty("method", "xml");
/* 1002 */         t.setOutputProperty("indent", "yes");
/* 1003 */         t.setOutputProperty("omit-xml-declaration", "yes");
/* 1004 */         t.setOutputProperty("encoding", "UTF-8");
/*      */         
/* 1006 */         AttributesImpl attsi = new AttributesImpl();
/* 1007 */         String xURI = "adobe:ns:meta/";
/* 1008 */         String rdfURI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
/* 1009 */         String pdfaidURI = "http://www.aiim.org/pdfa/ns/id/";
/* 1010 */         String pdfURI = "http://ns.adobe.com/pdf/1.3/";
/* 1011 */         String dcURI = "http://purl.org/dc/elements/1.1/";
/* 1012 */         String xmpURI = "http://ns.adobe.com/xap/1.0/";
/*      */         
/* 1014 */         handler.startDocument();
/* 1015 */         attsi.addAttribute("", "x", "xmlns:x", "CDATA", xURI);
/*      */         
/* 1017 */         handler.startElement(xURI, "xmpmeta", "x:xmpmeta", attsi);
/* 1018 */         attsi.clear();
/* 1019 */         attsi.addAttribute("", "rdf", "xmlns:rdf", "CDATA", rdfURI);
/* 1020 */         handler.startElement(rdfURI, "RDF", "rdf:RDF", attsi);
/* 1021 */         attsi.clear();
/*      */ 
/*      */         
/* 1024 */         if (this.h.h() == 1412) {
/* 1025 */           attsi.addAttribute("", "pdfaid", "xmlns:pdfaid", "CDATA", pdfaidURI);
/* 1026 */           attsi.addAttribute(rdfURI, "about", "rdf:about", "CDATA", "");
/* 1027 */           handler.startElement(rdfURI, "Description", "rdf:Description", attsi);
/* 1028 */           attsi.clear();
/* 1029 */           handler.startElement(pdfaidURI, "part", "pdfaid:part", attsi);
/* 1030 */           handler.characters("1".toCharArray(), 0, 1);
/* 1031 */           handler.endElement(pdfaidURI, "part", "pdfaid:part");
/* 1032 */           handler.startElement(pdfaidURI, "conformance", "pdfaid:conformance", attsi);
/* 1033 */           handler.characters("A".toCharArray(), 0, 1);
/* 1034 */           handler.endElement(pdfaidURI, "conformance", "pdfaid:conformance");
/* 1035 */           handler.endElement(rdfURI, "Description", "rdf:Description");
/*      */         } 
/*      */ 
/*      */         
/* 1039 */         attsi.addAttribute("", "pdf", "xmlns:pdf", "CDATA", pdfURI);
/* 1040 */         attsi.addAttribute(rdfURI, "about", "rdf:about", "CDATA", "");
/* 1041 */         handler.startElement(rdfURI, "Description", "rdf:Description", attsi);
/* 1042 */         attsi.clear();
/* 1043 */         if (keywords != null) {
/* 1044 */           handler.startElement(pdfURI, "Keywords", "pdf:Keywords", attsi);
/* 1045 */           handler.characters(keywords.toCharArray(), 0, keywords.length());
/* 1046 */           handler.endElement(pdfURI, "Keywords", "pdf:Keywords");
/*      */         } 
/* 1048 */         if (producer != null) {
/* 1049 */           handler.startElement(pdfURI, "Producer", "pdf:Producer", attsi);
/* 1050 */           handler.characters(producer.toCharArray(), 0, producer.length());
/* 1051 */           handler.endElement(pdfURI, "Producer", "pdf:Producer");
/*      */         } 
/* 1053 */         handler.endElement(rdfURI, "Description", "rdf:Description");
/*      */ 
/*      */         
/* 1056 */         attsi.addAttribute(rdfURI, "about", "rdf:about", "CDATA", "");
/* 1057 */         attsi.addAttribute("", "dc", "xmlns:dc", "CDATA", dcURI);
/* 1058 */         handler.startElement(rdfURI, "Description", "rdf:Description", attsi);
/* 1059 */         attsi.clear();
/*      */         
/* 1061 */         String format = "application/pdf";
/* 1062 */         handler.startElement(dcURI, "format", "dc:format", attsi);
/* 1063 */         handler.characters(format.toCharArray(), 0, format.length());
/* 1064 */         handler.endElement(dcURI, "format", "dc:format");
/*      */         
/* 1066 */         if (title != null) {
/* 1067 */           handler.startElement(dcURI, "title", "dc:title", attsi);
/* 1068 */           handler.startElement(rdfURI, "Alt", "rdf:Alt", attsi);
/* 1069 */           attsi.addAttribute("", "lang", "xml:lang", "CDATA", "x-default");
/* 1070 */           handler.startElement(rdfURI, "li", "rdf:li", attsi);
/* 1071 */           attsi.clear();
/* 1072 */           handler.characters(title.toCharArray(), 0, title.length());
/* 1073 */           handler.endElement(rdfURI, "li", "rdf:li");
/* 1074 */           handler.endElement(rdfURI, "Alt", "rdf:Alt");
/* 1075 */           handler.endElement(dcURI, "title", "dc:title");
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1085 */         if (author != null) {
/* 1086 */           handler.startElement(dcURI, "creator", "dc:creator", attsi);
/* 1087 */           handler.startElement(rdfURI, "Seq", "rdf:Seq", attsi);
/* 1088 */           handler.startElement(rdfURI, "li", "rdf:li", attsi);
/* 1089 */           handler.characters(author.toCharArray(), 0, author.length());
/* 1090 */           handler.endElement(rdfURI, "li", "rdf:li");
/* 1091 */           handler.endElement(rdfURI, "Seq", "rdf:Seq");
/* 1092 */           handler.endElement(dcURI, "creator", "dc:creator");
/*      */         } 
/* 1094 */         attsi.clear();
/* 1095 */         handler.endElement(rdfURI, "Description", "rdf:Description");
/*      */ 
/*      */         
/* 1098 */         attsi.addAttribute("", "xmp", "xmlns:xmp", "CDATA", xmpURI);
/* 1099 */         attsi.addAttribute(rdfURI, "about", "rdf:about", "CDATA", "");
/* 1100 */         handler.startElement(rdfURI, "Description", "rdf:Description", attsi);
/* 1101 */         attsi.clear();
/* 1102 */         if (creator != null) {
/* 1103 */           handler.startElement(xmpURI, "CreatorTool", "xmp:CreatorTool", attsi);
/* 1104 */           handler.characters(creator.toCharArray(), 0, creator.length());
/* 1105 */           handler.endElement(xmpURI, "CreatorTool", "xmp:CreatorTool");
/*      */         } 
/* 1107 */         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
/* 1108 */         handler.startElement(xmpURI, "CreateDate", "xmp:CreateDate", attsi);
/* 1109 */         String createStr = dateFormat.format(new Date(create));
/*      */         
/* 1111 */         createStr = createStr.substring(0, createStr.length() - 2) + ':' + createStr.substring(createStr.length() - 2);
/* 1112 */         handler.characters(createStr.toCharArray(), 0, createStr.length());
/* 1113 */         handler.endElement(xmpURI, "CreateDate", "xmp:CreateDate");
/* 1114 */         if (modify != -1L) {
/* 1115 */           handler.startElement(xmpURI, "ModifyDate", "xmp:ModifyDate", attsi);
/* 1116 */           String modifyStr = dateFormat.format(new Date(modify));
/*      */           
/* 1118 */           modifyStr = modifyStr.substring(0, modifyStr.length() - 2) + ':' + modifyStr.substring(modifyStr.length() - 2);
/* 1119 */           handler.characters(modifyStr.toCharArray(), 0, modifyStr.length());
/* 1120 */           handler.endElement(xmpURI, "ModifyDate", "xmp:ModifyDate");
/*      */         } 
/* 1122 */         handler.endElement(rdfURI, "Description", "rdf:Description");
/*      */         
/* 1124 */         handler.endElement(rdfURI, "RDF", "rdf:RDF");
/* 1125 */         handler.endElement(xURI, "xmpmeta", "x:xmpmeta");
/*      */         
/* 1127 */         handler.endDocument();
/*      */         
/* 1129 */         for (int k = 0; k < 26; k++) {
/* 1130 */           xout.write(C);
/*      */         }
/* 1132 */         xout.write("<?xpacket end='w'?>\n".getBytes("UTF-8"));
/* 1133 */       } catch (Exception exception) {
/* 1134 */         throw new RuntimeException(exception);
/*      */       } 
/* 1136 */       this.m.a();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1141 */     this.I.a();
/*      */ 
/*      */     
/* 1144 */     if (this.s != null) {
/* 1145 */       this.s.b();
/*      */     }
/*      */ 
/*      */     
/* 1149 */     this.t.a();
/*      */ 
/*      */     
/* 1152 */     if (this.J != null) {
/* 1153 */       this.J.a();
/*      */     }
/*      */ 
/*      */     
/* 1157 */     this.o.a();
/*      */ 
/*      */     
/* 1160 */     if (this.M != null) {
/* 1161 */       b ref = this.i.a();
/* 1162 */       this.l.a("OCProperties");
/* 1163 */       this.l.b(ref);
/*      */       
/* 1165 */       this.n.a(ref);
/* 1166 */       this.n.g();
/* 1167 */       this.n.a("OCGs");
/* 1168 */       this.n.i(); int k;
/* 1169 */       for (k = 0; k < this.M.size(); k++) {
/* 1170 */         b ocgRef = this.M.get(k);
/* 1171 */         this.n.b(ocgRef);
/*      */       } 
/* 1173 */       this.n.j();
/*      */       
/* 1175 */       this.n.a("D");
/* 1176 */       this.n.g();
/* 1177 */       this.n.a("ON");
/* 1178 */       this.n.i();
/* 1179 */       for (k = 0; k < this.M.size(); k++) {
/* 1180 */         b ocgRef = this.M.get(k);
/* 1181 */         this.n.b(ocgRef);
/*      */       } 
/* 1183 */       this.n.j();
/* 1184 */       this.n.a("AS");
/* 1185 */       this.n.i();
/*      */       
/* 1187 */       this.n.g();
/* 1188 */       this.n.a("Event");
/* 1189 */       this.n.a("View");
/* 1190 */       this.n.a("OCGs");
/* 1191 */       this.n.i();
/* 1192 */       for (k = 0; k < this.M.size(); k++) {
/* 1193 */         b ocgRef = this.M.get(k);
/* 1194 */         this.n.b(ocgRef);
/*      */       } 
/* 1196 */       this.n.j();
/* 1197 */       this.n.a("Category");
/* 1198 */       this.n.i();
/* 1199 */       this.n.a("View");
/* 1200 */       this.n.j();
/* 1201 */       this.n.h();
/*      */       
/* 1203 */       this.n.g();
/* 1204 */       this.n.a("Event");
/* 1205 */       this.n.a("Print");
/* 1206 */       this.n.a("OCGs");
/* 1207 */       this.n.i();
/* 1208 */       for (k = 0; k < this.M.size(); k++) {
/* 1209 */         b ocgRef = this.M.get(k);
/* 1210 */         this.n.b(ocgRef);
/*      */       } 
/* 1212 */       this.n.j();
/* 1213 */       this.n.a("Category");
/* 1214 */       this.n.i();
/* 1215 */       this.n.a("Print");
/* 1216 */       this.n.j();
/* 1217 */       this.n.h();
/*      */       
/* 1219 */       this.n.j();
/* 1220 */       this.n.h();
/*      */       
/* 1222 */       this.n.h();
/* 1223 */       this.n.a();
/*      */     } 
/*      */ 
/*      */     
/* 1227 */     j vp = this.h.p();
/* 1228 */     if (vp != null) {
/* 1229 */       this.l.a("ViewerPreferences");
/* 1230 */       this.l.g();
/*      */       
/* 1232 */       if (vp.b()) {
/* 1233 */         this.l.a("HideToolbar");
/* 1234 */         this.l.a(true);
/* 1235 */         this.l.k();
/*      */       } 
/*      */       
/* 1238 */       if (vp.c()) {
/* 1239 */         this.l.a("HideMenubar");
/* 1240 */         this.l.a(true);
/* 1241 */         this.l.k();
/*      */       } 
/*      */       
/* 1244 */       if (vp.d()) {
/* 1245 */         this.l.a("HideWindowUI");
/* 1246 */         this.l.a(true);
/* 1247 */         this.l.k();
/*      */       } 
/*      */       
/* 1250 */       if (vp.e()) {
/* 1251 */         this.l.a("FitWindow");
/* 1252 */         this.l.a(true);
/* 1253 */         this.l.k();
/*      */       } 
/*      */       
/* 1256 */       if (vp.f()) {
/* 1257 */         this.l.a("CenterWindow");
/* 1258 */         this.l.a(true);
/* 1259 */         this.l.k();
/*      */       } 
/*      */       
/* 1262 */       if (vp.g()) {
/* 1263 */         if (this.h.h() < 1400) {
/* 1264 */           throw new UnsupportedOperationException("ViewerPreferenceのDisplayDocTitleは PDF 1.4 以降で使用できます。");
/*      */         }
/* 1266 */         this.l.a("DisplayDocTitle");
/* 1267 */         this.l.a(true);
/* 1268 */         this.l.k();
/*      */       } 
/*      */       
/* 1271 */       if (vp.h() != 1) {
/* 1272 */         this.l.a("NonFullScreenPageMode");
/* 1273 */         switch (vp.h()) {
/*      */           case 2:
/* 1275 */             this.l.a("UseOutlines");
/*      */             break;
/*      */           case 3:
/* 1278 */             this.l.a("UseThumbs");
/*      */             break;
/*      */           case 4:
/* 1281 */             this.l.a("UseOC");
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           default:
/* 1287 */             throw new IllegalStateException();
/*      */         } 
/* 1289 */         this.l.k();
/*      */       } 
/*      */       
/* 1292 */       if (vp.a() != 1) {
/* 1293 */         if (this.h.h() < 1300) {
/* 1294 */           throw new UnsupportedOperationException("ViewerPreferenceのDirectionは PDF 1.3 以降で使用できます。");
/*      */         }
/* 1296 */         this.l.a("Direction");
/* 1297 */         switch (vp.a()) {
/*      */           case 2:
/* 1299 */             this.l.a("R2L");
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           default:
/* 1305 */             throw new IllegalStateException();
/*      */         } 
/* 1307 */         this.l.k();
/*      */       } 
/*      */       
/* 1310 */       if (vp.i() != 2) {
/* 1311 */         if (this.h.h() < 1400) {
/* 1312 */           throw new UnsupportedOperationException("ViewerPreferenceのViewAreaは PDF 1.4 以降で使用できます。");
/*      */         }
/* 1314 */         this.l.a("ViewArea");
/* 1315 */         a(vp.i());
/* 1316 */         this.l.k();
/*      */       } 
/*      */       
/* 1319 */       if (vp.j() != 2) {
/* 1320 */         if (this.h.h() < 1400) {
/* 1321 */           throw new UnsupportedOperationException("ViewerPreferenceのViewClipは PDF 1.4 以降で使用できます。");
/*      */         }
/* 1323 */         this.l.a("ViewClip");
/* 1324 */         a(vp.j());
/* 1325 */         this.l.k();
/*      */       } 
/*      */       
/* 1328 */       if (vp.k() != 2) {
/* 1329 */         if (this.h.h() < 1400) {
/* 1330 */           throw new UnsupportedOperationException("ViewerPreferenceのPrintAreaは PDF 1.4 以降で使用できます。");
/*      */         }
/* 1332 */         this.l.a("PrintArea");
/* 1333 */         a(vp.k());
/* 1334 */         this.l.k();
/*      */       } 
/*      */       
/* 1337 */       if (vp.l() != 2) {
/* 1338 */         if (this.h.h() < 1400) {
/* 1339 */           throw new UnsupportedOperationException("ViewerPreferenceのPrintClipは PDF 1.4 以降で使用できます。");
/*      */         }
/* 1341 */         this.l.a("PrintClip");
/* 1342 */         a(vp.l());
/* 1343 */         this.l.k();
/*      */       } 
/*      */       
/* 1346 */       if (vp.m() != 2) {
/* 1347 */         if (this.h.h() < 1600) {
/* 1348 */           throw new UnsupportedOperationException("ViewerPreferenceのPrintScalingは PDF 1.6 以降で使用できます。");
/*      */         }
/* 1350 */         this.l.a("PrintScaling");
/* 1351 */         switch (vp.m()) {
/*      */           case 1:
/* 1353 */             this.l.a("None");
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           default:
/* 1359 */             throw new IllegalStateException();
/*      */         } 
/* 1361 */         this.l.k();
/*      */       } 
/*      */       
/* 1364 */       if (vp.n() != 1) {
/* 1365 */         if (this.h.h() < 1700) {
/* 1366 */           throw new UnsupportedOperationException("ViewerPreferenceのDuplexは PDF 1.7 以降で使用できます。");
/*      */         }
/* 1368 */         this.l.a("Duplex");
/* 1369 */         switch (vp.n()) {
/*      */           case 2:
/* 1371 */             this.l.a("Simplex");
/*      */             break;
/*      */           case 3:
/* 1374 */             this.l.a("DuplexFlipShortEdge");
/*      */             break;
/*      */           case 4:
/* 1377 */             this.l.a("DuplexFlipLongEdge");
/*      */             break;
/*      */ 
/*      */           
/*      */           default:
/* 1382 */             throw new IllegalStateException();
/*      */         } 
/* 1384 */         this.l.k();
/*      */       } 
/*      */       
/* 1387 */       if (vp.o()) {
/* 1388 */         if (this.h.h() < 1700) {
/* 1389 */           throw new UnsupportedOperationException("ViewerPreferenceのPickTrayByPDFSizeは PDF 1.7 以降で使用できます。");
/*      */         }
/* 1391 */         this.l.a("PickTrayByPDFSize");
/* 1392 */         this.l.a(true);
/* 1393 */         this.l.k();
/*      */       } 
/*      */       
/* 1396 */       int[] printPageRange = vp.p();
/* 1397 */       if (printPageRange != null) {
/* 1398 */         if (this.h.h() < 1700) {
/* 1399 */           throw new UnsupportedOperationException("ViewerPreferenceのPrintPageRangeは PDF 1.7 以降で使用できます。");
/*      */         }
/* 1401 */         this.l.a("PrintPageRange");
/* 1402 */         this.l.i();
/* 1403 */         for (int k = 0; k < printPageRange.length; k++) {
/* 1404 */           this.l.a(printPageRange[k]);
/*      */         }
/* 1406 */         this.l.j();
/* 1407 */         this.l.k();
/*      */       } 
/*      */       
/* 1410 */       int numCopies = vp.q();
/* 1411 */       if (numCopies > 0) {
/* 1412 */         if (this.h.h() < 1700) {
/* 1413 */           throw new UnsupportedOperationException("ViewerPreferenceのNumCopiesは PDF 1.7 以降で使用できます。");
/*      */         }
/* 1415 */         this.l.a("NumCopies");
/* 1416 */         this.l.a(numCopies);
/* 1417 */         this.l.k();
/*      */       } 
/*      */       
/* 1420 */       this.l.h();
/*      */       
/* 1422 */       this.l.k();
/*      */     } 
/*      */ 
/*      */     
/* 1426 */     a action = this.h.o();
/* 1427 */     if (action != null) {
/* 1428 */       this.l.a("OpenAction");
/* 1429 */       this.l.g();
/* 1430 */       action.a((h)this.l);
/* 1431 */       this.l.h();
/* 1432 */       this.l.k();
/*      */     } 
/*      */ 
/*      */     
/* 1436 */     this.l.close();
/*      */ 
/*      */     
/* 1439 */     this.L.a();
/* 1440 */     this.p.a();
/* 1441 */     this.n.close();
/*      */ 
/*      */     
/* 1444 */     this.i.a(this.g.d(), infoRef, this.F, this.j);
/*      */     
/* 1446 */     this.k.close();
/*      */   }
/*      */   
/*      */   private void a(byte area) throws IOException {
/* 1450 */     switch (area) {
/*      */       case 1:
/* 1452 */         this.l.a("MediaBox");
/*      */       
/*      */       case 2:
/*      */         return;
/*      */       
/*      */       case 3:
/* 1458 */         this.l.a("BleedBox");
/*      */       
/*      */       case 4:
/* 1461 */         this.l.a("TrimBox");
/*      */       
/*      */       case 5:
/* 1464 */         this.l.a("ArtBox");
/*      */     } 
/*      */     
/* 1467 */     throw new IllegalStateException();
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/e/n.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */