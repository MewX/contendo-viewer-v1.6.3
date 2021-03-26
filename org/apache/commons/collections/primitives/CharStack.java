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
/*     */ public class CharStack
/*     */ {
/*  34 */   private ArrayCharList list = new ArrayCharList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharStack() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharStack(char[] chars) {
/*  52 */     for (int ii = 0; ii < chars.length; ii++)
/*     */     {
/*  54 */       this.list.add(chars[ii]);
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
/*     */   public char peek() {
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
/*     */   public char peek(int n) {
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
/*     */   public char pop() {
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
/*     */   public char push(char item) {
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
/*     */   public int search(char item) {
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
/*     */   public char get(int index) {
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/CharStack.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */