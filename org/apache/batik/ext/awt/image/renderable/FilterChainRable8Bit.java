/*     */ package org.apache.batik.ext.awt.image.renderable;
/*     */ 
/*     */ import java.awt.Composite;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.SVGComposite;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FilterChainRable8Bit
/*     */   extends AbstractRable
/*     */   implements FilterChainRable, PaintRable
/*     */ {
/*     */   private int filterResolutionX;
/*     */   private int filterResolutionY;
/*     */   private Filter chainSource;
/*     */   private FilterResRable filterRes;
/*     */   private PadRable crop;
/*     */   private Rectangle2D filterRegion;
/*     */   
/*     */   public FilterChainRable8Bit(Filter source, Rectangle2D filterRegion) {
/*  83 */     if (source == null) {
/*  84 */       throw new IllegalArgumentException();
/*     */     }
/*  86 */     if (filterRegion == null) {
/*  87 */       throw new IllegalArgumentException();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  92 */     Rectangle2D padRect = (Rectangle2D)filterRegion.clone();
/*  93 */     this.crop = new PadRable8Bit(source, padRect, PadMode.ZERO_PAD);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  98 */     this.chainSource = source;
/*  99 */     this.filterRegion = filterRegion;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     init(this.crop);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFilterResolutionX() {
/* 112 */     return this.filterResolutionX;
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
/*     */   public void setFilterResolutionX(int filterResolutionX) {
/* 124 */     touch();
/* 125 */     this.filterResolutionX = filterResolutionX;
/*     */     
/* 127 */     setupFilterRes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFilterResolutionY() {
/* 134 */     return this.filterResolutionY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilterResolutionY(int filterResolutionY) {
/* 144 */     touch();
/* 145 */     this.filterResolutionY = filterResolutionY;
/* 146 */     setupFilterRes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setupFilterRes() {
/* 155 */     if (this.filterResolutionX >= 0) {
/* 156 */       if (this.filterRes == null) {
/* 157 */         this.filterRes = new FilterResRable8Bit();
/* 158 */         this.filterRes.setSource(this.chainSource);
/*     */       } 
/*     */       
/* 161 */       this.filterRes.setFilterResolutionX(this.filterResolutionX);
/* 162 */       this.filterRes.setFilterResolutionY(this.filterResolutionY);
/*     */     }
/*     */     else {
/*     */       
/* 166 */       this.filterRes = null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 171 */     if (this.filterRes != null) {
/* 172 */       this.crop.setSource(this.filterRes);
/*     */     } else {
/*     */       
/* 175 */       this.crop.setSource(this.chainSource);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilterRegion(Rectangle2D filterRegion) {
/* 184 */     if (filterRegion == null) {
/* 185 */       throw new IllegalArgumentException();
/*     */     }
/* 187 */     touch();
/* 188 */     this.filterRegion = filterRegion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getFilterRegion() {
/* 195 */     return this.filterRegion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getSource() {
/* 205 */     return this.crop;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSource(Filter chainSource) {
/* 213 */     if (chainSource == null) {
/* 214 */       throw new IllegalArgumentException("Null Source for Filter Chain");
/*     */     }
/* 216 */     touch();
/* 217 */     this.chainSource = chainSource;
/*     */     
/* 219 */     if (this.filterRes == null) {
/* 220 */       this.crop.setSource(chainSource);
/*     */     } else {
/*     */       
/* 223 */       this.filterRes.setSource(chainSource);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/* 231 */     return (Rectangle2D)this.filterRegion.clone();
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
/* 247 */     Composite c = g2d.getComposite();
/* 248 */     if (!SVGComposite.OVER.equals(c)) {
/* 249 */       return false;
/*     */     }
/* 251 */     GraphicsUtil.drawImage(g2d, getSource());
/*     */     
/* 253 */     return true;
/*     */   }
/*     */   
/*     */   public RenderedImage createRendering(RenderContext context) {
/* 257 */     return this.crop.createRendering(context);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/FilterChainRable8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */