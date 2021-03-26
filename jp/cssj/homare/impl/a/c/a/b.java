/*     */ package jp.cssj.homare.impl.a.c.a;
/*     */ 
/*     */ import java.net.URI;
/*     */ import jp.cssj.homare.b.a.c.m;
/*     */ import jp.cssj.homare.css.c;
/*     */ import jp.cssj.homare.css.c.a;
/*     */ import jp.cssj.homare.css.c.e;
/*     */ import jp.cssj.homare.css.c.j;
/*     */ import jp.cssj.homare.css.c.l;
/*     */ import jp.cssj.homare.css.e.l;
/*     */ import jp.cssj.homare.css.f.C;
/*     */ import jp.cssj.homare.css.f.E;
/*     */ import jp.cssj.homare.css.f.M;
/*     */ import jp.cssj.homare.css.f.a;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.css.f.d;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */   extends a
/*     */ {
/*  31 */   public static final j a = (j)new b();
/*     */   
/*  33 */   public static final j b = (j)new b();
/*     */   
/*  35 */   private static final j[] c = new j[] { a, b }; public static m a(c style, jp.cssj.sakae.c.b.b image) { byte widthType; double width; byte heightType;
/*     */     double height;
/*     */     m size;
/*  38 */     ad widthValue = style.a(a);
/*  39 */     ad heightValue = style.a(b);
/*     */ 
/*     */     
/*  42 */     switch (widthValue.a()) {
/*     */       case 1000:
/*  44 */         widthType = 1;
/*  45 */         width = ((a)widthValue).c();
/*     */         break;
/*     */       case 23:
/*  48 */         widthType = 2;
/*  49 */         width = ((M)widthValue).c();
/*     */         break;
/*     */       case 1006:
/*  52 */         widthType = 1;
/*  53 */         width = image.a();
/*     */         break;
/*     */       default:
/*  56 */         throw new IllegalStateException();
/*     */     } 
/*     */ 
/*     */     
/*  60 */     switch (heightValue.a()) {
/*     */       case 1000:
/*  62 */         heightType = 1;
/*  63 */         height = ((a)heightValue).c();
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
/*  76 */         size = m.a(width, height, widthType, heightType);
/*  77 */         return size;case 23: heightType = 2; height = ((M)heightValue).c(); size = m.a(width, height, widthType, heightType); return size;case 1006: heightType = 1; height = image.b(); size = m.a(width, height, widthType, heightType); return size;
/*     */     } 
/*     */     throw new IllegalStateException(); }
/*     */    protected b() {
/*  81 */     super("-cssj-background-size");
/*     */   }
/*     */   
/*     */   public ad b(c style) {
/*  85 */     return (ad)d.a;
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  89 */     return false;
/*     */   }
/*     */   
/*     */   protected j[] a() {
/*  93 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ad a(ad value, c style) {
/* 100 */     return l.a(value, style);
/*     */   }
/*     */   protected e.a[] a(LexicalUnit lu, m ua, URI uri) throws l {
/*     */     E e1, e2;
/* 104 */     if (lu.getLexicalUnitType() == 12) {
/* 105 */       return new e.a[] { new e.a(a, (ad)C.a), new e.a(b, (ad)C.a) };
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 110 */     if (l.a(lu)) {
/* 111 */       d d = d.a;
/*     */     } else {
/* 113 */       M m1 = l.d(lu);
/* 114 */       if (m1 == null) {
/* 115 */         e1 = l.a(ua, lu);
/* 116 */         if (e1 == null || e1.d()) {
/* 117 */           throw new l();
/*     */         }
/* 119 */       } else if (((M)e1).d()) {
/* 120 */         throw new l();
/*     */       } 
/*     */     } 
/*     */     
/* 124 */     lu = lu.getNextLexicalUnit();
/* 125 */     if (lu == null) {
/* 126 */       e2 = e1;
/* 127 */       return new e.a[] { new e.a(a, (ad)e1), new e.a(b, (ad)e2) };
/*     */     } 
/*     */     
/* 130 */     if (l.a(lu)) {
/* 131 */       d d = d.a;
/*     */     } else {
/* 133 */       M m1 = l.d(lu);
/* 134 */       if (m1 == null) {
/* 135 */         e2 = l.a(ua, lu);
/* 136 */         if (e2 != null && e2.d()) {
/* 137 */           throw new l();
/*     */         }
/* 139 */       } else if (((M)e2).d()) {
/* 140 */         throw new l();
/*     */       } 
/*     */     } 
/* 143 */     if (e2 == null) {
/* 144 */       e2 = e1;
/*     */     }
/* 146 */     return new e.a[] { new e.a(a, (ad)e1), new e.a(b, (ad)e2) };
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */