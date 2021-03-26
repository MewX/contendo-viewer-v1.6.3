/*     */ package org.apache.pdfbox.pdmodel.graphics.blend;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Composite;
/*     */ import java.awt.CompositeContext;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BlendComposite
/*     */   implements Composite
/*     */ {
/*  40 */   private static final Log LOG = LogFactory.getLog(BlendComposite.class);
/*     */ 
/*     */ 
/*     */   
/*     */   private final BlendMode blendMode;
/*     */ 
/*     */   
/*     */   private final float constantAlpha;
/*     */ 
/*     */ 
/*     */   
/*     */   public static Composite getInstance(BlendMode blendMode, float constantAlpha) {
/*  52 */     if (constantAlpha < 0.0F) {
/*     */       
/*  54 */       LOG.warn("using 0 instead of incorrect Alpha " + constantAlpha);
/*  55 */       constantAlpha = 0.0F;
/*     */     }
/*  57 */     else if (constantAlpha > 1.0F) {
/*     */       
/*  59 */       LOG.warn("using 1 instead of incorrect Alpha " + constantAlpha);
/*  60 */       constantAlpha = 1.0F;
/*     */     } 
/*  62 */     if (blendMode == BlendMode.NORMAL)
/*     */     {
/*  64 */       return AlphaComposite.getInstance(3, constantAlpha);
/*     */     }
/*     */ 
/*     */     
/*  68 */     return new BlendComposite(blendMode, constantAlpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BlendComposite(BlendMode blendMode, float constantAlpha) {
/*  78 */     this.blendMode = blendMode;
/*  79 */     this.constantAlpha = constantAlpha;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompositeContext createContext(ColorModel srcColorModel, ColorModel dstColorModel, RenderingHints hints) {
/*  86 */     return new BlendCompositeContext(srcColorModel, dstColorModel, hints);
/*     */   }
/*     */ 
/*     */   
/*     */   class BlendCompositeContext
/*     */     implements CompositeContext
/*     */   {
/*     */     private final ColorModel srcColorModel;
/*     */     private final ColorModel dstColorModel;
/*     */     private final RenderingHints hints;
/*     */     
/*     */     BlendCompositeContext(ColorModel srcColorModel, ColorModel dstColorModel, RenderingHints hints) {
/*  98 */       this.srcColorModel = srcColorModel;
/*  99 */       this.dstColorModel = dstColorModel;
/* 100 */       this.hints = hints;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void dispose() {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void compose(Raster src, Raster dstIn, WritableRaster dstOut) {
/* 112 */       int x0 = src.getMinX();
/* 113 */       int y0 = src.getMinY();
/* 114 */       int width = Math.min(Math.min(src.getWidth(), dstIn.getWidth()), dstOut.getWidth());
/* 115 */       int height = Math.min(Math.min(src.getHeight(), dstIn.getHeight()), dstOut.getHeight());
/* 116 */       int x1 = x0 + width;
/* 117 */       int y1 = y0 + height;
/* 118 */       int dstInXShift = dstIn.getMinX() - x0;
/* 119 */       int dstInYShift = dstIn.getMinY() - y0;
/* 120 */       int dstOutXShift = dstOut.getMinX() - x0;
/* 121 */       int dstOutYShift = dstOut.getMinY() - y0;
/*     */       
/* 123 */       ColorSpace srcColorSpace = this.srcColorModel.getColorSpace();
/* 124 */       int numSrcColorComponents = this.srcColorModel.getNumColorComponents();
/* 125 */       int numSrcComponents = src.getNumBands();
/* 126 */       boolean srcHasAlpha = (numSrcComponents > numSrcColorComponents);
/* 127 */       ColorSpace dstColorSpace = this.dstColorModel.getColorSpace();
/* 128 */       int numDstColorComponents = this.dstColorModel.getNumColorComponents();
/* 129 */       int numDstComponents = dstIn.getNumBands();
/* 130 */       boolean dstHasAlpha = (numDstComponents > numDstColorComponents);
/*     */       
/* 132 */       int srcColorSpaceType = srcColorSpace.getType();
/* 133 */       int dstColorSpaceType = dstColorSpace.getType();
/* 134 */       boolean subtractive = (dstColorSpaceType != 5 && dstColorSpaceType != 6);
/*     */ 
/*     */       
/* 137 */       boolean blendModeIsSeparable = BlendComposite.this.blendMode instanceof SeparableBlendMode;
/*     */       
/* 139 */       SeparableBlendMode separableBlendMode = blendModeIsSeparable ? (SeparableBlendMode)BlendComposite.this.blendMode : null;
/*     */       
/* 141 */       NonSeparableBlendMode nonSeparableBlendMode = !blendModeIsSeparable ? (NonSeparableBlendMode)BlendComposite.this.blendMode : null;
/*     */       
/* 143 */       boolean needsColorConversion = !srcColorSpace.equals(dstColorSpace);
/*     */       
/* 145 */       Object srcPixel = null;
/* 146 */       Object dstPixel = null;
/* 147 */       float[] srcComponents = new float[numSrcComponents];
/*     */ 
/*     */       
/* 150 */       float[] dstComponents = null;
/*     */       
/* 152 */       float[] srcColor = new float[numSrcColorComponents];
/*     */ 
/*     */       
/* 155 */       float[] rgbResult = blendModeIsSeparable ? null : new float[dstHasAlpha ? 4 : 3];
/*     */       
/* 157 */       for (int y = y0; y < y1; y++) {
/*     */         
/* 159 */         for (int x = x0; x < x1; x++) {
/*     */           
/* 161 */           srcPixel = src.getDataElements(x, y, srcPixel);
/* 162 */           dstPixel = dstIn.getDataElements(dstInXShift + x, dstInYShift + y, dstPixel);
/*     */           
/* 164 */           srcComponents = this.srcColorModel.getNormalizedComponents(srcPixel, srcComponents, 0);
/*     */           
/* 166 */           dstComponents = this.dstColorModel.getNormalizedComponents(dstPixel, dstComponents, 0);
/*     */ 
/*     */           
/* 169 */           float srcAlpha = srcHasAlpha ? srcComponents[numSrcColorComponents] : 1.0F;
/* 170 */           float dstAlpha = dstHasAlpha ? dstComponents[numDstColorComponents] : 1.0F;
/*     */           
/* 172 */           srcAlpha *= BlendComposite.this.constantAlpha;
/*     */           
/* 174 */           float resultAlpha = dstAlpha + srcAlpha - srcAlpha * dstAlpha;
/* 175 */           float srcAlphaRatio = (resultAlpha > 0.0F) ? (srcAlpha / resultAlpha) : 0.0F;
/*     */           
/* 177 */           if (separableBlendMode != null) {
/*     */             float[] srcConverted;
/*     */             
/* 180 */             System.arraycopy(srcComponents, 0, srcColor, 0, numSrcColorComponents);
/* 181 */             if (needsColorConversion) {
/*     */ 
/*     */               
/* 184 */               float[] cieXYZ = srcColorSpace.toCIEXYZ(srcColor);
/* 185 */               srcConverted = dstColorSpace.fromCIEXYZ(cieXYZ);
/*     */             }
/*     */             else {
/*     */               
/* 189 */               srcConverted = srcColor;
/*     */             } 
/*     */             
/* 192 */             for (int k = 0; k < numDstColorComponents; k++) {
/*     */               
/* 194 */               float srcValue = srcConverted[k];
/* 195 */               float dstValue = dstComponents[k];
/*     */               
/* 197 */               if (subtractive) {
/*     */                 
/* 199 */                 srcValue = 1.0F - srcValue;
/* 200 */                 dstValue = 1.0F - dstValue;
/*     */               } 
/*     */               
/* 203 */               float value = separableBlendMode.blendChannel(srcValue, dstValue);
/* 204 */               value = srcValue + dstAlpha * (value - srcValue);
/* 205 */               value = dstValue + srcAlphaRatio * (value - dstValue);
/*     */               
/* 207 */               if (subtractive)
/*     */               {
/* 209 */                 value = 1.0F - value;
/*     */               }
/*     */               
/* 212 */               dstComponents[k] = value;
/*     */             } 
/*     */           } else {
/*     */             float[] srcConverted, dstConverted;
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 220 */             if (srcColorSpaceType == 5) {
/*     */               
/* 222 */               srcConverted = srcComponents;
/*     */             }
/*     */             else {
/*     */               
/* 226 */               srcConverted = srcColorSpace.toRGB(srcComponents);
/*     */             } 
/*     */             
/* 229 */             if (dstColorSpaceType == 5) {
/*     */               
/* 231 */               dstConverted = dstComponents;
/*     */             }
/*     */             else {
/*     */               
/* 235 */               dstConverted = dstColorSpace.toRGB(dstComponents);
/*     */             } 
/*     */             
/* 238 */             nonSeparableBlendMode.blend(srcConverted, dstConverted, rgbResult);
/*     */             
/* 240 */             for (int k = 0; k < 3; k++) {
/*     */               
/* 242 */               float srcValue = srcConverted[k];
/* 243 */               float dstValue = dstConverted[k];
/* 244 */               float value = rgbResult[k];
/* 245 */               value = Math.max(Math.min(value, 1.0F), 0.0F);
/* 246 */               value = srcValue + dstAlpha * (value - srcValue);
/* 247 */               value = dstValue + srcAlphaRatio * (value - dstValue);
/* 248 */               rgbResult[k] = value;
/*     */             } 
/*     */             
/* 251 */             if (dstColorSpaceType == 5) {
/*     */               
/* 253 */               System.arraycopy(rgbResult, 0, dstComponents, 0, dstComponents.length);
/*     */             }
/*     */             else {
/*     */               
/* 257 */               float[] temp = dstColorSpace.fromRGB(rgbResult);
/* 258 */               System.arraycopy(temp, 0, dstComponents, 0, 
/* 259 */                   Math.min(dstComponents.length, temp.length));
/*     */             } 
/*     */           } 
/*     */           
/* 263 */           if (dstHasAlpha)
/*     */           {
/* 265 */             dstComponents[numDstColorComponents] = resultAlpha;
/*     */           }
/*     */           
/* 268 */           dstPixel = this.dstColorModel.getDataElements(dstComponents, 0, dstPixel);
/* 269 */           dstOut.setDataElements(dstOutXShift + x, dstOutYShift + y, dstPixel);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/blend/BlendComposite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */