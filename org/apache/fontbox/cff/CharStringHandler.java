/*    */ package org.apache.fontbox.cff;
/*    */ 
/*    */ import java.util.List;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class CharStringHandler
/*    */ {
/*    */   public List<Number> handleSequence(List<Object> sequence) {
/* 40 */     Stack<Number> stack = new Stack<Number>();
/* 41 */     for (Object obj : sequence) {
/*    */       
/* 43 */       if (obj instanceof CharStringCommand) {
/*    */         
/* 45 */         List<Number> results = handleCommand(stack, (CharStringCommand)obj);
/* 46 */         stack.clear();
/* 47 */         if (results != null)
/*    */         {
/* 49 */           stack.addAll(results);
/*    */         }
/*    */         
/*    */         continue;
/*    */       } 
/* 54 */       stack.push((Number)obj);
/*    */     } 
/*    */     
/* 57 */     return stack;
/*    */   }
/*    */   
/*    */   public abstract List<Number> handleCommand(List<Number> paramList, CharStringCommand paramCharStringCommand);
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cff/CharStringHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */