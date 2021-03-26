/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.apache.batik.dom.svg.SVGContext;
/*     */ import org.apache.batik.ext.awt.LinearGradientPaint;
/*     */ import org.apache.batik.ext.awt.MultipleGradientPaint;
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
/*     */ public class SVGLinearGradientElementBridge
/*     */   extends AbstractSVGGradientElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  52 */     return "linearGradient";
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
/*  80 */     String x1Str = SVGUtilities.getChainableAttributeNS(paintElement, null, "x1", ctx);
/*     */     
/*  82 */     if (x1Str.length() == 0) {
/*  83 */       x1Str = "0%";
/*     */     }
/*     */ 
/*     */     
/*  87 */     String y1Str = SVGUtilities.getChainableAttributeNS(paintElement, null, "y1", ctx);
/*     */     
/*  89 */     if (y1Str.length() == 0) {
/*  90 */       y1Str = "0%";
/*     */     }
/*     */ 
/*     */     
/*  94 */     String x2Str = SVGUtilities.getChainableAttributeNS(paintElement, null, "x2", ctx);
/*     */     
/*  96 */     if (x2Str.length() == 0) {
/*  97 */       x2Str = "100%";
/*     */     }
/*     */ 
/*     */     
/* 101 */     String y2Str = SVGUtilities.getChainableAttributeNS(paintElement, null, "y2", ctx);
/*     */     
/* 103 */     if (y2Str.length() == 0) {
/* 104 */       y2Str = "0%";
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 109 */     String s = SVGUtilities.getChainableAttributeNS(paintElement, null, "gradientUnits", ctx);
/*     */     
/* 111 */     if (s.length() == 0) {
/* 112 */       coordSystemType = 2;
/*     */     } else {
/* 114 */       coordSystemType = SVGUtilities.parseCoordinateSystem(paintElement, "gradientUnits", s, ctx);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     SVGContext bridge = BridgeContext.getSVGContext(paintedElement);
/* 122 */     if (coordSystemType == 2 && bridge instanceof AbstractGraphicsNodeBridge) {
/*     */ 
/*     */ 
/*     */       
/* 126 */       Rectangle2D bbox = ((AbstractGraphicsNodeBridge)bridge).getBBox();
/* 127 */       if (bbox != null && (bbox.getWidth() == 0.0D || bbox.getHeight() == 0.0D)) {
/* 128 */         return null;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 133 */     if (coordSystemType == 2) {
/* 134 */       transform = SVGUtilities.toObjectBBox(transform, paintedNode);
/*     */     }
/* 136 */     UnitProcessor.Context uctx = UnitProcessor.createContext(ctx, paintElement);
/*     */ 
/*     */     
/* 139 */     Point2D p1 = SVGUtilities.convertPoint(x1Str, "x1", y1Str, "y1", coordSystemType, uctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 146 */     Point2D p2 = SVGUtilities.convertPoint(x2Str, "x2", y2Str, "y2", coordSystemType, uctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 156 */     if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
/* 157 */       return colors[colors.length - 1];
/*     */     }
/* 159 */     return (Paint)new LinearGradientPaint(p1, p2, offsets, colors, spreadMethod, colorSpace, transform);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGLinearGradientElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */