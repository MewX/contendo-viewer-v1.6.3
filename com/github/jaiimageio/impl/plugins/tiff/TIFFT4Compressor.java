/*     */ package com.github.jaiimageio.impl.plugins.tiff;
/*     */ 
/*     */ import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
/*     */ import com.github.jaiimageio.plugins.tiff.TIFFField;
/*     */ import java.io.IOException;
/*     */ import javax.imageio.IIOException;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TIFFT4Compressor
/*     */   extends TIFFFaxCompressor
/*     */ {
/*     */   private boolean is1DMode = false;
/*     */   private boolean isEOLAligned = false;
/*     */   
/*     */   public TIFFT4Compressor() {
/*  66 */     super("CCITT T.4", 3, true);
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
/*     */   public void setMetadata(IIOMetadata metadata) {
/*  82 */     super.setMetadata(metadata);
/*     */     
/*  84 */     if (metadata instanceof TIFFImageMetadata) {
/*  85 */       TIFFImageMetadata tim = (TIFFImageMetadata)metadata;
/*  86 */       TIFFField f = tim.getTIFFField(292);
/*  87 */       if (f != null) {
/*  88 */         int options = f.getAsInt(0);
/*  89 */         this.is1DMode = ((options & 0x1) == 0);
/*  90 */         this.isEOLAligned = ((options & 0x4) == 4);
/*     */       } else {
/*  92 */         long[] oarray = new long[1];
/*  93 */         oarray[0] = ((this.isEOLAligned ? true : false) | (this.is1DMode ? false : true));
/*     */ 
/*     */         
/*  96 */         BaselineTIFFTagSet base = BaselineTIFFTagSet.getInstance();
/*     */         
/*  98 */         TIFFField T4Options = new TIFFField(base.getTag(292), 4, 1, oarray);
/*     */ 
/*     */ 
/*     */         
/* 102 */         tim.rootIFD.addTIFFField(T4Options);
/*     */       } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int encodeT4(boolean is1DMode, boolean isEOLAligned, byte[] data, int lineStride, int colOffset, int width, int height, byte[] compData) {
/* 136 */     byte[] refData = data;
/* 137 */     int lineAddr = 0;
/* 138 */     int outIndex = 0;
/*     */     
/* 140 */     initBitBuf();
/*     */     
/* 142 */     int KParameter = 2;
/* 143 */     for (int numRows = 0; numRows < height; numRows++) {
/* 144 */       if (is1DMode || numRows % KParameter == 0) {
/*     */         
/* 146 */         outIndex += addEOL(is1DMode, isEOLAligned, true, compData, outIndex);
/*     */ 
/*     */ 
/*     */         
/* 150 */         outIndex += encode1D(data, lineAddr, colOffset, width, compData, outIndex);
/*     */       }
/*     */       else {
/*     */         
/* 154 */         outIndex += addEOL(is1DMode, isEOLAligned, false, compData, outIndex);
/*     */ 
/*     */ 
/*     */         
/* 158 */         int refAddr = lineAddr - lineStride;
/*     */ 
/*     */         
/* 161 */         int a0 = colOffset;
/* 162 */         int last = a0 + width;
/*     */         
/* 164 */         int testbit = (data[lineAddr + (a0 >>> 3)] & 0xFF) >>> 7 - (a0 & 0x7) & 0x1;
/*     */ 
/*     */ 
/*     */         
/* 168 */         int a1 = (testbit != 0) ? a0 : nextState(data, lineAddr, a0, last);
/*     */         
/* 170 */         testbit = (refData[refAddr + (a0 >>> 3)] & 0xFF) >>> 7 - (a0 & 0x7) & 0x1;
/*     */ 
/*     */         
/* 173 */         int b1 = (testbit != 0) ? a0 : nextState(refData, refAddr, a0, last);
/*     */ 
/*     */         
/* 176 */         int color = 0;
/*     */         
/*     */         while (true) {
/* 179 */           int b2 = nextState(refData, refAddr, b1, last);
/* 180 */           if (b2 < a1) {
/* 181 */             outIndex += add2DBits(compData, outIndex, pass, 0);
/* 182 */             a0 = b2;
/*     */           } else {
/* 184 */             int tmp = b1 - a1 + 3;
/* 185 */             if (tmp <= 6 && tmp >= 0) {
/* 186 */               outIndex += 
/* 187 */                 add2DBits(compData, outIndex, vert, tmp);
/* 188 */               a0 = a1;
/*     */             } else {
/* 190 */               int a2 = nextState(data, lineAddr, a1, last);
/* 191 */               outIndex += 
/* 192 */                 add2DBits(compData, outIndex, horz, 0);
/* 193 */               outIndex += 
/* 194 */                 add1DBits(compData, outIndex, a1 - a0, color);
/* 195 */               outIndex += 
/* 196 */                 add1DBits(compData, outIndex, a2 - a1, color ^ 0x1);
/* 197 */               a0 = a2;
/*     */             } 
/*     */           } 
/* 200 */           if (a0 >= last) {
/*     */             break;
/*     */           }
/* 203 */           color = (data[lineAddr + (a0 >>> 3)] & 0xFF) >>> 7 - (a0 & 0x7) & 0x1;
/*     */           
/* 205 */           a1 = nextState(data, lineAddr, a0, last);
/* 206 */           b1 = nextState(refData, refAddr, a0, last);
/* 207 */           testbit = (refData[refAddr + (b1 >>> 3)] & 0xFF) >>> 7 - (b1 & 0x7) & 0x1;
/*     */           
/* 209 */           if (testbit == color) {
/* 210 */             b1 = nextState(refData, refAddr, b1, last);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 216 */       lineAddr += lineStride;
/*     */     } 
/*     */     int i;
/* 219 */     for (i = 0; i < 6; i++) {
/* 220 */       outIndex += addEOL(is1DMode, isEOLAligned, true, compData, outIndex);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 227 */     while (this.ndex > 0) {
/* 228 */       compData[outIndex++] = (byte)(this.bits >>> 24);
/* 229 */       this.bits <<= 8;
/* 230 */       this.ndex -= 8;
/*     */     } 
/*     */ 
/*     */     
/* 234 */     if (this.inverseFill) {
/* 235 */       for (i = 0; i < outIndex; i++) {
/* 236 */         compData[i] = TIFFFaxDecompressor.flipTable[compData[i] & 0xFF];
/*     */       }
/*     */     }
/*     */     
/* 240 */     return outIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int encode(byte[] b, int off, int width, int height, int[] bitsPerSample, int scanlineStride) throws IOException {
/* 247 */     if (bitsPerSample.length != 1 || bitsPerSample[0] != 1) {
/* 248 */       throw new IIOException("Bits per sample must be 1 for T4 compression!");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 261 */     int maxBits = 9 * (width + 1) / 2 + 2;
/* 262 */     int bufSize = (maxBits + 7) / 8;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 267 */     bufSize = height * (bufSize + 2) + 12;
/*     */     
/* 269 */     byte[] compData = new byte[bufSize];
/*     */     
/* 271 */     int bytes = encodeT4(this.is1DMode, this.isEOLAligned, b, scanlineStride, 8 * off, width, height, compData);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 277 */     this.stream.write(compData, 0, bytes);
/* 278 */     return bytes;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/tiff/TIFFT4Compressor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */