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
/*    */ 
/*    */ public abstract class StoreInstruction
/*    */   extends LocalVariableInstruction
/*    */   implements PopInstruction
/*    */ {
/*    */   StoreInstruction(short canon_tag, short c_tag) {
/* 73 */     super(canon_tag, c_tag);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected StoreInstruction(short opcode, short c_tag, int n) {
/* 82 */     super(opcode, c_tag, n);
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
/*    */   public void accept(Visitor v) {
/* 94 */     v.visitStackConsumer(this);
/* 95 */     v.visitPopInstruction(this);
/* 96 */     v.visitStoreInstruction(this);
/* 97 */     v.visitTypedInstruction(this);
/* 98 */     v.visitLocalVariableInstruction(this);
/* 99 */     v.visitStoreInstruction(this);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/StoreInstruction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */