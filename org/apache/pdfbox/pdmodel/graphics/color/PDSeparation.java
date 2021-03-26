/*     */ package org.apache.pdfbox.pdmodel.graphics.color;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSNull;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.function.PDFunction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDSeparation
/*     */   extends PDSpecialColorSpace
/*     */ {
/*  45 */   private final PDColor initialColor = new PDColor(new float[] { 1.0F }, this);
/*     */   
/*     */   private static final int COLORANT_NAMES = 1;
/*     */   
/*     */   private static final int ALTERNATE_CS = 2;
/*     */   
/*     */   private static final int TINT_TRANSFORM = 3;
/*     */   
/*  53 */   private PDColorSpace alternateColorSpace = null;
/*  54 */   private PDFunction tintTransform = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   private Map<Integer, float[]> toRGBMap = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDSeparation() {
/*  69 */     this.array = new COSArray();
/*  70 */     this.array.add((COSBase)COSName.SEPARATION);
/*  71 */     this.array.add((COSBase)COSName.getPDFName(""));
/*     */     
/*  73 */     this.array.add((COSBase)COSNull.NULL);
/*  74 */     this.array.add((COSBase)COSNull.NULL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDSeparation(COSArray separation) throws IOException {
/*  84 */     this.array = separation;
/*  85 */     this.alternateColorSpace = PDColorSpace.create(this.array.getObject(2));
/*  86 */     this.tintTransform = PDFunction.create(this.array.getObject(3));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  92 */     return COSName.SEPARATION.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfComponents() {
/*  98 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getDefaultDecode(int bitsPerComponent) {
/* 104 */     return new float[] { 0.0F, 1.0F };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor getInitialColor() {
/* 110 */     return this.initialColor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] toRGB(float[] value) throws IOException {
/* 116 */     if (this.toRGBMap == null)
/*     */     {
/* 118 */       this.toRGBMap = (Map)new HashMap<Integer, float>();
/*     */     }
/* 120 */     int key = (int)(value[0] * 255.0F);
/* 121 */     float[] retval = this.toRGBMap.get(Integer.valueOf(key));
/* 122 */     if (retval != null)
/*     */     {
/* 124 */       return retval;
/*     */     }
/* 126 */     float[] altColor = this.tintTransform.eval(value);
/* 127 */     retval = this.alternateColorSpace.toRGB(altColor);
/* 128 */     this.toRGBMap.put(Integer.valueOf(key), retval);
/* 129 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage toRGBImage(WritableRaster raster) throws IOException {
/* 138 */     if (this.alternateColorSpace instanceof PDLab)
/*     */     {
/*     */       
/* 141 */       return toRGBImage2(raster);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 146 */     WritableRaster altRaster = Raster.createBandedRaster(0, raster
/* 147 */         .getWidth(), raster.getHeight(), this.alternateColorSpace
/* 148 */         .getNumberOfComponents(), new Point(0, 0));
/*     */ 
/*     */     
/* 151 */     int numAltComponents = this.alternateColorSpace.getNumberOfComponents();
/* 152 */     int width = raster.getWidth();
/* 153 */     int height = raster.getHeight();
/* 154 */     float[] samples = new float[1];
/*     */     
/* 156 */     Map<Integer, int[]> calculatedValues = (Map)new HashMap<Integer, int>();
/*     */     
/* 158 */     for (int y = 0; y < height; y++) {
/*     */       
/* 160 */       for (int x = 0; x < width; x++) {
/*     */         
/* 162 */         raster.getPixel(x, y, samples); Integer hash;
/* 163 */         int[] alt = calculatedValues.get(hash = Integer.valueOf(Float.floatToIntBits(samples[0])));
/* 164 */         if (alt == null) {
/*     */           
/* 166 */           alt = new int[numAltComponents];
/* 167 */           tintTransform(samples, alt);
/* 168 */           calculatedValues.put(hash, alt);
/*     */         } 
/* 170 */         altRaster.setPixel(x, y, alt);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 175 */     return this.alternateColorSpace.toRGBImage(altRaster);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private BufferedImage toRGBImage2(WritableRaster raster) throws IOException {
/* 181 */     int width = raster.getWidth();
/* 182 */     int height = raster.getHeight();
/* 183 */     BufferedImage rgbImage = new BufferedImage(width, height, 1);
/* 184 */     WritableRaster rgbRaster = rgbImage.getRaster();
/* 185 */     float[] samples = new float[1];
/*     */     
/* 187 */     Map<Integer, int[]> calculatedValues = (Map)new HashMap<Integer, int>();
/*     */     
/* 189 */     for (int y = 0; y < height; y++) {
/*     */       
/* 191 */       for (int x = 0; x < width; x++) {
/*     */         
/* 193 */         raster.getPixel(x, y, samples); Integer hash;
/* 194 */         int[] rgb = calculatedValues.get(hash = Integer.valueOf(Float.floatToIntBits(samples[0])));
/* 195 */         if (rgb == null) {
/*     */           
/* 197 */           samples[0] = samples[0] / 255.0F;
/* 198 */           float[] altColor = this.tintTransform.eval(samples);
/* 199 */           float[] fltab = this.alternateColorSpace.toRGB(altColor);
/* 200 */           rgb = new int[3];
/* 201 */           rgb[0] = (int)(fltab[0] * 255.0F);
/* 202 */           rgb[1] = (int)(fltab[1] * 255.0F);
/* 203 */           rgb[2] = (int)(fltab[2] * 255.0F);
/* 204 */           calculatedValues.put(hash, rgb);
/*     */         } 
/* 206 */         rgbRaster.setPixel(x, y, rgb);
/*     */       } 
/*     */     } 
/* 209 */     return rgbImage;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void tintTransform(float[] samples, int[] alt) throws IOException {
/* 214 */     samples[0] = samples[0] / 255.0F;
/* 215 */     float[] result = this.tintTransform.eval(samples);
/* 216 */     for (int s = 0; s < alt.length; s++)
/*     */     {
/*     */       
/* 219 */       alt[s] = (int)(result[s] * 255.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColorSpace getAlternateColorSpace() {
/* 229 */     return this.alternateColorSpace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getColorantName() {
/* 238 */     COSName name = (COSName)this.array.getObject(1);
/* 239 */     return name.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorantName(String name) {
/* 248 */     this.array.set(1, (COSBase)COSName.getPDFName(name));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlternateColorSpace(PDColorSpace colorSpace) {
/* 257 */     this.alternateColorSpace = colorSpace;
/* 258 */     COSBase space = null;
/* 259 */     if (colorSpace != null)
/*     */     {
/* 261 */       space = colorSpace.getCOSObject();
/*     */     }
/* 263 */     this.array.set(2, space);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTintTransform(PDFunction tint) {
/* 272 */     this.tintTransform = tint;
/* 273 */     this.array.set(3, (COSObjectable)tint);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 279 */     return getName() + "{\"" + 
/* 280 */       getColorantName() + "\" " + this.alternateColorSpace
/* 281 */       .getName() + " " + this.tintTransform + "}";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDSeparation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */