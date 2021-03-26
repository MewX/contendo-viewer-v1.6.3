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
/*     */ public class RadialShadingContext
/*     */   extends ShadingContext
/*     */   implements PaintContext
/*     */ {
/*  43 */   private static final Log LOG = LogFactory.getLog(RadialShadingContext.class);
/*     */ 
/*     */   
/*     */   private PDShadingType3 radialShadingType;
/*     */ 
/*     */   
/*     */   private final float[] coords;
/*     */ 
/*     */   
/*     */   private final float[] domain;
/*     */   
/*     */   private final boolean[] extend;
/*     */   
/*     */   private final double x1x0;
/*     */   
/*     */   private final double y1y0;
/*     */   
/*     */   private final double r1r0;
/*     */   
/*     */   private final double r0pow2;
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
/*     */   public RadialShadingContext(PDShadingType3 shading, ColorModel colorModel, AffineTransform xform, Matrix matrix, Rectangle deviceBounds) throws IOException {
/*  76 */     super(shading, colorModel, xform, matrix);
/*  77 */     this.radialShadingType = shading;
/*  78 */     this.coords = shading.getCoords().toFloatArray();
/*     */ 
/*     */     
/*  81 */     if (this.radialShadingType.getDomain() != null) {
/*     */       
/*  83 */       this.domain = shading.getDomain().toFloatArray();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  88 */       this.domain = new float[] { 0.0F, 1.0F };
/*     */     } 
/*     */ 
/*     */     
/*  92 */     COSArray extendValues = shading.getExtend();
/*  93 */     if (extendValues != null) {
/*     */       
/*  95 */       this.extend = new boolean[2];
/*  96 */       this.extend[0] = ((COSBoolean)extendValues.getObject(0)).getValue();
/*  97 */       this.extend[1] = ((COSBoolean)extendValues.getObject(1)).getValue();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 102 */       this.extend = new boolean[] { false, false };
/*     */     } 
/*     */     
/* 105 */     this.x1x0 = (this.coords[3] - this.coords[0]);
/* 106 */     this.y1y0 = (this.coords[4] - this.coords[1]);
/* 107 */     this.r1r0 = (this.coords[5] - this.coords[2]);
/* 108 */     this.r0pow2 = Math.pow(this.coords[2], 2.0D);
/* 109 */     this.denom = Math.pow(this.x1x0, 2.0D) + Math.pow(this.y1y0, 2.0D) - Math.pow(this.r1r0, 2.0D);
/* 110 */     this.d1d0 = this.domain[1] - this.domain[0];
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 116 */       this.rat = matrix.createAffineTransform().createInverse();
/* 117 */       this.rat.concatenate(xform.createInverse());
/*     */     }
/* 119 */     catch (NoninvertibleTransformException ex) {
/*     */       
/* 121 */       LOG.error(ex.getMessage() + ", matrix: " + matrix, ex);
/* 122 */       this.rat = new AffineTransform();
/*     */     } 
/*     */ 
/*     */     
/* 126 */     AffineTransform shadingToDevice = (AffineTransform)xform.clone();
/* 127 */     shadingToDevice.concatenate(matrix.createAffineTransform());
/*     */ 
/*     */     
/* 130 */     double dist = Math.sqrt(Math.pow(deviceBounds.getMaxX() - deviceBounds.getMinX(), 2.0D) + 
/* 131 */         Math.pow(deviceBounds.getMaxY() - deviceBounds.getMinY(), 2.0D));
/* 132 */     this.factor = (int)Math.ceil(dist);
/*     */ 
/*     */     
/* 135 */     this.colorTable = calcColorTable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] calcColorTable() throws IOException {
/* 146 */     int[] map = new int[this.factor + 1];
/* 147 */     if (this.factor == 0 || this.d1d0 == 0.0F) {
/*     */       
/* 149 */       float[] values = this.radialShadingType.evalFunction(this.domain[0]);
/* 150 */       map[0] = convertToRGB(values);
/*     */     }
/*     */     else {
/*     */       
/* 154 */       for (int i = 0; i <= this.factor; i++) {
/*     */         
/* 156 */         float t = this.domain[0] + this.d1d0 * i / this.factor;
/* 157 */         float[] values = this.radialShadingType.evalFunction(t);
/* 158 */         map[i] = convertToRGB(values);
/*     */       } 
/*     */     } 
/* 161 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 167 */     super.dispose();
/* 168 */     this.radialShadingType = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorModel getColorModel() {
/* 174 */     return super.getColorModel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Raster getRaster(int x, int y, int w, int h) {
/* 181 */     WritableRaster raster = getColorModel().createCompatibleWritableRaster(w, h);
/* 182 */     float inputValue = -1.0F;
/*     */     
/* 184 */     int[] data = new int[w * h * 4];
/* 185 */     for (int j = 0; j < h; j++) {
/*     */       
/* 187 */       for (int i = 0; i < w; i++) {
/*     */         int value;
/* 189 */         float[] values = { (x + i), (y + j) };
/* 190 */         this.rat.transform(values, 0, values, 0, 1);
/* 191 */         boolean useBackground = false;
/* 192 */         float[] inputValues = calculateInputValues(values[0], values[1]);
/* 193 */         if (Float.isNaN(inputValues[0]) && Float.isNaN(inputValues[1])) {
/*     */           
/* 195 */           if (getBackground() == null) {
/*     */             continue;
/*     */           }
/*     */           
/* 199 */           useBackground = true;
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 204 */           if (inputValues[0] >= 0.0F && inputValues[0] <= 1.0F) {
/*     */ 
/*     */             
/* 207 */             if (inputValues[1] >= 0.0F && inputValues[1] <= 1.0F)
/*     */             {
/* 209 */               inputValue = Math.max(inputValues[0], inputValues[1]);
/*     */             
/*     */             }
/*     */             else
/*     */             {
/* 214 */               inputValue = inputValues[0];
/*     */ 
/*     */             
/*     */             }
/*     */ 
/*     */           
/*     */           }
/* 221 */           else if (inputValues[1] >= 0.0F && inputValues[1] <= 1.0F) {
/*     */             
/* 223 */             inputValue = inputValues[1];
/*     */ 
/*     */ 
/*     */           
/*     */           }
/* 228 */           else if (this.extend[0] && this.extend[1]) {
/*     */             
/* 230 */             inputValue = Math.max(inputValues[0], inputValues[1]);
/*     */           }
/* 232 */           else if (this.extend[0]) {
/*     */             
/* 234 */             inputValue = inputValues[0];
/*     */           }
/* 236 */           else if (this.extend[1]) {
/*     */             
/* 238 */             inputValue = inputValues[1];
/*     */           }
/* 240 */           else if (getBackground() != null) {
/*     */             
/* 242 */             useBackground = true;
/*     */           } else {
/*     */             continue;
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 251 */           if (inputValue > 1.0F) {
/*     */ 
/*     */             
/* 254 */             if (this.extend[1] && this.coords[5] > 0.0F)
/*     */             {
/* 256 */               inputValue = 1.0F;
/*     */             }
/*     */             else
/*     */             {
/* 260 */               if (getBackground() == null) {
/*     */                 continue;
/*     */               }
/*     */               
/* 264 */               useBackground = true;
/*     */             }
/*     */           
/*     */           }
/* 268 */           else if (inputValue < 0.0F) {
/*     */ 
/*     */             
/* 271 */             if (this.extend[0] && this.coords[2] > 0.0F) {
/*     */               
/* 273 */               inputValue = 0.0F;
/*     */             }
/*     */             else {
/*     */               
/* 277 */               if (getBackground() == null) {
/*     */                 continue;
/*     */               }
/*     */               
/* 281 */               useBackground = true;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 286 */         if (useBackground) {
/*     */ 
/*     */           
/* 289 */           value = getRgbBackground();
/*     */         }
/*     */         else {
/*     */           
/* 293 */           int key = (int)(inputValue * this.factor);
/* 294 */           value = this.colorTable[key];
/*     */         } 
/* 296 */         int index = (j * w + i) * 4;
/* 297 */         data[index] = value & 0xFF;
/* 298 */         value >>= 8;
/* 299 */         data[index + 1] = value & 0xFF;
/* 300 */         value >>= 8;
/* 301 */         data[index + 2] = value & 0xFF;
/* 302 */         data[index + 3] = 255; continue;
/*     */       } 
/*     */     } 
/* 305 */     raster.setPixels(0, 0, w, h, data);
/* 306 */     return raster;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float[] calculateInputValues(double x, double y) {
/* 327 */     double p = -(x - this.coords[0]) * this.x1x0 - (y - this.coords[1]) * this.y1y0 - this.coords[2] * this.r1r0;
/* 328 */     double q = Math.pow(x - this.coords[0], 2.0D) + Math.pow(y - this.coords[1], 2.0D) - this.r0pow2;
/* 329 */     double root = Math.sqrt(p * p - this.denom * q);
/* 330 */     float root1 = (float)((-p + root) / this.denom);
/* 331 */     float root2 = (float)((-p - root) / this.denom);
/* 332 */     if (this.denom < 0.0D)
/*     */     {
/* 334 */       return new float[] { root1, root2 };
/*     */     }
/*     */ 
/*     */     
/* 338 */     return new float[] { root2, root1 };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getCoords() {
/* 347 */     return this.coords;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getDomain() {
/* 355 */     return this.domain;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean[] getExtend() {
/* 363 */     return this.extend;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFunction getFunction() throws IOException {
/* 373 */     return this.radialShadingType.getFunction();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/RadialShadingContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */