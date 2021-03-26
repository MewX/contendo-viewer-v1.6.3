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
/*     */ public class GETSTATIC
/*     */   extends FieldInstruction
/*     */   implements ExceptionThrower, PushInstruction
/*     */ {
/*     */   GETSTATIC() {}
/*     */   
/*     */   public GETSTATIC(int index) {
/*  77 */     super((short)178, index);
/*     */   }
/*     */   public int produceStack(ConstantPoolGen cpg) {
/*  80 */     return getFieldSize(cpg);
/*     */   }
/*     */   public Class[] getExceptions() {
/*  83 */     Class[] cs = new Class[1 + ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length];
/*     */     
/*  85 */     System.arraycopy(ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION, 0, cs, 0, ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length);
/*     */     
/*  87 */     cs[ExceptionConstants.EXCS_FIELD_AND_METHOD_RESOLUTION.length] = ExceptionConstants.INCOMPATIBLE_CLASS_CHANGE_ERROR;
/*     */ 
/*     */     
/*  90 */     return cs;
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
/* 103 */     v.visitStackProducer(this);
/* 104 */     v.visitPushInstruction(this);
/* 105 */     v.visitExceptionThrower(this);
/* 106 */     v.visitTypedInstruction(this);
/* 107 */     v.visitLoadClass(this);
/* 108 */     v.visitCPInstruction(this);
/* 109 */     v.visitFieldOrMethod(this);
/* 110 */     v.visitFieldInstruction(this);
/* 111 */     v.visitGETSTATIC(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/GETSTATIC.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */