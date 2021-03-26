/*     */ package jp.cssj.e.b;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import jp.cssj.e.b;
/*     */ import jp.cssj.e.c;
/*     */ import jp.cssj.e.c.b;
/*     */ import jp.cssj.e.d.b;
/*     */ import jp.cssj.e.f.b;
/*     */ import jp.cssj.e.i.b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   implements c
/*     */ {
/*  23 */   private Map<String, c> a = new HashMap<>();
/*     */   
/*  25 */   private c b = (c)new b();
/*     */   
/*  27 */   private String c = "file";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static a a() {
/*  35 */     a resolver = new a();
/*  36 */     resolver.a("file", (c)new b());
/*     */     try {
/*  38 */       b httpSourceResolver = new b();
/*  39 */       resolver.a("http", (c)httpSourceResolver);
/*  40 */       resolver.a("https", (c)httpSourceResolver);
/*  41 */     } catch (Throwable throwable) {}
/*     */ 
/*     */     
/*  44 */     resolver.a("data", (c)new b());
/*  45 */     return resolver;
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
/*     */   public void a(String scheme, c resolver) {
/*  57 */     this.a.put(scheme.trim().toLowerCase(), resolver);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String scheme) {
/*  67 */     this.a.remove(scheme.trim().toLowerCase());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c b(String scheme) {
/*  78 */     return this.a.get(scheme.trim()
/*  79 */         .toLowerCase());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<String> b() {
/*  88 */     return this.a.keySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(c defaultResolver) {
/*  98 */     this.b = defaultResolver;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c c() {
/* 107 */     return this.b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(String defaultScheme) {
/* 117 */     this.c = defaultScheme;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String d() {
/* 126 */     return this.c;
/*     */   }
/*     */   
/*     */   protected c a(URI uri) {
/* 130 */     String scheme = uri.getScheme();
/* 131 */     if (scheme == null) {
/* 132 */       scheme = this.c;
/*     */     }
/* 134 */     c resolver = b(scheme);
/* 135 */     if (resolver == null) {
/* 136 */       return this.b;
/*     */     }
/* 138 */     return resolver;
/*     */   }
/*     */   
/*     */   public b b(URI uri) throws IOException {
/* 142 */     c resolver = a(uri);
/* 143 */     return resolver.b(uri);
/*     */   }
/*     */   
/*     */   public void a(b source) {
/* 147 */     c resolver = a(source.d());
/* 148 */     resolver.a(source);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 152 */     return super.toString() + "[defaultSchema=" + this.c + ",defaultResolver=" + this.b + ",map=" + this.a + "]";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/e/b/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */