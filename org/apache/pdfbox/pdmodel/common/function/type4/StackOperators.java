/*     */ package org.apache.pdfbox.pdmodel.common.function.type4;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Stack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class StackOperators
/*     */ {
/*     */   static class Copy
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/*  36 */       Stack<Object> stack = context.getStack();
/*  37 */       int n = ((Number)stack.pop()).intValue();
/*  38 */       if (n > 0) {
/*     */         
/*  40 */         int size = stack.size();
/*     */ 
/*     */         
/*  43 */         List<Object> copy = new ArrayList(stack.subList(size - n, size));
/*  44 */         stack.addAll(copy);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Dup
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/*  56 */       Stack<Object> stack = context.getStack();
/*  57 */       stack.push(stack.peek());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Exch
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/*  68 */       Stack<Object> stack = context.getStack();
/*  69 */       Object any2 = stack.pop();
/*  70 */       Object any1 = stack.pop();
/*  71 */       stack.push(any2);
/*  72 */       stack.push(any1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Index
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/*  83 */       Stack<Object> stack = context.getStack();
/*  84 */       int n = ((Number)stack.pop()).intValue();
/*  85 */       if (n < 0)
/*     */       {
/*  87 */         throw new IllegalArgumentException("rangecheck: " + n);
/*     */       }
/*  89 */       int size = stack.size();
/*  90 */       stack.push(stack.get(size - n - 1));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Pop
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 101 */       Stack<Object> stack = context.getStack();
/* 102 */       stack.pop();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Roll
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 113 */       Stack<Object> stack = context.getStack();
/* 114 */       int j = ((Number)stack.pop()).intValue();
/* 115 */       int n = ((Number)stack.pop()).intValue();
/* 116 */       if (j == 0) {
/*     */         return;
/*     */       }
/*     */       
/* 120 */       if (n < 0)
/*     */       {
/* 122 */         throw new IllegalArgumentException("rangecheck: " + n);
/*     */       }
/*     */       
/* 125 */       LinkedList<Object> rolled = new LinkedList();
/* 126 */       LinkedList<Object> moved = new LinkedList();
/* 127 */       if (j < 0) {
/*     */ 
/*     */         
/* 130 */         int n1 = n + j; int i;
/* 131 */         for (i = 0; i < n1; i++)
/*     */         {
/* 133 */           moved.addFirst(stack.pop());
/*     */         }
/* 135 */         for (i = j; i < 0; i++)
/*     */         {
/* 137 */           rolled.addFirst(stack.pop());
/*     */         }
/* 139 */         stack.addAll(moved);
/* 140 */         stack.addAll(rolled);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 145 */         int n1 = n - j; int i;
/* 146 */         for (i = j; i > 0; i--)
/*     */         {
/* 148 */           rolled.addFirst(stack.pop());
/*     */         }
/* 150 */         for (i = 0; i < n1; i++)
/*     */         {
/* 152 */           moved.addFirst(stack.pop());
/*     */         }
/* 154 */         stack.addAll(rolled);
/* 155 */         stack.addAll(moved);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/function/type4/StackOperators.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */