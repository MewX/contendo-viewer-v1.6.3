/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import org.apache.bcel.Constants;
/*     */ import org.apache.bcel.ExceptionConstants;
/*     */ import org.apache.bcel.util.ByteSequence;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NEWARRAY
/*     */   extends Instruction
/*     */   implements AllocationInstruction, ExceptionThrower, StackProducer
/*     */ {
/*     */   private byte type;
/*     */   
/*     */   NEWARRAY() {}
/*     */   
/*     */   public NEWARRAY(byte type) {
/*  78 */     super((short)188, (short)2);
/*  79 */     this.type = type;
/*     */   }
/*     */   
/*     */   public NEWARRAY(BasicType type) {
/*  83 */     this(type.getType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(DataOutputStream out) throws IOException {
/*  91 */     out.writeByte(this.opcode);
/*  92 */     out.writeByte(this.type);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final byte getTypecode() {
/*  98 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final Type getType() {
/* 104 */     return new ArrayType(BasicType.getType(this.type), 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(boolean verbose) {
/* 111 */     return super.toString(verbose) + " " + Constants.TYPE_NAMES[this.type];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
/* 118 */     this.type = bytes.readByte();
/* 119 */     this.length = 2;
/*     */   }
/*     */   
/*     */   public Class[] getExceptions() {
/* 123 */     return new Class[] { ExceptionConstants.NEGATIVE_ARRAY_SIZE_EXCEPTION };
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
/* 136 */     v.visitAllocationInstruction(this);
/* 137 */     v.visitExceptionThrower(this);
/* 138 */     v.visitStackProducer(this);
/* 139 */     v.visitNEWARRAY(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/NEWARRAY.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */