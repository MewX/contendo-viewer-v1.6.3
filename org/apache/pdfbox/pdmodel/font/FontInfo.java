/*     */ package org.apache.pdfbox.pdmodel.font;
/*     */ 
/*     */ import org.apache.fontbox.FontBoxFont;
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
/*     */ public abstract class FontInfo
/*     */ {
/*     */   public abstract String getPostScriptName();
/*     */   
/*     */   public abstract FontFormat getFormat();
/*     */   
/*     */   public abstract CIDSystemInfo getCIDSystemInfo();
/*     */   
/*     */   public abstract FontBoxFont getFont();
/*     */   
/*     */   public abstract int getFamilyClass();
/*     */   
/*     */   public abstract int getWeightClass();
/*     */   
/*     */   final int getWeightClassAsPanose() {
/*  65 */     int usWeightClass = getWeightClass();
/*  66 */     switch (usWeightClass) {
/*     */       case -1:
/*  68 */         return 0;
/*  69 */       case 0: return 0;
/*  70 */       case 100: return 2;
/*  71 */       case 200: return 3;
/*  72 */       case 300: return 4;
/*  73 */       case 400: return 5;
/*  74 */       case 500: return 6;
/*  75 */       case 600: return 7;
/*  76 */       case 700: return 8;
/*  77 */       case 800: return 9;
/*  78 */       case 900: return 10;
/*  79 */     }  return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getCodePageRange1();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getCodePageRange2();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final long getCodePageRange() {
/*  98 */     long range1 = getCodePageRange1() & 0xFFFFFFFFL;
/*  99 */     long range2 = getCodePageRange2() & 0xFFFFFFFFL;
/* 100 */     return range2 << 32L | range1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getMacStyle();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract PDPanoseClassification getPanose();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 119 */     return getPostScriptName() + " (" + getFormat() + ", mac: 0x" + 
/* 120 */       Integer.toHexString(getMacStyle()) + ", os/2: 0x" + 
/* 121 */       Integer.toHexString(getFamilyClass()) + ", cid: " + 
/* 122 */       getCIDSystemInfo() + ")";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/FontInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */