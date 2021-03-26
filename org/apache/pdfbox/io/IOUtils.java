/*     */ package org.apache.pdfbox.io;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.commons.logging.Log;
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
/*     */ 
/*     */ public final class IOUtils
/*     */ {
/*     */   public static byte[] toByteArray(InputStream in) throws IOException {
/*  51 */     ByteArrayOutputStream baout = new ByteArrayOutputStream();
/*  52 */     copy(in, baout);
/*  53 */     return baout.toByteArray();
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
/*     */   public static long copy(InputStream input, OutputStream output) throws IOException {
/*  65 */     byte[] buffer = new byte[4096];
/*  66 */     long count = 0L;
/*  67 */     int n = 0;
/*  68 */     while (-1 != (n = input.read(buffer))) {
/*     */       
/*  70 */       output.write(buffer, 0, n);
/*  71 */       count += n;
/*     */     } 
/*  73 */     return count;
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
/*     */   public static long populateBuffer(InputStream in, byte[] buffer) throws IOException {
/*  87 */     int remaining = buffer.length;
/*  88 */     while (remaining > 0) {
/*     */       
/*  90 */       int bufferWritePos = buffer.length - remaining;
/*  91 */       int bytesRead = in.read(buffer, bufferWritePos, remaining);
/*  92 */       if (bytesRead < 0) {
/*     */         break;
/*     */       }
/*     */       
/*  96 */       remaining -= bytesRead;
/*     */     } 
/*  98 */     return (buffer.length - remaining);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void closeQuietly(Closeable closeable) {
/*     */     try {
/* 110 */       if (closeable != null)
/*     */       {
/* 112 */         closeable.close();
/*     */       }
/*     */     }
/* 115 */     catch (IOException iOException) {}
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
/*     */   public static IOException closeAndLogException(Closeable closeable, Log logger, String resourceName, IOException initialException) {
/*     */     try {
/* 136 */       closeable.close();
/*     */     }
/* 138 */     catch (IOException ioe) {
/*     */       
/* 140 */       logger.warn("Error closing " + resourceName, ioe);
/* 141 */       if (initialException == null)
/*     */       {
/* 143 */         return ioe;
/*     */       }
/*     */     } 
/* 146 */     return initialException;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/io/IOUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */