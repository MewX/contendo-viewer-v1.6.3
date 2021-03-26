/*     */ package com.levigo.jbig2.decoder.mmr;
/*     */ 
/*     */ import com.levigo.jbig2.Bitmap;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
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
/*     */ public class MMRDecompressor
/*     */ {
/*     */   private int width;
/*     */   private int height;
/*     */   private static final int FIRST_LEVEL_TABLE_SIZE = 8;
/*     */   private static final int FIRST_LEVEL_TABLE_MASK = 255;
/*     */   private static final int SECOND_LEVEL_TABLE_SIZE = 5;
/*     */   private static final int SECOND_LEVEL_TABLE_MASK = 31;
/*     */   
/*     */   private final class RunData
/*     */   {
/*     */     private static final int MAX_RUN_DATA_BUFFER = 131072;
/*     */     private static final int MIN_RUN_DATA_BUFFER = 3;
/*     */     private static final int CODE_OFFSET = 24;
/*     */     ImageInputStream stream;
/*     */     int offset;
/*  48 */     int lastOffset = 0;
/*  49 */     int lastCode = 0;
/*     */     
/*     */     byte[] buffer;
/*     */     int bufferBase;
/*     */     int bufferTop;
/*     */     
/*     */     RunData(ImageInputStream param1ImageInputStream) {
/*  56 */       this.stream = param1ImageInputStream;
/*  57 */       this.offset = 0;
/*  58 */       this.lastOffset = 1;
/*     */       
/*     */       try {
/*  61 */         long l = param1ImageInputStream.length();
/*     */         
/*  63 */         l = Math.min(Math.max(3L, l), 131072L);
/*     */         
/*  65 */         this.buffer = new byte[(int)l];
/*  66 */         fillBuffer(0);
/*  67 */       } catch (IOException iOException) {
/*  68 */         this.buffer = new byte[10];
/*  69 */         iOException.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/*     */     private final MMRDecompressor.Code uncompressGetCode(MMRDecompressor.Code[] param1ArrayOfCode) {
/*  74 */       return uncompressGetCodeLittleEndian(param1ArrayOfCode);
/*     */     }
/*     */     
/*     */     private final MMRDecompressor.Code uncompressGetCodeLittleEndian(MMRDecompressor.Code[] param1ArrayOfCode) {
/*  78 */       int i = uncompressGetNextCodeLittleEndian() & 0xFFFFFF;
/*  79 */       MMRDecompressor.Code code = param1ArrayOfCode[i >> 16];
/*     */ 
/*     */       
/*  82 */       if (null != code && null != code.subTable) {
/*  83 */         code = code.subTable[i >> 11 & 0x1F];
/*     */       }
/*     */ 
/*     */       
/*  87 */       return code;
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
/*     */     private final int uncompressGetNextCodeLittleEndian() {
/*     */       try {
/* 102 */         int i = this.offset - this.lastOffset;
/*     */ 
/*     */         
/* 105 */         if (i < 0 || i > 24) {
/*     */           
/* 107 */           int j = (this.offset >> 3) - this.bufferBase;
/*     */           
/* 109 */           if (j >= this.bufferTop) {
/* 110 */             j += this.bufferBase;
/* 111 */             fillBuffer(j);
/* 112 */             j -= this.bufferBase;
/*     */           } 
/*     */           
/* 115 */           this.lastCode = (this.buffer[j] & 0xFF) << 16 | (this.buffer[j + 1] & 0xFF) << 8 | this.buffer[j + 2] & 0xFF;
/*     */ 
/*     */           
/* 118 */           int k = this.offset & 0x7;
/* 119 */           this.lastCode <<= k;
/*     */         } else {
/*     */           
/* 122 */           int j = this.lastOffset & 0x7;
/* 123 */           int k = 7 - j;
/*     */ 
/*     */           
/* 126 */           if (i <= k) {
/* 127 */             this.lastCode <<= i;
/*     */           } else {
/* 129 */             int m = (this.lastOffset >> 3) + 3 - this.bufferBase;
/*     */             
/* 131 */             if (m >= this.bufferTop) {
/* 132 */               m += this.bufferBase;
/* 133 */               fillBuffer(m);
/* 134 */               m -= this.bufferBase;
/*     */             } 
/*     */             
/* 137 */             j = 8 - j;
/*     */             do {
/* 139 */               this.lastCode <<= j;
/* 140 */               this.lastCode |= this.buffer[m] & 0xFF;
/* 141 */               i -= j;
/* 142 */               m++;
/* 143 */               j = 8;
/* 144 */             } while (i >= 8);
/*     */             
/* 146 */             this.lastCode <<= i;
/*     */           } 
/*     */         } 
/* 149 */         this.lastOffset = this.offset;
/*     */         
/* 151 */         return this.lastCode;
/* 152 */       } catch (IOException iOException) {
/*     */         
/* 154 */         throw new ArrayIndexOutOfBoundsException("Corrupted RLE data caused by an IOException while reading raw data: " + iOException.toString());
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     private void fillBuffer(int param1Int) throws IOException {
/* 160 */       this.bufferBase = param1Int;
/* 161 */       synchronized (this.stream) {
/*     */         try {
/* 163 */           this.stream.seek(param1Int);
/* 164 */           this.bufferTop = this.stream.read(this.buffer);
/* 165 */         } catch (EOFException eOFException) {
/*     */           
/* 167 */           this.bufferTop = -1;
/*     */         } 
/*     */         
/* 170 */         if (this.bufferTop > -1 && this.bufferTop < 3) {
/*     */ 
/*     */ 
/*     */           
/* 174 */           int i = 0;
/* 175 */           while (this.bufferTop < 3) {
/*     */             try {
/* 177 */               i = this.stream.read();
/* 178 */             } catch (EOFException eOFException) {
/* 179 */               i = -1;
/*     */             } 
/* 181 */             this.buffer[this.bufferTop++] = (i == -1) ? 0 : (byte)(i & 0xFF);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 186 */       this.bufferTop -= 3;
/*     */       
/* 188 */       if (this.bufferTop < 0) {
/*     */         
/* 190 */         Arrays.fill(this.buffer, (byte)0);
/* 191 */         this.bufferTop = this.buffer.length - 3;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void align() {
/* 199 */       this.offset = this.offset + 7 >> 3 << 3;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Code {
/* 204 */     Code[] subTable = null;
/*     */     
/*     */     final int bitLength;
/*     */     
/*     */     Code(int[] param1ArrayOfint) {
/* 209 */       this.bitLength = param1ArrayOfint[0];
/* 210 */       this.codeWord = param1ArrayOfint[1];
/* 211 */       this.runLength = param1ArrayOfint[2];
/*     */     }
/*     */     final int codeWord; final int runLength;
/*     */     public String toString() {
/* 215 */       return this.bitLength + "/" + this.codeWord + "/" + this.runLength;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean equals(Object param1Object) {
/* 222 */       return (param1Object instanceof Code && ((Code)param1Object).bitLength == this.bitLength && ((Code)param1Object).codeWord == this.codeWord && ((Code)param1Object).runLength == this.runLength);
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
/* 234 */   private static Code[] whiteTable = null;
/* 235 */   private static Code[] blackTable = null;
/* 236 */   private static Code[] modeTable = null;
/*     */   
/*     */   private RunData data;
/*     */   
/*     */   private static final synchronized void initTables() {
/* 241 */     if (null == whiteTable) {
/* 242 */       whiteTable = createLittleEndianTable(MMRConstants.WhiteCodes);
/* 243 */       blackTable = createLittleEndianTable(MMRConstants.BlackCodes);
/* 244 */       modeTable = createLittleEndianTable(MMRConstants.ModeCodes);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private final int uncompress2D(RunData paramRunData, int[] paramArrayOfint1, int paramInt1, int[] paramArrayOfint2, int paramInt2) {
/* 250 */     byte b1 = 0;
/* 251 */     byte b2 = 0;
/* 252 */     int i = 0;
/*     */     
/* 254 */     boolean bool = true;
/* 255 */     Code code = null;
/*     */     
/* 257 */     paramArrayOfint1[paramInt1 + 1] = paramInt2; paramArrayOfint1[paramInt1] = paramInt2;
/* 258 */     paramArrayOfint1[paramInt1 + 3] = paramInt2 + 1; paramArrayOfint1[paramInt1 + 2] = paramInt2 + 1;
/*     */     
/*     */     try {
/* 261 */       while (i < paramInt2) {
/*     */         int j, k;
/*     */         
/* 264 */         code = paramRunData.uncompressGetCode(modeTable);
/*     */         
/* 266 */         if (code == null) {
/* 267 */           paramRunData.offset++;
/*     */           
/*     */           break;
/*     */         } 
/*     */         
/* 272 */         paramRunData.offset += code.bitLength;
/*     */         
/* 274 */         switch (code.runLength) {
/*     */           case 2:
/* 276 */             i = paramArrayOfint1[b1];
/*     */             break;
/*     */           
/*     */           case 3:
/* 280 */             i = paramArrayOfint1[b1] + 1;
/*     */             break;
/*     */           
/*     */           case 6:
/* 284 */             i = paramArrayOfint1[b1] - 1;
/*     */             break;
/*     */           
/*     */           case 1:
/* 288 */             for (j = 1; j; ) {
/*     */               
/* 290 */               code = paramRunData.uncompressGetCode((bool == true) ? whiteTable : blackTable);
/*     */               
/* 292 */               if (code == null) {
/*     */                 // Byte code: goto -> 888
/*     */               }
/* 295 */               paramRunData.offset += code.bitLength;
/* 296 */               if (code.runLength < 64) {
/* 297 */                 if (code.runLength < 0) {
/* 298 */                   paramArrayOfint2[b2++] = i;
/* 299 */                   code = null;
/*     */                   // Byte code: goto -> 888
/*     */                 } 
/* 302 */                 i += code.runLength;
/* 303 */                 paramArrayOfint2[b2++] = i;
/*     */                 break;
/*     */               } 
/* 306 */               i += code.runLength;
/*     */             } 
/*     */             
/* 309 */             j = i;
/* 310 */             for (k = 1; k; ) {
/* 311 */               code = paramRunData.uncompressGetCode((bool != true) ? whiteTable : blackTable);
/* 312 */               if (code == null) {
/*     */                 // Byte code: goto -> 888
/*     */               }
/* 315 */               paramRunData.offset += code.bitLength;
/* 316 */               if (code.runLength < 64) {
/* 317 */                 if (code.runLength < 0) {
/* 318 */                   paramArrayOfint2[b2++] = i;
/*     */                   // Byte code: goto -> 888
/*     */                 } 
/* 321 */                 i += code.runLength;
/*     */                 
/* 323 */                 if (i < paramInt2 || i != j)
/* 324 */                   paramArrayOfint2[b2++] = i; 
/*     */                 break;
/*     */               } 
/* 327 */               i += code.runLength;
/*     */             } 
/*     */             
/* 330 */             while (i < paramInt2 && paramArrayOfint1[b1] <= i) {
/* 331 */               b1 += true;
/*     */             }
/*     */             continue;
/*     */           
/*     */           case 0:
/* 336 */             b1++;
/* 337 */             i = paramArrayOfint1[b1++];
/*     */             continue;
/*     */           
/*     */           case 4:
/* 341 */             i = paramArrayOfint1[b1] + 2;
/*     */             break;
/*     */           
/*     */           case 7:
/* 345 */             i = paramArrayOfint1[b1] - 2;
/*     */             break;
/*     */           
/*     */           case 5:
/* 349 */             i = paramArrayOfint1[b1] + 3;
/*     */             break;
/*     */           
/*     */           case 8:
/* 353 */             i = paramArrayOfint1[b1] - 3;
/*     */             break;
/*     */ 
/*     */           
/*     */           default:
/* 358 */             System.err.println("Should not happen!");
/*     */             
/* 360 */             if (paramRunData.offset == 12 && code.runLength == -1) {
/* 361 */               paramRunData.offset = 0;
/* 362 */               uncompress1D(paramRunData, paramArrayOfint1, paramInt2);
/* 363 */               paramRunData.offset++;
/* 364 */               uncompress1D(paramRunData, paramArrayOfint2, paramInt2);
/* 365 */               k = uncompress1D(paramRunData, paramArrayOfint1, paramInt2);
/* 366 */               paramRunData.offset++;
/* 367 */               return k;
/*     */             } 
/* 369 */             i = paramInt2;
/*     */             continue;
/*     */         } 
/*     */ 
/*     */         
/* 374 */         if (i <= paramInt2) {
/* 375 */           bool = !bool ? true : false;
/*     */           
/* 377 */           paramArrayOfint2[b2++] = i;
/*     */           
/* 379 */           if (b1 > 0) {
/* 380 */             b1--;
/*     */           } else {
/* 382 */             b1++;
/*     */           } 
/*     */           
/* 385 */           while (i < paramInt2 && paramArrayOfint1[b1] <= i) {
/* 386 */             b1 += 2;
/*     */           }
/*     */         } 
/*     */       } 
/* 390 */     } catch (Throwable throwable) {
/* 391 */       StringBuffer stringBuffer = new StringBuffer();
/* 392 */       stringBuffer.append("whiteRun           = ");
/* 393 */       stringBuffer.append(bool);
/* 394 */       stringBuffer.append("\n");
/* 395 */       stringBuffer.append("code               = ");
/* 396 */       stringBuffer.append(code);
/* 397 */       stringBuffer.append("\n");
/* 398 */       stringBuffer.append("refOffset          = ");
/* 399 */       stringBuffer.append(b1);
/* 400 */       stringBuffer.append("\n");
/* 401 */       stringBuffer.append("curOffset          = ");
/* 402 */       stringBuffer.append(b2);
/* 403 */       stringBuffer.append("\n");
/* 404 */       stringBuffer.append("bitPos             = ");
/* 405 */       stringBuffer.append(i);
/* 406 */       stringBuffer.append("\n");
/* 407 */       stringBuffer.append("runData.offset = ");
/* 408 */       stringBuffer.append(paramRunData.offset);
/* 409 */       stringBuffer.append(" ( byte:");
/* 410 */       stringBuffer.append(paramRunData.offset / 8);
/* 411 */       stringBuffer.append(", bit:");
/* 412 */       stringBuffer.append(paramRunData.offset & 0x7);
/* 413 */       stringBuffer.append(" )");
/*     */       
/* 415 */       System.out.println(stringBuffer.toString());
/*     */       
/* 417 */       return -3;
/*     */     } 
/*     */     
/* 420 */     if (paramArrayOfint2[b2] != paramInt2) {
/* 421 */       paramArrayOfint2[b2] = paramInt2;
/*     */     }
/*     */     
/* 424 */     if (code == null) {
/* 425 */       return -1;
/*     */     }
/* 427 */     return b2;
/*     */   }
/*     */   
/*     */   public MMRDecompressor(int paramInt1, int paramInt2, ImageInputStream paramImageInputStream) {
/* 431 */     this.width = paramInt1;
/* 432 */     this.height = paramInt2;
/*     */     
/* 434 */     this.data = new RunData(paramImageInputStream);
/*     */     
/* 436 */     initTables();
/*     */   }
/*     */   
/*     */   public Bitmap uncompress() {
/* 440 */     Bitmap bitmap = new Bitmap(this.width, this.height);
/*     */     
/* 442 */     int[] arrayOfInt1 = new int[this.width + 5];
/* 443 */     int[] arrayOfInt2 = new int[this.width + 5];
/* 444 */     arrayOfInt2[0] = this.width;
/* 445 */     int i = 1;
/*     */     
/* 447 */     int j = 0;
/*     */     
/* 449 */     for (byte b = 0; b < this.height; b++) {
/* 450 */       j = uncompress2D(this.data, arrayOfInt2, i, arrayOfInt1, this.width);
/*     */       
/* 452 */       if (j == -3) {
/*     */         break;
/*     */       }
/*     */       
/* 456 */       if (j > 0) {
/* 457 */         fillBitmap(bitmap, b, arrayOfInt1, j);
/*     */       }
/*     */ 
/*     */       
/* 461 */       int[] arrayOfInt = arrayOfInt2;
/* 462 */       arrayOfInt2 = arrayOfInt1;
/* 463 */       arrayOfInt1 = arrayOfInt;
/* 464 */       i = j;
/*     */     } 
/*     */     
/* 467 */     detectAndSkipEOL();
/*     */     
/* 469 */     this.data.align();
/*     */     
/* 471 */     return bitmap;
/*     */   }
/*     */   
/*     */   private void detectAndSkipEOL() {
/*     */     while (true) {
/* 476 */       Code code = this.data.uncompressGetCode(modeTable);
/* 477 */       if (null != code && code.runLength == -1) {
/* 478 */         this.data.offset += code.bitLength;
/*     */         continue;
/*     */       } 
/*     */       break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void fillBitmap(Bitmap paramBitmap, int paramInt1, int[] paramArrayOfint, int paramInt2) {
/* 486 */     byte b1 = 0;
/* 487 */     int i = paramBitmap.getByteIndex(0, paramInt1);
/* 488 */     byte b = 0;
/* 489 */     for (byte b2 = 0; b2 < paramInt2; b2++) {
/*     */       boolean bool;
/* 491 */       int j = paramArrayOfint[b2];
/*     */ 
/*     */       
/* 494 */       if ((b2 & 0x1) == 0) {
/* 495 */         bool = false;
/*     */       } else {
/* 497 */         bool = true;
/*     */       } 
/*     */       
/* 500 */       while (b1 < j) {
/* 501 */         b = (byte)(b << 1 | bool);
/* 502 */         b1++;
/*     */         
/* 504 */         if ((b1 & 0x7) == 0) {
/* 505 */           paramBitmap.setByte(i++, b);
/* 506 */           b = 0;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 511 */     if ((b1 & 0x7) != 0) {
/* 512 */       b = (byte)(b << 8 - (b1 & 0x7));
/* 513 */       paramBitmap.setByte(i, b);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private final int uncompress1D(RunData paramRunData, int[] paramArrayOfint, int paramInt) {
/* 519 */     boolean bool = true;
/* 520 */     int i = 0;
/* 521 */     Code code = null;
/* 522 */     byte b = 0;
/*     */     
/* 524 */     while (i < paramInt) {
/*     */       while (true) {
/* 526 */         if (bool) {
/* 527 */           code = paramRunData.uncompressGetCode(whiteTable);
/*     */         } else {
/* 529 */           code = paramRunData.uncompressGetCode(blackTable);
/*     */         } 
/*     */         
/* 532 */         paramRunData.offset += code.bitLength;
/*     */         
/* 534 */         if (code.runLength < 0) {
/*     */           break;
/*     */         }
/*     */         
/* 538 */         i += code.runLength;
/*     */         
/* 540 */         if (code.runLength < 64) {
/* 541 */           if (!bool); bool = false;
/* 542 */           paramArrayOfint[b++] = i;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 548 */     if (paramArrayOfint[b] != paramInt) {
/* 549 */       paramArrayOfint[b] = paramInt;
/*     */     }
/*     */     
/* 552 */     return (code != null && code.runLength != -1) ? b : -1;
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
/*     */   private static Code[] createLittleEndianTable(int[][] paramArrayOfint) {
/* 575 */     Code[] arrayOfCode = new Code[256];
/* 576 */     for (byte b = 0; b < paramArrayOfint.length; b++) {
/* 577 */       Code code = new Code(paramArrayOfint[b]);
/*     */       
/* 579 */       if (code.bitLength <= 8) {
/* 580 */         int i = 8 - code.bitLength;
/* 581 */         int j = code.codeWord << i;
/*     */         
/* 583 */         for (int k = (1 << i) - 1; k >= 0; k--) {
/* 584 */           int m = j | k;
/* 585 */           arrayOfCode[m] = code;
/*     */         } 
/*     */       } else {
/*     */         
/* 589 */         int i = code.codeWord >>> code.bitLength - 8;
/*     */         
/* 591 */         if (arrayOfCode[i] == null) {
/* 592 */           Code code1 = new Code(new int[3]);
/* 593 */           code1.subTable = new Code[32];
/* 594 */           arrayOfCode[i] = code1;
/*     */         } 
/*     */ 
/*     */         
/* 598 */         if (code.bitLength <= 13) {
/* 599 */           Code[] arrayOfCode1 = (arrayOfCode[i]).subTable;
/* 600 */           int j = 13 - code.bitLength;
/* 601 */           int k = code.codeWord << j & 0x1F;
/*     */           
/* 603 */           for (int m = (1 << j) - 1; m >= 0; m--) {
/* 604 */             arrayOfCode1[k | m] = code;
/*     */           }
/*     */         } else {
/* 607 */           throw new IllegalArgumentException("Code table overflow in MMRDecompressor");
/*     */         } 
/*     */       } 
/* 610 */     }  return arrayOfCode;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/decoder/mmr/MMRDecompressor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */