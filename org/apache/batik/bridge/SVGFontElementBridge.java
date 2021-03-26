/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import org.apache.batik.gvt.font.GVTFontFace;
/*     */ import org.apache.batik.gvt.text.ArabicTextHandler;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGFontElementBridge
/*     */   extends AbstractSVGBridge
/*     */ {
/*     */   public String getLocalName() {
/*  44 */     return "font";
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
/*     */   public SVGGVTFont createFont(BridgeContext ctx, Element fontElement, Element textElement, float size, GVTFontFace fontFace) {
/*  68 */     NodeList glyphElements = fontElement.getElementsByTagNameNS("http://www.w3.org/2000/svg", "glyph");
/*     */     
/*  70 */     int numGlyphs = glyphElements.getLength();
/*  71 */     String[] glyphCodes = new String[numGlyphs];
/*  72 */     String[] glyphNames = new String[numGlyphs];
/*  73 */     String[] glyphLangs = new String[numGlyphs];
/*  74 */     String[] glyphOrientations = new String[numGlyphs];
/*  75 */     String[] glyphForms = new String[numGlyphs];
/*  76 */     Element[] glyphElementArray = new Element[numGlyphs];
/*     */     
/*  78 */     for (int i = 0; i < numGlyphs; i++) {
/*  79 */       Element glyphElement = (Element)glyphElements.item(i);
/*  80 */       glyphCodes[i] = glyphElement.getAttributeNS(null, "unicode");
/*  81 */       if (glyphCodes[i].length() > 1)
/*     */       {
/*  83 */         if (ArabicTextHandler.arabicChar(glyphCodes[i].charAt(0))) {
/*  84 */           glyphCodes[i] = (new StringBuffer(glyphCodes[i])).reverse().toString();
/*     */         }
/*     */       }
/*  87 */       glyphNames[i] = glyphElement.getAttributeNS(null, "glyph-name");
/*  88 */       glyphLangs[i] = glyphElement.getAttributeNS(null, "lang");
/*  89 */       glyphOrientations[i] = glyphElement.getAttributeNS(null, "orientation");
/*  90 */       glyphForms[i] = glyphElement.getAttributeNS(null, "arabic-form");
/*  91 */       glyphElementArray[i] = glyphElement;
/*     */     } 
/*     */ 
/*     */     
/*  95 */     NodeList missingGlyphElements = fontElement.getElementsByTagNameNS("http://www.w3.org/2000/svg", "missing-glyph");
/*     */     
/*  97 */     Element missingGlyphElement = null;
/*  98 */     if (missingGlyphElements.getLength() > 0) {
/*  99 */       missingGlyphElement = (Element)missingGlyphElements.item(0);
/*     */     }
/*     */ 
/*     */     
/* 103 */     NodeList hkernElements = fontElement.getElementsByTagNameNS("http://www.w3.org/2000/svg", "hkern");
/*     */     
/* 105 */     Element[] hkernElementArray = new Element[hkernElements.getLength()];
/*     */     
/* 107 */     for (int j = 0; j < hkernElementArray.length; j++) {
/* 108 */       Element hkernElement = (Element)hkernElements.item(j);
/* 109 */       hkernElementArray[j] = hkernElement;
/*     */     } 
/*     */ 
/*     */     
/* 113 */     NodeList vkernElements = fontElement.getElementsByTagNameNS("http://www.w3.org/2000/svg", "vkern");
/*     */     
/* 115 */     Element[] vkernElementArray = new Element[vkernElements.getLength()];
/*     */     
/* 117 */     for (int k = 0; k < vkernElementArray.length; k++) {
/* 118 */       Element vkernElement = (Element)vkernElements.item(k);
/* 119 */       vkernElementArray[k] = vkernElement;
/*     */     } 
/*     */ 
/*     */     
/* 123 */     return new SVGGVTFont(size, fontFace, glyphCodes, glyphNames, glyphLangs, glyphOrientations, glyphForms, ctx, glyphElementArray, missingGlyphElement, hkernElementArray, vkernElementArray, textElement);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGFontElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */