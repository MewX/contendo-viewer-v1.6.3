/*     */ package jp.cssj.sakae.pdf.e;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.util.Map;
/*     */ import jp.cssj.sakae.pdf.b;
/*     */ import jp.cssj.sakae.pdf.b.a;
/*     */ import jp.cssj.sakae.pdf.c;
/*     */ import jp.cssj.sakae.pdf.h;
/*     */ import jp.cssj.sakae.pdf.i;
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
/*     */ class m
/*     */   extends i
/*     */ {
/*     */   private final j i;
/*     */   private final b j;
/*     */   private final j k;
/*     */   private final j l;
/*     */   private boolean m = false;
/*     */   private Rectangle2D n;
/*     */   private Rectangle2D o;
/*     */   private Rectangle2D p;
/*     */   private Rectangle2D q;
/*     */   private Rectangle2D r;
/*     */   
/*     */   public m(n pdfWriter, b rootPageRef, j pagesKidsFlow, double width, double height) throws IOException {
/*  38 */     super(pdfWriter, null, width, height);
/*  39 */     if (width < 3.0D || height < 3.0D) {
/*  40 */       throw new IllegalArgumentException("ページサイズが3pt未満です: width=" + width + ",height=" + height);
/*     */     }
/*  42 */     if (width > 14400.0D || height > 14400.0D) {
/*  43 */       throw new IllegalArgumentException("ページサイズが14400ptを超えています: width=" + width + ",height=" + height);
/*     */     }
/*  45 */     if (pdfWriter.a().h() == 1421) {
/*  46 */       this.r = new Rectangle2D.Double(0.0D, 0.0D, width, height);
/*     */     }
/*     */     
/*  49 */     j mainFlow = pdfWriter.k;
/*     */     
/*  51 */     this.j = pdfWriter.i.a();
/*  52 */     mainFlow.a(this.j);
/*  53 */     pagesKidsFlow.b(this.j);
/*  54 */     mainFlow.g();
/*     */     
/*  56 */     mainFlow.a("Type");
/*  57 */     mainFlow.a("Page");
/*  58 */     mainFlow.k();
/*     */     
/*  60 */     this.k = mainFlow.c();
/*  61 */     this.n = new Rectangle2D.Double(0.0D, 0.0D, width, height);
/*     */     
/*  63 */     mainFlow.a("Parent");
/*  64 */     mainFlow.b(rootPageRef);
/*  65 */     mainFlow.k();
/*     */     
/*  67 */     mainFlow.a("Resources");
/*  68 */     mainFlow.b(pdfWriter.q);
/*  69 */     mainFlow.k();
/*     */     
/*  71 */     mainFlow.a("Contents");
/*  72 */     b contentsRef = pdfWriter.i.a();
/*  73 */     mainFlow.b(contentsRef);
/*  74 */     mainFlow.k();
/*     */     
/*  76 */     this.l = mainFlow.c();
/*  77 */     mainFlow.k();
/*     */     
/*  79 */     mainFlow.h();
/*  80 */     mainFlow.a();
/*     */     
/*  82 */     this.i = mainFlow.c();
/*  83 */     this.i.a(contentsRef);
/*     */     
/*  85 */     this.out = this.i.a((short)2);
/*     */   }
/*     */   
/*     */   private n n() {
/*  89 */     return (n)this.c;
/*     */   }
/*     */   
/*     */   public i e() {
/*  93 */     return this;
/*     */   }
/*     */   
/*     */   public void a(String type, String name) throws IOException {
/*  97 */     n pdfWriter = n();
/*  98 */     o resourceFlow = pdfWriter.p;
/*  99 */     if (resourceFlow.a(name)) {
/*     */       return;
/*     */     }
/* 102 */     Map<String, b> nameToResourceRef = pdfWriter.r;
/*     */     
/* 104 */     b objectRef = nameToResourceRef.get(name);
/* 105 */     resourceFlow.a(type, name, objectRef);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(a annot) throws IOException {
/* 112 */     if (this.c.a().h() == 1421) {
/* 113 */       throw new UnsupportedOperationException("アノテーションは PDF/X では利用できません。");
/*     */     }
/*     */     
/* 116 */     if (!this.m) {
/* 117 */       this.l.a("Annots");
/* 118 */       this.l.i();
/* 119 */       this.m = true;
/*     */     } 
/* 121 */     b annotRef = (n()).i.a();
/* 122 */     this.l.b(annotRef);
/*     */     
/* 124 */     try (c objectsFlow = (n()).n.c()) {
/* 125 */       objectsFlow.a(annotRef);
/* 126 */       objectsFlow.g();
/*     */ 
/*     */       
/* 129 */       annot.a((h)objectsFlow, this);
/*     */       
/* 131 */       if (this.c.a().h() == 1412 || this.c
/* 132 */         .a().h() == 1421) {
/*     */         
/* 134 */         objectsFlow.a("F");
/* 135 */         objectsFlow.a(4);
/* 136 */         objectsFlow.k();
/*     */       } 
/*     */       
/* 139 */       objectsFlow.h();
/* 140 */       objectsFlow.a();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String id, Point2D location) throws IOException {
/* 148 */     h.a dest = new h.a(this.j, location.getX(), this.b - location.getY(), 0.0D);
/* 149 */     (n()).t.a(id, dest);
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
/*     */ 
/*     */   
/*     */   public void b(String title, Point2D location) throws IOException {
/* 163 */     if ((n()).s != null) {
/* 164 */       (n()).s.a(this.j, title, this.b, location.getX(), location
/* 165 */           .getY());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c() throws IOException {
/* 175 */     if ((n()).s != null) {
/* 176 */       (n()).s.a();
/*     */     }
/*     */   }
/*     */   
/*     */   private void f(Rectangle2D r) throws IOException {
/* 181 */     this.k.i();
/* 182 */     this.k.a(r.getMinX());
/* 183 */     this.k.a(this.b - r.getMaxY());
/* 184 */     this.k.a(r.getMaxX());
/* 185 */     this.k.a(this.b - r.getMinY());
/* 186 */     this.k.j();
/* 187 */     this.k.k();
/*     */   }
/*     */   
/*     */   public void a(Rectangle2D mediaBox) {
/* 191 */     if (mediaBox == null) {
/* 192 */       throw new NullPointerException();
/*     */     }
/* 194 */     this.n = mediaBox;
/*     */   }
/*     */   
/*     */   public void b(Rectangle2D cropBox) {
/* 198 */     this.o = cropBox;
/*     */   }
/*     */   
/*     */   public void c(Rectangle2D bleedBox) {
/* 202 */     if (bleedBox != null && this.c.a().h() < 1300) {
/* 203 */       throw new UnsupportedOperationException("BleedBoxはPDF 1.4 以降で使用できます。");
/*     */     }
/* 205 */     this.p = bleedBox;
/*     */   }
/*     */   
/*     */   public void d(Rectangle2D trimBox) {
/* 209 */     if (trimBox != null && this.c.a().h() < 1300) {
/* 210 */       throw new UnsupportedOperationException("TrimBoxはPDF 1.4 以降で使用できます。");
/*     */     }
/* 212 */     this.q = trimBox;
/*     */   }
/*     */   
/*     */   public void e(Rectangle2D artBox) {
/* 216 */     if (artBox != null && this.c.a().h() < 1300) {
/* 217 */       throw new UnsupportedOperationException("ArtBoxはPDF 1.4 以降で使用できます。");
/*     */     }
/* 219 */     this.r = artBox;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 223 */     super.close();
/*     */     
/* 225 */     if (this.n == null) {
/* 226 */       throw new IllegalStateException();
/*     */     }
/* 228 */     this.k.a("MediaBox");
/* 229 */     f(this.n);
/*     */     
/* 231 */     if (this.o != null) {
/* 232 */       this.k.a("CropBox");
/* 233 */       f(this.o);
/*     */     } 
/*     */     
/* 236 */     if (this.p != null) {
/* 237 */       this.k.a("BleedBox");
/* 238 */       f(this.p);
/*     */     } 
/*     */     
/* 241 */     if (this.q != null) {
/* 242 */       this.k.a("TrimBox");
/* 243 */       f(this.q);
/*     */     } 
/*     */     
/* 246 */     if (this.r != null) {
/* 247 */       this.k.a("ArtBox");
/* 248 */       f(this.r);
/*     */     } 
/*     */     
/* 251 */     this.k.close();
/*     */     
/* 253 */     if (this.m) {
/* 254 */       this.l.j();
/* 255 */       this.m = false;
/*     */     } 
/* 257 */     this.l.close();
/* 258 */     this.i.a();
/* 259 */     this.i.close();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/e/m.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */