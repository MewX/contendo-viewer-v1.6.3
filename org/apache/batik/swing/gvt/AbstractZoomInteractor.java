/*     */ package org.apache.batik.swing.gvt;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Line2D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbstractZoomInteractor
/*     */   extends InteractorAdapter
/*     */ {
/*     */   protected boolean finished = true;
/*     */   protected int xStart;
/*     */   protected int yStart;
/*     */   protected int xCurrent;
/*     */   protected int yCurrent;
/*     */   protected Line2D markerTop;
/*     */   protected Line2D markerLeft;
/*     */   protected Line2D markerBottom;
/*     */   protected Line2D markerRight;
/*  88 */   protected Overlay overlay = new ZoomOverlay();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   protected BasicStroke markerStroke = new BasicStroke(1.0F, 2, 0, 10.0F, new float[] { 4.0F, 4.0F }, 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean endInteraction() {
/* 103 */     return this.finished;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mousePressed(MouseEvent e) {
/* 112 */     if (!this.finished) {
/* 113 */       mouseExited(e);
/*     */       
/*     */       return;
/*     */     } 
/* 117 */     this.finished = false;
/* 118 */     this.markerTop = null;
/* 119 */     this.markerLeft = null;
/* 120 */     this.markerBottom = null;
/* 121 */     this.markerRight = null;
/*     */     
/* 123 */     this.xStart = e.getX();
/* 124 */     this.yStart = e.getY();
/* 125 */     JGVTComponent c = (JGVTComponent)e.getSource();
/* 126 */     c.getOverlays().add(this.overlay);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {
/* 133 */     this.finished = true;
/* 134 */     JGVTComponent c = (JGVTComponent)e.getSource();
/* 135 */     c.getOverlays().remove(this.overlay);
/* 136 */     this.overlay.paint(c.getGraphics());
/*     */     
/* 138 */     this.xCurrent = e.getX();
/* 139 */     this.yCurrent = e.getY();
/*     */     
/* 141 */     if (this.xCurrent - this.xStart != 0 && this.yCurrent - this.yStart != 0) {
/*     */ 
/*     */       
/* 144 */       int dx = this.xCurrent - this.xStart;
/* 145 */       int dy = this.yCurrent - this.yStart;
/*     */       
/* 147 */       if (dx < 0) {
/* 148 */         dx = -dx;
/* 149 */         this.xStart = this.xCurrent;
/*     */       } 
/* 151 */       if (dy < 0) {
/* 152 */         dy = -dy;
/* 153 */         this.yStart = this.yCurrent;
/*     */       } 
/*     */       
/* 156 */       Dimension size = c.getSize();
/*     */ 
/*     */       
/* 159 */       float scaleX = size.width / dx;
/* 160 */       float scaleY = size.height / dy;
/* 161 */       float scale = (scaleX < scaleY) ? scaleX : scaleY;
/*     */ 
/*     */       
/* 164 */       AffineTransform at = new AffineTransform();
/* 165 */       at.scale(scale, scale);
/* 166 */       at.translate(-this.xStart, -this.yStart);
/*     */       
/* 168 */       at.concatenate(c.getRenderingTransform());
/* 169 */       c.setRenderingTransform(at);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseExited(MouseEvent e) {
/* 177 */     this.finished = true;
/* 178 */     JGVTComponent c = (JGVTComponent)e.getSource();
/* 179 */     c.getOverlays().remove(this.overlay);
/* 180 */     this.overlay.paint(c.getGraphics());
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
/*     */   public void mouseDragged(MouseEvent e) {
/*     */     float xMin, yMin, width, height;
/* 193 */     JGVTComponent c = (JGVTComponent)e.getSource();
/*     */     
/* 195 */     this.overlay.paint(c.getGraphics());
/*     */     
/* 197 */     this.xCurrent = e.getX();
/* 198 */     this.yCurrent = e.getY();
/*     */ 
/*     */ 
/*     */     
/* 202 */     if (this.xStart < this.xCurrent) {
/* 203 */       xMin = this.xStart;
/* 204 */       width = (this.xCurrent - this.xStart);
/*     */     } else {
/* 206 */       xMin = this.xCurrent;
/* 207 */       width = (this.xStart - this.xCurrent);
/*     */     } 
/* 209 */     if (this.yStart < this.yCurrent) {
/* 210 */       yMin = this.yStart;
/* 211 */       height = (this.yCurrent - this.yStart);
/*     */     } else {
/* 213 */       yMin = this.yCurrent;
/* 214 */       height = (this.yStart - this.yCurrent);
/*     */     } 
/* 216 */     Dimension d = c.getSize();
/* 217 */     float compAR = d.width / d.height;
/* 218 */     if (compAR > width / height) {
/* 219 */       width = compAR * height;
/*     */     } else {
/* 221 */       height = width / compAR;
/*     */     } 
/*     */     
/* 224 */     this.markerTop = new Line2D.Float(xMin, yMin, xMin + width, yMin);
/* 225 */     this.markerLeft = new Line2D.Float(xMin, yMin, xMin, yMin + height);
/* 226 */     this.markerBottom = new Line2D.Float(xMin, yMin + height, xMin + width, yMin + height);
/*     */     
/* 228 */     this.markerRight = new Line2D.Float(xMin + width, yMin, xMin + width, yMin + height);
/*     */ 
/*     */     
/* 231 */     this.overlay.paint(c.getGraphics());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class ZoomOverlay
/*     */     implements Overlay
/*     */   {
/*     */     public void paint(Graphics g) {
/* 243 */       if (AbstractZoomInteractor.this.markerTop != null) {
/* 244 */         Graphics2D g2d = (Graphics2D)g;
/*     */         
/* 246 */         g2d.setXORMode(Color.white);
/* 247 */         g2d.setColor(Color.black);
/* 248 */         g2d.setStroke(AbstractZoomInteractor.this.markerStroke);
/*     */         
/* 250 */         g2d.draw(AbstractZoomInteractor.this.markerTop);
/* 251 */         g2d.draw(AbstractZoomInteractor.this.markerLeft);
/* 252 */         g2d.draw(AbstractZoomInteractor.this.markerBottom);
/* 253 */         g2d.draw(AbstractZoomInteractor.this.markerRight);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/gvt/AbstractZoomInteractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */