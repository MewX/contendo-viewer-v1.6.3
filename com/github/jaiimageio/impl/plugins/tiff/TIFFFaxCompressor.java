/*     */ package com.github.jaiimageio.impl.plugins.tiff;
/*     */ 
/*     */ import com.github.jaiimageio.plugins.tiff.TIFFCompressor;
/*     */ import com.github.jaiimageio.plugins.tiff.TIFFField;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TIFFFaxCompressor
/*     */   extends TIFFCompressor
/*     */ {
/*     */   public static final int WHITE = 0;
/*     */   public static final int BLACK = 1;
/*  72 */   public static byte[] byteTable = new byte[] { 8, 7, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public static int[] termCodesBlack = new int[] { 230686730, 1073741827, -1073741822, -2147483646, 1610612739, 805306372, 536870916, 402653189, 335544326, 268435462, 134217735, 167772167, 234881031, 67108872, 117440520, 201326601, 96469002, 100663306, 33554442, 216006667, 218103819, 226492427, 115343371, 83886091, 48234507, 50331659, 211812364, 212860940, 213909516, 214958092, 109051916, 110100492, 111149068, 112197644, 220200972, 221249548, 222298124, 223346700, 224395276, 225443852, 113246220, 114294796, 228589580, 229638156, 88080396, 89128972, 90177548, 91226124, 104857612, 105906188, 85983244, 87031820, 37748748, 57671692, 58720268, 40894476, 41943052, 92274700, 93323276, 45088780, 46137356, 94371852, 106954764, 108003340 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public static int[] termCodesWhite = new int[] { 889192456, 469762054, 1879048196, -2147483644, -1342177276, -1073741820, -536870908, -268435452, -1744830459, -1610612731, 939524101, 1073741829, 536870918, 201326598, -805306362, -738197498, -1476395002, -1409286138, 1308622855, 402653191, 268435463, 771751943, 100663303, 134217735, 1342177287, 1442840583, 637534215, 1207959559, 805306375, 33554440, 50331656, 436207624, 452984840, 301989896, 318767112, 335544328, 352321544, 369098760, 385875976, 671088648, 687865864, 704643080, 721420296, 738197512, 754974728, 67108872, 83886088, 167772168, 184549384, 1375731720, 1392508936, 1409286152, 1426063368, 603979784, 620757000, 1476395016, 1493172232, 1509949448, 1526726664, 1241513992, 1258291208, 838860808, 855638024, 872415240 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public static int[] makeupCodesBlack = new int[] { 0, 62914570, 209715212, 210763788, 95420428, 53477388, 54525964, 55574540, 56623117, 57147405, 38797325, 39321613, 39845901, 40370189, 59768845, 60293133, 60817421, 61341709, 61865997, 62390285, 42991629, 43515917, 44040205, 44564493, 47185933, 47710221, 52428813, 52953101, 16777227, 25165835, 27262987, 18874380, 19922956, 20971532, 22020108, 23068684, 24117260, 29360140, 30408716, 31457292, 32505868, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   public static int[] makeupCodesWhite = new int[] { 0, -671088635, -1879048187, 1543503878, 1845493767, 905969672, 922746888, 1677721608, 1694498824, 1744830472, 1728053256, 1711276041, 1719664649, 1761607689, 1769996297, 1778384905, 1786773513, 1795162121, 1803550729, 1811939337, 1820327945, 1828716553, 1837105161, 1275068425, 1283457033, 1291845641, 1610612742, 1300234249, 16777227, 25165835, 27262987, 18874380, 19922956, 20971532, 22020108, 23068684, 24117260, 29360140, 30408716, 31457292, 32505868, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   public static int[] passMode = new int[] { 268435460 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 187 */   public static int[] vertMode = new int[] { 100663303, 201326598, 1610612739, -2147483647, 1073741827, 134217734, 67108871 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 200 */   public static int[] horzMode = new int[] { 536870915 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 207 */   public static int[][] termCodes = new int[][] { termCodesWhite, termCodesBlack };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 213 */   public static int[][] makeupCodes = new int[][] { makeupCodesWhite, makeupCodesBlack };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 219 */   public static int[][] pass = new int[][] { passMode, passMode };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 224 */   public static int[][] vert = new int[][] { vertMode, vertMode };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 229 */   public static int[][] horz = new int[][] { horzMode, horzMode };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean inverseFill = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int bits;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int ndex;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected TIFFFaxCompressor(String compressionType, int compressionTagValue, boolean isCompressionLossless) {
/* 255 */     super(compressionType, compressionTagValue, isCompressionLossless);
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
/*     */   public void setMetadata(IIOMetadata metadata) {
/* 270 */     super.setMetadata(metadata);
/*     */     
/* 272 */     if (metadata instanceof TIFFImageMetadata) {
/* 273 */       TIFFImageMetadata tim = (TIFFImageMetadata)metadata;
/* 274 */       TIFFField f = tim.getTIFFField(266);
/* 275 */       this.inverseFill = (f != null && f.getAsInt(0) == 2);
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
/*     */   public int nextState(byte[] data, int base, int bitOffset, int maxOffset) {
/*     */     int testbyte;
/* 288 */     if (data == null) {
/* 289 */       return maxOffset;
/*     */     }
/*     */     
/* 292 */     int next = base + (bitOffset >>> 3);
/*     */ 
/*     */     
/* 295 */     if (next >= data.length) {
/* 296 */       return maxOffset;
/*     */     }
/* 298 */     int end = base + (maxOffset >>> 3);
/* 299 */     if (end == data.length) {
/* 300 */       end--;
/*     */     }
/* 302 */     int extra = bitOffset & 0x7;
/*     */ 
/*     */ 
/*     */     
/* 306 */     if ((data[next] & 128 >>> extra) != 0) {
/* 307 */       testbyte = (data[next] ^ 0xFFFFFFFF) & 255 >>> extra;
/* 308 */       while (next < end && 
/* 309 */         testbyte == 0)
/*     */       {
/*     */         
/* 312 */         testbyte = (data[++next] ^ 0xFFFFFFFF) & 0xFF;
/*     */       }
/*     */     } else {
/* 315 */       if ((testbyte = data[next] & 255 >>> extra) != 0) {
/* 316 */         bitOffset = (next - base) * 8 + byteTable[testbyte];
/* 317 */         return (bitOffset < maxOffset) ? bitOffset : maxOffset;
/*     */       } 
/* 319 */       while (next < end) {
/* 320 */         if ((testbyte = data[++next] & 0xFF) != 0) {
/*     */           
/* 322 */           bitOffset = (next - base) * 8 + byteTable[testbyte];
/* 323 */           return (bitOffset < maxOffset) ? bitOffset : maxOffset;
/*     */         } 
/*     */       } 
/*     */     } 
/* 327 */     bitOffset = (next - base) * 8 + byteTable[testbyte];
/* 328 */     return (bitOffset < maxOffset) ? bitOffset : maxOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initBitBuf() {
/* 336 */     this.ndex = 0;
/* 337 */     this.bits = 0;
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
/*     */   public int add1DBits(byte[] buf, int where, int count, int color) {
/* 350 */     int len = where;
/*     */     
/* 352 */     int sixtyfours = count >>> 6;
/* 353 */     count &= 0x3F;
/* 354 */     if (sixtyfours != 0) {
/* 355 */       for (; sixtyfours > 40; sixtyfours -= 40) {
/* 356 */         int j = makeupCodes[color][40];
/* 357 */         this.bits |= (j & 0xFFF80000) >>> this.ndex;
/* 358 */         this.ndex += j & 0xFFFF;
/* 359 */         while (this.ndex > 7) {
/* 360 */           buf[len++] = (byte)(this.bits >>> 24);
/* 361 */           this.bits <<= 8;
/* 362 */           this.ndex -= 8;
/*     */         } 
/*     */       } 
/*     */       
/* 366 */       int i = makeupCodes[color][sixtyfours];
/* 367 */       this.bits |= (i & 0xFFF80000) >>> this.ndex;
/* 368 */       this.ndex += i & 0xFFFF;
/* 369 */       while (this.ndex > 7) {
/* 370 */         buf[len++] = (byte)(this.bits >>> 24);
/* 371 */         this.bits <<= 8;
/* 372 */         this.ndex -= 8;
/*     */       } 
/*     */     } 
/*     */     
/* 376 */     int mask = termCodes[color][count];
/* 377 */     this.bits |= (mask & 0xFFF80000) >>> this.ndex;
/* 378 */     this.ndex += mask & 0xFFFF;
/* 379 */     while (this.ndex > 7) {
/* 380 */       buf[len++] = (byte)(this.bits >>> 24);
/* 381 */       this.bits <<= 8;
/* 382 */       this.ndex -= 8;
/*     */     } 
/*     */     
/* 385 */     return len - where;
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
/*     */   public int add2DBits(byte[] buf, int where, int[][] mode, int entry) {
/* 397 */     int len = where;
/* 398 */     int color = 0;
/*     */     
/* 400 */     int mask = mode[color][entry];
/* 401 */     this.bits |= (mask & 0xFFF80000) >>> this.ndex;
/* 402 */     this.ndex += mask & 0xFFFF;
/* 403 */     while (this.ndex > 7) {
/* 404 */       buf[len++] = (byte)(this.bits >>> 24);
/* 405 */       this.bits <<= 8;
/* 406 */       this.ndex -= 8;
/*     */     } 
/*     */     
/* 409 */     return len - where;
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
/*     */   public int addEOL(boolean is1DMode, boolean addFill, boolean add1, byte[] buf, int where) {
/* 422 */     int len = where;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 429 */     if (addFill)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 437 */       this.ndex += (this.ndex <= 4) ? (4 - this.ndex) : (12 - this.ndex);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 443 */     if (is1DMode) {
/* 444 */       this.bits |= 1048576 >>> this.ndex;
/* 445 */       this.ndex += 12;
/*     */     } else {
/* 447 */       this.bits |= (add1 ? 1572864 : 1048576) >>> this.ndex;
/* 448 */       this.ndex += 13;
/*     */     } 
/*     */     
/* 451 */     while (this.ndex > 7) {
/* 452 */       buf[len++] = (byte)(this.bits >>> 24);
/* 453 */       this.bits <<= 8;
/* 454 */       this.ndex -= 8;
/*     */     } 
/*     */     
/* 457 */     return len - where;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int addEOFB(byte[] buf, int where) {
/* 467 */     int len = where;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 472 */     this.bits |= 1048832 >>> this.ndex;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 477 */     this.ndex += 24;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 482 */     while (this.ndex > 0) {
/* 483 */       buf[len++] = (byte)(this.bits >>> 24);
/* 484 */       this.bits <<= 8;
/* 485 */       this.ndex -= 8;
/*     */     } 
/*     */     
/* 488 */     return len - where;
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
/*     */   public int encode1D(byte[] data, int rowOffset, int colOffset, int rowLength, byte[] compData, int compOffset) {
/* 504 */     int lineAddr = rowOffset;
/* 505 */     int bitIndex = colOffset;
/*     */     
/* 507 */     int last = bitIndex + rowLength;
/* 508 */     int outIndex = compOffset;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 513 */     int testbit = (data[lineAddr + (bitIndex >>> 3)] & 0xFF) >>> 7 - (bitIndex & 0x7) & 0x1;
/*     */ 
/*     */     
/* 516 */     int currentColor = 1;
/* 517 */     if (testbit != 0) {
/* 518 */       outIndex += add1DBits(compData, outIndex, 0, 0);
/*     */     } else {
/* 520 */       currentColor = 0;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 526 */     while (bitIndex < last) {
/*     */       
/* 528 */       int bitCount = nextState(data, lineAddr, bitIndex, last) - bitIndex;
/* 529 */       outIndex += 
/* 530 */         add1DBits(compData, outIndex, bitCount, currentColor);
/* 531 */       bitIndex += bitCount;
/* 532 */       currentColor ^= 0x1;
/*     */     } 
/*     */     
/* 535 */     return outIndex - compOffset;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/tiff/TIFFFaxCompressor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */