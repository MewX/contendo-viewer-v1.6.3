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
/*     */ public class PUTSTATIC
/*     */   extends FieldInstruction
/*     */   implements ExceptionThrower, PopInstruction
/*     */ {
/*     */   PUTSTATIC() {}
/*     */   
/*     */   public PUTSTATIC(int index) {
/*  78 */     super((short)179, index);
/*     */   }
/*     */   public int consumeStack(ConstantPoolGen cpg) {
/*  81 */     return getFieldSize(cpg);
/*     */   }
/*     */   public Class[] getExceptions() {
/*  84 */     Class[] cs = new Class[1 + ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length];
/*     */     
/*  86 */     System.arraycopy(ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION, 0, cs, 0, ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length);
/*     */     
/*  88 */     cs[ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length] = ExceptionConstants.INCOMPATIBLE_CLASS_CHANGE_ERROR;
/*     */ 
/*     */     
/*  91 */     return cs;
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
/* 104 */     v.visitExceptionThrower(this);
/* 105 */     v.visitStackConsumer(this);
/* 106 */     v.visitPopInstruction(this);
/* 107 */     v.visitTypedInstruction(this);
/* 108 */     v.visitLoadClass(this);
/* 109 */     v.visitCPInstruction(this);
/* 110 */     v.visitFieldOrMethod(this);
/* 111 */     v.visitFieldInstruction(this);
/* 112 */     v.visitPUTSTATIC(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/PUTSTATIC.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */