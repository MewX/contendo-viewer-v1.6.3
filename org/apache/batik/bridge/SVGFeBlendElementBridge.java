/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.CompositeRule;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.renderable.CompositeRable8Bit;
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
/*     */ public class SVGFeBlendElementBridge
/*     */   extends AbstractSVGFilterPrimitiveElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  53 */     return "feBlend";
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
/*  84 */     CompositeRule rule = convertMode(filterElement, ctx);
/*     */ 
/*     */     
/*  87 */     Filter in = getIn(filterElement, filteredElement, filteredNode, inputFilter, filterMap, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     if (in == null) {
/*  94 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  98 */     Filter in2 = getIn2(filterElement, filteredElement, filteredNode, inputFilter, filterMap, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     if (in2 == null) {
/* 105 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 109 */     Rectangle2D defaultRegion = (Rectangle2D)in.getBounds2D().clone();
/* 110 */     defaultRegion.add(in2.getBounds2D());
/*     */ 
/*     */     
/* 113 */     Rectangle2D primitiveRegion = SVGUtilities.convertFilterPrimitiveRegion(filterElement, filteredElement, filteredNode, defaultRegion, filterRegion, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 122 */     List<Filter> srcs = new ArrayList(2);
/* 123 */     srcs.add(in2);
/* 124 */     srcs.add(in);
/*     */     
/* 126 */     CompositeRable8Bit compositeRable8Bit = new CompositeRable8Bit(srcs, rule, true);
/*     */     
/* 128 */     handleColorInterpolationFilters((Filter)compositeRable8Bit, filterElement);
/*     */     
/* 130 */     PadRable8Bit padRable8Bit = new PadRable8Bit((Filter)compositeRable8Bit, primitiveRegion, PadMode.ZERO_PAD);
/*     */ 
/*     */     
/* 133 */     updateFilterMap(filterElement, (Filter)padRable8Bit, filterMap);
/*     */ 
/*     */     
/* 136 */     return (Filter)padRable8Bit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static CompositeRule convertMode(Element filterElement, BridgeContext ctx) {
/* 146 */     String rule = filterElement.getAttributeNS(null, "mode");
/* 147 */     if (rule.length() == 0) {
/* 148 */       return CompositeRule.OVER;
/*     */     }
/* 150 */     if ("normal".equals(rule)) {
/* 151 */       return CompositeRule.OVER;
/*     */     }
/* 153 */     if ("multiply".equals(rule)) {
/* 154 */       return CompositeRule.MULTIPLY;
/*     */     }
/* 156 */     if ("screen".equals(rule)) {
/* 157 */       return CompositeRule.SCREEN;
/*     */     }
/* 159 */     if ("darken".equals(rule)) {
/* 160 */       return CompositeRule.DARKEN;
/*     */     }
/* 162 */     if ("lighten".equals(rule)) {
/* 163 */       return CompositeRule.LIGHTEN;
/*     */     }
/* 165 */     throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { "mode", rule });
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGFeBlendElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */