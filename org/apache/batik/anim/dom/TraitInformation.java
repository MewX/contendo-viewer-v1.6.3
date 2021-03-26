/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TraitInformation
/*     */ {
/*     */   public static final short PERCENTAGE_FONT_SIZE = 0;
/*     */   public static final short PERCENTAGE_VIEWPORT_WIDTH = 1;
/*     */   public static final short PERCENTAGE_VIEWPORT_HEIGHT = 2;
/*     */   public static final short PERCENTAGE_VIEWPORT_SIZE = 3;
/*     */   protected boolean isAnimatable;
/*     */   protected int type;
/*     */   protected short percentageInterpretation;
/*     */   
/*     */   public TraitInformation(boolean isAnimatable, int type, short percentageInterpretation) {
/*  60 */     this.isAnimatable = isAnimatable;
/*     */     
/*  62 */     this.type = type;
/*  63 */     this.percentageInterpretation = percentageInterpretation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TraitInformation(boolean isAnimatable, int type) {
/*  71 */     this.isAnimatable = isAnimatable;
/*     */     
/*  73 */     this.type = type;
/*  74 */     this.percentageInterpretation = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimatable() {
/*  81 */     return this.isAnimatable;
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
/*     */   public int getType() {
/*  95 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getPercentageInterpretation() {
/* 102 */     return this.percentageInterpretation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/TraitInformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */