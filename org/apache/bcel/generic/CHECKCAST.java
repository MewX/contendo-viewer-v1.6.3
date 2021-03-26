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
/*     */ public class CHECKCAST
/*     */   extends CPInstruction
/*     */   implements ExceptionThrower, LoadClass, StackConsumer, StackProducer
/*     */ {
/*     */   CHECKCAST() {}
/*     */   
/*     */   public CHECKCAST(int index) {
/*  76 */     super((short)192, index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Class[] getExceptions() {
/*  82 */     Class[] cs = new Class[1 + ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length];
/*     */     
/*  84 */     System.arraycopy(ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION, 0, cs, 0, ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length);
/*     */     
/*  86 */     cs[ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length] = ExceptionConstants.CLASS_CAST_EXCEPTION;
/*     */     
/*  88 */     return cs;
/*     */   }
/*     */   
/*     */   public ObjectType getLoadClassType(ConstantPoolGen cpg) {
/*  92 */     Type t = getType(cpg);
/*     */     
/*  94 */     if (t instanceof ArrayType) {
/*  95 */       t = ((ArrayType)t).getBasicType();
/*     */     }
/*  97 */     return (t instanceof ObjectType) ? (ObjectType)t : null;
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
/* 109 */     v.visitLoadClass(this);
/* 110 */     v.visitExceptionThrower(this);
/* 111 */     v.visitStackProducer(this);
/* 112 */     v.visitStackConsumer(this);
/* 113 */     v.visitTypedInstruction(this);
/* 114 */     v.visitCPInstruction(this);
/* 115 */     v.visitCHECKCAST(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/CHECKCAST.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */