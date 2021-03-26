/*     */ package org.apache.bcel.generic;
/*     */ 
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.bcel.Constants;
/*     */ import org.apache.bcel.classfile.Constant;
/*     */ import org.apache.bcel.classfile.ConstantPool;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class InvokeInstruction
/*     */   extends FieldOrMethod
/*     */   implements ExceptionThrower, StackConsumer, StackProducer, TypedInstruction
/*     */ {
/*     */   InvokeInstruction() {}
/*     */   
/*     */   protected InvokeInstruction(short opcode, int index) {
/*  78 */     super(opcode, index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(ConstantPool cp) {
/*  85 */     Constant c = cp.getConstant(this.index);
/*  86 */     StringTokenizer tok = new StringTokenizer(cp.constantToString(c));
/*     */     
/*  88 */     return Constants.OPCODE_NAMES[this.opcode] + " " + tok.nextToken().replace('.', '/') + tok.nextToken();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int consumeStack(ConstantPoolGen cpg) {
/*     */     int j;
/*  98 */     String signature = getSignature(cpg);
/*  99 */     Type[] args = Type.getArgumentTypes(signature);
/*     */ 
/*     */     
/* 102 */     if (this.opcode == 184) {
/* 103 */       j = 0;
/*     */     } else {
/* 105 */       j = 1;
/*     */     } 
/* 107 */     int n = args.length;
/* 108 */     for (int i = 0; i < n; i++) {
/* 109 */       j += args[i].getSize();
/*     */     }
/* 111 */     return j;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int produceStack(ConstantPoolGen cpg) {
/* 120 */     return getReturnType(cpg).getSize();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getType(ConstantPoolGen cpg) {
/* 126 */     return getReturnType(cpg);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMethodName(ConstantPoolGen cpg) {
/* 132 */     return getName(cpg);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getReturnType(ConstantPoolGen cpg) {
/* 138 */     return Type.getReturnType(getSignature(cpg));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Type[] getArgumentTypes(ConstantPoolGen cpg) {
/* 144 */     return Type.getArgumentTypes(getSignature(cpg));
/*     */   }
/*     */   
/*     */   public abstract Class[] getExceptions();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/generic/InvokeInstruction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */