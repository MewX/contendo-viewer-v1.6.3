/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Map;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.ext.awt.image.renderable.GaussianBlurRable8Bit;
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
/*     */ public class SVGFeGaussianBlurElementBridge
/*     */   extends AbstractSVGFilterPrimitiveElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  52 */     return "feGaussianBlur";
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
/*  82 */     float[] stdDeviationXY = convertStdDeviation(filterElement, ctx);
/*  83 */     if (stdDeviationXY[0] < 0.0F || stdDeviationXY[1] < 0.0F) {
/*  84 */       throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { "stdDeviation", String.valueOf(stdDeviationXY[0]) + stdDeviationXY[1] });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     Filter in = getIn(filterElement, filteredElement, filteredNode, inputFilter, filterMap, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  98 */     if (in == null) {
/*  99 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     Rectangle2D defaultRegion = in.getBounds2D();
/* 106 */     Rectangle2D primitiveRegion = SVGUtilities.convertFilterPrimitiveRegion(filterElement, filteredElement, filteredNode, defaultRegion, filterRegion, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 116 */     PadRable8Bit padRable8Bit1 = new PadRable8Bit(in, primitiveRegion, PadMode.ZERO_PAD);
/*     */ 
/*     */     
/* 119 */     GaussianBlurRable8Bit gaussianBlurRable8Bit = new GaussianBlurRable8Bit((Filter)padRable8Bit1, stdDeviationXY[0], stdDeviationXY[1]);
/*     */ 
/*     */ 
/*     */     
/* 123 */     handleColorInterpolationFilters((Filter)gaussianBlurRable8Bit, filterElement);
/*     */     
/* 125 */     PadRable8Bit padRable8Bit2 = new PadRable8Bit((Filter)gaussianBlurRable8Bit, primitiveRegion, PadMode.ZERO_PAD);
/*     */ 
/*     */ 
/*     */     
/* 129 */     updateFilterMap(filterElement, (Filter)padRable8Bit2, filterMap);
/*     */     
/* 131 */     return (Filter)padRable8Bit2;
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
/*     */   protected static float[] convertStdDeviation(Element filterElement, BridgeContext ctx) {
/* 143 */     String s = filterElement.getAttributeNS(null, "stdDeviation");
/*     */     
/* 145 */     if (s.length() == 0) {
/* 146 */       return new float[] { 0.0F, 0.0F };
/*     */     }
/* 148 */     float[] stdDevs = new float[2];
/* 149 */     StringTokenizer tokens = new StringTokenizer(s, " ,");
/*     */     try {
/* 151 */       stdDevs[0] = SVGUtilities.convertSVGNumber(tokens.nextToken());
/* 152 */       if (tokens.hasMoreTokens()) {
/* 153 */         stdDevs[1] = SVGUtilities.convertSVGNumber(tokens.nextToken());
/*     */       } else {
/* 155 */         stdDevs[1] = stdDevs[0];
/*     */       } 
/* 157 */     } catch (NumberFormatException nfEx) {
/* 158 */       throw new BridgeException(ctx, filterElement, nfEx, "attribute.malformed", new Object[] { "stdDeviation", s, nfEx });
/*     */     } 
/*     */ 
/*     */     
/* 162 */     if (tokens.hasMoreTokens()) {
/* 163 */       throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { "stdDeviation", s });
/*     */     }
/*     */ 
/*     */     
/* 167 */     return stdDevs;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGFeGaussianBlurElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */