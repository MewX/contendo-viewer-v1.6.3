/*     */ package org.apache.pdfbox.pdmodel.font;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.fontbox.ttf.GlyphData;
/*     */ import org.apache.fontbox.ttf.GlyphTable;
/*     */ import org.apache.fontbox.ttf.HorizontalMetricsTable;
/*     */ import org.apache.fontbox.ttf.TrueTypeFont;
/*     */ import org.apache.fontbox.ttf.VerticalHeaderTable;
/*     */ import org.apache.fontbox.ttf.VerticalMetricsTable;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class PDCIDFontType2Embedder
/*     */   extends TrueTypeEmbedder
/*     */ {
/*  56 */   private static final Log LOG = LogFactory.getLog(PDCIDFontType2Embedder.class);
/*     */ 
/*     */   
/*     */   private final PDDocument document;
/*     */ 
/*     */   
/*     */   private final PDType0Font parent;
/*     */ 
/*     */   
/*     */   private final COSDictionary dict;
/*     */ 
/*     */   
/*     */   private final COSDictionary cidFont;
/*     */ 
/*     */   
/*     */   private final boolean vertical;
/*     */ 
/*     */ 
/*     */   
/*     */   PDCIDFontType2Embedder(PDDocument document, COSDictionary dict, TrueTypeFont ttf, boolean embedSubset, PDType0Font parent, boolean vertical) throws IOException {
/*  76 */     super(document, dict, ttf, embedSubset);
/*  77 */     this.document = document;
/*  78 */     this.dict = dict;
/*  79 */     this.parent = parent;
/*  80 */     this.vertical = vertical;
/*     */ 
/*     */     
/*  83 */     dict.setItem(COSName.SUBTYPE, (COSBase)COSName.TYPE0);
/*  84 */     dict.setName(COSName.BASE_FONT, this.fontDescriptor.getFontName());
/*  85 */     dict.setItem(COSName.ENCODING, vertical ? (COSBase)COSName.IDENTITY_V : (COSBase)COSName.IDENTITY_H);
/*     */ 
/*     */     
/*  88 */     this.cidFont = createCIDFont();
/*  89 */     COSArray descendantFonts = new COSArray();
/*  90 */     descendantFonts.add((COSBase)this.cidFont);
/*  91 */     dict.setItem(COSName.DESCENDANT_FONTS, (COSBase)descendantFonts);
/*     */     
/*  93 */     if (!embedSubset)
/*     */     {
/*     */       
/*  96 */       buildToUnicodeCMap((Map<Integer, Integer>)null);
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
/*     */   protected void buildSubset(InputStream ttfSubset, String tag, Map<Integer, Integer> gidToCid) throws IOException {
/* 108 */     Map<Integer, Integer> cidToGid = new HashMap<Integer, Integer>(gidToCid.size());
/* 109 */     for (Map.Entry<Integer, Integer> entry : gidToCid.entrySet()) {
/*     */       
/* 111 */       int newGID = ((Integer)entry.getKey()).intValue();
/* 112 */       int oldGID = ((Integer)entry.getValue()).intValue();
/* 113 */       cidToGid.put(Integer.valueOf(oldGID), Integer.valueOf(newGID));
/*     */     } 
/*     */     
/* 116 */     buildToUnicodeCMap(gidToCid);
/*     */     
/* 118 */     if (this.vertical)
/*     */     {
/* 120 */       buildVerticalMetrics(cidToGid);
/*     */     }
/*     */     
/* 123 */     buildFontFile2(ttfSubset);
/* 124 */     addNameTag(tag);
/* 125 */     buildWidths(cidToGid);
/* 126 */     buildCIDToGIDMap(cidToGid);
/* 127 */     buildCIDSet(cidToGid);
/*     */   }
/*     */ 
/*     */   
/*     */   private void buildToUnicodeCMap(Map<Integer, Integer> newGIDToOldCID) throws IOException {
/* 132 */     ToUnicodeWriter toUniWriter = new ToUnicodeWriter();
/* 133 */     boolean hasSurrogates = false;
/* 134 */     for (int gid = 1, max = this.ttf.getMaximumProfile().getNumGlyphs(); gid <= max; gid++) {
/*     */       int cid;
/*     */ 
/*     */       
/* 138 */       if (newGIDToOldCID != null) {
/*     */         
/* 140 */         if (!newGIDToOldCID.containsKey(Integer.valueOf(gid))) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 146 */         cid = ((Integer)newGIDToOldCID.get(Integer.valueOf(gid))).intValue();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 151 */         cid = gid;
/*     */       } 
/*     */ 
/*     */       
/* 155 */       List<Integer> codes = this.cmapLookup.getCharCodes(cid);
/* 156 */       if (codes != null) {
/*     */ 
/*     */         
/* 159 */         int codePoint = ((Integer)codes.get(0)).intValue();
/* 160 */         if (codePoint > 65535)
/*     */         {
/* 162 */           hasSurrogates = true;
/*     */         }
/* 164 */         toUniWriter.add(cid, new String(new int[] { codePoint }, 0, 1));
/*     */       } 
/*     */       continue;
/*     */     } 
/* 168 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 169 */     toUniWriter.writeTo(out);
/* 170 */     InputStream cMapStream = new ByteArrayInputStream(out.toByteArray());
/*     */     
/* 172 */     PDStream stream = new PDStream(this.document, cMapStream, COSName.FLATE_DECODE);
/*     */ 
/*     */     
/* 175 */     if (hasSurrogates) {
/*     */       
/* 177 */       float version = this.document.getVersion();
/* 178 */       if (version < 1.5D)
/*     */       {
/* 180 */         this.document.setVersion(1.5F);
/*     */       }
/*     */     } 
/*     */     
/* 184 */     this.dict.setItem(COSName.TO_UNICODE, (COSObjectable)stream);
/*     */   }
/*     */ 
/*     */   
/*     */   private COSDictionary toCIDSystemInfo(String registry, String ordering, int supplement) {
/* 189 */     COSDictionary info = new COSDictionary();
/* 190 */     info.setString(COSName.REGISTRY, registry);
/* 191 */     info.setString(COSName.ORDERING, ordering);
/* 192 */     info.setInt(COSName.SUPPLEMENT, supplement);
/* 193 */     return info;
/*     */   }
/*     */ 
/*     */   
/*     */   private COSDictionary createCIDFont() throws IOException {
/* 198 */     COSDictionary cidFont = new COSDictionary();
/*     */ 
/*     */     
/* 201 */     cidFont.setItem(COSName.TYPE, (COSBase)COSName.FONT);
/* 202 */     cidFont.setItem(COSName.SUBTYPE, (COSBase)COSName.CID_FONT_TYPE2);
/*     */ 
/*     */     
/* 205 */     cidFont.setName(COSName.BASE_FONT, this.fontDescriptor.getFontName());
/*     */ 
/*     */     
/* 208 */     COSDictionary info = toCIDSystemInfo("Adobe", "Identity", 0);
/* 209 */     cidFont.setItem(COSName.CIDSYSTEMINFO, (COSBase)info);
/*     */ 
/*     */     
/* 212 */     cidFont.setItem(COSName.FONT_DESC, (COSBase)this.fontDescriptor.getCOSObject());
/*     */ 
/*     */     
/* 215 */     buildWidths(cidFont);
/*     */ 
/*     */     
/* 218 */     if (this.vertical)
/*     */     {
/* 220 */       buildVerticalMetrics(cidFont);
/*     */     }
/*     */ 
/*     */     
/* 224 */     cidFont.setItem(COSName.CID_TO_GID_MAP, (COSBase)COSName.IDENTITY);
/*     */     
/* 226 */     return cidFont;
/*     */   }
/*     */ 
/*     */   
/*     */   private void addNameTag(String tag) throws IOException {
/* 231 */     String name = this.fontDescriptor.getFontName();
/* 232 */     String newName = tag + name;
/*     */     
/* 234 */     this.dict.setName(COSName.BASE_FONT, newName);
/* 235 */     this.fontDescriptor.setFontName(newName);
/* 236 */     this.cidFont.setName(COSName.BASE_FONT, newName);
/*     */   }
/*     */ 
/*     */   
/*     */   private void buildCIDToGIDMap(Map<Integer, Integer> cidToGid) throws IOException {
/* 241 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 242 */     int cidMax = ((Integer)Collections.<Integer>max(cidToGid.keySet())).intValue();
/* 243 */     for (int i = 0; i <= cidMax; i++) {
/*     */       int gid;
/*     */       
/* 246 */       if (cidToGid.containsKey(Integer.valueOf(i))) {
/*     */         
/* 248 */         gid = ((Integer)cidToGid.get(Integer.valueOf(i))).intValue();
/*     */       }
/*     */       else {
/*     */         
/* 252 */         gid = 0;
/*     */       } 
/* 254 */       out.write(new byte[] { (byte)(gid >> 8 & 0xFF), (byte)(gid & 0xFF) });
/*     */     } 
/*     */     
/* 257 */     InputStream input = new ByteArrayInputStream(out.toByteArray());
/* 258 */     PDStream stream = new PDStream(this.document, input, COSName.FLATE_DECODE);
/*     */     
/* 260 */     this.cidFont.setItem(COSName.CID_TO_GID_MAP, (COSObjectable)stream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void buildCIDSet(Map<Integer, Integer> cidToGid) throws IOException {
/* 269 */     int cidMax = ((Integer)Collections.<Integer>max(cidToGid.keySet())).intValue();
/* 270 */     byte[] bytes = new byte[cidMax / 8 + 1];
/* 271 */     for (int cid = 0; cid <= cidMax; cid++) {
/*     */       
/* 273 */       int mask = 1 << 7 - cid % 8;
/* 274 */       bytes[cid / 8] = (byte)(bytes[cid / 8] | mask);
/*     */     } 
/*     */     
/* 277 */     InputStream input = new ByteArrayInputStream(bytes);
/* 278 */     PDStream stream = new PDStream(this.document, input, COSName.FLATE_DECODE);
/*     */     
/* 280 */     this.fontDescriptor.setCIDSet(stream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void buildWidths(Map<Integer, Integer> cidToGid) throws IOException {
/* 288 */     float scaling = 1000.0F / this.ttf.getHeader().getUnitsPerEm();
/*     */     
/* 290 */     COSArray widths = new COSArray();
/* 291 */     COSArray ws = new COSArray();
/* 292 */     int prev = Integer.MIN_VALUE;
/*     */     
/* 294 */     Set<Integer> keys = new TreeSet<Integer>(cidToGid.keySet());
/* 295 */     for (Iterator<Integer> iterator = keys.iterator(); iterator.hasNext(); ) { int cid = ((Integer)iterator.next()).intValue();
/*     */       
/* 297 */       int gid = ((Integer)cidToGid.get(Integer.valueOf(cid))).intValue();
/* 298 */       long width = Math.round(this.ttf.getHorizontalMetrics().getAdvanceWidth(gid) * scaling);
/* 299 */       if (width == 1000L) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 305 */       if (prev != cid - 1) {
/*     */         
/* 307 */         ws = new COSArray();
/* 308 */         widths.add((COSBase)COSInteger.get(cid));
/* 309 */         widths.add((COSBase)ws);
/*     */       } 
/* 311 */       ws.add((COSBase)COSInteger.get(width));
/* 312 */       prev = cid; }
/*     */     
/* 314 */     this.cidFont.setItem(COSName.W, (COSBase)widths);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean buildVerticalHeader(COSDictionary cidFont) throws IOException {
/* 319 */     VerticalHeaderTable vhea = this.ttf.getVerticalHeader();
/* 320 */     if (vhea == null) {
/*     */       
/* 322 */       LOG.warn("Font to be subset is set to vertical, but has no 'vhea' table");
/* 323 */       return false;
/*     */     } 
/*     */     
/* 326 */     float scaling = 1000.0F / this.ttf.getHeader().getUnitsPerEm();
/*     */     
/* 328 */     long v = Math.round(vhea.getAscender() * scaling);
/* 329 */     long w1 = Math.round(-vhea.getAdvanceHeightMax() * scaling);
/* 330 */     if (v != 880L || w1 != -1000L) {
/*     */       
/* 332 */       COSArray cosDw2 = new COSArray();
/* 333 */       cosDw2.add((COSBase)COSInteger.get(v));
/* 334 */       cosDw2.add((COSBase)COSInteger.get(w1));
/* 335 */       cidFont.setItem(COSName.DW2, (COSBase)cosDw2);
/*     */     } 
/* 337 */     return true;
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
/*     */   private void buildVerticalMetrics(Map<Integer, Integer> cidToGid) throws IOException {
/* 349 */     if (!buildVerticalHeader(this.cidFont)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 354 */     float scaling = 1000.0F / this.ttf.getHeader().getUnitsPerEm();
/*     */     
/* 356 */     VerticalHeaderTable vhea = this.ttf.getVerticalHeader();
/* 357 */     VerticalMetricsTable vmtx = this.ttf.getVerticalMetrics();
/* 358 */     GlyphTable glyf = this.ttf.getGlyph();
/* 359 */     HorizontalMetricsTable hmtx = this.ttf.getHorizontalMetrics();
/*     */     
/* 361 */     long v_y = Math.round(vhea.getAscender() * scaling);
/* 362 */     long w1 = Math.round(-vhea.getAdvanceHeightMax() * scaling);
/*     */     
/* 364 */     COSArray heights = new COSArray();
/* 365 */     COSArray w2 = new COSArray();
/* 366 */     int prev = Integer.MIN_VALUE;
/*     */     
/* 368 */     Set<Integer> keys = new TreeSet<Integer>(cidToGid.keySet());
/* 369 */     for (Iterator<Integer> iterator = keys.iterator(); iterator.hasNext(); ) { int cid = ((Integer)iterator.next()).intValue();
/*     */ 
/*     */ 
/*     */       
/* 373 */       GlyphData glyph = glyf.getGlyph(cid);
/* 374 */       if (glyph == null) {
/*     */         continue;
/*     */       }
/*     */       
/* 378 */       long height = Math.round((glyph.getYMaximum() + vmtx.getTopSideBearing(cid)) * scaling);
/* 379 */       long advance = Math.round(-vmtx.getAdvanceHeight(cid) * scaling);
/* 380 */       if (height == v_y && advance == w1) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 386 */       if (prev != cid - 1) {
/*     */         
/* 388 */         w2 = new COSArray();
/* 389 */         heights.add((COSBase)COSInteger.get(cid));
/* 390 */         heights.add((COSBase)w2);
/*     */       } 
/* 392 */       w2.add((COSBase)COSInteger.get(advance));
/* 393 */       long width = Math.round(hmtx.getAdvanceWidth(cid) * scaling);
/* 394 */       w2.add((COSBase)COSInteger.get(width / 2L));
/* 395 */       w2.add((COSBase)COSInteger.get(height));
/* 396 */       prev = cid; }
/*     */     
/* 398 */     this.cidFont.setItem(COSName.W2, (COSBase)heights);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void buildWidths(COSDictionary cidFont) throws IOException {
/* 406 */     int cidMax = this.ttf.getNumberOfGlyphs();
/* 407 */     int[] gidwidths = new int[cidMax * 2];
/* 408 */     for (int cid = 0; cid < cidMax; cid++) {
/*     */       
/* 410 */       gidwidths[cid * 2] = cid;
/* 411 */       gidwidths[cid * 2 + 1] = this.ttf.getHorizontalMetrics().getAdvanceWidth(cid);
/*     */     } 
/*     */     
/* 414 */     cidFont.setItem(COSName.W, (COSBase)getWidths(gidwidths));
/*     */   }
/*     */   
/*     */   enum State
/*     */   {
/* 419 */     FIRST, BRACKET, SERIAL;
/*     */   }
/*     */ 
/*     */   
/*     */   private COSArray getWidths(int[] widths) throws IOException {
/* 424 */     if (widths.length == 0)
/*     */     {
/* 426 */       throw new IllegalArgumentException("length of widths must be > 0");
/*     */     }
/*     */     
/* 429 */     float scaling = 1000.0F / this.ttf.getHeader().getUnitsPerEm();
/*     */     
/* 431 */     long lastCid = widths[0];
/* 432 */     long lastValue = Math.round(widths[1] * scaling);
/*     */     
/* 434 */     COSArray inner = new COSArray();
/* 435 */     COSArray outer = new COSArray();
/* 436 */     outer.add((COSBase)COSInteger.get(lastCid));
/*     */     
/* 438 */     State state = State.FIRST;
/*     */     
/* 440 */     for (int i = 2; i < widths.length; i += 2) {
/*     */       
/* 442 */       long cid = widths[i];
/* 443 */       long value = Math.round(widths[i + 1] * scaling);
/*     */       
/* 445 */       switch (state) {
/*     */         
/*     */         case FIRST:
/* 448 */           if (cid == lastCid + 1L && value == lastValue) {
/*     */             
/* 450 */             state = State.SERIAL; break;
/*     */           } 
/* 452 */           if (cid == lastCid + 1L) {
/*     */             
/* 454 */             state = State.BRACKET;
/* 455 */             inner = new COSArray();
/* 456 */             inner.add((COSBase)COSInteger.get(lastValue));
/*     */             
/*     */             break;
/*     */           } 
/* 460 */           inner = new COSArray();
/* 461 */           inner.add((COSBase)COSInteger.get(lastValue));
/* 462 */           outer.add((COSBase)inner);
/* 463 */           outer.add((COSBase)COSInteger.get(cid));
/*     */           break;
/*     */         
/*     */         case BRACKET:
/* 467 */           if (cid == lastCid + 1L && value == lastValue) {
/*     */             
/* 469 */             state = State.SERIAL;
/* 470 */             outer.add((COSBase)inner);
/* 471 */             outer.add((COSBase)COSInteger.get(lastCid)); break;
/*     */           } 
/* 473 */           if (cid == lastCid + 1L) {
/*     */             
/* 475 */             inner.add((COSBase)COSInteger.get(lastValue));
/*     */             
/*     */             break;
/*     */           } 
/* 479 */           state = State.FIRST;
/* 480 */           inner.add((COSBase)COSInteger.get(lastValue));
/* 481 */           outer.add((COSBase)inner);
/* 482 */           outer.add((COSBase)COSInteger.get(cid));
/*     */           break;
/*     */         
/*     */         case SERIAL:
/* 486 */           if (cid != lastCid + 1L || value != lastValue) {
/*     */             
/* 488 */             outer.add((COSBase)COSInteger.get(lastCid));
/* 489 */             outer.add((COSBase)COSInteger.get(lastValue));
/* 490 */             outer.add((COSBase)COSInteger.get(cid));
/* 491 */             state = State.FIRST;
/*     */           } 
/*     */           break;
/*     */       } 
/* 495 */       lastValue = value;
/* 496 */       lastCid = cid;
/*     */     } 
/*     */     
/* 499 */     switch (state) {
/*     */       
/*     */       case FIRST:
/* 502 */         inner = new COSArray();
/* 503 */         inner.add((COSBase)COSInteger.get(lastValue));
/* 504 */         outer.add((COSBase)inner);
/*     */         break;
/*     */       case BRACKET:
/* 507 */         inner.add((COSBase)COSInteger.get(lastValue));
/* 508 */         outer.add((COSBase)inner);
/*     */         break;
/*     */       case SERIAL:
/* 511 */         outer.add((COSBase)COSInteger.get(lastCid));
/* 512 */         outer.add((COSBase)COSInteger.get(lastValue));
/*     */         break;
/*     */     } 
/* 515 */     return outer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void buildVerticalMetrics(COSDictionary cidFont) throws IOException {
/* 523 */     if (!buildVerticalHeader(cidFont)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 528 */     int cidMax = this.ttf.getNumberOfGlyphs();
/* 529 */     int[] gidMetrics = new int[cidMax * 4];
/* 530 */     for (int cid = 0; cid < cidMax; cid++) {
/*     */       
/* 532 */       GlyphData glyph = this.ttf.getGlyph().getGlyph(cid);
/* 533 */       if (glyph == null) {
/*     */         
/* 535 */         gidMetrics[cid * 4] = Integer.MIN_VALUE;
/*     */       }
/*     */       else {
/*     */         
/* 539 */         gidMetrics[cid * 4] = cid;
/* 540 */         gidMetrics[cid * 4 + 1] = this.ttf.getVerticalMetrics().getAdvanceHeight(cid);
/* 541 */         gidMetrics[cid * 4 + 2] = this.ttf.getHorizontalMetrics().getAdvanceWidth(cid);
/* 542 */         gidMetrics[cid * 4 + 3] = glyph.getYMaximum() + this.ttf.getVerticalMetrics().getTopSideBearing(cid);
/*     */       } 
/*     */     } 
/*     */     
/* 546 */     cidFont.setItem(COSName.W2, (COSBase)getVerticalMetrics(gidMetrics));
/*     */   }
/*     */ 
/*     */   
/*     */   private COSArray getVerticalMetrics(int[] values) throws IOException {
/* 551 */     if (values.length == 0)
/*     */     {
/* 553 */       throw new IllegalArgumentException("length of values must be > 0");
/*     */     }
/*     */     
/* 556 */     float scaling = 1000.0F / this.ttf.getHeader().getUnitsPerEm();
/*     */     
/* 558 */     long lastCid = values[0];
/* 559 */     long lastW1Value = Math.round(-values[1] * scaling);
/* 560 */     long lastVxValue = Math.round(values[2] * scaling / 2.0F);
/* 561 */     long lastVyValue = Math.round(values[3] * scaling);
/*     */     
/* 563 */     COSArray inner = new COSArray();
/* 564 */     COSArray outer = new COSArray();
/* 565 */     outer.add((COSBase)COSInteger.get(lastCid));
/*     */     
/* 567 */     State state = State.FIRST;
/*     */     
/* 569 */     for (int i = 4; i < values.length; i += 4) {
/*     */       
/* 571 */       long cid = values[i];
/* 572 */       if (cid != -2147483648L) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 577 */         long w1Value = Math.round(-values[i + 1] * scaling);
/* 578 */         long vxValue = Math.round(values[i + 2] * scaling / 2.0F);
/* 579 */         long vyValue = Math.round(values[i + 3] * scaling);
/*     */         
/* 581 */         switch (state) {
/*     */           
/*     */           case FIRST:
/* 584 */             if (cid == lastCid + 1L && w1Value == lastW1Value && vxValue == lastVxValue && vyValue == lastVyValue) {
/*     */               
/* 586 */               state = State.SERIAL; break;
/*     */             } 
/* 588 */             if (cid == lastCid + 1L) {
/*     */               
/* 590 */               state = State.BRACKET;
/* 591 */               inner = new COSArray();
/* 592 */               inner.add((COSBase)COSInteger.get(lastW1Value));
/* 593 */               inner.add((COSBase)COSInteger.get(lastVxValue));
/* 594 */               inner.add((COSBase)COSInteger.get(lastVyValue));
/*     */               
/*     */               break;
/*     */             } 
/* 598 */             inner = new COSArray();
/* 599 */             inner.add((COSBase)COSInteger.get(lastW1Value));
/* 600 */             inner.add((COSBase)COSInteger.get(lastVxValue));
/* 601 */             inner.add((COSBase)COSInteger.get(lastVyValue));
/* 602 */             outer.add((COSBase)inner);
/* 603 */             outer.add((COSBase)COSInteger.get(cid));
/*     */             break;
/*     */           
/*     */           case BRACKET:
/* 607 */             if (cid == lastCid + 1L && w1Value == lastW1Value && vxValue == lastVxValue && vyValue == lastVyValue) {
/*     */               
/* 609 */               state = State.SERIAL;
/* 610 */               outer.add((COSBase)inner);
/* 611 */               outer.add((COSBase)COSInteger.get(lastCid)); break;
/*     */             } 
/* 613 */             if (cid == lastCid + 1L) {
/*     */               
/* 615 */               inner.add((COSBase)COSInteger.get(lastW1Value));
/* 616 */               inner.add((COSBase)COSInteger.get(lastVxValue));
/* 617 */               inner.add((COSBase)COSInteger.get(lastVyValue));
/*     */               
/*     */               break;
/*     */             } 
/* 621 */             state = State.FIRST;
/* 622 */             inner.add((COSBase)COSInteger.get(lastW1Value));
/* 623 */             inner.add((COSBase)COSInteger.get(lastVxValue));
/* 624 */             inner.add((COSBase)COSInteger.get(lastVyValue));
/* 625 */             outer.add((COSBase)inner);
/* 626 */             outer.add((COSBase)COSInteger.get(cid));
/*     */             break;
/*     */           
/*     */           case SERIAL:
/* 630 */             if (cid != lastCid + 1L || w1Value != lastW1Value || vxValue != lastVxValue || vyValue != lastVyValue) {
/*     */               
/* 632 */               outer.add((COSBase)COSInteger.get(lastCid));
/* 633 */               outer.add((COSBase)COSInteger.get(lastW1Value));
/* 634 */               outer.add((COSBase)COSInteger.get(lastVxValue));
/* 635 */               outer.add((COSBase)COSInteger.get(lastVyValue));
/* 636 */               outer.add((COSBase)COSInteger.get(cid));
/* 637 */               state = State.FIRST;
/*     */             } 
/*     */             break;
/*     */         } 
/* 641 */         lastW1Value = w1Value;
/* 642 */         lastVxValue = vxValue;
/* 643 */         lastVyValue = vyValue;
/* 644 */         lastCid = cid;
/*     */       } 
/*     */     } 
/* 647 */     switch (state) {
/*     */       
/*     */       case FIRST:
/* 650 */         inner = new COSArray();
/* 651 */         inner.add((COSBase)COSInteger.get(lastW1Value));
/* 652 */         inner.add((COSBase)COSInteger.get(lastVxValue));
/* 653 */         inner.add((COSBase)COSInteger.get(lastVyValue));
/* 654 */         outer.add((COSBase)inner);
/*     */         break;
/*     */       case BRACKET:
/* 657 */         inner.add((COSBase)COSInteger.get(lastW1Value));
/* 658 */         inner.add((COSBase)COSInteger.get(lastVxValue));
/* 659 */         inner.add((COSBase)COSInteger.get(lastVyValue));
/* 660 */         outer.add((COSBase)inner);
/*     */         break;
/*     */       case SERIAL:
/* 663 */         outer.add((COSBase)COSInteger.get(lastCid));
/* 664 */         outer.add((COSBase)COSInteger.get(lastW1Value));
/* 665 */         outer.add((COSBase)COSInteger.get(lastVxValue));
/* 666 */         outer.add((COSBase)COSInteger.get(lastVyValue));
/*     */         break;
/*     */     } 
/* 669 */     return outer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDCIDFont getCIDFont() throws IOException {
/* 677 */     return new PDCIDFontType2(this.cidFont, this.parent, this.ttf);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/PDCIDFontType2Embedder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */