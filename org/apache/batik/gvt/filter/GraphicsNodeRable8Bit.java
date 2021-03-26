/*     */ package org.apache.batik.gvt.filter;
/*     */ 
/*     */ import java.awt.Composite;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.SVGComposite;
/*     */ import org.apache.batik.ext.awt.image.renderable.AbstractRable;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.ext.awt.image.renderable.PaintRable;
/*     */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.TranslateRed;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GraphicsNodeRable8Bit
/*     */   extends AbstractRable
/*     */   implements PaintRable, GraphicsNodeRable
/*     */ {
/*  51 */   private AffineTransform cachedGn2dev = null;
/*  52 */   private AffineTransform cachedUsr2dev = null;
/*  53 */   private CachableRed cachedRed = null;
/*  54 */   private Rectangle2D cachedBounds = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean usePrimitivePaint = true;
/*     */ 
/*     */   
/*     */   private GraphicsNode node;
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getUsePrimitivePaint() {
/*  66 */     return this.usePrimitivePaint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUsePrimitivePaint(boolean usePrimitivePaint) {
/*  75 */     this.usePrimitivePaint = usePrimitivePaint;
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
/*     */   public GraphicsNode getGraphicsNode() {
/*  87 */     return this.node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGraphicsNode(GraphicsNode node) {
/*  94 */     if (node == null) {
/*  95 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  98 */     this.node = node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearCache() {
/* 105 */     this.cachedRed = null;
/* 106 */     this.cachedUsr2dev = null;
/* 107 */     this.cachedGn2dev = null;
/* 108 */     this.cachedBounds = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNodeRable8Bit(GraphicsNode node) {
/* 115 */     if (node == null) {
/* 116 */       throw new IllegalArgumentException();
/*     */     }
/* 118 */     this.node = node;
/* 119 */     this.usePrimitivePaint = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNodeRable8Bit(GraphicsNode node, Map props) {
/* 128 */     super((Filter)null, props);
/*     */     
/* 130 */     if (node == null) {
/* 131 */       throw new IllegalArgumentException();
/*     */     }
/* 133 */     this.node = node;
/* 134 */     this.usePrimitivePaint = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNodeRable8Bit(GraphicsNode node, boolean usePrimitivePaint) {
/* 144 */     if (node == null) {
/* 145 */       throw new IllegalArgumentException();
/*     */     }
/* 147 */     this.node = node;
/* 148 */     this.usePrimitivePaint = usePrimitivePaint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/* 155 */     if (this.usePrimitivePaint) {
/* 156 */       Rectangle2D primitiveBounds = this.node.getPrimitiveBounds();
/* 157 */       if (primitiveBounds == null) {
/* 158 */         return new Rectangle2D.Double(0.0D, 0.0D, 0.0D, 0.0D);
/*     */       }
/* 160 */       return (Rectangle2D)primitiveBounds.clone();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 168 */     Rectangle2D bounds = this.node.getBounds();
/* 169 */     if (bounds == null) {
/* 170 */       return new Rectangle2D.Double(0.0D, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */     
/* 173 */     AffineTransform at = this.node.getTransform();
/* 174 */     if (at != null) {
/* 175 */       bounds = at.createTransformedShape(bounds).getBounds2D();
/*     */     }
/* 177 */     return bounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDynamic() {
/* 188 */     return false;
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
/*     */   public boolean paintRable(Graphics2D g2d) {
/* 204 */     Composite c = g2d.getComposite();
/* 205 */     if (!SVGComposite.OVER.equals(c)) {
/* 206 */       return false;
/*     */     }
/* 208 */     ColorSpace g2dCS = GraphicsUtil.getDestinationColorSpace(g2d);
/* 209 */     if (g2dCS == null || g2dCS != ColorSpace.getInstance(1000))
/*     */     {
/*     */       
/* 212 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 216 */     GraphicsNode gn = getGraphicsNode();
/* 217 */     if (getUsePrimitivePaint()) {
/* 218 */       gn.primitivePaint(g2d);
/*     */     } else {
/*     */       
/* 221 */       gn.paint(g2d);
/*     */     } 
/*     */ 
/*     */     
/* 225 */     return true;
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
/*     */   public RenderedImage createRendering(RenderContext renderContext) {
/* 245 */     AffineTransform gn2dev, usr2dev = renderContext.getTransform();
/*     */ 
/*     */     
/* 248 */     if (usr2dev == null) {
/* 249 */       usr2dev = new AffineTransform();
/* 250 */       gn2dev = usr2dev;
/*     */     } else {
/* 252 */       gn2dev = (AffineTransform)usr2dev.clone();
/*     */     } 
/*     */ 
/*     */     
/* 256 */     AffineTransform gn2usr = this.node.getTransform();
/* 257 */     if (gn2usr != null) {
/* 258 */       gn2dev.concatenate(gn2usr);
/*     */     }
/*     */     
/* 261 */     Rectangle2D bounds2D = getBounds2D();
/*     */     
/* 263 */     if (this.cachedBounds != null && this.cachedGn2dev != null && this.cachedBounds.equals(bounds2D) && gn2dev.getScaleX() == this.cachedGn2dev.getScaleX() && gn2dev.getScaleY() == this.cachedGn2dev.getScaleY() && gn2dev.getShearX() == this.cachedGn2dev.getShearX() && gn2dev.getShearY() == this.cachedGn2dev.getShearY()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 272 */       double deltaX = usr2dev.getTranslateX() - this.cachedUsr2dev.getTranslateX();
/*     */       
/* 274 */       double deltaY = usr2dev.getTranslateY() - this.cachedUsr2dev.getTranslateY();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 279 */       if (deltaX == 0.0D && deltaY == 0.0D)
/*     */       {
/* 281 */         return (RenderedImage)this.cachedRed;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 286 */       if (deltaX == (int)deltaX && deltaY == (int)deltaY)
/*     */       {
/* 288 */         return (RenderedImage)new TranslateRed(this.cachedRed, (int)Math.round(this.cachedRed.getMinX() + deltaX), (int)Math.round(this.cachedRed.getMinY() + deltaY));
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 301 */     if (bounds2D.getWidth() > 0.0D && bounds2D.getHeight() > 0.0D) {
/*     */       
/* 303 */       this.cachedUsr2dev = (AffineTransform)usr2dev.clone();
/* 304 */       this.cachedGn2dev = gn2dev;
/* 305 */       this.cachedBounds = bounds2D;
/* 306 */       this.cachedRed = (CachableRed)new GraphicsNodeRed8Bit(this.node, usr2dev, this.usePrimitivePaint, renderContext.getRenderingHints());
/*     */ 
/*     */       
/* 309 */       return (RenderedImage)this.cachedRed;
/*     */     } 
/*     */     
/* 312 */     this.cachedUsr2dev = null;
/* 313 */     this.cachedGn2dev = null;
/* 314 */     this.cachedBounds = null;
/* 315 */     this.cachedRed = null;
/* 316 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/filter/GraphicsNodeRable8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */