/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import org.apache.bcel.ExceptionConstants;
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
/*     */ public class MULTIANEWARRAY
/*     */   extends CPInstruction
/*     */   implements AllocationInstruction, ExceptionThrower, LoadClass
/*     */ {
/*     */   private short dimensions;
/*     */   
/*     */   MULTIANEWARRAY() {}
/*     */   
/*     */   public MULTIANEWARRAY(int index, short dimensions) {
/*  78 */     super((short)197, index);
/*     */     
/*  80 */     if (dimensions < 1) {
/*  81 */       throw new ClassGenException("Invalid dimensions value: " + dimensions);
/*     */     }
/*  83 */     this.dimensions = dimensions;
/*  84 */     this.length = 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(DataOutputStream out) throws IOException {
/*  92 */     out.writeByte(this.opcode);
/*  93 */     out.writeShort(this.index);
/*  94 */     out.writeByte(this.dimensions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
/* 103 */     super.initFromFile(bytes, wide);
/* 104 */     this.dimensions = (short)bytes.readByte();
/* 105 */     this.length = 4;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final short getDimensions() {
/* 111 */     return this.dimensions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(boolean verbose) {
/* 117 */     return super.toString(verbose) + " " + this.index + " " + this.dimensions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(ConstantPool cp) {
/* 124 */     return super.toString(cp) + " " + this.dimensions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int consumeStack(ConstantPoolGen cpg) {
/* 132 */     return this.dimensions;
/*     */   }
/*     */   public Class[] getExceptions() {
/* 135 */     Class[] cs = new Class[2 + ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length];
/*     */     
/* 137 */     System.arraycopy(ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION, 0, cs, 0, ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length);
/*     */ 
/*     */     
/* 140 */     cs[ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length + 1] = ExceptionConstants.NEGATIVE_ARRAY_SIZE_EXCEPTION;
/* 141 */     cs[ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length] = ExceptionConstants.ILLEGAL_ACCESS_ERROR;
/*     */     
/* 143 */     return cs;
/*     */   }
/*     */   
/*     */   public ObjectType getLoadClassType(ConstantPoolGen cpg) {
/* 147 */     Type t = getType(cpg);
/*     */     
/* 149 */     if (t instanceof ArrayType) {
/* 150 */       t = ((ArrayType)t).getBasicType();
/*     */     }
/*     */     
/* 153 */     return (t instanceof ObjectType) ? (ObjectType)t : null;
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
/* 165 */     v.visitLoadClass(this);
/* 166 */     v.visitAllocationInstruction(this);
/* 167 */     v.visitExceptionThrower(this);
/* 168 */     v.visitTypedInstruction(this);
/* 169 */     v.visitCPInstruction(this);
/* 170 */     v.visitMULTIANEWARRAY(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/MULTIANEWARRAY.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */