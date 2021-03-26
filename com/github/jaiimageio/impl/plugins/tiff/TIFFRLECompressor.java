/*     */ package com.github.jaiimageio.impl.plugins.tiff;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.imageio.IIOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TIFFRLECompressor
/*     */   extends TIFFFaxCompressor
/*     */ {
/*     */   public TIFFRLECompressor() {
/*  60 */     super("CCITT RLE", 2, true);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public int encodeRLE(byte[] data, int rowOffset, int colOffset, int rowLength, byte[] compData) {
/*  83 */     initBitBuf();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  89 */     int outIndex = encode1D(data, rowOffset, colOffset, rowLength, compData, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  94 */     while (this.ndex > 0) {
/*  95 */       compData[outIndex++] = (byte)(this.bits >>> 24);
/*  96 */       this.bits <<= 8;
/*  97 */       this.ndex -= 8;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     if (this.inverseFill) {
/* 104 */       byte[] flipTable = TIFFFaxDecompressor.flipTable;
/* 105 */       for (int i = 0; i < outIndex; i++) {
/* 106 */         compData[i] = flipTable[compData[i] & 0xFF];
/*     */       }
/*     */     } 
/*     */     
/* 110 */     return outIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int encode(byte[] b, int off, int width, int height, int[] bitsPerSample, int scanlineStride) throws IOException {
/* 117 */     if (bitsPerSample.length != 1 || bitsPerSample[0] != 1) {
/* 118 */       throw new IIOException("Bits per sample must be 1 for RLE compression!");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     int maxBits = 9 * (width + 1) / 2 + 2;
/* 125 */     byte[] compData = new byte[(maxBits + 7) / 8];
/*     */     
/* 127 */     int bytes = 0;
/* 128 */     int rowOffset = off;
/*     */     
/* 130 */     for (int i = 0; i < height; i++) {
/* 131 */       int rowBytes = encodeRLE(b, rowOffset, 0, width, compData);
/* 132 */       this.stream.write(compData, 0, rowBytes);
/*     */       
/* 134 */       rowOffset += scanlineStride;
/* 135 */       bytes += rowBytes;
/*     */     } 
/*     */     
/* 138 */     return bytes;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/tiff/TIFFRLECompressor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */