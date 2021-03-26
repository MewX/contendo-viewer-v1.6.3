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
/*     */ public class FCONST
/*     */   extends Instruction
/*     */   implements ConstantPushInstruction, TypedInstruction
/*     */ {
/*     */   private float value;
/*     */   
/*     */   FCONST() {}
/*     */   
/*     */   public FCONST(float f) {
/*  76 */     super((short)11, (short)1);
/*     */     
/*  78 */     if (f == 0.0D) {
/*  79 */       this.opcode = 11;
/*  80 */     } else if (f == 1.0D) {
/*  81 */       this.opcode = 12;
/*  82 */     } else if (f == 2.0D) {
/*  83 */       this.opcode = 13;
/*     */     } else {
/*  85 */       throw new ClassGenException("FCONST can be used only for 0.0, 1.0 and 2.0: " + f);
/*     */     } 
/*  87 */     this.value = f;
/*     */   }
/*     */   public Number getValue() {
/*  90 */     return new Float(this.value);
/*     */   }
/*     */ 
/*     */   
/*     */   public Type getType(ConstantPoolGen cp) {
/*  95 */     return Type.FLOAT;
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
/* 108 */     v.visitPushInstruction(this);
/* 109 */     v.visitStackProducer(this);
/* 110 */     v.visitTypedInstruction(this);
/* 111 */     v.visitConstantPushInstruction(this);
/* 112 */     v.visitFCONST(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/FCONST.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */