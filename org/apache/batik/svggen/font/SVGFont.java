/*     */ package org.apache.batik.svggen.font;
/*     */ 
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.batik.svggen.font.table.CmapFormat;
/*     */ import org.apache.batik.svggen.font.table.Feature;
/*     */ import org.apache.batik.svggen.font.table.FeatureTags;
/*     */ import org.apache.batik.svggen.font.table.GsubTable;
/*     */ import org.apache.batik.svggen.font.table.KernSubtable;
/*     */ import org.apache.batik.svggen.font.table.KernTable;
/*     */ import org.apache.batik.svggen.font.table.KerningPair;
/*     */ import org.apache.batik.svggen.font.table.LangSys;
/*     */ import org.apache.batik.svggen.font.table.PostTable;
/*     */ import org.apache.batik.svggen.font.table.Script;
/*     */ import org.apache.batik.svggen.font.table.ScriptTags;
/*     */ import org.apache.batik.svggen.font.table.SingleSubst;
/*     */ import org.apache.batik.util.SVGConstants;
/*     */ import org.apache.batik.util.XMLConstants;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGFont
/*     */   implements FeatureTags, ScriptTags, SVGConstants, XMLConstants
/*     */ {
/*     */   static final String EOL;
/*     */   static final String PROPERTY_LINE_SEPARATOR = "line.separator";
/*     */   static final String PROPERTY_LINE_SEPARATOR_DEFAULT = "\n";
/*     */   static final int DEFAULT_FIRST = 32;
/*     */   static final int DEFAULT_LAST = 126;
/*     */   
/*     */   static {
/*     */     String str;
/*     */     try {
/*  61 */       str = System.getProperty("line.separator", "\n");
/*     */     }
/*  63 */     catch (SecurityException e) {
/*  64 */       str = "\n";
/*     */     } 
/*  66 */     EOL = str;
/*     */   }
/*     */   
/*  69 */   private static String QUOT_EOL = '"' + EOL;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   private static String CONFIG_USAGE = "SVGFont.config.usage";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   private static String CONFIG_SVG_BEGIN = "SVGFont.config.svg.begin";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   private static String CONFIG_SVG_TEST_CARD_START = "SVGFont.config.svg.test.card.start";
/*     */ 
/*     */   
/*     */   public static final char ARG_KEY_START_CHAR = '-';
/*     */   
/*     */   public static final String ARG_KEY_CHAR_RANGE_LOW = "-l";
/*     */   
/*  96 */   private static String CONFIG_SVG_TEST_CARD_END = "SVGFont.config.svg.test.card.end"; public static final String ARG_KEY_CHAR_RANGE_HIGH = "-h";
/*     */   public static final String ARG_KEY_ID = "-id";
/*     */   
/*     */   protected static String encodeEntities(String s) {
/* 100 */     StringBuffer sb = new StringBuffer();
/* 101 */     for (int i = 0; i < s.length(); i++) {
/* 102 */       if (s.charAt(i) == '<') {
/* 103 */         sb.append("&lt;");
/* 104 */       } else if (s.charAt(i) == '>') {
/* 105 */         sb.append("&gt;");
/* 106 */       } else if (s.charAt(i) == '&') {
/* 107 */         sb.append("&amp;");
/* 108 */       } else if (s.charAt(i) == '\'') {
/* 109 */         sb.append("&apos;");
/* 110 */       } else if (s.charAt(i) == '"') {
/* 111 */         sb.append("&quot;");
/*     */       } else {
/* 113 */         sb.append(s.charAt(i));
/*     */       } 
/*     */     } 
/* 116 */     return sb.toString();
/*     */   }
/*     */   public static final String ARG_KEY_ASCII = "-ascii";
/*     */   public static final String ARG_KEY_TESTCARD = "-testcard";
/*     */   
/*     */   protected static String getContourAsSVGPathData(Glyph glyph, int startIndex, int count) {
/* 122 */     if ((glyph.getPoint(startIndex)).endOfContour) {
/* 123 */       return "";
/*     */     }
/*     */     
/* 126 */     StringBuffer sb = new StringBuffer();
/* 127 */     int offset = 0;
/*     */     
/* 129 */     while (offset < count) {
/* 130 */       Point point = glyph.getPoint(startIndex + offset % count);
/* 131 */       Point point_plus1 = glyph.getPoint(startIndex + (offset + 1) % count);
/* 132 */       Point point_plus2 = glyph.getPoint(startIndex + (offset + 2) % count);
/*     */       
/* 134 */       if (offset == 0) {
/* 135 */         sb.append("M").append(String.valueOf(point.x)).append(" ").append(String.valueOf(point.y));
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 141 */       if (point.onCurve && point_plus1.onCurve) {
/* 142 */         if (point_plus1.x == point.x) {
/* 143 */           sb.append("V").append(String.valueOf(point_plus1.y));
/*     */         }
/* 145 */         else if (point_plus1.y == point.y) {
/* 146 */           sb.append("H").append(String.valueOf(point_plus1.x));
/*     */         } else {
/*     */           
/* 149 */           sb.append("L").append(String.valueOf(point_plus1.x)).append(" ").append(String.valueOf(point_plus1.y));
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 154 */         offset++; continue;
/* 155 */       }  if (point.onCurve && !point_plus1.onCurve && point_plus2.onCurve) {
/*     */         
/* 157 */         sb.append("Q").append(String.valueOf(point_plus1.x)).append(" ").append(String.valueOf(point_plus1.y)).append(" ").append(String.valueOf(point_plus2.x)).append(" ").append(String.valueOf(point_plus2.y));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 165 */         offset += 2; continue;
/* 166 */       }  if (point.onCurve && !point_plus1.onCurve && !point_plus2.onCurve) {
/*     */         
/* 168 */         sb.append("Q").append(String.valueOf(point_plus1.x)).append(" ").append(String.valueOf(point_plus1.y)).append(" ").append(String.valueOf(midValue(point_plus1.x, point_plus2.x))).append(" ").append(String.valueOf(midValue(point_plus1.y, point_plus2.y)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 176 */         offset += 2; continue;
/* 177 */       }  if (!point.onCurve && !point_plus1.onCurve) {
/*     */         
/* 179 */         sb.append("T").append(String.valueOf(midValue(point.x, point_plus1.x))).append(" ").append(String.valueOf(midValue(point.y, point_plus1.y)));
/*     */ 
/*     */ 
/*     */         
/* 183 */         offset++; continue;
/* 184 */       }  if (!point.onCurve && point_plus1.onCurve) {
/* 185 */         sb.append("T").append(String.valueOf(point_plus1.x)).append(" ").append(String.valueOf(point_plus1.y));
/*     */ 
/*     */ 
/*     */         
/* 189 */         offset++; continue;
/*     */       } 
/* 191 */       System.out.println("drawGlyph case not catered for!!");
/*     */     } 
/*     */ 
/*     */     
/* 195 */     sb.append("Z");
/*     */     
/* 197 */     return sb.toString();
/*     */   }
/*     */   public static final String ARG_KEY_AUTO_RANGE = "-autorange"; public static final String ARG_KEY_OUTPUT_PATH = "-o";
/*     */   protected static String getSVGFontFaceElement(Font font) {
/* 201 */     StringBuffer sb = new StringBuffer();
/* 202 */     String fontFamily = font.getNameTable().getRecord((short)1);
/* 203 */     short unitsPerEm = font.getHeadTable().getUnitsPerEm();
/* 204 */     String panose = font.getOS2Table().getPanose().toString();
/* 205 */     short ascent = font.getHheaTable().getAscender();
/* 206 */     short descent = font.getHheaTable().getDescender();
/* 207 */     int baseline = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 246 */     sb.append("<").append("font-face").append(EOL).append("    ").append("font-family").append("=\"").append(fontFamily).append(QUOT_EOL).append("    ").append("units-per-em").append("=\"").append(unitsPerEm).append(QUOT_EOL).append("    ").append("panose-1").append("=\"").append(panose).append(QUOT_EOL).append("    ").append("ascent").append("=\"").append(ascent).append(QUOT_EOL).append("    ").append("descent").append("=\"").append(descent).append(QUOT_EOL).append("    ").append("alphabetic").append("=\"").append(baseline).append('"').append(" />").append(EOL);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 261 */     return sb.toString();
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
/*     */   protected static void writeFontAsSVGFragment(PrintStream ps, Font font, String id, int first, int last, boolean autoRange, boolean forceAscii) throws Exception {
/* 279 */     int horiz_advance_x = font.getOS2Table().getAvgCharWidth();
/*     */     
/* 281 */     ps.print("<");
/* 282 */     ps.print("font");
/* 283 */     ps.print(" ");
/*     */     
/* 285 */     if (id != null) {
/* 286 */       ps.print("id");
/* 287 */       ps.print("=\"");
/*     */       
/* 289 */       ps.print(id);
/* 290 */       ps.print('"');
/* 291 */       ps.print(" ");
/*     */     } 
/*     */ 
/*     */     
/* 295 */     ps.print("horiz-adv-x");
/* 296 */     ps.print("=\"");
/*     */     
/* 298 */     ps.print(horiz_advance_x);
/* 299 */     ps.print('"');
/* 300 */     ps.print(" >");
/*     */ 
/*     */     
/* 303 */     ps.print(getSVGFontFaceElement(font));
/*     */ 
/*     */     
/* 306 */     CmapFormat cmapFmt = null;
/* 307 */     if (forceAscii) {
/*     */       
/* 309 */       cmapFmt = font.getCmapTable().getCmapFormat((short)1, (short)0);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 314 */       cmapFmt = font.getCmapTable().getCmapFormat((short)3, (short)1);
/*     */ 
/*     */       
/* 317 */       if (cmapFmt == null)
/*     */       {
/* 319 */         cmapFmt = font.getCmapTable().getCmapFormat((short)3, (short)0);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 324 */     if (cmapFmt == null) {
/* 325 */       throw new Exception("Cannot find a suitable cmap table");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 331 */     GsubTable gsub = (GsubTable)font.getTable(1196643650);
/* 332 */     SingleSubst initialSubst = null;
/* 333 */     SingleSubst medialSubst = null;
/* 334 */     SingleSubst terminalSubst = null;
/* 335 */     if (gsub != null) {
/* 336 */       Script s = gsub.getScriptList().findScript("arab");
/* 337 */       if (s != null) {
/* 338 */         LangSys ls = s.getDefaultLangSys();
/* 339 */         if (ls != null) {
/* 340 */           Feature init = gsub.getFeatureList().findFeature(ls, "init");
/* 341 */           Feature medi = gsub.getFeatureList().findFeature(ls, "medi");
/* 342 */           Feature fina = gsub.getFeatureList().findFeature(ls, "fina");
/*     */           
/* 344 */           if (init != null) {
/* 345 */             initialSubst = (SingleSubst)gsub.getLookupList().getLookup(init, 0).getSubtable(0);
/*     */           }
/*     */           
/* 348 */           if (medi != null) {
/* 349 */             medialSubst = (SingleSubst)gsub.getLookupList().getLookup(medi, 0).getSubtable(0);
/*     */           }
/*     */           
/* 352 */           if (fina != null) {
/* 353 */             terminalSubst = (SingleSubst)gsub.getLookupList().getLookup(fina, 0).getSubtable(0);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 361 */     ps.println(getGlyphAsSVG(font, font.getGlyph(0), 0, horiz_advance_x, initialSubst, medialSubst, terminalSubst, ""));
/*     */ 
/*     */     
/*     */     try {
/* 365 */       if (first == -1)
/* 366 */         if (!autoRange) { first = 32; }
/* 367 */         else { first = cmapFmt.getFirst(); }
/*     */          
/* 369 */       if (last == -1) {
/* 370 */         if (!autoRange) { last = 126; }
/* 371 */         else { last = cmapFmt.getLast(); }
/*     */       
/*     */       }
/*     */       
/* 375 */       Set<Integer> glyphSet = new HashSet();
/* 376 */       for (int i = first; i <= last; i++) {
/* 377 */         int glyphIndex = cmapFmt.mapCharCode(i);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 382 */         if (glyphIndex > 0) {
/*     */           
/* 384 */           glyphSet.add(Integer.valueOf(glyphIndex));
/*     */           
/* 386 */           ps.println(getGlyphAsSVG(font, font.getGlyph(glyphIndex), glyphIndex, horiz_advance_x, initialSubst, medialSubst, terminalSubst, (32 <= i && i <= 127) ? encodeEntities(String.valueOf((char)i)) : ("&#x" + Integer.toHexString(i) + ";")));
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 400 */       KernTable kern = (KernTable)font.getTable(1801810542);
/* 401 */       if (kern != null) {
/* 402 */         KernSubtable kst = kern.getSubtable(0);
/* 403 */         PostTable post = (PostTable)font.getTable(1886352244);
/* 404 */         for (int j = 0; j < kst.getKerningPairCount(); j++) {
/* 405 */           KerningPair kpair = kst.getKerningPair(j);
/*     */           
/* 407 */           if (glyphSet.contains(Integer.valueOf(kpair.getLeft())) && glyphSet.contains(Integer.valueOf(kpair.getRight()))) {
/* 408 */             ps.println(getKerningPairAsSVG(kpair, post));
/*     */           }
/*     */         } 
/*     */       } 
/* 412 */     } catch (Exception e) {
/* 413 */       System.err.println(e.getMessage());
/*     */     } 
/*     */     
/* 416 */     ps.print("</");
/* 417 */     ps.print("font");
/* 418 */     ps.println(">");
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
/*     */   protected static String getGlyphAsSVG(Font font, Glyph glyph, int glyphIndex, int defaultHorizAdvanceX, String attrib, String code) {
/* 430 */     StringBuffer sb = new StringBuffer();
/* 431 */     int firstIndex = 0;
/* 432 */     int count = 0;
/*     */ 
/*     */ 
/*     */     
/* 436 */     int horiz_advance_x = font.getHmtxTable().getAdvanceWidth(glyphIndex);
/*     */     
/* 438 */     if (glyphIndex == 0) {
/* 439 */       sb.append("<");
/* 440 */       sb.append("missing-glyph");
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 445 */       sb.append("<").append("glyph").append(" ").append("unicode").append("=\"").append(code).append('"');
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 451 */       String glyphName = font.getPostTable().getGlyphName(glyphIndex);
/* 452 */       if (glyphName != null) {
/* 453 */         sb.append(" ").append("glyph-name").append("=\"").append(glyphName).append('"');
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 460 */     if (horiz_advance_x != defaultHorizAdvanceX) {
/* 461 */       sb.append(" ").append("horiz-adv-x").append("=\"").append(horiz_advance_x).append('"');
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 466 */     if (attrib != null) {
/* 467 */       sb.append(attrib);
/*     */     }
/*     */     
/* 470 */     if (glyph != null) {
/*     */       
/* 472 */       sb.append(" ").append("d").append("=\"");
/* 473 */       for (int i = 0; i < glyph.getPointCount(); i++) {
/* 474 */         count++;
/* 475 */         if ((glyph.getPoint(i)).endOfContour) {
/* 476 */           sb.append(getContourAsSVGPathData(glyph, firstIndex, count));
/* 477 */           firstIndex = i + 1;
/* 478 */           count = 0;
/*     */         } 
/*     */       } 
/*     */       
/* 482 */       sb.append('"');
/*     */     } 
/*     */     
/* 485 */     sb.append(" />");
/*     */ 
/*     */ 
/*     */     
/* 489 */     chopUpStringBuffer(sb);
/*     */     
/* 491 */     return sb.toString();
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
/*     */   protected static String getGlyphAsSVG(Font font, Glyph glyph, int glyphIndex, int defaultHorizAdvanceX, SingleSubst arabInitSubst, SingleSubst arabMediSubst, SingleSubst arabTermSubst, String code) {
/* 504 */     StringBuffer sb = new StringBuffer();
/* 505 */     boolean substituted = false;
/*     */ 
/*     */     
/* 508 */     int arabInitGlyphIndex = glyphIndex;
/* 509 */     int arabMediGlyphIndex = glyphIndex;
/* 510 */     int arabTermGlyphIndex = glyphIndex;
/* 511 */     if (arabInitSubst != null) {
/* 512 */       arabInitGlyphIndex = arabInitSubst.substitute(glyphIndex);
/*     */     }
/* 514 */     if (arabMediSubst != null) {
/* 515 */       arabMediGlyphIndex = arabMediSubst.substitute(glyphIndex);
/*     */     }
/* 517 */     if (arabTermSubst != null) {
/* 518 */       arabTermGlyphIndex = arabTermSubst.substitute(glyphIndex);
/*     */     }
/*     */     
/* 521 */     if (arabInitGlyphIndex != glyphIndex) {
/* 522 */       sb.append(getGlyphAsSVG(font, font.getGlyph(arabInitGlyphIndex), arabInitGlyphIndex, defaultHorizAdvanceX, " arabic-form=\"initial\"", code));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 532 */       sb.append(EOL);
/* 533 */       substituted = true;
/*     */     } 
/*     */     
/* 536 */     if (arabMediGlyphIndex != glyphIndex) {
/* 537 */       sb.append(getGlyphAsSVG(font, font.getGlyph(arabMediGlyphIndex), arabMediGlyphIndex, defaultHorizAdvanceX, " arabic-form=\"medial\"", code));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 547 */       sb.append(EOL);
/* 548 */       substituted = true;
/*     */     } 
/*     */     
/* 551 */     if (arabTermGlyphIndex != glyphIndex) {
/* 552 */       sb.append(getGlyphAsSVG(font, font.getGlyph(arabTermGlyphIndex), arabTermGlyphIndex, defaultHorizAdvanceX, " arabic-form=\"terminal\"", code));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 562 */       sb.append(EOL);
/* 563 */       substituted = true;
/*     */     } 
/*     */     
/* 566 */     if (substituted) {
/* 567 */       sb.append(getGlyphAsSVG(font, glyph, glyphIndex, defaultHorizAdvanceX, " arabic-form=\"isolated\"", code));
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 577 */       sb.append(getGlyphAsSVG(font, glyph, glyphIndex, defaultHorizAdvanceX, null, code));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 586 */     return sb.toString();
/*     */   }
/*     */   
/*     */   protected static String getKerningPairAsSVG(KerningPair kp, PostTable post) {
/* 590 */     String leftGlyphName = post.getGlyphName(kp.getLeft());
/* 591 */     String rightGlyphName = post.getGlyphName(kp.getRight());
/*     */     
/* 593 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 595 */     sb.append("<").append("hkern").append(" ");
/*     */     
/* 597 */     if (leftGlyphName == null) {
/* 598 */       sb.append("u1").append("=\"");
/*     */       
/* 600 */       sb.append(kp.getLeft());
/*     */     } else {
/*     */       
/* 603 */       sb.append("g1").append("=\"");
/*     */       
/* 605 */       sb.append(leftGlyphName);
/*     */     } 
/*     */ 
/*     */     
/* 609 */     sb.append('"').append(" ");
/*     */     
/* 611 */     if (rightGlyphName == null) {
/*     */       
/* 613 */       sb.append("u2").append("=\"");
/*     */       
/* 615 */       sb.append(kp.getRight());
/*     */     } else {
/*     */       
/* 618 */       sb.append("g2").append("=\"");
/*     */       
/* 620 */       sb.append(rightGlyphName);
/*     */     } 
/*     */ 
/*     */     
/* 624 */     sb.append('"').append(" ").append("k").append("=\"");
/*     */ 
/*     */     
/* 627 */     sb.append(-kp.getValue());
/*     */     
/* 629 */     sb.append('"').append(" />");
/*     */     
/* 631 */     return sb.toString();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void writeSvgBegin(PrintStream ps) {
/* 669 */     ps.println(Messages.formatMessage(CONFIG_SVG_BEGIN, new Object[] { "-//W3C//DTD SVG 1.0//EN", "http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd" }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void writeSvgDefsBegin(PrintStream ps) {
/* 676 */     ps.println("<defs >");
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void writeSvgDefsEnd(PrintStream ps) {
/* 681 */     ps.println("</defs>");
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void writeSvgEnd(PrintStream ps) {
/* 686 */     ps.println("</svg>");
/*     */   }
/*     */   
/*     */   protected static void writeSvgTestCard(PrintStream ps, String fontFamily) {
/* 690 */     ps.println(Messages.formatMessage(CONFIG_SVG_TEST_CARD_START, null));
/* 691 */     ps.println(fontFamily);
/* 692 */     ps.println(Messages.formatMessage(CONFIG_SVG_TEST_CARD_END, null));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/* 728 */       String path = parseArgs(args, null);
/* 729 */       String low = parseArgs(args, "-l");
/* 730 */       String high = parseArgs(args, "-h");
/* 731 */       String id = parseArgs(args, "-id");
/* 732 */       String ascii = parseArgs(args, "-ascii");
/* 733 */       String testCard = parseArgs(args, "-testcard");
/* 734 */       String outPath = parseArgs(args, "-o");
/* 735 */       String autoRange = parseArgs(args, "-autorange");
/* 736 */       PrintStream ps = null;
/* 737 */       FileOutputStream fos = null;
/*     */ 
/*     */       
/* 740 */       if (outPath != null) {
/*     */         
/* 742 */         fos = new FileOutputStream(outPath);
/* 743 */         ps = new PrintStream(fos);
/*     */       } else {
/*     */         
/* 746 */         ps = System.out;
/*     */       } 
/*     */ 
/*     */       
/* 750 */       if (path != null) {
/* 751 */         Font font = Font.create(path);
/*     */ 
/*     */         
/* 754 */         writeSvgBegin(ps);
/* 755 */         writeSvgDefsBegin(ps);
/* 756 */         writeFontAsSVGFragment(ps, font, id, (low != null) ? Integer.parseInt(low) : -1, (high != null) ? Integer.parseInt(high) : -1, (autoRange != null), (ascii != null));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 764 */         writeSvgDefsEnd(ps);
/* 765 */         if (testCard != null) {
/* 766 */           String fontFamily = font.getNameTable().getRecord((short)1);
/* 767 */           writeSvgTestCard(ps, fontFamily);
/*     */         } 
/* 769 */         writeSvgEnd(ps);
/*     */ 
/*     */         
/* 772 */         if (fos != null) {
/* 773 */           fos.close();
/*     */         }
/*     */       } else {
/* 776 */         usage();
/*     */       } 
/* 778 */     } catch (Exception e) {
/* 779 */       e.printStackTrace();
/* 780 */       System.err.println(e.getMessage());
/* 781 */       usage();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void chopUpStringBuffer(StringBuffer sb) {
/* 786 */     if (sb.length() < 256) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 791 */     for (int i = 240; i < sb.length(); i++) {
/* 792 */       if (sb.charAt(i) == ' ') {
/* 793 */         sb.setCharAt(i, '\n');
/* 794 */         i += 240;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static int midValue(int a, int b) {
/* 801 */     return a + (b - a) / 2;
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
/*     */   private static String parseArgs(String[] args, String name) {
/* 817 */     for (int i = 0; i < args.length; i++) {
/* 818 */       if (name == null) {
/* 819 */         if (args[i].charAt(0) != '-') {
/* 820 */           return args[i];
/*     */         }
/* 822 */       } else if (name.equalsIgnoreCase(args[i])) {
/* 823 */         if (i < args.length - 1 && args[i + 1].charAt(0) != '-') {
/* 824 */           return args[i + 1];
/*     */         }
/* 826 */         return args[i];
/*     */       } 
/*     */     } 
/*     */     
/* 830 */     return null;
/*     */   }
/*     */   
/*     */   private static void usage() {
/* 834 */     System.err.println(Messages.formatMessage(CONFIG_USAGE, null));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/SVGFont.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */