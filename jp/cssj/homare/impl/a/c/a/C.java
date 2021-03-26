/*     */ package jp.cssj.homare.impl.a.c.a;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.homare.b.a.c.L;
/*     */ import jp.cssj.homare.css.c;
/*     */ import jp.cssj.homare.css.c.b;
/*     */ import jp.cssj.homare.css.c.j;
/*     */ import jp.cssj.homare.css.c.l;
/*     */ import jp.cssj.homare.css.e.c;
/*     */ import jp.cssj.homare.css.e.l;
/*     */ import jp.cssj.homare.css.f.E;
/*     */ import jp.cssj.homare.css.f.a;
/*     */ import jp.cssj.homare.css.f.a.j;
/*     */ import jp.cssj.homare.css.f.aa;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.css.f.n;
/*     */ import jp.cssj.homare.impl.a.c.u;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.c.c.b;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class C
/*     */   extends b
/*     */ {
/*  30 */   public static final j a = (j)new C();
/*     */   
/*     */   public static L[] c(c style) {
/*  33 */     j value = (j)style.a(a);
/*  34 */     if ((value.b()).length == 0) {
/*  35 */       return null;
/*     */     }
/*  37 */     j.a[] src = value.b();
/*  38 */     L[] shadows = new L[src.length];
/*  39 */     for (int i = 0; i < src.length; i++) {
/*     */       double x, y;
/*     */       
/*     */       n n;
/*  43 */       if ((src[i]).a == null) {
/*  44 */         x = 0.0D;
/*     */       } else {
/*  46 */         x = ((a)l.a((ad)(src[i]).a, style)).c();
/*     */       } 
/*  48 */       if ((src[i]).b == null) {
/*  49 */         y = 0.0D;
/*     */       } else {
/*  51 */         y = ((a)l.a((ad)(src[i]).a, style)).c();
/*     */       } 
/*  53 */       if ((src[i]).c == null) {
/*  54 */         b color = u.c(style);
/*     */       } else {
/*  56 */         n = (src[i]).c;
/*     */       } 
/*  58 */       shadows[i] = new L(x, y, (b)n);
/*     */     } 
/*  60 */     return shadows;
/*     */   }
/*     */   
/*     */   protected C() {
/*  64 */     super("text-shadow");
/*     */   }
/*     */   
/*     */   public ad b(c style) {
/*  68 */     return (ad)j.a;
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  72 */     return true;
/*     */   }
/*     */   
/*     */   public ad a(ad value, c style) {
/*  76 */     return value;
/*     */   }
/*     */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*     */     n n;
/*  80 */     if (l.b(lu)) {
/*  81 */       return (ad)j.a;
/*     */     }
/*  83 */     List<j.a> shadows = null;
/*  84 */     E x = null;
/*  85 */     E y = null;
/*  86 */     ad color = null;
/*  87 */     for (; lu != null; lu = lu.getNextLexicalUnit()) {
/*  88 */       if (lu.getLexicalUnitType() == 0) {
/*  89 */         if (color == null || color != aa.a) {
/*  90 */           if (shadows == null) {
/*  91 */             shadows = new ArrayList<>();
/*     */           }
/*  93 */           shadows.add(new j.a(x, y, (n)color));
/*     */         } 
/*  95 */         x = y = null;
/*  96 */         color = null;
/*     */         continue;
/*     */       } 
/*  99 */       if (x == null) {
/* 100 */         x = l.a(ua, lu);
/* 101 */         if (x != null) {
/*     */           continue;
/*     */         }
/*     */       } 
/* 105 */       if (y == null) {
/* 106 */         y = l.a(ua, lu);
/* 107 */         if (y != null) {
/*     */           continue;
/*     */         }
/*     */       } 
/* 111 */       if (color == null) {
/* 112 */         if (c.a(lu)) {
/* 113 */           aa aa = aa.a;
/*     */         } else {
/* 115 */           n = c.a(ua, lu);
/*     */         } 
/* 117 */         if (n != null) {
/*     */           continue;
/*     */         }
/*     */       } 
/* 121 */       throw new l();
/*     */     } 
/* 123 */     if (n == null || n != aa.a) {
/* 124 */       if (shadows == null) {
/* 125 */         shadows = new ArrayList<>();
/*     */       }
/* 127 */       shadows.add(new j.a(x, y, n));
/*     */     } 
/* 129 */     if (shadows == null) {
/* 130 */       return (ad)j.a;
/*     */     }
/* 132 */     return (ad)j.a(shadows.<j.a>toArray(new j.a[shadows.size()]));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/C.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */