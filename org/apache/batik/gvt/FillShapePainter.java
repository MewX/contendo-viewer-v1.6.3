/*     */ package org.apache.batik.gvt;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Point2D;
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
/*     */ public class FillShapePainter
/*     */   implements ShapePainter
/*     */ {
/*     */   protected Shape shape;
/*     */   protected Paint paint;
/*     */   
/*     */   public FillShapePainter(Shape shape) {
/*  53 */     if (shape == null) {
/*  54 */       throw new IllegalArgumentException("Shape can not be null!");
/*     */     }
/*  56 */     this.shape = shape;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPaint(Paint newPaint) {
/*  65 */     this.paint = newPaint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Paint getPaint() {
/*  72 */     return this.paint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics2D g2d) {
/*  81 */     if (this.paint != null) {
/*  82 */       g2d.setPaint(this.paint);
/*  83 */       g2d.fill(this.shape);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getPaintedArea() {
/*  91 */     if (this.paint == null)
/*  92 */       return null; 
/*  93 */     return this.shape;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getPaintedBounds2D() {
/* 100 */     if (this.paint == null || this.shape == null) {
/* 101 */       return null;
/*     */     }
/* 103 */     return this.shape.getBounds2D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean inPaintedArea(Point2D pt) {
/* 110 */     if (this.paint == null || this.shape == null) {
/* 111 */       return false;
/*     */     }
/* 113 */     return this.shape.contains(pt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getSensitiveArea() {
/* 121 */     return this.shape;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getSensitiveBounds2D() {
/* 129 */     if (this.shape == null)
/* 130 */       return null; 
/* 131 */     return this.shape.getBounds2D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean inSensitiveArea(Point2D pt) {
/* 138 */     if (this.shape == null)
/* 139 */       return false; 
/* 140 */     return this.shape.contains(pt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShape(Shape shape) {
/* 150 */     if (shape == null) {
/* 151 */       throw new IllegalArgumentException();
/*     */     }
/* 153 */     this.shape = shape;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getShape() {
/* 162 */     return this.shape;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/FillShapePainter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */