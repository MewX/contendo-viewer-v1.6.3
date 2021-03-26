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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TIFFLZWUtil
/*     */ {
/*     */   private static final boolean debug = false;
/*     */   byte[] srcData;
/*     */   int srcIndex;
/*     */   byte[] dstData;
/*  67 */   int dstIndex = 0;
/*     */   byte[][] stringTable;
/*     */   int tableIndex;
/*  70 */   int bitsToGet = 9;
/*     */   int predictor;
/*     */   int samplesPerPixel;
/*  73 */   int nextData = 0;
/*  74 */   int nextBits = 0;
/*     */   
/*  76 */   private static final int[] andTable = new int[] { 511, 1023, 2047, 4095 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] decode(byte[] data, int predictor, int samplesPerPixel, int width, int height) throws IOException {
/*  85 */     if (data[0] == 0 && data[1] == 1) {
/*  86 */       throw new IIOException("TIFF 5.0-style LZW compression is not supported!");
/*     */     }
/*     */     
/*  89 */     this.srcData = data;
/*  90 */     this.srcIndex = 0;
/*  91 */     this.nextData = 0;
/*  92 */     this.nextBits = 0;
/*     */     
/*  94 */     this.dstData = new byte[8192];
/*  95 */     this.dstIndex = 0;
/*     */     
/*  97 */     initializeStringTable();
/*     */     
/*  99 */     int oldCode = 0;
/*     */     
/*     */     int code;
/* 102 */     while ((code = getNextCode()) != 257) {
/* 103 */       if (code == 256) {
/* 104 */         initializeStringTable();
/* 105 */         code = getNextCode();
/* 106 */         if (code == 257) {
/*     */           break;
/*     */         }
/*     */         
/* 110 */         writeString(this.stringTable[code]);
/* 111 */         oldCode = code; continue;
/*     */       } 
/* 113 */       if (code < this.tableIndex) {
/* 114 */         byte[] arrayOfByte = this.stringTable[code];
/*     */         
/* 116 */         writeString(arrayOfByte);
/* 117 */         addStringToTable(this.stringTable[oldCode], arrayOfByte[0]);
/* 118 */         oldCode = code; continue;
/*     */       } 
/* 120 */       byte[] string = this.stringTable[oldCode];
/* 121 */       string = composeString(string, string[0]);
/* 122 */       writeString(string);
/* 123 */       addStringToTable(string);
/* 124 */       oldCode = code;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 129 */     if (predictor == 2)
/*     */     {
/*     */       
/* 132 */       for (int j = 0; j < height; j++) {
/*     */         
/* 134 */         int count = samplesPerPixel * (j * width + 1);
/*     */         
/* 136 */         for (int i = samplesPerPixel; i < width * samplesPerPixel; i++) {
/*     */           
/* 138 */           this.dstData[count] = (byte)(this.dstData[count] + this.dstData[count - samplesPerPixel]);
/* 139 */           count++;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 144 */     byte[] newDstData = new byte[this.dstIndex];
/* 145 */     System.arraycopy(this.dstData, 0, newDstData, 0, this.dstIndex);
/* 146 */     return newDstData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initializeStringTable() {
/* 153 */     this.stringTable = new byte[4096][];
/*     */     
/* 155 */     for (int i = 0; i < 256; i++) {
/* 156 */       this.stringTable[i] = new byte[1];
/* 157 */       this.stringTable[i][0] = (byte)i;
/*     */     } 
/*     */     
/* 160 */     this.tableIndex = 258;
/* 161 */     this.bitsToGet = 9;
/*     */   }
/*     */   
/*     */   private void ensureCapacity(int bytesToAdd) {
/* 165 */     if (this.dstIndex + bytesToAdd > this.dstData.length) {
/* 166 */       byte[] newDstData = new byte[Math.max((int)(this.dstData.length * 1.2F), this.dstIndex + bytesToAdd)];
/*     */       
/* 168 */       System.arraycopy(this.dstData, 0, newDstData, 0, this.dstData.length);
/* 169 */       this.dstData = newDstData;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeString(byte[] string) {
/* 177 */     ensureCapacity(string.length);
/* 178 */     for (int i = 0; i < string.length; i++) {
/* 179 */       this.dstData[this.dstIndex++] = string[i];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addStringToTable(byte[] oldString, byte newString) {
/* 187 */     int length = oldString.length;
/* 188 */     byte[] string = new byte[length + 1];
/* 189 */     System.arraycopy(oldString, 0, string, 0, length);
/* 190 */     string[length] = newString;
/*     */ 
/*     */     
/* 193 */     this.stringTable[this.tableIndex++] = string;
/*     */     
/* 195 */     if (this.tableIndex == 511) {
/* 196 */       this.bitsToGet = 10;
/* 197 */     } else if (this.tableIndex == 1023) {
/* 198 */       this.bitsToGet = 11;
/* 199 */     } else if (this.tableIndex == 2047) {
/* 200 */       this.bitsToGet = 12;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addStringToTable(byte[] string) {
/* 209 */     this.stringTable[this.tableIndex++] = string;
/*     */     
/* 211 */     if (this.tableIndex == 511) {
/* 212 */       this.bitsToGet = 10;
/* 213 */     } else if (this.tableIndex == 1023) {
/* 214 */       this.bitsToGet = 11;
/* 215 */     } else if (this.tableIndex == 2047) {
/* 216 */       this.bitsToGet = 12;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] composeString(byte[] oldString, byte newString) {
/* 224 */     int length = oldString.length;
/* 225 */     byte[] string = new byte[length + 1];
/* 226 */     System.arraycopy(oldString, 0, string, 0, length);
/* 227 */     string[length] = newString;
/*     */     
/* 229 */     return string;
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
/* 240 */       this.nextData = this.nextData << 8 | this.srcData[this.srcIndex++] & 0xFF;
/* 241 */       this.nextBits += 8;
/*     */       
/* 243 */       if (this.nextBits < this.bitsToGet) {
/* 244 */         this.nextData = this.nextData << 8 | this.srcData[this.srcIndex++] & 0xFF;
/* 245 */         this.nextBits += 8;
/*     */       } 
/*     */       
/* 248 */       int code = this.nextData >> this.nextBits - this.bitsToGet & andTable[this.bitsToGet - 9];
/*     */       
/* 250 */       this.nextBits -= this.bitsToGet;
/*     */       
/* 252 */       return code;
/* 253 */     } catch (ArrayIndexOutOfBoundsException e) {
/*     */       
/* 255 */       return 257;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/tiff/TIFFLZWUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */