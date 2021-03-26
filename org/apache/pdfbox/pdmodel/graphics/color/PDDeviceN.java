/*     */ package org.apache.pdfbox.pdmodel.graphics.color;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSNull;
/*     */ import org.apache.pdfbox.pdmodel.common.COSArrayList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDDeviceN
/*     */   extends PDSpecialColorSpace
/*     */ {
/*     */   private static final int COLORANT_NAMES = 1;
/*     */   private static final int ALTERNATE_CS = 2;
/*     */   private static final int TINT_TRANSFORM = 3;
/*     */   private static final int DEVICEN_ATTRIBUTES = 4;
/*  57 */   private PDColorSpace alternateColorSpace = null;
/*  58 */   private PDFunction tintTransform = null;
/*     */   
/*     */   private PDDeviceNAttributes attributes;
/*     */   
/*     */   private PDColor initialColor;
/*     */   
/*     */   private int numColorants;
/*     */   
/*     */   private int[] colorantToComponent;
/*     */   
/*     */   private PDColorSpace processColorSpace;
/*     */   
/*     */   private PDSeparation[] spotColorSpaces;
/*     */   
/*     */   public PDDeviceN() {
/*  73 */     this.array = new COSArray();
/*  74 */     this.array.add((COSBase)COSName.DEVICEN);
/*     */ 
/*     */     
/*  77 */     this.array.add((COSBase)COSNull.NULL);
/*  78 */     this.array.add((COSBase)COSNull.NULL);
/*  79 */     this.array.add((COSBase)COSNull.NULL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDDeviceN(COSArray deviceN) throws IOException {
/*  88 */     this.array = deviceN;
/*  89 */     this.alternateColorSpace = PDColorSpace.create(this.array.getObject(2));
/*  90 */     this.tintTransform = PDFunction.create(this.array.getObject(3));
/*     */     
/*  92 */     if (this.array.size() > 4)
/*     */     {
/*  94 */       this.attributes = new PDDeviceNAttributes((COSDictionary)this.array.getObject(4));
/*     */     }
/*  96 */     initColorConversionCache();
/*     */ 
/*     */     
/*  99 */     int n = getNumberOfComponents();
/* 100 */     float[] initial = new float[n];
/* 101 */     for (int i = 0; i < n; i++)
/*     */     {
/* 103 */       initial[i] = 1.0F;
/*     */     }
/* 105 */     this.initialColor = new PDColor(initial, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initColorConversionCache() throws IOException {
/* 112 */     if (this.attributes == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 118 */     List<String> colorantNames = getColorantNames();
/* 119 */     this.numColorants = colorantNames.size();
/*     */ 
/*     */     
/* 122 */     this.colorantToComponent = new int[this.numColorants];
/* 123 */     for (int c = 0; c < this.numColorants; c++)
/*     */     {
/* 125 */       this.colorantToComponent[c] = -1;
/*     */     }
/*     */     
/* 128 */     if (this.attributes.getProcess() != null) {
/*     */       
/* 130 */       List<String> components = this.attributes.getProcess().getComponents();
/*     */ 
/*     */       
/* 133 */       for (int j = 0; j < this.numColorants; j++)
/*     */       {
/* 135 */         this.colorantToComponent[j] = components.indexOf(colorantNames.get(j));
/*     */       }
/*     */ 
/*     */       
/* 139 */       this.processColorSpace = this.attributes.getProcess().getColorSpace();
/*     */     } 
/*     */ 
/*     */     
/* 143 */     this.spotColorSpaces = new PDSeparation[this.numColorants];
/*     */ 
/*     */     
/* 146 */     Map<String, PDSeparation> spotColorants = this.attributes.getColorants();
/*     */ 
/*     */     
/* 149 */     for (int i = 0; i < this.numColorants; i++) {
/*     */       
/* 151 */       String name = colorantNames.get(i);
/* 152 */       PDSeparation spot = spotColorants.get(name);
/* 153 */       if (spot != null) {
/*     */ 
/*     */         
/* 156 */         this.spotColorSpaces[i] = spot;
/*     */ 
/*     */ 
/*     */         
/* 160 */         if (!isNChannel())
/*     */         {
/* 162 */           this.colorantToComponent[i] = -1;
/*     */         
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 168 */         this.spotColorSpaces[i] = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage toRGBImage(WritableRaster raster) throws IOException {
/* 176 */     if (this.attributes != null)
/*     */     {
/* 178 */       return toRGBWithAttributes(raster);
/*     */     }
/*     */ 
/*     */     
/* 182 */     return toRGBWithTintTransform(raster);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BufferedImage toRGBWithAttributes(WritableRaster raster) throws IOException {
/* 191 */     int width = raster.getWidth();
/* 192 */     int height = raster.getHeight();
/*     */     
/* 194 */     BufferedImage rgbImage = new BufferedImage(width, height, 1);
/* 195 */     WritableRaster rgbRaster = rgbImage.getRaster();
/*     */ 
/*     */     
/* 198 */     Graphics2D g = rgbImage.createGraphics();
/* 199 */     g.setBackground(Color.WHITE);
/* 200 */     g.clearRect(0, 0, width, height);
/* 201 */     g.dispose();
/*     */ 
/*     */     
/* 204 */     for (int c = 0; c < this.numColorants; c++) {
/*     */       PDColorSpace componentColorSpace;
/*     */       
/* 207 */       if (this.colorantToComponent[c] >= 0) {
/*     */ 
/*     */         
/* 210 */         componentColorSpace = this.processColorSpace;
/*     */       } else {
/* 212 */         if (this.spotColorSpaces[c] == null)
/*     */         {
/*     */ 
/*     */           
/* 216 */           return toRGBWithTintTransform(raster);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 221 */         componentColorSpace = this.spotColorSpaces[c];
/*     */       } 
/*     */ 
/*     */       
/* 225 */       WritableRaster componentRaster = Raster.createBandedRaster(0, width, height, componentColorSpace
/* 226 */           .getNumberOfComponents(), new Point(0, 0));
/*     */       
/* 228 */       int[] samples = new int[this.numColorants];
/* 229 */       int[] componentSamples = new int[componentColorSpace.getNumberOfComponents()];
/* 230 */       boolean isProcessColorant = (this.colorantToComponent[c] >= 0);
/* 231 */       int componentIndex = this.colorantToComponent[c];
/* 232 */       for (int y = 0; y < height; y++) {
/*     */         
/* 234 */         for (int x = 0; x < width; x++) {
/*     */           
/* 236 */           raster.getPixel(x, y, samples);
/* 237 */           if (isProcessColorant) {
/*     */ 
/*     */             
/* 240 */             componentSamples[componentIndex] = samples[c];
/*     */           
/*     */           }
/*     */           else {
/*     */             
/* 245 */             componentSamples[0] = samples[c];
/*     */           } 
/* 247 */           componentRaster.setPixel(x, y, componentSamples);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 252 */       BufferedImage rgbComponentImage = componentColorSpace.toRGBImage(componentRaster);
/* 253 */       WritableRaster rgbComponentRaster = rgbComponentImage.getRaster();
/*     */ 
/*     */       
/* 256 */       int[] rgbChannel = new int[3];
/* 257 */       int[] rgbComposite = new int[3];
/* 258 */       for (int i = 0; i < height; i++) {
/*     */         
/* 260 */         for (int x = 0; x < width; x++) {
/*     */           
/* 262 */           rgbComponentRaster.getPixel(x, i, rgbChannel);
/* 263 */           rgbRaster.getPixel(x, i, rgbComposite);
/*     */ 
/*     */           
/* 266 */           rgbChannel[0] = rgbChannel[0] * rgbComposite[0] >> 8;
/* 267 */           rgbChannel[1] = rgbChannel[1] * rgbComposite[1] >> 8;
/* 268 */           rgbChannel[2] = rgbChannel[2] * rgbComposite[2] >> 8;
/*     */           
/* 270 */           rgbRaster.setPixel(x, i, rgbChannel);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 275 */     return rgbImage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BufferedImage toRGBWithTintTransform(WritableRaster raster) throws IOException {
/* 284 */     Map<String, int[]> map1 = (Map)new HashMap<String, int>();
/* 285 */     String key = null;
/*     */     
/* 287 */     int width = raster.getWidth();
/* 288 */     int height = raster.getHeight();
/*     */ 
/*     */ 
/*     */     
/* 292 */     BufferedImage rgbImage = new BufferedImage(width, height, 1);
/* 293 */     WritableRaster rgbRaster = rgbImage.getRaster();
/* 294 */     int[] rgb = new int[3];
/* 295 */     int numSrcComponents = getColorantNames().size();
/* 296 */     float[] src = new float[numSrcComponents];
/* 297 */     for (int y = 0; y < height; y++) {
/*     */       
/* 299 */       for (int x = 0; x < width; x++) {
/*     */         
/* 301 */         raster.getPixel(x, y, src);
/*     */         
/* 303 */         key = Float.toString(src[0]);
/* 304 */         for (int s = 1; s < numSrcComponents; s++)
/*     */         {
/* 306 */           key = key + "#" + Float.toString(src[s]);
/*     */         }
/* 308 */         int[] pxl = map1.get(key);
/* 309 */         if (pxl != null) {
/*     */           
/* 311 */           rgbRaster.setPixel(x, y, pxl);
/*     */         }
/*     */         else {
/*     */           
/* 315 */           for (int i = 0; i < numSrcComponents; i++)
/*     */           {
/* 317 */             src[i] = src[i] / 255.0F;
/*     */           }
/*     */           
/* 320 */           float[] result = this.tintTransform.eval(src);
/*     */ 
/*     */           
/* 323 */           float[] rgbFloat = this.alternateColorSpace.toRGB(result);
/*     */           
/* 325 */           for (int j = 0; j < 3; j++)
/*     */           {
/*     */             
/* 328 */             rgb[j] = (int)(rgbFloat[j] * 255.0F);
/*     */           }
/*     */           
/* 331 */           map1.put(key, rgb.clone());
/*     */           
/* 333 */           rgbRaster.setPixel(x, y, rgb);
/*     */         } 
/*     */       } 
/* 336 */     }  return rgbImage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] toRGB(float[] value) throws IOException {
/* 342 */     if (this.attributes != null)
/*     */     {
/* 344 */       return toRGBWithAttributes(value);
/*     */     }
/*     */ 
/*     */     
/* 348 */     return toRGBWithTintTransform(value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private float[] toRGBWithAttributes(float[] value) throws IOException {
/* 354 */     float[] rgbValue = { 1.0F, 1.0F, 1.0F };
/*     */ 
/*     */     
/* 357 */     for (int c = 0; c < this.numColorants; c++) {
/*     */       PDColorSpace componentColorSpace;
/*     */       
/* 360 */       if (this.colorantToComponent[c] >= 0) {
/*     */ 
/*     */         
/* 363 */         componentColorSpace = this.processColorSpace;
/*     */       } else {
/* 365 */         if (this.spotColorSpaces[c] == null)
/*     */         {
/*     */ 
/*     */           
/* 369 */           return toRGBWithTintTransform(value);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 374 */         componentColorSpace = this.spotColorSpaces[c];
/*     */       } 
/*     */ 
/*     */       
/* 378 */       boolean isProcessColorant = (this.colorantToComponent[c] >= 0);
/* 379 */       float[] componentSamples = new float[componentColorSpace.getNumberOfComponents()];
/* 380 */       int componentIndex = this.colorantToComponent[c];
/*     */       
/* 382 */       if (isProcessColorant) {
/*     */ 
/*     */         
/* 385 */         componentSamples[componentIndex] = value[c];
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 390 */         componentSamples[0] = value[c];
/*     */       } 
/*     */ 
/*     */       
/* 394 */       float[] rgbComponent = componentColorSpace.toRGB(componentSamples);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 399 */       rgbValue[0] = rgbValue[0] * rgbComponent[0];
/* 400 */       rgbValue[1] = rgbValue[1] * rgbComponent[1];
/* 401 */       rgbValue[2] = rgbValue[2] * rgbComponent[2];
/*     */     } 
/*     */     
/* 404 */     return rgbValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float[] toRGBWithTintTransform(float[] value) throws IOException {
/* 411 */     float[] altValue = this.tintTransform.eval(value);
/*     */ 
/*     */     
/* 414 */     return this.alternateColorSpace.toRGB(altValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNChannel() {
/* 423 */     return (this.attributes != null && this.attributes.isNChannel());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 429 */     return COSName.DEVICEN.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getNumberOfComponents() {
/* 435 */     return getColorantNames().size();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getDefaultDecode(int bitsPerComponent) {
/* 441 */     int n = getNumberOfComponents();
/* 442 */     float[] decode = new float[n * 2];
/* 443 */     for (int i = 0; i < n; i++)
/*     */     {
/* 445 */       decode[i * 2 + 1] = 1.0F;
/*     */     }
/* 447 */     return decode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor getInitialColor() {
/* 453 */     return this.initialColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getColorantNames() {
/* 462 */     COSArray names = (COSArray)this.array.getObject(1);
/* 463 */     return COSArrayList.convertCOSNameCOSArrayToList(names);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDDeviceNAttributes getAttributes() {
/* 472 */     return this.attributes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorantNames(List<String> names) {
/* 481 */     COSArray namesArray = COSArrayList.convertStringListToCOSNameCOSArray(names);
/* 482 */     this.array.set(1, (COSBase)namesArray);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttributes(PDDeviceNAttributes attributes) {
/* 492 */     this.attributes = attributes;
/* 493 */     if (attributes == null) {
/*     */       
/* 495 */       this.array.remove(4);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 500 */       while (this.array.size() <= 4)
/*     */       {
/* 502 */         this.array.add((COSBase)COSNull.NULL);
/*     */       }
/* 504 */       this.array.set(4, (COSBase)attributes.getCOSDictionary());
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
/*     */ 
/*     */   
/*     */   public PDColorSpace getAlternateColorSpace() throws IOException {
/* 518 */     if (this.alternateColorSpace == null)
/*     */     {
/* 520 */       this.alternateColorSpace = PDColorSpace.create(this.array.getObject(2));
/*     */     }
/* 522 */     return this.alternateColorSpace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlternateColorSpace(PDColorSpace cs) {
/* 532 */     this.alternateColorSpace = cs;
/* 533 */     COSBase space = null;
/* 534 */     if (cs != null)
/*     */     {
/* 536 */       space = cs.getCOSObject();
/*     */     }
/* 538 */     this.array.set(2, space);
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
/*     */   public PDFunction getTintTransform() throws IOException {
/* 550 */     if (this.tintTransform == null)
/*     */     {
/* 552 */       this.tintTransform = PDFunction.create(this.array.getObject(3));
/*     */     }
/* 554 */     return this.tintTransform;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTintTransform(PDFunction tint) {
/* 564 */     this.tintTransform = tint;
/* 565 */     this.array.set(3, (COSObjectable)tint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 572 */     StringBuilder sb = new StringBuilder(getName());
/* 573 */     sb.append('{');
/* 574 */     for (String col : getColorantNames()) {
/*     */       
/* 576 */       sb.append('"');
/* 577 */       sb.append(col);
/* 578 */       sb.append("\" ");
/*     */     } 
/* 580 */     sb.append(this.alternateColorSpace.getName());
/* 581 */     sb.append(' ');
/* 582 */     sb.append(this.tintTransform);
/* 583 */     sb.append(' ');
/* 584 */     if (this.attributes != null)
/*     */     {
/* 586 */       sb.append(this.attributes);
/*     */     }
/* 588 */     sb.append('}');
/* 589 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDDeviceN.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */