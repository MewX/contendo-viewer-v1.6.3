/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.Shape;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.GlyphMetrics;
/*     */ import java.awt.font.GlyphVector;
/*     */ import java.awt.font.LineMetrics;
/*     */ import java.awt.font.TextAttribute;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.g2d.GraphicContext;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   extends AbstractSVGConverter
/*     */ {
/*  46 */   public static final float EXTRA_LIGHT = TextAttribute.WEIGHT_EXTRA_LIGHT.floatValue();
/*     */   
/*  48 */   public static final float LIGHT = TextAttribute.WEIGHT_LIGHT.floatValue();
/*     */   
/*  50 */   public static final float DEMILIGHT = TextAttribute.WEIGHT_DEMILIGHT.floatValue();
/*     */   
/*  52 */   public static final float REGULAR = TextAttribute.WEIGHT_REGULAR.floatValue();
/*     */   
/*  54 */   public static final float SEMIBOLD = TextAttribute.WEIGHT_SEMIBOLD.floatValue();
/*     */   
/*  56 */   public static final float MEDIUM = TextAttribute.WEIGHT_MEDIUM.floatValue();
/*     */   
/*  58 */   public static final float DEMIBOLD = TextAttribute.WEIGHT_DEMIBOLD.floatValue();
/*     */   
/*  60 */   public static final float BOLD = TextAttribute.WEIGHT_BOLD.floatValue();
/*     */   
/*  62 */   public static final float HEAVY = TextAttribute.WEIGHT_HEAVY.floatValue();
/*     */   
/*  64 */   public static final float EXTRABOLD = TextAttribute.WEIGHT_EXTRABOLD.floatValue();
/*     */   
/*  66 */   public static final float ULTRABOLD = TextAttribute.WEIGHT_ULTRABOLD.floatValue();
/*     */ 
/*     */   
/*  69 */   public static final float POSTURE_REGULAR = TextAttribute.POSTURE_REGULAR.floatValue();
/*     */   
/*  71 */   public static final float POSTURE_OBLIQUE = TextAttribute.POSTURE_OBLIQUE.floatValue();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   static final float[] fontStyles = new float[] { POSTURE_REGULAR + (POSTURE_OBLIQUE - POSTURE_REGULAR) / 2.0F };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   static final String[] svgStyles = new String[] { "normal", "italic" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   static final float[] fontWeights = new float[] { EXTRA_LIGHT + (LIGHT - EXTRA_LIGHT) / 2.0F, LIGHT + (DEMILIGHT - LIGHT) / 2.0F, DEMILIGHT + (REGULAR - DEMILIGHT) / 2.0F, REGULAR + (SEMIBOLD - REGULAR) / 2.0F, SEMIBOLD + (MEDIUM - SEMIBOLD) / 2.0F, MEDIUM + (DEMIBOLD - MEDIUM) / 2.0F, DEMIBOLD + (BOLD - DEMIBOLD) / 2.0F, BOLD + (HEAVY - BOLD) / 2.0F, HEAVY + (EXTRABOLD - HEAVY) / 2.0F, EXTRABOLD + ULTRABOLD - EXTRABOLD };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   static final String[] svgWeights = new String[] { "100", "200", "300", "normal", "500", "500", "600", "bold", "800", "800", "900" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   static Map logicalFontMap = new HashMap<Object, Object>(); static final int COMMON_FONT_SIZE = 100;
/*     */   
/*     */   static {
/* 133 */     logicalFontMap.put("dialog", "sans-serif");
/* 134 */     logicalFontMap.put("dialoginput", "monospace");
/* 135 */     logicalFontMap.put("monospaced", "monospace");
/* 136 */     logicalFontMap.put("serif", "serif");
/* 137 */     logicalFontMap.put("sansserif", "sans-serif");
/* 138 */     logicalFontMap.put("symbol", "'WingDings'");
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
/* 150 */   final Map fontStringMap = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGFont(SVGGeneratorContext generatorContext) {
/* 156 */     super(generatorContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recordFontUsage(String string, Font font) {
/* 166 */     Font commonSizeFont = createCommonSizeFont(font);
/* 167 */     String fontKey = commonSizeFont.getFamily() + commonSizeFont.getStyle();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 184 */     CharListHelper chl = (CharListHelper)this.fontStringMap.get(fontKey);
/* 185 */     if (chl == null)
/*     */     {
/* 187 */       chl = new CharListHelper();
/*     */     }
/* 189 */     for (int i = 0; i < string.length(); i++) {
/* 190 */       char ch = string.charAt(i);
/* 191 */       chl.add(ch);
/*     */     } 
/*     */     
/* 194 */     this.fontStringMap.put(fontKey, chl);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Font createCommonSizeFont(Font font) {
/* 203 */     Map<Object, Object> attributes = new HashMap<Object, Object>();
/* 204 */     attributes.put(TextAttribute.SIZE, Float.valueOf(100.0F));
/*     */     
/* 206 */     attributes.put(TextAttribute.TRANSFORM, null);
/* 207 */     return font.deriveFont((Map)attributes);
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
/*     */   public SVGDescriptor toSVG(GraphicContext gc) {
/* 221 */     return toSVG(gc.getFont(), gc.getFontRenderContext());
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
/*     */   public SVGFontDescriptor toSVG(Font font, FontRenderContext frc) {
/*     */     Element fontDef;
/* 234 */     FontRenderContext localFRC = new FontRenderContext(new AffineTransform(), frc.isAntiAliased(), frc.usesFractionalMetrics());
/*     */ 
/*     */ 
/*     */     
/* 238 */     String fontSize = doubleString(font.getSize2D()) + "px";
/* 239 */     String fontWeight = weightToSVG(font);
/* 240 */     String fontStyle = styleToSVG(font);
/* 241 */     String fontFamilyStr = familyToSVG(font);
/*     */     
/* 243 */     Font commonSizeFont = createCommonSizeFont(font);
/* 244 */     String fontKey = commonSizeFont.getFamily() + commonSizeFont.getStyle();
/*     */ 
/*     */     
/* 247 */     CharListHelper clh = (CharListHelper)this.fontStringMap.get(fontKey);
/*     */     
/* 249 */     if (clh == null)
/*     */     {
/*     */       
/* 252 */       return new SVGFontDescriptor(fontSize, fontWeight, fontStyle, fontFamilyStr, null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 257 */     Document domFactory = this.generatorContext.domFactory;
/*     */ 
/*     */     
/* 260 */     SVGFontDescriptor fontDesc = (SVGFontDescriptor)this.descMap.get(fontKey);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 265 */     if (fontDesc != null) {
/*     */ 
/*     */       
/* 268 */       fontDef = fontDesc.getDef();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 273 */       fontDef = domFactory.createElementNS("http://www.w3.org/2000/svg", "font");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 279 */       Element fontFace = domFactory.createElementNS("http://www.w3.org/2000/svg", "font-face");
/*     */       
/* 281 */       String svgFontFamilyString = fontFamilyStr;
/* 282 */       if (fontFamilyStr.startsWith("'") && fontFamilyStr.endsWith("'"))
/*     */       {
/*     */         
/* 285 */         svgFontFamilyString = fontFamilyStr.substring(1, fontFamilyStr.length() - 1);
/*     */       }
/*     */       
/* 288 */       fontFace.setAttributeNS((String)null, "font-family", svgFontFamilyString);
/*     */       
/* 290 */       fontFace.setAttributeNS((String)null, "font-weight", fontWeight);
/*     */       
/* 292 */       fontFace.setAttributeNS((String)null, "font-style", fontStyle);
/*     */       
/* 294 */       fontFace.setAttributeNS((String)null, "units-per-em", "100");
/*     */       
/* 296 */       fontDef.appendChild(fontFace);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 301 */       Element missingGlyphElement = domFactory.createElementNS("http://www.w3.org/2000/svg", "missing-glyph");
/*     */ 
/*     */ 
/*     */       
/* 305 */       int[] missingGlyphCode = new int[1];
/* 306 */       missingGlyphCode[0] = commonSizeFont.getMissingGlyphCode();
/*     */       
/* 308 */       GlyphVector gv = commonSizeFont.createGlyphVector(localFRC, missingGlyphCode);
/* 309 */       Shape missingGlyphShape = gv.getGlyphOutline(0);
/* 310 */       GlyphMetrics gm = gv.getGlyphMetrics(0);
/*     */ 
/*     */ 
/*     */       
/* 314 */       AffineTransform at = AffineTransform.getScaleInstance(1.0D, -1.0D);
/* 315 */       missingGlyphShape = at.createTransformedShape(missingGlyphShape);
/*     */       
/* 317 */       missingGlyphElement.setAttributeNS((String)null, "d", SVGPath.toSVGPathData(missingGlyphShape, this.generatorContext));
/*     */       
/* 319 */       missingGlyphElement.setAttributeNS((String)null, "horiz-adv-x", String.valueOf(gm.getAdvance()));
/* 320 */       fontDef.appendChild(missingGlyphElement);
/*     */ 
/*     */ 
/*     */       
/* 324 */       fontDef.setAttributeNS((String)null, "horiz-adv-x", String.valueOf(gm.getAdvance()));
/*     */ 
/*     */       
/* 327 */       LineMetrics lm = commonSizeFont.getLineMetrics("By", localFRC);
/* 328 */       fontFace.setAttributeNS((String)null, "ascent", String.valueOf(lm.getAscent()));
/* 329 */       fontFace.setAttributeNS((String)null, "descent", String.valueOf(lm.getDescent()));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 334 */       fontDef.setAttributeNS((String)null, "id", this.generatorContext.idGenerator.generateID("font"));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 340 */     String textUsingFont = clh.getNewChars();
/* 341 */     clh.clearNewChars();
/*     */ 
/*     */ 
/*     */     
/* 345 */     for (int i = textUsingFont.length() - 1; i >= 0; ) {
/* 346 */       char c = textUsingFont.charAt(i);
/* 347 */       String searchStr = String.valueOf(c);
/* 348 */       boolean foundGlyph = false;
/* 349 */       NodeList fontChildren = fontDef.getChildNodes();
/* 350 */       for (int j = 0; j < fontChildren.getLength(); j++) {
/* 351 */         if (fontChildren.item(j) instanceof Element) {
/* 352 */           Element childElement = (Element)fontChildren.item(j);
/* 353 */           if (childElement.getAttributeNS((String)null, "unicode").equals(searchStr)) {
/* 354 */             foundGlyph = true;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 359 */       if (!foundGlyph) {
/*     */         
/* 361 */         Element glyphElement = domFactory.createElementNS("http://www.w3.org/2000/svg", "glyph");
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 366 */         GlyphVector gv = commonSizeFont.createGlyphVector(localFRC, "" + c);
/* 367 */         Shape glyphShape = gv.getGlyphOutline(0);
/* 368 */         GlyphMetrics gm = gv.getGlyphMetrics(0);
/*     */ 
/*     */ 
/*     */         
/* 372 */         AffineTransform at = AffineTransform.getScaleInstance(1.0D, -1.0D);
/* 373 */         glyphShape = at.createTransformedShape(glyphShape);
/*     */         
/* 375 */         glyphElement.setAttributeNS((String)null, "d", SVGPath.toSVGPathData(glyphShape, this.generatorContext));
/*     */         
/* 377 */         glyphElement.setAttributeNS((String)null, "horiz-adv-x", String.valueOf(gm.getAdvance()));
/* 378 */         glyphElement.setAttributeNS((String)null, "unicode", String.valueOf(c));
/*     */         
/* 380 */         fontDef.appendChild(glyphElement);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         i--;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 391 */     SVGFontDescriptor newFontDesc = new SVGFontDescriptor(fontSize, fontWeight, fontStyle, fontFamilyStr, fontDef);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 399 */     if (fontDesc == null) {
/* 400 */       this.descMap.put(fontKey, newFontDesc);
/* 401 */       this.defSet.add(fontDef);
/*     */     } 
/*     */     
/* 404 */     return newFontDesc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String familyToSVG(Font font) {
/* 412 */     String fontFamilyStr = font.getFamily();
/* 413 */     String logicalFontFamily = (String)logicalFontMap.get(font.getName().toLowerCase());
/*     */     
/* 415 */     if (logicalFontFamily != null) {
/* 416 */       fontFamilyStr = logicalFontFamily;
/*     */     } else {
/* 418 */       char QUOTE = '\'';
/* 419 */       fontFamilyStr = '\'' + fontFamilyStr + '\'';
/*     */     } 
/* 421 */     return fontFamilyStr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String styleToSVG(Font font) {
/* 429 */     Map<TextAttribute, ?> attrMap = font.getAttributes();
/* 430 */     Float styleValue = (Float)attrMap.get(TextAttribute.POSTURE);
/*     */     
/* 432 */     if (styleValue == null) {
/* 433 */       if (font.isItalic()) {
/* 434 */         styleValue = TextAttribute.POSTURE_OBLIQUE;
/*     */       } else {
/* 436 */         styleValue = TextAttribute.POSTURE_REGULAR;
/*     */       } 
/*     */     }
/* 439 */     float style = styleValue.floatValue();
/*     */     
/* 441 */     int i = 0;
/* 442 */     for (i = 0; i < fontStyles.length && 
/* 443 */       style > fontStyles[i]; i++);
/*     */ 
/*     */ 
/*     */     
/* 447 */     return svgStyles[i];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String weightToSVG(Font font) {
/* 456 */     Map<TextAttribute, ?> attrMap = font.getAttributes();
/* 457 */     Float weightValue = (Float)attrMap.get(TextAttribute.WEIGHT);
/* 458 */     if (weightValue == null) {
/* 459 */       if (font.isBold()) {
/* 460 */         weightValue = TextAttribute.WEIGHT_BOLD;
/*     */       } else {
/* 462 */         weightValue = TextAttribute.WEIGHT_REGULAR;
/*     */       } 
/*     */     }
/* 465 */     float weight = weightValue.floatValue();
/*     */     
/* 467 */     int i = 0;
/* 468 */     for (i = 0; i < fontWeights.length && 
/* 469 */       weight > fontWeights[i]; i++);
/*     */ 
/*     */ 
/*     */     
/* 473 */     return svgWeights[i];
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
/*     */   private static class CharListHelper
/*     */   {
/* 490 */     private int nUsed = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 495 */     private int[] charList = new int[40];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 501 */     private StringBuffer freshChars = new StringBuffer(40);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     String getNewChars() {
/* 512 */       return this.freshChars.toString();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void clearNewChars() {
/* 519 */       this.freshChars = new StringBuffer(40);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     boolean add(int c) {
/* 530 */       int pos = binSearch(this.charList, this.nUsed, c);
/* 531 */       if (pos >= 0)
/*     */       {
/* 533 */         return false;
/*     */       }
/*     */       
/* 536 */       if (this.nUsed == this.charList.length) {
/*     */         
/* 538 */         int[] t = new int[this.nUsed + 20];
/* 539 */         System.arraycopy(this.charList, 0, t, 0, this.nUsed);
/* 540 */         this.charList = t;
/*     */       } 
/*     */       
/* 543 */       pos = -pos - 1;
/* 544 */       System.arraycopy(this.charList, pos, this.charList, pos + 1, this.nUsed - pos);
/* 545 */       this.charList[pos] = c;
/* 546 */       this.freshChars.append((char)c);
/* 547 */       this.nUsed++;
/*     */       
/* 549 */       return true;
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
/*     */     static int binSearch(int[] list, int nUsed, int chr) {
/* 563 */       int low = 0;
/* 564 */       int high = nUsed - 1;
/*     */       
/* 566 */       while (low <= high) {
/* 567 */         int mid = low + high >>> 1;
/* 568 */         int midVal = list[mid];
/*     */         
/* 570 */         if (midVal < chr) {
/* 571 */           low = mid + 1; continue;
/* 572 */         }  if (midVal > chr) {
/* 573 */           high = mid - 1; continue;
/*     */         } 
/* 575 */         return mid;
/*     */       } 
/*     */       
/* 578 */       return -(low + 1);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGFont.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */