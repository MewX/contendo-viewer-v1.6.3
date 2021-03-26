/*     */ package org.apache.pdfbox.filter;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.DataBuffer;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.SequenceInputStream;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.imageio.ImageReadParam;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
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
/*     */ final class JBIG2Filter
/*     */   extends Filter
/*     */ {
/*  48 */   private static final Log LOG = LogFactory.getLog(JBIG2Filter.class);
/*     */   
/*     */   private static boolean levigoLogged = false;
/*     */ 
/*     */   
/*     */   private static synchronized void logLevigoDonated() {
/*  54 */     if (!levigoLogged) {
/*     */       
/*  56 */       LOG.info("The Levigo JBIG2 plugin has been donated to the Apache Foundation");
/*  57 */       LOG.info("and an improved version is available for download at https://pdfbox.apache.org/download.cgi");
/*     */       
/*  59 */       levigoLogged = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DecodeResult decode(InputStream encoded, OutputStream decoded, COSDictionary parameters, int index, DecodeOptions options) throws IOException {
/*  67 */     ImageReader reader = findImageReader("JBIG2", "jbig2-imageio is not installed");
/*  68 */     if (reader.getClass().getName().contains("levigo"))
/*     */     {
/*  70 */       logLevigoDonated();
/*     */     }
/*     */     
/*  73 */     int bits = parameters.getInt(COSName.BITS_PER_COMPONENT, 1);
/*  74 */     COSDictionary params = getDecodeParams(parameters, index);
/*     */     
/*  76 */     ImageReadParam irp = reader.getDefaultReadParam();
/*  77 */     irp.setSourceSubsampling(options.getSubsamplingX(), options.getSubsamplingY(), options
/*  78 */         .getSubsamplingOffsetX(), options.getSubsamplingOffsetY());
/*  79 */     irp.setSourceRegion(options.getSourceRegion());
/*  80 */     options.setFilterSubsampled(true);
/*     */     
/*  82 */     COSStream globals = null;
/*  83 */     if (params != null)
/*     */     {
/*  85 */       globals = (COSStream)params.getDictionaryObject(COSName.JBIG2_GLOBALS);
/*     */     }
/*     */     
/*  88 */     ImageInputStream iis = null;
/*     */     try {
/*     */       BufferedImage image;
/*  91 */       if (globals != null) {
/*     */         
/*  93 */         iis = ImageIO.createImageInputStream(new SequenceInputStream((InputStream)globals
/*  94 */               .createInputStream(), encoded));
/*  95 */         reader.setInput(iis);
/*     */       }
/*     */       else {
/*     */         
/*  99 */         iis = ImageIO.createImageInputStream(encoded);
/* 100 */         reader.setInput(iis);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 106 */         image = reader.read(0, irp);
/*     */       }
/* 108 */       catch (Exception e) {
/*     */ 
/*     */         
/* 111 */         throw new IOException("Could not read JBIG2 image", e);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 116 */       if (image.getColorModel().getPixelSize() != bits) {
/*     */         
/* 118 */         if (bits != 1)
/*     */         {
/* 120 */           LOG.warn("Attempting to handle a JBIG2 with more than 1-bit depth");
/*     */         }
/* 122 */         BufferedImage packedImage = new BufferedImage(image.getWidth(), image.getHeight(), 12);
/*     */         
/* 124 */         Graphics graphics = packedImage.getGraphics();
/* 125 */         graphics.drawImage(image, 0, 0, null);
/* 126 */         graphics.dispose();
/* 127 */         image = packedImage;
/*     */       } 
/*     */       
/* 130 */       DataBuffer dBuf = image.getData().getDataBuffer();
/* 131 */       if (dBuf.getDataType() == 0)
/*     */       {
/* 133 */         decoded.write(((DataBufferByte)dBuf).getData());
/*     */       }
/*     */       else
/*     */       {
/* 137 */         throw new IOException("Unexpected image buffer type");
/*     */       }
/*     */     
/*     */     } finally {
/*     */       
/* 142 */       if (iis != null)
/*     */       {
/* 144 */         iis.close();
/*     */       }
/* 146 */       reader.dispose();
/*     */     } 
/* 148 */     return new DecodeResult(parameters);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DecodeResult decode(InputStream encoded, OutputStream decoded, COSDictionary parameters, int index) throws IOException {
/* 155 */     return decode(encoded, decoded, parameters, index, DecodeOptions.DEFAULT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void encode(InputStream input, OutputStream encoded, COSDictionary parameters) throws IOException {
/* 162 */     throw new UnsupportedOperationException("JBIG2 encoding not implemented");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/filter/JBIG2Filter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */