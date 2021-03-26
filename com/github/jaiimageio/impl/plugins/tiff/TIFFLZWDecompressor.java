/*     */ package com.github.jaiimageio.impl.plugins.tiff;
/*     */ 
/*     */ import com.github.jaiimageio.plugins.tiff.TIFFDecompressor;
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
/*     */ public class TIFFLZWDecompressor
/*     */   extends TIFFDecompressor
/*     */ {
/*     */   private static final boolean DEBUG = false;
/*  61 */   private static final int[] andTable = new int[] { 511, 1023, 2047, 4095 };
/*     */   
/*     */   int predictor;
/*     */   
/*     */   byte[] srcData;
/*     */   
/*     */   byte[] dstData;
/*     */   
/*     */   int srcIndex;
/*     */   
/*     */   int dstIndex;
/*     */   
/*     */   byte[][] stringTable;
/*     */   
/*     */   int tableIndex;
/*     */   
/*  77 */   int bitsToGet = 9;
/*     */   
/*  79 */   int nextData = 0;
/*  80 */   int nextBits = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public TIFFLZWDecompressor(int predictor) throws IIOException {
/*  85 */     if (predictor != 1 && predictor != 2)
/*     */     {
/*     */       
/*  88 */       throw new IIOException("Illegal value for Predictor in TIFF file");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  96 */     this.predictor = predictor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void decodeRaw(byte[] b, int dstOffset, int bitsPerPixel, int scanlineStride) throws IOException {
/*     */     byte[] buf;
/*     */     int bufOffset;
/* 105 */     if (this.predictor == 2) {
/*     */       
/* 107 */       int len = this.bitsPerSample.length;
/* 108 */       for (int i = 0; i < len; i++) {
/* 109 */         if (this.bitsPerSample[i] != 8) {
/* 110 */           throw new IIOException(this.bitsPerSample[i] + "-bit samples " + "are not supported for Horizontal " + "differencing Predictor");
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     this.stream.seek(this.offset);
/*     */     
/* 120 */     byte[] sdata = new byte[this.byteCount];
/* 121 */     this.stream.readFully(sdata);
/*     */     
/* 123 */     int bytesPerRow = (this.srcWidth * bitsPerPixel + 7) / 8;
/*     */ 
/*     */     
/* 126 */     if (bytesPerRow == scanlineStride) {
/* 127 */       buf = b;
/* 128 */       bufOffset = dstOffset;
/*     */     } else {
/* 130 */       buf = new byte[bytesPerRow * this.srcHeight];
/* 131 */       bufOffset = 0;
/*     */     } 
/*     */     
/* 134 */     int numBytesDecoded = decode(sdata, 0, buf, bufOffset);
/*     */     
/* 136 */     if (bytesPerRow != scanlineStride) {
/*     */ 
/*     */ 
/*     */       
/* 140 */       int off = 0;
/* 141 */       for (int y = 0; y < this.srcHeight; y++) {
/* 142 */         System.arraycopy(buf, off, b, dstOffset, bytesPerRow);
/* 143 */         off += bytesPerRow;
/* 144 */         dstOffset += scanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int decode(byte[] sdata, int srcOffset, byte[] ddata, int dstOffset) throws IOException {
/* 152 */     if (sdata[0] == 0 && sdata[1] == 1) {
/* 153 */       throw new IIOException("TIFF 5.0-style LZW compression is not supported!");
/*     */     }
/*     */ 
/*     */     
/* 157 */     this.srcData = sdata;
/* 158 */     this.dstData = ddata;
/*     */     
/* 160 */     this.srcIndex = srcOffset;
/* 161 */     this.dstIndex = dstOffset;
/*     */     
/* 163 */     this.nextData = 0;
/* 164 */     this.nextBits = 0;
/*     */     
/* 166 */     initializeStringTable();
/*     */     
/* 168 */     int oldCode = 0;
/*     */     
/*     */     int code;
/* 171 */     while ((code = getNextCode()) != 257) {
/* 172 */       if (code == 256) {
/* 173 */         initializeStringTable();
/* 174 */         code = getNextCode();
/* 175 */         if (code == 257) {
/*     */           break;
/*     */         }
/*     */         
/* 179 */         writeString(this.stringTable[code]);
/* 180 */         oldCode = code; continue;
/*     */       } 
/* 182 */       if (code < this.tableIndex) {
/* 183 */         byte[] arrayOfByte = this.stringTable[code];
/*     */         
/* 185 */         writeString(arrayOfByte);
/* 186 */         addStringToTable(this.stringTable[oldCode], arrayOfByte[0]);
/* 187 */         oldCode = code; continue;
/*     */       } 
/* 189 */       byte[] string = this.stringTable[oldCode];
/* 190 */       string = composeString(string, string[0]);
/* 191 */       writeString(string);
/* 192 */       addStringToTable(string);
/* 193 */       oldCode = code;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 198 */     if (this.predictor == 2)
/*     */     {
/*     */       
/* 201 */       for (int j = 0; j < this.srcHeight; j++) {
/*     */         
/* 203 */         int count = dstOffset + this.samplesPerPixel * (j * this.srcWidth + 1);
/*     */         
/* 205 */         for (int i = this.samplesPerPixel; i < this.srcWidth * this.samplesPerPixel; i++) {
/*     */           
/* 207 */           this.dstData[count] = (byte)(this.dstData[count] + this.dstData[count - this.samplesPerPixel]);
/* 208 */           count++;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 213 */     return this.dstIndex - dstOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initializeStringTable() {
/* 220 */     this.stringTable = new byte[4096][];
/*     */     
/* 222 */     for (int i = 0; i < 256; i++) {
/* 223 */       this.stringTable[i] = new byte[1];
/* 224 */       this.stringTable[i][0] = (byte)i;
/*     */     } 
/*     */     
/* 227 */     this.tableIndex = 258;
/* 228 */     this.bitsToGet = 9;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeString(byte[] string) {
/* 235 */     if (this.dstIndex < this.dstData.length) {
/* 236 */       int maxIndex = Math.min(string.length, this.dstData.length - this.dstIndex);
/*     */ 
/*     */       
/* 239 */       for (int i = 0; i < maxIndex; i++) {
/* 240 */         this.dstData[this.dstIndex++] = string[i];
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addStringToTable(byte[] oldString, byte newString) {
/* 249 */     int length = oldString.length;
/* 250 */     byte[] string = new byte[length + 1];
/* 251 */     System.arraycopy(oldString, 0, string, 0, length);
/* 252 */     string[length] = newString;
/*     */ 
/*     */     
/* 255 */     this.stringTable[this.tableIndex++] = string;
/*     */     
/* 257 */     if (this.tableIndex == 511) {
/* 258 */       this.bitsToGet = 10;
/* 259 */     } else if (this.tableIndex == 1023) {
/* 260 */       this.bitsToGet = 11;
/* 261 */     } else if (this.tableIndex == 2047) {
/* 262 */       this.bitsToGet = 12;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addStringToTable(byte[] string) {
/* 271 */     this.stringTable[this.tableIndex++] = string;
/*     */     
/* 273 */     if (this.tableIndex == 511) {
/* 274 */       this.bitsToGet = 10;
/* 275 */     } else if (this.tableIndex == 1023) {
/* 276 */       this.bitsToGet = 11;
/* 277 */     } else if (this.tableIndex == 2047) {
/* 278 */       this.bitsToGet = 12;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] composeString(byte[] oldString, byte newString) {
/* 286 */     int length = oldString.length;
/* 287 */     byte[] string = new byte[length + 1];
/* 288 */     System.arraycopy(oldString, 0, string, 0, length);
/* 289 */     string[length] = newString;
/*     */     
/* 291 */     return string;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNextCode() {
/*     */     try {
/* 302 */       this.nextData = this.nextData << 8 | this.srcData[this.srcIndex++] & 0xFF;
/* 303 */       this.nextBits += 8;
/*     */       
/* 305 */       if (this.nextBits < this.bitsToGet) {
/* 306 */         this.nextData = this.nextData << 8 | this.srcData[this.srcIndex++] & 0xFF;
/* 307 */         this.nextBits += 8;
/*     */       } 
/*     */       
/* 310 */       int code = this.nextData >> this.nextBits - this.bitsToGet & andTable[this.bitsToGet - 9];
/*     */       
/* 312 */       this.nextBits -= this.bitsToGet;
/*     */       
/* 314 */       return code;
/* 315 */     } catch (ArrayIndexOutOfBoundsException e) {
/*     */       
/* 317 */       return 257;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/tiff/TIFFLZWDecompressor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */