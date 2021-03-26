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
/*     */ 
/*     */ public abstract class ArithmeticInstruction
/*     */   extends Instruction
/*     */   implements StackConsumer, StackProducer, TypedInstruction
/*     */ {
/*     */   ArithmeticInstruction() {}
/*     */   
/*     */   protected ArithmeticInstruction(short opcode) {
/*  75 */     super(opcode, (short)1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getType(ConstantPoolGen cp) {
/*  81 */     switch (this.opcode) { case 99: case 103: case 107: case 111:
/*     */       case 115:
/*     */       case 119:
/*  84 */         return Type.DOUBLE;
/*     */       case 98: case 102: case 106: case 110:
/*     */       case 114:
/*     */       case 118:
/*  88 */         return Type.FLOAT;
/*     */       case 96: case 100: case 104: case 108: case 112: case 116: case 120: case 122:
/*     */       case 124:
/*     */       case 126:
/*     */       case 128:
/*     */       case 130:
/*  94 */         return Type.INT;
/*     */       case 97: case 101: case 105: case 109: case 113: case 117: case 121: case 123:
/*     */       case 125:
/*     */       case 127:
/*     */       case 129:
/*     */       case 131:
/* 100 */         return Type.LONG; }
/*     */ 
/*     */     
/* 103 */     throw new ClassGenException("Unknown type " + this.opcode);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/ArithmeticInstruction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */