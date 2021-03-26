/*     */ package org.apache.pdfbox.pdmodel.graphics.state;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum RenderingMode
/*     */ {
/*  29 */   FILL(0),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   STROKE(1),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   FILL_STROKE(2),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   NEITHER(3),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   FILL_CLIP(4),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   STROKE_CLIP(5),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   FILL_STROKE_CLIP(6),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   NEITHER_CLIP(7); private static final RenderingMode[] VALUES;
/*     */   static {
/*  66 */     VALUES = values();
/*     */   }
/*     */   private final int value;
/*     */   public static RenderingMode fromInt(int value) {
/*  70 */     return VALUES[value];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   RenderingMode(int value) {
/*  77 */     this.value = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int intValue() {
/*  85 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFill() {
/*  93 */     return (this == FILL || this == FILL_STROKE || this == FILL_CLIP || this == FILL_STROKE_CLIP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStroke() {
/* 104 */     return (this == STROKE || this == FILL_STROKE || this == STROKE_CLIP || this == FILL_STROKE_CLIP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isClip() {
/* 115 */     return (this == FILL_CLIP || this == STROKE_CLIP || this == FILL_STROKE_CLIP || this == NEITHER_CLIP);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/state/RenderingMode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */