/*    */ package org.apache.bcel.generic;
/*    */ 
/*    */ import org.apache.bcel.ExceptionConstants;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ATHROW
/*    */   extends Instruction
/*    */   implements ExceptionThrower, UnconditionalBranch
/*    */ {
/*    */   public ATHROW() {
/* 69 */     super((short)191, (short)1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Class[] getExceptions() {
/* 75 */     return new Class[] { ExceptionConstants.THROWABLE };
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
/* 88 */     v.visitUnconditionalBranch(this);
/* 89 */     v.visitExceptionThrower(this);
/* 90 */     v.visitATHROW(this);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/ATHROW.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */