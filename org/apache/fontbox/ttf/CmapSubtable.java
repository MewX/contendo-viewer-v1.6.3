/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CmapSubtable
/*     */   implements CmapLookup
/*     */ {
/*  38 */   private static final Log LOG = LogFactory.getLog(CmapSubtable.class);
/*     */   
/*     */   private static final long LEAD_OFFSET = 55232L;
/*     */   
/*     */   private static final long SURROGATE_OFFSET = -56613888L;
/*     */   private int platformId;
/*     */   private int platformEncodingId;
/*     */   private long subTableOffset;
/*     */   private int[] glyphIdToCharacterCode;
/*  47 */   private final Map<Integer, List<Integer>> glyphIdToCharacterCodeMultiple = new HashMap<Integer, List<Integer>>();
/*  48 */   private Map<Integer, Integer> characterCodeToGlyphId = new HashMap<Integer, Integer>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initData(TTFDataStream data) throws IOException {
/*  58 */     this.platformId = data.readUnsignedShort();
/*  59 */     this.platformEncodingId = data.readUnsignedShort();
/*  60 */     this.subTableOffset = data.readUnsignedInt();
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
/*     */   public void initSubtable(CmapTable cmap, int numGlyphs, TTFDataStream data) throws IOException {
/*  73 */     data.seek(cmap.getOffset() + this.subTableOffset);
/*  74 */     int subtableFormat = data.readUnsignedShort();
/*     */ 
/*     */     
/*  77 */     if (subtableFormat < 8) {
/*     */       
/*  79 */       long length = data.readUnsignedShort();
/*  80 */       long version = data.readUnsignedShort();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  85 */       data.readUnsignedShort();
/*  86 */       long length = data.readUnsignedInt();
/*  87 */       long version = data.readUnsignedInt();
/*     */     } 
/*     */     
/*  90 */     switch (subtableFormat) {
/*     */       
/*     */       case 0:
/*  93 */         processSubtype0(data);
/*     */         return;
/*     */       case 2:
/*  96 */         processSubtype2(data, numGlyphs);
/*     */         return;
/*     */       case 4:
/*  99 */         processSubtype4(data, numGlyphs);
/*     */         return;
/*     */       case 6:
/* 102 */         processSubtype6(data, numGlyphs);
/*     */         return;
/*     */       case 8:
/* 105 */         processSubtype8(data, numGlyphs);
/*     */         return;
/*     */       case 10:
/* 108 */         processSubtype10(data, numGlyphs);
/*     */         return;
/*     */       case 12:
/* 111 */         processSubtype12(data, numGlyphs);
/*     */         return;
/*     */       case 13:
/* 114 */         processSubtype13(data, numGlyphs);
/*     */         return;
/*     */       case 14:
/* 117 */         processSubtype14(data, numGlyphs);
/*     */         return;
/*     */     } 
/* 120 */     throw new IOException("Unknown cmap format:" + subtableFormat);
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
/*     */   protected void processSubtype8(TTFDataStream data, int numGlyphs) throws IOException {
/* 134 */     int[] is32 = data.readUnsignedByteArray(8192);
/* 135 */     long nbGroups = data.readUnsignedInt();
/*     */ 
/*     */     
/* 138 */     if (nbGroups > 65536L)
/*     */     {
/* 140 */       throw new IOException("CMap ( Subtype8 ) is invalid");
/*     */     }
/*     */     
/* 143 */     this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(numGlyphs);
/* 144 */     this.characterCodeToGlyphId = new HashMap<Integer, Integer>(numGlyphs);
/*     */     long i;
/* 146 */     for (i = 0L; i < nbGroups; i++) {
/*     */       
/* 148 */       long firstCode = data.readUnsignedInt();
/* 149 */       long endCode = data.readUnsignedInt();
/* 150 */       long startGlyph = data.readUnsignedInt();
/*     */ 
/*     */       
/* 153 */       if (firstCode > endCode || 0L > firstCode)
/*     */       {
/* 155 */         throw new IOException("Range invalid");
/*     */       }
/*     */       long j;
/* 158 */       for (j = firstCode; j <= endCode; j++) {
/*     */         int currentCharCode;
/*     */         
/* 161 */         if (j > 2147483647L)
/*     */         {
/* 163 */           throw new IOException("[Sub Format 8] Invalid Character code");
/*     */         }
/*     */ 
/*     */         
/* 167 */         if ((is32[(int)j / 8] & 1 << (int)j % 8) == 0) {
/*     */           
/* 169 */           currentCharCode = (int)j;
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 175 */           long lead = 55232L + (j >> 10L);
/* 176 */           long trail = 56320L + (j & 0x3FFL);
/*     */           
/* 178 */           long codepoint = (lead << 10L) + trail + -56613888L;
/* 179 */           if (codepoint > 2147483647L)
/*     */           {
/* 181 */             throw new IOException("[Sub Format 8] Invalid Character code");
/*     */           }
/* 183 */           currentCharCode = (int)codepoint;
/*     */         } 
/*     */         
/* 186 */         long glyphIndex = startGlyph + j - firstCode;
/* 187 */         if (glyphIndex > numGlyphs || glyphIndex > 2147483647L)
/*     */         {
/* 189 */           throw new IOException("CMap contains an invalid glyph index");
/*     */         }
/*     */         
/* 192 */         this.glyphIdToCharacterCode[(int)glyphIndex] = currentCharCode;
/* 193 */         this.characterCodeToGlyphId.put(Integer.valueOf(currentCharCode), Integer.valueOf((int)glyphIndex));
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
/*     */   protected void processSubtype10(TTFDataStream data, int numGlyphs) throws IOException {
/* 207 */     long startCode = data.readUnsignedInt();
/* 208 */     long numChars = data.readUnsignedInt();
/* 209 */     if (numChars > 2147483647L)
/*     */     {
/* 211 */       throw new IOException("Invalid number of Characters");
/*     */     }
/*     */     
/* 214 */     if (startCode < 0L || startCode > 1114111L || startCode + numChars > 1114111L || (startCode + numChars >= 55296L && startCode + numChars <= 57343L))
/*     */     {
/*     */       
/* 217 */       throw new IOException("Invalid Characters codes");
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
/*     */   protected void processSubtype12(TTFDataStream data, int numGlyphs) throws IOException {
/* 231 */     long nbGroups = data.readUnsignedInt();
/* 232 */     this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(numGlyphs);
/* 233 */     this.characterCodeToGlyphId = new HashMap<Integer, Integer>(numGlyphs); long i;
/* 234 */     for (i = 0L; i < nbGroups; i++) {
/*     */       
/* 236 */       long firstCode = data.readUnsignedInt();
/* 237 */       long endCode = data.readUnsignedInt();
/* 238 */       long startGlyph = data.readUnsignedInt();
/*     */       
/* 240 */       if (firstCode < 0L || firstCode > 1114111L || (firstCode >= 55296L && firstCode <= 57343L))
/*     */       {
/*     */         
/* 243 */         throw new IOException("Invalid characters codes");
/*     */       }
/*     */       
/* 246 */       if ((endCode > 0L && endCode < firstCode) || endCode > 1114111L || (endCode >= 55296L && endCode <= 57343L))
/*     */       {
/*     */ 
/*     */         
/* 250 */         throw new IOException("Invalid characters codes");
/*     */       }
/*     */       long j;
/* 253 */       for (j = 0L; j <= endCode - firstCode; j++) {
/*     */         
/* 255 */         long glyphIndex = startGlyph + j;
/* 256 */         if (glyphIndex >= numGlyphs) {
/*     */           
/* 258 */           LOG.warn("Format 12 cmap contains an invalid glyph index");
/*     */           
/*     */           break;
/*     */         } 
/* 262 */         if (firstCode + j > 1114111L)
/*     */         {
/* 264 */           LOG.warn("Format 12 cmap contains character beyond UCS-4");
/*     */         }
/*     */         
/* 267 */         this.glyphIdToCharacterCode[(int)glyphIndex] = (int)(firstCode + j);
/* 268 */         this.characterCodeToGlyphId.put(Integer.valueOf((int)(firstCode + j)), Integer.valueOf((int)glyphIndex));
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
/*     */   protected void processSubtype13(TTFDataStream data, int numGlyphs) throws IOException {
/* 282 */     long nbGroups = data.readUnsignedInt();
/* 283 */     this.characterCodeToGlyphId = new HashMap<Integer, Integer>(numGlyphs); long i;
/* 284 */     for (i = 0L; i < nbGroups; i++) {
/*     */       
/* 286 */       long firstCode = data.readUnsignedInt();
/* 287 */       long endCode = data.readUnsignedInt();
/* 288 */       long glyphId = data.readUnsignedInt();
/*     */       
/* 290 */       if (glyphId > numGlyphs) {
/*     */         
/* 292 */         LOG.warn("Format 13 cmap contains an invalid glyph index");
/*     */         
/*     */         break;
/*     */       } 
/* 296 */       if (firstCode < 0L || firstCode > 1114111L || (firstCode >= 55296L && firstCode <= 57343L))
/*     */       {
/* 298 */         throw new IOException("Invalid Characters codes");
/*     */       }
/*     */       
/* 301 */       if ((endCode > 0L && endCode < firstCode) || endCode > 1114111L || (endCode >= 55296L && endCode <= 57343L))
/*     */       {
/*     */         
/* 304 */         throw new IOException("Invalid Characters codes");
/*     */       }
/*     */       long j;
/* 307 */       for (j = 0L; j <= endCode - firstCode; j++) {
/*     */         
/* 309 */         if (firstCode + j > 2147483647L)
/*     */         {
/* 311 */           throw new IOException("Character Code greater than Integer.MAX_VALUE");
/*     */         }
/*     */         
/* 314 */         if (firstCode + j > 1114111L)
/*     */         {
/* 316 */           LOG.warn("Format 13 cmap contains character beyond UCS-4");
/*     */         }
/*     */         
/* 319 */         this.glyphIdToCharacterCode[(int)glyphId] = (int)(firstCode + j);
/* 320 */         this.characterCodeToGlyphId.put(Integer.valueOf((int)(firstCode + j)), Integer.valueOf((int)glyphId));
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
/*     */   protected void processSubtype14(TTFDataStream data, int numGlyphs) throws IOException {
/* 336 */     LOG.warn("Format 14 cmap table is not supported and will be ignored");
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
/*     */   protected void processSubtype6(TTFDataStream data, int numGlyphs) throws IOException {
/* 348 */     int firstCode = data.readUnsignedShort();
/* 349 */     int entryCount = data.readUnsignedShort();
/*     */     
/* 351 */     if (entryCount == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 355 */     this.characterCodeToGlyphId = new HashMap<Integer, Integer>(numGlyphs);
/* 356 */     int[] glyphIdArray = data.readUnsignedShortArray(entryCount);
/* 357 */     int maxGlyphId = 0;
/* 358 */     for (int i = 0; i < entryCount; i++) {
/*     */       
/* 360 */       maxGlyphId = Math.max(maxGlyphId, glyphIdArray[i]);
/* 361 */       this.characterCodeToGlyphId.put(Integer.valueOf(firstCode + i), Integer.valueOf(glyphIdArray[i]));
/*     */     } 
/* 363 */     buildGlyphIdToCharacterCodeLookup(maxGlyphId);
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
/*     */   protected void processSubtype4(TTFDataStream data, int numGlyphs) throws IOException {
/* 375 */     int segCountX2 = data.readUnsignedShort();
/* 376 */     int segCount = segCountX2 / 2;
/* 377 */     int searchRange = data.readUnsignedShort();
/* 378 */     int entrySelector = data.readUnsignedShort();
/* 379 */     int rangeShift = data.readUnsignedShort();
/* 380 */     int[] endCount = data.readUnsignedShortArray(segCount);
/* 381 */     int reservedPad = data.readUnsignedShort();
/* 382 */     int[] startCount = data.readUnsignedShortArray(segCount);
/* 383 */     int[] idDelta = data.readUnsignedShortArray(segCount);
/* 384 */     long idRangeOffsetPosition = data.getCurrentPosition();
/* 385 */     int[] idRangeOffset = data.readUnsignedShortArray(segCount);
/*     */     
/* 387 */     this.characterCodeToGlyphId = new HashMap<Integer, Integer>(numGlyphs);
/* 388 */     int maxGlyphId = 0;
/*     */     
/* 390 */     for (int i = 0; i < segCount; i++) {
/*     */       
/* 392 */       int start = startCount[i];
/* 393 */       int end = endCount[i];
/* 394 */       int delta = idDelta[i];
/* 395 */       int rangeOffset = idRangeOffset[i];
/* 396 */       long segmentRangeOffset = idRangeOffsetPosition + (i * 2) + rangeOffset;
/* 397 */       if (start != 65535 && end != 65535)
/*     */       {
/* 399 */         for (int j = start; j <= end; j++) {
/*     */           
/* 401 */           if (rangeOffset == 0) {
/*     */             
/* 403 */             int glyphid = j + delta & 0xFFFF;
/* 404 */             maxGlyphId = Math.max(glyphid, maxGlyphId);
/* 405 */             this.characterCodeToGlyphId.put(Integer.valueOf(j), Integer.valueOf(glyphid));
/*     */           }
/*     */           else {
/*     */             
/* 409 */             long glyphOffset = segmentRangeOffset + ((j - start) * 2);
/* 410 */             data.seek(glyphOffset);
/* 411 */             int glyphIndex = data.readUnsignedShort();
/* 412 */             if (glyphIndex != 0) {
/*     */               
/* 414 */               glyphIndex = glyphIndex + delta & 0xFFFF;
/* 415 */               maxGlyphId = Math.max(glyphIndex, maxGlyphId);
/* 416 */               this.characterCodeToGlyphId.put(Integer.valueOf(j), Integer.valueOf(glyphIndex));
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 427 */     if (this.characterCodeToGlyphId.isEmpty()) {
/*     */       
/* 429 */       LOG.warn("cmap format 4 subtable is empty");
/*     */       return;
/*     */     } 
/* 432 */     buildGlyphIdToCharacterCodeLookup(maxGlyphId);
/*     */   }
/*     */ 
/*     */   
/*     */   private void buildGlyphIdToCharacterCodeLookup(int maxGlyphId) {
/* 437 */     this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(maxGlyphId + 1);
/* 438 */     for (Map.Entry<Integer, Integer> entry : this.characterCodeToGlyphId.entrySet()) {
/*     */       
/* 440 */       if (this.glyphIdToCharacterCode[((Integer)entry.getValue()).intValue()] == -1) {
/*     */ 
/*     */         
/* 443 */         this.glyphIdToCharacterCode[((Integer)entry.getValue()).intValue()] = ((Integer)entry.getKey()).intValue();
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 448 */       List<Integer> mappedValues = this.glyphIdToCharacterCodeMultiple.get(entry.getValue());
/* 449 */       if (mappedValues == null) {
/*     */         
/* 451 */         mappedValues = new ArrayList<Integer>();
/* 452 */         this.glyphIdToCharacterCodeMultiple.put(entry.getValue(), mappedValues);
/* 453 */         mappedValues.add(Integer.valueOf(this.glyphIdToCharacterCode[((Integer)entry.getValue()).intValue()]));
/*     */         
/* 455 */         this.glyphIdToCharacterCode[((Integer)entry.getValue()).intValue()] = Integer.MIN_VALUE;
/*     */       } 
/* 457 */       mappedValues.add(entry.getKey());
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
/*     */   protected void processSubtype2(TTFDataStream data, int numGlyphs) throws IOException {
/* 471 */     int[] subHeaderKeys = new int[256];
/*     */     
/* 473 */     int maxSubHeaderIndex = 0;
/* 474 */     for (int i = 0; i < 256; i++) {
/*     */       
/* 476 */       subHeaderKeys[i] = data.readUnsignedShort();
/* 477 */       maxSubHeaderIndex = Math.max(maxSubHeaderIndex, subHeaderKeys[i] / 8);
/*     */     } 
/*     */ 
/*     */     
/* 481 */     SubHeader[] subHeaders = new SubHeader[maxSubHeaderIndex + 1];
/* 482 */     for (int j = 0; j <= maxSubHeaderIndex; j++) {
/*     */       
/* 484 */       int firstCode = data.readUnsignedShort();
/* 485 */       int entryCount = data.readUnsignedShort();
/* 486 */       short idDelta = data.readSignedShort();
/* 487 */       int idRangeOffset = data.readUnsignedShort() - (maxSubHeaderIndex + 1 - j - 1) * 8 - 2;
/* 488 */       subHeaders[j] = new SubHeader(firstCode, entryCount, idDelta, idRangeOffset);
/*     */     } 
/* 490 */     long startGlyphIndexOffset = data.getCurrentPosition();
/* 491 */     this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(numGlyphs);
/* 492 */     this.characterCodeToGlyphId = new HashMap<Integer, Integer>(numGlyphs);
/* 493 */     for (int k = 0; k <= maxSubHeaderIndex; k++) {
/*     */       
/* 495 */       SubHeader sh = subHeaders[k];
/* 496 */       int firstCode = sh.getFirstCode();
/* 497 */       int idRangeOffset = sh.getIdRangeOffset();
/* 498 */       int idDelta = sh.getIdDelta();
/* 499 */       int entryCount = sh.getEntryCount();
/* 500 */       data.seek(startGlyphIndexOffset + idRangeOffset);
/* 501 */       for (int m = 0; m < entryCount; m++) {
/*     */ 
/*     */         
/* 504 */         int charCode = k;
/* 505 */         charCode = (charCode << 8) + firstCode + m;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 511 */         int p = data.readUnsignedShort();
/*     */         
/* 513 */         if (p > 0) {
/*     */           
/* 515 */           p = (p + idDelta) % 65536;
/* 516 */           if (p < 0)
/*     */           {
/* 518 */             p += 65536;
/*     */           }
/*     */         } 
/*     */         
/* 522 */         if (p >= numGlyphs) {
/*     */           
/* 524 */           LOG.warn("glyphId " + p + " for charcode " + charCode + " ignored, numGlyphs is " + numGlyphs);
/*     */         }
/*     */         else {
/*     */           
/* 528 */           this.glyphIdToCharacterCode[p] = charCode;
/* 529 */           this.characterCodeToGlyphId.put(Integer.valueOf(charCode), Integer.valueOf(p));
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
/*     */   protected void processSubtype0(TTFDataStream data) throws IOException {
/* 542 */     byte[] glyphMapping = data.read(256);
/* 543 */     this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(256);
/* 544 */     this.characterCodeToGlyphId = new HashMap<Integer, Integer>(glyphMapping.length);
/* 545 */     for (int i = 0; i < glyphMapping.length; i++) {
/*     */       
/* 547 */       int glyphIndex = glyphMapping[i] & 0xFF;
/* 548 */       this.glyphIdToCharacterCode[glyphIndex] = i;
/* 549 */       this.characterCodeToGlyphId.put(Integer.valueOf(i), Integer.valueOf(glyphIndex));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] newGlyphIdToCharacterCode(int size) {
/* 559 */     int[] gidToCode = new int[size];
/* 560 */     Arrays.fill(gidToCode, -1);
/* 561 */     return gidToCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPlatformEncodingId() {
/* 569 */     return this.platformEncodingId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlatformEncodingId(int platformEncodingIdValue) {
/* 577 */     this.platformEncodingId = platformEncodingIdValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPlatformId() {
/* 585 */     return this.platformId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPlatformId(int platformIdValue) {
/* 593 */     this.platformId = platformIdValue;
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
/*     */   public int getGlyphId(int characterCode) {
/* 605 */     Integer glyphId = this.characterCodeToGlyphId.get(Integer.valueOf(characterCode));
/* 606 */     return (glyphId == null) ? 0 : glyphId.intValue();
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
/*     */   public Integer getCharacterCode(int gid) {
/* 620 */     int code = getCharCode(gid);
/* 621 */     if (code == -1)
/*     */     {
/* 623 */       return null;
/*     */     }
/*     */     
/* 626 */     if (code == Integer.MIN_VALUE) {
/*     */       
/* 628 */       List<Integer> mappedValues = this.glyphIdToCharacterCodeMultiple.get(Integer.valueOf(gid));
/* 629 */       if (mappedValues != null)
/*     */       {
/*     */         
/* 632 */         return mappedValues.get(0);
/*     */       }
/*     */     } 
/* 635 */     return Integer.valueOf(code);
/*     */   }
/*     */ 
/*     */   
/*     */   private int getCharCode(int gid) {
/* 640 */     if (gid < 0 || gid >= this.glyphIdToCharacterCode.length)
/*     */     {
/* 642 */       return -1;
/*     */     }
/* 644 */     return this.glyphIdToCharacterCode[gid];
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
/*     */   public List<Integer> getCharCodes(int gid) {
/* 657 */     int code = getCharCode(gid);
/* 658 */     if (code == -1)
/*     */     {
/* 660 */       return null;
/*     */     }
/* 662 */     List<Integer> codes = null;
/* 663 */     if (code == Integer.MIN_VALUE) {
/*     */       
/* 665 */       List<Integer> mappedValues = this.glyphIdToCharacterCodeMultiple.get(Integer.valueOf(gid));
/* 666 */       if (mappedValues != null)
/*     */       {
/* 668 */         codes = new ArrayList<Integer>(mappedValues);
/*     */         
/* 670 */         Collections.sort(codes);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 675 */       codes = new ArrayList<Integer>(1);
/* 676 */       codes.add(Integer.valueOf(code));
/*     */     } 
/* 678 */     return codes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 684 */     return "{" + getPlatformId() + " " + getPlatformEncodingId() + "}";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class SubHeader
/*     */   {
/*     */     private final int firstCode;
/*     */ 
/*     */ 
/*     */     
/*     */     private final int entryCount;
/*     */ 
/*     */     
/*     */     private final short idDelta;
/*     */ 
/*     */     
/*     */     private final int idRangeOffset;
/*     */ 
/*     */ 
/*     */     
/*     */     private SubHeader(int firstCodeValue, int entryCountValue, short idDeltaValue, int idRangeOffsetValue) {
/* 707 */       this.firstCode = firstCodeValue;
/* 708 */       this.entryCount = entryCountValue;
/* 709 */       this.idDelta = idDeltaValue;
/* 710 */       this.idRangeOffset = idRangeOffsetValue;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int getFirstCode() {
/* 718 */       return this.firstCode;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int getEntryCount() {
/* 726 */       return this.entryCount;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private short getIdDelta() {
/* 734 */       return this.idDelta;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int getIdRangeOffset() {
/* 742 */       return this.idRangeOffset;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/CmapSubtable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */