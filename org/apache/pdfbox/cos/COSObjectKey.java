/*     */ package org.apache.pdfbox.cos;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class COSObjectKey
/*     */   implements Comparable<COSObjectKey>
/*     */ {
/*     */   private final long number;
/*     */   private int generation;
/*     */   
/*     */   public COSObjectKey(COSObject object) {
/*  37 */     this(object.getObjectNumber(), object.getGenerationNumber());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSObjectKey(long num, int gen) {
/*  48 */     this.number = num;
/*  49 */     this.generation = gen;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  58 */     COSObjectKey objToBeCompared = (obj instanceof COSObjectKey) ? (COSObjectKey)obj : null;
/*  59 */     return (objToBeCompared != null && objToBeCompared
/*  60 */       .getNumber() == getNumber() && objToBeCompared
/*  61 */       .getGeneration() == getGeneration());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGeneration() {
/*  71 */     return this.generation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fixGeneration(int genNumber) {
/*  81 */     this.generation = genNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getNumber() {
/*  91 */     return this.number;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 100 */     return Long.valueOf(this.number + this.generation).hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 106 */     return Long.toString(this.number) + " " + Integer.toString(this.generation) + " R";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(COSObjectKey other) {
/* 112 */     if (getNumber() < other.getNumber())
/*     */     {
/* 114 */       return -1;
/*     */     }
/* 116 */     if (getNumber() > other.getNumber())
/*     */     {
/* 118 */       return 1;
/*     */     }
/*     */ 
/*     */     
/* 122 */     if (getGeneration() < other.getGeneration())
/*     */     {
/* 124 */       return -1;
/*     */     }
/* 126 */     if (getGeneration() > other.getGeneration())
/*     */     {
/* 128 */       return 1;
/*     */     }
/*     */ 
/*     */     
/* 132 */     return 0;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/cos/COSObjectKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */