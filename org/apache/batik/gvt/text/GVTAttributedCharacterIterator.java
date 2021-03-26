/*     */ package org.apache.batik.gvt.text;
/*     */ 
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.AttributedString;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface GVTAttributedCharacterIterator
/*     */   extends AttributedCharacterIterator
/*     */ {
/*     */   void setString(String paramString);
/*     */   
/*     */   void setString(AttributedString paramAttributedString);
/*     */   
/*     */   void setAttributeArray(TextAttribute paramTextAttribute, Object[] paramArrayOfObject, int paramInt1, int paramInt2);
/*     */   
/*     */   Set getAllAttributeKeys();
/*     */   
/*     */   Object getAttribute(AttributedCharacterIterator.Attribute paramAttribute);
/*     */   
/*     */   Map getAttributes();
/*     */   
/*     */   int getRunLimit();
/*     */   
/*     */   int getRunLimit(AttributedCharacterIterator.Attribute paramAttribute);
/*     */   
/*     */   int getRunLimit(Set paramSet);
/*     */   
/*     */   int getRunStart();
/*     */   
/*     */   int getRunStart(AttributedCharacterIterator.Attribute paramAttribute);
/*     */   
/*     */   int getRunStart(Set paramSet);
/*     */   
/*     */   Object clone();
/*     */   
/*     */   char current();
/*     */   
/*     */   char first();
/*     */   
/*     */   int getBeginIndex();
/*     */   
/*     */   int getEndIndex();
/*     */   
/*     */   int getIndex();
/*     */   
/*     */   char last();
/*     */   
/*     */   char next();
/*     */   
/*     */   char previous();
/*     */   
/*     */   char setIndex(int paramInt);
/*     */   
/*     */   public static interface AttributeFilter
/*     */   {
/*     */     AttributedCharacterIterator mutateAttributes(AttributedCharacterIterator param1AttributedCharacterIterator);
/*     */   }
/*     */   
/*     */   public static class TextAttribute
/*     */     extends AttributedCharacterIterator.Attribute
/*     */   {
/*     */     public TextAttribute(String s) {
/* 209 */       super(s);
/*     */     }
/*     */     
/* 212 */     public static final TextAttribute FLOW_PARAGRAPH = new TextAttribute("FLOW_PARAGRAPH");
/*     */ 
/*     */     
/* 215 */     public static final TextAttribute FLOW_EMPTY_PARAGRAPH = new TextAttribute("FLOW_EMPTY_PARAGRAPH");
/*     */ 
/*     */     
/* 218 */     public static final TextAttribute FLOW_LINE_BREAK = new TextAttribute("FLOW_LINE_BREAK");
/*     */ 
/*     */     
/* 221 */     public static final TextAttribute FLOW_REGIONS = new TextAttribute("FLOW_REGIONS");
/*     */ 
/*     */     
/* 224 */     public static final TextAttribute LINE_HEIGHT = new TextAttribute("LINE_HEIGHT");
/*     */ 
/*     */     
/* 227 */     public static final TextAttribute PREFORMATTED = new TextAttribute("PREFORMATTED");
/*     */ 
/*     */ 
/*     */     
/* 231 */     public static final TextAttribute TEXT_COMPOUND_DELIMITER = new TextAttribute("TEXT_COMPOUND_DELIMITER");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 236 */     public static final TextAttribute TEXT_COMPOUND_ID = new TextAttribute("TEXT_COMPOUND_ID");
/*     */ 
/*     */ 
/*     */     
/* 240 */     public static final TextAttribute ANCHOR_TYPE = new TextAttribute("ANCHOR_TYPE");
/*     */ 
/*     */ 
/*     */     
/* 244 */     public static final TextAttribute EXPLICIT_LAYOUT = new TextAttribute("EXPLICIT_LAYOUT");
/*     */ 
/*     */ 
/*     */     
/* 248 */     public static final TextAttribute X = new TextAttribute("X");
/*     */ 
/*     */     
/* 251 */     public static final TextAttribute Y = new TextAttribute("Y");
/*     */ 
/*     */     
/* 254 */     public static final TextAttribute DX = new TextAttribute("DX");
/*     */ 
/*     */     
/* 257 */     public static final TextAttribute DY = new TextAttribute("DY");
/*     */ 
/*     */     
/* 260 */     public static final TextAttribute ROTATION = new TextAttribute("ROTATION");
/*     */ 
/*     */ 
/*     */     
/* 264 */     public static final TextAttribute PAINT_INFO = new TextAttribute("PAINT_INFO");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 270 */     public static final TextAttribute BBOX_WIDTH = new TextAttribute("BBOX_WIDTH");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 275 */     public static final TextAttribute LENGTH_ADJUST = new TextAttribute("LENGTH_ADJUST");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 280 */     public static final TextAttribute CUSTOM_SPACING = new TextAttribute("CUSTOM_SPACING");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 285 */     public static final TextAttribute KERNING = new TextAttribute("KERNING");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 290 */     public static final TextAttribute LETTER_SPACING = new TextAttribute("LETTER_SPACING");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 295 */     public static final TextAttribute WORD_SPACING = new TextAttribute("WORD_SPACING");
/*     */ 
/*     */ 
/*     */     
/* 299 */     public static final TextAttribute TEXTPATH = new TextAttribute("TEXTPATH");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 305 */     public static final TextAttribute FONT_VARIANT = new TextAttribute("FONT_VARIANT");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 310 */     public static final TextAttribute BASELINE_SHIFT = new TextAttribute("BASELINE_SHIFT");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 315 */     public static final TextAttribute WRITING_MODE = new TextAttribute("WRITING_MODE");
/*     */ 
/*     */     
/* 318 */     public static final TextAttribute VERTICAL_ORIENTATION = new TextAttribute("VERTICAL_ORIENTATION");
/*     */ 
/*     */     
/* 321 */     public static final TextAttribute VERTICAL_ORIENTATION_ANGLE = new TextAttribute("VERTICAL_ORIENTATION_ANGLE");
/*     */ 
/*     */     
/* 324 */     public static final TextAttribute HORIZONTAL_ORIENTATION_ANGLE = new TextAttribute("HORIZONTAL_ORIENTATION_ANGLE");
/*     */ 
/*     */     
/* 327 */     public static final TextAttribute GVT_FONT_FAMILIES = new TextAttribute("GVT_FONT_FAMILIES");
/*     */ 
/*     */     
/* 330 */     public static final TextAttribute GVT_FONTS = new TextAttribute("GVT_FONTS");
/*     */ 
/*     */     
/* 333 */     public static final TextAttribute GVT_FONT = new TextAttribute("GVT_FONT");
/*     */ 
/*     */     
/* 336 */     public static final TextAttribute ALT_GLYPH_HANDLER = new TextAttribute("ALT_GLYPH_HANDLER");
/*     */ 
/*     */     
/* 339 */     public static final TextAttribute BIDI_LEVEL = new TextAttribute("BIDI_LEVEL");
/*     */ 
/*     */     
/* 342 */     public static final TextAttribute CHAR_INDEX = new TextAttribute("CHAR_INDEX");
/*     */ 
/*     */     
/* 345 */     public static final TextAttribute ARABIC_FORM = new TextAttribute("ARABIC_FORM");
/*     */ 
/*     */     
/* 348 */     public static final TextAttribute SCRIPT = new TextAttribute("SCRIPT");
/*     */ 
/*     */     
/* 351 */     public static final TextAttribute LANGUAGE = new TextAttribute("LANGUAGE");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 357 */     public static final Integer WRITING_MODE_LTR = Integer.valueOf(1);
/*     */ 
/*     */     
/* 360 */     public static final Integer WRITING_MODE_RTL = Integer.valueOf(2);
/*     */ 
/*     */     
/* 363 */     public static final Integer WRITING_MODE_TTB = Integer.valueOf(3);
/*     */ 
/*     */     
/* 366 */     public static final Integer ORIENTATION_ANGLE = Integer.valueOf(1);
/*     */ 
/*     */     
/* 369 */     public static final Integer ORIENTATION_AUTO = Integer.valueOf(2);
/*     */ 
/*     */     
/* 372 */     public static final Integer SMALL_CAPS = Integer.valueOf(16);
/*     */ 
/*     */     
/* 375 */     public static final Integer UNDERLINE_ON = java.awt.font.TextAttribute.UNDERLINE_ON;
/*     */ 
/*     */ 
/*     */     
/* 379 */     public static final Boolean OVERLINE_ON = Boolean.TRUE;
/*     */ 
/*     */     
/* 382 */     public static final Boolean STRIKETHROUGH_ON = java.awt.font.TextAttribute.STRIKETHROUGH_ON;
/*     */ 
/*     */ 
/*     */     
/* 386 */     public static final Integer ADJUST_SPACING = Integer.valueOf(0);
/*     */ 
/*     */ 
/*     */     
/* 390 */     public static final Integer ADJUST_ALL = Integer.valueOf(1);
/*     */ 
/*     */ 
/*     */     
/* 394 */     public static final Integer ARABIC_NONE = Integer.valueOf(0);
/* 395 */     public static final Integer ARABIC_ISOLATED = Integer.valueOf(1);
/* 396 */     public static final Integer ARABIC_TERMINAL = Integer.valueOf(2);
/* 397 */     public static final Integer ARABIC_INITIAL = Integer.valueOf(3);
/* 398 */     public static final Integer ARABIC_MEDIAL = Integer.valueOf(4);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/text/GVTAttributedCharacterIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */