/*     */ package org.apache.batik.gvt.font;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.font.TextAttribute;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.gvt.text.GVTAttributedCharacterIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AWTFontFamily
/*     */   implements GVTFontFamily
/*     */ {
/*  38 */   public static final AttributedCharacterIterator.Attribute TEXT_COMPOUND_DELIMITER = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.TEXT_COMPOUND_DELIMITER;
/*     */ 
/*     */ 
/*     */   
/*     */   protected GVTFontFace fontFace;
/*     */ 
/*     */   
/*     */   protected Font font;
/*     */ 
/*     */ 
/*     */   
/*     */   public AWTFontFamily(GVTFontFace fontFace) {
/*  50 */     this.fontFace = fontFace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AWTFontFamily(String familyName) {
/*  59 */     this(new GVTFontFace(familyName));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AWTFontFamily(GVTFontFace fontFace, Font font) {
/*  68 */     this.fontFace = fontFace;
/*  69 */     this.font = font;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFamilyName() {
/*  78 */     return this.fontFace.getFamilyName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTFontFace getFontFace() {
/*  85 */     return this.fontFace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTFont deriveFont(float size, AttributedCharacterIterator aci) {
/*  96 */     if (this.font != null) {
/*  97 */       return new AWTGVTFont(this.font, size);
/*     */     }
/*  99 */     return deriveFont(size, aci.getAttributes());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTFont deriveFont(float size, Map<?, ?> attrs) {
/* 109 */     if (this.font != null) {
/* 110 */       return new AWTGVTFont(this.font, size);
/*     */     }
/* 112 */     Map<Object, Object> fontAttributes = new HashMap<Object, Object>(attrs);
/* 113 */     fontAttributes.put(TextAttribute.SIZE, Float.valueOf(size));
/* 114 */     fontAttributes.put(TextAttribute.FAMILY, this.fontFace.getFamilyName());
/* 115 */     fontAttributes.remove(TEXT_COMPOUND_DELIMITER);
/* 116 */     return new AWTGVTFont(fontAttributes);
/*     */   }
/*     */   
/*     */   public boolean isComplex() {
/* 120 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/font/AWTFontFamily.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */