/*     */ package org.apache.batik.gvt;
/*     */ 
/*     */ import java.awt.Paint;
/*     */ import java.awt.PaintContext;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.ext.awt.image.renderable.PadRable8Bit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PatternPaint
/*     */   implements Paint
/*     */ {
/*     */   private GraphicsNode node;
/*     */   private Rectangle2D patternRegion;
/*     */   private AffineTransform patternTransform;
/*     */   private Filter tile;
/*     */   private boolean overflow;
/*     */   private PatternPaintContext lastContext;
/*     */   
/*     */   public PatternPaint(GraphicsNode node, Rectangle2D patternRegion, boolean overflow, AffineTransform patternTransform) {
/*  90 */     if (node == null) {
/*  91 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  94 */     if (patternRegion == null) {
/*  95 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  98 */     this.node = node;
/*  99 */     this.patternRegion = patternRegion;
/* 100 */     this.overflow = overflow;
/* 101 */     this.patternTransform = patternTransform;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 106 */     CompositeGraphicsNode comp = new CompositeGraphicsNode();
/* 107 */     comp.getChildren().add(node);
/* 108 */     Filter gnr = comp.getGraphicsNodeRable(true);
/*     */     
/* 110 */     Rectangle2D padBounds = (Rectangle2D)patternRegion.clone();
/*     */ 
/*     */ 
/*     */     
/* 114 */     if (overflow) {
/* 115 */       Rectangle2D nodeBounds = comp.getBounds();
/*     */ 
/*     */       
/* 118 */       padBounds.add(nodeBounds);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 123 */     this.tile = (Filter)new PadRable8Bit(gnr, padBounds, PadMode.ZERO_PAD);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode getGraphicsNode() {
/* 130 */     return this.node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getPatternRect() {
/* 137 */     return (Rectangle2D)this.patternRegion.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getPatternTransform() {
/* 144 */     return this.patternTransform;
/*     */   }
/*     */   
/*     */   public boolean getOverflow() {
/* 148 */     return this.overflow;
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
/*     */   public PaintContext createContext(ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform xform, RenderingHints hints) {
/* 160 */     if (this.patternTransform != null) {
/* 161 */       xform = new AffineTransform(xform);
/* 162 */       xform.concatenate(this.patternTransform);
/*     */     } 
/*     */     
/* 165 */     if (this.lastContext != null && this.lastContext.getColorModel().equals(cm)) {
/*     */ 
/*     */       
/* 168 */       double[] p = new double[6];
/* 169 */       double[] q = new double[6];
/* 170 */       xform.getMatrix(p);
/* 171 */       this.lastContext.getUsr2Dev().getMatrix(q);
/* 172 */       if (p[0] == q[0] && p[1] == q[1] && p[2] == q[2] && p[3] == q[3]) {
/*     */         
/* 174 */         if (p[4] == q[4] && p[5] == q[5]) {
/* 175 */           return this.lastContext;
/*     */         }
/* 177 */         return new PatternPaintContextWrapper(this.lastContext, (int)(q[4] - p[4] + 0.5D), (int)(q[5] - p[5] + 0.5D));
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 187 */     this.lastContext = new PatternPaintContext(cm, xform, hints, this.tile, this.patternRegion, this.overflow);
/*     */ 
/*     */ 
/*     */     
/* 191 */     return this.lastContext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTransparency() {
/* 198 */     return 3;
/*     */   }
/*     */   
/*     */   static class PatternPaintContextWrapper
/*     */     implements PaintContext {
/*     */     PatternPaintContext ppc;
/*     */     
/*     */     PatternPaintContextWrapper(PatternPaintContext ppc, int xShift, int yShift) {
/* 206 */       this.ppc = ppc;
/* 207 */       this.xShift = xShift;
/* 208 */       this.yShift = yShift;
/*     */     }
/*     */     int xShift; int yShift;
/*     */     public void dispose() {}
/*     */     
/*     */     public ColorModel getColorModel() {
/* 214 */       return this.ppc.getColorModel();
/*     */     }
/*     */     public Raster getRaster(int x, int y, int width, int height) {
/* 217 */       return this.ppc.getRaster(x + this.xShift, y + this.yShift, width, height);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/PatternPaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */