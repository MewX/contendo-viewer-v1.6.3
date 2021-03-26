/*     */ package org.apache.pdfbox.filter;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.zip.DataFormatException;
/*     */ import java.util.zip.Deflater;
/*     */ import java.util.zip.DeflaterOutputStream;
/*     */ import java.util.zip.Inflater;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
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
/*     */ final class FlateFilter
/*     */   extends Filter
/*     */ {
/*  39 */   private static final Log LOG = LogFactory.getLog(FlateFilter.class);
/*     */ 
/*     */   
/*     */   private static final int BUFFER_SIZE = 16384;
/*     */ 
/*     */   
/*     */   public DecodeResult decode(InputStream encoded, OutputStream decoded, COSDictionary parameters, int index) throws IOException {
/*  46 */     COSDictionary decodeParams = getDecodeParams(parameters, index);
/*     */ 
/*     */     
/*     */     try {
/*  50 */       decompress(encoded, Predictor.wrapPredictor(decoded, decodeParams));
/*     */     }
/*  52 */     catch (DataFormatException e) {
/*     */ 
/*     */       
/*  55 */       LOG.error("FlateFilter: stop reading corrupt stream due to a DataFormatException");
/*     */ 
/*     */       
/*  58 */       throw new IOException(e);
/*     */     } 
/*  60 */     return new DecodeResult(parameters);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void decompress(InputStream in, OutputStream out) throws IOException, DataFormatException {
/*  67 */     byte[] buf = new byte[2048];
/*     */     
/*  69 */     in.read(buf, 0, 2);
/*  70 */     int read = in.read(buf);
/*  71 */     if (read > 0) {
/*     */ 
/*     */       
/*  74 */       Inflater inflater = new Inflater(true);
/*  75 */       inflater.setInput(buf, 0, read);
/*  76 */       byte[] res = new byte[1024];
/*  77 */       boolean dataWritten = false;
/*     */       
/*     */       while (true) {
/*  80 */         int resRead = 0;
/*     */         
/*     */         try {
/*  83 */           resRead = inflater.inflate(res);
/*     */         }
/*  85 */         catch (DataFormatException exception) {
/*     */           
/*  87 */           if (dataWritten) {
/*     */ 
/*     */             
/*  90 */             LOG.warn("FlateFilter: premature end of stream due to a DataFormatException");
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */           
/*  96 */           throw exception;
/*     */         } 
/*     */         
/*  99 */         if (resRead != 0) {
/*     */           
/* 101 */           out.write(res, 0, resRead);
/* 102 */           dataWritten = true;
/*     */           continue;
/*     */         } 
/* 105 */         if (inflater.finished() || inflater.needsDictionary() || in.available() == 0) {
/*     */           break;
/*     */         }
/*     */         
/* 109 */         read = in.read(buf);
/* 110 */         inflater.setInput(buf, 0, read);
/*     */       } 
/* 112 */       inflater.end();
/*     */     } 
/* 114 */     out.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void encode(InputStream input, OutputStream encoded, COSDictionary parameters) throws IOException {
/* 121 */     int compressionLevel = getCompressionLevel();
/* 122 */     Deflater deflater = new Deflater(compressionLevel);
/* 123 */     DeflaterOutputStream out = new DeflaterOutputStream(encoded, deflater);
/*     */     
/* 125 */     int mayRead = input.available();
/* 126 */     if (mayRead > 0) {
/*     */       
/* 128 */       byte[] buffer = new byte[Math.min(mayRead, 16384)]; int amountRead;
/* 129 */       while ((amountRead = input.read(buffer, 0, Math.min(mayRead, 16384))) != -1)
/*     */       {
/* 131 */         out.write(buffer, 0, amountRead);
/*     */       }
/*     */     } 
/* 134 */     out.close();
/* 135 */     encoded.flush();
/* 136 */     deflater.end();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/filter/FlateFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */