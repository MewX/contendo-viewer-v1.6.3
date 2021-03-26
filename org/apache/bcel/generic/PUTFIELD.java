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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PUTFIELD
/*     */   extends FieldInstruction
/*     */   implements ExceptionThrower, PopInstruction
/*     */ {
/*     */   PUTFIELD() {}
/*     */   
/*     */   public PUTFIELD(int index) {
/*  79 */     super((short)181, index);
/*     */   }
/*     */   public int consumeStack(ConstantPoolGen cpg) {
/*  82 */     return getFieldSize(cpg) + 1;
/*     */   }
/*     */   public Class[] getExceptions() {
/*  85 */     Class[] cs = new Class[2 + ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length];
/*     */     
/*  87 */     System.arraycopy(ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION, 0, cs, 0, ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length);
/*     */ 
/*     */     
/*  90 */     cs[ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length + 1] = ExceptionConstants.INCOMPATIBLE_CLASS_CHANGE_ERROR;
/*     */     
/*  92 */     cs[ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length] = ExceptionConstants.NULL_POINTER_EXCEPTION;
/*     */ 
/*     */     
/*  95 */     return cs;
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
/* 108 */     v.visitExceptionThrower(this);
/* 109 */     v.visitStackConsumer(this);
/* 110 */     v.visitPopInstruction(this);
/* 111 */     v.visitTypedInstruction(this);
/* 112 */     v.visitLoadClass(this);
/* 113 */     v.visitCPInstruction(this);
/* 114 */     v.visitFieldOrMethod(this);
/* 115 */     v.visitFieldInstruction(this);
/* 116 */     v.visitPUTFIELD(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/PUTFIELD.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */