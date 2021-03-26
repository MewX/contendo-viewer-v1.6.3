/*     */ package org.apache.pdfbox.pdmodel.graphics.color;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorConvertOp;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSObject;
/*     */ import org.apache.pdfbox.pdmodel.MissingResourceException;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.ResourceCache;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PDColorSpace
/*     */   implements COSObjectable
/*     */ {
/*     */   protected COSArray array;
/*     */   
/*     */   public static PDColorSpace create(COSBase colorSpace) throws IOException {
/*  53 */     return create(colorSpace, null);
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
/*     */   public static PDColorSpace create(COSBase colorSpace, PDResources resources) throws IOException {
/*  70 */     return create(colorSpace, resources, false);
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
/*     */   public static PDColorSpace create(COSBase colorSpace, PDResources resources, boolean wasDefault) throws IOException {
/*  90 */     if (colorSpace instanceof COSObject)
/*     */     {
/*  92 */       return createFromCOSObject((COSObject)colorSpace, resources);
/*     */     }
/*  94 */     if (colorSpace instanceof COSName) {
/*     */       
/*  96 */       COSName name = (COSName)colorSpace;
/*     */ 
/*     */       
/*  99 */       if (resources != null) {
/*     */         
/* 101 */         COSName defaultName = null;
/* 102 */         if (name.equals(COSName.DEVICECMYK) && resources
/* 103 */           .hasColorSpace(COSName.DEFAULT_CMYK)) {
/*     */           
/* 105 */           defaultName = COSName.DEFAULT_CMYK;
/*     */         }
/* 107 */         else if (name.equals(COSName.DEVICERGB) && resources
/* 108 */           .hasColorSpace(COSName.DEFAULT_RGB)) {
/*     */           
/* 110 */           defaultName = COSName.DEFAULT_RGB;
/*     */         }
/* 112 */         else if (name.equals(COSName.DEVICEGRAY) && resources
/* 113 */           .hasColorSpace(COSName.DEFAULT_GRAY)) {
/*     */           
/* 115 */           defaultName = COSName.DEFAULT_GRAY;
/*     */         } 
/*     */         
/* 118 */         if (resources.hasColorSpace(defaultName) && !wasDefault)
/*     */         {
/* 120 */           return resources.getColorSpace(defaultName, true);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 125 */       if (name == COSName.DEVICECMYK)
/*     */       {
/* 127 */         return PDDeviceCMYK.INSTANCE;
/*     */       }
/* 129 */       if (name == COSName.DEVICERGB)
/*     */       {
/* 131 */         return PDDeviceRGB.INSTANCE;
/*     */       }
/* 133 */       if (name == COSName.DEVICEGRAY)
/*     */       {
/* 135 */         return PDDeviceGray.INSTANCE;
/*     */       }
/* 137 */       if (name == COSName.PATTERN)
/*     */       {
/* 139 */         return new PDPattern(resources);
/*     */       }
/* 141 */       if (resources != null) {
/*     */         
/* 143 */         if (!resources.hasColorSpace(name))
/*     */         {
/* 145 */           throw new MissingResourceException("Missing color space: " + name.getName());
/*     */         }
/* 147 */         return resources.getColorSpace(name);
/*     */       } 
/*     */ 
/*     */       
/* 151 */       throw new MissingResourceException("Unknown color space: " + name.getName());
/*     */     } 
/*     */     
/* 154 */     if (colorSpace instanceof COSArray) {
/*     */       
/* 156 */       COSArray array = (COSArray)colorSpace;
/* 157 */       if (array.size() == 0)
/*     */       {
/* 159 */         throw new IOException("Colorspace array is empty");
/*     */       }
/* 161 */       COSBase base = array.getObject(0);
/* 162 */       if (!(base instanceof COSName))
/*     */       {
/* 164 */         throw new IOException("First element in colorspace array must be a name");
/*     */       }
/* 166 */       COSName name = (COSName)base;
/*     */ 
/*     */ 
/*     */       
/* 170 */       if (name == COSName.CALGRAY)
/*     */       {
/* 172 */         return new PDCalGray(array);
/*     */       }
/* 174 */       if (name == COSName.CALRGB)
/*     */       {
/* 176 */         return new PDCalRGB(array);
/*     */       }
/* 178 */       if (name == COSName.DEVICEN)
/*     */       {
/* 180 */         return new PDDeviceN(array);
/*     */       }
/* 182 */       if (name == COSName.INDEXED)
/*     */       {
/* 184 */         return new PDIndexed(array, resources);
/*     */       }
/* 186 */       if (name == COSName.SEPARATION)
/*     */       {
/* 188 */         return new PDSeparation(array);
/*     */       }
/* 190 */       if (name == COSName.ICCBASED)
/*     */       {
/* 192 */         return PDICCBased.create(array, resources);
/*     */       }
/* 194 */       if (name == COSName.LAB)
/*     */       {
/* 196 */         return new PDLab(array);
/*     */       }
/* 198 */       if (name == COSName.PATTERN) {
/*     */         
/* 200 */         if (array.size() == 1)
/*     */         {
/* 202 */           return new PDPattern(resources);
/*     */         }
/*     */ 
/*     */         
/* 206 */         return new PDPattern(resources, create(array.get(1)));
/*     */       } 
/*     */       
/* 209 */       if (name == COSName.DEVICECMYK || name == COSName.DEVICERGB || name == COSName.DEVICEGRAY)
/*     */       {
/*     */ 
/*     */ 
/*     */         
/* 214 */         return create((COSBase)name, resources, wasDefault);
/*     */       }
/*     */ 
/*     */       
/* 218 */       throw new IOException("Invalid color space kind: " + name);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 223 */     throw new IOException("Expected a name or array but got: " + colorSpace);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static PDColorSpace createFromCOSObject(COSObject colorSpace, PDResources resources) throws IOException {
/* 231 */     if (resources != null && resources.getResourceCache() != null) {
/*     */       
/* 233 */       ResourceCache resourceCache = resources.getResourceCache();
/* 234 */       PDColorSpace pDColorSpace = resourceCache.getColorSpace(colorSpace);
/* 235 */       if (pDColorSpace != null)
/*     */       {
/* 237 */         return pDColorSpace;
/*     */       }
/*     */     } 
/* 240 */     PDColorSpace cs = create(colorSpace.getObject(), resources);
/* 241 */     if (resources != null && resources.getResourceCache() != null && cs != null) {
/*     */       
/* 243 */       ResourceCache resourceCache = resources.getResourceCache();
/* 244 */       resourceCache.put(colorSpace, cs);
/*     */     } 
/* 246 */     return cs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String getName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getNumberOfComponents();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract float[] getDefaultDecode(int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract PDColor getInitialColor();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract float[] toRGB(float[] paramArrayOffloat) throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract BufferedImage toRGBImage(WritableRaster paramWritableRaster) throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BufferedImage toRGBImageAWT(WritableRaster raster, ColorSpace colorSpace) {
/* 308 */     ColorModel colorModel = new ComponentColorModel(colorSpace, false, false, 1, raster.getDataBuffer().getDataType());
/*     */     
/* 310 */     BufferedImage src = new BufferedImage(colorModel, raster, false, null);
/* 311 */     BufferedImage dest = new BufferedImage(raster.getWidth(), raster.getHeight(), 1);
/*     */     
/* 313 */     ColorConvertOp op = new ColorConvertOp(null);
/* 314 */     op.filter(src, dest);
/* 315 */     return dest;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/* 321 */     return (COSBase)this.array;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDColorSpace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */