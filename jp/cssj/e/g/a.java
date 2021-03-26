/*     */ package jp.cssj.e.g;
/*     */ 
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.e.b;
/*     */ import jp.cssj.e.c;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   implements c
/*     */ {
/*  21 */   private final List<a> c = new ArrayList<>();
/*     */   private c b;
/*     */   
/*     */   protected static class a {
/*     */     public final boolean a;
/*     */     public final int[] b;
/*     */     
/*     */     public a(boolean permit, int[] pattern) {
/*  29 */       this.a = permit;
/*  30 */       this.b = pattern;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public a(c enclosedSourceResolver) {
/*  41 */     this.b = enclosedSourceResolver;
/*     */   }
/*     */   
/*     */   public a() {
/*  45 */     this(null);
/*     */   }
/*     */   
/*     */   private static char a(char b) {
/*  49 */     if (b >= '0' && b <= '9')
/*  50 */       return (char)(b - 48); 
/*  51 */     if (b >= 'a' && b <= 'f')
/*  52 */       return (char)(b - 97 + 10); 
/*  53 */     if (b >= 'A' && b <= 'F')
/*  54 */       return (char)(b - 65 + 10); 
/*  55 */     return Character.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public static String a(URI uri) {
/*  59 */     String key = uri.toString();
/*  60 */     String scheme = uri.getScheme();
/*  61 */     if (scheme != null && (scheme.equals("http") || scheme.equals("https"))) {
/*     */       
/*  63 */       String exclude = "*?#";
/*  64 */       char[] ch = key.toCharArray();
/*  65 */       int len = ch.length;
/*  66 */       int ix = 0;
/*  67 */       int ox = 0;
/*  68 */       while (ix < len) {
/*  69 */         char b = ch[ix++];
/*  70 */         if (b == '?') {
/*  71 */           exclude = "*&=#";
/*  72 */         } else if (b == '+') {
/*  73 */           b = ' ';
/*  74 */         } else if (b == '%') {
/*  75 */           char c1 = (char)((a(ch[ix]) << 4) + a(ch[ix + 1]));
/*  76 */           if (exclude.indexOf(c1) == -1) {
/*  77 */             b = c1;
/*  78 */             ix += 2;
/*     */           } else {
/*  80 */             ch[ix] = Character.toUpperCase(ch[ix]);
/*  81 */             ch[ix + 1] = Character.toUpperCase(ch[ix + 1]);
/*     */           } 
/*     */         } 
/*  84 */         ch[ox++] = b;
/*     */       } 
/*  86 */       key = new String(ch, 0, ox);
/*     */     } 
/*  88 */     return key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(URI uriPattern) {
/*  99 */     String key = a(uriPattern.normalize());
/* 100 */     this.c.add(new a(true, b.a(key)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void d(URI uriPattern) {
/* 111 */     String key = a(uriPattern.normalize());
/* 112 */     this.c.add(new a(false, b.a(key)));
/*     */   }
/*     */   
/*     */   public b b(URI uri) throws IOException, SecurityException {
/* 116 */     return a(uri, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public b a(URI uri, boolean force) throws IOException, SecurityException {
/* 121 */     uri = uri.normalize();
/*     */     
/* 123 */     String key = a(uri);
/* 124 */     key = key.replaceAll("\\*", "%2A");
/* 125 */     if (force) {
/* 126 */       if (this.b == null) {
/* 127 */         throw new FileNotFoundException(key);
/*     */       }
/* 129 */       return this.b.b(uri);
/*     */     } 
/* 131 */     for (int i = 0; i < this.c.size(); i++) {
/* 132 */       a pattern = this.c.get(i);
/* 133 */       if (b.a(key, pattern.b)) {
/* 134 */         if (pattern.a) {
/* 135 */           if (this.b == null) {
/* 136 */             throw new FileNotFoundException(key);
/*     */           }
/* 138 */           return this.b.b(uri);
/*     */         } 
/* 140 */         throw new SecurityException(key);
/*     */       } 
/*     */     } 
/*     */     
/* 144 */     if ("data".equals(uri.getScheme())) {
/* 145 */       return this.b.b(uri);
/*     */     }
/* 147 */     throw new SecurityException(key);
/*     */   }
/*     */   
/*     */   public void a(b source) {
/* 151 */     if (!a && this.b == null) throw new AssertionError(); 
/* 152 */     this.b.a(source);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c a() {
/* 161 */     return this.b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(c enclosedSourceResolver) {
/* 171 */     this.b = enclosedSourceResolver;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b() {
/* 178 */     this.b = null;
/* 179 */     this.c.clear();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/e/g/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */