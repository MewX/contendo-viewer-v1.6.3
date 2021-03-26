/*     */ package com.github.jaiimageio.impl.plugins.tiff;
/*     */ 
/*     */ import com.github.jaiimageio.plugins.tiff.TIFFDecompressor;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TIFFPackBitsDecompressor
/*     */   extends TIFFDecompressor
/*     */ {
/*     */   private static final boolean DEBUG = false;
/*     */   
/*     */   public int decode(byte[] srcData, int srcOffset, byte[] dstData, int dstOffset) throws IOException {
/*  67 */     int srcIndex = srcOffset;
/*  68 */     int dstIndex = dstOffset;
/*     */     
/*  70 */     int dstArraySize = dstData.length;
/*  71 */     int srcArraySize = srcData.length;
/*     */     try {
/*  73 */       while (dstIndex < dstArraySize && srcIndex < srcArraySize) {
/*  74 */         byte b = srcData[srcIndex++];
/*     */         
/*  76 */         if (b >= 0 && b <= Byte.MAX_VALUE) {
/*     */ 
/*     */           
/*  79 */           for (int i = 0; i < b + 1; i++)
/*  80 */             dstData[dstIndex++] = srcData[srcIndex++];  continue;
/*     */         } 
/*  82 */         if (b <= -1 && b >= -127) {
/*     */           
/*  84 */           byte repeat = srcData[srcIndex++];
/*  85 */           for (int i = 0; i < -b + 1; i++) {
/*  86 */             dstData[dstIndex++] = repeat;
/*     */           }
/*     */           continue;
/*     */         } 
/*  90 */         srcIndex++;
/*     */       }
/*     */     
/*  93 */     } catch (ArrayIndexOutOfBoundsException e) {
/*  94 */       if (this.reader instanceof TIFFImageReader) {
/*  95 */         ((TIFFImageReader)this.reader)
/*  96 */           .forwardWarningMessage("ArrayIndexOutOfBoundsException ignored in TIFFPackBitsDecompressor.decode()");
/*     */       }
/*     */     } 
/*     */     
/* 100 */     return dstIndex - dstOffset;
/*     */   }
/*     */ 
/*     */   
/*     */   public void decodeRaw(byte[] b, int dstOffset, int bitsPerPixel, int scanlineStride) throws IOException {
/*     */     byte[] buf;
/*     */     int bufOffset;
/* 107 */     this.stream.seek(this.offset);
/*     */     
/* 109 */     byte[] srcData = new byte[this.byteCount];
/* 110 */     this.stream.readFully(srcData);
/*     */     
/* 112 */     int bytesPerRow = (this.srcWidth * bitsPerPixel + 7) / 8;
/*     */ 
/*     */     
/* 115 */     if (bytesPerRow == scanlineStride) {
/* 116 */       buf = b;
/* 117 */       bufOffset = dstOffset;
/*     */     } else {
/* 119 */       buf = new byte[bytesPerRow * this.srcHeight];
/* 120 */       bufOffset = 0;
/*     */     } 
/*     */     
/* 123 */     decode(srcData, 0, buf, bufOffset);
/*     */     
/* 125 */     if (bytesPerRow != scanlineStride) {
/*     */ 
/*     */ 
/*     */       
/* 129 */       int off = 0;
/* 130 */       for (int y = 0; y < this.srcHeight; y++) {
/* 131 */         System.arraycopy(buf, off, b, dstOffset, bytesPerRow);
/* 132 */         off += bytesPerRow;
/* 133 */         dstOffset += scanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/tiff/TIFFPackBitsDecompressor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */