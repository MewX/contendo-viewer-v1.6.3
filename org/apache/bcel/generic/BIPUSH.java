/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BIPUSH
/*     */   extends Instruction
/*     */   implements ConstantPushInstruction
/*     */ {
/*     */   private byte b;
/*     */   
/*     */   BIPUSH() {}
/*     */   
/*     */   public BIPUSH(byte b) {
/*  80 */     super((short)16, (short)2);
/*  81 */     this.b = b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(DataOutputStream out) throws IOException {
/*  88 */     super.dump(out);
/*  89 */     out.writeByte(this.b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(boolean verbose) {
/*  96 */     return super.toString(verbose) + " " + this.b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
/* 104 */     this.length = 2;
/* 105 */     this.b = bytes.readByte();
/*     */   }
/*     */   public Number getValue() {
/* 108 */     return new Integer(this.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public Type getType(ConstantPoolGen cp) {
/* 113 */     return Type.BYTE;
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
/* 126 */     v.visitPushInstruction(this);
/* 127 */     v.visitStackProducer(this);
/* 128 */     v.visitTypedInstruction(this);
/* 129 */     v.visitConstantPushInstruction(this);
/* 130 */     v.visitBIPUSH(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/BIPUSH.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */