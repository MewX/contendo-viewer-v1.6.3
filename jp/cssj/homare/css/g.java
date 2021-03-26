/*     */ package jp.cssj.homare.css;
/*     */ 
/*     */ import java.net.URI;
/*     */ import jp.cssj.homare.css.c.k;
/*     */ import jp.cssj.homare.css.c.n;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.sac.css.CSSException;
/*     */ import jp.cssj.sakae.sac.css.DocumentHandler;
/*     */ import jp.cssj.sakae.sac.css.InputSource;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*     */ import jp.cssj.sakae.sac.css.SACMediaList;
/*     */ import jp.cssj.sakae.sac.css.SelectorList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class g
/*     */   implements DocumentHandler
/*     */ {
/*     */   private final m b;
/*     */   private n c;
/*     */   private f d;
/*     */   private URI e;
/*     */   
/*     */   public g(m ua) {
/*  31 */     if (!a && ua == null) throw new AssertionError(); 
/*  32 */     this.b = ua;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(URI uri) {
/*  41 */     if (!a && uri == null) throw new AssertionError(); 
/*  42 */     this.e = uri;
/*     */   }
/*     */   
/*     */   public URI a() {
/*  46 */     return this.e;
/*     */   }
/*     */   
/*     */   public void a(n propertySet) {
/*  50 */     if (!a && propertySet == null) throw new AssertionError(); 
/*  51 */     this.c = propertySet;
/*     */   }
/*     */   
/*     */   public void a(f declaration) {
/*  55 */     this.d = declaration;
/*     */   }
/*     */   
/*     */   public f b() {
/*  59 */     return this.d;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startDocument(InputSource source) throws CSSException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void endDocument(InputSource source) throws CSSException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void comment(String text) throws CSSException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void ignorableAtRule(String atRule) throws CSSException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void namespaceDeclaration(String prefix, String uri) throws CSSException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void importStyle(String uri, SACMediaList media, String defaultNamespaceURI) throws CSSException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void startMedia(SACMediaList media) throws CSSException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void endMedia(SACMediaList media) throws CSSException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void startPage(String name, String pseudo_page) throws CSSException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void endPage(String name, String pseudoPage) throws CSSException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void startFontFace() throws CSSException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void endFontFace() throws CSSException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void startSelector(SelectorList selectors) throws CSSException {}
/*     */ 
/*     */   
/*     */   public void endSelector(SelectorList selectors) throws CSSException {}
/*     */ 
/*     */   
/*     */   public void property(String name, LexicalUnit lu, boolean important) throws CSSException {
/* 119 */     if (!a && (name == null || this.e == null || lu == null)) throw new AssertionError(); 
/* 120 */     if (c()) {
/* 121 */       k property = this.c.a(name, lu, this.b, this.e, important);
/* 122 */       if (property == null) {
/*     */         return;
/*     */       }
/* 125 */       if (this.d == null) {
/* 126 */         this.d = new f();
/*     */       }
/* 128 */       this.d.a(property);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean c() {
/* 134 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */