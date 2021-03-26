/*      */ package org.apache.fontbox.cff;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ import org.apache.fontbox.util.Charsets;
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
/*      */ public class CFFParser
/*      */ {
/*   41 */   private static final Log LOG = LogFactory.getLog(CFFParser.class);
/*      */   
/*      */   private static final String TAG_OTTO = "OTTO";
/*      */   
/*      */   private static final String TAG_TTCF = "ttcf";
/*      */   private static final String TAG_TTFONLY = "\000\001\000\000";
/*   47 */   private String[] stringIndex = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ByteSource source;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String debugFontName;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<CFFFont> parse(byte[] bytes, ByteSource source) throws IOException {
/*   74 */     this.source = source;
/*   75 */     return parse(bytes);
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
/*      */   public List<CFFFont> parse(byte[] bytes) throws IOException {
/*   87 */     CFFDataInput input = new CFFDataInput(bytes);
/*      */     
/*   89 */     String firstTag = readTagName(input);
/*      */     
/*   91 */     if ("OTTO".equals(firstTag)) {
/*      */       
/*   93 */       input = createTaggedCFFDataInput(input, bytes);
/*      */     } else {
/*   95 */       if ("ttcf".equals(firstTag))
/*      */       {
/*   97 */         throw new IOException("True Type Collection fonts are not supported.");
/*      */       }
/*   99 */       if ("\000\001\000\000".equals(firstTag))
/*      */       {
/*  101 */         throw new IOException("OpenType fonts containing a true type font are not supported.");
/*      */       }
/*      */ 
/*      */       
/*  105 */       input.setPosition(0);
/*      */     } 
/*      */ 
/*      */     
/*  109 */     Header header = readHeader(input);
/*  110 */     String[] nameIndex = readStringIndexData(input);
/*  111 */     if (nameIndex == null)
/*      */     {
/*  113 */       throw new IOException("Name index missing in CFF font");
/*      */     }
/*  115 */     byte[][] topDictIndex = readIndexData(input);
/*  116 */     this.stringIndex = readStringIndexData(input);
/*  117 */     byte[][] globalSubrIndex = readIndexData(input);
/*      */     
/*  119 */     List<CFFFont> fonts = new ArrayList<CFFFont>();
/*  120 */     for (int i = 0; i < nameIndex.length; i++) {
/*      */       
/*  122 */       CFFFont font = parseFont(input, nameIndex[i], topDictIndex[i]);
/*  123 */       font.setGlobalSubrIndex(globalSubrIndex);
/*  124 */       font.setData(this.source);
/*  125 */       fonts.add(font);
/*      */     } 
/*  127 */     return fonts;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private CFFDataInput createTaggedCFFDataInput(CFFDataInput input, byte[] bytes) throws IOException {
/*  134 */     short numTables = input.readShort();
/*      */     
/*  136 */     short searchRange = input.readShort();
/*      */     
/*  138 */     short entrySelector = input.readShort();
/*      */     
/*  140 */     short rangeShift = input.readShort();
/*  141 */     for (int q = 0; q < numTables; q++) {
/*      */       
/*  143 */       String tagName = readTagName(input);
/*      */       
/*  145 */       long checksum = readLong(input);
/*  146 */       long offset = readLong(input);
/*  147 */       long length = readLong(input);
/*  148 */       if ("CFF ".equals(tagName)) {
/*      */         
/*  150 */         byte[] bytes2 = Arrays.copyOfRange(bytes, (int)offset, (int)(offset + length));
/*  151 */         return new CFFDataInput(bytes2);
/*      */       } 
/*      */     } 
/*  154 */     throw new IOException("CFF tag not found in this OpenType font.");
/*      */   }
/*      */ 
/*      */   
/*      */   private static String readTagName(CFFDataInput input) throws IOException {
/*  159 */     byte[] b = input.readBytes(4);
/*  160 */     return new String(b, Charsets.ISO_8859_1);
/*      */   }
/*      */ 
/*      */   
/*      */   private static long readLong(CFFDataInput input) throws IOException {
/*  165 */     return (input.readCard16() << 16 | input.readCard16());
/*      */   }
/*      */ 
/*      */   
/*      */   private static Header readHeader(CFFDataInput input) throws IOException {
/*  170 */     Header cffHeader = new Header();
/*  171 */     cffHeader.major = input.readCard8();
/*  172 */     cffHeader.minor = input.readCard8();
/*  173 */     cffHeader.hdrSize = input.readCard8();
/*  174 */     cffHeader.offSize = input.readOffSize();
/*  175 */     return cffHeader;
/*      */   }
/*      */ 
/*      */   
/*      */   private static int[] readIndexDataOffsets(CFFDataInput input) throws IOException {
/*  180 */     int count = input.readCard16();
/*  181 */     if (count == 0)
/*      */     {
/*  183 */       return null;
/*      */     }
/*  185 */     int offSize = input.readOffSize();
/*  186 */     int[] offsets = new int[count + 1];
/*  187 */     for (int i = 0; i <= count; i++) {
/*      */       
/*  189 */       int offset = input.readOffset(offSize);
/*  190 */       if (offset > input.length())
/*      */       {
/*  192 */         throw new IOException("illegal offset value " + offset + " in CFF font");
/*      */       }
/*  194 */       offsets[i] = offset;
/*      */     } 
/*  196 */     return offsets;
/*      */   }
/*      */ 
/*      */   
/*      */   private static byte[][] readIndexData(CFFDataInput input) throws IOException {
/*  201 */     int[] offsets = readIndexDataOffsets(input);
/*  202 */     if (offsets == null)
/*      */     {
/*  204 */       return (byte[][])null;
/*      */     }
/*  206 */     int count = offsets.length - 1;
/*  207 */     byte[][] indexDataValues = new byte[count][];
/*  208 */     for (int i = 0; i < count; i++) {
/*      */       
/*  210 */       int length = offsets[i + 1] - offsets[i];
/*  211 */       indexDataValues[i] = input.readBytes(length);
/*      */     } 
/*  213 */     return indexDataValues;
/*      */   }
/*      */ 
/*      */   
/*      */   private static String[] readStringIndexData(CFFDataInput input) throws IOException {
/*  218 */     int[] offsets = readIndexDataOffsets(input);
/*  219 */     if (offsets == null)
/*      */     {
/*  221 */       return null;
/*      */     }
/*  223 */     int count = offsets.length - 1;
/*  224 */     String[] indexDataValues = new String[count];
/*  225 */     for (int i = 0; i < count; i++) {
/*      */       
/*  227 */       int length = offsets[i + 1] - offsets[i];
/*  228 */       if (length < 0)
/*      */       {
/*  230 */         throw new IOException("Negative index data length + " + length + " at " + i + ": offsets[" + (i + 1) + "]=" + offsets[i + 1] + ", offsets[" + i + "]=" + offsets[i]);
/*      */       }
/*      */ 
/*      */       
/*  234 */       indexDataValues[i] = new String(input.readBytes(length), Charsets.ISO_8859_1);
/*      */     } 
/*  236 */     return indexDataValues;
/*      */   }
/*      */ 
/*      */   
/*      */   private static DictData readDictData(CFFDataInput input) throws IOException {
/*  241 */     DictData dict = new DictData();
/*  242 */     while (input.hasRemaining())
/*      */     {
/*  244 */       dict.add(readEntry(input));
/*      */     }
/*  246 */     return dict;
/*      */   }
/*      */ 
/*      */   
/*      */   private static DictData readDictData(CFFDataInput input, int dictSize) throws IOException {
/*  251 */     DictData dict = new DictData();
/*  252 */     int endPosition = input.getPosition() + dictSize;
/*  253 */     while (input.getPosition() < endPosition)
/*      */     {
/*  255 */       dict.add(readEntry(input));
/*      */     }
/*  257 */     return dict;
/*      */   }
/*      */ 
/*      */   
/*      */   private static DictData.Entry readEntry(CFFDataInput input) throws IOException {
/*  262 */     DictData.Entry entry = new DictData.Entry();
/*      */     
/*      */     while (true) {
/*  265 */       int b0 = input.readUnsignedByte();
/*      */       
/*  267 */       if (b0 >= 0 && b0 <= 21) {
/*      */         
/*  269 */         entry.operator = readOperator(input, b0);
/*      */         break;
/*      */       } 
/*  272 */       if (b0 == 28 || b0 == 29) {
/*      */         
/*  274 */         entry.operands.add(readIntegerNumber(input, b0)); continue;
/*      */       } 
/*  276 */       if (b0 == 30) {
/*      */         
/*  278 */         entry.operands.add(readRealNumber(input, b0)); continue;
/*      */       } 
/*  280 */       if (b0 >= 32 && b0 <= 254) {
/*      */         
/*  282 */         entry.operands.add(readIntegerNumber(input, b0));
/*      */         
/*      */         continue;
/*      */       } 
/*  286 */       throw new IOException("invalid DICT data b0 byte: " + b0);
/*      */     } 
/*      */     
/*  289 */     return entry;
/*      */   }
/*      */ 
/*      */   
/*      */   private static CFFOperator readOperator(CFFDataInput input, int b0) throws IOException {
/*  294 */     CFFOperator.Key key = readOperatorKey(input, b0);
/*  295 */     return CFFOperator.getOperator(key);
/*      */   }
/*      */ 
/*      */   
/*      */   private static CFFOperator.Key readOperatorKey(CFFDataInput input, int b0) throws IOException {
/*  300 */     if (b0 == 12) {
/*      */       
/*  302 */       int b1 = input.readUnsignedByte();
/*  303 */       return new CFFOperator.Key(b0, b1);
/*      */     } 
/*  305 */     return new CFFOperator.Key(b0);
/*      */   }
/*      */ 
/*      */   
/*      */   private static Integer readIntegerNumber(CFFDataInput input, int b0) throws IOException {
/*  310 */     if (b0 == 28)
/*      */     {
/*  312 */       return Integer.valueOf(input.readShort());
/*      */     }
/*  314 */     if (b0 == 29)
/*      */     {
/*  316 */       return Integer.valueOf(input.readInt());
/*      */     }
/*  318 */     if (b0 >= 32 && b0 <= 246)
/*      */     {
/*  320 */       return Integer.valueOf(b0 - 139);
/*      */     }
/*  322 */     if (b0 >= 247 && b0 <= 250) {
/*      */       
/*  324 */       int b1 = input.readUnsignedByte();
/*  325 */       return Integer.valueOf((b0 - 247) * 256 + b1 + 108);
/*      */     } 
/*  327 */     if (b0 >= 251 && b0 <= 254) {
/*      */       
/*  329 */       int b1 = input.readUnsignedByte();
/*  330 */       return Integer.valueOf(-(b0 - 251) * 256 - b1 - 108);
/*      */     } 
/*      */ 
/*      */     
/*  334 */     throw new IllegalArgumentException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Double readRealNumber(CFFDataInput input, int b0) throws IOException {
/*  343 */     StringBuilder sb = new StringBuilder();
/*  344 */     boolean done = false;
/*  345 */     boolean exponentMissing = false;
/*  346 */     boolean hasExponent = false;
/*  347 */     while (!done) {
/*      */       
/*  349 */       int b = input.readUnsignedByte();
/*  350 */       int[] nibbles = { b / 16, b % 16 };
/*  351 */       for (int nibble : nibbles) {
/*      */         
/*  353 */         switch (nibble) {
/*      */           
/*      */           case 0:
/*      */           case 1:
/*      */           case 2:
/*      */           case 3:
/*      */           case 4:
/*      */           case 5:
/*      */           case 6:
/*      */           case 7:
/*      */           case 8:
/*      */           case 9:
/*  365 */             sb.append(nibble);
/*  366 */             exponentMissing = false;
/*      */             break;
/*      */           case 10:
/*  369 */             sb.append(".");
/*      */             break;
/*      */           case 11:
/*  372 */             if (hasExponent) {
/*      */               
/*  374 */               LOG.warn("duplicate 'E' ignored after " + sb);
/*      */               break;
/*      */             } 
/*  377 */             sb.append("E");
/*  378 */             exponentMissing = true;
/*  379 */             hasExponent = true;
/*      */             break;
/*      */           case 12:
/*  382 */             if (hasExponent) {
/*      */               
/*  384 */               LOG.warn("duplicate 'E-' ignored after " + sb);
/*      */               break;
/*      */             } 
/*  387 */             sb.append("E-");
/*  388 */             exponentMissing = true;
/*  389 */             hasExponent = true;
/*      */             break;
/*      */           case 13:
/*      */             break;
/*      */           case 14:
/*  394 */             sb.append("-");
/*      */             break;
/*      */           case 15:
/*  397 */             done = true;
/*      */             break;
/*      */           default:
/*  400 */             throw new IllegalArgumentException();
/*      */         } 
/*      */       } 
/*      */     } 
/*  404 */     if (exponentMissing)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/*  409 */       sb.append("0");
/*      */     }
/*  411 */     if (sb.length() == 0)
/*      */     {
/*  413 */       return Double.valueOf(0.0D);
/*      */     }
/*  415 */     return Double.valueOf(sb.toString());
/*      */   }
/*      */   
/*      */   private CFFFont parseFont(CFFDataInput input, String name, byte[] topDictIndex) throws IOException {
/*      */     CFFFont font;
/*      */     CFFCharset charset;
/*  421 */     CFFDataInput topDictInput = new CFFDataInput(topDictIndex);
/*  422 */     DictData topDict = readDictData(topDictInput);
/*      */ 
/*      */     
/*  425 */     DictData.Entry syntheticBaseEntry = topDict.getEntry("SyntheticBase");
/*  426 */     if (syntheticBaseEntry != null)
/*      */     {
/*  428 */       throw new IOException("Synthetic Fonts are not supported");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  433 */     boolean isCIDFont = (topDict.getEntry("ROS") != null);
/*  434 */     if (isCIDFont) {
/*      */       
/*  436 */       font = new CFFCIDFont();
/*  437 */       DictData.Entry rosEntry = topDict.getEntry("ROS");
/*  438 */       ((CFFCIDFont)font).setRegistry(readString(rosEntry.getNumber(0).intValue()));
/*  439 */       ((CFFCIDFont)font).setOrdering(readString(rosEntry.getNumber(1).intValue()));
/*  440 */       ((CFFCIDFont)font).setSupplement(rosEntry.getNumber(2).intValue());
/*      */     }
/*      */     else {
/*      */       
/*  444 */       font = new CFFType1Font();
/*      */     } 
/*      */ 
/*      */     
/*  448 */     this.debugFontName = name;
/*  449 */     font.setName(name);
/*      */ 
/*      */     
/*  452 */     font.addValueToTopDict("version", getString(topDict, "version"));
/*  453 */     font.addValueToTopDict("Notice", getString(topDict, "Notice"));
/*  454 */     font.addValueToTopDict("Copyright", getString(topDict, "Copyright"));
/*  455 */     font.addValueToTopDict("FullName", getString(topDict, "FullName"));
/*  456 */     font.addValueToTopDict("FamilyName", getString(topDict, "FamilyName"));
/*  457 */     font.addValueToTopDict("Weight", getString(topDict, "Weight"));
/*  458 */     font.addValueToTopDict("isFixedPitch", topDict.getBoolean("isFixedPitch", false));
/*  459 */     font.addValueToTopDict("ItalicAngle", topDict.getNumber("ItalicAngle", Integer.valueOf(0)));
/*  460 */     font.addValueToTopDict("UnderlinePosition", topDict.getNumber("UnderlinePosition", Integer.valueOf(-100)));
/*  461 */     font.addValueToTopDict("UnderlineThickness", topDict.getNumber("UnderlineThickness", Integer.valueOf(50)));
/*  462 */     font.addValueToTopDict("PaintType", topDict.getNumber("PaintType", Integer.valueOf(0)));
/*  463 */     font.addValueToTopDict("CharstringType", topDict.getNumber("CharstringType", Integer.valueOf(2)));
/*  464 */     font.addValueToTopDict("FontMatrix", topDict.getArray("FontMatrix", Arrays.asList(new Number[] {
/*  465 */               Double.valueOf(0.001D), Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(0.001D), 
/*  466 */               Double.valueOf(0.0D), Double.valueOf(0.0D) })));
/*  467 */     font.addValueToTopDict("UniqueID", topDict.getNumber("UniqueID", null));
/*  468 */     font.addValueToTopDict("FontBBox", topDict.getArray("FontBBox", 
/*  469 */           Arrays.asList(new Number[] { Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0) })));
/*  470 */     font.addValueToTopDict("StrokeWidth", topDict.getNumber("StrokeWidth", Integer.valueOf(0)));
/*  471 */     font.addValueToTopDict("XUID", topDict.getArray("XUID", null));
/*      */ 
/*      */     
/*  474 */     DictData.Entry charStringsEntry = topDict.getEntry("CharStrings");
/*  475 */     int charStringsOffset = charStringsEntry.getNumber(0).intValue();
/*  476 */     input.setPosition(charStringsOffset);
/*  477 */     byte[][] charStringsIndex = readIndexData(input);
/*      */ 
/*      */     
/*  480 */     DictData.Entry charsetEntry = topDict.getEntry("charset");
/*      */     
/*  482 */     if (charsetEntry != null) {
/*      */       
/*  484 */       int charsetId = charsetEntry.getNumber(0).intValue();
/*  485 */       if (!isCIDFont && charsetId == 0)
/*      */       {
/*  487 */         charset = CFFISOAdobeCharset.getInstance();
/*      */       }
/*  489 */       else if (!isCIDFont && charsetId == 1)
/*      */       {
/*  491 */         charset = CFFExpertCharset.getInstance();
/*      */       }
/*  493 */       else if (!isCIDFont && charsetId == 2)
/*      */       {
/*  495 */         charset = CFFExpertSubsetCharset.getInstance();
/*      */       }
/*      */       else
/*      */       {
/*  499 */         input.setPosition(charsetId);
/*  500 */         charset = readCharset(input, charStringsIndex.length, isCIDFont);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  505 */     else if (isCIDFont) {
/*      */ 
/*      */       
/*  508 */       charset = new EmptyCharset(charStringsIndex.length);
/*      */     }
/*      */     else {
/*      */       
/*  512 */       charset = CFFISOAdobeCharset.getInstance();
/*      */     } 
/*      */     
/*  515 */     font.setCharset(charset);
/*      */ 
/*      */     
/*  518 */     font.charStrings = charStringsIndex;
/*      */ 
/*      */     
/*  521 */     if (isCIDFont) {
/*      */       
/*  523 */       parseCIDFontDicts(input, topDict, (CFFCIDFont)font, charStringsIndex.length);
/*      */       
/*  525 */       List<Number> privMatrix = null;
/*  526 */       List<Map<String, Object>> fontDicts = ((CFFCIDFont)font).getFontDicts();
/*  527 */       if (!fontDicts.isEmpty() && ((Map)fontDicts.get(0)).containsKey("FontMatrix"))
/*      */       {
/*  529 */         privMatrix = (List<Number>)((Map)fontDicts.get(0)).get("FontMatrix");
/*      */       }
/*      */       
/*  532 */       List<Number> matrix = topDict.getArray("FontMatrix", null);
/*  533 */       if (matrix == null) {
/*      */         
/*  535 */         if (privMatrix != null)
/*      */         {
/*  537 */           font.addValueToTopDict("FontMatrix", privMatrix);
/*      */         
/*      */         }
/*      */         else
/*      */         {
/*  542 */           font.addValueToTopDict("FontMatrix", topDict.getArray("FontMatrix", 
/*  543 */                 Arrays.asList(new Number[] { Double.valueOf(0.001D), Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(0.001D), 
/*  544 */                     Double.valueOf(0.0D), Double.valueOf(0.0D) })));
/*      */         }
/*      */       
/*  547 */       } else if (privMatrix != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  552 */         concatenateMatrix(matrix, privMatrix);
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  558 */       parseType1Dicts(input, topDict, (CFFType1Font)font, charset);
/*      */     } 
/*      */     
/*  561 */     return font;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void concatenateMatrix(List<Number> matrixDest, List<Number> matrixConcat) {
/*  570 */     double a1 = ((Number)matrixDest.get(0)).doubleValue();
/*  571 */     double b1 = ((Number)matrixDest.get(1)).doubleValue();
/*  572 */     double c1 = ((Number)matrixDest.get(2)).doubleValue();
/*  573 */     double d1 = ((Number)matrixDest.get(3)).doubleValue();
/*  574 */     double x1 = ((Number)matrixDest.get(4)).doubleValue();
/*  575 */     double y1 = ((Number)matrixDest.get(5)).doubleValue();
/*      */     
/*  577 */     double a2 = ((Number)matrixConcat.get(0)).doubleValue();
/*  578 */     double b2 = ((Number)matrixConcat.get(1)).doubleValue();
/*  579 */     double c2 = ((Number)matrixConcat.get(2)).doubleValue();
/*  580 */     double d2 = ((Number)matrixConcat.get(3)).doubleValue();
/*  581 */     double x2 = ((Number)matrixConcat.get(4)).doubleValue();
/*  582 */     double y2 = ((Number)matrixConcat.get(5)).doubleValue();
/*      */     
/*  584 */     matrixDest.set(0, Double.valueOf(a1 * a2 + b1 * c2));
/*  585 */     matrixDest.set(1, Double.valueOf(a1 * b2 + b1 * d1));
/*  586 */     matrixDest.set(2, Double.valueOf(c1 * a2 + d1 * c2));
/*  587 */     matrixDest.set(3, Double.valueOf(c1 * b2 + d1 * d2));
/*  588 */     matrixDest.set(4, Double.valueOf(x1 * a2 + y1 * c2 + x2));
/*  589 */     matrixDest.set(5, Double.valueOf(x1 * b2 + y1 * d2 + y2));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void parseCIDFontDicts(CFFDataInput input, DictData topDict, CFFCIDFont font, int nrOfcharStrings) throws IOException {
/*  600 */     DictData.Entry fdArrayEntry = topDict.getEntry("FDArray");
/*  601 */     if (fdArrayEntry == null)
/*      */     {
/*  603 */       throw new IOException("FDArray is missing for a CIDKeyed Font.");
/*      */     }
/*      */ 
/*      */     
/*  607 */     int fontDictOffset = fdArrayEntry.getNumber(0).intValue();
/*  608 */     input.setPosition(fontDictOffset);
/*  609 */     byte[][] fdIndex = readIndexData(input);
/*      */     
/*  611 */     List<Map<String, Object>> privateDictionaries = new LinkedList<Map<String, Object>>();
/*  612 */     List<Map<String, Object>> fontDictionaries = new LinkedList<Map<String, Object>>();
/*      */     
/*  614 */     for (byte[] bytes : fdIndex) {
/*      */       
/*  616 */       CFFDataInput fontDictInput = new CFFDataInput(bytes);
/*  617 */       DictData fontDict = readDictData(fontDictInput);
/*      */ 
/*      */       
/*  620 */       DictData.Entry privateEntry = fontDict.getEntry("Private");
/*  621 */       if (privateEntry == null)
/*      */       {
/*  623 */         throw new IOException("Font DICT invalid without \"Private\" entry");
/*      */       }
/*      */ 
/*      */       
/*  627 */       Map<String, Object> fontDictMap = new LinkedHashMap<String, Object>(4);
/*  628 */       fontDictMap.put("FontName", getString(fontDict, "FontName"));
/*  629 */       fontDictMap.put("FontType", fontDict.getNumber("FontType", Integer.valueOf(0)));
/*  630 */       fontDictMap.put("FontBBox", fontDict.getArray("FontBBox", null));
/*  631 */       fontDictMap.put("FontMatrix", fontDict.getArray("FontMatrix", null));
/*      */       
/*  633 */       fontDictionaries.add(fontDictMap);
/*      */       
/*  635 */       int privateOffset = privateEntry.getNumber(1).intValue();
/*  636 */       input.setPosition(privateOffset);
/*  637 */       int privateSize = privateEntry.getNumber(0).intValue();
/*  638 */       DictData privateDict = readDictData(input, privateSize);
/*      */ 
/*      */       
/*  641 */       Map<String, Object> privDict = readPrivateDict(privateDict);
/*  642 */       privateDictionaries.add(privDict);
/*      */ 
/*      */       
/*  645 */       int localSubrOffset = ((Integer)privateDict.getNumber("Subrs", Integer.valueOf(0))).intValue();
/*  646 */       if (localSubrOffset > 0) {
/*      */         
/*  648 */         input.setPosition(privateOffset + localSubrOffset);
/*  649 */         privDict.put("Subrs", readIndexData(input));
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  654 */     DictData.Entry fdSelectEntry = topDict.getEntry("FDSelect");
/*  655 */     int fdSelectPos = fdSelectEntry.getNumber(0).intValue();
/*  656 */     input.setPosition(fdSelectPos);
/*  657 */     FDSelect fdSelect = readFDSelect(input, nrOfcharStrings, font);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  663 */     font.setFontDict(fontDictionaries);
/*  664 */     font.setPrivDict(privateDictionaries);
/*  665 */     font.setFdSelect(fdSelect);
/*      */   }
/*      */ 
/*      */   
/*      */   private Map<String, Object> readPrivateDict(DictData privateDict) {
/*  670 */     Map<String, Object> privDict = new LinkedHashMap<String, Object>(17);
/*  671 */     privDict.put("BlueValues", privateDict.getDelta("BlueValues", null));
/*  672 */     privDict.put("OtherBlues", privateDict.getDelta("OtherBlues", null));
/*  673 */     privDict.put("FamilyBlues", privateDict.getDelta("FamilyBlues", null));
/*  674 */     privDict.put("FamilyOtherBlues", privateDict.getDelta("FamilyOtherBlues", null));
/*  675 */     privDict.put("BlueScale", privateDict.getNumber("BlueScale", Double.valueOf(0.039625D)));
/*  676 */     privDict.put("BlueShift", privateDict.getNumber("BlueShift", Integer.valueOf(7)));
/*  677 */     privDict.put("BlueFuzz", privateDict.getNumber("BlueFuzz", Integer.valueOf(1)));
/*  678 */     privDict.put("StdHW", privateDict.getNumber("StdHW", null));
/*  679 */     privDict.put("StdVW", privateDict.getNumber("StdVW", null));
/*  680 */     privDict.put("StemSnapH", privateDict.getDelta("StemSnapH", null));
/*  681 */     privDict.put("StemSnapV", privateDict.getDelta("StemSnapV", null));
/*  682 */     privDict.put("ForceBold", privateDict.getBoolean("ForceBold", false));
/*  683 */     privDict.put("LanguageGroup", privateDict.getNumber("LanguageGroup", Integer.valueOf(0)));
/*  684 */     privDict.put("ExpansionFactor", privateDict.getNumber("ExpansionFactor", Double.valueOf(0.06D)));
/*  685 */     privDict.put("initialRandomSeed", privateDict.getNumber("initialRandomSeed", Integer.valueOf(0)));
/*  686 */     privDict.put("defaultWidthX", privateDict.getNumber("defaultWidthX", Integer.valueOf(0)));
/*  687 */     privDict.put("nominalWidthX", privateDict.getNumber("nominalWidthX", Integer.valueOf(0)));
/*  688 */     return privDict;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void parseType1Dicts(CFFDataInput input, DictData topDict, CFFType1Font font, CFFCharset charset) throws IOException {
/*      */     CFFEncoding encoding;
/*  698 */     DictData.Entry encodingEntry = topDict.getEntry("Encoding");
/*      */     
/*  700 */     int encodingId = (encodingEntry != null) ? encodingEntry.getNumber(0).intValue() : 0;
/*  701 */     switch (encodingId) {
/*      */       
/*      */       case 0:
/*  704 */         encoding = CFFStandardEncoding.getInstance();
/*      */         break;
/*      */       case 1:
/*  707 */         encoding = CFFExpertEncoding.getInstance();
/*      */         break;
/*      */       default:
/*  710 */         input.setPosition(encodingId);
/*  711 */         encoding = readEncoding(input, charset);
/*      */         break;
/*      */     } 
/*  714 */     font.setEncoding(encoding);
/*      */ 
/*      */     
/*  717 */     DictData.Entry privateEntry = topDict.getEntry("Private");
/*  718 */     if (privateEntry == null)
/*      */     {
/*  720 */       throw new IOException("Private dictionary entry missing for font " + font.fontName);
/*      */     }
/*  722 */     int privateOffset = privateEntry.getNumber(1).intValue();
/*  723 */     input.setPosition(privateOffset);
/*  724 */     int privateSize = privateEntry.getNumber(0).intValue();
/*  725 */     DictData privateDict = readDictData(input, privateSize);
/*      */ 
/*      */     
/*  728 */     Map<String, Object> privDict = readPrivateDict(privateDict);
/*  729 */     for (Map.Entry<String, Object> entry : privDict.entrySet())
/*      */     {
/*  731 */       font.addToPrivateDict(entry.getKey(), entry.getValue());
/*      */     }
/*      */ 
/*      */     
/*  735 */     int localSubrOffset = ((Integer)privateDict.getNumber("Subrs", Integer.valueOf(0))).intValue();
/*  736 */     if (localSubrOffset > 0) {
/*      */       
/*  738 */       input.setPosition(privateOffset + localSubrOffset);
/*  739 */       font.addToPrivateDict("Subrs", readIndexData(input));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private String readString(int index) throws IOException {
/*  745 */     if (index >= 0 && index <= 390)
/*      */     {
/*  747 */       return CFFStandardString.getName(index);
/*      */     }
/*  749 */     if (index - 391 < this.stringIndex.length)
/*      */     {
/*  751 */       return this.stringIndex[index - 391];
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  756 */     return "SID" + index;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String getString(DictData dict, String name) throws IOException {
/*  762 */     DictData.Entry entry = dict.getEntry(name);
/*  763 */     return (entry != null) ? readString(entry.getNumber(0).intValue()) : null;
/*      */   }
/*      */ 
/*      */   
/*      */   private CFFEncoding readEncoding(CFFDataInput dataInput, CFFCharset charset) throws IOException {
/*  768 */     int format = dataInput.readCard8();
/*  769 */     int baseFormat = format & 0x7F;
/*      */     
/*  771 */     switch (baseFormat) {
/*      */       
/*      */       case 0:
/*  774 */         return readFormat0Encoding(dataInput, charset, format);
/*      */       case 1:
/*  776 */         return readFormat1Encoding(dataInput, charset, format);
/*      */     } 
/*  778 */     throw new IllegalArgumentException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Format0Encoding readFormat0Encoding(CFFDataInput dataInput, CFFCharset charset, int format) throws IOException {
/*  785 */     Format0Encoding encoding = new Format0Encoding();
/*  786 */     encoding.format = format;
/*  787 */     encoding.nCodes = dataInput.readCard8();
/*  788 */     encoding.add(0, 0, ".notdef");
/*  789 */     for (int gid = 1; gid <= encoding.nCodes; gid++) {
/*      */       
/*  791 */       int code = dataInput.readCard8();
/*  792 */       int sid = charset.getSIDForGID(gid);
/*  793 */       encoding.add(code, sid, readString(sid));
/*      */     } 
/*  795 */     if ((format & 0x80) != 0)
/*      */     {
/*  797 */       readSupplement(dataInput, encoding);
/*      */     }
/*  799 */     return encoding;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Format1Encoding readFormat1Encoding(CFFDataInput dataInput, CFFCharset charset, int format) throws IOException {
/*  805 */     Format1Encoding encoding = new Format1Encoding();
/*  806 */     encoding.format = format;
/*  807 */     encoding.nRanges = dataInput.readCard8();
/*  808 */     encoding.add(0, 0, ".notdef");
/*  809 */     int gid = 1;
/*  810 */     for (int i = 0; i < encoding.nRanges; i++) {
/*      */       
/*  812 */       int rangeFirst = dataInput.readCard8();
/*  813 */       int rangeLeft = dataInput.readCard8();
/*  814 */       for (int j = 0; j < 1 + rangeLeft; j++) {
/*      */         
/*  816 */         int sid = charset.getSIDForGID(gid);
/*  817 */         int code = rangeFirst + j;
/*  818 */         encoding.add(code, sid, readString(sid));
/*  819 */         gid++;
/*      */       } 
/*      */     } 
/*  822 */     if ((format & 0x80) != 0)
/*      */     {
/*  824 */       readSupplement(dataInput, encoding);
/*      */     }
/*  826 */     return encoding;
/*      */   }
/*      */ 
/*      */   
/*      */   private void readSupplement(CFFDataInput dataInput, CFFBuiltInEncoding encoding) throws IOException {
/*  831 */     encoding.nSups = dataInput.readCard8();
/*  832 */     encoding.supplement = new CFFBuiltInEncoding.Supplement[encoding.nSups];
/*  833 */     for (int i = 0; i < encoding.supplement.length; i++) {
/*      */       
/*  835 */       CFFBuiltInEncoding.Supplement supplement = new CFFBuiltInEncoding.Supplement();
/*  836 */       supplement.code = dataInput.readCard8();
/*  837 */       supplement.sid = dataInput.readSID();
/*  838 */       supplement.name = readString(supplement.sid);
/*  839 */       encoding.supplement[i] = supplement;
/*  840 */       encoding.add(supplement.code, supplement.sid, readString(supplement.sid));
/*      */     } 
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
/*      */   
/*      */   private static FDSelect readFDSelect(CFFDataInput dataInput, int nGlyphs, CFFCIDFont ros) throws IOException {
/*  854 */     int format = dataInput.readCard8();
/*  855 */     switch (format) {
/*      */       
/*      */       case 0:
/*  858 */         return readFormat0FDSelect(dataInput, format, nGlyphs, ros);
/*      */       case 3:
/*  860 */         return readFormat3FDSelect(dataInput, format, nGlyphs, ros);
/*      */     } 
/*  862 */     throw new IllegalArgumentException();
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Format0FDSelect readFormat0FDSelect(CFFDataInput dataInput, int format, int nGlyphs, CFFCIDFont ros) throws IOException {
/*  878 */     Format0FDSelect fdselect = new Format0FDSelect(ros);
/*  879 */     fdselect.format = format;
/*  880 */     fdselect.fds = new int[nGlyphs];
/*  881 */     for (int i = 0; i < fdselect.fds.length; i++)
/*      */     {
/*  883 */       fdselect.fds[i] = dataInput.readCard8();
/*      */     }
/*  885 */     return fdselect;
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Format3FDSelect readFormat3FDSelect(CFFDataInput dataInput, int format, int nGlyphs, CFFCIDFont ros) throws IOException {
/*  901 */     Format3FDSelect fdselect = new Format3FDSelect(ros);
/*  902 */     fdselect.format = format;
/*  903 */     fdselect.nbRanges = dataInput.readCard16();
/*      */     
/*  905 */     fdselect.range3 = new Range3[fdselect.nbRanges];
/*  906 */     for (int i = 0; i < fdselect.nbRanges; i++) {
/*      */       
/*  908 */       Range3 r3 = new Range3();
/*  909 */       r3.first = dataInput.readCard16();
/*  910 */       r3.fd = dataInput.readCard8();
/*  911 */       fdselect.range3[i] = r3;
/*      */     } 
/*      */ 
/*      */     
/*  915 */     fdselect.sentinel = dataInput.readCard16();
/*  916 */     return fdselect;
/*      */   }
/*      */ 
/*      */   
/*      */   private static final class Format3FDSelect
/*      */     extends FDSelect
/*      */   {
/*      */     private int format;
/*      */     
/*      */     private int nbRanges;
/*      */     
/*      */     private CFFParser.Range3[] range3;
/*      */     private int sentinel;
/*      */     
/*      */     private Format3FDSelect(CFFCIDFont owner) {
/*  931 */       super(owner);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int getFDIndex(int gid) {
/*  937 */       for (int i = 0; i < this.nbRanges; i++) {
/*      */         
/*  939 */         if ((this.range3[i]).first <= gid)
/*      */         {
/*  941 */           if (i + 1 < this.nbRanges) {
/*      */             
/*  943 */             if ((this.range3[i + 1]).first > gid)
/*      */             {
/*  945 */               return (this.range3[i]).fd;
/*      */             
/*      */             }
/*      */           
/*      */           }
/*      */           else {
/*      */             
/*  952 */             if (this.sentinel > gid)
/*      */             {
/*  954 */               return (this.range3[i]).fd;
/*      */             }
/*  956 */             return -1;
/*      */           } 
/*      */         }
/*      */       } 
/*  960 */       return 0;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/*  966 */       return getClass().getName() + "[format=" + this.format + " nbRanges=" + this.nbRanges + ", range3=" + 
/*  967 */         Arrays.toString((Object[])this.range3) + " sentinel=" + this.sentinel + "]";
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static final class Range3
/*      */   {
/*      */     private int first;
/*      */     
/*      */     private int fd;
/*      */ 
/*      */     
/*      */     private Range3() {}
/*      */     
/*      */     public String toString() {
/*  982 */       return getClass().getName() + "[first=" + this.first + ", fd=" + this.fd + "]";
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static class Format0FDSelect
/*      */     extends FDSelect
/*      */   {
/*      */     private int format;
/*      */     
/*      */     private int[] fds;
/*      */ 
/*      */     
/*      */     private Format0FDSelect(CFFCIDFont owner) {
/*  997 */       super(owner);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int getFDIndex(int gid) {
/* 1003 */       if (gid < this.fds.length)
/*      */       {
/* 1005 */         return this.fds[gid];
/*      */       }
/* 1007 */       return 0;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1013 */       return getClass().getName() + "[fds=" + Arrays.toString(this.fds) + "]";
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private CFFCharset readCharset(CFFDataInput dataInput, int nGlyphs, boolean isCIDFont) throws IOException {
/* 1020 */     int format = dataInput.readCard8();
/* 1021 */     switch (format) {
/*      */       
/*      */       case 0:
/* 1024 */         return readFormat0Charset(dataInput, format, nGlyphs, isCIDFont);
/*      */       case 1:
/* 1026 */         return readFormat1Charset(dataInput, format, nGlyphs, isCIDFont);
/*      */       case 2:
/* 1028 */         return readFormat2Charset(dataInput, format, nGlyphs, isCIDFont);
/*      */     } 
/* 1030 */     throw new IllegalArgumentException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Format0Charset readFormat0Charset(CFFDataInput dataInput, int format, int nGlyphs, boolean isCIDFont) throws IOException {
/* 1037 */     Format0Charset charset = new Format0Charset(isCIDFont);
/* 1038 */     charset.format = format;
/* 1039 */     if (isCIDFont) {
/*      */       
/* 1041 */       charset.addCID(0, 0);
/*      */     }
/*      */     else {
/*      */       
/* 1045 */       charset.addSID(0, 0, ".notdef");
/*      */     } 
/*      */     
/* 1048 */     for (int gid = 1; gid < nGlyphs; gid++) {
/*      */       
/* 1050 */       int sid = dataInput.readSID();
/* 1051 */       if (isCIDFont) {
/*      */         
/* 1053 */         charset.addCID(gid, sid);
/*      */       }
/*      */       else {
/*      */         
/* 1057 */         charset.addSID(gid, sid, readString(sid));
/*      */       } 
/*      */     } 
/* 1060 */     return charset;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Format1Charset readFormat1Charset(CFFDataInput dataInput, int format, int nGlyphs, boolean isCIDFont) throws IOException {
/* 1066 */     Format1Charset charset = new Format1Charset(isCIDFont);
/* 1067 */     charset.format = format;
/* 1068 */     if (isCIDFont) {
/*      */       
/* 1070 */       charset.addCID(0, 0);
/* 1071 */       charset.rangesCID2GID = new ArrayList();
/*      */     }
/*      */     else {
/*      */       
/* 1075 */       charset.addSID(0, 0, ".notdef");
/*      */     } 
/*      */     
/* 1078 */     for (int gid = 1; gid < nGlyphs; gid++) {
/*      */       
/* 1080 */       int rangeFirst = dataInput.readSID();
/* 1081 */       int rangeLeft = dataInput.readCard8();
/* 1082 */       if (!isCIDFont) {
/*      */         
/* 1084 */         for (int j = 0; j < 1 + rangeLeft; j++)
/*      */         {
/* 1086 */           int sid = rangeFirst + j;
/* 1087 */           charset.addSID(gid + j, sid, readString(sid));
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1092 */         charset.rangesCID2GID.add(new RangeMapping(gid, rangeFirst, rangeLeft));
/*      */       } 
/* 1094 */       gid += rangeLeft;
/*      */     } 
/* 1096 */     return charset;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Format2Charset readFormat2Charset(CFFDataInput dataInput, int format, int nGlyphs, boolean isCIDFont) throws IOException {
/* 1102 */     Format2Charset charset = new Format2Charset(isCIDFont);
/* 1103 */     charset.format = format;
/* 1104 */     if (isCIDFont) {
/*      */       
/* 1106 */       charset.addCID(0, 0);
/* 1107 */       charset.rangesCID2GID = new ArrayList();
/*      */     }
/*      */     else {
/*      */       
/* 1111 */       charset.addSID(0, 0, ".notdef");
/*      */     } 
/*      */     
/* 1114 */     for (int gid = 1; gid < nGlyphs; gid++) {
/*      */       
/* 1116 */       int first = dataInput.readSID();
/* 1117 */       int nLeft = dataInput.readCard16();
/* 1118 */       if (!isCIDFont) {
/*      */         
/* 1120 */         for (int j = 0; j < 1 + nLeft; j++)
/*      */         {
/* 1122 */           int sid = first + j;
/* 1123 */           charset.addSID(gid + j, sid, readString(sid));
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1128 */         charset.rangesCID2GID.add(new RangeMapping(gid, first, nLeft));
/*      */       } 
/* 1130 */       gid += nLeft;
/*      */     } 
/* 1132 */     return charset;
/*      */   }
/*      */ 
/*      */   
/*      */   private static class Header
/*      */   {
/*      */     private int major;
/*      */     
/*      */     private int minor;
/*      */     
/*      */     private int hdrSize;
/*      */     private int offSize;
/*      */     
/*      */     private Header() {}
/*      */     
/*      */     public String toString() {
/* 1148 */       return getClass().getName() + "[major=" + this.major + ", minor=" + this.minor + ", hdrSize=" + this.hdrSize + ", offSize=" + this.offSize + "]";
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class DictData
/*      */   {
/* 1158 */     private final Map<String, Entry> entries = new HashMap<String, Entry>();
/*      */ 
/*      */     
/*      */     public void add(Entry entry) {
/* 1162 */       if (entry.operator != null)
/*      */       {
/* 1164 */         this.entries.put(entry.operator.getName(), entry);
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public Entry getEntry(String name) {
/* 1170 */       return this.entries.get(name);
/*      */     }
/*      */ 
/*      */     
/*      */     public Boolean getBoolean(String name, boolean defaultValue) {
/* 1175 */       Entry entry = getEntry(name);
/* 1176 */       return Boolean.valueOf((entry != null && !entry.getArray().isEmpty()) ? entry.getBoolean(0).booleanValue() : defaultValue);
/*      */     }
/*      */ 
/*      */     
/*      */     public List<Number> getArray(String name, List<Number> defaultValue) {
/* 1181 */       Entry entry = getEntry(name);
/* 1182 */       return (entry != null && !entry.getArray().isEmpty()) ? entry.getArray() : defaultValue;
/*      */     }
/*      */ 
/*      */     
/*      */     public Number getNumber(String name, Number defaultValue) {
/* 1187 */       Entry entry = getEntry(name);
/* 1188 */       return (entry != null && !entry.getArray().isEmpty()) ? entry.getNumber(0) : defaultValue;
/*      */     }
/*      */ 
/*      */     
/*      */     public List<Number> getDelta(String name, List<Number> defaultValue) {
/* 1193 */       Entry entry = getEntry(name);
/* 1194 */       return (entry != null && !entry.getArray().isEmpty()) ? entry.getDelta() : defaultValue;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1203 */       return getClass().getName() + "[entries=" + this.entries + "]";
/*      */     }
/*      */ 
/*      */     
/*      */     private DictData() {}
/*      */     
/*      */     private static class Entry
/*      */     {
/* 1211 */       private List<Number> operands = new ArrayList<Number>();
/* 1212 */       private CFFOperator operator = null;
/*      */ 
/*      */       
/*      */       public Number getNumber(int index) {
/* 1216 */         return this.operands.get(index);
/*      */       }
/*      */ 
/*      */       
/*      */       public Boolean getBoolean(int index) {
/* 1221 */         Number operand = this.operands.get(index);
/* 1222 */         if (operand instanceof Integer)
/*      */         {
/* 1224 */           switch (operand.intValue()) {
/*      */             
/*      */             case 0:
/* 1227 */               return Boolean.FALSE;
/*      */             case 1:
/* 1229 */               return Boolean.TRUE;
/*      */           } 
/*      */ 
/*      */         
/*      */         }
/* 1234 */         throw new IllegalArgumentException();
/*      */       }
/*      */ 
/*      */       
/*      */       public List<Number> getArray() {
/* 1239 */         return this.operands;
/*      */       }
/*      */ 
/*      */       
/*      */       public List<Number> getDelta() {
/* 1244 */         List<Number> result = new ArrayList<Number>(this.operands);
/* 1245 */         for (int i = 1; i < result.size(); i++) {
/*      */           
/* 1247 */           Number previous = result.get(i - 1);
/* 1248 */           Number current = result.get(i);
/* 1249 */           Integer sum = Integer.valueOf(previous.intValue() + current.intValue());
/* 1250 */           result.set(i, sum);
/*      */         } 
/* 1252 */         return result;
/*      */       }
/*      */       
/*      */       private Entry() {}
/*      */       
/*      */       public String toString() {
/* 1258 */         return getClass().getName() + "[operands=" + this.operands + ", operator=" + this.operator + "]"; } } } private static class Entry { private List<Number> operands = new ArrayList<Number>(); private CFFOperator operator = null; public Number getNumber(int index) { return this.operands.get(index); } public String toString() { return getClass().getName() + "[operands=" + this.operands + ", operator=" + this.operator + "]"; }
/*      */      public Boolean getBoolean(int index) {
/*      */       Number operand = this.operands.get(index);
/*      */       if (operand instanceof Integer)
/*      */         switch (operand.intValue()) {
/*      */           case 0:
/*      */             return Boolean.FALSE;
/*      */           case 1:
/*      */             return Boolean.TRUE;
/*      */         }  
/*      */       throw new IllegalArgumentException();
/*      */     } public List<Number> getArray() {
/*      */       return this.operands;
/*      */     } public List<Number> getDelta() {
/*      */       List<Number> result = new ArrayList<Number>(this.operands);
/*      */       for (int i = 1; i < result.size(); i++) {
/*      */         Number previous = result.get(i - 1);
/*      */         Number current = result.get(i);
/*      */         Integer sum = Integer.valueOf(previous.intValue() + current.intValue());
/*      */         result.set(i, sum);
/*      */       } 
/*      */       return result;
/*      */     } private Entry() {} } static abstract class CFFBuiltInEncoding extends CFFEncoding { private int nSups; private Supplement[] supplement; static class Supplement { private int code;
/*      */       public int getCode() {
/* 1282 */         return this.code;
/*      */       }
/*      */       private int sid; private String name;
/*      */       
/*      */       public int getSID() {
/* 1287 */         return this.sid;
/*      */       }
/*      */ 
/*      */       
/*      */       public String getName() {
/* 1292 */         return this.name;
/*      */       }
/*      */ 
/*      */       
/*      */       public String toString()
/*      */       {
/* 1298 */         return getClass().getName() + "[code=" + this.code + ", sid=" + this.sid + "]"; } } } static class Supplement { private int code; private int sid; private String name; public String toString() { return getClass().getName() + "[code=" + this.code + ", sid=" + this.sid + "]"; }
/*      */     
/*      */     public int getCode() {
/*      */       return this.code;
/*      */     }
/*      */     public int getSID() {
/*      */       return this.sid;
/*      */     }
/*      */     public String getName() {
/*      */       return this.name;
/*      */     } }
/*      */   private static class Format0Encoding extends CFFBuiltInEncoding { private int format; private int nCodes;
/*      */     
/*      */     private Format0Encoding() {}
/*      */     
/*      */     public String toString() {
/* 1314 */       return getClass().getName() + "[format=" + this.format + ", nCodes=" + this.nCodes + ", supplement=" + 
/* 1315 */         Arrays.toString((Object[])this.supplement) + "]";
/*      */     } }
/*      */ 
/*      */ 
/*      */   
/*      */   private static class Format1Encoding
/*      */     extends CFFBuiltInEncoding
/*      */   {
/*      */     private int format;
/*      */     
/*      */     private int nRanges;
/*      */     
/*      */     private Format1Encoding() {}
/*      */     
/*      */     public String toString() {
/* 1330 */       return getClass().getName() + "[format=" + this.format + ", nRanges=" + this.nRanges + ", supplement=" + 
/* 1331 */         Arrays.toString((Object[])this.supplement) + "]";
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static abstract class EmbeddedCharset
/*      */     extends CFFCharset
/*      */   {
/*      */     protected EmbeddedCharset(boolean isCIDFont) {
/* 1342 */       super(isCIDFont);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class EmptyCharset
/*      */     extends EmbeddedCharset
/*      */   {
/*      */     protected EmptyCharset(int numCharStrings) {
/* 1353 */       super(true);
/* 1354 */       addCID(0, 0);
/*      */ 
/*      */       
/* 1357 */       for (int i = 1; i <= numCharStrings; i++)
/*      */       {
/* 1359 */         addCID(i, i);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1366 */       return getClass().getName();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static class Format0Charset
/*      */     extends EmbeddedCharset
/*      */   {
/*      */     private int format;
/*      */ 
/*      */     
/*      */     protected Format0Charset(boolean isCIDFont) {
/* 1379 */       super(isCIDFont);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1385 */       return getClass().getName() + "[format=" + this.format + "]";
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static class Format1Charset
/*      */     extends EmbeddedCharset
/*      */   {
/*      */     private int format;
/*      */     
/*      */     private List<CFFParser.RangeMapping> rangesCID2GID;
/*      */ 
/*      */     
/*      */     protected Format1Charset(boolean isCIDFont) {
/* 1399 */       super(isCIDFont);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int getCIDForGID(int gid) {
/* 1405 */       if (isCIDFont())
/*      */       {
/* 1407 */         for (CFFParser.RangeMapping mapping : this.rangesCID2GID) {
/*      */           
/* 1409 */           if (mapping.isInRange(gid))
/*      */           {
/* 1411 */             return mapping.mapValue(gid);
/*      */           }
/*      */         } 
/*      */       }
/* 1415 */       return super.getCIDForGID(gid);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int getGIDForCID(int cid) {
/* 1421 */       if (isCIDFont())
/*      */       {
/* 1423 */         for (CFFParser.RangeMapping mapping : this.rangesCID2GID) {
/*      */           
/* 1425 */           if (mapping.isInReverseRange(cid))
/*      */           {
/* 1427 */             return mapping.mapReverseValue(cid);
/*      */           }
/*      */         } 
/*      */       }
/* 1431 */       return super.getGIDForCID(cid);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1437 */       return getClass().getName() + "[format=" + this.format + "]";
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static class Format2Charset
/*      */     extends EmbeddedCharset
/*      */   {
/*      */     private int format;
/*      */     
/*      */     private List<CFFParser.RangeMapping> rangesCID2GID;
/*      */ 
/*      */     
/*      */     protected Format2Charset(boolean isCIDFont) {
/* 1451 */       super(isCIDFont);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int getCIDForGID(int gid) {
/* 1457 */       for (CFFParser.RangeMapping mapping : this.rangesCID2GID) {
/*      */         
/* 1459 */         if (mapping.isInRange(gid))
/*      */         {
/* 1461 */           return mapping.mapValue(gid);
/*      */         }
/*      */       } 
/* 1464 */       return super.getCIDForGID(gid);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int getGIDForCID(int cid) {
/* 1470 */       for (CFFParser.RangeMapping mapping : this.rangesCID2GID) {
/*      */         
/* 1472 */         if (mapping.isInReverseRange(cid))
/*      */         {
/* 1474 */           return mapping.mapReverseValue(cid);
/*      */         }
/*      */       } 
/* 1477 */       return super.getGIDForCID(cid);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1483 */       return getClass().getName() + "[format=" + this.format + "]";
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static final class RangeMapping
/*      */   {
/*      */     private final int startValue;
/*      */     
/*      */     private final int endValue;
/*      */     
/*      */     private final int startMappedValue;
/*      */     
/*      */     private final int endMappedValue;
/*      */ 
/*      */     
/*      */     private RangeMapping(int startGID, int first, int nLeft) {
/* 1500 */       this.startValue = startGID;
/* 1501 */       this.endValue = this.startValue + nLeft;
/* 1502 */       this.startMappedValue = first;
/* 1503 */       this.endMappedValue = this.startMappedValue + nLeft;
/*      */     }
/*      */ 
/*      */     
/*      */     boolean isInRange(int value) {
/* 1508 */       return (value >= this.startValue && value <= this.endValue);
/*      */     }
/*      */ 
/*      */     
/*      */     boolean isInReverseRange(int value) {
/* 1513 */       return (value >= this.startMappedValue && value <= this.endMappedValue);
/*      */     }
/*      */ 
/*      */     
/*      */     int mapValue(int value) {
/* 1518 */       if (isInRange(value))
/*      */       {
/* 1520 */         return this.startMappedValue + value - this.startValue;
/*      */       }
/*      */ 
/*      */       
/* 1524 */       return 0;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     int mapReverseValue(int value) {
/* 1530 */       if (isInReverseRange(value))
/*      */       {
/* 1532 */         return this.startValue + value - this.startMappedValue;
/*      */       }
/*      */ 
/*      */       
/* 1536 */       return 0;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1543 */       return getClass().getName() + "[start value=" + this.startValue + ", end value=" + this.endValue + ", start mapped-value=" + this.startMappedValue + ", end mapped-value=" + this.endMappedValue + "]";
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1550 */     return getClass().getSimpleName() + "[" + this.debugFontName + "]";
/*      */   }
/*      */   
/*      */   public static interface ByteSource {
/*      */     byte[] getBytes() throws IOException;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cff/CFFParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */