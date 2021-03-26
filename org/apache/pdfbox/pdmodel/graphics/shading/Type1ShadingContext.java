/*     */ package org.apache.pdfbox.pdmodel.graphics.shading;
/*     */ 
/*     */ import java.awt.PaintContext;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*     */ import org.apache.pdfbox.util.Matrix;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Type1ShadingContext
/*     */   extends ShadingContext
/*     */   implements PaintContext
/*     */ {
/*  37 */   private static final Log LOG = LogFactory.getLog(Type1ShadingContext.class);
/*     */ 
/*     */ 
/*     */   
/*     */   private PDShadingType1 type1ShadingType;
/*     */ 
/*     */ 
/*     */   
/*     */   private AffineTransform rat;
/*     */ 
/*     */ 
/*     */   
/*     */   private final float[] domain;
/*     */ 
/*     */ 
/*     */   
/*     */   Type1ShadingContext(PDShadingType1 shading, ColorModel colorModel, AffineTransform xform, Matrix matrix) throws IOException {
/*  54 */     super(shading, colorModel, xform, matrix);
/*  55 */     this.type1ShadingType = shading;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  60 */     if (shading.getDomain() != null) {
/*     */       
/*  62 */       this.domain = shading.getDomain().toFloatArray();
/*     */     }
/*     */     else {
/*     */       
/*  66 */       this.domain = new float[] { 0.0F, 1.0F, 0.0F, 1.0F };
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  74 */       this.rat = shading.getMatrix().createAffineTransform().createInverse();
/*  75 */       this.rat.concatenate(matrix.createAffineTransform().createInverse());
/*  76 */       this.rat.concatenate(xform.createInverse());
/*     */     }
/*  78 */     catch (NoninvertibleTransformException ex) {
/*     */       
/*  80 */       LOG.error(ex.getMessage() + ", matrix: " + matrix, ex);
/*  81 */       this.rat = new AffineTransform();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/*  88 */     super.dispose();
/*     */     
/*  90 */     this.type1ShadingType = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorModel getColorModel() {
/*  96 */     return super.getColorModel();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Raster getRaster(int x, int y, int w, int h) {
/* 102 */     WritableRaster raster = getColorModel().createCompatibleWritableRaster(w, h);
/* 103 */     int[] data = new int[w * h * 4];
/* 104 */     for (int j = 0; j < h; j++) {
/*     */       
/* 106 */       for (int i = 0; i < w; i++) {
/*     */         
/* 108 */         int index = (j * w + i) * 4;
/* 109 */         boolean useBackground = false;
/* 110 */         float[] values = { (x + i), (y + j) };
/* 111 */         this.rat.transform(values, 0, values, 0, 1);
/* 112 */         if (values[0] < this.domain[0] || values[0] > this.domain[1] || values[1] < this.domain[2] || values[1] > this.domain[3]) {
/*     */ 
/*     */           
/* 115 */           if (getBackground() == null) {
/*     */             continue;
/*     */           }
/*     */           
/* 119 */           useBackground = true;
/*     */         } 
/*     */ 
/*     */         
/* 123 */         if (useBackground) {
/*     */           
/* 125 */           values = getBackground();
/*     */         } else {
/*     */ 
/*     */           
/*     */           try {
/*     */             
/* 131 */             values = this.type1ShadingType.evalFunction(values);
/*     */           }
/* 133 */           catch (IOException e) {
/*     */             
/* 135 */             LOG.error("error while processing a function", e);
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 140 */         PDColorSpace shadingColorSpace = getShadingColorSpace();
/* 141 */         if (shadingColorSpace != null) {
/*     */           
/*     */           try {
/*     */             
/* 145 */             values = shadingColorSpace.toRGB(values);
/*     */           }
/* 147 */           catch (IOException e) {
/*     */             
/* 149 */             LOG.error("error processing color space", e);
/*     */           } 
/*     */         }
/* 152 */         data[index] = (int)(values[0] * 255.0F);
/* 153 */         data[index + 1] = (int)(values[1] * 255.0F);
/* 154 */         data[index + 2] = (int)(values[2] * 255.0F);
/* 155 */         data[index + 3] = 255; continue;
/*     */       } 
/*     */     } 
/* 158 */     raster.setPixels(0, 0, w, h, data);
/* 159 */     return raster;
/*     */   }
/*     */ 
/*     */   
/*     */   public float[] getDomain() {
/* 164 */     return this.domain;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/Type1ShadingContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */