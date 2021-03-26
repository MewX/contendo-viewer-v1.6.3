/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import org.apache.batik.dom.util.XLinkSupport;
/*     */ import org.apache.batik.gvt.text.TextPath;
/*     */ import org.apache.batik.parser.AWTPathProducer;
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.apache.batik.parser.PathHandler;
/*     */ import org.apache.batik.parser.PathParser;
/*     */ import org.apache.batik.parser.UnitProcessor;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGTextPathElementBridge
/*     */   extends AnimatableGenericSVGBridge
/*     */   implements ErrorConstants
/*     */ {
/*     */   public String getLocalName() {
/*  50 */     return "textPath";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleElement(BridgeContext ctx, Element e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TextPath createTextPath(BridgeContext ctx, Element textPathElement) {
/*  69 */     String uri = XLinkSupport.getXLinkHref(textPathElement);
/*  70 */     Element pathElement = ctx.getReferencedElement(textPathElement, uri);
/*     */     
/*  72 */     if (pathElement == null || !"http://www.w3.org/2000/svg".equals(pathElement.getNamespaceURI()) || !pathElement.getLocalName().equals("path"))
/*     */     {
/*     */ 
/*     */ 
/*     */       
/*  77 */       throw new BridgeException(ctx, textPathElement, "uri.badTarget", new Object[] { uri });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  82 */     String s = pathElement.getAttributeNS((String)null, "d");
/*  83 */     Shape pathShape = null;
/*  84 */     if (s.length() != 0) {
/*  85 */       AWTPathProducer app = new AWTPathProducer();
/*  86 */       app.setWindingRule(CSSUtilities.convertFillRule(pathElement));
/*     */       try {
/*  88 */         PathParser pathParser = new PathParser();
/*  89 */         pathParser.setPathHandler((PathHandler)app);
/*  90 */         pathParser.parse(s);
/*  91 */       } catch (ParseException pEx) {
/*  92 */         throw new BridgeException(ctx, pathElement, pEx, "attribute.malformed", new Object[] { "d" });
/*     */       }
/*     */       finally {
/*     */         
/*  96 */         pathShape = app.getShape();
/*     */       } 
/*     */     } else {
/*  99 */       throw new BridgeException(ctx, pathElement, "attribute.missing", new Object[] { "d" });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     s = pathElement.getAttributeNS((String)null, "transform");
/* 106 */     if (s.length() != 0) {
/* 107 */       AffineTransform tr = SVGUtilities.convertTransform(pathElement, "transform", s, ctx);
/*     */ 
/*     */       
/* 110 */       pathShape = tr.createTransformedShape(pathShape);
/*     */     } 
/*     */ 
/*     */     
/* 114 */     TextPath textPath = new TextPath(new GeneralPath(pathShape));
/*     */ 
/*     */     
/* 117 */     s = textPathElement.getAttributeNS((String)null, "startOffset");
/* 118 */     if (s.length() > 0) {
/* 119 */       float startOffset = 0.0F;
/* 120 */       int percentIndex = s.indexOf('%');
/* 121 */       if (percentIndex != -1) {
/*     */         
/* 123 */         float pathLength = textPath.lengthOfPath();
/* 124 */         String percentString = s.substring(0, percentIndex);
/* 125 */         float startOffsetPercent = 0.0F;
/*     */         try {
/* 127 */           startOffsetPercent = SVGUtilities.convertSVGNumber(percentString);
/* 128 */         } catch (NumberFormatException e) {
/* 129 */           throw new BridgeException(ctx, textPathElement, "attribute.malformed", new Object[] { "startOffset", s });
/*     */         } 
/*     */ 
/*     */         
/* 133 */         startOffset = (float)((startOffsetPercent * pathLength) / 100.0D);
/*     */       }
/*     */       else {
/*     */         
/* 137 */         UnitProcessor.Context uctx = UnitProcessor.createContext(ctx, textPathElement);
/* 138 */         startOffset = UnitProcessor.svgOtherLengthToUserSpace(s, "startOffset", uctx);
/*     */       } 
/* 140 */       textPath.setStartOffset(startOffset);
/*     */     } 
/*     */     
/* 143 */     return textPath;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGTextPathElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */