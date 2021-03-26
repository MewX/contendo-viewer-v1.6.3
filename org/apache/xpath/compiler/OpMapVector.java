/*     */ package org.apache.xpath.compiler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OpMapVector
/*     */ {
/*     */   protected int m_blocksize;
/*     */   protected int[] m_map;
/*  38 */   protected int m_lengthPos = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int m_mapSize;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OpMapVector(int blocksize, int increaseSize, int lengthPos) {
/*  51 */     this.m_blocksize = increaseSize;
/*  52 */     this.m_mapSize = blocksize;
/*  53 */     this.m_lengthPos = lengthPos;
/*  54 */     this.m_map = new int[blocksize];
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
/*     */   public final int elementAt(int i) {
/*  66 */     return this.m_map[i];
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
/*     */   public final void setElementAt(int value, int index) {
/*  81 */     if (index >= this.m_mapSize) {
/*     */       
/*  83 */       int oldSize = this.m_mapSize;
/*     */       
/*  85 */       this.m_mapSize += this.m_blocksize;
/*     */       
/*  87 */       int[] newMap = new int[this.m_mapSize];
/*     */       
/*  89 */       System.arraycopy(this.m_map, 0, newMap, 0, oldSize);
/*     */       
/*  91 */       this.m_map = newMap;
/*     */     } 
/*     */     
/*  94 */     this.m_map[index] = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setToSize(int size) {
/* 105 */     int[] newMap = new int[size];
/*     */     
/* 107 */     System.arraycopy(this.m_map, 0, newMap, 0, this.m_map[this.m_lengthPos]);
/*     */     
/* 109 */     this.m_mapSize = size;
/* 110 */     this.m_map = newMap;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/compiler/OpMapVector.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */