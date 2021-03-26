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
/*     */ public class INSTANCEOF
/*     */   extends CPInstruction
/*     */   implements ExceptionThrower, LoadClass, StackConsumer, StackProducer
/*     */ {
/*     */   INSTANCEOF() {}
/*     */   
/*     */   public INSTANCEOF(int index) {
/*  73 */     super((short)193, index);
/*     */   }
/*     */   
/*     */   public Class[] getExceptions() {
/*  77 */     return ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION;
/*     */   }
/*     */   
/*     */   public ObjectType getLoadClassType(ConstantPoolGen cpg) {
/*  81 */     Type t = getType(cpg);
/*     */     
/*  83 */     if (t instanceof ArrayType) {
/*  84 */       t = ((ArrayType)t).getBasicType();
/*     */     }
/*  86 */     return (t instanceof ObjectType) ? (ObjectType)t : null;
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
/*  98 */     v.visitLoadClass(this);
/*  99 */     v.visitExceptionThrower(this);
/* 100 */     v.visitStackProducer(this);
/* 101 */     v.visitStackConsumer(this);
/* 102 */     v.visitTypedInstruction(this);
/* 103 */     v.visitCPInstruction(this);
/* 104 */     v.visitINSTANCEOF(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/INSTANCEOF.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */