/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import org.apache.bcel.Constants;
/*     */ import org.apache.bcel.classfile.Constant;
/*     */ import org.apache.bcel.classfile.ConstantPool;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CPInstruction
/*     */   extends Instruction
/*     */   implements IndexedInstruction, TypedInstruction
/*     */ {
/*     */   protected int index;
/*     */   
/*     */   CPInstruction() {}
/*     */   
/*     */   protected CPInstruction(short opcode, int index) {
/*  88 */     super(opcode, (short)3);
/*  89 */     setIndex(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(DataOutputStream out) throws IOException {
/*  97 */     out.writeByte(this.opcode);
/*  98 */     out.writeShort(this.index);
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
/*     */   public String toString(boolean verbose) {
/* 111 */     return super.toString(verbose) + " " + this.index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(ConstantPool cp) {
/* 118 */     Constant c = cp.getConstant(this.index);
/* 119 */     String str = cp.constantToString(c);
/*     */     
/* 121 */     if (c instanceof org.apache.bcel.classfile.ConstantClass) {
/* 122 */       str = str.replace('.', '/');
/*     */     }
/* 124 */     return Constants.OPCODE_NAMES[this.opcode] + " " + str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
/* 135 */     setIndex(bytes.readUnsignedShort());
/* 136 */     this.length = 3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getIndex() {
/* 142 */     return this.index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIndex(int index) {
/* 149 */     if (index < 0) {
/* 150 */       throw new ClassGenException("Negative index value: " + index);
/*     */     }
/* 152 */     this.index = index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getType(ConstantPoolGen cpg) {
/* 158 */     ConstantPool cp = cpg.getConstantPool();
/* 159 */     String name = cp.getConstantString(this.index, (byte)7);
/*     */     
/* 161 */     if (!name.startsWith("[")) {
/* 162 */       name = "L" + name + ";";
/*     */     }
/* 164 */     return Type.getType(name);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/CPInstruction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */