/*     */ package org.apache.batik.ext.awt.image.renderable;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.util.Map;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DeferRable
/*     */   implements Filter
/*     */ {
/*     */   volatile Filter src;
/*     */   Rectangle2D bounds;
/*     */   Map props;
/*     */   
/*     */   public synchronized Filter getSource() {
/*  55 */     while (this.src == null) {
/*     */       
/*     */       try {
/*  58 */         wait();
/*  59 */       } catch (InterruptedException interruptedException) {}
/*     */     } 
/*     */ 
/*     */     
/*  63 */     return this.src;
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
/*     */   public synchronized void setSource(Filter src) {
/*  77 */     if (this.src != null)
/*  78 */       return;  this.src = src;
/*  79 */     this.bounds = src.getBounds2D();
/*  80 */     notifyAll();
/*     */   }
/*     */   
/*     */   public synchronized void setBounds(Rectangle2D bounds) {
/*  84 */     if (this.bounds != null)
/*  85 */       return;  this.bounds = bounds;
/*  86 */     notifyAll();
/*     */   }
/*     */   
/*     */   public synchronized void setProperties(Map props) {
/*  90 */     this.props = props;
/*  91 */     notifyAll();
/*     */   }
/*     */   
/*     */   public long getTimeStamp() {
/*  95 */     return getSource().getTimeStamp();
/*     */   }
/*     */   
/*     */   public Vector getSources() {
/*  99 */     return getSource().getSources();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDynamic() {
/* 106 */     return getSource().isDynamic();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/* 114 */     synchronized (this) {
/* 115 */       while (this.src == null && this.bounds == null) {
/*     */         
/*     */         try {
/* 118 */           wait();
/*     */         }
/* 120 */         catch (InterruptedException interruptedException) {}
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 125 */     if (this.src != null)
/* 126 */       return this.src.getBounds2D(); 
/* 127 */     return this.bounds;
/*     */   }
/*     */   
/*     */   public float getMinX() {
/* 131 */     return (float)getBounds2D().getX();
/*     */   }
/*     */   public float getMinY() {
/* 134 */     return (float)getBounds2D().getY();
/*     */   }
/*     */   public float getWidth() {
/* 137 */     return (float)getBounds2D().getWidth();
/*     */   }
/*     */   public float getHeight() {
/* 140 */     return (float)getBounds2D().getHeight();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getProperty(String name) {
/* 147 */     synchronized (this) {
/* 148 */       while (this.src == null && this.props == null) {
/*     */         
/*     */         try {
/* 151 */           wait();
/* 152 */         } catch (InterruptedException interruptedException) {}
/*     */       } 
/*     */     } 
/* 155 */     if (this.src != null)
/* 156 */       return this.src.getProperty(name); 
/* 157 */     return this.props.get(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getPropertyNames() {
/* 164 */     synchronized (this) {
/* 165 */       while (this.src == null && this.props == null) {
/*     */         
/*     */         try {
/* 168 */           wait();
/* 169 */         } catch (InterruptedException interruptedException) {}
/*     */       } 
/*     */     } 
/* 172 */     if (this.src != null) {
/* 173 */       return this.src.getPropertyNames();
/*     */     }
/* 175 */     String[] ret = new String[this.props.size()];
/* 176 */     this.props.keySet().toArray((Object[])ret);
/* 177 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderedImage createDefaultRendering() {
/* 184 */     return getSource().createDefaultRendering();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderedImage createScaledRendering(int w, int h, RenderingHints hints) {
/* 192 */     return getSource().createScaledRendering(w, h, hints);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderedImage createRendering(RenderContext rc) {
/* 199 */     return getSource().createRendering(rc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getDependencyRegion(int srcIndex, Rectangle2D outputRgn) {
/* 207 */     return getSource().getDependencyRegion(srcIndex, outputRgn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getDirtyRegion(int srcIndex, Rectangle2D inputRgn) {
/* 215 */     return getSource().getDirtyRegion(srcIndex, inputRgn);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/DeferRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */