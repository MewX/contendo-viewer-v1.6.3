/*     */ package jp.cssj.homare.impl.a.c.a;
/*     */ 
/*     */ import java.net.URI;
/*     */ import jp.cssj.homare.css.c.d;
/*     */ import jp.cssj.homare.css.c.l;
/*     */ import jp.cssj.homare.css.c.o;
/*     */ import jp.cssj.homare.css.e.c;
/*     */ import jp.cssj.homare.css.e.l;
/*     */ import jp.cssj.homare.css.f.C;
/*     */ import jp.cssj.homare.css.f.H;
/*     */ import jp.cssj.homare.css.f.V;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.css.f.n;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class z
/*     */   extends d
/*     */ {
/*  22 */   public static final o a = (o)new z();
/*     */   
/*     */   protected z() {
/*  25 */     super("-cssj-text-emphasis");
/*     */   } public void a(LexicalUnit lu, m ua, URI uri, d.a primitives) throws l {
/*     */     n n;
/*     */     ad strv;
/*  29 */     if (lu.getLexicalUnitType() == 12) {
/*  30 */       primitives.a(A.a, (ad)C.a);
/*  31 */       primitives.a(y.a, (ad)C.a);
/*     */       return;
/*     */     } 
/*  34 */     byte fill = 0, type = 0;
/*  35 */     ad color = null;
/*  36 */     boolean none = false;
/*  37 */     String str = null; while (true)
/*     */     { String ident;
/*  39 */       switch (lu.getLexicalUnitType()) {
/*     */         case 35:
/*  41 */           if (l.b(lu)) {
/*  42 */             if (type != 0 || fill != 0 || str != null || none) {
/*  43 */               throw new l();
/*     */             }
/*  45 */             primitives.a(A.a, (ad)H.a);
/*  46 */             none = true;
/*     */             break;
/*     */           } 
/*  49 */           ident = lu.getStringValue().toLowerCase();
/*  50 */           if (ident.equals("filled")) {
/*  51 */             if (fill != 0 || str != null || none) {
/*  52 */               throw new l();
/*     */             }
/*  54 */             fill = 1; break;
/*  55 */           }  if (ident.equals("open")) {
/*  56 */             if (fill != 0 || str != null || none) {
/*  57 */               throw new l();
/*     */             }
/*  59 */             fill = 2; break;
/*  60 */           }  if (ident.equals("dot")) {
/*  61 */             if (type != 0 || str != null || none) {
/*  62 */               throw new l();
/*     */             }
/*  64 */             type = 1; break;
/*  65 */           }  if (ident.equals("circle")) {
/*  66 */             if (type != 0 || str != null || none) {
/*  67 */               throw new l();
/*     */             }
/*  69 */             type = 2; break;
/*  70 */           }  if (ident.equals("double-circle")) {
/*  71 */             if (type != 0 || str != null || none) {
/*  72 */               throw new l();
/*     */             }
/*  74 */             type = 3; break;
/*  75 */           }  if (ident.equals("triangle")) {
/*  76 */             if (type != 0 || str != null || none) {
/*  77 */               throw new l();
/*     */             }
/*  79 */             type = 4; break;
/*  80 */           }  if (ident.equals("sesame")) {
/*  81 */             if (type != 0 || str != null || none) {
/*  82 */               throw new l();
/*     */             }
/*  84 */             type = 5; break;
/*     */           } 
/*  86 */           if (color != null) {
/*  87 */             throw new l();
/*     */           }
/*  89 */           n = c.a(ua, lu);
/*  90 */           if (n == null) {
/*  91 */             throw new l();
/*     */           }
/*     */           break;
/*     */         
/*     */         case 36:
/*  96 */           if (fill != 0 || str != null) {
/*  97 */             throw new l();
/*     */           }
/*  99 */           str = lu.getStringValue();
/*     */           break;
/*     */         
/*     */         default:
/* 103 */           if (n != null) {
/* 104 */             throw new l();
/*     */           }
/* 106 */           n = c.a(ua, lu);
/* 107 */           if (n == null) {
/* 108 */             throw new l();
/*     */           }
/*     */           break;
/*     */       } 
/* 112 */       lu = lu.getNextLexicalUnit();
/* 113 */       if (lu == null) {
/* 114 */         if (str != null) {
/* 115 */           primitives.a(A.a, (ad)new V(str));
/* 116 */         } else if (!none) {
/* 117 */           if (type == 0) {
/* 118 */             type = -1;
/*     */           }
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
/*     */           break;
/*     */         } 
/*     */       } else {
/*     */         continue;
/*     */       } 
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
/* 169 */       if (n != null)
/* 170 */         primitives.a(y.a, (ad)n);  return; }  switch (type) { case -1: if (fill != 2) { ad ad = A.b; break; }  strv = A.c; break;case 1: if (fill != 2) { strv = A.d; break; }  strv = A.e; break;case 2: if (fill != 2) { strv = A.f; break; }  strv = A.g; break;case 3: if (fill != 2) { strv = A.h; break; }  strv = A.i; break;case 4: if (fill != 2) { strv = A.j; break; }  strv = A.k; break;case 5: if (fill != 2) { strv = A.l; break; }  strv = A.m; break;default: throw new l(); }  primitives.a(A.a, strv); if (n != null) primitives.a(y.a, (ad)n); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/z.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */