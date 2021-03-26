/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.geom.AffineTransform;
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
/*     */ public abstract class BasicTextPainter
/*     */   implements TextPainter
/*     */ {
/*  38 */   private static TextLayoutFactory textLayoutFactory = new ConcreteTextLayoutFactory();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   protected FontRenderContext fontRenderContext = new FontRenderContext(new AffineTransform(), true, true);
/*     */ 
/*     */   
/*  47 */   protected FontRenderContext aaOffFontRenderContext = new FontRenderContext(new AffineTransform(), false, true);
/*     */ 
/*     */   
/*     */   protected TextLayoutFactory getTextLayoutFactory() {
/*  51 */     return textLayoutFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Mark selectAt(double x, double y, TextNode node) {
/*  61 */     return hitTest(x, y, node);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Mark selectTo(double x, double y, Mark beginMark) {
/*  71 */     if (beginMark == null) {
/*  72 */       return null;
/*     */     }
/*  74 */     return hitTest(x, y, beginMark.getTextNode());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getGeometryBounds(TextNode node) {
/*  85 */     return getOutline(node).getBounds2D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract Mark hitTest(double paramDouble1, double paramDouble2, TextNode paramTextNode);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class BasicMark
/*     */     implements Mark
/*     */   {
/*     */     private TextNode node;
/*     */ 
/*     */ 
/*     */     
/*     */     private TextHit hit;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected BasicMark(TextNode node, TextHit hit) {
/* 110 */       this.hit = hit;
/* 111 */       this.node = node;
/*     */     }
/*     */     
/*     */     public TextHit getHit() {
/* 115 */       return this.hit;
/*     */     }
/*     */     
/*     */     public TextNode getTextNode() {
/* 119 */       return this.node;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getCharIndex() {
/* 128 */       return this.hit.getCharIndex();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/BasicTextPainter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */