/*     */ package org.apache.xmlgraphics.image.loader.impl;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*     */ import org.apache.xmlgraphics.image.loader.ImageInfo;
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
/*     */ public class ImageRawStream
/*     */   extends AbstractImage
/*     */ {
/*     */   private ImageFlavor flavor;
/*     */   private InputStreamFactory streamFactory;
/*     */   
/*     */   public ImageRawStream(ImageInfo info, ImageFlavor flavor, InputStreamFactory streamFactory) {
/*  49 */     super(info);
/*  50 */     this.flavor = flavor;
/*  51 */     setInputStreamFactory(streamFactory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageRawStream(ImageInfo info, ImageFlavor flavor, InputStream in) {
/*  61 */     this(info, flavor, new SingleStreamFactory(in));
/*     */   }
/*     */ 
/*     */   
/*     */   public ImageFlavor getFlavor() {
/*  66 */     return this.flavor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMimeType() {
/*  74 */     if (getFlavor() instanceof org.apache.xmlgraphics.image.loader.MimeEnabledImageFlavor) {
/*  75 */       return getFlavor().getMimeType();
/*     */     }
/*     */     
/*  78 */     return "application/octet-stream";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCacheable() {
/*  84 */     return !this.streamFactory.isUsedOnceOnly();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInputStreamFactory(InputStreamFactory factory) {
/*  93 */     if (this.streamFactory != null) {
/*  94 */       this.streamFactory.close();
/*     */     }
/*  96 */     this.streamFactory = factory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream createInputStream() {
/* 104 */     return this.streamFactory.createInputStream();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeTo(OutputStream out) throws IOException {
/* 114 */     InputStream in = createInputStream();
/*     */     try {
/* 116 */       IOUtils.copy(in, out);
/*     */     } finally {
/* 118 */       IOUtils.closeQuietly(in);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface InputStreamFactory
/*     */   {
/*     */     boolean isUsedOnceOnly();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     InputStream createInputStream();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class SingleStreamFactory
/*     */     implements InputStreamFactory
/*     */   {
/*     */     private InputStream in;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SingleStreamFactory(InputStream in) {
/* 155 */       this.in = in;
/*     */     }
/*     */     
/*     */     public synchronized InputStream createInputStream() {
/* 159 */       if (this.in != null) {
/* 160 */         InputStream tempin = this.in;
/* 161 */         this.in = null;
/* 162 */         return tempin;
/*     */       } 
/* 164 */       throw new IllegalStateException("Can only create an InputStream once!");
/*     */     }
/*     */ 
/*     */     
/*     */     public synchronized void close() {
/* 169 */       IOUtils.closeQuietly(this.in);
/* 170 */       this.in = null;
/*     */     }
/*     */     
/*     */     public boolean isUsedOnceOnly() {
/* 174 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void finalize() {
/* 179 */       close();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class ByteArrayStreamFactory
/*     */     implements InputStreamFactory
/*     */   {
/*     */     private byte[] data;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ByteArrayStreamFactory(byte[] data) {
/* 196 */       this.data = data;
/*     */     }
/*     */ 
/*     */     
/*     */     public InputStream createInputStream() {
/* 201 */       return new ByteArrayInputStream(this.data);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void close() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isUsedOnceOnly() {
/* 211 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageRawStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */