/*    */ package org.apache.pdfbox.pdmodel.common.function.type4;
/*    */ 
/*    */ import java.util.Stack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class ConditionalOperators
/*    */ {
/*    */   static class If
/*    */     implements Operator
/*    */   {
/*    */     public void execute(ExecutionContext context) {
/* 34 */       Stack<Object> stack = context.getStack();
/* 35 */       InstructionSequence proc = (InstructionSequence)stack.pop();
/* 36 */       Boolean condition = (Boolean)stack.pop();
/* 37 */       if (condition.booleanValue())
/*    */       {
/* 39 */         proc.execute(context);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static class IfElse
/*    */     implements Operator
/*    */   {
/*    */     public void execute(ExecutionContext context) {
/* 51 */       Stack<Object> stack = context.getStack();
/* 52 */       InstructionSequence proc2 = (InstructionSequence)stack.pop();
/* 53 */       InstructionSequence proc1 = (InstructionSequence)stack.pop();
/* 54 */       Boolean condition = (Boolean)stack.pop();
/* 55 */       if (condition.booleanValue()) {
/*    */         
/* 57 */         proc1.execute(context);
/*    */       }
/*    */       else {
/*    */         
/* 61 */         proc2.execute(context);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/function/type4/ConditionalOperators.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */