/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.apache.batik.anim.dom.AbstractSVGAnimatedLength;
/*     */ import org.apache.batik.anim.dom.AnimatedLiveAttributeValue;
/*     */ import org.apache.batik.anim.dom.SVGOMCircleElement;
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
/*     */ public class SVGCircleElementBridge
/*     */   extends SVGShapeElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  50 */     return "circle";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  57 */     return new SVGCircleElementBridge();
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
/*  71 */       SVGOMCircleElement ce = (SVGOMCircleElement)e;
/*     */ 
/*     */       
/*  74 */       AbstractSVGAnimatedLength _cx = (AbstractSVGAnimatedLength)ce.getCx();
/*     */       
/*  76 */       float cx = _cx.getCheckedValue();
/*     */ 
/*     */       
/*  79 */       AbstractSVGAnimatedLength _cy = (AbstractSVGAnimatedLength)ce.getCy();
/*     */       
/*  81 */       float cy = _cy.getCheckedValue();
/*     */ 
/*     */       
/*  84 */       AbstractSVGAnimatedLength _r = (AbstractSVGAnimatedLength)ce.getR();
/*     */       
/*  86 */       float r = _r.getCheckedValue();
/*     */       
/*  88 */       float x = cx - r;
/*  89 */       float y = cy - r;
/*  90 */       float w = r * 2.0F;
/*  91 */       shapeNode.setShape(new Ellipse2D.Float(x, y, w, w));
/*  92 */     } catch (LiveAttributeException ex) {
/*  93 */       throw new BridgeException(ctx, ex);
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
/* 104 */     if (alav.getNamespaceURI() == null) {
/* 105 */       String ln = alav.getLocalName();
/* 106 */       if (ln.equals("cx") || ln.equals("cy") || ln.equals("r")) {
/*     */ 
/*     */         
/* 109 */         buildShape(this.ctx, this.e, (ShapeNode)this.node);
/* 110 */         handleGeometryChanged();
/*     */         return;
/*     */       } 
/*     */     } 
/* 114 */     super.handleAnimatedAttributeChanged(alav);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ShapePainter createShapePainter(BridgeContext ctx, Element e, ShapeNode shapeNode) {
/* 120 */     Rectangle2D r2d = shapeNode.getShape().getBounds2D();
/* 121 */     if (r2d.getWidth() == 0.0D || r2d.getHeight() == 0.0D)
/* 122 */       return null; 
/* 123 */     return super.createShapePainter(ctx, e, shapeNode);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGCircleElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */