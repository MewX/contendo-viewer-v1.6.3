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
/*     */ public class ICONST
/*     */   extends Instruction
/*     */   implements ConstantPushInstruction, TypedInstruction
/*     */ {
/*     */   private int value;
/*     */   
/*     */   ICONST() {}
/*     */   
/*     */   public ICONST(int i) {
/*  76 */     super((short)3, (short)1);
/*     */     
/*  78 */     if (i >= -1 && i <= 5) {
/*  79 */       this.opcode = (short)(3 + i);
/*     */     } else {
/*  81 */       throw new ClassGenException("ICONST can be used only for value between -1 and 5: " + i);
/*     */     } 
/*  83 */     this.value = i;
/*     */   }
/*     */   public Number getValue() {
/*  86 */     return new Integer(this.value);
/*     */   }
/*     */ 
/*     */   
/*     */   public Type getType(ConstantPoolGen cp) {
/*  91 */     return Type.INT;
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
/* 104 */     v.visitPushInstruction(this);
/* 105 */     v.visitStackProducer(this);
/* 106 */     v.visitTypedInstruction(this);
/* 107 */     v.visitConstantPushInstruction(this);
/* 108 */     v.visitICONST(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/ICONST.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */