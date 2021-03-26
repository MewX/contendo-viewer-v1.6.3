/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.EOFException;
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
/*     */ class MemoryTTFDataStream
/*     */   extends TTFDataStream
/*     */ {
/*  33 */   private byte[] data = null;
/*  34 */   private int currentPosition = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MemoryTTFDataStream(InputStream is) throws IOException {
/*     */     try {
/*  45 */       ByteArrayOutputStream output = new ByteArrayOutputStream(is.available());
/*  46 */       byte[] buffer = new byte[1024];
/*     */       int amountRead;
/*  48 */       while ((amountRead = is.read(buffer)) != -1)
/*     */       {
/*  50 */         output.write(buffer, 0, amountRead);
/*     */       }
/*  52 */       this.data = output.toByteArray();
/*     */     }
/*     */     finally {
/*     */       
/*  56 */       is.close();
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
/*     */   public long readLong() throws IOException {
/*  68 */     return (readSignedInt() << 32L) + (readSignedInt() & 0xFFFFFFFFL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readSignedInt() throws IOException {
/*  79 */     int ch1 = read();
/*  80 */     int ch2 = read();
/*  81 */     int ch3 = read();
/*  82 */     int ch4 = read();
/*  83 */     if ((ch1 | ch2 | ch3 | ch4) < 0)
/*     */     {
/*  85 */       throw new EOFException();
/*     */     }
/*  87 */     return (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/*  98 */     if (this.currentPosition >= this.data.length)
/*     */     {
/* 100 */       return -1;
/*     */     }
/* 102 */     int retval = this.data[this.currentPosition];
/* 103 */     this.currentPosition++;
/* 104 */     return (retval + 256) % 256;
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
/*     */   public int readUnsignedShort() throws IOException {
/* 116 */     int ch1 = read();
/* 117 */     int ch2 = read();
/* 118 */     if ((ch1 | ch2) < 0)
/*     */     {
/* 120 */       throw new EOFException();
/*     */     }
/* 122 */     return (ch1 << 8) + (ch2 << 0);
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
/*     */   public short readSignedShort() throws IOException {
/* 134 */     int ch1 = read();
/* 135 */     int ch2 = read();
/* 136 */     if ((ch1 | ch2) < 0)
/*     */     {
/* 138 */       throw new EOFException();
/*     */     }
/* 140 */     return (short)((ch1 << 8) + (ch2 << 0));
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
/*     */   public void close() throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void seek(long pos) throws IOException {
/* 162 */     this.currentPosition = (int)pos;
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
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 182 */     if (this.currentPosition < this.data.length) {
/*     */       
/* 184 */       int amountRead = Math.min(len, this.data.length - this.currentPosition);
/* 185 */       System.arraycopy(this.data, this.currentPosition, b, off, amountRead);
/* 186 */       this.currentPosition += amountRead;
/* 187 */       return amountRead;
/*     */     } 
/*     */ 
/*     */     
/* 191 */     return -1;
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
/*     */   public long getCurrentPosition() throws IOException {
/* 203 */     return this.currentPosition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getOriginalData() throws IOException {
/* 212 */     return new ByteArrayInputStream(this.data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getOriginalDataSize() {
/* 221 */     return this.data.length;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/MemoryTTFDataStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */