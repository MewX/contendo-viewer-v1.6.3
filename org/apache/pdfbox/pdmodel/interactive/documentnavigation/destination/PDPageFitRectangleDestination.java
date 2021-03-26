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
/*     */ public class PDPageFitRectangleDestination
/*     */   extends PDPageDestination
/*     */ {
/*     */   protected static final String TYPE = "FitR";
/*     */   
/*     */   public PDPageFitRectangleDestination() {
/*  41 */     this.array.growToSize(6);
/*  42 */     this.array.setName(1, "FitR");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPageFitRectangleDestination(COSArray arr) {
/*  53 */     super(arr);
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
/*  64 */     return this.array.getInt(2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLeft(int x) {
/*  74 */     this.array.growToSize(3);
/*  75 */     if (x == -1) {
/*     */       
/*  77 */       this.array.set(2, null);
/*     */     }
/*     */     else {
/*     */       
/*  81 */       this.array.setInt(2, x);
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
/*     */   public int getBottom() {
/*  93 */     return this.array.getInt(3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBottom(int y) {
/* 103 */     this.array.growToSize(6);
/* 104 */     if (y == -1) {
/*     */       
/* 106 */       this.array.set(3, null);
/*     */     }
/*     */     else {
/*     */       
/* 110 */       this.array.setInt(3, y);
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
/*     */   public int getRight() {
/* 122 */     return this.array.getInt(4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRight(int x) {
/* 132 */     this.array.growToSize(6);
/* 133 */     if (x == -1) {
/*     */       
/* 135 */       this.array.set(4, null);
/*     */     }
/*     */     else {
/*     */       
/* 139 */       this.array.setInt(4, x);
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
/*     */   public int getTop() {
/* 152 */     return this.array.getInt(5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTop(int y) {
/* 162 */     this.array.growToSize(6);
/* 163 */     if (y == -1) {
/*     */       
/* 165 */       this.array.set(5, null);
/*     */     }
/*     */     else {
/*     */       
/* 169 */       this.array.setInt(5, y);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/documentnavigation/destination/PDPageFitRectangleDestination.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */