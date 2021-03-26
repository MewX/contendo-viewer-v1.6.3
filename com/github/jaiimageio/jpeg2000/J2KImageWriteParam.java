/*     */ package com.github.jaiimageio.jpeg2000;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import javax.imageio.ImageWriteParam;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class J2KImageWriteParam
/*     */   extends ImageWriteParam
/*     */ {
/*     */   public static final String FILTER_97 = "w9x7";
/*     */   public static final String FILTER_53 = "w5x3";
/* 156 */   private int numDecompositionLevels = 5;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 163 */   private double encodingRate = Double.MAX_VALUE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean lossless = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean componentTransformation = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 182 */   private String filter = "w5x3";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   private int[] codeBlockSize = new int[] { 64, 64 };
/*     */ 
/*     */ 
/*     */   
/* 193 */   private String progressionType = "layer";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean EPH = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean SOP = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean writeCodeStreamOnly = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public J2KImageWriteParam(Locale locale) {
/* 218 */     super(locale);
/* 219 */     setDefaults();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public J2KImageWriteParam() {
/* 228 */     setDefaults();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setDefaults() {
/* 234 */     this.canOffsetTiles = true;
/* 235 */     this.canWriteTiles = true;
/* 236 */     this.canOffsetTiles = true;
/* 237 */     this.compressionTypes = new String[] { "JPEG2000" };
/* 238 */     this.canWriteCompressed = true;
/* 239 */     this.tilingMode = 2;
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
/*     */   public void setNumDecompositionLevels(int numDecompositionLevels) {
/* 251 */     if (numDecompositionLevels < 0 || numDecompositionLevels > 32) {
/* 252 */       throw new IllegalArgumentException("numDecompositionLevels < 0 || numDecompositionLevels > 32");
/*     */     }
/*     */     
/* 255 */     this.numDecompositionLevels = numDecompositionLevels;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumDecompositionLevels() {
/* 265 */     return this.numDecompositionLevels;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncodingRate(double rate) {
/* 275 */     this.encodingRate = rate;
/* 276 */     if (this.encodingRate != Double.MAX_VALUE) {
/* 277 */       this.lossless = false;
/* 278 */       this.filter = "w9x7";
/*     */     } else {
/* 280 */       this.lossless = true;
/* 281 */       this.filter = "w5x3";
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEncodingRate() {
/* 292 */     return this.encodingRate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLossless(boolean lossless) {
/* 302 */     this.lossless = lossless;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getLossless() {
/* 312 */     return this.lossless;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilter(String value) {
/* 323 */     this.filter = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFilter() {
/* 334 */     return this.filter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setComponentTransformation(boolean value) {
/* 344 */     this.componentTransformation = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getComponentTransformation() {
/* 354 */     return this.componentTransformation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCodeBlockSize(int[] value) {
/* 364 */     this.codeBlockSize = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getCodeBlockSize() {
/* 374 */     return this.codeBlockSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSOP(boolean value) {
/* 384 */     this.SOP = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getSOP() {
/* 394 */     return this.SOP;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEPH(boolean value) {
/* 404 */     this.EPH = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getEPH() {
/* 414 */     return this.EPH;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProgressionType(String value) {
/* 425 */     this.progressionType = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getProgressionType() {
/* 436 */     return this.progressionType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWriteCodeStreamOnly(boolean value) {
/* 446 */     this.writeCodeStreamOnly = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getWriteCodeStreamOnly() {
/* 456 */     return this.writeCodeStreamOnly;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/J2KImageWriteParam.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */