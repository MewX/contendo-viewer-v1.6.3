/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.renderable.AffineRable8Bit;
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
/*     */ 
/*     */ public class SVGFeOffsetElementBridge
/*     */   extends AbstractSVGFilterPrimitiveElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  51 */     return "feOffset";
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
/*     */ 
/*     */   
/*     */   public Filter createFilter(BridgeContext ctx, Element filterElement, Element filteredElement, GraphicsNode filteredNode, Filter inputFilter, Rectangle2D filterRegion, Map filterMap) {
/*  82 */     Filter in = getIn(filterElement, filteredElement, filteredNode, inputFilter, filterMap, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     if (in == null) {
/*  89 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  95 */     Rectangle2D defaultRegion = in.getBounds2D();
/*  96 */     Rectangle2D primitiveRegion = SVGUtilities.convertFilterPrimitiveRegion(filterElement, filteredElement, filteredNode, defaultRegion, filterRegion, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     float dx = convertNumber(filterElement, "dx", 0.0F, ctx);
/* 105 */     float dy = convertNumber(filterElement, "dy", 0.0F, ctx);
/* 106 */     AffineTransform at = AffineTransform.getTranslateInstance(dx, dy);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     PadRable8Bit padRable8Bit1 = new PadRable8Bit(in, primitiveRegion, PadMode.ZERO_PAD);
/* 112 */     AffineRable8Bit affineRable8Bit = new AffineRable8Bit((Filter)padRable8Bit1, at);
/* 113 */     PadRable8Bit padRable8Bit2 = new PadRable8Bit((Filter)affineRable8Bit, primitiveRegion, PadMode.ZERO_PAD);
/*     */ 
/*     */     
/* 116 */     handleColorInterpolationFilters((Filter)padRable8Bit2, filterElement);
/*     */ 
/*     */     
/* 119 */     updateFilterMap(filterElement, (Filter)padRable8Bit2, filterMap);
/*     */     
/* 121 */     return (Filter)padRable8Bit2;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGFeOffsetElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */