/*     */ package org.apache.batik.swing.gvt;
/*     */ 
/*     */ import java.awt.Dimension;
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
/*     */ public class AbstractRotateInteractor
/*     */   extends InteractorAdapter
/*     */ {
/*     */   protected boolean finished;
/*     */   protected double initialRotation;
/*     */   
/*     */   public boolean endInteraction() {
/*  49 */     return this.finished;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mousePressed(MouseEvent e) {
/*  58 */     this.finished = false;
/*  59 */     JGVTComponent c = (JGVTComponent)e.getSource();
/*     */     
/*  61 */     Dimension d = c.getSize();
/*  62 */     double dx = (e.getX() - d.width / 2);
/*  63 */     double dy = (e.getY() - d.height / 2);
/*  64 */     double cos = -dy / Math.sqrt(dx * dx + dy * dy);
/*  65 */     this.initialRotation = (dx > 0.0D) ? Math.acos(cos) : -Math.acos(cos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {
/*  72 */     this.finished = true;
/*  73 */     JGVTComponent c = (JGVTComponent)e.getSource();
/*     */     
/*  75 */     AffineTransform at = rotateTransform(c.getSize(), e.getX(), e.getY());
/*  76 */     at.concatenate(c.getRenderingTransform());
/*  77 */     c.setRenderingTransform(at);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseExited(MouseEvent e) {
/*  84 */     this.finished = true;
/*  85 */     JGVTComponent c = (JGVTComponent)e.getSource();
/*  86 */     c.setPaintingTransform(null);
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
/*  99 */     JGVTComponent c = (JGVTComponent)e.getSource();
/*     */     
/* 101 */     c.setPaintingTransform(rotateTransform(c.getSize(), e.getX(), e.getY()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AffineTransform rotateTransform(Dimension d, int x, int y) {
/* 108 */     double dx = (x - d.width / 2);
/* 109 */     double dy = (y - d.height / 2);
/* 110 */     double cos = -dy / Math.sqrt(dx * dx + dy * dy);
/* 111 */     double angle = (dx > 0.0D) ? Math.acos(cos) : -Math.acos(cos);
/*     */     
/* 113 */     angle -= this.initialRotation;
/*     */     
/* 115 */     return AffineTransform.getRotateInstance(angle, (d.width / 2), (d.height / 2));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/gvt/AbstractRotateInteractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */