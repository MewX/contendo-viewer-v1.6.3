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
/*     */ public class ObjectStack
/*     */   extends ObjectVector
/*     */ {
/*     */   public ObjectStack() {}
/*     */   
/*     */   public ObjectStack(int blocksize) {
/*  51 */     super(blocksize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjectStack(ObjectStack v) {
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
/*     */   public Object push(Object i) {
/*  73 */     if (this.m_firstFree + 1 >= this.m_mapSize) {
/*     */       
/*  75 */       this.m_mapSize += this.m_blocksize;
/*     */       
/*  77 */       Object[] newMap = new Object[this.m_mapSize];
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
/*     */   public Object pop() {
/*  99 */     Object val = this.m_map[--this.m_firstFree];
/* 100 */     this.m_map[this.m_firstFree] = null;
/*     */     
/* 102 */     return val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void quickPop(int n) {
/* 111 */     this.m_firstFree -= n;
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
/*     */   public Object peek() {
/*     */     
/* 124 */     try { return this.m_map[this.m_firstFree - 1]; } catch (ArrayIndexOutOfBoundsException e)
/*     */     
/*     */     { 
/*     */       
/* 128 */       throw new EmptyStackException(); }
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
/*     */   public Object peek(int n) {
/*     */     
/* 142 */     try { return this.m_map[this.m_firstFree - 1 + n]; } catch (ArrayIndexOutOfBoundsException e)
/*     */     
/*     */     { 
/*     */       
/* 146 */       throw new EmptyStackException(); }
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
/*     */   public void setTop(Object val) {
/*     */     
/* 160 */     try { this.m_map[this.m_firstFree - 1] = val; } catch (ArrayIndexOutOfBoundsException e)
/*     */     
/*     */     { 
/*     */       
/* 164 */       throw new EmptyStackException(); }
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
/* 177 */     return (this.m_firstFree == 0);
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
/*     */   public int search(Object o) {
/* 192 */     int i = lastIndexOf(o);
/*     */     
/* 194 */     if (i >= 0)
/*     */     {
/* 196 */       return size() - i;
/*     */     }
/*     */     
/* 199 */     return -1;
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
/* 210 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/ObjectStack.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */