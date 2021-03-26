/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Map;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.renderable.ColorMatrixRable;
/*     */ import org.apache.batik.ext.awt.image.renderable.ColorMatrixRable8Bit;
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
/*     */ public class SVGFeColorMatrixElementBridge
/*     */   extends AbstractSVGFilterPrimitiveElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  51 */     return "feColorMatrix";
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
/*     */   public Filter createFilter(BridgeContext ctx, Element filterElement, Element filteredElement, GraphicsNode filteredNode, Filter inputFilter, Rectangle2D filterRegion, Map filterMap) {
/*     */     ColorMatrixRable colorMatrix;
/*     */     float a;
/*     */     PadRable8Bit padRable8Bit;
/*     */     float matrix[][], s;
/*  81 */     Filter in = getIn(filterElement, filteredElement, filteredNode, inputFilter, filterMap, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  87 */     if (in == null) {
/*  88 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  94 */     Rectangle2D defaultRegion = in.getBounds2D();
/*  95 */     Rectangle2D primitiveRegion = SVGUtilities.convertFilterPrimitiveRegion(filterElement, filteredElement, filteredNode, defaultRegion, filterRegion, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     int type = convertType(filterElement, ctx);
/*     */     
/* 105 */     switch (type) {
/*     */       case 2:
/* 107 */         a = convertValuesToHueRotate(filterElement, ctx);
/* 108 */         colorMatrix = ColorMatrixRable8Bit.buildHueRotate(a);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 124 */         colorMatrix.setSource(in);
/*     */ 
/*     */         
/* 127 */         handleColorInterpolationFilters((Filter)colorMatrix, filterElement);
/*     */         
/* 129 */         padRable8Bit = new PadRable8Bit((Filter)colorMatrix, primitiveRegion, PadMode.ZERO_PAD);
/*     */ 
/*     */ 
/*     */         
/* 133 */         updateFilterMap(filterElement, (Filter)padRable8Bit, filterMap);
/*     */         
/* 135 */         return (Filter)padRable8Bit;case 3: colorMatrix = ColorMatrixRable8Bit.buildLuminanceToAlpha(); colorMatrix.setSource(in); handleColorInterpolationFilters((Filter)colorMatrix, filterElement); padRable8Bit = new PadRable8Bit((Filter)colorMatrix, primitiveRegion, PadMode.ZERO_PAD); updateFilterMap(filterElement, (Filter)padRable8Bit, filterMap); return (Filter)padRable8Bit;case 0: matrix = convertValuesToMatrix(filterElement, ctx); colorMatrix = ColorMatrixRable8Bit.buildMatrix(matrix); colorMatrix.setSource(in); handleColorInterpolationFilters((Filter)colorMatrix, filterElement); padRable8Bit = new PadRable8Bit((Filter)colorMatrix, primitiveRegion, PadMode.ZERO_PAD); updateFilterMap(filterElement, (Filter)padRable8Bit, filterMap); return (Filter)padRable8Bit;case 1: s = convertValuesToSaturate(filterElement, ctx); colorMatrix = ColorMatrixRable8Bit.buildSaturate(s); colorMatrix.setSource(in); handleColorInterpolationFilters((Filter)colorMatrix, filterElement); padRable8Bit = new PadRable8Bit((Filter)colorMatrix, primitiveRegion, PadMode.ZERO_PAD); updateFilterMap(filterElement, (Filter)padRable8Bit, filterMap); return (Filter)padRable8Bit;
/*     */     } 
/*     */     throw new RuntimeException("invalid convertType:" + type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static float[][] convertValuesToMatrix(Element filterElement, BridgeContext ctx) {
/* 147 */     String s = filterElement.getAttributeNS(null, "values");
/* 148 */     float[][] matrix = new float[4][5];
/* 149 */     if (s.length() == 0) {
/* 150 */       matrix[0][0] = 1.0F;
/* 151 */       matrix[1][1] = 1.0F;
/* 152 */       matrix[2][2] = 1.0F;
/* 153 */       matrix[3][3] = 1.0F;
/* 154 */       return matrix;
/*     */     } 
/* 156 */     StringTokenizer tokens = new StringTokenizer(s, " ,");
/* 157 */     int n = 0;
/*     */     try {
/* 159 */       while (n < 20 && tokens.hasMoreTokens()) {
/* 160 */         matrix[n / 5][n % 5] = SVGUtilities.convertSVGNumber(tokens.nextToken());
/*     */         
/* 162 */         n++;
/*     */       } 
/* 164 */     } catch (NumberFormatException nfEx) {
/* 165 */       throw new BridgeException(ctx, filterElement, nfEx, "attribute.malformed", new Object[] { "values", s, nfEx });
/*     */     } 
/*     */ 
/*     */     
/* 169 */     if (n != 20 || tokens.hasMoreTokens()) {
/* 170 */       throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { "values", s });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 175 */     for (int i = 0; i < 4; i++) {
/* 176 */       matrix[i][4] = matrix[i][4] * 255.0F;
/*     */     }
/* 178 */     return matrix;
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
/*     */   protected static float convertValuesToSaturate(Element filterElement, BridgeContext ctx) {
/* 190 */     String s = filterElement.getAttributeNS(null, "values");
/* 191 */     if (s.length() == 0)
/* 192 */       return 1.0F; 
/*     */     try {
/* 194 */       return SVGUtilities.convertSVGNumber(s);
/* 195 */     } catch (NumberFormatException nfEx) {
/* 196 */       throw new BridgeException(ctx, filterElement, nfEx, "attribute.malformed", new Object[] { "values", s });
/*     */     } 
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
/*     */   protected static float convertValuesToHueRotate(Element filterElement, BridgeContext ctx) {
/* 211 */     String s = filterElement.getAttributeNS(null, "values");
/* 212 */     if (s.length() == 0)
/* 213 */       return 0.0F; 
/*     */     try {
/* 215 */       return (float)Math.toRadians(SVGUtilities.convertSVGNumber(s));
/* 216 */     } catch (NumberFormatException nfEx) {
/* 217 */       throw new BridgeException(ctx, filterElement, nfEx, "attribute.malformed", new Object[] { "values", s });
/*     */     } 
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
/*     */   protected static int convertType(Element filterElement, BridgeContext ctx) {
/* 230 */     String s = filterElement.getAttributeNS(null, "type");
/* 231 */     if (s.length() == 0) {
/* 232 */       return 0;
/*     */     }
/* 234 */     if ("hueRotate".equals(s)) {
/* 235 */       return 2;
/*     */     }
/* 237 */     if ("luminanceToAlpha".equals(s)) {
/* 238 */       return 3;
/*     */     }
/* 240 */     if ("matrix".equals(s)) {
/* 241 */       return 0;
/*     */     }
/* 243 */     if ("saturate".equals(s)) {
/* 244 */       return 1;
/*     */     }
/* 246 */     throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { "type", s });
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGFeColorMatrixElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */