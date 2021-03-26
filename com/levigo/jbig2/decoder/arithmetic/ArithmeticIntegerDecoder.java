/*     */ package com.levigo.jbig2.decoder.arithmetic;
/*     */ 
/*     */ import java.io.IOException;
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
/*     */ public class ArithmeticIntegerDecoder
/*     */ {
/*     */   private final ArithmeticDecoder decoder;
/*     */   private int prev;
/*     */   
/*     */   public ArithmeticIntegerDecoder(ArithmeticDecoder paramArithmeticDecoder) {
/*  32 */     this.decoder = paramArithmeticDecoder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long decode(CX paramCX) throws IOException {
/*     */     byte b1, b2;
/*  42 */     int i = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  48 */     if (paramCX == null) {
/*  49 */       paramCX = new CX(512, 1);
/*     */     }
/*     */     
/*  52 */     this.prev = 1;
/*     */     
/*  54 */     paramCX.setIndex(this.prev);
/*  55 */     int k = this.decoder.decode(paramCX);
/*  56 */     setPrev(k);
/*     */     
/*  58 */     paramCX.setIndex(this.prev);
/*  59 */     int j = this.decoder.decode(paramCX);
/*  60 */     setPrev(j);
/*     */     
/*  62 */     if (j == 1) {
/*  63 */       paramCX.setIndex(this.prev);
/*  64 */       j = this.decoder.decode(paramCX);
/*  65 */       setPrev(j);
/*     */       
/*  67 */       if (j == 1) {
/*  68 */         paramCX.setIndex(this.prev);
/*  69 */         j = this.decoder.decode(paramCX);
/*  70 */         setPrev(j);
/*     */         
/*  72 */         if (j == 1) {
/*  73 */           paramCX.setIndex(this.prev);
/*  74 */           j = this.decoder.decode(paramCX);
/*  75 */           setPrev(j);
/*     */           
/*  77 */           if (j == 1) {
/*  78 */             paramCX.setIndex(this.prev);
/*  79 */             j = this.decoder.decode(paramCX);
/*  80 */             setPrev(j);
/*     */             
/*  82 */             if (j == 1) {
/*  83 */               b1 = 32;
/*  84 */               b2 = 4436;
/*     */             } else {
/*  86 */               b1 = 12;
/*  87 */               b2 = 340;
/*     */             } 
/*     */           } else {
/*  90 */             b1 = 8;
/*  91 */             b2 = 84;
/*     */           } 
/*     */         } else {
/*  94 */           b1 = 6;
/*  95 */           b2 = 20;
/*     */         } 
/*     */       } else {
/*  98 */         b1 = 4;
/*  99 */         b2 = 4;
/*     */       } 
/*     */     } else {
/* 102 */       b1 = 2;
/* 103 */       b2 = 0;
/*     */     } 
/*     */     
/* 106 */     for (byte b3 = 0; b3 < b1; b3++) {
/* 107 */       paramCX.setIndex(this.prev);
/* 108 */       j = this.decoder.decode(paramCX);
/* 109 */       setPrev(j);
/* 110 */       i = i << 1 | j;
/*     */     } 
/*     */     
/* 113 */     i += b2;
/*     */     
/* 115 */     if (k == 0)
/* 116 */       return i; 
/* 117 */     if (k == 1 && i > 0) {
/* 118 */       return -i;
/*     */     }
/*     */     
/* 121 */     return Long.MAX_VALUE;
/*     */   }
/*     */   
/*     */   private void setPrev(int paramInt) {
/* 125 */     if (this.prev < 256) {
/* 126 */       this.prev = (this.prev << 1 | paramInt) & 0x1FF;
/*     */     } else {
/* 128 */       this.prev = ((this.prev << 1 | paramInt) & 0x1FF | 0x100) & 0x1FF;
/*     */     } 
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
/*     */ 
/*     */   
/*     */   public int decodeIAID(CX paramCX, long paramLong) throws IOException {
/* 144 */     this.prev = 1;
/*     */ 
/*     */     
/* 147 */     for (byte b = 0; b < paramLong; b++) {
/* 148 */       paramCX.setIndex(this.prev);
/* 149 */       this.prev = this.prev << 1 | this.decoder.decode(paramCX);
/*     */     } 
/*     */ 
/*     */     
/* 153 */     return this.prev - (1 << (int)paramLong);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/decoder/arithmetic/ArithmeticIntegerDecoder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */