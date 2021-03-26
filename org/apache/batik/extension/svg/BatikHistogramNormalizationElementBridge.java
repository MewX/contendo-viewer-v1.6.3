/*     */ package org.apache.batik.extension.svg;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.bridge.AbstractSVGFilterPrimitiveElementBridge;
/*     */ import org.apache.batik.bridge.Bridge;
/*     */ import org.apache.batik.bridge.BridgeContext;
/*     */ import org.apache.batik.bridge.BridgeException;
/*     */ import org.apache.batik.bridge.SVGUtilities;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.ext.awt.image.renderable.PadRable8Bit;
/*     */ import org.apache.batik.gvt.GraphicsNode;
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
/*     */ public class BatikHistogramNormalizationElementBridge
/*     */   extends AbstractSVGFilterPrimitiveElementBridge
/*     */   implements BatikExtConstants
/*     */ {
/*     */   public String getNamespaceURI() {
/*  54 */     return "http://xml.apache.org/batik/ext";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/*  61 */     return "histogramNormalization";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  68 */     return (Bridge)new BatikHistogramNormalizationElementBridge();
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
/*     */     Rectangle2D defaultRegion;
/*  98 */     Filter in = getIn(filterElement, filteredElement, filteredNode, inputFilter, filterMap, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     if (in == null) {
/* 105 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     Filter sourceGraphics = (Filter)filterMap.get("SourceGraphic");
/*     */     
/* 113 */     if (in == sourceGraphics) {
/* 114 */       defaultRegion = filterRegion;
/*     */     } else {
/* 116 */       defaultRegion = in.getBounds2D();
/*     */     } 
/*     */     
/* 119 */     Rectangle2D primitiveRegion = SVGUtilities.convertFilterPrimitiveRegion(filterElement, filteredElement, filteredNode, defaultRegion, filterRegion, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     float trim = 1.0F;
/* 128 */     String s = filterElement.getAttributeNS(null, "trim");
/*     */ 
/*     */     
/* 131 */     if (s.length() != 0) {
/*     */       try {
/* 133 */         trim = SVGUtilities.convertSVGNumber(s);
/* 134 */       } catch (NumberFormatException nfEx) {
/* 135 */         throw new BridgeException(ctx, filterElement, nfEx, "attribute.malformed", new Object[] { "trim", s });
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 141 */     if (trim < 0.0F) { trim = 0.0F; }
/* 142 */     else if (trim > 100.0F) { trim = 100.0F; }
/*     */     
/* 144 */     Filter filter = in;
/* 145 */     BatikHistogramNormalizationFilter8Bit batikHistogramNormalizationFilter8Bit = new BatikHistogramNormalizationFilter8Bit(filter, trim / 100.0F);
/*     */     
/* 147 */     PadRable8Bit padRable8Bit = new PadRable8Bit((Filter)batikHistogramNormalizationFilter8Bit, primitiveRegion, PadMode.ZERO_PAD);
/*     */ 
/*     */     
/* 150 */     updateFilterMap(filterElement, (Filter)padRable8Bit, filterMap);
/*     */ 
/*     */     
/* 153 */     handleColorInterpolationFilters((Filter)padRable8Bit, filterElement);
/*     */     
/* 155 */     return (Filter)padRable8Bit;
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
/*     */   protected static int convertSides(Element filterElement, String attrName, int defaultValue, BridgeContext ctx) {
/* 173 */     String s = filterElement.getAttributeNS(null, attrName);
/* 174 */     if (s.length() == 0) {
/* 175 */       return defaultValue;
/*     */     }
/* 177 */     int ret = 0;
/*     */     try {
/* 179 */       ret = SVGUtilities.convertSVGInteger(s);
/* 180 */     } catch (NumberFormatException nfEx) {
/* 181 */       throw new BridgeException(ctx, filterElement, nfEx, "attribute.malformed", new Object[] { attrName, s });
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 186 */     if (ret < 3) {
/* 187 */       throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { attrName, s });
/*     */     }
/*     */     
/* 190 */     return ret;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/BatikHistogramNormalizationElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */