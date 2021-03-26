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
/*     */ 
/*     */ 
/*     */ class RelationalOperators
/*     */ {
/*     */   static class Eq
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/*  34 */       Stack<Object> stack = context.getStack();
/*  35 */       Object op2 = stack.pop();
/*  36 */       Object op1 = stack.pop();
/*  37 */       boolean result = isEqual(op1, op2);
/*  38 */       stack.push(Boolean.valueOf(result));
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean isEqual(Object op1, Object op2) {
/*  43 */       boolean result = false;
/*  44 */       if (op1 instanceof Number && op2 instanceof Number) {
/*     */         
/*  46 */         Number num1 = (Number)op1;
/*  47 */         Number num2 = (Number)op2;
/*  48 */         result = (num1.floatValue() == num2.floatValue());
/*     */       }
/*     */       else {
/*     */         
/*  52 */         result = op1.equals(op2);
/*     */       } 
/*  54 */       return result;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static abstract class AbstractNumberComparisonOperator
/*     */     implements Operator
/*     */   {
/*     */     private AbstractNumberComparisonOperator() {}
/*     */     
/*     */     public void execute(ExecutionContext context) {
/*  65 */       Stack<Object> stack = context.getStack();
/*  66 */       Object op2 = stack.pop();
/*  67 */       Object op1 = stack.pop();
/*  68 */       Number num1 = (Number)op1;
/*  69 */       Number num2 = (Number)op2;
/*  70 */       boolean result = compare(num1, num2);
/*  71 */       stack.push(Boolean.valueOf(result));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected abstract boolean compare(Number param1Number1, Number param1Number2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class Ge
/*     */     extends AbstractNumberComparisonOperator
/*     */   {
/*     */     protected boolean compare(Number num1, Number num2) {
/*  85 */       return (num1.floatValue() >= num2.floatValue());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Gt
/*     */     extends AbstractNumberComparisonOperator
/*     */   {
/*     */     protected boolean compare(Number num1, Number num2) {
/*  97 */       return (num1.floatValue() > num2.floatValue());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Le
/*     */     extends AbstractNumberComparisonOperator
/*     */   {
/*     */     protected boolean compare(Number num1, Number num2) {
/* 109 */       return (num1.floatValue() <= num2.floatValue());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Lt
/*     */     extends AbstractNumberComparisonOperator
/*     */   {
/*     */     protected boolean compare(Number num1, Number num2) {
/* 121 */       return (num1.floatValue() < num2.floatValue());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Ne
/*     */     extends Eq
/*     */   {
/*     */     protected boolean isEqual(Object op1, Object op2) {
/* 133 */       boolean result = super.isEqual(op1, op2);
/* 134 */       return !result;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/function/type4/RelationalOperators.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */