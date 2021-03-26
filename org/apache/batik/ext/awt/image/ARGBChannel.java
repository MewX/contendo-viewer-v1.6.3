/*     */ package org.apache.batik.ext.awt.image;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public final class ARGBChannel
/*     */   implements Serializable
/*     */ {
/*     */   public static final int CHANNEL_A = 3;
/*     */   public static final int CHANNEL_R = 2;
/*     */   public static final int CHANNEL_G = 1;
/*     */   public static final int CHANNEL_B = 0;
/*     */   public static final String RED = "Red";
/*     */   public static final String GREEN = "Green";
/*     */   public static final String BLUE = "Blue";
/*     */   public static final String ALPHA = "Alpha";
/*  51 */   public static final ARGBChannel R = new ARGBChannel(2, "Red");
/*     */   
/*  53 */   public static final ARGBChannel G = new ARGBChannel(1, "Green");
/*     */   
/*  55 */   public static final ARGBChannel B = new ARGBChannel(0, "Blue");
/*     */   
/*  57 */   public static final ARGBChannel A = new ARGBChannel(3, "Alpha");
/*     */ 
/*     */ 
/*     */   
/*     */   private String desc;
/*     */ 
/*     */   
/*     */   private int val;
/*     */ 
/*     */ 
/*     */   
/*     */   private ARGBChannel(int val, String desc) {
/*  69 */     this.desc = desc;
/*  70 */     this.val = val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  77 */     return this.desc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int toInt() {
/*  85 */     return this.val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object readResolve() {
/*  95 */     switch (this.val) {
/*     */       case 2:
/*  97 */         return R;
/*     */       case 1:
/*  99 */         return G;
/*     */       case 0:
/* 101 */         return B;
/*     */       case 3:
/* 103 */         return A;
/*     */     } 
/* 105 */     throw new RuntimeException("Unknown ARGBChannel value");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/ARGBChannel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */