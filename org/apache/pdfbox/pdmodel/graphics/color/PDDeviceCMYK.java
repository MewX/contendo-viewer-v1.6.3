/*     */ package org.apache.pdfbox.pdmodel.graphics.color;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.color.ICC_ColorSpace;
/*     */ import java.awt.color.ICC_Profile;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Arrays;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDDeviceCMYK
/*     */   extends PDDeviceColorSpace
/*     */ {
/*  45 */   public static PDDeviceCMYK INSTANCE = new PDDeviceCMYK();
/*     */ 
/*     */   
/*  48 */   private final PDColor initialColor = new PDColor(new float[] { 0.0F, 0.0F, 0.0F, 1.0F }, this);
/*     */ 
/*     */ 
/*     */   
/*     */   private volatile ICC_ColorSpace awtColorSpace;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean usePureJavaCMYKConversion = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void init() throws IOException {
/*  62 */     if (this.awtColorSpace != null) {
/*     */       return;
/*     */     }
/*     */     
/*  66 */     synchronized (this) {
/*     */ 
/*     */       
/*  69 */       if (this.awtColorSpace != null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/*  74 */       ICC_Profile iccProfile = getICCProfile();
/*  75 */       if (iccProfile == null)
/*     */       {
/*  77 */         throw new IOException("Default CMYK color profile could not be loaded");
/*     */       }
/*  79 */       this.awtColorSpace = new ICC_ColorSpace(iccProfile);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  84 */       this.awtColorSpace.toRGB(new float[] { 0.0F, 0.0F, 0.0F, 0.0F });
/*  85 */       this
/*  86 */         .usePureJavaCMYKConversion = (System.getProperty("org.apache.pdfbox.rendering.UsePureJavaCMYKConversion") != null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ICC_Profile getICCProfile() throws IOException {
/*  97 */     String name = "/org/apache/pdfbox/resources/icc/ISOcoated_v2_300_bas.icc";
/*     */     
/*  99 */     InputStream is = PDDeviceCMYK.class.getResourceAsStream(name);
/* 100 */     if (is == null)
/*     */     {
/* 102 */       throw new IOException("Error loading resource: " + name);
/*     */     }
/* 104 */     ICC_Profile iccProfile = ICC_Profile.getInstance(is);
/* 105 */     is.close();
/*     */     
/* 107 */     return iccProfile;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 113 */     return COSName.DEVICECMYK.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfComponents() {
/* 119 */     return 4;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getDefaultDecode(int bitsPerComponent) {
/* 125 */     return new float[] { 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor getInitialColor() {
/* 131 */     return this.initialColor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] toRGB(float[] value) throws IOException {
/* 137 */     init();
/* 138 */     return this.awtColorSpace.toRGB(value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage toRGBImage(WritableRaster raster) throws IOException {
/* 144 */     init();
/* 145 */     return toRGBImageAWT(raster, this.awtColorSpace);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BufferedImage toRGBImageAWT(WritableRaster raster, ColorSpace colorSpace) {
/* 151 */     if (this.usePureJavaCMYKConversion) {
/*     */       
/* 153 */       BufferedImage dest = new BufferedImage(raster.getWidth(), raster.getHeight(), 1);
/*     */       
/* 155 */       ColorSpace destCS = dest.getColorModel().getColorSpace();
/* 156 */       WritableRaster destRaster = dest.getRaster();
/* 157 */       float[] srcValues = new float[4];
/* 158 */       float[] lastValues = { -1.0F, -1.0F, -1.0F, -1.0F };
/* 159 */       float[] destValues = new float[3];
/* 160 */       int width = raster.getWidth();
/* 161 */       int startX = raster.getMinX();
/* 162 */       int height = raster.getHeight();
/* 163 */       int startY = raster.getMinY();
/* 164 */       for (int x = startX; x < width + startX; x++) {
/*     */         
/* 166 */         for (int y = startY; y < height + startY; y++) {
/*     */           
/* 168 */           raster.getPixel(x, y, srcValues);
/*     */           
/* 170 */           if (!Arrays.equals(lastValues, srcValues)) {
/*     */             int k;
/* 172 */             for (k = 0; k < 4; k++) {
/*     */               
/* 174 */               lastValues[k] = srcValues[k];
/* 175 */               srcValues[k] = srcValues[k] / 255.0F;
/*     */             } 
/*     */             
/* 178 */             destValues = destCS.fromCIEXYZ(colorSpace.toCIEXYZ(srcValues));
/* 179 */             for (k = 0; k < destValues.length; k++)
/*     */             {
/* 181 */               destValues[k] = destValues[k] * 255.0F;
/*     */             }
/*     */           } 
/* 184 */           destRaster.setPixel(x, y, destValues);
/*     */         } 
/*     */       } 
/* 187 */       return dest;
/*     */     } 
/*     */ 
/*     */     
/* 191 */     return super.toRGBImageAWT(raster, colorSpace);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDDeviceCMYK.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */