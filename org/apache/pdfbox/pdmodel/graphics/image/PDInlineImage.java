/*     */ package org.apache.pdfbox.pdmodel.graphics.image;
/*     */ 
/*     */ import java.awt.Paint;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.filter.DecodeOptions;
/*     */ import org.apache.pdfbox.filter.DecodeResult;
/*     */ import org.apache.pdfbox.filter.Filter;
/*     */ import org.apache.pdfbox.filter.FilterFactory;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.common.COSArrayList;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PDInlineImage
/*     */   implements PDImage
/*     */ {
/*     */   private final COSDictionary parameters;
/*     */   private final PDResources resources;
/*     */   private final byte[] rawData;
/*     */   private final byte[] decodedData;
/*     */   
/*     */   public PDInlineImage(COSDictionary parameters, byte[] data, PDResources resources) throws IOException {
/*  70 */     this.parameters = parameters;
/*  71 */     this.resources = resources;
/*  72 */     this.rawData = data;
/*     */     
/*  74 */     DecodeResult decodeResult = null;
/*  75 */     List<String> filters = getFilters();
/*  76 */     if (filters == null || filters.isEmpty()) {
/*     */       
/*  78 */       this.decodedData = data;
/*     */     }
/*     */     else {
/*     */       
/*  82 */       ByteArrayInputStream in = new ByteArrayInputStream(data);
/*  83 */       ByteArrayOutputStream out = new ByteArrayOutputStream(data.length);
/*  84 */       for (int i = 0; i < filters.size(); i++) {
/*     */ 
/*     */         
/*  87 */         out.reset();
/*  88 */         Filter filter = FilterFactory.INSTANCE.getFilter(filters.get(i));
/*  89 */         decodeResult = filter.decode(in, out, parameters, i);
/*  90 */         in = new ByteArrayInputStream(out.toByteArray());
/*     */       } 
/*  92 */       this.decodedData = out.toByteArray();
/*     */     } 
/*     */ 
/*     */     
/*  96 */     if (decodeResult != null)
/*     */     {
/*  98 */       parameters.addAll(decodeResult.getParameters());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/* 105 */     return (COSBase)this.parameters;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBitsPerComponent() {
/* 111 */     if (isStencil())
/*     */     {
/* 113 */       return 1;
/*     */     }
/*     */ 
/*     */     
/* 117 */     return this.parameters.getInt(COSName.BPC, COSName.BITS_PER_COMPONENT, -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBitsPerComponent(int bitsPerComponent) {
/* 124 */     this.parameters.setInt(COSName.BPC, bitsPerComponent);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColorSpace getColorSpace() throws IOException {
/* 130 */     COSBase cs = this.parameters.getDictionaryObject(COSName.CS, COSName.COLORSPACE);
/* 131 */     if (cs != null)
/*     */     {
/* 133 */       return createColorSpace(cs);
/*     */     }
/* 135 */     if (isStencil())
/*     */     {
/*     */       
/* 138 */       return (PDColorSpace)PDDeviceGray.INSTANCE;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 143 */     throw new IOException("could not determine inline image color space");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private COSBase toLongName(COSBase cs) {
/* 150 */     if (COSName.RGB.equals(cs))
/*     */     {
/* 152 */       return (COSBase)COSName.DEVICERGB;
/*     */     }
/* 154 */     if (COSName.CMYK.equals(cs))
/*     */     {
/* 156 */       return (COSBase)COSName.DEVICECMYK;
/*     */     }
/* 158 */     if (COSName.G.equals(cs))
/*     */     {
/* 160 */       return (COSBase)COSName.DEVICEGRAY;
/*     */     }
/* 162 */     return cs;
/*     */   }
/*     */ 
/*     */   
/*     */   private PDColorSpace createColorSpace(COSBase cs) throws IOException {
/* 167 */     if (cs instanceof COSName)
/*     */     {
/* 169 */       return PDColorSpace.create(toLongName(cs), this.resources);
/*     */     }
/*     */     
/* 172 */     if (cs instanceof COSArray && ((COSArray)cs).size() > 1) {
/*     */       
/* 174 */       COSArray srcArray = (COSArray)cs;
/* 175 */       COSBase csType = srcArray.get(0);
/* 176 */       if (COSName.I.equals(csType) || COSName.INDEXED.equals(csType)) {
/*     */         
/* 178 */         COSArray dstArray = new COSArray();
/* 179 */         dstArray.addAll(srcArray);
/* 180 */         dstArray.set(0, (COSBase)COSName.INDEXED);
/* 181 */         dstArray.set(1, toLongName(srcArray.get(1)));
/* 182 */         return PDColorSpace.create((COSBase)dstArray, this.resources);
/*     */       } 
/*     */       
/* 185 */       throw new IOException("Illegal type of inline image color space: " + csType);
/*     */     } 
/*     */     
/* 188 */     throw new IOException("Illegal type of object for inline image color space: " + cs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorSpace(PDColorSpace colorSpace) {
/* 194 */     COSBase base = null;
/* 195 */     if (colorSpace != null)
/*     */     {
/* 197 */       base = colorSpace.getCOSObject();
/*     */     }
/* 199 */     this.parameters.setItem(COSName.CS, base);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 205 */     return this.parameters.getInt(COSName.H, COSName.HEIGHT, -1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHeight(int height) {
/* 211 */     this.parameters.setInt(COSName.H, height);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 217 */     return this.parameters.getInt(COSName.W, COSName.WIDTH, -1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWidth(int width) {
/* 223 */     this.parameters.setInt(COSName.W, width);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getInterpolate() {
/* 229 */     return this.parameters.getBoolean(COSName.I, COSName.INTERPOLATE, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInterpolate(boolean value) {
/* 235 */     this.parameters.setBoolean(COSName.I, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getFilters() {
/* 246 */     List<String> names = null;
/* 247 */     COSBase filters = this.parameters.getDictionaryObject(COSName.F, COSName.FILTER);
/* 248 */     if (filters instanceof COSName) {
/*     */       
/* 250 */       COSName name = (COSName)filters;
/* 251 */       COSArrayList cOSArrayList = new COSArrayList(name.getName(), (COSBase)name, this.parameters, COSName.FILTER);
/*     */     }
/* 253 */     else if (filters instanceof COSArray) {
/*     */       
/* 255 */       names = COSArrayList.convertCOSNameCOSArrayToList((COSArray)filters);
/*     */     } 
/* 257 */     return names;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilters(List<String> filters) {
/* 267 */     COSArray cOSArray = COSArrayList.convertStringListToCOSNameCOSArray(filters);
/* 268 */     this.parameters.setItem(COSName.F, (COSBase)cOSArray);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDecode(COSArray decode) {
/* 274 */     this.parameters.setItem(COSName.D, (COSBase)decode);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getDecode() {
/* 280 */     return (COSArray)this.parameters.getDictionaryObject(COSName.D, COSName.DECODE);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStencil() {
/* 286 */     return this.parameters.getBoolean(COSName.IM, COSName.IMAGE_MASK, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStencil(boolean isStencil) {
/* 292 */     this.parameters.setBoolean(COSName.IM, isStencil);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream createInputStream() throws IOException {
/* 298 */     return new ByteArrayInputStream(this.decodedData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream createInputStream(DecodeOptions options) throws IOException {
/* 305 */     return createInputStream();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream createInputStream(List<String> stopFilters) throws IOException {
/* 311 */     List<String> filters = getFilters();
/* 312 */     ByteArrayInputStream in = new ByteArrayInputStream(this.rawData);
/* 313 */     ByteArrayOutputStream out = new ByteArrayOutputStream(this.rawData.length);
/* 314 */     for (int i = 0; filters != null && i < filters.size(); i++) {
/*     */ 
/*     */       
/* 317 */       out.reset();
/* 318 */       if (stopFilters.contains(filters.get(i))) {
/*     */         break;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 324 */       Filter filter = FilterFactory.INSTANCE.getFilter(filters.get(i));
/* 325 */       filter.decode(in, out, this.parameters, i);
/* 326 */       in = new ByteArrayInputStream(out.toByteArray());
/*     */     } 
/*     */     
/* 329 */     return new ByteArrayInputStream(out.toByteArray());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 335 */     return (this.decodedData.length == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getData() {
/* 343 */     return this.decodedData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getImage() throws IOException {
/* 349 */     return SampledImageReader.getRGBImage(this, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getImage(Rectangle region, int subsampling) throws IOException {
/* 355 */     return SampledImageReader.getRGBImage(this, region, subsampling, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getStencilImage(Paint paint) throws IOException {
/* 361 */     if (!isStencil())
/*     */     {
/* 363 */       throw new IllegalStateException("Image is not a stencil");
/*     */     }
/* 365 */     return SampledImageReader.getStencilImage(this, paint);
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
/*     */   @Deprecated
/*     */   public COSArray getColorKeyMask() {
/* 378 */     COSBase mask = this.parameters.getDictionaryObject(COSName.IM, COSName.MASK);
/* 379 */     if (mask instanceof COSArray)
/*     */     {
/* 381 */       return (COSArray)mask;
/*     */     }
/* 383 */     return null;
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
/*     */   public String getSuffix() {
/* 395 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/image/PDInlineImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */