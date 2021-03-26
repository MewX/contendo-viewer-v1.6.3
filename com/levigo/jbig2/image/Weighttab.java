/*     */ package com.levigo.jbig2.image;
/*     */ 
/*     */ import com.levigo.jbig2.util.Utils;
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
/*     */ final class Weighttab
/*     */ {
/*     */   final int[] weights;
/*     */   final int i0;
/*     */   final int i1;
/*     */   
/*     */   public Weighttab(ParameterizedFilter paramParameterizedFilter, int paramInt1, double paramDouble, int paramInt2, int paramInt3, boolean paramBoolean) {
/*  38 */     int i = Math.max(paramParameterizedFilter.minIndex(paramDouble), paramInt2);
/*  39 */     int j = Math.min(paramParameterizedFilter.maxIndex(paramDouble), paramInt3);
/*     */ 
/*     */     
/*  42 */     double d1 = 0.0D;
/*  43 */     for (int k = i; k <= j; k++) {
/*  44 */       d1 += paramParameterizedFilter.eval(paramDouble, k);
/*     */     }
/*     */     
/*  47 */     double d2 = (d1 == 0.0D) ? paramInt1 : (paramInt1 / d1);
/*     */ 
/*     */     
/*  50 */     if (paramBoolean) {
/*  51 */       boolean bool = paramBoolean;
/*  52 */       int i2 = 0;
/*  53 */       for (int i3 = i; i3 <= j; i3++) {
/*     */         
/*  55 */         double d = Utils.clamp(d2 * paramParameterizedFilter.eval(paramDouble, i3), -32768.0D, 32767.0D);
/*     */         
/*  57 */         int i4 = (int)Math.floor(d + 0.5D);
/*  58 */         if (bool && i4 == 0) {
/*  59 */           i++;
/*     */         } else {
/*  61 */           bool = false;
/*  62 */           if (i4 != 0) {
/*  63 */             i2 = i3;
/*     */           }
/*     */         } 
/*     */       } 
/*  67 */       j = Math.max(i2, i);
/*     */     } 
/*     */ 
/*     */     
/*  71 */     this.weights = new int[j - i + 1];
/*     */ 
/*     */     
/*  74 */     int m = 0; int n, i1;
/*  75 */     for (n = 0, i1 = i; i1 <= j; i1++) {
/*     */       
/*  77 */       double d = Utils.clamp(d2 * paramParameterizedFilter.eval(paramDouble, i1), -32768.0D, 32767.0D);
/*     */       
/*  79 */       int i2 = (int)Math.floor(d + 0.5D);
/*  80 */       this.weights[n++] = i2;
/*  81 */       m += i2;
/*     */     } 
/*     */     
/*  84 */     if (m == 0) {
/*  85 */       j = i;
/*  86 */       this.weights[0] = paramInt1;
/*  87 */     } else if (m != paramInt1) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  92 */       n = (int)(paramDouble + 0.5D);
/*  93 */       if (n >= j)
/*  94 */         n = j - 1; 
/*  95 */       if (n < i)
/*  96 */         n = i; 
/*  97 */       i1 = paramInt1 - m;
/*     */ 
/*     */       
/* 100 */       this.weights[n - i] = this.weights[n - i] + i1;
/*     */     } 
/*     */     
/* 103 */     this.i0 = i - paramInt2;
/* 104 */     this.i1 = j - paramInt2;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/image/Weighttab.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */