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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LCONST
/*     */   extends Instruction
/*     */   implements ConstantPushInstruction, TypedInstruction
/*     */ {
/*     */   private long value;
/*     */   
/*     */   LCONST() {}
/*     */   
/*     */   public LCONST(long l) {
/*  76 */     super((short)9, (short)1);
/*     */     
/*  78 */     if (l == 0L) {
/*  79 */       this.opcode = 9;
/*  80 */     } else if (l == 1L) {
/*  81 */       this.opcode = 10;
/*     */     } else {
/*  83 */       throw new ClassGenException("LCONST can be used only for 0 and 1: " + l);
/*     */     } 
/*  85 */     this.value = l;
/*     */   }
/*     */   public Number getValue() {
/*  88 */     return new Long(this.value);
/*     */   }
/*     */ 
/*     */   
/*     */   public Type getType(ConstantPoolGen cp) {
/*  93 */     return Type.LONG;
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
/* 106 */     v.visitPushInstruction(this);
/* 107 */     v.visitStackProducer(this);
/* 108 */     v.visitTypedInstruction(this);
/* 109 */     v.visitConstantPushInstruction(this);
/* 110 */     v.visitLCONST(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/LCONST.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */