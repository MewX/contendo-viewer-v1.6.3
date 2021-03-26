/*     */ package org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
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
/*     */ public class PDPageFitHeightDestination
/*     */   extends PDPageDestination
/*     */ {
/*     */   protected static final String TYPE = "FitV";
/*     */   protected static final String TYPE_BOUNDED = "FitBV";
/*     */   
/*     */   public PDPageFitHeightDestination() {
/*  45 */     this.array.growToSize(3);
/*  46 */     this.array.setName(1, "FitV");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPageFitHeightDestination(COSArray arr) {
/*  57 */     super(arr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLeft() {
/*  68 */     return this.array.getInt(2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLeft(int x) {
/*  78 */     this.array.growToSize(3);
/*  79 */     if (x == -1) {
/*     */       
/*  81 */       this.array.set(2, null);
/*     */     }
/*     */     else {
/*     */       
/*  85 */       this.array.setInt(2, x);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean fitBoundingBox() {
/*  96 */     return "FitBV".equals(this.array.getName(1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFitBoundingBox(boolean fitBoundingBox) {
/* 106 */     this.array.growToSize(2);
/* 107 */     if (fitBoundingBox) {
/*     */       
/* 109 */       this.array.setName(1, "FitBV");
/*     */     }
/*     */     else {
/*     */       
/* 113 */       this.array.setName(1, "FitV");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/documentnavigation/destination/PDPageFitHeightDestination.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */