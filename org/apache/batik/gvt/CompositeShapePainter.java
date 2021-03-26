/*     */ package org.apache.batik.gvt;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Area;
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
/*     */ public class CompositeShapePainter
/*     */   implements ShapePainter
/*     */ {
/*     */   protected Shape shape;
/*     */   protected ShapePainter[] painters;
/*     */   protected int count;
/*     */   
/*     */   public CompositeShapePainter(Shape shape) {
/*  54 */     if (shape == null) {
/*  55 */       throw new IllegalArgumentException();
/*     */     }
/*  57 */     this.shape = shape;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addShapePainter(ShapePainter shapePainter) {
/*  66 */     if (shapePainter == null) {
/*     */       return;
/*     */     }
/*  69 */     if (this.shape != shapePainter.getShape()) {
/*  70 */       shapePainter.setShape(this.shape);
/*     */     }
/*  72 */     if (this.painters == null) {
/*  73 */       this.painters = new ShapePainter[2];
/*     */     }
/*  75 */     if (this.count == this.painters.length) {
/*  76 */       ShapePainter[] newPainters = new ShapePainter[this.count + this.count / 2 + 1];
/*  77 */       System.arraycopy(this.painters, 0, newPainters, 0, this.count);
/*  78 */       this.painters = newPainters;
/*     */     } 
/*  80 */     this.painters[this.count++] = shapePainter;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public ShapePainter getShapePainter(int index) {
/* 108 */     return this.painters[index];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getShapePainterCount() {
/* 115 */     return this.count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics2D g2d) {
/* 124 */     if (this.painters != null) {
/* 125 */       for (int i = 0; i < this.count; i++) {
/* 126 */         this.painters[i].paint(g2d);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getPaintedArea() {
/* 135 */     if (this.painters == null)
/* 136 */       return null; 
/* 137 */     Area paintedArea = new Area();
/* 138 */     for (int i = 0; i < this.count; i++) {
/* 139 */       Shape s = this.painters[i].getPaintedArea();
/* 140 */       if (s != null) {
/* 141 */         paintedArea.add(new Area(s));
/*     */       }
/*     */     } 
/* 144 */     return paintedArea;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getPaintedBounds2D() {
/* 151 */     if (this.painters == null) {
/* 152 */       return null;
/*     */     }
/* 154 */     Rectangle2D bounds = null;
/* 155 */     for (int i = 0; i < this.count; i++) {
/* 156 */       Rectangle2D pb = this.painters[i].getPaintedBounds2D();
/* 157 */       if (pb != null)
/* 158 */         if (bounds == null) { bounds = (Rectangle2D)pb.clone(); }
/* 159 */         else { bounds.add(pb); }
/*     */          
/* 161 */     }  return bounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean inPaintedArea(Point2D pt) {
/* 168 */     if (this.painters == null)
/* 169 */       return false; 
/* 170 */     for (int i = 0; i < this.count; i++) {
/* 171 */       if (this.painters[i].inPaintedArea(pt))
/* 172 */         return true; 
/*     */     } 
/* 174 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getSensitiveArea() {
/* 182 */     if (this.painters == null)
/* 183 */       return null; 
/* 184 */     Area paintedArea = new Area();
/* 185 */     for (int i = 0; i < this.count; i++) {
/* 186 */       Shape s = this.painters[i].getSensitiveArea();
/* 187 */       if (s != null) {
/* 188 */         paintedArea.add(new Area(s));
/*     */       }
/*     */     } 
/* 191 */     return paintedArea;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getSensitiveBounds2D() {
/* 198 */     if (this.painters == null) {
/* 199 */       return null;
/*     */     }
/* 201 */     Rectangle2D bounds = null;
/* 202 */     for (int i = 0; i < this.count; i++) {
/* 203 */       Rectangle2D pb = this.painters[i].getSensitiveBounds2D();
/* 204 */       if (pb != null)
/* 205 */         if (bounds == null) { bounds = (Rectangle2D)pb.clone(); }
/* 206 */         else { bounds.add(pb); }
/*     */          
/* 208 */     }  return bounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean inSensitiveArea(Point2D pt) {
/* 215 */     if (this.painters == null)
/* 216 */       return false; 
/* 217 */     for (int i = 0; i < this.count; i++) {
/* 218 */       if (this.painters[i].inSensitiveArea(pt))
/* 219 */         return true; 
/*     */     } 
/* 221 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShape(Shape shape) {
/* 231 */     if (shape == null) {
/* 232 */       throw new IllegalArgumentException();
/*     */     }
/* 234 */     if (this.painters != null) {
/* 235 */       for (int i = 0; i < this.count; i++) {
/* 236 */         this.painters[i].setShape(shape);
/*     */       }
/*     */     }
/* 239 */     this.shape = shape;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getShape() {
/* 248 */     return this.shape;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/CompositeShapePainter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */