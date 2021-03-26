/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class GlyfDescript
/*     */   implements GlyphDescription
/*     */ {
/*     */   public static final byte ON_CURVE = 1;
/*     */   public static final byte X_SHORT_VECTOR = 2;
/*     */   public static final byte Y_SHORT_VECTOR = 4;
/*     */   public static final byte REPEAT = 8;
/*     */   public static final byte X_DUAL = 16;
/*     */   public static final byte Y_DUAL = 32;
/*     */   private int[] instructions;
/*     */   private final int contourCount;
/*     */   
/*     */   protected GlyfDescript(short numberOfContours, TTFDataStream bais) throws IOException {
/*  80 */     this.contourCount = numberOfContours;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resolve() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getContourCount() {
/*  97 */     return this.contourCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getInstructions() {
/* 106 */     return this.instructions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readInstructions(TTFDataStream bais, int count) throws IOException {
/* 117 */     this.instructions = bais.readUnsignedByteArray(count);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/GlyfDescript.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */