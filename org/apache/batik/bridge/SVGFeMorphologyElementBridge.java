/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Map;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.ext.awt.image.renderable.MorphologyRable8Bit;
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
/*     */ public class SVGFeMorphologyElementBridge
/*     */   extends AbstractSVGFilterPrimitiveElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  52 */     return "feMorphology";
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
/*  82 */     float[] radii = convertRadius(filterElement, ctx);
/*  83 */     if (radii[0] == 0.0F || radii[1] == 0.0F) {
/*  84 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  88 */     boolean isDilate = convertOperator(filterElement, ctx);
/*     */ 
/*     */     
/*  91 */     Filter in = getIn(filterElement, filteredElement, filteredNode, inputFilter, filterMap, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     if (in == null) {
/*  98 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     Rectangle2D defaultRegion = in.getBounds2D();
/* 105 */     Rectangle2D primitiveRegion = SVGUtilities.convertFilterPrimitiveRegion(filterElement, filteredElement, filteredNode, defaultRegion, filterRegion, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     PadRable8Bit padRable8Bit1 = new PadRable8Bit(in, primitiveRegion, PadMode.ZERO_PAD);
/*     */ 
/*     */     
/* 118 */     MorphologyRable8Bit morphologyRable8Bit = new MorphologyRable8Bit((Filter)padRable8Bit1, radii[0], radii[1], isDilate);
/*     */ 
/*     */ 
/*     */     
/* 122 */     handleColorInterpolationFilters((Filter)morphologyRable8Bit, filterElement);
/*     */     
/* 124 */     PadRable8Bit padRable8Bit2 = new PadRable8Bit((Filter)morphologyRable8Bit, primitiveRegion, PadMode.ZERO_PAD);
/*     */ 
/*     */ 
/*     */     
/* 128 */     updateFilterMap(filterElement, (Filter)padRable8Bit2, filterMap);
/*     */     
/* 130 */     return (Filter)padRable8Bit2;
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
/*     */   protected static float[] convertRadius(Element filterElement, BridgeContext ctx) {
/* 142 */     String s = filterElement.getAttributeNS(null, "radius");
/* 143 */     if (s.length() == 0) {
/* 144 */       return new float[] { 0.0F, 0.0F };
/*     */     }
/* 146 */     float[] radii = new float[2];
/* 147 */     StringTokenizer tokens = new StringTokenizer(s, " ,");
/*     */     try {
/* 149 */       radii[0] = SVGUtilities.convertSVGNumber(tokens.nextToken());
/* 150 */       if (tokens.hasMoreTokens()) {
/* 151 */         radii[1] = SVGUtilities.convertSVGNumber(tokens.nextToken());
/*     */       } else {
/* 153 */         radii[1] = radii[0];
/*     */       } 
/* 155 */     } catch (NumberFormatException nfEx) {
/* 156 */       throw new BridgeException(ctx, filterElement, nfEx, "attribute.malformed", new Object[] { "radius", s, nfEx });
/*     */     } 
/*     */ 
/*     */     
/* 160 */     if (tokens.hasMoreTokens() || radii[0] < 0.0F || radii[1] < 0.0F) {
/* 161 */       throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { "radius", s });
/*     */     }
/*     */ 
/*     */     
/* 165 */     return radii;
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
/*     */   protected static boolean convertOperator(Element filterElement, BridgeContext ctx) {
/* 177 */     String s = filterElement.getAttributeNS(null, "operator");
/* 178 */     if (s.length() == 0) {
/* 179 */       return false;
/*     */     }
/* 181 */     if ("erode".equals(s)) {
/* 182 */       return false;
/*     */     }
/* 184 */     if ("dilate".equals(s)) {
/* 185 */       return true;
/*     */     }
/* 187 */     throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { "operator", s });
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGFeMorphologyElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */