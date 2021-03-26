/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.lang.ref.SoftReference;
/*     */ import org.apache.batik.gvt.AbstractGraphicsNode;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.util.SVGConstants;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MultiResGraphicsNode
/*     */   extends AbstractGraphicsNode
/*     */   implements SVGConstants
/*     */ {
/*     */   SoftReference[] srcs;
/*     */   Element[] srcElems;
/*     */   Dimension[] minSz;
/*     */   Dimension[] maxSz;
/*     */   Rectangle2D bounds;
/*     */   BridgeContext ctx;
/*     */   Element multiImgElem;
/*     */   
/*     */   public MultiResGraphicsNode(Element multiImgElem, Rectangle2D bounds, Element[] srcElems, Dimension[] minSz, Dimension[] maxSz, BridgeContext ctx) {
/*  60 */     this.multiImgElem = multiImgElem;
/*  61 */     this.srcElems = new Element[srcElems.length];
/*  62 */     this.minSz = new Dimension[srcElems.length];
/*  63 */     this.maxSz = new Dimension[srcElems.length];
/*  64 */     this.ctx = ctx;
/*     */     
/*  66 */     for (int i = 0; i < srcElems.length; i++) {
/*  67 */       this.srcElems[i] = srcElems[i];
/*  68 */       this.minSz[i] = minSz[i];
/*  69 */       this.maxSz[i] = maxSz[i];
/*     */     } 
/*     */     
/*  72 */     this.srcs = new SoftReference[srcElems.length];
/*  73 */     this.bounds = bounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void primitivePaint(Graphics2D g2d) {
/*     */     double gnDevX0, gnDevX1, gnDevY0, gnDevY1;
/*  83 */     AffineTransform at = g2d.getTransform();
/*     */     
/*  85 */     double scx = Math.sqrt(at.getShearY() * at.getShearY() + at.getScaleX() * at.getScaleX());
/*     */     
/*  87 */     double scy = Math.sqrt(at.getShearX() * at.getShearX() + at.getScaleY() * at.getScaleY());
/*     */ 
/*     */     
/*  90 */     GraphicsNode gn = null;
/*  91 */     int idx = -1;
/*  92 */     double w = this.bounds.getWidth() * scx;
/*  93 */     double minDist = calcDist(w, this.minSz[0], this.maxSz[0]);
/*  94 */     int minIdx = 0;
/*     */     
/*  96 */     for (int i = 0; i < this.minSz.length; i++) {
/*  97 */       double dist = calcDist(w, this.minSz[i], this.maxSz[i]);
/*     */       
/*  99 */       if (dist < minDist) {
/* 100 */         minDist = dist;
/* 101 */         minIdx = i;
/*     */       } 
/*     */       
/* 104 */       if ((this.minSz[i] == null || w >= (this.minSz[i]).width) && (this.maxSz[i] == null || w <= (this.maxSz[i]).width))
/*     */       {
/*     */ 
/*     */ 
/*     */         
/* 109 */         if (idx == -1 || minIdx == i) {
/* 110 */           idx = i;
/*     */         }
/*     */       }
/*     */     } 
/*     */     
/* 115 */     if (idx == -1)
/* 116 */       idx = minIdx; 
/* 117 */     gn = getGraphicsNode(idx);
/* 118 */     if (gn == null) {
/*     */       return;
/*     */     }
/*     */     
/* 122 */     Rectangle2D gnBounds = gn.getBounds();
/* 123 */     if (gnBounds == null)
/*     */       return; 
/* 125 */     double gnDevW = gnBounds.getWidth() * scx;
/* 126 */     double gnDevH = gnBounds.getHeight() * scy;
/* 127 */     double gnDevX = gnBounds.getX() * scx;
/* 128 */     double gnDevY = gnBounds.getY() * scy;
/*     */     
/* 130 */     if (gnDevW < 0.0D) {
/* 131 */       gnDevX0 = gnDevX + gnDevW;
/* 132 */       gnDevX1 = gnDevX;
/*     */     } else {
/* 134 */       gnDevX0 = gnDevX;
/* 135 */       gnDevX1 = gnDevX + gnDevW;
/*     */     } 
/* 137 */     if (gnDevH < 0.0D) {
/* 138 */       gnDevY0 = gnDevY + gnDevH;
/* 139 */       gnDevY1 = gnDevY;
/*     */     } else {
/* 141 */       gnDevY0 = gnDevY;
/* 142 */       gnDevY1 = gnDevY + gnDevH;
/*     */     } 
/*     */ 
/*     */     
/* 146 */     gnDevW = (int)(Math.ceil(gnDevX1) - Math.floor(gnDevX0));
/* 147 */     gnDevH = (int)(Math.ceil(gnDevY1) - Math.floor(gnDevY0));
/* 148 */     scx = gnDevW / gnBounds.getWidth() / scx;
/* 149 */     scy = gnDevH / gnBounds.getHeight() / scy;
/*     */ 
/*     */ 
/*     */     
/* 153 */     AffineTransform nat = g2d.getTransform();
/* 154 */     nat = new AffineTransform(nat.getScaleX() * scx, nat.getShearY() * scx, nat.getShearX() * scy, nat.getScaleY() * scy, nat.getTranslateX(), nat.getTranslateY());
/*     */ 
/*     */     
/* 157 */     g2d.setTransform(nat);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 163 */     gn.paint(g2d);
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
/*     */   public double calcDist(double loc, Dimension min, Dimension max) {
/* 178 */     if (min == null) {
/* 179 */       if (max == null) {
/* 180 */         return 1.0E11D;
/*     */       }
/* 182 */       return Math.abs(loc - max.width);
/*     */     } 
/* 184 */     if (max == null) {
/* 185 */       return Math.abs(loc - min.width);
/*     */     }
/* 187 */     double mid = (max.width + min.width) / 2.0D;
/* 188 */     return Math.abs(loc - mid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getPrimitiveBounds() {
/* 197 */     return this.bounds;
/*     */   }
/*     */   
/*     */   public Rectangle2D getGeometryBounds() {
/* 201 */     return this.bounds;
/*     */   }
/*     */   
/*     */   public Rectangle2D getSensitiveBounds() {
/* 205 */     return this.bounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getOutline() {
/* 212 */     return this.bounds;
/*     */   }
/*     */   
/*     */   public GraphicsNode getGraphicsNode(int idx) {
/* 216 */     if (this.srcs[idx] != null) {
/* 217 */       Object o = this.srcs[idx].get();
/* 218 */       if (o != null) {
/* 219 */         return (GraphicsNode)o;
/*     */       }
/*     */     } 
/*     */     
/* 223 */     try { GVTBuilder builder = this.ctx.getGVTBuilder();
/*     */       
/* 225 */       GraphicsNode gn = builder.build(this.ctx, this.srcElems[idx]);
/* 226 */       this.srcs[idx] = new SoftReference<GraphicsNode>(gn);
/* 227 */       return gn; }
/* 228 */     catch (Exception ex) { ex.printStackTrace();
/*     */       
/* 230 */       return null; }
/*     */   
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/MultiResGraphicsNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */