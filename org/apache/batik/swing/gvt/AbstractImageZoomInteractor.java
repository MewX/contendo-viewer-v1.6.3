/*     */ package org.apache.batik.swing.gvt;
/*     */ 
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.geom.AffineTransform;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbstractImageZoomInteractor
/*     */   extends InteractorAdapter
/*     */ {
/*     */   protected boolean finished = true;
/*     */   protected int xStart;
/*     */   protected int yStart;
/*     */   protected int xCurrent;
/*     */   protected int yCurrent;
/*     */   
/*     */   public boolean endInteraction() {
/*  63 */     return this.finished;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mousePressed(MouseEvent e) {
/*  72 */     if (!this.finished) {
/*  73 */       JGVTComponent c = (JGVTComponent)e.getSource();
/*  74 */       c.setPaintingTransform(null);
/*     */       
/*     */       return;
/*     */     } 
/*  78 */     this.finished = false;
/*     */     
/*  80 */     this.xStart = e.getX();
/*  81 */     this.yStart = e.getY();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {
/*  88 */     this.finished = true;
/*     */     
/*  90 */     JGVTComponent c = (JGVTComponent)e.getSource();
/*     */     
/*  92 */     AffineTransform pt = c.getPaintingTransform();
/*  93 */     if (pt != null) {
/*  94 */       AffineTransform rt = (AffineTransform)c.getRenderingTransform().clone();
/*  95 */       rt.preConcatenate(pt);
/*  96 */       c.setRenderingTransform(rt);
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
/*     */   public void mouseDragged(MouseEvent e) {
/*     */     double s;
/* 111 */     JGVTComponent c = (JGVTComponent)e.getSource();
/*     */     
/* 113 */     this.xCurrent = e.getX();
/* 114 */     this.yCurrent = e.getY();
/*     */     
/* 116 */     AffineTransform at = AffineTransform.getTranslateInstance(this.xStart, this.yStart);
/* 117 */     int dy = this.yCurrent - this.yStart;
/*     */     
/* 119 */     if (dy < 0) {
/* 120 */       dy -= 10;
/* 121 */       s = (dy > -15) ? 1.0D : (-15.0D / dy);
/*     */     } else {
/* 123 */       dy += 10;
/* 124 */       s = (dy < 15) ? 1.0D : (dy / 15.0D);
/*     */     } 
/*     */     
/* 127 */     at.scale(s, s);
/* 128 */     at.translate(-this.xStart, -this.yStart);
/* 129 */     c.setPaintingTransform(at);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/gvt/AbstractImageZoomInteractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */