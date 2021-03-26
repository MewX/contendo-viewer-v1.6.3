/*     */ package org.apache.pdfbox.filter;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DecodeOptions
/*     */ {
/*  32 */   public static final DecodeOptions DEFAULT = new FinalDecodeOptions(true);
/*     */   
/*  34 */   private Rectangle sourceRegion = null;
/*  35 */   private int subsamplingX = 1; private int subsamplingY = 1; private int subsamplingOffsetX = 0; private int subsamplingOffsetY = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean filterSubsampled = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DecodeOptions() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DecodeOptions(Rectangle sourceRegion) {
/*  54 */     this.sourceRegion = sourceRegion;
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
/*     */   public DecodeOptions(int x, int y, int width, int height) {
/*  68 */     this(new Rectangle(x, y, width, height));
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
/*     */   public DecodeOptions(int subsampling) {
/*  80 */     this.subsamplingX = subsampling;
/*  81 */     this.subsamplingY = subsampling;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle getSourceRegion() {
/*  92 */     return this.sourceRegion;
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
/*     */   public void setSourceRegion(Rectangle sourceRegion) {
/* 105 */     this.sourceRegion = sourceRegion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSubsamplingX() {
/* 116 */     return this.subsamplingX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubsamplingX(int ssX) {
/* 126 */     this.subsamplingX = ssX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSubsamplingY() {
/* 136 */     return this.subsamplingY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubsamplingY(int ssY) {
/* 146 */     this.subsamplingY = ssY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSubsamplingOffsetX() {
/* 156 */     return this.subsamplingOffsetX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubsamplingOffsetX(int ssOffsetX) {
/* 166 */     this.subsamplingOffsetX = ssOffsetX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSubsamplingOffsetY() {
/* 176 */     return this.subsamplingOffsetY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubsamplingOffsetY(int ssOffsetY) {
/* 186 */     this.subsamplingOffsetY = ssOffsetY;
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
/*     */   public boolean isFilterSubsampled() {
/* 199 */     return this.filterSubsampled;
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
/*     */   void setFilterSubsampled(boolean filterSubsampled) {
/* 212 */     this.filterSubsampled = filterSubsampled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class FinalDecodeOptions
/*     */     extends DecodeOptions
/*     */   {
/*     */     FinalDecodeOptions(boolean filterSubsampled) {
/* 222 */       super.setFilterSubsampled(filterSubsampled);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void setSourceRegion(Rectangle sourceRegion) {
/* 228 */       throw new UnsupportedOperationException("This instance may not be modified.");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void setSubsamplingX(int ssX) {
/* 234 */       throw new UnsupportedOperationException("This instance may not be modified.");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void setSubsamplingY(int ssY) {
/* 240 */       throw new UnsupportedOperationException("This instance may not be modified.");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void setSubsamplingOffsetX(int ssOffsetX) {
/* 246 */       throw new UnsupportedOperationException("This instance may not be modified.");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void setSubsamplingOffsetY(int ssOffsetY) {
/* 252 */       throw new UnsupportedOperationException("This instance may not be modified.");
/*     */     }
/*     */     
/*     */     void setFilterSubsampled(boolean filterSubsampled) {}
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/filter/DecodeOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */