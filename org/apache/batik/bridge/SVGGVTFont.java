/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.CharacterIterator;
/*     */ import java.text.StringCharacterIterator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.apache.batik.dom.util.XMLSupport;
/*     */ import org.apache.batik.gvt.font.GVTFont;
/*     */ import org.apache.batik.gvt.font.GVTFontFace;
/*     */ import org.apache.batik.gvt.font.GVTGlyphVector;
/*     */ import org.apache.batik.gvt.font.GVTLineMetrics;
/*     */ import org.apache.batik.gvt.font.Glyph;
/*     */ import org.apache.batik.gvt.font.Kern;
/*     */ import org.apache.batik.gvt.font.KerningTable;
/*     */ import org.apache.batik.gvt.font.SVGGVTGlyphVector;
/*     */ import org.apache.batik.gvt.text.GVTAttributedCharacterIterator;
/*     */ import org.apache.batik.gvt.text.TextPaintInfo;
/*     */ import org.apache.batik.util.SVGConstants;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SVGGVTFont
/*     */   implements GVTFont, SVGConstants
/*     */ {
/*  55 */   public static final AttributedCharacterIterator.Attribute PAINT_INFO = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.PAINT_INFO;
/*     */   
/*     */   private float fontSize;
/*     */   
/*     */   private GVTFontFace fontFace;
/*     */   private String[] glyphUnicodes;
/*     */   private String[] glyphNames;
/*     */   private String[] glyphLangs;
/*     */   private String[] glyphOrientations;
/*     */   private String[] glyphForms;
/*     */   private Element[] glyphElements;
/*     */   private Element[] hkernElements;
/*     */   private Element[] vkernElements;
/*     */   private BridgeContext ctx;
/*     */   private Element textElement;
/*     */   private Element missingGlyphElement;
/*     */   private KerningTable hKerningTable;
/*     */   private KerningTable vKerningTable;
/*     */   private String language;
/*     */   private String orientation;
/*     */   private float scale;
/*  76 */   private GVTLineMetrics lineMetrics = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGGVTFont(float fontSize, GVTFontFace fontFace, String[] glyphUnicodes, String[] glyphNames, String[] glyphLangs, String[] glyphOrientations, String[] glyphForms, BridgeContext ctx, Element[] glyphElements, Element missingGlyphElement, Element[] hkernElements, Element[] vkernElements, Element textElement) {
/* 112 */     this.fontFace = fontFace;
/* 113 */     this.fontSize = fontSize;
/* 114 */     this.glyphUnicodes = glyphUnicodes;
/* 115 */     this.glyphNames = glyphNames;
/* 116 */     this.glyphLangs = glyphLangs;
/* 117 */     this.glyphOrientations = glyphOrientations;
/* 118 */     this.glyphForms = glyphForms;
/* 119 */     this.ctx = ctx;
/* 120 */     this.glyphElements = glyphElements;
/* 121 */     this.missingGlyphElement = missingGlyphElement;
/* 122 */     this.hkernElements = hkernElements;
/* 123 */     this.vkernElements = vkernElements;
/* 124 */     this.scale = fontSize / fontFace.getUnitsPerEm();
/* 125 */     this.textElement = textElement;
/*     */     
/* 127 */     this.language = XMLSupport.getXMLLang(textElement);
/*     */     
/* 129 */     Value v = CSSUtilities.getComputedStyle(textElement, 59);
/*     */     
/* 131 */     if (v.getStringValue().startsWith("tb")) {
/*     */       
/* 133 */       this.orientation = "v";
/*     */     } else {
/* 135 */       this.orientation = "h";
/*     */     } 
/*     */     
/* 138 */     createKerningTables();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createKerningTables() {
/* 149 */     Kern[] hEntries = new Kern[this.hkernElements.length];
/* 150 */     for (int i = 0; i < this.hkernElements.length; i++) {
/* 151 */       Element hkernElement = this.hkernElements[i];
/* 152 */       SVGHKernElementBridge hkernBridge = (SVGHKernElementBridge)this.ctx.getBridge(hkernElement);
/*     */       
/* 154 */       Kern hkern = hkernBridge.createKern(this.ctx, hkernElement, this);
/* 155 */       hEntries[i] = hkern;
/*     */     } 
/* 157 */     this.hKerningTable = new KerningTable(hEntries);
/*     */     
/* 159 */     Kern[] vEntries = new Kern[this.vkernElements.length];
/* 160 */     for (int j = 0; j < this.vkernElements.length; j++) {
/* 161 */       Element vkernElement = this.vkernElements[j];
/* 162 */       SVGVKernElementBridge vkernBridge = (SVGVKernElementBridge)this.ctx.getBridge(vkernElement);
/*     */       
/* 164 */       Kern vkern = vkernBridge.createKern(this.ctx, vkernElement, this);
/* 165 */       vEntries[j] = vkern;
/*     */     } 
/* 167 */     this.vKerningTable = new KerningTable(vEntries);
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
/*     */   public float getHKern(int glyphCode1, int glyphCode2) {
/* 182 */     if (glyphCode1 < 0 || glyphCode1 >= this.glyphUnicodes.length || glyphCode2 < 0 || glyphCode2 >= this.glyphUnicodes.length)
/*     */     {
/* 184 */       return 0.0F;
/*     */     }
/*     */     
/* 187 */     float ret = this.hKerningTable.getKerningValue(glyphCode1, glyphCode2, this.glyphUnicodes[glyphCode1], this.glyphUnicodes[glyphCode2]);
/*     */ 
/*     */     
/* 190 */     return ret * this.scale;
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
/*     */   public float getVKern(int glyphCode1, int glyphCode2) {
/* 204 */     if (glyphCode1 < 0 || glyphCode1 >= this.glyphUnicodes.length || glyphCode2 < 0 || glyphCode2 >= this.glyphUnicodes.length)
/*     */     {
/* 206 */       return 0.0F;
/*     */     }
/*     */     
/* 209 */     float ret = this.vKerningTable.getKerningValue(glyphCode1, glyphCode2, this.glyphUnicodes[glyphCode1], this.glyphUnicodes[glyphCode2]);
/*     */ 
/*     */     
/* 212 */     return ret * this.scale;
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
/*     */   public int[] getGlyphCodesForName(String name) {
/* 224 */     List<Integer> glyphCodes = new ArrayList();
/* 225 */     for (int i = 0; i < this.glyphNames.length; i++) {
/* 226 */       if (this.glyphNames[i] != null && this.glyphNames[i].equals(name)) {
/* 227 */         glyphCodes.add(Integer.valueOf(i));
/*     */       }
/*     */     } 
/* 230 */     int[] glyphCodeArray = new int[glyphCodes.size()];
/* 231 */     for (int j = 0; j < glyphCodes.size(); j++) {
/* 232 */       glyphCodeArray[j] = ((Integer)glyphCodes.get(j)).intValue();
/*     */     }
/* 234 */     return glyphCodeArray;
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
/*     */   public int[] getGlyphCodesForUnicode(String unicode) {
/* 246 */     List<Integer> glyphCodes = new ArrayList();
/* 247 */     for (int i = 0; i < this.glyphUnicodes.length; i++) {
/* 248 */       if (this.glyphUnicodes[i] != null && this.glyphUnicodes[i].equals(unicode)) {
/* 249 */         glyphCodes.add(Integer.valueOf(i));
/*     */       }
/*     */     } 
/* 252 */     int[] glyphCodeArray = new int[glyphCodes.size()];
/* 253 */     for (int j = 0; j < glyphCodes.size(); j++) {
/* 254 */       glyphCodeArray[j] = ((Integer)glyphCodes.get(j)).intValue();
/*     */     }
/* 256 */     return glyphCodeArray;
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
/*     */   private boolean languageMatches(String glyphLang) {
/* 273 */     if (glyphLang == null || glyphLang.length() == 0) {
/* 274 */       return true;
/*     */     }
/* 276 */     StringTokenizer st = new StringTokenizer(glyphLang, ",");
/* 277 */     while (st.hasMoreTokens()) {
/* 278 */       String s = st.nextToken();
/* 279 */       if (s.equals(this.language) || (s.startsWith(this.language) && s.length() > this.language.length() && s.charAt(this.language.length()) == '-'))
/*     */       {
/*     */         
/* 282 */         return true;
/*     */       }
/*     */     } 
/* 285 */     return false;
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
/*     */   private boolean orientationMatches(String glyphOrientation) {
/* 299 */     if (glyphOrientation == null || glyphOrientation.length() == 0) {
/* 300 */       return true;
/*     */     }
/* 302 */     return glyphOrientation.equals(this.orientation);
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
/*     */   private boolean formMatches(String glyphUnicode, String glyphForm, AttributedCharacterIterator aci, int currentIndex) {
/* 319 */     if (aci == null || glyphForm == null || glyphForm.length() == 0)
/*     */     {
/*     */       
/* 322 */       return true;
/*     */     }
/*     */     
/* 325 */     char c = aci.setIndex(currentIndex);
/* 326 */     Integer form = (Integer)aci.getAttribute((AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.ARABIC_FORM);
/*     */ 
/*     */     
/* 329 */     if (form == null || form.equals(GVTAttributedCharacterIterator.TextAttribute.ARABIC_NONE))
/*     */     {
/*     */ 
/*     */       
/* 333 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 337 */     if (glyphUnicode.length() > 1) {
/*     */       
/* 339 */       boolean matched = true;
/* 340 */       for (int j = 1; j < glyphUnicode.length(); j++) {
/* 341 */         c = aci.next();
/* 342 */         if (glyphUnicode.charAt(j) != c) {
/* 343 */           matched = false;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       
/* 349 */       aci.setIndex(currentIndex);
/*     */       
/* 351 */       if (matched) {
/*     */ 
/*     */         
/* 354 */         aci.setIndex(currentIndex + glyphUnicode.length() - 1);
/* 355 */         Integer lastForm = (Integer)aci.getAttribute((AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.ARABIC_FORM);
/*     */ 
/*     */ 
/*     */         
/* 359 */         aci.setIndex(currentIndex);
/*     */         
/* 361 */         if (form != null && lastForm != null) {
/* 362 */           if (form.equals(GVTAttributedCharacterIterator.TextAttribute.ARABIC_TERMINAL) && lastForm.equals(GVTAttributedCharacterIterator.TextAttribute.ARABIC_INITIAL))
/*     */           {
/*     */ 
/*     */ 
/*     */             
/* 367 */             return glyphForm.equals("isolated");
/*     */           }
/*     */           
/* 370 */           if (form.equals(GVTAttributedCharacterIterator.TextAttribute.ARABIC_TERMINAL))
/*     */           {
/*     */             
/* 373 */             return glyphForm.equals("terminal");
/*     */           }
/*     */           
/* 376 */           if (form.equals(GVTAttributedCharacterIterator.TextAttribute.ARABIC_MEDIAL) && lastForm.equals(GVTAttributedCharacterIterator.TextAttribute.ARABIC_MEDIAL))
/*     */           {
/*     */ 
/*     */ 
/*     */             
/* 381 */             return glyphForm.equals("medial");
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 388 */     if (form.equals(GVTAttributedCharacterIterator.TextAttribute.ARABIC_ISOLATED))
/*     */     {
/* 390 */       return glyphForm.equals("isolated");
/*     */     }
/* 392 */     if (form.equals(GVTAttributedCharacterIterator.TextAttribute.ARABIC_TERMINAL))
/*     */     {
/* 394 */       return glyphForm.equals("terminal");
/*     */     }
/* 396 */     if (form.equals(GVTAttributedCharacterIterator.TextAttribute.ARABIC_INITIAL))
/*     */     {
/* 398 */       return glyphForm.equals("initial");
/*     */     }
/* 400 */     if (form.equals(GVTAttributedCharacterIterator.TextAttribute.ARABIC_MEDIAL))
/*     */     {
/* 402 */       return glyphForm.equals("medial");
/*     */     }
/* 404 */     return false;
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
/*     */   public boolean canDisplayGivenName(String name) {
/* 416 */     for (int i = 0; i < this.glyphNames.length; i++) {
/* 417 */       if (this.glyphNames[i] != null && this.glyphNames[i].equals(name) && languageMatches(this.glyphLangs[i]) && orientationMatches(this.glyphOrientations[i]))
/*     */       {
/*     */         
/* 420 */         return true;
/*     */       }
/*     */     } 
/* 423 */     return false;
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
/*     */   public boolean canDisplay(char c) {
/* 436 */     for (int i = 0; i < this.glyphUnicodes.length; i++) {
/* 437 */       if (this.glyphUnicodes[i].indexOf(c) != -1 && languageMatches(this.glyphLangs[i]) && orientationMatches(this.glyphOrientations[i]))
/*     */       {
/*     */         
/* 440 */         return true;
/*     */       }
/*     */     } 
/* 443 */     return false;
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
/*     */   public int canDisplayUpTo(char[] text, int start, int limit) {
/* 458 */     StringCharacterIterator sci = new StringCharacterIterator(new String(text));
/*     */     
/* 460 */     return canDisplayUpTo(sci, start, limit);
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
/*     */   public int canDisplayUpTo(CharacterIterator iter, int start, int limit) {
/* 476 */     AttributedCharacterIterator aci = null;
/* 477 */     if (iter instanceof AttributedCharacterIterator) {
/* 478 */       aci = (AttributedCharacterIterator)iter;
/*     */     }
/*     */     
/* 481 */     char c = iter.setIndex(start);
/* 482 */     int currentIndex = start;
/*     */     
/* 484 */     while (c != Character.MAX_VALUE && currentIndex < limit) {
/*     */       
/* 486 */       boolean foundMatchingGlyph = false;
/*     */       
/* 488 */       for (int i = 0; i < this.glyphUnicodes.length; i++) {
/* 489 */         if (this.glyphUnicodes[i].indexOf(c) == 0 && languageMatches(this.glyphLangs[i]) && orientationMatches(this.glyphOrientations[i]) && formMatches(this.glyphUnicodes[i], this.glyphForms[i], aci, currentIndex)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 496 */           if (this.glyphUnicodes[i].length() == 1) {
/* 497 */             foundMatchingGlyph = true;
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */           
/* 503 */           boolean matched = true;
/* 504 */           for (int j = 1; j < this.glyphUnicodes[i].length(); j++) {
/* 505 */             c = iter.next();
/* 506 */             if (this.glyphUnicodes[i].charAt(j) != c) {
/* 507 */               matched = false;
/*     */               break;
/*     */             } 
/*     */           } 
/* 511 */           if (matched) {
/* 512 */             foundMatchingGlyph = true;
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */           
/* 518 */           c = iter.setIndex(currentIndex);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 523 */       if (!foundMatchingGlyph) {
/* 524 */         return currentIndex;
/*     */       }
/* 526 */       c = iter.next();
/* 527 */       currentIndex = iter.getIndex();
/*     */     } 
/* 529 */     return -1;
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
/*     */   public int canDisplayUpTo(String str) {
/* 542 */     StringCharacterIterator sci = new StringCharacterIterator(str);
/* 543 */     return canDisplayUpTo(sci, 0, str.length());
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
/*     */   public GVTGlyphVector createGlyphVector(FontRenderContext frc, char[] chars) {
/* 557 */     StringCharacterIterator sci = new StringCharacterIterator(new String(chars));
/*     */     
/* 559 */     return createGlyphVector(frc, sci);
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
/*     */   public GVTGlyphVector createGlyphVector(FontRenderContext frc, CharacterIterator ci) {
/* 574 */     AttributedCharacterIterator aci = null;
/* 575 */     if (ci instanceof AttributedCharacterIterator) {
/* 576 */       aci = (AttributedCharacterIterator)ci;
/*     */     }
/*     */     
/* 579 */     List<Glyph> glyphs = new ArrayList();
/* 580 */     char c = ci.first();
/* 581 */     while (c != Character.MAX_VALUE) {
/* 582 */       boolean foundMatchingGlyph = false;
/* 583 */       for (int i = 0; i < this.glyphUnicodes.length; i++) {
/* 584 */         if (this.glyphUnicodes[i].indexOf(c) == 0 && languageMatches(this.glyphLangs[i]) && orientationMatches(this.glyphOrientations[i]) && formMatches(this.glyphUnicodes[i], this.glyphForms[i], aci, ci.getIndex())) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 590 */           if (this.glyphUnicodes[i].length() == 1) {
/* 591 */             Element glyphElement = this.glyphElements[i];
/* 592 */             SVGGlyphElementBridge glyphBridge = (SVGGlyphElementBridge)this.ctx.getBridge(glyphElement);
/*     */             
/* 594 */             TextPaintInfo tpi = null;
/* 595 */             if (aci != null) {
/* 596 */               tpi = (TextPaintInfo)aci.getAttribute(PAINT_INFO);
/*     */             }
/* 598 */             Glyph glyph = glyphBridge.createGlyph(this.ctx, glyphElement, this.textElement, i, this.fontSize, this.fontFace, tpi);
/*     */ 
/*     */             
/* 601 */             glyphs.add(glyph);
/* 602 */             foundMatchingGlyph = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */           
/* 607 */           int current = ci.getIndex();
/* 608 */           boolean matched = true;
/* 609 */           for (int j = 1; j < this.glyphUnicodes[i].length(); j++) {
/* 610 */             c = ci.next();
/* 611 */             if (this.glyphUnicodes[i].charAt(j) != c) {
/* 612 */               matched = false;
/*     */               break;
/*     */             } 
/*     */           } 
/* 616 */           if (matched) {
/*     */             
/* 618 */             Element glyphElement = this.glyphElements[i];
/* 619 */             SVGGlyphElementBridge glyphBridge = (SVGGlyphElementBridge)this.ctx.getBridge(glyphElement);
/*     */ 
/*     */             
/* 622 */             TextPaintInfo tpi = null;
/* 623 */             if (aci != null) {
/* 624 */               aci.setIndex(ci.getIndex());
/* 625 */               tpi = (TextPaintInfo)aci.getAttribute(PAINT_INFO);
/*     */             } 
/*     */             
/* 628 */             Glyph glyph = glyphBridge.createGlyph(this.ctx, glyphElement, this.textElement, i, this.fontSize, this.fontFace, tpi);
/*     */ 
/*     */             
/* 631 */             glyphs.add(glyph);
/* 632 */             foundMatchingGlyph = true;
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */           
/* 638 */           c = ci.setIndex(current);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 643 */       if (!foundMatchingGlyph) {
/*     */         
/* 645 */         SVGGlyphElementBridge glyphBridge = (SVGGlyphElementBridge)this.ctx.getBridge(this.missingGlyphElement);
/*     */         
/* 647 */         TextPaintInfo tpi = null;
/* 648 */         if (aci != null) {
/* 649 */           aci.setIndex(ci.getIndex());
/* 650 */           tpi = (TextPaintInfo)aci.getAttribute(PAINT_INFO);
/*     */         } 
/* 652 */         Glyph glyph = glyphBridge.createGlyph(this.ctx, this.missingGlyphElement, this.textElement, -1, this.fontSize, this.fontFace, tpi);
/*     */ 
/*     */         
/* 655 */         glyphs.add(glyph);
/*     */       } 
/* 657 */       c = ci.next();
/*     */     } 
/*     */ 
/*     */     
/* 661 */     int numGlyphs = glyphs.size();
/*     */     
/* 663 */     Glyph[] glyphArray = glyphs.<Glyph>toArray(new Glyph[numGlyphs]);
/*     */ 
/*     */     
/* 666 */     return (GVTGlyphVector)new SVGGVTGlyphVector(this, glyphArray, frc);
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
/*     */   public GVTGlyphVector createGlyphVector(FontRenderContext frc, int[] glyphCodes, CharacterIterator ci) {
/* 683 */     int nGlyphs = glyphCodes.length;
/* 684 */     StringBuffer workBuff = new StringBuffer(nGlyphs);
/* 685 */     for (int glyphCode : glyphCodes) {
/* 686 */       workBuff.append(this.glyphUnicodes[glyphCode]);
/*     */     }
/* 688 */     StringCharacterIterator sci = new StringCharacterIterator(workBuff.toString());
/* 689 */     return createGlyphVector(frc, sci);
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
/*     */   public GVTGlyphVector createGlyphVector(FontRenderContext frc, String str) {
/* 702 */     StringCharacterIterator sci = new StringCharacterIterator(str);
/* 703 */     return createGlyphVector(frc, sci);
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
/*     */   public GVTFont deriveFont(float size) {
/* 715 */     return new SVGGVTFont(size, this.fontFace, this.glyphUnicodes, this.glyphNames, this.glyphLangs, this.glyphOrientations, this.glyphForms, this.ctx, this.glyphElements, this.missingGlyphElement, this.hkernElements, this.vkernElements, this.textElement);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFamilyName() {
/* 722 */     return this.fontFace.getFamilyName();
/*     */   }
/*     */   
/*     */   protected GVTLineMetrics getLineMetrics(int beginIndex, int limit) {
/* 726 */     if (this.lineMetrics != null) {
/* 727 */       return this.lineMetrics;
/*     */     }
/* 729 */     float fontHeight = this.fontFace.getUnitsPerEm();
/* 730 */     float scale = this.fontSize / fontHeight;
/*     */     
/* 732 */     float ascent = this.fontFace.getAscent() * scale;
/* 733 */     float descent = this.fontFace.getDescent() * scale;
/*     */     
/* 735 */     float[] baselineOffsets = new float[3];
/* 736 */     baselineOffsets[0] = 0.0F;
/* 737 */     baselineOffsets[1] = (ascent + descent) / 2.0F - ascent;
/* 738 */     baselineOffsets[2] = -ascent;
/*     */     
/* 740 */     float stOffset = this.fontFace.getStrikethroughPosition() * -scale;
/* 741 */     float stThickness = this.fontFace.getStrikethroughThickness() * scale;
/* 742 */     float ulOffset = this.fontFace.getUnderlinePosition() * scale;
/* 743 */     float ulThickness = this.fontFace.getUnderlineThickness() * scale;
/* 744 */     float olOffset = this.fontFace.getOverlinePosition() * -scale;
/* 745 */     float olThickness = this.fontFace.getOverlineThickness() * scale;
/*     */ 
/*     */     
/* 748 */     this.lineMetrics = new GVTLineMetrics(ascent, 0, baselineOffsets, descent, fontHeight, fontHeight, limit - beginIndex, stOffset, stThickness, ulOffset, ulThickness, olOffset, olThickness);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 754 */     return this.lineMetrics;
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
/*     */   public GVTLineMetrics getLineMetrics(char[] chars, int beginIndex, int limit, FontRenderContext frc) {
/* 770 */     return getLineMetrics(beginIndex, limit);
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
/*     */   public GVTLineMetrics getLineMetrics(CharacterIterator ci, int beginIndex, int limit, FontRenderContext frc) {
/* 785 */     return getLineMetrics(beginIndex, limit);
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
/*     */   public GVTLineMetrics getLineMetrics(String str, FontRenderContext frc) {
/* 797 */     StringCharacterIterator sci = new StringCharacterIterator(str);
/* 798 */     return getLineMetrics(sci, 0, str.length(), frc);
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
/*     */   public GVTLineMetrics getLineMetrics(String str, int beginIndex, int limit, FontRenderContext frc) {
/* 813 */     StringCharacterIterator sci = new StringCharacterIterator(str);
/* 814 */     return getLineMetrics(sci, beginIndex, limit, frc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSize() {
/* 823 */     return this.fontSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 833 */     return this.fontFace.getFamilyName() + " " + this.fontFace.getFontWeight() + " " + this.fontFace.getFontStyle();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGGVTFont.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */