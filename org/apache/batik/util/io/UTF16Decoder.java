/*     */ package org.apache.batik.util.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
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
/*     */ public class UTF16Decoder
/*     */   extends AbstractCharDecoder
/*     */ {
/*     */   protected boolean bigEndian;
/*     */   
/*     */   public UTF16Decoder(InputStream is) throws IOException {
/*  44 */     super(is);
/*     */     
/*  46 */     int b1 = is.read();
/*  47 */     if (b1 == -1) {
/*  48 */       endOfStreamError("UTF-16");
/*     */     }
/*  50 */     int b2 = is.read();
/*  51 */     if (b2 == -1) {
/*  52 */       endOfStreamError("UTF-16");
/*     */     }
/*  54 */     int m = (b1 & 0xFF) << 8 | b2 & 0xFF;
/*  55 */     switch (m) {
/*     */       case 65279:
/*  57 */         this.bigEndian = true;
/*     */       
/*     */       case 65534:
/*     */         return;
/*     */     } 
/*  62 */     charError("UTF-16");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UTF16Decoder(InputStream is, boolean be) {
/*  73 */     super(is);
/*  74 */     this.bigEndian = be;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readChar() throws IOException {
/*  82 */     if (this.position == this.count) {
/*  83 */       fillBuffer();
/*     */     }
/*  85 */     if (this.count == -1) {
/*  86 */       return -1;
/*     */     }
/*  88 */     byte b1 = this.buffer[this.position++];
/*  89 */     if (this.position == this.count) {
/*  90 */       fillBuffer();
/*     */     }
/*  92 */     if (this.count == -1) {
/*  93 */       endOfStreamError("UTF-16");
/*     */     }
/*  95 */     byte b2 = this.buffer[this.position++];
/*  96 */     int c = this.bigEndian ? ((b1 & 0xFF) << 8 | b2 & 0xFF) : ((b2 & 0xFF) << 8 | b1 & 0xFF);
/*     */ 
/*     */     
/*  99 */     if (c == 65534) {
/* 100 */       charError("UTF-16");
/*     */     }
/* 102 */     return c;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/io/UTF16Decoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */