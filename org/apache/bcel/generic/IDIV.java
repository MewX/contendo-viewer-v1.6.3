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
/*    */ public class IDIV
/*    */   extends ArithmeticInstruction
/*    */   implements ExceptionThrower
/*    */ {
/*    */   public IDIV() {
/* 68 */     super((short)108);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Class[] getExceptions() {
/* 74 */     return new Class[] { ExceptionConstants.ARITHMETIC_EXCEPTION };
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
/* 87 */     v.visitExceptionThrower(this);
/* 88 */     v.visitTypedInstruction(this);
/* 89 */     v.visitStackProducer(this);
/* 90 */     v.visitStackConsumer(this);
/* 91 */     v.visitArithmeticInstruction(this);
/* 92 */     v.visitIDIV(this);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/IDIV.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */