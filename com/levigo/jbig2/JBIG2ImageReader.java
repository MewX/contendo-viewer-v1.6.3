/*     */ package com.levigo.jbig2;
/*     */ 
/*     */ import com.levigo.jbig2.err.JBIG2Exception;
/*     */ import com.levigo.jbig2.image.Bitmaps;
/*     */ import com.levigo.jbig2.image.FilterType;
/*     */ import com.levigo.jbig2.util.cache.CacheFactory;
/*     */ import com.levigo.jbig2.util.log.Logger;
/*     */ import com.levigo.jbig2.util.log.LoggerFactory;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.Raster;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import javax.imageio.ImageReadParam;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.spi.ImageReaderSpi;
/*     */ import javax.imageio.stream.ImageInputStream;
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
/*     */ public class JBIG2ImageReader
/*     */   extends ImageReader
/*     */ {
/*  45 */   private static final Logger log = LoggerFactory.getLogger(JBIG2ImageReader.class);
/*     */ 
/*     */ 
/*     */   
/*     */   public static final boolean DEBUG = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final boolean PERFORMANCE_TEST = false;
/*     */ 
/*     */   
/*     */   private JBIG2Document document;
/*     */ 
/*     */   
/*     */   private JBIG2Globals globals;
/*     */ 
/*     */ 
/*     */   
/*     */   public JBIG2ImageReader(ImageReaderSpi paramImageReaderSpi) throws IOException {
/*  64 */     super(paramImageReaderSpi);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JBIG2ReadParam getDefaultReadParam() {
/*  72 */     return new JBIG2ReadParam();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JBIG2ReadParam getDefaultReadParam(int paramInt) {
/*  82 */     int i = 1;
/*  83 */     int j = 1;
/*     */     
/*     */     try {
/*  86 */       boolean bool = (paramInt < getDocument().getAmountOfPages()) ? paramInt : false;
/*  87 */       i = getWidth(bool);
/*  88 */       j = getHeight(bool);
/*  89 */     } catch (IOException iOException) {
/*  90 */       if (log.isInfoEnabled()) {
/*  91 */         log.info("Dimensions could not be determined. Returning read params with size " + i + "x" + j);
/*     */       }
/*     */     } 
/*     */     
/*  95 */     return new JBIG2ReadParam(1, 1, 0, 0, new Rectangle(0, 0, i, j), new Dimension(i, j));
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
/*     */   public int getWidth(int paramInt) throws IOException {
/* 109 */     return getDocument().getPage(paramInt + 1).getWidth();
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
/*     */   public int getHeight(int paramInt) throws IOException {
/*     */     try {
/* 124 */       return getDocument().getPage(paramInt + 1).getHeight();
/* 125 */     } catch (JBIG2Exception jBIG2Exception) {
/* 126 */       throw new IOException(jBIG2Exception.getMessage());
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
/*     */   public IIOMetadata getImageMetadata(int paramInt) throws IOException {
/* 139 */     return new JBIG2ImageMetadata(getDocument().getPage(paramInt + 1));
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
/*     */   public Iterator<ImageTypeSpecifier> getImageTypes(int paramInt) throws IOException {
/* 154 */     ArrayList<ImageTypeSpecifier> arrayList = new ArrayList();
/*     */     
/* 156 */     arrayList.add(ImageTypeSpecifier.createFromBufferedImageType(13));
/*     */     
/* 158 */     return arrayList.iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumImages(boolean paramBoolean) throws IOException {
/* 166 */     if (paramBoolean) {
/* 167 */       if (getDocument().isAmountOfPagesUnknown()) {
/* 168 */         log.info("Amount of pages is unknown.");
/*     */       } else {
/* 170 */         return getDocument().getAmountOfPages();
/*     */       } 
/*     */     } else {
/* 173 */       log.info("Search is not allowed.");
/*     */     } 
/* 175 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIOMetadata getStreamMetadata() {
/* 185 */     log.info("No metadata recorded");
/* 186 */     return null;
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
/*     */   public JBIG2Globals getGlobals() throws IOException {
/* 198 */     return getDocument().getGlobalSegments();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage read(int paramInt, ImageReadParam paramImageReadParam) throws IOException {
/* 208 */     if (paramImageReadParam == null) {
/* 209 */       log.info("JBIG2ReadParam not specified. Default will be used.");
/* 210 */       paramImageReadParam = getDefaultReadParam(paramInt);
/*     */     } 
/*     */     
/* 213 */     JBIG2Page jBIG2Page = getPage(paramInt);
/*     */     
/* 215 */     Bitmap bitmap = (Bitmap)CacheFactory.getCache().get(jBIG2Page);
/*     */     
/* 217 */     if (bitmap == null) {
/*     */       try {
/* 219 */         bitmap = jBIG2Page.getBitmap();
/* 220 */         CacheFactory.getCache().put(jBIG2Page, bitmap, bitmap.getMemorySize());
/* 221 */         jBIG2Page.clearPageData();
/* 222 */       } catch (JBIG2Exception jBIG2Exception) {
/* 223 */         throw new IOException(jBIG2Exception.getMessage());
/*     */       } 
/*     */     }
/*     */     
/* 227 */     return Bitmaps.asBufferedImage(bitmap, paramImageReadParam, FilterType.Gaussian);
/*     */   }
/*     */   
/*     */   public boolean canReadRaster() {
/* 231 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Raster readRaster(int paramInt, ImageReadParam paramImageReadParam) throws IOException {
/* 236 */     if (paramImageReadParam == null) {
/* 237 */       log.info("JBIG2ReadParam not specified. Default will be used.");
/* 238 */       paramImageReadParam = getDefaultReadParam(paramInt);
/*     */     } 
/*     */     
/* 241 */     JBIG2Page jBIG2Page = getPage(paramInt);
/*     */     
/* 243 */     Bitmap bitmap = (Bitmap)CacheFactory.getCache().get(jBIG2Page);
/* 244 */     if (bitmap == null) {
/*     */       try {
/* 246 */         bitmap = jBIG2Page.getBitmap();
/* 247 */         CacheFactory.getCache().put(jBIG2Page, bitmap, bitmap.getMemorySize());
/* 248 */         jBIG2Page.clearPageData();
/* 249 */       } catch (JBIG2Exception jBIG2Exception) {
/* 250 */         throw new IOException(jBIG2Exception.getMessage());
/*     */       } 
/*     */     }
/*     */     
/* 254 */     return Bitmaps.asRaster(bitmap, paramImageReadParam, FilterType.Gaussian);
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
/*     */   public JBIG2Globals processGlobals(ImageInputStream paramImageInputStream) throws IOException {
/* 267 */     JBIG2Document jBIG2Document = new JBIG2Document(paramImageInputStream);
/* 268 */     return jBIG2Document.getGlobalSegments();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGlobals(JBIG2Globals paramJBIG2Globals) throws IOException {
/* 278 */     this.globals = paramJBIG2Globals;
/* 279 */     this.document = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInput(Object paramObject, boolean paramBoolean1, boolean paramBoolean2) {
/* 287 */     super.setInput(paramObject, paramBoolean1, paramBoolean2);
/* 288 */     this.document = null;
/*     */   }
/*     */   
/*     */   private JBIG2Document getDocument() throws IOException {
/* 292 */     if (this.document == null) {
/* 293 */       if (this.input == null) {
/* 294 */         throw new IOException("Input not set.");
/*     */       }
/*     */       
/* 297 */       if (this.globals == null) {
/* 298 */         log.debug("Globals not set.");
/*     */       }
/*     */       
/* 301 */       this.document = new JBIG2Document((ImageInputStream)this.input, this.globals);
/*     */     } 
/* 303 */     return this.document;
/*     */   }
/*     */   
/*     */   private JBIG2Page getPage(int paramInt) throws IOException {
/* 307 */     JBIG2Page jBIG2Page = getDocument().getPage(paramInt + 1);
/*     */     
/* 309 */     if (jBIG2Page == null) {
/* 310 */       throw new IndexOutOfBoundsException("Requested page at index=" + paramInt + " does not exist.");
/*     */     }
/* 312 */     return jBIG2Page;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/JBIG2ImageReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */