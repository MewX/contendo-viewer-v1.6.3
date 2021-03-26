/*     */ package org.apache.pdfbox.pdmodel.font;
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
/*     */ public class PDPanoseClassification
/*     */ {
/*     */   private final byte[] bytes;
/*     */   
/*     */   PDPanoseClassification(byte[] bytes) {
/*  31 */     this.bytes = bytes;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFamilyKind() {
/*  36 */     return this.bytes[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSerifStyle() {
/*  41 */     return this.bytes[1];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWeight() {
/*  46 */     return this.bytes[2];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getProportion() {
/*  51 */     return this.bytes[3];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getContrast() {
/*  56 */     return this.bytes[4];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStrokeVariation() {
/*  61 */     return this.bytes[5];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getArmStyle() {
/*  66 */     return this.bytes[6];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLetterform() {
/*  71 */     return this.bytes[7];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMidline() {
/*  76 */     return this.bytes[8];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getXHeight() {
/*  81 */     return this.bytes[9];
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getBytes() {
/*  86 */     return this.bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  92 */     return "{ FamilyKind = " + getFamilyKind() + ", SerifStyle = " + 
/*  93 */       getSerifStyle() + ", Weight = " + 
/*  94 */       getWeight() + ", Proportion = " + 
/*  95 */       getProportion() + ", Contrast = " + 
/*  96 */       getContrast() + ", StrokeVariation = " + 
/*  97 */       getStrokeVariation() + ", ArmStyle = " + 
/*  98 */       getArmStyle() + ", Letterform = " + 
/*  99 */       getLetterform() + ", Midline = " + 
/* 100 */       getMidline() + ", XHeight = " + 
/* 101 */       getXHeight() + "}";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/PDPanoseClassification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */