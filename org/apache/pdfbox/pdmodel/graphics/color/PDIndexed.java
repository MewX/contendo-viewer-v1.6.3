/*     */ package org.apache.pdfbox.pdmodel.graphics.color;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSNull;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
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
/*     */ public final class PDIndexed
/*     */   extends PDSpecialColorSpace
/*     */ {
/*  45 */   private final PDColor initialColor = new PDColor(new float[] { 0.0F }, this);
/*     */   
/*  47 */   private PDColorSpace baseColorSpace = null;
/*     */ 
/*     */   
/*     */   private byte[] lookupData;
/*     */ 
/*     */   
/*     */   private float[][] colorTable;
/*     */   
/*     */   private int actualMaxIndex;
/*     */   
/*     */   private int[][] rgbColorTable;
/*     */ 
/*     */   
/*     */   public PDIndexed() {
/*  61 */     this.array = new COSArray();
/*  62 */     this.array.add((COSBase)COSName.INDEXED);
/*  63 */     this.array.add((COSBase)COSName.DEVICERGB);
/*  64 */     this.array.add((COSBase)COSInteger.get(255L));
/*  65 */     this.array.add((COSBase)COSNull.NULL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDIndexed(COSArray indexedArray) throws IOException {
/*  75 */     this(indexedArray, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDIndexed(COSArray indexedArray, PDResources resources) throws IOException {
/*  86 */     this.array = indexedArray;
/*     */ 
/*     */     
/*  89 */     this.baseColorSpace = PDColorSpace.create(this.array.get(1), resources);
/*  90 */     readColorTable();
/*  91 */     initRgbColorTable();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  97 */     return COSName.INDEXED.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfComponents() {
/* 103 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getDefaultDecode(int bitsPerComponent) {
/* 109 */     return new float[] { 0.0F, (float)Math.pow(2.0D, bitsPerComponent) - 1.0F };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor getInitialColor() {
/* 115 */     return this.initialColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initRgbColorTable() throws IOException {
/*     */     WritableRaster baseRaster;
/* 123 */     int numBaseComponents = this.baseColorSpace.getNumberOfComponents();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 130 */       baseRaster = Raster.createBandedRaster(0, this.actualMaxIndex + 1, 1, numBaseComponents, new Point(0, 0));
/*     */     
/*     */     }
/* 133 */     catch (IllegalArgumentException ex) {
/*     */ 
/*     */       
/* 136 */       throw new IOException(ex);
/*     */     } 
/*     */     
/* 139 */     int[] base = new int[numBaseComponents];
/* 140 */     for (int i = 0, n = this.actualMaxIndex; i <= n; i++) {
/*     */       
/* 142 */       for (int c = 0; c < numBaseComponents; c++)
/*     */       {
/* 144 */         base[c] = (int)(this.colorTable[i][c] * 255.0F);
/*     */       }
/* 146 */       baseRaster.setPixel(i, 0, base);
/*     */     } 
/*     */ 
/*     */     
/* 150 */     BufferedImage rgbImage = this.baseColorSpace.toRGBImage(baseRaster);
/* 151 */     WritableRaster rgbRaster = rgbImage.getRaster();
/*     */ 
/*     */     
/* 154 */     this.rgbColorTable = new int[this.actualMaxIndex + 1][3];
/* 155 */     int[] nil = null;
/*     */     
/* 157 */     for (int j = 0, k = this.actualMaxIndex; j <= k; j++)
/*     */     {
/* 159 */       this.rgbColorTable[j] = rgbRaster.getPixel(j, 0, nil);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] toRGB(float[] value) {
/* 169 */     if (value.length > 1)
/*     */     {
/* 171 */       throw new IllegalArgumentException("Indexed color spaces must have one color value");
/*     */     }
/*     */ 
/*     */     
/* 175 */     int index = Math.round(value[0]);
/* 176 */     index = Math.max(index, 0);
/* 177 */     index = Math.min(index, this.actualMaxIndex);
/*     */ 
/*     */     
/* 180 */     int[] rgb = this.rgbColorTable[index];
/* 181 */     return new float[] { rgb[0] / 255.0F, rgb[1] / 255.0F, rgb[2] / 255.0F };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage toRGBImage(WritableRaster raster) throws IOException {
/* 191 */     int width = raster.getWidth();
/* 192 */     int height = raster.getHeight();
/*     */     
/* 194 */     BufferedImage rgbImage = new BufferedImage(width, height, 1);
/* 195 */     WritableRaster rgbRaster = rgbImage.getRaster();
/*     */     
/* 197 */     int[] src = new int[1];
/* 198 */     for (int y = 0; y < height; y++) {
/*     */       
/* 200 */       for (int x = 0; x < width; x++) {
/*     */         
/* 202 */         raster.getPixel(x, y, src);
/*     */ 
/*     */         
/* 205 */         int index = Math.min(src[0], this.actualMaxIndex);
/* 206 */         rgbRaster.setPixel(x, y, this.rgbColorTable[index]);
/*     */       } 
/*     */     } 
/*     */     
/* 210 */     return rgbImage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColorSpace getBaseColorSpace() {
/* 219 */     return this.baseColorSpace;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int getHival() {
/* 225 */     return ((COSNumber)this.array.getObject(2)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] getLookupData() throws IOException {
/* 231 */     if (this.lookupData == null) {
/*     */       
/* 233 */       COSBase lookupTable = this.array.getObject(3);
/* 234 */       if (lookupTable instanceof COSString) {
/*     */         
/* 236 */         this.lookupData = ((COSString)lookupTable).getBytes();
/*     */       }
/* 238 */       else if (lookupTable instanceof COSStream) {
/*     */         
/* 240 */         this.lookupData = (new PDStream((COSStream)lookupTable)).toByteArray();
/*     */       }
/* 242 */       else if (lookupTable == null) {
/*     */         
/* 244 */         this.lookupData = new byte[0];
/*     */       }
/*     */       else {
/*     */         
/* 248 */         throw new IOException("Error: Unknown type for lookup table " + lookupTable);
/*     */       } 
/*     */     } 
/* 251 */     return this.lookupData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readColorTable() throws IOException {
/* 259 */     byte[] lookupData = getLookupData();
/* 260 */     int maxIndex = Math.min(getHival(), 255);
/* 261 */     int numComponents = this.baseColorSpace.getNumberOfComponents();
/*     */ 
/*     */     
/* 264 */     if (lookupData.length / numComponents < maxIndex + 1)
/*     */     {
/* 266 */       maxIndex = lookupData.length / numComponents - 1;
/*     */     }
/* 268 */     this.actualMaxIndex = maxIndex;
/*     */     
/* 270 */     this.colorTable = new float[maxIndex + 1][numComponents];
/* 271 */     for (int i = 0, offset = 0; i <= maxIndex; i++) {
/*     */       
/* 273 */       for (int c = 0; c < numComponents; c++) {
/*     */         
/* 275 */         this.colorTable[i][c] = (lookupData[offset] & 0xFF) / 255.0F;
/* 276 */         offset++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBaseColorSpace(PDColorSpace base) {
/* 287 */     this.array.set(1, base.getCOSObject());
/* 288 */     this.baseColorSpace = base;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHighValue(int high) {
/* 297 */     this.array.set(2, high);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 303 */     return "Indexed{base:" + this.baseColorSpace + " hival:" + 
/* 304 */       getHival() + " lookup:(" + this.colorTable.length + " entries)}";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDIndexed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */