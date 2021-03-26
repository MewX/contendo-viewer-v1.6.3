/*     */ package org.apache.xmlgraphics.image.codec.png;
/*     */ 
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class IDATOutputStream
/*     */   extends FilterOutputStream
/*     */ {
/* 199 */   private static final byte[] TYPE_SIGNATURE = new byte[] { 73, 68, 65, 84 };
/*     */   
/*     */   private int bytesWritten;
/*     */   
/*     */   private int segmentLength;
/*     */   
/*     */   private byte[] buffer;
/*     */   
/*     */   public IDATOutputStream(OutputStream output, int segmentLength) {
/* 208 */     super(output);
/* 209 */     this.segmentLength = segmentLength;
/* 210 */     this.buffer = new byte[segmentLength];
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 215 */     flush();
/*     */   }
/*     */   
/*     */   private void writeInt(int x) throws IOException {
/* 219 */     this.out.write(x >> 24);
/* 220 */     this.out.write(x >> 16 & 0xFF);
/* 221 */     this.out.write(x >> 8 & 0xFF);
/* 222 */     this.out.write(x & 0xFF);
/*     */   }
/*     */ 
/*     */   
/*     */   public void flush() throws IOException {
/* 227 */     if (this.bytesWritten == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 232 */     writeInt(this.bytesWritten);
/*     */     
/* 234 */     this.out.write(TYPE_SIGNATURE);
/*     */     
/* 236 */     this.out.write(this.buffer, 0, this.bytesWritten);
/*     */     
/* 238 */     int crc = -1;
/* 239 */     crc = CRC.updateCRC(crc, TYPE_SIGNATURE, 0, 4);
/* 240 */     crc = CRC.updateCRC(crc, this.buffer, 0, this.bytesWritten);
/*     */ 
/*     */     
/* 243 */     writeInt(crc ^ 0xFFFFFFFF);
/*     */ 
/*     */     
/* 246 */     this.bytesWritten = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(byte[] b) throws IOException {
/* 251 */     write(b, 0, b.length);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/* 256 */     while (len > 0) {
/* 257 */       int bytes = Math.min(this.segmentLength - this.bytesWritten, len);
/* 258 */       System.arraycopy(b, off, this.buffer, this.bytesWritten, bytes);
/* 259 */       off += bytes;
/* 260 */       len -= bytes;
/* 261 */       this.bytesWritten += bytes;
/*     */       
/* 263 */       if (this.bytesWritten == this.segmentLength) {
/* 264 */         flush();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(int b) throws IOException {
/* 271 */     this.buffer[this.bytesWritten++] = (byte)b;
/* 272 */     if (this.bytesWritten == this.segmentLength)
/* 273 */       flush(); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/png/IDATOutputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */