/*     */ package jp.cssj.sakae.c.d.a.a;
/*     */ 
/*     */ import jp.cssj.sakae.c.a.f;
/*     */ import jp.cssj.sakae.c.a.h;
/*     */ import jp.cssj.sakae.c.d.a.a;
/*     */ import jp.cssj.sakae.c.d.d;
/*     */ import jp.cssj.sakae.c.d.e;
/*     */ import jp.cssj.sakae.c.d.g;
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
/*     */ public class d
/*     */   implements d
/*     */ {
/*     */   protected a a;
/*     */   private e b;
/*  25 */   private char c = Character.MIN_VALUE;
/*     */   
/*  27 */   private g d = null;
/*     */   
/*     */   public d(a hyph) {
/*  30 */     this.a = hyph;
/*     */   }
/*     */   
/*     */   public a c() {
/*  34 */     return this.a;
/*     */   }
/*     */   
/*     */   public void a(a hyph) {
/*  38 */     this.a = hyph;
/*     */   }
/*     */   
/*     */   public void a(e glyphHandler) {
/*  42 */     this.b = glyphHandler;
/*     */   }
/*     */   
/*     */   public void a(int charOffset, h fontStyle, f fontMetrics) {
/*  46 */     this.b.a(charOffset, fontStyle, fontMetrics);
/*     */   }
/*     */   
/*     */   public void a() {
/*  50 */     this.b.a();
/*     */   }
/*     */   
/*     */   public void a(int charOffset, char[] ch, int coff, byte clen, int gid) {
/*  54 */     char c1 = ch[coff];
/*  55 */     char c2 = ch[coff + clen - 1];
/*  56 */     a(c1, c2, clen);
/*  57 */     if (this.d != null) {
/*  58 */       this.b.a(this.d);
/*  59 */       this.d = null;
/*     */     } 
/*  61 */     this.b.a(charOffset, ch, coff, clen, gid);
/*     */   }
/*     */   
/*     */   public void a(g quad) {
/*  65 */     String str = quad.d();
/*  66 */     if (str == "​") {
/*     */       
/*  68 */       if (this.c != '\000' && this.c != '⁠') {
/*  69 */         d();
/*     */       }
/*  71 */       this.c = '​';
/*  72 */     } else if (str == "​⁠") {
/*     */       
/*  74 */       if (this.c == '​') {
/*  75 */         d();
/*  76 */         this.c = '⁠';
/*     */       } else {
/*  78 */         if (this.d != null) {
/*  79 */           this.b.a(this.d);
/*     */         }
/*  81 */         this.d = quad;
/*     */         return;
/*     */       } 
/*  84 */     } else if (str != "⁠​") {
/*     */       
/*  86 */       if (str.length() == 0) {
/*     */         
/*  88 */         if (this.c == '​') {
/*  89 */           d();
/*     */         }
/*  91 */         this.c = '⁠';
/*     */       } else {
/*     */         
/*  94 */         int strlen = str.length();
/*  95 */         char c1 = str.charAt(0);
/*  96 */         char c2 = str.charAt(strlen - 1);
/*  97 */         a(c1, c2, strlen);
/*     */       } 
/*  99 */     }  if (this.d != null) {
/* 100 */       this.b.a(this.d);
/* 101 */       this.d = null;
/*     */     } 
/* 103 */     this.b.a(quad);
/*     */   }
/*     */   
/*     */   private void d() {
/* 107 */     this.b.b();
/*     */   }
/*     */   
/*     */   public void b() {
/* 111 */     if (this.d != null) {
/* 112 */       this.b.a(this.d);
/* 113 */       this.d = null;
/*     */     } 
/* 115 */     d();
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
/*     */   private void a(char c1, char c2, int charCount) {
/* 128 */     if (this.c != '\000' && this.c != '⁠' && (this.c == '​' || 
/* 129 */       !this.a.a(this.c, c1))) {
/* 130 */       d();
/*     */     }
/* 132 */     this.c = c2;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/d/a/a/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */