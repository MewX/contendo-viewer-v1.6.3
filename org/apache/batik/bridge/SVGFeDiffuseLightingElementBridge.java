/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.Light;
/*     */ import org.apache.batik.ext.awt.image.renderable.DiffuseLightingRable8Bit;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
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
/*     */ public class SVGFeDiffuseLightingElementBridge
/*     */   extends AbstractSVGLightingElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  49 */     return "feDiffuseLighting";
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
/*  79 */     float surfaceScale = convertNumber(filterElement, "surfaceScale", 1.0F, ctx);
/*     */ 
/*     */ 
/*     */     
/*  83 */     float diffuseConstant = convertNumber(filterElement, "diffuseConstant", 1.0F, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  91 */     Light light = extractLight(filterElement, ctx);
/*     */ 
/*     */     
/*  94 */     double[] kernelUnitLength = convertKernelUnitLength(filterElement, ctx);
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
/*     */ 
/*     */     
/* 110 */     Rectangle2D defaultRegion = in.getBounds2D();
/* 111 */     Rectangle2D primitiveRegion = SVGUtilities.convertFilterPrimitiveRegion(filterElement, filteredElement, filteredNode, defaultRegion, filterRegion, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     DiffuseLightingRable8Bit diffuseLightingRable8Bit = new DiffuseLightingRable8Bit(in, primitiveRegion, light, diffuseConstant, surfaceScale, kernelUnitLength);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 126 */     handleColorInterpolationFilters((Filter)diffuseLightingRable8Bit, filterElement);
/*     */ 
/*     */     
/* 129 */     updateFilterMap(filterElement, (Filter)diffuseLightingRable8Bit, filterMap);
/*     */     
/* 131 */     return (Filter)diffuseLightingRable8Bit;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGFeDiffuseLightingElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */