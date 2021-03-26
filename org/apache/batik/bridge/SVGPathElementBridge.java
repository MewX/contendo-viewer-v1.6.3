/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
/*     */ import org.apache.batik.anim.dom.AnimatedLiveAttributeValue;
/*     */ import org.apache.batik.anim.dom.SVGOMAnimatedPathData;
/*     */ import org.apache.batik.anim.dom.SVGOMPathElement;
/*     */ import org.apache.batik.dom.svg.LiveAttributeException;
/*     */ import org.apache.batik.dom.svg.SVGAnimatedPathDataSupport;
/*     */ import org.apache.batik.dom.svg.SVGPathContext;
/*     */ import org.apache.batik.ext.awt.geom.PathLength;
/*     */ import org.apache.batik.gvt.ShapeNode;
/*     */ import org.apache.batik.parser.AWTPathProducer;
/*     */ import org.apache.batik.parser.PathHandler;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.svg.SVGPathSegList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGPathElementBridge
/*     */   extends SVGDecoratedShapeElementBridge
/*     */   implements SVGPathContext
/*     */ {
/*  52 */   protected static final Shape DEFAULT_SHAPE = new GeneralPath();
/*     */ 
/*     */   
/*     */   protected Shape pathLengthShape;
/*     */ 
/*     */   
/*     */   protected PathLength pathLength;
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/*  63 */     return "path";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  70 */     return new SVGPathElementBridge();
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
/*  84 */     SVGOMPathElement pe = (SVGOMPathElement)e;
/*  85 */     AWTPathProducer app = new AWTPathProducer();
/*     */     
/*     */     try {
/*  88 */       SVGOMAnimatedPathData _d = pe.getAnimatedPathData();
/*  89 */       _d.check();
/*  90 */       SVGPathSegList p = _d.getAnimatedPathSegList();
/*  91 */       app.setWindingRule(CSSUtilities.convertFillRule(e));
/*  92 */       SVGAnimatedPathDataSupport.handlePathSegList(p, (PathHandler)app);
/*  93 */     } catch (LiveAttributeException ex) {
/*  94 */       throw new BridgeException(ctx, ex);
/*     */     } finally {
/*  96 */       shapeNode.setShape(app.getShape());
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
/* 107 */     if (alav.getNamespaceURI() == null && alav.getLocalName().equals("d")) {
/*     */       
/* 109 */       buildShape(this.ctx, this.e, (ShapeNode)this.node);
/* 110 */       handleGeometryChanged();
/*     */     } else {
/* 112 */       super.handleAnimatedAttributeChanged(alav);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void handleCSSPropertyChanged(int property) {
/* 117 */     switch (property) {
/*     */       case 17:
/* 119 */         buildShape(this.ctx, this.e, (ShapeNode)this.node);
/* 120 */         handleGeometryChanged();
/*     */         return;
/*     */     } 
/* 123 */     super.handleCSSPropertyChanged(property);
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
/*     */   protected PathLength getPathLengthObj() {
/* 143 */     Shape s = ((ShapeNode)this.node).getShape();
/* 144 */     if (this.pathLengthShape != s) {
/* 145 */       this.pathLength = new PathLength(s);
/* 146 */       this.pathLengthShape = s;
/*     */     } 
/* 148 */     return this.pathLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getTotalLength() {
/* 155 */     PathLength pl = getPathLengthObj();
/* 156 */     return pl.lengthOfPath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point2D getPointAtLength(float distance) {
/* 163 */     PathLength pl = getPathLengthObj();
/* 164 */     return pl.pointAtLength(distance);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPathSegAtLength(float distance) {
/* 172 */     PathLength pl = getPathLengthObj();
/* 173 */     return pl.segmentAtLength(distance);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGPathElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */