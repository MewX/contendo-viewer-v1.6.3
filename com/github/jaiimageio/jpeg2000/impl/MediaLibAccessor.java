/*     */ package com.github.jaiimageio.jpeg2000.impl;
/*     */ 
/*     */ import com.github.jaiimageio.impl.common.ImageUtil;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ComponentSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MediaLibAccessor
/*     */ {
/*     */   private static final int COPY_MASK_SHIFT = 7;
/*     */   private static final int COPY_MASK_SIZE = 1;
/*     */   public static final int COPY_MASK = 128;
/*     */   public static final int UNCOPIED = 0;
/*     */   public static final int COPIED = 128;
/*     */   public static final int DATATYPE_MASK = 127;
/*     */   private static final int BINARY_MASK_SHIFT = 8;
/*     */   private static final int BINARY_MASK_SIZE = 1;
/*     */   public static final int BINARY_MASK = 256;
/*     */   public static final int NONBINARY = 0;
/*     */   public static final int BINARY = 256;
/*     */   public static final int TAG_BYTE_UNCOPIED = 0;
/*     */   public static final int TAG_USHORT_UNCOPIED = 1;
/*     */   public static final int TAG_SHORT_UNCOPIED = 2;
/*     */   public static final int TAG_INT_UNCOPIED = 3;
/*     */   public static final int TAG_FLOAT_UNCOPIED = 4;
/*     */   public static final int TAG_DOUBLE_UNCOPIED = 5;
/*     */   public static final int TAG_BYTE_COPIED = 128;
/*     */   public static final int TAG_USHORT_COPIED = 129;
/*     */   public static final int TAG_SHORT_COPIED = 130;
/*     */   public static final int TAG_INT_COPIED = 131;
/*     */   public static final int TAG_FLOAT_COPIED = 132;
/*     */   public static final int TAG_DOUBLE_COPIED = 133;
/*     */   protected Raster raster;
/*     */   protected Rectangle rect;
/*     */   protected int numBands;
/*     */   protected int[] bandOffsets;
/*     */   protected int formatTag;
/*     */   private boolean areBinaryDataPacked = false;
/*     */   
/*     */   public static int findCompatibleTag(Raster src) {
/* 186 */     SampleModel dstSM = src.getSampleModel();
/* 187 */     int dstDT = dstSM.getDataType();
/*     */     
/* 189 */     int defaultDataType = dstSM.getDataType();
/*     */     
/* 191 */     boolean allComponentSampleModel = dstSM instanceof ComponentSampleModel;
/*     */     
/* 193 */     boolean allBinary = ImageUtil.isBinary(dstSM);
/*     */     
/* 195 */     if (allBinary)
/*     */     {
/*     */ 
/*     */       
/* 199 */       return 256;
/*     */     }
/*     */     
/* 202 */     if (!allComponentSampleModel && (
/* 203 */       defaultDataType == 0 || defaultDataType == 1 || defaultDataType == 2))
/*     */     {
/*     */       
/* 206 */       defaultDataType = 3;
/*     */     }
/*     */ 
/*     */     
/* 210 */     int tag = defaultDataType | 0x80;
/*     */     
/* 212 */     if (!allComponentSampleModel) {
/* 213 */       return tag;
/*     */     }
/*     */     
/* 216 */     if (isPixelSequential(dstSM))
/* 217 */       return dstDT | 0x0; 
/* 218 */     return tag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isPixelSequential(SampleModel sm) {
/* 226 */     ComponentSampleModel csm = null;
/* 227 */     if (sm instanceof ComponentSampleModel) {
/* 228 */       csm = (ComponentSampleModel)sm;
/*     */     } else {
/* 230 */       return false;
/*     */     } 
/* 232 */     int pixelStride = csm.getPixelStride();
/* 233 */     int[] bandOffsets = csm.getBandOffsets();
/* 234 */     int[] bankIndices = csm.getBankIndices();
/* 235 */     if (pixelStride != bandOffsets.length) {
/* 236 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 240 */     if (pixelStride != sm.getNumBands()) {
/* 241 */       return false;
/*     */     }
/* 243 */     for (int i = 0; i < bandOffsets.length; i++) {
/* 244 */       if (bandOffsets[i] >= pixelStride || bankIndices[i] != bankIndices[0])
/*     */       {
/* 246 */         return false;
/*     */       }
/* 248 */       for (int j = i + 1; j < bandOffsets.length; j++) {
/* 249 */         if (bandOffsets[i] == bandOffsets[j]) {
/* 250 */           return false;
/*     */         }
/*     */ 
/*     */         
/* 254 */         if (bandOffsets[i] != i)
/* 255 */           return false; 
/*     */       } 
/*     */     } 
/* 258 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBinary() {
/* 266 */     return ((this.formatTag & 0x100) == 256);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDataType() {
/* 275 */     return this.formatTag & 0x7F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDataCopy() {
/* 283 */     return ((this.formatTag & 0x80) == 128);
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getBandOffsets() {
/* 288 */     return this.bandOffsets;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getIntParameters(int band, int[] params) {
/* 297 */     int[] returnParams = new int[this.numBands];
/* 298 */     for (int i = 0; i < this.numBands; i++) {
/* 299 */       returnParams[i] = params[this.bandOffsets[i + band]];
/*     */     }
/* 301 */     return returnParams;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[][] getIntArrayParameters(int band, int[][] params) {
/* 310 */     int[][] returnParams = new int[this.numBands][];
/* 311 */     for (int i = 0; i < this.numBands; i++) {
/* 312 */       returnParams[i] = params[this.bandOffsets[i + band]];
/*     */     }
/* 314 */     return returnParams;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getDoubleParameters(int band, double[] params) {
/* 323 */     double[] returnParams = new double[this.numBands];
/* 324 */     for (int i = 0; i < this.numBands; i++) {
/* 325 */       returnParams[i] = params[this.bandOffsets[i + band]];
/*     */     }
/* 327 */     return returnParams;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] toIntArray(double[] vals) {
/* 333 */     int[] returnVals = new int[vals.length];
/* 334 */     for (int i = 0; i < vals.length; i++) {
/* 335 */       returnVals[i] = (int)vals[i];
/*     */     }
/* 337 */     return returnVals;
/*     */   }
/*     */   
/*     */   private float[] toFloatArray(double[] vals) {
/* 341 */     float[] returnVals = new float[vals.length];
/* 342 */     for (int i = 0; i < vals.length; i++) {
/* 343 */       returnVals[i] = (float)vals[i];
/*     */     }
/* 345 */     return returnVals;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/MediaLibAccessor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */