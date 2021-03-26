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
/*     */ public class BatikStarElementBridge
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
/*  58 */     return "star";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  65 */     return (Bridge)new BatikStarElementBridge();
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
/*     */   protected void buildShape(BridgeContext ctx, Element e, ShapeNode shapeNode) {
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
/* 101 */     if (s.length() == 0) {
/* 102 */       throw new BridgeException(ctx, e, "attribute.missing", new Object[] { "r", s });
/*     */     }
/* 104 */     float r = UnitProcessor.svgOtherLengthToUserSpace(s, "r", uctx);
/*     */ 
/*     */ 
/*     */     
/* 108 */     s = e.getAttributeNS(null, "ir");
/*     */     
/* 110 */     if (s.length() == 0) {
/* 111 */       throw new BridgeException(ctx, e, "attribute.missing", new Object[] { "ir", s });
/*     */     }
/*     */ 
/*     */     
/* 115 */     float ir = UnitProcessor.svgOtherLengthToUserSpace(s, "ir", uctx);
/*     */ 
/*     */ 
/*     */     
/* 119 */     int sides = convertSides(e, "sides", 3, ctx);
/*     */     
/* 121 */     GeneralPath gp = new GeneralPath();
/*     */     
/* 123 */     double SECTOR = 6.283185307179586D / sides;
/* 124 */     double HALF_PI = 1.5707963267948966D;
/*     */     
/* 126 */     for (int i = 0; i < sides; i++) {
/* 127 */       double angle = i * SECTOR - 1.5707963267948966D;
/* 128 */       double x = cx + ir * Math.cos(angle);
/* 129 */       double y = cy - ir * Math.sin(angle);
/* 130 */       if (i == 0) {
/* 131 */         gp.moveTo((float)x, (float)y);
/*     */       } else {
/* 133 */         gp.lineTo((float)x, (float)y);
/*     */       } 
/* 135 */       angle = (i + 0.5D) * SECTOR - 1.5707963267948966D;
/* 136 */       x = cx + r * Math.cos(angle);
/* 137 */       y = cy - r * Math.sin(angle);
/* 138 */       gp.lineTo((float)x, (float)y);
/*     */     } 
/* 140 */     gp.closePath();
/*     */     
/* 142 */     shapeNode.setShape(gp);
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
/* 160 */     String s = filterElement.getAttributeNS(null, attrName);
/* 161 */     if (s.length() == 0) {
/* 162 */       return defaultValue;
/*     */     }
/* 164 */     int ret = 0;
/*     */     try {
/* 166 */       ret = SVGUtilities.convertSVGInteger(s);
/* 167 */     } catch (NumberFormatException nfEx) {
/* 168 */       throw new BridgeException(ctx, filterElement, nfEx, "attribute.malformed", new Object[] { attrName, s });
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 173 */     if (ret < 3) {
/* 174 */       throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { attrName, s });
/*     */     }
/*     */     
/* 177 */     return ret;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/BatikStarElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */