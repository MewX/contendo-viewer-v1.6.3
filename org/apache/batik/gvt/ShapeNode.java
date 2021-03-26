/*     */ package org.apache.batik.gvt;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.apache.batik.util.HaltingThread;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShapeNode
/*     */   extends AbstractGraphicsNode
/*     */ {
/*     */   protected Shape shape;
/*     */   protected ShapePainter shapePainter;
/*     */   private Rectangle2D primitiveBounds;
/*     */   private Rectangle2D geometryBounds;
/*     */   private Rectangle2D sensitiveBounds;
/*     */   private Shape paintedArea;
/*     */   private Shape sensitiveArea;
/*     */   
/*     */   public void setShape(Shape newShape) {
/*  86 */     fireGraphicsNodeChangeStarted();
/*  87 */     invalidateGeometryCache();
/*  88 */     this.shape = newShape;
/*  89 */     if (this.shapePainter != null) {
/*  90 */       if (newShape != null) {
/*  91 */         this.shapePainter.setShape(newShape);
/*     */       } else {
/*  93 */         this.shapePainter = null;
/*     */       } 
/*     */     }
/*  96 */     fireGraphicsNodeChangeCompleted();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getShape() {
/* 103 */     return this.shape;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShapePainter(ShapePainter newShapePainter) {
/* 113 */     if (this.shape == null)
/*     */       return; 
/* 115 */     fireGraphicsNodeChangeStarted();
/* 116 */     invalidateGeometryCache();
/* 117 */     this.shapePainter = newShapePainter;
/* 118 */     if (this.shapePainter != null && this.shape != this.shapePainter.getShape()) {
/* 119 */       this.shapePainter.setShape(this.shape);
/*     */     }
/* 121 */     fireGraphicsNodeChangeCompleted();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ShapePainter getShapePainter() {
/* 129 */     return this.shapePainter;
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
/*     */   public void paint(Graphics2D g2d) {
/* 142 */     if (this.isVisible) {
/* 143 */       super.paint(g2d);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void primitivePaint(Graphics2D g2d) {
/* 152 */     if (this.shapePainter != null) {
/* 153 */       this.shapePainter.paint(g2d);
/*     */     }
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
/*     */   protected void invalidateGeometryCache() {
/* 167 */     super.invalidateGeometryCache();
/* 168 */     this.primitiveBounds = null;
/* 169 */     this.geometryBounds = null;
/* 170 */     this.sensitiveBounds = null;
/* 171 */     this.paintedArea = null;
/* 172 */     this.sensitiveArea = null;
/*     */   }
/*     */   
/*     */   public void setPointerEventType(int pointerEventType) {
/* 176 */     super.setPointerEventType(pointerEventType);
/* 177 */     this.sensitiveBounds = null;
/* 178 */     this.sensitiveArea = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(Point2D p) {
/*     */     Rectangle2D b;
/* 187 */     switch (this.pointerEventType) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 192 */         if (!this.isVisible) return false;
/*     */       
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/* 198 */         b = getSensitiveBounds();
/* 199 */         if (b == null || !b.contains(p)) {
/* 200 */           return false;
/*     */         }
/* 202 */         return inSensitiveArea(p);
/*     */     } 
/*     */ 
/*     */     
/* 206 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean intersects(Rectangle2D r) {
/* 217 */     Rectangle2D b = getBounds();
/* 218 */     if (b != null) {
/* 219 */       return (b.intersects(r) && this.paintedArea != null && this.paintedArea.intersects(r));
/*     */     }
/*     */ 
/*     */     
/* 223 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getPrimitiveBounds() {
/* 230 */     if (!this.isVisible) return null; 
/* 231 */     if (this.shape == null) return null; 
/* 232 */     if (this.primitiveBounds != null) {
/* 233 */       return this.primitiveBounds;
/*     */     }
/* 235 */     if (this.shapePainter == null) {
/* 236 */       this.primitiveBounds = this.shape.getBounds2D();
/*     */     } else {
/* 238 */       this.primitiveBounds = this.shapePainter.getPaintedBounds2D();
/*     */     } 
/*     */     
/* 241 */     if (HaltingThread.hasBeenHalted())
/*     */     {
/*     */ 
/*     */       
/* 245 */       invalidateGeometryCache();
/*     */     }
/* 247 */     return this.primitiveBounds;
/*     */   }
/*     */   
/*     */   public boolean inSensitiveArea(Point2D pt) {
/* 251 */     if (this.shapePainter == null) {
/* 252 */       return false;
/*     */     }
/*     */     
/* 255 */     ShapePainter strokeShapePainter = null;
/* 256 */     ShapePainter fillShapePainter = null;
/* 257 */     if (this.shapePainter instanceof StrokeShapePainter) {
/* 258 */       strokeShapePainter = this.shapePainter;
/* 259 */     } else if (this.shapePainter instanceof FillShapePainter) {
/* 260 */       fillShapePainter = this.shapePainter;
/* 261 */     } else if (this.shapePainter instanceof CompositeShapePainter) {
/* 262 */       CompositeShapePainter cp = (CompositeShapePainter)this.shapePainter;
/*     */       
/* 264 */       for (int i = 0; i < cp.getShapePainterCount(); i++) {
/* 265 */         ShapePainter sp = cp.getShapePainter(i);
/* 266 */         if (sp instanceof StrokeShapePainter) {
/* 267 */           strokeShapePainter = sp;
/* 268 */         } else if (sp instanceof FillShapePainter) {
/* 269 */           fillShapePainter = sp;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 273 */       return false;
/*     */     } 
/*     */     
/* 276 */     switch (this.pointerEventType) {
/*     */       case 0:
/*     */       case 4:
/* 279 */         return this.shapePainter.inPaintedArea(pt);
/*     */       case 3:
/*     */       case 7:
/* 282 */         return this.shapePainter.inSensitiveArea(pt);
/*     */       case 1:
/*     */       case 5:
/* 285 */         if (fillShapePainter != null)
/* 286 */           return fillShapePainter.inSensitiveArea(pt); 
/*     */         break;
/*     */       case 2:
/*     */       case 6:
/* 290 */         if (strokeShapePainter != null) {
/* 291 */           return strokeShapePainter.inSensitiveArea(pt);
/*     */         }
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 297 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getSensitiveBounds() {
/* 306 */     if (this.sensitiveBounds != null) {
/* 307 */       return this.sensitiveBounds;
/*     */     }
/* 309 */     if (this.shapePainter == null) {
/* 310 */       return null;
/*     */     }
/*     */     
/* 313 */     ShapePainter strokeShapePainter = null;
/* 314 */     ShapePainter fillShapePainter = null;
/* 315 */     if (this.shapePainter instanceof StrokeShapePainter)
/* 316 */     { strokeShapePainter = this.shapePainter; }
/* 317 */     else if (this.shapePainter instanceof FillShapePainter)
/* 318 */     { fillShapePainter = this.shapePainter; }
/* 319 */     else if (this.shapePainter instanceof CompositeShapePainter)
/* 320 */     { CompositeShapePainter cp = (CompositeShapePainter)this.shapePainter;
/*     */       
/* 322 */       for (int i = 0; i < cp.getShapePainterCount(); i++) {
/* 323 */         ShapePainter sp = cp.getShapePainter(i);
/* 324 */         if (sp instanceof StrokeShapePainter) {
/* 325 */           strokeShapePainter = sp;
/* 326 */         } else if (sp instanceof FillShapePainter) {
/* 327 */           fillShapePainter = sp;
/*     */         } 
/*     */       }  }
/* 330 */     else { return null; }
/*     */ 
/*     */     
/* 333 */     switch (this.pointerEventType) {
/*     */       case 0:
/*     */       case 4:
/* 336 */         this.sensitiveBounds = this.shapePainter.getPaintedBounds2D();
/*     */         break;
/*     */       case 1:
/*     */       case 5:
/* 340 */         if (fillShapePainter != null) {
/* 341 */           this.sensitiveBounds = fillShapePainter.getSensitiveBounds2D();
/*     */         }
/*     */         break;
/*     */       case 2:
/*     */       case 6:
/* 346 */         if (strokeShapePainter != null) {
/* 347 */           this.sensitiveBounds = strokeShapePainter.getSensitiveBounds2D();
/*     */         }
/*     */         break;
/*     */       case 3:
/*     */       case 7:
/* 352 */         this.sensitiveBounds = this.shapePainter.getSensitiveBounds2D();
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 358 */     return this.sensitiveBounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getSensitiveArea() {
/* 366 */     if (this.sensitiveArea != null)
/* 367 */       return this.sensitiveArea; 
/* 368 */     if (this.shapePainter == null) {
/* 369 */       return null;
/*     */     }
/*     */     
/* 372 */     ShapePainter strokeShapePainter = null;
/* 373 */     ShapePainter fillShapePainter = null;
/* 374 */     if (this.shapePainter instanceof StrokeShapePainter)
/* 375 */     { strokeShapePainter = this.shapePainter; }
/* 376 */     else if (this.shapePainter instanceof FillShapePainter)
/* 377 */     { fillShapePainter = this.shapePainter; }
/* 378 */     else if (this.shapePainter instanceof CompositeShapePainter)
/* 379 */     { CompositeShapePainter cp = (CompositeShapePainter)this.shapePainter;
/*     */       
/* 381 */       for (int i = 0; i < cp.getShapePainterCount(); i++) {
/* 382 */         ShapePainter sp = cp.getShapePainter(i);
/* 383 */         if (sp instanceof StrokeShapePainter) {
/* 384 */           strokeShapePainter = sp;
/* 385 */         } else if (sp instanceof FillShapePainter) {
/* 386 */           fillShapePainter = sp;
/*     */         } 
/*     */       }  }
/* 389 */     else { return null; }
/*     */ 
/*     */     
/* 392 */     switch (this.pointerEventType) {
/*     */       case 0:
/*     */       case 4:
/* 395 */         this.sensitiveArea = this.shapePainter.getPaintedArea();
/*     */         break;
/*     */       case 1:
/*     */       case 5:
/* 399 */         if (fillShapePainter != null) {
/* 400 */           this.sensitiveArea = fillShapePainter.getSensitiveArea();
/*     */         }
/*     */         break;
/*     */       case 2:
/*     */       case 6:
/* 405 */         if (strokeShapePainter != null) {
/* 406 */           this.sensitiveArea = strokeShapePainter.getSensitiveArea();
/*     */         }
/*     */         break;
/*     */       case 3:
/*     */       case 7:
/* 411 */         this.sensitiveArea = this.shapePainter.getSensitiveArea();
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 417 */     return this.sensitiveArea;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getGeometryBounds() {
/* 427 */     if (this.geometryBounds == null) {
/* 428 */       if (this.shape == null) {
/* 429 */         return null;
/*     */       }
/* 431 */       this.geometryBounds = normalizeRectangle(this.shape.getBounds2D());
/*     */     } 
/* 433 */     return this.geometryBounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getOutline() {
/* 440 */     return this.shape;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/ShapeNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */