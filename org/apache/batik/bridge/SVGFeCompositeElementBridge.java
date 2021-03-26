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
/*     */ public class SVGFeCompositeElementBridge
/*     */   extends AbstractSVGFilterPrimitiveElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  53 */     return "feComposite";
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
/*  83 */     CompositeRule rule = convertOperator(filterElement, ctx);
/*     */ 
/*     */     
/*  86 */     Filter in = getIn(filterElement, filteredElement, filteredNode, inputFilter, filterMap, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     if (in == null) {
/*  93 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  97 */     Filter in2 = getIn2(filterElement, filteredElement, filteredNode, inputFilter, filterMap, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     if (in2 == null) {
/* 104 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 108 */     Rectangle2D defaultRegion = (Rectangle2D)in.getBounds2D().clone();
/* 109 */     defaultRegion.add(in2.getBounds2D());
/*     */ 
/*     */     
/* 112 */     Rectangle2D primitiveRegion = SVGUtilities.convertFilterPrimitiveRegion(filterElement, filteredElement, filteredNode, defaultRegion, filterRegion, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     List<Filter> srcs = new ArrayList(2);
/* 121 */     srcs.add(in2);
/* 122 */     srcs.add(in);
/* 123 */     CompositeRable8Bit compositeRable8Bit = new CompositeRable8Bit(srcs, rule, true);
/*     */ 
/*     */     
/* 126 */     handleColorInterpolationFilters((Filter)compositeRable8Bit, filterElement);
/*     */     
/* 128 */     PadRable8Bit padRable8Bit = new PadRable8Bit((Filter)compositeRable8Bit, primitiveRegion, PadMode.ZERO_PAD);
/*     */ 
/*     */     
/* 131 */     updateFilterMap(filterElement, (Filter)padRable8Bit, filterMap);
/*     */     
/* 133 */     return (Filter)padRable8Bit;
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
/*     */   protected static CompositeRule convertOperator(Element filterElement, BridgeContext ctx) {
/* 145 */     String s = filterElement.getAttributeNS(null, "operator");
/* 146 */     if (s.length() == 0) {
/* 147 */       return CompositeRule.OVER;
/*     */     }
/* 149 */     if ("atop".equals(s)) {
/* 150 */       return CompositeRule.ATOP;
/*     */     }
/* 152 */     if ("in".equals(s)) {
/* 153 */       return CompositeRule.IN;
/*     */     }
/* 155 */     if ("over".equals(s)) {
/* 156 */       return CompositeRule.OVER;
/*     */     }
/* 158 */     if ("out".equals(s)) {
/* 159 */       return CompositeRule.OUT;
/*     */     }
/* 161 */     if ("xor".equals(s)) {
/* 162 */       return CompositeRule.XOR;
/*     */     }
/* 164 */     if ("arithmetic".equals(s)) {
/* 165 */       float k1 = convertNumber(filterElement, "k1", 0.0F, ctx);
/* 166 */       float k2 = convertNumber(filterElement, "k2", 0.0F, ctx);
/* 167 */       float k3 = convertNumber(filterElement, "k3", 0.0F, ctx);
/* 168 */       float k4 = convertNumber(filterElement, "k4", 0.0F, ctx);
/* 169 */       return CompositeRule.ARITHMETIC(k1, k2, k3, k4);
/*     */     } 
/* 171 */     throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { "operator", s });
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGFeCompositeElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */