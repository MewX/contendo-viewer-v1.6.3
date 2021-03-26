/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.dom.util.XLinkSupport;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.renderable.AffineRable8Bit;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.ext.awt.image.renderable.PadRable8Bit;
/*     */ import org.apache.batik.ext.awt.image.spi.ImageTagRegistry;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.w3c.dom.Document;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGFeImageElementBridge
/*     */   extends AbstractSVGFilterPrimitiveElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  54 */     return "feImage";
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter createFilter(BridgeContext ctx, Element filterElement, Element filteredElement, GraphicsNode filteredNode, Filter inputFilter, Rectangle2D filterRegion, Map filterMap) {
/*     */     short coordSystemType;
/*  84 */     String uriStr = XLinkSupport.getXLinkHref(filterElement);
/*  85 */     if (uriStr.length() == 0) {
/*  86 */       throw new BridgeException(ctx, filterElement, "attribute.missing", new Object[] { "xlink:href" });
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     Document document = filterElement.getOwnerDocument();
/* 106 */     boolean isUse = (uriStr.indexOf('#') != -1);
/* 107 */     Element contentElement = null;
/* 108 */     if (isUse) {
/* 109 */       contentElement = document.createElementNS("http://www.w3.org/2000/svg", "use");
/*     */     } else {
/*     */       
/* 112 */       contentElement = document.createElementNS("http://www.w3.org/2000/svg", "image");
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 117 */     contentElement.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", uriStr);
/*     */ 
/*     */ 
/*     */     
/* 121 */     Element proxyElement = document.createElementNS("http://www.w3.org/2000/svg", "g");
/*     */     
/* 123 */     proxyElement.appendChild(contentElement);
/*     */ 
/*     */     
/* 126 */     Rectangle2D defaultRegion = filterRegion;
/* 127 */     Element filterDefElement = (Element)filterElement.getParentNode();
/*     */     
/* 129 */     Rectangle2D primitiveRegion = SVGUtilities.getBaseFilterPrimitiveRegion(filterElement, filteredElement, filteredNode, defaultRegion, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 138 */     contentElement.setAttributeNS((String)null, "x", String.valueOf(primitiveRegion.getX()));
/* 139 */     contentElement.setAttributeNS((String)null, "y", String.valueOf(primitiveRegion.getY()));
/* 140 */     contentElement.setAttributeNS((String)null, "width", String.valueOf(primitiveRegion.getWidth()));
/* 141 */     contentElement.setAttributeNS((String)null, "height", String.valueOf(primitiveRegion.getHeight()));
/*     */ 
/*     */     
/* 144 */     GraphicsNode node = ctx.getGVTBuilder().build(ctx, proxyElement);
/* 145 */     Filter filter = node.getGraphicsNodeRable(true);
/*     */ 
/*     */ 
/*     */     
/* 149 */     String s = SVGUtilities.getChainableAttributeNS(filterDefElement, null, "primitiveUnits", ctx);
/*     */     
/* 151 */     if (s.length() == 0) {
/* 152 */       coordSystemType = 1;
/*     */     } else {
/* 154 */       coordSystemType = SVGUtilities.parseCoordinateSystem(filterDefElement, "primitiveUnits", s, ctx);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 160 */     AffineTransform at = new AffineTransform();
/* 161 */     if (coordSystemType == 2) {
/* 162 */       at = SVGUtilities.toObjectBBox(at, filteredNode);
/*     */     }
/* 164 */     AffineRable8Bit affineRable8Bit = new AffineRable8Bit(filter, at);
/*     */ 
/*     */     
/* 167 */     handleColorInterpolationFilters((Filter)affineRable8Bit, filterElement);
/*     */ 
/*     */     
/* 170 */     Rectangle2D primitiveRegionUserSpace = SVGUtilities.convertFilterPrimitiveRegion(filterElement, filteredElement, filteredNode, defaultRegion, filterRegion, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 177 */     PadRable8Bit padRable8Bit = new PadRable8Bit((Filter)affineRable8Bit, primitiveRegionUserSpace, PadMode.ZERO_PAD);
/*     */ 
/*     */ 
/*     */     
/* 181 */     updateFilterMap(filterElement, (Filter)padRable8Bit, filterMap);
/*     */     
/* 183 */     return (Filter)padRable8Bit;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static Filter createSVGFeImage(BridgeContext ctx, Rectangle2D primitiveRegion, Element refElement, boolean toBBoxNeeded, Element filterElement, GraphicsNode filteredNode) {
/* 212 */     GraphicsNode node = ctx.getGVTBuilder().build(ctx, refElement);
/* 213 */     Filter filter = node.getGraphicsNodeRable(true);
/*     */     
/* 215 */     AffineTransform at = new AffineTransform();
/*     */     
/* 217 */     if (toBBoxNeeded) {
/*     */       short coordSystemType;
/*     */       
/* 220 */       Element filterDefElement = (Element)filterElement.getParentNode();
/* 221 */       String s = SVGUtilities.getChainableAttributeNS(filterDefElement, null, "primitiveUnits", ctx);
/*     */       
/* 223 */       if (s.length() == 0) {
/* 224 */         coordSystemType = 1;
/*     */       } else {
/* 226 */         coordSystemType = SVGUtilities.parseCoordinateSystem(filterDefElement, "primitiveUnits", s, ctx);
/*     */       } 
/*     */ 
/*     */       
/* 230 */       if (coordSystemType == 2) {
/* 231 */         at = SVGUtilities.toObjectBBox(at, filteredNode);
/*     */       }
/*     */       
/* 234 */       Rectangle2D bounds = filteredNode.getGeometryBounds();
/* 235 */       at.preConcatenate(AffineTransform.getTranslateInstance(primitiveRegion.getX() - bounds.getX(), primitiveRegion.getY() - bounds.getY()));
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 243 */       at.translate(primitiveRegion.getX(), primitiveRegion.getY());
/*     */     } 
/*     */     
/* 246 */     return (Filter)new AffineRable8Bit(filter, at);
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
/*     */   protected static Filter createRasterFeImage(BridgeContext ctx, Rectangle2D primitiveRegion, ParsedURL purl) {
/* 262 */     Filter filter = ImageTagRegistry.getRegistry().readURL(purl);
/*     */     
/* 264 */     Rectangle2D bounds = filter.getBounds2D();
/* 265 */     AffineTransform scale = new AffineTransform();
/* 266 */     scale.translate(primitiveRegion.getX(), primitiveRegion.getY());
/* 267 */     scale.scale(primitiveRegion.getWidth() / (bounds.getWidth() - 1.0D), primitiveRegion.getHeight() / (bounds.getHeight() - 1.0D));
/*     */     
/* 269 */     scale.translate(-bounds.getX(), -bounds.getY());
/*     */     
/* 271 */     return (Filter)new AffineRable8Bit(filter, scale);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGFeImageElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */