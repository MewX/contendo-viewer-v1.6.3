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
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class INVOKEINTERFACE
/*     */   extends InvokeInstruction
/*     */ {
/*     */   private int nargs;
/*     */   
/*     */   INVOKEINTERFACE() {}
/*     */   
/*     */   public INVOKEINTERFACE(int index, int nargs) {
/*  80 */     super((short)185, index);
/*  81 */     this.length = 5;
/*     */     
/*  83 */     if (nargs < 1) {
/*  84 */       throw new ClassGenException("Number of arguments must be > 0 " + nargs);
/*     */     }
/*  86 */     this.nargs = nargs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(DataOutputStream out) throws IOException {
/*  94 */     out.writeByte(this.opcode);
/*  95 */     out.writeShort(this.index);
/*  96 */     out.writeByte(this.nargs);
/*  97 */     out.writeByte(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCount() {
/* 104 */     return this.nargs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
/* 112 */     super.initFromFile(bytes, wide);
/*     */     
/* 114 */     this.length = 5;
/* 115 */     this.nargs = bytes.readUnsignedByte();
/* 116 */     bytes.readByte();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(ConstantPool cp) {
/* 123 */     return super.toString(cp) + " " + this.nargs;
/*     */   }
/*     */   
/*     */   public int consumeStack(ConstantPoolGen cpg) {
/* 127 */     return this.nargs;
/*     */   }
/*     */   
/*     */   public Class[] getExceptions() {
/* 131 */     Class[] cs = new Class[4 + ExceptionConstants.EXCS_INTERFACE_METHOD_RESOLUTION.length];
/*     */     
/* 133 */     System.arraycopy(ExceptionConstants.EXCS_INTERFACE_METHOD_RESOLUTION, 0, cs, 0, ExceptionConstants.EXCS_INTERFACE_METHOD_RESOLUTION.length);
/*     */ 
/*     */     
/* 136 */     cs[ExceptionConstants.EXCS_INTERFACE_METHOD_RESOLUTION.length + 3] = ExceptionConstants.INCOMPATIBLE_CLASS_CHANGE_ERROR;
/* 137 */     cs[ExceptionConstants.EXCS_INTERFACE_METHOD_RESOLUTION.length + 2] = ExceptionConstants.ILLEGAL_ACCESS_ERROR;
/* 138 */     cs[ExceptionConstants.EXCS_INTERFACE_METHOD_RESOLUTION.length + 1] = ExceptionConstants.ABSTRACT_METHOD_ERROR;
/* 139 */     cs[ExceptionConstants.EXCS_INTERFACE_METHOD_RESOLUTION.length] = ExceptionConstants.UNSATISFIED_LINK_ERROR;
/*     */     
/* 141 */     return cs;
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
/* 153 */     v.visitExceptionThrower(this);
/* 154 */     v.visitTypedInstruction(this);
/* 155 */     v.visitStackConsumer(this);
/* 156 */     v.visitStackProducer(this);
/* 157 */     v.visitLoadClass(this);
/* 158 */     v.visitCPInstruction(this);
/* 159 */     v.visitFieldOrMethod(this);
/* 160 */     v.visitInvokeInstruction(this);
/* 161 */     v.visitINVOKEINTERFACE(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/INVOKEINTERFACE.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */