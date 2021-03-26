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
/*     */ 
/*     */ 
/*     */ public class DoubleStack
/*     */ {
/*  34 */   private ArrayDoubleList list = new ArrayDoubleList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DoubleStack() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DoubleStack(double[] numbas) {
/*  52 */     for (int ii = 0; ii < numbas.length; ii++)
/*     */     {
/*  54 */       this.list.add(numbas[ii]);
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
/*  66 */     return this.list.isEmpty();
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
/*     */   public double peek() {
/*  78 */     if (this.list.isEmpty())
/*     */     {
/*  80 */       throw new EmptyStackException();
/*     */     }
/*     */     
/*  83 */     return this.list.get(this.list.size() - 1);
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
/*     */   public double peek(int n) {
/*  98 */     if (this.list.isEmpty())
/*     */     {
/* 100 */       throw new EmptyStackException();
/*     */     }
/*     */     
/* 103 */     return this.list.get(this.list.size() - n - 1);
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
/*     */   public double pop() {
/* 115 */     if (this.list.isEmpty())
/*     */     {
/* 117 */       throw new EmptyStackException();
/*     */     }
/*     */     
/* 120 */     return this.list.removeElementAt(this.list.size() - 1);
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
/*     */   public double push(double item) {
/* 132 */     this.list.add(item);
/* 133 */     return item;
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
/*     */   public int search(double item) {
/* 149 */     for (int ii = this.list.size() - 1; ii >= 0; ii--) {
/*     */       
/* 151 */       if (this.list.get(ii) == item)
/*     */       {
/* 153 */         return this.list.size() - ii;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 158 */     return -1;
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
/*     */   public double get(int index) {
/* 172 */     return this.list.get(index);
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
/* 183 */     return this.list.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 192 */     this.list.clear();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/DoubleStack.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */