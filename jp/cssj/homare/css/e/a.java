/*     */ package jp.cssj.homare.css.e;
/*     */ 
/*     */ import jp.cssj.homare.css.f.E;
/*     */ import jp.cssj.homare.css.f.i;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class a
/*     */ {
/*     */   public static E a(m ua, LexicalUnit lu) {
/*     */     String ident;
/*  26 */     short luType = lu.getLexicalUnitType();
/*  27 */     switch (luType)
/*     */     { case 35:
/*  29 */         ident = lu.getStringValue().toLowerCase();
/*  30 */         if (ident.equals("thin"))
/*  31 */           return (E)ua.c((byte)1); 
/*  32 */         if (ident.equals("medium"))
/*  33 */           return (E)ua.c((byte)2); 
/*  34 */         if (ident.equals("thick")) {
/*  35 */           return (E)ua.c((byte)3);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  46 */         return null; }  E length = l.a(ua, lu); if (length == null || !length.d()) return length;  return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static i a(LexicalUnit lu) {
/*     */     String ident;
/*  56 */     short luType = lu.getLexicalUnitType();
/*  57 */     switch (luType) {
/*     */       case 35:
/*  59 */         ident = lu.getStringValue().toLowerCase();
/*  60 */         if (ident.equals("none"))
/*  61 */           return i.k; 
/*  62 */         if (ident.equals("hidden"))
/*  63 */           return i.l; 
/*  64 */         if (ident.equals("dotted"))
/*  65 */           return i.p; 
/*  66 */         if (ident.equals("dashed"))
/*  67 */           return i.o; 
/*  68 */         if (ident.equals("solid"))
/*  69 */           return i.n; 
/*  70 */         if (ident.equals("double"))
/*  71 */           return i.m; 
/*  72 */         if (ident.equals("groove"))
/*  73 */           return i.s; 
/*  74 */         if (ident.equals("ridge"))
/*  75 */           return i.q; 
/*  76 */         if (ident.equals("inset"))
/*  77 */           return i.t; 
/*  78 */         if (ident.equals("outset"))
/*  79 */           return i.r; 
/*     */         break;
/*     */     } 
/*  82 */     return null;
/*     */   }
/*     */   
/*     */   public static jp.cssj.homare.css.f.a.a b(m ua, LexicalUnit lu) {
/*  86 */     E vr, hr = l.a(ua, lu);
/*  87 */     if (hr == null) {
/*  88 */       return null;
/*     */     }
/*  90 */     lu = lu.getNextLexicalUnit();
/*     */     
/*  92 */     if (lu != null) {
/*  93 */       if (lu.getNextLexicalUnit() != null) {
/*  94 */         return null;
/*     */       }
/*  96 */       vr = l.a(ua, lu);
/*  97 */       if (vr == null) {
/*  98 */         return null;
/*     */       }
/*     */     } else {
/* 101 */       vr = hr;
/*     */     } 
/* 103 */     return jp.cssj.homare.css.f.a.a.a(hr, vr);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/e/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */