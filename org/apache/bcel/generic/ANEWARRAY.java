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
/*     */ public class ANEWARRAY
/*     */   extends CPInstruction
/*     */   implements AllocationInstruction, ExceptionThrower, LoadClass, StackProducer
/*     */ {
/*     */   ANEWARRAY() {}
/*     */   
/*     */   public ANEWARRAY(int index) {
/*  74 */     super((short)189, index);
/*     */   }
/*     */   
/*     */   public Class[] getExceptions() {
/*  78 */     Class[] cs = new Class[1 + ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length];
/*     */     
/*  80 */     System.arraycopy(ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION, 0, cs, 0, ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length);
/*     */     
/*  82 */     cs[ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length] = ExceptionConstants.NEGATIVE_ARRAY_SIZE_EXCEPTION;
/*     */     
/*  84 */     return cs;
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
/*  96 */     v.visitLoadClass(this);
/*  97 */     v.visitAllocationInstruction(this);
/*  98 */     v.visitExceptionThrower(this);
/*  99 */     v.visitStackProducer(this);
/* 100 */     v.visitTypedInstruction(this);
/* 101 */     v.visitCPInstruction(this);
/* 102 */     v.visitANEWARRAY(this);
/*     */   }
/*     */   
/*     */   public ObjectType getLoadClassType(ConstantPoolGen cpg) {
/* 106 */     Type t = getType(cpg);
/*     */     
/* 108 */     if (t instanceof ArrayType) {
/* 109 */       t = ((ArrayType)t).getBasicType();
/*     */     }
/*     */     
/* 112 */     return (t instanceof ObjectType) ? (ObjectType)t : null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/ANEWARRAY.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */