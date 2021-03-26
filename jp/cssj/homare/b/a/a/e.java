/*     */ package jp.cssj.homare.b.a.a;
/*     */ 
/*     */ import jp.cssj.homare.b.a.h;
/*     */ import jp.cssj.sakae.c.a.d;
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
/*     */ public class e
/*     */   implements m
/*     */ {
/*     */   public static final short i = 0;
/*     */   public static final short j = 1;
/*     */   public static final short k = 2;
/*     */   public static final short l = 3;
/*     */   public static final short m = 4;
/*     */   public static final short n = 5;
/*     */   public static final short o = 6;
/*     */   public static final short p = 7;
/*  30 */   public static final m q = new e((short)0);
/*     */   
/*  32 */   public static final m r = new e((short)1);
/*     */   
/*  34 */   public static final m s = new e((short)2);
/*     */   
/*  36 */   public static final m t = new e((short)3);
/*     */   
/*  38 */   public static final m u = new e((short)4);
/*     */   
/*  40 */   public static final m v = new e((short)5);
/*     */   
/*  42 */   public static final m w = new e((short)6);
/*     */   
/*  44 */   public static final m x = new e((short)7);
/*     */   
/*     */   private final short a;
/*     */   
/*     */   protected e(short verticalAlign) {
/*  49 */     this.a = verticalAlign;
/*     */   }
/*     */   
/*     */   public double a(h parentBox, jp.cssj.homare.b.a.e lineBox, double ascent, double descent, double lineHeight, double baseline) {
/*     */     double v;
/*     */     d flm;
/*  55 */     switch (this.a) {
/*     */       
/*     */       case 0:
/*  58 */         v = 0.0D;
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
/* 118 */         return v;case 1: flm = parentBox.g().a(); v = flm.e() / 2.0D - (ascent + descent) / 2.0D - descent; return v;case 3: flm = parentBox.g().a(); v = descent + flm.c() - (ascent + descent) / 2.0D; return v;case 2: flm = parentBox.g().a(); v = -flm.d(); return v;case 4: v = parentBox.k() - ascent - (parentBox.i() - ascent + descent) / 2.0D; return v;case 5: v = -(parentBox.l() - descent) + (parentBox.i() - ascent + descent) / 2.0D; return v;case 6: v = lineBox.k() - ascent; return v;case 7: v = -lineBox.l() + descent; return v;
/*     */     } 
/*     */     throw new IllegalStateException();
/*     */   } public short b() {
/* 122 */     return this.a;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 126 */     switch (this.a) {
/*     */       case 0:
/* 128 */         return "baseline";
/*     */       
/*     */       case 1:
/* 131 */         return "middle";
/*     */       
/*     */       case 2:
/* 134 */         return "sub";
/*     */       
/*     */       case 3:
/* 137 */         return "super";
/*     */       
/*     */       case 4:
/* 140 */         return "text-top";
/*     */       
/*     */       case 5:
/* 143 */         return "text-bottom";
/*     */       
/*     */       case 6:
/* 146 */         return "top";
/*     */       
/*     */       case 7:
/* 149 */         return "bottom";
/*     */     } 
/*     */     
/* 152 */     throw new IllegalStateException();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/a/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */