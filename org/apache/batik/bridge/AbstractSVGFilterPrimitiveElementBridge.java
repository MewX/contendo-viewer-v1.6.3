/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.ext.awt.image.renderable.FilterAlphaRable;
/*     */ import org.apache.batik.ext.awt.image.renderable.FilterColorInterpolation;
/*     */ import org.apache.batik.ext.awt.image.renderable.FloodRable8Bit;
/*     */ import org.apache.batik.ext.awt.image.renderable.PadRable8Bit;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.gvt.filter.BackgroundRable8Bit;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSVGFilterPrimitiveElementBridge
/*     */   extends AnimatableGenericSVGBridge
/*     */   implements ErrorConstants, FilterPrimitiveBridge
/*     */ {
/*     */   protected static Filter getIn(Element filterElement, Element filteredElement, GraphicsNode filteredNode, Filter inputFilter, Map filterMap, BridgeContext ctx) {
/*  69 */     String s = filterElement.getAttributeNS(null, "in");
/*  70 */     if (s.length() == 0) {
/*  71 */       return inputFilter;
/*     */     }
/*  73 */     return getFilterSource(filterElement, s, filteredElement, filteredNode, filterMap, ctx);
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
/*     */   protected static Filter getIn2(Element filterElement, Element filteredElement, GraphicsNode filteredNode, Filter inputFilter, Map filterMap, BridgeContext ctx) {
/* 101 */     String s = filterElement.getAttributeNS(null, "in2");
/* 102 */     if (s.length() == 0) {
/* 103 */       throw new BridgeException(ctx, filterElement, "attribute.missing", new Object[] { "in2" });
/*     */     }
/*     */     
/* 106 */     return getFilterSource(filterElement, s, filteredElement, filteredNode, filterMap, ctx);
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
/*     */   protected static void updateFilterMap(Element filterElement, Filter filter, Map<String, Filter> filterMap) {
/* 125 */     String s = filterElement.getAttributeNS(null, "result");
/* 126 */     if (s.length() != 0 && s.trim().length() != 0) {
/* 127 */       filterMap.put(s, filter);
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
/*     */   protected static void handleColorInterpolationFilters(Filter filter, Element filterElement) {
/* 139 */     if (filter instanceof FilterColorInterpolation) {
/* 140 */       boolean isLinear = CSSUtilities.convertColorInterpolationFilters(filterElement);
/*     */ 
/*     */ 
/*     */       
/* 144 */       ((FilterColorInterpolation)filter).setColorSpaceLinear(isLinear);
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
/*     */ 
/*     */ 
/*     */   
/*     */   static Filter getFilterSource(Element filterElement, String s, Element filteredElement, GraphicsNode filteredNode, Map filterMap, BridgeContext ctx) {
/*     */     FloodRable8Bit floodRable8Bit;
/* 166 */     Filter filter1, srcG = (Filter)filterMap.get("SourceGraphic");
/* 167 */     Rectangle2D filterRegion = srcG.getBounds2D();
/*     */     
/* 169 */     int length = s.length();
/* 170 */     Filter source = null;
/* 171 */     switch (length) {
/*     */       case 13:
/* 173 */         if ("SourceGraphic".equals(s))
/*     */         {
/* 175 */           source = srcG;
/*     */         }
/*     */         break;
/*     */       case 11:
/* 179 */         if (s.charAt(1) == "SourceAlpha".charAt(1)) {
/* 180 */           if ("SourceAlpha".equals(s)) {
/*     */             
/* 182 */             source = srcG;
/* 183 */             FilterAlphaRable filterAlphaRable = new FilterAlphaRable(source);
/*     */           }  break;
/* 185 */         }  if ("StrokePaint".equals(s)) {
/*     */           
/* 187 */           Paint paint = PaintServer.convertStrokePaint(filteredElement, filteredNode, ctx);
/*     */ 
/*     */           
/* 190 */           floodRable8Bit = new FloodRable8Bit(filterRegion, paint);
/*     */         } 
/*     */         break;
/*     */       case 15:
/* 194 */         if (s.charAt(10) == "BackgroundImage".charAt(10)) {
/* 195 */           if ("BackgroundImage".equals(s)) {
/*     */             
/* 197 */             BackgroundRable8Bit backgroundRable8Bit = new BackgroundRable8Bit(filteredNode);
/* 198 */             PadRable8Bit padRable8Bit = new PadRable8Bit((Filter)backgroundRable8Bit, filterRegion, PadMode.ZERO_PAD);
/*     */           }  break;
/*     */         } 
/* 201 */         if ("BackgroundAlpha".equals(s)) {
/*     */           
/* 203 */           BackgroundRable8Bit backgroundRable8Bit = new BackgroundRable8Bit(filteredNode);
/* 204 */           FilterAlphaRable filterAlphaRable = new FilterAlphaRable((Filter)backgroundRable8Bit);
/* 205 */           PadRable8Bit padRable8Bit = new PadRable8Bit((Filter)filterAlphaRable, filterRegion, PadMode.ZERO_PAD);
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 9:
/* 210 */         if ("FillPaint".equals(s)) {
/*     */           
/* 212 */           Paint paint = PaintServer.convertFillPaint(filteredElement, filteredNode, ctx);
/*     */           
/* 214 */           if (paint == null) {
/* 215 */             paint = new Color(0, 0, 0, 0);
/*     */           }
/* 217 */           floodRable8Bit = new FloodRable8Bit(filterRegion, paint);
/*     */         } 
/*     */         break;
/*     */     } 
/* 221 */     if (floodRable8Bit == null)
/*     */     {
/* 223 */       filter1 = (Filter)filterMap.get(s);
/*     */     }
/* 225 */     return filter1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 232 */   static final Rectangle2D INFINITE_FILTER_REGION = new Rectangle2D.Float(-1.7014117E38F, -1.7014117E38F, Float.MAX_VALUE, Float.MAX_VALUE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static int convertInteger(Element filterElement, String attrName, int defaultValue, BridgeContext ctx) {
/* 254 */     String s = filterElement.getAttributeNS(null, attrName);
/* 255 */     if (s.length() == 0) {
/* 256 */       return defaultValue;
/*     */     }
/*     */     try {
/* 259 */       return SVGUtilities.convertSVGInteger(s);
/* 260 */     } catch (NumberFormatException nfEx) {
/* 261 */       throw new BridgeException(ctx, filterElement, nfEx, "attribute.malformed", new Object[] { attrName, s });
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static float convertNumber(Element filterElement, String attrName, float defaultValue, BridgeContext ctx) {
/* 283 */     String s = filterElement.getAttributeNS(null, attrName);
/* 284 */     if (s.length() == 0) {
/* 285 */       return defaultValue;
/*     */     }
/*     */     try {
/* 288 */       return SVGUtilities.convertSVGNumber(s);
/* 289 */     } catch (NumberFormatException nfEx) {
/* 290 */       throw new BridgeException(ctx, filterElement, nfEx, "attribute.malformed", new Object[] { attrName, s, nfEx });
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/AbstractSVGFilterPrimitiveElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */