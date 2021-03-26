/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.gvt.font.GVTFont;
/*     */ import org.apache.batik.gvt.font.GVTFontFace;
/*     */ import org.apache.batik.gvt.font.GVTFontFamily;
/*     */ import org.apache.batik.gvt.text.GVTAttributedCharacterIterator;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
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
/*     */ 
/*     */ 
/*     */ public class SVGFontFamily
/*     */   implements GVTFontFamily
/*     */ {
/*  44 */   public static final AttributedCharacterIterator.Attribute TEXT_COMPOUND_ID = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.TEXT_COMPOUND_ID;
/*     */   
/*     */   protected GVTFontFace fontFace;
/*     */   
/*     */   protected Element fontElement;
/*     */   protected BridgeContext ctx;
/*  50 */   protected Boolean complex = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGFontFamily(GVTFontFace fontFace, Element fontElement, BridgeContext ctx) {
/*  65 */     this.fontFace = fontFace;
/*  66 */     this.fontElement = fontElement;
/*  67 */     this.ctx = ctx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFamilyName() {
/*  76 */     return this.fontFace.getFamilyName();
/*     */   }
/*     */ 
/*     */ 
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
/*     */ 
/*     */   
/*     */   public GVTFont deriveFont(float size, AttributedCharacterIterator aci) {
/*  98 */     return deriveFont(size, aci.getAttributes());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTFont deriveFont(float size, Map attrs) {
/* 108 */     SVGFontElementBridge fontBridge = (SVGFontElementBridge)this.ctx.getBridge(this.fontElement);
/* 109 */     SoftReference<Element> sr = (SoftReference)attrs.get(TEXT_COMPOUND_ID);
/* 110 */     Element textElement = sr.get();
/* 111 */     return fontBridge.createFont(this.ctx, this.fontElement, textElement, size, this.fontFace);
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
/*     */   public boolean isComplex() {
/* 123 */     if (this.complex != null) return this.complex.booleanValue(); 
/* 124 */     boolean ret = isComplex(this.fontElement, this.ctx);
/* 125 */     this.complex = ret ? Boolean.TRUE : Boolean.FALSE;
/* 126 */     return ret;
/*     */   }
/*     */   
/*     */   public static boolean isComplex(Element fontElement, BridgeContext ctx) {
/* 130 */     NodeList glyphElements = fontElement.getElementsByTagNameNS("http://www.w3.org/2000/svg", "glyph");
/*     */ 
/*     */     
/* 133 */     int numGlyphs = glyphElements.getLength();
/* 134 */     for (int i = 0; i < numGlyphs; i++) {
/* 135 */       Element glyph = (Element)glyphElements.item(i);
/* 136 */       Node child = glyph.getFirstChild();
/* 137 */       for (; child != null; child = child.getNextSibling()) {
/* 138 */         if (child.getNodeType() == 1) {
/*     */           
/* 140 */           Element e = (Element)child;
/* 141 */           Bridge b = ctx.getBridge(e);
/* 142 */           if (b != null && b instanceof GraphicsNodeBridge)
/* 143 */             return true; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 147 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGFontFamily.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */