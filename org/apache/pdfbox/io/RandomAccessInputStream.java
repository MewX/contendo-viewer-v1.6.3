/*     */ package org.apache.pdfbox.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
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
/*     */ public class RandomAccessInputStream
/*     */   extends InputStream
/*     */ {
/*  33 */   private static final Log LOG = LogFactory.getLog(RandomAccessInputStream.class);
/*     */ 
/*     */ 
/*     */   
/*     */   private final RandomAccessRead input;
/*     */ 
/*     */ 
/*     */   
/*     */   private long position;
/*     */ 
/*     */ 
/*     */   
/*     */   public RandomAccessInputStream(RandomAccessRead randomAccessRead) {
/*  46 */     this.input = randomAccessRead;
/*  47 */     this.position = 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   void restorePosition() throws IOException {
/*  52 */     this.input.seek(this.position);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int available() throws IOException {
/*  58 */     restorePosition();
/*  59 */     long available = this.input.length() - this.input.getPosition();
/*  60 */     if (available > 2147483647L)
/*     */     {
/*  62 */       return Integer.MAX_VALUE;
/*     */     }
/*  64 */     return (int)available;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/*  70 */     restorePosition();
/*  71 */     if (this.input.isEOF())
/*     */     {
/*  73 */       return -1;
/*     */     }
/*  75 */     int b = this.input.read();
/*  76 */     if (b != -1) {
/*     */       
/*  78 */       this.position++;
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/*  84 */       LOG.error("read() returns -1, assumed position: " + this.position + ", actual position: " + this.input
/*  85 */           .getPosition());
/*     */     } 
/*  87 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/*  93 */     restorePosition();
/*  94 */     if (this.input.isEOF())
/*     */     {
/*  96 */       return -1;
/*     */     }
/*  98 */     int n = this.input.read(b, off, len);
/*  99 */     if (n != -1) {
/*     */       
/* 101 */       this.position += n;
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 107 */       LOG.error("read() returns -1, assumed position: " + this.position + ", actual position: " + this.input
/* 108 */           .getPosition());
/*     */     } 
/* 110 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long skip(long n) throws IOException {
/* 116 */     restorePosition();
/* 117 */     this.input.seek(this.position + n);
/* 118 */     this.position += n;
/* 119 */     return n;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/io/RandomAccessInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */