/*     */ package org.apache.xmlgraphics.image.loader.util;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Penalty
/*     */ {
/*  29 */   public static final Penalty ZERO_PENALTY = new Penalty(0);
/*  30 */   public static final Penalty INFINITE_PENALTY = new Penalty(2147483647);
/*     */ 
/*     */ 
/*     */   
/*     */   private final int value;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Penalty toPenalty(int value) {
/*  40 */     switch (value) {
/*     */       case 0:
/*  42 */         return ZERO_PENALTY;
/*     */       case 2147483647:
/*  44 */         return INFINITE_PENALTY;
/*     */     } 
/*  46 */     return new Penalty(value);
/*     */   }
/*     */ 
/*     */   
/*     */   private Penalty(int value) {
/*  51 */     this.value = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Penalty add(Penalty value) {
/*  60 */     return add(value.getValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Penalty add(int value) {
/*  69 */     long p = getValue() + value;
/*  70 */     return toPenalty(truncate(p));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getValue() {
/*  78 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInfinitePenalty() {
/*  87 */     return (this.value == Integer.MAX_VALUE);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  92 */     return "Penalty: " + (isInfinitePenalty() ? "INF" : Integer.toString(getValue()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int truncate(long penalty) {
/* 101 */     penalty = Math.min(2147483647L, penalty);
/* 102 */     penalty = Math.max(-2147483648L, penalty);
/* 103 */     return (int)penalty;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/util/Penalty.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */