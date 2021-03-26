/*     */ package org.apache.pdfbox.rendering;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.awt.PaintContext;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.pdmodel.common.function.PDFunction;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class SoftMask
/*     */   implements Paint
/*     */ {
/*  46 */   private static final ColorModel ARGB_COLOR_MODEL = (new BufferedImage(1, 1, 2))
/*  47 */     .getColorModel();
/*     */   
/*     */   private final Paint paint;
/*     */   private final BufferedImage mask;
/*     */   private final Rectangle2D bboxDevice;
/*  52 */   private int bc = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final PDFunction transferFunction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   SoftMask(Paint paint, BufferedImage mask, Rectangle2D bboxDevice, PDColor backdropColor, PDFunction transferFunction) {
/*  67 */     this.paint = paint;
/*  68 */     this.mask = mask;
/*  69 */     this.bboxDevice = bboxDevice;
/*  70 */     if (transferFunction instanceof org.apache.pdfbox.pdmodel.common.function.PDFunctionTypeIdentity) {
/*     */       
/*  72 */       this.transferFunction = null;
/*     */     }
/*     */     else {
/*     */       
/*  76 */       this.transferFunction = transferFunction;
/*     */     } 
/*  78 */     if (backdropColor != null) {
/*     */       
/*     */       try {
/*     */         
/*  82 */         Color color = new Color(backdropColor.toRGB());
/*     */         
/*  84 */         this.bc = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
/*     */       }
/*  86 */       catch (IOException iOException) {}
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
/*     */   public PaintContext createContext(ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform xform, RenderingHints hints) {
/*  98 */     PaintContext ctx = this.paint.createContext(cm, deviceBounds, userBounds, xform, hints);
/*  99 */     return new SoftPaintContext(ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTransparency() {
/* 105 */     return 3;
/*     */   }
/*     */   
/*     */   private class SoftPaintContext
/*     */     implements PaintContext
/*     */   {
/*     */     private final PaintContext context;
/*     */     
/*     */     SoftPaintContext(PaintContext context) {
/* 114 */       this.context = context;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ColorModel getColorModel() {
/* 120 */       return SoftMask.ARGB_COLOR_MODEL;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Raster getRaster(int x1, int y1, int w, int h) {
/* 126 */       Raster raster = this.context.getRaster(x1, y1, w, h);
/* 127 */       ColorModel rasterCM = this.context.getColorModel();
/* 128 */       float[] input = null;
/* 129 */       Float[] map = null;
/*     */       
/* 131 */       if (SoftMask.this.transferFunction != null) {
/*     */         
/* 133 */         map = new Float[256];
/* 134 */         input = new float[1];
/*     */       } 
/*     */ 
/*     */       
/* 138 */       WritableRaster output = getColorModel().createCompatibleWritableRaster(w, h);
/*     */ 
/*     */       
/* 141 */       x1 -= (int)SoftMask.this.bboxDevice.getX();
/* 142 */       y1 -= (int)SoftMask.this.bboxDevice.getY();
/*     */       
/* 144 */       int[] gray = new int[4];
/* 145 */       Object pixelInput = null;
/* 146 */       int[] pixelOutput = new int[4];
/* 147 */       for (int y = 0; y < h; y++) {
/*     */         
/* 149 */         for (int x = 0; x < w; x++) {
/*     */           
/* 151 */           pixelInput = raster.getDataElements(x, y, pixelInput);
/*     */           
/* 153 */           pixelOutput[0] = rasterCM.getRed(pixelInput);
/* 154 */           pixelOutput[1] = rasterCM.getGreen(pixelInput);
/* 155 */           pixelOutput[2] = rasterCM.getBlue(pixelInput);
/* 156 */           pixelOutput[3] = rasterCM.getAlpha(pixelInput);
/*     */ 
/*     */           
/* 159 */           gray[0] = 0;
/* 160 */           if (x1 + x >= 0 && y1 + y >= 0 && x1 + x < SoftMask.this.mask.getWidth() && y1 + y < SoftMask.this.mask.getHeight()) {
/*     */             
/* 162 */             SoftMask.this.mask.getRaster().getPixel(x1 + x, y1 + y, gray);
/* 163 */             int g = gray[0];
/* 164 */             if (SoftMask.this.transferFunction != null) {
/*     */ 
/*     */               
/*     */               try {
/*     */                 
/* 169 */                 if (map[g] != null)
/*     */                 {
/*     */                   
/* 172 */                   pixelOutput[3] = Math.round(pixelOutput[3] * map[g].floatValue());
/*     */                 
/*     */                 }
/*     */                 else
/*     */                 {
/* 177 */                   input[0] = g / 255.0F;
/* 178 */                   float f = SoftMask.this.transferFunction.eval(input)[0];
/* 179 */                   map[g] = Float.valueOf(f);
/* 180 */                   pixelOutput[3] = Math.round(pixelOutput[3] * f);
/*     */                 }
/*     */               
/* 183 */               } catch (IOException ex) {
/*     */ 
/*     */                 
/* 186 */                 pixelOutput[3] = Math.round(pixelOutput[3] * SoftMask.this.bc / 255.0F);
/*     */               }
/*     */             
/*     */             } else {
/*     */               
/* 191 */               pixelOutput[3] = Math.round(pixelOutput[3] * g / 255.0F);
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 196 */             pixelOutput[3] = Math.round(pixelOutput[3] * SoftMask.this.bc / 255.0F);
/*     */           } 
/* 198 */           output.setPixel(x, y, pixelOutput);
/*     */         } 
/*     */       } 
/*     */       
/* 202 */       return output;
/*     */     }
/*     */     
/*     */     public void dispose() {}
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/rendering/SoftMask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */