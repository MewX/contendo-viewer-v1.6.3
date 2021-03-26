/*     */ package org.apache.pdfbox.pdmodel.graphics.shading;
/*     */ 
/*     */ import java.awt.PaintContext;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBoolean;
/*     */ import org.apache.pdfbox.pdmodel.common.function.PDFunction;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AxialShadingContext
/*     */   extends ShadingContext
/*     */   implements PaintContext
/*     */ {
/*  43 */   private static final Log LOG = LogFactory.getLog(AxialShadingContext.class);
/*     */ 
/*     */   
/*     */   private PDShadingType2 axialShadingType;
/*     */ 
/*     */   
/*     */   private final float[] coords;
/*     */ 
/*     */   
/*     */   private final float[] domain;
/*     */ 
/*     */   
/*     */   private final boolean[] extend;
/*     */   
/*     */   private final double x1x0;
/*     */   
/*     */   private final double y1y0;
/*     */   
/*     */   private final float d1d0;
/*     */   
/*     */   private final double denom;
/*     */   
/*     */   private final int factor;
/*     */   
/*     */   private final int[] colorTable;
/*     */   
/*     */   private AffineTransform rat;
/*     */ 
/*     */   
/*     */   public AxialShadingContext(PDShadingType2 shading, ColorModel colorModel, AffineTransform xform, Matrix matrix, Rectangle deviceBounds) throws IOException {
/*  73 */     super(shading, colorModel, xform, matrix);
/*  74 */     this.axialShadingType = shading;
/*  75 */     this.coords = shading.getCoords().toFloatArray();
/*     */ 
/*     */     
/*  78 */     if (shading.getDomain() != null) {
/*     */       
/*  80 */       this.domain = shading.getDomain().toFloatArray();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  85 */       this.domain = new float[] { 0.0F, 1.0F };
/*     */     } 
/*     */     
/*  88 */     COSArray extendValues = shading.getExtend();
/*  89 */     if (extendValues != null) {
/*     */       
/*  91 */       this.extend = new boolean[2];
/*  92 */       this.extend[0] = ((COSBoolean)extendValues.getObject(0)).getValue();
/*  93 */       this.extend[1] = ((COSBoolean)extendValues.getObject(1)).getValue();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  98 */       this.extend = new boolean[] { false, false };
/*     */     } 
/*     */     
/* 101 */     this.x1x0 = (this.coords[2] - this.coords[0]);
/* 102 */     this.y1y0 = (this.coords[3] - this.coords[1]);
/* 103 */     this.d1d0 = this.domain[1] - this.domain[0];
/* 104 */     this.denom = Math.pow(this.x1x0, 2.0D) + Math.pow(this.y1y0, 2.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 110 */       this.rat = matrix.createAffineTransform().createInverse();
/* 111 */       this.rat.concatenate(xform.createInverse());
/*     */     }
/* 113 */     catch (NoninvertibleTransformException ex) {
/*     */       
/* 115 */       LOG.error(ex.getMessage() + ", matrix: " + matrix, ex);
/* 116 */       this.rat = new AffineTransform();
/*     */     } 
/*     */ 
/*     */     
/* 120 */     AffineTransform shadingToDevice = (AffineTransform)xform.clone();
/* 121 */     shadingToDevice.concatenate(matrix.createAffineTransform());
/*     */ 
/*     */     
/* 124 */     double dist = Math.sqrt(Math.pow(deviceBounds.getMaxX() - deviceBounds.getMinX(), 2.0D) + 
/* 125 */         Math.pow(deviceBounds.getMaxY() - deviceBounds.getMinY(), 2.0D));
/* 126 */     this.factor = (int)Math.ceil(dist);
/*     */ 
/*     */     
/* 129 */     this.colorTable = calcColorTable();
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
/*     */   private int[] calcColorTable() throws IOException {
/* 141 */     int[] map = new int[this.factor + 1];
/* 142 */     if (this.factor == 0 || this.d1d0 == 0.0F) {
/*     */       
/* 144 */       float[] values = this.axialShadingType.evalFunction(this.domain[0]);
/* 145 */       map[0] = convertToRGB(values);
/*     */     }
/*     */     else {
/*     */       
/* 149 */       for (int i = 0; i <= this.factor; i++) {
/*     */         
/* 151 */         float t = this.domain[0] + this.d1d0 * i / this.factor;
/* 152 */         float[] values = this.axialShadingType.evalFunction(t);
/* 153 */         map[i] = convertToRGB(values);
/*     */       } 
/*     */     } 
/* 156 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 162 */     super.dispose();
/* 163 */     this.axialShadingType = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorModel getColorModel() {
/* 169 */     return super.getColorModel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Raster getRaster(int x, int y, int w, int h) {
/* 176 */     WritableRaster raster = getColorModel().createCompatibleWritableRaster(w, h);
/*     */     
/* 178 */     int[] data = new int[w * h * 4];
/* 179 */     for (int j = 0; j < h; j++) {
/*     */       
/* 181 */       for (int i = 0; i < w; i++) {
/*     */         int value;
/* 183 */         boolean useBackground = false;
/* 184 */         float[] values = { (x + i), (y + j) };
/* 185 */         this.rat.transform(values, 0, values, 0, 1);
/* 186 */         double inputValue = this.x1x0 * (values[0] - this.coords[0]) + this.y1y0 * (values[1] - this.coords[1]);
/*     */         
/* 188 */         if (this.denom == 0.0D) {
/*     */           
/* 190 */           if (getBackground() == null) {
/*     */             continue;
/*     */           }
/*     */           
/* 194 */           useBackground = true;
/*     */         }
/*     */         else {
/*     */           
/* 198 */           inputValue /= this.denom;
/*     */         } 
/*     */         
/* 201 */         if (inputValue < 0.0D) {
/*     */ 
/*     */           
/* 204 */           if (this.extend[0])
/*     */           {
/* 206 */             inputValue = 0.0D;
/*     */           }
/*     */           else
/*     */           {
/* 210 */             if (getBackground() == null) {
/*     */               continue;
/*     */             }
/*     */             
/* 214 */             useBackground = true;
/*     */           }
/*     */         
/*     */         }
/* 218 */         else if (inputValue > 1.0D) {
/*     */ 
/*     */           
/* 221 */           if (this.extend[1]) {
/*     */             
/* 223 */             inputValue = 1.0D;
/*     */           }
/*     */           else {
/*     */             
/* 227 */             if (getBackground() == null) {
/*     */               continue;
/*     */             }
/*     */             
/* 231 */             useBackground = true;
/*     */           } 
/*     */         } 
/*     */         
/* 235 */         if (useBackground) {
/*     */ 
/*     */           
/* 238 */           value = getRgbBackground();
/*     */         }
/*     */         else {
/*     */           
/* 242 */           int key = (int)(inputValue * this.factor);
/* 243 */           value = this.colorTable[key];
/*     */         } 
/* 245 */         int index = (j * w + i) * 4;
/* 246 */         data[index] = value & 0xFF;
/* 247 */         value >>= 8;
/* 248 */         data[index + 1] = value & 0xFF;
/* 249 */         value >>= 8;
/* 250 */         data[index + 2] = value & 0xFF;
/* 251 */         data[index + 3] = 255; continue;
/*     */       } 
/*     */     } 
/* 254 */     raster.setPixels(0, 0, w, h, data);
/* 255 */     return raster;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getCoords() {
/* 263 */     return this.coords;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getDomain() {
/* 271 */     return this.domain;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean[] getExtend() {
/* 279 */     return this.extend;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFunction getFunction() throws IOException {
/* 289 */     return this.axialShadingType.getFunction();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/AxialShadingContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */