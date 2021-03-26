/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.apache.batik.dom.AbstractNode;
/*     */ import org.apache.batik.gvt.font.GVTFontFace;
/*     */ import org.apache.batik.gvt.font.GVTFontFamily;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGDocument;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class FontFace
/*     */   extends GVTFontFace
/*     */   implements ErrorConstants
/*     */ {
/*     */   List srcs;
/*     */   
/*     */   public FontFace(List srcs, String familyName, float unitsPerEm, String fontWeight, String fontStyle, String fontVariant, String fontStretch, float slope, String panose1, float ascent, float descent, float strikethroughPosition, float strikethroughThickness, float underlinePosition, float underlineThickness, float overlinePosition, float overlineThickness) {
/*  60 */     super(familyName, unitsPerEm, fontWeight, fontStyle, fontVariant, fontStretch, slope, panose1, ascent, descent, strikethroughPosition, strikethroughThickness, underlinePosition, underlineThickness, overlinePosition, overlineThickness);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  66 */     this.srcs = srcs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected FontFace(String familyName) {
/*  73 */     super(familyName);
/*     */   }
/*     */ 
/*     */   
/*     */   public static CSSFontFace createFontFace(String familyName, FontFace src) {
/*  78 */     return new CSSFontFace(new LinkedList(src.srcs), familyName, src.unitsPerEm, src.fontWeight, src.fontStyle, src.fontVariant, src.fontStretch, src.slope, src.panose1, src.ascent, src.descent, src.strikethroughPosition, src.strikethroughThickness, src.underlinePosition, src.underlineThickness, src.overlinePosition, src.overlineThickness);
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
/*     */   public GVTFontFamily getFontFamily(BridgeContext ctx) {
/*  92 */     FontFamilyResolver fontFamilyResolver = ctx.getFontFamilyResolver();
/*  93 */     GVTFontFamily family = fontFamilyResolver.resolve(this.familyName, this);
/*  94 */     if (family != null) {
/*  95 */       return family;
/*     */     }
/*     */     
/*  98 */     for (Object o : this.srcs) {
/*  99 */       if (o instanceof String) {
/* 100 */         family = fontFamilyResolver.resolve((String)o, this);
/* 101 */         if (family != null)
/* 102 */           return family;  continue;
/*     */       } 
/* 104 */       if (o instanceof ParsedURL) {
/*     */         try {
/* 106 */           GVTFontFamily ff = getFontFamily(ctx, (ParsedURL)o);
/* 107 */           if (ff != null)
/* 108 */             return ff; 
/* 109 */         } catch (SecurityException ex) {
/*     */           
/* 111 */           ctx.getUserAgent().displayError(ex);
/* 112 */         } catch (BridgeException ex) {
/*     */ 
/*     */           
/* 115 */           if ("uri.unsecure".equals(ex.getCode()))
/* 116 */             ctx.getUserAgent().displayError(ex); 
/* 117 */         } catch (Exception exception) {}
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 123 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected GVTFontFamily getFontFamily(BridgeContext ctx, ParsedURL purl) {
/* 131 */     String purlStr = purl.toString();
/*     */     
/* 133 */     Element e = getBaseElement(ctx);
/* 134 */     SVGDocument svgDoc = (SVGDocument)e.getOwnerDocument();
/* 135 */     String docURL = svgDoc.getURL();
/* 136 */     ParsedURL pDocURL = null;
/* 137 */     if (docURL != null) {
/* 138 */       pDocURL = new ParsedURL(docURL);
/*     */     }
/*     */     
/* 141 */     String baseURI = AbstractNode.getBaseURI(e);
/* 142 */     purl = new ParsedURL(baseURI, purlStr);
/* 143 */     UserAgent userAgent = ctx.getUserAgent();
/*     */     
/*     */     try {
/* 146 */       userAgent.checkLoadExternalResource(purl, pDocURL);
/* 147 */     } catch (SecurityException ex) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 153 */       userAgent.displayError(ex);
/* 154 */       return null;
/*     */     } 
/*     */     
/* 157 */     if (purl.getRef() != null) {
/*     */       
/* 159 */       Element ref = ctx.getReferencedElement(e, purlStr);
/* 160 */       if (!ref.getNamespaceURI().equals("http://www.w3.org/2000/svg") || !ref.getLocalName().equals("font"))
/*     */       {
/* 162 */         return null;
/*     */       }
/*     */       
/* 165 */       SVGDocument doc = (SVGDocument)e.getOwnerDocument();
/* 166 */       SVGDocument rdoc = (SVGDocument)ref.getOwnerDocument();
/*     */       
/* 168 */       Element fontElt = ref;
/* 169 */       if (doc != rdoc) {
/* 170 */         fontElt = (Element)doc.importNode(ref, true);
/* 171 */         String base = AbstractNode.getBaseURI(ref);
/* 172 */         Element g = doc.createElementNS("http://www.w3.org/2000/svg", "g");
/* 173 */         g.appendChild(fontElt);
/* 174 */         g.setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:base", base);
/*     */         
/* 176 */         CSSUtilities.computeStyleAndURIs(ref, fontElt, purlStr);
/*     */       } 
/*     */ 
/*     */       
/* 180 */       Element fontFaceElt = null;
/* 181 */       Node n = fontElt.getFirstChild();
/* 182 */       for (; n != null; 
/* 183 */         n = n.getNextSibling()) {
/* 184 */         if (n.getNodeType() == 1 && n.getNamespaceURI().equals("http://www.w3.org/2000/svg") && n.getLocalName().equals("font-face")) {
/*     */ 
/*     */           
/* 187 */           fontFaceElt = (Element)n;
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       
/* 194 */       SVGFontFaceElementBridge fontFaceBridge = (SVGFontFaceElementBridge)ctx.getBridge("http://www.w3.org/2000/svg", "font-face");
/*     */       
/* 196 */       GVTFontFace gff = fontFaceBridge.createFontFace(ctx, fontFaceElt);
/*     */ 
/*     */       
/* 199 */       return new SVGFontFamily(gff, fontElt, ctx);
/*     */     } 
/*     */     
/*     */     try {
/* 203 */       return ctx.getFontFamilyResolver().loadFont(purl.openStream(), this);
/* 204 */     } catch (Exception exception) {
/*     */       
/* 206 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Element getBaseElement(BridgeContext ctx) {
/* 214 */     SVGDocument d = (SVGDocument)ctx.getDocument();
/* 215 */     return (Element)d.getRootElement();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/FontFace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */