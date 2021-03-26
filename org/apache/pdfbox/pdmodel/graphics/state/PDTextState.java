/*     */ package org.apache.pdfbox.pdmodel.graphics.state;
/*     */ 
/*     */ import org.apache.pdfbox.pdmodel.font.PDFont;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDTextState
/*     */   implements Cloneable
/*     */ {
/*  29 */   private float characterSpacing = 0.0F;
/*  30 */   private float wordSpacing = 0.0F;
/*  31 */   private float horizontalScaling = 100.0F;
/*  32 */   private float leading = 0.0F;
/*     */   private PDFont font;
/*     */   private float fontSize;
/*  35 */   private RenderingMode renderingMode = RenderingMode.FILL;
/*  36 */   private float rise = 0.0F;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean knockout = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCharacterSpacing() {
/*  46 */     return this.characterSpacing;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCharacterSpacing(float value) {
/*  56 */     this.characterSpacing = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWordSpacing() {
/*  66 */     return this.wordSpacing;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWordSpacing(float value) {
/*  76 */     this.wordSpacing = value;
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
/*     */   public float getHorizontalScaling() {
/*  88 */     return this.horizontalScaling;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHorizontalScaling(float value) {
/*  98 */     this.horizontalScaling = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLeading() {
/* 108 */     return this.leading;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLeading(float value) {
/* 118 */     this.leading = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFont getFont() {
/* 128 */     return this.font;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFont(PDFont value) {
/* 138 */     this.font = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFontSize() {
/* 148 */     return this.fontSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFontSize(float value) {
/* 158 */     this.fontSize = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderingMode getRenderingMode() {
/* 168 */     return this.renderingMode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRenderingMode(RenderingMode renderingMode) {
/* 178 */     this.renderingMode = renderingMode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getRise() {
/* 188 */     return this.rise;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRise(float value) {
/* 198 */     this.rise = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getKnockoutFlag() {
/* 208 */     return this.knockout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKnockoutFlag(boolean value) {
/* 218 */     this.knockout = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDTextState clone() {
/*     */     try {
/* 226 */       return (PDTextState)super.clone();
/*     */     }
/* 228 */     catch (CloneNotSupportedException e) {
/*     */ 
/*     */       
/* 231 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/state/PDTextState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */