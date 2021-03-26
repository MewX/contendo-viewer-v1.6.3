/*    */ package org.apache.bcel.verifier.structurals;
/*    */ 
/*    */ import org.apache.bcel.generic.InstructionHandle;
/*    */ import org.apache.bcel.generic.ObjectType;
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
/*    */ 
/*    */ public class ExceptionHandler
/*    */ {
/*    */   private ObjectType catchtype;
/*    */   private InstructionHandle handlerpc;
/*    */   
/*    */   ExceptionHandler(ObjectType catch_type, InstructionHandle handler_pc) {
/* 76 */     this.catchtype = catch_type;
/* 77 */     this.handlerpc = handler_pc;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ObjectType getExceptionType() {
/* 84 */     return this.catchtype;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InstructionHandle getHandlerStart() {
/* 91 */     return this.handlerpc;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/verifier/structurals/ExceptionHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */