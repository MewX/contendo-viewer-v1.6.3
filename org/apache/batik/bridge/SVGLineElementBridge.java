/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.geom.Line2D;
/*     */ import org.apache.batik.anim.dom.AbstractSVGAnimatedLength;
/*     */ import org.apache.batik.anim.dom.AnimatedLiveAttributeValue;
/*     */ import org.apache.batik.anim.dom.SVGOMLineElement;
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
/*     */ public class SVGLineElementBridge
/*     */   extends SVGDecoratedShapeElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  49 */     return "line";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  56 */     return new SVGLineElementBridge();
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
/*     */   protected ShapePainter createFillStrokePainter(BridgeContext ctx, Element e, ShapeNode shapeNode) {
/*  81 */     return PaintServer.convertStrokePainter(e, shapeNode, ctx);
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
/*     */     try {
/*  96 */       SVGOMLineElement le = (SVGOMLineElement)e;
/*     */ 
/*     */       
/*  99 */       AbstractSVGAnimatedLength _x1 = (AbstractSVGAnimatedLength)le.getX1();
/*     */       
/* 101 */       float x1 = _x1.getCheckedValue();
/*     */ 
/*     */       
/* 104 */       AbstractSVGAnimatedLength _y1 = (AbstractSVGAnimatedLength)le.getY1();
/*     */       
/* 106 */       float y1 = _y1.getCheckedValue();
/*     */ 
/*     */       
/* 109 */       AbstractSVGAnimatedLength _x2 = (AbstractSVGAnimatedLength)le.getX2();
/*     */       
/* 111 */       float x2 = _x2.getCheckedValue();
/*     */ 
/*     */       
/* 114 */       AbstractSVGAnimatedLength _y2 = (AbstractSVGAnimatedLength)le.getY2();
/*     */       
/* 116 */       float y2 = _y2.getCheckedValue();
/*     */       
/* 118 */       shapeNode.setShape(new Line2D.Float(x1, y1, x2, y2));
/* 119 */     } catch (LiveAttributeException ex) {
/* 120 */       throw new BridgeException(ctx, ex);
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
/* 131 */     if (alav.getNamespaceURI() == null) {
/* 132 */       String ln = alav.getLocalName();
/* 133 */       if (ln.equals("x1") || ln.equals("y1") || ln.equals("x2") || ln.equals("y2")) {
/*     */ 
/*     */ 
/*     */         
/* 137 */         buildShape(this.ctx, this.e, (ShapeNode)this.node);
/* 138 */         handleGeometryChanged();
/*     */         return;
/*     */       } 
/*     */     } 
/* 142 */     super.handleAnimatedAttributeChanged(alav);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGLineElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */