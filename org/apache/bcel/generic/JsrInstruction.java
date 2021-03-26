/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class JsrInstruction
/*     */   extends BranchInstruction
/*     */   implements StackProducer, TypedInstruction, UnconditionalBranch
/*     */ {
/*     */   JsrInstruction(short opcode, InstructionHandle target) {
/*  67 */     super(opcode, target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   JsrInstruction() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getType(ConstantPoolGen cp) {
/*  79 */     return new ReturnaddressType(physicalSuccessor());
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
/*     */ 
/*     */ 
/*     */   
/*     */   public InstructionHandle physicalSuccessor() {
/*  95 */     InstructionHandle ih = this.target;
/*     */ 
/*     */     
/*  98 */     while (ih.getPrev() != null) {
/*  99 */       ih = ih.getPrev();
/*     */     }
/*     */     
/* 102 */     while (ih.getInstruction() != this) {
/* 103 */       ih = ih.getNext();
/*     */     }
/* 105 */     InstructionHandle toThis = ih;
/*     */     
/* 107 */     while (ih != null) {
/* 108 */       ih = ih.getNext();
/* 109 */       if (ih != null && ih.getInstruction() == this) {
/* 110 */         throw new RuntimeException("physicalSuccessor() called on a shared JsrInstruction.");
/*     */       }
/*     */     } 
/*     */     
/* 114 */     return toThis.getNext();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/JsrInstruction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */