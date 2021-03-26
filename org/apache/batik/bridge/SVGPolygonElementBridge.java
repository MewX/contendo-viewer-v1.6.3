/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import org.apache.batik.anim.dom.AnimatedLiveAttributeValue;
/*     */ import org.apache.batik.anim.dom.SVGOMAnimatedPoints;
/*     */ import org.apache.batik.anim.dom.SVGOMPolygonElement;
/*     */ import org.apache.batik.dom.svg.LiveAttributeException;
/*     */ import org.apache.batik.gvt.ShapeNode;
/*     */ import org.apache.batik.parser.AWTPolygonProducer;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.svg.SVGPoint;
/*     */ import org.w3c.dom.svg.SVGPointList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGPolygonElementBridge
/*     */   extends SVGDecoratedShapeElementBridge
/*     */ {
/*  48 */   protected static final Shape DEFAULT_SHAPE = new GeneralPath();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/*  59 */     return "polygon";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  66 */     return new SVGPolygonElementBridge();
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
/*  80 */     SVGOMPolygonElement pe = (SVGOMPolygonElement)e;
/*     */     try {
/*  82 */       SVGOMAnimatedPoints _points = pe.getSVGOMAnimatedPoints();
/*  83 */       _points.check();
/*  84 */       SVGPointList pl = _points.getAnimatedPoints();
/*  85 */       int size = pl.getNumberOfItems();
/*  86 */       if (size == 0) {
/*  87 */         shapeNode.setShape(DEFAULT_SHAPE);
/*     */       } else {
/*  89 */         AWTPolygonProducer app = new AWTPolygonProducer();
/*  90 */         app.setWindingRule(CSSUtilities.convertFillRule(e));
/*  91 */         app.startPoints();
/*  92 */         for (int i = 0; i < size; i++) {
/*  93 */           SVGPoint p = pl.getItem(i);
/*  94 */           app.point(p.getX(), p.getY());
/*     */         } 
/*  96 */         app.endPoints();
/*  97 */         shapeNode.setShape(app.getShape());
/*     */       } 
/*  99 */     } catch (LiveAttributeException ex) {
/* 100 */       throw new BridgeException(ctx, ex);
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
/* 111 */     if (alav.getNamespaceURI() == null) {
/* 112 */       String ln = alav.getLocalName();
/* 113 */       if (ln.equals("points")) {
/* 114 */         buildShape(this.ctx, this.e, (ShapeNode)this.node);
/* 115 */         handleGeometryChanged();
/*     */         return;
/*     */       } 
/*     */     } 
/* 119 */     super.handleAnimatedAttributeChanged(alav);
/*     */   }
/*     */   
/*     */   protected void handleCSSPropertyChanged(int property) {
/* 123 */     switch (property) {
/*     */       case 17:
/* 125 */         buildShape(this.ctx, this.e, (ShapeNode)this.node);
/* 126 */         handleGeometryChanged();
/*     */         return;
/*     */     } 
/* 129 */     super.handleCSSPropertyChanged(property);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGPolygonElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */