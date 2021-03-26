/*     */ package org.apache.xmlgraphics.image.loader.impl;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteOrder;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import javax.xml.transform.Source;
/*     */ import org.apache.xmlgraphics.image.loader.ImageContext;
/*     */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*     */ import org.apache.xmlgraphics.image.loader.util.ImageUtil;
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
/*     */ public class PreloaderEPS
/*     */   extends AbstractImagePreloader
/*     */ {
/*  49 */   public static final Object EPS_BINARY_HEADER = EPSBinaryFileHeader.class;
/*     */   
/*  51 */   public static final Object EPS_BOUNDING_BOX = Rectangle2D.class;
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageInfo preloadImage(String uri, Source src, ImageContext context) throws IOException {
/*  56 */     if (!ImageUtil.hasImageInputStream(src)) {
/*  57 */       return null;
/*     */     }
/*  59 */     ImageInputStream in = ImageUtil.needImageInputStream(src);
/*  60 */     in.mark();
/*  61 */     ByteOrder originalByteOrder = in.getByteOrder();
/*  62 */     in.setByteOrder(ByteOrder.LITTLE_ENDIAN);
/*  63 */     EPSBinaryFileHeader binaryHeader = null;
/*     */     try {
/*  65 */       long magic = in.readUnsignedInt();
/*  66 */       magic &= 0xFFFFFFFFL;
/*     */       
/*  68 */       boolean supported = false;
/*  69 */       if (magic == 3335770309L) {
/*  70 */         supported = true;
/*     */         
/*  72 */         binaryHeader = readBinaryFileHeader(in);
/*  73 */         in.reset();
/*  74 */         in.mark();
/*  75 */         in.seek(binaryHeader.psStart);
/*     */       }
/*  77 */       else if (magic == 1397760293L) {
/*  78 */         supported = true;
/*  79 */         in.reset();
/*  80 */         in.mark();
/*     */       } else {
/*  82 */         in.reset();
/*     */       } 
/*     */       
/*  85 */       if (supported) {
/*  86 */         ImageInfo info = new ImageInfo(uri, "application/postscript");
/*  87 */         boolean success = determineSize(in, context, info);
/*  88 */         in.reset();
/*  89 */         if (!success)
/*     */         {
/*  91 */           return null;
/*     */         }
/*  93 */         if (in.getStreamPosition() != 0L) {
/*  94 */           throw new IllegalStateException("Need to be at the start of the file here");
/*     */         }
/*  96 */         if (binaryHeader != null) {
/*  97 */           info.getCustomObjects().put(EPS_BINARY_HEADER, binaryHeader);
/*     */         }
/*  99 */         return info;
/*     */       } 
/* 101 */       return null;
/*     */     } finally {
/*     */       
/* 104 */       in.setByteOrder(originalByteOrder);
/*     */     } 
/*     */   }
/*     */   
/*     */   private EPSBinaryFileHeader readBinaryFileHeader(ImageInputStream in) throws IOException {
/* 109 */     EPSBinaryFileHeader offsets = new EPSBinaryFileHeader();
/* 110 */     offsets.psStart = in.readUnsignedInt();
/* 111 */     offsets.psLength = in.readUnsignedInt();
/* 112 */     offsets.wmfStart = in.readUnsignedInt();
/* 113 */     offsets.wmfLength = in.readUnsignedInt();
/* 114 */     offsets.tiffStart = in.readUnsignedInt();
/* 115 */     offsets.tiffLength = in.readUnsignedInt();
/* 116 */     return offsets;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean determineSize(ImageInputStream in, ImageContext context, ImageInfo info) throws IOException {
/* 122 */     in.mark();
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
/*     */   public static class EPSBinaryFileHeader
/*     */   {
/*     */     private long psStart;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private long psLength;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private long wmfStart;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private long wmfLength;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private long tiffStart;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private long tiffLength;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public long getPSStart() {
/* 193 */       return this.psStart;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public long getPSLength() {
/* 201 */       return this.psLength;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasWMFPreview() {
/* 209 */       return (this.wmfStart != 0L);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public long getWMFStart() {
/* 217 */       return this.wmfStart;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public long getWMFLength() {
/* 225 */       return this.wmfLength;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasTIFFPreview() {
/* 233 */       return (this.tiffStart != 0L);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public long getTIFFStart() {
/* 241 */       return this.tiffStart;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public long getTIFFLength() {
/* 249 */       return this.tiffLength;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/PreloaderEPS.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */