/*     */ package org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSNumber;
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
/*     */ public class PDPageXYZDestination
/*     */   extends PDPageDestination
/*     */ {
/*     */   protected static final String TYPE = "XYZ";
/*     */   
/*     */   public PDPageXYZDestination() {
/*  45 */     this.array.growToSize(5);
/*  46 */     this.array.setName(1, "XYZ");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPageXYZDestination(COSArray arr) {
/*  56 */     super(arr);
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
/*  67 */     return this.array.getInt(2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLeft(int x) {
/*  77 */     this.array.growToSize(3);
/*  78 */     if (x == -1) {
/*     */       
/*  80 */       this.array.set(2, null);
/*     */     }
/*     */     else {
/*     */       
/*  84 */       this.array.setInt(2, x);
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
/*     */   public int getTop() {
/*  96 */     return this.array.getInt(3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTop(int y) {
/* 106 */     this.array.growToSize(4);
/* 107 */     if (y == -1) {
/*     */       
/* 109 */       this.array.set(3, null);
/*     */     }
/*     */     else {
/*     */       
/* 113 */       this.array.setInt(3, y);
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
/*     */   public float getZoom() {
/* 125 */     COSBase obj = this.array.getObject(4);
/* 126 */     if (obj instanceof COSNumber)
/*     */     {
/* 128 */       return ((COSNumber)obj).floatValue();
/*     */     }
/* 130 */     return -1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setZoom(float zoom) {
/* 140 */     this.array.growToSize(5);
/* 141 */     if (zoom == -1.0F) {
/*     */       
/* 143 */       this.array.set(4, null);
/*     */     }
/*     */     else {
/*     */       
/* 147 */       this.array.set(4, (COSBase)new COSFloat(zoom));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/documentnavigation/destination/PDPageXYZDestination.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */