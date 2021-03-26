/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Map;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.ext.awt.image.renderable.TurbulenceRable8Bit;
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
/*     */ public class SVGFeTurbulenceElementBridge
/*     */   extends AbstractSVGFilterPrimitiveElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  49 */     return "feTurbulence";
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
/*  79 */     Filter in = getIn(filterElement, filteredElement, filteredNode, inputFilter, filterMap, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  85 */     if (in == null) {
/*  86 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  90 */     Rectangle2D defaultRegion = filterRegion;
/*  91 */     Rectangle2D primitiveRegion = SVGUtilities.convertFilterPrimitiveRegion(filterElement, filteredElement, filteredNode, defaultRegion, filterRegion, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     float[] baseFrequency = convertBaseFrenquency(filterElement, ctx);
/*     */ 
/*     */ 
/*     */     
/* 104 */     int numOctaves = convertInteger(filterElement, "numOctaves", 1, ctx);
/*     */ 
/*     */ 
/*     */     
/* 108 */     int seed = convertInteger(filterElement, "seed", 0, ctx);
/*     */ 
/*     */ 
/*     */     
/* 112 */     boolean stitchTiles = convertStitchTiles(filterElement, ctx);
/*     */ 
/*     */ 
/*     */     
/* 116 */     boolean isFractalNoise = convertType(filterElement, ctx);
/*     */ 
/*     */ 
/*     */     
/* 120 */     TurbulenceRable8Bit turbulenceRable8Bit = new TurbulenceRable8Bit(primitiveRegion);
/*     */ 
/*     */     
/* 123 */     turbulenceRable8Bit.setBaseFrequencyX(baseFrequency[0]);
/* 124 */     turbulenceRable8Bit.setBaseFrequencyY(baseFrequency[1]);
/* 125 */     turbulenceRable8Bit.setNumOctaves(numOctaves);
/* 126 */     turbulenceRable8Bit.setSeed(seed);
/* 127 */     turbulenceRable8Bit.setStitched(stitchTiles);
/* 128 */     turbulenceRable8Bit.setFractalNoise(isFractalNoise);
/*     */ 
/*     */     
/* 131 */     handleColorInterpolationFilters((Filter)turbulenceRable8Bit, filterElement);
/*     */ 
/*     */     
/* 134 */     updateFilterMap(filterElement, (Filter)turbulenceRable8Bit, filterMap);
/*     */     
/* 136 */     return (Filter)turbulenceRable8Bit;
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
/*     */   protected static float[] convertBaseFrenquency(Element e, BridgeContext ctx) {
/* 148 */     String s = e.getAttributeNS(null, "baseFrequency");
/* 149 */     if (s.length() == 0) {
/* 150 */       return new float[] { 0.001F, 0.001F };
/*     */     }
/* 152 */     float[] v = new float[2];
/* 153 */     StringTokenizer tokens = new StringTokenizer(s, " ,");
/*     */     try {
/* 155 */       v[0] = SVGUtilities.convertSVGNumber(tokens.nextToken());
/* 156 */       if (tokens.hasMoreTokens()) {
/* 157 */         v[1] = SVGUtilities.convertSVGNumber(tokens.nextToken());
/*     */       } else {
/* 159 */         v[1] = v[0];
/*     */       } 
/* 161 */       if (tokens.hasMoreTokens()) {
/* 162 */         throw new BridgeException(ctx, e, "attribute.malformed", new Object[] { "baseFrequency", s });
/*     */       
/*     */       }
/*     */     }
/* 166 */     catch (NumberFormatException nfEx) {
/* 167 */       throw new BridgeException(ctx, e, nfEx, "attribute.malformed", new Object[] { "baseFrequency", s });
/*     */     } 
/*     */ 
/*     */     
/* 171 */     if (v[0] < 0.0F || v[1] < 0.0F) {
/* 172 */       throw new BridgeException(ctx, e, "attribute.malformed", new Object[] { "baseFrequency", s });
/*     */     }
/*     */ 
/*     */     
/* 176 */     return v;
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
/*     */   protected static boolean convertStitchTiles(Element e, BridgeContext ctx) {
/* 188 */     String s = e.getAttributeNS(null, "stitchTiles");
/* 189 */     if (s.length() == 0) {
/* 190 */       return false;
/*     */     }
/* 192 */     if ("stitch".equals(s)) {
/* 193 */       return true;
/*     */     }
/* 195 */     if ("noStitch".equals(s)) {
/* 196 */       return false;
/*     */     }
/* 198 */     throw new BridgeException(ctx, e, "attribute.malformed", new Object[] { "stitchTiles", s });
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
/*     */   protected static boolean convertType(Element e, BridgeContext ctx) {
/* 210 */     String s = e.getAttributeNS(null, "type");
/* 211 */     if (s.length() == 0) {
/* 212 */       return false;
/*     */     }
/* 214 */     if ("fractalNoise".equals(s)) {
/* 215 */       return true;
/*     */     }
/* 217 */     if ("turbulence".equals(s)) {
/* 218 */       return false;
/*     */     }
/* 220 */     throw new BridgeException(ctx, e, "attribute.malformed", new Object[] { "type", s });
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGFeTurbulenceElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */