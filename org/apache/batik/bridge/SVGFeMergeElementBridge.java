/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.CompositeRule;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.renderable.CompositeRable8Bit;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.ext.awt.image.renderable.PadRable8Bit;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGFeMergeElementBridge
/*     */   extends AbstractSVGFilterPrimitiveElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  54 */     return "feMerge";
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
/*     */   public Filter createFilter(BridgeContext ctx, Element filterElement, Element filteredElement, GraphicsNode filteredNode, Filter inputFilter, Rectangle2D filterRegion, Map filterMap) {
/*  82 */     List srcs = extractFeMergeNode(filterElement, filteredElement, filteredNode, inputFilter, filterMap, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  89 */     if (srcs == null) {
/*  90 */       return null;
/*     */     }
/*     */     
/*  93 */     if (srcs.size() == 0) {
/*  94 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  98 */     Iterator<Filter> iter = srcs.iterator();
/*  99 */     Rectangle2D defaultRegion = (Rectangle2D)((Filter)iter.next()).getBounds2D().clone();
/*     */ 
/*     */     
/* 102 */     while (iter.hasNext()) {
/* 103 */       defaultRegion.add(((Filter)iter.next()).getBounds2D());
/*     */     }
/*     */ 
/*     */     
/* 107 */     Rectangle2D primitiveRegion = SVGUtilities.convertFilterPrimitiveRegion(filterElement, filteredElement, filteredNode, defaultRegion, filterRegion, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     CompositeRable8Bit compositeRable8Bit = new CompositeRable8Bit(srcs, CompositeRule.OVER, true);
/*     */ 
/*     */     
/* 118 */     handleColorInterpolationFilters((Filter)compositeRable8Bit, filterElement);
/*     */     
/* 120 */     PadRable8Bit padRable8Bit = new PadRable8Bit((Filter)compositeRable8Bit, primitiveRegion, PadMode.ZERO_PAD);
/*     */ 
/*     */     
/* 123 */     updateFilterMap(filterElement, (Filter)padRable8Bit, filterMap);
/*     */     
/* 125 */     return (Filter)padRable8Bit;
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
/*     */   protected static List extractFeMergeNode(Element filterElement, Element filteredElement, GraphicsNode filteredNode, Filter inputFilter, Map filterMap, BridgeContext ctx) {
/* 148 */     List<Filter> srcs = null;
/* 149 */     Node n = filterElement.getFirstChild();
/* 150 */     for (; n != null; 
/* 151 */       n = n.getNextSibling()) {
/*     */       
/* 153 */       if (n.getNodeType() == 1) {
/*     */ 
/*     */ 
/*     */         
/* 157 */         Element e = (Element)n;
/* 158 */         Bridge bridge = ctx.getBridge(e);
/* 159 */         if (bridge != null && bridge instanceof SVGFeMergeNodeElementBridge) {
/*     */ 
/*     */ 
/*     */           
/* 163 */           Filter filter = ((SVGFeMergeNodeElementBridge)bridge).createFilter(ctx, e, filteredElement, filteredNode, inputFilter, filterMap);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 170 */           if (filter != null)
/* 171 */           { if (srcs == null) {
/* 172 */               srcs = new LinkedList();
/*     */             }
/* 174 */             srcs.add(filter); } 
/*     */         } 
/*     */       } 
/* 177 */     }  return srcs;
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
/*     */   public static class SVGFeMergeNodeElementBridge
/*     */     extends AnimatableGenericSVGBridge
/*     */   {
/*     */     public String getLocalName() {
/* 195 */       return "feMergeNode";
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
/*     */ 
/*     */ 
/*     */     
/*     */     public Filter createFilter(BridgeContext ctx, Element filterElement, Element filteredElement, GraphicsNode filteredNode, Filter inputFilter, Map filterMap) {
/* 218 */       return AbstractSVGFilterPrimitiveElementBridge.getIn(filterElement, filteredElement, filteredNode, inputFilter, filterMap, ctx);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGFeMergeElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */