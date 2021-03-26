/*     */ package org.apache.batik.gvt;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CanvasGraphicsNode
/*     */   extends CompositeGraphicsNode
/*     */ {
/*     */   protected AffineTransform positionTransform;
/*     */   protected AffineTransform viewingTransform;
/*     */   protected Paint backgroundPaint;
/*     */   
/*     */   public void setBackgroundPaint(Paint newBackgroundPaint) {
/*  69 */     this.backgroundPaint = newBackgroundPaint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Paint getBackgroundPaint() {
/*  76 */     return this.backgroundPaint;
/*     */   }
/*     */   
/*     */   public void setPositionTransform(AffineTransform at) {
/*  80 */     fireGraphicsNodeChangeStarted();
/*  81 */     invalidateGeometryCache();
/*  82 */     this.positionTransform = at;
/*  83 */     if (this.positionTransform != null) {
/*  84 */       this.transform = new AffineTransform(this.positionTransform);
/*  85 */       if (this.viewingTransform != null)
/*  86 */         this.transform.concatenate(this.viewingTransform); 
/*  87 */     } else if (this.viewingTransform != null) {
/*  88 */       this.transform = new AffineTransform(this.viewingTransform);
/*     */     } else {
/*  90 */       this.transform = new AffineTransform();
/*     */     } 
/*  92 */     if (this.transform.getDeterminant() != 0.0D) {
/*     */       try {
/*  94 */         this.inverseTransform = this.transform.createInverse();
/*  95 */       } catch (NoninvertibleTransformException e) {
/*     */         
/*  97 */         throw new RuntimeException(e.getMessage());
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 103 */       this.inverseTransform = this.transform;
/*     */     } 
/* 105 */     fireGraphicsNodeChangeCompleted();
/*     */   }
/*     */   
/*     */   public AffineTransform getPositionTransform() {
/* 109 */     return this.positionTransform;
/*     */   }
/*     */   
/*     */   public void setViewingTransform(AffineTransform at) {
/* 113 */     fireGraphicsNodeChangeStarted();
/* 114 */     invalidateGeometryCache();
/* 115 */     this.viewingTransform = at;
/* 116 */     if (this.positionTransform != null) {
/* 117 */       this.transform = new AffineTransform(this.positionTransform);
/* 118 */       if (this.viewingTransform != null)
/* 119 */         this.transform.concatenate(this.viewingTransform); 
/* 120 */     } else if (this.viewingTransform != null) {
/* 121 */       this.transform = new AffineTransform(this.viewingTransform);
/*     */     } else {
/* 123 */       this.transform = new AffineTransform();
/*     */     } 
/* 125 */     if (this.transform.getDeterminant() != 0.0D) {
/*     */       try {
/* 127 */         this.inverseTransform = this.transform.createInverse();
/* 128 */       } catch (NoninvertibleTransformException e) {
/*     */         
/* 130 */         throw new RuntimeException(e.getMessage());
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 136 */       this.inverseTransform = this.transform;
/*     */     } 
/* 138 */     fireGraphicsNodeChangeCompleted();
/*     */   }
/*     */   
/*     */   public AffineTransform getViewingTransform() {
/* 142 */     return this.viewingTransform;
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
/*     */   public void primitivePaint(Graphics2D g2d) {
/* 155 */     if (this.backgroundPaint != null) {
/* 156 */       g2d.setPaint(this.backgroundPaint);
/* 157 */       g2d.fill(g2d.getClip());
/*     */     } 
/* 159 */     super.primitivePaint(g2d);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/CanvasGraphicsNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */