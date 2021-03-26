/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.Light;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.ext.awt.image.renderable.SpecularLightingRable8Bit;
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
/*     */ public class SVGFeSpecularLightingElementBridge
/*     */   extends AbstractSVGLightingElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  49 */     return "feSpecularLighting";
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
/*  80 */     float surfaceScale = convertNumber(filterElement, "surfaceScale", 1.0F, ctx);
/*     */ 
/*     */ 
/*     */     
/*  84 */     float specularConstant = convertNumber(filterElement, "specularConstant", 1.0F, ctx);
/*     */ 
/*     */ 
/*     */     
/*  88 */     float specularExponent = convertSpecularExponent(filterElement, ctx);
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
/*     */     
/* 119 */     SpecularLightingRable8Bit specularLightingRable8Bit = new SpecularLightingRable8Bit(in, primitiveRegion, light, specularConstant, specularExponent, surfaceScale, kernelUnitLength);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     handleColorInterpolationFilters((Filter)specularLightingRable8Bit, filterElement);
/*     */ 
/*     */     
/* 132 */     updateFilterMap(filterElement, (Filter)specularLightingRable8Bit, filterMap);
/*     */     
/* 134 */     return (Filter)specularLightingRable8Bit;
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
/*     */   protected static float convertSpecularExponent(Element filterElement, BridgeContext ctx) {
/* 146 */     String s = filterElement.getAttributeNS(null, "specularExponent");
/*     */     
/* 148 */     if (s.length() == 0) {
/* 149 */       return 1.0F;
/*     */     }
/*     */     try {
/* 152 */       float v = SVGUtilities.convertSVGNumber(s);
/* 153 */       if (v < 1.0F || v > 128.0F) {
/* 154 */         throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { "specularConstant", s });
/*     */       }
/*     */ 
/*     */       
/* 158 */       return v;
/* 159 */     } catch (NumberFormatException nfEx) {
/* 160 */       throw new BridgeException(ctx, filterElement, nfEx, "attribute.malformed", new Object[] { "specularConstant", s, nfEx });
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGFeSpecularLightingElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */