/*     */ package org.apache.batik.swing.gvt;
/*     */ 
/*     */ import java.awt.Cursor;
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
/*     */ public abstract class AbstractPanInteractor
/*     */   extends InteractorAdapter
/*     */ {
/*  38 */   public static final Cursor PAN_CURSOR = new Cursor(13);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean finished = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int xStart;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int yStart;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int xCurrent;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int yCurrent;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Cursor previousCursor;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean endInteraction() {
/*  74 */     return this.finished;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mousePressed(MouseEvent e) {
/*  83 */     if (!this.finished) {
/*  84 */       mouseExited(e);
/*     */       
/*     */       return;
/*     */     } 
/*  88 */     this.finished = false;
/*     */     
/*  90 */     this.xStart = e.getX();
/*  91 */     this.yStart = e.getY();
/*     */     
/*  93 */     JGVTComponent c = (JGVTComponent)e.getSource();
/*     */     
/*  95 */     this.previousCursor = c.getCursor();
/*  96 */     c.setCursor(PAN_CURSOR);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {
/* 103 */     if (this.finished) {
/*     */       return;
/*     */     }
/* 106 */     this.finished = true;
/*     */     
/* 108 */     JGVTComponent c = (JGVTComponent)e.getSource();
/*     */     
/* 110 */     this.xCurrent = e.getX();
/* 111 */     this.yCurrent = e.getY();
/*     */     
/* 113 */     AffineTransform at = AffineTransform.getTranslateInstance((this.xCurrent - this.xStart), (this.yCurrent - this.yStart));
/*     */ 
/*     */     
/* 116 */     AffineTransform rt = (AffineTransform)c.getRenderingTransform().clone();
/*     */     
/* 118 */     rt.preConcatenate(at);
/* 119 */     c.setRenderingTransform(rt);
/*     */     
/* 121 */     if (c.getCursor() == PAN_CURSOR) {
/* 122 */       c.setCursor(this.previousCursor);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseExited(MouseEvent e) {
/* 130 */     this.finished = true;
/*     */     
/* 132 */     JGVTComponent c = (JGVTComponent)e.getSource();
/* 133 */     c.setPaintingTransform((AffineTransform)null);
/* 134 */     if (c.getCursor() == PAN_CURSOR) {
/* 135 */       c.setCursor(this.previousCursor);
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
/* 149 */     JGVTComponent c = (JGVTComponent)e.getSource();
/*     */     
/* 151 */     this.xCurrent = e.getX();
/* 152 */     this.yCurrent = e.getY();
/*     */     
/* 154 */     AffineTransform at = AffineTransform.getTranslateInstance((this.xCurrent - this.xStart), (this.yCurrent - this.yStart));
/*     */ 
/*     */     
/* 157 */     c.setPaintingTransform(at);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/gvt/AbstractPanInteractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */