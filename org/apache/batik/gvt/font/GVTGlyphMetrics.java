/*     */ package org.apache.batik.gvt.font;
/*     */ 
/*     */ import java.awt.font.GlyphMetrics;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GVTGlyphMetrics
/*     */ {
/*     */   private GlyphMetrics gm;
/*     */   private float verticalAdvance;
/*     */   
/*     */   public GVTGlyphMetrics(GlyphMetrics gm, float verticalAdvance) {
/*  44 */     this.gm = gm;
/*  45 */     this.verticalAdvance = verticalAdvance;
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
/*     */   public GVTGlyphMetrics(float horizontalAdvance, float verticalAdvance, Rectangle2D bounds, byte glyphType) {
/*  60 */     this.gm = new GlyphMetrics(horizontalAdvance, bounds, glyphType);
/*  61 */     this.verticalAdvance = verticalAdvance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHorizontalAdvance() {
/*  68 */     return this.gm.getAdvance();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVerticalAdvance() {
/*  75 */     return this.verticalAdvance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/*  82 */     return this.gm.getBounds2D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLSB() {
/*  89 */     return this.gm.getLSB();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getRSB() {
/*  96 */     return this.gm.getRSB();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 103 */     return this.gm.getType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCombining() {
/* 110 */     return this.gm.isCombining();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isComponent() {
/* 117 */     return this.gm.isComponent();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLigature() {
/* 124 */     return this.gm.isLigature();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStandard() {
/* 131 */     return this.gm.isStandard();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isWhitespace() {
/* 138 */     return this.gm.isWhitespace();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/font/GVTGlyphMetrics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */