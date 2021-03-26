/*     */ package org.apache.xmlgraphics.image.codec.tiff;
/*     */ 
/*     */ import org.apache.xmlgraphics.image.codec.util.PropertyUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TIFFLZWDecoder
/*     */ {
/*     */   byte[][] stringTable;
/*     */   byte[] data;
/*     */   byte[] uncompData;
/*     */   int tableIndex;
/*  39 */   int bitsToGet = 9;
/*     */   
/*     */   int bytePointer;
/*     */   
/*     */   int dstIndex;
/*     */   int w;
/*     */   int predictor;
/*     */   int samplesPerPixel;
/*     */   int nextData;
/*     */   int nextBits;
/*  49 */   int[] andTable = new int[] { 511, 1023, 2047, 4095 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TIFFLZWDecoder(int w, int predictor, int samplesPerPixel) {
/*  57 */     this.w = w;
/*  58 */     this.predictor = predictor;
/*  59 */     this.samplesPerPixel = samplesPerPixel;
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
/*     */   public byte[] decode(byte[] data, byte[] uncompData, int h) {
/*  71 */     if (data[0] == 0 && data[1] == 1) {
/*  72 */       throw new UnsupportedOperationException(PropertyUtil.getString("TIFFLZWDecoder0"));
/*     */     }
/*     */     
/*  75 */     initializeStringTable();
/*     */     
/*  77 */     this.data = data;
/*     */     
/*  79 */     this.uncompData = uncompData;
/*     */ 
/*     */     
/*  82 */     this.bytePointer = 0;
/*     */     
/*  84 */     this.dstIndex = 0;
/*     */ 
/*     */     
/*  87 */     this.nextData = 0;
/*  88 */     this.nextBits = 0;
/*     */ 
/*     */     
/*  91 */     int oldCode = 0;
/*     */     
/*     */     int code;
/*     */     
/*  95 */     while ((code = getNextCode()) != 257 && this.dstIndex != uncompData.length) {
/*     */       
/*  97 */       if (code == 256) {
/*     */         
/*  99 */         initializeStringTable();
/* 100 */         code = getNextCode();
/*     */         
/* 102 */         if (code == 257) {
/*     */           break;
/*     */         }
/*     */         
/* 106 */         writeString(this.stringTable[code]);
/* 107 */         oldCode = code;
/*     */         
/*     */         continue;
/*     */       } 
/* 111 */       if (code < this.tableIndex) {
/*     */         
/* 113 */         byte[] arrayOfByte = this.stringTable[code];
/*     */         
/* 115 */         writeString(arrayOfByte);
/* 116 */         addStringToTable(this.stringTable[oldCode], arrayOfByte[0]);
/* 117 */         oldCode = code;
/*     */         
/*     */         continue;
/*     */       } 
/* 121 */       byte[] string = this.stringTable[oldCode];
/* 122 */       string = composeString(string, string[0]);
/* 123 */       writeString(string);
/* 124 */       addStringToTable(string);
/* 125 */       oldCode = code;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 133 */     if (this.predictor == 2)
/*     */     {
/*     */       
/* 136 */       for (int j = 0; j < h; j++) {
/*     */         
/* 138 */         int count = this.samplesPerPixel * (j * this.w + 1);
/*     */         
/* 140 */         for (int i = this.samplesPerPixel; i < this.w * this.samplesPerPixel; i++) {
/*     */           
/* 142 */           uncompData[count] = (byte)(uncompData[count] + uncompData[count - this.samplesPerPixel]);
/* 143 */           count++;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 148 */     return uncompData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initializeStringTable() {
/* 157 */     this.stringTable = new byte[4096][];
/*     */     
/* 159 */     for (int i = 0; i < 256; i++) {
/* 160 */       this.stringTable[i] = new byte[1];
/* 161 */       this.stringTable[i][0] = (byte)i;
/*     */     } 
/*     */     
/* 164 */     this.tableIndex = 258;
/* 165 */     this.bitsToGet = 9;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeString(byte[] string) {
/* 173 */     for (int i = 0; i < string.length; i++) {
/* 174 */       this.uncompData[this.dstIndex++] = string[i];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addStringToTable(byte[] oldString, byte newString) {
/* 182 */     int length = oldString.length;
/* 183 */     byte[] string = new byte[length + 1];
/* 184 */     System.arraycopy(oldString, 0, string, 0, length);
/* 185 */     string[length] = newString;
/*     */ 
/*     */     
/* 188 */     this.stringTable[this.tableIndex++] = string;
/*     */     
/* 190 */     if (this.tableIndex == 511) {
/* 191 */       this.bitsToGet = 10;
/* 192 */     } else if (this.tableIndex == 1023) {
/* 193 */       this.bitsToGet = 11;
/* 194 */     } else if (this.tableIndex == 2047) {
/* 195 */       this.bitsToGet = 12;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addStringToTable(byte[] string) {
/* 205 */     this.stringTable[this.tableIndex++] = string;
/*     */     
/* 207 */     if (this.tableIndex == 511) {
/* 208 */       this.bitsToGet = 10;
/* 209 */     } else if (this.tableIndex == 1023) {
/* 210 */       this.bitsToGet = 11;
/* 211 */     } else if (this.tableIndex == 2047) {
/* 212 */       this.bitsToGet = 12;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] composeString(byte[] oldString, byte newString) {
/* 220 */     int length = oldString.length;
/* 221 */     byte[] string = new byte[length + 1];
/* 222 */     System.arraycopy(oldString, 0, string, 0, length);
/* 223 */     string[length] = newString;
/*     */     
/* 225 */     return string;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNextCode() {
/*     */     try {
/* 235 */       this.nextData = this.nextData << 8 | this.data[this.bytePointer++] & 0xFF;
/* 236 */       this.nextBits += 8;
/*     */       
/* 238 */       if (this.nextBits < this.bitsToGet) {
/* 239 */         this.nextData = this.nextData << 8 | this.data[this.bytePointer++] & 0xFF;
/* 240 */         this.nextBits += 8;
/*     */       } 
/*     */       
/* 243 */       int code = this.nextData >> this.nextBits - this.bitsToGet & this.andTable[this.bitsToGet - 9];
/*     */       
/* 245 */       this.nextBits -= this.bitsToGet;
/*     */       
/* 247 */       return code;
/* 248 */     } catch (ArrayIndexOutOfBoundsException e) {
/*     */       
/* 250 */       return 257;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/tiff/TIFFLZWDecoder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */