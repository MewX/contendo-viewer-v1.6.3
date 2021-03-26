/*     */ package org.apache.pdfbox.pdmodel.common.function.type4;
/*     */ 
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
/*     */ class BitwiseOperators
/*     */ {
/*     */   private static abstract class AbstractLogicalOperator
/*     */     implements Operator
/*     */   {
/*     */     private AbstractLogicalOperator() {}
/*     */     
/*     */     public void execute(ExecutionContext context) {
/*  34 */       Stack<Object> stack = context.getStack();
/*  35 */       Object op2 = stack.pop();
/*  36 */       Object op1 = stack.pop();
/*  37 */       if (op1 instanceof Boolean && op2 instanceof Boolean) {
/*     */         
/*  39 */         boolean bool1 = ((Boolean)op1).booleanValue();
/*  40 */         boolean bool2 = ((Boolean)op2).booleanValue();
/*  41 */         boolean result = applyForBoolean(bool1, bool2);
/*  42 */         stack.push(Boolean.valueOf(result));
/*     */       }
/*  44 */       else if (op1 instanceof Integer && op2 instanceof Integer) {
/*     */         
/*  46 */         int int1 = ((Integer)op1).intValue();
/*  47 */         int int2 = ((Integer)op2).intValue();
/*  48 */         int result = applyforInteger(int1, int2);
/*  49 */         stack.push(Integer.valueOf(result));
/*     */       }
/*     */       else {
/*     */         
/*  53 */         throw new ClassCastException("Operands must be bool/bool or int/int");
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected abstract boolean applyForBoolean(boolean param1Boolean1, boolean param1Boolean2);
/*     */ 
/*     */ 
/*     */     
/*     */     protected abstract int applyforInteger(int param1Int1, int param1Int2);
/*     */   }
/*     */ 
/*     */   
/*     */   static class And
/*     */     extends AbstractLogicalOperator
/*     */   {
/*     */     protected boolean applyForBoolean(boolean bool1, boolean bool2) {
/*  71 */       return bool1 & bool2;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected int applyforInteger(int int1, int int2) {
/*  77 */       return int1 & int2;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class Bitshift
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/*  87 */       Stack<Object> stack = context.getStack();
/*  88 */       int shift = ((Integer)stack.pop()).intValue();
/*  89 */       int int1 = ((Integer)stack.pop()).intValue();
/*  90 */       if (shift < 0) {
/*     */         
/*  92 */         int result = int1 >> Math.abs(shift);
/*  93 */         stack.push(Integer.valueOf(result));
/*     */       }
/*     */       else {
/*     */         
/*  97 */         int result = int1 << shift;
/*  98 */         stack.push(Integer.valueOf(result));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class False
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 110 */       Stack<Object> stack = context.getStack();
/* 111 */       stack.push(Boolean.FALSE);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Not
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 122 */       Stack<Object> stack = context.getStack();
/* 123 */       Object op1 = stack.pop();
/* 124 */       if (op1 instanceof Boolean) {
/*     */         
/* 126 */         boolean bool1 = ((Boolean)op1).booleanValue();
/* 127 */         boolean result = !bool1;
/* 128 */         stack.push(Boolean.valueOf(result));
/*     */       }
/* 130 */       else if (op1 instanceof Integer) {
/*     */         
/* 132 */         int int1 = ((Integer)op1).intValue();
/* 133 */         int result = -int1;
/* 134 */         stack.push(Integer.valueOf(result));
/*     */       }
/*     */       else {
/*     */         
/* 138 */         throw new ClassCastException("Operand must be bool or int");
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Or
/*     */     extends AbstractLogicalOperator
/*     */   {
/*     */     protected boolean applyForBoolean(boolean bool1, boolean bool2) {
/* 151 */       return bool1 | bool2;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected int applyforInteger(int int1, int int2) {
/* 157 */       return int1 | int2;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class True
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 168 */       Stack<Object> stack = context.getStack();
/* 169 */       stack.push(Boolean.TRUE);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Xor
/*     */     extends AbstractLogicalOperator
/*     */   {
/*     */     protected boolean applyForBoolean(boolean bool1, boolean bool2) {
/* 181 */       return bool1 ^ bool2;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected int applyforInteger(int int1, int int2) {
/* 187 */       return int1 ^ int2;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/function/type4/BitwiseOperators.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */