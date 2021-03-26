/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import java.util.zip.Adler32;
/*     */ import java.util.zip.Checksum;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
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
/*     */ public abstract class ImageCacher
/*     */   implements ErrorConstants, SVGSyntax
/*     */ {
/*  47 */   DOMTreeManager domTreeManager = null;
/*     */   
/*     */   Map imageCache;
/*     */   
/*     */   Checksum checkSum;
/*     */ 
/*     */   
/*     */   public ImageCacher() {
/*  55 */     this.imageCache = new HashMap<Object, Object>();
/*  56 */     this.checkSum = new Adler32();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageCacher(DOMTreeManager domTreeManager) {
/*  65 */     this();
/*  66 */     setDOMTreeManager(domTreeManager);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDOMTreeManager(DOMTreeManager domTreeManager) {
/*  75 */     if (domTreeManager == null) {
/*  76 */       throw new IllegalArgumentException();
/*     */     }
/*  78 */     this.domTreeManager = domTreeManager;
/*     */   }
/*     */   
/*     */   public DOMTreeManager getDOMTreeManager() {
/*  82 */     return this.domTreeManager;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public String lookup(ByteArrayOutputStream os, int width, int height, SVGGeneratorContext ctx) throws SVGGraphics2DIOException {
/* 105 */     int checksum = getChecksum(os.toByteArray());
/* 106 */     Integer key = Integer.valueOf(checksum);
/* 107 */     String href = null;
/*     */     
/* 109 */     Object data = getCacheableData(os);
/*     */     
/* 111 */     LinkedList<ImageCacheEntry> list = (LinkedList)this.imageCache.get(key);
/* 112 */     if (list == null) {
/*     */       
/* 114 */       list = new LinkedList();
/* 115 */       this.imageCache.put(key, list);
/*     */     } else {
/*     */       
/* 118 */       for (ListIterator<ImageCacheEntry> i = list.listIterator(0); i.hasNext(); ) {
/* 119 */         ImageCacheEntry entry = i.next();
/* 120 */         if (entry.checksum == checksum && imagesMatch(entry.src, data)) {
/* 121 */           href = entry.href;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 127 */     if (href == null) {
/*     */       
/* 129 */       ImageCacheEntry newEntry = createEntry(checksum, data, width, height, ctx);
/*     */ 
/*     */       
/* 132 */       list.add(newEntry);
/* 133 */       href = newEntry.href;
/*     */     } 
/*     */     
/* 136 */     return href;
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
/*     */   abstract Object getCacheableData(ByteArrayOutputStream paramByteArrayOutputStream);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract boolean imagesMatch(Object paramObject1, Object paramObject2) throws SVGGraphics2DIOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract ImageCacheEntry createEntry(int paramInt1, Object paramObject, int paramInt2, int paramInt3, SVGGeneratorContext paramSVGGeneratorContext) throws SVGGraphics2DIOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getChecksum(byte[] data) {
/* 179 */     this.checkSum.reset();
/* 180 */     this.checkSum.update(data, 0, data.length);
/* 181 */     return (int)this.checkSum.getValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class ImageCacheEntry
/*     */   {
/*     */     public int checksum;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object src;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String href;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     ImageCacheEntry(int checksum, Object src, String href) {
/* 206 */       this.checksum = checksum;
/* 207 */       this.src = src;
/* 208 */       this.href = href;
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
/*     */   public static class Embedded
/*     */     extends ImageCacher
/*     */   {
/*     */     public void setDOMTreeManager(DOMTreeManager domTreeManager) {
/* 225 */       if (this.domTreeManager != domTreeManager) {
/* 226 */         this.domTreeManager = domTreeManager;
/* 227 */         this.imageCache = new HashMap<Object, Object>();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Object getCacheableData(ByteArrayOutputStream os) {
/* 235 */       return "data:image/png;base64," + os.toString();
/*     */     }
/*     */     
/*     */     boolean imagesMatch(Object o1, Object o2) {
/* 239 */       return o1.equals(o2);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     ImageCacher.ImageCacheEntry createEntry(int checksum, Object data, int width, int height, SVGGeneratorContext ctx) {
/* 247 */       String id = ctx.idGenerator.generateID("image");
/*     */ 
/*     */       
/* 250 */       addToTree(id, (String)data, width, height, ctx);
/*     */ 
/*     */       
/* 253 */       return new ImageCacher.ImageCacheEntry(checksum, data, "#" + id);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void addToTree(String id, String href, int width, int height, SVGGeneratorContext ctx) {
/* 264 */       Document domFactory = this.domTreeManager.getDOMFactory();
/*     */ 
/*     */ 
/*     */       
/* 268 */       Element imageElement = domFactory.createElementNS("http://www.w3.org/2000/svg", "image");
/*     */       
/* 270 */       imageElement.setAttributeNS(null, "id", id);
/*     */       
/* 272 */       imageElement.setAttributeNS(null, "width", Integer.toString(width));
/*     */       
/* 274 */       imageElement.setAttributeNS(null, "height", Integer.toString(height));
/*     */       
/* 276 */       imageElement.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", href);
/*     */ 
/*     */ 
/*     */       
/* 280 */       this.domTreeManager.addOtherDef(imageElement);
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
/*     */   public static class External
/*     */     extends ImageCacher
/*     */   {
/*     */     private String imageDir;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private String prefix;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private String suffix;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public External(String imageDir, String prefix, String suffix) {
/* 318 */       this.imageDir = imageDir;
/* 319 */       this.prefix = prefix;
/* 320 */       this.suffix = suffix;
/*     */     }
/*     */     
/*     */     Object getCacheableData(ByteArrayOutputStream os) {
/* 324 */       return os;
/*     */     }
/*     */ 
/*     */     
/*     */     boolean imagesMatch(Object o1, Object o2) throws SVGGraphics2DIOException {
/* 329 */       boolean match = false;
/* 330 */       FileInputStream imageStream = null;
/*     */       try {
/* 332 */         imageStream = new FileInputStream((File)o1);
/* 333 */         int imageLen = imageStream.available();
/* 334 */         byte[] imageBytes = new byte[imageLen];
/* 335 */         byte[] candidateBytes = ((ByteArrayOutputStream)o2).toByteArray();
/*     */ 
/*     */         
/* 338 */         int bytesRead = 0;
/* 339 */         while (bytesRead != imageLen) {
/* 340 */           bytesRead += imageStream.read(imageBytes, bytesRead, imageLen - bytesRead);
/*     */         }
/*     */ 
/*     */         
/* 344 */         match = Arrays.equals(imageBytes, candidateBytes);
/* 345 */       } catch (IOException e) {
/* 346 */         throw new SVGGraphics2DIOException("could not read image File " + ((File)o1).getName());
/*     */       } finally {
/*     */         
/*     */         try {
/* 350 */           if (imageStream != null)
/* 351 */             imageStream.close(); 
/* 352 */         } catch (IOException iOException) {}
/*     */       } 
/*     */       
/* 355 */       return match;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     ImageCacher.ImageCacheEntry createEntry(int checksum, Object data, int width, int height, SVGGeneratorContext ctx) throws SVGGraphics2DIOException {
/* 364 */       File imageFile = null;
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 369 */         while (imageFile == null) {
/* 370 */           String fileId = ctx.idGenerator.generateID(this.prefix);
/* 371 */           imageFile = new File(this.imageDir, fileId + this.suffix);
/* 372 */           if (imageFile.exists()) {
/* 373 */             imageFile = null;
/*     */           }
/*     */         } 
/*     */         
/* 377 */         OutputStream outputStream = new FileOutputStream(imageFile);
/* 378 */         ((ByteArrayOutputStream)data).writeTo(outputStream);
/* 379 */         ((ByteArrayOutputStream)data).close();
/* 380 */       } catch (IOException e) {
/* 381 */         throw new SVGGraphics2DIOException("could not write image File " + imageFile.getName());
/*     */       } 
/*     */ 
/*     */       
/* 385 */       return new ImageCacher.ImageCacheEntry(checksum, imageFile, imageFile.getName());
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/ImageCacher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */