/*     */ package org.apache.commons.collections.primitives;
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
/*     */ public class IntStack
/*     */ {
/*  32 */   private ArrayIntList list = new ArrayIntList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntStack() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public IntStack(int[] numbas) {
/*  42 */     for (int ii = 0; ii < numbas.length; ii++)
/*     */     {
/*  44 */       this.list.add(numbas[ii]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean empty() {
/*  56 */     return this.list.isEmpty();
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
/*     */   public int peek() {
/*  69 */     if (this.list.isEmpty())
/*     */     {
/*  71 */       throw new EmptyStackException();
/*     */     }
/*     */     
/*  74 */     return this.list.get(this.list.size() - 1);
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
/*     */   public int peek(int n) {
/*  89 */     if (this.list.isEmpty())
/*     */     {
/*  91 */       throw new EmptyStackException();
/*     */     }
/*     */     
/*  94 */     return this.list.get(this.list.size() - n - 1);
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
/*     */   public int pop() {
/* 107 */     if (this.list.isEmpty())
/*     */     {
/* 109 */       throw new EmptyStackException();
/*     */     }
/*     */     
/* 112 */     return this.list.removeElementAt(this.list.size() - 1);
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
/*     */   public int push(int item) {
/* 124 */     this.list.add(item);
/* 125 */     return item;
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
/*     */   
/*     */   public int search(int item) {
/* 141 */     for (int ii = this.list.size() - 1; ii >= 0; ii--) {
/*     */       
/* 143 */       if (this.list.get(ii) == item)
/*     */       {
/* 145 */         return this.list.size() - ii;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 150 */     return -1;
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
/*     */   public int get(int index) {
/* 164 */     return this.list.get(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 175 */     return this.list.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 184 */     this.list.clear();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/IntStack.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */