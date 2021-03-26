/*      */ package org.apache.fontbox.ttf;
/*      */ 
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.EOFException;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.nio.charset.Charset;
/*      */ import java.util.Calendar;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.SortedMap;
/*      */ import java.util.SortedSet;
/*      */ import java.util.TimeZone;
/*      */ import java.util.TreeMap;
/*      */ import java.util.TreeSet;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class TTFSubsetter
/*      */ {
/*   53 */   private static final Log LOG = LogFactory.getLog(TTFSubsetter.class);
/*      */   
/*   55 */   private static final byte[] PAD_BUF = new byte[] { 0, 0, 0 };
/*      */   
/*      */   private final TrueTypeFont ttf;
/*      */   
/*      */   private final CmapLookup unicodeCmap;
/*      */   
/*      */   private final SortedMap<Integer, Integer> uniToGID;
/*      */   
/*      */   private final List<String> keepTables;
/*      */   
/*      */   private final SortedSet<Integer> glyphIds;
/*      */   
/*      */   private String prefix;
/*      */   
/*      */   private boolean hasAddedCompoundReferences;
/*      */ 
/*      */   
/*      */   public TTFSubsetter(TrueTypeFont ttf) throws IOException {
/*   73 */     this(ttf, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TTFSubsetter(TrueTypeFont ttf, List<String> tables) throws IOException {
/*   84 */     this.ttf = ttf;
/*   85 */     this.keepTables = tables;
/*      */     
/*   87 */     this.uniToGID = new TreeMap<Integer, Integer>();
/*   88 */     this.glyphIds = new TreeSet<Integer>();
/*      */ 
/*      */     
/*   91 */     this.unicodeCmap = ttf.getUnicodeCmapLookup();
/*      */ 
/*      */     
/*   94 */     this.glyphIds.add(Integer.valueOf(0));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPrefix(String prefix) {
/*  102 */     this.prefix = prefix;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void add(int unicode) {
/*  112 */     int gid = this.unicodeCmap.getGlyphId(unicode);
/*  113 */     if (gid != 0) {
/*      */       
/*  115 */       this.uniToGID.put(Integer.valueOf(unicode), Integer.valueOf(gid));
/*  116 */       this.glyphIds.add(Integer.valueOf(gid));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addAll(Set<Integer> unicodeSet) {
/*  127 */     for (Iterator<Integer> iterator = unicodeSet.iterator(); iterator.hasNext(); ) { int unicode = ((Integer)iterator.next()).intValue();
/*      */       
/*  129 */       add(unicode); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Map<Integer, Integer> getGIDMap() throws IOException {
/*  138 */     addCompoundReferences();
/*      */     
/*  140 */     Map<Integer, Integer> newToOld = new HashMap<Integer, Integer>();
/*  141 */     int newGID = 0;
/*  142 */     for (Iterator<Integer> iterator = this.glyphIds.iterator(); iterator.hasNext(); ) { int oldGID = ((Integer)iterator.next()).intValue();
/*      */       
/*  144 */       newToOld.put(Integer.valueOf(newGID), Integer.valueOf(oldGID));
/*  145 */       newGID++; }
/*      */     
/*  147 */     return newToOld;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private long writeFileHeader(DataOutputStream out, int nTables) throws IOException {
/*  158 */     out.writeInt(65536);
/*  159 */     out.writeShort(nTables);
/*      */     
/*  161 */     int mask = Integer.highestOneBit(nTables);
/*  162 */     int searchRange = mask * 16;
/*  163 */     out.writeShort(searchRange);
/*      */     
/*  165 */     int entrySelector = log2(mask);
/*      */     
/*  167 */     out.writeShort(entrySelector);
/*      */ 
/*      */     
/*  170 */     int last = 16 * nTables - searchRange;
/*  171 */     out.writeShort(last);
/*      */     
/*  173 */     return 65536L + toUInt32(nTables, searchRange) + toUInt32(entrySelector, last);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private long writeTableHeader(DataOutputStream out, String tag, long offset, byte[] bytes) throws IOException {
/*  179 */     long checksum = 0L;
/*  180 */     for (int nup = 0, n = bytes.length; nup < n; nup++)
/*      */     {
/*  182 */       checksum += (bytes[nup] & 0xFFL) << 24 - nup % 4 * 8;
/*      */     }
/*  184 */     checksum &= 0xFFFFFFFFL;
/*      */     
/*  186 */     byte[] tagbytes = tag.getBytes("US-ASCII");
/*      */     
/*  188 */     out.write(tagbytes, 0, 4);
/*  189 */     out.writeInt((int)checksum);
/*  190 */     out.writeInt((int)offset);
/*  191 */     out.writeInt(bytes.length);
/*      */ 
/*      */     
/*  194 */     return toUInt32(tagbytes) + checksum + checksum + offset + bytes.length;
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeTableBody(OutputStream os, byte[] bytes) throws IOException {
/*  199 */     int n = bytes.length;
/*  200 */     os.write(bytes);
/*  201 */     if (n % 4 != 0)
/*      */     {
/*  203 */       os.write(PAD_BUF, 0, 4 - n % 4);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private byte[] buildHeadTable() throws IOException {
/*  209 */     ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*  210 */     DataOutputStream out = new DataOutputStream(bos);
/*      */     
/*  212 */     HeaderTable h = this.ttf.getHeader();
/*  213 */     writeFixed(out, h.getVersion());
/*  214 */     writeFixed(out, h.getFontRevision());
/*  215 */     writeUint32(out, 0L);
/*  216 */     writeUint32(out, h.getMagicNumber());
/*  217 */     writeUint16(out, h.getFlags());
/*  218 */     writeUint16(out, h.getUnitsPerEm());
/*  219 */     writeLongDateTime(out, h.getCreated());
/*  220 */     writeLongDateTime(out, h.getModified());
/*  221 */     writeSInt16(out, h.getXMin());
/*  222 */     writeSInt16(out, h.getYMin());
/*  223 */     writeSInt16(out, h.getXMax());
/*  224 */     writeSInt16(out, h.getYMax());
/*  225 */     writeUint16(out, h.getMacStyle());
/*  226 */     writeUint16(out, h.getLowestRecPPEM());
/*  227 */     writeSInt16(out, h.getFontDirectionHint());
/*      */     
/*  229 */     writeSInt16(out, (short)1);
/*  230 */     writeSInt16(out, h.getGlyphDataFormat());
/*  231 */     out.flush();
/*      */     
/*  233 */     return bos.toByteArray();
/*      */   }
/*      */ 
/*      */   
/*      */   private byte[] buildHheaTable() throws IOException {
/*  238 */     ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*  239 */     DataOutputStream out = new DataOutputStream(bos);
/*      */     
/*  241 */     HorizontalHeaderTable h = this.ttf.getHorizontalHeader();
/*  242 */     writeFixed(out, h.getVersion());
/*  243 */     writeSInt16(out, h.getAscender());
/*  244 */     writeSInt16(out, h.getDescender());
/*  245 */     writeSInt16(out, h.getLineGap());
/*  246 */     writeUint16(out, h.getAdvanceWidthMax());
/*  247 */     writeSInt16(out, h.getMinLeftSideBearing());
/*  248 */     writeSInt16(out, h.getMinRightSideBearing());
/*  249 */     writeSInt16(out, h.getXMaxExtent());
/*  250 */     writeSInt16(out, h.getCaretSlopeRise());
/*  251 */     writeSInt16(out, h.getCaretSlopeRun());
/*  252 */     writeSInt16(out, h.getReserved1());
/*  253 */     writeSInt16(out, h.getReserved2());
/*  254 */     writeSInt16(out, h.getReserved3());
/*  255 */     writeSInt16(out, h.getReserved4());
/*  256 */     writeSInt16(out, h.getReserved5());
/*  257 */     writeSInt16(out, h.getMetricDataFormat());
/*      */ 
/*      */ 
/*      */     
/*  261 */     int hmetrics = this.glyphIds.subSet(Integer.valueOf(0), Integer.valueOf(h.getNumberOfHMetrics())).size();
/*  262 */     if (((Integer)this.glyphIds.last()).intValue() >= h.getNumberOfHMetrics() && !this.glyphIds.contains(Integer.valueOf(h.getNumberOfHMetrics() - 1)))
/*      */     {
/*  264 */       hmetrics++;
/*      */     }
/*  266 */     writeUint16(out, hmetrics);
/*      */     
/*  268 */     out.flush();
/*  269 */     return bos.toByteArray();
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean shouldCopyNameRecord(NameRecord nr) {
/*  274 */     return (nr.getPlatformId() == 3 && nr
/*  275 */       .getPlatformEncodingId() == 1 && nr
/*  276 */       .getLanguageId() == 1033 && nr
/*  277 */       .getNameId() >= 0 && nr.getNameId() < 7);
/*      */   }
/*      */ 
/*      */   
/*      */   private byte[] buildNameTable() throws IOException {
/*  282 */     ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*  283 */     DataOutputStream out = new DataOutputStream(bos);
/*      */     
/*  285 */     NamingTable name = this.ttf.getNaming();
/*  286 */     if (name == null || (this.keepTables != null && !this.keepTables.contains("name")))
/*      */     {
/*  288 */       return null;
/*      */     }
/*      */     
/*  291 */     List<NameRecord> nameRecords = name.getNameRecords();
/*  292 */     int numRecords = 0;
/*  293 */     for (NameRecord record : nameRecords) {
/*      */       
/*  295 */       if (shouldCopyNameRecord(record))
/*      */       {
/*  297 */         numRecords++;
/*      */       }
/*      */     } 
/*  300 */     writeUint16(out, 0);
/*  301 */     writeUint16(out, numRecords);
/*  302 */     writeUint16(out, 6 + 12 * numRecords);
/*      */     
/*  304 */     if (numRecords == 0)
/*      */     {
/*  306 */       return null;
/*      */     }
/*      */     
/*  309 */     byte[][] names = new byte[numRecords][];
/*  310 */     int j = 0;
/*  311 */     for (NameRecord record : nameRecords) {
/*      */       
/*  313 */       if (shouldCopyNameRecord(record)) {
/*      */         
/*  315 */         int platform = record.getPlatformId();
/*  316 */         int encoding = record.getPlatformEncodingId();
/*  317 */         String charset = "ISO-8859-1";
/*      */         
/*  319 */         if (platform == 3 && encoding == 1) {
/*      */ 
/*      */           
/*  322 */           charset = "UTF-16BE";
/*      */         }
/*  324 */         else if (platform == 2) {
/*      */           
/*  326 */           if (encoding == 0) {
/*      */             
/*  328 */             charset = "US-ASCII";
/*      */           }
/*  330 */           else if (encoding == 1) {
/*      */ 
/*      */             
/*  333 */             charset = "UTF16-BE";
/*      */           }
/*  335 */           else if (encoding == 2) {
/*      */             
/*  337 */             charset = "ISO-8859-1";
/*      */           } 
/*      */         } 
/*  340 */         String value = record.getString();
/*  341 */         if (record.getNameId() == 6 && this.prefix != null)
/*      */         {
/*  343 */           value = this.prefix + value;
/*      */         }
/*  345 */         names[j] = value.getBytes(charset);
/*  346 */         j++;
/*      */       } 
/*      */     } 
/*      */     
/*  350 */     int offset = 0;
/*  351 */     j = 0;
/*  352 */     for (NameRecord nr : nameRecords) {
/*      */       
/*  354 */       if (shouldCopyNameRecord(nr)) {
/*      */         
/*  356 */         writeUint16(out, nr.getPlatformId());
/*  357 */         writeUint16(out, nr.getPlatformEncodingId());
/*  358 */         writeUint16(out, nr.getLanguageId());
/*  359 */         writeUint16(out, nr.getNameId());
/*  360 */         writeUint16(out, (names[j]).length);
/*  361 */         writeUint16(out, offset);
/*  362 */         offset += (names[j]).length;
/*  363 */         j++;
/*      */       } 
/*      */     } 
/*      */     
/*  367 */     for (int i = 0; i < numRecords; i++)
/*      */     {
/*  369 */       out.write(names[i]);
/*      */     }
/*      */     
/*  372 */     out.flush();
/*  373 */     return bos.toByteArray();
/*      */   }
/*      */ 
/*      */   
/*      */   private byte[] buildMaxpTable() throws IOException {
/*  378 */     ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*  379 */     DataOutputStream out = new DataOutputStream(bos);
/*      */     
/*  381 */     MaximumProfileTable p = this.ttf.getMaximumProfile();
/*  382 */     writeFixed(out, 1.0D);
/*  383 */     writeUint16(out, this.glyphIds.size());
/*  384 */     writeUint16(out, p.getMaxPoints());
/*  385 */     writeUint16(out, p.getMaxContours());
/*  386 */     writeUint16(out, p.getMaxCompositePoints());
/*  387 */     writeUint16(out, p.getMaxCompositeContours());
/*  388 */     writeUint16(out, p.getMaxZones());
/*  389 */     writeUint16(out, p.getMaxTwilightPoints());
/*  390 */     writeUint16(out, p.getMaxStorage());
/*  391 */     writeUint16(out, p.getMaxFunctionDefs());
/*  392 */     writeUint16(out, p.getMaxInstructionDefs());
/*  393 */     writeUint16(out, p.getMaxStackElements());
/*  394 */     writeUint16(out, p.getMaxSizeOfInstructions());
/*  395 */     writeUint16(out, p.getMaxComponentElements());
/*  396 */     writeUint16(out, p.getMaxComponentDepth());
/*      */     
/*  398 */     out.flush();
/*  399 */     return bos.toByteArray();
/*      */   }
/*      */ 
/*      */   
/*      */   private byte[] buildOS2Table() throws IOException {
/*  404 */     OS2WindowsMetricsTable os2 = this.ttf.getOS2Windows();
/*  405 */     if (os2 == null || this.uniToGID.isEmpty() || (this.keepTables != null && !this.keepTables.contains("OS/2")))
/*      */     {
/*  407 */       return null;
/*      */     }
/*      */     
/*  410 */     ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*  411 */     DataOutputStream out = new DataOutputStream(bos);
/*      */     
/*  413 */     writeUint16(out, os2.getVersion());
/*  414 */     writeSInt16(out, os2.getAverageCharWidth());
/*  415 */     writeUint16(out, os2.getWeightClass());
/*  416 */     writeUint16(out, os2.getWidthClass());
/*      */     
/*  418 */     writeSInt16(out, os2.getFsType());
/*      */     
/*  420 */     writeSInt16(out, os2.getSubscriptXSize());
/*  421 */     writeSInt16(out, os2.getSubscriptYSize());
/*  422 */     writeSInt16(out, os2.getSubscriptXOffset());
/*  423 */     writeSInt16(out, os2.getSubscriptYOffset());
/*      */     
/*  425 */     writeSInt16(out, os2.getSuperscriptXSize());
/*  426 */     writeSInt16(out, os2.getSuperscriptYSize());
/*  427 */     writeSInt16(out, os2.getSuperscriptXOffset());
/*  428 */     writeSInt16(out, os2.getSuperscriptYOffset());
/*      */     
/*  430 */     writeSInt16(out, os2.getStrikeoutSize());
/*  431 */     writeSInt16(out, os2.getStrikeoutPosition());
/*  432 */     writeSInt16(out, (short)os2.getFamilyClass());
/*  433 */     out.write(os2.getPanose());
/*      */     
/*  435 */     writeUint32(out, 0L);
/*  436 */     writeUint32(out, 0L);
/*  437 */     writeUint32(out, 0L);
/*  438 */     writeUint32(out, 0L);
/*      */     
/*  440 */     out.write(os2.getAchVendId().getBytes("US-ASCII"));
/*      */     
/*  442 */     writeUint16(out, os2.getFsSelection());
/*  443 */     writeUint16(out, ((Integer)this.uniToGID.firstKey()).intValue());
/*  444 */     writeUint16(out, ((Integer)this.uniToGID.lastKey()).intValue());
/*  445 */     writeUint16(out, os2.getTypoAscender());
/*  446 */     writeUint16(out, os2.getTypoDescender());
/*  447 */     writeUint16(out, os2.getTypoLineGap());
/*  448 */     writeUint16(out, os2.getWinAscent());
/*  449 */     writeUint16(out, os2.getWinDescent());
/*      */     
/*  451 */     out.flush();
/*  452 */     return bos.toByteArray();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] buildLocaTable(long[] newOffsets) throws IOException {
/*  458 */     ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*  459 */     DataOutputStream out = new DataOutputStream(bos);
/*      */     
/*  461 */     for (long offset : newOffsets)
/*      */     {
/*  463 */       writeUint32(out, offset);
/*      */     }
/*      */     
/*  466 */     out.flush();
/*  467 */     return bos.toByteArray();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addCompoundReferences() throws IOException {
/*      */     boolean hasNested;
/*  475 */     if (this.hasAddedCompoundReferences) {
/*      */       return;
/*      */     }
/*      */     
/*  479 */     this.hasAddedCompoundReferences = true;
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/*  484 */       GlyphTable g = this.ttf.getGlyph();
/*  485 */       long[] offsets = this.ttf.getIndexToLocation().getOffsets();
/*  486 */       InputStream is = this.ttf.getOriginalData();
/*  487 */       Set<Integer> glyphIdsToAdd = null;
/*      */       
/*      */       try {
/*  490 */         is.skip(g.getOffset());
/*  491 */         long lastOff = 0L;
/*  492 */         for (Integer glyphId : this.glyphIds)
/*      */         {
/*  494 */           long offset = offsets[glyphId.intValue()];
/*  495 */           long len = offsets[glyphId.intValue() + 1] - offset;
/*  496 */           is.skip(offset - lastOff);
/*  497 */           byte[] buf = new byte[(int)len];
/*  498 */           is.read(buf);
/*      */           
/*  500 */           if (buf.length >= 2 && buf[0] == -1 && buf[1] == -1) {
/*      */             
/*  502 */             int flags, off = 10;
/*      */ 
/*      */             
/*      */             do {
/*  506 */               flags = (buf[off] & 0xFF) << 8 | buf[off + 1] & 0xFF;
/*  507 */               off += 2;
/*  508 */               int ogid = (buf[off] & 0xFF) << 8 | buf[off + 1] & 0xFF;
/*  509 */               if (!this.glyphIds.contains(Integer.valueOf(ogid))) {
/*      */                 
/*  511 */                 if (glyphIdsToAdd == null)
/*      */                 {
/*  513 */                   glyphIdsToAdd = new TreeSet<Integer>();
/*      */                 }
/*  515 */                 glyphIdsToAdd.add(Integer.valueOf(ogid));
/*      */               } 
/*  517 */               off += 2;
/*      */               
/*  519 */               if ((flags & 0x1) != 0) {
/*      */                 
/*  521 */                 off += 4;
/*      */               }
/*      */               else {
/*      */                 
/*  525 */                 off += 2;
/*      */               } 
/*      */               
/*  528 */               if ((flags & 0x80) != 0)
/*      */               {
/*  530 */                 off += 8;
/*      */               
/*      */               }
/*  533 */               else if ((flags & 0x40) != 0)
/*      */               {
/*  535 */                 off += 4;
/*      */               
/*      */               }
/*  538 */               else if ((flags & 0x8) != 0)
/*      */               {
/*  540 */                 off += 2;
/*      */               }
/*      */             
/*  543 */             } while ((flags & 0x20) != 0);
/*      */           } 
/*      */           
/*  546 */           lastOff = offsets[glyphId.intValue() + 1];
/*      */         }
/*      */       
/*      */       } finally {
/*      */         
/*  551 */         is.close();
/*      */       } 
/*  553 */       if (glyphIdsToAdd != null)
/*      */       {
/*  555 */         this.glyphIds.addAll(glyphIdsToAdd);
/*      */       }
/*  557 */       hasNested = (glyphIdsToAdd != null);
/*      */     }
/*  559 */     while (hasNested);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] buildGlyfTable(long[] newOffsets) throws IOException {
/*  565 */     ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*      */     
/*  567 */     GlyphTable g = this.ttf.getGlyph();
/*  568 */     long[] offsets = this.ttf.getIndexToLocation().getOffsets();
/*  569 */     InputStream is = this.ttf.getOriginalData();
/*      */     
/*      */     try {
/*  572 */       is.skip(g.getOffset());
/*      */       
/*  574 */       long prevEnd = 0L;
/*  575 */       long newOffset = 0L;
/*  576 */       int newGid = 0;
/*      */ 
/*      */       
/*  579 */       for (Integer gid : this.glyphIds) {
/*      */         
/*  581 */         long offset = offsets[gid.intValue()];
/*  582 */         long length = offsets[gid.intValue() + 1] - offset;
/*      */         
/*  584 */         newOffsets[newGid++] = newOffset;
/*  585 */         is.skip(offset - prevEnd);
/*      */         
/*  587 */         byte[] buf = new byte[(int)length];
/*  588 */         is.read(buf);
/*      */ 
/*      */         
/*  591 */         if (buf.length >= 2 && buf[0] == -1 && buf[1] == -1) {
/*      */ 
/*      */           
/*  594 */           int flags, off = 10;
/*      */ 
/*      */ 
/*      */           
/*      */           do {
/*  599 */             flags = (buf[off] & 0xFF) << 8 | buf[off + 1] & 0xFF;
/*  600 */             off += 2;
/*      */ 
/*      */             
/*  603 */             int componentGid = (buf[off] & 0xFF) << 8 | buf[off + 1] & 0xFF;
/*  604 */             if (!this.glyphIds.contains(Integer.valueOf(componentGid)))
/*      */             {
/*  606 */               this.glyphIds.add(Integer.valueOf(componentGid));
/*      */             }
/*      */             
/*  609 */             int newComponentGid = getNewGlyphId(Integer.valueOf(componentGid));
/*  610 */             buf[off] = (byte)(newComponentGid >>> 8);
/*  611 */             buf[off + 1] = (byte)newComponentGid;
/*  612 */             off += 2;
/*      */ 
/*      */             
/*  615 */             if ((flags & 0x1) != 0) {
/*      */               
/*  617 */               off += 4;
/*      */             }
/*      */             else {
/*      */               
/*  621 */               off += 2;
/*      */             } 
/*      */             
/*  624 */             if ((flags & 0x80) != 0)
/*      */             {
/*  626 */               off += 8;
/*      */             
/*      */             }
/*  629 */             else if ((flags & 0x40) != 0)
/*      */             {
/*  631 */               off += 4;
/*      */             
/*      */             }
/*  634 */             else if ((flags & 0x8) != 0)
/*      */             {
/*  636 */               off += 2;
/*      */             }
/*      */           
/*  639 */           } while ((flags & 0x20) != 0);
/*      */ 
/*      */           
/*  642 */           if ((flags & 0x100) == 256) {
/*      */ 
/*      */             
/*  645 */             int numInstr = (buf[off] & 0xFF) << 8 | buf[off + 1] & 0xFF;
/*  646 */             off += 2;
/*      */ 
/*      */             
/*  649 */             off += numInstr;
/*      */           } 
/*      */ 
/*      */           
/*  653 */           bos.write(buf, 0, off);
/*      */ 
/*      */           
/*  656 */           newOffset += off;
/*      */         }
/*  658 */         else if (buf.length > 0) {
/*      */ 
/*      */           
/*  661 */           bos.write(buf, 0, buf.length);
/*      */ 
/*      */           
/*  664 */           newOffset += buf.length;
/*      */         } 
/*      */ 
/*      */         
/*  668 */         if (newOffset % 4L != 0L) {
/*      */           
/*  670 */           int len = 4 - (int)(newOffset % 4L);
/*  671 */           bos.write(PAD_BUF, 0, len);
/*  672 */           newOffset += len;
/*      */         } 
/*      */         
/*  675 */         prevEnd = offset + length;
/*      */       } 
/*  677 */       newOffsets[newGid++] = newOffset;
/*      */     }
/*      */     finally {
/*      */       
/*  681 */       is.close();
/*      */     } 
/*      */     
/*  684 */     return bos.toByteArray();
/*      */   }
/*      */ 
/*      */   
/*      */   private int getNewGlyphId(Integer oldGid) {
/*  689 */     return this.glyphIds.headSet(oldGid).size();
/*      */   }
/*      */ 
/*      */   
/*      */   private byte[] buildCmapTable() throws IOException {
/*  694 */     if (this.ttf.getCmap() == null || this.uniToGID.isEmpty() || (this.keepTables != null && !this.keepTables.contains("cmap")))
/*      */     {
/*  696 */       return null;
/*      */     }
/*      */     
/*  699 */     ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*  700 */     DataOutputStream out = new DataOutputStream(bos);
/*      */ 
/*      */     
/*  703 */     writeUint16(out, 0);
/*  704 */     writeUint16(out, 1);
/*      */ 
/*      */     
/*  707 */     writeUint16(out, 3);
/*  708 */     writeUint16(out, 1);
/*  709 */     writeUint32(out, 12L);
/*      */ 
/*      */     
/*  712 */     Iterator<Map.Entry<Integer, Integer>> it = this.uniToGID.entrySet().iterator();
/*  713 */     Map.Entry<Integer, Integer> lastChar = it.next();
/*  714 */     Map.Entry<Integer, Integer> prevChar = lastChar;
/*  715 */     int lastGid = getNewGlyphId(lastChar.getValue());
/*      */ 
/*      */     
/*  718 */     int[] startCode = new int[this.uniToGID.size() + 1];
/*  719 */     int[] endCode = new int[this.uniToGID.size() + 1];
/*  720 */     int[] idDelta = new int[this.uniToGID.size() + 1];
/*  721 */     int segCount = 0;
/*  722 */     while (it.hasNext()) {
/*      */       
/*  724 */       Map.Entry<Integer, Integer> curChar2Gid = it.next();
/*  725 */       int curGid = getNewGlyphId(curChar2Gid.getValue());
/*      */ 
/*      */       
/*  728 */       if (((Integer)curChar2Gid.getKey()).intValue() > 65535)
/*      */       {
/*  730 */         throw new UnsupportedOperationException("non-BMP Unicode character");
/*      */       }
/*      */       
/*  733 */       if (((Integer)curChar2Gid.getKey()).intValue() != ((Integer)prevChar.getKey()).intValue() + 1 || curGid - lastGid != ((Integer)curChar2Gid
/*  734 */         .getKey()).intValue() - ((Integer)lastChar.getKey()).intValue()) {
/*      */         
/*  736 */         if (lastGid != 0) {
/*      */ 
/*      */ 
/*      */           
/*  740 */           startCode[segCount] = ((Integer)lastChar.getKey()).intValue();
/*  741 */           endCode[segCount] = ((Integer)prevChar.getKey()).intValue();
/*  742 */           idDelta[segCount] = lastGid - ((Integer)lastChar.getKey()).intValue();
/*  743 */           segCount++;
/*      */         }
/*  745 */         else if (!((Integer)lastChar.getKey()).equals(prevChar.getKey())) {
/*      */ 
/*      */           
/*  748 */           startCode[segCount] = ((Integer)lastChar.getKey()).intValue() + 1;
/*  749 */           endCode[segCount] = ((Integer)prevChar.getKey()).intValue();
/*  750 */           idDelta[segCount] = lastGid - ((Integer)lastChar.getKey()).intValue();
/*  751 */           segCount++;
/*      */         } 
/*  753 */         lastGid = curGid;
/*  754 */         lastChar = curChar2Gid;
/*      */       } 
/*  756 */       prevChar = curChar2Gid;
/*      */     } 
/*      */ 
/*      */     
/*  760 */     startCode[segCount] = ((Integer)lastChar.getKey()).intValue();
/*  761 */     endCode[segCount] = ((Integer)prevChar.getKey()).intValue();
/*  762 */     idDelta[segCount] = lastGid - ((Integer)lastChar.getKey()).intValue();
/*  763 */     segCount++;
/*      */ 
/*      */     
/*  766 */     startCode[segCount] = 65535;
/*  767 */     endCode[segCount] = 65535;
/*  768 */     idDelta[segCount] = 1;
/*  769 */     segCount++;
/*      */ 
/*      */     
/*  772 */     int searchRange = 2 * (int)Math.pow(2.0D, log2(segCount));
/*  773 */     writeUint16(out, 4);
/*  774 */     writeUint16(out, 16 + segCount * 4 * 2);
/*  775 */     writeUint16(out, 0);
/*  776 */     writeUint16(out, segCount * 2);
/*  777 */     writeUint16(out, searchRange);
/*  778 */     writeUint16(out, log2(searchRange / 2));
/*  779 */     writeUint16(out, 2 * segCount - searchRange);
/*      */     
/*      */     int i;
/*  782 */     for (i = 0; i < segCount; i++)
/*      */     {
/*  784 */       writeUint16(out, endCode[i]);
/*      */     }
/*      */ 
/*      */     
/*  788 */     writeUint16(out, 0);
/*      */ 
/*      */     
/*  791 */     for (i = 0; i < segCount; i++)
/*      */     {
/*  793 */       writeUint16(out, startCode[i]);
/*      */     }
/*      */ 
/*      */     
/*  797 */     for (i = 0; i < segCount; i++)
/*      */     {
/*  799 */       writeUint16(out, idDelta[i]);
/*      */     }
/*      */     
/*  802 */     for (i = 0; i < segCount; i++)
/*      */     {
/*  804 */       writeUint16(out, 0);
/*      */     }
/*      */     
/*  807 */     return bos.toByteArray();
/*      */   }
/*      */ 
/*      */   
/*      */   private byte[] buildPostTable() throws IOException {
/*  812 */     PostScriptTable post = this.ttf.getPostScript();
/*  813 */     if (post == null || (this.keepTables != null && !this.keepTables.contains("post")))
/*      */     {
/*  815 */       return null;
/*      */     }
/*      */     
/*  818 */     ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*  819 */     DataOutputStream out = new DataOutputStream(bos);
/*      */     
/*  821 */     writeFixed(out, 2.0D);
/*  822 */     writeFixed(out, post.getItalicAngle());
/*  823 */     writeSInt16(out, post.getUnderlinePosition());
/*  824 */     writeSInt16(out, post.getUnderlineThickness());
/*  825 */     writeUint32(out, post.getIsFixedPitch());
/*  826 */     writeUint32(out, post.getMinMemType42());
/*  827 */     writeUint32(out, post.getMaxMemType42());
/*  828 */     writeUint32(out, post.getMinMemType1());
/*  829 */     writeUint32(out, post.getMaxMemType1());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  834 */     writeUint16(out, this.glyphIds.size());
/*      */ 
/*      */     
/*  837 */     Map<String, Integer> names = new LinkedHashMap<String, Integer>();
/*  838 */     for (null = this.glyphIds.iterator(); null.hasNext(); ) { int gid = ((Integer)null.next()).intValue();
/*      */       
/*  840 */       String name = post.getName(gid);
/*  841 */       Integer macId = WGL4Names.MAC_GLYPH_NAMES_INDICES.get(name);
/*  842 */       if (macId != null) {
/*      */ 
/*      */         
/*  845 */         writeUint16(out, macId.intValue());
/*      */         
/*      */         continue;
/*      */       } 
/*      */       
/*  850 */       Integer ordinal = names.get(name);
/*  851 */       if (ordinal == null) {
/*      */         
/*  853 */         ordinal = Integer.valueOf(names.size());
/*  854 */         names.put(name, ordinal);
/*      */       } 
/*  856 */       writeUint16(out, 258 + ordinal.intValue()); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  861 */     for (String name : names.keySet()) {
/*      */       
/*  863 */       byte[] buf = name.getBytes(Charset.forName("US-ASCII"));
/*  864 */       writeUint8(out, buf.length);
/*  865 */       out.write(buf);
/*      */     } 
/*      */     
/*  868 */     out.flush();
/*  869 */     return bos.toByteArray();
/*      */   }
/*      */ 
/*      */   
/*      */   private byte[] buildHmtxTable() throws IOException {
/*  874 */     ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*      */     
/*  876 */     HorizontalHeaderTable h = this.ttf.getHorizontalHeader();
/*  877 */     HorizontalMetricsTable hm = this.ttf.getHorizontalMetrics();
/*  878 */     InputStream is = this.ttf.getOriginalData();
/*      */ 
/*      */     
/*  881 */     int lastgid = h.getNumberOfHMetrics() - 1;
/*      */     
/*  883 */     boolean needLastGidWidth = false;
/*  884 */     if (((Integer)this.glyphIds.last()).intValue() > lastgid && !this.glyphIds.contains(Integer.valueOf(lastgid)))
/*      */     {
/*  886 */       needLastGidWidth = true;
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  891 */       is.skip(hm.getOffset());
/*  892 */       long lastOffset = 0L;
/*  893 */       for (Integer glyphId : this.glyphIds) {
/*      */ 
/*      */ 
/*      */         
/*  897 */         if (glyphId.intValue() <= lastgid) {
/*      */ 
/*      */           
/*  900 */           long l = (glyphId.intValue() * 4);
/*  901 */           lastOffset = copyBytes(is, bos, l, lastOffset, 4);
/*      */           
/*      */           continue;
/*      */         } 
/*  905 */         if (needLastGidWidth) {
/*      */ 
/*      */ 
/*      */           
/*  909 */           needLastGidWidth = false;
/*  910 */           long l = (lastgid * 4);
/*  911 */           lastOffset = copyBytes(is, bos, l, lastOffset, 2);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  917 */         long offset = (h.getNumberOfHMetrics() * 4 + (glyphId.intValue() - h.getNumberOfHMetrics()) * 2);
/*  918 */         lastOffset = copyBytes(is, bos, offset, lastOffset, 2);
/*      */       } 
/*      */ 
/*      */       
/*  922 */       return bos.toByteArray();
/*      */     }
/*      */     finally {
/*      */       
/*  926 */       is.close();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private long copyBytes(InputStream is, OutputStream os, long newOffset, long lastOffset, int count) throws IOException {
/*  934 */     long nskip = newOffset - lastOffset;
/*  935 */     if (nskip != is.skip(nskip))
/*      */     {
/*  937 */       throw new EOFException("Unexpected EOF exception parsing glyphId of hmtx table.");
/*      */     }
/*  939 */     byte[] buf = new byte[count];
/*  940 */     if (count != is.read(buf, 0, count))
/*      */     {
/*  942 */       throw new EOFException("Unexpected EOF exception parsing glyphId of hmtx table.");
/*      */     }
/*  944 */     os.write(buf, 0, count);
/*  945 */     return newOffset + count;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeToStream(OutputStream os) throws IOException {
/*  957 */     if (this.glyphIds.isEmpty() || this.uniToGID.isEmpty())
/*      */     {
/*  959 */       LOG.info("font subset is empty");
/*      */     }
/*      */     
/*  962 */     addCompoundReferences();
/*      */     
/*  964 */     DataOutputStream out = new DataOutputStream(os);
/*      */     
/*      */     try {
/*  967 */       long[] newLoca = new long[this.glyphIds.size() + 1];
/*      */ 
/*      */       
/*  970 */       byte[] head = buildHeadTable();
/*  971 */       byte[] hhea = buildHheaTable();
/*  972 */       byte[] maxp = buildMaxpTable();
/*  973 */       byte[] name = buildNameTable();
/*  974 */       byte[] os2 = buildOS2Table();
/*  975 */       byte[] glyf = buildGlyfTable(newLoca);
/*  976 */       byte[] loca = buildLocaTable(newLoca);
/*  977 */       byte[] cmap = buildCmapTable();
/*  978 */       byte[] hmtx = buildHmtxTable();
/*  979 */       byte[] post = buildPostTable();
/*      */ 
/*      */       
/*  982 */       Map<String, byte[]> tables = (Map)new TreeMap<String, byte>();
/*  983 */       if (os2 != null)
/*      */       {
/*  985 */         tables.put("OS/2", os2);
/*      */       }
/*  987 */       if (cmap != null)
/*      */       {
/*  989 */         tables.put("cmap", cmap);
/*      */       }
/*  991 */       tables.put("glyf", glyf);
/*  992 */       tables.put("head", head);
/*  993 */       tables.put("hhea", hhea);
/*  994 */       tables.put("hmtx", hmtx);
/*  995 */       tables.put("loca", loca);
/*  996 */       tables.put("maxp", maxp);
/*  997 */       if (name != null)
/*      */       {
/*  999 */         tables.put("name", name);
/*      */       }
/* 1001 */       if (post != null)
/*      */       {
/* 1003 */         tables.put("post", post);
/*      */       }
/*      */ 
/*      */       
/* 1007 */       for (Map.Entry<String, TTFTable> entry : this.ttf.getTableMap().entrySet()) {
/*      */         
/* 1009 */         String tag = entry.getKey();
/* 1010 */         TTFTable table = entry.getValue();
/*      */         
/* 1012 */         if (!tables.containsKey(tag) && (this.keepTables == null || this.keepTables.contains(tag)))
/*      */         {
/* 1014 */           tables.put(tag, this.ttf.getTableBytes(table));
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1019 */       long checksum = writeFileHeader(out, tables.size());
/* 1020 */       long offset = 12L + 16L * tables.size();
/* 1021 */       for (Map.Entry<String, byte[]> entry : tables.entrySet()) {
/*      */         
/* 1023 */         checksum += writeTableHeader(out, entry.getKey(), offset, entry.getValue());
/* 1024 */         offset += ((((byte[])entry.getValue()).length + 3) / 4 * 4);
/*      */       } 
/* 1026 */       checksum = 2981146554L - (checksum & 0xFFFFFFFFL);
/*      */ 
/*      */       
/* 1029 */       head[8] = (byte)(int)(checksum >>> 24L);
/* 1030 */       head[9] = (byte)(int)(checksum >>> 16L);
/* 1031 */       head[10] = (byte)(int)(checksum >>> 8L);
/* 1032 */       head[11] = (byte)(int)checksum;
/* 1033 */       for (byte[] bytes : tables.values())
/*      */       {
/* 1035 */         writeTableBody(out, bytes);
/*      */       }
/*      */     }
/*      */     finally {
/*      */       
/* 1040 */       out.close();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeFixed(DataOutputStream out, double f) throws IOException {
/* 1046 */     double ip = Math.floor(f);
/* 1047 */     double fp = (f - ip) * 65536.0D;
/* 1048 */     out.writeShort((int)ip);
/* 1049 */     out.writeShort((int)fp);
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeUint32(DataOutputStream out, long l) throws IOException {
/* 1054 */     out.writeInt((int)l);
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeUint16(DataOutputStream out, int i) throws IOException {
/* 1059 */     out.writeShort(i);
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeSInt16(DataOutputStream out, short i) throws IOException {
/* 1064 */     out.writeShort(i);
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeUint8(DataOutputStream out, int i) throws IOException {
/* 1069 */     out.writeByte(i);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeLongDateTime(DataOutputStream out, Calendar calendar) throws IOException {
/* 1075 */     Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone("UTC"));
/* 1076 */     cal.set(1904, 0, 1, 0, 0, 0);
/* 1077 */     cal.set(14, 0);
/* 1078 */     long millisFor1904 = cal.getTimeInMillis();
/* 1079 */     long secondsSince1904 = (calendar.getTimeInMillis() - millisFor1904) / 1000L;
/* 1080 */     out.writeLong(secondsSince1904);
/*      */   }
/*      */ 
/*      */   
/*      */   private long toUInt32(int high, int low) {
/* 1085 */     return (high & 0xFFFFL) << 16L | low & 0xFFFFL;
/*      */   }
/*      */ 
/*      */   
/*      */   private long toUInt32(byte[] bytes) {
/* 1090 */     return (bytes[0] & 0xFFL) << 24L | (bytes[1] & 0xFFL) << 16L | (bytes[2] & 0xFFL) << 8L | bytes[3] & 0xFFL;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int log2(int num) {
/* 1098 */     return (int)Math.round(Math.log(num) / Math.log(2.0D));
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/TTFSubsetter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */