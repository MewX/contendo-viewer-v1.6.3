/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.apache.batik.dom.AbstractNode;
/*     */ import org.apache.batik.dom.util.XLinkSupport;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGFontFaceElementBridge
/*     */   extends AbstractSVGBridge
/*     */   implements ErrorConstants
/*     */ {
/*     */   public String getLocalName() {
/*  50 */     return "font-face";
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
/*     */   public SVGFontFace createFontFace(BridgeContext ctx, Element fontFaceElement) {
/*     */     float unitsPerEm, slope, ascent, descent, underlinePos, underlineThickness, strikethroughPos, strikethroughThickness, overlinePos, overlineThickness;
/*  67 */     String familyNames = fontFaceElement.getAttributeNS((String)null, "font-family");
/*     */ 
/*     */ 
/*     */     
/*  71 */     String unitsPerEmStr = fontFaceElement.getAttributeNS((String)null, "units-per-em");
/*     */     
/*  73 */     if (unitsPerEmStr.length() == 0) {
/*  74 */       unitsPerEmStr = "1000";
/*     */     }
/*     */     
/*     */     try {
/*  78 */       unitsPerEm = SVGUtilities.convertSVGNumber(unitsPerEmStr);
/*  79 */     } catch (NumberFormatException nfEx) {
/*  80 */       throw new BridgeException(ctx, fontFaceElement, nfEx, "attribute.malformed", new Object[] { "units-per-em", unitsPerEmStr });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     String fontWeight = fontFaceElement.getAttributeNS((String)null, "font-weight");
/*     */     
/*  88 */     if (fontWeight.length() == 0) {
/*  89 */       fontWeight = "all";
/*     */     }
/*     */ 
/*     */     
/*  93 */     String fontStyle = fontFaceElement.getAttributeNS((String)null, "font-style");
/*     */     
/*  95 */     if (fontStyle.length() == 0) {
/*  96 */       fontStyle = "all";
/*     */     }
/*     */ 
/*     */     
/* 100 */     String fontVariant = fontFaceElement.getAttributeNS((String)null, "font-variant");
/*     */     
/* 102 */     if (fontVariant.length() == 0) {
/* 103 */       fontVariant = "normal";
/*     */     }
/*     */ 
/*     */     
/* 107 */     String fontStretch = fontFaceElement.getAttributeNS((String)null, "font-stretch");
/*     */     
/* 109 */     if (fontStretch.length() == 0) {
/* 110 */       fontStretch = "normal";
/*     */     }
/*     */ 
/*     */     
/* 114 */     String slopeStr = fontFaceElement.getAttributeNS((String)null, "slope");
/*     */     
/* 116 */     if (slopeStr.length() == 0) {
/* 117 */       slopeStr = "0";
/*     */     }
/*     */     
/*     */     try {
/* 121 */       slope = SVGUtilities.convertSVGNumber(slopeStr);
/* 122 */     } catch (NumberFormatException nfEx) {
/* 123 */       throw new BridgeException(ctx, fontFaceElement, nfEx, "attribute.malformed", new Object[] { "0", slopeStr });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     String panose1 = fontFaceElement.getAttributeNS((String)null, "panose-1");
/*     */     
/* 131 */     if (panose1.length() == 0) {
/* 132 */       panose1 = "0 0 0 0 0 0 0 0 0 0";
/*     */     }
/*     */ 
/*     */     
/* 136 */     String ascentStr = fontFaceElement.getAttributeNS((String)null, "ascent");
/*     */     
/* 138 */     if (ascentStr.length() == 0)
/*     */     {
/* 140 */       ascentStr = String.valueOf(unitsPerEm * 0.8D);
/*     */     }
/*     */     
/*     */     try {
/* 144 */       ascent = SVGUtilities.convertSVGNumber(ascentStr);
/* 145 */     } catch (NumberFormatException nfEx) {
/* 146 */       throw new BridgeException(ctx, fontFaceElement, nfEx, "attribute.malformed", new Object[] { "0", ascentStr });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 152 */     String descentStr = fontFaceElement.getAttributeNS((String)null, "descent");
/*     */     
/* 154 */     if (descentStr.length() == 0)
/*     */     {
/* 156 */       descentStr = String.valueOf(unitsPerEm * 0.2D);
/*     */     }
/*     */     
/*     */     try {
/* 160 */       descent = SVGUtilities.convertSVGNumber(descentStr);
/* 161 */     } catch (NumberFormatException nfEx) {
/* 162 */       throw new BridgeException(ctx, fontFaceElement, nfEx, "attribute.malformed", new Object[] { "0", descentStr });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 168 */     String underlinePosStr = fontFaceElement.getAttributeNS((String)null, "underline-position");
/*     */     
/* 170 */     if (underlinePosStr.length() == 0) {
/* 171 */       underlinePosStr = String.valueOf(-3.0F * unitsPerEm / 40.0F);
/*     */     }
/*     */     
/*     */     try {
/* 175 */       underlinePos = SVGUtilities.convertSVGNumber(underlinePosStr);
/* 176 */     } catch (NumberFormatException nfEx) {
/* 177 */       throw new BridgeException(ctx, fontFaceElement, nfEx, "attribute.malformed", new Object[] { "0", underlinePosStr });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 185 */     String underlineThicknessStr = fontFaceElement.getAttributeNS((String)null, "underline-thickness");
/*     */     
/* 187 */     if (underlineThicknessStr.length() == 0) {
/* 188 */       underlineThicknessStr = String.valueOf(unitsPerEm / 20.0F);
/*     */     }
/*     */     
/*     */     try {
/* 192 */       underlineThickness = SVGUtilities.convertSVGNumber(underlineThicknessStr);
/*     */     }
/* 194 */     catch (NumberFormatException nfEx) {
/* 195 */       throw new BridgeException(ctx, fontFaceElement, nfEx, "attribute.malformed", new Object[] { "0", underlineThicknessStr });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 203 */     String strikethroughPosStr = fontFaceElement.getAttributeNS((String)null, "strikethrough-position");
/*     */     
/* 205 */     if (strikethroughPosStr.length() == 0) {
/* 206 */       strikethroughPosStr = String.valueOf(3.0F * ascent / 8.0F);
/*     */     }
/*     */     
/*     */     try {
/* 210 */       strikethroughPos = SVGUtilities.convertSVGNumber(strikethroughPosStr);
/*     */     }
/* 212 */     catch (NumberFormatException nfEx) {
/* 213 */       throw new BridgeException(ctx, fontFaceElement, nfEx, "attribute.malformed", new Object[] { "0", strikethroughPosStr });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 221 */     String strikethroughThicknessStr = fontFaceElement.getAttributeNS((String)null, "strikethrough-thickness");
/*     */     
/* 223 */     if (strikethroughThicknessStr.length() == 0) {
/* 224 */       strikethroughThicknessStr = String.valueOf(unitsPerEm / 20.0F);
/*     */     }
/*     */     
/*     */     try {
/* 228 */       strikethroughThickness = SVGUtilities.convertSVGNumber(strikethroughThicknessStr);
/*     */     }
/* 230 */     catch (NumberFormatException nfEx) {
/* 231 */       throw new BridgeException(ctx, fontFaceElement, nfEx, "attribute.malformed", new Object[] { "0", strikethroughThicknessStr });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 238 */     String overlinePosStr = fontFaceElement.getAttributeNS((String)null, "overline-position");
/*     */     
/* 240 */     if (overlinePosStr.length() == 0) {
/* 241 */       overlinePosStr = String.valueOf(ascent);
/*     */     }
/*     */     
/*     */     try {
/* 245 */       overlinePos = SVGUtilities.convertSVGNumber(overlinePosStr);
/* 246 */     } catch (NumberFormatException nfEx) {
/* 247 */       throw new BridgeException(ctx, fontFaceElement, nfEx, "attribute.malformed", new Object[] { "0", overlinePosStr });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 255 */     String overlineThicknessStr = fontFaceElement.getAttributeNS((String)null, "overline-thickness");
/*     */     
/* 257 */     if (overlineThicknessStr.length() == 0) {
/* 258 */       overlineThicknessStr = String.valueOf(unitsPerEm / 20.0F);
/*     */     }
/*     */     
/*     */     try {
/* 262 */       overlineThickness = SVGUtilities.convertSVGNumber(overlineThicknessStr);
/*     */     }
/* 264 */     catch (NumberFormatException nfEx) {
/* 265 */       throw new BridgeException(ctx, fontFaceElement, nfEx, "attribute.malformed", new Object[] { "0", overlineThicknessStr });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 271 */     List srcs = null;
/* 272 */     Element fontElt = SVGUtilities.getParentElement(fontFaceElement);
/* 273 */     if (!fontElt.getNamespaceURI().equals("http://www.w3.org/2000/svg") || !fontElt.getLocalName().equals("font"))
/*     */     {
/* 275 */       srcs = getFontFaceSrcs(fontFaceElement);
/*     */     }
/*     */ 
/*     */     
/* 279 */     return new SVGFontFace(fontFaceElement, srcs, familyNames, unitsPerEm, fontWeight, fontStyle, fontVariant, fontStretch, slope, panose1, ascent, descent, strikethroughPos, strikethroughThickness, underlinePos, underlineThickness, overlinePos, overlineThickness);
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
/*     */   public List getFontFaceSrcs(Element fontFaceElement) {
/* 293 */     Element ffsrc = null;
/* 294 */     Node n = fontFaceElement.getFirstChild();
/* 295 */     for (; n != null; 
/* 296 */       n = n.getNextSibling()) {
/* 297 */       if (n.getNodeType() == 1 && n.getNamespaceURI().equals("http://www.w3.org/2000/svg") && n.getLocalName().equals("font-face-src")) {
/*     */ 
/*     */         
/* 300 */         ffsrc = (Element)n;
/*     */         break;
/*     */       } 
/*     */     } 
/* 304 */     if (ffsrc == null) {
/* 305 */       return null;
/*     */     }
/* 307 */     List<ParsedURL> ret = new LinkedList();
/*     */ 
/*     */     
/* 310 */     Node node1 = ffsrc.getFirstChild();
/* 311 */     for (; node1 != null; 
/* 312 */       node1 = node1.getNextSibling()) {
/* 313 */       if (node1.getNodeType() == 1 && node1.getNamespaceURI().equals("http://www.w3.org/2000/svg"))
/*     */       {
/*     */ 
/*     */         
/* 317 */         if (node1.getLocalName().equals("font-face-uri")) {
/* 318 */           ParsedURL purl; Element ffuri = (Element)node1;
/* 319 */           String uri = XLinkSupport.getXLinkHref(ffuri);
/* 320 */           String base = AbstractNode.getBaseURI(ffuri);
/*     */           
/* 322 */           if (base != null) { purl = new ParsedURL(base, uri); }
/* 323 */           else { purl = new ParsedURL(uri); }
/* 324 */            ret.add(purl);
/*     */         
/*     */         }
/* 327 */         else if (node1.getLocalName().equals("font-face-name")) {
/* 328 */           Element ffname = (Element)node1;
/* 329 */           String s = ffname.getAttribute("name");
/* 330 */           if (s.length() != 0)
/* 331 */             ret.add(s); 
/*     */         }  } 
/*     */     } 
/* 334 */     return ret;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGFontFaceElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */