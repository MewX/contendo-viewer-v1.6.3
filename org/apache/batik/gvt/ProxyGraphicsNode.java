/*     */ package org.apache.batik.gvt;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProxyGraphicsNode
/*     */   extends AbstractGraphicsNode
/*     */ {
/*     */   protected GraphicsNode source;
/*     */   
/*     */   public void setSource(GraphicsNode source) {
/*  52 */     this.source = source;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode getSource() {
/*  59 */     return this.source;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void primitivePaint(Graphics2D g2d) {
/*  68 */     if (this.source != null) {
/*  69 */       this.source.paint(g2d);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getPrimitiveBounds() {
/*  77 */     if (this.source == null) {
/*  78 */       return null;
/*     */     }
/*  80 */     return this.source.getBounds();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getTransformedPrimitiveBounds(AffineTransform txf) {
/*  91 */     if (this.source == null) {
/*  92 */       return null;
/*     */     }
/*  94 */     AffineTransform t = txf;
/*  95 */     if (this.transform != null) {
/*  96 */       t = new AffineTransform(txf);
/*  97 */       t.concatenate(this.transform);
/*     */     } 
/*  99 */     return this.source.getTransformedPrimitiveBounds(t);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getGeometryBounds() {
/* 109 */     if (this.source == null) {
/* 110 */       return null;
/*     */     }
/* 112 */     return this.source.getGeometryBounds();
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
/*     */   public Rectangle2D getTransformedGeometryBounds(AffineTransform txf) {
/* 126 */     if (this.source == null) {
/* 127 */       return null;
/*     */     }
/* 129 */     AffineTransform t = txf;
/* 130 */     if (this.transform != null) {
/* 131 */       t = new AffineTransform(txf);
/* 132 */       t.concatenate(this.transform);
/*     */     } 
/* 134 */     return this.source.getTransformedGeometryBounds(t);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getSensitiveBounds() {
/* 144 */     if (this.source == null) {
/* 145 */       return null;
/*     */     }
/* 147 */     return this.source.getSensitiveBounds();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getOutline() {
/* 154 */     if (this.source == null) {
/* 155 */       return null;
/*     */     }
/* 157 */     return this.source.getOutline();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/ProxyGraphicsNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */