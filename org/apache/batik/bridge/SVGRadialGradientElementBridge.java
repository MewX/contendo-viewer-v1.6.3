/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.apache.batik.dom.svg.SVGContext;
/*     */ import org.apache.batik.ext.awt.MultipleGradientPaint;
/*     */ import org.apache.batik.ext.awt.RadialGradientPaint;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.parser.UnitProcessor;
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
/*     */ public class SVGRadialGradientElementBridge
/*     */   extends AbstractSVGGradientElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  53 */     return "radialGradient";
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
/*     */   protected Paint buildGradient(Element paintElement, Element paintedElement, GraphicsNode paintedNode, MultipleGradientPaint.CycleMethodEnum spreadMethod, MultipleGradientPaint.ColorSpaceEnum colorSpace, AffineTransform transform, Color[] colors, float[] offsets, BridgeContext ctx) {
/*     */     short coordSystemType;
/*  81 */     String cxStr = SVGUtilities.getChainableAttributeNS(paintElement, null, "cx", ctx);
/*     */     
/*  83 */     if (cxStr.length() == 0) {
/*  84 */       cxStr = "50%";
/*     */     }
/*     */ 
/*     */     
/*  88 */     String cyStr = SVGUtilities.getChainableAttributeNS(paintElement, null, "cy", ctx);
/*     */     
/*  90 */     if (cyStr.length() == 0) {
/*  91 */       cyStr = "50%";
/*     */     }
/*     */ 
/*     */     
/*  95 */     String rStr = SVGUtilities.getChainableAttributeNS(paintElement, null, "r", ctx);
/*     */     
/*  97 */     if (rStr.length() == 0) {
/*  98 */       rStr = "50%";
/*     */     }
/*     */ 
/*     */     
/* 102 */     String fxStr = SVGUtilities.getChainableAttributeNS(paintElement, null, "fx", ctx);
/*     */     
/* 104 */     if (fxStr.length() == 0) {
/* 105 */       fxStr = cxStr;
/*     */     }
/*     */ 
/*     */     
/* 109 */     String fyStr = SVGUtilities.getChainableAttributeNS(paintElement, null, "fy", ctx);
/*     */     
/* 111 */     if (fyStr.length() == 0) {
/* 112 */       fyStr = cyStr;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 117 */     String s = SVGUtilities.getChainableAttributeNS(paintElement, null, "gradientUnits", ctx);
/*     */     
/* 119 */     if (s.length() == 0) {
/* 120 */       coordSystemType = 2;
/*     */     } else {
/* 122 */       coordSystemType = SVGUtilities.parseCoordinateSystem(paintElement, "gradientUnits", s, ctx);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     SVGContext bridge = BridgeContext.getSVGContext(paintedElement);
/* 130 */     if (coordSystemType == 2 && bridge instanceof AbstractGraphicsNodeBridge) {
/*     */ 
/*     */ 
/*     */       
/* 134 */       Rectangle2D bbox = ((AbstractGraphicsNodeBridge)bridge).getBBox();
/* 135 */       if (bbox != null && (bbox.getWidth() == 0.0D || bbox.getHeight() == 0.0D)) {
/* 136 */         return null;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 141 */     if (coordSystemType == 2) {
/* 142 */       transform = SVGUtilities.toObjectBBox(transform, paintedNode);
/*     */     }
/*     */     
/* 145 */     UnitProcessor.Context uctx = UnitProcessor.createContext(ctx, paintElement);
/*     */ 
/*     */     
/* 148 */     float r = SVGUtilities.convertLength(rStr, "r", coordSystemType, uctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 154 */     if (r == 0.0F) {
/* 155 */       return colors[colors.length - 1];
/*     */     }
/* 157 */     Point2D c = SVGUtilities.convertPoint(cxStr, "cx", cyStr, "cy", coordSystemType, uctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 164 */     Point2D f = SVGUtilities.convertPoint(fxStr, "fx", fyStr, "fy", coordSystemType, uctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 172 */     return (Paint)new RadialGradientPaint(c, r, f, offsets, colors, spreadMethod, RadialGradientPaint.SRGB, transform);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGRadialGradientElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */