/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.SinglePixelPackedSampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ColorMatrixRed
/*     */   extends AbstractRed
/*     */ {
/*     */   private float[][] matrix;
/*     */   
/*     */   public float[][] getMatrix() {
/*  42 */     return copyMatrix(this.matrix);
/*     */   }
/*     */   
/*     */   public void setMatrix(float[][] matrix) {
/*  46 */     float[][] tmp = copyMatrix(matrix);
/*     */     
/*  48 */     if (tmp == null) {
/*  49 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  52 */     if (tmp.length != 4) {
/*  53 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  56 */     for (int i = 0; i < 4; i++) {
/*  57 */       if ((tmp[i]).length != 5) {
/*  58 */         throw new IllegalArgumentException(String.valueOf(i) + " : " + (tmp[i]).length);
/*     */       }
/*     */     } 
/*  61 */     this.matrix = matrix;
/*     */   }
/*     */   
/*     */   private float[][] copyMatrix(float[][] m) {
/*  65 */     if (m == null) {
/*  66 */       return (float[][])null;
/*     */     }
/*     */     
/*  69 */     float[][] cm = new float[m.length][];
/*  70 */     for (int i = 0; i < m.length; i++) {
/*  71 */       if (m[i] != null) {
/*  72 */         cm[i] = new float[(m[i]).length];
/*  73 */         System.arraycopy(m[i], 0, cm[i], 0, (m[i]).length);
/*     */       } 
/*     */     } 
/*     */     
/*  77 */     return cm;
/*     */   }
/*     */   public ColorMatrixRed(CachableRed src, float[][] matrix) {
/*     */     ColorModel cm;
/*  81 */     setMatrix(matrix);
/*     */     
/*  83 */     ColorModel srcCM = src.getColorModel();
/*  84 */     ColorSpace srcCS = null;
/*  85 */     if (srcCM != null) {
/*  86 */       srcCS = srcCM.getColorSpace();
/*     */     }
/*  88 */     if (srcCS == null) {
/*  89 */       cm = GraphicsUtil.Linear_sRGB_Unpre;
/*     */     }
/*  91 */     else if (srcCS == ColorSpace.getInstance(1004)) {
/*  92 */       cm = GraphicsUtil.Linear_sRGB_Unpre;
/*     */     } else {
/*  94 */       cm = GraphicsUtil.sRGB_Unpre;
/*     */     } 
/*     */     
/*  97 */     SampleModel sm = cm.createCompatibleSampleModel(src.getWidth(), src.getHeight());
/*     */ 
/*     */ 
/*     */     
/* 101 */     init(src, src.getBounds(), cm, sm, src.getTileGridXOffset(), src.getTileGridYOffset(), (Map)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/* 112 */     CachableRed src = getSources().get(0);
/*     */ 
/*     */ 
/*     */     
/* 116 */     wr = src.copyData(wr);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     ColorModel cm = src.getColorModel();
/* 122 */     GraphicsUtil.coerceData(wr, cm, false);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     int minX = wr.getMinX();
/* 128 */     int minY = wr.getMinY();
/* 129 */     int w = wr.getWidth();
/* 130 */     int h = wr.getHeight();
/* 131 */     DataBufferInt dbf = (DataBufferInt)wr.getDataBuffer();
/* 132 */     int[] pixels = dbf.getBankData()[0];
/*     */ 
/*     */     
/* 135 */     SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)wr.getSampleModel();
/*     */     
/* 137 */     int offset = dbf.getOffset() + sppsm.getOffset(minX - wr.getSampleModelTranslateX(), minY - wr.getSampleModelTranslateY());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 144 */     int scanStride = ((SinglePixelPackedSampleModel)wr.getSampleModel()).getScanlineStride();
/*     */ 
/*     */     
/* 147 */     int adjust = scanStride - w;
/* 148 */     int p = offset;
/* 149 */     int i = 0, j = 0;
/*     */     
/* 151 */     float a00 = this.matrix[0][0] / 255.0F, a01 = this.matrix[0][1] / 255.0F, a02 = this.matrix[0][2] / 255.0F, a03 = this.matrix[0][3] / 255.0F, a04 = this.matrix[0][4] / 255.0F;
/* 152 */     float a10 = this.matrix[1][0] / 255.0F, a11 = this.matrix[1][1] / 255.0F, a12 = this.matrix[1][2] / 255.0F, a13 = this.matrix[1][3] / 255.0F, a14 = this.matrix[1][4] / 255.0F;
/* 153 */     float a20 = this.matrix[2][0] / 255.0F, a21 = this.matrix[2][1] / 255.0F, a22 = this.matrix[2][2] / 255.0F, a23 = this.matrix[2][3] / 255.0F, a24 = this.matrix[2][4] / 255.0F;
/* 154 */     float a30 = this.matrix[3][0] / 255.0F, a31 = this.matrix[3][1] / 255.0F, a32 = this.matrix[3][2] / 255.0F, a33 = this.matrix[3][3] / 255.0F, a34 = this.matrix[3][4] / 255.0F;
/*     */     
/* 156 */     for (i = 0; i < h; i++) {
/* 157 */       for (j = 0; j < w; j++) {
/* 158 */         int pel = pixels[p];
/*     */         
/* 160 */         int a = pel >>> 24;
/* 161 */         int r = pel >> 16 & 0xFF;
/* 162 */         int g = pel >> 8 & 0xFF;
/* 163 */         int b = pel & 0xFF;
/*     */         
/* 165 */         int dr = (int)((a00 * r + a01 * g + a02 * b + a03 * a + a04) * 255.0F);
/* 166 */         int dg = (int)((a10 * r + a11 * g + a12 * b + a13 * a + a14) * 255.0F);
/* 167 */         int db = (int)((a20 * r + a21 * g + a22 * b + a23 * a + a24) * 255.0F);
/* 168 */         int da = (int)((a30 * r + a31 * g + a32 * b + a33 * a + a34) * 255.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 179 */         if ((dr & 0xFFFFFF00) != 0)
/* 180 */           dr = ((dr & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/* 181 */         if ((dg & 0xFFFFFF00) != 0)
/* 182 */           dg = ((dg & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/* 183 */         if ((db & 0xFFFFFF00) != 0)
/* 184 */           db = ((db & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/* 185 */         if ((da & 0xFFFFFF00) != 0) {
/* 186 */           da = ((da & Integer.MIN_VALUE) != 0) ? 0 : 255;
/*     */         }
/* 188 */         pixels[p++] = da << 24 | dr << 16 | dg << 8 | db;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 197 */       p += adjust;
/*     */     } 
/*     */ 
/*     */     
/* 201 */     return wr;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/ColorMatrixRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */