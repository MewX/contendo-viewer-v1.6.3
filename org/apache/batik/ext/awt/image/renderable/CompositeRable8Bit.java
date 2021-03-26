/*     */ package org.apache.batik.ext.awt.image.renderable;
/*     */ 
/*     */ import java.awt.Composite;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.CompositeRule;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.SVGComposite;
/*     */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.CompositeRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.FloodRed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompositeRable8Bit
/*     */   extends AbstractColorInterpolationRable
/*     */   implements CompositeRable, PaintRable
/*     */ {
/*     */   protected CompositeRule rule;
/*     */   
/*     */   public CompositeRable8Bit(List srcs, CompositeRule rule, boolean csIsLinear) {
/*  57 */     super(srcs);
/*     */     
/*  59 */     setColorSpaceLinear(csIsLinear);
/*     */     
/*  61 */     this.rule = rule;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSources(List srcs) {
/*  69 */     init(srcs, (Map)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCompositeRule(CompositeRule cr) {
/*  77 */     touch();
/*  78 */     this.rule = cr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompositeRule getCompositeRule() {
/*  86 */     return this.rule;
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
/* 102 */     Composite c = g2d.getComposite();
/* 103 */     if (!SVGComposite.OVER.equals(c)) {
/* 104 */       return false;
/*     */     }
/*     */     
/* 107 */     if (getCompositeRule() != CompositeRule.OVER) {
/* 108 */       return false;
/*     */     }
/* 110 */     ColorSpace crCS = getOperationColorSpace();
/* 111 */     ColorSpace g2dCS = GraphicsUtil.getDestinationColorSpace(g2d);
/* 112 */     if (g2dCS == null || g2dCS != crCS) {
/* 113 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 118 */     for (Object o : getSources()) {
/* 119 */       GraphicsUtil.drawImage(g2d, (Filter)o);
/*     */     }
/* 121 */     return true;
/*     */   }
/*     */   public RenderedImage createRendering(RenderContext rc) {
/*     */     Rectangle2D aoiR;
/* 125 */     if (this.srcs.size() == 0) {
/* 126 */       return null;
/*     */     }
/*     */     
/* 129 */     RenderingHints rh = rc.getRenderingHints();
/* 130 */     if (rh == null) rh = new RenderingHints(null);
/*     */ 
/*     */     
/* 133 */     AffineTransform at = rc.getTransform();
/*     */     
/* 135 */     Shape aoi = rc.getAreaOfInterest();
/*     */     
/* 137 */     if (aoi == null) {
/* 138 */       aoiR = getBounds2D();
/*     */     } else {
/* 140 */       aoiR = aoi.getBounds2D();
/* 141 */       Rectangle2D bounds2d = getBounds2D();
/* 142 */       if (!bounds2d.intersects(aoiR)) {
/* 143 */         return null;
/*     */       }
/* 145 */       Rectangle2D.intersect(aoiR, bounds2d, aoiR);
/*     */     } 
/*     */     
/* 148 */     Rectangle devRect = at.createTransformedShape(aoiR).getBounds();
/*     */     
/* 150 */     rc = new RenderContext(at, aoiR, rh);
/*     */ 
/*     */     
/* 153 */     List<CachableRed> srcs = new ArrayList();
/*     */     
/* 155 */     for (Object o : getSources()) {
/*     */       
/* 157 */       Filter filt = (Filter)o;
/*     */ 
/*     */       
/* 160 */       RenderedImage ri = filt.createRendering(rc);
/* 161 */       if (ri != null) {
/*     */         
/* 163 */         CachableRed cr = convertSourceCS(ri);
/* 164 */         srcs.add(cr);
/*     */         
/*     */         continue;
/*     */       } 
/* 168 */       switch (this.rule.getRule()) {
/*     */ 
/*     */         
/*     */         case 2:
/* 172 */           return null;
/*     */ 
/*     */ 
/*     */         
/*     */         case 3:
/* 177 */           srcs.clear();
/*     */ 
/*     */         
/*     */         case 6:
/* 181 */           srcs.add(new FloodRed(devRect));
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/* 192 */     if (srcs.size() == 0) {
/* 193 */       return null;
/*     */     }
/*     */     
/* 196 */     return (RenderedImage)new CompositeRed(srcs, this.rule);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/CompositeRable8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */