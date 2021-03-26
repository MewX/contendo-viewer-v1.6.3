/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import org.apache.bcel.ExceptionConstants;
/*     */ import org.apache.bcel.classfile.Constant;
/*     */ import org.apache.bcel.classfile.ConstantFloat;
/*     */ import org.apache.bcel.classfile.ConstantInteger;
/*     */ import org.apache.bcel.classfile.ConstantString;
/*     */ import org.apache.bcel.classfile.ConstantUtf8;
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
/*     */ public class LDC
/*     */   extends CPInstruction
/*     */   implements ExceptionThrower, PushInstruction, TypedInstruction
/*     */ {
/*     */   LDC() {}
/*     */   
/*     */   public LDC(int index) {
/*  76 */     super((short)19, index);
/*  77 */     setSize();
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void setSize() {
/*  82 */     if (this.index <= 255) {
/*  83 */       this.opcode = 18;
/*  84 */       this.length = 2;
/*     */     } else {
/*  86 */       this.opcode = 19;
/*  87 */       this.length = 3;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(DataOutputStream out) throws IOException {
/*  96 */     out.writeByte(this.opcode);
/*     */     
/*  98 */     if (this.length == 2) {
/*  99 */       out.writeByte(this.index);
/*     */     } else {
/* 101 */       out.writeShort(this.index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setIndex(int index) {
/* 108 */     super.setIndex(index);
/* 109 */     setSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
/* 118 */     this.length = 2;
/* 119 */     this.index = bytes.readUnsignedByte();
/*     */   }
/*     */   public Object getValue(ConstantPoolGen cpg) {
/*     */     int i;
/* 123 */     Constant c = cpg.getConstantPool().getConstant(this.index);
/*     */     
/* 125 */     switch (c.getTag()) {
/*     */       case 8:
/* 127 */         i = ((ConstantString)c).getStringIndex();
/* 128 */         c = cpg.getConstantPool().getConstant(i);
/* 129 */         return ((ConstantUtf8)c).getBytes();
/*     */       
/*     */       case 4:
/* 132 */         return new Float(((ConstantFloat)c).getBytes());
/*     */       
/*     */       case 3:
/* 135 */         return new Integer(((ConstantInteger)c).getBytes());
/*     */     } 
/*     */     
/* 138 */     throw new RuntimeException("Unknown or invalid constant type at " + this.index);
/*     */   }
/*     */ 
/*     */   
/*     */   public Type getType(ConstantPoolGen cpg) {
/* 143 */     switch (cpg.getConstantPool().getConstant(this.index).getTag()) { case 8:
/* 144 */         return Type.STRING;
/* 145 */       case 4: return Type.FLOAT;
/* 146 */       case 3: return Type.INT; }
/*     */     
/* 148 */     throw new RuntimeException("Unknown or invalid constant type at " + this.index);
/*     */   }
/*     */ 
/*     */   
/*     */   public Class[] getExceptions() {
/* 153 */     return ExceptionConstants.EXCS_STRING_RESOLUTION;
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
/*     */   public void accept(Visitor v) {
/* 165 */     v.visitStackProducer(this);
/* 166 */     v.visitPushInstruction(this);
/* 167 */     v.visitExceptionThrower(this);
/* 168 */     v.visitTypedInstruction(this);
/* 169 */     v.visitCPInstruction(this);
/* 170 */     v.visitLDC(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/LDC.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */