/*     */ package com.github.jaiimageio.impl.plugins.clib;
/*     */ 
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.spi.ImageReaderSpi;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CLibImageReader
/*     */   extends ImageReader
/*     */ {
/*  84 */   private int currIndex = -1;
/*     */ 
/*     */   
/*  87 */   private long highWaterMark = Long.MIN_VALUE;
/*     */ 
/*     */ 
/*     */   
/*  91 */   private ArrayList imageStartPosition = new ArrayList();
/*     */ 
/*     */   
/*  94 */   private int numImages = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   private int mlibImageIndex = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean subBandsMatch(int[] sourceBands, int[] destinationBands) {
/* 108 */     if (sourceBands == null && destinationBands == null)
/* 109 */       return true; 
/* 110 */     if (sourceBands != null && destinationBands != null) {
/* 111 */       if (sourceBands.length != destinationBands.length)
/*     */       {
/* 113 */         return false;
/*     */       }
/* 115 */       for (int i = 0; i < sourceBands.length; i++) {
/* 116 */         if (sourceBands[i] != destinationBands[i]) {
/* 117 */           return false;
/*     */         }
/*     */       } 
/* 120 */       return true;
/*     */     } 
/*     */     
/* 123 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static final void subsample(Raster src, int subX, int subY, WritableRaster dst) {
/* 128 */     int sx0 = src.getMinX();
/* 129 */     int sy0 = src.getMinY();
/* 130 */     int sw = src.getWidth();
/* 131 */     int syUB = sy0 + src.getHeight();
/*     */     
/* 133 */     int dx0 = dst.getMinX();
/* 134 */     int dy0 = dst.getMinY();
/* 135 */     int dw = dst.getWidth();
/*     */     
/* 137 */     int b = src.getSampleModel().getNumBands();
/* 138 */     int t = src.getSampleModel().getDataType();
/*     */     
/* 140 */     int numSubSamples = (sw + subX - 1) / subX;
/*     */     
/* 142 */     if (t == 4 || t == 5) {
/* 143 */       float[] fsamples = new float[sw];
/* 144 */       float[] fsubsamples = new float[numSubSamples];
/*     */       
/* 146 */       for (int k = 0; k < b; k++) {
/* 147 */         for (int sy = sy0, dy = dy0; sy < syUB; sy += subY, dy++) {
/* 148 */           src.getSamples(sx0, sy, sw, 1, k, fsamples); int i, s;
/* 149 */           for (i = 0, s = 0; i < sw; s++, i += subX) {
/* 150 */             fsubsamples[s] = fsamples[i];
/*     */           }
/* 152 */           dst.setSamples(dx0, dy, dw, 1, k, fsubsamples);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 156 */       int[] samples = new int[sw];
/* 157 */       int[] subsamples = new int[numSubSamples];
/*     */       
/* 159 */       for (int k = 0; k < b; k++) {
/* 160 */         for (int sy = sy0, dy = dy0; sy < syUB; sy += subY, dy++) {
/* 161 */           src.getSamples(sx0, sy, sw, 1, k, samples); int i, s;
/* 162 */           for (i = 0, s = 0; i < sw; s++, i += subX) {
/* 163 */             subsamples[s] = samples[i];
/*     */           }
/* 165 */           dst.setSamples(dx0, dy, dw, 1, k, subsamples);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected CLibImageReader(ImageReaderSpi originatingProvider) {
/* 172 */     super(originatingProvider);
/*     */   }
/*     */ 
/*     */   
/*     */   private class SoloIterator
/*     */     implements Iterator
/*     */   {
/*     */     Object theObject;
/*     */     
/*     */     SoloIterator(Object o) {
/* 182 */       if (o == null) {
/* 183 */         new IllegalArgumentException(
/* 184 */             I18N.getString("CLibImageReader0"));
/*     */       }
/* 186 */       this.theObject = o;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 190 */       return (this.theObject != null);
/*     */     }
/*     */     
/*     */     public Object next() {
/* 194 */       if (this.theObject == null) {
/* 195 */         throw new NoSuchElementException();
/*     */       }
/* 197 */       Object theNextObject = this.theObject;
/* 198 */       this.theObject = null;
/* 199 */       return theNextObject;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 203 */       throw new UnsupportedOperationException();
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
/*     */   protected int getImageIndex() {
/* 216 */     return this.mlibImageIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public IIOMetadata getStreamMetadata() throws IOException {
/* 221 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/clib/CLibImageReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */