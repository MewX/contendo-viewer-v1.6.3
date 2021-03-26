/*     */ package org.apache.pdfbox.filter;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Iterator;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.imageio.ImageReader;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
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
/*     */ public abstract class Filter
/*     */ {
/*  43 */   private static final Log LOG = LogFactory.getLog(Filter.class);
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
/*     */   public static final String SYSPROP_DEFLATELEVEL = "org.apache.pdfbox.filter.deflatelevel";
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
/*     */   public abstract DecodeResult decode(InputStream paramInputStream, OutputStream paramOutputStream, COSDictionary paramCOSDictionary, int paramInt) throws IOException;
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
/*     */   public DecodeResult decode(InputStream encoded, OutputStream decoded, COSDictionary parameters, int index, DecodeOptions options) throws IOException {
/*  87 */     return decode(encoded, decoded, parameters, index);
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
/*     */   public final void encode(InputStream input, OutputStream encoded, COSDictionary parameters, int index) throws IOException {
/* 101 */     encode(input, encoded, parameters.asUnmodifiableDictionary());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void encode(InputStream paramInputStream, OutputStream paramOutputStream, COSDictionary paramCOSDictionary) throws IOException;
/*     */ 
/*     */ 
/*     */   
/*     */   protected COSDictionary getDecodeParams(COSDictionary dictionary, int index) {
/* 112 */     COSBase filter = dictionary.getDictionaryObject(COSName.FILTER, COSName.F);
/* 113 */     COSBase obj = dictionary.getDictionaryObject(COSName.DECODE_PARMS, COSName.DP);
/* 114 */     if (filter instanceof COSName && obj instanceof COSDictionary)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 119 */       return (COSDictionary)obj;
/*     */     }
/* 121 */     if (filter instanceof COSArray && obj instanceof COSArray) {
/*     */       
/* 123 */       COSArray array = (COSArray)obj;
/* 124 */       if (index < array.size())
/*     */       {
/* 126 */         COSBase objAtIndex = array.getObject(index);
/* 127 */         if (objAtIndex instanceof COSDictionary)
/*     */         {
/* 129 */           return (COSDictionary)array.getObject(index);
/*     */         }
/*     */       }
/*     */     
/* 133 */     } else if (obj != null && !(filter instanceof COSArray) && !(obj instanceof COSArray)) {
/*     */       
/* 135 */       LOG.error("Expected DecodeParams to be an Array or Dictionary but found " + obj
/* 136 */           .getClass().getName());
/*     */     } 
/* 138 */     return new COSDictionary();
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
/*     */   protected static ImageReader findImageReader(String formatName, String errorCause) throws MissingImageReaderException {
/* 151 */     Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(formatName);
/* 152 */     ImageReader reader = null;
/* 153 */     while (readers.hasNext()) {
/*     */       
/* 155 */       reader = readers.next();
/* 156 */       if (reader != null && reader.canReadRaster()) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */     
/* 161 */     if (reader == null)
/*     */     {
/* 163 */       throw new MissingImageReaderException("Cannot read " + formatName + " image: " + errorCause);
/*     */     }
/* 165 */     return reader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getCompressionLevel() {
/* 173 */     int compressionLevel = -1;
/*     */     
/*     */     try {
/* 176 */       compressionLevel = Integer.parseInt(System.getProperty("org.apache.pdfbox.filter.deflatelevel", "-1"));
/*     */     }
/* 178 */     catch (NumberFormatException ex) {
/*     */       
/* 180 */       LOG.warn(ex.getMessage(), ex);
/*     */     } 
/* 182 */     return Math.max(-1, Math.min(9, compressionLevel));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/filter/Filter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */