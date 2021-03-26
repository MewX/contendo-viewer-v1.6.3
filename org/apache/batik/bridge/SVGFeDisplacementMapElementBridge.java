/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.ARGBChannel;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.renderable.DisplacementMapRable8Bit;
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
/*     */ 
/*     */ public class SVGFeDisplacementMapElementBridge
/*     */   extends AbstractSVGFilterPrimitiveElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  54 */     return "feDisplacementMap";
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
/*     */   public Filter createFilter(BridgeContext ctx, Element filterElement, Element filteredElement, GraphicsNode filteredNode, Filter inputFilter, Rectangle2D filterRegion, Map filterMap) {
/*  84 */     float scale = convertNumber(filterElement, "scale", 0.0F, ctx);
/*     */ 
/*     */     
/*  87 */     ARGBChannel xChannelSelector = convertChannelSelector(filterElement, "xChannelSelector", ARGBChannel.A, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     ARGBChannel yChannelSelector = convertChannelSelector(filterElement, "yChannelSelector", ARGBChannel.A, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     Filter in = getIn(filterElement, filteredElement, filteredNode, inputFilter, filterMap, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     if (in == null) {
/* 104 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 108 */     Filter in2 = getIn2(filterElement, filteredElement, filteredNode, inputFilter, filterMap, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 114 */     if (in2 == null) {
/* 115 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 119 */     Rectangle2D defaultRegion = (Rectangle2D)in.getBounds2D().clone();
/* 120 */     defaultRegion.add(in2.getBounds2D());
/*     */     
/* 122 */     Rectangle2D primitiveRegion = SVGUtilities.convertFilterPrimitiveRegion(filterElement, filteredElement, filteredNode, defaultRegion, filterRegion, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     PadRable8Bit padRable8Bit1 = new PadRable8Bit(in, primitiveRegion, PadMode.ZERO_PAD);
/*     */ 
/*     */ 
/*     */     
/* 134 */     List<PadRable8Bit> srcs = new ArrayList(2);
/* 135 */     srcs.add(padRable8Bit1);
/* 136 */     srcs.add(in2);
/* 137 */     DisplacementMapRable8Bit displacementMapRable8Bit = new DisplacementMapRable8Bit(srcs, scale, xChannelSelector, yChannelSelector);
/*     */ 
/*     */ 
/*     */     
/* 141 */     handleColorInterpolationFilters((Filter)displacementMapRable8Bit, filterElement);
/*     */     
/* 143 */     PadRable8Bit padRable8Bit2 = new PadRable8Bit((Filter)displacementMapRable8Bit, primitiveRegion, PadMode.ZERO_PAD);
/*     */ 
/*     */ 
/*     */     
/* 147 */     updateFilterMap(filterElement, (Filter)padRable8Bit2, filterMap);
/*     */     
/* 149 */     return (Filter)padRable8Bit2;
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
/*     */   protected static ARGBChannel convertChannelSelector(Element filterElement, String attrName, ARGBChannel defaultChannel, BridgeContext ctx) {
/* 166 */     String s = filterElement.getAttributeNS(null, attrName);
/* 167 */     if (s.length() == 0) {
/* 168 */       return defaultChannel;
/*     */     }
/* 170 */     if ("A".equals(s)) {
/* 171 */       return ARGBChannel.A;
/*     */     }
/* 173 */     if ("R".equals(s)) {
/* 174 */       return ARGBChannel.R;
/*     */     }
/* 176 */     if ("G".equals(s)) {
/* 177 */       return ARGBChannel.G;
/*     */     }
/* 179 */     if ("B".equals(s)) {
/* 180 */       return ARGBChannel.B;
/*     */     }
/* 182 */     throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { attrName, s });
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGFeDisplacementMapElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */