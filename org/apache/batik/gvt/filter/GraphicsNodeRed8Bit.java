/*     */ package org.apache.batik.gvt.filter;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.rendered.AbstractRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.AbstractTiledRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.util.Platform;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GraphicsNodeRed8Bit
/*     */   extends AbstractRed
/*     */ {
/*     */   private GraphicsNode node;
/*     */   private AffineTransform node2dev;
/*     */   private RenderingHints hints;
/*     */   private boolean usePrimitivePaint;
/*     */   
/*     */   public GraphicsNodeRed8Bit(GraphicsNode node, AffineTransform node2dev, boolean usePrimitivePaint, RenderingHints hints) {
/*  65 */     this.node = node;
/*  66 */     this.node2dev = node2dev;
/*  67 */     this.hints = hints;
/*  68 */     this.usePrimitivePaint = usePrimitivePaint;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     AffineTransform at = node2dev;
/*  74 */     Rectangle2D bounds2D = node.getPrimitiveBounds();
/*  75 */     if (bounds2D == null) bounds2D = new Rectangle2D.Float(0.0F, 0.0F, 1.0F, 1.0F); 
/*  76 */     if (!usePrimitivePaint) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  83 */       AffineTransform nodeAt = node.getTransform();
/*  84 */       if (nodeAt != null) {
/*  85 */         at = (AffineTransform)at.clone();
/*  86 */         at.concatenate(nodeAt);
/*     */       } 
/*     */     } 
/*  89 */     Rectangle bounds = at.createTransformedShape(bounds2D).getBounds();
/*     */ 
/*     */     
/*  92 */     ColorModel cm = createColorModel();
/*     */     
/*  94 */     int defSz = AbstractTiledRed.getDefaultTileSize();
/*     */ 
/*     */     
/*  97 */     int tgX = defSz * (int)Math.floor((bounds.x / defSz));
/*  98 */     int tgY = defSz * (int)Math.floor((bounds.y / defSz));
/*     */     
/* 100 */     int tw = bounds.x + bounds.width - tgX;
/* 101 */     if (tw > defSz) tw = defSz; 
/* 102 */     int th = bounds.y + bounds.height - tgY;
/* 103 */     if (th > defSz) th = defSz; 
/* 104 */     if (tw <= 0 || th <= 0) {
/* 105 */       tw = 1;
/* 106 */       th = 1;
/*     */     } 
/*     */ 
/*     */     
/* 110 */     SampleModel sm = cm.createCompatibleSampleModel(tw, th);
/*     */ 
/*     */     
/* 113 */     init((CachableRed)null, bounds, cm, sm, tgX, tgY, null);
/*     */   }
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/* 117 */     genRect(wr);
/* 118 */     return wr;
/*     */   }
/*     */ 
/*     */   
/*     */   public void genRect(WritableRaster wr) {
/* 123 */     BufferedImage offScreen = new BufferedImage(this.cm, wr.createWritableTranslatedChild(0, 0), this.cm.isAlphaPremultiplied(), null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     Graphics2D g = GraphicsUtil.createGraphics(offScreen, this.hints);
/* 130 */     g.setComposite(AlphaComposite.Clear);
/* 131 */     g.fillRect(0, 0, wr.getWidth(), wr.getHeight());
/* 132 */     g.setComposite(AlphaComposite.SrcOver);
/* 133 */     g.translate(-wr.getMinX(), -wr.getMinY());
/*     */ 
/*     */     
/* 136 */     g.transform(this.node2dev);
/*     */ 
/*     */ 
/*     */     
/* 140 */     if (this.usePrimitivePaint) {
/* 141 */       this.node.primitivePaint(g);
/*     */     } else {
/*     */       
/* 144 */       this.node.paint(g);
/*     */     } 
/*     */     
/* 147 */     g.dispose();
/*     */   }
/*     */   
/*     */   public ColorModel createColorModel() {
/* 151 */     if (Platform.isOSX)
/* 152 */       return GraphicsUtil.sRGB_Pre; 
/* 153 */     return GraphicsUtil.sRGB_Unpre;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/filter/GraphicsNodeRed8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */