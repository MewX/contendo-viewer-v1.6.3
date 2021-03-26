/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import org.apache.bcel.ExceptionConstants;
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
/*     */ public class INVOKESTATIC
/*     */   extends InvokeInstruction
/*     */ {
/*     */   INVOKESTATIC() {}
/*     */   
/*     */   public INVOKESTATIC(int index) {
/*  75 */     super((short)184, index);
/*     */   }
/*     */   
/*     */   public Class[] getExceptions() {
/*  79 */     Class[] cs = new Class[2 + ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length];
/*     */     
/*  81 */     System.arraycopy(ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION, 0, cs, 0, ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length);
/*     */ 
/*     */     
/*  84 */     cs[ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length] = ExceptionConstants.UNSATISFIED_LINK_ERROR;
/*  85 */     cs[ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length + 1] = ExceptionConstants.INCOMPATIBLE_CLASS_CHANGE_ERROR;
/*     */     
/*  87 */     return cs;
/*     */   }
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
/*     */   public void accept(Visitor v) {
/* 100 */     v.visitExceptionThrower(this);
/* 101 */     v.visitTypedInstruction(this);
/* 102 */     v.visitStackConsumer(this);
/* 103 */     v.visitStackProducer(this);
/* 104 */     v.visitLoadClass(this);
/* 105 */     v.visitCPInstruction(this);
/* 106 */     v.visitFieldOrMethod(this);
/* 107 */     v.visitInvokeInstruction(this);
/* 108 */     v.visitINVOKESTATIC(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/INVOKESTATIC.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */