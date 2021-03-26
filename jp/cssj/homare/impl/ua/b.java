/*     */ package jp.cssj.homare.impl.ua;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import jp.cssj.e.e.d;
/*     */ import jp.cssj.homare.b.a.c.C;
/*     */ import jp.cssj.homare.b.a.j;
/*     */ import jp.cssj.homare.b.g.a;
/*     */ import jp.cssj.homare.css.a;
/*     */ import jp.cssj.homare.css.e.f;
/*     */ import jp.cssj.homare.ua.a.B;
/*     */ import jp.cssj.homare.ua.c;
/*     */ import jp.cssj.homare.ua.e;
/*     */ import jp.cssj.homare.ua.f;
/*     */ import jp.cssj.homare.ua.g;
/*     */ import jp.cssj.homare.ua.h;
/*     */ import jp.cssj.homare.ua.k;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.homare.xml.a.a;
/*     */ import jp.cssj.homare.xml.c.a;
/*     */ 
/*     */ public abstract class b
/*     */   implements a
/*     */ {
/*     */   protected final m a;
/*     */   
/*     */   private static boolean a(short type) {
/*  32 */     switch (type) {
/*     */       case 3:
/*     */       case 4:
/*     */       case 6:
/*  36 */         return true;
/*     */     } 
/*  38 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean b(short type) {
/*  42 */     switch (type) {
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */       case 7:
/*     */       case 8:
/*     */       case 9:
/*  49 */         return false;
/*     */     } 
/*  51 */     return true;
/*     */   }
/*     */ 
/*     */   
/*  55 */   private c[] b = null;
/*     */   
/*     */   private boolean c;
/*     */   
/*     */   private boolean d;
/*     */   private boolean e;
/*     */   private boolean f;
/*     */   
/*     */   protected b(m ua) {
/*  64 */     this.a = ua;
/*  65 */     d(B.aA.a(this.a));
/*     */   }
/*     */   
/*     */   protected abstract void a(String paramString, Point2D paramPoint2D);
/*     */   
/*     */   protected abstract void a(Shape paramShape, URI paramURI, a parama);
/*     */   
/*     */   protected abstract void a();
/*     */   
/*     */   private c[] i() {
/*  75 */     if (this.b == null) {
/*  76 */       c[] counters; e counter = this.a.b().a(0, false);
/*     */       
/*  78 */       if (counter == null) {
/*  79 */         counters = null;
/*     */       } else {
/*  81 */         counters = counter.a();
/*     */       } 
/*  83 */       this.b = counters;
/*     */     } 
/*  85 */     return this.b;
/*     */   }
/*     */   
/*     */   public boolean b() {
/*  89 */     return this.f;
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  93 */     return this.e;
/*     */   }
/*     */   
/*     */   public boolean d() {
/*  97 */     return this.d;
/*     */   }
/*     */   
/*     */   public boolean e() {
/* 101 */     return this.c;
/*     */   }
/*     */   
/*     */   public void f() {
/* 105 */     this.b = null;
/*     */   }
/*     */   
/*     */   public void a(boolean bookmarks) {
/* 109 */     this.f = bookmarks;
/*     */   }
/*     */   
/*     */   public void b(boolean fragments) {
/* 113 */     this.e = fragments;
/*     */   }
/*     */   
/*     */   public void c(boolean hyperlinks) {
/* 117 */     this.d = hyperlinks;
/*     */   }
/*     */   
/*     */   public void d(boolean processPageReference) {
/* 121 */     this.c = processPageReference;
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract void b(String paramString, Point2D paramPoint2D);
/*     */ 
/*     */   
/*     */   public void g() {}
/*     */   
/*     */   public void h() {
/* 131 */     if (this.f || this.c) {
/* 132 */       k state = this.a.b().a();
/* 133 */       for (int i = 0; i < state.f.length; i++) {
/* 134 */         state.f[i] = false;
/* 135 */         state.e[i] = state.g[i];
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public void a(AffineTransform transform, j box, double x, double y) {
/*     */     h pageRef;
/* 141 */     a ce = (box.b()).al;
/* 142 */     if (ce == null || ce.H == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 147 */     if (this.c) {
/* 148 */       pageRef = this.a.a().c();
/*     */     } else {
/* 150 */       pageRef = null;
/*     */     } 
/*     */     
/* 153 */     short type = (short)box.a();
/*     */     
/* 155 */     if (this.d && a(type)) {
/*     */       
/* 157 */       String href = null;
/* 158 */       URI uri = null;
/*     */       try {
/* 160 */         href = jp.cssj.homare.xml.b.j.a(ce.H);
/* 161 */         if (href != null) {
/* 162 */           if (href.length() > 4096) {
/* 163 */             throw new URISyntaxException(href, "URI too long: >4096");
/*     */           }
/* 165 */           f context = this.a.c();
/* 166 */           uri = d.a(context.c(), href);
/*     */         } 
/* 168 */       } catch (URISyntaxException e) {
/* 169 */         this.a.a((short)10252, e.getMessage());
/*     */       } 
/* 171 */       if (uri != null) {
/* 172 */         double width = box.p();
/* 173 */         double height = box.q();
/* 174 */         Shape s = new Rectangle2D.Double(x, y, width, height);
/* 175 */         if (!transform.isIdentity()) {
/* 176 */           s = transform.createTransformedShape(s);
/*     */         }
/* 178 */         a(s, uri, ce);
/*     */       } 
/*     */       
/* 181 */       if (type == 6) {
/*     */         
/* 183 */         String usemap = a.N.a(ce.H);
/* 184 */         if (usemap != null && usemap.startsWith("#")) {
/* 185 */           usemap = usemap.substring(1);
/* 186 */           g g = (g)this.a.a().d().get(usemap);
/* 187 */           if (g != null) {
/* 188 */             double f = f.a(this.a, 1.0D, (short)17, (short)21);
/* 189 */             AffineTransform t2 = AffineTransform.getScaleInstance(f, f);
/* 190 */             t2.translate(x, y);
/* 191 */             for (g.a area : g) {
/* 192 */               Shape s = area.b;
/* 193 */               if (!t2.isIdentity()) {
/* 194 */                 s = t2.createTransformedShape(s);
/*     */               }
/* 196 */               if (!transform.isIdentity()) {
/* 197 */                 s = transform.createTransformedShape(s);
/*     */               }
/* 199 */               a(s, area.a, null);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 205 */         C params = (C)box.b();
/* 206 */         g imageMap = (g)this.a.a().d().remove(params.a);
/* 207 */         if (imageMap != null) {
/* 208 */           AffineTransform t2 = AffineTransform.getTranslateInstance(x, y);
/* 209 */           t2.scale(box.s() / params.a.a(), box.t() / params.a.b());
/* 210 */           for (g.a link : imageMap) {
/* 211 */             Shape s = link.b;
/* 212 */             if (!t2.isIdentity()) {
/* 213 */               s = t2.createTransformedShape(s);
/*     */             }
/* 215 */             if (!transform.isIdentity()) {
/* 216 */               s = transform.createTransformedShape(s);
/*     */             }
/* 218 */             a(s, link.a, null);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 225 */     if ((this.e || pageRef != null) && b(type)) {
/* 226 */       String id = a.D.a(ce.H);
/* 227 */       if (id != null) {
/*     */         
/* 229 */         Point2D location = new Point2D.Double(x, y);
/* 230 */         if (!transform.isIdentity()) {
/* 231 */           location = transform.transform(location, location);
/*     */         }
/* 233 */         a(id, location);
/* 234 */         if (pageRef != null) {
/*     */           
/*     */           try {
/* 237 */             URI uri = d.a(this.a.c().c(), this.a
/* 238 */                 .c().a(), "#" + id);
/* 239 */             pageRef.a(uri, i());
/* 240 */           } catch (URISyntaxException e) {
/* 241 */             this.a.a((short)10252, e.getMessage());
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 248 */     if ((this.f || pageRef != null) && b(type)) {
/* 249 */       String header = a.d.a(ce.H);
/* 250 */       if (header != null) {
/*     */         try {
/*     */           String title;
/* 253 */           int level = Integer.parseInt(header);
/* 254 */           k state = this.a.b().a();
/*     */           
/* 256 */           StringBuffer textBuff = new StringBuffer();
/* 257 */           box.a(textBuff);
/*     */           
/* 259 */           if (textBuff.length() == 0) {
/* 260 */             title = null;
/*     */           } else {
/* 262 */             title = textBuff.toString();
/*     */           } 
/* 264 */           this.a.a((short)6146, (title == null) ? "" : title);
/*     */ 
/*     */ 
/*     */           
/* 268 */           for (int i = state.b - level; i >= 0 && state.c > 0; i--) {
/*     */             
/* 270 */             if (this.f) {
/* 271 */               a();
/*     */             }
/* 273 */             if (pageRef != null) {
/* 274 */               pageRef.b();
/*     */             }
/* 276 */             state.c--;
/* 277 */             state.b--;
/* 278 */             state.g[state.b] = null;
/*     */           } 
/*     */           
/* 281 */           String ref = "cssj-header-" + ++state.d;
/*     */           
/* 283 */           Point2D location = null;
/* 284 */           if (this.f || pageRef != null) {
/* 285 */             location = new Point2D.Double(x, y);
/* 286 */             if (!transform.isIdentity()) {
/* 287 */               location = transform.transform(location, location);
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 292 */           if (this.f)
/*     */           {
/* 294 */             b(title, location);
/*     */           }
/* 296 */           if (pageRef != null) {
/*     */             
/*     */             try {
/* 299 */               URI uri = d.a(this.a.c().c(), this.a
/* 300 */                   .c().a(), "#" + ref);
/* 301 */               pageRef.a(uri, title, i());
/* 302 */               a(ref, location);
/* 303 */             } catch (URISyntaxException e) {
/* 304 */               this.a.a((short)10252, e.getMessage());
/*     */             } 
/*     */           }
/*     */           
/* 308 */           state.c++;
/* 309 */           state.b = level;
/* 310 */           if (!state.f[level - 1]) {
/* 311 */             state.e[level - 1] = title;
/* 312 */             state.f[level - 1] = true;
/*     */           } 
/* 314 */           state.g[level - 1] = title;
/* 315 */         } catch (NumberFormatException e) {
/* 316 */           this.a.a((short)10250, header);
/*     */         } 
/*     */       }
/*     */       
/* 320 */       if (ce.H != null) {
/*     */         
/* 322 */         String annot = a.c.a(ce.H);
/* 323 */         if (annot != null)
/* 324 */           this.a.a((short)6148, annot); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */