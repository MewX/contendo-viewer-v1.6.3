/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BoolStack
/*     */   implements Cloneable
/*     */ {
/*     */   private boolean[] m_values;
/*     */   private int m_allocatedSize;
/*     */   private int m_index;
/*     */   
/*     */   public BoolStack() {
/*  44 */     this(32);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoolStack(int size) {
/*  55 */     this.m_allocatedSize = size;
/*  56 */     this.m_values = new boolean[size];
/*  57 */     this.m_index = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int size() {
/*  67 */     return this.m_index + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void clear() {
/*  76 */     this.m_index = -1;
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
/*     */   public final boolean push(boolean val) {
/*  89 */     if (this.m_index == this.m_allocatedSize - 1) {
/*  90 */       grow();
/*     */     }
/*  92 */     this.m_values[++this.m_index] = val; return val;
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
/*     */   public final boolean pop() {
/* 104 */     return this.m_values[this.m_index--];
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
/*     */   public final boolean popAndTop() {
/* 117 */     this.m_index--;
/*     */     
/* 119 */     return (this.m_index >= 0) ? this.m_values[this.m_index] : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setTop(boolean b) {
/* 130 */     this.m_values[this.m_index] = b;
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
/*     */   public final boolean peek() {
/* 142 */     return this.m_values[this.m_index];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean peekOrFalse() {
/* 153 */     return (this.m_index > -1) ? this.m_values[this.m_index] : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean peekOrTrue() {
/* 164 */     return (this.m_index > -1) ? this.m_values[this.m_index] : true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 175 */     return (this.m_index == -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void grow() {
/* 185 */     this.m_allocatedSize *= 2;
/*     */     
/* 187 */     boolean[] newVector = new boolean[this.m_allocatedSize];
/*     */     
/* 189 */     System.arraycopy(this.m_values, 0, newVector, 0, this.m_index + 1);
/*     */     
/* 191 */     this.m_values = newVector;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 197 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/BoolStack.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */