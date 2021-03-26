/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import org.apache.bcel.classfile.Constant;
/*     */ import org.apache.bcel.classfile.ConstantDouble;
/*     */ import org.apache.bcel.classfile.ConstantLong;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LDC2_W
/*     */   extends CPInstruction
/*     */   implements PushInstruction, TypedInstruction
/*     */ {
/*     */   LDC2_W() {}
/*     */   
/*     */   public LDC2_W(int index) {
/*  74 */     super((short)20, index);
/*     */   }
/*     */   
/*     */   public Type getType(ConstantPoolGen cpg) {
/*  78 */     switch (cpg.getConstantPool().getConstant(this.index).getTag()) { case 5:
/*  79 */         return Type.LONG;
/*  80 */       case 6: return Type.DOUBLE; }
/*     */     
/*  82 */     throw new RuntimeException("Unknown constant type " + this.opcode);
/*     */   }
/*     */ 
/*     */   
/*     */   public Number getValue(ConstantPoolGen cpg) {
/*  87 */     Constant c = cpg.getConstantPool().getConstant(this.index);
/*     */     
/*  89 */     switch (c.getTag()) {
/*     */       case 5:
/*  91 */         return new Long(((ConstantLong)c).getBytes());
/*     */       
/*     */       case 6:
/*  94 */         return new Double(((ConstantDouble)c).getBytes());
/*     */     } 
/*     */     
/*  97 */     throw new RuntimeException("Unknown or invalid constant type at " + this.index);
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
/* 110 */     v.visitStackProducer(this);
/* 111 */     v.visitPushInstruction(this);
/* 112 */     v.visitTypedInstruction(this);
/* 113 */     v.visitCPInstruction(this);
/* 114 */     v.visitLDC2_W(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/LDC2_W.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */