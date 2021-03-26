/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.Kernel;
/*     */ import java.util.Map;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.renderable.ConvolveMatrixRable8Bit;
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
/*     */ 
/*     */ public class SVGFeConvolveMatrixElementBridge
/*     */   extends AbstractSVGFilterPrimitiveElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  55 */     return "feConvolveMatrix";
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
/*  85 */     int[] orderXY = convertOrder(filterElement, ctx);
/*     */ 
/*     */     
/*  88 */     float[] kernelMatrix = convertKernelMatrix(filterElement, orderXY, ctx);
/*     */ 
/*     */     
/*  91 */     float divisor = convertDivisor(filterElement, kernelMatrix, ctx);
/*     */ 
/*     */     
/*  94 */     float bias = convertNumber(filterElement, "bias", 0.0F, ctx);
/*     */ 
/*     */     
/*  97 */     int[] targetXY = convertTarget(filterElement, orderXY, ctx);
/*     */ 
/*     */     
/* 100 */     PadMode padMode = convertEdgeMode(filterElement, ctx);
/*     */ 
/*     */     
/* 103 */     double[] kernelUnitLength = convertKernelUnitLength(filterElement, ctx);
/*     */ 
/*     */     
/* 106 */     boolean preserveAlpha = convertPreserveAlpha(filterElement, ctx);
/*     */ 
/*     */     
/* 109 */     Filter in = getIn(filterElement, filteredElement, filteredNode, inputFilter, filterMap, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     if (in == null) {
/* 116 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 122 */     Rectangle2D defaultRegion = in.getBounds2D();
/* 123 */     Rectangle2D primitiveRegion = SVGUtilities.convertFilterPrimitiveRegion(filterElement, filteredElement, filteredNode, defaultRegion, filterRegion, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 131 */     PadRable8Bit padRable8Bit1 = new PadRable8Bit(in, primitiveRegion, PadMode.ZERO_PAD);
/*     */ 
/*     */     
/* 134 */     ConvolveMatrixRable8Bit convolveMatrixRable8Bit = new ConvolveMatrixRable8Bit((Filter)padRable8Bit1);
/* 135 */     for (int i = 0; i < kernelMatrix.length; i++) {
/* 136 */       kernelMatrix[i] = kernelMatrix[i] / divisor;
/*     */     }
/* 138 */     convolveMatrixRable8Bit.setKernel(new Kernel(orderXY[0], orderXY[1], kernelMatrix));
/* 139 */     convolveMatrixRable8Bit.setTarget(new Point(targetXY[0], targetXY[1]));
/* 140 */     convolveMatrixRable8Bit.setBias(bias);
/* 141 */     convolveMatrixRable8Bit.setEdgeMode(padMode);
/* 142 */     convolveMatrixRable8Bit.setKernelUnitLength(kernelUnitLength);
/* 143 */     convolveMatrixRable8Bit.setPreserveAlpha(preserveAlpha);
/*     */ 
/*     */     
/* 146 */     handleColorInterpolationFilters((Filter)convolveMatrixRable8Bit, filterElement);
/*     */     
/* 148 */     PadRable8Bit padRable8Bit2 = new PadRable8Bit((Filter)convolveMatrixRable8Bit, primitiveRegion, PadMode.ZERO_PAD);
/*     */ 
/*     */ 
/*     */     
/* 152 */     updateFilterMap(filterElement, (Filter)padRable8Bit2, filterMap);
/*     */     
/* 154 */     return (Filter)padRable8Bit2;
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
/*     */   protected static int[] convertOrder(Element filterElement, BridgeContext ctx) {
/* 166 */     String s = filterElement.getAttributeNS(null, "order");
/* 167 */     if (s.length() == 0) {
/* 168 */       return new int[] { 3, 3 };
/*     */     }
/* 170 */     int[] orderXY = new int[2];
/* 171 */     StringTokenizer tokens = new StringTokenizer(s, " ,");
/*     */     try {
/* 173 */       orderXY[0] = SVGUtilities.convertSVGInteger(tokens.nextToken());
/* 174 */       if (tokens.hasMoreTokens()) {
/* 175 */         orderXY[1] = SVGUtilities.convertSVGInteger(tokens.nextToken());
/*     */       } else {
/* 177 */         orderXY[1] = orderXY[0];
/*     */       } 
/* 179 */     } catch (NumberFormatException nfEx) {
/* 180 */       throw new BridgeException(ctx, filterElement, nfEx, "attribute.malformed", new Object[] { "order", s, nfEx });
/*     */     } 
/*     */ 
/*     */     
/* 184 */     if (tokens.hasMoreTokens() || orderXY[0] <= 0 || orderXY[1] <= 0) {
/* 185 */       throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { "order", s });
/*     */     }
/*     */ 
/*     */     
/* 189 */     return orderXY;
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
/*     */   protected static float[] convertKernelMatrix(Element filterElement, int[] orderXY, BridgeContext ctx) {
/* 204 */     String s = filterElement.getAttributeNS(null, "kernelMatrix");
/*     */     
/* 206 */     if (s.length() == 0) {
/* 207 */       throw new BridgeException(ctx, filterElement, "attribute.missing", new Object[] { "kernelMatrix" });
/*     */     }
/*     */ 
/*     */     
/* 211 */     int size = orderXY[0] * orderXY[1];
/* 212 */     float[] kernelMatrix = new float[size];
/* 213 */     StringTokenizer tokens = new StringTokenizer(s, " ,");
/* 214 */     int i = 0;
/*     */     try {
/* 216 */       while (tokens.hasMoreTokens() && i < size) {
/* 217 */         kernelMatrix[i++] = SVGUtilities.convertSVGNumber(tokens.nextToken());
/*     */       }
/*     */     }
/* 220 */     catch (NumberFormatException nfEx) {
/* 221 */       throw new BridgeException(ctx, filterElement, nfEx, "attribute.malformed", new Object[] { "kernelMatrix", s, nfEx });
/*     */     } 
/*     */ 
/*     */     
/* 225 */     if (i != size) {
/* 226 */       throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { "kernelMatrix", s });
/*     */     }
/*     */ 
/*     */     
/* 230 */     return kernelMatrix;
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
/*     */   protected static float convertDivisor(Element filterElement, float[] kernelMatrix, BridgeContext ctx) {
/* 245 */     String s = filterElement.getAttributeNS(null, "divisor");
/* 246 */     if (s.length() == 0) {
/*     */       
/* 248 */       float sum = 0.0F;
/* 249 */       for (float aKernelMatrix : kernelMatrix) {
/* 250 */         sum += aKernelMatrix;
/*     */       }
/* 252 */       return (sum == 0.0F) ? 1.0F : sum;
/*     */     } 
/*     */     try {
/* 255 */       return SVGUtilities.convertSVGNumber(s);
/* 256 */     } catch (NumberFormatException nfEx) {
/* 257 */       throw new BridgeException(ctx, filterElement, nfEx, "attribute.malformed", new Object[] { "divisor", s, nfEx });
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
/*     */ 
/*     */ 
/*     */   
/*     */   protected static int[] convertTarget(Element filterElement, int[] orderXY, BridgeContext ctx) {
/* 275 */     int[] targetXY = new int[2];
/*     */     
/* 277 */     String s = filterElement.getAttributeNS(null, "targetX");
/* 278 */     if (s.length() == 0) {
/* 279 */       targetXY[0] = orderXY[0] / 2;
/*     */     } else {
/*     */       try {
/* 282 */         int v = SVGUtilities.convertSVGInteger(s);
/* 283 */         if (v < 0 || v >= orderXY[0]) {
/* 284 */           throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { "targetX", s });
/*     */         }
/*     */ 
/*     */         
/* 288 */         targetXY[0] = v;
/* 289 */       } catch (NumberFormatException nfEx) {
/* 290 */         throw new BridgeException(ctx, filterElement, nfEx, "attribute.malformed", new Object[] { "targetX", s, nfEx });
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 296 */     s = filterElement.getAttributeNS(null, "targetY");
/* 297 */     if (s.length() == 0) {
/* 298 */       targetXY[1] = orderXY[1] / 2;
/*     */     } else {
/*     */       try {
/* 301 */         int v = SVGUtilities.convertSVGInteger(s);
/* 302 */         if (v < 0 || v >= orderXY[1]) {
/* 303 */           throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { "targetY", s });
/*     */         }
/*     */ 
/*     */         
/* 307 */         targetXY[1] = v;
/* 308 */       } catch (NumberFormatException nfEx) {
/* 309 */         throw new BridgeException(ctx, filterElement, nfEx, "attribute.malformed", new Object[] { "targetY", s, nfEx });
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 314 */     return targetXY;
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
/*     */   protected static double[] convertKernelUnitLength(Element filterElement, BridgeContext ctx) {
/* 326 */     String s = filterElement.getAttributeNS(null, "kernelUnitLength");
/*     */     
/* 328 */     if (s.length() == 0) {
/* 329 */       return null;
/*     */     }
/* 331 */     double[] units = new double[2];
/* 332 */     StringTokenizer tokens = new StringTokenizer(s, " ,");
/*     */     try {
/* 334 */       units[0] = SVGUtilities.convertSVGNumber(tokens.nextToken());
/* 335 */       if (tokens.hasMoreTokens()) {
/* 336 */         units[1] = SVGUtilities.convertSVGNumber(tokens.nextToken());
/*     */       } else {
/* 338 */         units[1] = units[0];
/*     */       } 
/* 340 */     } catch (NumberFormatException nfEx) {
/* 341 */       throw new BridgeException(ctx, filterElement, nfEx, "attribute.malformed", new Object[] { "kernelUnitLength", s });
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 346 */     if (tokens.hasMoreTokens() || units[0] <= 0.0D || units[1] <= 0.0D) {
/* 347 */       throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { "kernelUnitLength", s });
/*     */     }
/*     */ 
/*     */     
/* 351 */     return units;
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
/*     */   protected static PadMode convertEdgeMode(Element filterElement, BridgeContext ctx) {
/* 363 */     String s = filterElement.getAttributeNS(null, "edgeMode");
/* 364 */     if (s.length() == 0) {
/* 365 */       return PadMode.REPLICATE;
/*     */     }
/* 367 */     if ("duplicate".equals(s)) {
/* 368 */       return PadMode.REPLICATE;
/*     */     }
/* 370 */     if ("wrap".equals(s)) {
/* 371 */       return PadMode.WRAP;
/*     */     }
/* 373 */     if ("none".equals(s)) {
/* 374 */       return PadMode.ZERO_PAD;
/*     */     }
/* 376 */     throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { "edgeMode", s });
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
/*     */   protected static boolean convertPreserveAlpha(Element filterElement, BridgeContext ctx) {
/* 390 */     String s = filterElement.getAttributeNS(null, "preserveAlpha");
/*     */     
/* 392 */     if (s.length() == 0) {
/* 393 */       return false;
/*     */     }
/* 395 */     if ("true".equals(s)) {
/* 396 */       return true;
/*     */     }
/* 398 */     if ("false".equals(s)) {
/* 399 */       return false;
/*     */     }
/* 401 */     throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { "preserveAlpha", s });
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGFeConvolveMatrixElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */