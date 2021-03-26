/*     */ package org.apache.batik.extension.svg;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import org.apache.batik.bridge.Bridge;
/*     */ import org.apache.batik.bridge.BridgeContext;
/*     */ import org.apache.batik.bridge.BridgeException;
/*     */ import org.apache.batik.bridge.SVGDecoratedShapeElementBridge;
/*     */ import org.apache.batik.bridge.SVGUtilities;
/*     */ import org.apache.batik.bridge.UnitProcessor;
/*     */ import org.apache.batik.gvt.ShapeNode;
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
/*     */ public class BatikRegularPolygonElementBridge
/*     */   extends SVGDecoratedShapeElementBridge
/*     */   implements BatikExtConstants
/*     */ {
/*     */   public String getNamespaceURI() {
/*  51 */     return "http://xml.apache.org/batik/ext";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/*  58 */     return "regularPolygon";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  65 */     return (Bridge)new BatikRegularPolygonElementBridge();
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
/*     */   protected void buildShape(BridgeContext ctx, Element e, ShapeNode shapeNode) {
/*     */     float r;
/*  79 */     UnitProcessor.Context uctx = UnitProcessor.createContext(ctx, e);
/*     */ 
/*     */ 
/*     */     
/*  83 */     String s = e.getAttributeNS(null, "cx");
/*  84 */     float cx = 0.0F;
/*  85 */     if (s.length() != 0) {
/*  86 */       cx = UnitProcessor.svgHorizontalCoordinateToUserSpace(s, "cx", uctx);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  91 */     s = e.getAttributeNS(null, "cy");
/*  92 */     float cy = 0.0F;
/*  93 */     if (s.length() != 0) {
/*  94 */       cy = UnitProcessor.svgVerticalCoordinateToUserSpace(s, "cy", uctx);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  99 */     s = e.getAttributeNS(null, "r");
/*     */     
/* 101 */     if (s.length() != 0) {
/* 102 */       r = UnitProcessor.svgOtherLengthToUserSpace(s, "r", uctx);
/*     */     } else {
/*     */       
/* 105 */       throw new BridgeException(ctx, e, "attribute.missing", new Object[] { "r", s });
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 110 */     int sides = convertSides(e, "sides", 3, ctx);
/*     */     
/* 112 */     GeneralPath gp = new GeneralPath();
/* 113 */     for (int i = 0; i < sides; i++) {
/* 114 */       double angle = (i + 0.5D) * 6.283185307179586D / sides - 1.5707963267948966D;
/* 115 */       double x = cx + r * Math.cos(angle);
/* 116 */       double y = cy - r * Math.sin(angle);
/* 117 */       if (i == 0) {
/* 118 */         gp.moveTo((float)x, (float)y);
/*     */       } else {
/* 120 */         gp.lineTo((float)x, (float)y);
/*     */       } 
/* 122 */     }  gp.closePath();
/*     */     
/* 124 */     shapeNode.setShape(gp);
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
/*     */   protected static int convertSides(Element filterElement, String attrName, int defaultValue, BridgeContext ctx) {
/* 142 */     String s = filterElement.getAttributeNS(null, attrName);
/* 143 */     if (s.length() == 0) {
/* 144 */       return defaultValue;
/*     */     }
/* 146 */     int ret = 0;
/*     */     try {
/* 148 */       ret = SVGUtilities.convertSVGInteger(s);
/* 149 */     } catch (NumberFormatException nfEx) {
/* 150 */       throw new BridgeException(ctx, filterElement, nfEx, "attribute.malformed", new Object[] { attrName, s });
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 155 */     if (ret < 3) {
/* 156 */       throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { attrName, s });
/*     */     }
/*     */     
/* 159 */     return ret;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/BatikRegularPolygonElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */