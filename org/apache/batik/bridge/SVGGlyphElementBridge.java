/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.batik.gvt.CompositeGraphicsNode;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.gvt.font.GVTFontFace;
/*     */ import org.apache.batik.gvt.font.Glyph;
/*     */ import org.apache.batik.gvt.text.TextPaintInfo;
/*     */ import org.apache.batik.parser.AWTPathProducer;
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.apache.batik.parser.PathHandler;
/*     */ import org.apache.batik.parser.PathParser;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NamedNodeMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGGlyphElementBridge
/*     */   extends AbstractSVGBridge
/*     */   implements ErrorConstants
/*     */ {
/*     */   public String getLocalName() {
/*  61 */     return "glyph";
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
/*     */   public Glyph createGlyph(BridgeContext ctx, Element glyphElement, Element textElement, int glyphCode, float fontSize, GVTFontFace fontFace, TextPaintInfo tpi) {
/*  85 */     float horizAdvX, vertAdvY, vertOriginX, vertOriginY, horizOriginX, horizOriginY, fontHeight = fontFace.getUnitsPerEm();
/*  86 */     float scale = fontSize / fontHeight;
/*  87 */     AffineTransform scaleTransform = AffineTransform.getScaleInstance(scale, -scale);
/*     */ 
/*     */ 
/*     */     
/*  91 */     String d = glyphElement.getAttributeNS((String)null, "d");
/*  92 */     Shape dShape = null;
/*  93 */     if (d.length() != 0) {
/*  94 */       AWTPathProducer app = new AWTPathProducer();
/*     */       
/*  96 */       app.setWindingRule(CSSUtilities.convertFillRule(textElement));
/*     */       try {
/*  98 */         PathParser pathParser = new PathParser();
/*  99 */         pathParser.setPathHandler((PathHandler)app);
/* 100 */         pathParser.parse(d);
/* 101 */       } catch (ParseException pEx) {
/* 102 */         throw new BridgeException(ctx, glyphElement, pEx, "attribute.malformed", new Object[] { "d" });
/*     */       
/*     */       }
/*     */       finally {
/*     */         
/* 107 */         Shape shape = app.getShape();
/* 108 */         Shape transformedShape = scaleTransform.createTransformedShape(shape);
/*     */         
/* 110 */         dShape = transformedShape;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 119 */     NodeList glyphChildren = glyphElement.getChildNodes();
/* 120 */     int numChildren = glyphChildren.getLength();
/* 121 */     int numGlyphChildren = 0;
/* 122 */     for (int i = 0; i < numChildren; i++) {
/* 123 */       Node childNode = glyphChildren.item(i);
/* 124 */       if (childNode.getNodeType() == 1) {
/* 125 */         numGlyphChildren++;
/*     */       }
/*     */     } 
/*     */     
/* 129 */     CompositeGraphicsNode glyphContentNode = null;
/*     */     
/* 131 */     if (numGlyphChildren > 0) {
/*     */ 
/*     */       
/* 134 */       GVTBuilder builder = ctx.getGVTBuilder();
/*     */       
/* 136 */       glyphContentNode = new CompositeGraphicsNode();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 142 */       Element fontElementClone = (Element)glyphElement.getParentNode().cloneNode(false);
/*     */ 
/*     */ 
/*     */       
/* 146 */       NamedNodeMap fontAttributes = glyphElement.getParentNode().getAttributes();
/*     */ 
/*     */       
/* 149 */       int numAttributes = fontAttributes.getLength();
/* 150 */       for (int j = 0; j < numAttributes; j++) {
/* 151 */         fontElementClone.setAttributeNode((Attr)fontAttributes.item(j));
/*     */       }
/* 153 */       Element clonedGlyphElement = (Element)glyphElement.cloneNode(true);
/* 154 */       fontElementClone.appendChild(clonedGlyphElement);
/*     */       
/* 156 */       textElement.appendChild(fontElementClone);
/*     */       
/* 158 */       CompositeGraphicsNode glyphChildrenNode = new CompositeGraphicsNode();
/*     */ 
/*     */       
/* 161 */       glyphChildrenNode.setTransform(scaleTransform);
/*     */       
/* 163 */       NodeList clonedGlyphChildren = clonedGlyphElement.getChildNodes();
/* 164 */       int numClonedChildren = clonedGlyphChildren.getLength();
/* 165 */       for (int k = 0; k < numClonedChildren; k++) {
/* 166 */         Node childNode = clonedGlyphChildren.item(k);
/* 167 */         if (childNode.getNodeType() == 1) {
/* 168 */           Element childElement = (Element)childNode;
/* 169 */           GraphicsNode childGraphicsNode = builder.build(ctx, childElement);
/*     */           
/* 171 */           glyphChildrenNode.add(childGraphicsNode);
/*     */         } 
/*     */       } 
/* 174 */       glyphContentNode.add(glyphChildrenNode);
/* 175 */       textElement.removeChild(fontElementClone);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     String unicode = glyphElement.getAttributeNS((String)null, "unicode");
/*     */ 
/*     */ 
/*     */     
/* 185 */     String nameList = glyphElement.getAttributeNS((String)null, "glyph-name");
/*     */     
/* 187 */     List<String> names = new ArrayList();
/* 188 */     StringTokenizer st = new StringTokenizer(nameList, " ,");
/* 189 */     while (st.hasMoreTokens()) {
/* 190 */       names.add(st.nextToken());
/*     */     }
/*     */ 
/*     */     
/* 194 */     String orientation = glyphElement.getAttributeNS((String)null, "orientation");
/*     */ 
/*     */ 
/*     */     
/* 198 */     String arabicForm = glyphElement.getAttributeNS((String)null, "arabic-form");
/*     */ 
/*     */ 
/*     */     
/* 202 */     String lang = glyphElement.getAttributeNS((String)null, "lang");
/*     */ 
/*     */     
/* 205 */     Element parentFontElement = (Element)glyphElement.getParentNode();
/*     */ 
/*     */     
/* 208 */     String s = glyphElement.getAttributeNS((String)null, "horiz-adv-x");
/* 209 */     if (s.length() == 0) {
/*     */       
/* 211 */       s = parentFontElement.getAttributeNS((String)null, "horiz-adv-x");
/* 212 */       if (s.length() == 0)
/*     */       {
/* 214 */         throw new BridgeException(ctx, parentFontElement, "attribute.missing", new Object[] { "horiz-adv-x" });
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 221 */       horizAdvX = SVGUtilities.convertSVGNumber(s) * scale;
/* 222 */     } catch (NumberFormatException nfEx) {
/* 223 */       throw new BridgeException(ctx, glyphElement, nfEx, "attribute.malformed", new Object[] { "horiz-adv-x", s });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 229 */     s = glyphElement.getAttributeNS((String)null, "vert-adv-y");
/* 230 */     if (s.length() == 0) {
/*     */       
/* 232 */       s = parentFontElement.getAttributeNS((String)null, "vert-adv-y");
/* 233 */       if (s.length() == 0)
/*     */       {
/* 235 */         s = String.valueOf(fontFace.getUnitsPerEm());
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/* 240 */       vertAdvY = SVGUtilities.convertSVGNumber(s) * scale;
/* 241 */     } catch (NumberFormatException nfEx) {
/* 242 */       throw new BridgeException(ctx, glyphElement, nfEx, "attribute.malformed", new Object[] { "vert-adv-y", s });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 248 */     s = glyphElement.getAttributeNS((String)null, "vert-origin-x");
/* 249 */     if (s.length() == 0) {
/*     */       
/* 251 */       s = parentFontElement.getAttributeNS((String)null, "vert-origin-x");
/* 252 */       if (s.length() == 0)
/*     */       {
/* 254 */         s = Float.toString(horizAdvX / 2.0F);
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/* 259 */       vertOriginX = SVGUtilities.convertSVGNumber(s) * scale;
/* 260 */     } catch (NumberFormatException nfEx) {
/* 261 */       throw new BridgeException(ctx, glyphElement, nfEx, "attribute.malformed", new Object[] { "vert-origin-x", s });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 267 */     s = glyphElement.getAttributeNS((String)null, "vert-origin-y");
/* 268 */     if (s.length() == 0) {
/*     */       
/* 270 */       s = parentFontElement.getAttributeNS((String)null, "vert-origin-y");
/* 271 */       if (s.length() == 0)
/*     */       {
/* 273 */         s = String.valueOf(fontFace.getAscent());
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/* 278 */       vertOriginY = SVGUtilities.convertSVGNumber(s) * -scale;
/* 279 */     } catch (NumberFormatException nfEx) {
/* 280 */       throw new BridgeException(ctx, glyphElement, nfEx, "attribute.malformed", new Object[] { "vert-origin-y", s });
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 285 */     Point2D vertOrigin = new Point2D.Float(vertOriginX, vertOriginY);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 291 */     s = parentFontElement.getAttributeNS((String)null, "horiz-origin-x");
/* 292 */     if (s.length() == 0)
/*     */     {
/* 294 */       s = "0";
/*     */     }
/*     */     
/*     */     try {
/* 298 */       horizOriginX = SVGUtilities.convertSVGNumber(s) * scale;
/* 299 */     } catch (NumberFormatException nfEx) {
/* 300 */       throw new BridgeException(ctx, parentFontElement, nfEx, "attribute.malformed", new Object[] { "horiz-origin-x", s });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 306 */     s = parentFontElement.getAttributeNS((String)null, "horiz-origin-y");
/* 307 */     if (s.length() == 0)
/*     */     {
/* 309 */       s = "0";
/*     */     }
/*     */     
/*     */     try {
/* 313 */       horizOriginY = SVGUtilities.convertSVGNumber(s) * -scale;
/* 314 */     } catch (NumberFormatException nfEx) {
/* 315 */       throw new BridgeException(ctx, glyphElement, nfEx, "attribute.malformed", new Object[] { "horiz-origin-y", s });
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 320 */     Point2D horizOrigin = new Point2D.Float(horizOriginX, horizOriginY);
/*     */ 
/*     */     
/* 323 */     return new Glyph(unicode, names, orientation, arabicForm, lang, horizOrigin, vertOrigin, horizAdvX, vertAdvY, glyphCode, tpi, dShape, (GraphicsNode)glyphContentNode);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGGlyphElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */