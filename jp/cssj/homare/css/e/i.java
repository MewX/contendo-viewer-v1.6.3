/*     */ package jp.cssj.homare.css.e;
/*     */ 
/*     */ import jp.cssj.homare.css.f.W;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.css.f.h;
/*     */ import jp.cssj.homare.css.f.l;
/*     */ import jp.cssj.homare.css.f.v;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class i
/*     */ {
/*     */   public static ad a(LexicalUnit lu) {
/*     */     String ident;
/*  26 */     short luType = lu.getLexicalUnitType();
/*  27 */     switch (luType) {
/*     */       case 35:
/*  29 */         ident = lu.getStringValue().toLowerCase();
/*  30 */         if (ident.equals("top"))
/*  31 */           return (ad)l.e; 
/*  32 */         if (ident.equals("bottom"))
/*  33 */           return (ad)l.f; 
/*  34 */         if (ident.equals("before"))
/*  35 */           return (ad)l.g; 
/*  36 */         if (ident.equals("after")) {
/*  37 */           return (ad)l.h;
/*     */         }
/*     */         break;
/*     */     } 
/*  41 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ad b(LexicalUnit lu) {
/*     */     String ident;
/*  52 */     short luType = lu.getLexicalUnitType();
/*  53 */     switch (luType) {
/*     */       case 35:
/*  55 */         ident = lu.getStringValue().toLowerCase();
/*  56 */         if (ident.equals("auto"))
/*  57 */           return (ad)W.a; 
/*  58 */         if (ident.equals("fixed"))
/*  59 */           return (ad)W.b; 
/*     */         break;
/*     */     } 
/*  62 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ad c(LexicalUnit lu) {
/*     */     String ident;
/*  73 */     short luType = lu.getLexicalUnitType();
/*  74 */     switch (luType) {
/*     */       case 35:
/*  76 */         ident = lu.getStringValue().toLowerCase();
/*  77 */         if (ident.equals("collapse"))
/*  78 */           return (ad)h.b; 
/*  79 */         if (ident.equals("separate"))
/*  80 */           return (ad)h.a; 
/*     */         break;
/*     */     } 
/*  83 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ad d(LexicalUnit lu) {
/*     */     String ident;
/*  94 */     short luType = lu.getLexicalUnitType();
/*  95 */     switch (luType) {
/*     */       case 35:
/*  97 */         ident = lu.getStringValue().toLowerCase();
/*  98 */         if (ident.equals("show"))
/*  99 */           return (ad)v.a; 
/* 100 */         if (ident.equals("hide"))
/* 101 */           return (ad)v.b; 
/*     */         break;
/*     */     } 
/* 104 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/e/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */