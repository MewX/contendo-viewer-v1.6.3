/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import org.apache.batik.anim.dom.SVGOMDocument;
/*     */ import org.apache.batik.dom.AbstractNode;
/*     */ import org.apache.batik.dom.util.XLinkSupport;
/*     */ import org.apache.batik.gvt.font.Glyph;
/*     */ import org.apache.batik.gvt.text.GVTAttributedCharacterIterator;
/*     */ import org.apache.batik.gvt.text.TextPaintInfo;
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
/*     */ public class SVGAltGlyphElementBridge
/*     */   extends AbstractSVGBridge
/*     */   implements ErrorConstants
/*     */ {
/*  43 */   public static final AttributedCharacterIterator.Attribute PAINT_INFO = (AttributedCharacterIterator.Attribute)GVTAttributedCharacterIterator.TextAttribute.PAINT_INFO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/*  56 */     return "altGlyph";
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
/*     */   public Glyph[] createAltGlyphArray(BridgeContext ctx, Element altGlyphElement, float fontSize, AttributedCharacterIterator aci) {
/*  77 */     String uri = XLinkSupport.getXLinkHref(altGlyphElement);
/*     */     
/*  79 */     Element refElement = null;
/*     */     
/*     */     try {
/*  82 */       refElement = ctx.getReferencedElement(altGlyphElement, uri);
/*  83 */     } catch (BridgeException e) {
/*  84 */       if ("uri.unsecure".equals(e.getCode())) {
/*  85 */         ctx.getUserAgent().displayError(e);
/*     */       }
/*     */     } 
/*     */     
/*  89 */     if (refElement == null)
/*     */     {
/*  91 */       return null;
/*     */     }
/*  93 */     if (!"http://www.w3.org/2000/svg".equals(refElement.getNamespaceURI())) {
/*  94 */       return null;
/*     */     }
/*     */     
/*  97 */     if (refElement.getLocalName().equals("glyph")) {
/*     */       
/*  99 */       Glyph glyph = getGlyph(ctx, uri, altGlyphElement, fontSize, aci);
/*     */       
/* 101 */       if (glyph == null)
/*     */       {
/* 103 */         return null;
/*     */       }
/*     */       
/* 106 */       Glyph[] glyphArray = new Glyph[1];
/* 107 */       glyphArray[0] = glyph;
/* 108 */       return glyphArray;
/*     */     } 
/*     */ 
/*     */     
/* 112 */     if (refElement.getLocalName().equals("altGlyphDef")) {
/*     */ 
/*     */ 
/*     */       
/* 116 */       SVGOMDocument document = (SVGOMDocument)altGlyphElement.getOwnerDocument();
/*     */       
/* 118 */       SVGOMDocument refDocument = (SVGOMDocument)refElement.getOwnerDocument();
/*     */       
/* 120 */       boolean isLocal = (refDocument == document);
/*     */       
/* 122 */       Element localRefElement = isLocal ? refElement : (Element)document.importNode(refElement, true);
/*     */       
/* 124 */       if (!isLocal) {
/*     */ 
/*     */         
/* 127 */         String base = AbstractNode.getBaseURI(altGlyphElement);
/* 128 */         Element g = document.createElementNS("http://www.w3.org/2000/svg", "g");
/* 129 */         g.appendChild(localRefElement);
/* 130 */         g.setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:base", base);
/*     */ 
/*     */         
/* 133 */         CSSUtilities.computeStyleAndURIs(refElement, localRefElement, uri);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 139 */       NodeList altGlyphDefChildren = localRefElement.getChildNodes();
/* 140 */       boolean containsGlyphRefNodes = false;
/* 141 */       int numAltGlyphDefChildren = altGlyphDefChildren.getLength();
/* 142 */       for (int i = 0; i < numAltGlyphDefChildren; i++) {
/* 143 */         Node altGlyphChild = altGlyphDefChildren.item(i);
/* 144 */         if (altGlyphChild.getNodeType() == 1) {
/* 145 */           Element agc = (Element)altGlyphChild;
/* 146 */           if ("http://www.w3.org/2000/svg".equals(agc.getNamespaceURI()) && "glyphRef".equals(agc.getLocalName())) {
/*     */             
/* 148 */             containsGlyphRefNodes = true;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 153 */       if (containsGlyphRefNodes) {
/*     */         
/* 155 */         NodeList glyphRefNodes = localRefElement.getElementsByTagNameNS("http://www.w3.org/2000/svg", "glyphRef");
/*     */ 
/*     */         
/* 158 */         int numGlyphRefNodes = glyphRefNodes.getLength();
/* 159 */         Glyph[] glyphArray = new Glyph[numGlyphRefNodes];
/* 160 */         for (int j = 0; j < numGlyphRefNodes; j++) {
/*     */           
/* 162 */           Element glyphRefElement = (Element)glyphRefNodes.item(j);
/* 163 */           String glyphUri = XLinkSupport.getXLinkHref(glyphRefElement);
/*     */           
/* 165 */           Glyph glyph = getGlyph(ctx, glyphUri, glyphRefElement, fontSize, aci);
/*     */           
/* 167 */           if (glyph == null)
/*     */           {
/* 169 */             return null;
/*     */           }
/* 171 */           glyphArray[j] = glyph;
/*     */         } 
/* 173 */         return glyphArray;
/*     */       } 
/*     */ 
/*     */       
/* 177 */       NodeList altGlyphItemNodes = localRefElement.getElementsByTagNameNS("http://www.w3.org/2000/svg", "altGlyphItem");
/*     */ 
/*     */       
/* 180 */       int numAltGlyphItemNodes = altGlyphItemNodes.getLength();
/* 181 */       if (numAltGlyphItemNodes > 0) {
/* 182 */         boolean foundMatchingGlyph = false;
/* 183 */         Glyph[] glyphArray = null;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 188 */         for (int j = 0; j < numAltGlyphItemNodes && !foundMatchingGlyph; j++) {
/*     */ 
/*     */           
/* 191 */           Element altGlyphItemElement = (Element)altGlyphItemNodes.item(j);
/* 192 */           NodeList altGlyphRefNodes = altGlyphItemElement.getElementsByTagNameNS("http://www.w3.org/2000/svg", "glyphRef");
/*     */ 
/*     */           
/* 195 */           int numAltGlyphRefNodes = altGlyphRefNodes.getLength();
/*     */           
/* 197 */           glyphArray = new Glyph[numAltGlyphRefNodes];
/*     */ 
/*     */ 
/*     */           
/* 201 */           foundMatchingGlyph = true;
/*     */           
/* 203 */           for (int k = 0; k < numAltGlyphRefNodes; k++) {
/*     */             
/* 205 */             Element glyphRefElement = (Element)altGlyphRefNodes.item(k);
/* 206 */             String glyphUri = XLinkSupport.getXLinkHref(glyphRefElement);
/*     */             
/* 208 */             Glyph glyph = getGlyph(ctx, glyphUri, glyphRefElement, fontSize, aci);
/* 209 */             if (glyph != null) {
/*     */               
/* 211 */               glyphArray[k] = glyph;
/*     */             
/*     */             }
/*     */             else {
/*     */               
/* 216 */               foundMatchingGlyph = false;
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/* 221 */         if (!foundMatchingGlyph)
/*     */         {
/*     */ 
/*     */           
/* 225 */           return null;
/*     */         }
/*     */         
/* 228 */         return glyphArray;
/*     */       } 
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
/* 240 */     return null;
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
/*     */   private Glyph getGlyph(BridgeContext ctx, String glyphUri, Element altGlyphElement, float fontSize, AttributedCharacterIterator aci) {
/* 260 */     Element refGlyphElement = null;
/*     */     try {
/* 262 */       refGlyphElement = ctx.getReferencedElement(altGlyphElement, glyphUri);
/*     */     }
/* 264 */     catch (BridgeException e) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 269 */       if ("uri.unsecure".equals(e.getCode())) {
/* 270 */         ctx.getUserAgent().displayError(e);
/*     */       }
/*     */     } 
/*     */     
/* 274 */     if (refGlyphElement == null || !"http://www.w3.org/2000/svg".equals(refGlyphElement.getNamespaceURI()) || !"glyph".equals(refGlyphElement.getLocalName()))
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 279 */       return null;
/*     */     }
/*     */     
/* 282 */     SVGOMDocument document = (SVGOMDocument)altGlyphElement.getOwnerDocument();
/*     */     
/* 284 */     SVGOMDocument refDocument = (SVGOMDocument)refGlyphElement.getOwnerDocument();
/*     */     
/* 286 */     boolean isLocal = (refDocument == document);
/*     */ 
/*     */     
/* 289 */     Element localGlyphElement = null;
/* 290 */     Element localFontFaceElement = null;
/* 291 */     Element localFontElement = null;
/* 292 */     if (isLocal) {
/* 293 */       localGlyphElement = refGlyphElement;
/* 294 */       localFontElement = (Element)localGlyphElement.getParentNode();
/* 295 */       NodeList fontFaceElements = localFontElement.getElementsByTagNameNS("http://www.w3.org/2000/svg", "font-face");
/*     */ 
/*     */       
/* 298 */       if (fontFaceElements.getLength() > 0) {
/* 299 */         localFontFaceElement = (Element)fontFaceElements.item(0);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 304 */       localFontElement = (Element)document.importNode(refGlyphElement.getParentNode(), true);
/*     */       
/* 306 */       String base = AbstractNode.getBaseURI(altGlyphElement);
/* 307 */       Element g = document.createElementNS("http://www.w3.org/2000/svg", "g");
/* 308 */       g.appendChild(localFontElement);
/* 309 */       g.setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:base", base);
/*     */ 
/*     */       
/* 312 */       CSSUtilities.computeStyleAndURIs((Element)refGlyphElement.getParentNode(), localFontElement, glyphUri);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 317 */       String glyphId = refGlyphElement.getAttributeNS((String)null, "id");
/*     */       
/* 319 */       NodeList glyphElements = localFontElement.getElementsByTagNameNS("http://www.w3.org/2000/svg", "glyph");
/*     */       
/* 321 */       for (int i = 0; i < glyphElements.getLength(); i++) {
/* 322 */         Element glyphElem = (Element)glyphElements.item(i);
/* 323 */         if (glyphElem.getAttributeNS((String)null, "id").equals(glyphId)) {
/* 324 */           localGlyphElement = glyphElem;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 329 */       NodeList fontFaceElements = localFontElement.getElementsByTagNameNS("http://www.w3.org/2000/svg", "font-face");
/*     */ 
/*     */       
/* 332 */       if (fontFaceElements.getLength() > 0) {
/* 333 */         localFontFaceElement = (Element)fontFaceElements.item(0);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 338 */     if (localGlyphElement == null || localFontFaceElement == null) {
/* 339 */       return null;
/*     */     }
/*     */     
/* 342 */     SVGFontFaceElementBridge fontFaceBridge = (SVGFontFaceElementBridge)ctx.getBridge(localFontFaceElement);
/*     */     
/* 344 */     SVGFontFace fontFace = fontFaceBridge.createFontFace(ctx, localFontFaceElement);
/*     */     
/* 346 */     SVGGlyphElementBridge glyphBridge = (SVGGlyphElementBridge)ctx.getBridge(localGlyphElement);
/*     */ 
/*     */     
/* 349 */     aci.first();
/* 350 */     TextPaintInfo tpi = (TextPaintInfo)aci.getAttribute(PAINT_INFO);
/*     */     
/* 352 */     return glyphBridge.createGlyph(ctx, localGlyphElement, altGlyphElement, -1, fontSize, fontFace, tpi);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGAltGlyphElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */