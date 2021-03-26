/*     */ package org.apache.batik.gvt;
/*     */ 
/*     */ import java.awt.Graphics2D;
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
/*     */ public class ImageNode
/*     */   extends CompositeGraphicsNode
/*     */ {
/*     */   protected boolean hitCheckChildren = false;
/*     */   
/*     */   public void setVisible(boolean isVisible) {
/*  41 */     fireGraphicsNodeChangeStarted();
/*  42 */     this.isVisible = isVisible;
/*  43 */     invalidateGeometryCache();
/*  44 */     fireGraphicsNodeChangeCompleted();
/*     */   }
/*     */   
/*     */   public Rectangle2D getPrimitiveBounds() {
/*  48 */     if (!this.isVisible) return null; 
/*  49 */     return super.getPrimitiveBounds();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHitCheckChildren(boolean hitCheckChildren) {
/*  57 */     this.hitCheckChildren = hitCheckChildren;
/*     */   }
/*     */   
/*     */   public boolean getHitCheckChildren() {
/*  61 */     return this.hitCheckChildren;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics2D g2d) {
/*  70 */     if (this.isVisible) {
/*  71 */       super.paint(g2d);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(Point2D p) {
/*  82 */     switch (this.pointerEventType) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*  87 */         return (this.isVisible && super.contains(p));
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/*  92 */         return super.contains(p);
/*     */       case 8:
/*  94 */         return false;
/*     */     } 
/*  96 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode nodeHitAt(Point2D p) {
/* 107 */     if (this.hitCheckChildren) return super.nodeHitAt(p);
/*     */     
/* 109 */     return contains(p) ? this : null;
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
/*     */   public void setImage(GraphicsNode newImage) {
/* 122 */     fireGraphicsNodeChangeStarted();
/* 123 */     invalidateGeometryCache();
/* 124 */     if (this.count == 0) ensureCapacity(1); 
/* 125 */     this.children[0] = newImage;
/* 126 */     ((AbstractGraphicsNode)newImage).setParent(this);
/* 127 */     ((AbstractGraphicsNode)newImage).setRoot(getRoot());
/* 128 */     this.count = 1;
/* 129 */     fireGraphicsNodeChangeCompleted();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode getImage() {
/* 136 */     if (this.count > 0) {
/* 137 */       return this.children[0];
/*     */     }
/* 139 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/ImageNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */