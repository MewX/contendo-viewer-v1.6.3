/*     */ package org.apache.batik.ext.awt.image.renderable;
/*     */ 
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.rendered.ColorMatrixRed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ColorMatrixRable8Bit
/*     */   extends AbstractColorInterpolationRable
/*     */   implements ColorMatrixRable
/*     */ {
/*  39 */   private static float[][] MATRIX_LUMINANCE_TO_ALPHA = new float[][] { { 0.0F, 0.0F, 0.0F, 0.0F, 0.0F }, { 0.0F, 0.0F, 0.0F, 0.0F, 0.0F }, { 0.0F, 0.0F, 0.0F, 0.0F, 0.0F }, { 0.2125F, 0.7154F, 0.0721F, 0.0F, 0.0F } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int type;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float[][] matrix;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSource(Filter src) {
/*  61 */     init(src, (Map)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getSource() {
/*  68 */     return getSources().get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/*  77 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[][] getMatrix() {
/*  85 */     return this.matrix;
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
/*     */   public static ColorMatrixRable buildMatrix(float[][] matrix) {
/*  99 */     if (matrix == null) {
/* 100 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 103 */     if (matrix.length != 4) {
/* 104 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 107 */     float[][] newMatrix = new float[4][];
/*     */     
/* 109 */     for (int i = 0; i < 4; i++) {
/* 110 */       float[] m = matrix[i];
/* 111 */       if (m == null) {
/* 112 */         throw new IllegalArgumentException();
/*     */       }
/* 114 */       if (m.length != 5) {
/* 115 */         throw new IllegalArgumentException();
/*     */       }
/* 117 */       newMatrix[i] = new float[5];
/* 118 */       for (int j = 0; j < 5; j++) {
/* 119 */         newMatrix[i][j] = m[j];
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     ColorMatrixRable8Bit filter = new ColorMatrixRable8Bit();
/*     */     
/* 131 */     filter.type = 0;
/* 132 */     filter.matrix = newMatrix;
/* 133 */     return filter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ColorMatrixRable buildSaturate(float s) {
/* 140 */     ColorMatrixRable8Bit filter = new ColorMatrixRable8Bit();
/*     */     
/* 142 */     filter.type = 1;
/* 143 */     filter.matrix = new float[][] { { 0.213F + 0.787F * s, 0.715F - 0.715F * s, 0.072F - 0.072F * s, 0.0F, 0.0F }, { 0.213F - 0.213F * s, 0.715F + 0.285F * s, 0.072F - 0.072F * s, 0.0F, 0.0F }, { 0.213F - 0.213F * s, 0.715F - 0.715F * s, 0.072F + 0.928F * s, 0.0F, 0.0F }, { 0.0F, 0.0F, 0.0F, 1.0F, 0.0F } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 149 */     return filter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ColorMatrixRable buildHueRotate(float a) {
/* 157 */     ColorMatrixRable8Bit filter = new ColorMatrixRable8Bit();
/*     */     
/* 159 */     filter.type = 2;
/*     */     
/* 161 */     float cos = (float)Math.cos(a);
/* 162 */     float sin = (float)Math.sin(a);
/*     */ 
/*     */ 
/*     */     
/* 166 */     float a00 = 0.213F + cos * 0.787F - sin * 0.213F;
/* 167 */     float a10 = 0.213F - cos * 0.212F + sin * 0.143F;
/* 168 */     float a20 = 0.213F - cos * 0.213F - sin * 0.787F;
/*     */     
/* 170 */     float a01 = 0.715F - cos * 0.715F - sin * 0.715F;
/* 171 */     float a11 = 0.715F + cos * 0.285F + sin * 0.14F;
/* 172 */     float a21 = 0.715F - cos * 0.715F + sin * 0.715F;
/*     */     
/* 174 */     float a02 = 0.072F - cos * 0.072F + sin * 0.928F;
/* 175 */     float a12 = 0.072F - cos * 0.072F - sin * 0.283F;
/* 176 */     float a22 = 0.072F + cos * 0.928F + sin * 0.072F;
/*     */     
/* 178 */     filter.matrix = new float[][] { { a00, a01, a02, 0.0F, 0.0F }, { a10, a11, a12, 0.0F, 0.0F }, { a20, a21, a22, 0.0F, 0.0F }, { 0.0F, 0.0F, 0.0F, 1.0F, 0.0F } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 190 */     return filter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ColorMatrixRable buildLuminanceToAlpha() {
/* 197 */     ColorMatrixRable8Bit filter = new ColorMatrixRable8Bit();
/*     */     
/* 199 */     filter.type = 3;
/* 200 */     filter.matrix = MATRIX_LUMINANCE_TO_ALPHA;
/* 201 */     return filter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderedImage createRendering(RenderContext rc) {
/* 208 */     RenderedImage srcRI = getSource().createRendering(rc);
/*     */     
/* 210 */     if (srcRI == null) {
/* 211 */       return null;
/*     */     }
/* 213 */     return (RenderedImage)new ColorMatrixRed(convertSourceCS(srcRI), this.matrix);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/ColorMatrixRable8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */