/*     */ package com.levigo.jbig2.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import javax.imageio.stream.ImageInputStreamImpl;
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
/*     */ public class SubInputStream
/*     */   extends ImageInputStreamImpl
/*     */ {
/*     */   protected final ImageInputStream wrappedStream;
/*     */   protected final long offset;
/*     */   protected final long length;
/*  48 */   private final byte[] buffer = new byte[4096];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   long bufferBase;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   long bufferTop;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SubInputStream(ImageInputStream paramImageInputStream, long paramLong1, long paramLong2) {
/*  68 */     assert null != paramImageInputStream;
/*  69 */     assert paramLong2 >= 0L;
/*  70 */     assert paramLong1 >= 0L;
/*     */     
/*  72 */     this.wrappedStream = paramImageInputStream;
/*  73 */     this.offset = paramLong1;
/*  74 */     this.length = paramLong2;
/*     */   }
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/*  79 */     if (this.streamPos >= this.length) {
/*  80 */       return -1;
/*     */     }
/*     */     
/*  83 */     if ((this.streamPos >= this.bufferTop || this.streamPos < this.bufferBase) && 
/*  84 */       !fillBuffer()) {
/*  85 */       return -1;
/*     */     }
/*     */ 
/*     */     
/*  89 */     int i = 0xFF & this.buffer[(int)(this.streamPos - this.bufferBase)];
/*     */     
/*  91 */     this.streamPos++;
/*     */     
/*  93 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
/*  98 */     if (this.streamPos >= this.length) {
/*  99 */       return -1;
/*     */     }
/*     */     
/* 102 */     synchronized (this.wrappedStream) {
/* 103 */       if (this.wrappedStream.getStreamPosition() != this.streamPos + this.offset) {
/* 104 */         this.wrappedStream.seek(this.streamPos + this.offset);
/*     */       }
/*     */       
/* 107 */       int i = (int)Math.min(paramInt2, this.length - this.streamPos);
/* 108 */       int j = this.wrappedStream.read(paramArrayOfbyte, paramInt1, i);
/* 109 */       this.streamPos += j;
/*     */       
/* 111 */       return j;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean fillBuffer() throws IOException {
/* 122 */     synchronized (this.wrappedStream) {
/* 123 */       if (this.wrappedStream.getStreamPosition() != this.streamPos + this.offset) {
/* 124 */         this.wrappedStream.seek(this.streamPos + this.offset);
/*     */       }
/*     */       
/* 127 */       this.bufferBase = this.streamPos;
/* 128 */       int i = (int)Math.min(this.buffer.length, this.length - this.streamPos);
/* 129 */       int j = this.wrappedStream.read(this.buffer, 0, i);
/* 130 */       this.bufferTop = this.bufferBase + j;
/*     */       
/* 132 */       return (j > 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public long length() {
/* 138 */     return this.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void skipBits() {
/* 145 */     if (this.bitOffset != 0) {
/* 146 */       this.bitOffset = 0;
/* 147 */       this.streamPos++;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/io/SubInputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */