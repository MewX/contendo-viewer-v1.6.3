/*     */ package com.github.jaiimageio.impl.plugins.tiff;
/*     */ 
/*     */ import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
/*     */ import com.github.jaiimageio.plugins.tiff.TIFFField;
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
/*     */ public class TIFFT6Compressor
/*     */   extends TIFFFaxCompressor
/*     */ {
/*     */   public TIFFT6Compressor() {
/*  62 */     super("CCITT T.6", 4, true);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized int encodeT6(byte[] data, int lineStride, int colOffset, int width, int height, byte[] compData) {
/*  90 */     byte[] refData = null;
/*  91 */     int refAddr = 0;
/*  92 */     int lineAddr = 0;
/*  93 */     int outIndex = 0;
/*     */     
/*  95 */     initBitBuf();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     while (height-- != 0) {
/* 101 */       int a0 = colOffset;
/* 102 */       int last = a0 + width;
/*     */       
/* 104 */       int testbit = (data[lineAddr + (a0 >>> 3)] & 0xFF) >>> 7 - (a0 & 0x7) & 0x1;
/*     */ 
/*     */ 
/*     */       
/* 108 */       int a1 = (testbit != 0) ? a0 : nextState(data, lineAddr, a0, last);
/*     */       
/* 110 */       testbit = (refData == null) ? 0 : ((refData[refAddr + (a0 >>> 3)] & 0xFF) >>> 7 - (a0 & 0x7) & 0x1);
/*     */ 
/*     */ 
/*     */       
/* 114 */       int b1 = (testbit != 0) ? a0 : nextState(refData, refAddr, a0, last);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 119 */       int color = 0;
/*     */       
/*     */       while (true) {
/* 122 */         int b2 = nextState(refData, refAddr, b1, last);
/* 123 */         if (b2 < a1) {
/* 124 */           outIndex += add2DBits(compData, outIndex, pass, 0);
/* 125 */           a0 = b2;
/*     */         } else {
/* 127 */           int tmp = b1 - a1 + 3;
/* 128 */           if (tmp <= 6 && tmp >= 0) {
/* 129 */             outIndex += add2DBits(compData, outIndex, vert, tmp);
/* 130 */             a0 = a1;
/*     */           } else {
/* 132 */             int a2 = nextState(data, lineAddr, a1, last);
/* 133 */             outIndex += add2DBits(compData, outIndex, horz, 0);
/* 134 */             outIndex += add1DBits(compData, outIndex, a1 - a0, color);
/* 135 */             outIndex += add1DBits(compData, outIndex, a2 - a1, color ^ 0x1);
/* 136 */             a0 = a2;
/*     */           } 
/*     */         } 
/* 139 */         if (a0 >= last) {
/*     */           break;
/*     */         }
/* 142 */         color = (data[lineAddr + (a0 >>> 3)] & 0xFF) >>> 7 - (a0 & 0x7) & 0x1;
/*     */         
/* 144 */         a1 = nextState(data, lineAddr, a0, last);
/* 145 */         b1 = nextState(refData, refAddr, a0, last);
/* 146 */         testbit = (refData == null) ? 0 : ((refData[refAddr + (b1 >>> 3)] & 0xFF) >>> 7 - (b1 & 0x7) & 0x1);
/*     */ 
/*     */         
/* 149 */         if (testbit == color) {
/* 150 */           b1 = nextState(refData, refAddr, b1, last);
/*     */         }
/*     */       } 
/*     */       
/* 154 */       refData = data;
/* 155 */       refAddr = lineAddr;
/* 156 */       lineAddr += lineStride;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 163 */     outIndex += addEOFB(compData, outIndex);
/*     */ 
/*     */     
/* 166 */     if (this.inverseFill) {
/* 167 */       for (int i = 0; i < outIndex; i++) {
/* 168 */         compData[i] = TIFFFaxDecompressor.flipTable[compData[i] & 0xFF];
/*     */       }
/*     */     }
/*     */     
/* 172 */     return outIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int encode(byte[] b, int off, int width, int height, int[] bitsPerSample, int scanlineStride) throws IOException {
/* 179 */     if (bitsPerSample.length != 1 || bitsPerSample[0] != 1) {
/* 180 */       throw new IIOException("Bits per sample must be 1 for T6 compression!");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 185 */     if (this.metadata instanceof TIFFImageMetadata) {
/* 186 */       TIFFImageMetadata tim = (TIFFImageMetadata)this.metadata;
/*     */       
/* 188 */       long[] options = new long[1];
/* 189 */       options[0] = 0L;
/*     */       
/* 191 */       BaselineTIFFTagSet base = BaselineTIFFTagSet.getInstance();
/*     */       
/* 193 */       TIFFField T6Options = new TIFFField(base.getTag(293), 4, 1, options);
/*     */ 
/*     */ 
/*     */       
/* 197 */       tim.rootIFD.addTIFFField(T6Options);
/*     */     } 
/*     */ 
/*     */     
/* 201 */     int maxBits = 9 * (width + 1) / 2 + 2;
/* 202 */     int bufSize = (maxBits + 7) / 8;
/* 203 */     bufSize = height * (bufSize + 2) + 12;
/*     */     
/* 205 */     byte[] compData = new byte[bufSize];
/* 206 */     int bytes = encodeT6(b, scanlineStride, 8 * off, width, height, compData);
/*     */     
/* 208 */     this.stream.write(compData, 0, bytes);
/* 209 */     return bytes;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/tiff/TIFFT6Compressor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */