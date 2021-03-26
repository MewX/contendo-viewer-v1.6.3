/*     */ package org.apache.batik.ext.awt.geom;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.PathIterator;
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
/*     */ public class ShapeExtender
/*     */   implements ExtendedShape
/*     */ {
/*     */   Shape shape;
/*     */   
/*     */   public ShapeExtender(Shape shape) {
/*  38 */     this.shape = shape;
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/*  42 */     return this.shape.contains(x, y);
/*     */   }
/*     */   public boolean contains(double x, double y, double w, double h) {
/*  45 */     return this.shape.contains(x, y, w, h);
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D p) {
/*  49 */     return this.shape.contains(p);
/*     */   }
/*     */   
/*     */   public boolean contains(Rectangle2D r) {
/*  53 */     return this.shape.contains(r);
/*     */   }
/*     */   
/*     */   public Rectangle getBounds() {
/*  57 */     return this.shape.getBounds();
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/*  61 */     return this.shape.getBounds2D();
/*     */   }
/*     */   
/*     */   public PathIterator getPathIterator(AffineTransform at) {
/*  65 */     return this.shape.getPathIterator(at);
/*     */   }
/*     */   
/*     */   public PathIterator getPathIterator(AffineTransform at, double flatness) {
/*  69 */     return this.shape.getPathIterator(at, flatness);
/*     */   }
/*     */   
/*     */   public ExtendedPathIterator getExtendedPathIterator() {
/*  73 */     return new EPIWrap(this.shape.getPathIterator(null));
/*     */   }
/*     */   
/*     */   public boolean intersects(double x, double y, double w, double h) {
/*  77 */     return this.shape.intersects(x, y, w, h);
/*     */   }
/*     */   
/*     */   public boolean intersects(Rectangle2D r) {
/*  81 */     return this.shape.intersects(r);
/*     */   }
/*     */   
/*     */   public static class EPIWrap
/*     */     implements ExtendedPathIterator {
/*  86 */     PathIterator pi = null;
/*     */     public EPIWrap(PathIterator pi) {
/*  88 */       this.pi = pi;
/*     */     }
/*     */     
/*     */     public int currentSegment() {
/*  92 */       float[] coords = new float[6];
/*  93 */       return this.pi.currentSegment(coords);
/*     */     }
/*     */     
/*     */     public int currentSegment(double[] coords) {
/*  97 */       return this.pi.currentSegment(coords);
/*     */     }
/*     */     public int currentSegment(float[] coords) {
/* 100 */       return this.pi.currentSegment(coords);
/*     */     }
/*     */     public int getWindingRule() {
/* 103 */       return this.pi.getWindingRule();
/*     */     }
/*     */     
/*     */     public boolean isDone() {
/* 107 */       return this.pi.isDone();
/*     */     }
/*     */     public void next() {
/* 110 */       this.pi.next();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/geom/ShapeExtender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */