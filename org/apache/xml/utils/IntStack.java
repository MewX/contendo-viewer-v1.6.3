/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ import java.util.EmptyStackException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IntStack
/*     */   extends IntVector
/*     */ {
/*     */   public IntStack() {}
/*     */   
/*     */   public IntStack(int blocksize) {
/*  51 */     super(blocksize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntStack(IntStack v) {
/*  61 */     super(v);
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
/*     */   public int push(int i) {
/*  73 */     if (this.m_firstFree + 1 >= this.m_mapSize) {
/*     */       
/*  75 */       this.m_mapSize += this.m_blocksize;
/*     */       
/*  77 */       int[] newMap = new int[this.m_mapSize];
/*     */       
/*  79 */       System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree + 1);
/*     */       
/*  81 */       this.m_map = newMap;
/*     */     } 
/*     */     
/*  84 */     this.m_map[this.m_firstFree] = i;
/*     */     
/*  86 */     this.m_firstFree++;
/*     */     
/*  88 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int pop() {
/*  99 */     return this.m_map[--this.m_firstFree];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void quickPop(int n) {
/* 108 */     this.m_firstFree -= n;
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
/*     */   public final int peek() {
/*     */     
/* 121 */     try { return this.m_map[this.m_firstFree - 1]; } catch (ArrayIndexOutOfBoundsException e)
/*     */     
/*     */     { 
/*     */       
/* 125 */       throw new EmptyStackException(); }
/*     */   
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
/*     */   public int peek(int n) {
/*     */     
/* 139 */     try { return this.m_map[this.m_firstFree - 1 + n]; } catch (ArrayIndexOutOfBoundsException e)
/*     */     
/*     */     { 
/*     */       
/* 143 */       throw new EmptyStackException(); }
/*     */   
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
/*     */   public void setTop(int val) {
/*     */     
/* 157 */     try { this.m_map[this.m_firstFree - 1] = val; } catch (ArrayIndexOutOfBoundsException e)
/*     */     
/*     */     { 
/*     */       
/* 161 */       throw new EmptyStackException(); }
/*     */   
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
/*     */   public boolean empty() {
/* 174 */     return (this.m_firstFree == 0);
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
/*     */   public int search(int o) {
/* 189 */     int i = lastIndexOf(o);
/*     */     
/* 191 */     if (i >= 0)
/*     */     {
/* 193 */       return size() - i;
/*     */     }
/*     */     
/* 196 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 207 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/IntStack.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */