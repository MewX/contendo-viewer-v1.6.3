/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.RoundRectangle2D;
/*     */ import org.apache.batik.anim.dom.AbstractSVGAnimatedLength;
/*     */ import org.apache.batik.anim.dom.AnimatedLiveAttributeValue;
/*     */ import org.apache.batik.anim.dom.SVGOMRectElement;
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
/*     */ public class SVGRectElementBridge
/*     */   extends SVGShapeElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  51 */     return "rect";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  58 */     return new SVGRectElementBridge();
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
/*     */       Shape shape;
/*  73 */       SVGOMRectElement re = (SVGOMRectElement)e;
/*     */ 
/*     */       
/*  76 */       AbstractSVGAnimatedLength _x = (AbstractSVGAnimatedLength)re.getX();
/*     */       
/*  78 */       float x = _x.getCheckedValue();
/*     */ 
/*     */       
/*  81 */       AbstractSVGAnimatedLength _y = (AbstractSVGAnimatedLength)re.getY();
/*     */       
/*  83 */       float y = _y.getCheckedValue();
/*     */ 
/*     */       
/*  86 */       AbstractSVGAnimatedLength _width = (AbstractSVGAnimatedLength)re.getWidth();
/*     */       
/*  88 */       float w = _width.getCheckedValue();
/*     */ 
/*     */       
/*  91 */       AbstractSVGAnimatedLength _height = (AbstractSVGAnimatedLength)re.getHeight();
/*     */       
/*  93 */       float h = _height.getCheckedValue();
/*     */ 
/*     */       
/*  96 */       AbstractSVGAnimatedLength _rx = (AbstractSVGAnimatedLength)re.getRx();
/*     */       
/*  98 */       float rx = _rx.getCheckedValue();
/*  99 */       if (rx > w / 2.0F) {
/* 100 */         rx = w / 2.0F;
/*     */       }
/*     */ 
/*     */       
/* 104 */       AbstractSVGAnimatedLength _ry = (AbstractSVGAnimatedLength)re.getRy();
/*     */       
/* 106 */       float ry = _ry.getCheckedValue();
/* 107 */       if (ry > h / 2.0F) {
/* 108 */         ry = h / 2.0F;
/*     */       }
/*     */ 
/*     */       
/* 112 */       if (rx == 0.0F || ry == 0.0F) {
/* 113 */         shape = new Rectangle2D.Float(x, y, w, h);
/*     */       } else {
/* 115 */         shape = new RoundRectangle2D.Float(x, y, w, h, rx * 2.0F, ry * 2.0F);
/*     */       } 
/* 117 */       shapeNode.setShape(shape);
/* 118 */     } catch (LiveAttributeException ex) {
/* 119 */       throw new BridgeException(ctx, ex);
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
/* 130 */     if (alav.getNamespaceURI() == null) {
/* 131 */       String ln = alav.getLocalName();
/* 132 */       if (ln.equals("x") || ln.equals("y") || ln.equals("width") || ln.equals("height") || ln.equals("rx") || ln.equals("ry")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 138 */         buildShape(this.ctx, this.e, (ShapeNode)this.node);
/* 139 */         handleGeometryChanged();
/*     */         return;
/*     */       } 
/*     */     } 
/* 143 */     super.handleAnimatedAttributeChanged(alav);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ShapePainter createShapePainter(BridgeContext ctx, Element e, ShapeNode shapeNode) {
/* 149 */     Shape shape = shapeNode.getShape();
/* 150 */     Rectangle2D r2d = shape.getBounds2D();
/* 151 */     if (r2d.getWidth() == 0.0D || r2d.getHeight() == 0.0D)
/* 152 */       return null; 
/* 153 */     return super.createShapePainter(ctx, e, shapeNode);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGRectElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */