/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.ArrayList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArrayStack
/*     */   extends ArrayList
/*     */   implements Buffer
/*     */ {
/*     */   private static final long serialVersionUID = 2130079159931574599L;
/*     */   
/*     */   public ArrayStack() {}
/*     */   
/*     */   public ArrayStack(int initialSize) {
/*  65 */     super(initialSize);
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
/*  77 */     return isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object peek() throws EmptyStackException {
/*  87 */     int n = size();
/*  88 */     if (n <= 0) {
/*  89 */       throw new EmptyStackException();
/*     */     }
/*  91 */     return get(n - 1);
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
/*     */   public Object peek(int n) throws EmptyStackException {
/* 105 */     int m = size() - n - 1;
/* 106 */     if (m < 0) {
/* 107 */       throw new EmptyStackException();
/*     */     }
/* 109 */     return get(m);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object pop() throws EmptyStackException {
/* 120 */     int n = size();
/* 121 */     if (n <= 0) {
/* 122 */       throw new EmptyStackException();
/*     */     }
/* 124 */     return remove(n - 1);
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
/*     */   public Object push(Object item) {
/* 136 */     add((E)item);
/* 137 */     return item;
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
/*     */   public int search(Object object) {
/* 152 */     int i = size() - 1;
/* 153 */     int n = 1;
/* 154 */     while (i >= 0) {
/* 155 */       Object current = get(i);
/* 156 */       if ((object == null && current == null) || (object != null && object.equals(current)))
/*     */       {
/* 158 */         return n;
/*     */       }
/* 160 */       i--;
/* 161 */       n++;
/*     */     } 
/* 163 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get() {
/* 173 */     int size = size();
/* 174 */     if (size == 0) {
/* 175 */       throw new BufferUnderflowException();
/*     */     }
/* 177 */     return get(size - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object remove() {
/* 187 */     int size = size();
/* 188 */     if (size == 0) {
/* 189 */       throw new BufferUnderflowException();
/*     */     }
/* 191 */     return remove(size - 1);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/ArrayStack.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */