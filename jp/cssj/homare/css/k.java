/*     */ package jp.cssj.homare.css;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.StringReader;
/*     */ import java.net.URI;
/*     */ import jp.cssj.homare.b.a.c.u;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ import jp.cssj.homare.css.a.b;
/*     */ import jp.cssj.homare.css.c.f;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.css.f.ah;
/*     */ import jp.cssj.homare.impl.a.c.G;
/*     */ import jp.cssj.homare.impl.a.c.L;
/*     */ import jp.cssj.homare.impl.a.c.a.c;
/*     */ import jp.cssj.homare.impl.a.c.au;
/*     */ import jp.cssj.homare.impl.a.c.aw;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.homare.xml.c.a;
/*     */ import jp.cssj.sakae.sac.css.CSSException;
/*     */ import jp.cssj.sakae.sac.css.InputSource;
/*     */ import jp.cssj.sakae.sac.parser.Parser;
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
/*     */ public class k
/*     */ {
/*     */   private final m b;
/*     */   private final l c;
/*     */   private final Parser d;
/*     */   private final g e;
/*     */   private final b f;
/*     */   
/*     */   public k(m ua, l styleContext) {
/*  43 */     this.f = new b();
/*  44 */     this.b = ua;
/*  45 */     this.c = styleContext;
/*     */     
/*  47 */     this.e = new g(ua);
/*  48 */     this.e.a(f.b());
/*     */     
/*  50 */     this.d = new Parser();
/*  51 */     this.d.setDocumentHandler(this.e);
/*     */     
/*  53 */     a(ua.c().a());
/*     */   }
/*     */   
/*     */   public l a() {
/*  57 */     return this.c;
/*     */   }
/*     */   
/*     */   public void a(URI uri) {
/*  61 */     if (!a && uri == null) throw new AssertionError(); 
/*  62 */     this.e.a(uri);
/*     */   }
/*     */   
/*     */   public URI b() {
/*  66 */     return this.e.a();
/*     */   }
/*     */   
/*     */   public void a(c style) {
/*  70 */     a ce = style.a();
/*     */     
/*  72 */     this.c.a(ce);
/*  73 */     f declaration = this.c.a((f)null);
/*     */ 
/*     */     
/*  76 */     this.e.a(declaration);
/*  77 */     String inlineStyleDecl = ce.H.getValue("http://www.w3.org/1999/xhtml", a.F.b);
/*  78 */     if (inlineStyleDecl != null) {
/*     */       try {
/*  80 */         this.d.parseStyleDeclaration(new InputSource(new StringReader(inlineStyleDecl)));
/*  81 */         declaration = this.e.b();
/*  82 */       } catch (CSSException e) {
/*  83 */         this.b.a((short)10263, inlineStyleDecl, e.getMessage());
/*  84 */       } catch (IOException e) {
/*  85 */         throw new RuntimeException(e);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*  90 */     this.f.c(style);
/*     */ 
/*     */     
/*  93 */     if (declaration != null) {
/*  94 */       declaration.a(style);
/*     */     }
/*     */     
/*  97 */     short display = (short)G.c(style);
/*  98 */     if (display == 14 && aw.d(style).a() == 1) {
/*     */       u length;
/*     */       
/* 101 */       c pStyle = style.c();
/* 102 */       if (pStyle != null && e.a(c.c(pStyle))) {
/* 103 */         length = L.d(style);
/*     */       } else {
/* 105 */         length = aw.d(style);
/*     */       } 
/* 107 */       if (length.a() == 1) {
/* 108 */         style.a(au.a, (ad)ah.a);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void c() {
/* 114 */     this.c.a();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/k.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */