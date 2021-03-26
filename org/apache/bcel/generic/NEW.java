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
/*     */ public class NEW
/*     */   extends CPInstruction
/*     */   implements AllocationInstruction, ExceptionThrower, LoadClass, StackProducer
/*     */ {
/*     */   NEW() {}
/*     */   
/*     */   public NEW(int index) {
/*  75 */     super((short)187, index);
/*     */   }
/*     */   
/*     */   public Class[] getExceptions() {
/*  79 */     Class[] cs = new Class[2 + ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length];
/*     */     
/*  81 */     System.arraycopy(ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION, 0, cs, 0, ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length);
/*     */ 
/*     */     
/*  84 */     cs[ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length + 1] = ExceptionConstants.INSTANTIATION_ERROR;
/*  85 */     cs[ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length] = ExceptionConstants.ILLEGAL_ACCESS_ERROR;
/*     */     
/*  87 */     return cs;
/*     */   }
/*     */   
/*     */   public ObjectType getLoadClassType(ConstantPoolGen cpg) {
/*  91 */     return (ObjectType)getType(cpg);
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
/*     */   public void accept(Visitor v) {
/* 103 */     v.visitLoadClass(this);
/* 104 */     v.visitAllocationInstruction(this);
/* 105 */     v.visitExceptionThrower(this);
/* 106 */     v.visitStackProducer(this);
/* 107 */     v.visitTypedInstruction(this);
/* 108 */     v.visitCPInstruction(this);
/* 109 */     v.visitNEW(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/NEW.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */