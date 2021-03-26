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
/*     */ public abstract class ArrayInstruction
/*     */   extends Instruction
/*     */   implements ExceptionThrower, TypedInstruction
/*     */ {
/*     */   ArrayInstruction() {}
/*     */   
/*     */   protected ArrayInstruction(short opcode) {
/*  75 */     super(opcode, (short)1);
/*     */   }
/*     */   
/*     */   public Class[] getExceptions() {
/*  79 */     return ExceptionConstants.EXCS_ARRAY_EXCEPTION;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getType(ConstantPoolGen cp) {
/*  85 */     switch (this.opcode) { case 46:
/*     */       case 79:
/*  87 */         return Type.INT;
/*     */       case 52: case 85:
/*  89 */         return Type.CHAR;
/*     */       case 51: case 84:
/*  91 */         return Type.BYTE;
/*     */       case 53: case 86:
/*  93 */         return Type.SHORT;
/*     */       case 47: case 80:
/*  95 */         return Type.LONG;
/*     */       case 49: case 82:
/*  97 */         return Type.DOUBLE;
/*     */       case 48: case 81:
/*  99 */         return Type.FLOAT;
/*     */       case 50: case 83:
/* 101 */         return Type.OBJECT; }
/*     */     
/* 103 */     throw new ClassGenException("Oops: unknown case in switch" + this.opcode);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/ArrayInstruction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */