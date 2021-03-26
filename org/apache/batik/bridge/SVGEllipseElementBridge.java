/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.apache.batik.anim.dom.AbstractSVGAnimatedLength;
/*     */ import org.apache.batik.anim.dom.AnimatedLiveAttributeValue;
/*     */ import org.apache.batik.anim.dom.SVGOMEllipseElement;
/*     */ import org.apache.batik.dom.svg.LiveAttributeException;
/*     */ import org.apache.batik.gvt.ShapeNode;
/*     */ import org.apache.batik.gvt.ShapePainter;
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
/*     */ public class SVGEllipseElementBridge
/*     */   extends SVGShapeElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  50 */     return "ellipse";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  57 */     return new SVGEllipseElementBridge();
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
/*     */     try {
/*  71 */       SVGOMEllipseElement ee = (SVGOMEllipseElement)e;
/*     */ 
/*     */       
/*  74 */       AbstractSVGAnimatedLength _cx = (AbstractSVGAnimatedLength)ee.getCx();
/*     */       
/*  76 */       float cx = _cx.getCheckedValue();
/*     */ 
/*     */       
/*  79 */       AbstractSVGAnimatedLength _cy = (AbstractSVGAnimatedLength)ee.getCy();
/*     */       
/*  81 */       float cy = _cy.getCheckedValue();
/*     */ 
/*     */       
/*  84 */       AbstractSVGAnimatedLength _rx = (AbstractSVGAnimatedLength)ee.getRx();
/*     */       
/*  86 */       float rx = _rx.getCheckedValue();
/*     */ 
/*     */       
/*  89 */       AbstractSVGAnimatedLength _ry = (AbstractSVGAnimatedLength)ee.getRy();
/*     */       
/*  91 */       float ry = _ry.getCheckedValue();
/*     */       
/*  93 */       shapeNode.setShape(new Ellipse2D.Float(cx - rx, cy - ry, rx * 2.0F, ry * 2.0F));
/*     */     }
/*  95 */     catch (LiveAttributeException ex) {
/*  96 */       throw new BridgeException(ctx, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleAnimatedAttributeChanged(AnimatedLiveAttributeValue alav) {
/* 107 */     if (alav.getNamespaceURI() == null) {
/* 108 */       String ln = alav.getLocalName();
/* 109 */       if (ln.equals("cx") || ln.equals("cy") || ln.equals("rx") || ln.equals("ry")) {
/*     */ 
/*     */ 
/*     */         
/* 113 */         buildShape(this.ctx, this.e, (ShapeNode)this.node);
/* 114 */         handleGeometryChanged();
/*     */         return;
/*     */       } 
/*     */     } 
/* 118 */     super.handleAnimatedAttributeChanged(alav);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ShapePainter createShapePainter(BridgeContext ctx, Element e, ShapeNode shapeNode) {
/* 124 */     Rectangle2D r2d = shapeNode.getShape().getBounds2D();
/* 125 */     if (r2d.getWidth() == 0.0D || r2d.getHeight() == 0.0D)
/* 126 */       return null; 
/* 127 */     return super.createShapePainter(ctx, e, shapeNode);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGEllipseElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */