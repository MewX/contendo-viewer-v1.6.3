/*     */ package org.apache.batik.ext.awt.image.codec.png;
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
/*     */ class IDATOutputStream
/*     */   extends FilterOutputStream
/*     */ {
/* 188 */   private static final byte[] typeSignature = new byte[] { 73, 68, 65, 84 };
/*     */ 
/*     */   
/* 191 */   private int bytesWritten = 0;
/*     */   
/*     */   private int segmentLength;
/*     */   byte[] buffer;
/*     */   
/*     */   public IDATOutputStream(OutputStream output, int segmentLength) {
/* 197 */     super(output);
/* 198 */     this.segmentLength = segmentLength;
/* 199 */     this.buffer = new byte[segmentLength];
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 203 */     flush();
/*     */   }
/*     */   
/*     */   private void writeInt(int x) throws IOException {
/* 207 */     this.out.write(x >> 24);
/* 208 */     this.out.write(x >> 16 & 0xFF);
/* 209 */     this.out.write(x >> 8 & 0xFF);
/* 210 */     this.out.write(x & 0xFF);
/*     */   }
/*     */   
/*     */   public void flush() throws IOException {
/* 214 */     if (this.bytesWritten == 0) {
/*     */       return;
/*     */     }
/* 217 */     writeInt(this.bytesWritten);
/*     */     
/* 219 */     this.out.write(typeSignature);
/*     */     
/* 221 */     this.out.write(this.buffer, 0, this.bytesWritten);
/*     */     
/* 223 */     int crc = -1;
/* 224 */     crc = CRC.updateCRC(crc, typeSignature, 0, 4);
/* 225 */     crc = CRC.updateCRC(crc, this.buffer, 0, this.bytesWritten);
/*     */ 
/*     */     
/* 228 */     writeInt(crc ^ 0xFFFFFFFF);
/*     */ 
/*     */     
/* 231 */     this.bytesWritten = 0;
/*     */   }
/*     */   
/*     */   public void write(byte[] b) throws IOException {
/* 235 */     write(b, 0, b.length);
/*     */   }
/*     */   
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/* 239 */     while (len > 0) {
/* 240 */       int bytes = Math.min(this.segmentLength - this.bytesWritten, len);
/* 241 */       System.arraycopy(b, off, this.buffer, this.bytesWritten, bytes);
/* 242 */       off += bytes;
/* 243 */       len -= bytes;
/* 244 */       this.bytesWritten += bytes;
/*     */       
/* 246 */       if (this.bytesWritten == this.segmentLength) {
/* 247 */         flush();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void write(int b) throws IOException {
/* 253 */     this.buffer[this.bytesWritten++] = (byte)b;
/* 254 */     if (this.bytesWritten == this.segmentLength)
/* 255 */       flush(); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/png/IDATOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */