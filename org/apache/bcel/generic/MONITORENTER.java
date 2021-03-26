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
/*    */ public class MONITORENTER
/*    */   extends Instruction
/*    */   implements ExceptionThrower, StackConsumer
/*    */ {
/*    */   public MONITORENTER() {
/* 67 */     super((short)194, (short)1);
/*    */   }
/*    */   
/*    */   public Class[] getExceptions() {
/* 71 */     return new Class[] { ExceptionConstants.NULL_POINTER_EXCEPTION };
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
/* 84 */     v.visitExceptionThrower(this);
/* 85 */     v.visitStackConsumer(this);
/* 86 */     v.visitMONITORENTER(this);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/MONITORENTER.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */