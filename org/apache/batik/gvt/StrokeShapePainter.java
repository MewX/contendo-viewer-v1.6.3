/*     */ package org.apache.batik.gvt;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StrokeShapePainter
/*     */   implements ShapePainter
/*     */ {
/*     */   protected Shape shape;
/*     */   protected Shape strokedShape;
/*     */   protected Stroke stroke;
/*     */   protected Paint paint;
/*     */   
/*     */   public StrokeShapePainter(Shape shape) {
/*  64 */     if (shape == null) {
/*  65 */       throw new IllegalArgumentException();
/*     */     }
/*  67 */     this.shape = shape;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStroke(Stroke newStroke) {
/*  76 */     this.stroke = newStroke;
/*  77 */     this.strokedShape = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Stroke getStroke() {
/*  84 */     return this.stroke;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPaint(Paint newPaint) {
/*  93 */     this.paint = newPaint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Paint getPaint() {
/* 100 */     return this.paint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics2D g2d) {
/* 110 */     if (this.stroke != null && this.paint != null) {
/* 111 */       g2d.setPaint(this.paint);
/* 112 */       g2d.setStroke(this.stroke);
/* 113 */       g2d.draw(this.shape);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getPaintedArea() {
/* 121 */     if (this.paint == null || this.stroke == null) {
/* 122 */       return null;
/*     */     }
/* 124 */     if (this.strokedShape == null) {
/* 125 */       this.strokedShape = this.stroke.createStrokedShape(this.shape);
/*     */     }
/* 127 */     return this.strokedShape;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getPaintedBounds2D() {
/* 134 */     Shape painted = getPaintedArea();
/* 135 */     if (painted == null) {
/* 136 */       return null;
/*     */     }
/* 138 */     return painted.getBounds2D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean inPaintedArea(Point2D pt) {
/* 145 */     Shape painted = getPaintedArea();
/* 146 */     if (painted == null)
/* 147 */       return false; 
/* 148 */     return painted.contains(pt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getSensitiveArea() {
/* 155 */     if (this.stroke == null) {
/* 156 */       return null;
/*     */     }
/* 158 */     if (this.strokedShape == null) {
/* 159 */       this.strokedShape = this.stroke.createStrokedShape(this.shape);
/*     */     }
/* 161 */     return this.strokedShape;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getSensitiveBounds2D() {
/* 169 */     Shape sensitive = getSensitiveArea();
/* 170 */     if (sensitive == null) {
/* 171 */       return null;
/*     */     }
/* 173 */     return sensitive.getBounds2D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean inSensitiveArea(Point2D pt) {
/* 181 */     Shape sensitive = getSensitiveArea();
/* 182 */     if (sensitive == null)
/* 183 */       return false; 
/* 184 */     return sensitive.contains(pt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShape(Shape shape) {
/* 194 */     if (shape == null) {
/* 195 */       throw new IllegalArgumentException();
/*     */     }
/* 197 */     this.shape = shape;
/* 198 */     this.strokedShape = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getShape() {
/* 207 */     return this.shape;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/StrokeShapePainter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */