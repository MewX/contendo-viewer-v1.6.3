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
/*    */ public class IFNULL
/*    */   extends IfInstruction
/*    */ {
/*    */   IFNULL() {}
/*    */   
/*    */   public IFNULL(InstructionHandle target) {
/* 73 */     super((short)198, target);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IfInstruction negate() {
/* 80 */     return new IFNONNULL(this.target);
/*    */   }
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
/*    */   public void accept(Visitor v) {
/* 93 */     v.visitStackConsumer(this);
/* 94 */     v.visitBranchInstruction(this);
/* 95 */     v.visitIfInstruction(this);
/* 96 */     v.visitIFNULL(this);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/IFNULL.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */