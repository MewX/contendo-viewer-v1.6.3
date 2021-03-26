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
/*     */ public class DCONST
/*     */   extends Instruction
/*     */   implements ConstantPushInstruction, TypedInstruction
/*     */ {
/*     */   private double value;
/*     */   
/*     */   DCONST() {}
/*     */   
/*     */   public DCONST(double f) {
/*  76 */     super((short)14, (short)1);
/*     */     
/*  78 */     if (f == 0.0D) {
/*  79 */       this.opcode = 14;
/*  80 */     } else if (f == 1.0D) {
/*  81 */       this.opcode = 15;
/*     */     } else {
/*  83 */       throw new ClassGenException("DCONST can be used only for 0.0 and 1.0: " + f);
/*     */     } 
/*  85 */     this.value = f;
/*     */   }
/*     */   public Number getValue() {
/*  88 */     return new Double(this.value);
/*     */   }
/*     */ 
/*     */   
/*     */   public Type getType(ConstantPoolGen cp) {
/*  93 */     return Type.DOUBLE;
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
/* 110 */     v.visitDCONST(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/DCONST.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */