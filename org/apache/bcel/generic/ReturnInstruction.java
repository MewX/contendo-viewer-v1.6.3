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
/*     */ 
/*     */ public abstract class ReturnInstruction
/*     */   extends Instruction
/*     */   implements ExceptionThrower, StackConsumer, TypedInstruction
/*     */ {
/*     */   ReturnInstruction() {}
/*     */   
/*     */   protected ReturnInstruction(short opcode) {
/*  77 */     super(opcode, (short)1);
/*     */   }
/*     */   
/*     */   public Type getType() {
/*  81 */     switch (this.opcode) { case 172:
/*  82 */         return Type.INT;
/*  83 */       case 173: return Type.LONG;
/*  84 */       case 174: return Type.FLOAT;
/*  85 */       case 175: return Type.DOUBLE;
/*  86 */       case 176: return Type.OBJECT;
/*  87 */       case 177: return Type.VOID; }
/*     */ 
/*     */     
/*  90 */     throw new ClassGenException("Unknown type " + this.opcode);
/*     */   }
/*     */ 
/*     */   
/*     */   public Class[] getExceptions() {
/*  95 */     return new Class[] { ExceptionConstants.ILLEGAL_MONITOR_STATE };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getType(ConstantPoolGen cp) {
/* 101 */     return getType();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/ReturnInstruction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */