/*     */ package net.zamasoft.a.b;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   implements Serializable
/*     */ {
/*  35 */   private Logger a = Logger.getLogger(a.class.getName());
/*     */ 
/*     */ 
/*     */   
/*     */   private static final long b = 0L;
/*     */ 
/*     */   
/*     */   private static final long c = 55232L;
/*     */ 
/*     */   
/*     */   private static final long d = -56613888L;
/*     */ 
/*     */   
/*     */   private int[] e;
/*     */ 
/*     */   
/*     */   private Map<Integer, Integer> f;
/*     */ 
/*     */ 
/*     */   
/*     */   public a(int format, int numGlyphs, RandomAccessFile data) throws IOException {
/*  56 */     if (format < 8) {
/*  57 */       data.readUnsignedShort();
/*  58 */       data.readUnsignedShort();
/*     */     } else {
/*     */       
/*  61 */       data.readUnsignedShort();
/*  62 */       data.readInt();
/*  63 */       data.readInt();
/*     */     } 
/*  65 */     switch (format) {
/*     */       case 0:
/*  67 */         a(data);
/*     */         return;
/*     */       case 2:
/*  70 */         i(data, numGlyphs);
/*     */         return;
/*     */       case 4:
/*  73 */         h(data, numGlyphs);
/*     */         return;
/*     */       case 6:
/*  76 */         g(data, numGlyphs);
/*     */         return;
/*     */       case 8:
/*  79 */         b(data, numGlyphs);
/*     */         return;
/*     */       case 10:
/*  82 */         c(data, numGlyphs);
/*     */         return;
/*     */       case 12:
/*  85 */         d(data, numGlyphs);
/*     */         return;
/*     */       case 13:
/*  88 */         e(data, numGlyphs);
/*     */         return;
/*     */       case 14:
/*  91 */         f(data, numGlyphs);
/*     */         return;
/*     */     } 
/*  94 */     throw new IOException("Unknown cmap format:" + format);
/*     */   }
/*     */ 
/*     */   
/*     */   private int[] j(RandomAccessFile raf, int length) throws IOException {
/*  99 */     int[] array = new int[length];
/* 100 */     for (int i = 0; i < length; i++) {
/* 101 */       array[i] = raf.read();
/*     */     }
/* 103 */     return array;
/*     */   }
/*     */   
/*     */   public int[] a(RandomAccessFile raf, int length) throws IOException {
/* 107 */     int[] array = new int[length];
/* 108 */     for (int i = 0; i < length; i++) {
/* 109 */       array[i] = raf.readUnsignedShort();
/*     */     }
/* 111 */     return array;
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
/*     */   protected void b(RandomAccessFile raf, int numGlyphs) throws IOException {
/* 126 */     int[] is32 = j(raf, 8192);
/* 127 */     long nbGroups = raf.readInt();
/*     */     
/* 129 */     if (nbGroups > 65536L) {
/* 130 */       throw new IOException("CMap ( Subtype8 ) is invalid");
/*     */     }
/* 132 */     this.e = c(numGlyphs);
/* 133 */     this.f = new HashMap<>(numGlyphs);
/*     */     long i;
/* 135 */     for (i = 0L; i < nbGroups; i++) {
/* 136 */       long firstCode = raf.readInt();
/* 137 */       long endCode = raf.readInt();
/* 138 */       long startGlyph = raf.readInt();
/*     */       
/* 140 */       if (firstCode > endCode || 0L > firstCode)
/* 141 */         throw new IOException("Range invalid"); 
/*     */       long j;
/* 143 */       for (j = firstCode; j <= endCode; j++) {
/*     */         int currentCharCode;
/* 145 */         if (j > 2147483647L) {
/* 146 */           throw new IOException("[Sub Format 8] Invalid Character code");
/*     */         }
/*     */         
/* 149 */         if ((is32[(int)j / 8] & 1 << (int)j % 8) == 0) {
/* 150 */           currentCharCode = (int)j;
/*     */         }
/*     */         else {
/*     */           
/* 154 */           long lead = 55232L + (j >> 10L);
/* 155 */           long trail = 56320L + (j & 0x3FFL);
/* 156 */           long codepoint = (lead << 10L) + trail + -56613888L;
/* 157 */           if (codepoint > 2147483647L) {
/* 158 */             throw new IOException("[Sub Format 8] Invalid Character code");
/*     */           }
/* 160 */           currentCharCode = (int)codepoint;
/*     */         } 
/* 162 */         long glyphIndex = startGlyph + j - firstCode;
/* 163 */         if (glyphIndex > numGlyphs || glyphIndex > 2147483647L) {
/* 164 */           throw new IOException("CMap contains an invalid glyph index");
/*     */         }
/* 166 */         this.e[(int)glyphIndex] = currentCharCode;
/* 167 */         this.f.put(Integer.valueOf(currentCharCode), Integer.valueOf((int)glyphIndex));
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
/*     */   protected void c(RandomAccessFile data, int numGlyphs) throws IOException {
/* 183 */     long startCode = data.readInt();
/* 184 */     long numChars = data.readInt();
/* 185 */     if (numChars > 2147483647L) {
/* 186 */       throw new IOException("Invalid number of Characters");
/*     */     }
/* 188 */     if (startCode < 0L || startCode > 1114111L || startCode + numChars > 1114111L || (startCode + numChars >= 55296L && startCode + numChars <= 57343L))
/*     */     {
/* 190 */       throw new IOException("Invalid Characters codes");
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
/*     */   protected void d(RandomAccessFile data, int numGlyphs) throws IOException {
/* 205 */     long nbGroups = data.readInt();
/* 206 */     this.e = c(numGlyphs);
/* 207 */     this.f = new HashMap<>(numGlyphs); long i;
/* 208 */     for (i = 0L; i < nbGroups; i++) {
/* 209 */       long firstCode = data.readInt();
/* 210 */       long endCode = data.readInt();
/* 211 */       long startGlyph = data.readInt();
/* 212 */       if (firstCode < 0L || firstCode > 1114111L || (firstCode >= 55296L && firstCode <= 57343L)) {
/* 213 */         throw new IOException("Invalid characters codes");
/*     */       }
/* 215 */       if ((endCode > 0L && endCode < firstCode) || endCode > 1114111L || (endCode >= 55296L && endCode <= 57343L))
/*     */       {
/* 217 */         throw new IOException("Invalid characters codes"); } 
/*     */       long j;
/* 219 */       for (j = 0L; j <= endCode - firstCode; j++) {
/* 220 */         long glyphIndex = startGlyph + j;
/* 221 */         if (glyphIndex >= numGlyphs) {
/* 222 */           this.a.log(Level.WARNING, "Format 12 cmap contains an invalid glyph index");
/*     */           break;
/*     */         } 
/* 225 */         if (firstCode + j > 1114111L) {
/* 226 */           this.a.log(Level.WARNING, "Format 12 cmap contains character beyond UCS-4");
/*     */         }
/* 228 */         this.e[(int)glyphIndex] = (int)(firstCode + j);
/* 229 */         this.f.put(Integer.valueOf((int)(firstCode + j)), Integer.valueOf((int)glyphIndex));
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
/*     */   protected void e(RandomAccessFile data, int numGlyphs) throws IOException {
/* 245 */     long nbGroups = data.readInt();
/* 246 */     this.f = new HashMap<>(numGlyphs); long i;
/* 247 */     for (i = 0L; i < nbGroups; i++) {
/* 248 */       long firstCode = data.readInt();
/* 249 */       long endCode = data.readInt();
/* 250 */       long glyphId = data.readInt();
/* 251 */       if (glyphId > numGlyphs) {
/* 252 */         this.a.log(Level.WARNING, "Format 13 cmap contains an invalid glyph index");
/*     */         break;
/*     */       } 
/* 255 */       if (firstCode < 0L || firstCode > 1114111L || (firstCode >= 55296L && firstCode <= 57343L)) {
/* 256 */         throw new IOException("Invalid Characters codes");
/*     */       }
/* 258 */       if ((endCode > 0L && endCode < firstCode) || endCode > 1114111L || (endCode >= 55296L && endCode <= 57343L))
/*     */       {
/* 260 */         throw new IOException("Invalid Characters codes"); } 
/*     */       long j;
/* 262 */       for (j = 0L; j <= endCode - firstCode; j++) {
/* 263 */         if (firstCode + j > 2147483647L) {
/* 264 */           throw new IOException("Character Code greater than Integer.MAX_VALUE");
/*     */         }
/* 266 */         if (firstCode + j > 1114111L) {
/* 267 */           this.a.log(Level.WARNING, "Format 13 cmap contains character beyond UCS-4");
/*     */         }
/* 269 */         this.e[(int)glyphId] = (int)(firstCode + j);
/* 270 */         this.f.put(Integer.valueOf((int)(firstCode + j)), Integer.valueOf((int)glyphId));
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
/*     */   protected void f(RandomAccessFile data, int numGlyphs) throws IOException {
/* 289 */     this.a.log(Level.WARNING, "Format 14 cmap table is not supported and will be ignored");
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
/*     */   protected void g(RandomAccessFile data, int numGlyphs) throws IOException {
/* 303 */     int firstCode = data.readUnsignedShort();
/* 304 */     int entryCount = data.readUnsignedShort();
/*     */     
/* 306 */     if (entryCount == 0) {
/*     */       return;
/*     */     }
/* 309 */     Map<Integer, Integer> tmpGlyphToChar = new HashMap<>(numGlyphs);
/* 310 */     this.f = new HashMap<>(numGlyphs);
/* 311 */     int[] glyphIdArray = a(data, entryCount);
/* 312 */     int maxGlyphId = 0;
/* 313 */     for (int i = 0; i < entryCount; i++) {
/* 314 */       maxGlyphId = Math.max(maxGlyphId, glyphIdArray[i]);
/* 315 */       tmpGlyphToChar.put(Integer.valueOf(glyphIdArray[i]), Integer.valueOf(firstCode + i));
/* 316 */       this.f.put(Integer.valueOf(firstCode + i), Integer.valueOf(glyphIdArray[i]));
/*     */     } 
/* 318 */     a(tmpGlyphToChar, maxGlyphId);
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
/*     */   protected void h(RandomAccessFile raf, int numGlyphs) throws IOException {
/* 332 */     int segCountX2 = raf.readUnsignedShort();
/* 333 */     int segCount = segCountX2 / 2;
/* 334 */     raf.readUnsignedShort();
/* 335 */     raf.readUnsignedShort();
/* 336 */     raf.readUnsignedShort();
/* 337 */     int[] endCount = a(raf, segCount);
/* 338 */     raf.readUnsignedShort();
/* 339 */     int[] startCount = a(raf, segCount);
/* 340 */     int[] idDelta = a(raf, segCount);
/* 341 */     int[] idRangeOffset = a(raf, segCount);
/* 342 */     Map<Integer, Integer> tmpGlyphToChar = new HashMap<>(numGlyphs);
/* 343 */     this.f = new HashMap<>(numGlyphs);
/* 344 */     int maxGlyphId = 0;
/* 345 */     long currentPosition = raf.getFilePointer();
/* 346 */     for (int i = 0; i < segCount; i++) {
/* 347 */       int start = startCount[i];
/* 348 */       int end = endCount[i];
/* 349 */       int delta = idDelta[i];
/* 350 */       int rangeOffset = idRangeOffset[i];
/* 351 */       if (start != 65535 && end != 65535) {
/* 352 */         for (int j = start; j <= end; j++) {
/* 353 */           if (rangeOffset == 0) {
/* 354 */             int glyphid = j + delta & 0xFFFF;
/* 355 */             maxGlyphId = Math.max(glyphid, maxGlyphId);
/* 356 */             tmpGlyphToChar.put(Integer.valueOf(glyphid), Integer.valueOf(j));
/* 357 */             this.f.put(Integer.valueOf(j), Integer.valueOf(glyphid));
/*     */           } else {
/* 359 */             long glyphOffset = currentPosition + ((rangeOffset / 2 + j - start + i - segCount) * 2);
/* 360 */             raf.seek(glyphOffset);
/* 361 */             int glyphIndex = raf.readUnsignedShort();
/* 362 */             if (glyphIndex != 0) {
/* 363 */               glyphIndex = glyphIndex + delta & 0xFFFF;
/* 364 */               if (!tmpGlyphToChar.containsKey(Integer.valueOf(glyphIndex))) {
/* 365 */                 maxGlyphId = Math.max(glyphIndex, maxGlyphId);
/* 366 */                 tmpGlyphToChar.put(Integer.valueOf(glyphIndex), Integer.valueOf(j));
/* 367 */                 this.f.put(Integer.valueOf(j), Integer.valueOf(glyphIndex));
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 378 */     if (tmpGlyphToChar.isEmpty()) {
/* 379 */       this.a.log(Level.WARNING, "cmap format 4 subtable is empty");
/*     */       return;
/*     */     } 
/* 382 */     a(tmpGlyphToChar, maxGlyphId);
/*     */   }
/*     */   
/*     */   private void a(Map<Integer, Integer> tmpGlyphToChar, int maxGlyphId) {
/* 386 */     this.e = c(maxGlyphId + 1);
/* 387 */     for (Map.Entry<Integer, Integer> entry : tmpGlyphToChar.entrySet())
/*     */     {
/* 389 */       this.e[((Integer)entry.getKey()).intValue()] = ((Integer)entry.getValue()).intValue();
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
/*     */   protected void i(RandomAccessFile data, int numGlyphs) throws IOException {
/* 404 */     int[] subHeaderKeys = new int[256];
/*     */     
/* 406 */     int maxSubHeaderIndex = 0;
/* 407 */     for (int i = 0; i < 256; i++) {
/* 408 */       subHeaderKeys[i] = data.readUnsignedShort();
/* 409 */       maxSubHeaderIndex = Math.max(maxSubHeaderIndex, subHeaderKeys[i] / 8);
/*     */     } 
/*     */     
/* 412 */     a[] subHeaders = new a[maxSubHeaderIndex + 1];
/* 413 */     for (int j = 0; j <= maxSubHeaderIndex; j++) {
/* 414 */       int firstCode = data.readUnsignedShort();
/* 415 */       int entryCount = data.readUnsignedShort();
/* 416 */       short idDelta = data.readShort();
/* 417 */       int idRangeOffset = data.readUnsignedShort() - (maxSubHeaderIndex + 1 - j - 1) * 8 - 2;
/* 418 */       subHeaders[j] = new a(firstCode, entryCount, idDelta, idRangeOffset);
/*     */     } 
/* 420 */     long startGlyphIndexOffset = data.getFilePointer();
/* 421 */     this.e = c(numGlyphs);
/* 422 */     this.f = new HashMap<>(numGlyphs);
/* 423 */     for (int k = 0; k <= maxSubHeaderIndex; k++) {
/* 424 */       a sh = subHeaders[k];
/* 425 */       int firstCode = a.a(sh);
/* 426 */       int idRangeOffset = a.b(sh);
/* 427 */       int idDelta = a.c(sh);
/* 428 */       int entryCount = a.d(sh);
/* 429 */       data.seek(startGlyphIndexOffset + idRangeOffset);
/* 430 */       for (int m = 0; m < entryCount; m++) {
/*     */         
/* 432 */         int charCode = k;
/* 433 */         charCode = (charCode << 8) + firstCode + m;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 438 */         int p = data.readUnsignedShort();
/*     */         
/* 440 */         if (p > 0) {
/* 441 */           p = (p + idDelta) % 65536;
/*     */         }
/*     */         
/* 444 */         if (p >= numGlyphs) {
/* 445 */           this.a.log(Level.WARNING, "glyphId " + p + " for charcode " + charCode + " ignored, numGlyphs is " + numGlyphs);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 450 */           this.e[p] = charCode;
/* 451 */           this.f.put(Integer.valueOf(charCode), Integer.valueOf(p));
/*     */         } 
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
/*     */   protected void a(RandomAccessFile data) throws IOException {
/* 465 */     byte[] glyphMapping = new byte[256];
/* 466 */     data.readFully(glyphMapping);
/* 467 */     this.e = c(256);
/* 468 */     this.f = new HashMap<>(glyphMapping.length);
/* 469 */     for (int i = 0; i < glyphMapping.length; i++) {
/* 470 */       int glyphIndex = (glyphMapping[i] + 256) % 256;
/* 471 */       this.e[glyphIndex] = i;
/* 472 */       this.f.put(Integer.valueOf(i), Integer.valueOf(glyphIndex));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] c(int size) {
/* 481 */     int[] gidToCode = new int[size];
/* 482 */     Arrays.fill(gidToCode, -1);
/* 483 */     return gidToCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(int characterCode) {
/* 494 */     Integer glyphId = this.f.get(Integer.valueOf(characterCode));
/* 495 */     return (glyphId == null) ? 0 : glyphId.intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer b(int gid) {
/* 506 */     if (gid < 0 || gid >= this.e.length) {
/* 507 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 512 */     int code = this.e[gid];
/* 513 */     if (code == -1) {
/* 514 */       return null;
/*     */     }
/* 516 */     return Integer.valueOf(code);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private class a
/*     */   {
/*     */     private final int b;
/*     */ 
/*     */     
/*     */     private final int c;
/*     */ 
/*     */     
/*     */     private final short d;
/*     */ 
/*     */     
/*     */     private final int e;
/*     */ 
/*     */ 
/*     */     
/*     */     private a(a this$0, int firstCodeValue, int entryCountValue, short idDeltaValue, int idRangeOffsetValue) {
/* 538 */       this.b = firstCodeValue;
/* 539 */       this.c = entryCountValue;
/* 540 */       this.d = idDeltaValue;
/* 541 */       this.e = idRangeOffsetValue;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int a() {
/* 548 */       return this.b;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int b() {
/* 555 */       return this.c;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private short c() {
/* 562 */       return this.d;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int d() {
/* 569 */       return this.e;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */