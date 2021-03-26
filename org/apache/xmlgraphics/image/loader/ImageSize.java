/*     */ package org.apache.xmlgraphics.image.loader;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.geom.Dimension2D;
/*     */ import org.apache.xmlgraphics.java2d.Dimension2DDouble;
/*     */ import org.apache.xmlgraphics.util.UnitConv;
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
/*     */ public class ImageSize
/*     */ {
/*     */   private int widthPx;
/*     */   private int heightPx;
/*     */   private int widthMpt;
/*     */   private int heightMpt;
/*     */   private int baselinePositionFromBottomMpt;
/*     */   private double dpiHorizontal;
/*     */   private double dpiVertical;
/*     */   
/*     */   public ImageSize(int widthPx, int heightPx, double dpiHorizontal, double dpiVertical) {
/*  52 */     setSizeInPixels(widthPx, heightPx);
/*  53 */     setResolution(dpiHorizontal, dpiVertical);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageSize(int widthPx, int heightPx, double dpi) {
/*  63 */     this(widthPx, heightPx, dpi, dpi);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageSize() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSizeInPixels(int width, int height) {
/*  79 */     this.widthPx = width;
/*  80 */     this.heightPx = height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSizeInMillipoints(int width, int height) {
/*  89 */     this.widthMpt = width;
/*  90 */     this.heightMpt = height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResolution(double horizontal, double vertical) {
/*  99 */     this.dpiHorizontal = horizontal;
/* 100 */     this.dpiVertical = vertical;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResolution(double resolution) {
/* 108 */     setResolution(resolution, resolution);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBaselinePositionFromBottom(int distance) {
/* 119 */     this.baselinePositionFromBottomMpt = distance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBaselinePositionFromBottom() {
/* 130 */     return this.baselinePositionFromBottomMpt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWidthPx() {
/* 138 */     return this.widthPx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeightPx() {
/* 146 */     return this.heightPx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWidthMpt() {
/* 154 */     return this.widthMpt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeightMpt() {
/* 162 */     return this.heightMpt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDpiHorizontal() {
/* 170 */     return this.dpiHorizontal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDpiVertical() {
/* 178 */     return this.dpiVertical;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getDimensionMpt() {
/* 186 */     return new Dimension(getWidthMpt(), getHeightMpt());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension2D getDimensionPt() {
/* 194 */     return (Dimension2D)new Dimension2DDouble(getWidthMpt() / 1000.0D, getHeightMpt() / 1000.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getDimensionPx() {
/* 202 */     return new Dimension(getWidthPx(), getHeightPx());
/*     */   }
/*     */   
/*     */   private void checkResolutionAvailable() {
/* 206 */     if (this.dpiHorizontal == 0.0D || this.dpiVertical == 0.0D) {
/* 207 */       throw new IllegalStateException("The resolution must be set");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void calcSizeFromPixels() {
/* 215 */     checkResolutionAvailable();
/* 216 */     this.widthMpt = (int)Math.round(UnitConv.in2mpt(this.widthPx / this.dpiHorizontal));
/* 217 */     this.heightMpt = (int)Math.round(UnitConv.in2mpt(this.heightPx / this.dpiVertical));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void calcPixelsFromSize() {
/* 224 */     checkResolutionAvailable();
/* 225 */     this.widthPx = (int)Math.round(UnitConv.mpt2in(this.widthMpt * this.dpiHorizontal));
/* 226 */     this.heightPx = (int)Math.round(UnitConv.mpt2in(this.heightMpt * this.dpiVertical));
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 231 */     StringBuffer sb = new StringBuffer();
/* 232 */     sb.append("Size: ");
/* 233 */     sb.append(getWidthMpt()).append('x').append(getHeightMpt()).append(" mpt");
/* 234 */     sb.append(" (");
/* 235 */     sb.append(getWidthPx()).append('x').append(getHeightPx()).append(" px");
/* 236 */     sb.append(" at ").append(getDpiHorizontal()).append('x').append(getDpiVertical());
/* 237 */     sb.append(" dpi");
/* 238 */     sb.append(")");
/* 239 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/ImageSize.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */