/*     */ package org.apache.pdfbox.pdmodel.graphics.color;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.color.CMMException;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.color.ICC_ColorSpace;
/*     */ import java.awt.color.ICC_Profile;
/*     */ import java.awt.color.ProfileDataException;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSInputStream;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSObject;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.common.COSArrayList;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRange;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
/*     */ import org.apache.pdfbox.util.Charsets;
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
/*     */ public final class PDICCBased
/*     */   extends PDCIEBasedColorSpace
/*     */ {
/*  60 */   private static final Log LOG = LogFactory.getLog(PDICCBased.class);
/*     */   
/*     */   private final PDStream stream;
/*  63 */   private int numberOfComponents = -1;
/*     */   
/*     */   private ICC_Profile iccProfile;
/*     */   
/*     */   private PDColorSpace alternateColorSpace;
/*     */   
/*     */   private ICC_ColorSpace awtColorSpace;
/*     */   
/*     */   private PDColor initialColor;
/*     */   private boolean isRGB = false;
/*     */   private boolean useOnlyAlternateColorSpace = false;
/*     */   private static final boolean IS_KCMS;
/*     */   
/*     */   static {
/*  77 */     String cmmProperty = System.getProperty("sun.java2d.cmm");
/*  78 */     IS_KCMS = (!isMinJdk8() || "sun.java2d.cmm.kcms.KcmsServiceProvider".equals(cmmProperty));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDICCBased(PDDocument doc) {
/*  87 */     this.array = new COSArray();
/*  88 */     this.array.add((COSBase)COSName.ICCBASED);
/*  89 */     this.stream = new PDStream(doc);
/*  90 */     this.array.add((COSObjectable)this.stream);
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
/*     */   @Deprecated
/*     */   public PDICCBased(COSArray iccArray) throws IOException {
/* 107 */     checkArray(iccArray);
/* 108 */     this
/* 109 */       .useOnlyAlternateColorSpace = (System.getProperty("org.apache.pdfbox.rendering.UseAlternateInsteadOfICCColorSpace") != null);
/* 110 */     this.array = iccArray;
/* 111 */     this.stream = new PDStream((COSStream)iccArray.getObject(1));
/* 112 */     loadICCProfile();
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
/*     */   public static PDICCBased create(COSArray iccArray, PDResources resources) throws IOException {
/* 126 */     checkArray(iccArray);
/* 127 */     COSBase base = iccArray.get(1);
/* 128 */     COSObject indirect = null;
/* 129 */     if (base instanceof COSObject)
/*     */     {
/* 131 */       indirect = (COSObject)base;
/*     */     }
/* 133 */     if (indirect != null && resources != null && resources.getResourceCache() != null) {
/*     */       
/* 135 */       PDColorSpace pDColorSpace = resources.getResourceCache().getColorSpace(indirect);
/* 136 */       if (pDColorSpace != null && pDColorSpace instanceof PDICCBased)
/*     */       {
/* 138 */         return (PDICCBased)pDColorSpace;
/*     */       }
/*     */     } 
/* 141 */     PDICCBased space = new PDICCBased(iccArray);
/* 142 */     if (indirect != null && resources != null && resources.getResourceCache() != null)
/*     */     {
/* 144 */       resources.getResourceCache().put(indirect, space);
/*     */     }
/* 146 */     return space;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void checkArray(COSArray iccArray) throws IOException {
/* 151 */     if (iccArray.size() < 2)
/*     */     {
/* 153 */       throw new IOException("ICCBased colorspace array must have two elements");
/*     */     }
/* 155 */     if (!(iccArray.getObject(1) instanceof COSStream))
/*     */     {
/* 157 */       throw new IOException("ICCBased colorspace array must have a stream as second element");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 164 */     return COSName.ICCBASED.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDStream getPDStream() {
/* 173 */     return this.stream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void loadICCProfile() throws IOException {
/*     */     COSInputStream cOSInputStream;
/* 181 */     if (this.useOnlyAlternateColorSpace) {
/*     */       
/*     */       try {
/*     */         
/* 185 */         fallbackToAlternateColorSpace((Exception)null);
/*     */         
/*     */         return;
/* 188 */       } catch (IOException e) {
/*     */         
/* 190 */         LOG.warn("Error initializing alternate color space: " + e.getLocalizedMessage());
/*     */       } 
/*     */     }
/* 193 */     InputStream input = null;
/*     */     
/*     */     try {
/* 196 */       cOSInputStream = this.stream.createInputStream();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 201 */       synchronized (LOG)
/*     */       {
/* 203 */         ICC_Profile profile = ICC_Profile.getInstance((InputStream)cOSInputStream);
/* 204 */         if (is_sRGB(profile)) {
/*     */           
/* 206 */           this.isRGB = true;
/* 207 */           this.awtColorSpace = (ICC_ColorSpace)ColorSpace.getInstance(1000);
/* 208 */           this.iccProfile = this.awtColorSpace.getProfile();
/*     */         }
/*     */         else {
/*     */           
/* 212 */           profile = ensureDisplayProfile(profile);
/* 213 */           this.awtColorSpace = new ICC_ColorSpace(profile);
/* 214 */           this.iccProfile = profile;
/*     */         } 
/*     */ 
/*     */         
/* 218 */         float[] initial = new float[getNumberOfComponents()];
/* 219 */         for (int c = 0; c < getNumberOfComponents(); c++)
/*     */         {
/* 221 */           initial[c] = Math.max(0.0F, getRangeForComponent(c).getMin());
/*     */         }
/* 223 */         this.initialColor = new PDColor(initial, this);
/*     */         
/* 225 */         if (IS_KCMS)
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 231 */           new Color(this.awtColorSpace, new float[getNumberOfComponents()], 1.0F);
/*     */         
/*     */         }
/*     */         else
/*     */         {
/* 236 */           new ComponentColorModel(this.awtColorSpace, false, false, 1, 0);
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 241 */     } catch (ProfileDataException e) {
/*     */       
/* 243 */       fallbackToAlternateColorSpace(e);
/*     */     }
/* 245 */     catch (CMMException e) {
/*     */       
/* 247 */       fallbackToAlternateColorSpace(e);
/*     */     }
/* 249 */     catch (IllegalArgumentException e) {
/*     */       
/* 251 */       fallbackToAlternateColorSpace(e);
/*     */     }
/* 253 */     catch (ArrayIndexOutOfBoundsException e) {
/*     */       
/* 255 */       fallbackToAlternateColorSpace(e);
/*     */     }
/* 257 */     catch (IOException e) {
/*     */       
/* 259 */       fallbackToAlternateColorSpace(e);
/*     */     }
/*     */     finally {
/*     */       
/* 263 */       IOUtils.closeQuietly((Closeable)cOSInputStream);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void fallbackToAlternateColorSpace(Exception e) throws IOException {
/* 269 */     this.awtColorSpace = null;
/* 270 */     this.alternateColorSpace = getAlternateColorSpace();
/* 271 */     if (this.alternateColorSpace.equals(PDDeviceRGB.INSTANCE))
/*     */     {
/* 273 */       this.isRGB = true;
/*     */     }
/* 275 */     if (e != null)
/*     */     {
/* 277 */       LOG.warn("Can't read embedded ICC profile (" + e.getLocalizedMessage() + "), using alternate color space: " + this.alternateColorSpace
/* 278 */           .getName());
/*     */     }
/* 280 */     this.initialColor = this.alternateColorSpace.getInitialColor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean is_sRGB(ICC_Profile profile) {
/* 288 */     byte[] bytes = Arrays.copyOfRange(profile.getData(1751474532), 52, 59);
/*     */     
/* 290 */     String deviceModel = (new String(bytes, Charsets.US_ASCII)).trim();
/* 291 */     return deviceModel.equals("sRGB");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static ICC_Profile ensureDisplayProfile(ICC_Profile profile) {
/* 298 */     if (profile.getProfileClass() != 1) {
/*     */       
/* 300 */       byte[] profileData = profile.getData();
/*     */       
/* 302 */       if (profileData[64] == 0) {
/*     */         
/* 304 */         LOG.warn("ICC profile is Perceptual, ignoring, treating as Display class");
/* 305 */         intToBigEndian(1835955314, profileData, 12);
/* 306 */         return ICC_Profile.getInstance(profileData);
/*     */       } 
/*     */     } 
/* 309 */     return profile;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void intToBigEndian(int value, byte[] array, int index) {
/* 314 */     array[index] = (byte)(value >> 24);
/* 315 */     array[index + 1] = (byte)(value >> 16);
/* 316 */     array[index + 2] = (byte)(value >> 8);
/* 317 */     array[index + 3] = (byte)value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] toRGB(float[] value) throws IOException {
/* 323 */     if (this.isRGB)
/*     */     {
/* 325 */       return value;
/*     */     }
/* 327 */     if (this.awtColorSpace != null)
/*     */     {
/*     */ 
/*     */       
/* 331 */       return this.awtColorSpace.toRGB(clampColors(this.awtColorSpace, value));
/*     */     }
/*     */ 
/*     */     
/* 335 */     return this.alternateColorSpace.toRGB(value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private float[] clampColors(ICC_ColorSpace cs, float[] value) {
/* 341 */     float[] result = new float[value.length];
/* 342 */     for (int i = 0; i < value.length; i++) {
/*     */       
/* 344 */       float minValue = cs.getMinValue(i);
/* 345 */       float maxValue = cs.getMaxValue(i);
/* 346 */       result[i] = (value[i] < minValue) ? minValue : ((value[i] > maxValue) ? maxValue : value[i]);
/*     */     } 
/* 348 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage toRGBImage(WritableRaster raster) throws IOException {
/* 354 */     if (this.awtColorSpace != null)
/*     */     {
/* 356 */       return toRGBImageAWT(raster, this.awtColorSpace);
/*     */     }
/*     */ 
/*     */     
/* 360 */     return this.alternateColorSpace.toRGBImage(raster);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfComponents() {
/* 367 */     if (this.numberOfComponents < 0)
/*     */     {
/* 369 */       this.numberOfComponents = this.stream.getCOSObject().getInt(COSName.N);
/*     */     }
/* 371 */     return this.numberOfComponents;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getDefaultDecode(int bitsPerComponent) {
/* 377 */     if (this.awtColorSpace != null) {
/*     */       
/* 379 */       int n = getNumberOfComponents();
/* 380 */       float[] decode = new float[n * 2];
/* 381 */       for (int i = 0; i < n; i++) {
/*     */         
/* 383 */         decode[i * 2] = this.awtColorSpace.getMinValue(i);
/* 384 */         decode[i * 2 + 1] = this.awtColorSpace.getMaxValue(i);
/*     */       } 
/* 386 */       return decode;
/*     */     } 
/*     */ 
/*     */     
/* 390 */     return this.alternateColorSpace.getDefaultDecode(bitsPerComponent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor getInitialColor() {
/* 397 */     return this.initialColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColorSpace getAlternateColorSpace() throws IOException {
/*     */     COSArray alternateArray;
/* 408 */     COSBase alternate = this.stream.getCOSObject().getDictionaryObject(COSName.ALTERNATE);
/*     */     
/* 410 */     if (alternate == null) {
/*     */       COSName csName;
/* 412 */       alternateArray = new COSArray();
/* 413 */       int numComponents = getNumberOfComponents();
/*     */       
/* 415 */       switch (numComponents) {
/*     */         
/*     */         case 1:
/* 418 */           csName = COSName.DEVICEGRAY;
/*     */           break;
/*     */         case 3:
/* 421 */           csName = COSName.DEVICERGB;
/*     */           break;
/*     */         case 4:
/* 424 */           csName = COSName.DEVICECMYK;
/*     */           break;
/*     */         default:
/* 427 */           throw new IOException("Unknown color space number of components:" + numComponents);
/*     */       } 
/* 429 */       alternateArray.add((COSBase)csName);
/*     */ 
/*     */     
/*     */     }
/* 433 */     else if (alternate instanceof COSArray) {
/*     */       
/* 435 */       alternateArray = (COSArray)alternate;
/*     */     }
/* 437 */     else if (alternate instanceof COSName) {
/*     */       
/* 439 */       alternateArray = new COSArray();
/* 440 */       alternateArray.add(alternate);
/*     */     }
/*     */     else {
/*     */       
/* 444 */       throw new IOException("Error: expected COSArray or COSName and not " + alternate
/* 445 */           .getClass().getName());
/*     */     } 
/*     */     
/* 448 */     return PDColorSpace.create((COSBase)alternateArray);
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
/*     */   public PDRange getRangeForComponent(int n) {
/* 460 */     COSArray rangeArray = (COSArray)this.stream.getCOSObject().getDictionaryObject(COSName.RANGE);
/* 461 */     if (rangeArray == null || rangeArray.size() < getNumberOfComponents() * 2)
/*     */     {
/* 463 */       return new PDRange();
/*     */     }
/* 465 */     return new PDRange(rangeArray, n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSStream getMetadata() {
/* 474 */     return (COSStream)this.stream.getCOSObject().getDictionaryObject(COSName.METADATA);
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
/*     */   public int getColorSpaceType() {
/* 488 */     if (this.iccProfile != null)
/*     */     {
/* 490 */       return this.iccProfile.getColorSpaceType();
/*     */     }
/*     */ 
/*     */     
/* 494 */     switch (this.alternateColorSpace.getNumberOfComponents()) {
/*     */       
/*     */       case 1:
/* 497 */         return 6;
/*     */       case 3:
/* 499 */         return 5;
/*     */       case 4:
/* 501 */         return 9;
/*     */     } 
/*     */     
/* 504 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setNumberOfComponents(int n) {
/* 516 */     this.numberOfComponents = n;
/* 517 */     this.stream.getCOSObject().setInt(COSName.N, n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlternateColorSpaces(List<PDColorSpace> list) {
/* 527 */     COSArray altArray = null;
/* 528 */     if (list != null)
/*     */     {
/* 530 */       altArray = COSArrayList.converterToCOSArray(list);
/*     */     }
/* 532 */     this.stream.getCOSObject().setItem(COSName.ALTERNATE, (COSBase)altArray);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRangeForComponent(PDRange range, int n) {
/* 542 */     COSArray rangeArray = (COSArray)this.stream.getCOSObject().getDictionaryObject(COSName.RANGE);
/* 543 */     if (rangeArray == null) {
/*     */       
/* 545 */       rangeArray = new COSArray();
/* 546 */       this.stream.getCOSObject().setItem(COSName.RANGE, (COSBase)rangeArray);
/*     */     } 
/*     */     
/* 549 */     while (rangeArray.size() < (n + 1) * 2) {
/*     */       
/* 551 */       rangeArray.add((COSBase)new COSFloat(0.0F));
/* 552 */       rangeArray.add((COSBase)new COSFloat(1.0F));
/*     */     } 
/* 554 */     rangeArray.set(n * 2, (COSBase)new COSFloat(range.getMin()));
/* 555 */     rangeArray.set(n * 2 + 1, (COSBase)new COSFloat(range.getMax()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMetadata(COSStream metadata) {
/* 564 */     this.stream.getCOSObject().setItem(COSName.METADATA, (COSBase)metadata);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 570 */     return getName() + "{numberOfComponents: " + getNumberOfComponents() + "}";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isMinJdk8() {
/* 576 */     String version = System.getProperty("java.specification.version");
/* 577 */     StringTokenizer st = new StringTokenizer(version, ".");
/*     */     
/*     */     try {
/* 580 */       int major = Integer.parseInt(st.nextToken());
/* 581 */       int minor = 0;
/* 582 */       if (st.hasMoreTokens())
/*     */       {
/* 584 */         minor = Integer.parseInt(st.nextToken());
/*     */       }
/* 586 */       return (major > 1 || (major == 1 && minor >= 8));
/*     */     }
/* 588 */     catch (NumberFormatException nfe) {
/*     */ 
/*     */       
/* 591 */       return true;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDICCBased.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */