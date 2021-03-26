/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import org.apache.batik.gvt.CompositeShapePainter;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SVGDecoratedShapeElementBridge
/*     */   extends SVGShapeElementBridge
/*     */ {
/*     */   ShapePainter createFillStrokePainter(BridgeContext ctx, Element e, ShapeNode shapeNode) {
/*  58 */     return super.createShapePainter(ctx, e, shapeNode);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ShapePainter createMarkerPainter(BridgeContext ctx, Element e, ShapeNode shapeNode) {
/*  67 */     return PaintServer.convertMarkers(e, shapeNode, ctx);
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
/*     */   protected ShapePainter createShapePainter(BridgeContext ctx, Element e, ShapeNode shapeNode) {
/*  84 */     ShapePainter painter, fillAndStroke = createFillStrokePainter(ctx, e, shapeNode);
/*     */     
/*  86 */     ShapePainter markerPainter = createMarkerPainter(ctx, e, shapeNode);
/*     */     
/*  88 */     Shape shape = shapeNode.getShape();
/*     */ 
/*     */     
/*  91 */     if (markerPainter != null) {
/*  92 */       if (fillAndStroke != null) {
/*  93 */         CompositeShapePainter cp = new CompositeShapePainter(shape);
/*  94 */         cp.addShapePainter(fillAndStroke);
/*  95 */         cp.addShapePainter(markerPainter);
/*  96 */         CompositeShapePainter compositeShapePainter1 = cp;
/*     */       } else {
/*  98 */         painter = markerPainter;
/*     */       } 
/*     */     } else {
/* 101 */       painter = fillAndStroke;
/*     */     } 
/* 103 */     return painter;
/*     */   }
/*     */   
/*     */   protected void handleCSSPropertyChanged(int property) {
/* 107 */     switch (property) {
/*     */       case 34:
/*     */       case 35:
/*     */       case 36:
/* 111 */         if (!this.hasNewShapePainter) {
/* 112 */           this.hasNewShapePainter = true;
/* 113 */           ShapeNode shapeNode = (ShapeNode)this.node;
/* 114 */           shapeNode.setShapePainter(createShapePainter(this.ctx, this.e, shapeNode));
/*     */         } 
/*     */         return;
/*     */     } 
/* 118 */     super.handleCSSPropertyChanged(property);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGDecoratedShapeElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */