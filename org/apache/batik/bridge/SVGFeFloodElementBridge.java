/*    */ package org.apache.batik.bridge;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.util.Map;
/*    */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*    */ import org.apache.batik.ext.awt.image.renderable.FloodRable8Bit;
/*    */ import org.apache.batik.gvt.GraphicsNode;
/*    */ import org.w3c.dom.Element;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SVGFeFloodElementBridge
/*    */   extends AbstractSVGFilterPrimitiveElementBridge
/*    */ {
/*    */   public String getLocalName() {
/* 48 */     return "feFlood";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Filter createFilter(BridgeContext ctx, Element filterElement, Element filteredElement, GraphicsNode filteredNode, Filter inputFilter, Rectangle2D filterRegion, Map filterMap) {
/* 77 */     Rectangle2D primitiveRegion = SVGUtilities.convertFilterPrimitiveRegion(filterElement, filteredElement, filteredNode, filterRegion, filterRegion, ctx);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 85 */     Color color = CSSUtilities.convertFloodColor(filterElement, ctx);
/*    */     
/* 87 */     FloodRable8Bit floodRable8Bit = new FloodRable8Bit(primitiveRegion, color);
/*    */ 
/*    */     
/* 90 */     handleColorInterpolationFilters((Filter)floodRable8Bit, filterElement);
/*    */ 
/*    */     
/* 93 */     updateFilterMap(filterElement, (Filter)floodRable8Bit, filterMap);
/*    */     
/* 95 */     return (Filter)floodRable8Bit;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGFeFloodElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */