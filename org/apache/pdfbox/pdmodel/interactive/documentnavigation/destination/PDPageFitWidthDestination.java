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
/*     */ 
/*     */ public class PDPageFitWidthDestination
/*     */   extends PDPageDestination
/*     */ {
/*     */   protected static final String TYPE = "FitH";
/*     */   protected static final String TYPE_BOUNDED = "FitBH";
/*     */   
/*     */   public PDPageFitWidthDestination() {
/*  46 */     this.array.growToSize(3);
/*  47 */     this.array.setName(1, "FitH");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPageFitWidthDestination(COSArray arr) {
/*  58 */     super(arr);
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
/*     */   public int getTop() {
/*  70 */     return this.array.getInt(2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTop(int y) {
/*  80 */     this.array.growToSize(3);
/*  81 */     if (y == -1) {
/*     */       
/*  83 */       this.array.set(2, null);
/*     */     }
/*     */     else {
/*     */       
/*  87 */       this.array.setInt(2, y);
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
/*  98 */     return "FitBH".equals(this.array.getName(1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFitBoundingBox(boolean fitBoundingBox) {
/* 108 */     this.array.growToSize(2);
/* 109 */     if (fitBoundingBox) {
/*     */       
/* 111 */       this.array.setName(1, "FitBH");
/*     */     }
/*     */     else {
/*     */       
/* 115 */       this.array.setName(1, "FitH");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/documentnavigation/destination/PDPageFitWidthDestination.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */