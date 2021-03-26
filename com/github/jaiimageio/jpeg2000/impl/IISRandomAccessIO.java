/*     */ package com.github.jaiimageio.jpeg2000.impl;
/*     */ 
/*     */ import c.a.f.f;
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteOrder;
/*     */ import javax.imageio.stream.ImageInputStream;
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
/*     */ public class IISRandomAccessIO
/*     */   implements f
/*     */ {
/*     */   private ImageInputStream iis;
/*     */   
/*     */   public IISRandomAccessIO(ImageInputStream iis) {
/*  71 */     if (iis == null) {
/*  72 */       throw new IllegalArgumentException("iis == null!");
/*     */     }
/*  74 */     this.iis = iis;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*  78 */     this.iis.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPos() throws IOException {
/*  86 */     long pos = this.iis.getStreamPosition();
/*  87 */     return (pos > 2147483647L) ? Integer.MAX_VALUE : (int)pos;
/*     */   }
/*     */   
/*     */   public void seek(int off) throws IOException {
/*  91 */     this.iis.seek(off);
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
/*     */   public int length() throws IOException {
/* 106 */     long len = this.iis.length();
/*     */ 
/*     */     
/* 109 */     if (len != -1L) {
/* 110 */       return (len > 2147483647L) ? Integer.MAX_VALUE : (int)len;
/*     */     }
/*     */ 
/*     */     
/* 114 */     this.iis.mark();
/* 115 */     int bufLen = 1024;
/* 116 */     byte[] buf = new byte[bufLen];
/* 117 */     long pos = this.iis.getStreamPosition();
/* 118 */     while (pos < 2147483647L) {
/* 119 */       int numRead = this.iis.read(buf, 0, bufLen);
/* 120 */       if (numRead == -1)
/* 121 */         break;  pos += numRead;
/*     */     } 
/* 123 */     this.iis.reset();
/*     */ 
/*     */     
/* 126 */     return (pos > 2147483647L) ? Integer.MAX_VALUE : (int)pos;
/*     */   }
/*     */   
/*     */   public int read() throws IOException {
/* 130 */     return this.iis.read();
/*     */   }
/*     */   
/*     */   public void readFully(byte[] b, int off, int n) throws IOException {
/* 134 */     this.iis.readFully(b, off, n);
/*     */   }
/*     */   
/*     */   public int getByteOrdering() {
/* 138 */     return (this.iis.getByteOrder() == ByteOrder.BIG_ENDIAN) ? 0 : 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte readByte() throws IOException {
/* 143 */     return this.iis.readByte();
/*     */   }
/*     */   
/*     */   public int readUnsignedByte() throws IOException {
/* 147 */     return this.iis.readUnsignedByte();
/*     */   }
/*     */   
/*     */   public short readShort() throws IOException {
/* 151 */     return this.iis.readShort();
/*     */   }
/*     */   
/*     */   public int readUnsignedShort() throws IOException {
/* 155 */     return this.iis.readUnsignedShort();
/*     */   }
/*     */   
/*     */   public int readInt() throws IOException {
/* 159 */     return this.iis.readInt();
/*     */   }
/*     */   
/*     */   public long readUnsignedInt() throws IOException {
/* 163 */     return this.iis.readUnsignedInt();
/*     */   }
/*     */   
/*     */   public long readLong() throws IOException {
/* 167 */     return this.iis.readLong();
/*     */   }
/*     */   
/*     */   public float readFloat() throws IOException {
/* 171 */     return this.iis.readFloat();
/*     */   }
/*     */   
/*     */   public double readDouble() throws IOException {
/* 175 */     return this.iis.readDouble();
/*     */   }
/*     */   
/*     */   public int skipBytes(int n) throws IOException {
/* 179 */     return this.iis.skipBytes(n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(int b) throws IOException {
/* 193 */     throw new IOException("Writing is not supported!");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeByte(int v) throws IOException {
/* 200 */     throw new IOException("Writing is not supported!");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeShort(int v) throws IOException {
/* 207 */     throw new IOException("Writing is not supported!");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeInt(int v) throws IOException {
/* 214 */     throw new IOException("Writing is not supported!");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeLong(long v) throws IOException {
/* 221 */     throw new IOException("Writing is not supported!");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeFloat(float v) throws IOException {
/* 228 */     throw new IOException("Writing is not supported!");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeDouble(double v) throws IOException {
/* 235 */     throw new IOException("Writing is not supported!");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/IISRandomAccessIO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */