/*    */ package org.apache.bcel.generic;
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
/*    */ public abstract class IfInstruction
/*    */   extends BranchInstruction
/*    */   implements StackConsumer
/*    */ {
/*    */   IfInstruction() {}
/*    */   
/*    */   protected IfInstruction(short opcode, InstructionHandle target) {
/* 74 */     super(opcode, target);
/*    */   }
/*    */   
/*    */   public abstract IfInstruction negate();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/IfInstruction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */